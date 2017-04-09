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
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddContract;
import com.intertek.domain.AddContractCust;
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
import com.intertek.service.NotesService;
import com.intertek.service.TestService;
import com.intertek.service.UserService;
import com.intertek.service.ZoneService;
import com.intertek.statemachine.ExecutionContext;
import com.intertek.statemachine.StateMachine;
import com.intertek.statemachine.StateMachineManager;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.util.ProductGroupSetUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.VesselTypeSetUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractCustomerFormController extends BaseEditContractFormController
{
  public EditContractCustomerFormController() {
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

    AddContractCust[] contractCustItems = addContract.getAddContractCusts();

    String addOrDelete=request.getParameter("addOrDelete");
    String addOrDeleteContractContact=request.getParameter("addOrDeleteContractContact");
    String contractCustIndex = request.getParameter("contractCustIndex");
    String contractCustContactIndex = request.getParameter("contractCustContactIndex");
    String contactSearchFlag = request.getParameter("contactSearchFlag");

  //START: To fix the issue 99933
    String contactcustFlag=request.getParameter("loadCustContflag");
  //END: To fix the issue 99933

    String contSeqFlag=request.getParameter("contSeqFlag");
    HttpSession session = request.getSession();

  //START: To fix the issue 99933


    if("contadd".equals(contactcustFlag))
    {
        String pageNumberStr = request.getParameter("pageNum");
        try {
        	AddContractCust[] addContractCusts = null;
        	if((addContract.getSearchCustomerId() == null ||addContract.getSearchCustomerId().trim().equals("")) 
        			&& (addContract.getSearchCustomerName() == null || addContract.getSearchCustomerName().trim().equals(""))
        			&& (addContract.getSearchContactId() == null || addContract.getSearchContactId().trim().equals(""))
        			&& (addContract.getSearchContactName() == null || addContract.getSearchContactName().trim().equals(""))){

        		addContractCusts = getContractCustPaginationData(request,
        		          contractService,addContract,contract.getContractCode(),pageNumberStr);
        		        addContract.setAddContractCusts(addContractCusts); 
        	} else {
        		addContractCusts = getContractCustomersBySearchCriteria(request,
        				contractService,addContract,contract.getContractCode(),pageNumberStr);
        	}
          if(addContractCusts != null && addContractCusts.length == 0){
        	  addContract.setAddContractCusts(addContractCusts);
        	  errors.reject("contract.customer.search.error");
              return showForm(request, response, errors);
          }
          addContract.setAddContractCusts(addContractCusts);
          //addContract.setLoadCustContflag("none");
      } catch(Exception e) {
          e.printStackTrace();
      }
      return showForm(request, response, errors);
    }
  //END: To fix the issue 99933

    if("custval".equals(contSeqFlag))
    {
      String rowNo=request.getParameter("rowNum");
      int num=Integer.parseInt(rowNo);
      //  AddContractCust[] contractCustItems = addContractCust.getAddContractCusts();
      //  ContractCust contactCust=new ContractCust();
      //Customer customer=new Customer();
      AddContractCust addContractCust = contractCustItems[num];
      ContractCust contractCust = addContractCust.getContractCust();

      Customer customer = customerService.getCustomerByCustCode(contractCust.getContractCustId().getCustCode());
      //customer=customerService.getCustomerByCustCode(contactCustItems[num].getContractCust().getContractCustId().getCustCode());
      contractCust.setCustomer(customer);
      contractCust.getContractCustId().setCustCode(customer.getCustCode());
      addContractCust.setContractCust(contractCust);
      contractCustItems[num] = addContractCust;
      addContract.setAddContractCusts(contractCustItems);
      addContract.setContSeqFlag("none");
      addContract.setTabName("1");
      /* For itrack issue 99933 to keep pagination*/
      if(session != null)
      {
        Pagination  sessPagination = (Pagination)session.getAttribute("ContractCustomersPagination");
        Pagination  reqPagination = (Pagination) request.getAttribute("pagination");
        if(reqPagination == null){
            if(sessPagination != null){
              request.setAttribute("pagination",sessPagination);
            }
        }
      }//up to here
      return showForm(request, response, errors);
    }

    if("newval".equals(contactSearchFlag))
    {
      String rowNo=request.getParameter("rowNum");
      String contactRowNo = request.getParameter("rowNumContact");
      int num=Integer.parseInt(rowNo);
      int contactNum = Integer.parseInt(contactRowNo);


      AddContractCust addContractCust = contractCustItems[num];
      ContractCustContact  contractCustContact = addContractCust.getContractCustContacts()[contactNum];

      //Contact contact = new Contact();

      Contact contact=customerService.getContactById(contractCustContact.getContractCustContactId().getContactId());
      if(contact!=null)
      {
      String name = contact.getFirstName() +" "+contact.getLastName();
      contact.setFirstName(name);
      contractCustContact.setContact(contact);
      }
      else
              {contractCustContact.setContact(new Contact());}
      addContractCust.getContractCustContacts()[contactNum] = contractCustContact;

      contractCustItems[num] = addContractCust;
      addContract.setAddContractCusts(contractCustItems);
      addContract.setContactSearchFlag("none");

      addContract.setTabName("1");
      // For itrack issue 99933 to keep pagination
      if(session != null)
      {
        Pagination  sessPagination = (Pagination)session.getAttribute("ContractCustomersPagination");
        Pagination  reqPagination = (Pagination) request.getAttribute("pagination");
        if(reqPagination == null){
            if(sessPagination != null) {
              request.setAttribute("pagination",sessPagination);
            }
        }
      }//up to here
      return showForm(request, response, errors);
    }

    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      if("add".equals(addOrDelete))
      {
        addContract.setAddContractCusts(addiContractCust(addContract.getAddContractCusts()));
      }
      else
      {
        contractService.deleteContractCustAssociations(addContract.getAddContractCusts()[addContract.getIndex()].getContractCust());
        addContract.setAddContractCusts(delContractCust(addContract.getAddContractCusts(), addContract.getIndex()));
      }
      addContract.setContractCustCount(addContract.getAddContractCusts().length);
      addContract.setAddOrDelete("none");
      addContract.setTabName("1");
      // For itrack issue 99933 to keep pagination
      if(session != null)
      {
        Pagination  sessPagination = (Pagination)session.getAttribute("ContractCustomersPagination");
        Pagination  reqPagination = (Pagination) request.getAttribute("pagination");
        if(reqPagination == null){
            if(sessPagination != null){
              request.setAttribute("pagination",sessPagination);
            }
        }
      }//up to here
      return showForm(request, response, errors);
    }

    // For adding/deleting contacts under contract custs

    if((addOrDeleteContractContact != null) && ("addContact".equals(addOrDeleteContractContact) || "deleteContact".equals(addOrDeleteContractContact)))
    {
      int intContractCustIndex = Integer.parseInt(contractCustIndex);
      if("addContact".equals(addOrDeleteContractContact))
      {
        AddContractCust[] addContractCusts = addContract.getAddContractCusts();
        AddContractCust addContractCust = addContractCusts[intContractCustIndex];
        addContractCust.setContractCustContacts(addContractCustContact(addContractCust.getContractCustContacts()));
        //   addContractCust.setCustAddressCount(addCustomerAddress.getCustAddresses().length);

        addContractCusts[intContractCustIndex] = addContractCust;
        addContract.setAddContractCusts(addContractCusts);
      }
      else
      {
         int intContractCustContactIndex = Integer.parseInt(contractCustContactIndex);

        AddContractCust[] addContractCusts = addContract.getAddContractCusts();
        AddContractCust addContractCust = addContractCusts[intContractCustIndex];
        contractService.deleteContractCustContactAssociations(addContractCust.getContractCustContacts()[intContractCustContactIndex]);
        addContractCust.setContractCustContacts(deleteContractCustContact(addContractCust.getContractCustContacts(),intContractCustContactIndex));

        //addCustomerAddress.setCustAddressCount(addCustomerAddress.getCustAddresses().length);

        addContractCusts[intContractCustIndex] = addContractCust;
        addContract.setAddContractCusts(addContractCusts);
      }
      addContract.setAddOrDeleteContractContact("none");
      addContract.setTabName("1");
      // For itrack issue 99933 to keep pagination
     if(session != null)
      {
        Pagination  sessPagination = (Pagination)session.getAttribute("ContractCustomersPagination");
        Pagination  reqPagination = (Pagination) request.getAttribute("pagination");
        if(reqPagination == null){
            if(sessPagination != null) {
              request.setAttribute("pagination",sessPagination);
            }
        }
      }//up to here
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
      if ("saveContract".equals(operationType) && (contractCustItems != null ) && (!addContract.getViewOnly()))
      {
        for(int i=0; i< contractCustItems.length; i++)
        {
          AddContractCust addContractCust = contractCustItems[i];
          ContractCust contractCust = addContractCust.getContractCust();
          if(contractCust.getContractCustId().getCustCode() != null && (!contractCust.getContractCustId().getCustCode().trim().equals("")))
          {

            Customer existingCustomer = customerService.getCustomerByCustCode(contractCust.getContractCustId().getCustCode());
            if(existingCustomer != null)
            {
              ContractCustContact[] contractCustContactItems = contractCustItems[i].getContractCustContacts();

              contractCust.setContract(contract);
              contractCust.getContractCustId().setContractCode(contract.getContractCode());
              Customer customer = customerService.getCustomerByCustCode(contractCust.getContractCustId().getCustCode());
              contractCust.setCustomer(customer);
              //customer = customerService.loadCustomerByCustCode(contractCust.getContractCustId().getCustCode());

              //customer = customerService.loadContractCustsByCustCode(contractCust.getContractCustId().getCustCode());
              //customer.getContractCusts().add(contractCust);
              customerService.saveContractCust(contractCust);

              for(int j=0; j< contractCustContactItems.length; j++)
              {
                ContractCustContact contractCustContact = contractCustContactItems[j];
                if(contractCustContact.getContractCustContactId().getContactId() > 0)
                {
                  Contact existingContact = customerService.getContactById(contractCustContact.getContractCustContactId().getContactId());
                  if(existingContact != null)
                  {
                    contractCustContact.getContractCustContactId().setCustCode(customer.getCustCode());
                    contractCustContact.getContractCustContactId().setContractCode(contract.getContractCode());
                    contractCustContact.setContractCust(contractService.getContractCustByCustCodeAndContractCode(customer.getCustCode(),contract.getContractCode()));
                    contractCustContact.setContact(customerService.getContactById(contractCustContact.getContractCustContactId().getContactId()));
                    contractCustContact = contractService.addContractCustContact(contractCustContact);
                  }
                  else
                  {
                    errors.reject("edit.contract.error", new Object[] {"Invalid Contact Id : "+contractCustContact.getContractCustContactId().getContactId()}, null);
                    return showForm(request, response, errors);
                  }
                }
                else
                {
                  errors.reject("edit.contract.error", new Object[] {"Invalid Contact Id : "+contractCustContact.getContractCustContactId().getContactId()}, null);
                  return showForm(request, response, errors);
                }
              }
            }
            else
            {
              errors.reject("edit.contract.error", new Object[] {"Invalid Customer Code : "+contractCust.getContractCustId().getCustCode()}, null);
              return showForm(request, response, errors);
            }
          }
        }
      }

      ModelAndView modelAndView = getModelAndView(
        operationType,
        request.getParameter("goToContractTab"),
        "customer",
        contract,
        addContract.getContractSearch(),
        errors
      );
      if(modelAndView == null) return showForm(request, response, errors);
      // For itrack issue 99933 to clear ContractCustomersPagination from session
      if(session != null){
        session.setAttribute("ContractCustomersPagination",null);
      }
      //up to here
      return modelAndView;
    }
    // For itrack issue 99933 to improve pageNavigate performance
    String pageNavigate = request.getParameter("pageNavigateFlag");
    if(pageNavigate.equals("true")){
      String contractCode = request.getParameter("ctrCode");
      String pageNumberStr = request.getParameter("pageNum");
      try {
        AddContractCust[] addContractCusts = getContractCustPaginationData(request,
          contractService,addContract,contractCode,pageNumberStr);
        addContract.setAddContractCusts(addContractCusts);
        boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateContract","ContractCustomerTab"});
          addContract.setViewOnly(editable == false);
          addContract.setContractSearch(getContractSearch(request));
        addContract.setPageNavigateFlag("false");
      } catch(Exception e){
          e.printStackTrace();
        }
      return showForm(request, response, errors);
    }//up to here
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
    String addOrDelete = request.getParameter("addOrDelete");
    String loadCustContflag = request.getParameter("loadCustContflag");
    if(loadCustContflag!=null && "contadd".equalsIgnoreCase(loadCustContflag)){
      return true;
    }

    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      return true;
    }
    String addOrDeleteContractContact = request.getParameter("addOrDeleteContractContact");
    if((addOrDeleteContractContact != null) && ("addContact".equals(addOrDeleteContractContact) || "deleteContact".equals(addOrDeleteContractContact)))
    {
      return true;
    }

    String contSeqFlag=request.getParameter("contSeqFlag");

    if((contSeqFlag != null) && ("custval".equals(contSeqFlag)))
    {
      return true;
    }
    String contactSearchFlag=request.getParameter("contactSearchFlag");

    if((contactSearchFlag != null) && ("newval".equals(contactSearchFlag)))
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

    try
    {
      contract = contractService.loadContractByContractCode(contractCode);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    if(contract == null) contract = new Contract();
    if(contract.getInvoiceType()==null)
    {
      contract.setInvoiceType("Reg");
    }
    addContract.setContract(contract);

    String pageNumberStr = request.getParameter("pageNumber");
 //START: To fix the issue 99933
    //pagination for customer tab
 try {
    AddContractCust[] addContractCusts = getContractCustPaginationData(request,
          contractService,addContract,contractCode,pageNumberStr);
       addContract.setAddContractCusts(addContractCusts);
 } catch(Exception e) {
      e.printStackTrace();
 }
//END: To fix the issue 99933
    boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateContract","ContractCustomerTab"});
    addContract.setViewOnly(editable == false);

    addContract.setContractSearch(getContractSearch(request));
    //addContract.setContactSearch(getContactSearch(request));
   // addContract.setCustomerSearch(getCustomerSearch(request));
    return addContract;
  }
   /**
   * Name :getContractCustPaginationData
   * Date :April 13, 2009
   * Purpose : To get ContractCustomers pagination data
   * @param request
   * @param contractService
   * @param addContract
   * @param contractCode
   * @param pageNumberStr
   * @return AddContractCust
   */
  private AddContractCust[] getContractCustPaginationData(HttpServletRequest request,ContractService contractService,AddContract addContract,String contractCode,String pageNumberStr){

        int pageNumber = 1;
        if(pageNumberStr != null && pageNumberStr.trim().length()>0){
          pageNumber = Integer.parseInt(pageNumberStr);
          addContract.setTabName("1");
        }
        if(pageNumber <= 0) { pageNumber = 1; }
        int resultCount = contractService.getContractCustsCountByContractCode(contractCode);
        Pagination pagination = new Pagination(resultCount,20, pageNumber, 10);
        pagination.calculatePages();
        request.setAttribute("pagination", pagination);
        HttpSession session = request.getSession();
        if(session != null){
          session.setAttribute("ContractCustomersPagination",pagination);
        }
        List contractCusts = contractService.getContractCustsByContractCode(contractCode, pagination);
        AddContractCust[] addContractCusts = new AddContractCust[contractCusts.size()];
        //103082
        String[] customerNotes = new String[contractCusts.size()];
        //103082
        for(int i =0;i<contractCusts.size();i++) {
          ContractCust contractCust = (ContractCust) contractCusts.get(i);
          /*For iTrack #103082 START*/
          NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");
          List custNotesList = notesService.getNotes(contractCust.getCustomer().getCustCode(),"CUSTOMER");
          if(custNotesList != null && custNotesList.size()>0){
        	  customerNotes[i]= "T";
          } else {
        	  customerNotes[i]= "F";
          }
          /*For iTrack #103082 END*/
          AddContractCust addContractCust = new AddContractCust();
          addContractCust.setContractCust(contractCust);

          List contractCustContactList = contractService.getContractCustContactsByCustCodeAndContractCode(contractCust.getContractCustId().getCustCode(), contractCust.getContractCustId().getContractCode());
          ContractCustContact[] contractCustContacts = new ContractCustContact[contractCustContactList.size()];
          for(int j =0;j<contractCustContactList.size();j++) {
            ContractCustContact contractCustContact = (ContractCustContact) contractCustContactList.get(j);
                      contractCustContact.getContact().setFirstName(contractCustContact.getContact().getFirstName()+" "+contractCustContact.getContact().getLastName());
            //up to here
            contractCustContacts[j] = contractCustContact;
          }
          addContractCust.setContractCustContacts(contractCustContacts);
          addContractCusts[i] = addContractCust;
        }
        //103082
        addContract.setCustomerNotes(customerNotes);
        //103082
        return addContractCusts;

        //START: Issue # 99933
 	    /* boolean isContactIdExists = false;
 	     if(null != addContract.getSearchContactId() &&
 	    		addContract.getSearchContactId().trim().length() > 0){
 	    	isContactIdExists = true;
 	     }
 	     
 	     List contractCusts = null; 	    
 	     if(null != addContract.getSearchCustomerId() && addContract.getSearchCustomerId().trim().length() > 0){
 	    	ContractCust contractCust = contractService.loadContractCustByCustCodeContract(addContract.getSearchCustomerId(),contractCode); 	    	
 	    	if(null != contractCust){
 	    		contractCusts = new ArrayList();
 	    		contractCusts.add(contractCust);
 	    	}
 	     }
 	     else if(null != addContract.getSearchContactId() && addContract.getSearchContactId().trim().length() > 0){
 	    	contractCusts = contractService.getContractCustsByContractCode(contractCode, null);
 	     }
 	     
 	    AddContractCust[] addContractCusts = null;
 	    List tempAddContractCusts = new ArrayList();
 	    List tempContractCustContacts = null;
 	    if(null != contractCusts){	 	    
 	     addContractCusts = new AddContractCust[contractCusts.size()];
	 	     	for(int i =0;i<contractCusts.size();i++) {	 	     	  
		 	      ContractCust contractCust = (ContractCust) contractCusts.get(i);
		 	      AddContractCust addContractCust = new AddContractCust();
		 	      addContractCust.setContractCust(contractCust);
		 	      List contractCustContactList = contractService.getContractCustContactsByCustCodeAndContractCode(contractCust.getContractCustId().getCustCode(), contractCust.getContractCustId().getContractCode());
		 	      ContractCustContact[] contractCustContacts = new ContractCustContact[contractCustContactList.size()];
		 	      tempContractCustContacts = new ArrayList();
		 	      for(int j =0;j<contractCustContactList.size();j++) {
			 	      ContractCustContact contractCustContact = (ContractCustContact) contractCustContactList.get(j);
			 	      if(isContactIdExists && 
			 	    		 !addContract.getSearchContactId().equals(String.valueOf(contractCustContact.getContact().getId()))){
			 	    	  continue;
			 	      }			 	      
			 	      
			 	      contractCustContact.getContact().setFirstName(contractCustContact.getContact().getFirstName()+" "+contractCustContact.getContact().getLastName());
			 	      //up to here
			 	      //contractCustContacts[j] = contractCustContact;
			 	     tempContractCustContacts.add(contractCustContact);
		 	      }
		 	      if(tempContractCustContacts.size() > 0){
		 	    	  contractCustContacts = null;
		 	    	  contractCustContacts = new ContractCustContact[tempContractCustContacts.size()];
		 	    	  for(int k=0;k<tempContractCustContacts.size();k++){
		 	    		 contractCustContacts[k] = (ContractCustContact)tempContractCustContacts.get(k);
		 	    	  }
			 	      addContractCust.setContractCustContacts(contractCustContacts);
			 	      //addContractCusts[i] = addContractCust;	
			 	      tempAddContractCusts.add(addContractCust);
		 	      } 
		 	     tempContractCustContacts = null;
	 	      }
	 	     addContractCusts = null; 
	 	     addContractCusts = new AddContractCust[tempAddContractCusts.size()];
	 	     for(int l=0;l<tempAddContractCusts.size();l++){
	 	    	addContractCusts[l] = (AddContractCust)tempAddContractCusts.get(l);
	 	     }
	 	    tempAddContractCusts = null;
 	    }
 	    else{
 	    	addContractCusts = new AddContractCust[0];
 	    }
 	   return addContractCusts;*/
 	//END: Issue # 99933
  }
