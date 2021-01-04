package asw.instagnam.ricetteseguite.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RicettaRepository extends JpaRepository<Ricetta, Long> {

    Collection<Ricetta> findByAutore(String autore);

}
