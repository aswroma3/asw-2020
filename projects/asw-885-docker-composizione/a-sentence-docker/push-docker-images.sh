#!/bin/bash

docker tag sentence-sentence-docker aswroma3/sentence-sentence-docker:2020.1
docker tag sentence-sentence-async-docker aswroma3/sentence-sentence-async-docker:2020.1
docker tag sentence-word-docker aswroma3/sentence-word-docker:2020.1
docker tag sentence-apigateway-docker aswroma3/sentence-apigateway-docker:2020.1 

docker push aswroma3/sentence-sentence-docker:2020.1
docker push aswroma3/sentence-sentence-async-docker:2020.1
docker push aswroma3/sentence-word-docker:2020.1
docker push aswroma3/sentence-apigateway-docker:2020.1 
