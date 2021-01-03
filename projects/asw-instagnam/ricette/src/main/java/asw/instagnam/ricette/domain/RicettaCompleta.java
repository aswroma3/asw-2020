package asw.instagnam.ricette.domain;

import javax.persistence.*; 

import lombok.*; 

/* Ricetta, in formato completo. */ 
@Entity 
@Data @NoArgsConstructor
public class RicettaCompleta {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	private String autore; 
	private String titolo; 
	private String preparazione; 
	
	public RicettaCompleta(String autore, String titolo, String preparazione) {
		this(); 
		this.autore = autore; 
		this.titolo = titolo; 
		this.preparazione = preparazione; 
	}
	
}
