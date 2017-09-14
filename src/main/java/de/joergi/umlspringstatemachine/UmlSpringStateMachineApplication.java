package de.joergi.umlspringstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class UmlSpringStateMachineApplication implements CommandLineRunner {

	@Autowired
	private StateMachine<String, String> stateMachine;

	
	public static void main(String[] args) {
		SpringApplication.run(UmlSpringStateMachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		stateMachine.getExtendedState().getVariables().put("Foo", "Bar");
		stateMachine.start();
	}

	
}
