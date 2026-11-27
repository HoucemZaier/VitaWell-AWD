package com.vitawell.fitness.repository;

import com.vitawell.fitness.document.FitnessSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FitnessSessionRepository
        extends MongoRepository<FitnessSession, Integer> {

    // Rechercher les séances d'un utilisateur
    List<FitnessSession> findByUserId(String userId);

    // Rechercher les séances terminées d'un utilisateur
    List<FitnessSession> findByUserIdAndCompleted(
            String userId,
            boolean completed
    );

    // Rechercher les séances par type d'activité
    List<FitnessSession> findByActivityType(String activityType);

    // Rechercher les séances associées à un programme
    List<FitnessSession> findByWorkoutPlanId(Integer workoutPlanId);
}