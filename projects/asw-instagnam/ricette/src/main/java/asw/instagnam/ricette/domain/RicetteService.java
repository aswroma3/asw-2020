package asw.instagnam.ricette.domain;

import asw.instagnam.ricette.async.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RicetteService {

    @Autowired
    private RicetteRepository ricetteRepository;

    @Autowired
    private MessagePublisher<RicettaCompleta> ricettaMessagePublisher;

    public RicettaCompleta createRicetta(String autore, String titolo, String preparazione) {
        RicettaCompleta ricetta = new RicettaCompleta(autore, titolo, preparazione);
        ricetta = ricetteRepository.save(ricetta);
        ricettaMessagePublisher.sendMessage(ricetta);
        return ricetta;
    }

    public RicettaCompleta getRicetta(Long id) {
        RicettaCompleta ricetta = ricetteRepository.findById(id).orElse(null);
        return ricetta;
    }

    public Collection<RicettaCompleta> getRicette() {
        Collection<RicettaCompleta> ricette = ricetteRepository.findAll();
        return ricette;
    }

    public Collection<RicettaCompleta> getRicetteByAutore(String autore) {
        Collection<RicettaCompleta> ricette = ricetteRepository.findAllByAutore(autore);
        return ricette;
    }

}
