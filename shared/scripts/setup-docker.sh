#!/bin/bash

source "/home/asw/shared/scripts/common.sh"

# see https://docs.docker.com/install/linux/docker-ce/ubuntu/

# per sovrascrivere la configurazione di docker, 
# invocare uno script separato come prepare-docker-configuration.sh 

# set up Docker constants 
# DOCKER_VERSION=18.03.1~ce-0~ubuntu
# DOCKER_VERSION=18.06.1~ce~3-0~ubuntu
# DOCKER_VERSION=5:18.09.1~3-0~ubuntu-xenial
# DOCKER_VERSION=5:18.09.5~3-0~ubuntu-bionic
# DOCKER_VERSION=5:18.09.7~3-0~ubuntu-bionic
# DOCKER_VERSION=5:19.03.7~3-0~ubuntu-bionic
DOCKER_VERSION=5:19.03.8~3-0~ubuntu-bionic

# Per vedere le versioni disponibili 
# apt-cache madison docker-ce
# oppure https://download.docker.com/linux/ubuntu/dists/xenial/pool/stable/amd64/ 
# vedi anche https://github.com/docker/docker-ce/releases 

echo "================="
echo "installing docker"
echo "================="

# per Ubuntu 18.04 LTS 
VAGRANT_USER=vagrant 

# Update the apt package index 
apt-get update 

# Install packages to allow apt to use a repository over HTTPS:
apt-get -y install \
    apt-transport-https \
    ca-certificates \
    curl \
	gnupg-agent \
    software-properties-common

# Add Docker’s official GPG key: 
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

# Set up the stable repository
add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"

# Update (again) the apt package index 
apt-get update 

# Per vedere le versioni disponibili 
# apt-cache madison docker-ce

# Per installare una versione specifica (raccomandato in produzione) 
apt-get -y install docker-ce=${DOCKER_VERSION} docker-ce-cli=${DOCKER_VERSION} containerd.io

# Install the latest version of Docker CE 
# apt-get -y install docker-ce docker-ce-cli containerd.io

# Alcuni esempi per verificare l'installazione 
# docker run hello-world
# docker run docker/whalesay cowsay Hello, world! 
# docker run -it ubuntu bash

##### post-installation 

# groupadd docker

# abilita l'utente vagrant 
usermod -aG docker ${VAGRANT_USER}
# Remember to log out and back in for this to take effect! 

##### configure docker to start on boot 

### Su Ubuntu 16.04 e superiori 
systemctl daemon-reload
systemctl enable docker 
systemctl restart docker.service

### Su Ubuntu 14.04 viene avviato di default 
