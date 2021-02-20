FROM openjdk:11

COPY . .

ENTRYPOINT ["./gradlew", "build"]
CMD ["java", "-jar", "build/libs/hello-spring-0.0.1-SNAPSHOT.jar"]