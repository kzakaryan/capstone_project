FROM alpine:latest AS base

RUN apk add git openjdk21

WORKDIR /app

COPY . /app

RUN ./mvnw clean package
RUN cp target/project-local-repo/capstone_project/launcher/4.0.0/launcher-4.0.0.jar ./app-jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-jar"]