//START: Issue # 99933
  private AddContractCust[] getContractCustomersBySearchCriteria(HttpServletRequest request,ContractService contractService,AddContract addContract,String contractCode,String pageNumberStr){
	  int pageNumber = 1;
      if(pageNumberStr != null && pageNumberStr.trim().length()>0){
        pageNumber = Integer.parseInt(pageNumberStr);
        addContract.setTabName("1");
      }
      if(pageNumber <= 0) { pageNumber = 1; }
      int resultCount = contractService.getContractCustsCountByContractCode(contractCode);
      Pagination pagination = new Pagination(resultCount,20, pageNumber, 10);
      pagination.calculatePages();
      request.setAttribute("pagination", pagination);
      HttpSession session = request.getSession();
      if(session != null){
        session.setAttribute("ContractCustomersPagination",pagination);
      }
	     List contractCusts = null; 
	     boolean blnContactCrt = false;
	     if(null != addContract.getSearchCustomerId() && addContract.getSearchCustomerId().trim().length() > 0){
	    	 contractCusts = contractService.loadContractCustsByCustCodeAndContractCode(addContract,contractCode);
	     } else if(null != addContract.getSearchCustomerName() && addContract.getSearchCustomerName().trim().length() > 0){
	    	 contractCusts = contractService.loadContractCustsByCustCodeAndContractCode(addContract,contractCode);
	     } else if(null != addContract.getSearchContactId() && addContract.getSearchContactId().trim().length() > 0){
	    	 blnContactCrt = true;
	    	 contractCusts = contractService.getContractCustsByContractCode(contractCode, null);
	     } else {
	    	 blnContactCrt = true;
	    	 contractCusts = contractService.getContractCustsByContractCode(contractCode, null);
	     }
	     
	     if(null != addContract.getSearchContactId() && addContract.getSearchContactId().trim().length() > 0){
	    	 blnContactCrt = true;
	     }
	     
	     if(null != addContract.getSearchContactName() && addContract.getSearchContactName().trim().length() > 0){
	    	 blnContactCrt = true;
	     }	     
	     
	     AddContractCust[] addContractCusts = null;
	     List addContractCustContacts = null;
	     boolean blnContactExts = false;
	     if(null != contractCusts){
	    	 addContractCustContacts = new ArrayList();
		     for(int i =0;i<contractCusts.size();i++) {		    	 
		    	  
		          ContractCust contractCust = (ContractCust) contractCusts.get(i);
	
		          AddContractCust addContractCust = new AddContractCust();
		          addContractCust.setContractCust(contractCust);
	
		          List contractCustContactList = contractService.loadContactsByCustCodeAndContractCode(addContract,contractCust.getContractCustId().getCustCode(), contractCust.getContractCustId().getContractCode());
		          ContractCustContact[] contractCustContacts = new ContractCustContact[contractCustContactList.size()];
		          blnContactExts = false;
		          for(int j =0;j<contractCustContactList.size();j++) {		        	
		            ContractCustContact contractCustContact = (ContractCustContact) contractCustContactList.get(j);
		                      contractCustContact.getContact().setFirstName(contractCustContact.getContact().getFirstName()+" "+contractCustContact.getContact().getLastName());
		            contractCustContacts[j] = contractCustContact;
		            addContractCust.setContractCustContacts(contractCustContacts);
		            blnContactExts = true;
			        //addContractCustContacts.add(addContractCust); commented by YG			        
		          }
		          if(blnContactCrt && blnContactExts){
		        	  addContractCustContacts.add(addContractCust);//added by YG
		          }
		          else if(!blnContactCrt){
		        	  addContractCustContacts.add(addContractCust);
		          }
		     }
	     }
	     
	     if(addContractCustContacts != null && addContractCustContacts.size()>0){
	    	 
	    	 int strtIndex = (pageNumber-1)*20;
		     int endIndex = 0;
		     
		     
		     if(addContractCustContacts.size() <= 20){
		    	 endIndex = addContractCustContacts.size();
		     }
		     else{
		    	 if( addContractCustContacts.size() <= (pageNumber * 20)){
		    		 endIndex = addContractCustContacts.size();
		    	 }
		    	 else{
		    		 endIndex = (pageNumber * 20);
		    	 }
		     }
		    
	    	 
	    	 addContractCusts = new AddContractCust[endIndex-strtIndex];
	    	 int indx = 0;
		     for(int i = strtIndex ;i<endIndex;i++) {
		    	 AddContractCust addContractCust = (AddContractCust)addContractCustContacts.get(i);
		    	 addContractCusts[indx] = addContractCust;
		    	 indx++;		    	 
		     }
	     } else {
	  	    	addContractCusts = new AddContractCust[0];
	     }
	   return addContractCusts;
	//END: Issue # 99933
  }
  private AddContractCust[] addiContractCust(AddContractCust[] addContractCusts)
  {
    AddContractCust addContractCust = new AddContractCust();
    ContractCust contractCust= new ContractCust();
    contractCust.setContractCustId(new ContractCustId());
    Customer customer = new Customer();
    Contract contract = new Contract();
    contractCust.setCustomer(customer);
    contractCust.setContract(contract);

    ContractCustContact[] contractCustContacts=new ContractCustContact[1];
    ContractCustContact  contractCustContact = new ContractCustContact();
    contractCustContact.setContractCustContactId(new ContractCustContactId());
    contractCustContact.setContact(new Contact());
    contractCustContact.setContractCust(contractCust);
    contractCustContacts[0] =  contractCustContact;
    addContractCust.setContractCust(contractCust);
    addContractCust.setContractCustContacts (contractCustContacts);
    AddContractCust[] results = null;
    /*if(addContractCusts == null) results = new AddContractCust[1];
    else results = new AddContractCust[addContractCusts.length + 1];*/
 if(addContractCusts == null){
      results = new AddContractCust[1];
      addContractCusts = new AddContractCust[1];
    }
    else
      results = new AddContractCust[addContractCusts.length + 1];

    for(int i=0; i<addContractCusts.length; i++)
    {
      results[i] = addContractCusts[i];
    }

    results[results.length - 1] = addContractCust;
    return results;
  }

  private AddContractCust[] delContractCust(AddContractCust[] addContractCusts, int index)
  {
    if(addContractCusts == null) return null;
    if(addContractCusts.length <= 0) return addContractCusts;

    AddContractCust[] results = new AddContractCust[addContractCusts.length - 1];
    int count = 0;
    for(int i=0; i<addContractCusts.length; i++)
    {
      if(i == index) continue;
      results[count] = addContractCusts[i];
      count ++;
    }
    return results;
  }

  private ContractCustContact[] addContractCustContact(ContractCustContact[] contractCustContacts)
  {
    ContractCustContact contractCustContact = new ContractCustContact();
    contractCustContact.setContractCustContactId(new ContractCustContactId());

    ContractCust contractCust = new ContractCust();
    contractCust.setContractCustId(new ContractCustId());
    Contact contact = new Contact();
    contractCustContact.setContractCust(contractCust);
    contractCustContact.setContact(contact);
    contractCustContact.setContractCustContactId(new ContractCustContactId() );



    ContractCustContact[] results = null;
    if(contractCustContacts == null) results = new ContractCustContact[1];
    else results = new ContractCustContact[contractCustContacts.length + 1];

    for(int i=0; i<contractCustContacts.length; i++)
    {
      results[i] = contractCustContacts[i];
    }
    results[results.length - 1] = contractCustContact;

    return results;
  }

  private ContractCustContact[] deleteContractCustContact(ContractCustContact[] contractCustContacts, int index)
  {
    if(contractCustContacts == null) return null;
    if(contractCustContacts.length <= 0) return contractCustContacts;

    ContractCustContact[] results = new ContractCustContact[contractCustContacts.length - 1];

    int count = 0;
    for(int i=0; i<contractCustContacts.length; i++)
    {
      if(i == index) continue;
      results[count] = contractCustContacts[i];

      count ++;
    }

    return results;
  }

}
