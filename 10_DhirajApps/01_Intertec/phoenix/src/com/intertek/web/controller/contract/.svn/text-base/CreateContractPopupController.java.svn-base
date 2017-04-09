package com.intertek.web.controller.contract;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddContract;
import com.intertek.entity.Contract;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ContractService;
import com.intertek.util.CommonUtil;

public class CreateContractPopupController extends SimpleFormController
{
  public CreateContractPopupController() {
    super();
    setCommandClass(AddContract.class);
	setSessionForm(true);
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

	  AddContract  addContract = (AddContract)command;
      ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
	   Contract contract = addContract.getContract();
	   CommonUtil commonUtil=null;
		 
			if( contract.getContractCode() == null || contract.getContractCode().trim().equals(""))
			{
				errors.reject("create.contract.error",new Object[] { "Please enter contact code" }, null);	
			   return showForm(request, response, errors);
		    }
		
		   if(contract.getOriginator().getEmail()!= null && !(contract.getOriginator().getEmail().equals("")))
		   {
				commonUtil= new CommonUtil();
				boolean emailvalid =commonUtil.validateEmail(contract.getOriginator().getEmail()); 	
			//if(contract.getOriginator().getEmail().indexOf('@') == -1)
			   if(emailvalid == false)
			   {
				errors.reject("originator.email.error", new Object[] { contract.getOriginator().getEmail() }, null);
				return showForm(request, response, errors);
			   }			
		   }
		
		if(contract.getSignatory().getEmail()!= null && !(contract.getSignatory().getEmail().equals("")))
		{
			commonUtil= new CommonUtil();
			boolean emailvalid =commonUtil.validateEmail(contract.getSignatory().getEmail());
			
			//if(contract.getSignatory().getEmail().indexOf('@') == -1)
			if(emailvalid == false)
			{
				errors.reject("signatory.email.error", new Object[] { contract.getSignatory().getEmail() }, null);
				return showForm(request, response, errors);
			}
	   }
			
	try
    {
	String status=request.getParameter("status");
	if(status == null || status.trim().equals("")) status = "SUB";
	contract.setStatus(status);
     contract = contractService.addContract(contract);
	}
	catch(Exception e)
    {
      e.printStackTrace();
     errors.reject("create.contract.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }
 
   ModelAndView modelAndView = new ModelAndView("common-messagepopup-r");
	

	String inputfield = String.valueOf( addContract.getInputFieldId());
	String id = contract.getContractCode();
	String divparam="newcontract"+addContract.getRowNum();
	String divparambody="newcontractbody"+addContract.getRowNum();
    String searchForm=addContract.getSearchForm(); 
	
    modelAndView.addObject("param1", addContract.getInputFieldId());
	modelAndView.addObject("param2", contract.getContractCode());
	//modelAndView.addObject("param3", addContract.getRowNum());
	modelAndView.addObject("param3", divparam);
	modelAndView.addObject("param4", divparambody);
	modelAndView.addObject("searchForm", addContract.getSearchForm());

    modelAndView.addObject("description", "Your contract has been successfully created.");



    return modelAndView;

    //return showForm(request, response, errors);
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
   
      return super.suppressValidation(request);
    }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	
	AddContract addContract = new AddContract();
    Contract contract = new Contract();
	 User user=new User();
	  Date date=new Date();
	  Date masterListDate=new Date();
	  Date expireDate=new Date();
	  Date statusDate=new Date();
	  User originator=new User();
	  User signatory=new User();
	  contract.setMasterListDate(masterListDate);
	  contract.setStatusDate(statusDate);
	  contract.setExpireDate(expireDate);
	  contract.setOriginator(originator);
	  contract.setSignatory(signatory);
	  contract.setInvoiceType("Reg");
	  addContract.setContract(contract);
	  String inputFieldId = request.getParameter("inputFieldId");
	  String rowNum = request.getParameter("rowNum");
	  String searchForm=request.getParameter("searchForm");
	  addContract.setRowNum(rowNum);
	  addContract.setInputFieldId(inputFieldId);
	  addContract.setSearchForm(searchForm);
	  return addContract;
  }
}
