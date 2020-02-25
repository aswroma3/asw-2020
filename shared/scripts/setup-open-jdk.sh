#!/bin/bash

source "/home/asw/shared/scripts/common.sh"

# see https://openjdk.java.net/install/ 

# set up Java constants 
# OPENJDK_PACKAGE=openjdk-8-jdk
OPENJDK_PACKAGE=openjdk-11-jdk

echo "==================="
echo "installing open jdk"
echo "==================="

apt update 
apt install -y ${OPENJDK_PACKAGE}
