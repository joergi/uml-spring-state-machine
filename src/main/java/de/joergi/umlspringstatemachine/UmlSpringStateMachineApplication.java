package de.joergi.umlspringstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;
import org.springframework.statemachine.uml.UmlStateMachineModelFactory;

@SpringBootApplication
public class UmlSpringStateMachineApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(UmlSpringStateMachineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        UmlStateMachineModelFactory usmmf1 = new UmlStateMachineModelFactory("classpath:papyrus/StateMachine1.uml");

        Builder<String, String> builder1 = StateMachineBuilder.builder();
        
        
        builder1.configureConfiguration().withConfiguration().autoStartup(false).and().withConfiguration().beanFactory(appContext.getAutowireCapableBeanFactory());
        
        builder1.configureConfiguration().withConfiguration().beanFactory(new StaticListableBeanFactory());
        builder1.configureModel().withModel().factory(usmmf1);
        StateMachine<String, String> stateMachine1 = builder1.build();
        stateMachine1.addStateListener(MyStateListener.listener());
        
        stateMachine1.start();
        
        
        UmlStateMachineModelFactory usmmf2 = new UmlStateMachineModelFactory("classpath:papyrus/StateMachine2.uml");

        Builder<String, String> builder2 = StateMachineBuilder.builder();
        
        
        builder2.configureConfiguration().withConfiguration().autoStartup(false);
        builder2.configureConfiguration().withConfiguration().beanFactory(appContext.getAutowireCapableBeanFactory());
        
        builder2.configureConfiguration().withConfiguration().beanFactory(new StaticListableBeanFactory());
        builder2.configureModel().withModel().factory(usmmf2);
        StateMachine<String, String> stateMachine2 = builder2.build();
        stateMachine2.addStateListener(MyStateListener.listener());
        
        stateMachine2.start();

    }

}
