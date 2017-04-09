package com.intertek.web.controller.jobtype;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.intertek.exception.ServiceException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddOperation;
import com.intertek.entity.Event;
import com.intertek.entity.Operation;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobTypeService;
import com.intertek.web.controller.BaseSimpleFormController;

//public class CreateOperationEventFormController extends SimpleFormController
public class CreateOperationEventFormController extends BaseSimpleFormController
{
  public CreateOperationEventFormController() {
    super();
	setSessionForm(true);
       setCommandClass(AddOperation.class);
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
     AddOperation addOperation= (AddOperation)command;
   
	String addOrDeleteEvent = request.getParameter("addOrDeleteEvent");
	String indexs = request.getParameter("jobTypeIndex");
	Operation operation = addOperation.getOperation();
	JobTypeService jobTypeService = (JobTypeService)ServiceLocator.getInstance().getBean("jobTypeService");

	
	if((addOrDeleteEvent != null) && ("add".equals(addOrDeleteEvent) || "delete".equals(addOrDeleteEvent)))
    {
			if("add".equals(addOrDeleteEvent))
			{
				addOperation.setEvents(addEvents(addOperation.getEvents()));
			}
			else
			{
				addOperation.setEvents(deleteEvents(addOperation.getEvents(),addOperation.getEventIndex()));
			}
		  addOperation.setEventCount(addOperation.getEvents().length);

		  addOperation.setAddOrDeleteEvent("none");
	  
      return showForm(request, response, errors);
    } 


    	String eventFlag=request.getParameter("eventFlag");
		if("newval".equals(eventFlag)){
			String rowNo=request.getParameter("rowNum");
			int num=Integer.parseInt(rowNo);
			Event[] eventItems =addOperation .getEvents();
			Event event = null;
			try{
	        event=jobTypeService.getEventByEventCode(eventItems[num].getEventCode());
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
			eventItems[num]=event;
			addOperation.setEvents(eventItems);
			addOperation.setEventFlag("none");
			return showForm(request, response, errors);
       }


	Event[] eventItems =addOperation .getEvents();
		
    if(operation.getOperationCode()!=null &&(!operation.getOperationCode().trim().equals(""))&&operation.getDescription()!=null && (!operation.getDescription().trim().equals("")))
	  {
		if (eventItems   != null )
		 {			
			operation.getEvents().clear();
			for(int i=0; i< eventItems.length; i++)
			{
				Event event = eventItems[i];	
				
				 if(event.getEventCode() != null && (!event.getEventCode().trim().equals(""))&&event.getEventName()!=null && (!event.getEventName().trim().equals("")))		
					{				
					 try{
					event=jobTypeService.saveEvent(event);
					operation.getEvents().add(event);
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
						else
						{
						errors.reject("invalid.event.error", new Object[] {event.getEventCode()}, null);
						return showForm(request, response, errors);
						}
					}
			   }
		 }
	else
	{
	errors.reject("invalid.operation.error", new Object[] {operation.getOperationCode()}, null);
	return showForm(request, response, errors);
	}

    try
    {
	 if(operation.getOperationCode()!=null && (!operation.getOperationCode().trim().equals("")))
       jobTypeService.saveOperation(operation);
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

  ModelAndView modelAndView = new ModelAndView("common-message-r");
  modelAndView.addObject("backUrl", "create_operation_event.htm?name="+ operation.getOperationCode());
  modelAndView.addObject("backUrlDescription", "You can continue to edit this Operation.");
  modelAndView.addObject("description", "Your operation has been successfully updated.");

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
      String addOrDeleteEvent = request.getParameter("addOrDeleteEvent");
  
    if((addOrDeleteEvent != null) && ("add".equals(addOrDeleteEvent) || "delete".equals(addOrDeleteEvent)))
    {
      return true;
    }

 String eventFlag=request.getParameter("eventFlag");
  if((eventFlag != null) && ("newval".equals(eventFlag)))
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
    AddOperation addOperation = new AddOperation();
	Event event=new Event();
	  Operation operation    = null;

JobTypeService jobTypeService = (JobTypeService)ServiceLocator.getInstance().getBean("jobTypeService");
       String countStr = request.getParameter("eventCount");
	
		String eventCode= request.getParameter("name");
		try{
		operation = jobTypeService.getOperationByOperatoinCode(eventCode);
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
		if(operation != null)
		{
		Event[] events = new Event[(operation.getEvents()).size()];
		if(operation != null)
	    {
			int i=0;	
			
			for(Iterator itr=(operation.getEvents()).iterator();itr.hasNext();)
			{
			 events[i] =(Event)itr.next();
		     i++;
			}
			addOperation.setEvents(events);
		}
		
		
		
		if( countStr == null || countStr.trim().equals("") )
			  countStr = "0";

			int count = 0;
			try
			{
				count = new Integer(countStr).intValue();
			}
			catch(Exception e) { }

		 
		addOperation.setEventCount(count);		
		addOperation.setEvents(events); 
		addOperation.setOperation(operation);
		}
    return addOperation;
  }

    private Event[] addEvents(Event[] events){		
		Event event = new Event();	
		Event[] results = null;		
		if(events == null) results = new Event[1];
		else results = new Event[events.length + 1];
		if(events != null)
		{
		for(int i=0; i<events.length; i++)
		{
			results[i] = events[i];
		}
		}
		results[results.length - 1] = event;
		return results;
	}

  private Event[] deleteEvents(Event[] events, int index){
		if(events == null) return null;
		if(events.length <= 0) return events;
		Event[] results = new Event[events.length - 1];
		int count = 0;
		for(int i=0; i<events.length; i++)
		{
			if(i == index) continue;
			results[count] = events[i];
			count ++;
		}
		return results;
	}
}
