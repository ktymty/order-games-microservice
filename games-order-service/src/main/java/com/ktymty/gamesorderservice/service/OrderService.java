package com.ktymty.gamesorderservice.service;

import com.ktymty.gamesorderservice.client.GamesInventoryClient;
import com.ktymty.gamesorderservice.dto.OrderDto;
import com.ktymty.gamesorderservice.model.Order;
import com.ktymty.gamesorderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final StreamBridge streamBridge;
    private final OrderRepository orderRepository;
    private final ExecutorService traceableExecutorService; // TraceableExecutorService applies sleuth traceId to outgoing request
    private final GamesInventoryClient gamesInventoryClient;
    private final Resilience4JCircuitBreakerFactory resilience4JCircuitBreakerFactory;

    public String placeOrder(OrderDto orderDto) {
        log.info("Inside placeOrder of OrderService");

        boolean productsInStock = isProductsInStock(orderDto);

        return placeOrder(orderDto, productsInStock);
    }

    private String placeOrder(OrderDto orderDto, boolean productsInStock) {
        if (productsInStock) {
            Order order = new Order();
            order.setOrderLineItems(orderDto.getOrderLineItemsList());
            order.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(order);
            log.info("Sending Order Details with Order Id {} to Notification Service", order.getId());
            // MessageBuilder used to wrap payload in Message object so that same traceId is propagated to Notification Service.
            streamBridge.send("notificationEventSupplier-out-0", MessageBuilder.withPayload(order.getId()).build());
            return "Order Place Successfully";
        } else {
            return "Order Failed - One of the Product in your Order is out of stock";
        }
    }

    private boolean isProductsInStock(OrderDto orderDto) {
        resilience4JCircuitBreakerFactory.configureExecutorService(traceableExecutorService);
        Resilience4JCircuitBreaker circuitBreaker = resilience4JCircuitBreakerFactory.create("inventory");
        Supplier<Boolean> booleanSupplier = () -> orderDto.getOrderLineItemsList().stream()
                .allMatch(lineItem -> {
                    log.info("Making Call to Games Inventory Service for SkuCode {}", lineItem.getSkuCode());
                    return gamesInventoryClient.checkStock(lineItem.getSkuCode());
                });

        return circuitBreaker.run(booleanSupplier, throwable -> handleErrorCase());
    }

    private Boolean handleErrorCase() {
        return false;
    }

}
