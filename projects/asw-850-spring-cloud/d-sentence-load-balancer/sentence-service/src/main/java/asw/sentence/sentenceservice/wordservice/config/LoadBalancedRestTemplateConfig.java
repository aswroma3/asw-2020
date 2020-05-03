package asw.sentence.sentenceservice.wordservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

/* Configurazione per un RestTemplate load balanced. */
@Configuration
public class LoadBalancedRestTemplateConfig {

   @LoadBalanced
   @Bean
   public RestTemplate loadBalancedRestTemplate() {
      return new RestTemplate();
   }
	
}

