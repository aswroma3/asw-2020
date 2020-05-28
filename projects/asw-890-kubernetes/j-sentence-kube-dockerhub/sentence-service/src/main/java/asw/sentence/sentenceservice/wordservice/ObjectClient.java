package asw.sentence.sentenceservice.wordservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient("object")
@FeignClient(name="object", url="object:8080")
public interface ObjectClient {

	@GetMapping("/")
	public String getWord(); 

}
