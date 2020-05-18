#!/bin/bash

docker image rm $(docker image ls | grep sentence | grep docker | awk '{print $3}')




