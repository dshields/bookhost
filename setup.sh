#!/bin/bash

docker-machine create --driver virtualbox default
docker-machine start default
eval "$(docker-machine env default)"
docker build -f Dockerfile -t amplitude/tomee .
