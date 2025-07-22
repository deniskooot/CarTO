# CarTO Application

Car maintenance schedule app shows upcoming work on the car.

Java backend application built using Spring Boot and Docker. The project includes settings for working with PostgreSQL, Liquibase migrations, and Swagger.

Using:
-- Java 21 Docker, The project includes settings for working with PostgreSQL, Liquibase migrations, Swagger (springdoc-openapi).
-- Spring Boot 3
-- Gradle
-- PostgreSQL
-- Liquibase
-- Docker, Docker Compose

# Project structure

src/main/java/com/github/Denis/
├── external_info_files/ # Non required files. Script for handle create database structure including all changesets.
├── gradle/              # Gradle wrapper and config
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

2. Running App:

Create `.env` file:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/dbname
SPRING_DATASOURCE_USERNAME=your_user
SPRING_DATASOURCE_PASSWORD=your_password
SERVER_PORT=8082
APPLICATION_NAME=carTO
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

3. Database migration:

Migrations are managed via Liquibase, main file:
`src/main/resources/db/changelog/db.changelog-master.yaml`

# Frontend application
The frontend of the application is currently being prepared for release to the public repository.
