#!/bin/bash

docker network create sentence-net 

docker run -d --network=sentence-net --name=consul consul 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" --name=subject sentence-word-docker 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" --name=verb sentence-word-docker 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" --name=object sentence-word-docker 

docker run -d --network=sentence-net --name=sentence sentence-sentence-docker 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway sentence-apigateway-docker 
