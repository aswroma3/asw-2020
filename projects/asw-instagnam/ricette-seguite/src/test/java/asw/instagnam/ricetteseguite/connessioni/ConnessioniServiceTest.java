package asw.instagnam.ricetteseguite.connessioni;

import asw.instagnam.ricetteseguite.connessioni.async.ConnessioneCreatedEvent;
import asw.instagnam.ricetteseguite.connessioni.async.ConnessioneMessageConsumer;
import asw.instagnam.ricetteseguite.domain.Connessione;
import asw.instagnam.ricetteseguite.domain.ConnessioneRepository;
import asw.instagnam.ricetteseguite.domain.ConnessioniService;
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
public class ConnessioniServiceTest {

    @Autowired
    ConnessioneMessageConsumer connessioneMessageConsumer;

    @Autowired
    @Qualifier("connessioniServiceImpl")
    ConnessioniService connessioniService;

    @Autowired
    ConnessioneRepository connessioneRepository;

    @BeforeEach
    public void setUp() {
        connessioneRepository.deleteAll();
    }

    @Test
    public void testReceiveAndSaveNewConnessioniEvent() {
        connessioneMessageConsumer.listener(new ConnessioneCreatedEvent(1L, "vito", "francesco"));

        Optional<Connessione> connessioneOpt = connessioneRepository.findById(1L);
        assertThat(connessioneOpt.isPresent()).isTrue();
        assertThat(connessioneOpt.get().getId()).isEqualTo(1L);
        assertThat(connessioneOpt.get().getFollower()).isEqualTo("vito");
        assertThat(connessioneOpt.get().getFollowed()).isEqualTo("francesco");
    }

    @Test
    public void testGetConnessioniByFollower() {
        connessioneRepository.save(new Connessione(1L, "Michael", "David"));
        connessioneRepository.save(new Connessione(2L, "Michael", "Mary"));
        connessioneRepository.save(new Connessione(3L, "Michael", "Robert"));
        connessioneRepository.save(new Connessione(4L, "James", "Michael"));

        Collection<Connessione> connessioni = connessioniService.getConnessioniByFollower("Michael");
        assertThat(connessioni).hasSize(3);
    }

    @Test
    public void testSaveOrUpdateConnessione() {
        connessioniService.save(new Connessione(1L, "Maria", "Mary"));

        Optional<Connessione> connessioneOpt = connessioneRepository.findById(1L);
        assertThat(connessioneOpt.isPresent()).isTrue();
        assertThat(connessioneOpt.get().getFollower()).isEqualTo("Maria");
        assertThat(connessioneOpt.get().getFollowed()).isEqualTo("Mary");

        connessioniService.save(new Connessione(1L, "James", "David"));
        connessioneOpt = connessioneRepository.findById(1L);
        assertThat(connessioneOpt.isPresent()).isTrue();
        assertThat(connessioneOpt.get().getFollower()).isEqualTo("James");
        assertThat(connessioneOpt.get().getFollowed()).isEqualTo("David");
    }

}
