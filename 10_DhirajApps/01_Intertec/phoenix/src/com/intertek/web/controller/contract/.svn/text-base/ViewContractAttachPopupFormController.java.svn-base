package com.intertek.web.controller.contract;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.CharacterIterator;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.Iterator;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddContract;
import com.intertek.domain.AddContractCust;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ContractService;
import com.intertek.util.StringUtil;


public class ViewContractAttachPopupFormController extends SimpleFormController
{
  public ViewContractAttachPopupFormController() {
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

  String showAttachFile=request.getParameter("showAttachFile");
  ContractAttach[] contractAttachItems = addContract.getContractAttaches();
  AddContractCust[] contractCustItems = addContract.getAddContractCusts();

  String page = addContract.getTabName();
  //Resetting Special codes with special chars
  if (contractAttachItems != null )
  {
	ContractAttach[] newContractAttachItems = new ContractAttach[contractAttachItems.length];
    for(int i=0; i< contractAttachItems.length; i++)
    {
      ContractAttach contractAttach = contractAttachItems[i];
      contractAttach.setSystemFileName(StringUtil.replaceSpecialCodes(contractAttach.getSystemFileName()));
      newContractAttachItems[i] = contractAttach;
    }
    addContract.setContractAttaches(newContractAttachItems);
  }
  //End

if((showAttachFile != null) && ("showAttach".equals(showAttachFile)))
  {
	System.out.println("in viewcontractattachpopup show atatach!=null");
    	ContractAttach[] results = new ContractAttach[addContract.getContractAttaches().length - 1];
 		ContractAttach contractAttach = addContract.getContractAttaches()[addContract.getContractAttachIndex()];
     try{
		//PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("contractfilepath");
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
		System.out.println("prb is "+ pRB);
		if(pRB == null) System.out.println("pRB is null");
		String path ="";
		if(pRB != null)
		{
			path = pRB.getString("contractfilepath");
		}
		String filename=contractAttach.getFileName();
	    File file = new File(path + contractAttach.getFileName());
		InputStream in = new FileInputStream(file);
        response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
        OutputStream out = response.getOutputStream();         
		byte[] buffer = new byte[4 * 1024];
		int data; 
		while((data =in.read(buffer)) != -1){
		out.write(buffer, 0, data);
		}
       /* out.flush();
		out.close();*/
     }
	 catch(Exception e){
	 e.printStackTrace();
	 }
	
    addContract.setShowAttachFile("none");
    addContract.setTabName("2");
    return showForm(request,response,errors);
}

  

    ModelAndView modelAndView = new ModelAndView("common-message-r");
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
    
   
   String showAttachFile=request.getParameter("showAttachFile");
    if((showAttachFile != null) && ("showAttach".equals(showAttachFile)))
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
    String contractCode = request.getParameter("contractCode");
    AddContract addContract = new AddContract();
    Contract contract = new Contract();
    User user=new User();
    Date date=new Date();
   

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
  if(contract.getContractAttaches() != null && contract.getContractAttaches().size() > 0 )
   {
    contractAttaches = new ContractAttach[contract.getContractAttaches().size()];
  
    Iterator iter = contract.getContractAttaches().iterator();
    while(iter.hasNext())
     {
      ContractAttach contractAttach = (ContractAttach) iter.next();
      // Replacing Special characters in filenames
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
  addContract.setContract(contract);
  addContract.setContractAttaches(contractAttaches);
  return addContract;
  }
 
}
