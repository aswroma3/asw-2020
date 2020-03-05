#!/bin/bash

echo "==============================================="
echo "installing kubernetes (with kubeadm) for client"
echo "==============================================="

# https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/

# ensure iptables tooling does not use the nftables backend (non so se serve davvero) 
apt-get install -y iptables arptables ebtables
update-alternatives --set iptables /usr/sbin/iptables-legacy
update-alternatives --set ip6tables /usr/sbin/ip6tables-legacy
update-alternatives --set arptables /usr/sbin/arptables-legacy
update-alternatives --set ebtables /usr/sbin/ebtables-legacy

# installing kubectl

apt-get update && apt-get install -y apt-transport-https curl

# aggiungo il repository per l'installazione di kubernetes
# see https://www.poftut.com/setup-kubernetes-1-4-ubuntu/
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
cat <<EOF >/etc/apt/sources.list.d/kubernetes.list
deb http://apt.kubernetes.io/ kubernetes-xenial main
EOF

# installo kubernetes
apt-get update
#apt-get install -y kubelet kubeadm kubectl
apt-get install -y kubectl

# blocco l'aggiornamento del tool (misura di sicurezza implementata senza indicazioni esterne)
#apt-mark hold kubelet kubeadm kubectl
apt-mark hold kubectl

