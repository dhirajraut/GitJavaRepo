package com.intertek.web.controller.shippingagent;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.ShippingAgentSearch;
import com.intertek.entity.DropDowns;
import com.intertek.entity.ShippingAgent;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.DropdownService;
import com.intertek.service.ShippingAgentService;
import com.intertek.util.Constants;

public class SearchShippingAgentFormController extends SimpleFormController
{
  public SearchShippingAgentFormController() {
    super();
    setCommandClass(ShippingAgentSearch.class);
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
	    ShippingAgentSearch search = (ShippingAgentSearch)command;
		ShippingAgentService service = (ShippingAgentService)ServiceLocator.getInstance().getBean("shippingAgentService");
		DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

		ShippingAgent shippingAgent = new  ShippingAgent();
		String pageNumberStr = request.getParameter("pageNumber");
		String sortFlag = request.getParameter("sortFlag");
		String sxcel=request.getParameter("sxcel");
		String submitFlag=request.getParameter("submitFlag");
		
		/*List results = new ArrayList();
		List finalResults = new ArrayList();	
		search.setPagination(null);*/
		
		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);
			if(sortFlag != null && !sortFlag.trim().equals(""))
			{	  
			//if(pageNumberStr.equals("1")) 
				//{
					try
						{
						
						service.searchShippingAgent(search,sortFlag);	
					} catch(Throwable t)
					{
					  t.printStackTrace();
					  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
						return showForm(request, response, errors);
						}				
					/*}
				results = search.getTotalResults();
				results = getPaginationResults(results,search,pageNumberStr);
				search.setResults(results);	 */      
				return showForm(request, response, errors);
			 } 
	 if((submitFlag!=null) && "true".equals(submitFlag))
		{             
			/*service.searchShippingAgent(search,sortFlag);
			results = search.getResults();
			search.setSubmitFlag("none");
			search.setTotalResults(results);
			results = search.getTotalResults();	
			results = getPaginationResults(results,search,pageNumberStr);
			search.setResults(results); */
		 search.setSubmitFlag("none");
		try {
		service.searchShippingAgent(search,sortFlag);	
		} catch(Throwable t)
		{
		  t.printStackTrace();
		  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		  return showForm(request, response, errors);
		}
		/*	catch(Exception e)
			{
			e.printStackTrace();
			errors.reject("shippingAgent.error", new Object[] {e.getMessage()}, null);
			return showForm(request, response, errors);
			}	*/	
		}

if((sxcel!=null) && "true".equals(sxcel))
	{ 
    List  ships=new ArrayList();
	//int pageNumber=-1;
	search.setPagination(null);
	 try
		{
		
		service.searchShippingAgent(search,sortFlag);	
		} catch(Throwable t)
		{
		  t.printStackTrace();
		  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		  return showForm(request, response, errors);
		}
		ships=search.getResults();
	//ships=search.getTotalResults();
	if(ships!=null&&ships.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"shippingagent.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("Shipping");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.SColumnSize;k++)
				{
				path=pRB.getString("SHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}


        int j=1;
		for(int i=0;i<ships.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((ShippingAgent)ships.get(i)).getId());
		row = main.createRow(j);cell = row.createCell((short) 1);
		if(((ShippingAgent)ships.get(i)).getName()!=null && !((ShippingAgent)ships.get(i)).getName().trim().equals(""))
		{cell.setCellValue(((ShippingAgent)ships.get(i)).getName());}
		else{cell.setCellValue("");}
        String dropdowntype="activeStatus";
		DropDowns dropDown=null;
		dropDown=dropdownService.getDropdownByDropdownCodeAndType(((ShippingAgent)ships.get(i)).getStatus(),dropdowntype);
		if(dropDown!=null)
		{
		//row = main.createRow(j);cell = row.createCell((short) 2);cell.setCellValue(((ShippingAgent)ships.get(i)).getStatus());
		row = main.createRow(j);cell = row.createCell((short) 2);cell.setCellValue(dropDown.getFieldValue());}
        else{cell.setCellValue("");}


		j++;
		}
		
		OutputStream out = response.getOutputStream(); 
		workBook.write(out);
		out.flush();
		out.close();
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
		/*catch (Exception ioe) {
		System.out.println("Exception..." +  ioe.toString());
		}*/
	}
	search.setSxcel("false");
	return showForm(request, response, errors);
	}

	List results=search.getResults();
	if(results!=null && results.size()==1){
		ShippingAgent sa=(ShippingAgent)results.get(0);
		return new ModelAndView(new RedirectView("edit_shipping_agent.htm"), "id", sa.getId());
	}

		request.setAttribute("command", search);
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
	  }

	  /* (non-Javadoc)
	   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	   */
	  protected Object formBackingObject(
		HttpServletRequest request
	  ) throws Exception
	  {
		
	return  new ShippingAgentSearch();
  }
 private List getPaginationResults(List results,ShippingAgentSearch search,String pageNumberStr){
		
		ShippingAgent shippingAgent = new  ShippingAgent();
	    List finalResults = new ArrayList();
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;		
		Pagination shipAgentPagination = new Pagination(1, 20, pageNumber,10);
	    	search.setPagination(shipAgentPagination);
	    	if(shipAgentPagination != null)
			{
			  if(results.size() > 0){
				  shipAgentPagination.setTotalRecord(results.size());
			  }
			  shipAgentPagination.calculatePages();
			}
	    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
	    	int sortedResultsSize = results.size();
	    	
	    	if(startRecord == 1 )
	    	{
	    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
	    		{
			    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
			    	{
			    		shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
			    	}
	    		}else
	    		{
	    			for(int i=0;i<sortedResultsSize;i++)
			    	{
	    				shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
			    	}
	    		}
	    	}else
	    	{
	    		int end=startRecord * Constants.RECORDS_PAGE;
	    		int start=end - Constants.RECORDS_PAGE;
	    		if(end<=sortedResultsSize)
	    		{
		    		for(int i=start;i<end;i++)
			    	{
		    			shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
			    	}
	    		}else
	    		{
	    			start=(startRecord -1)*Constants.RECORDS_PAGE;
	    			end=sortedResultsSize;
	    			for(int i=start;i<end;i++)
			    	{
	    				shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
			    	}
	    		}
	    		
	    	}
    	return finalResults;
	}
}
