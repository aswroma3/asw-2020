package asw.sentence.sentenceservice.wordservice;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service 
@Primary 
public class WordClientWebClient implements WordClient {

    private WebClient webClient = WebClient.builder().build();
	
	public String getWord(String wordUri) {
		String word = null; 
        Mono<String> response = webClient
                .get()
				.uri(wordUri)
                .retrieve()
                .bodyToMono(String.class);
        try {
            word = response.block();
        } catch (WebClientException e) {
            // e.printStackTrace();
        }
		return word; 
	}	

}
