package com.intertek.web.controller.customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddContact;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContactCustId;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddrSeqId;
import com.intertek.entity.Customer;
import com.intertek.entity.Notes;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CustomerService;
import com.intertek.service.NotesService;
import com.intertek.service.UserService;
import com.intertek.util.CommonUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

//public class CreateContactFormController extends SimpleFormController
public class CreateContactFormController extends BaseSimpleFormController
{
  public CreateContactFormController() {
    super();
	setSessionForm(true);
    setCommandClass(AddContact.class);
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
    
	AddContact addContact = (AddContact)command;
	CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
	//For iTrack #103082-START
	NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");
	UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	String addOrDeleteContactNote = request.getParameter("addOrDeleteContactNote");
	//For iTrack #103082-END
	Contact contact = addContact.getContact();

       String addOrDelete = request.getParameter("addOrDelete");
	   String index = request.getParameter("index");
	   String page = addContact.getTabName();
		String contSeqFlag=request.getParameter("contSeqFlag");
		String addSeqFlag=request.getParameter("addSeqFlag");
		CommonUtil commonUtil=null;

		if("custval".equals(contSeqFlag))
		{
			try {
		String rowNo=request.getParameter("rowNum");			
		int num=Integer.parseInt(rowNo);
		ContactCust[] contactCustItems = addContact.getContactCusts();
		ContactCust contactCust=new ContactCust();
		Customer customer=new Customer();
		customer=customerService.getCustomerByCustCode(contactCustItems[num].getCustomer().getCustCode());

		List contacts=customerService.getContactSeqByCustCode(contactCustItems[num].getCustomer().getCustCode());
        // if(contacts.size()!=0)
		if(contacts != null && contacts.size() > 0)
		{
        contactCustItems[num].setContactSeqNum(contacts.size()+1);
		}
		else
		{
		 //contactCustItems[num].setContactSeqNum(contacts.size()+1);
		 contactCustItems[num].setContactSeqNum(1);
		}
		contactCustItems[num].setCustomer(customer);
		addContact.setContactCusts(contactCustItems);
		addContact.setContSeqFlag("none");
		addContact.setTabName("1");
		return showForm(request, response, errors);
		   } catch(ServiceException e)
		   {
			  addContact.setAddSeqFlag("none");
			  addContact.setTabName("1");
		      e.printStackTrace();
		      errors.reject(e.getMessage(), e.getParams(), null);
		      return showForm(request, response, errors);
		   } catch(Throwable t)
		   {
		      addContact.setAddSeqFlag("none");
			  addContact.setTabName("1");
		      t.printStackTrace();
		      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		      return showForm(request, response, errors);
		   }
		}	
		if("newval".equals(addSeqFlag))
		{
			try {
		String rowNo=request.getParameter("rowNum");			
		int num=Integer.parseInt(rowNo);
		ContactCust[] contactCustItems = addContact.getContactCusts();
		ContactCust contactCust=new ContactCust();
		CustAddrSeq custAddrSeq=new CustAddrSeq();


			//try {							
		custAddrSeq=customerService.getCustAddrSeqByLocationNumber(contactCustItems[num].getCustAddrSeq().getCustAddrSeqId().getLocationNumber(),contactCustItems[num].getCustomer().getCustCode());
		//sreeram code
		if(custAddrSeq != null)
			contactCustItems[num].setCustAddrSeq(custAddrSeq);
		else
			contactCustItems[num].setCustAddrSeq(new CustAddrSeq());
		//end
		/*custAddrSeq=customerService.getCustAddrSeqByLocationNumber(contactCustItems[num].getCustAddrSeq().getCustAddrSeqId().getLocationNumber(),contactCustItems[num].getCustomer().getCustCode());
		contactCustItems[num].setCustAddrSeq(custAddrSeq);*/
		addContact.setContactCusts(contactCustItems);
		addContact.setAddSeqFlag("none");
		addContact.setTabName("1");
		return showForm(request, response, errors);
		 } catch(ServiceException e)
		 {
			  addContact.setAddSeqFlag("none");
			  addContact.setTabName("1");
		      e.printStackTrace();
		      errors.reject(e.getMessage(), e.getParams(), null);
		      return showForm(request, response, errors);
		 } catch(Throwable t)
		 {
		      addContact.setAddSeqFlag("none");
			  addContact.setTabName("1");
		      t.printStackTrace();
		      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		      return showForm(request, response, errors);
		 }
		}	

    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
			if("add".equals(addOrDelete))
			{
				addContact.setContactCusts(addContactCust(addContact.getContactCusts()));
			}
			else
			{
				if(addContact.getContactCusts().length <= 1)
				{
			   	      errors.reject("customer.relation.error", new Object[] {}, null);
		    	      return showForm(request, response, errors);					
				}
				addContact.setContactCusts(deleteContactCust(addContact.getContactCusts(), addContact.getIndex()));
			}
		  addContact.setContactCustCount(addContact.getContactCusts().length);
            addContact.setTabName("1");
		  addContact.setAddOrDelete("none");
      return showForm(request, response, errors);
    }
	/*else
	  {
		addContact.setTabName("0");
	  }*/
    
