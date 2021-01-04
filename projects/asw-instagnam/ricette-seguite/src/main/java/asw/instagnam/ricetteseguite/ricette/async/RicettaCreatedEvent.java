package asw.instagnam.ricetteseguite.ricette.async;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RicettaCreatedEvent {

    private Long id;
    private String autore;
    private String titolo;

}
