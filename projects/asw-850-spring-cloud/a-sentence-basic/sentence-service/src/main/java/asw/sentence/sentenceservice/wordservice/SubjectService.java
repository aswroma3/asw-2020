package asw.sentence.sentenceservice.wordservice;

import asw.sentence.sentenceservice.domain.WordService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 

@Service 
public class SubjectService implements WordService {

	private final Logger logger = Logger.getLogger(SubjectService.class.toString()); 

	@Value("${asw.sentence.sentenceservice.subject.uri}") 
	private String subjectUri;

	@Autowired 
	private WordClient wordClient;
	
	public String getWord() {
		return wordClient.getWord(subjectUri); 
	}
	
}



