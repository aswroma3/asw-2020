package asw.sentence.sentenceservice.wordservice;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.web.client.RestTemplate;

@Service 
// @Primary 
public class WordClientLoadBalancerClientRestTemplateAlt implements WordClient {

	@Autowired 
	private RestTemplate loadBalancedRestTemplate;

	public String getWord(String service) {
		String uri = "http://" + service;
		return loadBalancedRestTemplate.getForObject(uri, String.class);
	}	

}
