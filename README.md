# CarTO Application

Car maintenance schedule app shows upcoming work on the car.

Java backend application built using Spring Boot and Docker. The project includes settings for working with PostgreSQL, Liquibase migrations, and Swagger.

Using: </br>
-- Java 25 Docker, The project includes settings for working with PostgreSQL, Liquibase migrations, Swagger (springdoc-openapi). </br>
-- Spring Boot 4 </br>
-- Gradle </br>
-- PostgreSQL </br>
-- Liquibase </br>
-- Docker, Docker Compose </br>

# Project structure

```
src/main/java/com/github/Denis/
├── external_info_files/        # Non required files. Script for handle create database structure including all changesets.
├── gradle/                     # Gradle wrapper and config
├── src/main/       
    ├── java/
        ├── config/             # Local spring configuration
        ├── controller/         # REST-controllers
        ├── converter/          # Duration type converter
        ├── dto/                # DTO-classes
        ├── entity/             # JPA-entities
        ├── repository/         # JPA-repositories
        ├── service/            # Business-logic
        ├── utils/              # Utils for string processing 
        └── CarToApplication    # Main application class
    ├── resources/
        └── db.changelog        # Liquibase config and changelog
    └── application.properties  # Properties file
├── .dockerignore               # Docker ignored files
├── .gitignore                  # Git ignored files
├── build.gradle                # Gradle config and dependencies
├── carto.env                   # Local env. secret file
├── docker-compose              # Multistage build (database + application)
├── Dockerfile                  # Build container config
└── settings.gradle             # Gradle build settings
```
# Running App in DEV mode:

It can be useful to run a local instance of the PostgreSQL database for development.
```bash
docker compose up -d postgres-local
```
Then open IntelliJ IDEA and run [`LocalRun`](./.run/LocalRun.run.xml) configuration.
Alternatively, you can run gradlew task directly:
```bash
SPRING_PROFILES_ACTIVE=local ./gradlew bootRun
```

# Running App in PROD mode:

Create [the file with production DB credentials](./carto.env) :

```env
POSTGRES_URL=jdbc:postgresql://postgres-prod:5432/
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=<your_password_here>
```

Run app:

```bash
docker compose up --build
```
You can view application endpoints by Swagger UI (if on): http://localhost:8080/swagger-ui.html

If you don't use Docker, you can try this 
(adjust [postgres settings](carto.env) if needed):
```bash
SPRING_PROFILES_ACTIVE=prod ./gradlew bootRun
```
Please note that the PROD database is not connectable from outside the docker network.

# Database migration:

Migrations are managed via Liquibase. Start [here](src/main/resources/db/changelog/db.changelog-master.yaml)

# Frontend application
The frontend of the application is currently being prepared for release to the public repository.
