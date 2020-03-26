package asw.spring.show;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


public class HendrixTest {

	private Artist artist; 
	
	@BeforeEach
	public void setupHendrix() {
		ApplicationContext context = new ClassPathXmlApplicationContext("show-beans.xml");
    	this.artist = (Artist) context.getBean("hendrix");
	}

	@Test 
	public void hendrixTest() {
    	assertEquals( "I'm Jimi: Ta ta taa", artist.perform() );
	}

}
