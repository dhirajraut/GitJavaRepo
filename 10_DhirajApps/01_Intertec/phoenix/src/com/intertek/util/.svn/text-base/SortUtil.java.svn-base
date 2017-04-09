package com.intertek.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.Search;
import com.intertek.domain.SelectedTest;
import com.intertek.entity.Branch;
import com.intertek.entity.CfgContract;
import com.intertek.entity.ContractCustContact;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.JobServiceLevel;


public class SortUtil extends Search{
	public static List sortByBranchColumnHeader(List results,String header)
	{
		Branch branch 		= new  Branch();
	    List finalResults 	= new ArrayList();
	    List emptyResults 	= new ArrayList();
	    TreeSet sortedSet 	= new TreeSet();
	    Set set 		  	= new HashSet();
	    HashMap map 		= new LinkedHashMap();
	    Search search       = new Search();
	    if(header != null )
	    {
	    	int startRecord=0;
	    	//Collections.sort(results); 
	    	//return results;
	    	for(int i=0; i<results.size(); i++)
	        {
	    		
	          branch = (Branch)results.get(i);
	          if(header.equals("businessUnit.name"))
	          {
		          if(branch.getBusinessUnit()!= null && !branch.getBusinessUnit().getName().equals(""))
		          {
		        	  if (set.add(branch.getBusinessUnit().getName()))
		        	  map.put(branch.getBusinessUnit().getName(),branch);
		        	  else
		        		  emptyResults.add(branch);    
		          }
		          else
		          emptyResults.add(branch);  
	          }
	          
	          if(header.equals("name"))
	          {
		          if(branch.getName()!= null && !branch.getName().equals(""))
		          {
		        	  if (set.add(branch.getName()))
		        	  map.put(branch.getName(),branch);
		        	  else
		        		  emptyResults.add(branch);    
		          }
		          else
		          emptyResults.add(branch);  
	          }
	         if(header.equals("description"))
	          {
	        	  
	        	  if(branch.getDescription()!= null && !branch.getDescription().equals(""))
		          {
		        	  if (set.add(branch.getDescription()))
		        	  map.put(branch.getDescription(),branch);
		        	  else
		        		  emptyResults.add(branch);    
		          }
		          else
		          emptyResults.add(branch);  
	          }
	         
	         if(header.equals("status"))
	          {
	        	  
	        	  if(branch.getStatus()!= null && !branch.getStatus().equals(""))
		          {
		        	  if (set.add(branch.getStatus()))
		        	  map.put(branch.getStatus(),branch);
		        	  else
		        		  emptyResults.add(branch);    
		          }
		          else
		          emptyResults.add(branch);  
	          }
	        }
	       List mapKeys = new ArrayList(map.keySet());
	       for(int i=0;i<mapKeys.size();i++)
	        {
	        	if(mapKeys.get(i)!= null)
	        		sortedSet.add(mapKeys.get(i));
	        	else
	        	{
	        		branch = (Branch)results.get(i);
	        		emptyResults.add(branch);
	        	}
	        }
	    	results.clear();
	    	Iterator itrr=sortedSet.iterator();
	    	int sortSize=sortedSet.size();
	    	
	    	while(sortSize>0)
	    	{
	    		if(header.equals("businessUnit.name"))
	    		{
	    			branch.getBusinessUnit().setName((String)itrr.next());
		    		finalResults.add(map.get(branch.getBusinessUnit().getName()));
	    		}
	    		if(header.equals("name"))
		        {
	    			branch.setName((String)itrr.next());
		    		finalResults.add(map.get(branch.getName()));
		        }
	    		if(header.equals("description"))
		        {
	    			branch.setDescription((String)itrr.next());
		    		finalResults.add(map.get(branch.getDescription()));
		        }
	    		if(header.equals("status"))
		        {
	    			branch.setStatus((String)itrr.next());
		    		finalResults.add(map.get(branch.getStatus()));
		        }
	    		sortSize--;
	        }
	    	for(int j=0;j<emptyResults.size();j++)
	    		finalResults.add(emptyResults.get(j));
	    }
	
	    return finalResults;
	}

