<<<<<<< HEAD
VitaWell est un mini-projet consacré à la promotion d'un mode de vie sain. Il permet aux utilisateurs de suivre leur nutrition, leurs activités sportives, leur hydratation et leur sommeil grâce à une architecture basée sur les microservices.
=======
# VitaWell

VitaWell est une application organisée en microservices avec :

- **React** pour le frontend ;
- **Spring Boot** pour les services backend ;
- **Eureka** pour la découverte des services ;
- **Spring Cloud Gateway** comme point d'entrée API ;
- **Docker Compose** pour l'infrastructure locale.

## Structure du projet

```text
VitaWell/
├── frontend/
│   └── vitawell-frontend/
├── backend/
│   ├── eureka-server/
│   ├── api-gateway/
│   ├── user-service/
│   └── microservices/
│       ├── sleep-tracking-service/
│       ├── sleep-planning-service/
│       ├── nutrition-service/
│       ├── hydration-service/
│       └── fitness-management-service/
├── infrastructure/
│   └── docker/
│       └── docker-compose.yml
└── docs/
    ├── architecture/
    ├── api/
    └── database/
```

## Démarrage

Les modules frontend et backend seront ajoutés indépendamment dans leurs dossiers respectifs. Les services d'infrastructure locale peuvent ensuite être démarrés avec :

```bash
docker compose -f infrastructure/docker/docker-compose.yml up -d
```

>>>>>>> 68ed40b (add project structure)
