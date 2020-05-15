# INSTAGNAM 

Progetto del corso di Analisi e progettazione del software per l'anno accademico 2019-2020. 


## Descrizione di questo progetto 

Questo progetto contiene il il codice di *Instagnam*, un semplice social-network per la condivisione di ricette di cucina. 
Gli utenti del sistema possono pubblicare delle ricette. 
Possono poi seguire altri utenti del sistema. 
Quando un utente accede la pagina delle ricette che segue, gli vengono mostrate le ricette degli altri utenti che segue. 

L'applicazione *Instagnam* è composta dai seguenti microservizi: 

* Il servizio *ricette* gestisce le ricette di cucina dei suoi utenti. 
  Ogni ricetta ha un autore, un titolo e una preparazione. 
  Operazioni: 
  * `POST /ricette` aggiunge una nuova ricetta (dato autore, titolo e preparazione)
  * `GET /ricette/{id}` trova una ricetta dato l'id 
  * `GET /ricette` trova tutte le ricette (in formato breve, solo id, autore e titolo)
  * `GET /ricette?autore={autore}` trova tutte le ricette di autore (in formato breve, solo id, autore e titolo)
  
* Il servizio *connessioni* gestisce le connessioni tra gli utenti. 
  Ogni connessione è una coppia follower-followed, in cui follower e followed sono due utenti del sistema. 
  Operazioni: 
  * `POST /connessioni` aggiunge una nuova connessione (dato follower e followed)
  * `GET /connessioni/{id}` trova una connessione dato l’id 
  * `GET /connessioni` trova tutte le connessioni
  * `GET /connessioni?follower={utente}` trova tutte le connessioni di utente (quelle in cui l’utente è follower)

* Il servizio *ricette-seguite* consente a un utente di trovare le ricette di tutti gli utenti che segue. 
  Operazioni: 
  * `GET /ricetteseguite/{utente}` trova tutte le ricette seguite da utente, ovvero le ricette di utenti di cui l’utente è follower (ricette in formato breve, trova solo id, autore e titolo)
  
* Il servizio *api-gateway* (esposto sulla porta *8080*) è l'API gateway dell'applicazione che: 
  * espone il servizio *ricette* sul path `/ricette` - ad esempio, `GET /ricette/ricette`
  * espone il servizio *connessioni* sul path `/connessioni` - ad esempio, `GET /connessioni/connessioni`
  * espone il servizio *ricette-seguite* sul path `/ricette-seguite` - ad esempio, `GET /ricette-seguite/ricetteseguite/{utente}`

In questo progetto, l'implementazione dell'operazione `GET /ricetteseguite/U` del servizio *ricette-seguite*, 
per trovare le ricette seguite dall'utente U, è basata su invocazioni remote REST ai servizi *connessioni* e *ricette*: 
* prima viene invocata `GET /connessioni?follower=U` di *connessioni* 
  per trovare l'insieme AA di tutti gli utenti seguiti dall'utente U 
* poi, ripetutamente, per ciascun utente A nell'insieme AA, viene invocata `GET /ricette?autore=A` di *ricette*, 
  in modo da trovare, complessivamente, le ricette degli autori nell'insieme degli utenti AA seguiti da U 


## Esecuzione 

Per eseguire questo progetto: 

* avviare *Consul* eseguendo lo script `start-consul.sh` 

* per avviare l'applicazione *Instagnam*, eseguire lo script `run-instagnam.sh` 

Alla fine, l'applicazione può essere arrestata usando lo script `stop-java-processes.sh` (**da usare con cautela!**). 

Inoltre, *Consul* può essere arrestato con lo script `stop-consul.sh`. 


## Descrizione delle attività da svolgere 

Si veda il sito web del corso di [Architettura dei sistemi software](http://cabibbo.dia.uniroma3.it/asw/).