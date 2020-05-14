package asw.instagnam.ricette.rest;

import asw.instagnam.ricette.domain.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger; 
import java.util.*; 
import java.util.stream.*; 

@RestController
public class RicetteController {

	@Autowired 
	private RicetteService ricetteService; 

	private final Logger logger = Logger.getLogger(RicetteController.class.toString()); 

	/* Crea un nuovo ristorante. 
	* la richiesta contiene nel corpo autore e titolo */ 
	@PostMapping("/ricette")
	public RicettaCompleta createRicetta(@RequestBody CreateRicettaRequest request) {
		String autore = request.getAutore();
		String titolo = request.getTitolo();
		String preparazione = request.getPreparazione();
		logger.info("REST CALL: createRicetta " + autore + ", " + titolo + ", " + preparazione); 
		RicettaCompleta ricetta = ricetteService.createRicetta(autore, titolo, preparazione);
		return ricetta; 
	}	

	/* Trova la ricetta con ricettaId. */ 
	@GetMapping("/ricette/{ricettaId}")
	public RicettaCompleta getRicetta(@PathVariable Long ricettaId) {
		logger.info("REST CALL: getRicetta " + ricettaId); 
		RicettaCompleta ricetta = ricetteService.getRicetta(ricettaId);
		if (ricetta!=null) {
			return ricetta;
		} else {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Ricetta not found"
			);
		}
	}

	/* Trova tutte le ricette (in formato breve) (o tutte le ricette di autore). */ 
	@GetMapping("/ricette")
	public Collection<Ricetta> getRicette(@RequestParam(value="autore", required=false) String autore) {
		Collection<RicettaCompleta> ricette = null; 
		if (autore==null) {
			logger.info("REST CALL: getRicette"); 
			ricette = ricetteService.getRicette();
		} else {
			logger.info("REST CALL: getRicette " + autore); 
			ricette = ricetteService.getRicetteByAutore(autore);
		}
		return toRicetteBrevi(ricette);
	}
	
	/* Converte una collezione di ricette (in formato completo), in una collezione di ricette (in formato breve). */ 
	private Collection<Ricetta> toRicetteBrevi(Collection<RicettaCompleta> ricetteComplete) {
		Collection<Ricetta> ricette = 
			ricetteComplete
				.stream()
				.map(r -> new Ricetta(r))
				.collect(Collectors.toList());
		return ricette; 
	}

}
