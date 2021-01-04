package asw.instagnam.ricetteseguite.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor
public class Connessione {

	@Id
	private Long id; 
	private String follower; 
	private String followed; 
	
}
