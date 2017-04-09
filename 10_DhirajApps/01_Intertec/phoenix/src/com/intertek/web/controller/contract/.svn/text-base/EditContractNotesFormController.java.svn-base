package com.intertek.web.controller.contract;

import java.text.NumberFormat;
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

import com.intertek.domain.AddContract;

import com.intertek.entity.Contract;
import com.intertek.entity.Notes;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ContractService;
import com.intertek.service.NotesService;
import com.intertek.service.UserService;
import com.intertek.util.DateUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractNotesFormController extends BaseEditContractFormController
{
  public EditContractNotesFormController() {
    super();
    setSessionForm(true);
    setCommandClass(AddContract.class);
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
    AddContract addContract = (AddContract)command;
    Contract contract = addContract.getContract();
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");
	UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	String addOrDeleteContractNote = request.getParameter("addOrDeleteContractNote");
	if(addOrDeleteContractNote!=null && ("add".equals(addOrDeleteContractNote)||"view".equals(addOrDeleteContractNote) || "delete".equals(addOrDeleteContractNote) || "reset".equals(addOrDeleteContractNote))){
		   if("add".equals(addOrDeleteContractNote)){
				Notes contractNote = new Notes();
				contractNote.setNoteSummary(addContract.getNoteSummary());
				contractNote.setNoteDetails(addContract.getNote());
				contractNote.setAddedByUserName(user.getLoginName());
				contractNote.setDateTimeAdded(new Date());
				contractNote.setId(addContract.getNoteId());
				if(addContract.getContractNotesList() == null){
					List<Notes> contactNoteList = new ArrayList<Notes>();
					addContract.setContractNotesList(contactNoteList);
					addContract.setContractNoteEditFlag(-1);
				}
				if(addContract.getContractNoteEditFlag()>=0){
					addContract.getContractNotesList().set(addContract.getContractNoteEditFlag(),contractNote);
				} else {
				addContract.getContractNotesList().add(contractNote);
				}
			} else if("view".equals(addOrDeleteContractNote)){
				if(addContract.getContractNotesList()!= null && addContract.getContractNotesList().size()>0){					
					for(int i=0;i<addContract.getContractNotesList().size();i++){	
						Notes contactNote=addContract.getContractNotesList().get(i);
						if(i==addContract.getContractNoteCount()){
							addContract.setNoteAddBy(contactNote.getAddedByUserName());
						  	try {
								SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
								addContract.setNoteAddDateTime(df.format(contactNote.getDateTimeAdded()));
							} catch (Exception e) {

							}
						  	addContract.setNote(contactNote.getNoteDetails());
							addContract.setNoteSummary(contactNote.getNoteSummary());
						  	addContract.setAddOrDeleteContractNote("none");
						    addContract.setTabName("8");
						    addContract.setContractNoteEditFlag(i);
						    addContract.setNoteId(contactNote.getId());
						    return showForm(request, response, errors);
						}
					}
				}
			} else if("delete".equals(addOrDeleteContractNote)){
				if(addContract.getContractNotesList()!=null && addContract.getContractNotesList().size()>0){
					List <Notes> contactNoteTemp = new ArrayList<Notes>();
					for(int i=0;i<addContract.getContractNotesList().size();i++){
						if(i==addContract.getContractNoteCount()){
							if(addContract.getDelNotesList()==null){
								addContract.setDelNotesList(new ArrayList<Notes>());
							}
							Notes delNote=addContract.getContractNotesList().get(i);
							addContract.getDelNotesList().add(delNote);
							continue;
						}
						contactNoteTemp.add(addContract.getContractNotesList().get(i));
					}
					addContract.getContractNotesList().clear();
					addContract.setContractNotesList(contactNoteTemp);
				}
			}
		  	addContract.setNoteAddBy("");
		  	addContract.setNoteId(0L);
		  	addContract.setNoteAddDateTime("");
		  	addContract.setNote("");
			addContract.setNoteSummary("");
		  	if(addContract.getContractNotesList()!=null)
			addContract.setContractNoteCount(addContract.getContractNotesList().size());
		    addContract.setAddOrDeleteContractNote("none");
		    addContract.setTabName("8");
		    addContract.setContractNoteEditFlag(-1);
		    return showForm(request, response, errors);
	}
    String operationType = request.getParameter("operationType");
    if("saveContract".equals(operationType)
      || "switchTab".equals(operationType)
      || "prevContract".equals(operationType)
      || "nextContract".equals(operationType)
      || "searchContract".equals(operationType)
    )
    {
      if("saveContract".equals(operationType) && (!addContract.getViewOnly()))
      {
    	 try {
        	if (addContract.getContractNotesList() != null ){
    	        for(Notes contractNotes:addContract.getContractNotesList()){
    	        	contractNotes.setNoteCode(contract.getContractCode());
    	        	contractNotes.setNoteType("CONTRACT");
    	        } 
    	        if(addContract.getDelNotesList() == null || addContract.getDelNotesList().size() == 0){
    	        	notesService.addNotes(addContract.getContractNotesList());
    	        } else {
    	        	notesService.UpdateDeleteNote(addContract.getContractNotesList(), addContract.getDelNotesList());
    	        }
        	}
        }
        catch(Exception e)
        {
          e.printStackTrace();
          errors.reject("contract.note.save.error", new Object[] {e.getMessage()}, null);
          return showForm(request, response, errors);
        }
      }
      ModelAndView modelAndView = getModelAndView(
        operationType,
        request.getParameter("goToContractTab"),
        "crtnotes",
        contract,
        addContract.getContractSearch(),
        errors
      );
      if(modelAndView == null) return showForm(request, response, errors);
      return modelAndView;
    }
    return showForm(request, response, errors);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.getUserDateFormat());
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    NumberFormat nf = NumberFormat.getInstance();
    binder.registerCustomEditor(java.lang.Double.class, null, new CustomDiscountEditor(Double.class, nf, new Double(-1)));
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    String operationType = request.getParameter("operationType");
    if("switchTab".equals(operationType)
      || "prevContract".equals(operationType)
      || "nextContract".equals(operationType)
      || "searchContract".equals(operationType)
    )
    {
      return true;
    }
    String addOrDeleteContractNote = request.getParameter("addOrDeleteContractNote");
	if((addOrDeleteContractNote != null) && ("add".equals(addOrDeleteContractNote)||"view".equals(addOrDeleteContractNote) || "delete".equals(addOrDeleteContractNote)||"reset".equals(addOrDeleteContractNote)))
    {
      return true;
    }
    return super.suppressValidation(request, command);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    String contractCode = request.getParameter("contractCode");
    AddContract addContract = new AddContract();
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");
    try {
    	Contract contract = contractService.loadContractByContractCode(contractCode);
    	List contractNotesList = notesService.getNotes(contractCode,"CONTRACT");
    	addContract.setContractNoteEditFlag(-1);
    	if(contractNotesList!= null && contractNotesList.size()>0){
    		addContract.setContractNotesList(contractNotesList);
    		addContract.setContractNoteCount(contractNotesList.size());
    	}
    	addContract.setContractAddBy(contract.getCreByUserName());
    	addContract.setContractModiBy(contract.getModByUserName());
    	SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    	if(contract.getUpdatedTime()!=null){
    		addContract.setContractModiDateTime(df.format(contract.getUpdatedTime()));
    	}
    	if(contract.getCreatedTime()!=null){
    		addContract.setContractAddDateTime(df.format(contract.getCreatedTime()));
    	}
        addContract.setContract(contract);
    } catch(Exception e){
      e.printStackTrace();
    }
    boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateContract","ContractCustomerTab"});
    addContract.setViewOnly(editable == false);
    addContract.setContractSearch(getContractSearch(request));
    return addContract;
  }
}
