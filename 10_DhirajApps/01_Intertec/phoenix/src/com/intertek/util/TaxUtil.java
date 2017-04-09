package com.intertek.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.ContactCust;
import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddress;
import com.intertek.entity.Customer;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.State;
import com.intertek.entity.TaxCode;
import com.intertek.entity.TaxCodeTaxRate;
import com.intertek.entity.TaxRate;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.CustomerService;
import com.intertek.service.JobService;
import com.intertek.service.ServiceLocationService;
import com.intertek.service.TaxService;
import com.intertek.service.UserService;

public class TaxUtil
{
  public static List determineVATCodesByCountry(String buName,String serviceLocationCode, String custCode,long contactId, String invoiceDate,String returnType)
  {
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
	  ServiceLocationService serviceLocationService = (ServiceLocationService)ServiceLocator.getInstance().getBean("serviceLocationService");
	  CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
	  TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
	  JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
	  
	 
	 	  BusinessUnit bu = null;
	  Boolean considerStateFlag = false;
	  Country vatCountry = null;
	  State vatState = null;
	  List vatRates = new ArrayList();
	  List allVatRates = new ArrayList();
	  Country shipFromCountry = null;
	  State shipFromState = null;
	  
	  Country shipToCountry = null;
	  State shipToState = null;
	  
	  Country locationCountry = null;
	  State locationState = null;
	  
	  Country customerLocationCountry = null;
	  State customerLocationState = null;
	  
	  Country servicePerformedCountry = null;
	  State servicePerformedState = null;
	  
	  Country reportingCountry = null;
	  State reportingState = null;
	  
	  String treatment = "";
	  Country defaultReportingCountry = null;
	  
	  if(buName != null && (!buName.trim().equals("")))
	   bu = userService.getBusinessUnitByName(buName);
	  
	   
	  if(bu.getBusUnitVatLocs() == null)
		  return null;
	  
	  Iterator iter = bu.getBusUnitVatLocs().iterator();
	  

	  if(iter != null && iter.hasNext()) //Iterate through each busUnitVatLoc corresponding countries
	  {
		  while(iter.hasNext())
		  {
			  
			  BusUnitVatLoc busUnitVatLoc = (BusUnitVatLoc) iter.next();
			  
			  vatCountry = busUnitVatLoc.getCountry();
			  //Check if this country handles VAT
			  
			  
			  if(vatCountry.getVatCountry())
			  {
				  //Check if BU handles VAT
				 
				  
				  if(bu.getVatEnabledInd())
				  {
					  //Check if country handles VAT by province
					  if(vatCountry.getVatByProvince())
					  {
						  considerStateFlag = true;
					  }
					  
					  if(busUnitVatLoc.getDefaultLocInd())
					  {
						  shipFromCountry = vatCountry;
						  locationCountry = vatCountry;
						  defaultReportingCountry = vatCountry;
						  break;
					  }
					  
					  //To be uncommented when the Country state relationship has been sorted out
					  //if(considerStateFlag && busUnitVatLoc.getDefaultLocInd())
						  //shipFromState = busUnitVatLoc.getState();
					  	  //locationState = busUnitVatLoc.getState();
					  
				  }
				  else //BU does not handle VAT, no VAT processing
				  {
					  return null;
				  }
			  }
			  else //Country does not handle VAT, NO vat processing
			  {
				  return null;
			  }
			  
			  //If size of busUnitVatLoc is 1 then reportingCountry = BU country
			  
			  if(bu.getBusUnitVatLocs() != null && bu.getBusUnitVatLocs().size() == 1 )
			  {
				  reportingCountry = vatCountry;
			  }
		  }
		  //Load the serviceLocation & customer Objects
		  ServiceLocation servLocation = serviceLocationService.getServiceLocationByServiceLocationCode(serviceLocationCode);
		  
		  Customer customer = customerService.getCustomerByCustCode(custCode);
		 
		  List custAddrList = customerService.getCustAddrSeqsByCustCode(custCode);
		 
		  
		  if( servLocation != null && servLocation.getCountry() != null)
		  {
			  shipToCountry = servLocation.getCountry();
			  if(servLocation.getState() != null)
				  shipToState = servLocation.getState();
		  }
		  else
		  {

			  if(custAddrList != null)
			  for(int i=0;i<custAddrList.size();i++)
			  {
				  
				  CustAddrSeq custAddrSeq = (CustAddrSeq) custAddrList.get(i);
				  if(custAddrSeq.getCustAddrSeqId().getLocationNumber() == customer.getPrimaryShipToAddress())
				  {
					  Set custAddrSet = custAddrSeq.getCustAddresses();
					  if(custAddrSet != null && custAddrSet.size() > 0)
					  {
						  Iterator iterAddr = custAddrSet.iterator();
						  while(iterAddr.hasNext())
						  {
							  CustAddress custAddress = (CustAddress) iterAddr.next();
							  shipToCountry = countryService.getCountryByCode(custAddress.getCountry());
							  if(custAddress.getState() != null)
								  shipToState = countryService.getStateByCode(custAddress.getState(), custAddress.getCountry());
						  }
					  }
				  }
			  }
		  }
		  
		  //customer location country
		  
		/*  if(custAddrList != null)
			  for(int i=0;i<custAddrList.size();i++)
			  {
				  CustAddrSeq custAddrSeq = (CustAddrSeq) custAddrList.get(i);
				  if(custAddrSeq.getCustAddrSeqId().getLocationNumber() == customer.getPrimaryBillToAddress())
				  {
					  Set custAddrSet = custAddrSeq.getCustAddresses();
					  if(custAddrSet != null && custAddrSet.size() > 0)
					  {
						  Iterator iterAddr = custAddrSet.iterator();
						  while(iterAddr.hasNext())
						  {
							  customerLocationCountry = countryService.getCountryByCode(((CustAddress) iterAddr.next()).getCountry());
						  }
					  }
				  }
			  }*/
		  // new code added on 03122008 for the issue 77476
		  ContactCust contactCust=jobService.getBillingContactByContactId(contactId,custCode);		  
		   if(contactCust!=null)
		   {
		    Set st =contactCust.getCustAddrSeq().getCustAddresses();		   
		    Iterator itr =st.iterator();
		    
		    while(itr.hasNext())
			  {
		    	CustAddress  custAddress=(CustAddress)itr.next();		    	
				
		    	customerLocationCountry = countryService.getCountryByCode(custAddress.getCountry());
			  }
                        }
		  //up to here for the issue 77476
		  
		  
		  //Service Performed Country
		  if(servLocation != null)
		  {
			  servicePerformedCountry = servLocation.getCountry();
			  if(servLocation.getState() != null)
				  servicePerformedState = servLocation.getState();
		  }
		  else
		  {
			  servicePerformedCountry = shipToCountry;
			  if(shipToState != null)
				  servicePerformedState = shipToState;
		  }
		  
		  
		  //Reporting Country
		  if(reportingCountry == null)
		  {
			  if(bu.getBusUnitVatLocs() != null && bu.getBusUnitVatLocs().size() > 1 )
			  {
				  boolean matchFoundFlag = false;
				  Iterator buIter = bu.getBusUnitVatLocs().iterator();
				  while(buIter.hasNext())
				  {
					  BusUnitVatLoc busUnitVatLoc = (BusUnitVatLoc) buIter.next();
					  if(!matchFoundFlag && busUnitVatLoc.getCountry().getCountryCode().trim().equals(servicePerformedCountry.getCountryCode()))
					  {
						  matchFoundFlag = true;
						  reportingCountry = servicePerformedCountry;
					  }
					  
				  }
			  }
			  if(reportingCountry == null)
				  reportingCountry = defaultReportingCountry;
			  
		  }
			  
		  
		  if(reportingState == null)
			  reportingState = servicePerformedState;
		  
		 // System.out.println("reportingState :"+reportingState.getStateId().getStateCode());
		  
		//Determine treatment as domestic or export
		  
		  System.out.println("reportingCountry  : "+reportingCountry.getCountryCode());
		  System.out.println("shipFromCountry : "+shipFromCountry.getCountryCode());
		  System.out.println("vatCountry before: "+vatCountry.getCountryCode());
		  if(reportingCountry != null)
		  {
			  if(shipFromCountry != null)
			  {
				  if(!reportingCountry.getCountryCode().trim().equals(shipFromCountry.getCountryCode()))
				  {
					  vatCountry = reportingCountry;
					  vatCountry = countryService.getCountryByCode(vatCountry.getCountryCode());
					  vatState = reportingState;
					  if(reportingState != null)
						  vatState = countryService.getStateByCodeAndCountryCode(reportingState.getStateId().getStateCode(), vatCountry.getCountryCode());
				  }
				  	  
			  }
		  }
		  System.out.println("vatCountry after: "+vatCountry.getCountryCode());
		  //System.out.println("vatState : "+vatState);
		 if(vatState == null)
			  vatState = reportingState;
		  
		 
		 System.out.println("reportingCountry  xxxxxxxxxxxxxxxx: "+reportingCountry.getCountryCode());

         System.out.println("shipFromCountry xxxxxxxxxxxxxxxx: "+shipFromCountry.getCountryCode());

         System.out.println("vatCountry xxxxxxxxxxxxxxxxxx: "+vatCountry.getCountryCode());

         System.out.println("shipToCountry xxxxxxxxxxxxxxxx: "+shipToCountry.getCountryCode());

         System.out.println("customerLocationCountry xxxxxxxxxxx: "+customerLocationCountry.getCountryCode());

		 
		 
		 
		 
		  if(shipToCountry != null)
		  {
			  if(reportingCountry.getCountryCode().equals(shipToCountry.getCountryCode()))
			  {
				  //System.out.println("customerLocationCountry.getCountryCode="+customerLocationCountry.getCountryCode());
				  String countryCode="";
				  if(customerLocationCountry != null)
					  countryCode = customerLocationCountry.getCountryCode();
				  if(reportingCountry.getCountryCode().equals(countryCode))
					  treatment = "domestic";
				  else
				  {
					  if(reportingCountry.getForeignBuyer() != null && reportingCountry.getForeignBuyer().equalsIgnoreCase("Dom"))
					  {
						  treatment = "domestic";
					  }
					  else
						  treatment = "export";
				  }
			  }
			  else if(!reportingCountry.getCountryCode().equals(shipToCountry.getCountryCode()))
			  {
				  //Modified based on the out of scope logic
				  if(!shipToCountry.getVatCountry())
				  {
					  Iterator iterBu = bu.getBusUnitVatLocs().iterator();		  
					  boolean outOfScopeFlag = false;
					  if(iterBu != null && iterBu.hasNext()) //Iterate through each busUnitVatLoc corresponding countries
					  {
						  while(iterBu.hasNext())
						  {
							  BusUnitVatLoc busUnitVatLoc = (BusUnitVatLoc) iterBu.next();
							  if(busUnitVatLoc.getBusUnitVatLocId().getCountryCode().trim().equals(reportingCountry.getCountryCode()))
							  {
								  if(!outOfScopeFlag && busUnitVatLoc.getOutOfScope() != null && busUnitVatLoc.getOutOfScope().trim().equals("true"))
									  outOfScopeFlag = true;
							  }
						  }
					  }
					  if(outOfScopeFlag)  //No zero rated VAT code processing
						  return null;
					  else
						  treatment = "export";
				  }
				  else
					  treatment = "export";
			  }
		  }
		  else
			  treatment = "domestic";
		  
		  System.out.println("vatCountry : "+vatCountry.getCountryCode());
		  Set countryVats = vatCountry.getCountryVats();
		  System.out.println("treatment : "+treatment);
		  List standardVatCodes = null;
		  if(countryVats != null && countryVats.size()>0)
		  {
			  
			  
			  
			  if(considerStateFlag)
			  {
				  Iterator iterVat = countryVats.iterator();
				  boolean stateMatchFlag = false;
				  String genericVatCode = "";
				  String genericZeroRatedVatCode = "";
				  TaxCode stateVatCode = null;
				  TaxCode stateZeroRatedVatCode = null;
				  TaxCode generalVatCode = null;
				  TaxCode generalZeroRatedVatCode = null;
				  while(iterVat.hasNext())
				  {
					  CountryVAT countryVAT = (CountryVAT) iterVat.next();
					  
					  TaxCode standardVatCode = taxService.getTaxCodeByCode(countryVAT.getVatCode());
					  TaxCode zeroRatedVatCode = taxService.getTaxCodeByCode(countryVAT.getZeroRatedVATCode());
					  
					  if(standardVatCode != null && (!allVatRates.contains(standardVatCode)))
						  allVatRates.add(standardVatCode);
					  if(zeroRatedVatCode != null && (!allVatRates.contains(zeroRatedVatCode)))
						  allVatRates.add(zeroRatedVatCode);
					  
					  
					  if(countryVAT.getCountryVATId().getStateCode() == null || countryVAT.getCountryVATId().getStateCode().trim().equals(""))
					  {
						  genericVatCode = countryVAT.getVatCode();
						  genericZeroRatedVatCode = countryVAT.getZeroRatedVATCode();
					  }
					  if(vatState != null)
					  {
						  if(!stateMatchFlag && countryVAT.getCountryVATId().getStateCode().trim().equalsIgnoreCase(vatState.getStateId().getStateCode()))
						  {
							  stateVatCode = taxService.getTaxCodeByCode(countryVAT.getVatCode());
							  stateZeroRatedVatCode = taxService.getTaxCodeByCode(countryVAT.getZeroRatedVATCode());
							  stateMatchFlag = true;
						  }
					  }
				  }
				  if(stateVatCode == null)
				  {
					  generalVatCode = taxService.getTaxCodeByCode(genericVatCode);
					  generalZeroRatedVatCode = taxService.getTaxCodeByCode(genericZeroRatedVatCode);
					  
					  if(treatment.trim().equals("domestic"))
					  {
						  if(generalVatCode != null && !vatRates.contains(generalVatCode))					  
							  vatRates.add(generalVatCode);
					  }
					  else
					  {
						  if(generalZeroRatedVatCode != null && !vatRates.contains(generalZeroRatedVatCode))	
							  vatRates.add(generalZeroRatedVatCode);
					  }
					  
					  
				  }
				  else
				  {
					  
					  if(treatment.trim().equals("domestic"))
					  {
						  if(stateVatCode != null && !vatRates.contains(stateVatCode))					  
							  vatRates.add(stateVatCode);
					  }
					  else
					  {
						  if(stateZeroRatedVatCode != null && !vatRates.contains(stateZeroRatedVatCode))	
							  vatRates.add(stateZeroRatedVatCode);
					  }
					  
					 
				  }
				  
			  }
			  else
			  {
				  Iterator iterVat = countryVats.iterator();
				  while(iterVat.hasNext())
				  {
					  CountryVAT countryVAT = (CountryVAT) iterVat.next();
					  
					  TaxCode standardVatCode = taxService.getTaxCodeByCode(countryVAT.getVatCode());
					  TaxCode zeroRatedVatCode = taxService.getTaxCodeByCode(countryVAT.getZeroRatedVATCode());
					  
					  if(standardVatCode != null && (!allVatRates.contains(standardVatCode)))
						  allVatRates.add(standardVatCode);
					  if(zeroRatedVatCode != null && (!allVatRates.contains(zeroRatedVatCode)))
						  allVatRates.add(zeroRatedVatCode);
					  
					  
						  if(treatment.trim().equals("domestic"))
						  {
							  if(standardVatCode != null && !vatRates.contains(standardVatCode))					  
								  vatRates.add(standardVatCode);
						  }
						  else
						  {
							  if(zeroRatedVatCode != null && !vatRates.contains(zeroRatedVatCode))	
								  vatRates.add(zeroRatedVatCode);
						  }
				  }
			  }
		  }
		 
		  
		  
	  }
	  else //BU does not have any vat location, NO vat processing
	  {
		  return null;
	  }
	  
	  if(returnType != null && returnType.trim().equals("single"))
		  return vatRates;
	  else if(returnType != null && returnType.trim().equals("multiple"))
		  return allVatRates;
	  else
		  return vatRates;
  }
  
public static List determineTaxCodesByServiceLocation(String serviceLocationCode)
{
	List taxCodes = new ArrayList();
	
	ServiceLocationService serviceLocationService = (ServiceLocationService)ServiceLocator.getInstance().getBean("serviceLocationService");
	TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
	
	if(serviceLocationCode == null || serviceLocationCode.trim().equals(""))
		return null;
	
	ServiceLocation servLocation = serviceLocationService.getServiceLocationByServiceLocationCode(serviceLocationCode);
	
	if(servLocation == null)
		return null;
	CustAddrSeq shipToAddrSeq = servLocation.getShipToCustAddress();
	CustAddress custAddress = new CustAddress();
	Set shipToAddresses =  shipToAddrSeq.getCustAddresses();
	
	if(shipToAddresses == null || shipToAddresses.size() <= 0)
		return null;
	
	Iterator iter = shipToAddresses.iterator();
	while(iter.hasNext())
	{
		custAddress = (CustAddress) iter.next();
	}
	
	String taxcode = custAddress.getTaxCode();
	TaxCode taxCode = taxService.getTaxCodeByCode(taxcode);
	if(taxCode == null)
		return null;
	
	taxCodes.add(taxCode);
	return taxCodes;
}

public static double getTaxPctByTaxCode(String taxcode,Date jobFinishDate)
{
	TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
	double taxPct = 0;
	if(taxcode != null)
	{
	TaxCode taxCode = taxService.getTaxCodeByCodeWithTaxRates(taxcode);
	
	if(taxCode != null)
	{
	Set taxCodeTaxRates = taxCode.getTaxCodeTaxRates();
	if(taxCodeTaxRates != null && taxCodeTaxRates.size() > 0)
	{
		Iterator iter = taxCodeTaxRates.iterator();
		
		while(iter.hasNext())
		{
			TaxCodeTaxRate taxCodeTaxrate = (TaxCodeTaxRate) iter.next();
			TaxRate effTaxRate = taxService.getTaxRateByTaxCodeAndEffDate(taxCodeTaxrate.getTaxCodeTaxRateId().getTaxCode(),jobFinishDate,taxCode.getTaxType());
			if(effTaxRate != null)
			{
				taxPct = taxPct + effTaxRate.getTaxPct();
			}
		}
		

	}
	}
	}
	return taxPct;
}


public static String determineVATTreatementByCountry(String buName,String serviceLocationCode, String custCode,long contactId, String invoiceDate,String returnType)
{
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
	  ServiceLocationService serviceLocationService = (ServiceLocationService)ServiceLocator.getInstance().getBean("serviceLocationService");
	  CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
	  TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
	  JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
	  
	 
	 	  BusinessUnit bu = null;
	  Boolean considerStateFlag = false;
	  Country vatCountry = null;
	  State vatState = null;
	  List vatRates = new ArrayList();
	  List allVatRates = new ArrayList();
	  Country shipFromCountry = null;
	  State shipFromState = null;
	  
	  Country shipToCountry = null;
	  State shipToState = null;
	  
	  Country locationCountry = null;
	  State locationState = null;
	  
	  Country customerLocationCountry = null;
	  State customerLocationState = null;
	  
	  Country servicePerformedCountry = null;
	  State servicePerformedState = null;
	  
	  Country reportingCountry = null;
	  State reportingState = null;
	  
	  String treatment = "";
	  Country defaultReportingCountry = null;
	  
	  if(buName != null && (!buName.trim().equals("")))
	   bu = userService.getBusinessUnitByName(buName);
	  
	   
	  if(bu.getBusUnitVatLocs() == null)
		  return null;
	  
	  Iterator iter = bu.getBusUnitVatLocs().iterator();
	  

	  if(iter != null && iter.hasNext()) //Iterate through each busUnitVatLoc corresponding countries
	  {
		  while(iter.hasNext())
		  {
			  
			  BusUnitVatLoc busUnitVatLoc = (BusUnitVatLoc) iter.next();
			  
			  vatCountry = busUnitVatLoc.getCountry();
			  //Check if this country handles VAT
			  
			  
			  if(vatCountry.getVatCountry())
			  {
				  //Check if BU handles VAT
				 
				  
				  if(bu.getVatEnabledInd())
				  {
					  //Check if country handles VAT by province
					  if(vatCountry.getVatByProvince())
					  {
						  considerStateFlag = true;
					  }
					  
					  if(busUnitVatLoc.getDefaultLocInd())
					  {
						  shipFromCountry = vatCountry;
						  locationCountry = vatCountry;
						  defaultReportingCountry = vatCountry;
						  break;
					  }
					  
					  //To be uncommented when the Country state relationship has been sorted out
					  //if(considerStateFlag && busUnitVatLoc.getDefaultLocInd())
						  //shipFromState = busUnitVatLoc.getState();
					  	  //locationState = busUnitVatLoc.getState();
					  
				  }
				  else //BU does not handle VAT, no VAT processing
				  {
					  return null;
				  }
			  }
			  else //Country does not handle VAT, NO vat processing
			  {
				  return null;
			  }
			  
			  //If size of busUnitVatLoc is 1 then reportingCountry = BU country
			  
			  if(bu.getBusUnitVatLocs() != null && bu.getBusUnitVatLocs().size() == 1 )
			  {
				  reportingCountry = vatCountry;
			  }
		  }
		  //Load the serviceLocation & customer Objects
		  ServiceLocation servLocation = serviceLocationService.getServiceLocationByServiceLocationCode(serviceLocationCode);
		  
		  Customer customer = customerService.getCustomerByCustCode(custCode);
		 
		  List custAddrList = customerService.getCustAddrSeqsByCustCode(custCode);
		 
		  
		  if( servLocation != null && servLocation.getCountry() != null)
		  {
			  shipToCountry = servLocation.getCountry();
			  if(servLocation.getState() != null)
				  shipToState = servLocation.getState();
		  }
		  else
		  {

			  if(custAddrList != null)
			  for(int i=0;i<custAddrList.size();i++)
			  {
				  
				  CustAddrSeq custAddrSeq = (CustAddrSeq) custAddrList.get(i);
				  if(custAddrSeq.getCustAddrSeqId().getLocationNumber() == customer.getPrimaryShipToAddress())
				  {
					  Set custAddrSet = custAddrSeq.getCustAddresses();
					  if(custAddrSet != null && custAddrSet.size() > 0)
					  {
						  Iterator iterAddr = custAddrSet.iterator();
						  while(iterAddr.hasNext())
						  {
							  CustAddress custAddress = (CustAddress) iterAddr.next();
							  shipToCountry = countryService.getCountryByCode(custAddress.getCountry());
							  if(custAddress.getState() != null)
								  shipToState = countryService.getStateByCode(custAddress.getState(), custAddress.getCountry());
						  }
					  }
				  }
			  }
		  }
		  
		  //customer location country
		  
		 /* if(custAddrList != null)
			  for(int i=0;i<custAddrList.size();i++)
			  {
				  CustAddrSeq custAddrSeq = (CustAddrSeq) custAddrList.get(i);
				  if(custAddrSeq.getCustAddrSeqId().getLocationNumber() == customer.getPrimaryBillToAddress())
				  {
					  Set custAddrSet = custAddrSeq.getCustAddresses();
					  if(custAddrSet != null && custAddrSet.size() > 0)
					  {
						  Iterator iterAddr = custAddrSet.iterator();
						  while(iterAddr.hasNext())
						  {
							  customerLocationCountry = countryService.getCountryByCode(((CustAddress) iterAddr.next()).getCountry());
						  }
					  }
				  }
			  }*/
		  
		  // new code added on 03122008 for the issue 77476
		  ContactCust contactCust=jobService.getBillingContactByContactId(contactId,custCode);		  
		   if(contactCust!=null)
		   {
		    Set st =contactCust.getCustAddrSeq().getCustAddresses();
		    Iterator itr =st.iterator();
		    while(itr.hasNext())
			  {
		    	 CustAddress  custAddress=( CustAddress)itr.next();
				  customerLocationCountry = countryService.getCountryByCode(custAddress.getCountry());
			  }	 
		   }
		  //up to here for the issue 77476
		  
		  
		  //Service Performed Country
		  if(servLocation != null)
		  {
			  servicePerformedCountry = servLocation.getCountry();
			  if(servLocation.getState() != null)
				  servicePerformedState = servLocation.getState();
		  }
		  else
		  {
			  servicePerformedCountry = shipToCountry;
			  if(shipToState != null)
				  servicePerformedState = shipToState;
		  }
		  
		  
		  //Reporting Country
		  if(reportingCountry == null)
		  {
			  if(bu.getBusUnitVatLocs() != null && bu.getBusUnitVatLocs().size() > 1 )
			  {
				  boolean matchFoundFlag = false;
				  Iterator buIter = bu.getBusUnitVatLocs().iterator();
				  while(buIter.hasNext())
				  {
					  BusUnitVatLoc busUnitVatLoc = (BusUnitVatLoc) buIter.next();
					  if(!matchFoundFlag && busUnitVatLoc.getCountry().getCountryCode().trim().equals(servicePerformedCountry.getCountryCode()))
					  {
						  matchFoundFlag = true;
						  reportingCountry = servicePerformedCountry;
					  }
					  
				  }
			  }
			  if(reportingCountry == null)
				  reportingCountry = defaultReportingCountry;
			  
		  }
			  
		  
		  if(reportingState == null)
			  reportingState = servicePerformedState;
		  
		 // System.out.println("reportingState :"+reportingState.getStateId().getStateCode());
		  
		//Determine treatment as domestic or export
		  
		 
		  if(reportingCountry != null)
		  {
			  if(shipFromCountry != null)
			  {
				  if(!reportingCountry.getCountryCode().trim().equals(shipFromCountry.getCountryCode()))
				  {
					  vatCountry = reportingCountry;
					  vatCountry = countryService.getCountryByCode(vatCountry.getCountryCode());
					  vatState = reportingState;
					  if(reportingState != null)
						  vatState = countryService.getStateByCodeAndCountryCode(reportingState.getStateId().getStateCode(), vatCountry.getCountryCode());
				  }
				  	  
			  }
		  }
		  
		  //System.out.println("vatState : "+vatState);
		 if(vatState == null)
			  vatState = reportingState;
		 
		  if(shipToCountry != null)
		  {
			  if(reportingCountry.getCountryCode().equals(shipToCountry.getCountryCode()))
			  {
				  //System.out.println("customerLocationCountry.getCountryCode="+customerLocationCountry.getCountryCode());
				  String countryCode="";
				  if(customerLocationCountry != null)
					  countryCode = customerLocationCountry.getCountryCode();
				  if(reportingCountry.getCountryCode().equals(countryCode))
					  treatment = "domestic";
				  else
				  {
					  if(reportingCountry.getForeignBuyer() != null && reportingCountry.getForeignBuyer().equalsIgnoreCase("Dom"))
					  {
						  treatment = "domestic";
					  }
					  else
						  treatment = "export";
				  }
			  }
			  else if(!reportingCountry.getCountryCode().equals(shipToCountry.getCountryCode()))
			  {
				  //Modified based on the out of scope logic
				  if(!shipToCountry.getVatCountry())
				  {
					  Iterator iterBu = bu.getBusUnitVatLocs().iterator();		  
					  boolean outOfScopeFlag = false;
					  if(iterBu != null && iterBu.hasNext()) //Iterate through each busUnitVatLoc corresponding countries
					  {
						  while(iterBu.hasNext())
						  {
							  BusUnitVatLoc busUnitVatLoc = (BusUnitVatLoc) iterBu.next();
							  if(busUnitVatLoc.getBusUnitVatLocId().getCountryCode().trim().equals(reportingCountry.getCountryCode()))
							  {
								  if(!outOfScopeFlag && busUnitVatLoc.getOutOfScope() != null && busUnitVatLoc.getOutOfScope().trim().equals("true"))
									  outOfScopeFlag = true;
							  }
						  }
					  }
					  if(outOfScopeFlag)  //No zero rated VAT code processing
						  return null;
					  else
						  treatment = "export";
				  }
				  else
					  treatment = "export";
			  }
		  }
		  else
			  treatment = "domestic";
		  
		  System.out.println("vatCountry : "+vatCountry.getCountryCode());
		  Set countryVats = vatCountry.getCountryVats();
		  System.out.println("treatment : "+treatment);
		  List standardVatCodes = null;
		  if(countryVats != null && countryVats.size()>0)
		  {
			  
			  
			  
			  if(considerStateFlag)
			  {
				  Iterator iterVat = countryVats.iterator();
				  boolean stateMatchFlag = false;
				  String genericVatCode = "";
				  String genericZeroRatedVatCode = "";
				  TaxCode stateVatCode = null;
				  TaxCode stateZeroRatedVatCode = null;
				  TaxCode generalVatCode = null;
				  TaxCode generalZeroRatedVatCode = null;
				  while(iterVat.hasNext())
				  {
					  CountryVAT countryVAT = (CountryVAT) iterVat.next();
					  
					  TaxCode standardVatCode = taxService.getTaxCodeByCode(countryVAT.getVatCode());
					  TaxCode zeroRatedVatCode = taxService.getTaxCodeByCode(countryVAT.getZeroRatedVATCode());
					  
					  if(standardVatCode != null && (!allVatRates.contains(standardVatCode)))
						  allVatRates.add(standardVatCode);
					  if(zeroRatedVatCode != null && (!allVatRates.contains(zeroRatedVatCode)))
						  allVatRates.add(zeroRatedVatCode);
					  
					  
					  if(countryVAT.getCountryVATId().getStateCode() == null || countryVAT.getCountryVATId().getStateCode().trim().equals(""))
					  {
						  genericVatCode = countryVAT.getVatCode();
						  genericZeroRatedVatCode = countryVAT.getZeroRatedVATCode();
					  }
					  if(vatState != null)
					  {
						  if(!stateMatchFlag && countryVAT.getCountryVATId().getStateCode().trim().equalsIgnoreCase(vatState.getStateId().getStateCode()))
						  {
							  stateVatCode = taxService.getTaxCodeByCode(countryVAT.getVatCode());
							  stateZeroRatedVatCode = taxService.getTaxCodeByCode(countryVAT.getZeroRatedVATCode());
							  stateMatchFlag = true;
						  }
					  }
				  }
				  if(stateVatCode == null)
				  {
					  generalVatCode = taxService.getTaxCodeByCode(genericVatCode);
					  generalZeroRatedVatCode = taxService.getTaxCodeByCode(genericZeroRatedVatCode);
					  
					  if(treatment.trim().equals("domestic"))
					  {
						  if(generalVatCode != null && !vatRates.contains(generalVatCode))					  
							  vatRates.add(generalVatCode);
					  }
					  else
					  {
						  if(generalZeroRatedVatCode != null && !vatRates.contains(generalZeroRatedVatCode))	
							  vatRates.add(generalZeroRatedVatCode);
					  }
					  
					  
				  }
				  else
				  {
					  
					  if(treatment.trim().equals("domestic"))
					  {
						  if(stateVatCode != null && !vatRates.contains(stateVatCode))					  
							  vatRates.add(stateVatCode);
					  }
					  else
					  {
						  if(stateZeroRatedVatCode != null && !vatRates.contains(stateZeroRatedVatCode))	
							  vatRates.add(stateZeroRatedVatCode);
					  }
					  
					 
				  }
				  
			  }
			  else
			  {
				  Iterator iterVat = countryVats.iterator();
				  while(iterVat.hasNext())
				  {
					  CountryVAT countryVAT = (CountryVAT) iterVat.next();
					  
					  TaxCode standardVatCode = taxService.getTaxCodeByCode(countryVAT.getVatCode());
					  TaxCode zeroRatedVatCode = taxService.getTaxCodeByCode(countryVAT.getZeroRatedVATCode());
					  
					  if(standardVatCode != null && (!allVatRates.contains(standardVatCode)))
						  allVatRates.add(standardVatCode);
					  if(zeroRatedVatCode != null && (!allVatRates.contains(zeroRatedVatCode)))
						  allVatRates.add(zeroRatedVatCode);
					  
					  
						  if(treatment.trim().equals("domestic"))
						  {
							  if(standardVatCode != null && !vatRates.contains(standardVatCode))					  
								  vatRates.add(standardVatCode);
						  }
						  else
						  {
							  if(zeroRatedVatCode != null && !vatRates.contains(zeroRatedVatCode))	
								  vatRates.add(zeroRatedVatCode);
						  }
				  }
			  }
		  }
		 
		  
		  
	  }
	  else //BU does not have any vat location, NO vat processing
	  {
		  return null;
	  }
	  
	  if(returnType != null && returnType.trim().equals("single"))
		  return treatment;
	  else if(returnType != null && returnType.trim().equals("multiple"))
		  return treatment;
	  else
		  return treatment;
}

}
