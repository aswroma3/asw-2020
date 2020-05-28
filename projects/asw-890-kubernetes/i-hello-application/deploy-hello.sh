#!/bin/bash

echo 'Starting hello' 

kubectl create namespace hello-ns  
kubectl apply -f hello-application.yaml -n hello-ns

