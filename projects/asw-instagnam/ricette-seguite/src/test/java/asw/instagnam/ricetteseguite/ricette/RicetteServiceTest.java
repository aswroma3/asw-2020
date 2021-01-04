package asw.instagnam.ricetteseguite.ricette;

import asw.instagnam.ricetteseguite.domain.Ricetta;
import asw.instagnam.ricetteseguite.domain.RicettaRepository;
import asw.instagnam.ricetteseguite.domain.RicetteService;
import asw.instagnam.ricetteseguite.ricette.async.RicettaCreatedEvent;
import asw.instagnam.ricetteseguite.ricette.async.RicettaMessageConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
public class RicetteServiceTest {

    @Autowired
    RicettaMessageConsumer ricettaMessageConsumer;

    @Autowired
    @Qualifier("ricetteServiceImpl")
    RicetteService ricetteService;

    @Autowired
    RicettaRepository ricettaRepository;

    @BeforeEach
    public void setUp() {
        ricettaRepository.deleteAll();
    }

    @Test
    public void testReceiveAndSaveNewRicettaEvent() {
        ricettaMessageConsumer.listener(new RicettaCreatedEvent(1L, "Elizabeth", "Paccheri ripieni"));

        Optional<Ricetta> ricetteOpt = ricettaRepository.findById(1L);
        assertThat(ricetteOpt.isPresent()).isTrue();
        assertThat(ricetteOpt.get().getId()).isEqualTo(1L);
        assertThat(ricetteOpt.get().getAutore()).isEqualTo("Elizabeth");
        assertThat(ricetteOpt.get().getTitolo()).isEqualTo("Paccheri ripieni");
    }

    @Test
    public void testGetRicetteByAutore() {
        ricettaRepository.save(new Ricetta(1L, "Nancy", "Cannelloni di pesce"));
        ricettaRepository.save(new Ricetta(2L, "Nancy", "Zuppa di verza e patate"));
        ricettaRepository.save(new Ricetta(3L, "Nancy", "Ragù di pesce"));
        ricettaRepository.save(new Ricetta(4L, "Susan", "Lasagne ai broccoli"));

        Collection<Ricetta> ricette = ricetteService.getRicetteByAutore("Nancy");
        assertThat(ricette).hasSize(3);
    }

    @Test
    public void testSaveOrUpdateRicetta() {
        ricetteService.save(new Ricetta(1L, "Susan", "Pasta alla sorrentina"));

        Optional<Ricetta> ricettaOpt = ricettaRepository.findById(1L);
        assertThat(ricettaOpt.isPresent()).isTrue();
        assertThat(ricettaOpt.get().getAutore()).isEqualTo("Susan");
        assertThat(ricettaOpt.get().getTitolo()).isEqualTo("Pasta alla sorrentina");

        ricetteService.save(new Ricetta(1L, "George", "Risotto alle castagne"));
        ricettaOpt = ricettaRepository.findById(1L);
        assertThat(ricettaOpt.isPresent()).isTrue();
        assertThat(ricettaOpt.get().getAutore()).isEqualTo("George");
        assertThat(ricettaOpt.get().getTitolo()).isEqualTo("Risotto alle castagne");
    }

}
