#!/bin/bash

N=${1:-1}

# itera la richiesta N volte (default: 1) 

for ((i=0; i<$N; i++)); do 
	curl localhost:8080
	echo "" ; 
done 
