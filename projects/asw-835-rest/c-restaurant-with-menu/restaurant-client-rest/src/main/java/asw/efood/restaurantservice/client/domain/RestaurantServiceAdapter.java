package asw.efood.restaurantservice.client.domain;

import java.util.*; 

public interface RestaurantServiceAdapter {
    Long createRestaurant(String name, String location);
    Long createRestaurantMenu(Long restaurantId, List<MenuItem> menuItems);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurant(Long restaurantId);
    List<MenuItem> getRestaurantMenu(Long restaurantId);
}
