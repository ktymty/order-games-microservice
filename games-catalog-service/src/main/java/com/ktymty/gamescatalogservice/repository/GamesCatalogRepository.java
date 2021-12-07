package com.ktymty.gamescatalogservice.repository;

import com.ktymty.gamescatalogservice.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesCatalogRepository extends MongoRepository<Game, String> {

}
