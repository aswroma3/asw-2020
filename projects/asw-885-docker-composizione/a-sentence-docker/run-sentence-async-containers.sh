#!/bin/bash

docker network create sentence-net 

docker run -d --network=sentence-net --name=consul consul 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" -e "ASW_SENTENCE_WORDSERVICE_LATENCY=100" --name=subject sentence-word-docker 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" -e "ASW_SENTENCE_WORDSERVICE_LATENCY=100" --name=verb sentence-word-docker 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" -e "ASW_SENTENCE_WORDSERVICE_LATENCY=100" --name=object sentence-word-docker 

docker run -d --network=sentence-net -e "ASW_SENTENCE_SENTENCESERVICE_RETURNLATENCY=true" --name=sentence-async sentence-sentence-async-docker 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway sentence-apigateway-docker 
