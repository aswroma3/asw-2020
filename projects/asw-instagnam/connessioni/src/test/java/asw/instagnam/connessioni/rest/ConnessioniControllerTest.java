package asw.instagnam.connessioni.rest;

import asw.instagnam.connessioni.domain.Connessione;
import asw.instagnam.connessioni.domain.ConnessioniRepository;
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
public class ConnessioniControllerTest {

    @Autowired
    ConnessioniController connessioniController;

    @Autowired
    ConnessioniRepository connessioniRepository;

    @BeforeEach
    public void setUp() {
        connessioniRepository.deleteAll();
    }

    @Test
    public void createConnessioneTest() {
        final String VITO = "vito";
        final String FRANCESCO = "francesco";

        CreateConnessioneRequest createConnessioneRequest = new CreateConnessioneRequest(VITO, FRANCESCO);
        Connessione connessione = connessioniController.createConnessione(createConnessioneRequest);
        assertThat(connessione).isNotNull();

        Optional<Connessione> connessioneSalvata = connessioniRepository.findById(connessione.getId());
        assertThat(connessioneSalvata.isPresent()).isTrue();
        assertThat(connessioneSalvata.get().getFollower()).isEqualTo(VITO);
        assertThat(connessioneSalvata.get().getFollowed()).isEqualTo(FRANCESCO);
    }

    @Test
    public void getConnessioneTest() {
        final String VITO = "vito";
        final String FRANCESCO = "francesco";

        Connessione connessioneSalvata = connessioniRepository.save(new Connessione(VITO, FRANCESCO));
        Connessione connessione = connessioniController.getConnessione(connessioneSalvata.getId());
        assertThat(connessione).isNotNull();
        assertThat(connessione.getFollower()).isEqualTo(VITO);
        assertThat(connessione.getFollowed()).isEqualTo(FRANCESCO);
    }

    @Test
    public void getConnessioneNonEsistenteTest() {
        assertThatThrownBy(() -> connessioniController.getConnessione(new Random().nextLong()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    public void getConnessioniPerUnUtenteTest() {
        final String VITO = "vito";
        final String FRANCESCO = "francesco";
        final String TOMMASO = "tommaso";

        connessioniRepository.save(new Connessione(VITO, FRANCESCO));
        connessioniRepository.save(new Connessione(VITO, TOMMASO));
        Collection<Connessione> connessioniCollection = connessioniController.getConnessioni(VITO);
        assertThat(connessioniCollection).hasSize(2);
        assertThat(connessioniCollection)
                .extracting(Connessione::getFollowed)
                .containsExactlyInAnyOrder(FRANCESCO, TOMMASO);
    }

}
