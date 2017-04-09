package com.intertek.web.controller.conslinv;

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

import com.intertek.domain.ConsolidatedInvoiceSearch;
import com.intertek.entity.ConsolidatedInvoice;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ConsolidatedInvoiceService;
import com.intertek.util.Constants;

public class SearchConsolidatedInvoiceFormController extends SimpleFormController {
	  public SearchConsolidatedInvoiceFormController() {
		    super();
		    setCommandClass(ConsolidatedInvoiceSearch.class);
		   // setSessionForm(true);
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
		    String sortFlag = request.getParameter("sortFlag");
			String cxcel=request.getParameter("cxcel");
		    int pageNumber = 1;
		    try
		    {
		      pageNumber = new Integer(pageNumberStr).intValue();
		    }
		    catch(Exception e)
		    {
		    }
		    if(pageNumber <= 0) pageNumber = 1;

		    ConsolidatedInvoiceSearch search = (ConsolidatedInvoiceSearch)command;

		    Pagination pagination = new Pagination(1,20, pageNumber, 10);
		    search.setPagination(pagination);

		    ConsolidatedInvoiceService consolidatedInvoiceService = (ConsolidatedInvoiceService)ServiceLocator.getInstance().getBean("consolidatedInvoiceService");
		    
		    if(sortFlag != null && !sortFlag.trim().equals(""))
			{
				try {
				consolidatedInvoiceService.searchConsolidatedInvoice(search,sortFlag);
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
					/*catch(Exception e)
					{
					e.printStackTrace();
					errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
					return showForm(request, response, errors);
					}*/
		        return showForm(request, response, errors);
		   } 
 if((cxcel!=null) && "true".equals(cxcel))
	{ 
    List  coninvs=new ArrayList();
	//int pageNumber=-1;
	search.setPagination(null);
	try {
			
		consolidatedInvoiceService.searchConsolidatedInvoice(search,sortFlag);
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
	    }/*catch (Exception e) {
			e.printStackTrace();
			errors.reject("search.contact.error", new Object[] { e.getMessage() }, null);
			return showForm(request, response, errors);
		}*/
	coninvs=search.getResults();
	if(coninvs!=null&&coninvs.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"ConsolidatedInvoices.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("coninvs");
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
		for(int i=0;i<coninvs.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((ConsolidatedInvoice)coninvs.get(i)).getConsolidatedInvoiceId().getConsolInvoiceNo());
		
		
		
		row = main.createRow(j);cell = row.createCell((short) 1);cell.setCellValue(((ConsolidatedInvoice)coninvs.get(i)).getConsolidatedInvoiceId().getBuName());
		
		row = main.createRow(j);cell = row.createCell((short) 2);cell.setCellValue(((ConsolidatedInvoice)coninvs.get(i)).getCustCode());

		row = main.createRow(j);cell = row.createCell((short) 3);		
		if(((ConsolidatedInvoice)coninvs.get(i)).getCustomer().getName()!=null&& ((ConsolidatedInvoice)coninvs.get(i)).getCustomer().getName()!= null){
		cell.setCellValue((((ConsolidatedInvoice)coninvs.get(i)).getCustomer().getName())+" "+(((ConsolidatedInvoice)coninvs.get(i)).getCustomer().getName()));
		}		
		else
		{cell.setCellValue("");}	
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
     search.setCxcel("false");
	return showForm(request, response, errors);
	}

   try {
	   consolidatedInvoiceService.searchConsolidatedInvoice(search,sortFlag);
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
		     
		    List results = search.getTotalResults();


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
		    return new ConsolidatedInvoiceSearch();
		  }
}
