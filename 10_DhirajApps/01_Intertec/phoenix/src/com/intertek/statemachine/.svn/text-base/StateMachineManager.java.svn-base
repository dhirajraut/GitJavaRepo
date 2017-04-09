package com.intertek.statemachine;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.intertek.config.DtdEntityResolver;
import com.intertek.util.CommonUtil;

/**
 * This class is the manager of the state machines.
 *
 * It will load the all the state machine defintions from statemachne.xml in the classpath.
 *
 **/
public class StateMachineManager
{
  private static final Log log = LogFactory.getLog(StateMachineManager.class);

  private Map stateMachineMap = new HashMap();
  private String[] locations;

  /**
   * .ctr
   **/
  public StateMachineManager() {}

  /**
   * Get the state machine by name.
   *
   * @param name -the name of the state machine.
   * @return - the state machine identified by the name.
   **/
  public StateMachine getStateMachine(String name)
  {
    return (StateMachine)stateMachineMap.get(name);
  }

  /**
   * Set the state machine with its name.
   *
   * @param name -the name of the state machine.
   * @param stateMachine - the state machine identified by the name.
   **/
  public void setStateMachine(String name, StateMachine stateMachine)
  {
    stateMachineMap.put(name, stateMachine);
  }

  /**
   * Set the location of the state machine defintion file.
   *
   * @param location - the location of the state machine defintion file.
   *
   **/
  public void setLocation(String location) {
    this.locations = new String[] {location};
  }

  /**
   * Set the locations of the state machine defintion file.
   *
   * @param locations - an array of locations of the state machine defintion files.
   *
   **/
  public void setLocations(String[] locations) {
    this.locations = locations;
  }

  /**
   * load the state machine defintions from the location(s)
   *
   */
  public void loadStateMachineData()
  {
    try
    {
      int size = locations != null ? locations.length : 0;
      for(int i=0; i<locations.length; i++)
      {
        System.out.println("======= locations[i] = " + i + " " + locations[i]);

        loadData(CommonUtil.getClassPathResource(locations[i]));
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    System.out.println("======= stateMachineMap = " + stateMachineMap);
  }

  private void loadData(InputStream inputStream) throws StateMachineException
  {
    try
    {
      SAXBuilder builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser", true);
      builder.setEntityResolver(new DtdEntityResolver());

      Document document = builder.build(inputStream);
      Element root = document.getRootElement();

      log.info("****************** In parsing state machine xml: " + inputStream);

      List stNodes = root.getChildren(StateMachineConstants.STATE_MACHINE);
      for(int i=0; i<stNodes.size(); i++)
      {
        Element stNode = (Element)stNodes.get(i);
        String name = stNode.getAttributeValue(StateMachineConstants.NAME);
        //if((name == null) || "".equals(name.trim()))
        //  throw new StateMachineException("STATE_MACHINE_NAME_IS_NULL");

        StateMachine stateMachine = new StateMachine(name);

        Element statesNode = stNode.getChild(StateMachineConstants.STATES);
        //if(statesNode == null)
        //  throw new StateMachineException("STATE_MACHINE_STATES_NODE_NOT_EXIST");

        List stateNodes = statesNode.getChildren(StateMachineConstants.STATE);
        //if(stateNodes.size() == 0)
        //  throw new StateMachineException("STATE_MACHINE_STATE_NODE_NOT_EXIST");

        for(int j=0; j<stateNodes.size(); j++)
        {
          Element stateNode = (Element)stateNodes.get(j);
          State state = new State(stateNode.getAttributeValue(StateMachineConstants.NAME));
          stateMachine.addState(state);
        }

        Element transitionsNode = stNode.getChild(StateMachineConstants.TRANSITIONS);

        //if(transitionsNode == null)
        //  throw new StateMachineException("STATE_MACHINE_TRANSITIONS_NODE_NOT_EXIST");

        List transitionNodes = transitionsNode.getChildren(StateMachineConstants.TRANSITION);
        //if(transitionNodes.size() == 0)
        //  throw new StateMachineException("STATE_MACHINE_TRANSITION_NODE_NOT_EXIST");

        for(int j=0; j<transitionNodes.size(); j++)
        {
          Element transitionNode = (Element)transitionNodes.get(j);
          String event = transitionNode.getAttributeValue(StateMachineConstants.EVENT);
          //if((event == null) || "".equals(event.trim()))
          //  throw new StateMachineException("STATE_MACHINE_TRANSITION_EVENT_IS_NULL");

          Transition transition = new Transition(stateMachine, event);

          Element fromNode = transitionNode.getChild(StateMachineConstants.FROM);
          if(fromNode != null)
          {
            String fromStateName = fromNode.getAttributeValue(StateMachineConstants.NAME);
            //if((fromStateName == null) || "".equals(fromStateName.trim()))
            //  throw new StateMachineException("STATE_MACHINE_FROM_STATE_NAME_IS_NULL");

            //State fromState = new State(fromStateName);
            //if(!stateMachine.getStates().contains(fromState))
            //  throw new StateMachineException("STATE_MACHINE_IN_VALID_FROM_STATE");

            transition.setFrom(fromStateName);
          }

          Element toNode = transitionNode.getChild(StateMachineConstants.TO);
          //if(toNode == null)
          //  throw new StateMachineException("STATE_MACHINE_TO_NODE_IS_NULL");

          String toStateName = toNode.getAttributeValue(StateMachineConstants.NAME);
          //if((toStateName == null) || "".equals(toStateName.trim()))
          //  throw new StateMachineException("STATE_MACHINE_TO_STATE_NAME_IS_NULL");

          log.info("toStateName = " + toStateName);
          log.info("all states: " + stateMachine.getStates());
          //State toState = new State(toStateName);
          //if(!stateMachine.getStates().contains(toState))
          //  throw new StateMachineException("STATE_MACHINE_INVALID_TO_STATE", new Object[] {toStateName});

          transition.setTo(toStateName);

          Element permissionNode = transitionNode.getChild(StateMachineConstants.PERMISSION);
          if(permissionNode != null)
          {
            String permissionName = permissionNode.getAttributeValue(StateMachineConstants.NAME);
            if(permissionName != null) transition.setPermission(permissionName);
          }


          Element actionsNode = transitionNode.getChild(StateMachineConstants.ACTIONS);
          if(actionsNode != null)
          {
            List actionNodes = actionsNode.getChildren(StateMachineConstants.ACTION);
            for(int k=0; k<actionNodes.size(); k++)
            {
              Element actionNode = (Element)actionNodes.get(k);
              String serviceName = actionNode.getAttributeValue(StateMachineConstants.SERVICE);
              String apiName = actionNode.getAttributeValue(StateMachineConstants.API);

              //if((actionName == null) || "".equals(actionName.trim()))
              //  throw new StateMachineException("STATE_MACHINE_ACTION_NAME_IS_NULL");

              Action action = new Action();
              action.setServiceName(serviceName);
              action.setApiName(apiName);

              transition.addAction(action);
            }
          }

          stateMachine.addTransition(transition);
        }

        setStateMachine(name, stateMachine);
      }
    }
    catch(StateMachineException e)
    {
      e.printStackTrace();
      throw e;
    }
    catch(Exception e)
    {
      e.printStackTrace();
      throw new StateMachineException(e);
    }
  }

}

