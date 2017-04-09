package com.intertek.web.controller.contract;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddContract;
import com.intertek.domain.AddContractCust;
import com.intertek.domain.CfgContractExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractCustContactId;
import com.intertek.entity.ContractCustId;
import com.intertek.entity.Customer;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ContractService;
import com.intertek.service.CustomerService;
import com.intertek.statemachine.ExecutionContext;
import com.intertek.statemachine.StateMachine;
import com.intertek.statemachine.StateMachineManager;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class CreateContractMainFormController extends BaseSimpleFormController
{
  public CreateContractMainFormController()
  {
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

    String page = addContract.getTabName();

    String indexs = request.getParameter("index");

    ContractUtil.sortCfgContractExtList(addContract.getCfgContractExtList());
    addContract.setActiveCfgContractIndex(ContractUtil.getActiveCfgContractIndex(addContract));

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");

    String userFlag=request.getParameter("userFlag");
    if("originFlag".equals(userFlag))
    {
      User originator=customerService.getAccountOwnerByLoginName(contract.getOriginatorUserName());
      contract.setOriginator(originator);
      addContract.setUserFlag("none");
      addContract.setTabName("0");
      return showForm(request, response, errors);
    }

    if("sigFlag".equals(userFlag))
    {
      User signatory=customerService.getAccountOwnerByLoginName(contract.getSignatoryUserName());
      contract.setSignatory(signatory);
      addContract.setUserFlag("none");
      addContract.setTabName("0");
      return showForm(request, response, errors);
    }

    String operationType = request.getParameter("operationType");
    if("refresh".equals(operationType))
    {
      return showForm(request, response, errors);
    }
    else if("changePriceBookId".equals(operationType))
    {
      CfgContractExt cfgContractExt = ContractUtil.getCfgContractExtByIndex(addContract, request.getParameter("cfgContractIndex"));
      if(cfgContractExt != null)
      {
        CfgContract cfgContract = cfgContractExt.getCfgContract();
        String priceBookId = cfgContract.getPriceBookId();
        String priceBookSeries = contractService.getPriceBookSeriesNameByPriceBookId(priceBookId);
        if(priceBookSeries != null) cfgContract.setPbSeries(priceBookSeries);

        CfgContractExt activeCfgContractExt = ContractUtil.getActiveCfgContractExt(addContract);
        if(activeCfgContractExt != null)
        {
          if(cfgContract == activeCfgContractExt.getCfgContract())
          {
            ContractUtil.setWorkingPriceBookIdByCfgContract(addContract, cfgContract);
          }
        }
      }

      return showForm(request, response, errors);
    }
    else if("changePriceBookSeries".equals(operationType))
    {
      CfgContractExt cfgContractExt = ContractUtil.getCfgContractExtByIndex(addContract, request.getParameter("cfgContractIndex"));
      if(cfgContractExt != null)
      {
        CfgContract cfgContract = cfgContractExt.getCfgContract();
        String priceBookId = cfgContract.getPriceBookId();

        boolean existPriceBook = contractService.existPriceBookByCurrencyCDAndPriceBookSeriesAndPriceBookId(
          cfgContract.getCurrencyCD(),
          cfgContract.getPbSeries(),
          cfgContract.getPriceBookId()
        );
        if(!existPriceBook)
        {
          cfgContract.setPriceBookId(Constants.CURRENT);
        }

        CfgContractExt activeCfgContractExt = ContractUtil.getActiveCfgContractExt(addContract);
        if(activeCfgContractExt != null)
        {
          if(cfgContract == activeCfgContractExt.getCfgContract())
          {
            ContractUtil.setWorkingPriceBookIdByCfgContract(addContract, cfgContract);
          }
        }
      }

      return showForm(request, response, errors);
    }
    else if("changeCurrencyCD".equals(operationType))
    {
      CfgContractExt cfgContractExt = ContractUtil.getCfgContractExtByIndex(addContract, request.getParameter("cfgContractIndex"));
      if(cfgContractExt != null)
      {
        CfgContract cfgContract = cfgContractExt.getCfgContract();
        List pbSeriesList = contractService.getPriceBookSeriesByCurrencyCD(cfgContract.getCurrencyCD());
        if(pbSeriesList.size() > 0)
        {
          String pbSeries = (String)pbSeriesList.get(0);
          cfgContract.setPbSeries(pbSeries);
        }

        cfgContract.setPriceBookId(Constants.CURRENT);

        CfgContractExt activeCfgContractExt = ContractUtil.getActiveCfgContractExt(addContract);
        if(activeCfgContractExt != null)
        {
          if(cfgContract == activeCfgContractExt.getCfgContract())
          {
            ContractUtil.setWorkingPriceBookIdByCfgContract(addContract, cfgContract);
          }
        }
      }

      return showForm(request, response, errors);
    }
    else if("changeUOM".equals(operationType))
    {
      CfgContractExt cfgContractExt = ContractUtil.getCfgContractExtByIndex(addContract, request.getParameter("cfgContractIndex"));
      if(cfgContractExt != null)
      {
        CfgContract cfgContract = cfgContractExt.getCfgContract();

        CfgContractExt activeCfgContractExt = ContractUtil.getActiveCfgContractExt(addContract);
        if(activeCfgContractExt != null)
        {
          if(cfgContract == activeCfgContractExt.getCfgContract())
          {
            addContract.getContract().setWorkingUOM(cfgContract.getUom());
          }
        }
      }

      return showForm(request, response, errors);
    }
    else if("addCfgContract".equals(operationType))
    {
      ContractUtil.addNewCfgContract(addContract);
      addContract.setActiveCfgContractIndex(ContractUtil.getActiveCfgContractIndex(addContract));

      return showForm(request, response, errors);
    }
    else if("changeProductGroupSet".equals(operationType))
    {
      return showForm(request, response, errors);
    }
    else if("changeVesselTypeSet".equals(operationType))
    {
      return showForm(request, response, errors);
    }
    else if("saveContract".equals(operationType))
    {
      try
      {
        ContractUtil.setCfgContractDataFromContract(addContract);

        //String st=request.getParameter("status");
        //  if(st == null || st.trim().equals("")) st = "SUB1";
        //if(st == null || st.trim().equals("")) st = "SUB";
        //contract.setStatus(st);

        contractService.saveAddContract(addContract);
      }
      catch(Exception e)
      {
        e.printStackTrace();
        errors.reject("create.contract.error", new Object[] {e.getMessage()}, null);

        User originator = contract.getOriginator();
        if(originator == null) contract.setOriginator(new User());

        User signatory = contract.getSignatory();
        if(signatory == null) contract.setSignatory(new User());

        return showForm(request, response, errors);
      }

      ModelAndView modelAndView = new ModelAndView(ContractUtil.getViewNameByTabName("main"));
      modelAndView.addObject("contractCode", contract.getContractCode());
      modelAndView.addObject("saved_message", "Saved successfully");

      return modelAndView;
    }

    if((operationType != null) && !"".equals(operationType.trim()))
    {
      try
      {

        StateMachineManager stateMachineMgr = (StateMachineManager)ServiceLocator.getInstance().getBean("stateMachineMgr");
        StateMachine stateMachine = stateMachineMgr.getStateMachine("ContractStateMachine");
        if(stateMachine != null)
        {
          stateMachine.applyTransition(new ExecutionContext(), contract, operationType);
        }

        ContractUtil.setCfgContractDataFromContract(addContract);
        contractService.saveAddContract(addContract);
      }
      catch(Exception e)
      {
        e.printStackTrace();

        User originator = contract.getOriginator();
        if(originator == null) contract.setOriginator(new User());

        User signatory = contract.getSignatory();
        if(signatory == null) contract.setSignatory(new User());

        errors.reject("edit.contract.error", new Object[] {e.getMessage()}, null);
        return showForm(request, response, errors);
      }

      ModelAndView modelAndView = new ModelAndView(ContractUtil.getViewNameByTabName("main"));
      modelAndView.addObject("contractCode", contract.getContractCode());
      modelAndView.addObject("saved_message", "Saved successfully");

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
    String userFlag = request.getParameter("userFlag");
    if((userFlag != null) && ("sigFlag".equals(userFlag)))
    {
      return true;
    }

    if((userFlag != null) && ("originFlag".equals(userFlag)))
    {
      return true;
    }

    String operation = request.getParameter("operation");
    if((operation != null) && "refresh".equals(operation))
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
    AddContract addContract = new AddContract();

    addContract.setIsNewFlag(true);

    Contract contract = new Contract();

    User originator=new User();
    User signatory=new User();

    contract.setMasterListDate(new Date());
    contract.setStatus("INPR");
    contract.setStatusDate(new Date());
    contract.setExpireDate(new Date());
    contract.setOriginator(originator);
    contract.setSignatory(signatory);
    contract.setInvoiceType(Constants.INV_TYPE_REG);
    contract.setWorkingUOM(Constants.UOM_METRIC_TON_VALUE);
    addContract.setContract(contract);

    contract.setCreatedTime(new Date());
    contract.setUpdatedTime(new Date());

    User user = SecurityUtil.getUser();
    if(user != null)
    {
      contract.setCreByUserName(SecurityUtil.getUser().getLoginName());
      contract.setModByUserName(SecurityUtil.getUser().getLoginName());
    }

    CfgContract cfgContract = ContractUtil.createInitCfgContract();
    CfgContractExt cfgContractExt = new CfgContractExt();
    cfgContractExt.setCfgContract(cfgContract);

    List cfgContractExtList = new ArrayList();
    cfgContractExtList.add(cfgContractExt);

    addContract.setCfgContractExtList(cfgContractExtList);
    ContractUtil.setWorkingPriceBookIdByCfgContract(addContract, cfgContract);

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    String activeCurrencyCD = ContractUtil.getActiveCurrencyCD(addContract);
    if(activeCurrencyCD != null)
    {
      List pbIds = contractService.getPriceBookIdsByCurrencyCD(activeCurrencyCD);
      if(pbIds.size() > 0)
      {
        String pbId = (String)pbIds.get(0);
        List pbReferenceFieldList = contractService.getReferenceFieldsByContractId(pbId);
        addContract.setPbReferenceFieldList(pbReferenceFieldList);
        List referenceFieldList = ContractUtil.copyReferenceFieldList(pbReferenceFieldList);
        int size = referenceFieldList.size();
        if(size < 5)
        {
          for(int i=size; i<5; i++)
          {
            referenceFieldList.add(ContractUtil.createReferenceField(i + 1));
          }
        }
        addContract.setReferenceFieldList(referenceFieldList);
      }
    }

    return addContract;
  }
}
