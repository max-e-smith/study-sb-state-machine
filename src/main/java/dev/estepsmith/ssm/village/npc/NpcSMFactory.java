package dev.estepsmith.ssm.village.npc;

import java.util.EnumSet;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachineFactory
public class NpcSMFactory extends EnumStateMachineConfigurerAdapter<NpcStates, NpcEvents> {

  @Override
  public void configure(StateMachineStateConfigurer<NpcStates, NpcEvents> states) throws Exception {
    states.withStates()
        .initial(NpcStates.ALIVE)
        .states(EnumSet.allOf(NpcStates.class));
  }

  @Override
  public void configure(StateMachineTransitionConfigurer<NpcStates, NpcEvents> transitions) throws Exception {
    transitions
        .withExternal()
          .source(NpcStates.ALIVE)
          .target(NpcStates.DEAD)
          .event(NpcEvents.KILL)
          .and()
        .withExternal()
          .source(NpcStates.DEAD)
          .target(NpcStates.ALIVE)
          .event(NpcEvents.WAKE);
  }

//  @Bean
//  public StateMachineListenerAdapter<NpcStates, NpcEvents> npcStateMachineListener() {
//    return new StateMachineListenerAdapter<NpcStates, NpcEvents>() {
//      @Override
//      public void stateChanged(State<NpcStates, NpcEvents> from, State<NpcStates, NpcEvents> to) {
//        System.out.println("state changed from " + from.getId() + " to " + to.getId());
//      }
//    };
//  }

}
