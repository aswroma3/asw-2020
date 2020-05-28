package asw.sentence.sentenceservice.wordservice;

import asw.sentence.sentenceservice.domain.WordService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;

import java.util.logging.Logger; 

@Service 
public class SubjectService implements WordService {

	private final Logger logger = Logger.getLogger(SubjectService.class.toString()); 
	
	private static final String CIRCUIT_BREAKER_NAME = "getSubject";

	@Autowired 
	private SubjectClient subjectClient;

	@Autowired 
	private CircuitBreakerFactory cbFactory;
	
	@Async
	public CompletableFuture<String> getWord() {
//		return CompletableFuture.completedFuture(subjectClient.getWord()); 
		return CompletableFuture.completedFuture(this.getWordCBWrapper()); 
	} 

	public String getWordCBWrapper() {
		return cbFactory.create(CIRCUIT_BREAKER_NAME).run(
				() -> subjectClient.getWord(),
                throwable -> this.getFallbackWord()
			);
	} 
	
	private String getFallbackWord() {
		logger.info("subject.getWord(): FALLBACK WORD: *Someone*");
		return "*Someone*"; 
	}
	
}
