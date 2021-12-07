package com.ktymty.gamesinventoryservice.service;

import com.ktymty.gamesinventoryservice.model.Inventory;
import com.ktymty.gamesinventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Boolean isInStock(String skuCode) {
        log.info("Inside isInStock of InventoryService.");
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Cannot Find Product by skuCode " + skuCode));
        return inventory.getStock() > 0;
    }

}
