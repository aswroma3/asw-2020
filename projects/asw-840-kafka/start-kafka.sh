#!/bin/bash

# calcola il mio indirizzo IP (sulla rete 10.11.1.xx)
# Ubuntu 16.04 
# MY_IP_ADDR=$(ifconfig  | grep 'inet addr:'| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $1}' | grep '10.11.1.')
# Ubuntu 18.04 
MY_IP_ADDR=$(ifconfig  | grep 'inet' | grep -v 'inet6' | grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $2}' | grep '10.11.1.')

if [ -z "$DOCKER_HOST_IP" ] ; then
    echo "please do source set-docker-host-ip.sh"
    export DOCKER_HOST_IP=${MY_IP_ADDR}
fi

# Script per avviare Kafka con Docker Compose

echo Starting Kafka...

# docker-compose up -d zookeeper kafka
docker-compose up -d 
