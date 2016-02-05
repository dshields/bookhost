#!/bin/bash
docker build -f Dockerfile .
echo '"docker-machine ls" to get ip of the machine running tomee then you can browse to http://<ip>:8888/mp3'
docker run -it -p 8888:8080 amplitude/tomee
docker rm -v $(docker ps -a -q -f status=exited)
docker rmi $(docker images -f "dangling=true" -q)


