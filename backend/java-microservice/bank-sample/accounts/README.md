Training project for Java and microservices

## Useful docker commands:

-------

[Usefull commands for docker](https://www.udemy.com/course/master-microservices-with-spring-docker-kubernetes/learn/lecture/39944012#overview)

-------

1 - `docker images` -> show all images

2 - `docker inspect image ${imageId}`

3 - `docker ps`

4 - `docker pull ${username}/${image}`


Create containers:

5 - `dcoker compose up -d`

6 - `docker compose down / docker compose stop`

Start command from already created containers:

7 - `docker compose start`


## Steps for docker:

1 - Make Dockerfile

2 - build it with: `docker build . -t iamehraad/accounts:0.1`

3 - `docker run -d -p 8080:8080 iamehraad/accounts:0.1`

## For config management:

We can use spring cloud

 - Expose refresh API via actuator
 - use spring-bus
 - use rabbitMQ as broker to automatically call refresh endpoint
 - Add rabbit config to application.yml of each microservice