package com.intertek.web.controller.job;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddJobOrder;
import com.intertek.domain.ContractSearch;
import com.intertek.domain.StringSearchField;
import com.intertek.entity.CfgContract;
import com.intertek.entity.ContractCustContact;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.JobService;
import com.intertek.util.Constants;
import com.intertek.util.SortUtil;
import com.intertek.entity.JobOrder;

public class SearchJobOrderInspPopupFormController extends SimpleFormController
{
  public SearchJobOrderInspPopupFormController() {
    super();
    setCommandClass(ContractSearch.class);
	setSessionForm(true);
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
	  
	   String pageNumberStr = request.getParameter("pageNumber");
		String searchForm = request.getParameter("searchForm");
		  	  String inputFieldId = request.getParameter("inputFieldId");
		String sortFlag = request.getParameter("sortFlag");
		List results = new ArrayList();
	  List finalResults = new ArrayList();			  
	  String cxcel=request.getParameter("cxcel");
	  
	    ContractSearch search = (ContractSearch)command;   
		
		
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
	    
	    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");			
		ContractCustContact contractCustContact=new ContractCustContact();

      if(sortFlag != null && !sortFlag.trim().equals(""))
	    {	    	
	    	if(pageNumberStr.equals("1")) 
	    	{
	    try
	    {
	    			jobService.searchContract(search);
	    }
	    catch(Exception e)
	    {
			e.printStackTrace();
						errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
						 return showForm(request, response, errors);
					}
		        results = search.getResults();
	    	}
	    	
				if(pageNumberStr.equals("1"))
				{
					results = SortUtil.sortByContractColumnHeader(results,sortFlag, search.getSortOrderFlag());
					search.setTotalResults(results);
				}
	    	results = search.getTotalResults();
	    	results = getPaginationResults(results,search,pageNumberStr);
			search.setResults(results);	       
	        return showForm(request, response, errors);
	    }  

   
   if((cxcel!=null) && "true".equals(cxcel))
	{ 
    List  contacts=new ArrayList();
	//int pageNumber=-1;
	search.setPagination(null);
	jobService.searchContract(search);
	contacts=search.getResults();
	if(contacts!=null&&contacts.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"jobContracts.xls\"");
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
				for(int k=0;k<=Constants.JConColumnSize;k++)
				{
				path=pRB.getString("JCHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}
        int j=1;
		for(int i=0;i<contacts.size();i++)
		{ 
			short cellIndex=0;
		row = main.createRow(j);cell = row.createCell((short) cellIndex++);
		if(((ContractCustContact)contacts.get(i)).getContractCust().getContract().getDescription()!=null && !((ContractCustContact)contacts.get(i)).getContractCust().getContract().getDescription().trim().equals(""))
		{
		cell.setCellValue(((ContractCustContact)contacts.get(i)).getContractCust().getContract().getDescription());
		}else
			{cell.setCellValue("");}

		row=main.createRow(j);cell=row.createCell((short)cellIndex++);cell.setCellValue(((ContractCustContact)contacts.get(i)).getContractCust().getContract().getContractCode());

		row=main.createRow(j);cell=row.createCell((short)cellIndex++);cell.setCellValue(((ContractCustContact)contacts.get(i)).getContractCust().getCustomer().getCustCode());
		row=main.createRow(j);cell=row.createCell((short)cellIndex++);
			Set<CfgContract> aSet=((ContractCustContact)contacts.get(i)).getContractCust().getContract().getCfgContracts();
			String curCd="";
			for(CfgContract cfg:aSet){
				curCd+=curCd+cfg==null?"":cfg.getCurrencyCD();
				curCd=curCd+" ";
			}
			cell.setCellValue(curCd.trim());
		row=main.createRow(j);cell=row.createCell((short)cellIndex++);cell.setCellValue(((ContractCustContact)contacts.get(i)).getContractCust().getCustomer().getName());
	
		row = main.createRow(j);cell = row.createCell((short) cellIndex++);
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

		jobService.searchContract(search);
		results = search.getResults();
		search.setTotalResults(results);
		results = search.getTotalResults();	
		results = getPaginationResults(results,search,pageNumberStr);
		search.setResults(results);  	
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
	 String pageNumberStr = request.getParameter("pageNumber");
	
	  String inputFieldId = request.getParameter("inputFieldId");
	  String concode=request.getParameter("code");
	  String rowNum = request.getParameter("rowNum");
	String searchForm = request.getParameter("searchForm");
	String buName=request.getParameter("buName");
	
	  List results = new ArrayList();
	  List finalResults = new ArrayList();
	ContractSearch search =new ContractSearch();
	search.setAsOfDate(getAsOfDate(request));
	
	search.setSearchForm(searchForm);
	search.setInputFieldId(inputFieldId);
   StringSearchField stringSearchField=new StringSearchField();
	stringSearchField.setValue(concode);
	search.setContractCode(stringSearchField);
	search.setBuName(buName);
	ContractCustContact contractCustContact=new ContractCustContact();

	JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
        jobService.searchContract(search);
		results = search.getResults();
		search.setTotalResults(results);
       	results = search.getTotalResults();	
		results = getPaginationResults(results,search,pageNumberStr);
		search.setResults(results);
        request.setAttribute("command", search);
	return search;	
}
private List getPaginationResults(List results,ContractSearch search,String pageNumberStr){
		
		ContractCustContact contractCustContact=new ContractCustContact();
	  
	    List finalResults = new ArrayList();
	    
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);
		
		search.setPageNo(pageNumberStr);
		Pagination contractPagination = new Pagination(1, 20, pageNumber,10);
    	
    	search.setPagination(contractPagination);
    	if(contractPagination != null)
		{
		  if(results.size() > 0){
			  contractPagination.setTotalRecord(results.size());
		  }
		  contractPagination.calculatePages();
		}
		
    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
    	int sortedResultsSize = results.size();
    	
    	if(startRecord == 1 )
    	{
    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
    		{
		    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
		    	{
		    		contractCustContact = (ContractCustContact) results.get(i);
		    		finalResults.add(contractCustContact);
		    	}
    		}else
    		{
    			for(int i=0;i<sortedResultsSize;i++)
		    	{
    				contractCustContact = (ContractCustContact) results.get(i);
		    		finalResults.add(contractCustContact);
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
	    			contractCustContact = (ContractCustContact) results.get(i);
		    		finalResults.add(contractCustContact);
		    	}
    		}else
    		{
    			start=(startRecord -1)*Constants.RECORDS_PAGE;
    			end=sortedResultsSize;
    			for(int i=start;i<end;i++)
		    	{
    				contractCustContact = (ContractCustContact) results.get(i);
		    		finalResults.add(contractCustContact);
		    	}
    		}
    		
    	}
    	return finalResults;
	}

	private Date getAsOfDate(HttpServletRequest request){
		Date asOfDate=null;
		String fromPage=request.getParameter("fromPage");
		int conrollerIndex=0;
		try{
			conrollerIndex=Integer.parseInt(fromPage);
		}
		catch(Exception e){
			conrollerIndex=-1;
		}
		
		String[] controllerClasses=new String[]{
			"com.intertek.web.controller.job.CreateJobOrderAnalyticalServiceFormController.FORM.command",
			"com.intertek.web.controller.job.CreateJobOrderInspFormController.FORM.command",
			"com.intertek.web.controller.job.CreateJobOrderMarineFormController.FORM.command",
			"com.intertek.web.controller.job.CreateJobOrderOutSourcingFormController.FORM.command",

			"com.intertek.web.controller.job.EditJobOrderAnalyticalServiceFormController.FORM.command",
			"com.intertek.web.controller.job.EditJobOrderInspFormController.FORM.command",
			"com.intertek.web.controller.job.EditJobOrderMarineFormController.FORM.command",
			"com.intertek.web.controller.job.EditJobOrderOutSourcingFormController.FORM.command",

			"com.intertek.web.controller.job.ViewJobOrderAnalyticalServiceFormController.FORM.command",
			"com.intertek.web.controller.job.ViewJobOrderInspFormController.FORM.command",
			"com.intertek.web.controller.job.ViewJobOrderMarineFormController.FORM.command",
			"com.intertek.web.controller.job.ViewJobOrderOutSourcingFormController.FORM.command"
		};
		
		String controllerClass=null;
		if(conrollerIndex>=0 && conrollerIndex<controllerClasses.length){
			controllerClass=controllerClasses[conrollerIndex];
		}
		

		if(controllerClass!=null){
			HttpSession session=request.getSession();
			AddJobOrder addJobOrder=(AddJobOrder)session.getAttribute(controllerClass);
			
			if(addJobOrder!=null){
				JobOrder jobOrder = addJobOrder.getJobOrder();
				if(jobOrder!=null){
					if(jobOrder.getJobFinishDate()!=null ){
						asOfDate=jobOrder.getJobFinishDate();
					}
					else if(jobOrder.getJobFinishDate()==null && jobOrder.getEtaDate()!=null ){
						asOfDate=jobOrder.getEtaDate();
					}
					else if(jobOrder.getJobFinishDate()==null && jobOrder.getEtaDate()==null ){
						asOfDate=new Date();
					}
				}
			}
		}
		return asOfDate;
	}
}
