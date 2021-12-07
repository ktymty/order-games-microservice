package com.ktymty.gamescatalogservice.service;

import com.ktymty.gamescatalogservice.model.GameInfo;
import com.ktymty.gamescatalogservice.utility.UrlGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GamesRatingServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private UrlGenerator urlGenerator;

    private GamesRatingService gamesRatingService;
    private GameInfo gameInfo;

    @BeforeEach
    void setUp() {
        gamesRatingService = new GamesRatingService(urlGenerator,restTemplate);
        gameInfo = GameInfo.builder().imdbRating("9.0").plot("plot").build();
    }

    @AfterEach
    void tearDown() {
        gameInfo = null;
    }

    @Test
    void getRatingAndDescription() {
        when(restTemplate.getForEntity("url/to/game/rating/service", GameInfo.class)).thenReturn(new ResponseEntity<>(gameInfo, HttpStatus.OK));
        GameInfo actualGameInfo = GameInfo.builder().imdbRating("9.0").plot("plot").build();
        assertEquals(gameInfo, actualGameInfo);
    }
}