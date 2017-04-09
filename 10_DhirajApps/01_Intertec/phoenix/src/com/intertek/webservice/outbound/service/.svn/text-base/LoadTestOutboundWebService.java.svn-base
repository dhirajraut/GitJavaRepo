package com.intertek.webservice.outbound.service;

import java.util.List;

import com.intertek.domain.NameValuePair;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.WSService;
import com.intertek.webservice.outbound.WSOutboundContext;

public class LoadTestOutboundWebService extends DefaultOutboundWebService{
	protected long sleepAmount;
	protected boolean updateEntity;
	protected String invoiceStartSeq;
	
	public long getNumberToSend(){
		return numberToSend;
	}

	public void setNumberToSend(long numberToSend){
		this.numberToSend = numberToSend;
	}

	protected long numberToSend=0;
	
	public synchronized boolean sendService(WSOutboundContext context){//make sure timer wont run this concurrently
		if(!isEnabled())
			return false;

		if((context == null) || (dataCollector == null) || (webServiceSender == null))
			return false;

		WSService wsService = (WSService) ServiceLocator.getInstance().getBean("wsService");

		List nvs = dataCollector.collect(context.getDataMap());
		if(nvs == null)
			return false;

		boolean result = true;
		for(int i = 0; !nvs.isEmpty() && i<numberToSend; i++){
			NameValuePair nv = (NameValuePair) nvs.get(0);
			WebServiceThreadSender ts=new WebServiceThreadSender(nv, this);
			Thread th=new Thread(ts);
			th.start();
			try{
				Thread.sleep(sleepAmount);
			}
			catch(Exception e){
				e.printStackTrace();
			}			
		}
		return result;
	}

	public long getSleepAmount(){
		return sleepAmount;
	}

	public void setSleepAmount(long sleepAmount){
		this.sleepAmount = sleepAmount;
	}

	public boolean isUpdateEntity(){
		return updateEntity;
	}

	public void setUpdateEntity(boolean updateEntity){
		this.updateEntity = updateEntity;
	}

	public String getInvoiceStartSeq(){
		return invoiceStartSeq;
	}

	public void setInvoiceStartSeq(String invoiceStartSeq){
		this.invoiceStartSeq = invoiceStartSeq;
	}
}
