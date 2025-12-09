# ======================
# Build stage
# ======================
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copy source code
COPY . .

# Build jar với Maven Wrapper
RUN chmod +x ./mvnw && ./mvnw -B clean package -DskipTests


# ======================
# Runtime stage
# ======================
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Update toàn bộ gói để vá CVE + cài curl
RUN apt-get update \
    && apt-get upgrade -y \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

# ✅ Thêm lib cũ có CVE cho Trivy detect 
 RUN mkdir -p /app/vulnerable-libs \
     && curl -L -o /app/vulnerable-libs/jackson-databind-2.9.5.jar \
        https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.9.5/jackson-databind-2.9.5.jar

# Copy jar từ build stage
COPY --from=build /app/target/*.jar app.jar

# Tạo user không phải root để chạy app
RUN useradd -ms /bin/bash appuser
USER appuser

# Expose cổng Spring Boot
EXPOSE 8080

# Healthcheck (Spring Boot Actuator)
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run app
ENTRYPOINT ["java","-jar","app.jar"]
