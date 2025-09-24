# ======================
# Build stage
# ======================
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# ======================
# Runtime stage
# ======================
FROM eclipse-temurin:21-jre-ubi9-minimal AS runtime
WORKDIR /app

# Ensure latest security patches
RUN microdnf update -y && microdnf clean all

# Copy jar from build
COPY --from=build /app/target/*.jar app.jar

# Create non-root user
RUN useradd -ms /bin/bash appuser
USER appuser

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java","-jar","app.jar"]
