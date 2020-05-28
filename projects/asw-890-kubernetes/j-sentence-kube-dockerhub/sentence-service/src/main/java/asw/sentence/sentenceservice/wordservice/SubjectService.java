package asw.sentence.sentenceservice.wordservice;

import asw.sentence.sentenceservice.domain.WordService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import java.util.logging.Logger; 

@Service 
public class SubjectService implements WordService {

	private final Logger logger = Logger.getLogger(SubjectService.class.toString()); 

	@Autowired 
	private SubjectClient subjectClient;

	@Autowired 
	private CircuitBreakerFactory cbFactory;
	
	public String getWord() {
		return cbFactory.create("getSubject").run(
				() -> subjectClient.getWord(),
                throwable -> this.getFallbackWord()
			);
	} 
	
	public String getFallbackWord() {
		logger.info("subject.getWord(): FALLBACK WORD: *Someone*");
		return "*Someone*"; 
	}
	
}
