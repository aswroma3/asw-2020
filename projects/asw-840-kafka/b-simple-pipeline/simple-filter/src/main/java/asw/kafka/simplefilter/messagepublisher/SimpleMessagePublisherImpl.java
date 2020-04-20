package asw.kafka.simplefilter.messagepublisher;

import asw.kafka.simplefilter.domain.SimpleMessagePublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component 
public class SimpleMessagePublisherImpl implements SimpleMessagePublisher {

    private final Logger logger = Logger.getLogger(SimpleMessagePublisherImpl.class.toString());

	@Value("${asw.kafka.channel.out}")
	private String channel;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void publish(String message) {
        // logger.info("PUBLISHING MESSAGE: " + message + " ON CHANNEL: " + channel);
        template.send(channel, message);
        // template.flush();
    }

}
