package com.intertek.util;

import java.util.ArrayList;
import java.util.List;

import com.intertek.locator.ServiceLocator;
import com.intertek.statemachine.StateMachine;
import com.intertek.statemachine.StateMachineManager;

public class StateMachineUtil
{
  public static List getOperationsByStateMachine(String stateMachineName, String stateName)
  {
    List results = new ArrayList();

    if((stateMachineName == null) || (stateName == null)) return results;

    StateMachineManager stateMachineMgr = (StateMachineManager)ServiceLocator.getInstance().getBean("stateMachineMgr");
    StateMachine stateMachine = stateMachineMgr.getStateMachine("ContractStateMachine");
    if(stateMachine != null)
    {
      results.addAll(stateMachine.getTransitions(stateName));
    }

    return results;
  }
}
