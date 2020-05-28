#!/bin/bash

kubectl delete -f sentence-async-application.yaml -n sentence-async-ns
kubectl delete namespace sentence-async-ns

