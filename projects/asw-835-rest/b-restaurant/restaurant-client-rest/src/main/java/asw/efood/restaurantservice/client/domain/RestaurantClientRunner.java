package asw.efood.restaurantservice.client.domain;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.*;

@Component
public class RestaurantClientRunner implements CommandLineRunner {

	private static final Logger logger = Logger.getLogger(RestaurantClientRunner.class.toString());

	@Autowired
	private RestaurantServiceAdapter restaurantServiceAdapter;

	public void run(String[] args) throws InterruptedException {
		logger.info(restaurantServiceAdapter.getRestaurant(1L).toString());

		Long newRestaurantId = restaurantServiceAdapter.createRestaurant("Alfa", "Roma");
		logger.info(newRestaurantId.toString());
		logger.info(restaurantServiceAdapter.getRestaurant(newRestaurantId).toString());

		logger.info(restaurantServiceAdapter.getAllRestaurants().toString());
	}
}
