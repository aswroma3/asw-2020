#!/bin/bash

docker create --name=my-data-volume my-data-volume 
docker start my-data-volume 

docker create --volumes-from my-data-volume -p 8080:80 --name=myapache2 myapache
docker start myapache2
