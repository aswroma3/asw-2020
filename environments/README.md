# Ambienti di esecuzione del corso di Architettura dei Sistemi Software 

Questa sezione del repository contiene il codice (*infrastructure-as-code*) 
di alcuni *ambienti di esecuzione* distribuiti virtuali. 
Ogni sottosezione (sottocartella) è relativa a un diverso ambiente di esecuzione. 

Attualmente non sono presenti tutti gli ambienti. 
Verranno aggiunti a questo repository durante lo svolgimento del corso. 

Questi ambienti di esecuzione possono essere utilizzati per 
eseguire i progetti delle applicazioni distribuite 
definiti nella cartella [projects/](../projects/) del repository. 


## Preparazione 

Per usare delle versioni più recenti del software di sviluppo (come *JDK* e *Gradle*) 
potrebbe essere necessario modificare le prime righe dei relativi script di installazione 
(ad esempio, **asw/shared/scripts/setup-gradle.sh** per *Gradle*), 
indicando il numero della versione da utilizzare. 


## Utilizzo degli ambienti di esecuzione 

Ogni ambiente di esecuzione è composto da uno o più macchine virtuali, 
collegate in una rete privata. 

Ogni ambiente è rappresentato da una diversa cartella di questa sezione del repository. 
Si veda il file **README.md** di una cartella per la descrizione del relativo ambiente. 

Gli ambienti vengono creati con **Vagrant**, 
e possono essere tutti gestiti allo stesso modo. 

Per gestire un ambiente bisogna: 

1. usare una shell (per esempio, Git) del proprio PC 

2. posizionarsi nella cartella dell'ambiente di interesse (ad esempio, **asw/environments/developer**)

3. per avviare o creare l'ambiente di esecuzione, usare il comando `vagrant up` 

4. per collegarsi con SSH a una macchina virtuale *VM* dell'ambiente, usare il comando `vagrant ssh VM`
    
E' anche possibile: 

* arrestare l'ambiente di esecuzione, con il comando `vagrant halt`

* distruggere l'ambiente di esecuzione, con il comando `vagrant destroy -f`  


## Ambienti 

* [developer](developer/):
  per la compilazione e l'assemblaggio (build) dei progetti definiti nella cartella [projects/](../projects/) del repository, 
  nonché per l'esecuzione di semplici applicazioni Java; 
  i progetti (in questo e, anche negli altri ambienti) potranno essere trovati 
  nella cartella **/home/asw/projects/** oppure nella cartella **projects/** dell'utente di default 

* [workstation](workstation/):
  un ambiente alternativo a [developer](developer/) per la compilazione e l'assemblaggio (build) dei progetti 
  e per la loro esecuzione; 
  rispetto a [developer](developer/) ha una configurazione più potente in termini di memoria e processore 
  (dunque richiede maggiori risorse nel computer host), 
  per consentire anche l'esecuzione concorrente di molteplici applicazioni; 
  inoltre, ha anche *Docker* oltre al software di sviluppo per *Java*

<!---
* [client-server](client-server/): 
  per l'esecuzione di applicazioni Java distribuite di tipo client-server 

* [glassfish](glassfish/): 
  per l'esecuzione di applicazioni Java EE con l'application server *GlassFish* 
  (basato su *Payara 5*)
  
* [docker](docker/): 
  per la gestione e l'esecuzione di contenitori *Docker*  

* [docker-swarm](docker-swarm/): 
  per la gestione e l'esecuzione di un cluster (*swarm*) di nodi *Docker*  

  nulla di altro da escludere, in questo momento 
--> 

<!---
* [standalone](standalone/):
  l'ambiente più semplice, per l'esecuzione di applicazioni Java non distribuite 
--> 

