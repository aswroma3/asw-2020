package asw.instagnam.connessioni.domain;

import javax.persistence.*; 

import lombok.*; 

@Entity 
@Data @NoArgsConstructor
public class Connessione {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	private String follower; 
	private String followed; 
	
	public Connessione(String follower, String followed) {
		this(); 
		this.follower = follower; 
		this.followed = followed; 
	}
	
}
