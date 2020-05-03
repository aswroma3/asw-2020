package asw.sentence.sentenceservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service 
public class SentenceService {

	@Autowired 
	private WordService subjectService;

	@Autowired 
	private WordService verbService;

	@Autowired 
	private WordService objectService;

	public String getSentence() {
		String sentence = 
			subjectService.getWord() + " " + 
			verbService.getWord() + " " + 
			objectService.getWord() + ".";
		return sentence; 
	}
	
}
