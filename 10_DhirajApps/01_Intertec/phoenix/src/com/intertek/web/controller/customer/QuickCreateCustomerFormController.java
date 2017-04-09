package com.intertek.web.controller.customer;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.cache.ITSCacheManager;
import com.intertek.domain.AddCustomerAddress;
import com.intertek.domain.QuickCreateCustomer;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Collector;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContactCustId;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractCustContactId;
import com.intertek.entity.ContractCustId;
import com.intertek.entity.Country;
import com.intertek.entity.CreditAnalyst;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddrSeqId;
import com.intertek.entity.CustAddress;
import com.intertek.entity.CustAddressLanguage;
import com.intertek.entity.Customer;
import com.intertek.entity.CustomerLanguage;
import com.intertek.entity.DropDowns;
import com.intertek.entity.JobContract;
import com.intertek.entity.Notes;
import com.intertek.entity.TaxRate;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.Field;
import com.intertek.meta.dropdown.impl.StaticDropDown;
import com.intertek.service.ContractService;
import com.intertek.service.CountryService;
import com.intertek.service.CustomerService;
import com.intertek.service.DropdownService;
import com.intertek.service.NotesService;
import com.intertek.service.SequenceNumberService;
import com.intertek.service.TaxService;
import com.intertek.service.UserService;
import com.intertek.statemachine.ExecutionContext;
import com.intertek.statemachine.StateMachine;
import com.intertek.statemachine.StateMachineManager;
import com.intertek.util.CommonUtil;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;
import com.intertek.web.propertyeditors.CustomPrimitiveNumberEditor;

