# ---- Build Stage ----
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy Maven config first (so dependencies can be cached)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# ---- Runtime Stage ----
FROM openjdk:21-jdk AS runner

WORKDIR /app

# Copy the built JAR (grab the only .jar from target/)
COPY --from=builder /app/target/*.jar /app/app.jar

EXPOSE 1567

CMD ["java", "-jar", "/app/app.jar", "--debug"]
