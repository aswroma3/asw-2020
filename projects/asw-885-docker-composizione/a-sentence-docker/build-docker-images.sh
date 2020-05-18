#!/bin/bash

docker build --rm -t sentence-sentence-docker ./sentence-service
docker build --rm -t sentence-word-docker ./word-service 
docker build --rm -t sentence-apigateway-docker ./api-gateway
