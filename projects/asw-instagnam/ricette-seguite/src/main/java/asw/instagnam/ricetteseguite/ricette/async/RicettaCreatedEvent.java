package asw.instagnam.ricetteseguite.ricette.async;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RicettaCreatedEvent {

    private Long id;
    private String autore;
    private String titolo;

}
