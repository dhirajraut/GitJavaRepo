package com.intertek.ariba;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.intertek.acegi.MyLdapAuthenticationProvider;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.User;
import com.intertek.entity.WebServiceEntity;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.WSService;
import com.intertek.tool.datatransporter.DataTransporter;
import com.intertek.tool.oxm.OxmManager;
import com.intertek.tool.transformer.DataTransformer;
import com.intertek.util.SecurityUtil;

public class SendInvoiceToAribaManager
{
	private static final Log logger = LogFactory.getLog(MyLdapAuthenticationProvider.class);
	protected DataTransporter dataTransporter;
	protected DataTransformer dataTransformer;
	  

	public String sendToAriba(String jobContractIdstr) throws Exception {
		
		  String result = "Sending Invoice to Ariba Failed!";
		  System.out.println("jobContractIdstr"+jobContractIdstr);
		  WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
		  WebServiceEntity wsEntity = new WebServiceEntity();
		  if(dataTransformer!=null && dataTransporter!=null  && jobContractIdstr!=null && jobContractIdstr.trim().length()>0){
		    {
		    	 long jobContractId = Long.parseLong(jobContractIdstr);
		    	 try
		 	    {
		 	      OxmManager oxmManager = (OxmManager)ServiceLocator.getInstance().getBean("oxmManager");
		 	      List<JobContractInvoice> jobContractInvoiceList = wsService.getJobContractInvoicesForAribaOutboundInvoiceMessage(jobContractId);

		 	      if(jobContractInvoiceList.size() > 0){
		 	        for(int i=0; i<jobContractInvoiceList.size(); i++)
		 	        {
		 	          JobContractInvoice jobContractInvoice = jobContractInvoiceList.get(i);

		 	         List<String> sysFileNameList = wsService.getAttachSysFileNameByJobContractId(jobContractId);
		 	          
		 	          //For customerCode=124 or 114 we need to send invoice cXML document to Ariba network.
		 	          if( dataTransporter!=null && (jobContractInvoice.getJobContract().getCustCode().equals("124") || jobContractInvoice.getJobContract().getCustCode().equals("114") || jobContractInvoice.getJobContract().getCustCode().equals("109"))){

					 //START:To avoid PO# from CustRef / PO
		 	         	  if(null != jobContractInvoice.getCustRefNum()){
		 	         		  String strTemp = jobContractInvoice.getCustRefNum();
		 	         		  String strTemp1 = strTemp.toUpperCase();
		 	         		  if(strTemp1.indexOf("PO#") >= 0){
		 	         			jobContractInvoice.setCustRefNum(strTemp.substring(strTemp1.indexOf("PO#")+"PO#".length(), strTemp.length()));
		 	         		  }
		 	         	  }
		 	         	  //END:To avoid PO# from CustRef / PO
		 	        	  
			 	          System.out.println("before marshal jobContractInvoice.getId:"+jobContractInvoice.getId());
			 	          Document doc = oxmManager.marshal(jobContractInvoice);
			 	           
			 	     	//START: Patni Offshore changes to allow multiple files to be attached in Ariba Invoice
			 	         updateJobContractAttachments(doc, sysFileNameList);			 	        
			 	    	//END: Patni Offshore changes to allow multiple files to be attached in Ariba Invoice			 	         
			 	          
		 	              XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
		 	              xmlOut.output(doc, System.out);
		 	            	  
		 	              Document transformed = doc;
		 	              transformed = dataTransformer.transform(doc);
		 	              
		 	        	  StringWriter sw = new StringWriter();
		 			      xmlOut = new XMLOutputter(Format.getPrettyFormat());
		 			      xmlOut.output(transformed.getRootElement(), sw);
		 			      StringBuffer sb = new StringBuffer("");
		 			      sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		 			      sb.append("<!DOCTYPE cXML SYSTEM \"http://xml.cxml.org/schemas/cXML/1.2.018/InvoiceDetail.dtd\">\r\n");
		 			      sb.append(sw.toString());
		 			      String content = sb.toString();
		 			      
		 			      System.out.println("content:"+content);
		 			      String resultStr = dataTransporter.send(content, sysFileNameList);
					      System.out.println("resultStr from transpoerter: "+resultStr);
					      if(resultStr!=null & resultStr.trim().length()>0){
							      wsEntity.setContent(content);
						          wsEntity.setCreateTime(new Date());
						          wsEntity.setResult(resultStr);
						          wsEntity.setEntityKey(String.valueOf(jobContractInvoice.getId()));
						          wsEntity.setType("ARIBA_INVOICE");
						          User user = SecurityUtil.getUser();
						          if(user != null)
						            wsEntity.setCreateUser(user.getLoginName());
							      
							      if(resultStr.contains("Acknowledged")){
							    	  wsService.updateJobContractAribaFlag(jobContractInvoice.getId());
							    	  wsEntity.setStatus(true);
							    	  result = "Invoice was sent to Ariba successfully.";
							      }
							      wsService.saveWebServiceEntity(wsEntity);
					      }
					      
		 	          }
		 	        }
		 	      }
		 	    }
		 	    catch(Throwable t)
		 	    {
		 	      t.printStackTrace();
		 	      logger.error(t.getMessage());
		 	     wsEntity.setMessage(t.getMessage());
		          wsEntity.setStatus(false);
		 	    }
		    }
		}
		  return result;
	}

	  
	public DataTransformer getDataTransformer() {
		return dataTransformer;
	}

	public void setDataTransformer(DataTransformer dataTransformer) {
		this.dataTransformer = dataTransformer;
	}

	public DataTransporter getDataTransporter() {
		return dataTransporter;
	}


	public void setDataTransporter(DataTransporter dataTransporter) {
		this.dataTransporter = dataTransporter;
	}
	//START: Patni Offshore changes to allow multiple files to be attached in Ariba Invoice 
	/**
	 * This method adds Elements, 
	 * <JobContractAttaches>
	 * 	<FileName>cid:part1@intertek.com</FileName>
	 *  	...
	 *  <FileName>cid:partn@intertek.com</FileName>
	 * </JobContractAttaches>
	 * @param pDoc as org.jdom.Document
	 * @param pLstAttachments as java.util.List
	 */
	private void updateJobContractAttachments(Document pDoc, List pLstAttachments){
		if(null != pDoc && null !=pLstAttachments){
 	          Element elmJcfa = null;
 	          Element elmFn = null; 
 	          for(int i=0;i<pLstAttachments.size();i++){
 	        	  elmJcfa = new Element("JobContractAttaches");
 	        	  elmFn = new Element("FileName");
 	        	  elmFn.setText("cid:part"+(i+2)+"@intertek.com");
 	        	  elmJcfa.addContent(elmFn);
 	        	  pDoc.getRootElement().addContent(elmJcfa);
 	          } 	         
		}
	}
	//END: Patni Offshore changes to allow multiple files to be attached in Ariba Invoice	
}
