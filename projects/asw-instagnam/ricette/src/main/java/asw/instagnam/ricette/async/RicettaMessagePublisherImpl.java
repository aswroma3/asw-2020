package asw.instagnam.ricette.async;

import asw.instagnam.ricette.domain.RicettaCompleta;
import org.apache.commons.lang.StringUtils;
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

    @Value("${asw.kafka.topic.out:}")
    private String topic;

    public void sendMessage(RicettaCompleta ricettaCompleta) {
        if (StringUtils.isBlank(topic)) {
            //TODO -> Questa è una decisione importante! Dal punto di vista architetturale stiamo perdendo
            // un'informazione! Ce lo possiamo permettere? Se no, eviteremo di far salire il contesto dando un errore
            logger.warning("Skip sending message:" + ricettaCompleta + ". No topic configured");
            return;
        }
        RicettaCreatedEvent ricettaCreatedEvent = new RicettaCreatedEvent(ricettaCompleta.getId(), ricettaCompleta.getAutore(), ricettaCompleta.getTitolo());
        logger.info(String.format("$$$$ => Producing message: %s", ricettaCreatedEvent));
        this.kafkaTemplate.send(topic, ricettaCreatedEvent);
    }

}
