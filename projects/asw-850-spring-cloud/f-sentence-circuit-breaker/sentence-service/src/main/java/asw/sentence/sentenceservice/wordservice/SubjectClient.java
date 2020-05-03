package asw.sentence.sentenceservice.wordservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("subject")
public interface SubjectClient {

	@GetMapping("/")
	public String getWord(); 

}
