package asw.instagnam.ricetteseguite.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/* Ricetta (in formato breve). */
@Entity
@Table(indexes = @Index(name = "index_autore", columnList = "autore"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ricetta {

	@Id
	private Long id; 
	private String autore; 
	private String titolo; 
	
}
