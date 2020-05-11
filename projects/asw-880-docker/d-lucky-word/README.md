# Lucky word

Questo sottoprogetto esemplifica come eseguire un'applicazione web Spring Boot 
in un contenitore Docker. 

### Build (Java)  

Per prima cosa, è necessario effettuare il build dell'applicazione con Gradle,  
nell'ambiente [developer](../../../environments/developer/): 

* eseguire `gradle build` nella cartella del sottoprogetto 

### Esecuzione (Docker)  

Poi bisogna costruire e mandare in esecuzione l'applicazione in un contenitore Docker, 
operando come segue nell'ambiente [docker](../../../environments/docker/) 
(sempre essendo posizionati nella cartella del sotto progetto): 

* eseguire il comando `docker build --rm -t lucky-word-img .` 
  oppure lo script `build-image.sh` 
 
* eseguire il comando `docker run -d -p 8080:8080 --name=lucky-word lucky-word-img` 
  oppure lo script `run-container.sh` 
  
* in alternativa, vedere gli script `run-container-as-english.sh` e 
  `run-container-as-italian.sh`

Il contenitore espone il suo servizio sulla porta `8080`, 
che è anche collegata alla porta `8080` della macchina virtuale **docker**, 
sul path `/lucky-word`. 
Pertanto, il servizio sarà accessibile nella macchina virtuale **docker** 
all'indirizzo `localhost:8080/lucky-word` 
(si veda lo script `run-curl-client.sh`). 

Sull'host, potrebbe essere accessibile ad una porta diversa, 
in genere sulla porta `8081` (vedere il port forwarding di vagrant). 