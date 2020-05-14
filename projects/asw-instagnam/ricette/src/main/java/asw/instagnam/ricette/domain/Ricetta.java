package asw.instagnam.ricette.domain;

import lombok.*; 

/* Ricetta, in formato breve. */ 
@Data @NoArgsConstructor
public class Ricetta {

	private Long id; 
	private String autore; 
	private String titolo; 
	
	public Ricetta(RicettaCompleta r) {
		this.id = r.getId(); 
		this.autore = r.getAutore(); 
		this.titolo = r.getTitolo(); 
	}
	
}

