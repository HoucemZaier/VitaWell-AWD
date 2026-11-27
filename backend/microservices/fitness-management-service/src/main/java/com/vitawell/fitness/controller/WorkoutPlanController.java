package com.vitawell.fitness.controller;

import com.vitawell.fitness.document.WorkoutPlan;
import com.vitawell.fitness.service.WorkoutPlanService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fitness/plans")
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;

    // Injection du service
    public WorkoutPlanController(WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }

    // 1. Créer un programme
    @PostMapping
    public ResponseEntity<WorkoutPlan> createWorkoutPlan(
            @RequestBody WorkoutPlan workoutPlan) {

        WorkoutPlan createdPlan =
                workoutPlanService.createWorkoutPlan(workoutPlan);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdPlan);
    }

    // 2. Récupérer tous les programmes
    @GetMapping
    public ResponseEntity<List<WorkoutPlan>> getAllWorkoutPlans() {

        return ResponseEntity.ok(
                workoutPlanService.getAllWorkoutPlans()
        );
    }

    // 3. Récupérer un programme par son ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlan> getWorkoutPlanById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                workoutPlanService.getWorkoutPlanById(id)
        );
    }

    // 4. Récupérer les programmes d'un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutPlan>> getWorkoutPlansByUser(
            @PathVariable String userId) {

        return ResponseEntity.ok(
                workoutPlanService.getWorkoutPlansByUser(userId)
        );
    }

    // 5. Récupérer les programmes actifs d'un utilisateur
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<WorkoutPlan>> getActiveWorkoutPlans(
            @PathVariable String userId) {

        return ResponseEntity.ok(
                workoutPlanService.getActiveWorkoutPlans(userId)
        );
    }

    // 6. Rechercher les programmes par objectif
    @GetMapping("/goal/{goal}")
    public ResponseEntity<List<WorkoutPlan>> getWorkoutPlansByGoal(
            @PathVariable String goal) {

        return ResponseEntity.ok(
                workoutPlanService.getWorkoutPlansByGoal(goal)
        );
    }

    // 7. Modifier un programme
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlan> updateWorkoutPlan(
            @PathVariable Integer id,
            @RequestBody WorkoutPlan workoutPlan) {

        return ResponseEntity.ok(
                workoutPlanService.updateWorkoutPlan(id, workoutPlan)
        );
    }

    // 8. Supprimer un programme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(
            @PathVariable Integer id) {

        workoutPlanService.deleteWorkoutPlan(id);

        return ResponseEntity.noContent().build();
    }
}