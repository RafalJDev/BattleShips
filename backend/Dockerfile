FROM openjdk:8-jre-alpine

COPY ["target/*.jar", "/app/"]

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/backend.jar"]