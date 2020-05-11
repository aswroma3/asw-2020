#!/bin/bash

docker create -v ~/projects/www:/var/www/html -p 8080:80 --name=myapache myapache
