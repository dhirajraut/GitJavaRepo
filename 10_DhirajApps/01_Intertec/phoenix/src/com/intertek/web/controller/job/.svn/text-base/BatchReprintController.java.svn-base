package com.intertek.web.controller.job;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow ;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.entity.*;
import com.intertek.exception.ServiceException;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.locator.*;
import com.intertek.domain.*;
import com.intertek.pagination.*;
import com.intertek.web.controller.*;

public class BatchReprintController extends BaseSimpleFormController
{
  public BatchReprintController() {
    super();
    setCommandClass(BatchReprintHelper.class);
    setSessionForm(false);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws Exception
  {
	  
	  BatchReprintHelper batchReprintHelper = (BatchReprintHelper)command;
	  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	  String dirName = InvoiceUtil.getInvoiceDir();
	  String jasperDirName = InvoiceUtil.getJasperDir();
      String sortFlag = request.getParameter("sortFlag");
	  String batchReprintFlag = request.getParameter("batchReprintFlag");
	  List  invoices = new ArrayList();
	  
	  if(batchReprintFlag != null && batchReprintFlag.trim().length()>0)
	  {
		  boolean nullMV=false;  
		  String batchReprintBranch = batchReprintHelper.getBatchReprintBranch().getValue();
		  if(!batchReprintFlag.equals("loadLastFive")){
			  Date timeToShow = null;
			  boolean needSave=true;
			  List<JobContractInvoice> jciList=null;
			  try{
				    //byte[] pdfData = null;
				    boolean doPrint=false;
				    if(batchReprintFlag.equals("invoiceReprint")){
				    	needSave=false;
						String invoiceIdArray[] = request.getParameterValues("invoiceReprint[]");
						
						
						
//START 116077				
						//retrive selected invoices of the previous pages from the session and add that to a set object
						HttpSession session = request.getSession();
						Map map = (Map)session.getAttribute("map");
						Set<String> invoiceSet = new TreeSet<String>();
						if(null != map && map.size() > 0){
							Iterator iterator = map.entrySet().iterator();
							while(iterator.hasNext()){
								Map.Entry entry = (Map.Entry)iterator.next();
								String key = (String)entry.getKey();
								invoiceSet.add(key);
							}
						}
						//retrive the selected invoices from the current page which was not there in the session
						if(invoiceIdArray!=null && invoiceIdArray.length>0){
							//to add all the entries in the current page to a common set
							for(String l:invoiceIdArray){
								invoiceSet.add(l);	
							}
						}
						
						Iterator itr = invoiceSet.iterator();
						List<String> lst = new ArrayList<String>();
						while(itr.hasNext()){
							lst.add(String.valueOf(itr.next()));
						}
						if(lst!=null && lst.size()>0){
							Long[] ids=new Long[lst.size()];
							for(int i=0; i<ids.length; i++){
								ids[i]=Long.parseLong(lst.get(i));
							}
							jciList=jobService.getJobContractInvoices(ids);
							doPrint=true;
						}
//END 116077						
						/*if(invoiceIdArray!=null && invoiceIdArray.length>0){
							System.out.println("invoiceIdArray.length......"+invoiceIdArray.length);
							Long[] ids=new Long[invoiceIdArray.length];
							for(int i=0; i<ids.length; i++){
								ids[i]=Long.parseLong(invoiceIdArray[i]);
							}
							jciList=jobService.getJobContractInvoices(ids);
							doPrint=true;
							//pdfData = jobService.generateBatchReprintPDF(dirName, jasperDirName, invoiceIdArray);
						}*/
					}
				    else{
					    if(batchReprintFlag.equals("true")){
					    	timeToShow = new Date();
					    	needSave=true;
					    	doPrint=true;
					    	//pdfData = jobService.getBatchReprint(timeToShow, dirName, jasperDirName, batchReprintHelper, newFlag, SecurityUtil.getUser().getLoginName());
						    //if(pdfData!=null && pdfData.length>0 && batchReprintBranch!=null && batchReprintBranch.trim().length()>0){
							//	batchReprintHelper.getBatchReprintBranch().setValue(batchReprintBranch);
						    //}
					    }else{
					    	needSave=false;
				    		if(!batchReprintFlag.equals("loadLastFive")){
						    	String batachReprintIdStr = batchReprintFlag;
						    	long id = 0;
						    	try{
						    		id = Long.parseLong(batachReprintIdStr);
						    	}catch(Exception e){}
						    	if(id>0){
						    		BatchReprint selectedBatchReprint = jobService.getBatchReprintById(id);
						    		if(selectedBatchReprint != null){
						    			timeToShow = selectedBatchReprint.getRunDate();
						    			doPrint=true;
						    		    //pdfData = jobService.getBatchReprint(timeToShow, dirName, jasperDirName, batchReprintHelper, false, SecurityUtil.getUser().getLoginName());
						    		}
						    	}
				    		}
					    }
					}
				    
				    if(doPrint){
				    	String fileName = "InvoiceReprint.pdf";
				    	if(timeToShow!=null){
						    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
						    String formattedTime = sdf.format(timeToShow);
						    fileName = "BatchReprint_"+ batchReprintBranch + "_" + formattedTime + ".pdf";
				    	}

				    	String[] fileNames=null;
				    	if(jciList==null){
				    		jciList=jobService.getBatchReprint(timeToShow, batchReprintHelper);
				    	}
				    	
			    		if(jciList!=null && !jciList.isEmpty()){
			        		fileNames=new String[jciList.size()];
			        		int i=0;
			        		for(JobContractInvoice jci : jciList){
			        			//System.out.println(jci.getJobContract().getJobNumber()+" "+jci.getJobContract().getInvoice());
			        			fileNames[i++]=jci.getInvoiceFileName();
			        		}
			    		}
		        		
		        		if(fileNames!=null && fileNames.length>0){
			        		List<String> missing=PDFUtil.getMissingFiles(dirName, fileNames);
			        		if(missing==null || missing.isEmpty()){
				        	    response.setHeader("Content-Disposition","attachment; filename=\"" + fileName+"\"");
				        	    response.setContentType("application/x-download");
				        	    response.setHeader("Pragma", "public");
				        		response.setHeader("Cache-Control", "max-age=0");
	
			        			OutputStream out=response.getOutputStream();
			        			PDFUtil.concatPDFs(dirName, fileNames, out);

						    	if(needSave){
								    if(batchReprintBranch!=null && batchReprintBranch.trim().length()>0){
										batchReprintHelper.getBatchReprintBranch().setValue(batchReprintBranch);
								    }
								    
									BatchReprint batchReprint = new BatchReprint();
									batchReprint.setBranchName(batchReprintBranch);
									batchReprint.setRunDate(timeToShow);
									batchReprint.setRunnedByUserName(SecurityUtil.getUser().getLoginName());
									jobService.saveBatchReprint(batchReprint);
						    	}
						    	nullMV=true;
			        		}
			        		else{
			        			request.setAttribute("batchReprintResult", "One or more invoice PDF selected is missing!");
			        		}
			        		batchReprintHelper.setBatchReprintFlag(null);
		        		}
		        		else{
		        			request.setAttribute("batchReprintResult", "No Invoice Found For That Criteria!");
		        		}
				    }else{
			    		request.setAttribute("batchReprintResult", "No Invoice Found For That Criteria!");
				    }
			  	}
		        catch(Exception e)
		        {
		          e.printStackTrace();
		          errors.reject("batch.reprint.error", new Object[] {e.getMessage()}, null);
		        }
		  	}
	      batchReprintHelper.setBatchReprintFlag(null);
	      if(batchReprintHelper.getBatchReprintBranch()!=null && batchReprintHelper.getBatchReprintBranch().getValue()!=null && batchReprintHelper.getBatchReprintBranch().getValue().trim().length()>0){
	    	    batchReprintHelper.getLastFiveBatchReprintList().clear();
	    		batchReprintHelper.getLastFiveBatchReprintList().addAll(jobService.getLastFiveBatchReprint(batchReprintHelper.getBatchReprintBranch().getValue()));
	      }
	      if(nullMV){
	    	  return null;
	      }
		  return showForm(request, response, errors); 
	  }
	  
/* excel download code starts here*/
   String jxcel=request.getParameter("jxcel");
   if((jxcel!=null) && "true".equals(jxcel))
   {
		int pageNumber=-1;
		batchReprintHelper.setPagination(null);
		invoices = batchReprintHelper.getResults();
		if(invoices==null || invoices.isEmpty())
			invoices =  invoices = jobService.searchInvoice(batchReprintHelper, pageNumber, sortFlag);
		if(invoices!=null && !invoices.isEmpty()){
			try
				{
				 response.setContentType("application/download");
			 	response.setHeader("Content-Disposition", "attachment; filename=\"InvoiceSearch.xls\"");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
		
				
				HSSFWorkbook workBook = new HSSFWorkbook();
				HSSFSheet main=null;
				
				main=workBook.createSheet("Invoices");
				HSSFRow   row ;
				HSSFCell  cell;
				HSSFRow headerRow;
				HSSFCell headerCell ;
				
				PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
				String path="";
				if(pRB!= null)
				{
					int  l=0;
					for(int k=1;k<=3;k++)
					{
					path=pRB.getString("IHeading"+k);
					headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
					l++;
					}
					
				}
				
				 int j=1;
				for(int i=0;i<invoices.size();i++){ 
					
					row = main.createRow(j);cell = row.createCell((short) 0);
					cell.setCellValue(((JobContractInvoice)invoices.get(i)).getInvoice());
				
				row = main.createRow(j);cell = row.createCell((short) 1);
				if(((JobContractInvoice)invoices.get(i)).getGeneratedDateTime()!=null)
				{
					Date date=(((JobContractInvoice)invoices.get(i)).getGeneratedDateTime());
					String s=DateUtil.formatDate(date,"MM/dd/yyyy H:mm:ss"); cell.setCellValue(s);
				}
				else
				{
					cell.setCellValue("");
				}
				
				row = main.createRow(j);cell = row.createCell((short) 2);
		       if(((JobContractInvoice)invoices.get(i)).getCreationUserName()!= null)
			   {
		    	   cell.setCellValue(((JobContractInvoice)invoices.get(i)).getCreationUserName());
			   }
			   else
				{cell.setCellValue("");}
				j++;
				}
				OutputStream out = response.getOutputStream(); 
				workBook.write(out);
				out.flush();
				out.close();
				batchReprintHelper.setJxcel("false");
			   } catch (FileNotFoundException fne) {
				  fne.printStackTrace();
			   } catch (IOException ioe) {
			        System.out.println("IOException..." +  ioe.toString());
			   }
		   }
		   batchReprintHelper.setJxcel("false");
		    return showForm(request, response, errors);
		 }
   /*excel download ends here*/
	  
   
    String pageNumberStr = request.getParameter("pageNumber");
    int pageNumber = 1;
    if(pageNumberStr!=null && pageNumberStr.trim().length()>0){
	   try
	    {
	      pageNumber = new Integer(pageNumberStr).intValue();
	    }
	    catch(Exception e)
	    {
	    }
    }
    if(pageNumber <= 0) pageNumber = 1;
    int resultCount = 20;
    if(batchReprintHelper.getSearchResults().getValue() != null && (!batchReprintHelper.getSearchResults().getValue().trim().equals("")))
    {
    	resultCount = Integer.parseInt(batchReprintHelper.getSearchResults().getValue());
    }
	Pagination pagination = new Pagination(1,resultCount, pageNumber, 10);
	batchReprintHelper.setPagination(pagination);
    
	String fromJobid = batchReprintHelper.getFromJobId().getValue();
    String toJobid = batchReprintHelper.getToJobId().getValue();
    String branch = batchReprintHelper.getBranch().getValue();
 
    if((fromJobid != null) && !"".equals(fromJobid.trim())&& ((toJobid != null) && !"".equals(toJobid.trim()))&& (branch != null) && !"".equals(branch.trim()))
    {
    	 String[] fromJob=fromJobid.trim().split("\\-");
         String fromStringPart= fromJob[0];
         String[] toJob = toJobid.trim().split("\\-");
         String toStringPart= toJob[0];
         if(!fromStringPart.equalsIgnoreCase(branch))
         {
        	 errors.reject("invalid.fromJob.id.select.branch.associated.fromJobIderror", new Object[] { fromJobid }, null);
			return showForm(request, response, errors);
         }
       JobOrder jobOrder = jobService.getJobOrderByJobNumber(fromJobid.trim().toUpperCase());
         if (jobOrder == null)
         {
        	 errors.reject("invalid.fromJob.id.select.valid.branch.associated.fromJobIderror",new Object[] { fromJobid }, null);
 			return showForm(request, response, errors);
         }
         if(!toStringPart.equalsIgnoreCase(branch))
         {
        	 errors.reject("invalid.tojob.id.please.select.branch.associated.tojobid",new Object[] {}, null);
			return showForm(request, response, errors);
         }
         jobOrder = jobService.getJobOrderByJobNumber(toJobid.trim().toUpperCase());
         if (jobOrder == null)
         {
        	 errors.reject("invalid.tojob.id.please.select.valid.branch.associated.tojobid",new Object[] { toJobid}, null);
 			return showForm(request, response, errors);
         }
    }
   
    try
    {
	     invoices = jobService.searchInvoice(batchReprintHelper, pageNumber, sortFlag);
	     batchReprintHelper.setResults(invoices);
	     request.setAttribute("command", batchReprintHelper);
	     
	     if(batchReprintHelper.getBatchReprintBranch().getValue()!=null && batchReprintHelper.getBatchReprintBranch().getValue().trim().length()>0)
				batchReprintHelper.getLastFiveBatchReprintList().addAll(jobService.getLastFiveBatchReprint(batchReprintHelper.getBatchReprintBranch().getValue()));
    }
    catch(Exception e)
    {
      e.printStackTrace();
      errors.reject("batch.reprint.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }
    
    request.setAttribute("searchResult", "main");
    request.setAttribute("command", batchReprintHelper);
    return showForm(request, response, errors);  
    }

  protected boolean suppressValidation(HttpServletRequest request)
  {
	String jxcel=request.getParameter("jxcel");
   if((jxcel!=null) && "true".equals(jxcel))
	  {
	 return true;
	  }
   
    return super.suppressValidation(request);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception
  {
	  UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
	  User loginUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	  String dateFormate = loginUser.getDateFormat();
	  if(dateFormate != null && !dateFormate.trim().equals("")){
		String[] pattern = dateFormate.split("/");
	       String month = pattern[1];
	       if(pattern[0].trim().equalsIgnoreCase("mm"))
	    	   dateFormate = "MM/dd/yyyy";  
	       if(month.trim().equalsIgnoreCase("mmm"))
	    	   dateFormate = "dd/MMM/yyyy"; 
	       if(month.trim().equalsIgnoreCase("mm"))
	    	   dateFormate = "dd/MM/yyyy"; 
		}else{
			dateFormate =  Constants.DD_MM_YYYY_DATE_FORMAT; 
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormate);
		
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
  }

 
  protected Object formBackingObject(HttpServletRequest request) throws Exception
  {
	  
	  BatchReprintHelper batchReprintHelper = new BatchReprintHelper();
	  UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
	  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    
	  User loginUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	  String dateFormat = loginUser.getDateFormat();
	  if(dateFormat != null && !dateFormat.trim().equals(""))
	  {
			String[] pattern = dateFormat.split("/");
		    String month = pattern[1];
		    if(pattern[0].trim().equalsIgnoreCase("mm"))
			    dateFormat = Constants.MM_DD_YYYY_DATE_FORMAT;  
		    if(month.trim().equalsIgnoreCase("mmm"))
		    	dateFormat = Constants.DD_MMM_YYYY_DATE_FORMAT; 
		    if(month.trim().equalsIgnoreCase("mm"))
			    dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT; 
	  }else
		    dateFormat = dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT; 
	  batchReprintHelper.setDateFormat(dateFormat);
      batchReprintHelper.getStatus().setValue("4");
	  batchReprintHelper.getInvoiceStatus().setValue("0");
      //jobSearch.getPort().setOperator(2);
	  batchReprintHelper.getContractId().setOperator(2);
  	  //jobSearch.getSvcLocation().setOperator(2);
  	  batchReprintHelper.getCompanyId().setOperator(2);
	  batchReprintHelper.getCompany().setOperator(2);
	  //batchReprintHelper.getSchedulerId().setOperator(2);
	  batchReprintHelper.getScheduler().setOperator(2);
	  //batchReprintHelper.getBillingContactId().setOperator(2);
	  batchReprintHelper.getBillingContact().setOperator(2);
	
      if(!"POST".equals(request.getMethod()))
	    {
    	  
	      if(batchReprintHelper.getBusinessUnit().getValue() == null)
	      {
	        String buName=SecurityUtil.getUser().getBusinessUnit().getName();
	        batchReprintHelper.getBusinessUnit().setValue(buName);
			
			String branchName=SecurityUtil.getUser().getBranch().getName();
			batchReprintHelper.getBranch().setValue(branchName);	
	      }
	      
	      if(batchReprintHelper.getBatchReprintBusinessUnit().getValue() == null){
	    	  String buName=SecurityUtil.getUser().getBusinessUnit().getName();
	          batchReprintHelper.getBatchReprintBusinessUnit().setValue(buName);
	          String branchName=SecurityUtil.getUser().getBranch().getName();
	    	  batchReprintHelper.getBatchReprintBranch().setValue(branchName);	
	      }
	      
		  if(batchReprintHelper.getBatchReprintBranch().getValue()!=null && batchReprintHelper.getBatchReprintBranch().getValue().trim().length()>0)
			  batchReprintHelper.getLastFiveBatchReprintList().addAll(jobService.getLastFiveBatchReprint(batchReprintHelper.getBatchReprintBranch().getValue()));
      }

	 return batchReprintHelper;
  }
}
