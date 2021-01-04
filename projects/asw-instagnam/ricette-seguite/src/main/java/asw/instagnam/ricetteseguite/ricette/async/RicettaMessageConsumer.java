package asw.instagnam.ricetteseguite.ricette.async;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RicettaMessageConsumer {

    @KafkaListener(topics = "${asw.kafka.topic.ricetta.in}")
    public void list(RicettaCreatedEvent ricettaCreatedEvent) {
        System.out.println("ricetta event received " + ricettaCreatedEvent);
    }

}
