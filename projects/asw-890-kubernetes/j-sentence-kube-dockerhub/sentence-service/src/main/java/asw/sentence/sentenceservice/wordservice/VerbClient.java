package asw.sentence.sentenceservice.wordservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient("verb")
@FeignClient(name="verb", url="verb:8080")
public interface VerbClient {

	@GetMapping("/")
	public String getWord(); 

}
