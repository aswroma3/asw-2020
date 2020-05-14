package asw.instagnam.ricette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class RicetteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RicetteApplication.class, args);
	}
}
