package asw.instagnam.ricetteseguite.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ConnessioneRepository extends JpaRepository<Connessione, Long> {

    List<Connessione> findByFollower(String follower);

}
