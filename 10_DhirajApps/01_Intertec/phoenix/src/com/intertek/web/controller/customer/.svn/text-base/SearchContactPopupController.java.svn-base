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
import com.intertek.entity.Contact;
import com.intertek.entity.ContractCustContact;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CustomerService;
import com.intertek.util.Constants;

public class SearchContactPopupController extends SimpleFormController {
	public SearchContactPopupController() {
		super();
		setCommandClass(ContactSearch.class);
		//setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String pageNumberStr = request.getParameter("pageNumber");
		String searchType = request.getParameter("searchType");
		String searchForm = request.getParameter("searchForm");
		  String sortFlag = request.getParameter("sortFlag");
		String cxcel=request.getParameter("cxcel");

		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		ContactSearch search = (ContactSearch) command;
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);
		search.setSearchForm(searchForm);
	
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
		
		CustomerService customerService = (CustomerService) ServiceLocator	.getInstance().getBean("customerService");
		// List results = new ArrayList();

		 //   Contact contact = new Contact();
		    if(sortFlag != null && !sortFlag.trim().equals(""))
		    {
		    //	List finalResults = new ArrayList();

		    	//if(pageNumberStr.equals("1"))
		    	//{
		    		try
			        {
		    			if(search.getSearchForm() != null && (search.getSearchForm().trim().equals("conslInvCreateForm")|| search.getSearchForm().trim().equals("conslInvEditForm"))){
		    				customerService.searchContact(search, sortFlag);
		    			}else{
		    				customerService.contactIDSearch(search,sortFlag);
		    			}
			        }
			        catch(Exception e)
			        {
			        	e.printStackTrace();
						errors.reject("search.contact.error", new Object[] { e.getMessage() }, null);
						return showForm(request, response, errors);
			        }
		    	//}
		    	/*results = search.getTotalResults();

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
				    		if(search.getSearchForm().trim().equals("conslInvCreateForm")|| search.getSearchForm().trim().equals("conslInvEditForm")){
				    			ContactCust contactCust = (ContactCust)results.get(i);
				    			finalResults.add(contactCust);
				    		}else{
				    			contact = (Contact) results.get(i);
				    			finalResults.add(contact);
				    		}

				    	}
		    		}else
		    		{
		    			for(int i=0;i<sortedResultsSize;i++)
				    	{
		    				if(search.getSearchForm().trim().equals("conslInvCreateForm")|| search.getSearchForm().trim().equals("conslInvEditForm")){
				    			ContactCust contactCust = (ContactCust)results.get(i);
				    			finalResults.add(contactCust);
				    		}else{
				    			contact = (Contact) results.get(i);
				    			finalResults.add(contact);
				    		}

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
			    			if(search.getSearchForm().trim().equals("conslInvCreateForm")|| search.getSearchForm().trim().equals("conslInvEditForm")){
				    			ContactCust contactCust = (ContactCust)results.get(i);
				    			finalResults.add(contactCust);
				    		}else{
				    			contact = (Contact) results.get(i);
				    			finalResults.add(contact);
				    		}

				    	}
		    		}else
		    		{
		    			start=(startRecord -1)*Constants.RECORDS_PAGE;
		    			end=sortedResultsSize;
		    			for(int i=start;i<end;i++)
				    	{
		    				if(search.getSearchForm().trim().equals("conslInvCreateForm")|| search.getSearchForm().trim().equals("conslInvEditForm")){
				    			ContactCust contactCust = (ContactCust)results.get(i);
				    			finalResults.add(contactCust);
				    		}else{
				    			contact = (Contact) results.get(i);
				    			finalResults.add(contact);
				    		}
				    	}
		    		}

		    	}

		    	search.setResults(finalResults);
		    	search.setTotalResults(finalResults);*/
		    	request.setAttribute("command", search);

		        return showForm(request, response, errors);
		    }
 if((cxcel!=null) && "true".equals(cxcel))
	{ 
    List  contacts=new ArrayList();
	//int pageNumber=-1;
	search.setPagination(null);
	try {
			if(search.getSearchForm() != null && (search.getSearchForm().trim().equals("conslInvCreateForm") || search.getSearchForm().trim().equals("conslInvEditForm")))
				customerService.searchContact(search, sortFlag);
			else
				customerService.contactIDSearch(search,sortFlag);
		} catch (Exception e) {
			e.printStackTrace();
			errors.reject("search.contact.error", new Object[] { e.getMessage() }, null);
			return showForm(request, response, errors);
		}
	contacts=search.getResults();
	// Contact contact = new Contact();
	if(contacts!=null&&contacts.size()!=0)
	{
	try
		{
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
				for(int k=0;k<=Constants.CPColumnSize;k++)
				{
				path=pRB.getString("CoHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}
        int j=1;
        //Contract->Customer->Contact enhancement issue 96937 changes starts
        if(("createJobsInspForm".equals(searchForm))||("editJobsInspForm".equals(searchForm))
        		||("createJobsMarineForm".equals(searchForm))||("editJobsMarineForm".equals(searchForm))
        		||("editJobsOutSourcingForm".equals(searchForm))||("createJobsOutSourcingForm".equals(searchForm))
        		||("createJobsAnalyticalServicesForm".equals(searchForm))||("editJobsAnalyticalServicesForm".equals(searchForm))) {
        
        	for(int i=0;i<contacts.size();i++)
			{ 
			row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((ContractCustContact)contacts.get(i)).getContractCustContactId().getContactId());
			row = main.createRow(j);cell = row.createCell((short) 1);
			if(((ContractCustContact)contacts.get(i)).getContact().getFirstName()!=null&& ((ContractCustContact)contacts.get(i)).getContact().getLastName() != null){
			cell.setCellValue((((ContractCustContact)contacts.get(i)).getContact().getFirstName())+" "+(((ContractCustContact)contacts.get(i)).getContact().getLastName()));
			}
			else if(((ContractCustContact)contacts.get(i)).getContact().getFirstName()!= null ){
	        cell.setCellValue((((ContractCustContact)contacts.get(i)).getContact().getFirstName())+"");
	        }
			else if(((ContractCustContact)contacts.get(i)).getContact().getLastName()!= null ){
	        cell.setCellValue(""+(((ContractCustContact)contacts.get(i)).getContact().getLastName()));
	        }
			else
			{cell.setCellValue("");}	
			j++;
			}
        } else {//Contract->Customer->Contact enhancement issue 96937 changes end
        
			for(int i=0;i<contacts.size();i++)
			{ 
			row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((Contact)contacts.get(i)).getId());
			row = main.createRow(j);cell = row.createCell((short) 1);
			if(((Contact)contacts.get(i)).getFirstName()!=null&& ((Contact)contacts.get(i)).getLastName() != null){
			cell.setCellValue((((Contact)contacts.get(i)).getFirstName())+" "+(((Contact)contacts.get(i)).getLastName()));
			}
			else if(((Contact)contacts.get(i)).getFirstName()!= null ){
	        cell.setCellValue((((Contact)contacts.get(i)).getFirstName())+"");
	        }
			else if(((Contact)contacts.get(i)).getLastName()!= null ){
	        cell.setCellValue(""+(((Contact)contacts.get(i)).getLastName()));
	        }
			else
			{cell.setCellValue("");}	
			j++;
			}
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
     search.setCxcel("false");
	return showForm(request, response, errors);
	}
		    // search.setTotalResults(results);
		try {
			if(search.getSearchForm() != null && (search.getSearchForm().trim().equals("conslInvCreateForm") || search.getSearchForm().trim().equals("conslInvEditForm")))
				customerService.searchContact(search, sortFlag);
			else
				customerService.contactIDSearch(search,sortFlag);
		} catch (Exception e) {
			e.printStackTrace();
			errors.reject("search.contact.error", new Object[] { e.getMessage() }, null);
			return showForm(request, response, errors);
		}

		request.setAttribute("command", search);

		return showForm(request, response, errors);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
	}

	/*  protected boolean suppressValidation(HttpServletRequest request)
	 {
	 String searchType = request.getParameter("searchType");
	 if((searchType != null) && ("opsBranch".equals(searchType) || "labBranch".equals(searchType) || searchType.startsWith("assoc")))
	 {
	 return true;
	 }

	 return super.suppressValidation(request);
	 }	*/

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String inputFieldId = request.getParameter("inputFieldId");
		String sortFlag = "";
		String searchForm = request.getParameter("searchForm");
		String rowNum = request.getParameter("rowNum");
		String rowNumContact = request.getParameter("rowNumContact");
		String div=request.getParameter("divName");
		String divbody=request.getParameter("divbody");
		String custCode = request.getParameter("custCode");
		String contractCode = request.getParameter("contractCode");
		ContactSearch contactSearch = new ContactSearch();
		contactSearch.setSearchForm(searchForm);
		contactSearch.setInputFieldId(inputFieldId);
		contactSearch.setRowNum(rowNum);
		contactSearch.setRowNumContact(rowNumContact);
		contactSearch.setDivName(div);
		contactSearch.setDivbody(divbody);
		contactSearch.setCustCode(custCode);
		contactSearch.setContractCode(contractCode);
		contactSearch.getCustomerId().setValue(custCode);
		contactSearch.getCustomerId().setOperator(Constants.EQUALS);
	//	contactSearch.getCustCode().setValue(custCode);
  String pageNumberStr = request.getParameter("pageNumber");
if(searchForm != null)
{
if(("createJobsInspForm".equals(searchForm))||("editJobsInspForm".equals(searchForm))
	||("createJobsMarineForm".equals(searchForm))||("editJobsMarineForm".equals(searchForm))
	||("editJobsOutSourcingForm".equals(searchForm))||("createJobsOutSourcingForm".equals(searchForm))
	||("conslInvCreateForm".equals(searchForm))||("conslInvEditForm".equals(searchForm))
	||("createJobsAnalyticalServicesForm".equals(searchForm))||("editJobsAnalyticalServicesForm".equals(searchForm))) {
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
    contactSearch.setPagination(pagination);
    contactSearch.setSearchForm(searchForm);
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    try {
    if("conslInvCreateForm".equals(searchForm) || "conslInvEditForm".equals(searchForm)){
    	customerService.searchContact(contactSearch, sortFlag);
    }else
		customerService.contactIDSearch(contactSearch,sortFlag);
    } catch(ServiceException e)
    {
    	throw new ServiceException(e.getMessage(), e.getParams(), e);
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
request.setAttribute("command", contactSearch);
}
}
return contactSearch;
}
}
