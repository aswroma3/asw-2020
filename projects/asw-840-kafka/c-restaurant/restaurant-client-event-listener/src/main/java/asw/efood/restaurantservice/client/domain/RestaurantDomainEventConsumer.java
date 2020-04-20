package asw.efood.restaurantservice.client.domain;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import org.springframework.stereotype.Service;

import java.util.logging.*;

@Service
public class RestaurantDomainEventConsumer {

	private final Logger logger = Logger.getLogger(RestaurantDomainEventConsumer.class.toString());

	public void onEvent(DomainEvent event) {
		if (event.getClass().equals(RestaurantCreatedEvent.class)) {
			RestaurantCreatedEvent rce = (RestaurantCreatedEvent) event;
			restaurantCreated(rce); 
//			Restaurant restaurant = new Restaurant(rce.getId(), rce.getName(), rce.getLocation());
//			logger.info("CREATED RESTAURANT: " + restaurant);
		} else {
			logger.info("UNKNOWN EVENT: " + event);			
		}
	}
	
	private void restaurantCreated(RestaurantCreatedEvent event) {
		Restaurant restaurant = new Restaurant(event.getId(), event.getName(), event.getLocation());
		logger.info("CREATED RESTAURANT: " + restaurant);
	}

}
