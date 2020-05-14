package asw.instagnam.ricetteseguite.domain;

import lombok.*; 

@Data @NoArgsConstructor
public class Connessione {

	private Long id; 
	private String follower; 
	private String followed; 
	
}
