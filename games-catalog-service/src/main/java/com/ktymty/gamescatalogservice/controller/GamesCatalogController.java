package com.ktymty.gamescatalogservice.controller;

import com.ktymty.gamescatalogservice.model.Game;
import com.ktymty.gamescatalogservice.model.GameDto;
import com.ktymty.gamescatalogservice.service.GamesCatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/catalog")
@RequiredArgsConstructor
public class GamesCatalogController {
    private final GamesCatalogService gamesCatalogService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameDto> findAllGames() {
        log.info("Inside findAllGames of GamesCatalogController");
        return gamesCatalogService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game saveGame(@RequestBody Game game) {
        log.info("Inside saveGame of GamesCatalogController");
        return gamesCatalogService.save(game);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteGame(@RequestBody String id) {
        log.info("Inside deleteGame of GamesCatalogController");
        gamesCatalogService.deleteGame(id);
    }
}
