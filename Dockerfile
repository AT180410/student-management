# ======================
# Build stage
# ======================
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy source code vào container
COPY . .

# Build jar với Maven Wrapper
RUN chmod +x ./mvnw && ./mvnw -B package -DskipTests


# ======================
# Runtime stage
# ======================
# Dùng JRE nhẹ hơn JDK để giảm size
FROM eclipse-temurin:21-jre
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
