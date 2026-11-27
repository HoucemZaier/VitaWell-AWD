package com.vitawell.fitness.service;

import com.vitawell.fitness.document.FitnessSession;
import com.vitawell.fitness.repository.FitnessSessionRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessSessionService {

    private final FitnessSessionRepository fitnessSessionRepository;
    private final SequenceService sequenceService;

    // Injection du repository
    public FitnessSessionService(
            FitnessSessionRepository fitnessSessionRepository,
            SequenceService sequenceService) {
        this.fitnessSessionRepository = fitnessSessionRepository;
        this.sequenceService = sequenceService;
    }

    // CREATE : créer une séance sportive
    public FitnessSession createFitnessSession(
            FitnessSession fitnessSession) {

        fitnessSession.setId(sequenceService.nextValue("fitness_sessions"));
        return fitnessSessionRepository.save(fitnessSession);
    }

    // READ : récupérer toutes les séances
    public List<FitnessSession> getAllFitnessSessions() {
        return fitnessSessionRepository.findAll();
    }

    // READ : récupérer une séance par son ID
    public FitnessSession getFitnessSessionById(Integer id) {

        return fitnessSessionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Fitness session not found with id: " + id
                        )
                );
    }

    // READ : récupérer les séances d'un utilisateur
    public List<FitnessSession> getSessionsByUser(String userId) {
        return fitnessSessionRepository.findByUserId(userId);
    }

    // READ : récupérer les séances terminées d'un utilisateur
    public List<FitnessSession> getCompletedSessions(String userId) {
        return fitnessSessionRepository
                .findByUserIdAndCompleted(userId, true);
    }

    // READ : rechercher les séances par type d'activité
    public List<FitnessSession> getSessionsByActivityType(
            String activityType) {

        return fitnessSessionRepository
                .findByActivityType(activityType);
    }

    // READ : récupérer les séances d'un programme
    public List<FitnessSession> getSessionsByWorkoutPlan(
            Integer workoutPlanId) {

        return fitnessSessionRepository
                .findByWorkoutPlanId(workoutPlanId);
    }

    // UPDATE : modifier une séance
    public FitnessSession updateFitnessSession(
            Integer id,
            FitnessSession updatedSession) {

        FitnessSession existingSession = getFitnessSessionById(id);

        updatedSession.setId(existingSession.getId());

        // Conserver le propriétaire de la séance
        updatedSession.setUserId(existingSession.getUserId());

        return fitnessSessionRepository.save(updatedSession);
    }

    // DELETE : supprimer une séance
    public void deleteFitnessSession(Integer id) {

        FitnessSession existingSession = getFitnessSessionById(id);

        fitnessSessionRepository.delete(existingSession);
    }
}
