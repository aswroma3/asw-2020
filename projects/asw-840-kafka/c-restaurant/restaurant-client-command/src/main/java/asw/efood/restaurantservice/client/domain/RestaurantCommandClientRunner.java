package asw.efood.restaurantservice.client.domain;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.*;

@Component
public class RestaurantCommandClientRunner implements CommandLineRunner {

	private final Logger logger = Logger.getLogger(RestaurantCommandClientRunner.class.toString());

	@Autowired
	private RestaurantCommandPublisher restaurantCommandPublisher;

	public void run(String[] args) throws InterruptedException {
		Command command; 
		
		command = new CreateRestaurantCommand("Da Mario", "Roma"); 
		logger.info("ISSUING COMMAND: " + command); 
		restaurantCommandPublisher.publish(command);
		
		command = new CreateRestaurantCommand("Da Giovanna", "Torino"); 
		logger.info("ISSUING COMMAND: " + command); 
		restaurantCommandPublisher.publish(command);
	}
}
