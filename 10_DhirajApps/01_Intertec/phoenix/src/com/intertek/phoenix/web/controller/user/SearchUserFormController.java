package com.intertek.phoenix.web.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.export.Exporter;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.search.Search;
import com.intertek.phoenix.search.entity.UserSearch;
import com.intertek.phoenix.web.controller.AbstractSearchFormController;
import com.intertek.service.AdminService;

/**
 * 
 * This class has been significantly modified to meet the new com.intertek.phoenix standard.
 * 
 * @author richard.qin
 */
public class SearchUserFormController extends AbstractSearchFormController {
    private static final Logger log = Logger.getLogger(SearchUserFormController.class);
    
    public SearchUserFormController() {
        super();
        setCommandClass(UserSearch.class);
        // START : #119240 : 19/06/09
        setSessionForm(true);
        // END : #119240 : 19/06/09
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax
     * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, 
                                    Object command, BindException errors) throws Exception {
        UserSearch search = (UserSearch) command;

        String pageNumberStr = request.getParameter("pageNumber");
        String sortFlag = request.getParameter("sortFlag");
        String uxcel = request.getParameter("uxcel");
        String submitFlag = request.getParameter("submitFlag");

        // setting up pagination
        Pagination pagination = doPagination(pageNumberStr);
        search.setPagination(pagination);

        // START : #119240 : 19/06/09
        
        String pageSort = request.getParameter("checkPageSort");
        
        boolean changeSortOrder = false; 
        if(pageNumberStr != null && Integer.valueOf(pageNumberStr).intValue() == 1){
        	if(sortFlag != null && !sortFlag.trim().equals("") && pageSort.equals("")){
        		changeSortOrder = true;
        	}else if(sortFlag == null || sortFlag.trim().equals("")){
        		  search.setSortOrderFlag("");
	  			  search.setCurrentSortFlag("");
	  			  search.setPrevSortFlag("");
        	}
        }
        
        
    	if(pageNumberStr != null && Integer.valueOf(pageNumberStr).intValue() == 1 && changeSortOrder){
    		  
    		  if(sortFlag != null && !sortFlag.trim().equals("")){
    			  search.setCurrentSortFlag(sortFlag);
    			  if(search.getCurrentSortFlag()!= null && search.getCurrentSortFlag().trim().equals(search.getPrevSortFlag())){
    				  search.setSortOrderFlag("desc");
    				  search.setPrevSortFlag("");
    			  } else {
    				  search.setPrevSortFlag(sortFlag);
    				  search.setSortOrderFlag("asc");
    			  }
    		  } else {
    			  search.setSortOrderFlag("");
    			  search.setCurrentSortFlag("");
    			  search.setPrevSortFlag("");
    		  }
    	 }
       	
    	// END : #119240 : 19/06/09
        
        // executing query
        try{
            if (sortFlag != null && !sortFlag.trim().equals("")) {
                doQuery(search, errors);
            }
            else if ("true".equals(submitFlag)) {
                search.setSubmitFlag("none");
                doQuery(search, errors);
            }
            else if ("true".equals(uxcel)) {
                search.setPagination(null);
                doQuery(search, errors);
                Exporter.exportTOCSV(response, search);
                search.setUxcel("false");
            }
            else {
                request.setAttribute("command", search);
            }
        }
        catch (ServiceException e) {
            log.error(e);
            errors.reject(e.getMessage(), e.getParams(), null);
        }
        catch (Throwable t) {
            log.error(t);
            errors.reject("generic.error", new Object[] {t.getMessage()}, null);
        }
        
        // START : #119240 : 22/06/09
        HttpSession session = request.getSession();
        session.setAttribute("userSearch", search);
        
        // START for Itrack note : 27-Jul-2009
        List<User> myResults = search.getResults();
        if(myResults != null && myResults.size() == 1){
       	  User u=(User)myResults.get(0);
          return new ModelAndView(new RedirectView("edit_user.htm?loginName="+u.getLoginName()));
        }
        // END for Itrack note : 27-Jul-2009
        
        // END : #119240 : 22/06/09
        return showForm(request, response, errors);
    }
    
   protected void doQuery(Search<?> search, BindException errors) throws Exception {
        // use the new search framework via AdminServiceImpl2
        AdminService adminService = (AdminService) ServiceLocator.getInstance().getBean("adminService2");
        adminService.searchAdminObject(search);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
     * (javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        // START : #119240 : 19/06/09
    	//return new UserSearch();
    	UserSearch userSearch = null;
    	AdminService adminService = (AdminService) ServiceLocator.getInstance().getBean("adminService2");
    	
    	HttpSession session = request.getSession();
    	String fromEdit = request.getParameter("fromEdit");
    	
    	if(fromEdit == null){
    		session.removeAttribute("userSearch");
    	}
	  	
	  	if(session != null)
	  	{
	  		if(session.getAttribute("userSearch")!=null){
	  			userSearch = (UserSearch)session.getAttribute("userSearch");	
	  			// START for Itrack note : 27-Jul-2009
	  			//adminService.searchAdminObject(userSearch);
	  			if(userSearch.getResults()!=null && userSearch.getResults().size() > 1){
	  				adminService.searchAdminObject(userSearch);	  				
	  			}else{
	  				userSearch.setResults(null);
	  			}
	  			// END for Itrack note : 27-Jul-2009
	  		}
	  	}
	  	if(userSearch == null) userSearch = new UserSearch();
	  	return userSearch;
    	// END : #119240 : 19/06/09
    }
}
