package com.intertek.web.controller.customer;

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
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddCustomer;
import com.intertek.domain.AddCustomerAddress;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContactCustId;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustId;
import com.intertek.entity.Country;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddrSeqId;
import com.intertek.entity.CustAddress;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.CustVatRegistrationId;
import com.intertek.entity.Customer;
import com.intertek.entity.Notes;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ContractService;
import com.intertek.service.CountryService;
import com.intertek.service.CustomerService;
import com.intertek.service.NotesService;
import com.intertek.service.SequenceNumberService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomPrimitiveNumberEditor;

public class CreateCustomerFormController extends BaseSimpleFormController
//public class CreateCustomerFormController extends SimpleFormController
{
  public CreateCustomerFormController() {
    super();
    setSessionForm(true);
    setCommandClass(AddCustomer.class);
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




   AddCustomer addCustomer = (AddCustomer) command;
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
  NotesService notesService=(NotesService)ServiceLocator.getInstance().getBean("notesService");
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    SequenceNumberService sequenceNumberService = (SequenceNumberService)ServiceLocator.getInstance().getBean("sequenceNumberService");

   UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
       String addOrDeleteCustAddrSeq = request.getParameter("addOrDeleteCustAddrSeq");
       String addOrDeleteCustAddress = request.getParameter("addOrDeleteCustAddrDtl");

     String index = request.getParameter("custContactsIndex");
       String addrSeqIndex = request.getParameter("custAddrSeqIndex");
       String addrDtlIndex = request.getParameter("custAddrDtlIndex");

     String page = addCustomer.getTabName();


     String addOrDeleteCustContract = request.getParameter("addOrDeleteCustContract");
     String indexs = request.getParameter("contractCustIndex");

     String addOrDeleteCustVatReg = request.getParameter("addOrDeleteCustVatReg");
     String custVatRegIndex = request.getParameter("custVatRegIndex");

     String addOrDeleteCustNote = request.getParameter("addOrDeleteCustNote");

     Customer customer = addCustomer.getCustomer();


//Check for primary address flags

  int custAddrCount = addCustomer.getCustAddrSeqCount();

  boolean billCheck = false;
  boolean soldCheck = false;

  for(int i=0; i< custAddrCount;i++)
  {
    String billPrimaryParam = "billPrimaryFlag" + i;
    String soldPrimaryParam = "soldPrimaryFlag" + i;
    String billPrimaryVal = addCustomer.getAddCustomerAddresses()[i].getPrimaryBillToAddr();
    String soldPrimaryVal = addCustomer.getAddCustomerAddresses()[i].getPrimarySoldToAddr();


    if(!billCheck && billPrimaryVal != null && billPrimaryVal.trim().equals("1"))
    {
      billCheck = true;
      customer.setPrimaryBillToAddress(i+1);
    }
    else if(billCheck && billPrimaryVal != null)
    {
            errors.reject("primary.bill.address.error", new Object[] {}, null);
          return showForm(request, response, errors);
    }

    if(!soldCheck && soldPrimaryVal != null)
    {
      soldCheck = true;
      customer.setPrimarySoldToAddress(i+1);
    }
    else if(soldCheck && soldPrimaryVal != null)
    {
            errors.reject("primary.sold.address.error", new Object[] {}, null);
          return showForm(request, response, errors);
    }

  }
  User user=userService.getUserByName(SecurityUtil.getUser().getLoginName());

    if((addOrDeleteCustAddrSeq != null) && ("addAddr".equals(addOrDeleteCustAddrSeq) || "deleteAddr".equals(addOrDeleteCustAddrSeq)))
    {
      if("addAddr".equals(addOrDeleteCustAddrSeq))
      {
        addCustomer.setAddCustomerAddresses(addCustomerAddress(addCustomer.getAddCustomerAddresses(),user));
        if(addCustomer.getAddCustomerAddresses().length == 1)
        {
          addCustomer.getAddCustomerAddresses()[0].setPrimaryBillToAddr("1");
          addCustomer.getAddCustomerAddresses()[0].setPrimarySoldToAddr("1");
        }
      }
      else
      {
        addCustomer.setAddCustomerAddresses(deleteCustomerAddress(addCustomer.getAddCustomerAddresses(), addCustomer.getCustAddrSeqIndex()));
      }
      addCustomer.setCustAddrSeqCount(addCustomer.getAddCustomerAddresses().length);

      addCustomer.setAddOrDeleteCustAddrSeq("none");
      addCustomer.setTabName("1");
      return showForm(request, response, errors);
    }

      if((addOrDeleteCustAddress != null) && ("addAddrDtls".equals(addOrDeleteCustAddress) || "deleteAddrDtls".equals(addOrDeleteCustAddress)))
    {
      int intAddrSeqIndex = Integer.parseInt(addrSeqIndex);
      if("addAddrDtls".equals(addOrDeleteCustAddress))
      {
        AddCustomerAddress[] addCustomerAddresses = addCustomer.getAddCustomerAddresses();
        AddCustomerAddress addCustomerAddress = addCustomerAddresses[intAddrSeqIndex];
        addCustomerAddress.setCustAddresses(addCustomerAddressDetails(addCustomerAddress.getCustAddresses(),user));
         addCustomerAddress.setCustAddressCount(addCustomerAddress.getCustAddresses().length);

        addCustomerAddresses[intAddrSeqIndex] = addCustomerAddress;
        addCustomer.setAddCustomerAddresses(addCustomerAddresses);
      }
      else
      {
         int intAddrDtlIndex = Integer.parseInt(addrDtlIndex);

        AddCustomerAddress[] addCustomerAddresses = addCustomer.getAddCustomerAddresses();
        AddCustomerAddress addCustomerAddress = addCustomerAddresses[intAddrSeqIndex];
        addCustomerAddress.setCustAddresses(deleteCustomerAddressDetails(addCustomerAddress.getCustAddresses(),intAddrDtlIndex));
        addCustomerAddress.setCustAddressCount(addCustomerAddress.getCustAddresses().length);

        addCustomerAddresses[intAddrSeqIndex] = addCustomerAddress;
        addCustomer.setAddCustomerAddresses(addCustomerAddresses);
      }

      addCustomer.setAddOrDeleteCustAddrDtl("none");
      addCustomer.setTabName("1");
      return showForm(request, response, errors);
    }

   if((addOrDeleteCustContract != null) && ("add".equals(addOrDeleteCustContract) || "delete".equals(addOrDeleteCustContract)))
    {
      if("add".equals(addOrDeleteCustContract))
      {
        addCustomer.setContractCusts(addContractCust(addCustomer.getContractCusts()));
      }
      else
      {
        addCustomer.setContractCusts(deleteContractCust(addCustomer.getContractCusts(), addCustomer.getContractCustIndex()));
      }
      addCustomer.setContractCustCount(addCustomer.getContractCusts().length);

      addCustomer.setAddOrDeleteCustContract("none");
      addCustomer.setTabName("2");
      return showForm(request, response, errors);
    }
   if((addOrDeleteCustVatReg != null) && ("add".equals(addOrDeleteCustVatReg) || "delete".equals(addOrDeleteCustVatReg)))
   {
    if("add".equals(addOrDeleteCustVatReg))
    {
      addCustomer.setCustVatRegistrations(addCustVatRegistration(addCustomer.getCustVatRegistrations()));
    }
    else
    {
      addCustomer.setCustVatRegistrations(deleteCustVatRegistration(addCustomer.getCustVatRegistrations(), addCustomer.getCustVatRegIndex()));
    }
    addCustomer.setCustVatRegCount(addCustomer.getCustVatRegistrations().length);
      addCustomer.setAddOrDeleteCustVatReg("none");
      addCustomer.setTabName("3");
      return showForm(request, response, errors);
  }

    /************************ cust Note**********************************/

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
        List <Notes> custNoteList=addCustomer.getCustomerNotesList();
        if(addCustomer.getCustomerNotesList()!= null && addCustomer.getCustomerNotesList().size()>0)
        { List <Notes>custNoteTemp = new ArrayList<Notes>();
          for(int i=0;i<addCustomer.getCustomerNotesList().size();i++)
          { Notes custNote=addCustomer.getCustomerNotesList().get(i);
            if(i==addCustomer.getCustNoteCount())
              {
              addCustomer.setNoteAddBy(custNote.getAddedByUserName());
                try {
                SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
                addCustomer.setNoteAddDateTime(df.format(custNote.getDateTimeAdded()));
              } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
                addCustomer.setNote(custNote.getNoteDetails());
              addCustomer.setNoteSummary(custNote.getNoteSummary());
                addCustomer.setAddOrDeleteCustNote("none");
                addCustomer.setTabName("4");
                addCustomer.setCustNoteEditFlag(i);
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
        //addCustomer.setCustomerNotes(deleteCustNote(addCustomer.getCustomerNotes(),addCustomer.getCustNoteCount()));
      }
        addCustomer.setNoteAddBy("");
        addCustomer.setNoteAddDateTime("");
        addCustomer.setNote("");
      addCustomer.setNoteSummary("");
        if(addCustomer.getCustomerNotesList()!=null)
      addCustomer.setCustNoteCount(addCustomer.getCustomerNotesList().size());
        addCustomer.setAddOrDeleteCustNote("none");
        addCustomer.setTabName("4");
        addCustomer.setCustNoteEditFlag(-1);
        return showForm(request, response, errors);
    }


    /***************************cust Note ends here*******************************/



   AddCustomerAddress[] addCustomerAddressItems = addCustomer.getAddCustomerAddresses();

  ContactCust[] contactCustItems = addCustomer.getContactCusts();

  ContractCust[] contractCustItems = addCustomer.getContractCusts();

  CustVatRegistration[] custVatRegistrationItems = addCustomer.getCustVatRegistrations();

  //CustomerNote[]custNoteItems=addCustomer.getCustomerNotes();
       String contractFlag=request.getParameter("contractFlag");

       if("contractFlag".equals(contractFlag))
         {
      try {
        String rowNo=request.getParameter("rowNum");
      int num=Integer.parseInt(rowNo);
      ContractCust contractCust=new ContractCust();
      Contract contract=new Contract();

      contract=contractService.getContractByContractCode(contractCustItems[num].getContract().getContractCode());

      contractCustItems[num].setContract(contract);
      contractCustItems[num].setCustomer(customer);
      contractCustItems[num].getContractCustId().setCustCode(customer.getCustCode());
      addCustomer.setContractCusts(contractCustItems);
      addCustomer.setTabName("2");
      addCustomer.setContractFlag("none");
      return showForm(request, response, errors);
          } catch(ServiceException e)
          {
             addCustomer.setContractFlag("none");
             addCustomer.setTabName("2");
             e.printStackTrace();
             errors.reject(e.getMessage(), e.getParams(), null);
             return showForm(request, response, errors);
          } catch(Throwable t)
          {
             addCustomer.setContractFlag("none");
             addCustomer.setTabName("2");
             t.printStackTrace();
             errors.reject("generic.error", new Object[] {t.getMessage()}, null);
             return showForm(request, response, errors);
          }
        }


if(customer.getInterunitInd()!=null&&customer.getInterunitInd()==true)
 {
  if(customer.getInterunitBusUnitName()==null || customer.getInterunitBusUnitName().trim().equals(""))
  {
  /*if(customer.getInterunitBusUnitName()!=null && !customer.getInterunitBusUnitName().trim().equals(""))
   {
        if(customer.getInterunitBusUnitName().equalsIgnoreCase(customer.getCustCode()))
     {
      //errors.reject("create.customer.error", new Object[] {"Invalid Customer Code :"+customer.getCustCode()}, null);
      errors.reject("customer.businessunit.validation.error", new Object[] {customer.getCustCode()}, null);
       return showForm(request, response, errors);
         }
   }
   else
    {*/
         errors.reject("customer.businessunit.required.error", new Object[] { customer.getName()}, null);
       return showForm(request, response, errors);
     }
 }

       String busFlag=request.getParameter("busFlag");
    if("bus".equals(busFlag))
      {
        customer.setCustCode(customer.getInterunitBusUnitName());
    addCustomer.setBusFlag("none");
    addCustomer.setTabName("0");
      return showForm(request,response,errors);
    }

if(customer.getCustCode().equalsIgnoreCase("new"))
    {
    Long custCodeSequence = sequenceNumberService.getSequenceNumber("CUSTOMER_SEQ");
    customer.setCustCode(custCodeSequence.toString());
    }





    if (contactCustItems != null )
    {
      for(int i=0; i< contactCustItems.length; i++)
      {

        ContactCust contactCust = contactCustItems[i];
        contactCust.setCustomer(customer);
        customer.getContactCusts().add(contactCust);
      }
    }

    if (contractCustItems != null )
    {
      try {
      for(int i=0; i< contractCustItems.length; i++)
      {
        ContractCust contractCust = contractCustItems[i];
        if(contractCust.getContract().getContractCode() != null && (!contractCust.getContract().getContractCode().trim().equals("")))
        {
          Contract existingContract = contractService.getContractByContractCode(contractCust.getContract().getContractCode());
          if(existingContract != null)
          {
            contractCust.getContractCustId().setCustCode(customer.getCustCode());
            contractCust.getContractCustId().setContractCode(contractCust.getContract().getContractCode());

            contractCust.setCustomer(customer);
            customer.getContractCusts().add(contractCust);
          }
          else
          {
                errors.reject("invalid.contractcode.error", new Object[] {contractCust.getContract().getContractCode()}, null);
                return showForm(request, response, errors);
          }
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

    if (custVatRegistrationItems != null )
    {
      boolean homeCountryInd = false;
      for(int i=0; i< custVatRegistrationItems.length; i++)
      {
        CustVatRegistration custVatRegistration = custVatRegistrationItems[i];
        if(homeCountryInd && custVatRegistration.getHomeCountryInd())
        {
              errors.reject("country.vatregistration.error", new Object[] {}, null);
              addCustomer.setTabName("3");
              return showForm(request, response, errors);
        }
        if(!homeCountryInd && custVatRegistration.getHomeCountryInd())
          homeCountryInd = custVatRegistration.getHomeCountryInd();
      }
      if(!homeCountryInd)
      {
            errors.reject("country.vatregistration.required.error", new Object[] {}, null);
            addCustomer.setTabName("3");
            return showForm(request, response, errors);
      }
      for(int i=0; i< custVatRegistrationItems.length; i++)
      {
        CustVatRegistration custVatRegistration = custVatRegistrationItems[i];
        if(custVatRegistration.getCustVatRegistrationId().getCountryCode() == null
            || custVatRegistration.getCustVatRegistrationId().getCountryCode().trim().equals(""))
        {
              errors.reject("customer.vatregistration.country.required.error", new Object[] {(i+1)}, null);
              addCustomer.setTabName("3");
              return showForm(request, response, errors);
        }
        custVatRegistration.getCustVatRegistrationId().setCustCode(customer.getCustCode());
        custVatRegistration.setCustomer(customer);
        customer.getCustVatRegistrations().add(custVatRegistration);

      }

    }
    /*-----------------------------Cust notes -------------------------------------------*/
    if (addCustomer.getCustomerNotesList() != null )
    {
      for(int i=0; i< addCustomer.getCustomerNotesList().size(); i++)
      {
        Notes custNote = addCustomer.getCustomerNotesList().get(i);
        custNote.setNoteCode(customer.getCustCode());
        custNote.setNoteType("CUSTOMER");
//        customer.getCustNotes().add(custNote);

      }

    }

    /*-----------------------------Cust notes Ends-------------------------------------------*/


      if (addCustomerAddressItems != null )
    {
      for(int i=0; i< addCustomerAddressItems.length; i++)
      {
        CustAddrSeq custAddrSeq = addCustomerAddressItems[i].getCustAddrSeq();
        if(custAddrSeq.getCustAddrSeqId().getLocationNumber() != null && custAddrSeq.getCustAddrSeqId().getLocationNumber() > 0)
        {
          custAddrSeq.setCustomer(customer);
          CustAddress[] custAddressItems = addCustomerAddressItems[i].getCustAddresses();

          //new code added for the 68988
          int count=0;
          int countnew=0;
          int lengcustadd=custAddressItems.length;

          for(int k=0; k< custAddressItems.length; k++)
          {
            CustAddress custAddress = custAddressItems[k];
            if(addCustomerAddressItems[i].getPrimaryBillToAddr() != null && Integer.parseInt(addCustomerAddressItems[i].getPrimaryBillToAddr()) > 0
                &&custAddressItems[k].getEffStatus().equals(Constants.STATUS_I))
               {
                count++;
               }

              if(addCustomerAddressItems[i].getPrimarySoldToAddr() != null && Integer.parseInt(addCustomerAddressItems[i].getPrimarySoldToAddr()) > 0
                  &&custAddressItems[k].getEffStatus().equals(Constants.STATUS_I))
              {
                countnew++;
              }

          }
           if(count==lengcustadd)
           {errors.reject("primary.bill.status.error", new Object[] {}, null);
            return showForm(request, response, errors);}
           if(countnew==lengcustadd)
           {
             errors.reject("primary.sold.status.error", new Object[] {}, null);
               return showForm(request, response, errors);
           }

           //up to here
           CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
          for(int j=0; j< custAddressItems.length; j++)
          {
            CustAddress custAddress = custAddressItems[j];
            Country country = countryService.getCountryByCode(custAddress.getCountry().trim());
            if(country.getStateAvail() && country.getStateRequiredInAddress()){
              if(custAddress.getState().trim().equals("")){
                errors.reject("state.required.error", new Object[] { custAddress.getCountry()}, null);
                return showForm(request, response, errors);
              }
            }

           if(custAddress.getCountry().trim().equalsIgnoreCase("USA")||custAddress.getCountry().trim().equalsIgnoreCase("CAN"))
           {
              if(custAddress.getAddress1().trim().equals("")&& custAddress.getCity().trim().equals("") && custAddress.getState().trim().equals(""))
            {
            errors.reject("customer.address.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }


            if(custAddress.getAddress1().trim().equals(""))
            {
            errors.reject("address.required.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

            if(custAddress.getCity().trim().equals(""))
            {
            errors.reject("city.required.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

            //if(custAddress.getState().trim().equals(""))
            //{
            //errors.reject("state.required.error", new Object[] { custAddress.getCountry()}, null);
            //return showForm(request, response, errors);
            //}

          }

          //else if(custAddress.getCountry().trim().equalsIgnoreCase("NLD")||custAddress.getCountry().trim().equalsIgnoreCase("BEL"))
           else if(custAddress.getCountry().trim().equalsIgnoreCase("NLD"))
          {
        	// Commented due to itrack issue 108056
            // if( custAddress.getAddress1().trim().equals("")&& custAddress.getCity().trim().equals("") && custAddress.getAddress2().trim().equals(""))
        	if( custAddress.getAddress1().trim().equals("")&& custAddress.getCity().trim().equals(""))
        	{
        		// errors.reject("address1.address2.city.required.error", new Object[] { custAddress.getCountry()}, null);
        		errors.reject("address1.city.required.error", new Object[] {custAddress.getCountry()}, null);
        		return showForm(request, response, errors);
            }

            if(custAddress.getAddress1().trim().equals(""))
            {
            errors.reject("address.required.error", new Object[] {custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

            if(custAddress.getCity().trim().equals(""))
            {
            errors.reject("city.required.error", new Object[] {custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }
            // Commented due to itrack issue 108056
           /* if(custAddress.getAddress2().trim().equals(""))
            {
            errors.reject("address2.required.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }*/

          }

                    else
          {
            if(custAddress.getAddress1().trim().equals("")&&  custAddress.getCity().trim().equals(""))
            {
            errors.reject("address.city.required.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

            if(custAddress.getAddress1().trim().equals(""))
            {
            errors.reject("address.required.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }

            if(custAddress.getCity().trim().equals(""))
            {
            errors.reject("city.required.error", new Object[] { custAddress.getCountry()}, null);
            return showForm(request, response, errors);
            }
            }
            custAddress.setCustAddrSeq(custAddrSeq);
            custAddrSeq.getCustAddresses().add(custAddress);
          }

          customer.getCustAddrSeqs().add(custAddrSeq);
        }
        else
        {
              errors.reject("invalid.location.number.error", new Object[] {custAddrSeq.getCustAddrSeqId().getLocationNumber()}, null);
              return showForm(request, response, errors);
        }
      }
    }


    try
    {
  customer.setBillTo("Y");
  customer.setSoldTo("Y");
  customer.setShipTo("N");
  customer.setStatusDate(new Date());
  //added for user info in customer
  customer.setUpdatedTime(new Date());
  customer.setCreatedTime(new Date());
  customer.setCreByUserName(user.getLoginName());
  customer.setModByUserName(user.getLoginName());
  if(customer.getPrimaryBillToAddress() == 0 || customer.getPrimarySoldToAddress() == 0)
  {
    errors.reject("primarybill.primarysold.address.required.error", new Object[] {}, null);
    addCustomer.setTabName("1");
      return showForm(request, response, errors);
  }
  /*For iTrack#135193-START */
  if(customer != null && customer.getParentCustomer()!= null){
	  
      if(!customer.getName().trim().equalsIgnoreCase(customer.getParentCustomer().getName().trim())){
    	  customer.setIsChildCustomer(true);
      }
  }
  /*For iTrack#135193-END */
  customer = customerService.addCustomer(customer);
  notesService.addNotes(addCustomer.getCustomerNotesList());
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

      errors.reject("create.customer.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/
   return new ModelAndView(new RedirectView("edit_customer.htm?reqFrom=createCustomerForm&customer.custCode="+customer.getCustCode()+"&tabname="+addCustomer.getTabName()));


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
    CustomPrimitiveNumberEditor integerEditor = new CustomPrimitiveNumberEditor(Integer.class, nf,Integer.valueOf(0));
    binder.registerCustomEditor(Integer.class, "customer.custAddrSeq.custAddrSeqId.locationNumber", integerEditor);
    binder.registerCustomEditor(Integer.class, "customer.contactCust.contactCustId.locationNumber", integerEditor);
    CustomPrimitiveNumberEditor longEditor = new CustomPrimitiveNumberEditor(Long.class, nf, Long.valueOf(0));
    binder.registerCustomEditor(Long.class, "customer.contactCust.contact.id", longEditor);
  }

   protected boolean suppressValidation(HttpServletRequest request,Object command)
  {
    String addOrDelete = request.getParameter("addOrDeleteCustContact");
    String addOrDeleteAddrSeq = request.getParameter("addOrDeleteCustAddrSeq");
    String addOrDeleteCustAddress = request.getParameter("addOrDeleteCustAddrDtl");
    String addOrDeleteCustVatReg = request.getParameter("addOrDeleteCustVatReg");
    String addOrDeleteCustNote = request.getParameter("addOrDeleteCustNote");
    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      return true;
    }
    if((addOrDeleteAddrSeq != null) && ("addAddr".equals(addOrDeleteAddrSeq) || "deleteAddr".equals(addOrDeleteAddrSeq)))
    {
      return true;
    }
    if((addOrDeleteCustAddress != null) && ("addAddrDtls".equals(addOrDeleteCustAddress) || "deleteAddrDtls".equals(addOrDeleteCustAddress)))
    {
      return true;
    }
   String addOrDeleteCustContract = request.getParameter("addOrDeleteCustContract");

    if((addOrDeleteCustContract != null) && ("add".equals(addOrDeleteCustContract) || "delete".equals(addOrDeleteCustContract)))
    {
      return true;
    }
    if((addOrDeleteCustVatReg != null) && ("add".equals(addOrDeleteCustVatReg) || "delete".equals(addOrDeleteCustVatReg)))
    {
      return true;
    }
    if((addOrDeleteCustNote != null) && ("add".equals(addOrDeleteCustNote)||"view".equals(addOrDeleteCustNote) || "delete".equals(addOrDeleteCustNote)||"reset".equals(addOrDeleteCustNote)))
    {
      return true;
    }
    String contractFlag=request.getParameter("contractFlag");
    if((contractFlag!=null)&&("contractFlag".equals(contractFlag)))
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

   protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        BindException errors = getErrorsForNewForm(request);
        errors.reject("create.customer.error", "Duplicate form submission");
        return showForm(request, response, errors);
    }
  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {


    AddCustomer addCustomer = new AddCustomer();
  Customer customer = new Customer();

  int intYear = 103;
  int intMon = 0;
  int intDate = 1;

   Customer parentCustomer = new Customer();
   /*CreditAnalyst creditAnalyst = new  CreditAnalyst();
   Collector collector         = new Collector();
   BusinessUnit businessUnit   = new BusinessUnit();

   customer.setParentCustomer(parentCustomer);
   customer.setCreditAnalyst(creditAnalyst);
   customer.setCollector(collector);
   customer.setInterunitBusUnit(businessUnit);*/

  Date addDate = new Date();
  if(customer.getAddDate()==null)
  customer.setAddDate(addDate);

  Date sinceDate = new Date(intYear,intMon,intDate);
  if(customer.getSinceDate()==null)
    customer.setSinceDate(sinceDate);



  if(customer.getCustCode() == null)
  {
    customer.setCustCode("New");
  }

  customer.setCreditAnalystName("CREDIT");
  customer.setCollectorName("COLLECT");
  customer.setPaymentType("CRE");
  //customer.setCreditApproved(true);
    customer.setInvoiceType(Constants.INV_TYPE_REG);
    
    /*For iTrack#135193 - START Setting default values*/
    customer.setVipPlatinumPreferred(Constants.TIER2);
    customer.setCreditApproved(true);
    customer.setPaymentType(Constants.OAC);
   /*For iTrack#135193 -END */

   String countStr = request.getParameter("custContactsCount");
   String countAddrSeqStr = request.getParameter("custAddrSeqCount");

  String contractCountStr=request.getParameter("contractCustCount");

    if( countStr == null || countStr.trim().equals("") )
      countStr = "0";

    int count = 0;
    try
    {
      count = new Integer(countStr).intValue();
    }
    catch(Exception e) { }

    if( countAddrSeqStr == null || countAddrSeqStr.trim().equals("") )
      countAddrSeqStr = "0";

    int countAddrSeq = 0;
    try
    {
      countAddrSeq = new Integer(countAddrSeqStr).intValue();
    }
    catch(Exception e) { }


  //Contract code
      if( contractCountStr== null ||contractCountStr.trim().equals("") )
      contractCountStr = "0";

    int contractCount = 0;
    try

    {
      contractCount= new Integer(contractCountStr).intValue();
    }
    catch(Exception e) { }

     addCustomer.setContactCustCount(count);

     addCustomer.setCustAddrSeqCount(countAddrSeq);

    ContactCust[] contactCusts = new ContactCust[count];
    AddCustomerAddress[] addCustomerAddresses = new AddCustomerAddress[countAddrSeq];

    addCustomer.setContractCustCount(contractCount);
    ContractCust[] contractCusts = new ContractCust[contractCount];

    for(int i=0; i<count; i++)
    {
      ContactCust contactCust = new ContactCust();
      CustAddrSeq custAddrSeq = new CustAddrSeq();
      Contact contact = new Contact();
      contactCust.setCustomer(customer);
      contactCust.setCustAddrSeq(custAddrSeq);
      contactCust.setContact(contact);
      contactCusts[i] = contactCust;
    }


      for(int i=0; i<countAddrSeq; i++)
    {
      AddCustomerAddress addCustomerAddress = new AddCustomerAddress();
      CustAddrSeq custAddrSeq = new CustAddrSeq();
      CustAddress[] custAddresses = new CustAddress[0];

      addCustomerAddress.setCustAddrSeq(custAddrSeq);
      addCustomerAddress.setCustAddresses(custAddresses);

      addCustomerAddresses[i] = addCustomerAddress;
    }

    for(int i=0; i<contractCount; i++)
    {
            Contract contract = new Contract();
      ContractCust contractCust = new ContractCust();
      contractCust.setCustomer(customer);
      contractCust.setContract(contract);
      contractCusts[i] = contractCust;

    }
     UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
     try {
    User user=userService.getUserByName(SecurityUtil.getUser().getLoginName());
     customer.setCurrencyCd(user.getCurrencyCd());

   customer.setParentCustomer(parentCustomer);
     addCustomer.setContactCusts(contactCusts);
     addCustomer.setAddCustomerAddresses(addCustomerAddresses);
   addCustomer.setCustomer(customer);
   addCustomer.setTabName("0");
   addCustomer.setContractCusts(contractCusts);
     } catch(ServiceException e)
   {
      throw new ServiceException(e.getMessage(), e.getParams(), e);
   }
   catch(Throwable t)
   {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
   }

   return addCustomer;
  }


   private ContactCust[] addContactCust(ContactCust[] contactCusts)
  {
    ContactCust contactCust = new ContactCust();
    ContactCustId contactCustId = new ContactCustId();
    contactCust.setContactCustId(contactCustId);

    Customer customer = new Customer();
    CustAddrSeq custAddrSeq = new CustAddrSeq();
    Contact contact = new Contact();

    contactCust.setCustomer(customer);
    contactCust.setCustAddrSeq(custAddrSeq);
    contactCust.setContact(contact);

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

   private AddCustomerAddress[] addCustomerAddress(AddCustomerAddress[] addCustomerAddresses,User user)
  {
    AddCustomerAddress addCustomerAddress = new AddCustomerAddress();

    CustAddrSeq custAddrSeq = new CustAddrSeq();
    CustAddrSeqId custAddrSeqId = new CustAddrSeqId();
    custAddrSeq.setCustAddrSeqId(custAddrSeqId);
    custAddrSeq.setSoldTo(true);
    custAddrSeq.setBillTo(true);
    custAddrSeq.setShipTo(false);

        custAddrSeq.setCorresponence(true);
    CustAddress[] custAddresses = new CustAddress[1];

    custAddresses[0] = new CustAddress();



    addCustomerAddress.setCustAddrSeq(custAddrSeq);


       /*new code added here */
        int intYear = 103;
      int intMon = 0;
      int intDate = 1;
              Date effDate = new Date(intYear,intMon,intDate);
             if(custAddresses[0].getEffDate()==null)
         custAddresses[0].setEffDate(effDate);
        if(custAddresses[0].getCountry()==null)
    //   custAddresses[0].setCountry(SecurityUtil.getUser().getCountryCode());
           custAddresses[0].setCountry(user.getCountryCode());
        custAddresses[0].setInCityLimit(true);

      /*  closed at here*/
    addCustomerAddress.setCustAddresses(custAddresses);



    AddCustomerAddress[] results = null;
    if(addCustomerAddresses == null) results = new AddCustomerAddress[1];
    else results = new AddCustomerAddress[addCustomerAddresses.length + 1];

    if(addCustomerAddresses != null)
    {


      for(int i=0; i<addCustomerAddresses.length; i++)
      {

        results[i] = addCustomerAddresses[i];

        results[i].getCustAddrSeq().getCustAddrSeqId().setLocationNumber(Integer.valueOf(i+1));

      }

    }
    addCustomerAddress.getCustAddrSeq().getCustAddrSeqId().setLocationNumber(results.length);
    results[results.length - 1] = addCustomerAddress;


    return results;
  }

  private AddCustomerAddress[] deleteCustomerAddress(AddCustomerAddress[] addCustomerAddresses, int index)
  {
    if(addCustomerAddresses == null) return null;
    if(addCustomerAddresses.length <= 0) return addCustomerAddresses;

    AddCustomerAddress[] results = new AddCustomerAddress[addCustomerAddresses.length - 1];

    int count = 0;
    for(int i=0; i<addCustomerAddresses.length; i++)
    {
      if(i == index) continue;
      results[count] = addCustomerAddresses[i];

      count ++;
    }

    return results;
  }

   private CustAddress[] addCustomerAddressDetails(CustAddress[] custAddresses,User user)
  {
    CustAddress custAddress = new CustAddress();

    CustAddrSeq custAddrSeq = new CustAddrSeq();
    custAddrSeq.setSoldTo(true);
    custAddrSeq.setBillTo(true);
    custAddrSeq.setShipTo(false);
    /*newly added*/
    custAddrSeq.setCorresponence(true);
    int intYear = 103;
    int intMon = 0;
    int intDate = 1;
    Date effDate = new Date(intYear,intMon,intDate);
    if(custAddress.getEffDate()==null)
    custAddress.setEffDate(effDate);
    if(custAddress.getCountry()==null)
       custAddress.setCountry(user.getCountryCode());
    custAddress.setInCityLimit(true);

    /*  closed at here*/
    custAddress.setCustAddrSeq(custAddrSeq);



    CustAddress[] results = null;
    if(custAddresses == null) results = new CustAddress[1];
    else results = new CustAddress[custAddresses.length + 1];
    if(custAddresses != null)
    {
    for(int i=0; i<custAddresses.length; i++)
    {
      results[i] = custAddresses[i];
    }
    results[results.length - 1] = custAddress;
    }
    return results;
  }

  private CustAddress[] deleteCustomerAddressDetails(CustAddress[] custAddresses, int index)
  {
    if(custAddresses == null) return null;
    if(custAddresses.length <= 0) return custAddresses;

    CustAddress[] results = new CustAddress[custAddresses.length - 1];

    int count = 0;
    for(int i=0; i<custAddresses.length; i++)
    {
      if(i == index) continue;
      results[count] = custAddresses[i];

      count ++;
    }

    return results;
  }

 private ContractCust[] addContractCust(ContractCust[] contractCusts)
 {


    ContractCust contractCust = new ContractCust();
    ContractCustId contractCustId = new ContractCustId();
    contractCust.setContractCustId(contractCustId);

    Customer customer = new Customer();
    Contract contract = new Contract();

    contractCust.setCustomer(customer);

    contractCust.setContract(contract);

    ContractCust[] results = null;
    if(contractCusts == null) results = new ContractCust[1];
    else results = new ContractCust[contractCusts.length + 1];
    if(contractCusts != null)
    {
    for(int i=0; i<contractCusts.length; i++)
    {
      results[i] = contractCusts[i];
    }

    results[results.length - 1] = contractCust;
    }
    return results;
  }

  private ContractCust[] deleteContractCust(ContractCust[] contractCusts, int indexs)
  {
    if(contractCusts == null) return null;
    if(contractCusts.length <= 0) return contractCusts;

    ContractCust[] results = new ContractCust[contractCusts.length - 1];

    int count = 0;
    for(int i=0; i<contractCusts.length; i++)
    {
      if(i == indexs) continue;
      results[count] = contractCusts[i];

      count ++;
    }

    return results;
  }

  private CustVatRegistration[] addCustVatRegistration(CustVatRegistration[] custVatRegistrations)
  {


      CustVatRegistration custVatRegistration = new CustVatRegistration();
      CustVatRegistrationId custVatRegistrationId = new CustVatRegistrationId();
      custVatRegistration.setCustVatRegistrationId(custVatRegistrationId);

      CustVatRegistration[] results = null;
    if(custVatRegistrations == null || custVatRegistrations.length <= 0)
    {
      results = new CustVatRegistration[1];
      custVatRegistration.setHomeCountryInd(true);
    }
    else results = new CustVatRegistration[custVatRegistrations.length + 1];


    if(custVatRegistrations != null)
    {
      for(int i=0; i<custVatRegistrations.length; i++)
      {
        results[i] = custVatRegistrations[i];
      }
    }

    results[results.length - 1] = custVatRegistration;

    return results;
  }

   private CustVatRegistration[] deleteCustVatRegistration(CustVatRegistration[] custVatRegistrations, int indexs)
   {
    if(custVatRegistrations == null) return null;
    if(custVatRegistrations.length <= 0) return custVatRegistrations;

    CustVatRegistration[] results = new CustVatRegistration[custVatRegistrations.length - 1];

    int count = 0;
    for(int i=0; i<custVatRegistrations.length; i++)
    {
      if(i == indexs) continue;
      results[count] = custVatRegistrations[i];

      count ++;
    }

    return results;
  }


}
