package asw.instagnam.ricette.rest;

import asw.instagnam.ricette.domain.Ricetta;
import asw.instagnam.ricette.domain.RicettaCompleta;
import asw.instagnam.ricette.domain.RicetteRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class RicetteControllerTest {

    @Autowired
    RicetteController ricetteController;

    @Autowired
    RicetteRepository ricetteRepository;

    @BeforeEach
    public void setUp() {
        ricetteRepository.deleteAll();
    }

    @Test
    public void createRicettaTest() {
        final String BRUNO_BARBIERI = "Bruno Barbieri";
        final String TITOLO = "Bignè fritti e caramellati in salsa inglese";

        CreateRicettaRequest createRicettaRequest = new CreateRicettaRequest(
                BRUNO_BARBIERI,
                TITOLO,
                RandomStringUtils.randomAlphanumeric(100));
        RicettaCompleta ricettaCompleta = ricetteController.createRicetta(createRicettaRequest);
        assertThat(ricettaCompleta).isNotNull();

        Optional<RicettaCompleta> ricettaCompletaSalvata = ricetteRepository.findById(ricettaCompleta.getId());
        assertThat(ricettaCompletaSalvata.isPresent()).isTrue();
        assertThat(ricettaCompletaSalvata.get().getAutore()).isEqualTo(BRUNO_BARBIERI);
        assertThat(ricettaCompletaSalvata.get().getTitolo()).isEqualTo(TITOLO);
        assertThat(ricettaCompletaSalvata.get().getPreparazione()).hasSize(100);
    }

    @Test
    public void getRicettaTest() {
        final String ANTONINO_CANNAVACCIUOLO = "Antonino Cannavacciuolo";
        final String TITOLO = "Tonno con semi di sesamo e salsa al basilico";

        RicettaCompleta ricettaCompleta = ricetteRepository.save(new RicettaCompleta(
                ANTONINO_CANNAVACCIUOLO,
                TITOLO,
                RandomStringUtils.randomAlphanumeric(150)));

        RicettaCompleta ricettaCompletaSalvata = ricetteController.getRicetta(ricettaCompleta.getId());
        assertThat(ricettaCompletaSalvata).isNotNull();
        assertThat(ricettaCompletaSalvata.getAutore()).isEqualTo(ANTONINO_CANNAVACCIUOLO);
        assertThat(ricettaCompletaSalvata.getTitolo()).isEqualTo(TITOLO);
        assertThat(ricettaCompletaSalvata.getPreparazione()).hasSize(150);
    }

    @Test
    public void getRicettaNonEsistenteTest() {
        assertThatThrownBy(() -> ricetteController.getRicetta(new Random().nextLong()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    public void getRicette() {
        final String JOE_BASTIANICH = "Joe Bastianich";
        final String CARLO_CRACCO = " Carlo Cracco";

        ricetteRepository.save(new RicettaCompleta(
                JOE_BASTIANICH,
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(150)));

        ricetteRepository.save(new RicettaCompleta(
                CARLO_CRACCO,
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(150)));

        ricetteRepository.save(new RicettaCompleta(
                CARLO_CRACCO,
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(150)));

        Collection<Ricetta> ricetteJoe = ricetteController.getRicette(JOE_BASTIANICH);
        Collection<Ricetta> ricetteCarlo = ricetteController.getRicette(CARLO_CRACCO);

        assertThat(ricetteJoe).hasSize(1);
        assertThat(ricetteCarlo).hasSize(2);

        Collection<Ricetta> ricetteTutte = ricetteController.getRicette(null);
        assertThat(ricetteTutte).hasSize(3);
    }
}
