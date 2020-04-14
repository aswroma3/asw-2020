package asw.rest.hello.client.hello.rest;

import asw.rest.hello.client.domain.HelloServiceAdapter;

import java.util.logging.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service
public class HelloServiceAdapterRestImpl implements HelloServiceAdapter {

    private Logger logger = Logger.getLogger(HelloServiceAdapterRestImpl.class.toString());

    @Value("${asw.rest.hello.service.uri}")
    private String helloServiceUri;

    private WebClient webClient;

    public HelloServiceAdapterRestImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(helloServiceUri).build();
    }

	/* Invoca sayHello. */ 
    public String sayHello(String name) {
        logger.info("sayHello(" + name + ") [WEBCLIENT]");
		String greeting = null; 
        Mono<String> response = webClient
                .get()
				.uri(helloServiceUri + "/hello/{name}", name)
                .retrieve()
                .bodyToMono(String.class);
        try {
            greeting = response.block();
        } catch (WebClientException e) {
            logger.info("sayHello(" + name + ") [WEBCLIENT] exception: " + e.getMessage());
        }
		logger.info("sayHello(" + name + ") [WEBCLIENT]: " + greeting);
		return greeting; 
    }

}
