

# Build stage
FROM gradle:jdk21-alpine AS build

COPY . .

RUN gradle build -x test --no-daemon --stacktrace --parallel --build-cache


## Runtime stage
FROM eclipse-temurin:21-jre AS cartodokerrunner

WORKDIR /app

COPY --from=build /home/gradle/build/libs/Denis-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]



