FROM openjdk:23
LABEL authors="angie-cobo"
WORKDIR /app
COPY target/ova-service-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "ova-service-0.0.1-SNAPSHOT.jar"]