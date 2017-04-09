package com.intertek.web.controller.bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.BankAccountSearch;
import com.intertek.domain.BankAccountSearchResult;
import com.intertek.export.Exporter;
import com.intertek.export.template.BankAccountTemplate;
import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.search.entity.UserSearch;
import com.intertek.service.BankService;

public class SearchBankAccountController extends SimpleFormController{
	
	public SearchBankAccountController(){
	    super();
	    // START : #119240 : 02/07/09
	    setSessionForm(true);
	    // END : #119240 : 02/07/09
	    setCommandClass(BankAccountSearch.class);
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception{
		BankAccountSearch bs=(BankAccountSearch)command;
		BankService bankService= (BankService)ServiceLocator.getInstance().getBean("bankService");
		List<BankAccountSearchResult> list=bankService.searchBankAccount(bs);
		
		boolean exportToCSV="true".equalsIgnoreCase(""+request.getParameter("csv"));
		if(exportToCSV){
			if(exportToCSV(list, response)){
				return null;
			}
		}

		bs.setResults(list);
		// START : #119240 : 02/07/09
		HttpSession session = request.getSession();
		session.setAttribute("bankAccountSearch", bs);
		// START for Itrack note : 27-Jul-2009
	    List<BankAccountSearchResult> myResults = bs.getResults();
	    System.out.println("Search :BANK ACCT List SIZE :--- " + myResults.size() );
	    
		List<String> buList = new ArrayList<String>();
		if(myResults!=null && myResults.size() > 0){
		    for (int i = 0; i < myResults.size(); i++) {
				BankAccountSearchResult result = (BankAccountSearchResult) myResults.get(i);
				if(!buList.contains(result.getBU()))
					buList.add(result.getBU());
			}
		}
	    System.out.println("BU list Size :--- "  + buList.size());
	    bs.setBUListSize(buList.size());
	    if(buList.size() == 1){
	    	return new ModelAndView(new RedirectView("edit_bank_account.htm?BU=" + buList.get(0)));
	    }
	    
	    /*if(myResults != null && myResults.size() == 1){
	    	BankAccountSearchResult b = (BankAccountSearchResult)myResults.get(0);
	    	return new ModelAndView(new RedirectView("edit_bank_account.htm?BU="+ b.getBU()));
	    }*/
	    // END for Itrack note : 27-Jul-2009
		
		// END : #119240 : 02/07/09
		return showForm(request, response, errors);
	}
	
	// START : #119240 : 02/07/09
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		BankAccountSearch bankAccountSearch = null; 
		HttpSession session = request.getSession();
		
		String fromEdit = request.getParameter("fromEdit");
    	if(fromEdit == null){
    		session.removeAttribute("bankAccountSearch");
    	}
	  	
	  	if(session != null)
	  	{
	  		if(session.getAttribute("bankAccountSearch")!=null){
	  			bankAccountSearch = (BankAccountSearch)session.getAttribute("bankAccountSearch");	
	  			BankService bankService= (BankService)ServiceLocator.getInstance().getBean("bankService");
	  			// START for Itrack note : 27-Jul-2009
	  			/*List<BankAccountSearchResult> list=bankService.searchBankAccount(bankAccountSearch);
	  			bankAccountSearch.setResults(list);*/
	  			
	  			if(bankAccountSearch !=null && bankAccountSearch.getBUListSize() > 1 ){
	  				List<BankAccountSearchResult> list=bankService.searchBankAccount(bankAccountSearch);
		  			bankAccountSearch.setResults(list);
	  			}else{
	  				bankAccountSearch.setResults(null);
	  			}
	  			
	  			/*if(bankAccountSearch.getResults()!=null && bankAccountSearch.getResults().size() > 1){
	  				List<BankAccountSearchResult> list=bankService.searchBankAccount(bankAccountSearch);
		  			bankAccountSearch.setResults(list);
	  			}else{
	  				bankAccountSearch.setResults(null);
	  			}*/
	  			System.out.println("ACCT Code : ------ " + bankAccountSearch.getAccountCode());
				// END for Itrack note : 27-Jul-2009
	  		}
	  	}
	  	if(bankAccountSearch == null) bankAccountSearch = new BankAccountSearch();
	  	return bankAccountSearch;
		
	}
	// END : #119240 : 02/07/09

	protected boolean exportToCSV(List<BankAccountSearchResult> list, HttpServletResponse response)throws IOException{
		BankAccountTemplate template=new BankAccountTemplate(list);
		return Exporter.exportTOCSV(response, template);
	}
}
