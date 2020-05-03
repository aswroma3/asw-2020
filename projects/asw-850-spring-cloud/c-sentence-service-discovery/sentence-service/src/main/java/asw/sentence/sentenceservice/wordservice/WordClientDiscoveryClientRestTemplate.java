package asw.sentence.sentenceservice.wordservice;

import java.net.URI;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Primary;

@Service 
// @Primary 
public class WordClientDiscoveryClientRestTemplate implements WordClient {

	@Autowired 
	private DiscoveryClient discoveryClient;

	@Autowired 
	private RestTemplate restTemplate;
	
	private Random random = new Random(); 

	public String getWord(String service) {
		List<ServiceInstance> list = discoveryClient.getInstances(service);
		if (list!=null && list.size()>0) {
//			URI uri = list.get(0).getUri();
			int randomIndex = random.nextInt(list.size());
			URI uri = list.get(randomIndex).getUri();
			if (uri!=null) {
				return restTemplate.getForObject(uri, String.class);
			}
		}
		return null;
	}	
	
}
