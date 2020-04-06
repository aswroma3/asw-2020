package asw.efood.restaurantservice.client.domain;

import java.util.*; 

public interface RestaurantServiceAdapter {
    Long createRestaurant(String name, String location);
    Restaurant getRestaurant(Long restaurantId);
    List<Restaurant> getAllRestaurants();
}
