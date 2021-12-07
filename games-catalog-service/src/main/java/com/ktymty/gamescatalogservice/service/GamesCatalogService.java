package com.ktymty.gamescatalogservice.service;

import com.ktymty.gamescatalogservice.model.Game;
import com.ktymty.gamescatalogservice.model.GameDto;
import com.ktymty.gamescatalogservice.model.GameInfo;
import com.ktymty.gamescatalogservice.repository.GamesCatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class GamesCatalogService {
    private final GamesCatalogRepository gamesCatalogRepository;
    private final GamesRatingService gamesRatingService;

    public Game save(Game game) {
        log.info("Inside save of GamesCatalogService");
        return gamesCatalogRepository.save(game);
    }

    public List<GameDto> findAll() {
        log.info("Inside findAll of GamesCatalogService");
        List<Game> games = gamesCatalogRepository.findAll();
        return games.stream()
                .flatMap(game -> Stream.of(getGameRatingAndDescription(game)))
                .collect(Collectors.toList());
    }

    private GameDto getGameRatingAndDescription(Game game) {
        GameInfo gameInfo = gamesRatingService.getRatingAndDescription(game.getGameId());
        return GameDto.builder()
                .id(game.getId())
                .name(game.getName())
                .rating(gameInfo.getImdbRating())
                .description(gameInfo.getPlot())
                .price(game.getPrice())
                .build();
    }

    public void deleteGame(String id){
        gamesCatalogRepository.deleteById(id);
    }
}
