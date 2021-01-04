package asw.instagnam.ricetteseguite.domain;

import java.util.*; 

public interface RicetteService {

	public Collection<Ricetta> getRicetteByAutore(String autore); 

	public void save(Ricetta ricetta);

}
