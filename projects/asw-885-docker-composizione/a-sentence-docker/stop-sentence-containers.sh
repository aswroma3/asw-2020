#!/bin/bash

docker stop subject 
docker stop verb 
docker stop object 
docker stop sentence 
docker stop apigateway 
docker stop consul 

docker rm subject 
docker rm verb 
docker rm object 
docker rm sentence 
docker rm apigateway 
docker rm consul 

docker network rm sentence-net 
