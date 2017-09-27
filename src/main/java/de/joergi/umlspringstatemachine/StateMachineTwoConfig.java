package de.joergi.umlspringstatemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.uml.UmlStateMachineModelFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableStateMachine(name="stateMachineTwo")
public class StateMachineTwoConfig extends StateMachineConfigurerAdapter<String, String> {
    
    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config.withConfiguration().autoStartup(false).listener(listener2()).machineId("machinetwo");
                
    }

    @Override
    public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
        model.withModel().factory(modelFactory2());
    }

    
    @Bean
    public StateMachineModelFactory<String, String> modelFactory2() {
        return new UmlStateMachineModelFactory("classpath:papyrus/StateMachine2.uml");
    }
    
    @Bean
    public StateMachineListener<String, String> listener2() {
        return new StateMachineListenerAdapter<String, String>() {
            
            
            @Override
            public void stateChanged(State<String, String> from, State<String, String> to) {
                log.info("State 2 changed to " + to.getId());
            }
        };

    }

}
