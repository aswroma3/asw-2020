package asw.instagnam.ricetteseguite.connessioni.async;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConnessioneMessageConsumer {

    @KafkaListener(topics = "${asw.kafka.topic.connessione.in}")
    public void list(ConnessioneCreatedEvent connessioneCreatedEvent) {
        System.out.println("connessione event received " + connessioneCreatedEvent);
    }

}
