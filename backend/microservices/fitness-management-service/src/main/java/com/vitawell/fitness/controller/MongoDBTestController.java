package com.vitawell.fitness.controller;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoDBTestController {

    private final MongoTemplate mongoTemplate;

    public MongoDBTestController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/api/fitness/test-mongodb")
    public String testMongoDB() {

        String databaseName = mongoTemplate
                .getDb()
                .getName();

        return "Connexion MongoDB réussie ! Base : " + databaseName;
    }
}