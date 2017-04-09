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
import com.intertek.domain.AddTaxArticles;
import com.intertek.domain.Search;
import com.intertek.entity.DropDowns;
import com.intertek.entity.TaxArticle;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.DropdownService;
import com.intertek.service.TaxService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class CreateTaxArticlesFormController extends BaseSimpleFormController

//public class CreateTaxArticlesFormController extends SimpleFormController
{
  public CreateTaxArticlesFormController() {
    super();
	setSessionForm(true);	 
    setCommandClass(AddTaxArticles.class);
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
  AddTaxArticles  addTaxArticles = (AddTaxArticles)command;
  TaxArticle[] taxArticles = addTaxArticles.getTaxArticles();
    TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
	DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

	String txcel=request.getParameter("txcel"); 
	
	

      List deletedList=addTaxArticles.getDeletedList();
	 
	   String addOrDeleteTaxArticle = request.getParameter("addOrDeleteTaxArticle");
	   String indexs = request.getParameter("taxArticleIndex");
	 	 if((addOrDeleteTaxArticle != null) && ("add".equals(addOrDeleteTaxArticle) || "delete".equals(addOrDeleteTaxArticle)))
       {
			if("add".equals(addOrDeleteTaxArticle))
			{
				addTaxArticles.setTaxArticles(addTaxArticle(addTaxArticles.getTaxArticles(),null));
			}
			else
			{
				
				deletedList.add( taxArticles[addTaxArticles.getTaxArticleIndex()]);

				addTaxArticles.setTaxArticles(deleteTaxArticle(addTaxArticles.getTaxArticles(), addTaxArticles.getTaxArticleIndex()));
			}
		  addTaxArticles.setTaxArticleCount(addTaxArticles.getTaxArticles().length);

		  addTaxArticles.setAddOrDeleteTaxArticle("none");
		return showForm(request, response, errors);
		} //if

if((txcel!=null) && "true".equals(txcel))
	{        
		
	int pageNumber=addTaxArticles.getPagination().getCurrentPageNum();
    List  tas=new ArrayList();
	addTaxArticles.setPagination(null);
                try 
					{ 
					 tas=taxService.getAllTaxArticles(addTaxArticles);
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
  addTaxArticles.setPagination(pagination);
 }
	if(tas!=null&&tas.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"taxarticles.xls\"");
		

		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		main=workBook.createSheet("Taxarticles");		

		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
             	int  l=0;
				for(int k=0;k<=Constants.TColumnSize;k++)
				{
				path=pRB.getString("THeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}


        int j=1;
		for(int i=0;i<tas.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((TaxArticle)tas.get(i)).getTaxArticleCode());

		row = main.createRow(j);cell = row.createCell((short) 1);
		
		if(((TaxArticle)tas.get(i)).getTaxArticleDescription()!=null && !((TaxArticle)tas.get(i)).getTaxArticleDescription().trim().equals(""))
		{cell.setCellValue(((TaxArticle)tas.get(i)).getTaxArticleDescription());}
		else{cell.setCellValue("");}
		
		

		row = main.createRow(j);cell = row.createCell((short) 2);
		if(((TaxArticle)tas.get(i)).getEffectiveDate()!=null){Date date1=(((TaxArticle)tas.get(i)).getEffectiveDate());String k=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(k);}else{cell.setCellValue("");}
		
        String dropdowntype="activeStatus";
		DropDowns dropDown=null;
		dropDown=dropdownService.getDropdownByDropdownCodeAndType(((TaxArticle)tas.get(i)).getStatus(),dropdowntype);
		if(dropDown!=null)
		{
        row = main.createRow(j);cell = row.createCell((short) 3);cell.setCellValue(dropDown.getFieldValue());
		}else{cell.setCellValue("");}		j++;
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
	
	addTaxArticles.setTxcel("false");
	return showForm(request, response, errors);
	}
		String pageFlag=request.getParameter("pageFlag");
		if("pageFlag".equals(pageFlag))
		{
			System.out.println("flag is "+pageFlag);
         String pageNumberStr = request.getParameter("pageNumber");
		int pageNumber=1;
		
		try { pageNumber = new Integer(pageNumberStr).intValue();}
		catch (Exception e){	}
		
		if (pageNumber <= 0)	pageNumber = 1;
		
		
		Pagination pagination = new Pagination(1,20, pageNumber,10);		
		
		addTaxArticles.setPagination(pagination);
	
		List taxarticles1=new ArrayList();
			try
			
			{			
			taxarticles1=taxService.getAllTaxArticles(addTaxArticles);
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
           TaxArticle[] taxArticle = new TaxArticle[taxarticles1.size()];
			if(taxarticles1 != null)
			{
			int i=0;
			for(Iterator itr=taxarticles1.iterator();itr.hasNext();)
				{
				taxArticle[i] =(TaxArticle)itr.next();
				i++;
				}//for(i)

			addTaxArticles.setTaxArticles(taxArticle);
			}//if

			addTaxArticles.setPageFlag("none");
			request.setAttribute("command", addTaxArticles);
			return showForm(request, response, errors);
		}//page flag
		
		 

	TaxArticle[] taxArticlesItems = addTaxArticles.getTaxArticles();
    try
    {
    if(deletedList.size()>0)
	  {
		int i=0;
	     	for(Iterator itr=(deletedList.iterator());itr.hasNext();)
		    {
		    TaxArticle taxArticle =(TaxArticle)itr.next();
			if(taxArticle.getTaxArticleCode()!=null && !taxArticle.getTaxArticleCode().trim().equals(""))
			{
		    //i++;
			try
			{
			boolean deletedFlag=taxService.deleteTaxArticle(taxArticle);
			 i++;
			}//try
		        catch(ServiceException e)
						{
					     addTaxArticles.setTaxArticles(addTaxArticle(taxArticlesItems,taxArticle));
		                 deletedList.remove(taxArticle);
						  e.printStackTrace();
						  errors.reject(e.getMessage(), e.getParams(), null);
						  return showForm(request, response, errors);
						  }
							catch(Throwable t)
							{
							  addTaxArticles.setTaxArticles(addTaxArticle(taxArticlesItems,taxArticle));
		                      deletedList.remove(taxArticle);
							  t.printStackTrace();
							  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
							  return showForm(request, response, errors);
							}
			/*if(deletedFlag==true)
		   {
		    errors.reject("taxArticle.delete.error", new Object[] {taxArticle.getTaxArticleCode()}, null);
		   addTaxArticles.setTaxArticles(addTaxArticle(taxArticlesItems,taxArticle));
		   deletedList.remove(taxArticle);
           return showForm(request, response, errors);
			}
			else
				{
                deletedList.remove(taxArticle);
				}*/
			}
		  }//for
		}//if
            	
	    if (taxArticlesItems != null )
		{
			for(int i=0; i< taxArticlesItems.length; i++)
			{
			TaxArticle taxArticle = taxArticlesItems[i];
			 if(taxArticle.getTaxArticleCode() != null && (!taxArticle.getTaxArticleCode().trim().equals("")))
				{
				 taxService.addTaxArticle(taxArticle);
				}
				else
					{
					errors.reject("invalid.taxArticle.error", new Object[] {taxArticle.getTaxArticleCode()}, null);
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
    modelAndView.addObject("backUrl", "create_tax_article.htm");
    modelAndView.addObject("description", "The TaxArticle  has been successfully saved.");
    modelAndView.addObject("backUrlDescription", "You can continue to edit this taxarticle.");*/
	
	ModelAndView modelAndView = new ModelAndView("create-tax-article-r");
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
   AddTaxArticles addTaxArticles = new AddTaxArticles();
   addTaxArticles.setPagination(pagination);
   List taxarticles1=new ArrayList();
   TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
    try
    {
	 taxarticles1=taxService.getAllTaxArticles(addTaxArticles);
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

   if(taxarticles1 != null)
    {
		int i=0;
   		TaxArticle[] taxArticles=new TaxArticle[taxarticles1.size()];
		for(Iterator itr=taxarticles1.iterator();itr.hasNext();)
		{
		 taxArticles[i] =(TaxArticle)itr.next();
    	  i++;
	   }
		addTaxArticles.setTaxArticles(taxArticles);
		}
	   request.setAttribute("command", addTaxArticles);
   return addTaxArticles;
  }



   private TaxArticle[] addTaxArticle(TaxArticle[] taxArticles,TaxArticle existingTaxArticle)
 {
	   TaxArticle taxArticle = new TaxArticle();
    if(existingTaxArticle != null)
	 
       taxArticle = existingTaxArticle;
	 

		TaxArticle[] results = null;
		if(taxArticles == null) results = new TaxArticle[1];
		else results = new TaxArticle[taxArticles.length + 1];
		if(taxArticles != null)
		{
		for(int i=0; i<taxArticles.length; i++)
		{
			results[i] = taxArticles[i];
		}
		}
		results[results.length - 1] = taxArticle;
		return results;
	}

  private TaxArticle[] deleteTaxArticle(TaxArticle[] taxArticles, int indexs)
  {
		if(taxArticles == null) return null;
		if(taxArticles.length <= 0) return taxArticles;
		TaxArticle[] results = new TaxArticle[taxArticles.length - 1];
		int count = 0;
		for(int i=0; i<taxArticles.length; i++)
		{
			if(i == indexs) continue;
			results[count] = taxArticles[i];
			count ++;
		}
		return results;
	}
}
