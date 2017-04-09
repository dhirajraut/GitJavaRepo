package com.intertek.web.controller.user;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.intertek.domain.AddBusinessStream;

import com.intertek.domain.Search;


import com.intertek.entity.BusStream;
import com.intertek.entity.BusStreamId;
import com.intertek.entity.Branch;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.UserService;
import com.intertek.util.Constants;

import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class CreateBusinessStreamFormController extends BaseSimpleFormController
{
  public CreateBusinessStreamFormController() {
    super();
	setSessionForm(true);	 
    setCommandClass(AddBusinessStream.class);
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
	  AddBusinessStream  addBusinessStreams = (AddBusinessStream)command;
	  BusStream[] busStreams = addBusinessStreams.getBusStreams();
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  String busExcel = request.getParameter("busExcel"); 
      List deletedList = addBusinessStreams.getDeletedList();
	 
	  String addOrDeleteBuStream = request.getParameter("addOrDeleteBuStream");
	  String indexs = request.getParameter("busStreamIndex");
	  if((addOrDeleteBuStream != null) && ("add".equals(addOrDeleteBuStream) || "delete".equals(addOrDeleteBuStream)))
      {
		if("add".equals(addOrDeleteBuStream))
		{
			addBusinessStreams.setBusStreams(addBuStream(addBusinessStreams.getBusStreams(),null));
		}
		else
		{				
			deletedList.add(busStreams[addBusinessStreams.getBusStreamIndex()]);
			addBusinessStreams.setBusStreams(deleteBuStream(addBusinessStreams.getBusStreams(), addBusinessStreams.getBusStreamIndex()));
		}
		addBusinessStreams.setBusStreamCount(addBusinessStreams.getBusStreams().length);

		addBusinessStreams.setAddOrDeleteBuStream("none");
		return showForm(request, response, errors);
	 } //if
	if((busExcel!=null) && "true".equals(busExcel))
	{  
		int pageNum = addBusinessStreams.getPagination().getCurrentPageNum();
	    List  buStreams = new ArrayList();
	    addBusinessStreams.setPagination(null);
        try { 
        	buStreams = userService.getAllBuStreams(addBusinessStreams);
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
	  if(buStreams != null && buStreams.size() > 0){
	  Pagination pagination = new Pagination(1, 20, pageNum,10);	
	  pagination.setTotalRecord(buStreams.size());
	  pagination.calculatePages();
	  addBusinessStreams.setPagination(pagination);
	  }
	  if(buStreams!=null && buStreams.size()!=0)
	  {
	  try {
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"buStreams.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		main=workBook.createSheet("Business Stream");		

		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
		if(pRB!= null)
		{
         	int  l=0;
			for(int k=0;k<=Constants.BusStreamsColumnSize;k++)
			{
			path=pRB.getString("BSHeading"+k);
			headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
			l++;
			}
		}
        int j=1;
		for(int i=0;i<buStreams.size();i++)
		{ 
			row = main.createRow(j);cell = row.createCell((short) 0);
	        if(((BusStream)buStreams.get(i)).getBranch().getBuName()!=null && !((BusStream)buStreams.get(i)).getBranch().getBuName().trim().equals(""))
			{
				cell.setCellValue(((BusStream)buStreams.get(i)).getBranch().getBuName());
			} else
			{
				cell.setCellValue("");
			}
	        row = main.createRow(j);cell = row.createCell((short) 1);
	       if(((BusStream)buStreams.get(i)).getBusStreamId().getBranchCode()!=null && !((BusStream)buStreams.get(i)).getBusStreamId().getBranchCode().trim().equals(""))
			{
				cell.setCellValue(((BusStream)buStreams.get(i)).getBusStreamId().getBranchCode());
			} else {
				cell.setCellValue("");
			}
			row = main.createRow(j);cell = row.createCell((short) 2);		
			if(((BusStream)buStreams.get(i)).getBusStreamId().getBusStreamCode()!=null && !((BusStream)buStreams.get(i)).getBusStreamId().getBusStreamCode().trim().equals(""))
			{
				cell.setCellValue(((BusStream)buStreams.get(i)).getBusStreamId().getBusStreamCode());
			} else {
				cell.setCellValue("");
			}
			row = main.createRow(j);cell = row.createCell((short) 3);		
			if(((BusStream)buStreams.get(i)).getSortOrderNum()!= null )
			{
				cell.setCellValue(((BusStream)buStreams.get(i)).getSortOrderNum());
			} else {
				cell.setCellValue("");
			}
		    j++;
		}
		
		OutputStream out = response.getOutputStream(); 
		workBook.write(out);
		out.flush();
		out.close();
		} catch (Exception ioe) {
		System.out.println("Exception..." +  ioe.toString());
		}
	}
	
	addBusinessStreams.setBusExcel("false");
	return showForm(request, response, errors);
	}
	
	String searchFlag = request.getParameter("searchFlag");
	if("searchFlag".equals(searchFlag))
	{
		 String pageNumberStr = request.getParameter("pageNumber");
		  int pageNumber = 1;
	      try
	      {
	      pageNumber = new Integer(pageNumberStr).intValue();
	      }
	      catch(Exception e)
	      {
	      }
	    if(pageNumber <= 0) pageNumber = 1;
	    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
	   
	    addBusinessStreams.setPagination(pagination);
	    
		try {
			userService.searchBusinessStream(addBusinessStreams);
		} catch(Throwable t)
		{
		  t.printStackTrace();
		  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		  return showForm(request, response, errors);
		}
		addBusinessStreams.setSearchFlag("none");
		request.setAttribute("command", addBusinessStreams);
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
		
		
		Pagination pagination = new Pagination(1,20, pageNumber,10);		
		
		addBusinessStreams.setPagination(pagination);
	
		List buStreams1=new ArrayList();
		try {
				userService.searchBusinessStream(addBusinessStreams);
		} catch(ServiceException e){
		  e.printStackTrace();
		  errors.reject(e.getMessage(), e.getParams(), null);
		  return showForm(request, response, errors);
		} catch(Throwable t)
		{
		  t.printStackTrace();
		  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		  return showForm(request, response, errors);
		}//catch
		
		addBusinessStreams.setPageFlag("none");
		request.setAttribute("command", addBusinessStreams);
		return showForm(request, response, errors);
		}//page flag
				
		BusStream[] busStreamItems = addBusinessStreams.getBusStreams();
	    try
	    {
	    	
	      if(deletedList.size()>0)
		  {
				//int i=0;
		     	for(Iterator itr=(deletedList.iterator());itr.hasNext();)
			    {
		     		BusStream busStream =(BusStream)itr.next();
					
					try
					{
						 if((busStream.getBusStreamId().getBranchCode()!= null && !busStream.getBusStreamId().getBranchCode().trim().equals("")) &&
									( busStream.getBusStreamId().getBusStreamCode() != null && !busStream.getBusStreamId().getBusStreamCode().trim().equals(""))
							 ){
							 boolean deletedFlag = userService.deleteBuStreams(busStream);
						 }
					//i++;
					}//try
					catch(ServiceException e)
					{
						 addBusinessStreams.setBusStreams(addBuStream(busStreamItems,busStream));
						 deletedList.remove(busStream);
						 e.printStackTrace();
						 errors.reject(e.getMessage(), e.getParams(), null);
						 return showForm(request, response, errors);
					} catch(Throwable t)
					{
						 addBusinessStreams.setBusStreams(addBuStream(busStreamItems,busStream));
						 deletedList.remove(busStream);
						 t.printStackTrace();
						 errors.reject("generic.error", new Object[] {t.getMessage()}, null);
						 return showForm(request, response, errors);
					}
				}
		 }//for
			//}//if
	        BusStream[] busStreamsItems = addBusinessStreams.getBusStreams();
			if (busStreamsItems != null )
			{
		    	String empty="";
		    	
				for(int i=0; i< busStreamsItems.length; i++)
				{
					 BusStream busStream = busStreamsItems[i];
					
					 // START : #119240 : 07/07/09 : Fix for new business stream saved without branch name associated for that BU 
					 
					 /*if(busStream.getBusStreamId().getBranchCode()!= null && busStream.getBusStreamId().getBranchCode().trim().equals(""))
					   {
						 errors.reject("invalid.branch.error", new Object[] {busStream.getBusStreamId().getBranchCode()}, null);
						 return showForm(request, response, errors);
					   } */
					 if(busStream.getBusStreamId().getBranchCode() == null || busStream.getBusStreamId().getBranchCode().trim().equals(""))
					 {
						 errors.reject("invalid.branchCode.error");
						 return showForm(request, response, errors);
					 } 
					 // END : #119240 : 07/07/09
					 if(busStream.getBusStreamId().getBusStreamCode() != null && busStream.getBusStreamId().getBusStreamCode().trim().equals(""))
					 {
						 errors.reject("invalid.buStream.error", new Object[] {busStream.getBusStreamId().getBusStreamCode()}, null);
						 return showForm(request, response, errors);
					 }
					
					 if((busStream.getBusStreamId().getBranchCode()!= null && !busStream.getBusStreamId().getBranchCode().trim().equals("")) &&
							( busStream.getBusStreamId().getBusStreamCode() != null && !busStream.getBusStreamId().getBusStreamCode().trim().equals("")) 
				
					 )
					 if((busStream.getBusStreamId().getBranchCode()!= null && !busStream.getBusStreamId().getBranchCode().trim().equals("")) &&
								( busStream.getBusStreamId().getBusStreamCode() != null && !busStream.getBusStreamId().getBusStreamCode().trim().equals(""))
						 ){
						
						 Branch branch = userService.getBranchByName(busStream.getBusStreamId().getBranchCode());
						 busStream.setBranch(branch);
						 userService.addBuStreams(busStream);
						
					 } else
					 {
						 errors.reject("invalid.bustream.error", new Object[] {busStream.getBusStreamId().getBusStreamCode()}, null);
						 return showForm(request, response, errors);
						 
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
		
	// START : #119240 : 02/07/09
	
	/* ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "create_business_stream.htm");
    modelAndView.addObject("description", "The BuStreams has been successfully saved.");
    modelAndView.addObject("backUrlDescription", "You can continue to edit this buStream."); */

	ModelAndView modelAndView = new ModelAndView("create-business-stream-r");
	modelAndView.addObject("saved_message", "Saved successfully");
	
	// END : #119240 : 02/07/09
	 
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
	Search search = new Search();
	String pageNumberStr = request.getParameter("pageNumber");
	int pageNumber = 1;		
	try {
		pageNumber = new Integer(pageNumberStr).intValue();
	    } catch (Exception e) {}
		if (pageNumber <= 0)
			pageNumber = 1;
		Pagination pagination = new Pagination(1, 20, pageNumber,10);
		AddBusinessStream addBusinessStreams = new AddBusinessStream();
		addBusinessStreams.setPagination(pagination);
		
		BusStream busStream = new BusStream();
		busStream.setBranch(new Branch());
		busStream.setBusStreamId(new BusStreamId());
		addBusinessStreams.setBusStream(busStream);
		
		List buStreams1 = new ArrayList();
		UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    try
    {
    	buStreams1 = userService.getAllBuStreams(addBusinessStreams);
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
   
   if(buStreams1 != null && buStreams1.size()>0)
    {
		int i=0;
		BusStream[] busStreams = new BusStream[buStreams1.size()];
		for(Iterator itr = buStreams1.iterator();itr.hasNext();)
		{
			busStreams[i] =(BusStream)itr.next();
			i++;
	    }
		addBusinessStreams.setBusStreams(busStreams);
		
	}
	 request.setAttribute("command", addBusinessStreams);
   return addBusinessStreams;
  }



 private BusStream[] addBuStream(BusStream[] busStreams,BusStream existingBusStream)
 {
	 BusStream busStream = new BusStream();
	 BusStreamId busStreamId = new BusStreamId();
	 Branch buBranch = new Branch();
	 // START : #119240 : 07/07/09 : Fix for new business stream saved without branch name associated for that BU 
	 //busStreamId.setBranchCode(SecurityUtil.getUser().getBranchName());
	 // END : #119240 : 07/07/09
	 busStream.setBranch(buBranch);
	 busStream.setBusStreamId(busStreamId);
	 
    if(existingBusStream != null)
    	busStream = existingBusStream;
	
    	BusStream[] results = null;
		if(busStreams == null) results = new BusStream[1];
		else results = new BusStream[busStreams.length + 1];
		if(busStreams != null)
		{
			for(int i=0; i<busStreams.length; i++)
			{
				results[i] = busStreams[i];
			}
		}
		results[results.length - 1] = busStream;
		return results;
	}

  private BusStream[] deleteBuStream(BusStream[] busStreams, int indexs)
  {
		if(busStreams == null) return null;
		if(busStreams.length <= 0) return busStreams;
		BusStream[] results = new BusStream[busStreams.length - 1];
		int count = 0;
		for(int i=0; i<busStreams.length; i++)
		{
			if(i == indexs) continue;
			results[count] = busStreams[i];
			count ++;
		}
		return results;
	}
}
