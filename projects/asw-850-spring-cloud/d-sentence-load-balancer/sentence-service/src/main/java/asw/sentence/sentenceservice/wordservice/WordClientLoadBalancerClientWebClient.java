package asw.sentence.sentenceservice.wordservice;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.ServiceInstance;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service 
@Primary 
public class WordClientLoadBalancerClientWebClient implements WordClient {

	@Autowired 
	private LoadBalancerClient loadBalancer;

    private WebClient webClient = WebClient.builder().build();
	
	public String getWord(String service) {
		ServiceInstance instance = loadBalancer.choose(service);
		if (instance!=null) {
			URI uri = instance.getUri();
			if (uri!=null) {
				return webClientGet(uri);
			}
		}
		return null;
	}	

	private String webClientGet(URI wordUri) {
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
