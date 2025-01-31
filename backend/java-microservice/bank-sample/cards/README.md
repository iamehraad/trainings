## Docker setup

This microservice uses Google JIB

### Command to build image:

- Update pom.xml file to add jib plugin

- `mvn compile jib:dockerBuild
  `