# ======================
# Build stage
# ======================
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml và tải dependencies để cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code và build jar
COPY src ./src
RUN mvn clean package -DskipTests

# ======================
# Runtime stage
# ======================
FROM eclipse-temurin:21-jre   # JRE nhẹ hơn JDK
WORKDIR /app

# Cài curl cho healthcheck
RUN apt-get update && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

# Copy jar từ build stage
COPY --from=build /app/target/*.jar app.jar

# Tạo user không phải root
RUN useradd -ms /bin/bash appuser
USER appuser

# Expose cổng Spring Boot
EXPOSE 8080

# Thêm HEALTHCHECK để kiểm tra service
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Chạy app
ENTRYPOINT ["java","-jar","app.jar"]
