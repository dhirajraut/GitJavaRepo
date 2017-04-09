package com.intertek.web.controller.contract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.intertek.service.UserService;
import com.intertek.util.Constants;

public class ViewContractPopupController extends SimpleFormController
{
  public ViewContractPopupController() {
    super();
    setCommandClass(AddContract.class);
  //setSessionForm(true);
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

   ModelAndView modelAndView = new ModelAndView("common-messagepopup-r");
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

   String rowNum = request.getParameter("rowNum");
   ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
   UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

   String div1=request.getParameter("div1");
   String div2=request.getParameter("div2");
   addContract.setDiv1(div1+rowNum);
   addContract.setDiv2(div2+rowNum);
    addContract.setRowNum(rowNum);

    String contractCode = request.getParameter("contractId");

      User user=new User();
    Date date=new Date();
    Date masterListDate=new Date();
    Date expireDate=new Date();
    Date statusDate=new Date();
    User originator=new User();
    User signatory=new User();
    contract.setMasterListDate(masterListDate);
    contract.getMasterListDate();
    contract.setStatusDate(statusDate);
    contract.getStatusDate();
    contract.setExpireDate(expireDate);
    contract.getExpireDate();

    try
    {
      contract = contractService.loadContractByContractCode(contractCode);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    if(contract!=null && (contract.getOriginator() == null || contract.getOriginator().getLoginName().trim().equals("")))
    {
      contract.setOriginator(new User());
    }
    else
    {
      User existingUser = userService.getUserByName(contract.getOriginatorUserName());
      if(existingUser == null)
        contract.setOriginator(new User());
      else
        contract.setOriginator(existingUser);
    }
    if(contract.getSignatory() == null || contract.getSignatory().getLoginName().trim().equals(""))
    {
      contract.setSignatory(new User());
    }
    else
    {
      User existingUser = userService.getUserByName(contract.getSignatoryUserName());
      if(existingUser == null)
        contract.setSignatory(new User());
      else
        contract.setSignatory(existingUser);
    }

    if(contract.getInvoiceType()==null)
    {
      contract.setInvoiceType("Reg");
    }
    if((contract.getWorkingUOM()==null) || "".equals(contract.getWorkingUOM().trim()))
    {
      contract.setWorkingUOM(Constants.UOM_METRIC_TON_VALUE);
    }
  addContract.setContract(contract);


  return addContract;
  }
}
