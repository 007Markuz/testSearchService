FROM adoptopenjdk/openjdk11:alpine-slim

EXPOSE 8080

COPY build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]