package com.intertek.web.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Organization;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.UserService;
import com.intertek.util.SecurityUtil;
import com.intertek.util.Constants;

public class BranchController extends AbstractController
{
  private static Log log = LogFactory.getLog(BranchController.class);

  /**
   * .ctor
   */
  public BranchController() {
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
	 UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

	  String assocBranchName = "";
	  if(request.getParameter("assocbranch.name") != null && (!request.getParameter("assocbranch.name").trim().equals("")))
	  {
		  assocBranchName = request.getParameter("assocbranch.name");

		  List branch = userService.searchBranchByName(assocBranchName);

  		  String xml = new AjaxXmlBuilder().addItems(branch, "description", "description").toString();

		  Map model = new HashMap();
		  model.put("Content", xml);

		  View view = (View)getApplicationContext().getBean("xmlView");

		  return new ModelAndView(view, model);
	  }

    String branchType = request.getParameter("branchType");
	log.info("inside branchController : branchType"+branchType);
	String branchName = "";
	String index = "";
	String buName = "";
	if(branchType != null)
	  {
	if(branchType.trim().equals(Constants.OPS_BRANCH))
		branchName = request.getParameter("branch.opsBranchName");
	else if(branchType.trim().equals(Constants.LAB_BRANCH))
		branchName = request.getParameter("branch.labBranchName");
	else if(branchType.trim().startsWith(Constants.ASSOC))
	  {
		index = request.getParameter("index");
		String paramName = "assocBranches["+index+"].assocBranchId.assocBranchName";
		branchName = request.getParameter(paramName);
	  }
	buName = request.getParameter("buName");
	  }
	log.info("inside branchController : branchName "+branchName);

    List branches = userService.getBranchesByBusinessUnitNameAndBranchName(buName,branchName);
	String xml = "";
    log.info("======== in BranchController: branches = " + branches);
	if(branchType != null)
	  {
	if(branchType.trim().startsWith(Constants.ASSOC))
	  {
		String fieldName = request.getParameter("fieldname");
			//xml = new AjaxXmlBuilder().addItems(branches, "name", "description").toString();
		xml = new AjaxXmlBuilder().addItems(branches, Constants.name, fieldName).toString();
	  }
	  else
	  {
		xml = new AjaxXmlBuilder().addItems(branches, Constants.name, Constants.name).toString();
		log.info("======== in BranchController: xml = " + xml);
		  }
	 }//Service location regd code
	   if(request.getParameter("name") != null && (!request.getParameter("name").trim().equals("")))
	  {

	     String brchName = request.getParameter("name");
	     List  brch = new ArrayList();

	   /* String brchName ="";
	     List  brch= new ArrayList();
		 List organization = new ArrayList();
		 List bu = new ArrayList();
		 List branch = new ArrayList();

		  brchName = request.getParameter("name");

          String divisionName = SecurityUtil.getUser().getBranch().getBusinessUnit().getOrganization().getName();
		  organization = userService.singleOrganizationByDivisionName(divisionName);

		  Object orgObj = organization.get(0);

		  Organization org =(Organization) organization.get(0);
		  int buSize = (org.getBusinessUnits()).size();

		  for(Iterator itrOrg = (org.getBusinessUnits()).iterator(); itrOrg.hasNext();)
		  {
				 BusinessUnit bunits = (BusinessUnit)itrOrg.next();
				 for(Iterator itrBu =(bunits.getBranches()).iterator(); itrBu.hasNext();)
			  {
				   branch.add(itrBu.next());
			  }
		  }

		  branchName=SecurityUtil.getUser().getBranch().getBusinessUnit().getName();

		  brch = userService.searchBranchByNameAndBranches(branchName,branch);*/
	      String bu = SecurityUtil.getUser().getBranch().getBusinessUnit().getName();

	      brch = userService.searchBranchesByNameAndBu(brchName,bu);
	      AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
	      if(brch != null)
	      {
		      for(int i=0;i<brch.size();i++)
		  	  {
		    	Branch branch=(Branch)brch.get(i);
		  		if(branch.getDescription() == null){
		  			branch.setDescription("");
		  		}
		  		String barnchName = branch.getName() + "," + branch.getDescription();

		  		xmlBuilder.addItem(barnchName,barnchName);
		  	  }
	      }
	      xml = xmlBuilder.toString();
  	     // xml = new AjaxXmlBuilder().addItems(brch, "name", "name").toString();
	  }
	  //code end
	   if(request.getParameter("branchName") != null && (!request.getParameter("branchName").trim().equals(""))&& request.getParameter("buName") != null && (!request.getParameter("buName").trim().equals("")))
		  {
		   	  buName = request.getParameter("buName");
		   	  branchName=request.getParameter("branchName");
			 /* For Itrack Issue # 117812 - STARTS 17 Jun 2009*/
			  // List branch = userService.getBranchesByBusinessUnitNameAndBranchName(buName,branchName);
			  List branch = userService.getJobBranchesByBusinessUnitNameAndBranchName(buName,branchName);
			  /* For Itrack Issue # 117812 - END 17 Jun 2009*/
			 // List branch = userService.getBranchesByBusinessUnitNameAndBranchName(buName,branchName);
			  //List branch = userService.getJobBranchesByBusinessUnitNameAndBranchName(buName,branchName);
			  AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();

			    if(branch!= null && branch.size() > 0)
			    {
			    	for(int i=0;i<branch.size();i++)
			    	{
							  Branch branchObj = (Branch) branch.get(i);
							  String displayVal="";
				              if(branchObj.getDescription()!=null && !branchObj.getDescription().equals(""))
				              {
							  String branchDesc = branchObj.getDescription();
							  if(branchDesc.contains("&"))
							  {branchDesc=branchDesc.replace("&","&amp;");}
							  displayVal = branchObj.getName() + " - " + branchDesc;
							  }
							  else
						      {displayVal = branchObj.getName();}
							  xmlBuilder.addItem(displayVal,branchObj.getName());
					}
			    }
			    xml = xmlBuilder.toString();
	  		// xml = new AjaxXmlBuilder().addItems(branch, Constants.name, Constants.name).toString();

			 Map model = new HashMap();
			  model.put("Content", xml);

			  View view = (View)getApplicationContext().getBean("xmlView");

			  return new ModelAndView(view, model);
		  }


	   if(request.getParameter("branchSearch") != null && (!request.getParameter("branchSearch").trim().equals(""))&& (request.getParameter("branchSearch").trim().equals("prebill")))
		  {

		   	  branchName=request.getParameter("branchName");
		   	  String primaryBranchName = request.getParameter("primaryBranch");
		   	  String pbBranchType = request.getParameter("branchType");

			  Branch branch = userService.getBranchByName(primaryBranchName);

			  //BusinessUnit bu = userService.getBusinessUnitByName(branch.getBuName());
			 // List branches = userService.getBranchesByDivisionAndBusinessUnitName(bu.getOrgName(), branch.getBuName());

			  List branchlist = userService.getBranchesByBusinessUnitNameAndBranchName(branch.getBuName(),branchName);
			  List updatedBranchList = new ArrayList();

			  if(branchlist != null && branchlist.size() > 0)
			  {
				  for(int i=0;i<branchlist.size();i++)
				  {
					  Branch branchItem = (Branch) branchlist.get(i);
					  if(branchItem.getName().trim().equals(primaryBranchName))
					  {
						  branchlist.remove(branchItem);
					  }
					  if(branchItem.getType() == null)
						  branchlist.remove(branchItem);
					  else
					  {
						 if(pbBranchType.trim().equalsIgnoreCase(Constants.OPS))
						 {
						  if((branchItem.getType().trim().equalsIgnoreCase(pbBranchType)) || (branchItem.getType().trim().equalsIgnoreCase(Constants.OPSLAB))
								  || (branchItem.getType().trim().equalsIgnoreCase(Constants.OPSC)))
							  updatedBranchList.add(branchItem);
						 }
						 else
						 {
							 if((branchItem.getType().trim().equalsIgnoreCase(pbBranchType)) || (branchItem.getType().trim().equalsIgnoreCase(Constants.OPSLAB)))
								  updatedBranchList.add(branchItem);
						 }
					  }
				  }
			  }

			  AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
			  for(int i=0;i<updatedBranchList.size();i++)
			  {
				  Branch branchObj = (Branch) updatedBranchList.get(i);
				  String branchDesc = branchObj.getDescription();
				  String displayVal = branchObj.getName() + " - " + branchDesc;
				  xmlBuilder.addItem(displayVal,branchObj.getName());
			  }

			 xml = xmlBuilder.toString();

	  		 // xml = new AjaxXmlBuilder().addItems(branchlist, "name", "name").toString();

			  Map model = new HashMap();
			  model.put("Content", xml);

			  View view = (View)getApplicationContext().getBean("xmlView");

			  return new ModelAndView(view, model);
		  }
	   if(request.getParameter("labBranchSearch") != null && (!request.getParameter("labBranchSearch").trim().equals("")))
		  {

		   	  branchName=request.getParameter("labBranchSearch");
		   	  buName=request.getParameter("buName");
			  /* For Itrack Issue # 117812 - STARTS 17 Jun 2009*/
			  //List branchlist = userService.getLabBranchesByBUAndBranchName(buName,branchName);
		   	  List branchlist = userService.getLabBranchesByBranchName(branchName);
		   	 /* For Itrack Issue # 117812 - END 17 Jun 2009*/
			  //List branchlist = userService.getLabBranchesByBUAndBranchName(buName,branchName);
			  AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
			  for(int i=0;i<branchlist.size();i++)
			  {
				  Branch branchObj = (Branch) branchlist.get(i);
				  xmlBuilder.addItem(branchObj.getName(),branchObj.getName());
			  }

			 xml = xmlBuilder.toString();

	  		 // xml = new AjaxXmlBuilder().addItems(branchlist, "name", "name").toString();

			  Map model = new HashMap();
			  model.put("Content", xml);

			  View view = (View)getApplicationContext().getBean("xmlView");

			  return new ModelAndView(view, model);
		  }

		    Map model = new HashMap();
		    model.put("Content", xml);

		    View view = (View)getApplicationContext().getBean("xmlView");

		    return new ModelAndView(view, model);
  }
}
