# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml và download dependencies trước để cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code và build jar
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy jar từ build stage
COPY --from=build /app/target/*.jar app.jar

# Expose cổng mặc định Spring Boot
EXPOSE 8080

# Run application
ENTRYPOINT ["java","-jar","app.jar"]
