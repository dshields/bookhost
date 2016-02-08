#!/bin/bash
docker-machine start default
eval `docker-machine env default`
docker build -f Dockerfile .
URL=`docker-machine ls | awk '{ print $5 }' | tail -n 1 | sed 's|tcp://\(.*\):.*|http://\1/bookhost|'`
echo "bookhost url is $URL"
docker run -it -p 8080:8080 amplitude/tomee
docker rm -v $(docker ps -a -q -f status=exited)
docker rmi $(docker images -f "dangling=true" -q)


