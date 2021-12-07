package com.ktymty.gamescatalogservice.service;

import com.ktymty.gamescatalogservice.model.Game;
import com.ktymty.gamescatalogservice.repository.GamesCatalogRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GamesCatalogServiceTest {

    @MockBean
    private GamesCatalogRepository gamesCatalogRepository;

    @MockBean
    private GamesRatingService gamesRatingService;

    private GamesCatalogService gamesCatalogService;
    private Game game;

    @BeforeEach
    void setUp() {
        gamesCatalogService = new GamesCatalogService(gamesCatalogRepository,gamesRatingService);
        game = Game.builder().name("Game1").price("50,00€").gameId("tt2382320").build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        gamesCatalogService.save(game);
        verify(gamesCatalogRepository, times(1)).save(any(Game.class));
    }

    @Test
    void findAll() {
        gamesCatalogService.findAll();
        verify(gamesCatalogRepository, times(1)).findAll();
    }
}