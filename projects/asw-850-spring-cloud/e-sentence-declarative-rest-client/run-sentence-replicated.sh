#!/bin/bash

# Script per avviare l'applicazione Sentence 

echo Running SENTENCE [Declarative REST Client]

# Consul deve essere avviato separatamente 

echo Starting Word Services [subject*2 + verb*2 + object*2]
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object word-service/build/libs/word.jar &

echo Starting Sentence Service [*1, using Declarative REST Client, listening on 8080]

java -Xms64m -Xmx128m -jar sentence-service/build/libs/sentence.jar &

