package de.joergi.umlspringstatemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StateNameAction {

    @Bean
    public Action<String, String> sysoName() {
        return new Action<String, String>() {

            @Override
            public void execute(StateContext<String, String> context) {
                log.info((String) context.getExtendedState().getVariables().get("foo"));
            }
        };
    }
}
