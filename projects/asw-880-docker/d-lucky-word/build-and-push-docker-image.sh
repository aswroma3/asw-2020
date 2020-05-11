#!/bin/bash

# per creare l'immagine docker e salvarla su docker hub 

# prerequisito: aver eseguito il docker login su docker hub 

DOCKERHUB_USERNAME=aswroma3 
IMAGE_NAME=lucky-word

docker build --rm -t ${DOCKERHUB_USERNAME}/${IMAGE_NAME} . 
docker push ${DOCKERHUB_USERNAME}/${IMAGE_NAME}
