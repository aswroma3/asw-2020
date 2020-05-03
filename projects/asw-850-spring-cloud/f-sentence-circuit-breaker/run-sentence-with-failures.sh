#!/bin/bash

# Script per avviare l'applicazione Sentence 

echo Running SENTENCE [Declarative REST Client and Circuit Breaker]

# Consul deve essere avviato separatamente 

echo Starting Word Services [subject*1 failure=60 + verb*1 + object*1 failure=20]
# java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject -Dasw.sentence.wordservice.failureRate=60 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb word-service/build/libs/word.jar &
# java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb -Dasw.sentence.wordservice.failureRate=40 word-service/build/libs/word.jar &
# java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object -Dasw.sentence.wordservice.failureRate=20 word-service/build/libs/word.jar &

echo Starting Sentence Service [*1, using Declarative REST Client and Circuit Breaker, listening on 8080]

java -Xms64m -Xmx128m -jar sentence-service/build/libs/sentence.jar &

