FROM openjdk:23
LABEL authors="angiecobo"
WORKDIR /app
COPY target/ova-microservicio-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "ova-microservicio-0.0.1-SNAPSHOT.jar"]
