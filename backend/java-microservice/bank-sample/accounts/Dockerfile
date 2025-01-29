# Start with base image 22-slim
FROM openjdk:22-slim

LABEL "org.opencontainers.image.authors"="Mehrad"

# Take Jar and put it inside docker environment
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Execute this command when someone wants to use image
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]