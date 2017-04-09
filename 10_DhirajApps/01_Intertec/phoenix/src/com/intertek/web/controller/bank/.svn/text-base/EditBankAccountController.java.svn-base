package com.intertek.web.controller.bank;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.BankAccountSearch;
import com.intertek.domain.BankAccountSearchResult;
import com.intertek.domain.BranchSearch;
import com.intertek.entity.BankAccount;
import com.intertek.entity.Branch;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.BankService;
import com.intertek.service.UserService;


public class EditBankAccountController extends SimpleFormController{
	
	public EditBankAccountController(){
	    super();
	    setCommandClass(BankAccountSearch.class);
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception{
		BankService bankService=(BankService)ServiceLocator.getInstance().getBean("bankService");
		int numOfGroups=Integer.parseInt(request.getParameter("numOfGroups"));
		boolean success=false;
		for(int i=0; i<numOfGroups; i++){
			String prim=request.getParameter("primary_"+i);
			if(prim!=null){
				String[] keys=prim.split("_;_");
				if(keys.length>3){
					bankService.saveBankAccountPrimary(keys[0], keys[1], keys[2], keys[3]);
					success=true;
				}
			}
		}
		
		BankAccountSearch bs=(BankAccountSearch)command;
		bs.setResults(getPrims(bs.getBU()));
		if(success){
			request.setAttribute("saved_message", "saveSuccessfully");
		}
		
// START : #119240 : 29/06/09
		
		BankAccountSearch bankAccountSearch = (BankAccountSearch)request.getSession().getAttribute("bankAccountSearch");
		String operationType = request.getParameter("operationType");
		String name = null;
		ModelAndView modelAndView = null;
		if(operationType!=null && ("prevBank".equals(operationType)
				|| "nextBank".equals(operationType)
			    || "searchBank".equals(operationType) 
			    || "saveBank".equals(operationType))   )
		{
			String viewName = null;
			if("searchBank".equals(operationType))
		    {
				viewName = "search-bank-account-r";
		    }
			
		    if(viewName == null)
		    {
		    	viewName = "edit-bank-account-r"; 
		    }

		    modelAndView = new ModelAndView(viewName);
			
		    if ("prevBank".equals(operationType) || "nextBank".equals(operationType)) 
		    {
				name = this.getPrevOrNextBank(bs.getBU(), bankAccountSearch,
						"prevBank".equals(operationType));
				if (name == null) {
					if ("prevBank".equals(operationType)) {
						modelAndView.addObject("saved_message",	"No Previous Bank!");
					} else {
						modelAndView.addObject("saved_message",	"No Next Bank!");
					}
				}
			}
		    
		    if("saveBank".equals(operationType))
			{
		    	modelAndView.addObject("saved_message", "Saved successfully");		    	
			}		    
		 }
		
		 if(name == null) 
			 name = bs.getBU();
		 modelAndView.addObject("BU", name);  
		 modelAndView.addObject("fromEdit", "true");
		 
		 /*return showForm(request, response, errors);*/
		 
		 return modelAndView;
	// END : #119240 : 29/06/09
		
	}
	
	protected Object formBackingObject(HttpServletRequest request)  throws Exception {
		String BU=request.getParameter("BU");
		BankAccountSearch bs=new BankAccountSearch();
		bs.setBU(BU);
		bs.setResults(getPrims(BU));
		return bs;
	}
	
	private List<List<BankAccountSearchResult>> getPrims(String BU){
		BankService bankService=(BankService)ServiceLocator.getInstance().getBean("bankService");
		List<BankAccountSearchResult> list=bankService.searchBankAccount(BU);
		
		Hashtable<String, List<BankAccountSearchResult>> buCurrency=new Hashtable<String, List<BankAccountSearchResult>>();
		for(int i=0; i<list.size(); i++){
			BankAccountSearchResult ba=list.get(i);
			String key=ba.getBU()+";"+ba.getCurrencyCode();
			List<BankAccountSearchResult> aList=buCurrency.get(key);
			if(aList==null){
				aList=new ArrayList<BankAccountSearchResult>();
				buCurrency.put(key, aList);
			}
			aList.add(ba);
		}

		List<List<BankAccountSearchResult>> myList=new ArrayList<List<BankAccountSearchResult>>();
		Enumeration<List<BankAccountSearchResult>> em=buCurrency.elements();
		while(em.hasMoreElements()){
			myList.add(em.nextElement());
		}
		
		return myList;
	}
	
	
	//START : #119240 : 29/06/09
	 public String  getPrevOrNextBank(String currentBU, BankAccountSearch bankAccountSearch, boolean prev){
		  
		  if(currentBU == null || bankAccountSearch == null) 
			  return null;
		  
		  List<BankAccountSearchResult> results =  bankAccountSearch.getResults();
		  
		  if(results == null) return null;
		  
		  	List<String> buList = new ArrayList<String>();
			
			for (int i = 0; i < results.size(); i++) {
				BankAccountSearchResult result = (BankAccountSearchResult) results.get(i);
				if(!buList.contains(result.getBU()))
					buList.add(result.getBU());
			}

			for(int i=0; i<buList.size(); i++)
		    {
				String BU = buList.get(i);
				if(BU.equals(currentBU)){
					if(prev){
						if(i==0){
							return null;
						}
						return buList.get(i-1);
					}else{
						if(i == buList.size() - 1)
		    			{
							return null;
		    			}
						return buList.get(i+1);
					}
				}
		    }
		  
		  return null;
	  }
	// END : #119240 : 29/06/09
}
