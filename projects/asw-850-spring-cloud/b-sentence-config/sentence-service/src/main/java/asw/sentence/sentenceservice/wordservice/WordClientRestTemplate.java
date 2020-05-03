package asw.sentence.sentenceservice.wordservice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

@Service 
// @Primary 
public class WordClientRestTemplate implements WordClient {

	@Autowired 
	private RestTemplate restTemplate;
	
	public String getWord(String wordUri) {
		return restTemplate.getForObject(wordUri, String.class);
	}	

}
