package com.intertek.phoenix.web.controller.job;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.entity.JobOrder;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.search.SearchKey;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.search.entity.CEJobSearch;
import com.intertek.phoenix.web.controller.AbstractSearchFormController;

/**
 * This class is used to find the jobs from the given search value based on the
 * search type.
 * 
 * @author Patni
 * 
 */
public class SearchCEJobPopupFormController extends AbstractSearchFormController {

    public SearchCEJobPopupFormController() {
        super();
        setCommandClass(CEJobSearch.class);
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        CEJobSearch search = (CEJobSearch) command;
        request.setAttribute("command", search);

        return showForm(request, response, errors);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    @SuppressWarnings("unchecked")
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        SearchService searchService = ServiceManager.getSearchService();
        String searchValue = request.getParameter("searchValue");
        String searchType = request.getParameter("searchString");
        Object obj = searchService.quickJobSearch(SearchKey.valueOf(searchType), searchValue);
        List<CEJobOrder> jobsCeList = new ArrayList<CEJobOrder>();
        List<JobOrder> jobsOcaList = new ArrayList<JobOrder>();
        CEJobSearch searchForm = new CEJobSearch();

        if (obj != null) {
            if (obj instanceof CEJobOrder) {
                jobsCeList.add((CEJobOrder) obj);
                searchForm.setResults(jobsCeList);
            }
            else if (obj instanceof JobOrder) {
                jobsOcaList.add((JobOrder) obj);
                searchForm.setResults(jobsOcaList);
            }
        }
        return searchForm;
    }
}
