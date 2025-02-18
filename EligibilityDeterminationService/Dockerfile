# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/*.jar app.jar

# Expose the required port (Change according to your service)
EXPOSE 7073

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
