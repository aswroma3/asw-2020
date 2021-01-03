package asw.instagnam.ricette.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RicettaMessagePublisher implements MessagePublisher {

    private final Logger logger = Logger.getLogger(RicettaMessagePublisher.class.toString());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "ricetteTopic";

    public void sendMessage(String message) {
        logger.info(String.format("$$$$ => Producing message: %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }

}
