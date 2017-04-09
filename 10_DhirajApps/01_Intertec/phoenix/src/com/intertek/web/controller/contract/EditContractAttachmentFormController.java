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
import com.intertek.util.StringUtil;
import com.intertek.util.VesselTypeSetUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractAttachmentFormController extends BaseEditContractFormController
{
  public EditContractAttachmentFormController() {
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

    String uploadedFileName = request.getParameter("uploadedFileName");
    String deleteAttach = request.getParameter("deleteAttach");
    ContractAttach[] contractAttachItems = addContract.getContractAttaches();

    PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");

    if(pRB == null) System.out.println("pRB is null");
    String path ="";
    if(pRB != null)
    {
      path = pRB.getString("contractfilepath");
    }
    //For attaching/deleting the uploaded file
    if(uploadedFileName != null && (!uploadedFileName.trim().equals("")))
    {
      int trimIndex = uploadedFileName.lastIndexOf("\\");
      uploadedFileName = uploadedFileName.substring(trimIndex +1, uploadedFileName.length());

      //to be
      String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
      File f = new File(path.concat(dateFolder));
      if(!f.exists())
          f.mkdir();
      String systemFileName = dateFolder.concat("/").concat(uploadedFileName);
      //Replacing Special chars
      systemFileName = StringUtil.replaceSpecialChars(systemFileName);
      //End
      addContract.setContractAttaches(addContractAttach(addContract.getContractAttaches(),uploadedFileName, systemFileName));
      addContract.setContractAttachCount(addContract.getContractAttaches().length);
      addContract.setUploadedFileName("");
      addContract.setTabName("2");

      return showForm(request, response, errors);
    }
    else if((deleteAttach != null) && ("deleteAttach".equals(deleteAttach) ))
    {
    //Delete the uploaded file from file system
      addContract.setContractAttaches(deleteContractAttach(addContract.getContractAttaches(), addContract.getContractAttachIndex()));
      addContract.setContractAttachCount(addContract.getContractAttaches().length);
      addContract.setDeleteAttach("none");
      addContract.setTabName("2");
      return showForm(request, response, errors);
    }

    if (contractAttachItems != null )
    {
      Set contractAttachesSet = new HashSet();
      for(int i=0; i< contractAttachItems.length; i++)
      {
        ContractAttach contractAttach = contractAttachItems[i];
          try
        {
          if(contractAttach.getAttachDescr() == null || contractAttach.getAttachDescr().trim().equals(""))
          {
            errors.reject("edit.contract.error", new Object[] {"Please enter description for file : "+contractAttach.getFileName()}, null);
            return showForm(request, response, errors);
          }

          //Resetting Special codes with special chars
          contractAttach.setSystemFileName(StringUtil.replaceSpecialCodes(contractAttach.getSystemFileName()));
          //End
        }
        catch(Exception e)
        {
          e.printStackTrace();
          errors.reject("edit.contract.error", new Object[] {e.getMessage()}, null);
          return showForm(request, response, errors);
        }
        contractAttach.setContractCode(contract.getContractCode());

        if(contractAttach.getSystemFileName()==null || contractAttach.getSystemFileName().trim().length()==0){
          String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
            File f = new File(path.concat(dateFolder));
            if(!f.exists())
              f.mkdir();
          contractAttach.setSystemFileName(dateFolder.concat("/").concat(contractAttach.getFileName()));
        }
        contractAttachesSet.add(contractAttach);
      }
      contract.setContractAttaches(contractAttachesSet);
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
        try
        {
          // it will save contract attachments
          contractService.saveContract(contract);
        }
        catch(Exception e)
        {
          e.printStackTrace();

          errors.reject("edit.contract.error", new Object[] {e.getMessage()}, null);
          return showForm(request, response, errors);
        }
      }

      ModelAndView modelAndView = getModelAndView(
        operationType,
        request.getParameter("goToContractTab"),
        "attachment",
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
    String uploadedFileName = request.getParameter("uploadedFileName");
    if((uploadedFileName != null) && (!"".equals(uploadedFileName.trim()) ))
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

    ContractAttach[] contractAttaches = null;
    int counter = 0;
    if(contract!=null && contract.getContractAttaches() != null && contract.getContractAttaches().size() > 0 )
    {
      contractAttaches = new ContractAttach[contract.getContractAttaches().size()];

      Iterator iter = contract.getContractAttaches().iterator();
      while(iter.hasNext())
      {
        ContractAttach contractAttach = (ContractAttach) iter.next();
        // Replacing Special characters in filename
        String sysFileName = "";
        if (contractAttach != null){
          sysFileName = contractAttach.getSystemFileName();
          sysFileName = StringUtil.replaceSpecialChars(sysFileName);
          contractAttach.setSystemFileName(sysFileName);
        }
        //End
        contractAttaches[counter] = contractAttach;
        counter++;
      }
    }

    if(contract == null) contract = new Contract();
    if(contract.getInvoiceType()==null)
    {
      contract.setInvoiceType("Reg");
    }
    addContract.setContract(contract);
    addContract.setContractAttaches(contractAttaches);
    addContract.setContractAttachCount(counter);

    boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateContract", "ContractCBAttach"});
    addContract.setViewOnly(editable == false);

    addContract.setContractSearch(getContractSearch(request));

    return addContract;
  }
  private ContractAttach[] addContractAttach(ContractAttach[] contractAttaches,String uploadedFileName, String systemFileName)
  {

    ContractAttach contractAttach = new ContractAttach();

    contractAttach.setFileName(uploadedFileName);
    contractAttach.setSystemFileName(systemFileName);
    contractAttach.setAddedByUserName(SecurityUtil.getUser().getLoginName());
    contractAttach.setDateTimeAdded(new Date());

    ContractAttach[] results = null;
    if(contractAttaches == null) results = new ContractAttach[1];
    else results = new ContractAttach[contractAttaches.length + 1];
    if(contractAttaches != null)
    {
    for(int i=0; i<contractAttaches.length; i++)
    {
      results[i] = contractAttaches[i];
    }
    }
    results[results.length - 1] = contractAttach;

    return results;
  }

  private ContractAttach[] deleteContractAttach(ContractAttach[] contractAttaches, int index)
  {
    if(contractAttaches == null) return null;
    if(contractAttaches.length <= 0) return contractAttaches;

    ContractAttach[] results = new ContractAttach[contractAttaches.length - 1];
    //delete uploaded file from file system
    ContractAttach contractAttach = contractAttaches[index];
    //Resetting Special codes with special chars
    contractAttach.setSystemFileName(StringUtil.replaceSpecialCodes(contractAttach.getSystemFileName()));
    //End
    //PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("contractfilepath");
    PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
    if(pRB == null)
      System.out.println("pRB is null");
    String path ="";
    if(pRB != null)
    {
      path = pRB.getString("contractfilepath");

    }
    File file = new File(path + contractAttach.getSystemFileName());
    boolean success = file.delete();
    if (success)
    {
      //File was deleted
      System.out.println("File was deleted.");
    }
    else
    {
      //File was not deleted
      System.out.println("File was not deleted.");
    }

    int count = 0;
    for(int i=0; i<contractAttaches.length; i++)
    {
      if(i == index) continue;
      results[count] = contractAttaches[i];

      count ++;
    }

    return results;
  }
}
