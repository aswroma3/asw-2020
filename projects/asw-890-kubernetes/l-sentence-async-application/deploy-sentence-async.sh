#!/bin/bash

echo 'Starting sentence-async' 

kubectl create namespace sentence-async-ns
kubectl create -f sentence-async-application.yaml -n sentence-async-ns

# kubectl rollout status deployment/sentence -n sentence-async-ns

