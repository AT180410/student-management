# ======================
# Build stage
# ======================
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy pom.xml first for better caching
COPY pom.xml .
# Download dependencies and plugins
RUN mvn dependency:go-offline -B

COPY src ./src
# Build the application (skip tests for faster build, run them separately)
RUN mvn clean package -DskipTests -B

# ======================
# Runtime stage - USING JRE + SECURITY UPDATES
# ======================
FROM eclipse-temurin:21-jre-jammy AS runtime

# Install security updates and clean up
RUN apt-get update && \
    apt-get upgrade -y --no-install-recommends && \
    apt-get install -y --no-install-recommends curl ca-certificates && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get clean

# Create app user and group
RUN groupadd -r appuser && useradd -r -g appuser appuser

WORKDIR /app

# Copy the built application from build stage
COPY --from=build --chown=appuser:appuser /app/target/*.jar app.jar

# Switch to non-root user
USER appuser

# Expose application port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Use exec form for better signal handling
ENTRYPOINT ["java", "-jar", "app.jar"]

# Add JVM options for security and performance
CMD ["-Djava.security.egd=file:/dev/./urandom", "-XX:+UseContainerSupport", "-Dspring.profiles.active=prod"]
