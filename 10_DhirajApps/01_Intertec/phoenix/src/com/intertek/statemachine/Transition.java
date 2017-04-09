package com.intertek.statemachine;

import java.util.ArrayList;
import java.util.List;

/**
 * It represent a change of states from one to another within the state machine for the object applied.
 **/
public class Transition
{
  private StateMachine stateMachine;
  private String event;
  private String from;
  private String to;
  private String permission;
  private List actions = new ArrayList();

  /**
   * .ctr
   *
   * @param stateMachine - the state machine the transition will be applied on.
   * @param event - the event name of the transition.
   **/
  public Transition(StateMachine stateMachine, String event)
  {
    this.stateMachine = stateMachine;
    this.event = event;
  }

  /**
   * Get the event name of the transition.
   *
   * @return - the event name.
   **/
  public String getEvent() { return event; }

  /**
   * Set the event name of the transition.
   *
   * @param event - the event name.
   **/
  public void setEvent(String event) { this.event = event; }

  /**
   * Get the from state name of the transition.
   *
   * @return - the from state name.
   **/
  public String getFrom() { return from; }

  /**
   * Set the from state name of the transition.
   *
   * @param from - the from state name.
   **/
  public void setFrom(String from) { this.from = from; }

  /**
   * Get the to state name of the transition.
   *
   * @return - the to state name.
   **/
  public String getTo() { return to; }

  /**
   * Set the to state name of the transition.
   *
   * @param to - the to state name.
   **/
  public void setTo(String to) { this.to = to; }

  /**
   * Get the permission of the transition. User can execute the transition only when he has this permission.
   *
   * @return - the permission used to guard this transition.
   **/
  public String getPermission() { return permission; }

  /**
   * Set the permission of the transition. User can execute the transition only when he has this permission.
   *
   * @param to - the permission used to guard this transition.
   **/
  public void setPermission(String permission) { this.permission = permission;}

  /**
   * Get all the actions defined in this transition.
   *
   * @return - the actions of this transtion.
   **/
  public List getActions() { return actions; }

  /**
   * Add an action to this transtion.
   *
   * @param action - the action to be added to the transtion.
   **/
  public void addAction(Action action) { actions.add(action); }

  /**
   * Remove an action from this transtion.
   *
   * @param action - the action to be removed from the transtion.
   **/
  public void removeAction(Action action)
  {
    actions.remove(action);
  }
}