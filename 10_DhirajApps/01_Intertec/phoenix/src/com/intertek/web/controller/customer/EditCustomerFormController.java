package com.intertek.web.controller.customer;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddCustomer;
import com.intertek.domain.AddCustomerAddress;
import com.intertek.domain.CustomerAddresses;
import com.intertek.domain.CustomerContacts;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContactCustId;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustId;
import com.intertek.entity.Country;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddrSeqId;
import com.intertek.entity.CustAddress;
import com.intertek.entity.CustAddressLanguage;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.CustVatRegistrationId;
import com.intertek.entity.Customer;
import com.intertek.entity.CustomerLanguage;
import com.intertek.entity.JobContract;
import com.intertek.entity.Notes;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ContractService;
import com.intertek.service.CountryService;
import com.intertek.service.CustomerService;
import com.intertek.service.NotesService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomPrimitiveNumberEditor;
import java.util.*;
public class EditCustomerFormController extends BaseSimpleFormController
//public class EditCustomerFormController extends SimpleFormController
{
  public EditCustomerFormController() {
    super();
	  setSessionForm(true);
    setCommandClass(AddCustomer.class);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  protected ModelAndView onSubmit(
    HttpServletRequest request,
    HttpServletResponse response,
    Object command,
    BindException errors
  ) throws Exception
  {
    AddCustomer addCustomer = (AddCustomer) command;

    Customer customer = addCustomer.getCustomer();

    HttpSession session = request.getSession();
    Contact newContact = (Contact) session.getAttribute("EditCustomerFormController.NewContact");
   
    Map custAddrLangmap = (Map)session.getAttribute("customerAddressMultilingual");
    CustomerLanguage customerLanguage = (CustomerLanguage)session.getAttribute("customermultilingual");
    
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
	UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	String addCustContact = request.getParameter("addCustContact");
	String sortFlag = request.getParameter("sortFlag");


 	  String addOrDeleteCustAddrSeq = request.getParameter("addOrDeleteCustAddrSeq");
   	String addOrDeleteCustAddress = request.getParameter("addOrDeleteCustAddrDtl");
   	String addrSeqIndex = request.getParameter("custAddrSeqIndex");
   	String addrDtlIndex = request.getParameter("custAddrDtlIndex");
	  String index = request.getParameter("custContactsIndex");
    String addOrDeleteCustContract = request.getParameter("addOrDeleteCustContract");
    String indexs = request.getParameter("contractCustIndex");
    String navFlag = addCustomer.getNavFlag();
    
	   String addOrDeleteCustVatReg = request.getParameter("addOrDeleteCustVatReg");
	   String addOrDeleteCustNote=request.getParameter("addOrDeleteCustNote");
	   String custVatRegIndex = request.getParameter("custVatRegIndex");
	  String page = addCustomer.getTabName();
	  
	  String pageNumber = addCustomer.getPageNumber();
	  if(pageNumber != null && (!pageNumber.trim().equals("")) && (!pageNumber.trim().equals("none")))
	  {
		  int pageNo = Integer.parseInt(pageNumber);
		  if(pageNo <= 0)	pageNo = 1;
			Pagination pagination = new Pagination(1, 10, pageNo,10);
		  if(page.trim().equals("1")) //Request for address pagination
		  {
			  CustomerAddresses  customerAddresses = addCustomer.getCustomerAddresses();
			  customerAddresses.getPagination().setCurrentPageNum(pageNo);
			  
			  //Move CustomerAddressesToAddCustomerAddresses
			  addCustomer.setAddCustomerAddresses(copyCustomerAddresses(customerAddresses,addCustomer.getAddCustomerAddresses(),customerAddresses.getPagination()));
			  customerAddresses = populateCustomerAddresses(customerAddresses,addCustomer.getAddCustomerAddresses(),customerAddresses.getPagination());
			  addCustomer.setCustomerAddresses(customerAddresses);
			  
		  }	 
		  else if(page.trim().equals("2")) //Request for contact pagination
		  {
			  CustomerContacts  customerContacts = addCustomer.getCustomerContacts();
			  customerContacts.getPagination().setCurrentPageNum(pageNo);
			  
			  //Move CustomerContacts To ContactCusts
			  addCustomer.setContactCusts(copyCustomerContacts(customerContacts,addCustomer.getContactCusts(),customerContacts.getPagination()));
			  customerContacts = populateCustomerContacts(customerContacts,addCustomer.getContactCusts(),customerContacts.getPagination());
			  addCustomer.setCustomerContacts(customerContacts);
			  
		  }			  
		  
		  addCustomer.setPageNumber("");
		  addCustomer.setTabName(page);
		  return showForm(request, response, errors);
	  }
	  
	  if(navFlag != null && (!navFlag.trim().equals("")))
	  {
		  if(navFlag.trim().equals("nav"))
		  {
			  addCustomer.setTabName(page);
			  addCustomer.setNavFlag("none");
			  return showForm(request, response, errors);
			  
		  }
	  }

 //addCustomer.setTabName("0");
	String contactFlag=null;

  User user=userService.getUserByName(SecurityUtil.getUser().getLoginName());
  try {
  if((sortFlag != null) && ("Contactid".equals(sortFlag)))
  {
	 List custContacts = customerService.sortContactCustsByContactIdWithCustCode(customer.getCustCode());
		if(custContacts != null && custContacts.size() > 0)
		{
		addCustomer.setContactCustCount(custContacts.size());
		int custCount = 0;
		ContactCust[] contactCusts = new ContactCust[custContacts.size()];
		Set custContactsSet = new HashSet();
		for(int k=0;k<custContacts.size();k++)
	  {
			ContactCust contactCust = (ContactCust) custContacts.get(k);
			contactCust.getContact().setFirstName(contactCust.getContact().getFirstName());
			contactCust.getContact().setLastName(contactCust.getContact().getLastName());
			contactCusts[k] = contactCust;
			custContactsSet.add(contactCust);
			custCount++;
	  }
	  addCustomer.setContactCusts(contactCusts);
	  
	  addCustomer.setCustomerContacts(populateCustomerContacts(addCustomer.getCustomerContacts(),addCustomer.getContactCusts(),addCustomer.getCustomerContacts().getPagination()));
	  
		}
		  addCustomer.setTabName("2");
		  addCustomer.setSortFlag("none");
    return showForm(request, response, errors);
  }

  if((sortFlag != null) && ("Fnamesort".equals(sortFlag)))
    {
	 List custContacts = customerService.sortContactCustsByFirstNameWithCustCode(customer.getCustCode());
		if(custContacts != null && custContacts.size() > 0)
		{
		addCustomer.setContactCustCount(custContacts.size());
		int custCount = 0;
		ContactCust[] contactCusts = new ContactCust[custContacts.size()];
		Set custContactsSet = new HashSet();
		for(int k=0;k<custContacts.size();k++)
	  {
			ContactCust contactCust = (ContactCust) custContacts.get(k);
			contactCust.getContact().setFirstName(contactCust.getContact().getFirstName());
			contactCust.getContact().setLastName(contactCust.getContact().getLastName());
			contactCusts[k] = contactCust;
			custContactsSet.add(contactCust);
			custCount++;
	  }
	  addCustomer.setContactCusts(contactCusts);
	  addCustomer.setCustomerContacts(populateCustomerContacts(addCustomer.getCustomerContacts(),addCustomer.getContactCusts(),addCustomer.getCustomerContacts().getPagination()));
		}
		  addCustomer.setTabName("2");
		  addCustomer.setSortFlag("none");
      return showForm(request, response, errors);
    }
  
  if((sortFlag != null) && ("Lnamesort".equals(sortFlag)))
  {
	 List custContacts = customerService.sortContactCustsByLastNameWithCustCode(customer.getCustCode());
		if(custContacts != null && custContacts.size() > 0)
		{
		addCustomer.setContactCustCount(custContacts.size());
		int custCount = 0;
		ContactCust[] contactCusts = new ContactCust[custContacts.size()];
		Set custContactsSet = new HashSet();
		for(int k=0;k<custContacts.size();k++)
	  {
			ContactCust contactCust = (ContactCust) custContacts.get(k);
			contactCust.getContact().setFirstName(contactCust.getContact().getFirstName());
			contactCust.getContact().setLastName(contactCust.getContact().getLastName());
			contactCusts[k] = contactCust;
			custContactsSet.add(contactCust);
			custCount++;
	  }
	  addCustomer.setContactCusts(contactCusts);
	  addCustomer.setCustomerContacts(populateCustomerContacts(addCustomer.getCustomerContacts(),addCustomer.getContactCusts(),addCustomer.getCustomerContacts().getPagination()));
		}
		  addCustomer.setTabName("2");
		  addCustomer.setSortFlag("none");
    return showForm(request, response, errors);
  }
  if((sortFlag != null) && ("addressSeq".equals(sortFlag)))
  {
	 List custContacts = customerService.sortContactCustsByAddrSeqWithCustCode(customer.getCustCode());
		if(custContacts != null && custContacts.size() > 0)
		{
		addCustomer.setContactCustCount(custContacts.size());
		int custCount = 0;
		ContactCust[] contactCusts = new ContactCust[custContacts.size()];
		Set custContactsSet = new HashSet();
		for(int k=0;k<custContacts.size();k++)
	  {
			ContactCust contactCust = (ContactCust) custContacts.get(k);
			contactCust.getContact().setFirstName(contactCust.getContact().getFirstName());
			contactCust.getContact().setLastName(contactCust.getContact().getLastName());
			contactCusts[k] = contactCust;
			custContactsSet.add(contactCust);
			custCount++;
	  }
	  addCustomer.setContactCusts(contactCusts);
	  addCustomer.setCustomerContacts(populateCustomerContacts(addCustomer.getCustomerContacts(),addCustomer.getContactCusts(),addCustomer.getCustomerContacts().getPagination()));
		}
		  addCustomer.setTabName("2");
		  addCustomer.setSortFlag("none");
		  
		 
    return showForm(request, response, errors);
  }
  
  if((sortFlag != null) && ("addressDesc".equals(sortFlag)))
  {
	 List custContacts = customerService.sortContactCustsByAddrDescWithCustCode(customer.getCustCode());
		if(custContacts != null && custContacts.size() > 0)
		{
		addCustomer.setContactCustCount(custContacts.size());
		int custCount = 0;
		ContactCust[] contactCusts = new ContactCust[custContacts.size()];
		Set custContactsSet = new HashSet();
		for(int k=0;k<custContacts.size();k++)
	  {
			ContactCust contactCust = (ContactCust) custContacts.get(k);
			contactCust.getContact().setFirstName(contactCust.getContact().getFirstName());
			contactCust.getContact().setLastName(contactCust.getContact().getLastName());
			contactCusts[k] = contactCust;
			custContactsSet.add(contactCust);
			custCount++;
	  }
	  addCustomer.setContactCusts(contactCusts);
	  addCustomer.setCustomerContacts(populateCustomerContacts(addCustomer.getCustomerContacts(),addCustomer.getContactCusts(),addCustomer.getCustomerContacts().getPagination()));
		}
		  addCustomer.setTabName("2");
		  addCustomer.setSortFlag("none");
		  
		 
    return showForm(request, response, errors);
  }
 
  if((sortFlag != null) && ("rstatus".equals(sortFlag)))
  {
	 List custContacts = customerService.sortContactCustsByRelStatusWithCustCode(customer.getCustCode());
		if(custContacts != null && custContacts.size() > 0)
		{
		addCustomer.setContactCustCount(custContacts.size());
		int custCount = 0;
		ContactCust[] contactCusts = new ContactCust[custContacts.size()];
		Set custContactsSet = new HashSet();
		for(int k=0;k<custContacts.size();k++)
	  {
			ContactCust contactCust = (ContactCust) custContacts.get(k);
			contactCust.getContact().setFirstName(contactCust.getContact().getFirstName());
			contactCust.getContact().setLastName(contactCust.getContact().getLastName());
			contactCusts[k] = contactCust;
			custContactsSet.add(contactCust);
			custCount++;
	  }
	  addCustomer.setContactCusts(contactCusts);
	  addCustomer.setCustomerContacts(populateCustomerContacts(addCustomer.getCustomerContacts(),addCustomer.getContactCusts(),addCustomer.getCustomerContacts().getPagination()));
		}
		  addCustomer.setTabName("2");
		  addCustomer.setSortFlag("none");
		  
		  
    return showForm(request, response, errors);
  }
  if((sortFlag != null) && (("con.contractCode".equals(sortFlag)) || ("con.description".equals(sortFlag))))
  {
	 List custContracts = customerService.getSortedContractCustsByCustCode(customer.getCustCode(),sortFlag);
		if(custContracts != null && custContracts.size() > 0)
		{
		addCustomer.setContractCustCount(custContracts.size());
		int custCount = 0;
		ContractCust[] contractCusts = new ContractCust[custContracts.size()];
		Set custContractsSet = new HashSet();
		for(int k=0;k<custContracts.size();k++)
	  {
			ContractCust contractCust = (ContractCust) custContracts.get(k);
			contractCusts[k] = contractCust;
			custContractsSet.add(contractCust);
			custCount++;
	  }
	  addCustomer.setContractCusts(contractCusts);
	  
	  
		}
		  addCustomer.setTabName("3");
		  addCustomer.setSortFlag("none");
    return showForm(request, response, errors);
  }
  } catch(ServiceException e)
  {
      e.printStackTrace();
      errors.reject(e.getMessage(), e.getParams(), null);
      return showForm(request, response, errors);
  } catch(Throwable t)
  {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
  }
  if((addCustContact != null) && ("add".equals(addCustContact) ))
  {
	  if("add".equals(addCustContact))
	  {		
		   
		   if(addCustomer.getContactCusts()!=null)
		  { addCustomer.setCustomerContacts(addContactCust(addCustomer.getCustomerContacts(),addCustomer,addCustomer.getContactCusts().length));}
		   else
		  {addCustomer.setCustomerContacts(addContactCust(addCustomer.getCustomerContacts(),addCustomer,0));}
	  }
			
	  addCustomer.setContactCustCount(addCustomer.getContactCusts().length);

	  addCustomer.setAddCustContact("none");
	  addCustomer.setAddCustContact("none");
	  addCustomer.setTabName("2");
    return showForm(request, response, errors);
  }

    if((addOrDeleteCustAddrSeq != null) && ("addAddr".equals(addOrDeleteCustAddrSeq) || "deleteAddr".equals(addOrDeleteCustAddrSeq)))
    {
			if("addAddr".equals(addOrDeleteCustAddrSeq))
			{
				
				addCustomer.getCustomerAddresses().setAddCustomerAddresses(addCustomerAddress(addCustomer.getCustomerAddresses().getAddCustomerAddresses(),user,addCustomer));
				 addCustomer.setVesselFlag("vessel");
			}
			else
			{
				
				AddCustomerAddress[] addCustomerAddressItems = copyCustomerAddresses(addCustomer.getCustomerAddresses(),addCustomer.getAddCustomerAddresses(),addCustomer.getCustomerAddresses().getPagination());
				CustAddrSeq custAddrSeq = addCustomerAddressItems[addCustomer.getCustAddrSeqIndex()].getCustAddrSeq();
				boolean custaddressvalidate=customerService.getContactCustsByCustCodeandLocationNumber(customer.getCustCode(),custAddrSeq.getCustAddrSeqId().getLocationNumber());
               if(custaddressvalidate==true)
                {
            	   addCustomer.setCustAddrSeqCount(addCustomer.getAddCustomerAddresses().length);
         		  addCustomer.setAddOrDeleteCustAddrSeq("none");
         		  addCustomer.setTabName("1");
            	   errors.reject("customer.address.delete.error", null, null);
                	
                	return showForm(request, response, errors);
                }
                else{							
				addCustomer.getCustomerAddresses().setAddCustomerAddresses(deleteCustomerAddress(addCustomer.getCustomerAddresses().getAddCustomerAddresses(), addCustomer.getCustAddrSeqIndex(),addCustomer));
               }
			}
		  addCustomer.setCustAddrSeqCount(addCustomer.getAddCustomerAddresses().length);

		  addCustomer.setAddOrDeleteCustAddrSeq("none");
		  addCustomer.setTabName("1");
      return showForm(request, response, errors);
    }

      if((addOrDeleteCustAddress != null) && ("addAddrDtls".equals(addOrDeleteCustAddress) || "deleteAddrDtls".equals(addOrDeleteCustAddress)))
    {
		  int intAddrSeqIndex = Integer.parseInt(addrSeqIndex);
			if("addAddrDtls".equals(addOrDeleteCustAddress))
			{
				AddCustomerAddress[] addCustomerAddresses = addCustomer.getCustomerAddresses().getAddCustomerAddresses();
				AddCustomerAddress addCustomerAddress = addCustomerAddresses[intAddrSeqIndex];
				addCustomerAddress.setCustAddresses(addCustomerAddressDetails(addCustomerAddress.getCustAddresses(),user));
				 addCustomerAddress.setCustAddressCount(addCustomerAddress.getCustAddresses().length);

				addCustomerAddresses[intAddrSeqIndex] = addCustomerAddress;
				addCustomer.getCustomerAddresses().setAddCustomerAddresses(addCustomerAddresses);
			}
			else
			{
				 int intAddrDtlIndex = Integer.parseInt(addrDtlIndex);

				AddCustomerAddress[] addCustomerAddresses = addCustomer.getCustomerAddresses().getAddCustomerAddresses();
				AddCustomerAddress addCustomerAddress = addCustomerAddresses[intAddrSeqIndex];
				addCustomerAddress.setCustAddresses(deleteCustomerAddressDetails(addCustomerAddress.getCustAddresses(),intAddrDtlIndex));
				 addCustomerAddress.setCustAddressCount(addCustomerAddress.getCustAddresses().length);

				addCustomerAddresses[intAddrSeqIndex] = addCustomerAddress;

				addCustomer.getCustomerAddresses().setAddCustomerAddresses(addCustomerAddresses);
			}
		  addCustomer.setAddOrDeleteCustAddrDtl("none");
		  addCustomer.setTabName("1");
      return showForm(request, response, errors);
    }
	 if((addOrDeleteCustContract != null) && ("add".equals(addOrDeleteCustContract) || "delete".equals(addOrDeleteCustContract)))
    {		try {
			if("add".equals(addOrDeleteCustContract))
			{
				addCustomer.setContractCusts(addContractCust(addCustomer.getContractCusts()));
			}
			else
			{
				contractService.deleteContractCustAssociations(addCustomer.getContractCusts()[addCustomer.getContractCustIndex()]);
				addCustomer.setContractCusts(deleteContractCust(addCustomer.getContractCusts(), addCustomer.getContractCustIndex()));
			}
		  addCustomer.setContractCustCount(addCustomer.getContractCusts().length);

		  addCustomer.setAddOrDeleteCustContract("none");
		  addCustomer.setTabName("3");
      return showForm(request, response, errors);
    	   } catch(ServiceException e)
    	   {
    		  addCustomer.setAddOrDeleteCustContract("none");
    	      e.printStackTrace();
    	      errors.reject(e.getMessage(), e.getParams(), null);
    	      return showForm(request, response, errors);
    	   } catch(Throwable t)
    	   {
    		  addCustomer.setAddOrDeleteCustContract("none");
    	      t.printStackTrace();
    	      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
    	      return showForm(request, response, errors);
    	   }
    }
	 if((addOrDeleteCustVatReg != null) && ("add".equals(addOrDeleteCustVatReg) || "delete".equals(addOrDeleteCustVatReg)))
	 {
		if("add".equals(addOrDeleteCustVatReg))
		{
			addCustomer.setCustVatRegistrations(addCustVatRegistration(addCustomer.getCustVatRegistrations()));
		}
		else
		{
			addCustomer.setCustVatRegistrations(deleteCustVatRegistration(addCustomer.getCustVatRegistrations(), addCustomer.getCustVatRegIndex()));
		}
		addCustomer.setCustVatRegCount(addCustomer.getCustVatRegistrations().length);
	    addCustomer.setAddOrDeleteCustVatReg("none");
	    addCustomer.setTabName("4");
	    return showForm(request, response, errors);
	}	
	
	
	 /************************ cust Note**********************************/
	  
	  if(addOrDeleteCustNote!=null && ("add".equals(addOrDeleteCustNote)||"view".equals(addOrDeleteCustNote) || "delete".equals(addOrDeleteCustNote) || "reset".equals(addOrDeleteCustNote)))
		{
		   if("add".equals(addOrDeleteCustNote))
			{
				Notes custNote=new Notes();
				custNote.setNoteSummary(addCustomer.getNoteSummary());
				custNote.setNoteDetails(addCustomer.getNote());
				custNote.setAddedByUserName(user.getLoginName());
				custNote.setDateTimeAdded(new Date());
				custNote.setId(addCustomer.getNoteId());
				if(addCustomer.getCustomerNotesList()==null)
				{
					List  <Notes> custNoteList=new ArrayList<Notes>();
					addCustomer.setCustomerNotesList(custNoteList);
					addCustomer.setCustNoteEditFlag(-1);
				}
				if(addCustomer.getCustNoteEditFlag()>=0)
				{
					addCustomer.getCustomerNotesList().set(addCustomer.getCustNoteEditFlag(),custNote);
				}
				else{
				addCustomer.getCustomerNotesList().add(custNote);
				}
			  //addCustomer.setCustomerNotes(addCustNote(addCustomer.getCustomerNotes(), user));
			}
		  else if("view".equals(addOrDeleteCustNote))
			{
			  List <Notes> custNoteList=addCustomer.getCustomerNotesList();
				if(addCustomer.getCustomerNotesList()!=null && addCustomer.getCustomerNotesList().size()>0)
				{	List <Notes>custNoteTemp=new ArrayList<Notes>();
					for(int i=0;i<addCustomer.getCustomerNotesList().size();i++)
					{	Notes custNote=addCustomer.getCustomerNotesList().get(i);
						if(i==addCustomer.getCustNoteCount())
							{
							addCustomer.setNoteAddBy(custNote.getAddedByUserName());
						  	try {
								SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy hh:mm a");
								addCustomer.setNoteAddDateTime(df.format(custNote.getDateTimeAdded()));
							} catch (Exception e) {

							}
						  	addCustomer.setNote(custNote.getNoteDetails());
							addCustomer.setNoteSummary(custNote.getNoteSummary());
						  	addCustomer.setAddOrDeleteCustNote("none");
						    addCustomer.setTabName("5");
						    addCustomer.setCustNoteEditFlag(i);
						    addCustomer.setNoteId(custNote.getId());
						    return showForm(request, response, errors);
							}
					}
				}
			}
			else if("delete".equals(addOrDeleteCustNote))
			{
				List <Notes> custNoteList=addCustomer.getCustomerNotesList();
				if(addCustomer.getCustomerNotesList()!=null && addCustomer.getCustomerNotesList().size()>0)
				{	List <Notes>custNoteTemp=new ArrayList<Notes>();
					for(int i=0;i<addCustomer.getCustomerNotesList().size();i++)
					{
						if(i==addCustomer.getCustNoteCount())
						{
							if(addCustomer.getDelNotesList()==null)
							{
								addCustomer.setDelNotesList(new ArrayList<Notes>());
							}
							Notes delNote=addCustomer.getCustomerNotesList().get(i);
							//if(addCustomer.getNoteId()!=0L)
							addCustomer.getDelNotesList().add(delNote);
							continue;
						}
						custNoteTemp.add(addCustomer.getCustomerNotesList().get(i));
					}
					addCustomer.getCustomerNotesList().clear();
					addCustomer.setCustomerNotesList(custNoteTemp);
				}
				//addCustomer.setCustomerNotes(deleteCustNote(addCustomer.getCustomerNotes(),addCustomer.getCustNoteCount()));
			}
		  	addCustomer.setNoteAddBy("");
		  	addCustomer.setNoteId(0L);
		  	addCustomer.setNoteAddDateTime("");
		  	addCustomer.setNote("");
			addCustomer.setNoteSummary("");
		  	if(addCustomer.getCustomerNotesList()!=null)
			addCustomer.setCustNoteCount(addCustomer.getCustomerNotesList().size());
		    addCustomer.setAddOrDeleteCustNote("none");
		    addCustomer.setTabName("5");
		    addCustomer.setCustNoteEditFlag(-1);
		    return showForm(request, response, errors);
	  }
	  

	  /***************************cust Note ends here*******************************/
	//customer.getContractCusts().clear();
	//customer.getContactCusts().clear();
	//customer.getCustAddrSeqs().clear();
	ContactCust[] contactCustItems = null;
	
	if(addCustomer.getCustomerContacts() != null)
	{
		contactCustItems = addCustomer.getCustomerContacts().getContactCusts();
	}
		

	ContractCust[] contractCustItems = addCustomer.getContractCusts();

	CustVatRegistration[] custVatRegistrationItems = addCustomer.getCustVatRegistrations();
	//CustomerNote[] custNoteItems=addCustomer.getCustomerNotes();
	contactFlag=request.getParameter("contactFlag");

	if("newval".equals(contactFlag)){
		try {
		String rowNo=request.getParameter("rowNum");
		int num=Integer.parseInt(rowNo);

		contactCustItems = addCustomer.getCustomerContacts().getContactCusts();
		
		Contact contact=customerService.getContactById((contactCustItems[num].getContact()).getId());
		
		contactCustItems[num].setContact(contact);
		contactCustItems[num].setCustomer(customer);
		contactCustItems[num].getContactCustId().setCustCode(customer.getCustCode());
		contactCustItems[num].getContactCustId().setContactId(contact.getId());
		contactCustItems[num].getContactCustId().setLocationNumber(customer.getPrimaryBillToAddress());
//		 sreeram code
		CustAddrSeq custAddrSeq = null;
		custAddrSeq = customerService.getCustAddrSeqByLocationNumber(customer.getPrimaryBillToAddress(), customer.getCustCode());
		if(custAddrSeq == null)
			contactCustItems[num].setCustAddrSeq(new CustAddrSeq());
		else
			contactCustItems[num].setCustAddrSeq(custAddrSeq);
		//end
		//contactCustItems[num].setCustAddrSeq(customerService.getCustAddrSeqByLocationNumber(customer.getPrimaryBillToAddress(), customer.getCustCode()));
		//customerService.s
		contactCustItems[num] = contactCustItems[num];
		addCustomer.getCustomerContacts().setContactCusts(contactCustItems);
		
		//Populkate main contactcust with new contact
	/*	ContactCust[] mainContactCusts = addCustomer.getContactCusts();
		if(mainContactCusts != null)
		{
			for(int i=0;i<mainContactCusts.length;i++)
			{
				if(mainContactCusts[i].getContact().getId() <= 0)
				{
					mainContactCusts[i].setContact(contact);
				}
			}
		}
		addCustomer.setContactCusts(mainContactCusts);		*/
		addCustomer.setContactFlag("none");
		addCustomer.setTabName("2");
		return showForm(request, response, errors);
		} catch(ServiceException e)
		{
			 addCustomer.setContactFlag("none");
		     e.printStackTrace();
		     errors.reject(e.getMessage(), e.getParams(), null);
		     return showForm(request, response, errors);
		} catch(Throwable t)
		{
			 addCustomer.setContactFlag("none");
		     t.printStackTrace();
		     errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		     return showForm(request, response, errors);
		}
	}



   if("newcontactval".equals(contactFlag)){

	    try {
		String rowNo=request.getParameter("rowNum");
		int num=Integer.parseInt(rowNo);

		//contactCustItems = addCustomer.getContactCusts();
		contactCustItems = addCustomer.getCustomerContacts().getContactCusts();
       Contact contact = newContact;
		contactCustItems[num].setContact(contact);
		contactCustItems[num].setCustomer(customer);
		contactCustItems[num].getContactCustId().setCustCode(customer.getCustCode());
		contactCustItems[num].getContactCustId().setContactId(contact.getId());
		contactCustItems[num].getContactCustId().setLocationNumber(customer.getPrimaryBillToAddress());
		// sreeram code
		CustAddrSeq custAddrSeq = null;
		custAddrSeq = customerService.getCustAddrSeqByLocationNumber(customer.getPrimaryBillToAddress(), customer.getCustCode());
		if(custAddrSeq == null)
			contactCustItems[num].setCustAddrSeq(new CustAddrSeq());
		else
			contactCustItems[num].setCustAddrSeq(custAddrSeq);
		//end
		//contactCustItems[num].setCustAddrSeq(customerService.getCustAddrSeqByLocationNumber(customer.getPrimaryBillToAddress(), customer.getCustCode()));
		//customerService.s
		contactCustItems[num] = contactCustItems[num];
		addCustomer.getCustomerContacts().setContactCusts(contactCustItems);
		
		//Populkate main contactcust with new contact
		ContactCust[] mainContactCusts = addCustomer.getContactCusts();
		if(mainContactCusts != null)
		{
			for(int i=0;i<mainContactCusts.length;i++)
			{
				if(mainContactCusts[i].getContact().getId() <= 0)
				{
					mainContactCusts[i].setContact(contact);
				}
			}
		}
		addCustomer.setContactCusts(mainContactCusts);
		addCustomer.setContactFlag("none");
		addCustomer.setTabName("2");
		return showForm(request, response, errors);
	    } catch(ServiceException e)
		{
			 addCustomer.setContactFlag("none");
		     e.printStackTrace();
		     errors.reject(e.getMessage(), e.getParams(), null);
		     return showForm(request, response, errors);
		} catch(Throwable t)
		{
			 addCustomer.setContactFlag("none");
		     t.printStackTrace();
		     errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		     return showForm(request, response, errors);
		}
	}
	String addSeqFlag=request.getParameter("addSeqFlag");
	 if("newval".equals(addSeqFlag))
	{
		try {
		    String rowNo=request.getParameter("rowNum");			
			int num=Integer.parseInt(rowNo);
			String custCode=	request.getParameter("customer.custCode");
			ContactCust contactCust=new ContactCust();
			CustAddrSeq custAddrSeq=new CustAddrSeq();
			//try{
			// sreeram code
					custAddrSeq=customerService.getCustAddrSeqByLocationNumber(contactCustItems[num].getContactCustId().getLocationNumber(),custCode);
			if(custAddrSeq == null)
				contactCustItems[num].setCustAddrSeq(new CustAddrSeq());
			else
				contactCustItems[num].setCustAddrSeq(custAddrSeq);
			//end
					/*custAddrSeq=customerService.getCustAddrSeqByLocationNumber(contactCustItems[num].getContactCustId().getLocationNumber(),custCode);
					contactCustItems[num].setCustAddrSeq(custAddrSeq);*/
					addCustomer.getCustomerContacts().setContactCusts(contactCustItems);
					
					//Populate main contactcust with new contact
					ContactCust[] mainContactCusts = addCustomer.getContactCusts();
					if(mainContactCusts != null)
					{
						for(int i=0;i<mainContactCusts.length;i++)
						{
							if(mainContactCusts[i].getCustAddrSeq() == null || mainContactCusts[i].getContactCustId().getLocationNumber() <= 0)
							{
								mainContactCusts[i].getContactCustId().setLocationNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber());
								mainContactCusts[i].setCustAddrSeq(custAddrSeq);
							}
						}
					}
					addCustomer.setContactCusts(mainContactCusts);
					addCustomer.setAddSeqFlag("none");
					addCustomer.setTabName("2");
					return showForm(request, response, errors);
		} catch(ServiceException e)
		{
			 addCustomer.setAddSeqFlag("none");
		     e.printStackTrace();
		     errors.reject(e.getMessage(), e.getParams(), null);
		     return showForm(request, response, errors);
		} catch(Throwable t)
		{
			 addCustomer.setAddSeqFlag("none");
		     t.printStackTrace();
		     errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		     return showForm(request, response, errors);
		}
			/*	     }
			catch(Exception e)
					{
					   e.printStackTrace();
					}*/
    }	
	

    String contractFlag=request.getParameter("contractFlag");
			   if("contractFlag".equals(contractFlag))
	       {
			try {
		    String rowNo=request.getParameter("rowNum");
			int num=Integer.parseInt(rowNo);
			ContractCust contractCust=new ContractCust();
			Contract contract=new Contract();
			//try{
			contract=contractService.getContractByContractCode(contractCustItems[num].getContract().getContractCode());
			
				      contractCustItems[num].setContract(contract);
				      contractCustItems[num].getContractCustId().setCustCode(customer.getCustCode());
				      contractCustItems[num].setCustomer(customer);
				      addCustomer.setContractCusts(contractCustItems);
					   addCustomer.setTabName("3");
					  addCustomer.setContractFlag("none");	
					  addCustomer.setTabName("3");
					  return showForm(request, response, errors);
					  
			} catch(Throwable t)
			{
				 addCustomer.setContractFlag("none");	
			     t.printStackTrace();
			     errors.reject("generic.error", new Object[] {t.getMessage()}, null);
			     return showForm(request, response, errors);
			}
		/*	}
			catch(Exception e)
			   {
				e.printStackTrace();
			   }*/
   	       }	
		try {
		if(addCustomer.getCustomerContacts() != null)
			contactCustItems = copyCustomerContacts(addCustomer.getCustomerContacts(),addCustomer.getContactCusts(),addCustomer.getCustomerContacts().getPagination());       			   
		if (contactCustItems != null )
		{
			Set contactCustSet = new HashSet();
			for(int i=0; i< contactCustItems.length; i++)
			{
				
				ContactCust contactCust = contactCustItems[i];
				if(contactCust.getContact().getId()  > 0)
				{
					Contact existingContact = customerService.getContactById(contactCust.getContact().getId());
					if(existingContact != null)
					{
						if(contactCust.getContactCustId().getLocationNumber() != null && contactCust.getContactCustId().getLocationNumber()> 0)
						{
							contactCust.setCustomer(customer);
							contactCust.setContact(existingContact);
							//sreeram added
							CustAddrSeq custAddrSeq =customerService.getCustAddrSeqByLocationNumber(contactCust.getContactCustId().getLocationNumber(), customer.getCustCode());
							if(custAddrSeq == null)
								contactCust.setCustAddrSeq(new CustAddrSeq());
							else
								contactCust.setCustAddrSeq(custAddrSeq);
							//end
							//contactCust.setCustAddrSeq(customerService.getCustAddrSeqByLocationNumber(contactCust.getContactCustId().getLocationNumber(), customer.getCustCode()));
							if(contactCust.getContact().getFirstName() != null && (!contactCust.getContact().getFirstName().equals("")))
								contactCustSet.add(contactCust);
						}
						else
						{
						     // errors.reject("edit.customer.error", new Object[] {"Invalid location number :"+contactCust.getContactCustId().getLocationNumber()}, null);
							  errors.reject("address.contact.association.error", new Object[] {contactCust.getContact().getId()}, null);
						      return showForm(request, response, errors);							
						}
					}
					else
					{
					      errors.reject("contact.error", new Object[] {contactCust.getContact().getId()}, null);
					      return showForm(request, response, errors);						
					}
				}
				else if(contactCust.getContact().getId() == 0) //New contact to be saved to DB
				{
					Contact contact = customerService.addContact(contactCust.getContact());
					if(contactCust.getContactCustId().getLocationNumber() != null && contactCust.getContactCustId().getLocationNumber()> 0)
					{
						contactCust.setCustomer(customer);
						contactCust.setContact(contact);
//						sreeram added
						CustAddrSeq custAddrSeq = customerService.getCustAddrSeqByLocationNumber(contactCust.getContactCustId().getLocationNumber(), customer.getCustCode());
						if(custAddrSeq == null)
							contactCust.setCustAddrSeq(new CustAddrSeq());
						else
							contactCust.setCustAddrSeq(custAddrSeq);
						//end
						//contactCust.setCustAddrSeq(customerService.getCustAddrSeqByLocationNumber(contactCust.getContactCustId().getLocationNumber(), customer.getCustCode()));
						if(contactCust.getContact().getFirstName() != null && (!contactCust.getContact().getFirstName().equals("")))
							contactCustSet.add(contactCust);
					}
					else
					{
					     // errors.reject("edit.customer.error", new Object[] {"Invalid location number :"+contactCust.getContactCustId().getLocationNumber()}, null);
						errors.reject("address.contact.association.error", new Object[] {contactCust.getContact().getId()}, null);
					      return showForm(request, response, errors);							
					}
					
				}
				else
				{
				      errors.reject("invalid.contact.error", new Object[] {contactCust.getContact().getId()}, null);
				      return showForm(request, response, errors);
				}
				
			}
			customer.getContactCusts().clear();
			//System.out.println("after clearing contact custs:"+customer.getContactCusts());
			//sheida commented	customerService.deleteContactCustAssociations(customer.getCustCode());
			customer.setContactCusts(contactCustSet);
		}

		if (contractCustItems != null )
		{
			Set contractCustSet = new HashSet();
			for(int i=0; i< contractCustItems.length; i++)
			{
				ContractCust contractCust = contractCustItems[i];
				if(contractCust.getContract().getContractCode() != null && (!contractCust.getContract().getContractCode().trim().equals("")))
				{
					Contract existingContract = contractService.getContractByContractCode(contractCust.getContract().getContractCode());
					if(existingContract != null)
					{
						contractCust.getContractCustId().setCustCode(customer.getCustCode());
						contractCust.getContractCustId().setContractCode(contractCust.getContract().getContractCode());

						contractCust.setCustomer(customer);
						contractCustSet.add(contractCust);
						//customer.getContractCusts().add(contractCust);
					}
					else
					{
					      errors.reject("invalid.contractcode.error", new Object[] {contractCust.getContract().getContractCode()}, null);
					      return showForm(request, response, errors);
					}
				}
			}
			customer.setContractCusts(contractCustSet);
		}
		
		if (custVatRegistrationItems != null )
		{
			boolean homeCountryInd = false;
			Set custVatRegistrationSet = new HashSet();
			if(custVatRegistrationItems.length > 0)
			{
				for(int i=0; i< custVatRegistrationItems.length; i++)
				{
					CustVatRegistration custVatRegistration = custVatRegistrationItems[i];
					if(homeCountryInd && custVatRegistration.getHomeCountryInd())
					{
					      errors.reject("country.vatregistration.error", new Object[] {}, null);
					      addCustomer.setTabName("4");
					      return showForm(request, response, errors);
					}
					if(!homeCountryInd && custVatRegistration.getHomeCountryInd())
						homeCountryInd = custVatRegistration.getHomeCountryInd();
				}
				if(!homeCountryInd)
				{
				      errors.reject("country.vatregistration.required.error", new Object[] {}, null);
				      addCustomer.setTabName("4");
				      return showForm(request, response, errors);
				}
			}
			
			
			for(int i=0; i< custVatRegistrationItems.length; i++)
			{
				CustVatRegistration custVatRegistration = custVatRegistrationItems[i];
				if(custVatRegistration.getCustVatRegistrationId().getCountryCode() == null 
						|| custVatRegistration.getCustVatRegistrationId().getCountryCode().trim().equals(""))
				{
				      errors.reject("customer.vatregistration.country.required.error", new Object[] {i+1}, null);
				      addCustomer.setTabName("4");
				      return showForm(request, response, errors);
				}
				custVatRegistration.getCustVatRegistrationId().setCustCode(customer.getCustCode());	
				custVatRegistration.setCustomer(customer);
				custVatRegistrationSet.add(custVatRegistration);
				//customer.getCustVatRegistrations().add(custVatRegistration);

			}
			customer.getCustVatRegistrations().clear();
			customer.setCustVatRegistrations(custVatRegistrationSet);
		}		
		
		/*-----------------------------Cust notes -------------------------------------------*/
		if (addCustomer.getCustomerNotesList() != null )
		{	//Set custNoteSet = new HashSet();
			for(int i=0; i< addCustomer.getCustomerNotesList().size(); i++)
			{
				Notes custNote = addCustomer.getCustomerNotesList().get(i);
				custNote.setNoteCode(customer.getCustCode());
				custNote.setNoteType("CUSTOMER");
			}
			//customer.setCustNotes(custNoteSet);
		}		
		/*-----------------------------Cust notes Ends-------------------------------------------*/

		AddCustomerAddress[] addCustomerAddressItems = copyCustomerAddresses(addCustomer.getCustomerAddresses(),addCustomer.getAddCustomerAddresses(),addCustomer.getCustomerAddresses().getPagination());
   //AddCustomerAddress[] addCustomerAddressItems = addCustomer.getCustomerAddresses().getAddCustomerAddresses();

   		if (addCustomerAddressItems != null )
		{
			customer.setPrimarySoldToAddress(0);
			customer.setPrimaryBillToAddress(0);
			
			Set custAddrSeqSet = new HashSet();
			for(int i=0; i< addCustomerAddressItems.length; i++)
			{
				CustAddrSeq custAddrSeq = addCustomerAddressItems[i].getCustAddrSeq();
				if(custAddrSeq.getCustAddrSeqId().getLocationNumber() != null && custAddrSeq.getCustAddrSeqId().getLocationNumber() > 0)
				{
	
					if (addCustomerAddressItems[i].getPrimarySoldToAddr() != null && Integer.parseInt(addCustomerAddressItems[i].getPrimarySoldToAddr()) > 0 
						&& customer.getPrimarySoldToAddress() == 0 )
					{
						customer.setPrimarySoldToAddress(custAddrSeq.getCustAddrSeqId().getLocationNumber());
					}
					else if (addCustomerAddressItems[i].getPrimarySoldToAddr() != null && Integer.parseInt(addCustomerAddressItems[i].getPrimarySoldToAddr()) > 0 
						&& customer.getPrimarySoldToAddress() > 0 )
					{
						errors.reject("primary.sold.address.error", new Object[] {}, null);
						return showForm(request, response, errors);
					}
	
					if (addCustomerAddressItems[i].getPrimaryBillToAddr() != null && Integer.parseInt(addCustomerAddressItems[i].getPrimaryBillToAddr()) > 0 
						&& customer.getPrimaryBillToAddress() == 0 )
					{
						customer.setPrimaryBillToAddress(custAddrSeq.getCustAddrSeqId().getLocationNumber());
					}
					else if (addCustomerAddressItems[i].getPrimaryBillToAddr() != null && Integer.parseInt(addCustomerAddressItems[i].getPrimaryBillToAddr()) > 0 
						&& customer.getPrimaryBillToAddress() > 0 )
					{
						errors.reject("primary.bill.address.error", new Object[] {}, null);
						return showForm(request, response, errors);
					}
					custAddrSeq.setCustomer(customer);
					CustAddress[] custAddressItems = addCustomerAddressItems[i].getCustAddresses();
					custAddrSeq.getCustAddresses().clear();
					
					//new code added for the 68988
					int count=0;
					int countnew=0;
					int lengcustadd=custAddressItems.length;
					
					for(int k=0; k< custAddressItems.length; k++)
					{
						CustAddress custAddress = custAddressItems[k];	
						if(addCustomerAddressItems[i].getPrimaryBillToAddr() != null && Integer.parseInt(addCustomerAddressItems[i].getPrimaryBillToAddr()) > 0 
								&&custAddressItems[k].getEffStatus().equals(Constants.STATUS_I))					
							 {
								count++;							   
							 }
								
							if(addCustomerAddressItems[i].getPrimarySoldToAddr() != null && Integer.parseInt(addCustomerAddressItems[i].getPrimarySoldToAddr()) > 0 
									&&custAddressItems[k].getEffStatus().equals(Constants.STATUS_I))					
							{									
								countnew++;								
							}	
						
					}
					 if(count==lengcustadd)
					 {errors.reject("primary.bill.status.error", new Object[] {}, null);
					  return showForm(request, response, errors);}
					 if(countnew==lengcustadd)
					 {
						 errors.reject("primary.sold.status.error", new Object[] {}, null);
						   return showForm(request, response, errors); 
					 }
					 
					 //up to here
					 CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");					 
					for(int j=0; j< custAddressItems.length; j++)
					{
						CustAddress custAddress = custAddressItems[j];
						Country country = countryService.getCountryByCode(custAddress.getCountry().trim());
						if(country.getStateAvail() && country.getStateRequiredInAddress()){
							if(custAddress.getState().trim().equals("")){
								errors.reject("state.required.error", new Object[] { custAddress.getCountry()}, null);
								return showForm(request, response, errors);
							}					
						}
						
						if(custAddress.getCountry().trim().equalsIgnoreCase("USA")||custAddress.getCountry().trim().equalsIgnoreCase("CAN"))
					 {
					    if(custAddress.getAddress1().trim().equals("")&& custAddress.getCity().trim().equals("") && custAddress.getState().trim().equals(""))
						{
						errors.reject("address.city.state.required.error", new Object[] { custAddress.getCountry()}, null);
						return showForm(request, response, errors);
						}


						if(custAddress.getAddress1().trim().equals(""))
						{
						errors.reject("address.required.error", new Object[] { custAddress.getCountry()}, null);
						return showForm(request, response, errors);
						}

						if(custAddress.getCity().trim().equals(""))
						{
						errors.reject("city.required.error", new Object[] { custAddress.getCountry()}, null);
						return showForm(request, response, errors);
						}

						//if(custAddress.getState().trim().equals(""))
						//{
						//errors.reject("state.required.error", new Object[] { custAddress.getCountry()}, null);
						//return showForm(request, response, errors);
						//}					

					}
                    
					//else if(custAddress.getCountry().trim().equalsIgnoreCase("NLD")||custAddress.getCountry().trim().equalsIgnoreCase("BEL"))
					else if(custAddress.getCountry().trim().equalsIgnoreCase("NLD"))
					{
						// Commented due to itrack issue 108056
						//if( custAddress.getAddress1().trim().equals("")&& custAddress.getCity().trim().equals("") && custAddress.getAddress2().trim().equals(""))
						if( custAddress.getAddress1().trim().equals("")&& custAddress.getCity().trim().equals(""))
						{
							//errors.reject("address1.address2.city.required.error", new Object[] { custAddress.getCountry()}, null);
							errors.reject("address1.city.required.error", new Object[] {custAddress.getCountry()}, null);
							return showForm(request, response, errors);
						}

						if(custAddress.getAddress1().trim().equals(""))
						{
						errors.reject("address.required.error", new Object[] {custAddress.getCountry()}, null);
						return showForm(request, response, errors);
						}

						if(custAddress.getCity().trim().equals(""))
						{
						errors.reject("city.required.error", new Object[] { custAddress.getCountry()}, null);
						return showForm(request, response, errors);
						}
						// Commented due to itrack issue 108056
						/*if(custAddress.getAddress2().trim().equals(""))
						{
						errors.reject("address2.required.error", new Object[] { custAddress.getCountry()}, null);
						return showForm(request, response, errors);
						}*/

					}

                    else 
					{
						if(custAddress.getAddress1().trim().equals("")&&  custAddress.getCity().trim().equals(""))
						{
						errors.reject("address.city.required.error", new Object[] { custAddress.getCountry()}, null);
						return showForm(request, response, errors);
						}

						if(custAddress.getAddress1().trim().equals(""))
						{
						errors.reject("address.required.error", new Object[] { custAddress.getCountry()}, null);
						return showForm(request, response, errors);
						}

						if(custAddress.getCity().trim().equals(""))
						{
						errors.reject("city.required.error", new Object[] { custAddress.getCountry()}, null);
						return showForm(request, response, errors);
						}			                       
				    }
						custAddress.setCustAddrSeq(custAddrSeq);
						custAddrSeq.getCustAddresses().add(custAddress);
					}
	                
					custAddrSeqSet.add(custAddrSeq);
				}
				else
				{
				      errors.reject("invalid.location.number.error", new Object[] {custAddrSeq.getCustAddrSeqId().getLocationNumber()}, null);
				      return showForm(request, response, errors);
				}
			}
			customer.setCustAddrSeqs(custAddrSeqSet);
		}

   	 if(customerLanguage != null)
     {
   	  System.out.println("Setting IsMultilingual as true");
   	  customer.setIsMultilingual(true);
     }

    	customer.setStatusDate(new Date());
    	if(customer.getPrimaryBillToAddress() == 0 || customer.getPrimarySoldToAddress() == 0)
    	{
    		errors.reject("primarybill.primarysold.address.required.error", new Object[] {}, null);
    		addCustomer.setTabName("1");
    	    return showForm(request, response, errors);
    	}    	

if(customer.getInterunitInd()!=null&&customer.getInterunitInd()==true)
 {
  if(customer.getInterunitBusUnitName()==null || customer.getInterunitBusUnitName().trim().equals(""))
	 {
         errors.reject("bu.require.customer.error", new Object[] { customer.getName()}, null);
	     return showForm(request, response, errors);
	   }
 }
	//If customer Location Type is 04 (HQRT) It can not have any contacts
	if(customer.getLocationType() != null && customer.getLocationType().equals(Constants.HQRT) && customer.getContactCusts()!=null && !customer.getContactCusts().isEmpty()){
			errors.reject("customer.hq.contact.error" , new Object[] {customer.getCustCode()}, null);
			return showForm(request, response, errors);
	}
	String customerName = request.getParameter("customer.name");
	if(customerName==null || customerName.trim().equals(""))
	{
	    errors.reject("name.require.customer.error", null, null);
	    return showForm(request, response, errors);
	}
	System.out.println("customerName"+customerName);
	customer.setName(customerName);
	String parentCustomerName = addCustomer.getParentCompanyName();
	//user modified details
	customer.setModByUserName(user.getLoginName());
	customer.setUpdatedTime(new Date());
	/*For iTrack#135193-START */
	  if(customer != null && customer.getParentCustomer()!= null){
		  
	      if(!customer.getName().trim().equalsIgnoreCase(customer.getParentCustomer().getName().trim())){
	    	  customer.setIsChildCustomer(true);
	      }
	  }
	  /*For iTrack#135193-END */
    customerService.saveCustomer(customer,parentCustomerName);
    notesService.UpdateDeleteNote(addCustomer.getCustomerNotesList(), addCustomer.getDelNotesList());
    } catch(ServiceException e)
	  {
	      e.printStackTrace();
	      errors.reject(e.getMessage(), e.getParams(), null);
	      return showForm(request, response, errors);
	  } catch(Throwable t)
	  {
	      t.printStackTrace();
	      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
	      return showForm(request, response, errors);
	  }
	  if(customerLanguage != null)
	  {
	  	customerLanguage.getCustomerLanguageId().setCustCode(customer.getCustCode());
	  	//customerLanguage.setName(customer.getName());
	  	try
	      {
	      	customerLanguage = customerService.addCustomerLanguage(customerLanguage);
	      }
	      catch(ServiceException e)
	  	{
	  	e.printStackTrace();
	  	errors.reject(e.getMessage(), e.getParams(), null);
	  	return showForm(request, response, errors);
	  	}
	  	catch(Throwable t)
	  	{
	  	t.printStackTrace();
	  	errors.reject("generic.error", new Object[] {t.getMessage()}, null);
	  	return showForm(request, response, errors);
	  	}
	  }
	     
	  
	  if(custAddrLangmap != null)
	  {	 
		 List custAddrSeqs = customerService.getCustAddrSeqsByCustCode(customer.getCustCode());
		 AddCustomerAddress[] addCustomerAddresses = new AddCustomerAddress[custAddrSeqs.size()];
		 Map finalMultiLingual = new HashMap();
		 if(custAddrSeqs != null && custAddrSeqs.size() >0)
		 {			  
			   int l=0;
			   for(int j=0;j<custAddrSeqs.size();j++)// main custaddr loop
			   {
				CustAddrSeq custAddrSeq = (CustAddrSeq) custAddrSeqs.get(j);
			
				List addrDtlList = customerService.getCustAddressDetailsBySeqNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber(),custAddrSeq.getCustAddrSeqId().getCustCode()	);
						
				for(int i=0;i<addrDtlList.size();i++) // inner custaddr loop
			    {
				CustAddress custAddress = (CustAddress) addrDtlList.get(i);
				
					List mapKeys = new ArrayList(custAddrLangmap.keySet());	 
					for(int k=0;k<mapKeys.size();k++){
				    	
				    CustAddressLanguage custAddressLang =(CustAddressLanguage)custAddrLangmap.get(mapKeys.get(k));
				  
					long custAddrId=0;
					if((String.valueOf(mapKeys.get(k))).indexOf("_")!= -1)
			  		{
			  			custAddrId = 0;
			  			String uiIndex =String.valueOf(l)+String.valueOf(i)+"_";
			  			String mapIndex =String.valueOf(mapKeys.get(k));
			  			if(uiIndex.trim().equals(mapIndex))
			  			{
			  				finalMultiLingual.put(custAddress.getId(),custAddressLang);
			  				custAddrLangmap.remove(mapIndex);
			  				custAddrLangmap.put(custAddress.getId(), custAddressLang);
			  				
			  			}
			  		} else if(custAddress.getId() == (Long)mapKeys.get(k))
			  		{
			  			finalMultiLingual.put(custAddress.getId(),custAddressLang);		
			  		}
				  }
			
			    }// End inner custaddr loop
				l++;
			 }//End main custaddr loop
		   }
		    List mapKeys = new ArrayList(finalMultiLingual.keySet());	 
		    for(int x=0;x<mapKeys.size();x++){		    			    	
		    		CustAddressLanguage custAddressLang =(CustAddressLanguage)finalMultiLingual.get(mapKeys.get(x));
		    		custAddressLang.getCustAddressLanguageId().setCustAddressId((Long)mapKeys.get(x));
					 try
				        {
				    	  custAddressLang = customerService.addCustomerAddrLanguage(custAddressLang);
				    	}
				        catch(ServiceException e)
					  	{
					  	e.printStackTrace();
					  	errors.reject(e.getMessage(), e.getParams(), null);
					  	return showForm(request, response, errors);
					  	}
					  	catch(Throwable t)
					  	{
					  	t.printStackTrace();
					  	errors.reject("generic.error", new Object[] {t.getMessage()}, null);
					  	return showForm(request, response, errors);					 
				       }
		      }
		 
		      if(session != null)
			  {
				  CustomerLanguage customerLang = (CustomerLanguage)session.getAttribute("customermultilingual");
				  if(customerLang != null)
					  session.setAttribute("customermultilingual",null);
				  Map custAddressLanguage =(Map) session.getAttribute("customerAddressMultilingual");
				  if(custAddressLanguage != null)
					  session.setAttribute("customerAddressMultilingual",null);
			  }
	     }
	
    ModelAndView modelAndView = new ModelAndView("common-message-r");
  //  modelAndView.addObject("backUrl", "edit_customer.htm?customer.custCode=" + customer.getCustCode());
    modelAndView.addObject("backUrl", "edit_customer.htm?customer.custCode=" + customer.getCustCode()+"&tabname="+addCustomer.getTabName());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this customer.");
    modelAndView.addObject("description", "Your customer has been successfully updated.");

    return modelAndView;
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    NumberFormat nf = NumberFormat.getInstance();
    CustomPrimitiveNumberEditor doubleEditor = new CustomPrimitiveNumberEditor(Double.class, nf, new Double(0));
    binder.registerCustomEditor(Double.class, "customer.creditLimit", doubleEditor);
    CustomPrimitiveNumberEditor integerEditor = new CustomPrimitiveNumberEditor(Integer.class, nf, Integer.valueOf(0));
    binder.registerCustomEditor(Integer.class, "customer.custAddrSeq.custAddrSeqId.locationNumber", integerEditor);
    binder.registerCustomEditor(Integer.class, "customer.contactCust.contactCustId.locationNumber", integerEditor);
    CustomPrimitiveNumberEditor longEditor = new CustomPrimitiveNumberEditor(Long.class, nf,Long.valueOf(0));
    binder.registerCustomEditor(Long.class, "customer.contactCust.contact.id", longEditor);
  }

   protected boolean suppressValidation(HttpServletRequest request,Object command)
  {
    String addOrDelete = request.getParameter("addOrDeleteCustContact");
    String addOrDeleteAddrSeq = request.getParameter("addOrDeleteCustAddrSeq");
   	String addOrDeleteCustAddress = request.getParameter("addOrDeleteCustAddrDtl");
   	String addOrDeleteCustVatReg = request.getParameter("addOrDeleteCustVatReg");
String addOrDeleteCustNote = request.getParameter("addOrDeleteCustNote");   	if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      return true;
    }
    if((addOrDeleteAddrSeq != null) && ("addAddr".equals(addOrDeleteAddrSeq) || "deleteAddr".equals(addOrDeleteAddrSeq)))
    {
      return true;
    }
    if((addOrDeleteCustAddress != null) && ("addAddrDtls".equals(addOrDeleteCustAddress) || "deleteAddrDtls".equals(addOrDeleteCustAddress)))
    {
      return true;
    }
   String addOrDeleteCustContract = request.getParameter("addOrDeleteCustContract");

    if((addOrDeleteCustContract != null) && ("add".equals(addOrDeleteCustContract) || "delete".equals(addOrDeleteCustContract)))
    {
      return true;
    }
    if((addOrDeleteCustVatReg != null) && ("add".equals(addOrDeleteCustVatReg) || "delete".equals(addOrDeleteCustVatReg)))
    {
      return true;
    }
    if((addOrDeleteCustNote != null) && ("add".equals(addOrDeleteCustNote)||"view".equals(addOrDeleteCustNote) || "delete".equals(addOrDeleteCustNote)||"reset".equals(addOrDeleteCustNote)))
    {
      return true;
    }
   
	 String addSeqFlag=request.getParameter("addSeqFlag");

    if((addSeqFlag != null) && ("newval".equals(addSeqFlag)))
    {
      return true;
    }
    String contactFlag=request.getParameter("contactFlag");
	//if((contactFlag != null) && ("contactFlag".equals(contactFlag))||()
	if((contactFlag != null) && (("newval".equals(contactFlag))||("newcontactval".equals(contactFlag))))
    {
      return true;
    }
    String contractFlag=request.getParameter("contractFlag");
	
	if((contractFlag!=null) && ("contractFlag".equals(contractFlag)))
	{
	return true;
	}

    return super.suppressValidation(request);
  }

   protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        BindException errors = getErrorsForNewForm(request);
        errors.reject("create.customer.error", "Duplicate form submission");
        return showForm(request, response, errors);
    }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	  // Setting session multilingual objects null
	  String reqFrom = request.getParameter("reqFrom");
	  if(reqFrom != null && (reqFrom.trim().equals("searchCustomerForm")||reqFrom.trim().equals("quickCustomerForm")||reqFrom.trim().equals("createCustomerForm")))
	  {
		  HttpSession session = request.getSession();
		  if(session != null)
		  {
		  CustomerLanguage customerLanguage = (CustomerLanguage)session.getAttribute("customermultilingual");
		  if(customerLanguage != null)
			  session.setAttribute("customermultilingual",null);
		  Map custAddressLanguage =(Map) session.getAttribute("customerAddressMultilingual");
		  if(custAddressLanguage != null)
			  session.setAttribute("customerAddressMultilingual",null);
		  }
	  }
	  //end
	AddCustomer addCustomer = new AddCustomer();
	Customer customer = new Customer();
	 Customer parentCustomer = new Customer();

   BusinessUnit businessUnit   = new BusinessUnit();
    User user= new User();

	 String countStr = request.getParameter("custContactsCount");
	String countAddrSeqStr = request.getParameter("custAddrSeqCount");

	try
    {
		  if( countStr == null || countStr.trim().equals("") )
		  countStr = "0";

		int count = 0;
		try
		{
			count = new Integer(countStr).intValue();
		}
		catch(Exception e) { }

	  if( countAddrSeqStr == null || countAddrSeqStr.trim().equals("") )
		  countAddrSeqStr = "0";

		int countAddrSeq = 0;
		try
		{
			countAddrSeq = Integer.valueOf(countAddrSeqStr).intValue();
		}
		catch(Exception e) { }
    String custCode = request.getParameter("customer.custCode");
	String tabname=request.getParameter("tabname"); 
	// For iTrack #103082 START
	if(reqFrom != null && reqFrom.equals("contractCustTab")){
		  addCustomer.setNotesViewOnly(true);
	} else {
		addCustomer.setNotesViewOnly(false);
	}
   // For iTrack #103082 END
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");

    try
    {
    	customer = customerService.loadCustomerByCustCode(custCode);

    	//START: 135193 - To get Service Location and Date & save it
    	List<JobContract> lstServLocInfo = customerService.getServiceLocationInfo(custCode);
    	if(null != lstServLocInfo && lstServLocInfo.size() > 0){
    		JobContract frstJobContract = (JobContract)lstServLocInfo.get(0);
    		customer.setLastServiceDate(frstJobContract.getJobOrder().getJobFinishDate());
    		customer.setLastServiceLocation(frstJobContract.getJobOrder().getBuName());
    		customerService.updateServiceLocationInfo(customer);
    	}
    	//END: 135193 - To get Service Location and Date & save it 
    	
	  if(customer.getInterunitBusUnit() == null)
		   customer.setInterunitBusUnit(businessUnit);
	//START: 135193 - To get Service Location and Date & save it
//	  if(customer.getAccountOwner() == null)
//			customer.setAccountOwner(user);
	//END: 135193 - To get Service Location and Date & save it	  
	  if(customer.getParentCustomer() == null)
	  {
		customer.setParentCustomer(parentCustomer);
	  }
	  addCustomer.setParentCompanyName(customer.getParentCustomer().getName());
	 /* if(customer.getPaymentType() == null)
		  customer.setPaymentType("CRE");*/

	 /* if(customer.getCreditApproved()== null)
		  customer.setCreditApproved(true);*/
	  Set custAddrSeqSet = new HashSet();
	  int pageNumber = 1;
	  Pagination pagination = new Pagination(1, 10, pageNumber,10);	  
	  CustomerAddresses customerAddresses = new CustomerAddresses();
	  customerAddresses.setPagination(pagination);  
	      
	  
	  List custAddrSeqs = customerService.getCustAddrSeqsByCustCode(customer.getCustCode());
	    
	  if(custAddrSeqs != null && custAddrSeqs.size() >0)
		//if(customer.getCustAddrSeqs() != null )
		{
			//custAddrSeqSet = customer.getCustAddrSeqs();

		//addCustomer.setCustAddrSeqCount(custAddrSeqSet.size());
		addCustomer.setCustAddrSeqCount(custAddrSeqs.size());
		
		//AddCustomerAddress[] addCustomerAddresses = new AddCustomerAddress[custAddrSeqSet.size()];
		AddCustomerAddress[] addCustomerAddresses = new AddCustomerAddress[custAddrSeqs.size()];
		
		
		int addrSeqCount = 0;
		
		for(int j=0;j<custAddrSeqs.size();j++)
	  {
			CustAddrSeq custAddrSeq = (CustAddrSeq) custAddrSeqs.get(j);
			AddCustomerAddress addCustomerAddress = new AddCustomerAddress();
			addCustomerAddress.setCustAddrSeq(custAddrSeq);
			custAddrSeqSet.add(custAddrSeq);
			if (custAddrSeq.getCustAddrSeqId().getLocationNumber() == customer.getPrimaryBillToAddress())
			{
				addCustomerAddress.setPrimaryBillToAddr("1");
			}
			if (custAddrSeq.getCustAddrSeqId().getLocationNumber() == customer.getPrimarySoldToAddress())
			{
				addCustomerAddress.setPrimarySoldToAddr("1");
			}

			Set custAddressesSet = new HashSet();
			List addrDtlList = customerService.getCustAddressDetailsBySeqNumber(
				custAddrSeq.getCustAddrSeqId().getLocationNumber(),
				custAddrSeq.getCustAddrSeqId().getCustCode()
			);
			if(addrDtlList != null)
			{
								CustAddress[] customerAddrs = new CustAddress[addrDtlList.size()];

		if(addrDtlList.size() > 0)
		addCustomerAddress.setCustAddressCount(addrDtlList.size());

		for(int i=0;i<addrDtlList.size();i++)
		  {
			CustAddress custAddress = (CustAddress) addrDtlList.get(i);
			customerAddrs[i] = custAddress;
			custAddressesSet.add(custAddress);

		  }
		custAddrSeq.setCustAddresses(custAddressesSet);
			addCustomerAddress.setCustAddresses(customerAddrs);
		}
			//sort addresses in the array based on location number
			int locationNumber = custAddrSeq.getCustAddrSeqId().getLocationNumber();
			 addCustomerAddresses[addrSeqCount] = addCustomerAddress;
			 //addCustomerAddresses[locationNumber - 1] = addCustomerAddress;
			addrSeqCount++;

	  }
		//customerAddresses.setAddCustomerAddresses(addCustomerAddresses);
		addCustomer.setCustomerAddresses(populateCustomerAddresses(customerAddresses,addCustomerAddresses,pagination));
	    addCustomer.setAddCustomerAddresses(addCustomerAddresses);
	}
	  customer.setCustAddrSeqs(custAddrSeqSet);
		Set custContactsSet = new HashSet();
		List custContacts = customerService.getContactCustsByCustCode(customer.getCustCode());
		
		  Pagination contactPagination = new Pagination(1, 10, pageNumber,10);	  
		  CustomerContacts customerContacts = new CustomerContacts();
		  customerContacts.setPagination(contactPagination);
		  
		if(custContacts != null && custContacts.size() > 0)
		//if(customer.getContactCusts() != null )
		{
			//custContactsSet = customer.getContactCusts();

		//addCustomer.setContactCustCount(custContactsSet.size());
		addCustomer.setContactCustCount(custContacts.size());
		
		//Iterator iterContact = custContactsSet.iterator();
		int custCount = 0;
		ContactCust[] contactCusts = new ContactCust[custContacts.size()];

		for(int k=0;k<custContacts.size();k++)
	  {

			//ContactCust contactCust = (ContactCust) iterContact.next();
			ContactCust contactCust = (ContactCust) custContacts.get(k);
			contactCust.getContact().setFirstName(contactCust.getContact().getFirstName());
			contactCust.getContact().setLastName(contactCust.getContact().getLastName());
			contactCusts[k] = contactCust;
			custContactsSet.add(contactCust);
			custCount++;
	  }
		addCustomer.setCustomerContacts(populateCustomerContacts(customerContacts,contactCusts,contactPagination));
	  addCustomer.setContactCusts(contactCusts);
		}
		customer.setContactCusts(custContactsSet);
		Set custContractsSet = new HashSet();
		List custContracts = customerService.getContractCustsByCustCode(customer.getCustCode());
		//if(customer.getContractCusts() != null )
		if(custContracts != null && custContracts.size() > 0)
		{
			//custContractsSet = customer.getContractCusts();

		//addCustomer.setContractCustCount(custContractsSet.size());
		addCustomer.setContractCustCount(custContracts.size());
		
		//Iterator iterContract = custContractsSet.iterator();
		int conCount = 0;
		ContractCust[] contractCusts = new ContractCust[custContracts.size()];

		for(int l=0;l<custContracts.size();l++)
	  {

			ContractCust contractCust = (ContractCust) custContracts.get(l);
			contractCusts[l] = contractCust;
			custContractsSet.add(contractCust);
			conCount++;
	  }
		addCustomer.setContractCusts(contractCusts);
		}
		customer.setContractCusts(custContractsSet);

		Set custVatRegSet = new HashSet();
		
		List custVatRegs = customerService.getCustomerVatRegistrationsByCustCode(customer.getCustCode());
		
		
		if(custVatRegs != null && custVatRegs.size() > 0)
		{
	
		addCustomer.setCustVatRegCount(custVatRegs.size());
		
		
		int regCount = 0;
		CustVatRegistration[] custVatRegistrations = new CustVatRegistration[custVatRegs.size()];

		for(int l=0;l<custVatRegs.size();l++)
	  {

			CustVatRegistration custVatRegistration = (CustVatRegistration) custVatRegs.get(l);
			custVatRegistrations[l] = custVatRegistration;
			custVatRegSet.add(custVatRegistration);
			regCount++;
	  }
		addCustomer.setCustVatRegistrations(custVatRegistrations);
		}
		customer.setCustVatRegistrations(custVatRegSet);
		//Setting customer notes
		List custNotesList = notesService.getNotes(customer.getCustCode(),"CUSTOMER");
		addCustomer.setCustNoteEditFlag(-1);
		if(custNotesList!= null && custNotesList.size()>0)
		{
			addCustomer.setCustomerNotesList(custNotesList);
			addCustomer.setCustNoteCount(custNotesList.size());
		}
		addCustomer.setCustAddBy(customer.getCreByUserName());
		addCustomer.setCustModiBy(customer.getModByUserName());
		SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		if(customer.getUpdatedTime()!=null)
		{
			addCustomer.setCustModiDateTime(df.format(customer.getUpdatedTime()));
		}
		if(customer.getCreatedTime()!=null)
		{
			addCustomer.setCustAddDateTime(df.format(customer.getCreatedTime()));
		}
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
	 addCustomer.setCustomer(customer);
	if(tabname!=null)
	  {
	addCustomer.setTabName(tabname);
	  }
	  else
	  {
     addCustomer.setTabName("0");
	  }
	  addCustomer.setVesselFlag("none");
    } catch(ServiceException e)
    {
    	throw new ServiceException(e.getMessage(), e.getParams(), e);
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return  addCustomer;
  }
  private CustomerContacts addContactCust(CustomerContacts customerContacts,AddCustomer addCustomer,int index)
  //private CustomerContacts addContactCust(CustomerContacts customerContacts,AddCustomer addCustomer)
  {
		ContactCust contactCust = new ContactCust();
		ContactCustId contactCustId = new ContactCustId();
		contactCust.setContactCustId(contactCustId);
       contactCust.setContactSeqNum(index+1);

		Customer customer = new Customer();
		CustAddrSeq custAddrSeq = new CustAddrSeq();
		Contact contact = new Contact();

		contactCust.setCustomer(customer);
		contactCust.setCustAddrSeq(custAddrSeq);
		contactCust.setContact(contact);

		ContactCust[] results = null;
		ContactCust[] resultsMain = null;
		
		if(customerContacts == null || customerContacts.getContactCusts() == null)
		{
			if(customerContacts == null)
			{
				CustomerContacts customerContactsNew = new CustomerContacts();
				customerContacts = customerContactsNew;
			}
			results = new ContactCust[1];
		}
		else results = new ContactCust[customerContacts.getContactCusts().length + 1];
		
		if(customerContacts.getContactCusts() != null)
		{
			for(int i=0; i<customerContacts.getContactCusts().length; i++)
			{
				results[i] = customerContacts.getContactCusts()[i];
			}
		}
		results[results.length - 1] = contactCust;
		
		if(addCustomer.getContactCusts() == null) resultsMain = new ContactCust[1];
		else resultsMain = new ContactCust[addCustomer.getContactCusts().length + 1];

		if(addCustomer.getContactCusts() != null)
	  {

			for(int i=0; i<addCustomer.getContactCusts().length; i++)
			{

				resultsMain[i] = addCustomer.getContactCusts()[i];

			}
	  }
		//addCustomerAddress.getCustAddrSeq().getCustAddrSeqId().setLocationNumber(results.length);
       
	   
	   
		resultsMain[resultsMain.length - 1] = contactCust;
		
		addCustomer.setContactCusts(resultsMain);
		
		customerContacts.setContactCusts(results);
		

		return customerContacts;
	}


   private AddCustomerAddress[] addCustomerAddress(AddCustomerAddress[] addCustomerAddresses,User user,AddCustomer addCustomer)
  {
		AddCustomerAddress addCustomerAddress = new AddCustomerAddress();
		int nextCustAddrSeq = 0;
		CustAddrSeq custAddrSeq = new CustAddrSeq();
		CustAddrSeqId custAddrSeqId = new CustAddrSeqId();
		custAddrSeq.setCustAddrSeqId(custAddrSeqId);
		custAddrSeq.setSoldTo(true);
		custAddrSeq.setBillTo(true);
		custAddrSeq.setShipTo(false);
        custAddrSeq.setCorresponence(true);
		CustAddress[] custAddresses = new CustAddress[1];
		custAddresses[0] = new CustAddress();


		addCustomerAddress.setCustAddrSeq(custAddrSeq);
		    int intYear = 103;
			int intMon = 0;
			int intDate = 1;
              Date effDate = new Date(intYear,intMon,intDate);
             if(custAddresses[0].getEffDate()==null)
		     custAddresses[0].setEffDate(effDate);
             if( custAddresses[0].getCountry()==null)
			custAddresses[0].setCountry(user.getCountryCode());
		  custAddresses[0].setInCityLimit(true);


		addCustomerAddress.setCustAddresses(custAddresses);


		AddCustomerAddress[] results = null;
		AddCustomerAddress[] resultsMain = null;
		if(addCustomerAddresses == null) results = new AddCustomerAddress[1];
		else results = new AddCustomerAddress[addCustomerAddresses.length + 1];
		
		if(addCustomer.getAddCustomerAddresses() != null)
	  {

			for(int i=0; i<addCustomer.getAddCustomerAddresses().length; i++)
			{
				if(addCustomer.getAddCustomerAddresses()[i].getCustAddrSeq().getCustAddrSeqId().getLocationNumber() > nextCustAddrSeq)
					nextCustAddrSeq = addCustomer.getAddCustomerAddresses()[i].getCustAddrSeq().getCustAddrSeqId().getLocationNumber();
			}
	  }

		if(addCustomerAddresses != null)
	  {

			for(int i=0; i<addCustomerAddresses.length; i++)
			{

				results[i] = addCustomerAddresses[i];
				

			}
	  }
		//addCustomerAddress.getCustAddrSeq().getCustAddrSeqId().setLocationNumber(results.length);
		addCustomerAddress.getCustAddrSeq().getCustAddrSeqId().setLocationNumber(nextCustAddrSeq + 1);
		results[results.length - 1] = addCustomerAddress;

		
		if(addCustomer.getAddCustomerAddresses() == null) resultsMain = new AddCustomerAddress[1];
		else resultsMain = new AddCustomerAddress[addCustomer.getAddCustomerAddresses().length + 1];

		if(addCustomer.getAddCustomerAddresses() != null)
	  {

			for(int i=0; i<addCustomer.getAddCustomerAddresses().length; i++)
			{

				resultsMain[i] = addCustomer.getAddCustomerAddresses()[i];

				//resultsMain[i].getCustAddrSeq().getCustAddrSeqId().setLocationNumber(Integer.valueOf(i+1));

			}
	  }
		//addCustomerAddress.getCustAddrSeq().getCustAddrSeqId().setLocationNumber(results.length);
		
		resultsMain[resultsMain.length - 1] = addCustomerAddress;
		
		addCustomer.setAddCustomerAddresses(resultsMain);
		
		return results;
	}

  private AddCustomerAddress[] deleteCustomerAddress(AddCustomerAddress[] addCustomerAddresses, int index,AddCustomer addCustomer)
  {
		if(addCustomerAddresses == null) return null;
		if(addCustomerAddresses.length <= 0) return addCustomerAddresses;

		AddCustomerAddress[] results = new AddCustomerAddress[addCustomerAddresses.length - 1];

		int count = 0;
		AddCustomerAddress toDelete = new AddCustomerAddress();
		for(int i=0; i<addCustomerAddresses.length; i++)
		{
			if(i == index)
			{
				toDelete = addCustomerAddresses[i];
				continue;
			}
			results[count] = addCustomerAddresses[i];

			count ++;
		}
		
		AddCustomerAddress[] resultsMain = new AddCustomerAddress[addCustomer.getAddCustomerAddresses().length - 1];
		int countMain = 0;
		for(int i=0; i< addCustomer.getAddCustomerAddresses().length;i++)
		{
			if(addCustomer.getAddCustomerAddresses()[i].getCustAddrSeq().getCustAddrSeqId().getLocationNumber() == 
				toDelete.getCustAddrSeq().getCustAddrSeqId().getLocationNumber())
			{
				continue;
			}
			resultsMain[countMain] = addCustomer.getAddCustomerAddresses()[i];

			countMain ++;
		}
		addCustomer.setAddCustomerAddresses(resultsMain);
		return results;
	}

   private CustAddress[] addCustomerAddressDetails(CustAddress[] custAddresses,User user)
  {
		CustAddress custAddress = new CustAddress();

		CustAddrSeq custAddrSeq = new CustAddrSeq();
		custAddrSeq.setSoldTo(true);
		custAddrSeq.setBillTo(true);
		custAddrSeq.setShipTo(false);
		custAddrSeq.setCorresponence(true);
		int intYear = 103;
		int intMon = 0;
		int intDate = 1;
		Date effDate = new Date(intYear,intMon,intDate);
		if(custAddress.getEffDate()==null)
		custAddress.setEffDate(effDate);
      if(custAddress.getCountry()==null)
       custAddress.setCountry(user.getCountryCode());
	   custAddress.setInCityLimit(true);
		custAddress.setCustAddrSeq(custAddrSeq);



		CustAddress[] results = null;
		if(custAddresses == null) results = new CustAddress[1];
		else results = new CustAddress[custAddresses.length + 1];
		if(custAddresses != null)
		{
			for(int i=0; i<custAddresses.length; i++)
			{
				results[i] = custAddresses[i];
			}
		}
		results[results.length - 1] = custAddress;

		return results;
	}

  private CustAddress[] deleteCustomerAddressDetails(CustAddress[] custAddresses, int index)
  {
		if(custAddresses == null) return null;
		if(custAddresses.length <= 0) return custAddresses;

		CustAddress[] results = new CustAddress[custAddresses.length - 1];

		int count = 0;
		for(int i=0; i<custAddresses.length; i++)
		{
			if(i == index) continue;
			results[count] = custAddresses[i];

			count ++;
		}

		return results;
	}
 private ContractCust[] addContractCust(ContractCust[] contractCusts)
 {


		ContractCust contractCust = new ContractCust();
		ContractCustId contractCustId = new ContractCustId();
		contractCust.setContractCustId(contractCustId);
		Customer customer = new Customer();
		Contract contract = new Contract();

		contractCust.setCustomer(customer);

		contractCust.setContract(contract);

		ContractCust[] results = null;
		if(contractCusts == null) results = new ContractCust[1];
		else results = new ContractCust[contractCusts.length + 1];
		if(contractCusts != null)
		{
			for(int i=0; i<contractCusts.length; i++)
			{
				results[i] = contractCusts[i];
			}
		}
		results[results.length - 1] = contractCust;

		return results;
	}

  private ContractCust[] deleteContractCust(ContractCust[] contractCusts, int indexs)
  {
		if(contractCusts == null) return null;
		if(contractCusts.length <= 0) return contractCusts;

		ContractCust[] results = new ContractCust[contractCusts.length - 1];

		int count = 0;
		for(int i=0; i<contractCusts.length; i++)
		{
			if(i == indexs) continue;
			results[count] = contractCusts[i];

			count ++;
		}

		return results;
	}

private CustomerAddresses populateCustomerAddresses(CustomerAddresses customerAddresses,AddCustomerAddress[] addCustomerAddresses,Pagination pagination)
{
	if(pagination != null)
	{
		pagination.setTotalRecord(addCustomerAddresses.length);
		pagination.calculatePages();
		
		
		int startIndex = (pagination.getCurrentPageNum() - 1) * pagination.getNumInPage();
		int endIndex = startIndex + pagination.getNumInPage() - 1;
		if(pagination.getTotalRecord() == endIndex )
			endIndex = endIndex - 1;
		else if(pagination.getTotalRecord() < endIndex )
			endIndex = pagination.getTotalRecord() - 1 ;
		
		AddCustomerAddress[] groupAddCustomerAddrs = null;
		
		if(pagination.getTotalRecord() < pagination.getNumInPage())
		{
			groupAddCustomerAddrs = new AddCustomerAddress[pagination.getTotalRecord()];
		}
		else
		{
			groupAddCustomerAddrs = new AddCustomerAddress[endIndex - startIndex + 1];
		}
		
		for(int i=0;i<groupAddCustomerAddrs.length;i++)
		{
			groupAddCustomerAddrs[i] = addCustomerAddresses[startIndex];
			startIndex++;
		}
		customerAddresses.setAddCustomerAddresses(groupAddCustomerAddrs);
	}
	
	customerAddresses.setPagination(pagination);
	return customerAddresses;
}
private AddCustomerAddress[] copyCustomerAddresses(CustomerAddresses customerAddresses,AddCustomerAddress[] addCustomerAddresses,Pagination pagination)
{

		
		for(int i=0;i<customerAddresses.getAddCustomerAddresses().length;i++)
		{
			for(int j=0;j<addCustomerAddresses.length;j++)
			{
				if(addCustomerAddresses[j].getCustAddrSeq().getCustAddrSeqId().getLocationNumber() == 
					customerAddresses.getAddCustomerAddresses()[i].getCustAddrSeq().getCustAddrSeqId().getLocationNumber())
				{
					addCustomerAddresses[j] = customerAddresses.getAddCustomerAddresses()[i];
				}
			}
			
		}
		
		
		return addCustomerAddresses;
	
}

private CustomerContacts populateCustomerContacts(CustomerContacts customerContacts,ContactCust[] contactCusts,Pagination pagination)
{
	if(pagination != null)
	{
		pagination.setTotalRecord(contactCusts.length);
		pagination.calculatePages();
		
		
		int startIndex = (pagination.getCurrentPageNum() - 1) * pagination.getNumInPage();
		int endIndex = startIndex + pagination.getNumInPage() - 1;
		if(pagination.getTotalRecord() == endIndex )
			endIndex = endIndex - 1;
		else if(pagination.getTotalRecord() < endIndex )
			endIndex = pagination.getTotalRecord() - 1;
		
		ContactCust[] groupContactCusts = null;
		
		if(pagination.getTotalRecord() < pagination.getNumInPage())
		{
			groupContactCusts = new ContactCust[pagination.getTotalRecord()];
		}
		else
		{
			groupContactCusts = new ContactCust[endIndex - startIndex + 1];
		}
		
		for(int i=0;i<groupContactCusts.length;i++)
		{
			groupContactCusts[i] = contactCusts[startIndex];
			startIndex++;
		}
		customerContacts.setContactCusts(groupContactCusts);
	}
	
	customerContacts.setPagination(pagination);
	return customerContacts;
}
private ContactCust[] copyCustomerContacts(CustomerContacts customerContacts,ContactCust[] contactCusts,Pagination pagination)
{

		
		for(int i=0;i<customerContacts.getContactCusts().length;i++)
		{
			for(int j=0;j<contactCusts.length;j++)
			{
				if((contactCusts[j].getContact().getId() == 
					customerContacts.getContactCusts()[i].getContact().getId()) && (contactCusts[j].getContactCustId().getLocationNumber() ==
						customerContacts.getContactCusts()[i].getContactCustId().getLocationNumber()))
					
				{
					contactCusts[j] = customerContacts.getContactCusts()[i];
				}
			}
			
		}
		
		
		return contactCusts;
	
}

private CustVatRegistration[] addCustVatRegistration(CustVatRegistration[] custVatRegistrations)
{


	  	CustVatRegistration custVatRegistration = new CustVatRegistration();
	  	CustVatRegistrationId custVatRegistrationId = new CustVatRegistrationId();
	  	custVatRegistration.setCustVatRegistrationId(custVatRegistrationId);

	  	CustVatRegistration[] results = null;
		if(custVatRegistrations == null || custVatRegistrations.length <= 0)
		{
			results = new CustVatRegistration[1];
			custVatRegistration.setHomeCountryInd(true);
		}
		else results = new CustVatRegistration[custVatRegistrations.length + 1];
		
		
		if(custVatRegistrations != null)
		{
	 		for(int i=0; i<custVatRegistrations.length; i++)
	 		{
	 			results[i] = custVatRegistrations[i];
	 		}
		}

		results[results.length - 1] = custVatRegistration;

		return results;
	}

 private CustVatRegistration[] deleteCustVatRegistration(CustVatRegistration[] custVatRegistrations, int indexs)
 {
		if(custVatRegistrations == null) return null;
		if(custVatRegistrations.length <= 0) return custVatRegistrations;

		CustVatRegistration[] results = new CustVatRegistration[custVatRegistrations.length - 1];

		int count = 0;
		for(int i=0; i<custVatRegistrations.length; i++)
		{
			if(i == indexs) continue;
			results[count] = custVatRegistrations[i];

			count ++;
		}

		return results;
	}


}
