package com.ktymty.gamescatalogservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.ktymty")
public class OmdbConfigurationProperties {

    private OMDB omdb = new OMDB();

    @Data
    public class OMDB {
        private String apiKey;
        private String url;
    }

}