    //added for the issue-104407
    if(contact.getFax() != null && !(contact.getFax().equals(""))){
   	 	commonUtil= new CommonUtil();
   	    boolean faxvalid =commonUtil.validateFax(contact.getFax());
   	    if(faxvalid == false)
   	    {
   	      errors.reject("contact.fax.error", new Object[] {contact.getFax() }, null);
   	      return showForm(request, response, errors);
   	    }
    }

    if(contact.getWorkPhone()!= null && !(contact.getWorkPhone().equals("")))
    {
        commonUtil= new CommonUtil();
      boolean phonevalid =commonUtil.validatePhone(contact.getWorkPhone());
       // if(contact.getWorkPhone().length()>12)
      if(phonevalid == false)
      {
        errors.reject("contact.workphone.error", new Object[] {contact.getWorkPhone() }, null);
        return showForm(request, response, errors);
      }

    }
    
    if(contact.getPersonalPhone()!= null && !(contact.getPersonalPhone().equals("")))
    {
        commonUtil= new CommonUtil();
     
      boolean phonevalid =commonUtil.validatePhone(contact.getPersonalPhone());
          if(phonevalid == false)
      {
        errors.reject("contact.personalphone.error", new Object[] {contact.getPersonalPhone() }, null);
        return showForm(request, response, errors);
      }

    }
   
   if(contact.getCellPhone()!= null && !(contact.getCellPhone().equals("")))
    {
      commonUtil= new CommonUtil();		    
      boolean phonevalid =commonUtil.validatePhone(contact.getCellPhone());
      if(phonevalid == false)
      {
        errors.reject("contact.cellphone.error", new Object[] {contact.getCellPhone() }, null);
        return showForm(request, response, errors);
      }

    }
   
    
    
