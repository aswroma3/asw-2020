package asw.kafka.simplefilter.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Service 
public class SimpleFilterService {

    private final Logger logger = Logger.getLogger(SimpleFilterService.class.toString());

	@Autowired
	private SimpleMessagePublisher simpleMessagePublisher;

	@Value("${asw.kafka.filter.name}")
	private String filterName;

    public void filter(String inMessage) {
		String outMessage = String.format("*** [%1$s] %2$s ***", filterName, inMessage);
		logger.info("FILTERING MESSAGE: " + inMessage + " TO: " + outMessage);
		simpleMessagePublisher.publish(outMessage); 
	}
	
}
