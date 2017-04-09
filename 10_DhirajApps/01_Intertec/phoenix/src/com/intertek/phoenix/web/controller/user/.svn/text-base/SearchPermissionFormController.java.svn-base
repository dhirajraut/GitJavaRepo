/**
 * 
 */
package com.intertek.phoenix.web.controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.entity.Permission;
import com.intertek.export.Exporter;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.search.Search;
import com.intertek.phoenix.search.entity.PermissionSearch;
import com.intertek.phoenix.web.controller.AbstractSearchFormController;
import com.intertek.service.AdminService;

/**
 * This class has been significantly modified from original to meet the new 
 * com.intertek.phoenix standard.
 *
 * @author richard.qin
 */
public class SearchPermissionFormController extends AbstractSearchFormController{
    public SearchPermissionFormController() {
        super();
        setCommandClass(PermissionSearch.class);
        // START : #119240 : 22/06/09
        setSessionForm(true);
        // END : #119240 : 22/06/09
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax
     * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        PermissionSearch search = (PermissionSearch) command;

        String pageNumberStr = request.getParameter("pageNo");
        String sortFlag = request.getParameter("sortFlag");
        String pxcel = request.getParameter("pxcel");
        String submitFlag = request.getParameter("submitFlag");

        // setting up pagination
        Pagination pagination = doPagination(pageNumberStr);
        search.setPagination(pagination);

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
        
        if (sortFlag != null && !sortFlag.trim().equals("")) {
            doQuery(search, errors);
        }
        else if ((submitFlag != null) && "true".equals(submitFlag)) {
            search.setSubmitFlag("none");
            doQuery(search, errors);
        }
        else if ("true".equals(pxcel)) {
            search.setPagination(null);
            doQuery(search, errors);
            Exporter.exportTOCSV(response, search);
            search.setPxcel("false");
        }
        else{
            request.setAttribute("command", search);
        }
        
        // START : #119240 : 22/06/09
        HttpSession session = request.getSession();
        session.setAttribute("permissionSearch", search);
        
        // START for Itrack note : 27-Jul-2009
        List<Permission> myResults = search.getResults();
        if(myResults != null && myResults.size() == 1){
        	Permission p = (Permission)myResults.get(0);
        	return new ModelAndView(new RedirectView("edit_permission_list.htm?name="+ p.getName()));
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
        // START : #119240 : 22/06/09
    	//return new PermissionSearch();
    	PermissionSearch permissionSearch = null;
    	AdminService adminService = (AdminService) ServiceLocator.getInstance().getBean("adminService2");

    	HttpSession session = request.getSession();
	  	String fromEdit = request.getParameter("fromEdit");
    
    	if(fromEdit == null){
    		session.removeAttribute("permissionSearch");
    	}
    	
	  	if(session != null)
	  	{
	  		if(session.getAttribute("permissionSearch")!=null){
	  			permissionSearch = (PermissionSearch)session.getAttribute("permissionSearch");
	  	        // START for Itrack note : 27-Jul-2009
	  			//adminService.searchAdminObject(permissionSearch);
	  			if(permissionSearch.getResults()!=null && permissionSearch.getResults().size() > 1){
	  				adminService.searchAdminObject(permissionSearch);	
	  			}else{
	  				permissionSearch.setResults(null);
	  			}
	  			// END for Itrack note : 27-Jul-2009
	  		}
	  	}
	  	if(permissionSearch == null) permissionSearch = new PermissionSearch();
	  	return permissionSearch;
    	// END : #119240 : 22/06/09
    
    }
}
