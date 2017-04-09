package com.intertek.web.controller.user;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import com.intertek.exception.ServiceException;
import com.intertek.domain.AddBranchInt;
import com.intertek.domain.Search;
import com.intertek.entity.Branch;
import com.intertek.entity.BranchInt;
import com.intertek.entity.BusinessUnit;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.web.controller.BaseSimpleFormController;

public class CreateSAMLIMSBranchIntFormController extends BaseSimpleFormController
//public class CreateVatRateFormController extends SimpleFormController
{
  public CreateSAMLIMSBranchIntFormController() {
    super();
	setSessionForm(true);	 
    setCommandClass(AddBranchInt.class);
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
	  AddBranchInt  addBranchInt = (AddBranchInt)command;
	  BranchInt[] branches = addBranchInt.getBranches();
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	
	  String sortFlag = request.getParameter("sortFlag");
	  if(sortFlag!=null && (!sortFlag.trim().equals("")))
	  {
		  addBranchInt.setSortFlag(sortFlag);
		  List branchList=userService.getAllBranchIntegrations(addBranchInt);
		  if(branchList != null && branchList.size() > 0)
		  {
			  BranchInt[] branchInts = new BranchInt[branchList.size()];
				if(branchList != null)
				{
				int i=0;
				for(Iterator itr=branchList.iterator();itr.hasNext();)
					{
					branchInts[i] =(BranchInt)itr.next();
					i++;
					}//for(i)

				addBranchInt.setBranches(branchInts);
				}
		  }
		  addBranchInt.setSortFlag("");
		  return showForm(request, response, errors);
		  
		  
	  }
	
	String vxcel=request.getParameter("vxcel"); 
      List deletedList=addBranchInt.getDeletedList();
	 
	   String addOrDeleteBranchCode = request.getParameter("addOrDeleteBranchCode");
	   String indexs = request.getParameter("branchCodeIndex");
	 	 if((addOrDeleteBranchCode != null) && ("add".equals(addOrDeleteBranchCode) || "delete".equals(addOrDeleteBranchCode)))
       {
			if("add".equals(addOrDeleteBranchCode))
			{
				addBranchInt.setBranches(addBranch(addBranchInt.getBranches()));
			}
			else
			{
				
				deletedList.add( branches[addBranchInt.getBranchCodeIndex()]);

				addBranchInt.setBranches(deleteBranch(addBranchInt.getBranches(), addBranchInt.getBranchCodeIndex()));
			}
			addBranchInt.setBranchCount(addBranchInt.getBranches().length);

			addBranchInt.setAddOrDeleteBranchCode("none");
		return showForm(request, response, errors);
		} //if
if((vxcel!=null) && "true".equals(vxcel))
	{ 
       
		
	int pageNumber=addBranchInt.getPagination().getCurrentPageNum();
	addBranchInt.setPagination(null);

    List  branchList=new ArrayList();

   // branchList=userService.getAllBranchIntegrations(addBranchInt);
                 try 
					{ 
					 branchList=userService.getAllBranchIntegrations(addBranchInt);
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
	if(branchList.size() > 0){
	  Pagination pagination = new Pagination(1, 20, pageNumber,10);	
	  pagination.setTotalRecord(branchList.size());
	  pagination.calculatePages();
	  addBranchInt.setPagination(pagination);
	}
	if(branchList!=null&&branchList.size()!=0)
	{
	try
		{
		response.setHeader("Content-Disposition", "attachment; filename=\"branchintegration.xls\"");

		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		main=workBook.createSheet("BranchInt");

		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
             
				 int  l=0;
				for(int k=0;k<Constants.BIColumnSize;k++)
				{
				path=pRB.getString("BIHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			    
			}


        int j=1;
		for(int i=0;i<branchList.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((BranchInt)branchList.get(i)).getBranch().getBuName());
		
		row = main.createRow(j);cell = row.createCell((short) 1);
		if(((BranchInt)branchList.get(i)).getBranchName()!=null && !((BranchInt)branchList.get(i)).getBranchName().trim().equals(""))
		{cell.setCellValue(((BranchInt)branchList.get(i)).getBranchName());}
		else
			{cell.setCellValue("");}

		row = main.createRow(j);cell = row.createCell((short) 2);
		if(((BranchInt)branchList.get(i)).getSamInd()!=null)
		{
			
			cell.setCellValue(((BranchInt)branchList.get(i)).getSamInd());
		}
		
        row = main.createRow(j);cell = row.createCell((short) 3);
        cell.setCellValue(((BranchInt)branchList.get(i)).getLimsInd());
		j++;
		}
		
		OutputStream out = response.getOutputStream(); 
		workBook.write(out);
		out.flush();
		out.close();
		}
		/*catch (Exception ioe) {
		System.out.println("Exception..." +  ioe.toString());
		}*/
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
	
	addBranchInt.setVxcel("false");
	return showForm(request, response, errors);
	}
		String pageFlag=request.getParameter("pageFlag");
		if("pageFlag".equals(pageFlag))
		{
         String pageNumberStr = request.getParameter("pageNumber");
		int pageNumber=1;
		
		try { pageNumber = new Integer(pageNumberStr).intValue();}
		catch (Exception e){	}
		
		if (pageNumber <= 0)	pageNumber = 1;
		
		
		Pagination pagination = new Pagination(1, 20, pageNumber,10);		
		
		addBranchInt.setPagination(pagination);
	
		List branchlist=new ArrayList();
			try
			
			{
			
				branchlist=userService.getAllBranchIntegrations(addBranchInt);
			
			}//try
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
							}//catch
           BranchInt[] branchInts = new BranchInt[branchlist.size()];
			if(branchlist != null)
			{
			int i=0;
			for(Iterator itr=branchlist.iterator();itr.hasNext();)
				{
				branchInts[i] =(BranchInt)itr.next();
				i++;
				}//for(i)

			addBranchInt.setBranches(branchInts);
			}//if

			addBranchInt.setPageFlag("none");
			request.setAttribute("command", addBranchInt);
			return showForm(request, response, errors);
		}//page flag
		
		 String branchFlag=request.getParameter("branchFlag");
		  if("newval".equals(branchFlag))
	  {
    		String rowNo=request.getParameter("rowNum");
			int num=Integer.parseInt(rowNo);
			BranchInt[] branchItems=addBranchInt.getBranches();
			BranchInt branchInt=new BranchInt();
			branchInt.setBranchName(branchItems[num].getBranchName());
			//branchInt.setBranch(userService.getBranchByName(branchItems[num].getBranchName()));
			try			
			{			
				branchInt.setBranch(userService.getBranchByName(branchItems[num].getBranchName()));
			}//try
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
			//branchInt.getBranch().setBuName(branchItems[num].getBranch().getBuName());
			//branchInt=userService.getBranchIntByCode( branchItems[num].getBranchName());
			branchItems[num] = branchInt;
			addBranchInt.setBranches(branchItems);
			addBranchInt.setBranchFlag("none");
			return showForm(request, response, errors);
		}							 



  		BranchInt[] branchIntItems = addBranchInt.getBranches();
    try
    {
    if(deletedList.size()>0)
	  {
    	int i=0;
	     	for(Iterator itr=(deletedList.iterator());itr.hasNext();)
		    {
	     		BranchInt branchInt =(BranchInt)itr.next();
			
			 boolean deletedFlag=userService.deleteBranchInt(branchInt);
			 i++;
			if(deletedFlag==true)
		   {
					   errors.reject("branch.delete.error", new Object[] {branchInt.getBranchName()}, null);
		 //  addTaxRates.setTaxRates(taxRatesItems);
		   //addBranchInt.setBranches(addBranch(branchIntItems,branchInt));
		   deletedList.remove(branchInt);
           return showForm(request, response, errors);
				}
          
			 }//for
		}//if
            	
	//	TaxRate[] taxRatesItems = addTaxRates.getTaxRates();
	    if (branchIntItems != null )
		{
			for(int i=0; i< branchIntItems.length; i++)
			{
				BranchInt branchInt = branchIntItems[i];
				if(branchInt.getBranchName() != null && (!branchInt.getBranchName().trim().equals("")))
					{
					branchInt.setBranch(userService.getBranchByName(branchInt.getBranchName()));
					 userService.addBranchInt(branchInt);
					}
			}
		}
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
    
    ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "sam_lims_int.htm");
    modelAndView.addObject("backUrlDescription", "You can continue to edit the branches.");
    modelAndView.addObject("description", "SAM/LIMS branches  has been successfully saved.");

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

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	Search search=new Search();
	String pageNumberStr = request.getParameter("pageNumber");
	int pageNumber = 1;		
	try {
		pageNumber = new Integer(pageNumberStr).intValue();
	    } catch (Exception e) {}
		if (pageNumber <= 0)
			pageNumber = 1;
		Pagination pagination = new Pagination(1, 20, pageNumber,10);
   AddBranchInt addBranchInt = new AddBranchInt();
   addBranchInt.setPagination(pagination);
   List branchInts=new ArrayList();
   UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    try
    {
    	branchInts=userService.getAllBranchIntegrations(addBranchInt);
    }
     catch(ServiceException e)
    {
    	throw new ServiceException(e.getMessage(), e.getParams(), e);    	
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }

   if(branchInts != null)
    {
		int i=0;
   		BranchInt[] branchInt=new BranchInt[branchInts.size()];
		for(Iterator itr=branchInts.iterator();itr.hasNext();)
		{
			branchInt[i] =(BranchInt)itr.next();
    	  i++;
	   }
		addBranchInt.setBranches(branchInt);
		}
	   request.setAttribute("command", addBranchInt);
   return addBranchInt;
  }



   private BranchInt[] addBranch(BranchInt[] branchInts)
 {
	   BranchInt branchInt = new BranchInt();
	   BusinessUnit bu = new BusinessUnit();
	  	   
	   Branch branch = new Branch();
	   branch.setBusinessUnit(bu);
	   branchInt.setBranch(branch);
	

    BranchInt[] results = null;
		if(branchInts == null) results = new BranchInt[1];
		else results = new BranchInt[branchInts.length + 1];
		if(branchInts != null)
		{
		for(int i=0; i<branchInts.length; i++)
		{
			results[i] = branchInts[i];
		}
		}
		results[results.length - 1] = branchInt;
		return results;
	}

  private BranchInt[] deleteBranch(BranchInt[] branchInts, int indexs)
  {
		if(branchInts == null) return null;
		if(branchInts.length <= 0) return branchInts;
		BranchInt[] results = new BranchInt[branchInts.length - 1];
		int count = 0;
		for(int i=0; i<branchInts.length; i++)
		{
			if(i == indexs) continue;
			results[count] = branchInts[i];
			count ++;
		}
		return results;
	}		
}
