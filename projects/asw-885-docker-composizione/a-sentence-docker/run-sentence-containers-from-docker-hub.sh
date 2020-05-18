#!/bin/bash

docker network create sentence-net 

docker run -d --network=sentence-net --name=consul consul 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" --name=subject aswroma3/sentence-word-docker:2020.1 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" --name=verb aswroma3/sentence-word-docker:2020.1 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" --name=object aswroma3/sentence-word-docker:2020.1 

docker run -d --network=sentence-net --name=sentence aswroma3/sentence-sentence-docker:2020.1 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway aswroma3/sentence-apigateway-docker:2020.1 

