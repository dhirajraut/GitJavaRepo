package com.intertek.web.controller.job;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class BatchReprintMultiController extends MultiActionController {
	
	public ModelAndView maintainDirtyBack(HttpServletRequest httpRequest, HttpServletResponse httpResponse)throws Exception {
//		Map<String,String> mapInvoiceIds = new HashMap<String,String>();
		String args = (String)httpRequest.getParameter("args");
		String pageNumber = (String)httpRequest.getParameter("pageNumber");
		String[] str = null;
		if(null != args && !"".equals(args)){
			str = args.split(",");
		}
		HttpSession session = httpRequest.getSession();
//		session.setAttribute("map", mapInvoiceIds);
		
		
		Map<String,String> map = (Map)session.getAttribute("map");
		if(null != map && !map.isEmpty()){
			List<String> lst = new ArrayList<String>();
			 Iterator itr = map.entrySet().iterator();
			 while(itr.hasNext()){
				 Map.Entry pairs = (Map.Entry)itr.next();
				 String key = (String)pairs.getKey();
				 String value = (String)pairs.getValue();
				 if(pageNumber.trim().equals(value.trim())){
					 lst.add(key);
				 }
			 }
			 for(int j=0;j<lst.size();j++){
				 String s = map.remove(lst.get(j));
			 }
			 if(null != str && !"".equals(args)){
				 for(int i=0;i<str.length;i++){
					 map.put(str[i], pageNumber);
				 }
			 }
		 }
		else{
			map = new HashMap<String,String>();
			if(null != str && !"".equals(args)){
				for(int i=0;i<str.length;i++){
					map.put(str[i],pageNumber);
				 }
			}
		}
		
		
		session.setAttribute("map", map);
		
		
		
		httpResponse.setContentType("text/xml");
		PrintWriter out=httpResponse.getWriter();
		out.print("flag");
		return null;
	}
}
