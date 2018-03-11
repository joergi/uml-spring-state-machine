package de.joergi.umlspringstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class UmlSpringStateMachineApplication implements CommandLineRunner {

    @Autowired
    private StateMachine<String, String> stateMachineOne;

    @Autowired
    private StateMachine<String, String> stateMachineTwo;

    public static void main(String[] args) {
        SpringApplication.run(UmlSpringStateMachineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    	synchronized (stateMachineOne) {
    		 stateMachineOne.getExtendedState().getVariables().put("foo", "machine1");
    	     stateMachineOne.start();
		}
       
        synchronized (stateMachineTwo) {
            if(stateMachineOne.isComplete()) {
                stateMachineTwo.getExtendedState().getVariables().put("foo", (String)stateMachineOne.getExtendedState().getVariables().get("foo"));
                stateMachineTwo.start();
            }
		}
        
    }
}
