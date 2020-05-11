#!/bin/bash

# docker run -d -p 8080:8080 --name=lucky-word aswroma3/lucky-word 
# docker run -d -p 8080:8080 --name=lucky-word aswroma3/lucky-word -jar -Dspring.profiles.active=italian lucky-word.jar
docker run -d -p 8080:8080 --name=lucky-word aswroma3/lucky-word -jar -Dspring.profiles.active=english lucky-word.jar

