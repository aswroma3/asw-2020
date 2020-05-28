package asw.sentence.sentenceservice.wordservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient("subject")
@FeignClient(name="subject", url="subject:8080")
public interface SubjectClient {

	@GetMapping("/")
	public String getWord(); 

}
