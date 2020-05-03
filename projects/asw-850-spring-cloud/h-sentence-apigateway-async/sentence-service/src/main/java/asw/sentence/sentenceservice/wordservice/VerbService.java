package asw.sentence.sentenceservice.wordservice;

import asw.sentence.sentenceservice.domain.WordService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;

import java.util.logging.Logger; 

@Service 
public class VerbService implements WordService {

	private final Logger logger = Logger.getLogger(VerbService.class.toString()); 

	private static final String CIRCUIT_BREAKER_NAME = "getVerb";

	@Autowired 
	private VerbClient verbClient;

	@Autowired 
	private CircuitBreakerFactory cbFactory;
	
	@Async
	public CompletableFuture<String> getWord() {
//		return CompletableFuture.completedFuture(verbClient.getWord()); 
		return CompletableFuture.completedFuture(this.getWordCBWrapper()); 
	} 
	
	public String getWordCBWrapper() {
		return cbFactory.create(CIRCUIT_BREAKER_NAME).run(
				() -> verbClient.getWord(),
                throwable -> this.getFallbackWord()
			);
	} 

	private String getFallbackWord() {
		logger.info("verb.getWord(): FALLBACK WORD: *does*");
		return "*does*"; 
	}
	
}
