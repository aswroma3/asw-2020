package asw.instagnam.ricetteseguite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class RicetteSeguiteService {

    @Autowired
    @Qualifier("connessioniServiceImpl")
    private ConnessioniService connessioniService;

    @Autowired
    @Qualifier("ricetteServiceImpl")
    private RicetteService ricetteService;

    /* Trova le ricette (in formato breve) degli utenti seguiti da utente. */
    public Collection<Ricetta> getRicetteSeguite(String utente) {
        Collection<Ricetta> ricette = new ArrayList<>();
        Collection<Connessione> connessioni = connessioniService.getConnessioniByFollower(utente);
        for (Connessione connessione : connessioni) {
            String followed = connessione.getFollowed();
            Collection<Ricetta> ricetteByFollowed = ricetteService.getRicetteByAutore(followed);
            ricette.addAll(ricetteByFollowed);
        }
        return ricette;
    }

}
