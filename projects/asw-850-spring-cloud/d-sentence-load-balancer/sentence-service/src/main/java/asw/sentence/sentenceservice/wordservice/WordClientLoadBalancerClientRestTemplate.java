package asw.sentence.sentenceservice.wordservice;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.client.RestTemplate;

@Service 
// @Primary 
public class WordClientLoadBalancerClientRestTemplate implements WordClient {

	@Autowired 
	private LoadBalancerClient loadBalancer;

	@Autowired 
	private RestTemplate restTemplate;

	public String getWord(String service) {
		ServiceInstance instance = loadBalancer.choose(service);
		if (instance!=null) {
			URI uri = instance.getUri();
			if (uri!=null) {
				return restTemplate.getForObject(uri, String.class);
			}
		}
		return null;
	}	
	
}
