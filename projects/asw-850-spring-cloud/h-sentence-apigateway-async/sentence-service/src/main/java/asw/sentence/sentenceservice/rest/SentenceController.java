package asw.sentence.sentenceservice.rest;

import asw.sentence.sentenceservice.domain.SentenceService; 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant; 
import java.time.Duration; 

import java.util.logging.Logger; 

@RestController
public class SentenceController {

	private final Logger logger = Logger.getLogger(SentenceController.class.toString()); 

	@Autowired 
	private SentenceService sentenceService;

	@Value("${asw.sentence.sentenceservice.returnLatency}") 
	/* effettua il logging della latenza */ 
	private boolean returnLatency;

	@GetMapping("/")
	public String getSentence() {
		Instant start = Instant.now();
		String sentence = sentenceService.getSentence(); 
		Duration duration = Duration.between(start, Instant.now()); 
		if (returnLatency) {
			sentence += " [duration: " + duration + "]";
		}
		logger.info("getSentence(): " + sentence);
		return sentence; 
	}
	
}
