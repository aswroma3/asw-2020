#!/bin/bash

# delete all containers from image myhello 

IMAGENAME=myhello

docker rm $(docker ps -a | grep $IMAGENAME | awk '{ print $NF }')
