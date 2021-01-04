package asw.instagnam.ricetteseguite.ricette;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
public class RicetteServiceTest {
}
