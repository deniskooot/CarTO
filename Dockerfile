

# Build stage
FROM gradle:9.3.1-jdk25 AS build

COPY . .

RUN gradle build -x test --no-daemon --stacktrace --parallel --build-cache --configuration-cache


## Runtime stage
FROM eclipse-temurin:25-jre AS cartodokerrunner

WORKDIR /app

COPY --from=build /home/gradle/build/libs/Denis-*.jar /app/app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]



