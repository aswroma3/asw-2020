#!/bin/bash

echo 'Inspecting sentence' 

kubectl get services -n sentence-ns -o wide
kubectl get pods -n sentence-ns -o wide

