package dev.estepsmith.ssm.village.npc;

import java.util.EnumSet;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class NpcStateMachineConfig extends EnumStateMachineConfigurerAdapter<NpcStates, NpcEvents> {

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
          .event(NpcEvents.DAMAGE)
          .and()
        .withExternal()
          .source(NpcStates.DEAD)
          .target(NpcStates.ALIVE)
          .event(NpcEvents.WAKE);
  }

}
