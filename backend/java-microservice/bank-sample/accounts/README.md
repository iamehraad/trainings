Training project for Java and microservices

## Useful docker commands:
1 - `docker images` -> show all images

2 - `docker inspect image ${imageId}`

3 - `docker ps`

4 - `docker pull ${username}/${image}`

5 - `dcoker compose up -d`

6 - `docker compose down / docker compose stop`


## Steps for docker:

1 - Make Dockerfile

2 - build it with: `docker build . -t iamehraad/accounts:0.1`

3 - `docker run -d -p 8080:8080 iamehraad/accounts:0.1`