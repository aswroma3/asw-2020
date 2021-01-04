package asw.instagnam.ricetteseguite.rest;

import asw.instagnam.ricetteseguite.domain.Connessione;
import asw.instagnam.ricetteseguite.domain.ConnessioneRepository;
import asw.instagnam.ricetteseguite.domain.Ricetta;
import asw.instagnam.ricetteseguite.domain.RicettaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RicetteSeguiteControllerTest {

    @Autowired
    ConnessioneRepository connessioneRepository;

    @Autowired
    RicettaRepository ricettaRepository;

    @Test
    public void test() {
        connessioneRepository.save(new Connessione(1L, "follower", "followed"));
        ricettaRepository.save(new Ricetta(1L, "autore", "titolo"));
    }

}
