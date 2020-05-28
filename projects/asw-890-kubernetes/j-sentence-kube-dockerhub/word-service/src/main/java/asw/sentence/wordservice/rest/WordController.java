package asw.sentence.wordservice.rest;

import asw.sentence.wordservice.domain.WordService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.server.ResponseStatusException; 
import org.springframework.http.HttpStatus;

import java.util.logging.Logger; 
import java.util.Random;

@RestController
public class WordController {

	@Autowired 
	private WordService wordService;

	@Value("${asw.sentence.wordservice.latency}") 
	/* la latenza da introdurre */ 
	private int latency;

	@Value("${asw.sentence.wordservice.failureRate}") 
	/* tasso percentuale di fallimento, tra 0 e 100 */ 
	private int failureRate;

	private Random random = new Random(); 

	private final Logger logger = Logger.getLogger(WordController.class.toString()); 

	@GetMapping("/")
	public String getWord() {
		String word = wordService.getWord(); 
		if (failureRate>0) {
			int randomIndex = random.nextInt(100);
			if (randomIndex<failureRate) {
				String errorMessage = "Word service is temporarily unavailable";
				logger.info("getWord() ERROR: " + errorMessage);
				throw new ResponseStatusException(
					HttpStatus.SERVICE_UNAVAILABLE, errorMessage);
			}
		}
		if (latency>0) {
			this.sleep(latency); 
			word += " (" + latency + ")";
		}
		logger.info("getWord(): " + word);
		return word; 
	}

	/* Introduce un ritardo di delay millisecondi. */
	private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
	}

}
