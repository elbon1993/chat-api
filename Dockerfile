# Use the official OpenJDK image as a base image
FROM openjdk:17-jdk-slim as builder

# Set the working directory
WORKDIR /chat-api

# Copy the build.gradle and settings.gradle files
COPY build.gradle .
COPY settings.gradle .

# Copy the Gradle wrapper
COPY gradlew .
COPY gradlew.bat .
COPY gradle ./gradle/

# Copy the source code
COPY src ./src

# Download dependencies and build the application
RUN chmod +x gradlew && ./gradlew build --no-daemon

# Use the official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /chat-api

# Copy the jar file from the builder stage
COPY --from=builder /chat-api/build/libs/*.jar chat-api.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "chat-api.jar"]
