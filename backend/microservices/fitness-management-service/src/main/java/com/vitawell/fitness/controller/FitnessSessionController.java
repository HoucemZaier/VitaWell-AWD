package com.vitawell.fitness.controller;

import com.vitawell.fitness.document.FitnessSession;
import com.vitawell.fitness.service.FitnessSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fitness/sessions")
public class FitnessSessionController {

    private final FitnessSessionService fitnessSessionService;

    public FitnessSessionController(FitnessSessionService fitnessSessionService) {
        this.fitnessSessionService = fitnessSessionService;
    }

    // 1. Créer une séance sportive
    @PostMapping
    public ResponseEntity<FitnessSession> createFitnessSession(
            @RequestBody FitnessSession fitnessSession) {

        FitnessSession createdSession =
                fitnessSessionService.createFitnessSession(fitnessSession);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdSession);
    }

    // 2. Récupérer toutes les séances
    @GetMapping
    public ResponseEntity<List<FitnessSession>> getAllFitnessSessions() {
        return ResponseEntity.ok(fitnessSessionService.getAllFitnessSessions());
    }

    // 3. Récupérer une séance par son ID
    @GetMapping("/{id}")
    public ResponseEntity<FitnessSession> getFitnessSessionById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                fitnessSessionService.getFitnessSessionById(id)
        );
    }

    // 4. Récupérer les séances d'un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FitnessSession>> getSessionsByUser(
            @PathVariable String userId) {

        return ResponseEntity.ok(
                fitnessSessionService.getSessionsByUser(userId)
        );
    }

    // 5. Récupérer les séances terminées d'un utilisateur
    @GetMapping("/user/{userId}/completed")
    public ResponseEntity<List<FitnessSession>> getCompletedSessions(
            @PathVariable String userId) {

        return ResponseEntity.ok(
                fitnessSessionService.getCompletedSessions(userId)
        );
    }

    // 6. Rechercher les séances par type d'activité
    @GetMapping("/activity/{activityType}")
    public ResponseEntity<List<FitnessSession>> getSessionsByActivityType(
            @PathVariable String activityType) {

        return ResponseEntity.ok(
                fitnessSessionService.getSessionsByActivityType(activityType)
        );
    }

    // 7. Récupérer les séances d'un programme
    @GetMapping("/workout-plan/{workoutPlanId}")
    public ResponseEntity<List<FitnessSession>> getSessionsByWorkoutPlan(
            @PathVariable Integer workoutPlanId) {

        return ResponseEntity.ok(
                fitnessSessionService.getSessionsByWorkoutPlan(workoutPlanId)
        );
    }

    // 8. Modifier une séance
    @PutMapping("/{id}")
    public ResponseEntity<FitnessSession> updateFitnessSession(
            @PathVariable Integer id,
            @RequestBody FitnessSession fitnessSession) {

        return ResponseEntity.ok(
                fitnessSessionService.updateFitnessSession(id, fitnessSession)
        );
    }

    // 9. Supprimer une séance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFitnessSession(
            @PathVariable Integer id) {

        fitnessSessionService.deleteFitnessSession(id);
        return ResponseEntity.noContent().build();
    }
}
