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

import com.intertek.domain.AddTaxLabels;

import com.intertek.domain.Search;
import com.intertek.entity.Country;

import com.intertek.entity.State;

import com.intertek.entity.TaxLabel;
import com.intertek.entity.TaxLabelId;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CountryService;

import com.intertek.service.TaxService;
import com.intertek.util.Constants;

import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class CreateTaxLabelsFormController extends BaseSimpleFormController
{
  public CreateTaxLabelsFormController() {
    super();
	setSessionForm(true);	 
    setCommandClass(AddTaxLabels.class);
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
	  AddTaxLabels  addTaxLables = (AddTaxLabels)command;
    TaxLabel[] taxLabels = addTaxLables.getTaxLabels();
    TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
	
	CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

	String txcel=request.getParameter("txcel"); 
	
	

      List deletedList=addTaxLables.getDeletedList();
	 
	   String addOrDeleteTaxLabel = request.getParameter("addOrDeleteTaxLabel");
	   String indexs = request.getParameter("taxLabelIndex");
	 	 if((addOrDeleteTaxLabel != null) && ("add".equals(addOrDeleteTaxLabel) || "delete".equals(addOrDeleteTaxLabel)))
       {
			if("add".equals(addOrDeleteTaxLabel))
			{
				addTaxLables.setTaxLabels(addTaxLabel(addTaxLables.getTaxLabels(),null));
			}
			else
			{				
				deletedList.add(taxLabels[addTaxLables.getTaxLabelIndex()]);
				addTaxLables.setTaxLabels(deleteTaxLabel(addTaxLables.getTaxLabels(), addTaxLables.getTaxLabelIndex()));
			}
			addTaxLables.setTaxLabelCount(addTaxLables.getTaxLabels().length);

			addTaxLables.setAddOrDeleteTaxLabel("none");
		return showForm(request, response, errors);
		} //if

	 	 
	 	 
if((txcel!=null) && "true".equals(txcel))
	{  
	int pageNumber=addTaxLables.getPagination().getCurrentPageNum();
    List  tas=new ArrayList();
    addTaxLables.setPagination(null);
                try 
					{ 
					 tas=taxService.getAllTaxLabels(addTaxLables);
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
  if(tas!=null && tas.size() > 0){
  Pagination pagination = new Pagination(1, 20, pageNumber,10);	
  pagination.setTotalRecord(tas.size());
  pagination.calculatePages();
  addTaxLables.setPagination(pagination);
 }
	if(tas!=null&&tas.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"taxlabels.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		main=workBook.createSheet("Taxlabels");		

		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
             	int  l=0;
				for(int k=0;k<=Constants.TLColumnSize;k++)
				{
				path=pRB.getString("TLHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}
        int j=1;
		for(int i=0;i<tas.size();i++)
		{ 
		
			
			row = main.createRow(j);cell = row.createCell((short) 0);
	        if(((TaxLabel)tas.get(i)).getTaxLabelId().getCountryCode()!=null && !((TaxLabel)tas.get(i)).getTaxLabelId().getCountryCode().trim().equals(""))
			{
				Country country=null;
				country=countryService.getCountryByCode(((TaxLabel)tas.get(i)).getTaxLabelId().getCountryCode());
				if(country!=null)
				{cell.setCellValue(country.getName());}}
			    else{cell.setCellValue("");}	
			
		
		
	        row = main.createRow(j);cell = row.createCell((short) 1);
	        if(((TaxLabel)tas.get(i)).getTaxLabelId().getState()!=null && !((TaxLabel)tas.get(i)).getTaxLabelId().getState().trim().equals(""))
			{
				State state=null;
				state=countryService.getStateByCodeAndCountryCode(((TaxLabel)tas.get(i)).getTaxLabelId().getState(),((TaxLabel)tas.get(i)).getTaxLabelId().getCountryCode());
				if(state!=null)
				{cell.setCellValue(state.getName());}}
			    else{cell.setCellValue("");} 
	        

		row = main.createRow(j);cell = row.createCell((short) 2);		
		if(((TaxLabel)tas.get(i)).getVatLabel()!=null && !((TaxLabel)tas.get(i)).getVatLabel().trim().equals(""))
		{cell.setCellValue(((TaxLabel)tas.get(i)).getVatLabel());}
		else{cell.setCellValue("");}
		
		

		row = main.createRow(j);cell = row.createCell((short) 3);
		if(((TaxLabel)tas.get(i)).getSalesTaxLabel()!=null && !((TaxLabel)tas.get(i)).getSalesTaxLabel().trim().equals(""))
		{cell.setCellValue(((TaxLabel)tas.get(i)).getSalesTaxLabel());}
		else{cell.setCellValue("");}
		
		row = main.createRow(j);cell = row.createCell((short) 4);
		if(((TaxLabel)tas.get(i)).getVatRegLabel()!=null && !((TaxLabel)tas.get(i)).getVatRegLabel().trim().equals(""))
		{cell.setCellValue(((TaxLabel)tas.get(i)).getVatRegLabel());}
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
	
	addTaxLables.setTxcel("false");
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
		
		addTaxLables.setPagination(pagination);
	
		List taxlables1=new ArrayList();
		try
			
			{			
				taxlables1=taxService.getAllTaxLabels(addTaxLables);
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
			TaxLabel[] taxLabel = new TaxLabel[taxlables1.size()];
			if(taxlables1 != null)
			{
			int i=0;
			for(Iterator itr=taxlables1.iterator();itr.hasNext();)
				{
				taxLabel[i] =(TaxLabel)itr.next();
				i++;
				}//for(i)

			addTaxLables.setTaxLabels(taxLabel);
			}//if

			addTaxLables.setPageFlag("none");
			request.setAttribute("command", addTaxLables);
			return showForm(request, response, errors);
		}//page flag
				
		TaxLabel[] taxLabelItems = addTaxLables.getTaxLabels();
	    try
	    {
			
	    if(deletedList.size()>0)
		  {
				int i=0;
		     	for(Iterator itr=(deletedList.iterator());itr.hasNext();)
			    {
		     		TaxLabel taxLabel =(TaxLabel)itr.next();
				if(taxLabel.getTaxLabelId().getCountryCode()!=null && !taxLabel.getTaxLabelId().getCountryCode().trim().equals(""))
				{
			    //i++;
				try
				{
				boolean deletedFlag=taxService.deleteTaxLabel(taxLabel);
				i++;
				}//try
			        catch(ServiceException e)
							{
			        	     addTaxLables.setTaxLabels(addTaxLabel(taxLabelItems,taxLabel));
			                 deletedList.remove(taxLabel);
							  e.printStackTrace();
							  errors.reject(e.getMessage(), e.getParams(), null);
							  return showForm(request, response, errors);
							  }
								catch(Throwable t)
								{
								  addTaxLables.setTaxLabels(addTaxLabel(taxLabelItems,taxLabel));
			                      deletedList.remove(taxLabel);
								  t.printStackTrace();
								  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
								  return showForm(request, response, errors);
								}
				}
			  }//for
			}//if
	            	
	    
	        TaxLabel[] taxArticlesItems = addTaxLables.getTaxLabels();
			if (taxLabelItems != null )
			{
		    	String empty="";
				for(int i=0; i< taxArticlesItems.length; i++)
				{
					TaxLabel taxLabel = taxLabelItems[i];
				 if(taxLabel.getTaxLabelId().getCountryCode()!= null && (!taxLabel.getTaxLabelId().getCountryCode().trim().equals("")))
					{
					 if(taxLabel.getTaxLabelId().getState()==null || taxLabel.getTaxLabelId().getState().trim().equals(""))
					 {taxLabel.getTaxLabelId().setState(" ");}
					 if(taxLabel.getSalesTaxLabel()==null ||taxLabel.getSalesTaxLabel().trim().equals("")){
						taxLabel.setSalesTaxLabel(" ");}
					 if(taxLabel.getVatLabel()==null ||taxLabel.getVatLabel().trim().equals(""))
					 {taxLabel.setVatLabel(" ");}
					 if(taxLabel.getVatRegLabel()==null || taxLabel.getVatRegLabel().trim().equals(""))
					 {taxLabel.setVatRegLabel(" ");}
					 
					 taxService.addTaxLabel(taxLabel);
					}
					else
						{
						errors.reject("invalid.taxlabel.error", new Object[] {taxLabel.getTaxLabelId().getCountryCode()}, null);
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

		
	// START : #119240 : 01/07/09
    /*ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "create_tax_label.htm");
    modelAndView.addObject("description", "The TaxLabel  has been successfully saved.");
    modelAndView.addObject("backUrlDescription", "You can continue to edit this taxlabel.");*/

	ModelAndView modelAndView = new ModelAndView("create-tax-labels-r");
	modelAndView.addObject("saved_message", "Saved successfully");
	// END : #119240 : 01/07/09
		 
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
	String pageNumberStr = request.getParameter("pageNumber");
	int pageNumber = 1;		
	try {
		pageNumber = new Integer(pageNumberStr).intValue();
	    } catch (Exception e) {}
		if (pageNumber <= 0)
			pageNumber = 1;
		Pagination pagination = new Pagination(1, 20, pageNumber,10);
		AddTaxLabels addTaxLabels = new AddTaxLabels();
		addTaxLabels.setPagination(pagination);
		
   List taxlables1 = new ArrayList();
   TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
    try
    {
    	taxlables1=taxService.getAllTaxLabels(addTaxLabels);
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

   if(taxlables1 != null)
    {
		int i=0;
		TaxLabel[] taxLables = new TaxLabel[taxlables1.size()];
		for(Iterator itr=taxlables1.iterator();itr.hasNext();)
		{
		  taxLables[i] =(TaxLabel)itr.next();
    	  i++;
	   }
		addTaxLabels.setTaxLabels(taxLables);
	}
	 request.setAttribute("command", addTaxLabels);
   return addTaxLabels;
  }



 private TaxLabel[] addTaxLabel(TaxLabel[] taxLabels,TaxLabel existingTaxLabel)
 {
	 TaxLabel taxLabel = new TaxLabel();
	 TaxLabelId taxLabelId=new TaxLabelId();
	 taxLabelId.setCountryCode(SecurityUtil.getUser().getCountryCode());
	 taxLabel.setTaxLabelId(taxLabelId);
	 
    if(existingTaxLabel != null)
    	taxLabel = existingTaxLabel;
	
        TaxLabel[] results = null;
		if(taxLabels == null) results = new TaxLabel[1];
		else results = new TaxLabel[taxLabels.length + 1];
		if(taxLabels != null)
		{
			for(int i=0; i<taxLabels.length; i++)
			{
				results[i] = taxLabels[i];
			}
		}
		results[results.length - 1] = taxLabel;
		return results;
	}

  private TaxLabel[] deleteTaxLabel(TaxLabel[] taxLabels, int indexs)
  {
		if(taxLabels == null) return null;
		if(taxLabels.length <= 0) return taxLabels;
		TaxLabel[] results = new TaxLabel[taxLabels.length - 1];
		int count = 0;
		for(int i=0; i<taxLabels.length; i++)
		{
			if(i == indexs) continue;
			results[count] = taxLabels[i];
			count ++;
		}
		return results;
	}
}
