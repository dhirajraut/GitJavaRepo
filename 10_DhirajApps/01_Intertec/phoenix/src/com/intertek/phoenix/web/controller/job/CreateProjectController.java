package com.intertek.phoenix.web.controller.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.Project;
import com.intertek.phoenix.entity.ProjectType;
import com.intertek.phoenix.esb.ESBService;
import com.intertek.phoenix.search.SearchService;

public class CreateProjectController extends AbstractController {
    private ESBService esbService;
    private SearchService searchService;
    
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> model = new HashMap<String, String>();
        try {
            String jobNumber=request.getParameter("jobNumber");
            
            HashMap<String, String> criteria=new HashMap<String, String>();
            criteria.put("jobNumber", jobNumber);
            List<CEJobOrder> list=searchService.search(CEJobOrder.class, criteria);
            CEJobOrder jo = list.get(0);

            String projectType=request.getParameter("projectType");
            if(projectType!=null && !projectType.trim().isEmpty()){
                jo.setProjectType(ProjectType.valueOf(projectType));
            }
            
            Project p = esbService.createOrUpdateProject(jo);
            
            model.put("Content", p.getProjectNumber());
        }
        catch (Exception e) {
            e.printStackTrace();
            model.put("Content", "ERROR");
        }
        View view = (View) getApplicationContext().getBean("xmlView");
        return new ModelAndView(view, model);
    }

    public ESBService getEsbService() {
        return esbService;
    }

    public void setEsbService(ESBService esbService) {
        this.esbService = esbService;
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}
