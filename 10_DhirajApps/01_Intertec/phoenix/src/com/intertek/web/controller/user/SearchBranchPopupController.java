package com.intertek.web.controller.user;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.intertek.exception.ServiceException;
import com.intertek.domain.BranchSearch;
import com.intertek.entity.Branch;
import com.intertek.entity.DropDowns;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.DropdownService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;

public class SearchBranchPopupController extends SimpleFormController
{
  public SearchBranchPopupController() {
    super();
    setCommandClass(BranchSearch.class);
    setSessionForm(true);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  protected ModelAndView onSubmit(
    HttpServletRequest request,
    HttpServletResponse response,
    Object command,
    BindException errors
  ) throws Exception
  {
    String pageNumberStr = request.getParameter("pageNumber");
    String searchType = request.getParameter("searchType");
    String searchForm = request.getParameter("formName");
  String sortFlag = request.getParameter("sortFlag");
  String bxcel=request.getParameter("bxcel");
  String submitFlag=request.getParameter("submitFlag");
  String limsFlag = request.getParameter("limsFlag");

    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;

    BranchSearch search = (BranchSearch)command;
    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
    search.setPagination(pagination);
    search.setLimsFlag(limsFlag);
    search.setSearchType(searchType);
    //if(search.getSearchForm().equals("jobsForm"))
    if(searchForm == null)
      searchForm = search.getSearchForm();
    if(searchForm != null && searchForm.equals("jobsForm")||searchForm.equals("createJobsInspForm")||
               searchForm.equals("createJobsMarineForm")||searchForm.equals("createJobsOutSourcingForm")||
               searchForm.equals("createJobsAnalyticalServicesForm"))
      search.setSearchForm("jobsForm");
    else if(search.getSearchForm().equals("serviceLocationCreateForm"))
      search.setSearchForm("serviceLocationCreateForm");
    else
    search.setSearchForm(searchForm);
    search.setPageNo(pageNumberStr);
    
    // START : #119240 : 22/06/09
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
	// END : #119240 : 22/06/09

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
  DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");
  /*  Branch branch = new Branch();
  List results = new ArrayList();*/
  if(sortFlag != null && !sortFlag.trim().equals(""))
   {
     /* List finalResults = new ArrayList();

    if(pageNumberStr.equals("1"))
    {*/
      try
          {
        userService.searchBranch(search,sortFlag);
          }
         /* catch(Exception e)
          {
            e.printStackTrace();
              errors.reject("search.branch.error", new Object[] {e.getMessage()}, null);
              return showForm(request, response, errors);
          }*/
      catch(ServiceException e)
        {
          e.printStackTrace();
          errors.reject(e.getMessage(), e.getParams(), null);
          return showForm(request, response, errors);
        }
          catch(Throwable t)
          {
            t.printStackTrace();
            errors.reject("generic.error", new Object[] {t.getMessage()}, null);
            return showForm(request, response, errors);
          }

  }
  /*  results = search.getTotalResults();
    if(search.getSearchForm()!= null && search.getSearchForm().equals("jobsForm")&& pageNumberStr.equals("1"))
    {

      results = SortUtil.sortByBranchColumnHeader(results,sortFlag);
      search.setTotalResults(results);
    }
  results = getPaginationResults(results,search,pageNumberStr);
  search.setResults(results);
    return showForm(request, response, errors);
   }*/
  /*  try
    {
      userService.searchBranch(search,sortFlag);
   }
    catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("search.branch.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/
       if((submitFlag!=null) && "true".equals(submitFlag))
      {
    /*userService.searchBranch(search,sortFlag);
    results = search.getResults();
    search.setSubmitFlag("none");
    search.setTotalResults(results);
    results = search.getTotalResults();
    results = getPaginationResults(results,search,pageNumberStr);
    search.setResults(results);  */
         try
          {
        userService.searchBranch(search,sortFlag);
          }
         /* catch(Exception e)
          {
            e.printStackTrace();
              errors.reject("search.branch.error", new Object[] {e.getMessage()}, null);
              return showForm(request, response, errors);
          }*/
      catch(ServiceException e)
        {
          e.printStackTrace();
          errors.reject(e.getMessage(), e.getParams(), null);
          return showForm(request, response, errors);
        }
          catch(Throwable t)
          {
            t.printStackTrace();
            errors.reject("generic.error", new Object[] {t.getMessage()}, null);
            return showForm(request, response, errors);
          }
        search.setSubmitFlag("none");
     }
  /*results = search.getTotalResults();
  search.setTotalResults(results);*/


    if((bxcel!=null) && "true".equals(bxcel))
  {
    List  branches=new ArrayList();
  search.setPagination(null);
  //branches=search.getTotalResults();
   try
     {
    userService.searchBranch(search,sortFlag);
     }
   /*  catch(Exception e)
     {
      e.printStackTrace();
         errors.reject("search.branch.error", new Object[] {e.getMessage()}, null);
         return showForm(request, response, errors);
     }*/
   catch(ServiceException e)
        {
          e.printStackTrace();
          errors.reject(e.getMessage(), e.getParams(), null);
          return showForm(request, response, errors);
        }
          catch(Throwable t)
          {
            t.printStackTrace();
            errors.reject("generic.error", new Object[] {t.getMessage()}, null);
            return showForm(request, response, errors);
          }
     branches=search.getResults();
  if(branches!=null&&branches.size()!=0)
  {
  try
    {
    response.setContentType("application/download");
    response.setHeader("Content-Disposition", "attachment; filename=\"branches.xls\"");
    response.setHeader("Pragma", "public");
    response.setHeader("Cache-Control", "max-age=0");

    HSSFWorkbook workBook = new HSSFWorkbook();
    HSSFSheet main=null;

    main=workBook.createSheet("Branch");
    HSSFRow   row ;
    HSSFCell  cell;
    HSSFRow headerRow;
    HSSFCell headerCell ;
    PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
    String path="";
      if(pRB!= null)
      {
        int  l=0;
        for(int k=0;k<=Constants.BrColumnSize;k++)
        {
        path=pRB.getString("BrHeading"+k);
        headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
        l++;
        }
      }
        int j=1;
    for(int i=0;i<branches.size();i++)
    {
    row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((Branch)branches.get(i)).getBusinessUnit().getName());
    row = main.createRow(j);cell = row.createCell((short) 1);cell.setCellValue(((Branch)branches.get(i)).getName());
        row = main.createRow(j);cell = row.createCell((short) 2);
    if(((Branch)branches.get(i)).getDescription()!=null && !((Branch)branches.get(i)).getDescription().trim().equals(""))
    {cell.setCellValue(((Branch)branches.get(i)).getDescription());}
    else{cell.setCellValue("");}

    String dropdowntype="activeStatus";
    DropDowns dropDown=null;
    dropDown=dropdownService.getDropdownByDropdownCodeAndType(((Branch)branches.get(i)).getStatus(),dropdowntype);
    if(dropDown!=null)
    {
    row = main.createRow(j);cell = row.createCell((short) 3);cell.setCellValue(dropDown.getFieldValue());}
        else{cell.setCellValue("");}
    j++;
    }
    OutputStream out = response.getOutputStream();
    workBook.write(out);
    out.flush();
    out.close();
    }
    /*catch (Exception ioe) {
    System.out.println("Exception..." +  ioe.toString());
    }*/
    catch(ServiceException e)
        {
          e.printStackTrace();
          errors.reject(e.getMessage(), e.getParams(), null);
          return showForm(request, response, errors);
        }
          catch(Throwable t)
          {
            t.printStackTrace();
            errors.reject("generic.error", new Object[] {t.getMessage()}, null);
            return showForm(request, response, errors);
          }
  }
  search.setBxcel("false");
      return showForm(request, response, errors);
    }

