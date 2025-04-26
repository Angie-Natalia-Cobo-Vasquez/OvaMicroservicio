FROM alpine:latest
LABEL authors="angie-cobo"
WORKDIR /app
COPY target/OvaMicroservicio-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "OvaMicroservicio-0.0.1-SNAPSHOT.jar"]