	public static List sortByTestsColumnHeader(List results,String header)
	{
		List finalResults 			= new ArrayList();
	    List emptyResults 			= new ArrayList();
	    TreeSet sortedSet 			= new TreeSet();
	    Set set 		  			= new HashSet();
	    HashMap map 				= new LinkedHashMap();
	    Search search       		= new Search();
	    SelectedTest selectedTest   = new SelectedTest();
	    ArrayList al 				= new ArrayList();
	    ArrayList emptyAl 				= new ArrayList();
	    for(int i=0; i<results.size(); i++)
        {
    	  selectedTest = (SelectedTest)results.get(i);
          if(header.equals("test.testId"))
          {
	        	  map.put(selectedTest,selectedTest.getTest().getTestId());
	        	  al.add(selectedTest.getTest().getTestId());
          }
          
          if(header.equals("test.testDescription"))
          {
	        	  map.put(selectedTest,selectedTest.getTest().getTestDescription());
	        	  if(selectedTest.getTest()!= null && selectedTest.getTest().getTestDescription()!= null && !selectedTest.getTest().getTestDescription().equals(""))
	        	  al.add(selectedTest.getTest().getTestDescription());
	        	  else
	        		  emptyResults.add(selectedTest);  
          }
          if(header.equals("contractRef"))
          {
	        	  map.put(selectedTest,selectedTest.getContractRef());
	        	  if(selectedTest.getContractRef()!= null && !selectedTest.getContractRef().equals(""))
	        	  al.add(selectedTest.getContractRef());
	        	  else
	        		  emptyResults.add(selectedTest);   
          }
        }
	   Collections.sort(al); 
	   for (int i=0; i<al.size(); i++) 
	   {
		String name = (String)al.get(i);
		List mapKeys = new ArrayList(map.keySet());
	
		 boolean haswhere=true;
	       for(int j=0;j<mapKeys.size();j++)
	        {
	        	if(mapKeys.get(j)!= null)
				{
					if(name.equals(map.get(mapKeys.get(j))))
					{
						if(haswhere)
						{
							finalResults.add(mapKeys.get(j));
							map.remove(mapKeys.get(j));
							haswhere=false;
						}
					}
				}
             }
	     }
		 for(int j=0;j<emptyResults.size();j++)
		 {
			 finalResults.add(emptyResults.get(j));
		 }
	    return finalResults;
	}
	

public static List sortByContractColumnHeader(List results,String header, String sortOrder)
	{
		ContractCustContact contractCustContact=new ContractCustContact();
        List finalResults = new ArrayList();
		Map map1 	  = new HashMap();
		ArrayList al=new ArrayList();
		ArrayList result=new ArrayList();

   if(header != null )
	    {      
	    	for(int i=0; i<results.size(); i++)
	        {
	          contractCustContact = (ContractCustContact)results.get(i);
	           if(header.equals("contractdescription"))
	           {
				 map1.put(contractCustContact,contractCustContact.getContractCust().getContract().getDescription());
				 al.add(contractCustContact.getContractCust().getContract().getDescription());
			   }	

               if(header.equals("contractCode"))
	           {		   
                 map1.put(contractCustContact,contractCustContact.getContractCust().getContract().getContractCode());
 				  al.add(contractCustContact.getContractCust().getContract().getContractCode());				  
			   }	

               if(header.equals("customercode"))
	           {
                 map1.put(contractCustContact,contractCustContact.getContractCust().getCustomer().getCustCode());
    			 al.add(contractCustContact.getContractCust().getCustomer().getCustCode());				  
			   }


			   if(header.equals("customername"))
	           {
                 map1.put(contractCustContact,contractCustContact.getContractCust().getCustomer().getName());
    			 al.add(contractCustContact.getContractCust().getCustomer().getName());				  
			   }
			   
			   if(header.equals("contactName"))
	           {
                 map1.put(contractCustContact,contractCustContact.getContact().getFirstName());
				 al.add(contractCustContact.getContact().getFirstName());				  
			   }

			   if(header.equals("currency"))
	           {
				   Set aSet=contractCustContact.getContractCust().getContract().getCfgContracts();
				   if(aSet!=null && !aSet.isEmpty() && aSet.iterator().hasNext()){
					   CfgContract cfg=(CfgContract)aSet.iterator().next();
					   String currency=cfg==null?"":cfg.getCurrencyCD();
		               map1.put(contractCustContact, currency);
		               al.add(currency);				  
				   }
				   // START : #119240
				   else if(aSet!=null && aSet.isEmpty()){
					   String currency = "";
					   map1.put(contractCustContact, currency);
		               al.add(currency);
				   }
				   // END : #119240
			   }
			   
	      }

       }
	    Collections.sort(al); 
	    // START : #119240
   		if(sortOrder!=null && sortOrder.equals("desc")){
	   		Collections.reverse(al);
	   	}
	   	// END : #119240
	   	
	   for (int i=0; i<al.size(); i++) 
	   {
		String name = (String)al.get(i);
		List mapKeys = new ArrayList(map1.keySet());
		boolean haswhere=true;
		   for(int j=0;j<mapKeys.size();j++)
	        {	
	   	      if(mapKeys.get(j)!= null)
			    {					
			      if(name.equals(map1.get(mapKeys.get(j))))
				   {					
					  if(haswhere)
					  {
						result.add(mapKeys.get(j));
						map1.remove(mapKeys.get(j));
						haswhere=false;
					  }
					}
				 }
             }			 
	   }
	   return result;
    }

public static List sortByJobLogColumnHeader(List results,String sortFlag,String sortOrder)
{
   List finalSortResults = new ArrayList();
   List sortResults = new ArrayList();
   List groupResults = new ArrayList();
   List processResults = new ArrayList();
   List emptyResults = new ArrayList();
   boolean multiSort = false;
   if(sortFlag.indexOf(",")!= -1)
   {
	   multiSort=true;
	   String sortF[] = sortFlag.split(",");
	   sortResults = getSortList(results,sortF[0],multiSort,sortOrder);
	   String[] sortValue = new String[sortResults.size()];
	   Date[] dateValue = new Date[sortResults.size()];
	   Long[] longValue = new Long[sortResults.size()];
	   Double[] doubleValue = new Double[sortResults.size()];
	   
	   for(int i=0;i<sortResults.size();i++)
	   {
		  AddJobContract addJobCon =(AddJobContract)sortResults.get(i);
		  if(sortF[0].equals("job.jobNumber"))
			   sortValue[i] = addJobCon.getJobOrder().getJobNumber();
	   	  if(sortF[0].equals("job.etaDate"))
	   		dateValue[i] = addJobCon.getJobOrder().getEtaDate();
	   	  if(sortF[0].equals("job.etaTime"))
	   		 sortValue[i] = addJobCon.getEtaTime();
	   	  if(sortF[0].equals("job.etaTimeTz"))
	   		 sortValue[i] = addJobCon.getJobOrder().getEtaTimeTz();
	   	  if(sortF[0].equals("job.operation"))
	   		 sortValue[i] = addJobCon.getOperation();
	   	  if(sortF[0].equals("job.jobDescription"))
	   		 sortValue[i] = addJobCon.getJobOrder().getJobDescription();
	   	  if(sortF[0].equals("joblog.dispatchStatus"))
	   		 sortValue[i] = addJobCon.getJobLog().getDispatchStatus();
	   	  if(sortF[0].equals("job.jobType"))
	   		 sortValue[i] = addJobCon.getJobType();
	   	  if(sortF[0].equals("serv.city"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getServiceLocation() != null)
	   			 sortValue[i] = addJobCon.getJobOrder().getServiceLocation().getCity();
	   	  }
	   	  if(sortF[0].equals("serv.name"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getServiceLocation() != null)
	   			 sortValue[i] = addJobCon.getJobOrder().getServiceLocation().getName();
	   	  }
	   	  if(sortF[0].equals("job.vesselNames"))
	   		 sortValue[i] = addJobCon.getJobOrder().getVesselNames();
	   	  if(sortF[0].equals("job.productNames"))
	   		 sortValue[i] = addJobCon.getJobOrder().getProductNames();
	   	
	   	  if(sortF[0].equalsIgnoreCase("mh_col14"))
	   		 sortValue[i] = addJobCon.getAddNote();
	   	 //For ITrack#58291- START
	   	  if(sortF[0].equalsIgnoreCase("mh_col32"))
	   		 sortValue[i] = addJobCon.getCustomerNote();
	   	 //For ITrack#58291- END
	      if(sortF[0].equals("cust.custCode"))
	    	  sortValue[i] = addJobCon.getJobContract().getCustCode();
	   	  if(sortF[0].equals("cust.name"))
	   		 sortValue[i] = addJobCon.getJobContract().getCustomer().getName();
	   	  if(sortF[0].equals("contact.id"))
	   		longValue[i] = addJobCon.getJobContract().getContactId();
	   	  if(sortF[0].equals("contact.firstName"))
	   	  {
	   		 String schedularName=addJobCon.getJobContract().getContact().getFirstName()+addJobCon.getJobContract().getContact().getLastName();
	   		 sortValue[i] = schedularName;
	   	  }
	   	  if(sortF[0].equals("jobc.custRefNum"))
	   		 sortValue[i] = addJobCon.getJobContract().getCustRefNum();
	   	  if(sortF[0].equals("jobc.invoiceValue5"))
	   		 sortValue[i] = addJobCon.getJobContract().getInvoiceValue5();
	   	  if(sortF[0].equals("ship.name"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getShippingAgent() != null)
	   			 sortValue[i] = addJobCon.getJobOrder().getShippingAgent().getName();
	   	  }
	   	  if(sortF[0].equals("ship.phone"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getShippingAgent() != null)
	   			 sortValue[i] = addJobCon.getJobOrder().getShippingAgent().getPhone();
	   	  }
	   	  if(sortF[0].equals("tco.name"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getTowingCompany() != null)
	   			 sortValue[i] = addJobCon.getJobOrder().getTowingCompany().getName();
	   	  }
	   	  if(sortF[0].equals("tco.phone"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getTowingCompany() != null)
	   			 sortValue[i] = addJobCon.getJobOrder().getTowingCompany().getPhone();
	   	  }
	   	  if(sortF[0].equals("job.jobFinishDate"))
	   		dateValue[i] = addJobCon.getJobOrder().getJobFinishDate();
	   	  if(sortF[0].equals("contract.description"))
	   	  {
	   		  if(addJobCon.getJobContract() != null && addJobCon.getJobContract().getContract() != null)
	   			 sortValue[i] = addJobCon.getJobContract().getContract().getDescription();
	   	  }
	   	  if(sortF[0].equals("contract.status"))
	   		 sortValue[i] = addJobCon.getContractStatus();
	   	  if(sortF[0].equals("job.createdByUserName"))
	   		 sortValue[i] = addJobCon.getJobOrder().getCreatedByUserName();
	   	  if(sortF[0].equals("job.updatedByUserName"))
	   		 sortValue[i] = addJobCon.getJobOrder().getUpdatedByUserName();
	   	  // main end
//		   	Dispatch begin
	   	  if(sortF[0].equals("joblog.coordinator"))
	   		sortValue[i] = addJobCon.getJobLog().getCoordinator();
	   	  if(sortF[0].equals("joblog.inspector"))
	   		sortValue[i] = addJobCon.getJobLog().getInspector();
	   	  if(sortF[0].equals("joblog.dispatchTz"))
	   		sortValue[i] = addJobCon.getJobLog().getDispatchTz();
	   	  if(sortF[0].equals("joblog.inspectorNotifyDt"))
	   		dateValue[i] = addJobCon.getJobLog().getInspectorNotifyDt();
	   	  if(sortF[0].equals("joblog.inspectorNotifyTime"))
	   		sortValue[i] = addJobCon.getInspectorNotifyTime();
	   	  if(sortFlag.equals("joblog.inspectorArriveDt"))
	   		dateValue[i] = addJobCon.getJobLog().getInspectorArriveDt();
	   	  if(sortF[0].equals("joblog.inspectorArriveTime"))
	   		sortValue[i] = addJobCon.getInspectorArriveTime();
	   	  if(sortF[0].equals("joblog.arriveDt"))
	   		dateValue[i] = addJobCon.getJobLog().getArriveDt();
	   	  if(sortF[0].equals("joblog.arriveTime"))
	   		sortValue[i] = addJobCon.getInspectorArriveTime();
	   	  if(sortF[0].equals("joblog.dockDt"))
	   		dateValue[i] = addJobCon.getJobLog().getDockDt();
	   	  if(sortF[0].equals("joblog.dockTime"))
	   		sortValue[i] = addJobCon.getDockTime();
	   	  if(sortF[0].equals("joblog.hoseOnDt"))
	   		dateValue[i] = addJobCon.getJobLog().getHoseOnDt();
		  if(sortF[0].equals("joblog.hoseOnTime"))
			  sortValue[i] = addJobCon.getHoseOnTime();
		  if(sortF[0].equals("joblog.estStartDt"))
			  dateValue[i] = addJobCon.getJobLog().getEstStartDt();
		  if(sortF[0].equals("joblog.estStartTime"))
			  sortValue[i] = addJobCon.getEstStartTime();
		  if(sortF[0].equals("joblog.commenceDt"))
			  dateValue[i] = addJobCon.getJobLog().getCommenceDt();
		  if(sortF[0].equals("joblog.commenceTime"))
			  sortValue[i] = addJobCon.getCommenceTime();
		  if(sortF[0].equals("joblog.delayDt"))
			  dateValue[i] = addJobCon.getJobLog().getDelayDt();
		  if(sortF[0].equals("joblog.delayTime"))
			  sortValue[i] = addJobCon.getDelayTime();
		  if(sortF[0].equals("joblog.delayEndDt"))
			  dateValue[i] = addJobCon.getJobLog().getDelayEndDt();
		  if(sortF[0].equals("joblog.delayEndTime"))
			  sortValue[i] = addJobCon.getDelayEndTime();
		  if(sortF[0].equals("joblog.estCompleteDt"))
			  dateValue[i] = addJobCon.getJobLog().getEstCompleteDt();
		  if(sortF[0].equals("joblog.estCompleteTime"))
			  sortValue[i] = addJobCon.getEstCompleteTime();
		  if(sortF[0].equals("joblog.completeDt"))
			  dateValue[i] = addJobCon.getJobLog().getCompleteDt();
		  if(sortF[0].equals("joblog.completeTime"))
			  sortValue[i] = addJobCon.getCompleteTime();
		  if(sortF[0].equals("joblog.hoseOffDt"))
			  dateValue[i] = addJobCon.getJobLog().getHoseOffDt();
		  if(sortF[0].equals("joblog.hoseOffTime"))
			  sortValue[i] = addJobCon.getHoseOffTime();
		  if(sortF[0].equals("joblog.releaseDt"))
			  dateValue[i] = addJobCon.getJobLog().getReleaseDt();
		  if(sortF[0].equals("joblog.releaseTime"))
			  sortValue[i] = addJobCon.getReleaseTime();
		  if(sortF[0].equals("joblog.summaryDt"))
		  dateValue[i] = addJobCon.getJobLog().getSummaryDt();
		  //Dispatch end
		  //Billing Tab
		  if(sortF[0].equals("blconct.id"))
		  {
			  if(addJobCon.getJobContract()!= null && addJobCon.getJobContract().getBillingContact()!= null)
				  longValue[i] = addJobCon.getJobContract().getBillingContact().getId();
		  }
		  if(sortF[0].equals("blconct.firstName"))
		  {
			  if(addJobCon.getJobContract()!= null && addJobCon.getJobContract().getBillingContact()!= null)
			  {
			  String firstName = addJobCon.getJobContract().getBillingContact().getFirstName();
			  String lastName = addJobCon.getJobContract().getBillingContact().getLastName();
			  sortValue[i] = firstName+lastName;
			  }
		  }
		  if(sortF[0].equals("jobc.billStatus"))
			  sortValue[i] = addJobCon.getJobContract().getBillStatus();
		  if(sortF[0].equals("jobcinvc.invoice"))
		  {
			 if(addJobCon.getLatestJobContractInvoice()!= null)
				 sortValue[i] = addJobCon.getLatestJobContractInvoice().getInvoice();
		  }
		  if(sortF[0].equals("jobc.invoiceAmt"))
		  {
			  doubleValue[i] = addJobCon.getJobContract().getInvoiceAmt();
		  }
		  if(sortF[0].equals("jobc.invoiceDate"))
		  {
			  if(addJobCon.getLatestJobContractInvoice()!= null)
				  dateValue[i] = addJobCon.getLatestJobContractInvoice().getInvoiceDate();
		  }
		  if(sortF[0].equals("jobcinvc.generatedDateTime"))
		  {
			  if(addJobCon.getLatestJobContractInvoice()!= null)
				  dateValue[i] = addJobCon.getLatestJobContractInvoice().getGeneratedDateTime();
		  }
		  if(sortF[0].equals("jobc.creditInd"))
			  sortValue[i] = addJobCon.getJobContract().getCreditInd();
		  
		  if(sortF[0].equals("job.reOpenDate"))
		  {
			  if(addJobCon.getJobOrder()!= null && addJobCon.getJobOrder().getReOpenDate()!= null)
			  dateValue[i] = addJobCon.getJobOrder().getReOpenDate();
		  }
		  //end
		  //process log start
		  if(sortF[0].equals("joblog.processTz"))
			  sortValue[i] = addJobCon.getJobLog().getProcessTz();
		  if(sortF[0].equals("joblog.sampleReceiveDt"))
			  dateValue[i] = addJobCon.getJobLog().getSampleReceiveDt();
		  if(sortF[0].equals("joblog.sampleReceiveTime"))
			  sortValue[i] = addJobCon.getSampleReceiveTime();
		  if(sortF[0].equals("joblog.prelimReportDt"))
			  dateValue[i] = addJobCon.getJobLog().getPrelimReportDt();
		  if(sortF[0].equals("joblog.prelimReportTime"))
			  sortValue[i] = addJobCon.getPrelimReportTime();
		  if(sortF[0].equals("joblog.sampleShipDt"))
			  dateValue[i] = addJobCon.getJobLog().getSampleShipDt();
		  if(sortF[0].equals("joblog.sampleShipTime"))
			  sortValue[i] = addJobCon.getSampleShipTime();
		  if(sortF[0].equals("joblog.finalReportDt"))
			  dateValue[i] = addJobCon.getJobLog().getFinalReportDt();
		  if(sortF[0].equals("joblog.finalReportTime"))
			  sortValue[i] = addJobCon.getFinalReportTime();
		  if(sortF[0].equals("joblog.distributeDt"))
			  dateValue[i] = addJobCon.getJobLog().getDistributeDt();
		  if(sortF[0].equals("joblog.distributeTime"))
			  sortValue[i] = addJobCon.getDistributeTime();
	   }
	 
	   int m=1;
	   for(int i=0;i<sortValue.length;i++)
	   {
		  if(sortValue[i] != null && !sortValue[i].equals("")&& m <=(sortValue.length-1) && sortValue[i].equals(sortValue[m]))
		   {
			   groupResults.add((AddJobContract)sortResults.get(i));
		   }
		   else if(sortValue[i] != null && !sortValue[i].equals(""))
		   {
			   groupResults.add((AddJobContract)sortResults.get(i));
			  
			  /* if(groupResults.size()>1)
			   {*/
				   for(int j=1;j<sortF.length;j++)
				   {
				      if(j==1)
				      {
				    	  processResults = getSortList(groupResults,sortF[j],multiSort,sortOrder);
				      }else
				      {
				    	  groupResults.clear();  
				    	  groupResults = processResults;
				    	  processResults = getSortList(groupResults,sortF[j],multiSort,sortOrder);
				      }
				   }
				
				   for(int k=0;k<processResults.size();k++)
				   finalSortResults.add((AddJobContract)processResults.get(k));
			  /* }else
				   finalSortResults.add((AddJobContract)sortResults.get(i));*/
		   }else
			   emptyResults.add((AddJobContract)sortResults.get(i));
		   m++;
	   }
	   for( int j=0;j<emptyResults.size();j++)
	   {
	   	AddJobContract addJobCon = (AddJobContract)emptyResults.get(j);
	   	finalSortResults.add(addJobCon);
	   }
   } else {
   finalSortResults = getSortList(results,sortFlag,multiSort,sortOrder) ; 
   }
   
   return finalSortResults;
}
private static List getSortList(List results,String sortFlag,boolean multiSort,String sortOrder)
{
	List emptyDataResults = new ArrayList();
	List finalResults 	  = new ArrayList();
	
	   ArrayList al = new ArrayList();
	   Map map      = new HashMap();
	   Map objMap = new HashMap();
	   boolean timeSortFlag=false;
	   for(int i=0; i< results.size();i++)
	   {
	     AddJobContract addJobCon = new AddJobContract();
	     String mapStrValue = null;
	     Date mapDtValue = null;
	     long mapLValue = 0;
	     Double  mapDblValue = null;
	     addJobCon = (AddJobContract)results.get(i);
	     if(sortFlag.equals("job.buName"))
	     {
	   		  mapStrValue = addJobCon.getJobOrder().getBuName();
	     }
	     if(sortFlag.equals("job.jobStatus"))
	     {
	   		  mapStrValue = addJobCon.getJobOrder().getJobStatus();
	     }
	     //	main start
	      if(sortFlag.equals("job.jobNumber"))
	   		  mapStrValue = addJobCon.getJobOrder().getJobNumber();
	      if(sortFlag.equals("job.etaTime"))
	      {
	    	  sortFlag = "job.etaDate";
		   	  timeSortFlag=true;
	      }
	   	  if(sortFlag.equals("job.etaDate"))
	   	  {
	   		if(timeSortFlag)
	   		mapDtValue = addJobCon.getJobOrder().getEtaTime();
	    	else
	   		mapDtValue = addJobCon.getJobOrder().getEtaDate();
	   	  }
	   	 
	   	  if(sortFlag.equals("job.etaTimeTz"))
	   		  mapStrValue = addJobCon.getJobOrder().getEtaTimeTz();
	   	  if(sortFlag.equals("job.operation"))
	   		  mapStrValue = addJobCon.getOperation();
	   	  if(sortFlag.equals("jobDescription"))
	   		  mapStrValue = addJobCon.getJobOrder().getJobDescription();
	   	  if(sortFlag.equals("joblog.dispatchStatus"))
	   		  mapStrValue = addJobCon.getJobLog().getDispatchStatus();
	   	  if(sortFlag.equals("job.jobType"))
	   		  mapStrValue = addJobCon.getJobType();
	   	  if(sortFlag.equals("serv.city"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getServiceLocation() != null)
	   		  mapStrValue = addJobCon.getJobOrder().getServiceLocation().getCity();
	   	  }
	   	  if(sortFlag.equals("serv.name"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getServiceLocation() != null)
	   			  mapStrValue = addJobCon.getJobOrder().getServiceLocation().getName();
	   	  }
	   	  if(sortFlag.equals("job.vesselNames"))
	   		  mapStrValue = addJobCon.getJobOrder().getVesselNames();
	   	  if(sortFlag.equals("job.productNames"))
	   		  mapStrValue = addJobCon.getJobOrder().getProductNames();
	   	
	   	  if(sortFlag.equalsIgnoreCase("mh_col14"))
			  mapStrValue = addJobCon.getAddUpdatedNote();
	   	if(sortFlag.equalsIgnoreCase("mh_col32"))
			  mapStrValue = addJobCon.getCustomerNoteDetails();
	   		 // mapStrValue = addJobCon.getAddNote();
		
	      if(sortFlag.equals("cust.custCode"))
	   		  mapStrValue = addJobCon.getJobContract().getCustCode();
	   	  if(sortFlag.equals("cust.name"))
	   		  mapStrValue = addJobCon.getJobContract().getCustomer().getName();
	   	  if(sortFlag.equals("contact.id"))
	   		  mapLValue = addJobCon.getJobContract().getContactId();
	   	  if(sortFlag.equals("contact.firstName"))
	   	 {
	   		 String schedularName=addJobCon.getJobContract().getContact().getFirstName()+addJobCon.getJobContract().getContact().getLastName();
	   		  mapStrValue = schedularName;
	   	 }
	   	  if(sortFlag.equals("jobc.custRefNum"))
	   		  mapStrValue = addJobCon.getJobContract().getCustRefNum();
	   	  if(sortFlag.equals("jobc.invoiceValue5"))
	   		  mapStrValue = addJobCon.getJobContract().getInvoiceValue5();
	   	  if(sortFlag.equals("ship.name"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getShippingAgent() != null)
	   		  mapStrValue = addJobCon.getJobOrder().getShippingAgent().getName();
	   	  }
	   	  if(sortFlag.equals("ship.phone"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getShippingAgent() != null)
	   		  mapStrValue = addJobCon.getJobOrder().getShippingAgent().getPhone();
	   	  }
	   	  if(sortFlag.equals("tco.name"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getTowingCompany() != null)
	   		  mapStrValue = addJobCon.getJobOrder().getTowingCompany().getName();
	   	  }
	   	  if(sortFlag.equals("tco.phone"))
	   	  {
	   		  if(addJobCon.getJobOrder() != null && addJobCon.getJobOrder().getTowingCompany() != null)
	   		  mapStrValue = addJobCon.getJobOrder().getTowingCompany().getPhone();
	   	  }
	   	  if(sortFlag.equals("job.jobFinishDate"))
	   		  mapDtValue = addJobCon.getJobOrder().getJobFinishDate();
	   	  if(sortFlag.equals("contract.description"))
	   	  {
	   		  if(addJobCon.getJobContract() != null && addJobCon.getJobContract().getContract() != null)
	   		  mapStrValue = addJobCon.getJobContract().getContract().getDescription();
	   	  }
	   	  if(sortFlag.equals("contract.status"))
	   		  mapStrValue = addJobCon.getContractStatus();
	   	  if(sortFlag.equals("job.createdByUserName"))
	   		  mapStrValue = addJobCon.getJobOrder().getCreatedByUserName();
	   	  if(sortFlag.equals("job.updatedByUserName"))
	   		  mapStrValue = addJobCon.getJobOrder().getUpdatedByUserName();
	   	  // main end
//	   	Dispatch begin
	   	  if(sortFlag.equals("joblog.coordinator"))
	   		  mapStrValue = addJobCon.getJobLog().getCoordinator();
	   	  if(sortFlag.equals("joblog.inspector"))
	   		  mapStrValue = addJobCon.getJobLog().getInspector();
	   	  if(sortFlag.equals("joblog.dispatchTz"))
	   		  mapStrValue = addJobCon.getJobLog().getDispatchTz();
	      if(sortFlag.equals("joblog.inspectorNotifyTime"))
	      {
	   		 sortFlag = "joblog.inspectorNotifyDt";
	   		 timeSortFlag=true;
	      }
	      if(sortFlag.equals("joblog.inspectorNotifyDt"))
	      {
	    	  if(timeSortFlag)
	   		  mapDtValue = addJobCon.getJobLog().getInspectorNotifyTime();
	    	  else
	    	  mapDtValue = addJobCon.getJobLog().getInspectorNotifyDt();  
	      }
	      if(sortFlag.equals("joblog.inspectorArriveTime"))
	      { 
	    	  sortFlag = "joblog.inspectorArriveDt";
	   		  timeSortFlag=true;
	      }
	   	  if(sortFlag.equals("joblog.inspectorArriveDt"))
	   	  {
	   		 if(timeSortFlag)
	   		 mapDtValue = addJobCon.getJobLog().getInspectorArriveTime();
	    	 else
	    	 mapDtValue = addJobCon.getJobLog().getInspectorArriveDt();  
	   	  }
	   	 if(sortFlag.equals("joblog.arriveTime"))
	   	 {
		   	 sortFlag = "joblog.arriveDt";
		   	 timeSortFlag=true;
	   	 }
	   	 if(sortFlag.equals("joblog.arriveDt"))
	   	 {
	   		if(timeSortFlag)
	   		mapDtValue = addJobCon.getJobLog().getArriveTime();
	    	else
	    	mapDtValue = addJobCon.getJobLog().getArriveDt();
	   	 }
	   	if(sortFlag.equals("joblog.dockTime"))
	   	{
	   	 sortFlag = "joblog.dockDt";
	   	 timeSortFlag=true;
	   	}
	   	if(sortFlag.equals("joblog.dockDt"))
	   	{
	   		if(timeSortFlag)
	   		mapDtValue = addJobCon.getJobLog().getDockTime();
	    	else
	   		mapDtValue = addJobCon.getJobLog().getDockDt();
	   	}
	   	if(sortFlag.equals("joblog.hoseOnTime"))
	   	{
	   		sortFlag = "joblog.hoseOnDt";
		   	timeSortFlag=true;
	   	}
   	   if(sortFlag.equals("joblog.hoseOnDt"))
   	   {
   		   if(timeSortFlag)
	   	   mapDtValue = addJobCon.getJobLog().getHoseOnTime();
	       else
	       mapDtValue = addJobCon.getJobLog().getHoseOnDt();
   	   }
   	  if(sortFlag.equals("joblog.estStartTime"))
   	  {
   		   sortFlag = "joblog.estStartDt";
	   	   timeSortFlag=true;
   	  }
	  if(sortFlag.equals("joblog.estStartDt"))
	  {
		  if(timeSortFlag)
	   	  mapDtValue = addJobCon.getJobLog().getEstStartTime();
	      else
		  mapDtValue = addJobCon.getJobLog().getEstStartDt();
	  }
	  if(sortFlag.equals("joblog.commenceTime"))
	  {
		  sortFlag = "joblog.commenceDt";
  	      timeSortFlag=true;
	  }
	  if(sortFlag.equals("joblog.commenceDt"))
	  {
		  if(timeSortFlag)
	   	  mapDtValue = addJobCon.getJobLog().getCommenceTime();
	      else
		  mapDtValue = addJobCon.getJobLog().getCommenceDt();
	  }
	  if(sortFlag.equals("joblog.delayTime"))
	  {
		  sortFlag = "joblog.delayDt";
  	      timeSortFlag=true;
	  }
	  if(sortFlag.equals("joblog.delayDt"))
	  {
		  if(timeSortFlag)
	   	  mapDtValue = addJobCon.getJobLog().getDelayTime();
	      else
		  mapDtValue = addJobCon.getJobLog().getDelayDt();
	  }
	  if(sortFlag.equals("joblog.delayEndTime"))
	  {
		  sortFlag = "joblog.delayEndDt";
  	      timeSortFlag=true;
	  }
	  if(sortFlag.equals("joblog.delayEndDt"))
	  {
		  if(timeSortFlag)
	   	  mapDtValue = addJobCon.getJobLog().getDelayEndTime();
	      else
		  mapDtValue = addJobCon.getJobLog().getDelayEndDt();
	  }
	  if(sortFlag.equals("joblog.estCompleteTime"))
	  {
		  sortFlag = "joblog.estCompleteDt";
  	      timeSortFlag=true;
	  }
	  if(sortFlag.equals("joblog.estCompleteDt"))
	  {
		  if(timeSortFlag)
	   	  mapDtValue = addJobCon.getJobLog().getEstCompleteTime();
	      else
		  mapDtValue = addJobCon.getJobLog().getEstCompleteDt();
	  }
	  if(sortFlag.equals("joblog.completeTime"))
	  {
		  sortFlag = "joblog.completeDt";
  	      timeSortFlag=true;
	  }
	  if(sortFlag.equals("joblog.completeDt"))
	  {
		  if(timeSortFlag)
	   	  mapDtValue = addJobCon.getJobLog().getCompleteTime();
	      else
		  mapDtValue = addJobCon.getJobLog().getCompleteDt();
	  }
	  if(sortFlag.equals("joblog.hoseOffTime"))
	  {
		  sortFlag = "joblog.hoseOffDt";
  	      timeSortFlag=true;
	  }
	  if(sortFlag.equals("joblog.hoseOffDt"))
	  {
		  if(timeSortFlag)
	   	  mapDtValue = addJobCon.getJobLog().getHoseOffTime();
	      else
		  mapDtValue = addJobCon.getJobLog().getHoseOffDt();
	  }
	  if(sortFlag.equals("joblog.releaseTime"))
	  {
		  sortFlag = "joblog.releaseDt";
  	      timeSortFlag=true;
	  }
	  if(sortFlag.equals("joblog.releaseDt"))
	  {
		  if(timeSortFlag)
	   	  mapDtValue = addJobCon.getJobLog().getReleaseTime();
	      else
		  mapDtValue = addJobCon.getJobLog().getReleaseDt();
	  }
		  if(sortFlag.equals("joblog.summaryDt"))
   		  mapDtValue = addJobCon.getJobLog().getSummaryDt();
		  //Dispatch end
		  //Billing Tab
		  if(sortFlag.equals("blconct.id"))
		  {
			  if(addJobCon.getJobContract()!= null && addJobCon.getJobContract().getBillingContact()!= null)
			  mapLValue = addJobCon.getJobContract().getBillingContact().getId();
		  }
		  if(sortFlag.equals("blconct.firstName"))
		  {
			  if(addJobCon.getJobContract()!= null && addJobCon.getJobContract().getBillingContact()!= null)
			  {
			  String firstName = addJobCon.getJobContract().getBillingContact().getFirstName();
			  String lastName = addJobCon.getJobContract().getBillingContact().getLastName();
   		      mapStrValue = firstName+lastName;
			  }
		  }
		  if(sortFlag.equals("jobc.billStatus"))
   		  mapStrValue = addJobCon.getJobContract().getBillStatus();
		 if(sortFlag.equals("jobcinvc.invoice"))
		 {
			 if(addJobCon.getLatestJobContractInvoice()!= null)
			 mapStrValue = addJobCon.getLatestJobContractInvoice().getInvoice();
		 }
			  if(sortFlag.equals("jobc.invoiceAmt"))
			  {
				  mapDblValue = addJobCon.getJobContract().getInvoiceAmt();
			  }
			  if(sortFlag.equals("jobc.invoiceDate"))
			  {
				  if(addJobCon.getLatestJobContractInvoice()!= null)
		   		  mapDtValue = addJobCon.getLatestJobContractInvoice().getInvoiceDate();
			  }
			  if(sortFlag.equals("jobcinvc.generatedDateTime"))
			  {
				  if(addJobCon.getLatestJobContractInvoice()!= null)
		   		  mapDtValue = addJobCon.getLatestJobContractInvoice().getGeneratedDateTime();
			  }
			 
			  if(sortFlag.equals("jobc.creditInd"))
		   		  mapStrValue = addJobCon.getJobContract().getCreditInd();
			  
			  if(sortFlag.equals("job.reOpenDate"))
			  {
				  if(addJobCon.getJobOrder()!= null && addJobCon.getJobOrder().getReOpenDate()!= null)
					  mapDtValue = addJobCon.getJobOrder().getReOpenDate();
			  }
			  //end
			  //process log start
			  if(sortFlag.equals("joblog.processTz"))
	   		  mapStrValue = addJobCon.getJobLog().getProcessTz();
		  if(sortFlag.equals("joblog.sampleReceiveTime"))
		  {
			  sortFlag = "joblog.sampleReceiveDt";
	  	      timeSortFlag=true;
		  }
		  if(sortFlag.equals("joblog.sampleReceiveDt"))
		  {
			  if(timeSortFlag)
		   	  mapDtValue = addJobCon.getJobLog().getSampleReceiveTime();
		      else
		      mapDtValue = addJobCon.getJobLog().getSampleReceiveDt();
		  }
		  if(sortFlag.equals("joblog.prelimReportTime"))
		  {
			  sortFlag = "joblog.prelimReportDt";
	  	      timeSortFlag=true;
		  }
		  if(sortFlag.equals("joblog.prelimReportDt"))
		  {
			  if(timeSortFlag)
		   	  mapDtValue = addJobCon.getJobLog().getPrelimReportTime();
		      else
		      mapDtValue = addJobCon.getJobLog().getPrelimReportDt();
		  }
		  if(sortFlag.equals("joblog.sampleShipTime"))
		  {
			  sortFlag = "joblog.sampleShipDt";
	  	      timeSortFlag=true;
		  }
		  if(sortFlag.equals("joblog.sampleShipDt"))
		  {
			  if(timeSortFlag)
		   	  mapDtValue = addJobCon.getJobLog().getSampleShipTime();
		      else
			  mapDtValue = addJobCon.getJobLog().getSampleShipDt();
		  }
		  if(sortFlag.equals("joblog.finalReportTime"))
		  {
			  sortFlag = "joblog.finalReportDt";
	  	      timeSortFlag=true;
		  }
		  if(sortFlag.equals("joblog.finalReportDt"))
		  {
			  if(timeSortFlag)
		   	  mapDtValue = addJobCon.getJobLog().getFinalReportTime();
		      else
		      mapDtValue = addJobCon.getJobLog().getFinalReportDt();
		  }
		  if(sortFlag.equals("joblog.distributeTime"))
		  {
			  sortFlag = "joblog.distributeDt";
	  	      timeSortFlag=true;
		  }
		  if(sortFlag.equals("joblog.distributeDt"))
		  {
			  if(timeSortFlag)
		   	  mapDtValue = addJobCon.getJobLog().getDistributeTime();
		      else
			  mapDtValue = addJobCon.getJobLog().getDistributeDt();
		  }
	 	if(sortFlag != null &&(sortFlag.equals("job.jobNumber")||sortFlag.equals("job.etaTimeTz")||
 			sortFlag.equals("job.operation")||sortFlag.equals("joblog.dispatchStatus")||
 			sortFlag.equals("job.jobType")||sortFlag.equals("serv.city")||
 			sortFlag.equals("serv.name")||sortFlag.equals("job.vesselNames")||
 			sortFlag.equals("job.productNames")||sortFlag.equalsIgnoreCase("mh_col14")||sortFlag.equalsIgnoreCase("mh_col32")||
 			sortFlag.equals("cust.custCode")||sortFlag.equals("cust.name")||
 			sortFlag.equals("contact.firstName")||sortFlag.equals("jobc.custRefNum")||
 			sortFlag.equals("jobc.invoiceValue5")||sortFlag.equals("ship.name")||
 			sortFlag.equals("ship.phone")||sortFlag.equals("tco.name")||
 			sortFlag.equals("tco.phone")||sortFlag.equals("contract.description")||
 			sortFlag.equals("contract.status")||sortFlag.equals("job.createdByUserName")||
 			sortFlag.equals("jobDescription")||sortFlag.equals("job.updatedByUserName")||
 			sortFlag.equals("joblog.coordinator")|| sortFlag.equals("joblog.inspector")|| 
 			sortFlag.equals("joblog.dispatchTz")|| sortFlag.equals("blconct.firstName")||
 			sortFlag.equals("jobc.billStatus")||sortFlag.equals("jobcinvc.invoice")||
 			sortFlag.equals("jobc.creditInd")||sortFlag.equals("joblog.processTz")||
 			sortFlag.equals("job.buName")||sortFlag.equals("job.jobStatus")
  			))
	 	 {
	   	  if(mapStrValue != null && !mapStrValue.trim().equals("nullnull") && !mapStrValue.trim().equals(""))
	   	  { 
		      	 //map.put(addJobCon, mapStrValue);
	   		  	 map.put(i, mapStrValue);
	   		  	 objMap.put(i, addJobCon);
			     al.add(mapStrValue);
	   	  }else
	   	  {
	   		  emptyDataResults.add(addJobCon); 
	   	  }
	 	}
	   	 if(sortFlag != null && (sortFlag.equals("job.etaDate") ||sortFlag.equals("job.jobFinishDate")||sortFlag.equals("joblog.inspectorNotifyDt")||
	   		sortFlag.equals("joblog.inspectorArriveDt")||sortFlag.equals("joblog.arriveDt")||sortFlag.equals("joblog.dockDt")||
	   		sortFlag.equals("joblog.hoseOnDt")||sortFlag.equals("joblog.estStartDt")||sortFlag.equals("joblog.commenceDt")||
	   		sortFlag.equals("joblog.delayDt")||sortFlag.equals("joblog.delayEndDt")||sortFlag.equals("joblog.estCompleteDt")||
	   		sortFlag.equals("joblog.completeDt")||sortFlag.equals("joblog.hoseOffDt")||
	   		sortFlag.equals("joblog.summaryDt")||sortFlag.equals("jobc.invoiceDate")||sortFlag.equals("jobc.invoiceDate")|| 
	   		sortFlag.equals("joblog.sampleReceiveDt")||sortFlag.equals("joblog.prelimReportDt")||
	   		sortFlag.equals("joblog.sampleShipDt")||sortFlag.equals("joblog.finalReportDt")||sortFlag.equals("joblog.distributeDt")|| sortFlag.equals("joblog.releaseDt")||sortFlag.equals("jobcinvc.generatedDateTime")|| sortFlag.equals("job.reOpenDate")))
	   		 
		  {
		   	  if(mapDtValue != null)
		   	  { 
			      	 map.put(i, mapDtValue);
			      	 objMap.put(i, addJobCon);
				     al.add(mapDtValue);
		   	  }else
		   	  {
		   		  emptyDataResults.add(addJobCon); 
		   	  }
		 }
	   	if(sortFlag != null && (sortFlag.equals("contact.id")||sortFlag.equals("blconct.id")))
	   	{
	   	  if(mapLValue != 0)
	   	  { 
	   		  mapLValue = Long.valueOf(mapLValue);
		     // map.put(addJobCon, mapLValue);
	   		     map.put(i, mapLValue);
	   		     objMap.put(i, addJobCon);
			     al.add(mapLValue);
	   	  }else
	   	  {
	   		  emptyDataResults.add(addJobCon); 
	   	  }
	   	}
		if(sortFlag != null && (sortFlag.equals("jobc.invoiceAmt")))
	   	{
	   	  if(mapDblValue != null)
	   	  {
	   		  	 map.put(i, mapDblValue);
	   		  	 objMap.put(i, addJobCon);
			     al.add(mapDblValue);
	   	  }else
	   	  {
	   		  emptyDataResults.add(addJobCon); 
	   	  }
	   	}
	   }
	   
	   if(multiSort)
	   {
		   for( int j=0;j<emptyDataResults.size();j++)
		   {
		   	AddJobContract addJobCon = (AddJobContract)emptyDataResults.get(j);
		   	finalResults.add(addJobCon);
		   }
	   }
	   //Collections.sort(al);
	   // Setting Sort Order on 03/02/09
	   if(sortOrder != null && sortOrder.trim().equals("asc")){		   
		   Collections.sort(al);
	   } else if(sortOrder != null && sortOrder.trim().equals("desc")){
		   Collections.sort(al,Collections.reverseOrder());  
	   } else {
		   Collections.sort(al);
	   }//End
	   results.clear();
	   if(sortFlag != null && (sortFlag.equals("job.etaDate") ||sortFlag.equals("job.jobFinishDate")||
		   sortFlag.equals("joblog.inspectorNotifyDt")||sortFlag.equals("joblog.inspectorArriveDt")||
		   sortFlag.equals("joblog.arriveDt")||sortFlag.equals("joblog.dockDt")||
	   	   sortFlag.equals("joblog.hoseOnDt")||sortFlag.equals("joblog.estStartDt")||
	   	   sortFlag.equals("joblog.commenceDt")||sortFlag.equals("joblog.delayDt")||
	   	   sortFlag.equals("joblog.delayEndDt")||sortFlag.equals("joblog.estCompleteDt")||
	   	   sortFlag.equals("joblog.completeDt")||sortFlag.equals("joblog.hoseOffDt")||
	   	   sortFlag.equals("joblog.summaryDt")||sortFlag.equals("jobc.invoiceDate")||
	   	   sortFlag.equals("jobc.invoiceDate")||sortFlag.equals("joblog.sampleReceiveDt")||
	   	   sortFlag.equals("joblog.prelimReportDt")||sortFlag.equals("joblog.sampleShipDt")||
	   	   sortFlag.equals("joblog.finalReportDt")||sortFlag.equals("joblog.distributeDt")||
	   	   sortFlag.equals("joblog.releaseDt")||sortFlag.equals("jobcinvc.generatedDateTime")|| sortFlag.equals("job.reOpenDate")))
	   {
		   for (int i=0; i<al.size(); i++)
			{
				 Date date = (Date)al.get(i);
				 List mapKeys = new ArrayList(map.keySet());
				 List dupMapKeys = new ArrayList(objMap.keySet());
				 for(int j=0;j<mapKeys.size();j++)
				 {
					  boolean haswhere=true;
				      if(mapKeys.get(j)!= null)
				      {
				          if(date.equals(map.get(mapKeys.get(j))))
				          {
				            if(haswhere)
				            {
				            	finalResults.add(objMap.get(mapKeys.get(j)));
				            	map.remove(mapKeys.get(j));
				            	objMap.remove(dupMapKeys.get(j));
				            	haswhere = false;
					        }
				          }
				      }
				  }
			}
	  
	   }else if(sortFlag != null && (sortFlag.equals("contact.id")||sortFlag.equals("blconct.id")))
	   {
	   	 for (int i=0; i<al.size(); i++)
		 {
		  long lngValue = ((Long)al.get(i)).longValue();
		  List mapKeys = new ArrayList(map.keySet());
		  List dupMapKeys = new ArrayList(objMap.keySet());
	      for(int j=0;j<mapKeys.size();j++)
	      {
	    	  boolean haswhere=true;
	          if(mapKeys.get(j)!= null)
	          {
		          if(lngValue == ((Long)map.get(mapKeys.get(j))).longValue())
		          {
		            if(haswhere)
		            {
		            	finalResults.add(objMap.get(mapKeys.get(j)));
		            	objMap.remove(dupMapKeys.get(j));
		            	map.remove(mapKeys.get(j));
		            	haswhere = false;
			        }
		          }
	          }
	      }
		}
	   }else if(sortFlag != null && (sortFlag.equals("jobc.invoiceAmt"))){
		   
		   	for (int i=0; i<al.size(); i++)
			{
			  double dblValue = ((Double)al.get(i)).doubleValue();
			  List mapKeys = new ArrayList(map.keySet());
			  List dupMapKeys = new ArrayList(objMap.keySet());
		      for(int j=0;j<mapKeys.size();j++)
		        {
		    	  boolean haswhere=true;
		          if(mapKeys.get(j)!= null)
		          {
			          if(dblValue ==((Double)map.get(mapKeys.get(j))).doubleValue())
			          {
			            if(haswhere)
			            {
			            	//finalResults.add(mapKeys.get(j));
			            	finalResults.add(objMap.get(mapKeys.get(j)));
			            	objMap.remove(dupMapKeys.get(j));
			            	map.remove(mapKeys.get(j));
			            	
			            	haswhere = false;
				        }
			          }
		          }
		       }
			}
	   }else
	   {
	   	 for (int i=0; i<al.size(); i++)
		 {
		  String name = (String)al.get(i);
		  List mapKeys = new ArrayList(map.keySet());
		  List dupMapKeys = new ArrayList(objMap.keySet());
	      for(int j=0;j<mapKeys.size();j++)
	        {
	    	  boolean haswhere=true;
	          if(mapKeys.get(j)!= null)
	        {
	          if(name.equals(map.get(mapKeys.get(j))))
	          {
	            if(haswhere)
	            {
	            	finalResults.add(objMap.get(mapKeys.get(j)));
	            	objMap.remove(dupMapKeys.get(j));
	            	map.remove(mapKeys.get(j));
	            	haswhere = false;
		        }
	          }
	        }
	      }
		}
	   }
	
	 if(!multiSort)
	 {
		
	   for( int j=0;j<emptyDataResults.size();j++)
	   {
	   	AddJobContract addJobCon = (AddJobContract)emptyDataResults.get(j);
	   	finalResults.add(addJobCon);
	   }
	 }
	return finalResults;
}

    public static List<DepositInvoice> sortDepositInvoice(Set<DepositInvoice> depInvSet){
        
        if(depInvSet!=null){
            List<DepositInvoice> lst = new ArrayList<DepositInvoice>(depInvSet); 
            Comparator myComp = new Comparator() {
                public int compare(Object o1, Object o2) {
                    try {
                        DepositInvoice f1 = (DepositInvoice) o1;
                        DepositInvoice f2 = (DepositInvoice) o2;
                        Long n1 = Long.valueOf(f1.getSortNumber());
                        Long n2 = Long.valueOf(f2.getSortNumber());;
                        return n1.compareTo(n2);
                    }
                    catch (Exception e) {
                        // e.printStackTrace();
                    }
                    return -1;
                }
            };
            Collections.sort(lst, myComp);
            
            
            return lst;
        }
        else{
            return null;
        }        
    }

}
