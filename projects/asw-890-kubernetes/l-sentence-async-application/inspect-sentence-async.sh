#!/bin/bash

echo 'Inspecting sentence' 

kubectl get services -n sentence-async-ns -o wide
kubectl get pods -n sentence-async-ns -o wide

