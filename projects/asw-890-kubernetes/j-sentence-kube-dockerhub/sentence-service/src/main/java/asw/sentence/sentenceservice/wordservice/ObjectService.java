package asw.sentence.sentenceservice.wordservice;

import asw.sentence.sentenceservice.domain.WordService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import java.util.logging.Logger; 

@Service 
public class ObjectService implements WordService {

	private final Logger logger = Logger.getLogger(ObjectService.class.toString()); 

	@Autowired 
	private ObjectClient objectClient;
	
	@Autowired 
	private CircuitBreakerFactory cbFactory;

	public String getWord() {
		return cbFactory.create("getObject").run(
				() -> objectClient.getWord(),
                throwable -> this.getFallbackWord()
			);
	} 
	
	public String getFallbackWord() {
		logger.info("object.getWord(): FALLBACK WORD: *something*");
		return "*something*"; 
	}

}
