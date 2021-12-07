package com.ktymty.gamesorderservice.controller;

import com.ktymty.gamesorderservice.dto.OrderDto;
import com.ktymty.gamesorderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/order")
@RequiredArgsConstructor
@Slf4j
public class GamesOrderController {

    private final OrderService orderService;

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto) {
        log.info("Placing order for game");
        return orderService.placeOrder(orderDto);
    }
}
