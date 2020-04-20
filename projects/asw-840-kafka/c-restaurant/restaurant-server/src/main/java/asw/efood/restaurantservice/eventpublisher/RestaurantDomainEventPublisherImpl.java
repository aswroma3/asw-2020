package asw.efood.restaurantservice.eventpublisher;

import asw.efood.common.api.event.DomainEvent;
import asw.efood.restaurantservice.api.event.RestaurantServiceEventChannel; 
import asw.efood.restaurantservice.domain.RestaurantDomainEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component 
public class RestaurantDomainEventPublisherImpl implements RestaurantDomainEventPublisher {

    private final Logger logger = Logger.getLogger(RestaurantDomainEventPublisherImpl.class.toString());

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

	private String channel = RestaurantServiceEventChannel.channel; 

    @Override
    public void publish(DomainEvent event) {
        logger.info("PUBLISHING EVENT: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
        // template.flush();
    }

}
