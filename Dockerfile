# Base image with Java 21 and Maven
FROM maven:3.9.6-eclipse-temurin-21

# Set workspace
WORKDIR /app

# Copy all files from your laptop to the container
COPY . .

# Command to run tests
CMD ["mvn", "clean", "verify"]