    request.setAttribute("command", search);

    return showForm(request, response, errors);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
  }
  protected boolean suppressValidation(HttpServletRequest request)
  {
  String searchType = request.getParameter("searchType");
    if((searchType != null) && ("opsBranch".equals(searchType) || "labBranch".equals(searchType) || searchType.startsWith("assoc")))
    {
      return true;
    }

    return super.suppressValidation(request);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    int pageNumber = 1;
  List results = new ArrayList();
    String sortFlag ="";
    String pageNumberStr = request.getParameter("pageNumber");
    String branchType = request.getParameter("branchtype");
    String inputFieldId = request.getParameter("inputFieldId");
    String divName1 = request.getParameter("div1");
    String divName2 = request.getParameter("div2");
    String buName = request.getParameter("buName");
    String searchForm = request.getParameter("formName");
    String limsFlag = request.getParameter("limsFlag");

    String excludeAdminTypeStr = request.getParameter("excludeAdminType");
    String excludeEmptyTypeStr = request.getParameter("excludeEmptyType");

    BranchSearch branchSearch = new BranchSearch();

    boolean excludeAdminType = false;
    try
    {
      excludeAdminType = new Boolean(excludeAdminTypeStr).booleanValue();
    }
    catch(Exception e)
    {
    }
    branchSearch.setExcludeAdminType(excludeAdminType);

    boolean excludeEmptyType = false;
    try
    {
      excludeEmptyType = new Boolean(excludeEmptyTypeStr).booleanValue();
    }
    catch(Exception e)
    {
    }
    branchSearch.setExcludeEmptyType(excludeEmptyType);

    branchSearch.setPageNo("1");
  if(searchForm != null && searchForm.trim().equals("serviceLocationCreateForm"))
    {
    branchSearch.setSearchForm(searchForm);
    branchSearch.getBusinessUnit().setValue(SecurityUtil.getUser().getBranch().getBusinessUnit().getName());
    branchSearch.getStatus().setValue(Constants.STATUS_A);
    }else if(searchForm != null && (searchForm.equals("jobsForm")||searchForm.equals("createJobsInspForm")||
     searchForm.equals("createJobsMarineForm")||searchForm.equals("createJobsOutSourcingForm")||
     searchForm.equals("createJobsAnalyticalServicesForm")))
     {
       // branchSearch.setSearchForm("jobsForm");
      branchSearch.setSearchForm(searchForm);
          branchSearch.getBusinessUnit().setValue(buName);
     branchSearch.getStatus().setValue(Constants.STATUS_A);
   } else if(searchForm != null && searchForm.equals("opInstrForm"))
   {
       // branchSearch.setSearchForm("jobsForm");
      	  branchSearch.setSearchForm(searchForm);
      	  // For iTrack Issue # 117812 START on 19-06-09
          //branchSearch.getBusinessUnit().setValue(buName);
          //For iTrack Issue # 117812 END on 19-06-09
          branchSearch.getStatus().setValue(Constants.STATUS_A);
          branchSearch.setLimsFlag(limsFlag);
   }
   else
   {
     branchSearch.setSearchForm(searchForm);
     branchSearch.getBusinessUnit().setValue(buName);
     branchSearch.getStatus().setValue(Constants.STATUS_A);
   }
    branchSearch.setSearchType(branchType);
      branchSearch.setInputFieldId(inputFieldId);
      branchSearch.setDivName1(divName1);
      branchSearch.setDivName2(divName2);
      branchSearch.setSearchForm(searchForm);
      Pagination pagination = new Pagination(1, 20, pageNumber, 10);
      branchSearch.setPagination(pagination);
    branchSearch.setInputFieldId(inputFieldId);

     UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
  /*try
    {
    userService.searchBranch(branchSearch,sortFlag);
    }
      catch(Exception e)
      {
      e.printStackTrace();
      }*/
  try{
         userService.searchBranch(branchSearch,sortFlag);
  }
  /*catch(Exception e)
     {
         e.printStackTrace();
     }*/
     catch(ServiceException e)
    {
      throw new ServiceException(e.getMessage(), e.getParams(), e);
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return branchSearch;
  }
}
