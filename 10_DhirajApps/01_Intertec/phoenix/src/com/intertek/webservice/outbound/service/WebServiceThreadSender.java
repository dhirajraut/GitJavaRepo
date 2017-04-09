package com.intertek.webservice.outbound.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.mail.javamail.JavaMailSender;

import com.intertek.domain.NameValuePair;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.User;
import com.intertek.entity.WebServiceEntity;
import com.intertek.locator.ServiceLocator;
import com.intertek.mail.VelocityMimeMessagePreparator;
import com.intertek.service.WSService;
import com.intertek.util.SecurityUtil;
import com.intertek.webservice.outbound.WSOutboundContext;

public class WebServiceThreadSender implements Runnable {
	protected LoadTestOutboundWebService aLoadTest;
	protected NameValuePair nv;
	protected static long invoiceNum=1;
	protected static String myInvoice=null;
	
	public WebServiceThreadSender(NameValuePair nv, LoadTestOutboundWebService aLoadTest) {
		this.aLoadTest=aLoadTest;
		this.nv=nv;
	}


	private String getEmailTo(){
		try{
			PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
			return pRB.getString(aLoadTest.getType().toUpperCase() + "_ERROR_EMAIL_TO");
		}
		catch (Exception e){
		}
		return null;
	}

	private void sendOutEmail(WebServiceEntity wsEntity){
		try{
			JavaMailSender sender = (JavaMailSender) ServiceLocator.getInstance().getBean("sender");
			VelocityMimeMessagePreparator preparator = (VelocityMimeMessagePreparator) ServiceLocator.getInstance().getBean("integrationMailPreparator");

			String emailTo = getEmailTo();
			if(emailTo != null && !emailTo.equals("")){
				preparator.setTo(emailTo);
			}

			if((sender != null) && (preparator != null)){
				Map data = new HashMap();
				data.put("wsEntity", wsEntity);
				preparator.setData(data);

				sender.send(preparator);
			}
		}
		catch (Throwable t){
			t.printStackTrace();
		}
	}

	public void run(){
		send();
	}

	protected boolean send(){
		WSService wsService = (WSService) ServiceLocator.getInstance().getBean("wsService");
		boolean result = true;
		WSOutboundContext context = new WSOutboundContext();
		WebServiceEntity wsEntity = new WebServiceEntity();
		context.setWebServiceEntity(wsEntity);

		String message = null;
		boolean status = true;

		String name = nv.getName();
		Object valueObj = nv.getValue();
		Document doc;
		if(valueObj instanceof Document){
			doc = (Document) valueObj;
			throw new RuntimeException("Not yet Implement This type of DOC!!!");
		}
		else{
			JobContractInvoice jci=(JobContractInvoice)valueObj;
			String s="000000000000000"+invoiceNum;
			if(myInvoice==null){
			    myInvoice=jci.getInvoice();
			}
			jci.setInvoice(myInvoice+aLoadTest.getInvoiceStartSeq()+s.substring(s.length()-4));
			invoiceNum++;
			doc = aLoadTest.getDataCollector().getDoc(valueObj);
		}

		try{
			XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
			// xmlOut.output(doc, System.out);

			Document transformed = doc;
			if(aLoadTest.getDataTransformer() != null){
				transformed = aLoadTest.getDataTransformer().transform(doc);
			}

			if(transformed == null)
				return false;
			// xmlOut.output(transformed, System.out);

			// System.out.println("=========== wsEntity = " + wsEntity);
			wsService.sendWebService(context, name, transformed, aLoadTest.getDataCollector(), aLoadTest.getWebServiceSender());

			if(aLoadTest.getResultParser() != null){
				status = aLoadTest.getResultParser().parseResult(wsEntity.getResult());
				if(aLoadTest.isUpdateEntity()){
					if(status == false){
						aLoadTest.getDataCollector().updateFail(name);
					}
					else{
						aLoadTest.getDataCollector().updateSuccess(name);
					}
				}
			}
			else{
				if(aLoadTest.isUpdateEntity()){
					aLoadTest.getDataCollector().updateSuccess(name);
				}
			}
			result = result && status;
		}
		catch (Exception e){
			e.printStackTrace();
			message = e.getMessage();
			status = false;
			result = false;
		}

		// System.out.println("=========== message = " + message);

		wsEntity.setMessage(message);
		wsEntity.setStatus(status);
		try{
			if((wsEntity.getContent() != null) || (wsEntity.getMessage() != null)){
				wsEntity.setType(aLoadTest.getType());
				String entityKey = aLoadTest.getDataCollector().getKey(valueObj);
				if(entityKey != null){
					wsEntity.setEntityKey(entityKey);
				}
				else{
					wsEntity.setEntityKey(name);
				}

				String createdByUser = aLoadTest.getDataCollector().getCreatedUser(valueObj);
				if(createdByUser != null){
					wsEntity.setCreateUser(createdByUser);
				}
				else{
					User user = SecurityUtil.getUser();
					if(user != null){
						wsEntity.setCreateUser(user.getLoginName());
					}
				}

				wsEntity.setCreateTime(new Date());

				//if(aLoadTest.isUpdateEntity()){
					wsService.saveWebServiceEntity(wsEntity);
				//}

				if(wsEntity.getStatus() == false){
					//sendOutEmail(wsEntity);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
