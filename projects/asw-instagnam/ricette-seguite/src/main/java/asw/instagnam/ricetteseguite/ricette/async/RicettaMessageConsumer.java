package asw.instagnam.ricetteseguite.ricette.async;

import asw.instagnam.ricetteseguite.domain.Ricetta;
import asw.instagnam.ricetteseguite.domain.RicetteService;
import asw.instagnam.ricetteseguite.rest.RicetteSeguiteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RicettaMessageConsumer {

    private final Logger logger = Logger.getLogger(RicetteSeguiteController.class.toString());

    @Autowired
    RicetteService ricetteService;

    @KafkaListener(topics = "${asw.kafka.topic.ricetta.in}")
    public void listener(RicettaCreatedEvent ricettaCreatedEvent) {
        logger.info(String.format("$$$$ => Consumed message: %s", ricettaCreatedEvent));
        ricetteService.save(new Ricetta(ricettaCreatedEvent.getId(), ricettaCreatedEvent.getAutore(), ricettaCreatedEvent.getTitolo()));
    }

}
