#!/bin/bash

echo "========================================="
echo "installing calico network add-on (master)"
echo "========================================="

# usa questa pod network al posto di quella di default 
# POD_NETWORK_CIDR=10.12.0.0
POD_NETWORK_CIDR=$1

# avvio il controller di rete Calico che si occupa della sicurezza del cluster
# see https://docs.projectcalico.org/v3.7/introduction/
#kubectl apply -f https://docs.projectcalico.org/v3.1/getting-started/kubernetes/installation/hosted/rbac-kdd.yaml
#kubectl apply -f https://docs.projectcalico.org/v3.1/getting-started/kubernetes/installation/hosted/kubernetes-datastore/calico-networking/1.7/calico.yaml
# https://docs.projectcalico.org/v3.8/getting-started/kubernetes/
#kubectl apply -f https://docs.projectcalico.org/v3.8/manifests/calico.yaml

mkdir -p /etc/kube-cluster
curl -L https://docs.projectcalico.org/v3.11/manifests/calico.yaml | sed s/192.168.0.0/${POD_NETWORK_CIDR}/ > /etc/kube-cluster/calico-kube-cluster.yaml
kubectl apply -f /etc/kube-cluster/calico-kube-cluster.yaml


