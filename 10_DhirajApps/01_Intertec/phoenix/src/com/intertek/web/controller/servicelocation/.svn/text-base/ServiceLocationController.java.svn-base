package com.intertek.web.controller.servicelocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.entity.Country;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.State;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.ServiceLocationService;

public class ServiceLocationController extends AbstractController {
	private static Log log = LogFactory.getLog(ServiceLocationController.class);

	/**
	 * .ctor
	 */
	public ServiceLocationController() {
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * <context dynamic="true|false">
	 <User Name="jaloia111" />
	 <Type Name="MANIFEST_FILE" />
	 </context>
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String serviceLocationName = request.getParameter("serviceLocation.serviceLocationName");
		String serviceLocationCity = request.getParameter("serviceLocation.serviceLocationCity");
		String destination = request.getParameter("jobOprInstrFrom");
		String servCity    = "";
		
		String xml ="";
		log
				.info("======== in ServiceLocationController: serviceLocationName = "
						+ serviceLocationName);

		ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator
				.getInstance().getBean("serviceLocationService");
		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
		log
				.info("======== in ServiceLocationController: serviceLocationService = "
						+ serviceLocationService);
		//if(serviceLocationCity != null && !("".equals(serviceLocationCity))){
		if(serviceLocationCity != null && !("".equals(serviceLocationCity))&& serviceLocationCity.indexOf(",")== -1){
			
			List serviceLocations = serviceLocationService
			.searchServiceLocationsByCity(serviceLocationCity);
			log.info("======== in ServiceLocationController: serviceLocations = "
					+ serviceLocations);
			String port[]= new String[serviceLocations.size()];
			String portValues[]= new String[serviceLocations.size()];
			ArrayList al= new ArrayList();
			int count=0;
			AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
			for(int i=0;i<serviceLocations.size();i++)
			{
				String servCountry="";
				String servState=""; 
				ServiceLocation serviceLocation = new ServiceLocation();
				 serviceLocation = (ServiceLocation)serviceLocations.get(i);
				 Country country =null;
					if(serviceLocation.getCountryCode()!= null)
					{
						country = countryService.getCountryByCode(serviceLocation.getCountryCode());
					}				
				if(serviceLocation.getStateCode()!= null && serviceLocation.getCountryCode()!= null)
				{
					State state = countryService.getStateByCodeAndCountryCode(serviceLocation.getStateCode(),serviceLocation.getCountryCode());
					if(state != null && (country==null || country.getShowState())){
						servState =","+ state.getName();
					}
				}
				if(serviceLocation.getCountryCode()!= null)
				{
					if(country != null)
					servCountry = ","+country.getName();
				}
				String portValue = serviceLocation.getCity() + servState + servCountry;
			String value =serviceLocation.getCity();
				 boolean uniqueFlag = true;
	             if(al != null && al.size() > 0)
	             {      
	            	 for(int j=0;j<al.size();j++)
	                 {
	                     String alStr = (String) al.get(j);
	
	                     if(alStr.trim().equalsIgnoreCase(portValue))
	                     {
	                                 uniqueFlag = false;
	                                 break;
	                     }
	                     else   
	                    	 uniqueFlag = true;
	                 }
	             }
	             if(uniqueFlag)
				al.add(portValue);
			}
			for(Iterator itr=al.iterator();itr.hasNext();)
			{
				String portValue =(itr.next()).toString();
				String servc[]=portValue.split("\\,");
				servCity=servc[0];
				if(destination != null && destination.trim().equals("jobOperationlInstr")){
					xmlBuilder.addItem(portValue,portValue);
					serviceLocationName = null;
				} else {
				xmlBuilder.addItem(portValue,servCity);
				}
				xml = xmlBuilder.toString();
			}
			log.info("======== in ServiceLocationController: xml = " + xml);
						}
		
if(serviceLocationName != null && !("".equals(serviceLocationName))){
	String countryName="";
	String stateName="";
	String servcLocationCity = "";
	String portLocationCity = "";
    String serLocCity ="";
	List serviceLocations = new ArrayList();
			
			if(serviceLocationCity != null && !serviceLocationCity.equals(""))
			{
				if(serviceLocationCity.indexOf(',')!= -1)
				{
					String servc[]=serviceLocationCity.split("\\,");
					int portLocLength = servc.length;
					servCity=servc[0];
					if(servc.length==2)
					{
						countryName = servc[1];
					}else
					{
					  for(int i=0;i<portLocLength;)
		           	  {
		           		  ServiceLocation serviceLocation = null;
		            	  if(i>0)
			            	  serLocCity = serLocCity+"," + servc[i];
		            		  else
		            			  serLocCity = serLocCity + servc[i];
			            	  serviceLocation = serviceLocationService.getServiceLocationByCity(serLocCity.trim());
			            	 
			            	  if(serviceLocation != null)
			            	  {
			            		  servcLocationCity = serLocCity;
			            		  serLocCity = serLocCity;
			            	  }else
			            	  {
			            		  serLocCity = serLocCity;
			            	  }
		            	  i++;
		           	  }
					  servCity = servcLocationCity;					  
					  stateName = servc[portLocLength-2];
					  State state = countryService.getStateByName(stateName);
					  if(state== null)
						  stateName =""; 
					  countryName = servc[portLocLength-1];
				}
				serviceLocations = serviceLocationService
				.getServiceLocationByNameAndPortValues(serviceLocationName,servCity,stateName,countryName);
			}else
			{
				serviceLocations = serviceLocationService
				.getServiceLocationByNameAndCity(serviceLocationName,serviceLocationCity);
			}
		}else
		{
			serviceLocations = serviceLocationService
				.searchServiceLocationsByName(serviceLocationName);
			}
		log.info("======== in ServiceLocationController: serviceLocations = "
				+ serviceLocations);
		String serv[]= new String[serviceLocations.size()];
		String servLocValues[]= new String[serviceLocations.size()];
		ArrayList al= new ArrayList();
		AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
		for(int j=0;j<serviceLocations.size();j++)
		{
			String servCountry="";
			String servState=""; 
			
			ServiceLocation servLocation = new ServiceLocation();
			servLocation = (ServiceLocation)serviceLocations.get(j);
			
			if(servLocation.getName()==null){
				servLocation.setName("");
			}
			if(servLocation.getCity()==null){
				servLocation.setCity("");
			}

			Country country =null;
			if(servLocation.getCountryCode()!= null)
			{
				country = countryService.getCountryByCode(servLocation.getCountryCode());
			}
			
			if(servLocation.getStateCode()!= null && servLocation.getCountryCode()!= null)
			{
				State state = countryService.getStateByCodeAndCountryCode(servLocation.getStateCode(),servLocation.getCountryCode());
				if(state != null && (country==null || country.getShowState())){
					servState =","+ state.getName();
				}
			}
			if(servLocation.getCountryCode()!= null)
			{
				if(country != null)
				servCountry = ","+country.getName();
			}
		
		String portValue = (servLocation.getCity() + servState + servCountry).toUpperCase();
		String servValue = servLocation.getName() +","+ servLocation.getCity() + servState + servCountry;
		String value =servLocation.getName();
		
		boolean uniqueFlag = true;
        if(al != null && al.size() > 0)
        {      
       	 for(int k=0;k<al.size();k++)
            {
                String alStr = (String) al.get(k);

                if(alStr.trim().equalsIgnoreCase(servValue))
                {
                            uniqueFlag = false;
                            break;
                }
                else   
               	 uniqueFlag = true;
            }
        }
        if(uniqueFlag)
        al.add(servValue);
		}
		for(Iterator itr=al.iterator();itr.hasNext();)
		{
			int k = 3;
    		int l = 2;
			String servValue =(itr.next()).toString();
			System.out.println("Service location value"+servValue);
			String portCity = "";
			String servc[]= servValue.split("\\,");
			if(servc.length == 3)
				portCity = servc[1]+","+servc[2];
			else
			{
			  int servLength = servc.length;
			  ServiceLocation serviceLocation = null;
			  State state = countryService.getStateByName(servc[servLength-2]);
			  if(state == null)
				stateName =""; 
			  else
				stateName = ","+servc[servLength-2];
				
          	  for(int j=0;j<servLength;)
          	  {
      		  	  if(stateName!= "")
      		  	  {
      		  		  if(k==3)
      		  			portCity = portCity	;
      		  		  else
      		  			portCity =","+portCity;
      		  		  if(k <= servLength)
      		  			portCity = servc[servLength-k]+portCity;
      		  		  k++;
      		  	  }else
      		  	  {
  		  		  	 if(l==2)
  		  		  	 portCity = portCity;
  		  		  	 else
  		  		  	 portCity =","+portCity;
  		  			 if(l<=servLength)
  		  				portCity = servc[servLength-l]+portCity;
    		  	    l++; 
      		  	  }
            	  serviceLocation = serviceLocationService.getServiceLocationByCity(portCity.trim());
            	  if(serviceLocation != null)
            	  {
            		  portLocationCity = portCity;
            		  portCity = portCity;
            	  }else
            	  {
            		  portCity = portCity;
            	  }
            	  j++;
          	  } 
          	portCity = portLocationCity+stateName+","+servc[servLength-1];
			}
			System.out.println("port location value"+portCity);
			xmlBuilder.addItem(servValue,portCity);
			xml = xmlBuilder.toString();
		}
		log.info("======== in ServiceLocationController: xml = " + xml);
		}
		Map model = new HashMap();
		model.put("Content", xml);

		View view = (View) getApplicationContext().getBean("xmlView");

		return new ModelAndView(view, model);
	}
}
