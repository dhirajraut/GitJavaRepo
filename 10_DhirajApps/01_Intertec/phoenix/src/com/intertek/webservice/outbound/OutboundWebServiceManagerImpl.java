package com.intertek.webservice.outbound;

import java.util.List;

public class OutboundWebServiceManagerImpl implements OutboundWebServiceManager
{
  private List outboundWebServices;

  public void setOutboundWebServices(List outboundWebServices)
  {
    this.outboundWebServices = outboundWebServices;
  }

  public void sendServices()
  {
    if(outboundWebServices != null)
    {
      for(int i=0; i<outboundWebServices.size(); i++)
      {
        OutboundWebService service = (OutboundWebService)outboundWebServices.get(i);
        if(service.isEnabled()){
	        WSOutboundContext context = new WSOutboundContext();
	        service.sendService(context);
        }
      }
    }
  }
}
