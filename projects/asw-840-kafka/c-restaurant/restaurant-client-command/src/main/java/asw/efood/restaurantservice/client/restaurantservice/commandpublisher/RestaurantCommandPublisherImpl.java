package asw.efood.restaurantservice.client.restaurantservice.commandpublisher;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 
import asw.efood.restaurantservice.client.domain.*; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;

import java.util.logging.Logger;

@Service
public class RestaurantCommandPublisherImpl implements RestaurantCommandPublisher {

    private final Logger logger = Logger.getLogger(RestaurantCommandPublisherImpl.class.toString());

    @Autowired
    private KafkaTemplate<String, Command> template;

	private String channel = RestaurantServiceCommandChannel.channel; 

    @Override
    public void publish(Command command) {
        logger.info("PUBLISHING COMMAND: " + command.toString() + " ON CHANNEL: " + channel);
        template.send(channel, command);
        // template.flush();
    }

}
