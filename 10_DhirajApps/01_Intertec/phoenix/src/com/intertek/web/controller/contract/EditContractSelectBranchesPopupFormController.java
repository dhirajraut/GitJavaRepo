package com.intertek.web.controller.contract;

import java.util.List;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.entity.HighLevelService;
import com.intertek.domain.EditZone;
import com.intertek.domain.BranchSearch;
import com.intertek.domain.SelectBranches;
import com.intertek.domain.ZoneExt;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.UserService;
import com.intertek.service.ZoneService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractSelectBranchesPopupFormController extends SimpleFormController
{
  public EditContractSelectBranchesPopupFormController() {
    super();
    setCommandClass(SelectBranches.class);
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
    SelectBranches selectBranches = (SelectBranches)command;
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("search".equals(operation))
    {
      try
      {
        selectBranches.getAllBranchPagination().setCurrentPageNum(selectBranches.getPageNumber());
        userService.searchBranch(selectBranches.getBranchSearch(), selectBranches.getSortFlag());
        //selectBranches.getBranchSearch().getPagination().calculatePages();
        List allBranchExtList = ZoneUtil.constructBranchExtListFromBranchList(selectBranches.getBranchSearch().getResults());
        selectBranches.getAllBranchExtList().clear();
        selectBranches.getAllBranchExtList().addAll(allBranchExtList);
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }

      return showForm(request, response, errors);
    }
    else if("select".equals(operation))
    {
      ZoneUtil.addSelectedBranchList(selectBranches);

      return showForm(request, response, errors);
    }
    else if("deselect".equals(operation))
    {
      ZoneUtil.removeSelectedBranchList(selectBranches);

      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      return new ModelAndView("contract-refresh-page", "operationType", "selectBranchesDone");
    }

    try
    {
      ZoneUtil.createBranchLocationListFromSelectedBranchList(selectBranches);
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    return new ModelAndView("contract-refresh-page", "operationType", "selectBranchesDone");
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    String operation = request.getParameter("operation");
    if((operation != null) && ("search".equals(operation) || "return".equals(operation)))
    {
      return true;
    }

    return super.suppressValidation(request, command);
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

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    SelectBranches selectBranches = new SelectBranches();
    selectBranches.setSortFlag("name");
    selectBranches.setPageNumber(1);

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

    HttpSession session = request.getSession();
    EditZone editZone = (EditZone)session.getAttribute("com.intertek.web.controller.contract.EditContractZoneFormController.FORM.command");

    if(editZone != null)
    {
      selectBranches.setEditZone(editZone);

      Pagination allBranchesPagination = new Pagination(1, 10, selectBranches.getPageNumber(), 10);
      selectBranches.setAllBranchPagination(allBranchesPagination);

      BranchSearch branchSearch = new BranchSearch();
      selectBranches.setBranchSearch(branchSearch);
      branchSearch.setPagination(allBranchesPagination);
      branchSearch.setSearchForm("branchForm");
      branchSearch.getStatus().setValue(Constants.STATUS_A);
      branchSearch.setExcludeAdminType(true);
      branchSearch.setExcludeEmptyType(true);

      try
      {
        userService.searchBranch(branchSearch, selectBranches.getSortFlag());
        //branchSearch.getPagination().calculatePages();
        List allBranchExtList = ZoneUtil.constructBranchExtListFromBranchList(branchSearch.getResults());
        selectBranches.getAllBranchExtList().clear();
        selectBranches.getAllBranchExtList().addAll(allBranchExtList);
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }

      String zoneIndexStr = request.getParameter("zoneIndex");
      selectBranches.setZoneIndex(zoneIndexStr);

      ZoneExt zoneExt = ZoneUtil.getZoneExtByIndexStr(editZone, zoneIndexStr);
      if(zoneExt != null)
      {
        selectBranches.setZoneExt(zoneExt);
        List selectedBranchExtList = ZoneUtil.constructBranchExtListFromBranchLocationExtList(
          zoneExt.getBranchLocationExts()
        );
        selectBranches.getSelectedBranchExtList().clear();
        selectBranches.getSelectedBranchExtList().addAll(selectedBranchExtList);
      }
    }

    return selectBranches;
  }
}
