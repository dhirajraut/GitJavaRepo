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
import com.intertek.domain.AddTaxRates;
import com.intertek.domain.Search;
import com.intertek.entity.TaxRate;
import com.intertek.entity.TaxRateId;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.TaxService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class CreateVatRateFormController extends BaseSimpleFormController
//public class CreateVatRateFormController extends SimpleFormController
{
  public CreateVatRateFormController() {
    super();
	setSessionForm(true);	 
    setCommandClass(AddTaxRates.class);
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
  AddTaxRates  addTaxRates = (AddTaxRates)command;
  TaxRate[] taxRates = addTaxRates.getTaxRates();
    TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
	
	
	String vxcel=request.getParameter("vxcel"); 
      List deletedList=addTaxRates.getDeletedList();
	 
	   String addOrDeleteVatCode = request.getParameter("addOrDeleteVatCode");
	   String indexs = request.getParameter("vatCodeIndex");
	 	 if((addOrDeleteVatCode != null) && ("add".equals(addOrDeleteVatCode) || "delete".equals(addOrDeleteVatCode)))
       {
			if("add".equals(addOrDeleteVatCode))
			{
				addTaxRates.setTaxRates(addTaxRate(addTaxRates.getTaxRates(),null,addTaxRates.getTaxType()));
			}
			else
			{
				
				deletedList.add( taxRates[addTaxRates.getVatCodeIndex()]);

				addTaxRates.setTaxRates(deleteTaxRate(addTaxRates.getTaxRates(), addTaxRates.getVatCodeIndex()));
			}
		  addTaxRates.setTaxRateCount(addTaxRates.getTaxRates().length);

		  addTaxRates.setAddOrDeleteVatCode("none");
		return showForm(request, response, errors);
		} //if
if((vxcel!=null) && "true".equals(vxcel))
	{ 
       
		
	int pageNumber=addTaxRates.getPagination().getCurrentPageNum();
		addTaxRates.setPagination(null);

    List  vats=new ArrayList();

	addTaxRates.setPagination(null);
   //vats=taxService.getAllTaxRates(addTaxRates);
                 try 
					{ 
					 vats=taxService.getAllTaxRates(addTaxRates);
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
  if(vats.size() > 0){
  Pagination pagination = new Pagination(1, 20, pageNumber,10);	
  pagination.setTotalRecord(vats.size());
  pagination.calculatePages();
  addTaxRates.setPagination(pagination);
 }
	if(vats!=null&&vats.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
		if(addTaxRates.getTaxType().equals("V")){
	 	response.setHeader("Content-Disposition", "attachment; filename=\"vatrates.xls\"");
		}else{response.setHeader("Content-Disposition", "attachment; filename=\"saletaxs.xls\"");}

		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		if(addTaxRates.getTaxType().equals("V")){
		main=workBook.createSheet("Vat");
		}else{main=workBook.createSheet("Sales");}

		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
             if(addTaxRates.getTaxType().equals("V")){
				int  l=0;
				for(int k=0;k<=Constants.VColumnSize;k++)
				{
				path=pRB.getString("VHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			 }else
				{
				 int  l=0;
				for(int k=0;k<=Constants.VColumnSize;k++)
				{
				path=pRB.getString("StHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			    }
			}


        int j=1;
		for(int i=0;i<vats.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((TaxRate)vats.get(i)).getTaxRateId().getTaxCode());
		
		row = main.createRow(j);cell = row.createCell((short) 1);
		if(((TaxRate)vats.get(i)).getDescription()!=null && !((TaxRate)vats.get(i)).getDescription().trim().equals(""))
		{cell.setCellValue(((TaxRate)vats.get(i)).getDescription());}
		else
			{cell.setCellValue("");}

		row = main.createRow(j);cell = row.createCell((short) 2);
		if(((TaxRate)vats.get(i)).getTaxRateId().getEffDate()!=null){Date date1=(((TaxRate)vats.get(i)).getTaxRateId().getEffDate());String k=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(k);}else{cell.setCellValue("");}
		
        row = main.createRow(j);cell = row.createCell((short) 3);cell.setCellValue(((TaxRate)vats.get(i)).getTaxPct());
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
	
	addTaxRates.setVxcel("false");
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
		
		addTaxRates.setPagination(pagination);
	
		List taxRates1=new ArrayList();
			try
			
			{
			
			taxRates1=taxService.getAllTaxRates(addTaxRates);
			
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
           TaxRate[] taxRate = new TaxRate[taxRates1.size()];
			if(taxRates1 != null)
			{
			int i=0;
			for(Iterator itr=taxRates1.iterator();itr.hasNext();)
				{
				taxRate[i] =(TaxRate)itr.next();
				i++;
				}//for(i)

			addTaxRates.setTaxRates(taxRate);
			}//if

			addTaxRates.setPageFlag("none");
			request.setAttribute("command", addTaxRates);
			return showForm(request, response, errors);
		}//page flag
		
		 String vatRateFlag=request.getParameter("vatRateFlag");

  		  if("newval".equals(vatRateFlag))
	  {
    		String rowNo=request.getParameter("rowNum");
			int num=Integer.parseInt(rowNo);
	  		TaxRate[] taxRateItems=addTaxRates.getTaxRates();
			TaxRate taxRate=new TaxRate();
			// taxRate=taxService.getTaxdateByCode( taxRateItems[num].getTaxCode());
			try
			{
			taxRate=taxService.getTaxdateByCode( taxRateItems[num].getTaxRateId().getTaxCode());		
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
			  taxRateItems[num] = taxRate;
	  		  addTaxRates.setTaxRates(taxRateItems);
			  addTaxRates.setVatRateFlag("none");
			  return showForm(request, response, errors);
		}							 

  /*     if(deletedList.size()>0)
	  {
		int i=0;
	     	for(Iterator itr=(deletedList.iterator());itr.hasNext();)
		    {
		    TaxRate taxRate =(TaxRate)itr.next();
			taxService.deleteTaxRate(taxRate);
		    i++;
	        }
	   }	
            	
		TaxRate[] taxRatesItems = addTaxRates.getTaxRates();
	    if (taxRatesItems != null )
		{
			for(int i=0; i< taxRatesItems.length; i++)
			{
			TaxRate taxRate = taxRatesItems[i];
		//	taxRate=taxService.checkTaxCode(taxRate);
		   	 taxService.addTaxRate(taxRate);
			}
		}*/

			TaxRate[] taxRatesItems = addTaxRates.getTaxRates();
    try
    {
    if(deletedList.size()>0)
	  {
		int i=0;
	     	for(Iterator itr=(deletedList.iterator());itr.hasNext();)
		    {
		    TaxRate taxRate =(TaxRate)itr.next();
			String taxType=addTaxRates.getTaxType();
			if(taxRate.getTaxRateId().getTaxCode()!=null && !taxRate.getTaxRateId().getTaxCode().trim().equals(""))
			{
             taxRate.setTaxType(addTaxRates.getTaxType());
			// boolean deletedFlag=taxService.deleteTaxRate(taxRate);
			 // i++;
			try
			{
			boolean deletedFlag=taxService.deleteTaxRate(taxRate);
			 i++;
			}//try
		        catch(ServiceException e)
						{
					     addTaxRates.setTaxRates(addTaxRate(taxRatesItems,taxRate,taxType));
		                  deletedList.remove(taxRate);
						  e.printStackTrace();
						  errors.reject(e.getMessage(), e.getParams(), null);
						  return showForm(request, response, errors);
						  }
							catch(Throwable t)
							{
							  addTaxRates.setTaxRates(addTaxRate(taxRatesItems,taxRate,taxType));
		                      deletedList.remove(taxRate);
							  t.printStackTrace();
							  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
							  return showForm(request, response, errors);
							}
          
			   }
			 }//for
		}//if
            	
	//	TaxRate[] taxRatesItems = addTaxRates.getTaxRates();
	    if (taxRatesItems != null )
		{
			for(int i=0; i< taxRatesItems.length; i++)
			{
			TaxRate taxRate = taxRatesItems[i];
			taxRate.setTaxType(addTaxRates.getTaxType());
			 if(taxRate.getTaxRateId().getTaxCode() != null && (!taxRate.getTaxRateId().getTaxCode().trim().equals("")))
				{
				 taxService.addTaxRate(taxRate);
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
    // START : #119240 : 06/07/09
	/*ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("description", "The TaxRate  has been successfully saved.");*/
	
	ModelAndView modelAndView = new ModelAndView("create-vat-rate-r");
	modelAndView.addObject("taxType", addTaxRates.getTaxType());
	modelAndView.addObject("saved_message", "Saved successfully");
    // END : #119240 : 06/07/09
    
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
String taxType=request.getParameter("taxType");
	String pageNumberStr = request.getParameter("pageNumber");
	int pageNumber = 1;		
	try {
		pageNumber = new Integer(pageNumberStr).intValue();
	    } catch (Exception e) {}
		if (pageNumber <= 0)
			pageNumber = 1;
		Pagination pagination = new Pagination(1, 20, pageNumber,10);
   AddTaxRates addTaxRates = new AddTaxRates();
   addTaxRates.setPagination(pagination);
   addTaxRates.setTaxType(taxType);
   List taxRates1=new ArrayList();
   TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
    try
    {
   	 taxRates1=taxService.getAllTaxRates(addTaxRates);
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

   if(taxRates1 != null)
    {
		int i=0;
   		TaxRate[] taxRates=new TaxRate[taxRates1.size()];
		for(Iterator itr=taxRates1.iterator();itr.hasNext();)
		{
		 taxRates[i] =(TaxRate)itr.next();
    	  i++;
	   }
		addTaxRates.setTaxRates(taxRates);
		}
	   request.setAttribute("command", addTaxRates);
   return addTaxRates;
  }



   private TaxRate[] addTaxRate(TaxRate[] taxRates,TaxRate existingTaxRate, String taxType)
 {
	   TaxRate taxRate = new TaxRate();
	   
    if(existingTaxRate != null)
       taxRate = existingTaxRate;
    else
    	taxRate.setTaxRateId(new TaxRateId());
	 

		TaxRate[] results = null;
		if(taxRates == null) results = new TaxRate[1];
		else results = new TaxRate[taxRates.length + 1];
		if(taxRates != null)
		{
		for(int i=0; i<taxRates.length; i++)
		{
			taxRates[i].setTaxType(taxType);
			results[i] = taxRates[i];
		}
		}
		results[results.length - 1] = taxRate;
		return results;
	}

  private TaxRate[] deleteTaxRate(TaxRate[] taxRates, int indexs)
  {
		if(taxRates == null) return null;
		if(taxRates.length <= 0) return taxRates;
		TaxRate[] results = new TaxRate[taxRates.length - 1];
		int count = 0;
		for(int i=0; i<taxRates.length; i++)
		{
			if(i == indexs) continue;
			results[count] = taxRates[i];
			count ++;
		}
		return results;
	}
}