public class QuickCreateCustomerFormController extends BaseSimpleFormController
//public class QuickCreateCustomerFormController extends SimpleFormController
{
  public QuickCreateCustomerFormController() {
    super();
  setSessionForm(true);
    setCommandClass(QuickCreateCustomer.class);
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

    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
  ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
  TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
  SequenceNumberService sequenceNumberService = (SequenceNumberService)ServiceLocator.getInstance().getBean("sequenceNumberService");
    QuickCreateCustomer quickCustomer = (QuickCreateCustomer)command;
  Customer customer=quickCustomer.getCustomer();
  Customer parentCustomer=customer.getParentCustomer();
  Contact contact=quickCustomer.getContact();
  ContactCust contactCust = quickCustomer.getContactCust();
  ContractCust contractCust = quickCustomer.getContractCust();
  ContractCustContact contractCustContact = quickCustomer.getContractCustContact();
  AddCustomerAddress[] addCustomerAddresses = quickCustomer.getAddCustomerAddresses();
  Contract contract = null;

  String existingCustomerFlag = request.getParameter("existingCustomerFlag");
  String existingContactFlag = request.getParameter("existingContactFlag");
  String existingLocationFlag = request.getParameter("existingLocationFlag");
  String existingContactTypeFlag=request.getParameter("existingContactTypeFlag");

 // Getting multilingual data from session
  HttpSession session = request.getSession();

  CustAddressLanguage custAddressLanguage = (CustAddressLanguage)session.getAttribute("customerAddrMultilingual");
  CustAddressLanguage contactAddressLanguage = (CustAddressLanguage)session.getAttribute("contactAddrMultilingual");
  CustomerLanguage customerLanguage = (CustomerLanguage)session.getAttribute("customermultilingual");
 //End
    String operationType = request.getParameter("operationType");
    try {
    if("changePriceBookId".equals(operationType))
    {
      CfgContract cfgContract = quickCustomer.getCfgContract();
      if(cfgContract != null)
      {
        String priceBookId = cfgContract.getPriceBookId();
        String priceBookSeries = contractService.getPriceBookSeriesNameByPriceBookId(priceBookId);
        if(priceBookSeries != null) cfgContract.setPbSeries(priceBookSeries);

        ContractUtil.setWorkingPriceBookIdByCfgContract(contractCust.getContract(), cfgContract);
      }

      return showForm(request, response, errors);
    }
    else if("changePriceBookSeries".equals(operationType))
    {
      CfgContract cfgContract = quickCustomer.getCfgContract();
      if(cfgContract != null)
      {
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

        ContractUtil.setWorkingPriceBookIdByCfgContract(contractCust.getContract(), cfgContract);
      }

      return showForm(request, response, errors);
    }
    else if("changeCurrencyCD".equals(operationType))
    {
      CfgContract cfgContract = quickCustomer.getCfgContract();
      if(cfgContract != null)
      {
        List pbSeriesList = contractService.getPriceBookSeriesByCurrencyCD(cfgContract.getCurrencyCD());
        if(pbSeriesList != null && pbSeriesList.size() > 0)
        {
          String pbSeries = (String)pbSeriesList.get(0);
          cfgContract.setPbSeries(pbSeries);
        }

        cfgContract.setPriceBookId(Constants.CURRENT);

        ContractUtil.setWorkingPriceBookIdByCfgContract(contractCust.getContract(), cfgContract);
      }

      return showForm(request, response, errors);
    }
    else if("changeUOM".equals(operationType))
    {
      CfgContract cfgContract = quickCustomer.getCfgContract();
      if(cfgContract != null)
      {
        contractCust.getContract().setWorkingUOM(cfgContract.getUom());
      }

      return showForm(request, response, errors);
    }

   if(customer.getParentCustomer() == null)
    {
      customer.setParentCustomer(parentCustomer);
    }
  } catch(ServiceException e)
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
  if(existingCustomerFlag != null && existingCustomerFlag.trim().equals("Y") && customer.getName() != null)
    {
    try {
     // customer = customerService.getCustomerByName(customer.getName());
      customer = customerService.getCustomerByNameAndCode(customer.getName());
      customer = customerService.loadCustomerByCustCode(customer.getCustCode());
      CustAddrSeq custAddrSeq = customerService.getCustAddrSeqByLocationNumber(new Integer("1"),customer.getCustCode());
      List contractCusts = customerService.getContractCustsByCustCode(customer.getCustCode());
      List custAddrSeqs = customerService.getCustAddrSeqsByCustCode(customer.getCustCode());
      Set contractCustSet = new HashSet();
      Set custAddrSet = new HashSet();
      
    //START: 135193 - To get Service Location and Date & save it
  	List<JobContract> lstServLocInfo = customerService.getServiceLocationInfo(customer.getCustCode());
  	if(null != lstServLocInfo && lstServLocInfo.size() > 0){
  		JobContract frstJobContract = (JobContract)lstServLocInfo.get(0);
  		customer.setLastServiceDate(frstJobContract.getJobOrder().getJobFinishDate());
		if(frstJobContract.getJobOrder().getBusinessUnit().getDescription() != null){
			customer.setLastServiceLocation(frstJobContract.getJobOrder().getBuName() + " - " + frstJobContract.getJobOrder().getBusinessUnit().getDescription());
		} else {
			customer.setLastServiceLocation(frstJobContract.getJobOrder().getBuName());
		}
  		customerService.updateServiceLocationInfo(customer);
  	}
  	//END: 135193 - To get Service Location and Date & save it 
      if(contractCusts != null && contractCusts.size() >0)
      {
        for(int i=0;i<contractCusts.size();i++)
        {
          contractCustSet.add((ContractCust)contractCusts.get(i));
        }
      }
      if(custAddrSeqs != null && custAddrSeqs.size() >0)
      {
        for(int i=0;i<custAddrSeqs.size();i++)
        {

          custAddrSet.add((CustAddrSeq)custAddrSeqs.get(i));
        }
      }
      customer.setContractCusts(contractCustSet);
      customer.setCustAddrSeqs(custAddrSet);
      CustAddress[] custAddresses = null;
      if(custAddrSeq != null)
        {
          List custAddrList = customerService.getCustAddressDetailsBySeqNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber(), custAddrSeq.getCustAddrSeqId().getCustCode());

          if(custAddrList != null && custAddrList.size() > 0)
          {
            custAddresses = new CustAddress[custAddrList.size()];
            for(int i=0;i<custAddrList.size();i++)
            {
              custAddresses[i] = (CustAddress) custAddrList.get(i);
            }
          }

        }
      else
      {
        custAddrSeq = new CustAddrSeq();
        addCustomerAddresses[0].setCustAddrSeq(custAddrSeq);
        custAddresses = new CustAddress[1];
        custAddresses[0] = new CustAddress();
      }
      addCustomerAddresses[0].setCustAddrSeq(custAddrSeq);
      addCustomerAddresses[0].setCustAddresses(custAddresses);
      quickCustomer.setAddCustomerAddresses(addCustomerAddresses);
      quickCustomer.setCustomer(customer);
       if(customer.getParentCustomer() == null)
        {
        customer.setParentCustomer(parentCustomer);
        }
      quickCustomer.setExistingCustomerFlag("none");
      quickCustomer.setCustomerExistsFlag("Y");
      return showForm(request, response, errors);
    } catch(ServiceException e)
    {
      quickCustomer.setExistingCustomerFlag("none");
        e.printStackTrace();
        errors.reject(e.getMessage(), e.getParams(), null);
        return showForm(request, response, errors);
    } catch(Throwable t)
    {
      quickCustomer.setExistingCustomerFlag("none");
        t.printStackTrace();
        errors.reject("generic.error", new Object[] {t.getMessage()}, null);
        return showForm(request, response, errors);
    }
    }

  if(existingContactFlag != null && existingContactFlag.trim().equals("Y") && contact.getId() != 0)
    {
   try {
      //contact = customerService.getContactById(contact.getId());
    long id=contact.getId();
     if(customer.getCustCode().trim().equalsIgnoreCase("New"))
     {
     contact = customerService.getContactById(contact.getId());
     }
     else{
       contact = customerService.getContactByIdandCustCode(contact.getId(),customer.getCustCode());
     if(contact==null){
       contact = customerService.getContactById(id);
         }
     }
      Set contactCusts = contact.getContactCusts();

      //Check if an association between the chosen customer & contact already exists
      if(quickCustomer.getCustomerExistsFlag() != null && quickCustomer.getCustomerExistsFlag().trim().equals("Y"))
      {
        if(contactCusts != null && contactCusts.size() >0)
        {
          Iterator iter = contactCusts.iterator();
          while(iter.hasNext())
          {
            ContactCust contactCustItem = (ContactCust) iter.next();
            if(contactCustItem.getContactCustId().getCustCode().equals(customer.getCustCode()))
            {
              quickCustomer.setContactAddrIndicator("existingAddr");
              quickCustomer.getContactCust().getCustAddrSeq().getCustAddrSeqId().setLocationNumber(contactCustItem.getContactCustId().getLocationNumber());
              quickCustomer.setContactCustExistsFlag("Y");
            }
            //newly added

            if(contactCustItem.getContactSeqNum()!=null)
            {
            //boolean existingseq=customerService.getExistingseqNumber(contact.getId(),contactCustItem.getContactCustId().getCustCode(),contactCustItem.getContactCustId().getLocationNumber());
            boolean existingseq=customerService.getExistingseqNumber(contact.getId(),customer.getCustCode(),contactCustItem.getContactCustId().getLocationNumber());
            if(existingseq==true){
              quickCustomer.getContactCust().setContactSeqNum(contactCustItem.getContactSeqNum());
             }
            }
             /* else
              {
                            List contacts=customerService.getContactSeqByCustCode(customer.getCustCode());

                  if(contacts!=null&&contacts.size()!=0)
                  {
                  quickCustomer.getContactCust().setContactSeqNum(contacts.size()+1);

                  }
                  else
                  {

                  quickCustomer.getContactCust().setContactSeqNum(Integer.valueOf(1));
                  }
              }*/
              //up to here
          }
        }
      }
      quickCustomer.setContact(contact);
      quickCustomer.setExistingContactFlag("none");
      quickCustomer.setContactExistsFlag("Y");
      return showForm(request, response, errors);
    } catch(ServiceException e)
    {
      quickCustomer.setExistingContactFlag("none");
        e.printStackTrace();
        errors.reject(e.getMessage(), e.getParams(), null);
        return showForm(request, response, errors);
    } catch(Throwable t)
    {
      quickCustomer.setExistingContactFlag("none");
        t.printStackTrace();
        errors.reject("generic.error", new Object[] {t.getMessage()}, null);
        return showForm(request, response, errors);
    }

    }
  if(existingLocationFlag != null && existingLocationFlag.trim().equals("Y") )
    {
      quickCustomer.setExistingLocationFlag("none");
      return showForm(request, response, errors);
    }
  
  if(existingContactTypeFlag!=null && existingContactTypeFlag.trim().equals("Y"))
  {		    
	  quickCustomer.setContactTypeExistsFlag("Y");
	  if(customer.getCustCode()!=null && !customer.getCustCode().trim().equals("")&& contact.getId()>0){
	    ContractCustContact contractCustContact1=null;
		contractCustContact1=customerService.getContactCustByContactidcustCodeandcontractCode(customer.getCustCode(),contact.getId(),contractCust.getContractCustId().getContractCode());
		if(contractCustContact1!=null){				
		quickCustomer.setContractCustContact(contractCustContact1);
		}
		else
		{  
			contractCustContact.setSchedulerContactFlag(false);
			contractCustContact.setBillContactFlag(false);
			contractCustContact.setReportContactFlag(false);
			quickCustomer.setContactTypeExistsFlag("Y");
			quickCustomer.setContractCustContact(contractCustContact);	
		}		
	}
	quickCustomer.setExistingContactTypeFlag("none");
	return showForm(request, response, errors);
  }
  
  
if(customer.getInterunitInd()!=null&&customer.getInterunitInd()==true)
 {

  if(customer.getInterunitBusUnitName()==null || customer.getInterunitBusUnitName().trim().equals(""))
  {
  /*if(customer.getInterunitBusUnitName()!=null && !customer.getInterunitBusUnitName().trim().equals(""))
   {
        if(customer.getInterunitBusUnitName().equalsIgnoreCase(customer.getCustCode()))
       {
        errors.reject("bu.custcode.same.error", new Object[] {customer.getCustCode()}, null);
       return showForm(request, response, errors);
       }
   }
   else
    {*/
       errors.reject("bu.require.customer.error", new Object[] {customer.getName()}, null);
       return showForm(request, response, errors);
     }
 }
 String busFlag=request.getParameter("busFlag");
    if("bus".equals(busFlag))
      {
        customer.setCustCode(customer.getInterunitBusUnitName());
        quickCustomer.setBusFlag("none");
      return showForm(request,response,errors);
    }

  if(customer.getCustCode().equalsIgnoreCase("new"))
    {
    Long custCodeSequence = sequenceNumberService.getSequenceNumber("CUSTOMER_SEQ");
    customer.setCustCode(custCodeSequence.toString());
    }

  if(quickCustomer.getSelectedContractIndicator() != null && (!quickCustomer.getSelectedContractIndicator().trim().equals("")) && (quickCustomer.getSelectedContractIndicator().equals("newContract")))
  {
    Date beginDate = quickCustomer.getCfgContract().getCfgContractId().getBeginDate();
    Date endDate = quickCustomer.getCfgContract().getEndDate();
    boolean hasError = false;

    if(beginDate == null)
    {
      errors.rejectValue("cfgContract.cfgContractId.beginDate", "not.blank");
      hasError = true;
    }
    else if(endDate == null)
    {
      errors.rejectValue("cfgContract.cfgContractId.endDate", "not.blank");
      hasError = true;
    }
    else
    {
      int dateResult = DateUtil.compareToInDate(beginDate, endDate);
      if(dateResult > 0)
      {
        errors.rejectValue("cfgContract.cfgContractId.beginDate", "invalid.date");
        hasError = true;
      }
    }

    String newContractCode = contractCust.getContract().getContractCode();
    if((newContractCode == null) || "".equals(newContractCode.trim()))
    {
      errors.rejectValue("contractCust.contract.contractCode", "not.blank");
      hasError = true;
    }
    else
    {
      Contract existedContract = contractService.getContractByContractCode(newContractCode);
      if(existedContract != null)
      {
        errors.rejectValue("contractCust.contract.contractCode", "duplicate");
        hasError = true;
      }
    }

    boolean validCode = ContractUtil.isContractCodeValid(newContractCode);
    if(!validCode)
    {
      errors.rejectValue("contractCust.contract.contractCode", "invalid");
      hasError = true;
    }

    if(hasError)
    {
      return showForm(request, response, errors);
    }

    contractCust.getContract().setStatus(Constants.STATUS_INPROCESS);
    contractCust.getContract().setStatusDate(new Date());
    contractCust.getContract().setExpireDate(new Date());
    contractCust.getContract().setMasterListDate(new Date());
    contractCust.getContract().setStatusDate(new Date());

    CfgContract cfgContract = quickCustomer.getCfgContract();
    if(cfgContract != null)
    {
      ContractUtil.setWorkingPriceBookIdByCfgContract(contractCust.getContract(), cfgContract);
      contractCust.getContract().setWorkingUOM(cfgContract.getUom());
    }

    String submitForApproval = request.getParameter("submitForApproval");
    if(Constants.Yes.equals(submitForApproval))
    {
      StateMachineManager stateMachineMgr = (StateMachineManager)ServiceLocator.getInstance().getBean("stateMachineMgr");
      StateMachine stateMachine = stateMachineMgr.getStateMachine("ContractStateMachine");
      if(stateMachine != null)
      {
        stateMachine.applyTransition(new ExecutionContext(), contractCust.getContract(), "Submit");
        contract = contractCust.getContract();
        contractService.saveContract(contract);
      }
    }
    else
    {
      contractService.addContract(contractCust.getContract());
    }

    contractCust.setContract(contractCust.getContract());
    contractCust.getContractCustId().setContractCode(contractCust.getContract().getContractCode());
    contractCust.setCustomer(customer);
    contractCust.setStatus(Constants.STATUS_A);
    contractCust.getContractCustId().setCustCode(customer.getCustCode());

    ContractUtil.setCfgContractDataFromContract(quickCustomer.getCfgContract(), contractCust.getContract());
    contractService.saveCfgContract(quickCustomer.getCfgContract());

    //customer.getContractCusts().add(contractCust);
  }
  if(quickCustomer.getSelectedContractIndicator() != null && (!quickCustomer.getSelectedContractIndicator().trim().equals("")) && (quickCustomer.getSelectedContractIndicator().equals("existingContract")))
  {
    if(contractCust.getContractCustId().getContractCode() != null && (!contractCust.getContractCustId().getContractCode().trim().equals("")))
      {

        Contract existingContract = contractService.getContractByContractCode(contractCust.getContractCustId().getContractCode());
        if(existingContract!=null)
        {
          contractCust.setContract(existingContract);
          contractCust.setCustomer(customer);
          contractCust.getContractCustId().setCustCode(customer.getCustCode());
          contractCust.setStatus(Constants.STATUS_A);
          //customer.getContractCusts().add(contractCust);
        }
        else
        {
             errors.reject("invalid.contractcode.error", new Object[] {contractCust.getContractCustId().getContractCode()}, null);
              return showForm(request, response, errors);
        }
      }

      /*else
      {
           errors.reject("create.customer.error", new Object[] {"Please select a contract code"}, null);
            return showForm(request, response, errors);
      }*/
    }

  //If customer Location Type is 04 (HQRT) It can not have any contacts
    if(customer.getLocationType()!=null && customer.getLocationType().equals(Constants.HQRT) && contact!=null && contact.getFirstName()!=null && !contact.getFirstName().trim().equals("")){
      errors.reject("customer.hq.contact.error" , new Object[] {customer.getCustCode()},null);
      return showForm(request, response, errors);
    }


   AddCustomerAddress[] addCustomerAddressItems = quickCustomer.getAddCustomerAddresses();
  //String contactAddrIndicator = request.getParameter("selectedAddrIndicator");
   String contactAddrIndicator="";
   if(quickCustomer.getContactAddrIndicator().trim().equals("existingAddr"))
   {  contactAddrIndicator = "existingAddr";}
   else
   { contactAddrIndicator = request.getParameter("selectedAddrIndicator");}

  int counter = 0;

      if (addCustomerAddressItems != null )
    {
        CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

      if(contactAddrIndicator == null || (contactAddrIndicator.trim().equals("primaryAddr")) || (contactAddrIndicator.trim().equals("existingAddr")))
        counter = addCustomerAddressItems.length -1 ;
      else
        counter = addCustomerAddressItems.length;

      int nextSeqNo = 0;
      Set addrSet = customer.getCustAddrSeqs();
      if(addrSet == null || addrSet.size() == 0)
        nextSeqNo = 1;
      else
      {
        Iterator addrIt = addrSet.iterator();
        while(addrIt.hasNext())
        {
          CustAddrSeq custAddrSeq = (CustAddrSeq) addrIt.next();
          if(custAddrSeq.getCustAddrSeqId().getLocationNumber() > nextSeqNo)
            nextSeqNo = custAddrSeq.getCustAddrSeqId().getLocationNumber();
        }
        nextSeqNo = nextSeqNo + 1;
      }
      for(int i=0; i< counter; i++)
      {
        CustAddrSeq custAddrSeq = addCustomerAddressItems[i].getCustAddrSeq();

        if(custAddrSeq.getCustAddrSeqId().getLocationNumber() == null || custAddrSeq.getCustAddrSeqId().getLocationNumber().toString().trim().equals(""))
        {

            custAddrSeq.getCustAddrSeqId().setLocationNumber(nextSeqNo++);

        custAddrSeq.getCustAddrSeqId().setCustCode(customer.getCustCode());
        custAddrSeq.setCustomer(customer);
        CustAddress[] custAddressItems = addCustomerAddressItems[i].getCustAddresses();


        for(int j=0; j< custAddressItems.length; j++)
        {
          CustAddress custAddress = custAddressItems[j];
          custAddress.setEffStatus(Constants.STATUS_A);
          custAddress.setCustAddrSeq(custAddrSeq);
          custAddress.setCustCode(customer.getCustCode());
          custAddress.setLocationNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber());

          Country country = countryService.getCountryByCode(custAddress.getCountry().trim());
          if(country==null){
              custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
              errors.reject("country.required.error", new Object[] {custAddress.getCountry()}, null);
              return showForm(request, response, errors);
          }
          else if(country.getVatByProvince() || (country.getStateAvail() && country.getStateRequiredInAddress())){
              if(custAddress.getState().trim().equals("")){
                custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
                errors.reject("state.required.error", new Object[] { custAddress.getCountry()}, null);
                return showForm(request, response, errors);
              }
          }

          if(custAddress.getCountry().trim().equalsIgnoreCase("USA")||custAddress.getCountry().trim().equalsIgnoreCase("CAN"))
          {
              if(custAddress.getAddress1().trim().equals("")&& custAddress.getCity().trim().equals("") && custAddress.getState().trim().equals(""))
            {
                        custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
            errors.reject("customer.address.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }


            if(custAddress.getAddress1().trim().equals(""))
            {
                        custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
            errors.reject("address.required.error", new Object[] {custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

            if(custAddress.getCity().trim().equals(""))
            {
                        custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
            errors.reject("city.required.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

            //if(custAddress.getState().trim().equals(""))
            //{
            //             custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
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
                custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
	            //errors.reject("address1.address2.city.required.error", new Object[] {custAddress.getCountry()}, null);
	            errors.reject("address1.city.required.error", new Object[] {custAddress.getCountry()}, null);
	            return showForm(request, response, errors);
            }

            if(custAddress.getAddress1().trim().equals(""))
            {
            custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
            errors.reject("address.required.error", new Object[] {custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

            if(custAddress.getCity().trim().equals(""))
            {
                        custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
            errors.reject("city.required.error", new Object[] {custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }
            // Commented due to itrack issue 108056
           /* if(custAddress.getAddress2().trim().equals(""))
            {
                         custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
            errors.reject("address2.required.error", new Object[] {custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }*/

          } else
          {
            if(custAddress.getAddress1().trim().equals("")&&  custAddress.getCity().trim().equals(""))
            {
            custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
            errors.reject("address.city.required.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

            if(custAddress.getAddress1().trim().equals(""))
            {
            custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
            errors.reject("address.required.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

            if(custAddress.getCity().trim().equals(""))
            {
                        custAddrSeq.getCustAddrSeqId().setLocationNumber(null);
            errors.reject("city.required.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

           }

          /*if(custAddress.getTaxCode() != null && (!custAddress.getTaxCode().trim().equals("")))
          {
            TaxRate taxRate = taxService.getTaxRateByCode(custAddress.getTaxCode());
            if(taxRate == null)
            {
                 errors.reject("create.customer.error", new Object[] {"Invalid Tax Code :"+custAddress.getTaxCode()}, null);
                  return showForm(request, response, errors);
            }
            else
            {
              //custAddress.setTaxRate(taxRate);
            }
          }
          else
          {
            custAddress.setTaxCode(null);
          }*/

          custAddrSeq.getCustAddresses().add(custAddress);
        }
        customer.getCustAddrSeqs().add(custAddrSeq);

        }
      }
    }


    try
    {
      customer.setBillTo("Y");
      customer.setSoldTo("Y");
      customer.setShipTo("N");
      // Setting isMultilingual flag
      if(customerLanguage != null)
      {
        customer.setIsMultilingual(true);
      }
      //end
      /*For iTrack#135193 -SRART */      
      if(customer != null && customer.getParentCustomer()!= null){
    	  
	      if(!customer.getName().trim().equalsIgnoreCase(customer.getParentCustomer().getName().trim())){
	    	  customer.setIsChildCustomer(true);
	      }
      }
      /*For iTrack#135193 -END */
      customer = customerService.addQuickCustomer(customer);
      /*For iTrack#135193 -SRART */
      List<Notes> customerNotesList =(List) session.getAttribute("CustomerNotes");
      if (customerNotesList != null )
      {
        for(int i=0; i< customerNotesList.size(); i++)
        {
          Notes custNote = customerNotesList.get(i);
          custNote.setNoteCode(customer.getCustCode());
          custNote.setNoteType("CUSTOMER");
        }
        NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");
        notesService.addNotes(customerNotesList);
        session.setAttribute("CustomerNotes",null);
      }
      /*For iTrack#135193 -END */
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
  /*  catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("create.customer.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/



if(contact != null && contact.getId() == 0 &&contact.getFirstName()!=null && !contact.getFirstName().trim().equals("")) //new contact
{
  try
    {
    contact.setStatus("1");
    CommonUtil commonUtil=null;
    if(contact.getWorkEmail()!= null && !(contact.getWorkEmail().equals("")))
  {
      commonUtil= new CommonUtil();
    boolean emailvalid =commonUtil.validateEmail(contact.getWorkEmail());
    if(emailvalid == false)
    {
      errors.reject("contact.workemail.error", new Object[] {contact.getWorkEmail() }, null);
      return showForm(request, response, errors);
    }

  }

  if(contact.getPersonalEmail()!= null && !(contact.getPersonalEmail().equals("")))
  {
    commonUtil= new CommonUtil();
    boolean emailvalid =commonUtil.validateEmail(contact.getPersonalEmail());
    if(emailvalid == false)
    {
      errors.reject("contact.personalemail.error", new Object[] {contact.getPersonalEmail() }, null);
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

//added for the issue-104407
 if(contact.getFax() != null && !(contact.getFax().equals(""))){
	 System.out.println("in controller........");
	 	commonUtil= new CommonUtil();
	    boolean faxvalid =commonUtil.validateFax(contact.getFax());
	    if(faxvalid == false)
	    {
	      errors.reject("contact.fax.error", new Object[] {contact.getFax() }, null);
	      return showForm(request, response, errors);
	    }
 }

// For iTrack issue 103082-START
	if(contact.getId() == 0){
		User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
		contact.setCreByUserName(user.getLoginName());
		contact.setCreatedTime(new Date());
	}
// For iTrack issue 103082-END   
 contact = customerService.addContact(contact);
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
   /* catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("create.customer.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/
}

if(contactCust != null)
{
  if(contact != null && contact.getId() > 0)
  {
  try {
    Contact existingContact = customerService.getContactById(contact.getId());
    if(existingContact == null)
    {
          errors.reject("contact.error", new Object[] {contact.getId()}, null);
          return showForm(request, response, errors);
    }
    else
    {
      if (contactAddrIndicator != null)
      {
        String oldlocationstr = request.getParameter("oldlocation");
        int oldlocation=0;
        if (oldlocationstr!=null && oldlocationstr.trim().length()>0 )
          oldlocation = Integer.parseInt(oldlocationstr);
        if(contactAddrIndicator.trim().equals("primaryAddr"))
        {

          /*new code for the email validation*/
           CommonUtil commonUtil=null;
              if(contact.getWorkEmail()!= null && !(contact.getWorkEmail().equals("")))
            {
                commonUtil= new CommonUtil();
              boolean emailvalid =commonUtil.validateEmail(contact.getWorkEmail());
              if(emailvalid == false)
              {
                errors.reject("contact.workemail.error", new Object[] {contact.getWorkEmail() }, null);
                return showForm(request, response, errors);
              }

            }

            if(contact.getPersonalEmail()!= null && !(contact.getPersonalEmail().equals("")))
            {
              commonUtil= new CommonUtil();
              boolean emailvalid =commonUtil.validateEmail(contact.getPersonalEmail());

              if(emailvalid == false)
              {
                errors.reject("contact.personalemail.error", new Object[] {contact.getPersonalEmail() }, null);
                return showForm(request, response, errors);
              }

              }

          /*up to here*/

          //start code
        CustAddrSeq custAddrSeq = null;
        custAddrSeq = customerService.getCustAddrSeqByLocationNumber(Integer.valueOf(1),customer.getCustCode());
        if(custAddrSeq == null)
           contactCust.setCustAddrSeq(new CustAddrSeq());
        else
           contactCust.setCustAddrSeq(custAddrSeq);
         //end
         // contactCust.setCustAddrSeq(customerService.getCustAddrSeqByLocationNumber(Integer.valueOf(1),customer.getCustCode()));
          contactCust.getContactCustId().setLocationNumber(Integer.valueOf(1));

        }
        else  if(contactAddrIndicator.trim().equals("existingAddr"))
        {
//          start code
        CustAddrSeq custAddrSeq = null;
        custAddrSeq = customerService.getCustAddrSeqByLocationNumber(contactCust.getCustAddrSeq().getCustAddrSeqId().getLocationNumber(),customer.getCustCode());
        if(custAddrSeq == null)
           contactCust.setCustAddrSeq(new CustAddrSeq());
        else
           contactCust.setCustAddrSeq(custAddrSeq);
        //end
         // contactCust.setCustAddrSeq(customerService.getCustAddrSeqByLocationNumber(contactCust.getCustAddrSeq().getCustAddrSeqId().getLocationNumber(),customer.getCustCode()));
          contactCust.getContactCustId().setLocationNumber(contactCust.getCustAddrSeq().getCustAddrSeqId().getLocationNumber());
        }
        else  if(contactAddrIndicator.trim().equals("newAddr"))
        {
          contactCust.setCustAddrSeq(addCustomerAddressItems[1].getCustAddrSeq());
          contactCust.getContactCustId().setLocationNumber(addCustomerAddressItems[1].getCustAddrSeq().getCustAddrSeqId().getLocationNumber());
          ContactCustId contactCustId = new ContactCustId(contact.getId(), customer.getCustCode(),addCustomerAddressItems[1].getCustAddrSeq().getCustAddrSeqId().getLocationNumber() );

          contactCust.setContactCustId(contactCustId);
        }

          contactCust.setStatus(Constants.STATUS_A);
          contactCust.setCustomer(customer);
          contactCust.getContactCustId().setCustCode(customer.getCustCode());

          contactCust.setContact(contact);
          contactCust.getContactCustId().setContactId(contact.getId());

          if(contactCust.getCustAddrSeq() == null)
              {
          contactCust.setCustAddrSeq(addCustomerAddressItems[0].getCustAddrSeq());
          contactCust.getContactCustId().setLocationNumber(Integer.valueOf(1));
            }

               /* boolean existingContacts=customerService.getContactCustomersByIdandCode(contactCust.getContactCustId().getContactId(),contactCust.getContactCustId().getCustCode(),contactCust.getContactCustId().getLocationNumber());
        if(existingContacts==true)
        {
        errors.reject("create.customer.error", new Object[] {"contact already exists for this customer"}, null);
        return showForm(request, response, errors);
        }
        else
                {*/

          boolean existingContacts=customerService.existCustContact(contactCust.getContactCustId().getContactId(), contactCust.getContactCustId().getCustCode());
          if(existingContacts==true)
          {
            contactCust.setContactSeqNum(contactCust.getContactSeqNum());
                     }
          else{
            List contacts=customerService.getContactSeqByCustCode(contactCust.getContactCustId().getCustCode());
            if(contacts!=null&&contacts.size()!=0)
            {
            contactCust.setContactSeqNum(contacts.size()+1);
            }
            else
            {
            contactCust.setContactSeqNum(Integer.valueOf(1));
            }
         }
         // contact.getContactCusts().add(contactCust);

          if(oldlocation>0){
             ContactCust oldContactCust = customerService.getContactCustByPK(contactCust.getContactCustId().getContactId(), contactCust.getContactCustId().getCustCode(), oldlocation);
             customerService.deleteContactCustByPK(contactCust.getContactCustId().getContactId(), contactCust.getContactCustId().getCustCode(), oldlocation);
             contact.getContactCusts().remove(oldContactCust);
           }
            List oldContactList=customerService.getContactCustListByContactid(contactCust.getContactCustId().getContactId());
            contact.getContactCusts().add(contactCust);

          if(oldContactList!=null && oldContactList.size()>0){
          Iterator oldContactListIt =  oldContactList.iterator();
          ContactCust contactCust1=null;
          while(oldContactListIt.hasNext())
          {
            contactCust1 = (ContactCust)oldContactListIt.next();
            contact.getContactCusts().add(contactCust1);
          }
          oldContactList.clear();
          }
          contact.setStatus(Constants.STATUS_A);
          customerService.saveContact(contact);
          customer = customerService.loadCustomerByCustCode(customer.getCustCode());
          List custAddrSeqs = customerService.getCustAddrSeqsByCustCode(customer.getCustCode());
          Set custAddrSet = new HashSet();
          if(custAddrSeqs != null && custAddrSeqs.size() >0)
          {
            for(int i=0;i<custAddrSeqs.size();i++)
            {

              custAddrSet.add((CustAddrSeq)custAddrSeqs.get(i));
            }
          }
          customer.setCustAddrSeqs(custAddrSet);
          }
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
  }
}

if(contractCust != null)
{
  if(contractCust.getContractCustId().getContractCode()!=null && !contractCust.getContractCustId().getContractCode().trim().equals(""))
    {
   if((contact.getId()==0||contact.getId()>0) && contact.getFirstName()!=null && !contact.getFirstName().trim().equals("")&&
    customer.getCustCode()!=null && !customer.getCustCode().trim().equals(""))
   {
  //ContractCustContact contractCustContact = new ContractCustContact(new ContractCustContactId());
 
  contractCustContact.setContact(contact);
  contractCustContact.getContractCustContactId().setContactId(contact.getId());

  contractCustContact.getContractCustContactId().setContractCode(contractCust.getContractCustId().getContractCode());
  contractCustContact.getContractCustContactId().setCustCode(customer.getCustCode());
  contractCustContact.setStatus(Constants.STATUS_A);

    try
    {
    ContractCust existingContractCust = contractService.getContractCustByCustCodeAndContractCode(customer.getCustCode(),contractCust.getContractCustId().getContractCode());
    if(existingContractCust == null)
    {
      contractCust.getContractCustId().setCustCode(customer.getCustCode());
      customerService.saveContractCust(contractCust);
      existingContractCust = contractService.getContractCustByCustCodeAndContractCode(customer.getCustCode(),contractCust.getContractCustId().getContractCode());
    }

    contractCustContact.setContractCust(existingContractCust);

    contractCustContact = contractService.addContractCustContact(contractCustContact);

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
  /*  catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("create.customer.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
     }*/
  }
  else
    {
    errors.reject("customer.contact.required.error", new Object[] {}, null);
     return showForm(request, response, errors);
    }
  }
}

//saving multilingual data

if(customerLanguage != null)
{
  //System.out.println("Saving CustomerLanguage process");
  customerLanguage.getCustomerLanguageId().setCustCode(customer.getCustCode());

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
if(custAddressLanguage != null)
{
  //System.out.println("Saving CustomerAddressLanguage process");

  /*if((custAddressLanguage.getCustAddressLanguageId().getCustAddressId()!= null &&
      (custAddressLanguage.getCustAddressLanguageId().getCustAddressId().trim().equals("0") ||
          custAddressLanguage.getCustAddressLanguageId().getCustAddressId().trim().equals("")))|| custAddressLanguage.getCustAddressLanguageId().getCustAddressId()== null)*/
  if(custAddressLanguage.getCustAddressLanguageId().getCustAddressId()== 0)
  {
    CustAddress custAddress = customerService.getCustAddresIdByCustCodeAndAddrSeqNumber(customer.getCustCode(),addCustomerAddressItems[0].getCustAddrSeq().getCustAddrSeqId().getLocationNumber());

    if(custAddress != null )
    {
      custAddressLanguage.getCustAddressLanguageId().setCustAddressId(custAddress.getId());
    }
  }
    try
    {
      custAddressLanguage = customerService.addCustomerAddrLanguage(custAddressLanguage);
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
if(contactAddressLanguage != null)
{
  //System.out.println("Saving contacAddressLanguage process");

  /*if((contactAddressLanguage.getCustAddressLanguageId().getCustAddressId()!= null &&
      (contactAddressLanguage.getCustAddressLanguageId().getCustAddressId().trim().equals("0") ||
          contactAddressLanguage.getCustAddressLanguageId().getCustAddressId().trim().equals("")))|| contactAddressLanguage.getCustAddressLanguageId().getCustAddressId()== null)*/
  if(contactAddressLanguage.getCustAddressLanguageId().getCustAddressId()== 0)
  {
    CustAddress custAddress  = customerService.getCustAddresIdByCustCodeAndAddrSeqNumber(customer.getCustCode(),addCustomerAddressItems[1].getCustAddrSeq().getCustAddrSeqId().getLocationNumber());

    if(custAddress != null )
    {
      contactAddressLanguage.getCustAddressLanguageId().setCustAddressId(custAddress.getId());
    }
  }
    try
    {
      contactAddressLanguage = customerService.addCustomerAddrLanguage(contactAddressLanguage);
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
//end

  if(customer.getCustCode()!=null&&!customer.getCustCode().trim().equals("") && (contact.getId()==0 || contact.getId()>0) &&
  contractCust.getContractCustId().getContractCode()!=null&& !contractCust.getContractCustId().getContractCode().trim().equals(""))
  {
  ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "edit_customer.htm?reqFrom=quickCustomerForm&customer.custCode=" + customer.getCustCode());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this customer.");
    modelAndView.addObject("description", "Your customer has been successfully created.");
    return modelAndView;
  }
  else
    {
    quickCustomer.setExistingCustomerFlag("Y");
  quickCustomer.setCustomerExistsFlag("Y");
  if(contact.getFirstName()!=null && !contact.getFirstName().trim().equals(""))
  {quickCustomer.setContactExistsFlag("Y");
  quickCustomer.setExistingContactFlag("Y");
  }
  quickCustomer.setContact(contact);
  quickCustomer.setCustomer(customer);
    return showForm(request, response, errors);
  }
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

    CustomDiscountEditor customerDiscountEditor = new CustomDiscountEditor(Double.class, nf, new Double(-1));
    binder.registerCustomEditor(java.lang.Double.class, "cfgContract.annualEscalator", customerDiscountEditor);
    binder.registerCustomEditor(java.lang.Double.class, "cfgContract.cfgDiscountPercent", customerDiscountEditor);
    binder.registerCustomEditor(java.lang.Double.class, "cfgContract.inspectionDiscountPercent", customerDiscountEditor);
    binder.registerCustomEditor(java.lang.Double.class, "cfgContract.testDiscount", customerDiscountEditor);
    binder.registerCustomEditor(java.lang.Double.class, "cfgContract.slateDiscount", customerDiscountEditor);
    binder.registerCustomEditor(java.lang.Double.class, "cfgContract.opsDiscountPercent", customerDiscountEditor);
    binder.registerCustomEditor(java.lang.Double.class, "cfgContract.labDiscountPercent", customerDiscountEditor);
  }

   protected boolean suppressValidation(HttpServletRequest request,Object command)
  {
  String existingCustomerFlag = request.getParameter("existingCustomerFlag");
  String existingContactFlag = request.getParameter("existingContactFlag");
  String existingLocationFlag = request.getParameter("existingLocationFlag");
  String existingContactTypeFlag = request.getParameter("existingContactTypeFlag");

    if((existingCustomerFlag != null) && ("Y".equals(existingCustomerFlag) ))
    {
      return true;
    }
    if((existingContactFlag != null) && ("Y".equals(existingContactFlag) ))
    {
      return true;
    }
    if((existingLocationFlag != null) && ("Y".equals(existingLocationFlag) ))
    {
      return true;
    }
    
    if(existingContactTypeFlag!=null && "Y".equals(existingContactTypeFlag))
    {
    	return true;
    }
   
  String busFlag=request.getParameter("busFlag");
    if((busFlag!=null)&&("bus".equals(busFlag)))
    {
      return true;
  }

    return super.suppressValidation(request);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    // Setting session multilingual objects null
    HttpSession session = request.getSession();
    if(session != null)
    {
      CustomerLanguage customerLanguage = (CustomerLanguage)session.getAttribute("customermultilingual");
      if(customerLanguage != null)
        session.setAttribute("customermultilingual",null);
      CustAddressLanguage custAddressLanguage =(CustAddressLanguage) session.getAttribute("customerAddrMultilingual");
      if(custAddressLanguage != null)
        session.setAttribute("customerAddrMultilingual",null);
      CustAddressLanguage contactAddressLanguage = (CustAddressLanguage)session.getAttribute("contactAddrMultilingual");
      if(contactAddressLanguage != null)
        session.setAttribute("contactAddrMultilingual",null);
    }
    //end
    QuickCreateCustomer quickCustomer = new QuickCreateCustomer();
    User user= null;
    Customer customer = new Customer();
    int countAddrSeq = 2;
    try {
    AddCustomerAddress[] addCustomerAddresses = new AddCustomerAddress[countAddrSeq];
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    user=userService.getUserByName(SecurityUtil.getUser().getLoginName());
    int intYear = 103;
    int intMon = 0;
    int intDate = 1;

    for(int i=0; i<countAddrSeq; i++)
    {
      AddCustomerAddress addCustomerAddress = new AddCustomerAddress();
      CustAddrSeq custAddrSeq = new CustAddrSeq(new CustAddrSeqId());
      CustAddress[] custAddresses = new CustAddress[1];
      custAddresses[0] = new CustAddress();
      TaxRate taxRate = new TaxRate();
//      custAddresses[0].setTaxRate(taxRate);
      custAddresses[0].setCountry(user.getCountryCode());
       Date effDate = new Date(intYear,intMon,intDate);
        if(custAddresses[0].getEffDate()==null)
        custAddresses[0].setEffDate(effDate);
      custAddresses[0].setInCityLimit(true);

    custAddrSeq.setSoldTo(true);
    custAddrSeq.setBillTo(true);
    custAddrSeq.setShipTo(false);
    custAddrSeq.setCorresponence(true);

      addCustomerAddress.setCustAddrSeq(custAddrSeq);
      addCustomerAddress.setCustAddresses(custAddresses);
      addCustomerAddresses[i] = addCustomerAddress;
    }


    Customer parentCustomer = new Customer();
    BusinessUnit interUnitBU = new BusinessUnit();
    CreditAnalyst creditAnalyst = new  CreditAnalyst();
    Collector collector           = new Collector();
    Contact contact = new Contact();
    Contract contract = new Contract();

    contract.setCreatedTime(new Date());
    contract.setUpdatedTime(new Date());
    if(SecurityUtil.getUser() != null)
    {
      contract.setCreByUserName(SecurityUtil.getUser().getLoginName());
      contract.setModByUserName(SecurityUtil.getUser().getLoginName());
    }

    contract.setInvoiceType(Constants.INV_TYPE_REG);
    ContactCust contactCust = new ContactCust(new ContactCustId());
    ContractCust contractCust = new ContractCust(new ContractCustId());
    contractCust.setContract(contract);
    ContractCustContact contractCustContact = new ContractCustContact(new ContractCustContactId());
    CustAddrSeq custAddrSeq=new CustAddrSeq(new CustAddrSeqId());
    custAddrSeq.setSoldTo(true);
    custAddrSeq.setBillTo(true);
    custAddrSeq.setShipTo(false);
    custAddrSeq.setCorresponence(true);
    contactCust.setCustAddrSeq(custAddrSeq);
    quickCustomer.setContact(contact);
      customer.setStatus("A");
      customer.setCustCode("New");
    //customer.setCreditApproved(true);
    customer.setPaymentType("CRE");
    customer.setPrimaryBillToAddress(1);
    customer.setPrimarySoldToAddress(1);
       customer.setCurrencyCd(user.getCurrencyCd());
      customer.setCreditAnalystName("CREDIT");
      customer.setCollectorName("COLLECT");
    customer.setInvoiceType(Constants.INV_TYPE_REG);

    Date addDate = new Date();
  if(customer.getAddDate()==null)
  customer.setAddDate(addDate);

  Date sinceDate = new Date(intYear,intMon,intDate);
  if(customer.getSinceDate()==null)
    customer.setSinceDate(sinceDate);

      customer.setParentCustomer(parentCustomer);
    /*For iTrack#135193 -END Setting default values*/
      customer.setVipPlatinumPreferred(Constants.TIER2);
      contractCustContact.setInvoiceLabelLanguage(Constants.ENGLISH);
      contractCustContact.setInvoiceLineItemLanguage(Constants.ENGLISH);
      customer.setCreditApproved(true);
      customer.setPaymentType(Constants.OAC);
    /*For iTrack#135193 -END */
    quickCustomer.setCustomer(customer);
    quickCustomer.setContact(contact);
    quickCustomer.setContactCust(contactCust);
    quickCustomer.setContractCust(contractCust);
    quickCustomer.setContractCustContact(contractCustContact);   
    quickCustomer.setAddCustomerAddresses(addCustomerAddresses);

    CfgContract cfgContract = ContractUtil.createInitCfgContract();
    cfgContract.getCfgContractId().setBeginDate(
      DateUtil.parseDate("01/01/2003", "MM/dd/yyyy")
    );
    quickCustomer.setCfgContract(cfgContract);
    ContractUtil.setWorkingPriceBookIdByCfgContract(contract, cfgContract);
    } catch(ServiceException e)
    {
      throw new ServiceException(e.getMessage(), e.getParams(), e);
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return quickCustomer;
  }
}
