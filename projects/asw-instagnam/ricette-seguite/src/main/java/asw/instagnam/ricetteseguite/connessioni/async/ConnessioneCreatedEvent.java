package asw.instagnam.ricetteseguite.connessioni.async;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnessioneCreatedEvent {

    private Long id;
    private String follower;
    private String followed;


}
