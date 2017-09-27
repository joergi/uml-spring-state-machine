package de.joergi.umlspringstatemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class Actions extends StateMachineConfigurerAdapter<String, String> {

    @Bean
    public Action<String, String> sysoName() {
        return new Action<String, String>() {

            @Override
            public void execute(StateContext<String, String> context) {
                log.info(context.getStateMachine().getId());
                log.info("xxxx " + (String)context.getExtendedState().getVariables().get("foo"));
                
                if(("machineone").equalsIgnoreCase(context.getStateMachine().getId())) {
                    context.getExtendedState().getVariables().put("foo", "machine one finished, start machine 2");
                }
            }
        };
    }
}
