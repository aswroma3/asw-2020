package asw.instagnam.ricetteseguite.connessioni;

import asw.instagnam.ricetteseguite.domain.*; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*; 

@Service 
@Primary 
public class ConnessioniServiceWebClient implements ConnessioniService {

	@Autowired 
	@Qualifier("loadBalancedWebClient")
    private WebClient loadBalancedWebClient;
	
	public Collection<Connessione> getConnessioniByFollower(String follower) {
		Collection<Connessione> connessioni = null; 
        Flux<Connessione> response = loadBalancedWebClient
                .get()
				.uri(uriBuilder -> uriBuilder 
					.scheme("http")
					.host("connessioni")
					.path("/connessioni")
					.queryParam("follower", follower)
					.build())
                .retrieve()
                .bodyToFlux(Connessione.class);
        try {
            connessioni = response.collectList().block();
        } catch (WebClientException e) {
            e.printStackTrace();
        }
		return connessioni; 
	}	

}
