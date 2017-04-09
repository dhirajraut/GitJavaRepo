package com.intertek.timer;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.webservice.outbound.OutboundWebServiceManager;

/**
 * It defines a timer task used to send outbound web service. Please see applicationContext-timer.xml for usage.
 *
 **/
public class OutboundWebServiceTask extends TimerTask
{
  private static final Log log = LogFactory.getLog(OutboundWebServiceTask.class);

  private OutboundWebServiceManager outboundWebServiceManager;

  /**
   * Set OutboundWebServiceManager which does the work to send outbound web service.
   *
   * @param outboundWebServiceManager - the OutboundWebServiceManager used to send out outbound web service.
   **/
  public void setOutboundWebServiceManager(OutboundWebServiceManager outboundWebServiceManager)
  {
    this.outboundWebServiceManager = outboundWebServiceManager;
  }

  /**
   * The timer of spring framework will call this method according to schedule defined in applicationContext-timer.xml
   **/
  public void run()
  {
    try
    {
      log.info("Starting to do OutboundWebServiceTask!");

      if(outboundWebServiceManager != null)
      {
        outboundWebServiceManager.sendServices();
      }

      log.info("Finished doing OutboundWebServiceTask!");
    }
    catch(Throwable t)
    {
      t.printStackTrace();
    }
  }
}
