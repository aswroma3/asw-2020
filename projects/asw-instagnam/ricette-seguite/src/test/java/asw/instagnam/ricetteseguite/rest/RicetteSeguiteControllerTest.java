package asw.instagnam.ricetteseguite.rest;

import asw.instagnam.ricetteseguite.domain.Connessione;
import asw.instagnam.ricetteseguite.domain.ConnessioneRepository;
import asw.instagnam.ricetteseguite.domain.Ricetta;
import asw.instagnam.ricetteseguite.domain.RicettaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
public class RicetteSeguiteControllerTest {

    @Autowired
    ConnessioneRepository connessioneRepository;

    @Autowired
    RicettaRepository ricettaRepository;

    @Autowired
    RicetteSeguiteController ricetteSeguiteController;

    @Test
    public void testGetRicetteSeguite() {
        connessioneRepository.save(new Connessione(1L, "Olivia", "Ava"));
        connessioneRepository.save(new Connessione(2L, "Olivia", "Isla"));
        connessioneRepository.save(new Connessione(3L, "Olivia", "Michael"));
        connessioneRepository.save(new Connessione(4L, "Isla", "Harry"));
        connessioneRepository.save(new Connessione(5L, "Ava", "Noah"));
        connessioneRepository.save(new Connessione(6L, "James", "Noah"));
        connessioneRepository.save(new Connessione(7L, "Michael", "Richard"));

        ricettaRepository.save(new Ricetta(1L, "Isla", "Insalata di polipo"));
        ricettaRepository.save(new Ricetta(2L, "Ava", "Polpettine di tonno e ricotta"));
        ricettaRepository.save(new Ricetta(3L, "Michael", "Salmone croccante"));
        ricettaRepository.save(new Ricetta(4L, "James", "Orata al forno"));

        Collection<Ricetta> ricette = ricetteSeguiteController.getRicetteSeguite("Olivia");
        assertThat(ricette).hasSize(3);
        assertThat(ricette)
                .extracting(Ricetta::getTitolo)
                .containsExactlyInAnyOrder("Insalata di polipo", "Polpettine di tonno e ricotta", "Salmone croccante");
    }

}
