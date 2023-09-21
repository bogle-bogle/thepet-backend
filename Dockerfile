# Use a base image with Java 8
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the Spring Boot application WAR file to the container
COPY ./build/libs/thepet-0.0.1-SNAPSHOT.jar /app/thepet.jar
ENV JAVA_OPTS default
# Expose port 8080 for the Spring Boot application
EXPOSE 8080

# Run the Spring Boot application
CMD ["java","-Djasypt.encryptor.password=${JAVA_OPTS}" ,"-jar", "thepet.jar"]
