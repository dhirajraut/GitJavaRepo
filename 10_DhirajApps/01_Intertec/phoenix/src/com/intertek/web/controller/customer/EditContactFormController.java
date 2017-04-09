package com.intertek.web.controller.customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

//public class EditContactFormController extends SimpleFormController
public class EditContactFormController extends BaseSimpleFormController
{
  public EditContactFormController() {
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
NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");
UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
String addOrDeleteContactNote = request.getParameter("addOrDeleteContactNote");
    String addOrDelete = request.getParameter("addOrDelete");
		   String page = addContact.getTabName();
String contSeqFlag=request.getParameter("contSeqFlag");
String addSeqFlag=request.getParameter("addSeqFlag");

CommonUtil commonUtil=null;
User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
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
    if(contacts != null && contacts.size()!=0)
		{
        contactCustItems[num].setContactSeqNum(contacts.size()+1);
		}
		else
			{
	 //contactCustItems[num].setContactSeqNum(contacts.size()+1);
		//sreeram changed
		contactCustItems[num].setContactSeqNum(1);
			}
contactCustItems[num].setCustomer(customer);
addContact.setContactCusts(contactCustItems);
addContact.setContSeqFlag("none");
addContact.setTabName("1");
return showForm(request, response, errors);
   } catch(ServiceException e)
   {
	  addContact.setContSeqFlag("none");
	  addContact.setTabName("1");
      e.printStackTrace();
      errors.reject(e.getMessage(), e.getParams(), null);
      return showForm(request, response, errors);
   } catch(Throwable t)
   {
	  addContact.setContSeqFlag("none");
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
		//Sreeram code
		custAddrSeq=customerService.getCustAddrSeqByLocationNumber(contactCustItems[num].getCustAddrSeq().getCustAddrSeqId().getLocationNumber(),contactCustItems[num].getCustomer().getCustCode());
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
	}catch(ServiceException e)
	{
		addContact.setAddSeqFlag("none");
	    e.printStackTrace();
	    errors.reject(e.getMessage(), e.getParams(), null);
	    return showForm(request, response, errors);
	} catch(Throwable t)
	{
		addContact.setAddSeqFlag("none");
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
	/*newly added 
else
	  {
		addContact.setTabName("0");
	  }
up to here*/
		Contact contact = addContact.getContact();
		
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
		
		   //added for the issue-104407
		   if(contact.getFax() != null && !(contact.getFax().equals(""))){
		  	 System.out.println("in edit controller........");
		  	 	commonUtil= new CommonUtil();
		  	    boolean faxvalid =commonUtil.validateFax(contact.getFax());
		  	    if(faxvalid == false)
		  	    {
		  	      errors.reject("contact.fax.error", new Object[] {contact.getFax() }, null);
		  	      return showForm(request, response, errors);
		  	    }
		   }
		
		ContactCust[] contactCustItems = addContact.getContactCusts();
		Set contactCustSet = new HashSet();
		try {
		if (contactCustItems != null )
		{
			for(int i=0; i< contactCustItems.length; i++)
			{
				if(contactCustItems[i].getCustomer().getCustCode() != null && (!contactCustItems[i].getCustomer().getCustCode().trim().equals("")))
				{
				
					Customer existingCustomer = customerService.getCustomerByCustCode(contactCustItems[i].getCustomer().getCustCode());
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

				contactCustSet.add(contactCustItems[i]);

			}
			
		}
			contact.setContactCusts(contactCustSet);
		}
		//For iTrack #103082-START		
		  if(addOrDeleteContactNote!=null && ("add".equals(addOrDeleteContactNote)||"view".equals(addOrDeleteContactNote) || "delete".equals(addOrDeleteContactNote) || "reset".equals(addOrDeleteContactNote))){
			   if("add".equals(addOrDeleteContactNote)){
					Notes contactNote = new Notes();
					contactNote.setNoteSummary(addContact.getNoteSummary());
					contactNote.setNoteDetails(addContact.getNote());
					contactNote.setAddedByUserName(user.getLoginName());
					contactNote.setDateTimeAdded(new Date());
					contactNote.setId(addContact.getNoteId());
					if(addContact.getContactNotesList() == null){
						List<Notes> contactNoteList = new ArrayList<Notes>();
						addContact.setContactNotesList(contactNoteList);
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
							if(i==addContact.getContactNoteCount()){
								System.out.println(" SetAded by value"+contactNote.getAddedByUserName());
								addContact.setNoteAddBy(contactNote.getAddedByUserName());
							  	try {
									SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
									addContact.setNoteAddDateTime(df.format(contactNote.getDateTimeAdded()));
								} catch (Exception e) {

								}
							  	addContact.setNote(contactNote.getNoteDetails());
								addContact.setNoteSummary(contactNote.getNoteSummary());
							  	addContact.setAddOrDeleteContactNote("none");
							    addContact.setTabName("2");
							    addContact.setContactNoteEditFlag(i);
							    addContact.setNoteId(contactNote.getId());
							    return showForm(request, response, errors);
							}
						}
					}
				}
				else if("delete".equals(addOrDeleteContactNote))
				{
					if(addContact.getContactNotesList()!=null && addContact.getContactNotesList().size()>0){
						List <Notes> contactNoteTemp = new ArrayList<Notes>();
						for(int i=0;i<addContact.getContactNotesList().size();i++){
							if(i==addContact.getContactNoteCount()){
								if(addContact.getDelNotesList()==null){
									addContact.setDelNotesList(new ArrayList<Notes>());
								}
								Notes delNote=addContact.getContactNotesList().get(i);
								addContact.getDelNotesList().add(delNote);
								continue;
							}
							contactNoteTemp.add(addContact.getContactNotesList().get(i));
						}
						addContact.getContactNotesList().clear();
						addContact.setContactNotesList(contactNoteTemp);
					}
				}
			  	addContact.setNoteAddBy("");
			  	addContact.setNoteId(0L);
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
		  //For iTrack #103082-END
		contact.setModByUserName(user.getLoginName());
		contact.setUpdatedTime(new Date());
		//For iTrack #103082-END
        customerService.saveContact(contact);
      //For iTrack #103082-START
      if (addContact.getContactNotesList() != null ){
	        for(Notes custNote:addContact.getContactNotesList()){
		        custNote.setNoteCode(String.valueOf(contact.getId()));
		        custNote.setNoteType("CONTACT");
	        }
      }
      notesService.UpdateDeleteNote(addContact.getContactNotesList(), addContact.getDelNotesList());
     //For iTrack #103082-END
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

      errors.reject("save.business.unit.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/
//return showForm(request, response, errors);
   ModelAndView modelAndView = new ModelAndView("common-message-r");
    //modelAndView.addObject("backUrl", "edit_contact.htm?contact.id=" + contact.getId());
	modelAndView.addObject("backUrl", "edit_contact.htm?contact.id=" + contact.getId()+"&tabname="+addContact.getTabName());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this contact.");
    modelAndView.addObject("description", "Your contact has been successfully updated.");

  

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
		String contactId = request.getParameter("contact.id");
		String countStr = request.getParameter("contactCustCount");
		String deleteIndex = request.getParameter("index");
		int delRow = 0;

	  if( deleteIndex != null && (!deleteIndex.trim().equals("")) )
	  {
		try
		{
			delRow = new Integer(deleteIndex).intValue();
		}
		catch(Exception e) { }
	  }

    Contact contact = null;
		AddContact addContact = new AddContact();
	  try {
	CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
   /* try
    {*/
      contact = customerService.getContactById(new Long(contactId));

   /* }
    catch(Exception e)
    {
      e.printStackTrace();
    }*/

    if(contact == null)
    {
      contact = new Contact();
    }

		addContact.setContact(contact);



		Set contactCustSet = contact.getContactCusts();

		int count = 0;

	  if( countStr != null && (!countStr.trim().equals("")) )
	  {
		try
		{
			count = new Integer(countStr).intValue();
		}
		catch(Exception e) { }
	  }
	  else
	  {
		  count = contactCustSet.size();
	  }

		if(count < contactCustSet.size())
	  {
			contactCustSet.remove(delRow);
	  }


		addContact.setContactCustCount(count);

		ContactCust[] contactCusts = new ContactCust[count];
		Iterator iter = contactCustSet.iterator();
		int custCount = 0;

		while(iter.hasNext())
	  {

			ContactCust contactCust = (ContactCust) iter.next();
			contactCusts[custCount] = contactCust;
			custCount++;
			if (custCount == count)
			{
				break;
			}
	  }

		addContact.setContactCusts(contactCusts);
		addContact.setContact(contact);
		//addContact.setTabName("0");
		 //newly added
	  String tabname=request.getParameter("tabname");
//up to here
		addContact.setTabName(tabname);
		//For iTrack #103082-START
		NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");
		List contactNotesList = notesService.getNotes(String.valueOf(contact.getId()),"CONTACT");
		addContact.setContactNoteEditFlag(-1);
		if(contactNotesList!= null && contactNotesList.size()>0){
			addContact.setContactNotesList(contactNotesList);
			addContact.setContactNoteCount(contactNotesList.size());
		}
		addContact.setContactAddBy(contact.getCreByUserName());
		addContact.setContactModiBy(contact.getModByUserName());
		SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		if(contact.getUpdatedTime()!=null){
			addContact.setContactModiDateTime(df.format(contact.getUpdatedTime()));
		}
		if(contact.getCreatedTime()!=null){
			addContact.setContactAddDateTime(df.format(contact.getCreatedTime()));
		}
		//For iTrack #103082-END
      } catch(ServiceException e)
      {
      	throw new ServiceException(e.getMessage(), e.getParams(), e);
      }
      catch(Throwable t)
      {
          t.printStackTrace();
          throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
      }
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
