package com.intertek.phoenix.ajax;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.Product;
import com.intertek.phoenix.search.SearchCriteria;
import com.intertek.phoenix.search.SearchService;

public class AjaxController extends AbstractController {
    public AjaxController() {
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SearchService searchService = ServiceManager.getSearchService();
        String entity = request.getParameter("entity");
        String namedQuery = request.getParameter("namedQuery");
        
        List<?> results=null;
        if(namedQuery!=null && !namedQuery.trim().isEmpty()){
            Object[] params=buildParams(request);
            results=DaoManager.getDao(Class.forName(entity)).searchByNamedQuery(namedQuery, params);
        }
        else{
            List<SearchCriteria> criteria = buildCriteria(request);
            results = searchService.advancedSearch(Class.forName(entity), criteria, null, null);
        }
        
        String xml = "";
        AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();

        //build the result based on entity wrapper or just getters from entity
        AjaxWrapper wrapperObj = null;
        String entityWrapper = request.getParameter("entityWrapper");
        if (entityWrapper != null && !entityWrapper.isEmpty()) {//use entity wrapper
            wrapperObj = (AjaxWrapper) Class.forName(entityWrapper).newInstance();
            for (int i = 0; i < results.size(); i++) {
                wrapperObj.setObject(results.get(i));
                String text = wrapperObj.getText();
                String value = wrapperObj.getValue();
                xmlBuilder.addItem(text, value);
            }
        }
        else {//user getters from entity
            String textAttribute = request.getParameter("textAttribute");
            String valueAttribute = request.getParameter("valueAttribute");
            if (valueAttribute == null || valueAttribute.isEmpty()) {
                valueAttribute = textAttribute;
            }

            for (int i = 0; i < results.size(); i++) {
                Object obj = results.get(i);
                String text = BeanUtils.getProperty(obj, textAttribute);
                String value = BeanUtils.getProperty(obj, valueAttribute);
                xmlBuilder.addItem(text, value);
            }
        }
        
        //return the results
        xml = xmlBuilder.toString();
        Map<String, String> model = new HashMap<String, String>();
        model.put("Content", xml);
        View view = (View) getApplicationContext().getBean("xmlView");
        return new ModelAndView(view, model);
    }

    @SuppressWarnings("unchecked")
    private Object[] buildParams(HttpServletRequest request){
        List<String> list=new ArrayList<String>();
        Enumeration<String> em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = em.nextElement();
            if (name.startsWith("~")) {
                String value = request.getParameter(name);
                list.add(value);
            }
        }        
        return list.toArray();
    }
    
    @SuppressWarnings("unchecked")
    private List<SearchCriteria> buildCriteria(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<SearchCriteria> criteria = new ArrayList<SearchCriteria>();
        Enumeration<String> em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = em.nextElement();
            if (name.startsWith("~")) {
                String value = request.getParameter(name);
                if (value != null && !value.isEmpty()) {
                    criteria.add(new SearchCriteria(name.substring(1), value, "contains"));
                }
            }
        }
        return criteria;
    }
}
