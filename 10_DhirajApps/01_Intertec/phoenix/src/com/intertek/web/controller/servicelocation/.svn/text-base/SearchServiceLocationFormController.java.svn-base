package com.intertek.web.controller.servicelocation;

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

import com.intertek.domain.ServiceLocationSearch;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.State;
import com.intertek.entity.TowingCompany;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CountryService;
import com.intertek.service.ServiceLocationService;
import com.intertek.util.Constants;

public class SearchServiceLocationFormController extends SimpleFormController
{
  public SearchServiceLocationFormController() {
    super();
    setCommandClass(ServiceLocationSearch.class);
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
	String reqForm=request.getParameter("reqForm");
    String pageNumberStr = request.getParameter("pageNumber");
    String sortFlag = request.getParameter("sortFlag");

    String sxcel=request.getParameter("sxcel");
	String submitFlag=request.getParameter("submitFlag");
	
/*	List results = new ArrayList();
	List finalResults = new ArrayList();*/


    ServiceLocationSearch search = (ServiceLocationSearch)command;
	//ServiceLocation serviceLocation =new  ServiceLocation();
    ServiceLocationService serviceLocationService = (ServiceLocationService)ServiceLocator.getInstance().getBean("serviceLocationService");
    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;
    Pagination pagination = new Pagination(1, 20, pageNumber,10);
    search.setPagination(pagination);
	//search.setPagination(null);
   	 if(sortFlag != null && !sortFlag.trim().equals(""))
	{	  
	/*if(pageNumberStr.equals("1")) 
	{*/
			try
				{
				 serviceLocationService.searchServiceLocation(search,reqForm,sortFlag);
				}
				catch(Exception e)
				{
				e.printStackTrace();
				errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
				return showForm(request, response, errors);
				}
		
			/*}

		   	results = search.getTotalResults();
	    	results = getPaginationResults(results,search,pageNumberStr);
			search.setResults(results);	*/       
	        return showForm(request, response, errors);
	    } 
		
    if((submitFlag!=null) && "true".equals(submitFlag))
	{ 
		/*serviceLocationService.searchServiceLocation(search,reqForm,sortFlag);
		results = search.getResults();
		search.setSubmitFlag("none");
		search.setTotalResults(results);
		results = search.getTotalResults();	
		results = getPaginationResults(results,search,pageNumberStr);
		search.setResults(results);  */
    	search.setSubmitFlag("none");
    	
    	try
		{
		 serviceLocationService.searchServiceLocation(search,reqForm,sortFlag);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
		return showForm(request, response, errors);
		}
	}

	if((sxcel!=null) && "true".equals(sxcel))
	{ 
    List  servs=new ArrayList();
	//int pageNumber=-1;
	search.setPagination(null);
	//servs=search.getTotalResults();
	try
	{
	 serviceLocationService.searchServiceLocation(search,reqForm,sortFlag);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
	return showForm(request, response, errors);
	}
	servs=search.getResults();
	if(servs!=null&&servs.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"servlocations.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("Service");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.SLColumnSize;k++)
				{
				path=pRB.getString("SLHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}


        int j=1;
		for(int i=0;i<servs.size();i++)
		{ 
		row=main.createRow(j);cell=row.createCell((short) 0);cell.setCellValue(((ServiceLocation)servs.get(i)).getServiceLocationCode());
		row = main.createRow(j);cell = row.createCell((short) 1);cell.setCellValue(((ServiceLocation)servs.get(i)).getName());
		
		row = main.createRow(j);cell = row.createCell((short) 2);
		if(((ServiceLocation)servs.get(i)).getCity()!=null && !((ServiceLocation)servs.get(i)).getCity().trim().equals(""))
		{cell.setCellValue(((ServiceLocation)servs.get(i)).getCity());}
		else{cell.setCellValue("");}
		
        String address="";
		row = main.createRow(j);cell = row.createCell((short) 3);
			
		if(((ServiceLocation)servs.get(i)).getAddress1()!=null && !((ServiceLocation)servs.get(i)).getAddress1().trim().equals(""))
		 {address=((ServiceLocation)servs.get(i)).getAddress1();}
 		 
		 if(((ServiceLocation)servs.get(i)).getAddress2()!=null && !((ServiceLocation)servs.get(i)).getAddress2().trim().equals(""))
		 {if(((ServiceLocation)servs.get(i)).getAddress1()!=null && !((ServiceLocation)servs.get(i)).getAddress1().trim().equals(""))

			 {
			  address=address + "," +((ServiceLocation)servs.get(i)).getAddress2();
		     }
			 else{address=((ServiceLocation)servs.get(i)).getAddress2();}
		 }
		 

		 if(((ServiceLocation)servs.get(i)).getAddress3()!=null && !((ServiceLocation)servs.get(i)).getAddress3().trim().equals(""))
		 {if(address!=null && !address.trim().equals(""))
			 {address=address+ "," +((ServiceLocation)servs.get(i)).getAddress3();}
			 else
			 {address=((ServiceLocation)servs.get(i)).getAddress3();}
         }


        if(((ServiceLocation)servs.get(i)).getAddress4()!=null && !((ServiceLocation)servs.get(i)).getAddress4().trim().equals(""))
		 {if(address!=null && !address.trim().equals(""))
			 {address=address+ "," +((ServiceLocation)servs.get(i)).getAddress4();}
			 else
			 {address=((ServiceLocation)servs.get(i)).getAddress4();}
		 }

		if(address!=null && !address.trim().equals(""))
			{	
			cell.setCellValue(address);}
		    else{cell.setCellValue("");}
       
	    row = main.createRow(j);cell = row.createCell((short) 4);
		if(((ServiceLocation)servs.get(i)).getStateCode()!=null && !((ServiceLocation)servs.get(i)).getStateCode().trim().equals(""))
		{			
            State state=null;
			state=countryService.getStateByCode(((ServiceLocation)servs.get(i)).getStateCode(), ((ServiceLocation)servs.get(i)).getCountryCode());
			if(state!=null)
			{cell.setCellValue(state.getName());}}
		    else{cell.setCellValue("");}


			
		/*	cell.setCellValue(((ServiceLocation)servs.get(i)).getStateCode());}
		else{cell.setCellValue("");}*/

        row = main.createRow(j);cell = row.createCell((short) 5);
		if(((ServiceLocation)servs.get(i)).getCountry().getName()!=null && !((ServiceLocation)servs.get(i)).getCountry().getName().trim().equals(""))
		{cell.setCellValue(((ServiceLocation)servs.get(i)).getCountry().getName());}
		else{cell.setCellValue("");}

		j++;
		}
		
		OutputStream out = response.getOutputStream(); 
		workBook.write(out);
		out.flush();
		out.close();
		}
		catch (Exception ioe) {
		System.out.println("Exception..." +  ioe.toString());
		}
	}
	search.setSxcel("false");
	return showForm(request, response, errors);
	}

	
	List results=search.getResults();
	if(results!=null && results.size()==1){
		ServiceLocation sl=(ServiceLocation)results.get(0);
		return new ModelAndView(new RedirectView("edit_service_location.htm"), "serviceLocationCode", sl.getServiceLocationCode());
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

  return new ServiceLocationSearch();
  }
private List getPaginationResults(List results,ServiceLocationSearch search,String pageNumberStr){
		
		ServiceLocation serviceLocation =new  ServiceLocation();
	  
	    List finalResults = new ArrayList();
	    
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;
		
		Pagination servcLocPagination = new Pagination(1, 20, pageNumber,10);
    	search.setPagination(servcLocPagination);
    	if(servcLocPagination != null)
		{
		  if(results.size() > 0){
			 servcLocPagination.setTotalRecord(results.size());
		  }
			 servcLocPagination.calculatePages();
		}
    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
    	int sortedResultsSize = results.size();
    	
    	if(startRecord == 1 )
    	{
    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
    		{
		    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
		    	{
		    		serviceLocation = (ServiceLocation) results.get(i);
		    		finalResults.add(serviceLocation);
		    	}
    		}else
    		{
    			for(int i=0;i<sortedResultsSize;i++)
		    	{
		    		serviceLocation = (ServiceLocation) results.get(i);
		    		finalResults.add(serviceLocation);
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
		    		serviceLocation = (ServiceLocation) results.get(i);
		    		finalResults.add(serviceLocation);
		    	}
    		}else
    		{
    			start=(startRecord -1)*Constants.RECORDS_PAGE;
    			end=sortedResultsSize;
    			for(int i=start;i<end;i++)
		    	{
		    		serviceLocation = (ServiceLocation) results.get(i);
		    		finalResults.add(serviceLocation);
		    	}
    		}
    	}
    	return finalResults;
	}
}

