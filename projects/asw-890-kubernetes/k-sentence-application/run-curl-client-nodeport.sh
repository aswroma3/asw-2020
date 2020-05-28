#!/bin/bash

# accede al servizio tramite service nodeport - sulla porta del servizio, su uno dei nodi del cluster  

N=${1:-1}
# itera la richiesta N volte (default: 1) 

#SERVICE=sentence
SERVICE=apigateway
SERVICE_NAMESPACE=sentence-ns

SERVICE_HOST=kube-cluster
SERVICE_PORT=$(kubectl get services/${SERVICE} -n ${SERVICE_NAMESPACE} -o go-template='{{(index .spec.ports 0).nodePort}}')

echo Accessing ${SERVICE} on ${SERVICE_HOST}:${SERVICE_PORT}

for ((i=0; i<$N; i++)); do 
	curl ${SERVICE_HOST}:${SERVICE_PORT}
	echo "" ; 
done 

