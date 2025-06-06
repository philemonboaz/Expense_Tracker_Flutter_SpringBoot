

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
