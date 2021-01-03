package asw.instagnam.ricette.async;

import asw.instagnam.ricette.domain.RicettaCompleta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RicettaMessagePublisherImpl implements RicettaMessagePublisher {

    private final Logger logger = Logger.getLogger(RicettaMessagePublisher.class.toString());

    @Autowired
    private KafkaTemplate<String, RicettaCreatedEvent> kafkaTemplate;

    @Value("${asw.kafka.topic.out}")
    private String topic;

    public void sendMessage(RicettaCompleta ricettaCompleta) {
        RicettaCreatedEvent ricettaCreatedEvent = new RicettaCreatedEvent(ricettaCompleta.getId(), ricettaCompleta.getAutore(), ricettaCompleta.getTitolo());
        logger.info(String.format("$$$$ => Producing message: %s", ricettaCreatedEvent));
        this.kafkaTemplate.send(topic, ricettaCreatedEvent);
    }

}
