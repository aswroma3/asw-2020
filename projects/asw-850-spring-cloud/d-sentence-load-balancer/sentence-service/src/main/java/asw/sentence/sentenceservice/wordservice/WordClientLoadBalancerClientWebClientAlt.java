package asw.sentence.sentenceservice.wordservice;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service 
// @Primary 
public class WordClientLoadBalancerClientWebClientAlt implements WordClient {

	@Autowired 
	@Qualifier("loadBalancedWebClient")
    private WebClient loadBalancedWebClient;
	
	public String getWord(String service) {
		String word = null; 
		String wordUri = "http://" + service; 
        Mono<String> response = loadBalancedWebClient
                .get()
				.uri(wordUri)
                .retrieve()
                .bodyToMono(String.class);
        try {
            word = response.block();
        } catch (WebClientException e) {
            e.printStackTrace();
        }
		return word; 
	}	

}
