package com.intertek.web.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.entity.Branch;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.AutoNumberService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.StringUtil;

public class BusinessUnitController extends AbstractController
{
  private static Log log = LogFactory.getLog(BusinessUnitController.class);

  /**
   * .ctor
   */
  public BusinessUnitController() {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   * <context dynamic="true|false">
       <User Name="jaloia111" />
       <Type Name="MANIFEST_FILE" />
     </context>
   */
  protected ModelAndView handleRequestInternal(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception
  {
    String buName = request.getParameter("branch.businessUnit.name");
    String branchBu = request.getParameter("autoNumberId.numberCategory");
    String busStreamBuName1 = request.getParameter("bus.stream.buname1");
    String busStreamBuName2 = request.getParameter("bus.stream.buname2");
	String businessUnitName ="";
	String xml="";
    log.info("======== in BusinessUnitController: buName = " + buName);

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    AutoNumberService autoNumberService = (AutoNumberService)ServiceLocator.getInstance().getBean("autoNumberService");
    if(branchBu!= null)
    {
	    if(!branchBu.equals(""))
	    {
		    List numcats = autoNumberService.getNumCategoryByBusinessUnit(branchBu);
		     xml = new AjaxXmlBuilder().addItems(numcats, "autoNumberId.numberCategory","autoNumberId.numberCategory").toString();
		     log.info("======== in BusinessUnitController: xml = " + xml);

	    }
    }
    if(buName != null)
    {
	    if(!buName.equals(""))
	    {
	    List branches = userService.getBranchesByBusinessUnitName(buName);
	   	AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();

	    if(branches != null && branches.size() > 0)
	    {

	    	for(int i=0;i<branches.size();i++)
	    	{
					  Branch branchObj = (Branch) branches.get(i);
					  String displayVal="";
					  if(branchObj.getDescription()!=null && !branchObj.getDescription().equals(""))
					  {
					  String branchDesc = branchObj.getDescription();
					  if(branchDesc.contains("&"))
					  {branchDesc=branchDesc.replace("&","&amp;");}
					   displayVal = branchObj.getName() + " - " + branchDesc;
					  }
					  else
					  { displayVal = branchObj.getName();}
					  xmlBuilder.addItem(displayVal,branchObj.getName());
			}
		}
	    xml = xmlBuilder.toString();
	    log.info("======== in BusinessUnitController branches are : xml = " + xml);
	    } else
	    {
	    	AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
	    	xml = xmlBuilder.toString();
	    }
    }


  /*  if(request.getParameter("branchName") != null && (!request.getParameter("branchName").trim().equals(""))&& request.getParameter("buName") != null && (!request.getParameter("buName").trim().equals("")))
	  {

	   	 String branchName=request.getParameter("branchName");
	   	 String buName1 = request.getParameter("buName");

		  List branch = userService.getUserBranchesByBusinessUnitNameAndBranchName(buName1,branchName);
			AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();

		    if(branch!= null && branch.size() > 0)
		    {
		    	for(int i=0;i<branch.size();i++)
		    	{
						  Branch branchObj = (Branch) branch.get(i);
						  String branchDesc = branchObj.getDescription();
						  String displayVal = branchObj.getName() + " - " + branchDesc;
						  xmlBuilder.addItem(displayVal,branchObj.getName());
				}
		    }
		    xml = xmlBuilder.toString();

		  //xml = new AjaxXmlBuilder().addItems(branch, Constants.name, Constants.name).toString();
	  }*/


    if(request.getParameter("customer.interunitBusUnitName") != null )
    {
	  if(!request.getParameter("customer.interunitBusUnitName").trim().equals(""))
	  {
		  businessUnitName = request.getParameter("customer.interunitBusUnitName");

		  List businessUnit = userService.getBusinessUnitsByName(businessUnitName);

  		 xml = new AjaxXmlBuilder().addItems(businessUnit, Constants.name, Constants.name).toString();
	 }
	}

    if(busStreamBuName1 != null || busStreamBuName2 != null )
    {
    	AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
    	String busStreamBuName = "";
    	if(busStreamBuName1 != null && !busStreamBuName1.equals("")){
    		xmlBuilder.addItem("&#32;","1");
    		busStreamBuName = busStreamBuName1;
    	} else {
    		busStreamBuName = busStreamBuName2;
    	}

    	if(!busStreamBuName.equals(""))
	    {
	    List branches = userService.getBranchesByBusinessUnitName(busStreamBuName);

	   //	System.out.println("Setting default branch null");

	    if(branches != null && branches.size() > 0)
	    {

	    	for(int i=0;i<branches.size();i++)
	    	{
			  Branch branchObj = (Branch) branches.get(i);
			  String displayVal="";
			  if(branchObj.getDescription()!=null && !branchObj.getDescription().equals(""))
			  {
			  String branchDesc = branchObj.getDescription();
			  //if(branchDesc.contains("&"))
			  // {	branchDesc=branchDesc.replace("&","&amp;");}
			  // branchDesc = StringUtil.forHTML(branchDesc);
			  displayVal = branchObj.getName() + " - " + branchDesc;
			  }
			  else
			  { displayVal = branchObj.getName();
			  }
			  	xmlBuilder.addItem(displayVal,branchObj.getName());
			}
		}
	    xml = xmlBuilder.toString();
	   // log.info("======== in BusinessUnitController branches are : xml = " + xml);
	    } else
	    {
	    	//AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
	    	xml = xmlBuilder.toString();
	    }
    }


    Map model = new HashMap();
    model.put("Content", xml);

    View view = (View)getApplicationContext().getBean("xmlView");

    return new ModelAndView(view, model);
  }
}
