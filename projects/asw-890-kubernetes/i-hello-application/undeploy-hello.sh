#!/bin/bash

kubectl delete -f hello-application.yaml -n hello-ns
kubectl delete namespace hello-ns  

