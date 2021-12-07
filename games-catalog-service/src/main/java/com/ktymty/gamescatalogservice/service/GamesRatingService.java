package com.ktymty.gamescatalogservice.service;

import com.ktymty.gamescatalogservice.exception.GameNotFoundException;
import com.ktymty.gamescatalogservice.model.GameInfo;
import com.ktymty.gamescatalogservice.utility.UrlGenerator;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class GamesRatingService {
    private final UrlGenerator urlGenerator;
    private final RestTemplate restTemplate;

    @Retry(name = "retryService", fallbackMethod = "localCacheGameInfoSearch")
    public GameInfo getRatingAndDescription(final String gameId) {
        log.info("Inside getRatingAndDescription of GamesRatingService");
        try {
            final var response = restTemplate.getForEntity(urlGenerator.generate(gameId), GameInfo.class);
            return response.getBody();
        }catch (Exception ex){
            throw new GameNotFoundException("Game id '" + gameId + "' does no exist");
        }
    }

    public GameInfo localCacheGameInfoSearch(String data, Throwable t) {
        log.error("Inside localCacheGameInfoSearch, cause – {}", t.toString());
        System.out.println("Returning search results from cache");
        //TODO: fetch results from the cache
        return GameInfo.builder().imdbRating("8.9").plot("Game plot").build();
    }
}
