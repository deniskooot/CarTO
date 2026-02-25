# CarTO Application

Car maintenance schedule app shows upcoming work on the car.

Java backend application built using Spring Boot and Docker. The project includes settings for working with PostgreSQL, Liquibase migrations, and Swagger.

Using: </br>
-- Java 21 Docker, The project includes settings for working with PostgreSQL, Liquibase migrations, Swagger (springdoc-openapi). </br>
-- Spring Boot 3 </br>
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

# Running App:

Create `carto.env` file with production DB credentials:

```env
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=<your_password_here>
```

Run app:

```bash
docker-compose up --build
```
You can view application endpoints by Swagger UI (if on): http://localhost:8082/swagger-ui.html

If you don't use Docker you can try:
```bash
./gradlew build -x test
java -jar build/libs/CarTO-0.0.1-SNAPSHOT.jar
```

# Database migration:

Migrations are managed via Liquibase, main file:
`src/main/resources/db/changelog/db.changelog-master.yaml`

# Frontend application
The frontend of the application is currently being prepared for release to the public repository.
