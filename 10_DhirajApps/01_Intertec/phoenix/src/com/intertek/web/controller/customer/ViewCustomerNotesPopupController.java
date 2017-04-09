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
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddCustomer;
import com.intertek.entity.Customer;
import com.intertek.entity.Notes;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CustomerService;
import com.intertek.service.NotesService;
import com.intertek.service.UserService;
import com.intertek.util.SecurityUtil;

public class ViewCustomerNotesPopupController extends SimpleFormController {
	public ViewCustomerNotesPopupController() {
		super();
		setSessionForm(true);
		setCommandClass(AddCustomer.class);
	}
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		  AddCustomer addCustomer = (AddCustomer) command;
		  /* Committed for itrack#135193-START*/ 
		  /*if(addCustomer.getCustomerNotesList()!= null && addCustomer.getCustomerNotesList().size()>0){	
				for(int i=0;i<addCustomer.getCustomerNotesList().size();i++){	
					Notes custNote = addCustomer.getCustomerNotesList().get(i);
					if(i == addCustomer.getCustNoteCount()){
						addCustomer.setNoteAddBy(custNote.getAddedByUserName());
					  	try {
							SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
							addCustomer.setNoteAddDateTime(df.format(custNote.getDateTimeAdded()));
						} catch (Exception e) {
						}
					  	addCustomer.setNote(custNote.getNoteDetails());
						addCustomer.setNoteSummary(custNote.getNoteSummary());
					    addCustomer.setCustNoteEditFlag(i);
					    addCustomer.setNoteId(custNote.getId());
					    return showForm(request, response, errors);
					}
				}
			}*/
		  /* Committed for itrack#135193-END*/
		  /*For itrack#135193- START*/
		  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		  User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
		  String addOrDeleteCustNote = request.getParameter("addOrDeleteCustNote");
		    if(addOrDeleteCustNote!=null && ("add".equals(addOrDeleteCustNote)||"view".equals(addOrDeleteCustNote) || "delete".equals(addOrDeleteCustNote) || "reset".equals(addOrDeleteCustNote)))
		    {
		      if("add".equals(addOrDeleteCustNote))
		      {
		        Notes custNote = new Notes();
		        custNote.setNoteSummary(addCustomer.getNoteSummary());
		        custNote.setNoteDetails(addCustomer.getNote());
		        custNote.setAddedByUserName(user.getLoginName());
		        custNote.setDateTimeAdded(new Date());
		        if(addCustomer.getCustomerNotesList() == null)
		        {
		          List  <Notes> custNoteList = new ArrayList<Notes>();
		          addCustomer.setCustomerNotesList(custNoteList);
		          addCustomer.setCustNoteEditFlag(-1);
		        }
		        if(addCustomer.getCustNoteEditFlag()>=0)
		        {
		          addCustomer.getCustomerNotesList().set(addCustomer.getCustNoteEditFlag(),custNote);
		        }
		        else {
		        addCustomer.getCustomerNotesList().add(custNote);
		        }
		      }
		      else if("view".equals(addOrDeleteCustNote))
		      {
		    	  if(addCustomer.getCustomerNotesList()!= null && addCustomer.getCustomerNotesList().size()>0){	
						for(int i=0;i<addCustomer.getCustomerNotesList().size();i++){	
							Notes custNote = addCustomer.getCustomerNotesList().get(i);
							if(i == addCustomer.getCustNoteCount()){
								addCustomer.setNoteAddBy(custNote.getAddedByUserName());
							  	try {
									SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
									addCustomer.setNoteAddDateTime(df.format(custNote.getDateTimeAdded()));
								} catch (Exception e) {
								}
							  	addCustomer.setNote(custNote.getNoteDetails());
								addCustomer.setNoteSummary(custNote.getNoteSummary());
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
		        if(addCustomer.getCustomerNotesList()!= null && addCustomer.getCustomerNotesList().size()>0)
		        { List <Notes>custNoteTemp = new ArrayList<Notes>();
		          for(int i=0;i<addCustomer.getCustomerNotesList().size();i++)
		          {
		            if(i==addCustomer.getCustNoteCount())continue;
		            custNoteTemp.add(addCustomer.getCustomerNotesList().get(i));
		          }
		          addCustomer.getCustomerNotesList().clear();
		          addCustomer.setCustomerNotesList(custNoteTemp);
		        }
		      }
		        addCustomer.setNoteAddBy("");
		        addCustomer.setNoteAddDateTime("");
		        addCustomer.setNote("");
		        addCustomer.setNoteSummary("");
		        if(addCustomer.getCustomerNotesList()!=null)
		        addCustomer.setCustNoteCount(addCustomer.getCustomerNotesList().size());
		        addCustomer.setAddOrDeleteCustNote("none");
		        addCustomer.setCustNoteEditFlag(-1);
		        request.getSession().setAttribute("CustomerNotes", addCustomer.getCustomerNotesList());
		        return showForm(request, response, errors);
		    }
		    /*For itrack#135193- END*/
		ModelAndView modelAndView = new ModelAndView("common-messagepopup-r");
		return modelAndView;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null,new CustomDateEditor(dateFormat, true));
	}

	protected boolean suppressValidation(HttpServletRequest request) {
		String addOrDelete = request.getParameter("addOrDelete");
		if ((addOrDelete != null)	&& ("add".equals(addOrDelete) || "delete".equals(addOrDelete))) {
			return true;
		}
		String addOrDeleteCustNote = request.getParameter("addOrDeleteCustNote");
		if((addOrDeleteCustNote != null) && ("add".equals(addOrDeleteCustNote)||"view".equals(addOrDeleteCustNote) || "delete".equals(addOrDeleteCustNote)||"reset".equals(addOrDeleteCustNote)))
		{
		      return true;
		}
		return super.suppressValidation(request);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String custCode = request.getParameter("custCode");
		String divName = request.getParameter("divName");
		String divBody = request.getParameter("divBody");
		AddCustomer addCustomer = new AddCustomer();
		/*For ITrack#135193-START  */
		String reqForm = request.getParameter("reqForm");
		addCustomer.setReqForm(reqForm);
		if(reqForm != null && reqForm.equals("quickCreateForm")){
			addCustomer.setNotesViewOnly(false);
		} else {
			addCustomer.setNotesViewOnly(true);
		}
		/*For ITrack#135193-END  */
		CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
		NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");
		try {
			if(custCode != null && !custCode.isEmpty()){
				Customer customer = customerService.loadCustomerByCustCode(custCode);
				List custNotesList = notesService.getNotes(custCode,"CUSTOMER");		
				if(custNotesList != null && custNotesList.size() > 0){
					addCustomer.setCustomerNotesList(custNotesList);
					addCustomer.setCustNoteCount(custNotesList.size());
				}
				addCustomer.setCustAddBy(customer.getCreByUserName());
				addCustomer.setCustModiBy(customer.getModByUserName());
				SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
				if(customer.getUpdatedTime() != null){
					addCustomer.setCustModiDateTime(df.format(customer.getUpdatedTime()));
				}
				if(customer.getCreatedTime() != null){
					addCustomer.setCustAddDateTime(df.format(customer.getCreatedTime()));
				}
			}
			addCustomer.setDivName(divName);
			addCustomer.setDivBody(divBody);
		} catch(Exception e){
		}
		return addCustomer;
	}
}
