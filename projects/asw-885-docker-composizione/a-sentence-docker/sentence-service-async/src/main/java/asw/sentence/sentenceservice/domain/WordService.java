package asw.sentence.sentenceservice.domain;

import java.util.concurrent.CompletableFuture;

public interface WordService {

	public CompletableFuture<String> getWord(); 
	
}
