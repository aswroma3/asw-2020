package asw.instagnam.ricette.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface RicetteRepository extends CrudRepository<RicettaCompleta, Long> {

	public Collection<RicettaCompleta> findAll();

	public Collection<RicettaCompleta> findAllByAutore(String autore);

}

