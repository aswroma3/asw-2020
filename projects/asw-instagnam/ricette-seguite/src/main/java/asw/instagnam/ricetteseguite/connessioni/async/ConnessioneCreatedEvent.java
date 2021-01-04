package asw.instagnam.ricetteseguite.connessioni.async;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnessioneCreatedEvent {

    private Long id;
    private String follower;
    private String followed;


}
