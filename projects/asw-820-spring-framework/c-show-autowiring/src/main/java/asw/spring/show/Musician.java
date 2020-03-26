package asw.spring.show;

import org.springframework.stereotype.Component; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/* Un musicista (suona uno strumento). */
@Component(value="hendrix")
public class Musician implements Artist {

	/* il nome del musicista */
	private String name;

	/* lo strumento suonato dal musicista */
	private Instrument instrument;

	/* Crea un nuovo musicista. */
	@Autowired 
	public Musician(@Value("${show.hendrix.name}") String name, Instrument instrument) {
		this.name = name; 
		this.instrument = instrument;
	}

	/* Esibizione del musicista. */
	public String perform() {
		return "I'm " + name + ": " + instrument.play(); 
	}

}
