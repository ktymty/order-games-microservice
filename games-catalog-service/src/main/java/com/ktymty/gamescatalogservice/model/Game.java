package com.ktymty.gamescatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "game")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Game {
    @Id
    private String id;
    private String name;
    private String price;
    private String gameId;
}
