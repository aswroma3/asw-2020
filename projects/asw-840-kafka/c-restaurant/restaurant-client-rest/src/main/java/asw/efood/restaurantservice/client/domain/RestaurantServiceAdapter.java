package asw.efood.restaurantservice.client.domain;

import java.util.*; 

public interface RestaurantServiceAdapter {
    Long createRestaurant(String name, String location);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurant(Long restaurantId);
}
