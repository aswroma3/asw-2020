#!/bin/bash

docker network create sentence-net 

docker run -d --network=sentence-net --name=consul consul 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" -e "ASW_SENTENCE_WORDSERVICE_LATENCY=100" --name=subject aswroma3/sentence-word-docker:2020.1 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" -e "ASW_SENTENCE_WORDSERVICE_LATENCY=100" --name=verb aswroma3/sentence-word-docker:2020.1 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" -e "ASW_SENTENCE_WORDSERVICE_LATENCY=100" --name=object aswroma3/sentence-word-docker:2020.1 

docker run -d --network=sentence-net -e "ASW_SENTENCE_SENTENCESERVICE_RETURNLATENCY=true" --name=sentence-async aswroma3/sentence-sentence-async-docker:2020.1 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway aswroma3/sentence-apigateway-docker:2020.1 

