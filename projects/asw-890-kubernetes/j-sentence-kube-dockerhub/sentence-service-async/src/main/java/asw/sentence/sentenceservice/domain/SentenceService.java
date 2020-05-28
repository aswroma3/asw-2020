package asw.sentence.sentenceservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

@Service 
public class SentenceService {

	@Autowired 
	private WordService subjectService;

	@Autowired 
	private WordService verbService;

	@Autowired 
	private WordService objectService;

	public String getSentence() {
		CompletableFuture<String> subject = subjectService.getWord(); 
		CompletableFuture<String> verb = verbService.getWord(); 
		CompletableFuture<String> object = objectService.getWord(); 
		CompletableFuture.allOf(subject, verb, object).join();
		String sentence = null; 
		try {
			sentence = subject.get() + " " + verb.get() + " " + object.get() + ".";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sentence; 
	}

}
