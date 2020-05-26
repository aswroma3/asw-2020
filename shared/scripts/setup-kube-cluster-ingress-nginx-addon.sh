#!/bin/bash

# nota: ci sono ingress controller basati su nginx: 1) uno mantenuto da nginx e 2) uno da kubernetes
# 1) https://www.nginx.com/products/nginx/kubernetes-ingress-controller 
# 2) https://kubernetes.github.io/ingress-nginx/ 

# usiamo il secondo (malgrado non sia possibile accedervi sulla porta 80) 
# perché il primo è lento in modo imbarazzante (provato sul cluster su vsphere) 

echo "======================================================="
echo "installing kubernetes nginx ingress controller (master)"
echo "======================================================="

#kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-0.32.0/deploy/static/provider/baremetal/deploy.yaml

# modifica il file di configurazione in modo che la porta per http/hhtps dell'ingress siano fissate su 31080/31443 

mkdir -p /etc/kube-cluster
curl -L https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-0.32.0/deploy/static/provider/baremetal/deploy.yaml \
        | sed 's/targetPort: http$/&\n      nodePort: 31080/' \
        | sed 's/targetPort: https$/&\n      nodePort: 31443/' \
		> /etc/kube-cluster/ingress-nginx-nodeport-deploy.yaml

kubectl apply -f /etc/kube-cluster/ingress-nginx-nodeport-deploy.yaml

# test: 
# kubectl get pods --namespace=ingress-nginx

# uninstall: 
# kubectl delete namespace ingress-nginx

