package asw.instagnam.ricetteseguite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RicetteSeguiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RicetteSeguiteApplication.class, args);
	}
}
