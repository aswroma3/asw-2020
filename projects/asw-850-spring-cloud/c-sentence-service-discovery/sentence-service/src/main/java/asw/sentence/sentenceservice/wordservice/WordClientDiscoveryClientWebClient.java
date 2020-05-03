package asw.sentence.sentenceservice.wordservice;

import java.net.URI;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service 
@Primary 
public class WordClientDiscoveryClientWebClient implements WordClient {

	@Autowired 
	private DiscoveryClient discoveryClient;

    private WebClient webClient = WebClient.builder().build();

	private Random random = new Random(); 

	public String getWord(String service) {
		List<ServiceInstance> list = discoveryClient.getInstances(service);
		if (list!=null && list.size()>0) {
//			URI uri = list.get(0).getUri();
			int randomIndex = random.nextInt(list.size());
			URI uri = list.get(randomIndex).getUri();
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