    try
    {
    	ContactCust[] contactCustItems = addContact.getContactCusts();
    	if(contactCustItems == null || contactCustItems.length == 0)
    	{
    	      errors.reject("contact.customer.association.error", new Object[] {}, null);
    	      return showForm(request, response, errors);
    	}
    	//For iTrack #103082-START
        if(addOrDeleteContactNote!=null && ("add".equals(addOrDeleteContactNote)||"view".equals(addOrDeleteContactNote) || "delete".equals(addOrDeleteContactNote) || "reset".equals(addOrDeleteContactNote))){
          if("add".equals(addOrDeleteContactNote)){
            Notes contactNote = new Notes();
            contactNote.setNoteSummary(addContact.getNoteSummary());
            contactNote.setNoteDetails(addContact.getNote());
            contactNote.setAddedByUserName(user.getLoginName());
            contactNote.setDateTimeAdded(new Date());
            if(addContact.getContactNotesList() == null){
                List<Notes> custNoteList = new ArrayList<Notes>();
                addContact.setContactNotesList(custNoteList);
                addContact.setContactNoteEditFlag(-1);
            }
            if(addContact.getContactNoteEditFlag()>=0){
            	addContact.getContactNotesList().set(addContact.getContactNoteEditFlag(),contactNote);
            } else {
            	addContact.getContactNotesList().add(contactNote);
            }
          } else if("view".equals(addOrDeleteContactNote)){
            if(addContact.getContactNotesList()!= null && addContact.getContactNotesList().size()>0){ 
                for(int i=0;i<addContact.getContactNotesList().size();i++){ 
                  Notes contactNote=addContact.getContactNotesList().get(i);
                  if(i == addContact.getContactNoteCount()){
                	  System.out.println(" SetAded by value"+contactNote.getAddedByUserName());
                	addContact.setNoteAddBy(contactNote.getAddedByUserName());
                    try {
                    SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
                    addContact.setNoteAddDateTime(df.format(contactNote.getDateTimeAdded()));
                    } catch (Exception e){
                    e.printStackTrace();
                    }
    	              addContact.setNote(contactNote.getNoteDetails());
    	              addContact.setNoteSummary(contactNote.getNoteSummary());
    	              addContact.setAddOrDeleteContactNote("none");
    	              addContact.setTabName("2");
    	              addContact.setContactNoteEditFlag(i);
                    return showForm(request, response, errors);
                  }
              }
            }
          } else if("delete".equals(addOrDeleteContactNote)){
            if(addContact.getContactNotesList()!= null && addContact.getContactNotesList().size()>0)
            { List <Notes> contactNoteTemp = new ArrayList<Notes>();
              for(int i=0;i<addContact.getContactNotesList().size();i++)
              {
                if(i==addContact.getContactNoteCount())continue;
                contactNoteTemp.add(addContact.getContactNotesList().get(i));
              }
              addContact.getContactNotesList().clear();
              addContact.setContactNotesList(contactNoteTemp);
            }
          }
           addContact.setNoteAddBy("");
           addContact.setNoteAddDateTime("");
           addContact.setNote("");
           addContact.setNoteSummary("");
           if(addContact.getContactNotesList()!=null)
            	addContact.setContactNoteCount(addContact.getContactNotesList().size());
           addContact.setAddOrDeleteContactNote("none");
           addContact.setTabName("2");
           addContact.setContactNoteEditFlag(-1);
           return showForm(request, response, errors);
        }
    	contact.setUpdatedTime(new Date());
    	contact.setCreatedTime(new Date());
    	contact.setCreByUserName(user.getLoginName());
    	contact.setModByUserName(user.getLoginName());
    	//For iTrack #103082-END 
    	contact = customerService.addContact(contact);
    	//For iTrack #103082-START
        if (addContact.getContactNotesList() != null ){
	        for(Notes custNote:addContact.getContactNotesList()){
		        custNote.setNoteCode(String.valueOf(contact.getId()));
		        custNote.setNoteType("CONTACT");
	        }
        }
        notesService.addNotes(addContact.getContactNotesList());
      //For iTrack #103082-END
    }  catch(ServiceException e)
    {
	   e.printStackTrace();
	   errors.reject(e.getMessage(), e.getParams(), null);
	   return showForm(request, response, errors);
    } catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }/* catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("create.contact.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/
     try {
		ContactCust[] contactCustItems = addContact.getContactCusts();

		if (contactCustItems != null )
		{
			for(int i=0; i< contactCustItems.length; i++)
			{
				if(contactCustItems[i].getCustomer().getCustCode() != null && (!contactCustItems[i].getCustomer().getCustCode().trim().equals(""))
				 )
				{
					Customer existingCustomer = null;
					try{
					//Customer existingCustomer = customerService.getCustomerByCustCode(contactCustItems[i].getCustomer().getCustCode());
						existingCustomer = customerService.getCustomerByCustCode(contactCustItems[i].getCustomer().getCustCode());
					}catch(ServiceException e)
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
					if(existingCustomer == null)
					{
					     errors.reject("customer.error", new Object[] {contactCustItems[i].getCustomer().getCustCode()}, null);
				      	return showForm(request, response, errors);					
						
					}
					if(contactCustItems[i].getCustAddrSeq().getCustAddrSeqId().getLocationNumber() != null && (contactCustItems[i].getCustAddrSeq().getCustAddrSeqId().getLocationNumber() >0))
					{

					CustAddrSeq existingAddrSeq = customerService.getCustAddrSeqByLocationNumber(contactCustItems[i].getCustAddrSeq().getCustAddrSeqId().getLocationNumber(), contactCustItems[i].getCustomer().getCustCode());
					if(existingAddrSeq == null)
					{
					     errors.reject("customer.location.error", new Object[] {contactCustItems[i].getCustAddrSeq().getCustAddrSeqId().getLocationNumber()}, null);
				      	return showForm(request, response, errors);					
						
					}
					}
					else
					{
					     errors.reject("invalid.location.error", new Object[] {}, null);
					     return showForm(request, response, errors);							
					}
					contactCustItems[i].getContactCustId().setContactId(contact.getId());
					contactCustItems[i].getContactCustId().setCustCode(contactCustItems[i].getCustomer().getCustCode());
					contactCustItems[i].getContactCustId().setLocationNumber(contactCustItems[i].getCustAddrSeq().getCustAddrSeqId().getLocationNumber());
					contact.getContactCusts().add(contactCustItems[i]);
				}
			}
		}

      customerService.saveContact(contact);
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
   /* catch(Exception e)
    {
      e.printStackTrace();
      errors.reject("create.contact.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/
    ModelAndView modelAndView = new ModelAndView("common-message-r");
    //modelAndView.addObject("backUrl", "edit_contact.htm?contact.id=" + contact.getId());
	modelAndView.addObject("backUrl", "edit_contact.htm?contact.id=" + contact.getId()+"&tabname=1");
    modelAndView.addObject("backUrlDescription", "You can continue to edit this contact.");
    modelAndView.addObject("description", "Your contact has been successfully created.");

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
  }
  protected boolean suppressValidation(HttpServletRequest request)
  {
    String addOrDelete = request.getParameter("addOrDelete");
    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      return true;
    }


	String contSeqFlag=request.getParameter("contSeqFlag");
	
	if((contSeqFlag != null) && ("custval".equals(contSeqFlag)))
	{
	  return true;
	}
	
	String addSeqFlag=request.getParameter("addSeqFlag");
	
	if((addSeqFlag != null) && ("newval".equals(addSeqFlag)))
	{
	  return true;
	} 
	//For iTrack #103082-START
	String addOrDeleteContactNote = request.getParameter("addOrDeleteContactNote");
	if((addOrDeleteContactNote != null) && ("add".equals(addOrDeleteContactNote)||"view".equals(addOrDeleteContactNote) || "delete".equals(addOrDeleteContactNote)||"reset".equals(addOrDeleteContactNote)))
    {
      return true;
    }//For iTrack #103082-END
    return super.suppressValidation(request);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
     Contact contact = new Contact();
	 AddContact addContact = new AddContact();

	  String countStr = request.getParameter("contactCustCount");

	  if( countStr == null || countStr.trim().equals("") ) countStr = "0";

		int count = 0;
		try
		{
			count = new Integer(countStr).intValue();
		}
		catch(Exception e) { }


		addContact.setContactCustCount(count);
		ContactCust[] contactCusts = new ContactCust[count];
		for(int i=0; i<count; i++)
		{
			ContactCust contactCust = new ContactCust();
			Customer customer = new Customer();
			CustAddrSeq custAddrSeq = new CustAddrSeq();
			contactCust.setCustomer(customer);
			contactCust.setCustAddrSeq(custAddrSeq);

			contactCusts[i] = contactCust;
		}
		addContact.setContactCusts(contactCusts);

	 addContact.setContact(contact);
	// addContact.setTabName("0");
     return addContact;
  }

  private ContactCust[] addContactCust(ContactCust[] contactCusts)
  {
		ContactCust contactCust = new ContactCust();
		ContactCustId contactCustId = new ContactCustId();
		contactCust.setContactCustId(contactCustId);
		Customer customer = new Customer();
		CustAddrSeq custAddrSeq = new CustAddrSeq();
		CustAddrSeqId custAddrSeqId = new CustAddrSeqId();
		custAddrSeq.setCustAddrSeqId(custAddrSeqId);
		contactCust.setCustomer(customer);
		contactCust.setCustAddrSeq(custAddrSeq);

		ContactCust[] results = null;
		if(contactCusts == null) results = new ContactCust[1];
		else results = new ContactCust[contactCusts.length + 1];
		if(contactCusts != null)
		{
		for(int i=0; i<contactCusts.length; i++)
		{
			results[i] = contactCusts[i];
		}

		results[results.length - 1] = contactCust;
		}
		return results;
	}

  private ContactCust[] deleteContactCust(ContactCust[] contactCusts, int index)
  {
		if(contactCusts == null) return null;
		if(contactCusts.length <= 0) return contactCusts;

		ContactCust[] results = new ContactCust[contactCusts.length - 1];

    int count = 0;
		for(int i=0; i<contactCusts.length; i++)
		{
			if(i == index) continue;
			results[count] = contactCusts[i];

			count ++;
		}

		return results;
	}

}
