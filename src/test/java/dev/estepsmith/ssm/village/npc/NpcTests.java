package dev.estepsmith.ssm.village.npc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

@SpringBootTest
public class NpcTests {

  @Autowired
  StateMachineFactory<NpcStates, NpcEvents> stateMachineFactory;

  @Test
  public void testNpcKill() {

    StateMachine<NpcStates,NpcEvents> npcStateMachine = stateMachineFactory.getStateMachine();
    npcStateMachine.start();
    npcStateMachine.sendEvent(NpcEvents.KILL);

    assertEquals(NpcStates.DEAD, npcStateMachine.getState().getId());

    npcStateMachine.stop();

  }

  @Test
  public void testNpcWakeFromDead() {

    StateMachine<NpcStates,NpcEvents> npcStateMachine = stateMachineFactory.getStateMachine();
    npcStateMachine.start();
    npcStateMachine.sendEvent(NpcEvents.KILL);

    assertEquals(NpcStates.DEAD, npcStateMachine.getState().getId());

    npcStateMachine.sendEvent(NpcEvents.WAKE);

    assertEquals(NpcStates.ALIVE, npcStateMachine.getState().getId());

    npcStateMachine.stop();

  }

}
