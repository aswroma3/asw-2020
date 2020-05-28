#!/bin/bash

# accede al servizio tramite ingress - ovvero, tramite il nome del servizio (sulla porta 80)
# DEVE ESSERE INSTALLATO UN INGRESS CONTROLLER COME ADD-ON  
# IL NOME DEL SERVIZIO DEVE ESSERE UN ALIAS DEI NOMI DEL CLUSTER - CONFIGURATO NEL DNS OPPURE IN /ETC/HOSTS 

N=${1:-1}
# itera la richiesta N volte (default: 1) 

# se non conosco la porta associata all'ingress 
#INGRESS_SERVICE=ingress-nginx-controller
#INGRESS_NAMESPACE=ingress-nginx
#INGRESS_PORT=$(kubectl get services/${INGRESS_SERVICE} -n ${INGRESS_NAMESPACE} -o go-template='{{(index .spec.ports 0).nodePort}}')

# ma nell'ambiente kube-cluster, la porta per l'ingress e' 31080 (31443 per hhtps) 
INGRESS_PORT=31080

SERVICE=sentence
SERVICE_HOST=sentence

echo Accessing ${SERVICE} on ${SERVICE_HOST}:${INGRESS_PORT}

for ((i=0; i<$N; i++)); do 
	curl ${SERVICE_HOST}:${INGRESS_PORT}
	echo "" ; 
done 
