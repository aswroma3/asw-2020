package asw.instagnam.ricetteseguite.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = @Index(name = "index_follower", columnList = "follower"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Connessione {

    @Id
    private Long id;
    private String follower;
    private String followed;

}
