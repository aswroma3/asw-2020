package asw.sentence.sentenceservice.wordservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("verb")
public interface VerbClient {

	@GetMapping("/")
	public String getWord(); 

}
