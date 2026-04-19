FROM maven:3.8.6-eclipse-temurin-8 AS builder

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests -Dmaven.repo.local=/root/.m2/repository

FROM eclipse-temurin:8-jre-alpine

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8885

ENTRYPOINT ["java", "-jar", "app.jar"]
