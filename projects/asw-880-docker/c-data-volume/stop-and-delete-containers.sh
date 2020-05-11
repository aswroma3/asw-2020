#!/bin/bash

docker stop myapache2 
docker rm myapache2 

docker stop my-data-volume 
docker rm -v my-data-volume 
