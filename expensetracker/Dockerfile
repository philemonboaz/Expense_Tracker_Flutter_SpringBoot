# Use a Java 17 image (compatible with Spring Boot 3+)
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy your app code
COPY . .

# Give execute permissions to Maven wrapper (important for Linux builds)
RUN chmod +x mvnw

# Package your app
RUN ./mvnw clean package -DskipTests

## Use an official OpenJDK runtime as base image
#FROM eclipse-temurin:17-jdk
##openjdk:17-jdk-slim
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the JAR file into the container
#COPY . .
##target/*.jar app.jar
#
## Expose port 8080
## EXPOSE 9090
#RUN ./mvnw package
## Run the application
## ENTRYPOINT ["java", "-jar", "app.jar"]
#

CMD["java", "-jar", "target/<expensetracker-0.0.1-SNAPSHOT.jar"]