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

import com.intertek.domain.ServiceLocationSearch;
import com.intertek.domain.StringSearchField;
import com.intertek.entity.Country;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.State;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Page;
import com.intertek.pagination.Pagination;
import com.intertek.service.CountryService;
import com.intertek.service.ServiceLocationService;
import com.intertek.util.Constants;

public class SearchServiceLocationPopupController extends SimpleFormController
{
  public SearchServiceLocationPopupController() {
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
	  int count = 0;
	  int pages = 0;
	  int num = 0;
    String pageNumberStr = request.getParameter("pageNumber");
    String sortFlag = request.getParameter("sortFlag");
			String sxcel=request.getParameter("sxcel");
    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;


    ServiceLocationSearch search = (ServiceLocationSearch)command;

    Pagination pagination = new Pagination(1, 20, pageNumber,10);
    search.setPagination(pagination);

 // START : #119240 
	String pageSort = request.getParameter("checkPageSort");
        
    boolean changeSortOrder = false; 
    if(pageNumberStr != null && Integer.valueOf(pageNumberStr).intValue() == 1){
    	if(sortFlag != null && !sortFlag.trim().equals("") && pageSort.equals("")){
    		changeSortOrder = true;
    	}else if(sortFlag == null || sortFlag.trim().equals("")){
    		  search.setSortOrderFlag("");
  			  search.setCurrentSortFlag("");
  			  search.setPrevSortFlag("");
    	}
    }
	
	if(pageNumberStr != null && Integer.valueOf(pageNumberStr).intValue() == 1 && changeSortOrder){
		  
		  if(sortFlag != null && !sortFlag.trim().equals("")){
			  search.setCurrentSortFlag(sortFlag);
			  if(search.getCurrentSortFlag()!= null && search.getCurrentSortFlag().trim().equals(search.getPrevSortFlag())){
				  search.setSortOrderFlag("desc");
				  search.setPrevSortFlag("");
			  } else {
				  search.setPrevSortFlag(sortFlag);
				  search.setSortOrderFlag("asc");
			  }
		  } else {
			  search.setSortOrderFlag("");
			  search.setCurrentSortFlag("");
			  search.setPrevSortFlag("");
		  }
	 }
	// END : #119240 
    
    
   String reqForm = search.getReqForm();
    ServiceLocationService serviceLocationService = (ServiceLocationService)ServiceLocator.getInstance().getBean("serviceLocationService");
	    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
   /* ServiceLocation serviceLocation =new  ServiceLocation();
    List results = new ArrayList();*/
    if(sortFlag != null && !sortFlag.trim().equals(""))
    {
    	
    	/*List finalResults = new ArrayList();
    	System.out.println("pageNumberStr="+pageNumberStr);
    	if(pageNumberStr.equals("1"))
    	{*/
    		try
	        {
	          serviceLocationService.searchServiceLocation(search,reqForm,sortFlag);
	        } catch(Throwable t)
	        {
	            t.printStackTrace();
	            errors.reject("generic.error", new Object[] {t.getMessage()}, null);
	            return showForm(request, response, errors);
	        }
	       /* catch(Exception e)
	        {
	          e.printStackTrace();
	
	          errors.reject("search.serviceLocation.error", new Object[] {e.getMessage()}, null);
	          return showForm(request, response, errors);
	        }*/
	        return showForm(request, response, errors);       
    	}

	if((sxcel!=null) && "true".equals(sxcel))
	{ 
    List  servs=new ArrayList();
	search.setPagination(null);
	serviceLocationService.searchServiceLocation(search,reqForm,sortFlag);
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
				if(reqForm != null && reqForm.trim().equals("destinationForm")){
					for(int k=2;k<=5;k++)
					{   if(k != 3){
						path=pRB.getString("SLHeading"+k);
						headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
						l++;
						}
					}
				} else {
					for(int k=0;k<=Constants.SLColumnSize;k++)
					{
						path=pRB.getString("SLHeading"+k);
						headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
						l++;
					}
				}
			}


        int j=1;
		for(int i=0;i<servs.size();i++)
		{ 
		if(reqForm != null && reqForm.trim().equals("destinationForm")){
//			row = main.createRow(j);cell = row.createCell((short) 2);
		    row = main.createRow(j);cell = row.createCell((short) 0);
		
			if(((ServiceLocation)servs.get(i)).getCity()!=null && !((ServiceLocation)servs.get(i)).getCity().trim().equals(""))
			{cell.setCellValue(((ServiceLocation)servs.get(i)).getCity());}
			else{cell.setCellValue("");}
		   // row = main.createRow(j);cell = row.createCell((short) 4);
			 row = main.createRow(j);cell = row.createCell((short) 1);
			
			if(((ServiceLocation)servs.get(i)).getStateCode()!=null && !((ServiceLocation)servs.get(i)).getStateCode().trim().equals(""))
			{			
	            State state=null;
				state=countryService.getStateByCode(((ServiceLocation)servs.get(i)).getStateCode(), ((ServiceLocation)servs.get(i)).getCountryCode());
				if(state!=null)
				{cell.setCellValue(state.getName());}}
			    else{cell.setCellValue("");}
	
	      //  row = main.createRow(j);cell = row.createCell((short) 5);
			  row = main.createRow(j);cell = row.createCell((short) 2);
			if(((ServiceLocation)servs.get(i)).getCountry().getName()!=null && !((ServiceLocation)servs.get(i)).getCountry().getName().trim().equals(""))
			{cell.setCellValue(((ServiceLocation)servs.get(i)).getCountry().getName());}
			else{cell.setCellValue("");}
			j++;
		} else {
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

        row = main.createRow(j);cell = row.createCell((short) 5);
		if(((ServiceLocation)servs.get(i)).getCountry().getName()!=null && !((ServiceLocation)servs.get(i)).getCountry().getName().trim().equals(""))
		{cell.setCellValue(((ServiceLocation)servs.get(i)).getCountry().getName());}
		else{cell.setCellValue("");}

		j++;
		}
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
	    }
	    catch(Throwable t)
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
 //   search.setTotalResults(results);
    try
    {
      serviceLocationService.searchServiceLocation(search,reqForm,sortFlag);
    } catch(Throwable t)
    {
        t.printStackTrace();
        errors.reject("generic.error", new Object[] {t.getMessage()}, null);
        return showForm(request, response, errors);
    }
   /* catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("search.serviceLocation.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/

    request.setAttribute("command", search);
    count=pagination.getTotalRecord();
	pages=pagination.getPagesToDisplay();
	num=pagination.getNumInPage();
	List pagesList=pagination.getPages();

	if(pagesList.size()>0)
	{
		Page page =(Page)pagesList.get(0);
		String pageName=page.getName();
		if(pageName.equals("Prev"))
		search.setPageName(pageName);
		else if(count <= (num*pages))
		search.setPageName(pageName);
		else
		search.setPageName(null);
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
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	  int pageNumber = 1;
		String inputFieldId = request.getParameter("inputFieldId");
		String servCountryName="";
		String servcLocationCity ="";
		String servcLocationName ="";
		String servStateName="";
		String servCity ="";
		String servName ="";
		String reqForm = request.getParameter("reqForm");
		String sortFlag ="";
		String div1 = request.getParameter("div1");
		String div2 = request.getParameter("div2");
		String serviceLocatioName = request.getParameter("serviceLcoationName");
		String portLocatioName = request.getParameter("portLocationName");

		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
		ServiceLocationService serviceLocationService = (ServiceLocationService)ServiceLocator.getInstance().getBean("serviceLocationService");
		ServiceLocationSearch serviceLocationSearch = new ServiceLocationSearch();
		StringSearchField stringSearchField1 = new StringSearchField();
		StringSearchField stringSearchField2 = new StringSearchField();
		StringSearchField stringSearchField3 = new StringSearchField();
		StringSearchField stringSearchField4 = new StringSearchField();

		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		serviceLocationSearch.setPagination(pagination);
		try {
		if((serviceLocatioName!= null && !"".equals(serviceLocatioName.trim()))||((portLocatioName!= null) && (!"".equals(portLocatioName.trim())))){
			ServiceLocation serviceLocation=null;
			if(portLocatioName.indexOf(',')!= -1)
			{
				String servc[]=portLocatioName.split("\\,");
				if(servc.length==2)
				{
					servCity = servc[0];
					servCountryName = servc[1];
					Country country = countryService.getCountryByName(servCountryName);
					stringSearchField1.setValue(country.getCountryCode());
					stringSearchField3.setValue(servCity);
				}else
				{
				
			  int portLength = servc.length;
           	  for(int j=0;j<portLength;)
           	  {
           		  if(j>0)
           			servCity = servCity+"," + servc[j];
           		  else
           			servCity = servCity + servc[j];
	            	  serviceLocation = serviceLocationService.getServiceLocationByCity(servCity.trim());
	            	  if(serviceLocation != null)
	            	  {
	            		  servcLocationCity = servCity;
	            		  servCity = servCity;
	            	  }else
	            	  {
	            		  servCity = servCity;
	            	  }
	            	  j++;
           	  } 
           	servCity = servcLocationCity;
           	Country country = countryService.getCountryByName(servc[servc.length-1]);
			stringSearchField1.setValue(country.getCountryCode());
			
			State state = countryService.getStateByName(servc[servc.length-2]);
			if(state != null)
			stringSearchField2.setValue(state.getStateId().getStateCode());
			stringSearchField3.setValue(servCity);
			}	
		   }else
		   {
			   stringSearchField3.setValue(portLocatioName);
		   }
		   if(serviceLocatioName.indexOf(',')!= -1)
		   {
				String servc[]= serviceLocatioName.split("\\,");
				if(servc.length == 3)
				{ servName = servc[0];
				  servCity = servc[1];
				  stringSearchField3.setValue(servCity);
				  stringSearchField4.setValue(servName);
				  servCountryName=servc[2];
				  Country country = countryService.getCountryByName(servCountryName);
				  stringSearchField1.setValue(country.getCountryCode());
				}else
				{
					  int servLength = servc.length;
		           	  for(int j=0;j<servLength;)
		           	  {
		           		  if(j>0)
		           			servName = servName+"," + servc[j];
		           		  else
		           			servName = servName + servc[j];
			            	  serviceLocation = serviceLocationService.getServiceLocationByName(servName.trim());
			            	  if(serviceLocation != null)
			            	  {
			            		  servcLocationName = servName;
			            		  servName = servName;
			            	  }else
			            	  {
			            		  servCity = servCity;
			            	  }
			            	  j++;
		           	  } 
		           	servName = servcLocationName;
		           	Country country = countryService.getCountryByName(servc[servc.length-1]);
					stringSearchField1.setValue(country.getCountryCode());
					
					State state = countryService.getStateByName(servc[servc.length-2]);
					if(state != null)
					stringSearchField2.setValue(state.getStateId().getStateCode());
					stringSearchField4.setValue(servName);
				}
		   }else
		   {
			   stringSearchField4.setValue(serviceLocatioName);
		   }
			
			
			serviceLocationSearch.setCountry(stringSearchField1);
		    serviceLocationSearch.setState(stringSearchField2);
		    serviceLocationSearch.setCity(stringSearchField3);
		    serviceLocationSearch.setName(stringSearchField4);
		  //  try
		   // {
		    	serviceLocationService.searchServiceLocation(serviceLocationSearch,reqForm,sortFlag);
		  //  }
		   /* catch(Exception e)
		    {
		        e.printStackTrace();
		    }*/
		}


		serviceLocationSearch.setInputFieldId(inputFieldId);
		serviceLocationSearch.setDiv1(div1);
		serviceLocationSearch.setDiv2(div2);
		if(reqForm != null && !reqForm.trim().equals(""))
		{
		if(reqForm.equals("jobsForm"))
			serviceLocationSearch.setReqForm("jobsForm");
		}
		if(reqForm != null && !reqForm.trim().equals(""))
		{
		if(reqForm.equals("portJobsForm"))
			serviceLocationSearch.setReqForm("portJobsForm");
		}
// For itrack issue 24329
		if(reqForm != null && !reqForm.trim().equals(""))
		{
		if(reqForm.equals("destinationForm"))
			serviceLocationSearch.setReqForm("destinationForm");
		}		} catch(ServiceException e)
	    {
	    	throw new ServiceException(e.getMessage(), e.getParams(), e);
	    }
	    catch(Throwable t)
	    {
	        t.printStackTrace();
	        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
	    }
	return serviceLocationSearch;
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
	/*
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
    		System.out.println("Start Index="+start);
    		System.out.println("End Index="+end);
    	}
    	search.setResults(finalResults);
    	request.setAttribute("command", search);*/
}
