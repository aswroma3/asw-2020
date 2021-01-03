package asw.instagnam.connessioni.async;

import asw.instagnam.connessioni.domain.Connessione;
import org.apache.commons.lang.StringUtils;
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

    @Value("${asw.kafka.topic.out:}")
    private String topic;

    public void sendMessage(Connessione connessione) {
        if (StringUtils.isBlank(topic)) {
            //TODO -> Questa è una decisione importante! Dal punto di vista architetturale stiamo perdendo
            // un'informazione! Ce lo possiamo permettere? Se no, eviteremo di far salire il contesto dando un errore
            logger.warning("Skip sending message:" + connessione + ". No topic configured");
            return;
        }
        ConnessioneCreatedEvent connessioneCreatedEvent = new ConnessioneCreatedEvent(connessione.getId(), connessione.getFollower(), connessione.getFollowed());
        logger.info(String.format("$$$$ => Producing message: %s", connessioneCreatedEvent));
        this.kafkaTemplate.send(topic, connessioneCreatedEvent);
    }

}
