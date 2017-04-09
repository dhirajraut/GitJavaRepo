package com.intertek.web.controller.dropdown;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.cache.ITSCacheManager;
import com.intertek.domain.AddDropDown;
import com.intertek.entity.DropDownId;
import com.intertek.entity.DropDowns;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.DropdownService;
import com.intertek.util.Constants;
import com.intertek.web.controller.BaseSimpleFormController;

//public class ConfigureDropDownController extends SimpleFormController
public class ConfigureDropDownController extends BaseSimpleFormController
{
  public ConfigureDropDownController() {
    super();
	setSessionForm(true);
    setCommandClass(AddDropDown.class);
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
	  AddDropDown addDropDown= (AddDropDown)command;
	  
	  String operationFlag = addDropDown.getOperationFlag();
	  String dropdownType = addDropDown.getDropdownType();
	  String addOrDelete = addDropDown.getAddOrDelete();
	  int dropdownIndex = addDropDown.getDropDownIndex();
	  
	  String cacheAdminFlag = addDropDown.getCacheAdminFlag();
	  
	  if(cacheAdminFlag != null && !cacheAdminFlag.trim().equals(""))
	  {
		  if(cacheAdminFlag.trim().equals("true"))
		  {
				String flushSpace = request.getParameter("FlushCacheSpace");
				String[] toggle = request.getParameterValues("ToggleList");
			    String[] flush = request.getParameterValues("FlushList");

			    if (flushSpace != null) {
					ITSCacheManager.flushCacheSpace();
				}
			    else {
					if (toggle != null) {
						for (int i = 0; i < toggle.length; i++) {
							ITSCacheManager.enableCache(toggle[i], !ITSCacheManager.isCacheEnabled(toggle[i]));
						}
					}
					
					if (flush != null) {
						for (int i = 0; i < flush.length; i++) {
							ITSCacheManager.flushCache(flush[i]);
						}
					}
				}
			    addDropDown.setCacheAdminFlag("none");
				addDropDown.setTabName("1");
				return showForm(request, response, errors);
		  }
		  
	  }
	  
	  if(operationFlag != null && (!operationFlag.trim().equals("")))
	  {
		  if(operationFlag.trim().equals("load"))
		  {
			  if(dropdownType != null && (!dropdownType.trim().equals("")))
			  {
				  addDropDown.setDropDown(loadDropDownData(dropdownType));
				  addDropDown.setOperationFlag("none");
				  addDropDown.setTabName("0");
				  return showForm(request, response, errors);
			  }
		  }
	  }
   
	  if(addOrDelete != null && ((addOrDelete.trim().equals("add")) || (addOrDelete.trim().equals("delete")) ))
	  {
		  if(addOrDelete.trim().equals("add"))
		  {
			  addDropDown.setDropDown(addDropDownItem(addDropDown.getDropDown(),dropdownType));
		  }
		  else
		  {
			  addDropDown.setDropDown(deleteDropDownItem(addDropDown.getDropDown(),dropdownIndex));
		  }
		  addDropDown.setAddOrDelete("none");
		  addDropDown.setTabName("0");
		  return showForm(request, response, errors);
	  }
	  
	  //Save the dropdown elements for the given drop down type
	  DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");
	  
	  //Clear existing data for the given dropdown type first
       List dropdowns=null;
	  try 
	  { 
		  dropdowns = dropdownService.getDropDownsByDropdownType(dropdownType);
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
	  
	  if(dropdowns != null && dropdowns.size() > 0)
	  {
		  for(int i=0;i<dropdowns.size();i++)
		  {
			  DropDowns dropdown = (DropDowns) dropdowns.get(i);
		     try 
				{ 
				 dropdownService.deleteDropdown(dropdown);
				}catch(ServiceException e)
				{
				  e.printStackTrace();
				  errors.reject(e.getMessage(), e.getParams(), null);
				  return showForm(request, response, errors);
				}catch(Throwable t)
				{
				  t.printStackTrace();
				  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				  return showForm(request, response, errors);
				}
		  }
	  }
	  
	  DropDowns[] dropdownItems = addDropDown.getDropDown();
	  
	  
	  //Now save the new dropdown data
	  if(dropdownItems != null && dropdownItems.length >0)
	  {
		  for(int i=0;i<dropdownItems.length;i++)
		  {
			  DropDowns dropdown = dropdownItems[i];
			if(dropdown.getDropDownId().getDropdownType().equals("jobTimeFormat"))
			{
				dropdown.getDropDownId().setFieldName(dropdown.getDropDownId().getFieldName().toUpperCase());
				dropdown.setFieldValue(dropdown.getFieldValue().toUpperCase());
			}
			if(dropdown.getDropDownId().getDropdownType().equals("dateFormat"))
			{
				if(dropdown.getDropDownId().getFieldName().equalsIgnoreCase(Constants.MM_DD_YYYY_DATE_FORMAT))
				dropdown.getDropDownId().setFieldName(Constants.MM_DD_YYYY_DATE_FORMAT);
				if(dropdown.getFieldValue().equalsIgnoreCase(Constants.MM_DD_YYYY_DATE_FORMAT))
				dropdown.setFieldValue(Constants.MM_DD_YYYY_DATE_FORMAT);
				if(dropdown.getDropDownId().getFieldName().equalsIgnoreCase(Constants.DD_MMM_YYYY_DATE_FORMAT))
				dropdown.getDropDownId().setFieldName(Constants.DD_MMM_YYYY_DATE_FORMAT);
				if(dropdown.getFieldValue().equalsIgnoreCase(Constants.DD_MMM_YYYY_DATE_FORMAT))
				dropdown.setFieldValue(Constants.DD_MMM_YYYY_DATE_FORMAT);
				if(dropdown.getDropDownId().getFieldName().equalsIgnoreCase(Constants.DD_MM_YYYY_DATE_FORMAT))
				dropdown.getDropDownId().setFieldName(Constants.DD_MM_YYYY_DATE_FORMAT);
				if(dropdown.getFieldValue().equalsIgnoreCase(Constants.DD_MM_YYYY_DATE_FORMAT))
				dropdown.setFieldValue(Constants.DD_MM_YYYY_DATE_FORMAT);
			}
			 // dropdownService.saveDropdown(dropdown);
			     try 
					{ 
					 dropdownService.saveDropdown(dropdown);
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
		  }
	  }
	 
	  
    ModelAndView modelAndView = new ModelAndView("common-message-r");
   modelAndView.addObject("backUrl", "configure_dropdown.htm");
    modelAndView.addObject("backUrlDescription", "You can continue to edit the dropdown items.");
    modelAndView.addObject("description", "Your Dropdown data has been successfully saved.");

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
  protected boolean suppressValidation(HttpServletRequest request)
  {
    String addOrDelete = request.getParameter("addOrDelete");
  
    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      return true;
    }
    String operationFlag=request.getParameter("operationFlag");
	if((operationFlag != null) && ("load".equals(operationFlag)))
    {
      return true;
    }
    return super.suppressValidation(request);
  }
  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    AddDropDown addDropDown = new AddDropDown();
    DropDowns dropDowns = new DropDowns();
    DropDownId dropDownId = new DropDownId();
    
    dropDowns.setDropDownId(dropDownId);
	

  String countStr = request.getParameter("jobTypeCount");


		if( countStr == null || countStr.trim().equals("") )
			  countStr = "0";

			int count = 0;
			try
			{
				count = new Integer(countStr).intValue();
			}
			catch(Exception e) { }

		 
			addDropDown.setDropDownCount(count);
			DropDowns[] dropDown = new DropDowns[count];
			 for(int i=0; i<count; i++)
			{
				 dropDown[i] = dropDowns;
			}
		
			 addDropDown.setDropDowns(dropDowns); 
			 addDropDown.setDropDown(dropDown);
		
			 
			 

    return addDropDown;
  }

 
  DropDowns[] loadDropDownData(String dropdownType)
  {
	  if(dropdownType == null || dropdownType.trim().equals(""))
		  return null;
	  
	  DropDowns[] dropdowns = null;
	  DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");
	  
	 // List dropdownItems = dropdownService.getDropDownsByDropdownType(dropdownType);
	 List dropdownItems=null;
		try 
		{ 
		dropdownItems = dropdownService.getDropDownsByDropdownType(dropdownType);
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
	  
	  if(dropdownItems == null)
		  return null;
	  
	  else
	  {
		  dropdowns = new DropDowns[dropdownItems.size()];
		  for(int i=0;i<dropdownItems.size();i++)
		  {
			  dropdowns[i] = (DropDowns) dropdownItems.get(i);
		  }
	  }
	  return dropdowns;
	  
  }
  DropDowns[]  addDropDownItem(DropDowns[] dropDowns,String dropdownType)
  {

	  DropDowns dropDown = new DropDowns();
	  DropDownId dropDownId = new DropDownId();
	  dropDownId.setDropdownType(dropdownType);
	  dropDown.setDropDownId(dropDownId);
		


	  DropDowns[] results = null;
			if(dropDowns == null) results = new DropDowns[1];
			else
			{
				results = new DropDowns[dropDowns.length + 1];
				
				
			}
			if(dropDowns != null)
			{
			for(int i=0; i<dropDowns.length; i++)
			{
				results[i] = dropDowns[i];
			}
			}

			results[results.length - 1] = dropDown;
				
			return results;
	}

	  private DropDowns[] deleteDropDownItem(DropDowns[] dropDowns, int index)
	  {
			if(dropDowns == null) return null;
			if(dropDowns.length <= 0) return dropDowns;

			DropDowns[] results = new DropDowns[dropDowns.length - 1];

			int count = 0;
			for(int i=0; i<dropDowns.length; i++)
			{
				if(i == index) continue;
				results[count] = dropDowns[i];

				count ++;
			}

			return results;
	}


}
