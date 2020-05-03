package asw.sentence.sentenceservice.wordservice;

import asw.sentence.sentenceservice.domain.WordService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import java.util.logging.Logger; 

@Service 
public class VerbService implements WordService {

	private final Logger logger = Logger.getLogger(VerbService.class.toString()); 

	@Autowired 
	private VerbClient verbClient;

	@Autowired 
	private CircuitBreakerFactory cbFactory;

	public String getWord() {
		return cbFactory.create("getVerb").run(
				() -> verbClient.getWord(),
                throwable -> this.getFallbackWord()
			);
	} 
	
	public String getFallbackWord() {
		logger.info("verb.getWord(): FALLBACK WORD: *does*");
		return "*does*"; 
	}
	
}
