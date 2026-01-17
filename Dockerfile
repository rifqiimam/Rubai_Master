# Build stage - Use official Maven image
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /build

# Copy only pom.xml first for better caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests -B

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

RUN mkdir -p /app/config /app/images /app/logs

EXPOSE 8200

# Make external config optional so env vars can be used instead
ENTRYPOINT ["java", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-Dspring.config.import=optional:file:/app/config/application-prod.properties", \
    "-Dspring.profiles.active=prod", \
    "-jar", \
    "app.jar"]