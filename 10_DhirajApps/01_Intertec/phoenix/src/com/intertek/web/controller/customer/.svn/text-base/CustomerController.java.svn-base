package com.intertek.web.controller.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.entity.Contact;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.Customer;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ContractService;
import com.intertek.service.CustomerService;
import com.intertek.service.TaxService;
import com.intertek.util.Constants;

public class CustomerController extends AbstractController
{
  private static Log log = LogFactory.getLog(CustomerController.class);

  /**
   * .ctor
   */
  public CustomerController() {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   * <context dynamic="true|false">
       <User Name="jaloia111" />
       <Type Name="MANIFEST_FILE" />
     </context>
   */
  protected ModelAndView handleRequestInternal(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception
  {
	 CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
	 ContractService contractService =(ContractService)ServiceLocator.getInstance().getBean("contractService");
	 TaxService taxService =(TaxService)ServiceLocator.getInstance().getBean("taxService");

	String custCode = "";
	String parentCustomerName = "";
	String contactId = "";
	String creditAnalystCode = "";
	String collectorCode="";
	String businessUnitName="";
	String contractCode= "";
    String accountOwner=" ";
	String taxCode= "";
	String customerName="";
	String location="";
	String vatCode="";
    String taxType="";

	List accountowners=new ArrayList();  		   //accoutn owner names
	List customer = new ArrayList();
	List creditAnalyst = new ArrayList();
	List collector = new ArrayList();
	List businessUnit = new ArrayList();
	List contacts = new ArrayList();
	List contract = new ArrayList();
	List taxRates = new ArrayList();
	List locations = new ArrayList();
	List vatCodes=new ArrayList();

	String xml = "";
	  /*For iTrack135193-START*/
	  if(request.getParameter("accountOwnerRole")!=null && (!request.getParameter("accountOwnerRole").trim().equals("")))
	  {
	   	  String accountOwnerRole = request.getParameter("accountOwnerRole");	   	
	   	  List<User> accountOwnerRoles = customerService.getAccountOwnerRoleUsers(accountOwnerRole);
		  xml = new AjaxXmlBuilder().addItems(accountOwnerRoles, "loginName", "loginName").toString();
	  }
	  if(request.getParameter("customer.parentCustomerName")!=null && (!request.getParameter("customer.parentCustomerName").trim().equals("")))
	  {
	   	  String parentCustomer = request.getParameter("customer.parentCustomerName");	   	
	   	  List childCustomers = customerService.getChildCustomersByParentcutomer(parentCustomer);
		  xml = new AjaxXmlBuilder().addItems(childCustomers, "name", "name").toString();
	  }
	 
	  /*For iTrack135193-END*/
	  if(request.getParameter("custCode") != null && (!request.getParameter("custCode").trim().equals("")))
	  {
		  custCode = request.getParameter("custCode");

		  customer = customerService.getCustomersByCustCode(custCode);

  		  xml = new AjaxXmlBuilder().addItems(customer, "custCode", "name").toString();

	  }
	  if(request.getParameter("customerName")!=null && (!request.getParameter("customerName").trim().equals("")))
	  {
		  customerName=request.getParameter("customerName");
		 List customers=customerService.getCustomersByName(customerName);
		  AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
		  for(int i=0;i<customers.size();i++)
		  {
			  Customer cUstomer = (Customer)customers.get(i);
			  String  ccode = cUstomer.getCustCode();
			  String name = cUstomer.getName();
			  String value = name+","+ccode;
			   xmlBuilder.addItem(name,value);
		  }
		 xml = xmlBuilder.toString();
		  
		  //xml=new AjaxXmlBuilder().addItems(customer,"name","name").toString();
	  }
	  
	  
	  if(request.getParameter("parentCustName") != null && (!request.getParameter("parentCustName").trim().equals("")))
	  {
		 
		  parentCustomerName =request.getParameter("parentCustName");
		  customer = customerService.getParentCustomersByName(parentCustomerName);
		

  		  xml = new AjaxXmlBuilder().addItems(customer, "name", "name").toString();

	  }
	  if(request.getParameter("contractCode") != null && (!request.getParameter("contractCode").trim().equals("")))
	  {
		  contractCode = request.getParameter("contractCode");

		  contract = contractService.getContractsByContractCode(contractCode);

  		  xml = new AjaxXmlBuilder().addItems(contract, "contractCode", "contractCode").toString();

	  }
	  if(request.getParameter("contractCodeAndDesc") != null && (!request.getParameter("contractCodeAndDesc").trim().equals("")))
	  {
		  contractCode = request.getParameter("contractCodeAndDesc");

		  contract = contractService.getContractsByContractCode(contractCode);

  		  xml = new AjaxXmlBuilder().addItems(contract, "contractCode", "description").toString();

	  }

	   if(request.getParameter("creditAnalystName") != null && (!request.getParameter("creditAnalystName").trim().equals("")))
	  {
		  creditAnalystCode = request.getParameter("creditAnalystName");

		  creditAnalyst = customerService.listCreditAnalystByCode(creditAnalystCode);

  		  xml = new AjaxXmlBuilder().addItems(creditAnalyst, "creditAnalystCode", "creditAnalystCode").toString();

	  }

	    if(request.getParameter("collectorName") != null && (!request.getParameter("collectorName").trim().equals("")))
	  {
		  collectorCode = request.getParameter("collectorName");

		  collector = customerService.listCollectorByCode(collectorCode);

  		  xml = new AjaxXmlBuilder().addItems(collector, "collectorCode", "collectorCode").toString();

	  }
	    if(request.getParameter("taxCode") != null && (!request.getParameter("taxCode").trim().equals("")))
	  {
		  taxCode = request.getParameter("taxCode");

		  taxRates = taxService.getTaxRatesByCode(taxCode);
		  
  		  //xml = new AjaxXmlBuilder().addItems(taxRates, "taxCode", "taxCode").toString();
		  xml = new AjaxXmlBuilder().addItems(taxRates, "taxCodeHeader", "taxCodeHeader").toString();
	  }



 if(request.getParameter("job.contactId") != null && (!request.getParameter("job.contactId").trim().equals(""))&&
		   request.getParameter("job.custCode")!=null && (!request.getParameter("job.custCode").trim().equals(""))&&
           request.getParameter("job.contractCode")!=null && (!request.getParameter("job.contractCode").trim().equals("")))
	  {
		  contactId = request.getParameter("job.contactId");
		  String customerId=request.getParameter("job.custCode");
		  String index=request.getParameter("job.contactIndex");
		  String contractcode=request.getParameter("job.contractCode");		 
          contacts = customerService.getBillingContactsByContactIdandCustCode(contactId,customerId,contractcode);
		  AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
		  for(int i=0;i<contacts.size();i++)
		  {
			  //Contact contact = (Contact) contacts.get(i);
			  ContractCustContact contractCustContact=(ContractCustContact)contacts.get(i);
			 // Long id = Long.valueOf(contact.getId());
			  Long id=Long.valueOf(contractCustContact.getContractCustContactId().getContactId());			  
			  String name = id.toString();
			 // String value = contact.getLastName() + "  " + contact.getFirstName() ;
			  String  value=index;
    		  xmlBuilder.addItem(name,value);
		  }
		 xml = xmlBuilder.toString();
	  }   
   
   
   if(request.getParameter("contactId") != null && (!request.getParameter("contactId").trim().equals(""))&&
		   request.getParameter("custCode")!=null && (!request.getParameter("custCode").trim().equals("")) )
	  {
		  contactId = request.getParameter("contactId");
		  String customerId=request.getParameter("custCode");
         contacts = customerService.getContactsByContactIdandCustCode(contactId,customerId);
		  AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
		  for(int i=0;i<contacts.size();i++)
		  {
			  Contact contact = (Contact) contacts.get(i);
			  Long id = Long.valueOf(contact.getId());
			  String name = id.toString();
			  String value = contact.getLastName() + "  " + contact.getFirstName() ;
    		  xmlBuilder.addItem(name,value);
		  }
		 xml = xmlBuilder.toString();
	  }


       if(request.getParameter("contactid") != null && (!request.getParameter("contactid").trim().equals("")))
	  {
		  contactId = request.getParameter("contactid");
		 contacts = customerService.getContactsById(contactId);
	     AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
		  for(int i=0;i<contacts.size();i++)
		  {
			  Contact contact = (Contact) contacts.get(i);
			  Long id = Long.valueOf(contact.getId());
			  String name = id.toString();
			  String value =contact.getFirstName()  + " " +  contact.getLastName();
    		  xmlBuilder.addItem(name,value);
		  }
		 xml = xmlBuilder.toString();
	  }
	 /* if(request.getParameter("contact.id") != null && (!request.getParameter("contact.id").trim().equals("")))
	  {
		  contactId = request.getParameter("contact.id");

		  contacts = customerService.getContactsById(contactId);


  		  xml = new AjaxXmlBuilder().addItems(contacts, Constants.ID, Constants.ID).toString();

	  }*/
	    if(request.getParameter("contact.id") != null && (!request.getParameter("contact.id").trim().equals("")))
	  {
		  contactId = request.getParameter("contact.id");
		  String existCustCode = request.getParameter("existCustCode");
		  // checking existing customer
	      Customer cust = null;
	      if(existCustCode != null && !existCustCode.trim().equalsIgnoreCase("New"))
	    	  cust = customerService.getCustomerByCustCode(existCustCode);
	      if(cust == null )
	    	  existCustCode = null;
	      //end
	      
          if(existCustCode != null && !existCustCode.trim().equals(""))
        	  contacts = customerService.getContactsByContactIdandCustCode(contactId,existCustCode);
          else
		  contacts = customerService.getContactsById(contactId);


  		  xml = new AjaxXmlBuilder().addItems(contacts, "id", "id").toString();

	  }
	   if(request.getParameter("accountOwner")!=null && (!request.getParameter("accountOwner").trim().equals("")))
	  {
		 accountOwner=request.getParameter("accountOwner");
		   accountowners=customerService.getAccountOwner(accountOwner);

  		  xml = new AjaxXmlBuilder().addItems(accountowners, "loginName", "loginName").toString();
	  }

	   if(request.getParameter("location")!=null && (!request.getParameter("location").trim().equals("")) &&
		   request.getParameter("custCode")!=null && (!request.getParameter("custCode").trim().equals("")) )
	  {
		 location=request.getParameter("location");
 		 custCode=request.getParameter("custCode");

		   CustAddrSeq custAddrSeq=customerService.getCustAddrSeqByLocationNumber(new Integer(location),custCode);
		  if(custAddrSeq != null)
  		  xml = new AjaxXmlBuilder().addItem(custAddrSeq.getCustAddrSeqId().getLocationNumber().toString(), custAddrSeq.getCustAddrSeqId().getLocationNumber().toString()).toString();
	  }
			if(request.getParameter("vatCode") != null && (!request.getParameter("vatCode").trim().equals(""))&&
            request.getParameter("taxType")!=null &&(!request.getParameter("taxType").trim().equals("")))
	  {
		  vatCode = request.getParameter("vatCode");
			taxType=  request.getParameter("taxType");
			vatCodes = taxService.getVatCodesByCode(vatCode,taxType);


  		  xml = new AjaxXmlBuilder().addItems(vatCodes, "taxCode", "effDate").toString();

	  }

	 	if(request.getParameter("effDate") != null && (!request.getParameter("effDate").trim().equals(""))&&
        request.getParameter("taxType")!=null &&(!request.getParameter("taxType").trim().equals("")))
	  {
		  vatCode = request.getParameter("vatCode");
		  taxType=  request.getParameter("taxType");
		  vatCodes = taxService.getVatCodesByCode(vatCode,taxType);


  		  xml = new AjaxXmlBuilder().addItems(vatCodes, "effDate", "taxPct").toString();
	  }

	    if(null != request.getParameter("custContractFlag") 
	    		  && request.getParameter("custContractFlag").trim().length() > 0
	    		  && "true".equalsIgnoreCase(request.getParameter("custContractFlag"))){
	    	  	if(null != request.getParameter("custCode") 
	    	    		  && request.getParameter("custCode").trim().length() > 0){
	    			  custCode = request.getParameter("custCode");
	    			  contractCode = request.getParameter("contractCode");
	    			 // customer = customerService.getCustomersByCustCode(custCode);
	    			  List contractCustomers = contractService.getContractCustsByCustCodeAndContractCode(custCode,contractCode);
	    			  xml = new AjaxXmlBuilder().addItems(contractCustomers, "contractCustId.custCode", "contractCustId.custCode").toString();
	    			//  List contractCusts = contractService.getContractCustsByContractCode(contractCode, null);

	    	  		 // xml = new AjaxXmlBuilder().addItems(contractCusts, "custCode", "name").toString();
	    	  	}
	    	  
	      }
	      else if(null != request.getParameter("contactCustContractFlag") 
	    		  && request.getParameter("contactCustContractFlag").trim().length() > 0
	    		  && "true".equalsIgnoreCase(request.getParameter("contactCustContractFlag"))
	    		  && null != request.getParameter("contactId") 
	    		  && request.getParameter("contactId").trim().length() > 0){	 
	    		  contactId = request.getParameter("contactId");
	    		  contractCode = request.getParameter("contractCode");
	              contacts = contractService.getContactsByContractCode(contactId,contractCode);
	    		  AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
	    		  for(int i=0;i<contacts.size();i++)
	    		  {
	    			  ContractCustContact contact = (ContractCustContact) contacts.get(i);
	    			  Long id = contact.getContractCustContactId().getContactId();
	    			  String name = id.toString();
	    			  String value = contact.getContact().getLastName() + "  " +contact.getContact().getFirstName() ;
	        		  xmlBuilder.addItem(name,name);
	    		  }
	    		 xml = xmlBuilder.toString();    	  
	      }	 	
	 	
		  Map model = new HashMap();
		  model.put("Content", xml);

		  View view = (View)getApplicationContext().getBean("xmlView");
		 return new ModelAndView(view, model);
  }



}
