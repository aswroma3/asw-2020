package asw.instagnam.connessioni.async;

import asw.instagnam.connessioni.domain.Connessione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ConnessioneMessagePublisherImpl implements ConnessioneMessagePublisher {

    private final Logger logger = Logger.getLogger(ConnessioneMessagePublisherImpl.class.toString());

    @Autowired
    private KafkaTemplate<String, ConnessioneCreatedEvent> kafkaTemplate;

    @Value("${asw.kafka.topic.out}")
    private String topic;

    public void sendMessage(Connessione connessione) {
        ConnessioneCreatedEvent connessioneCreatedEvent = new ConnessioneCreatedEvent(connessione.getId(), connessione.getFollower(), connessione.getFollowed());
        logger.info(String.format("$$$$ => Producing message: %s", connessioneCreatedEvent));
        this.kafkaTemplate.send(topic, connessioneCreatedEvent);
    }

}
