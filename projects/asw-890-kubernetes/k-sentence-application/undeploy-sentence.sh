#!/bin/bash

kubectl delete -f sentence-application.yaml -n sentence-ns
kubectl delete namespace sentence-ns

