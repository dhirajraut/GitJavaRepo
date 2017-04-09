package com.intertek.statemachine;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.intertek.locator.ServiceLocator;
import com.intertek.util.SecurityUtil;

/**
 * A state machine contains a set of states and the transitions among the states. It has a name to uniquely identify it. Phoenx can define many state machines.
 *
 **/
public class StateMachine
{
  private String name;
  private Set states = new HashSet();
  private Map transitions = new LinkedHashMap();

  /**
   * .ctr
   *
   * @param name - the name of the state machine.
   **/
  public StateMachine(String name)
  {
    this.name = name;
  }

  /**
   * Get the name of the state machine.
   *
   * @return - the name of the state machine.
   **/
  public String getName() { return name; }

  /**
   * Set the name of the state machine.
   *
   * @param name - the name of the state machine.
   **/
  public void setName(String name) { this.name = name; }

  /**
   * Get the states of the state machine as a Set.
   *
   * @return - the states of the state machine.
   **/
  public Set getStates() { return states; }

  /**
   * Add a state to the state machine.
   *
   * @param state - a state to be added to the state machine.
   **/
  public void addState(State state) { states.add(state); }

  /**
   * Remove a state from the state machine.
   *
   * @param state - a state to be removed from the state machine.
   **/
  public void removeState(State state) { states.remove(state); }

  /**
   * Get the transition by event name.
   *
   * @param event - the event name used to get the transition.
   * @return - the transtion identified by the event name.
   **/
  public Transition getTransition(String event)
  {
    return (Transition)transitions.get(event);
  }

  /**
   * Add a transition to the state machine.
   *
   * @param transition - the transition to be added to the state machine.
   **/
  public void addTransition(Transition transition)
  {
    transitions.put(transition.getEvent(), transition);
  }

  /**
   * Remove a transition from the state machine.
   *
   * @param event - the event name which is used to get the transtion to be removed from the state machine.
   **/
  public void removeTransition(String event)
  {
    transitions.remove(event);
  }

  /**
   * Get a list of transtions by the state name.
   *
   * @param stateName - a state name.
   * @return - the list of transitions identified by the state name.
   **/
  public List getTransitions(String stateName)
  {
    List results = new ArrayList();

    if(stateName == null) return results;

    Iterator it = transitions.keySet().iterator();
    while(it.hasNext())
    {
      String event = (String)it.next();
      Transition transition = (Transition)transitions.get(event);
      if(transition != null)
      {
        String permission = transition.getPermission();
        boolean granted = false;

        if(permission == null)
        {
          granted = true;
        }
        else
        {
          Set permNameSet = SecurityUtil.getPermNameSet();
          if(permNameSet == null) granted = true;
          else granted = permNameSet.contains(permission);
        }

        if(granted)
        {
          String from = transition.getFrom();
          if(from == null)
          {
            results.add(event);
          }
          else
          {
            String[] splits = from.split(",");
            for(int i=0; i<splits.length; i++)
            {
              if(stateName.equals(splits[i]))
              {
                results.add(event);
                break;
              }
            }
          }
        }
      }
    }

    return results;
  }

  /**
   * Apply a transition for the object with the event name.
   *
   * @param conext - the execution context.
   * @param object - the object the state transtion will be applied on.
   * @param event - the event name of the transtion.
   **/
  public void applyTransition(
    ExecutionContext context,
    Object object,
    String event
  )
  {
    Transition transition = getTransition(event);
    if(transition == null)
    {
      throw new RuntimeException("NOT_VALID_TRANSITION");
    }

    // check the from state ???

    // execute a list of actions
    List actions = transition.getActions();
    if(actions != null)
    {
      for(int i=0; i<actions.size(); i++)
      {
        Action action = (Action)actions.get(i);
        executeAction(context, object, transition.getTo(), action);
      }
    }

    // set the status
    //State toState = transition.getTo();
    //setStatus(context, stateHolder, toState);
  }

  private void setStatus(
    ExecutionContext context,
    StateHolder stateHolder,
    State toState
  )
  {
    stateHolder.setState(toState.getName());
  }

  private void executeAction(
    ExecutionContext context,
    Object object,
    String toStateName,
    Action action
  )
  {
    String methodName = action.getApiName();
    String className = action.getServiceName();

    try
    {
      Class[] dataClasses = new Class[2];
      Object[] dataObjects = new Object[2];

      dataObjects[0] = object;
      dataClasses[0] = object.getClass();

      dataObjects[1] = toStateName;
      dataClasses[1] = toStateName.getClass();

      Object loaderObject = ServiceLocator.getInstance().getBean(className);

      Class loaderClass = loaderObject.getClass();
      Method loaderMethod = loaderClass.getDeclaredMethod(methodName, dataClasses);

      loaderMethod.invoke(loaderObject, dataObjects);
    }
    catch(Exception e)
    {
      e.printStackTrace();

      throw new RuntimeException(e.getMessage(), e);
    }
  }

}