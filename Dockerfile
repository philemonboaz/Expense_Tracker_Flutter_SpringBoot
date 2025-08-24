#
#
## Use an official OpenJDK runtime as base image
#FROM openjdk:17-jdk-slim
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the JAR file into the container
#COPY target/*.jar app.jar
#
## Expose port 8080
#EXPOSE 9090
#
## Run the application
#ENTRYPOINT ["java", "-jar", "app.jar"]


## Step 1: Build the JAR
#FROM maven:3.9.4-eclipse-temurin-17 AS builder
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests
#
## Step 2: Run the app
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY --from=builder /app/target/*.jar app.jar
#EXPOSE 9090
#ENTRYPOINT ["java", "-jar", "app.jar"]


# Use an official OpenJDK runtime as base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]