package com.intertek.web.controller.contract;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddContract;
import com.intertek.domain.AddContractCust;
import com.intertek.domain.CfgContractExt;
import com.intertek.domain.ContractSearch;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contact;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractCustContactId;
import com.intertek.entity.ContractCustId;
import com.intertek.entity.Customer;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ContractService;
import com.intertek.service.CustomerService;
import com.intertek.service.TestService;
import com.intertek.service.UserService;
import com.intertek.service.ZoneService;
import com.intertek.statemachine.ExecutionContext;
import com.intertek.statemachine.StateMachine;
import com.intertek.statemachine.StateMachineManager;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.util.EntityUtil;
import com.intertek.util.ProductGroupSetUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.VesselTypeSetUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractMainFormController extends BaseEditContractFormController
{
  public EditContractMainFormController() {
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
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");

    String userFlag=request.getParameter("userFlag");

    ContractUtil.sortCfgContractExtList(addContract.getCfgContractExtList());
    addContract.setActiveCfgContractIndex(ContractUtil.getActiveCfgContractIndex(addContract));

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
      ProductGroupSetUtil.updateProductGroupSetName(addContract);

      return showForm(request, response, errors);
    }
    else if("changeVesselTypeSet".equals(operationType))
    {
      VesselTypeSetUtil.updateVesselTypeSetName(addContract);

      return showForm(request, response, errors);
    }
    else if("saveContract".equals(operationType)
      || "switchTab".equals(operationType)
      || "prevContract".equals(operationType)
      || "nextContract".equals(operationType)
      || "searchContract".equals(operationType)
    )
    {
      if("saveContract".equals(operationType) && (!addContract.getViewOnly()))
      {
        try
        {
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
      }

      ModelAndView modelAndView = getModelAndView(
        operationType,
        request.getParameter("goToContractTab"),
        "main",
        contract,
        addContract.getContractSearch(),
        errors
      );
      if(modelAndView == null) return showForm(request, response, errors);

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

      String viewName = ContractUtil.getViewNameByTabName(request.getParameter("goToContractTab"));
      if(viewName != null)
      {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("contractCode", contract.getContractCode());

        return modelAndView;
      }
      else
      {
        ModelAndView modelAndView = new ModelAndView(ContractUtil.getViewNameByTabName("main"));
        modelAndView.addObject("contractCode", contract.getContractCode());
        modelAndView.addObject("saved_message", "Saved successfully");

        return modelAndView;
      }
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

    String operationType = request.getParameter("operationType");
    if("switchTab".equals(operationType)
      || "prevContract".equals(operationType)
      || "nextContract".equals(operationType)
      || "searchContract".equals(operationType)
    )
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
    Contract contract = null;

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

    try
    {
      contract = contractService.loadContractByContractCode(contractCode);
      if(contract != null)
      {
        List cfgContractList = contractService.loadCfgContractsByContractCode(contractCode);

        List cfgContractExtList = new ArrayList();

        if(cfgContractList != null)
        {
          for(int i=0; i<cfgContractList.size(); i++)
          {
            CfgContract cfgContract = (CfgContract)cfgContractList.get(i);

            CfgContract oldCfgContract = new CfgContract();
            EntityUtil.copyCfgContract(oldCfgContract, cfgContract);

            CfgContractExt cfgContractExt = new CfgContractExt();
            cfgContractExt.setCfgContract(cfgContract);
            cfgContractExt.setOldCfgContract(oldCfgContract);

            cfgContractExtList.add(cfgContractExt);

            ProductGroupSetUtil.createEditProductGroupSet(addContract, cfgContractExt, true);
            VesselTypeSetUtil.createEditVesselTypeSet(addContract, cfgContractExt, true);
          }
        }

        ContractUtil.sortCfgContractExtList(cfgContractExtList);
        addContract.setCfgContractExtList(cfgContractExtList);

        addContract.setActiveCfgContractIndex(ContractUtil.getActiveCfgContractIndex(addContract));
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    if(contract == null) contract = new Contract();
    if((contract.getInvoiceType()==null) || "".equals(contract.getInvoiceType().trim()))
    {
      contract.setInvoiceType(Constants.INV_TYPE_REG);
    }

    if((contract.getWorkingUOM()==null) || "".equals(contract.getWorkingUOM().trim()))
    {
      contract.setWorkingUOM(Constants.UOM_METRIC_TON_VALUE);
    }

    addContract.setContract(contract);

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

    boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateContract", "ContractCBHeader"});
    addContract.setViewOnly(editable == false);

    List referenceFieldList = contractService.getReferenceFieldsByContractId(contractCode);
    int size = referenceFieldList.size();
    if(size == 0)
    {
      String activeCurrencyCD = ContractUtil.getActiveCurrencyCD(addContract);
      if(activeCurrencyCD != null)
      {
        List pbIds = contractService.getPriceBookIdsByCurrencyCD(activeCurrencyCD);
        if(pbIds.size() > 0)
        {
          String pbId = (String)pbIds.get(0);
          List pbReferenceFieldList = contractService.getReferenceFieldsByContractId(pbId);
          addContract.setPbReferenceFieldList(pbReferenceFieldList);
          referenceFieldList = ContractUtil.copyReferenceFieldList(pbReferenceFieldList);
          size = referenceFieldList.size();
        }
      }
    }

    if(size < 5)
    {
      for(int i=size; i<5; i++)
      {
        referenceFieldList.add(ContractUtil.createReferenceField(i + 1));
      }
    }
    addContract.setReferenceFieldList(referenceFieldList);

    addContract.setContractSearch(getContractSearch(request));

    return addContract;
  }
}
