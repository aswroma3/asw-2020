#!/bin/bash

echo 'Starting sentence' 

kubectl create namespace sentence-ns
kubectl create -f sentence-application.yaml -n sentence-ns

# kubectl rollout status deployment/sentence -n sentence-ns

