package asw.efood.restaurantservice.client.domain;

import asw.efood.common.api.command.Command; 

public interface RestaurantCommandPublisher {

    public void publish(Command command); 

}
