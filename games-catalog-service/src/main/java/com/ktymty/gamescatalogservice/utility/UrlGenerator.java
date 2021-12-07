package com.ktymty.gamescatalogservice.utility;

import com.ktymty.gamescatalogservice.config.OmdbConfigurationProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(value = OmdbConfigurationProperties.class)
public class UrlGenerator {

    private final OmdbConfigurationProperties omdbConfigurationProperties;

    public String generate(final String gameId) {
        final var properties = omdbConfigurationProperties.getOmdb();
        return properties.getUrl().replace("{key}", properties.getApiKey()).replace("{imdbId}", gameId).trim()
                .replace(" ", "_");
    }

}
