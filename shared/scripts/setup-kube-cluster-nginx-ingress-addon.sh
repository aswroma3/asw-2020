#!/bin/bash

# nota: ci sono ingress controller basati su nginx: 1) uno mantenuto da nginx e 2) uno da kubernetes
# 1) https://www.nginx.com/products/nginx/kubernetes-ingress-controller 
# 2) https://kubernetes.github.io/ingress-nginx/ 

# usiamo il secondo (malgrado non sia possibile accedervi sulla porta 80) 
# perché il primo è lento in modo imbarazzante (provato sul cluster su vsphere) 

#echo "======================================================="
#echo "installing nginx kubernetes ingress controller (master)"
#echo "======================================================="

# see https://github.com/nginxinc/kubernetes-ingress 
# see https://github.com/nginxinc/kubernetes-ingress/blob/master/docs/installation.md 
# see https://stackoverflow.com/questions/4604663/download-single-files-from-github 

##NGINX_INGRESS_BASE_URI=https://raw.githubusercontent.com/nginxinc/kubernetes-ingress/master/deployments
##
##kubectl apply -f ${NGINX_INGRESS_BASE_URI}/common/ns-and-sa.yaml
##kubectl apply -f ${NGINX_INGRESS_BASE_URI}/common/default-server-secret.yaml
##kubectl apply -f ${NGINX_INGRESS_BASE_URI}/common/nginx-config.yaml
##kubectl apply -f ${NGINX_INGRESS_BASE_URI}/common/custom-resource-definitions.yaml
##kubectl apply -f ${NGINX_INGRESS_BASE_URI}/rbac/rbac.yaml
### kubectl apply -f ${NGINX_INGRESS_BASE_URI}/deployment/nginx-ingress.yaml
##kubectl apply -f ${NGINX_INGRESS_BASE_URI}/daemon-set/nginx-ingress.yaml

# test: 
# kubectl get pods --namespace=nginx-ingress 

# uninstall: 
# kubectl delete namespace nginx-ingress 

echo "======================================================="
echo "installing kubernetes nginx ingress controller (master)"
echo "======================================================="

mkdir -p /etc/kube-cluster
#curl -L https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/baremetal/service-nodeport.yaml ...
curl -L https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/provider/baremetal/service-nodeport.yaml \
        | sed 's/targetPort: 80/&\n      nodePort: 31080/' \
        | sed 's/targetPort: 443/&\n      nodePort: 31443/' \
		> /etc/kube-cluster/ingress-nodeport-kube-cluster.yaml

#kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/mandatory.yaml
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/mandatory.yaml
# kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/baremetal/service-nodeport.yaml
kubectl apply -f /etc/kube-cluster/ingress-nodeport-kube-cluster.yaml

# test: 
# kubectl get pods --namespace=ingress-nginx

# uninstall: 
# kubectl delete namespace ingress-nginx

