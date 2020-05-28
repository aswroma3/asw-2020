#!/bin/bash

# accede al servizio tramite ingress - ovvero, tramite il nome del servizio (sulla porta 80 o, nel caso di kube-cluster, 31080)
# DEVE ESSERE INSTALLATO UN INGRESS CONTROLLER COME ADD-ON  
# IL NOME DEL SERVIZIO DEVE ESSERE UN ALIAS DEI NOMI DEL CLUSTER - CONFIGURATO NEL DNS OPPURE IN /ETC/HOSTS 

# se non conosco la porta associata all'ingress 
#INGRESS_SERVICE=ingress-nginx-controller
#INGRESS_NAMESPACE=ingress-nginx
#INGRESS_PORT=$(kubectl get services/${INGRESS_SERVICE} -n ${INGRESS_NAMESPACE} -o go-template='{{(index .spec.ports 0).nodePort}}')

SERVICE=hello
SERVICE_HOST=hello
# SERVICE_HOST=hello.default.svc.cluster.local
INGRESS_PORT=31080

echo Accessing ${SERVICE} on ${SERVICE_HOST}:${INGRESS_PORT}

while true; do 
	curl ${SERVICE_HOST}:${INGRESS_PORT}
#	curl ${SERVICE_HOST}
	echo "" ; 
done 

