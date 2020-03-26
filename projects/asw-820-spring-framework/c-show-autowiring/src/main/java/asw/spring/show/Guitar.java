package asw.spring.show;

import org.springframework.stereotype.Component; 
import org.springframework.beans.factory.annotation.Value;

/* Una chitarra. */
@Component(value="stratocaster")
public class Guitar implements Instrument {

	/* il suono della chitarra */
	private String sound;

	/* Crea una nuova chitarra. */
	public Guitar() {
	}

	/* Assegna il suono della chiatarra. */ 
	@Value("${show.stratocaster.sound}")
	public void setSound(String sound) {
		this.sound = sound; 
	}
	
	/* Suona la chitarra. */
	public String play() {
		return sound; 
	}

}

