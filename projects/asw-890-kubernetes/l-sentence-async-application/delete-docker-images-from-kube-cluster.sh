#!/bin/bash

docker -H kube-2 image rm  aswroma3/sentence-sentence-async-kube:2020.1
docker -H kube-2 image rm  aswroma3/sentence-word-kube:2020.1
docker -H kube-2 image rm  aswroma3/sentence-apigateway-kube:2020.1

docker -H kube-3 image rm  aswroma3/sentence-sentence-async-kube:2020.1
docker -H kube-3 image rm  aswroma3/sentence-word-kube:2020.1
docker -H kube-3 image rm  aswroma3/sentence-apigateway-kube:2020.1

