package asw.instagnam.ricetteseguite.connessioni;

import asw.instagnam.ricetteseguite.domain.Connessione;
import asw.instagnam.ricetteseguite.domain.ConnessioneRepository;
import asw.instagnam.ricetteseguite.domain.ConnessioniService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class ConnessioniServiceImpl implements ConnessioniService {

    @Autowired
    ConnessioneRepository connessioneRepository;

    @Override
    public Collection<Connessione> getConnessioniByFollower(String follower) {
        return connessioneRepository.findByFollower(follower);
    }

    @Override
    public void save(Connessione connessione) {
        connessioneRepository.save(connessione);
    }

}
