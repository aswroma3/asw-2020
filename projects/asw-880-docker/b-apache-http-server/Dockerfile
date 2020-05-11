###########################################
# Dockerfile to build an apache2 image
###########################################

# Base image is Ubuntu
FROM ubuntu:18.04

# Author: Luca Cabibbo 
LABEL author="Luca Cabibbo <cabibbo@dia.uniroma3.it>"

# Install apache2 package
RUN apt update && \
    apt install -y apache2 

# Funziona, ma è preferibile l'altra soluzione 
# RUN apt update 
# RUN apt install -y apache2 

# Set the log directory PATH
ENV APACHE_LOG_DIR /var/log/apache2

# Expose port 80
EXPOSE 80

VOLUME /var/www/html 

# Launch apache2 server in the foreground
ENTRYPOINT ["/usr/sbin/apache2ctl", "-D", "FOREGROUND"] 
