package com.intertek.web.controller.contract;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.ContractSearch;
import com.intertek.entity.Contract;
import com.intertek.entity.DropDowns;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ContractService;
import com.intertek.service.DropdownService;
import com.intertek.util.Constants;

public class SearchContractFormController extends SimpleFormController
{
  public SearchContractFormController() {
    super();
    setCommandClass(ContractSearch.class);
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
  ContractSearch search = (ContractSearch)command;
  ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
  DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

    Contract contract = new Contract();


    String pageNumberStr = request.getParameter("pageNumber");
    String sortFlag = request.getParameter("sortFlag");
  String cxcel=request.getParameter("cxcel");
  String submitFlag=request.getParameter("submitFlag");

  if(sortFlag != null) search.setSortFlag(sortFlag);


  //List results = new ArrayList();
  //List finalResults = new ArrayList();

  //search.setPagination(null);

  int pageNumber;
  if(pageNumberStr == null || pageNumberStr.trim().equals(""))
    pageNumber = 1;

  pageNumber = Integer.parseInt(pageNumberStr);
  Pagination pagination = new Pagination(1,20,pageNumber,10);
  search.setPagination(pagination);
  if(sortFlag != null && !sortFlag.trim().equals(""))
  {
  //if(pageNumberStr.equals("1"))
  //{
      try
        {
         contractService.searchContract(search,sortFlag);
        }
        catch(Exception e)
        {
        e.printStackTrace();
        errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
        return showForm(request, response, errors);
        }
      //}
       // results = search.getTotalResults();
        //results = getPaginationResults(results,search,pageNumberStr);
      //search.setResults(results);
          return showForm(request, response, errors);
      }
 if((submitFlag!=null) && "true".equals(submitFlag))
  {
    /*int pageNumber=-1;

    contractService.searchContract(search,sortFlag);
    results = search.getResults();
    search.setSubmitFlag("none");
    search.setTotalResults(results);
    results = search.getTotalResults();
    results = getPaginationResults(results,search,pageNumberStr);
    search.setResults(results); */
   search.setSubmitFlag("none");
      try
        {
         contractService.searchContract(search,sortFlag);
        }
        catch(Exception e)
        {
        e.printStackTrace();
        errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
        return showForm(request, response, errors);
        }
      }

if((cxcel!=null) && "true".equals(cxcel))
  {
    List  contracts=new ArrayList();
  //int pageNumber=-1;
  search.setPagination(null);
   try
    {
     contractService.searchContract(search,sortFlag);
    }
    catch(Exception e)
    {
    e.printStackTrace();
    errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
    return showForm(request, response, errors);
    }
  //contracts=search.getTotalResults();
  contracts=search.getResults();
  if(contracts!=null&&contracts.size()!=0)
  {
  try
    {
    response.setContentType("application/download");
    response.setHeader("Content-Disposition", "attachment; filename=\"contracts.xls\"");
    response.setHeader("Pragma", "public");
    response.setHeader("Cache-Control", "max-age=0");


    HSSFWorkbook workBook = new HSSFWorkbook();
    HSSFSheet main=null;

    main=workBook.createSheet("Contract");
    HSSFRow   row ;
    HSSFCell  cell;
    HSSFRow headerRow;
    HSSFCell headerCell ;
    PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
    String path="";
      if(pRB!= null)
      {
        int  l=0;
        for(int k=0;k<=Constants.ConColumnSize;k++)
        {
        path=pRB.getString("ConHeading"+k);
        headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
        l++;
        }
      }


        int j=1;
    for(int i=0;i<contracts.size();i++)
    {
    row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((Contract)contracts.get(i)).getContractCode());

        String dropdowntype="contractStatus";
    DropDowns dropDown=new DropDowns();
    dropDown=dropdownService.getDropdownByDropdownCodeAndType(((Contract)contracts.get(i)).getStatus(),dropdowntype);
    if(dropDown!=null)
    {
    //row = main.createRow(j);cell = row.createCell((short) 1);cell.setCellValue(((Contract)contracts.get(i)).getStatus());
    row = main.createRow(j);cell = row.createCell((short) 1);cell.setCellValue(dropDown.getFieldValue());}
    else{cell.setCellValue("");}
    row = main.createRow(j);cell = row.createCell((short) 2);cell.setCellValue(((Contract)contracts.get(i)).getDescription());
    j++;
    }

    OutputStream out = response.getOutputStream();
    workBook.write(out);
    out.flush();
    out.close();
    }
    catch (Exception ioe) {
    System.out.println("Exception..." +  ioe.toString());
    }
  }
  search.setCxcel("false");
  return showForm(request, response, errors);
  }

    request.setAttribute("command", search);
  List myResults=search.getResults();
  if(myResults!=null && myResults.size()==1){
    Contract c=(Contract)myResults.get(0);
    return new ModelAndView(new RedirectView("edit_contract.htm?contractCode="+c.getContractCode()));
  }

    return showForm(request, response, errors);
  }
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
  }
  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    ContractSearch contractSearch = null;
    HttpSession session = request.getSession();
    if(session != null)
    {
      contractSearch = (ContractSearch)session.getAttribute(
        "com.intertek.web.controller.contract.SearchContractFormController.FORM.command"
      );
    }

    if(contractSearch == null) contractSearch = new ContractSearch();

    return contractSearch;
  }

   private List getPaginationResults(List results,ContractSearch search,String pageNumberStr){

    Contract contract = new Contract();

      List finalResults = new ArrayList();

      int pageNumber = 1;
    try {
      pageNumber = new Integer(pageNumberStr).intValue();
    } catch (Exception e) {
    }
    if (pageNumber <= 0)
      pageNumber = 1;

    Pagination contractPagination = new Pagination(1, 20, pageNumber,10);

      search.setPagination(contractPagination);
      if(contractPagination != null)
    {
      if(results.size() > 0){
        contractPagination.setTotalRecord(results.size());
      }
      contractPagination.calculatePages();
    }
      int startRecord=Integer.valueOf(pageNumberStr).intValue();
      int sortedResultsSize = results.size();

      if(startRecord == 1 )
      {
        if(sortedResultsSize >= Constants.RECORDS_PAGE)
        {
          for(int i=0;i<Constants.RECORDS_PAGE;i++)
          {
            contract = (Contract) results.get(i);
            finalResults.add(contract);
          }
        }else
        {
          for(int i=0;i<sortedResultsSize;i++)
          {
            contract = (Contract) results.get(i);
            finalResults.add(contract);
          }
        }
      }else
      {

        int end=startRecord * Constants.RECORDS_PAGE;
        int start=end - Constants.RECORDS_PAGE;
        if(end<=sortedResultsSize)
        {
          for(int i=start;i<end;i++)
          {
            contract = (Contract) results.get(i);
            finalResults.add(contract);
          }
        }else
        {
          start=(startRecord -1)*Constants.RECORDS_PAGE;
          end=sortedResultsSize;
          for(int i=start;i<end;i++)
          {
            contract = (Contract) results.get(i);
            finalResults.add(contract);
          }
        }

      }
      return finalResults;
  }
}
