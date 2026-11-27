package com.vitawell.fitness.repository;
import com.vitawell.fitness.document.WorkoutPlan;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkoutPlanRepository
        extends MongoRepository<WorkoutPlan, Integer> {

    // Rechercher les programmes d'un utilisateur
    List<WorkoutPlan> findByUserId(String userId);

    // Rechercher les programmes actifs d'un utilisateur
    List<WorkoutPlan> findByUserIdAndActive(
            String userId,
            boolean active
    );

    // Rechercher les programmes par objectif
    List<WorkoutPlan> findByGoal(String goal);
}
