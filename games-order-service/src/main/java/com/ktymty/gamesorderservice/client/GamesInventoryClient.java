package com.ktymty.gamesorderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GAMES-INVENTORY-SERVICE")
public interface GamesInventoryClient {
    @GetMapping("/v1/api/inventory/{skuCode}")
    Boolean checkStock(@PathVariable String skuCode);
}
