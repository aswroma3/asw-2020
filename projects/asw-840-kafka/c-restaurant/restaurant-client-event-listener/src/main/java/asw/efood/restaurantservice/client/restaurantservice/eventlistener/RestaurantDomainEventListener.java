package asw.efood.restaurantservice.client.restaurantservice.eventlistener;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import asw.efood.restaurantservice.client.domain.RestaurantDomainEventConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component 
public class RestaurantDomainEventListener {

    private final Logger logger = Logger.getLogger(RestaurantDomainEventListener.class.toString());

    @Autowired
    private RestaurantDomainEventConsumer restaurantDomainEventConsumer;

	@KafkaListener(topics = RestaurantServiceEventChannel.channel)
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("EVENT LISTENER: " + record.toString());
        DomainEvent event = record.value();
		restaurantDomainEventConsumer.onEvent(event); 
    }

}
