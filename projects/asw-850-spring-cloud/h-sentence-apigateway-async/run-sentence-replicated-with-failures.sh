#!/bin/bash

# Script per avviare l'applicazione Sentence 

echo Running SENTENCE [Async REST Client and Circuit Breaker and API Gateway]

# Consul deve essere avviato separatamente 

echo Starting Word Services [subject*1 + subject*1 failure=60 + verb*2 + object *1 + object *1 failure=20]
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject -Dasw.sentence.wordservice.failureRate=60 word-service/build/libs/word.jar &
# java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb -Dasw.sentence.wordservice.failureRate=40 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object -Dasw.sentence.wordservice.failureRate=20 word-service/build/libs/word.jar &
# java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb word-service/build/libs/word.jar &
# java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object word-service/build/libs/word.jar &

echo Starting Sentence Service [*2, using Async REST Client and Circuit Breaker]

java -Xms64m -Xmx128m -jar sentence-service-async/build/libs/sentence-async.jar &
java -Xms64m -Xmx128m -jar sentence-service-async/build/libs/sentence-async.jar &

sleep 10

echo Starting API Gateway [*1, listening on 8080]

java -Xms64m -Xmx128m -jar api-gateway/build/libs/apigateway.jar &


