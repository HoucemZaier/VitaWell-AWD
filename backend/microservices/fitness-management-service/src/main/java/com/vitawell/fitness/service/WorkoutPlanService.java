package com.vitawell.fitness.service;

import com.vitawell.fitness.document.WorkoutPlan;
import com.vitawell.fitness.repository.WorkoutPlanRepository;
import com.vitawell.fitness.service.SequenceService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final SequenceService sequenceService;

    // Injection du repository
    public WorkoutPlanService(
            WorkoutPlanRepository workoutPlanRepository,
            SequenceService sequenceService) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.sequenceService = sequenceService;
    }

    // CREATE : créer un programme
    public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan) {
        workoutPlan.setId(sequenceService.nextValue("workout_plans"));
        workoutPlan.setCreatedAt(LocalDateTime.now());
        return workoutPlanRepository.save(workoutPlan);
    }

    // READ : récupérer tous les programmes
    public List<WorkoutPlan> getAllWorkoutPlans() {
        return workoutPlanRepository.findAll();
    }

    // READ : récupérer un programme par son ID
    public WorkoutPlan getWorkoutPlanById(Integer id) {

        return workoutPlanRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Workout plan not found with id: " + id
                        )
                );
    }

    // READ : récupérer les programmes d'un utilisateur
    public List<WorkoutPlan> getWorkoutPlansByUser(String userId) {
        return workoutPlanRepository.findByUserId(userId);
    }

    // READ : récupérer les programmes actifs d'un utilisateur
    public List<WorkoutPlan> getActiveWorkoutPlans(String userId) {
        return workoutPlanRepository
                .findByUserIdAndActive(userId, true);
    }

    // READ : rechercher les programmes par objectif
    public List<WorkoutPlan> getWorkoutPlansByGoal(String goal) {
        return workoutPlanRepository.findByGoal(goal);
    }

    // UPDATE : modifier un programme
    public WorkoutPlan updateWorkoutPlan(
            Integer id,
            WorkoutPlan updatedPlan) {

        WorkoutPlan existingPlan = getWorkoutPlanById(id);

        updatedPlan.setId(existingPlan.getId());

        // Conserver la date initiale de création
        updatedPlan.setCreatedAt(existingPlan.getCreatedAt());

        // Conserver le propriétaire du programme
        updatedPlan.setUserId(existingPlan.getUserId());

        return workoutPlanRepository.save(updatedPlan);
    }

    // DELETE : supprimer un programme
    public void deleteWorkoutPlan(Integer id) {

        WorkoutPlan existingPlan = getWorkoutPlanById(id);

        workoutPlanRepository.delete(existingPlan);
    }
}
