# Backend (Spring Boot)

This folder contains the Spring Boot backend application.

Quick commands:

Build and run with Maven wrapper:

```bash
cd fitness
./mvnw package -DskipTests
./mvnw spring-boot:run
```

Docker (built by docker-compose):

```bash
# from repository root
docker compose build backend
docker compose up -d backend
```

Notes:
- The app uses Java 17.
- Configuration is in `src/main/resources/application.properties` and can be overridden with environment variables (e.g., `SPRING_DATASOURCE_URL`).
- Seed data is inserted at startup by a CommandLineRunner if the database is empty.
