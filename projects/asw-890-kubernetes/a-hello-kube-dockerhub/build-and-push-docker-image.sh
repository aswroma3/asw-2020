#!/bin/bash

# per creare l'immagine docker e salvarla su docker hub 

# prerequisito: aver eseguito il docker login su docker hub 

docker build --rm -t hello-kube . 
docker tag hello-kube aswroma3/hello-kube
docker push aswroma3/hello-kube
