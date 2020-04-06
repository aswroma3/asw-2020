package asw.grpc.hello.client.domain;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.*;

@Component
public class HelloClientRunner implements CommandLineRunner {

	private static final Logger logger = Logger.getLogger(HelloClientRunner.class.toString());

	@Autowired
	private HelloServiceAdapter helloServiceAdapter;

	public void run(String[] args) throws InterruptedException {
		logger.info( helloServiceAdapter.sayHello("Luca") );
		logger.info( helloServiceAdapter.sayHello("World") );
	}
}
