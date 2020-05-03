# Sentence (con Consul e Spring Cloud Consul Discovery, Spring Cloud LoadBalancer, Spring Cloud OpenFeign e Spring Cloud Circuit Breaker/Resilience4J)

Questo sottoprogetto mostra una versione dell'applicazione **sentence** che, oltre al servizio di service discovery, un load balancer lato client, client REST dichiarativi, circuit breaker, utilizza un API gateway (*Spring Cloud Gateway*). 

I servizi *word-service* e *sentence-service* agiscono da client nei confronti del servizio di service discovery. 

Il servizio *sentence-service* usa i client REST dichiarativi *OpenFeign* con un circuit breaker *Resilience4J* per accedere alle diverse istanze del servizio *word-service*. 

Il servizio *api-gateway* è un API Gateway che espone le funzionalità dell'applicazione sulla porta *8080*. 

Il servizio *sentence-service* può essere ora replicato. 

## Componenti eseguibili

Questa versione dell'applicazione **sentence** è formata dai seguenti componenti eseguibili: 

* **word-service** è il servizio per la generazione di parole casuali, che agisce da client nei confronti del servizio di service discovery, e che viene avviato con tre istanze: 
  * una con il profilo *subject* su una porta casuale, 
  * una con il profilo *verb* su una porta casuale, 
  * una con il profilo *object* su una porta casuale, 
* **sentence-service** è il servizio per la generazione delle frasi casuali, su una porta casuale, che agisce da client nei confronti dei servizi per le parole tramite il servizio di service discovery 
* **api-gateway** è un API gateway per esporre le funzionalità dell'intera applicazione sulla porta *8080*, anche lui protetto con un circuit breaker *Relisience4J*

## Esecuzione 

Per eseguire questa versione dell'applicazione: 

* avviare *Consul* eseguendo lo script `start-consul.sh` 

* per avviare l'applicazione *sentence* (compreso l'API gateway), eseguire lo script `run-sentence.sh` 

L'applicazione può essere verificata usando lo script `run-curl-client.sh` oppure `run-curl-client-forever.sh`. 

Alla fine, l'applicazione può essere arrestata usando lo script `stop-java-processes.sh` (**da usare con cautela!**). 

Inoltre, *Consul* può essere arrestato con lo script `stop-consul.sh`. 

## Esecuzione con più istanze dei servizi per le parole 

Lo script `run-sentence-replicated.sh` avvia due istanze del servizio *word-service* per ciascuno dei suoi tre profili e anche due istanze del servizio *sentence-service* (ma una sola istanza del servizio *api-gateway*). 

### Esperimenti 

Durante l'esecuzione dell'applicazione, è possibile: 

* Uccidere gentilmente (usando il comando *kill -15*) il processo di uno o più dei servizi delle parole: 
  la frase generata potrà contenere una o più parole di *fallback*. 
  Per uccidere gentilmente un'istanza del servizio *subject per il soggetto si può anche usare il comando `kill-15-a-java-process.sh subject`.
  Eseguire questo esperimento, usando come client lo script `run-curl-client-forever.sh` per almeno un minuto. 

* Uccidere gentilmente (usando il comando *kill -15*) il processo di uno o più dei servizi delle frasi: 
  l'API gateway potrà restituire una una frase di *fallback*. 
  Per uccidere brutalmente un'istanza del servizio *subject per il soggetto si può anche usare il comando `kill-9-a-java-process.sh subject`.
  Eseguire questo esperimento, usando come client lo script `run-curl-client-forever.sh` per almeno un minuto. 

## Esecuzione con latenza 

Gli script `run-sentence-delayed.sh` e `run-sentence-replicated-delayed.sh` avviano le istanze del servizio *word-service* in modo da introdurre una latenza di 100ms. 
Si può anche usare il comando `run-sentence-delayed.sh 500` per introdurre una latenza di 500ms. 
Inoltre, il servizio *sentence-service* restituisce, insieme alla frase, la latenza necessaria per il calcolo della frase. 

Si noti che il circuit breaker usato per accedere al servizio delle parole è configurato per considerare errate le chiamate con una latenza maggiore di 800ms e problematiche le chiamate con una latenza maggiore di 400ms.

### Esperimenti 

* Avviare l'applicazione con il comando `run-sentence-delayed.sh` e `run-sentence-replicated-delayed.sh`. 
  Eseguire come client lo script `run-curl-client-forever.sh` per almeno un minuto per misurare la latenza per il calcolo di una frase. 

* Avviare l'applicazione con il comando `run-sentence-delayed.sh 500` e `run-sentence-replicated-delayed.sh 500`. 
  Eseguire come client lo script `run-curl-client-forever.sh` per almeno un minuto per misurare la latenza per il calcolo di una frase. 

* Avviare l'applicazione con il comando `run-sentence-delayed.sh 1000` e `run-sentence-replicated-delayed.sh 1000`. 
  Eseguire come client lo script `run-curl-client-forever.sh` per almeno un minuto per misurare la latenza per il calcolo di una frase. 

