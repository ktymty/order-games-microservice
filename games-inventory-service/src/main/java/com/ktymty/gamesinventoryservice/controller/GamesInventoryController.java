package com.ktymty.gamesinventoryservice.controller;

import com.ktymty.gamesinventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class GamesInventoryController {

    public final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    Boolean isInStock(@PathVariable String skuCode) {
        log.info("Checking stock for product with skucode - " + skuCode);
        return inventoryService.isInStock(skuCode);
    }

}

