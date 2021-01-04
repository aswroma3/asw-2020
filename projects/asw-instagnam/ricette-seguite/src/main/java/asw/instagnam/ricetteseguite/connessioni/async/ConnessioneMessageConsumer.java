package asw.instagnam.ricetteseguite.connessioni.async;

import asw.instagnam.ricetteseguite.domain.Connessione;
import asw.instagnam.ricetteseguite.domain.ConnessioniService;
import asw.instagnam.ricetteseguite.rest.RicetteSeguiteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ConnessioneMessageConsumer {

    private final Logger logger = Logger.getLogger(RicetteSeguiteController.class.toString());

    @Autowired
    @Qualifier("connessioniServiceImpl")
    ConnessioniService connessioniServiceImpl;

    @KafkaListener(topics = "${asw.kafka.topic.connessione.in}")
    public void listener(ConnessioneCreatedEvent connessioneCreatedEvent) {
        logger.info(String.format("$$$$ => Consumed message: %s", connessioneCreatedEvent));
        connessioniServiceImpl.save(new Connessione(connessioneCreatedEvent.getId(), connessioneCreatedEvent.getFollower(), connessioneCreatedEvent.getFollowed()));
    }

}
