#!/bin/bash

# Script per avviare l'applicazione Sentence 

echo Running SENTENCE [Async REST Client and Circuit Breaker and API Gateway]

WORD_DELAY=${1:-100}

# Consul deve essere avviato separatamente 

echo Starting Word Services [subject*2 delay=${WORD_DELAY} + verb*2 delay=${WORD_DELAY} + object *2 delay=${WORD_DELAY}]
#java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject word-service/build/libs/word.jar &
#java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb word-service/build/libs/word.jar &
#java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject -Dasw.sentence.wordservice.latency=${WORD_DELAY} word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb -Dasw.sentence.wordservice.latency=${WORD_DELAY} word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object -Dasw.sentence.wordservice.latency=${WORD_DELAY} word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject -Dasw.sentence.wordservice.latency=${WORD_DELAY} word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb -Dasw.sentence.wordservice.latency=${WORD_DELAY} word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object -Dasw.sentence.wordservice.latency=${WORD_DELAY} word-service/build/libs/word.jar &

echo Starting Sentence Service [*2, using Async REST Client and Circuit Breaker]

#java -Xms64m -Xmx128m -jar sentence-service/build/libs/sentence.jar &
#java -Xms64m -Xmx128m -jar sentence-service/build/libs/sentence.jar &
java -Xms64m -Xmx128m -jar -Dasw.sentence.sentenceservice.returnLatency=true sentence-service/build/libs/sentence.jar &
java -Xms64m -Xmx128m -jar -Dasw.sentence.sentenceservice.returnLatency=true sentence-service/build/libs/sentence.jar &

sleep 10

echo Starting API Gateway [*1, listening on 8080]

java -Xms64m -Xmx128m -jar api-gateway/build/libs/apigateway.jar &


