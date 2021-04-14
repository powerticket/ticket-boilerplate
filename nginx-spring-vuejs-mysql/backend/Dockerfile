FROM openjdk:11 AS build-stage
COPY . .
RUN ./gradlew build

FROM openjdk:11 AS prod-stage
COPY --from=build-stage build/libs/backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
