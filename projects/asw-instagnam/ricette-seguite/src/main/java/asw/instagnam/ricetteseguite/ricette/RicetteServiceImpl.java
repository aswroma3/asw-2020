package asw.instagnam.ricetteseguite.ricette;

import asw.instagnam.ricetteseguite.domain.Ricetta;
import asw.instagnam.ricetteseguite.domain.RicettaRepository;
import asw.instagnam.ricetteseguite.domain.RicetteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class RicetteServiceImpl implements RicetteService {

    @Autowired
    RicettaRepository ricettaRepository;

    @Override
    public Collection<Ricetta> getRicetteByAutore(String autore) {
        return ricettaRepository.findByAutore(autore);
    }

    @Override
    public void save(Ricetta ricetta) {
        ricettaRepository.save(ricetta);
    }

}
