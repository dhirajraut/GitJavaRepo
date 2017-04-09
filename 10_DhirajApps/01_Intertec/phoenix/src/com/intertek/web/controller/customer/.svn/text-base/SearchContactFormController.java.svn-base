package com.intertek.web.controller.customer;

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

import com.intertek.domain.ContactSearch;
import com.intertek.entity.ContactCust;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CustomerService;
import com.intertek.util.Constants;

public class SearchContactFormController extends SimpleFormController
{
  public SearchContactFormController() {
    super();
    setCommandClass(ContactSearch.class);
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
  ContactSearch search = (ContactSearch)command;
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");

    String pageNumberStr = request.getParameter("pageNumber");
    String sortFlag = request.getParameter("sortFlag");
	String cxcel=request.getParameter("cxcel");
	String submitFlag=request.getParameter("submitFlag");
	//List results = new ArrayList();
	//List finalResults = new ArrayList();	
   
   // ContactCust contactCust = new ContactCust();
//  search.setPagination(null);
    int pageNumber;
	if(pageNumberStr == null || pageNumberStr.trim().equals(""))
	{
		pageNumber = 1;
	}
	else
	{
    	pageNumber = Integer.parseInt(pageNumberStr);
	}
    
	Pagination pagination = new Pagination(1,20,pageNumber,10);
	search.setPagination(pagination);
   //search.setPagination(null);
   
if(sortFlag != null && !sortFlag.trim().equals(""))
	{	  
	
			try{
				 customerService.searchContact(search,sortFlag);
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
				/*catch(Exception e){
				e.printStackTrace();
				errors.reject("search.contact.error", new Object[] {e.getMessage()}, null);
				return showForm(request, response, errors);
				}	*/	
		
		   //	results = search.getTotalResults();
	    	//results = getPaginationResults(results,search,pageNumberStr);
			//search.setResults(results);	       
	        return showForm(request, response, errors);
	} 

	if((submitFlag!=null) && "true".equals(submitFlag))
	{ 
		/*customerService.searchContact(search,sortFlag);
		results = search.getResults();
		search.setSubmitFlag("none");
		search.setTotalResults(results);
		results = search.getTotalResults();	
		results = getPaginationResults(results,search,pageNumberStr);
		System.out.println("results size in submit flag after pagination is  "+ results.size());
		search.setResults(results); */ 
		search.setSubmitFlag("none");
		try{
			 customerService.searchContact(search,sortFlag);
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
			/*catch(Exception e){
			e.printStackTrace();
			errors.reject("search.contact.error", new Object[] {e.getMessage()}, null);
			return showForm(request, response, errors);
			}*/		
	}

   if((cxcel!=null) && "true".equals(cxcel))
	{ 
    List  contacts=new ArrayList();
	//int pageNumber=-1;
    
	search.setPagination(null);
	try{
		 customerService.searchContact(search,sortFlag);
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
		/*catch(Exception e){
		e.printStackTrace();
		errors.reject("search.contact.error", new Object[] {e.getMessage()}, null);
		return showForm(request, response, errors);
		}	*/	
	//contacts=search.getTotalResults();
	contacts=search.getResults();
	
	if(contacts!=null&&contacts.size()!=0)
	{
	try {
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"contacts.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("Contacts");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.CoColumnSize;k++)
				{
				path=pRB.getString("CoHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}
        int j=1;
		for(int i=0;i<contacts.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((ContactCust)contacts.get(i)).getContact().getId());
		row = main.createRow(j);cell = row.createCell((short) 1);
		if(((ContactCust)contacts.get(i)).getContact().getFirstName()!=null&& ((ContactCust)contacts.get(i)).getContact().getLastName() != null){
		cell.setCellValue((((ContactCust)contacts.get(i)).getContact().getFirstName())+" "+(((ContactCust)contacts.get(i)).getContact().getLastName()));
		}
		else if(((ContactCust)contacts.get(i)).getContact().getFirstName()!= null ){
        cell.setCellValue((((ContactCust)contacts.get(i)).getContact().getFirstName())+"");
        }
		else if(((ContactCust)contacts.get(i)).getContact().getLastName()!= null ){
        cell.setCellValue(""+(((ContactCust)contacts.get(i)).getContact().getLastName()));
        }
		else
		{cell.setCellValue("");}
		

        row = main.createRow(j);cell = row.createCell((short) 2);
		if(((ContactCust)contacts.get(i)).getCustomer().getCustCode()!=null && !((ContactCust)contacts.get(i)).getCustomer().getCustCode().trim().equals(""))
		{cell.setCellValue(((ContactCust)contacts.get(i)).getCustomer().getCustCode());}
		else{cell.setCellValue("");}

        row = main.createRow(j);cell = row.createCell((short) 3);
		if(((ContactCust)contacts.get(i)).getCustomer().getName()!=null && !((ContactCust)contacts.get(i)).getCustomer().getName().trim().equals(""))
		{cell.setCellValue(((ContactCust)contacts.get(i)).getCustomer().getName());}
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
     search.setCxcel("false");
	return showForm(request, response, errors);
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
    return new ContactSearch();
  }

 private List getPaginationResults(List results,ContactSearch search,String pageNumberStr){
		
		ContactCust contactCust = new ContactCust();
	  
	    List finalResults = new ArrayList();
    
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;
		
		Pagination contactPagination = new Pagination(1, 20, pageNumber,10);
    	
    	search.setPagination(contactPagination);
    	if(contactPagination != null)
		{
		  if(results.size() > 0){
			  contactPagination.setTotalRecord(results.size());
		  }
		  contactPagination.calculatePages();
		}
    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
    	int sortedResultsSize = results.size();
    	
    	if(startRecord == 1 )
    	{
    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
    		{
		    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
		    	{
		    		contactCust = (ContactCust) results.get(i);
		    		finalResults.add(contactCust);
		    	}
    		}else
    		{
    			for(int i=0;i<sortedResultsSize;i++)
		    	{
    				contactCust = (ContactCust) results.get(i);
		    		finalResults.add(contactCust);
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
	    			contactCust = (ContactCust) results.get(i);
		    		finalResults.add(contactCust);
		    	}
    		}else
    		{
    			start=(startRecord -1)*Constants.RECORDS_PAGE;
    			end=sortedResultsSize;
    			for(int i=start;i<end;i++)
		    	{
    				contactCust = (ContactCust) results.get(i);
		    		finalResults.add(contactCust);
		    	}
    		}
    		
    	}
    	return finalResults;
	}
}
