package asw.instagnam.ricette.rest;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRicettaRequest {

	private String autore; 
	private String titolo; 
	private String preparazione; 

}

