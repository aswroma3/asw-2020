package asw.instagnam.ricette.init;

import asw.instagnam.ricette.domain.*; 

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class InitRicetteDb implements CommandLineRunner {

	@Autowired 
	private RicetteService ricetteService; 

	public void run(String[] args) {		
		ricetteService.createRicetta( "Cristiano", "Panino al prosciutto", "Metti il prosciutto nel panino" );	
		ricetteService.createRicetta( "Cristiano", "Pizza e mortazza", "Metti la mortadella nella pizza" );	
		ricetteService.createRicetta( "Gennaro", "Tonno e fagioli", "Unisci i fagioli con il tonno" );	
		ricetteService.createRicetta( "Antonino", "Pizza margherita", "... e alla fine inforna" );	
		ricetteService.createRicetta( "Benedetta", "Tonno e fagioli", "Unisci il tonno con i fagioli" );	
	}
	
}
