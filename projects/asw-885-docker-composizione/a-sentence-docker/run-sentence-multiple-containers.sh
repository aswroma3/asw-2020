#!/bin/bash

docker network create sentence-net 

# esegue piu' container di ogni tipo (tranne consul e apigateway) 

docker run -d --network=sentence-net --name=consul consul 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" --name=subject-1 sentence-word-docker 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" --name=verb-1 sentence-word-docker 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" --name=object-1 sentence-word-docker 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" --name=subject-2 sentence-word-docker 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" --name=verb-2 sentence-word-docker 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" --name=object-2 sentence-word-docker 

docker run -d --network=sentence-net --name=sentence-1 sentence-sentence-docker 
docker run -d --network=sentence-net --name=sentence-2 sentence-sentence-docker 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway sentence-apigateway-docker 

