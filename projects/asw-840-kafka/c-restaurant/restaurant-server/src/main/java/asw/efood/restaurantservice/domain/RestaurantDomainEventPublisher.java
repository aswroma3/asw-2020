package asw.efood.restaurantservice.domain;

import asw.efood.common.api.event.DomainEvent; 

public interface RestaurantDomainEventPublisher {

    public void publish(DomainEvent event);

}
