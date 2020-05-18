# Sentence (versione per Docker)

Questo sottoprogetto mostra una versione dell'applicazione **sentence** per *Docker*. 

## Costruzione ed esecuzione 

### Build (Java) 

Dalla cartella principale dell'applicazione, usare il comando `gradle build` oppure lo script `build-java-projects.sh`

### Build (Docker) 

Dalla cartella principale dell'applicazione, usare lo script `build-docker-images.sh`

Volendo, eseguire anche lo script `push-docker-images.sh` (richiede di aver effettuato il login su Docker Hub con il comando `docker login`)

### Esecuzione 

Dalla cartella principale dell'applicazione, usare lo script `run-sentence-containers.sh`

L'applicazione può essere verificata usando lo script `run-curl-client.sh` oppure `run-curl-client-forever.sh`. 

Alla fine, l'applicazione può essere arrestata usando lo script `stop-sentence-containers.sh`  


### Esecuzione con più istanze dei contenitori  

Usare gli script `run-sentence-multiple-containers.sh` e `stop-sentence-multiple-containers.sh`

