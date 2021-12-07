package com.ktymty.gamescatalogservice.controller;

import com.ktymty.gamescatalogservice.model.Game;
import com.ktymty.gamescatalogservice.model.GameDto;
import com.ktymty.gamescatalogservice.service.GamesCatalogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GamesCatalogController.class)
class GamesCatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GamesCatalogService gamesCatalogService;

    private GameDto gameDto;
    private Game game;

    @BeforeEach
    void setUp() {
        gameDto = GameDto.builder().id("1").name("Game").price("20,09€").description("Game description").rating("8.5").build();
        game = Game.builder().name("Game1").price("50,00€").gameId("tt2382320").build();
    }

    @AfterEach
    void tearDown() {
        gameDto = null;
        game = null;
    }

    @Test
    void findAll() throws Exception {
        String expectedResponse = "[{\"id\":\"1\",\"name\":\"Game\",\"description\":\"Game description\",\"price\":\"20,09€\",\"rating\":\"8.5\"}]";

        when(gamesCatalogService.findAll()).thenReturn(Collections.singletonList(gameDto));

        mockMvc.perform(get("/v1/api/catalog"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    void saveGame() throws Exception {

        when(gamesCatalogService.save(game)).thenReturn(Game.builder().id("1").name("Game1").price("50,00€").gameId("tt2382320").build());

        mockMvc.perform(
                        post("/v1/api/catalog").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Game 1\",\"price\": \"50,00€\",\"gameId\": \"tt2382320\"}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}