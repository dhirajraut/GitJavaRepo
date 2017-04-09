package com.intertek.web.controller.contract;

import java.util.ArrayList;
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
import com.intertek.domain.HighLevelServiceExt;
import com.intertek.domain.EditServiceRate;
import com.intertek.domain.SelectServices;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ServiceRateService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.ContractServiceUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;
import com.intertek.sort.HighLevelServiceExtComparator;

public class EditContractSelectServicesPopupFormController extends SimpleFormController
{
  public EditContractSelectServicesPopupFormController() {
    super();
    setCommandClass(SelectServices.class);
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
    SelectServices selectServices = (SelectServices)command;
    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");

    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("search".equals(operation))
    {
      try
      {
        selectServices.getAllHighLevelServicePagination().setCurrentPageNum(selectServices.getPageNumber());
        List allHighLevelServiceList = serviceRateService.searchHighLevelServices(
          selectServices.getServiceName(),
          selectServices.getIncludeMasterListServices(),
          selectServices.getAllHighLevelServicePagination(),
          selectServices.getSortFlag()
        );
        List allHighLevelServiceExtList = ContractServiceUtil.createHighLevelServiceExtList(
          allHighLevelServiceList,
          selectServices.getOldHighLevelServices()
        );
        selectServices.getAllHighLevelServiceExtList().clear();
        selectServices.getAllHighLevelServiceExtList().addAll(allHighLevelServiceExtList);
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }

      return showForm(request, response, errors);
    }
    else if("select".equals(operation))
    {
      List allHighLevelServiceExtList = selectServices.getAllHighLevelServiceExtList();
      for(int i=0; i<allHighLevelServiceExtList.size(); i++)
      {
        HighLevelServiceExt highLevelServiceExt = (HighLevelServiceExt)allHighLevelServiceExtList.get(i);
        if(highLevelServiceExt.getChecked())
        {
          HighLevelService highLevelService = highLevelServiceExt.getHighLevelService();

          if(!selectServices.getOldHighLevelServices().contains(highLevelService)
            && !selectServices.getNewHighLevelServices().contains(highLevelService)
          )
          {
            selectServices.getNewHighLevelServices().add(highLevelService);
            HighLevelServiceExt newHighLevelServiceExt = new HighLevelServiceExt();
            newHighLevelServiceExt.setHighLevelService(highLevelService);
            selectServices.getSelectedHighLevelServiceExtList().add(newHighLevelServiceExt);
          }
        }
      }
      Collections.sort(selectServices.getSelectedHighLevelServiceExtList(), new HighLevelServiceExtComparator());

      return showForm(request, response, errors);
    }
    else if("deselect".equals(operation))
    {
      List selectedHighLevelServiceExtList = selectServices.getSelectedHighLevelServiceExtList();
      List deleteExtList = new ArrayList();
      List deleteNewList = new ArrayList();

      for(int i=0; i<selectedHighLevelServiceExtList.size(); i++)
      {
        HighLevelServiceExt highLevelServiceExt = (HighLevelServiceExt)selectedHighLevelServiceExtList.get(i);
        if(highLevelServiceExt.getChecked())
        {
          HighLevelService highLevelService = highLevelServiceExt.getHighLevelService();

          if(!selectServices.getOldHighLevelServices().contains(highLevelService))
          {
            deleteExtList.add(highLevelServiceExt);
            deleteNewList.add(highLevelService);
          }
        }
      }
      selectServices.getSelectedHighLevelServiceExtList().removeAll(deleteExtList);
      selectServices.getNewHighLevelServices().removeAll(deleteNewList);

      Collections.sort(selectServices.getSelectedHighLevelServiceExtList(), new HighLevelServiceExtComparator());

      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      return new ModelAndView("contract-refresh-page", "operationType", "selectServicesDone");
    }

    try
    {
      EditServiceRate editServiceRate = selectServices.getEditServiceRate();
      if(editServiceRate != null)
      {
        editServiceRate.getSelectedHighLevelServiceList().clear();
        List selectedHighLevelServiceExtList = selectServices.getSelectedHighLevelServiceExtList();

        for(int i=0; i<selectedHighLevelServiceExtList.size(); i++)
        {
          HighLevelServiceExt highLevelServiceExt = (HighLevelServiceExt)selectedHighLevelServiceExtList.get(i);
          editServiceRate.getSelectedHighLevelServiceList().add(highLevelServiceExt.getHighLevelService());
        }

        editServiceRate.getNewHighLevelServices().clear();
        editServiceRate.getNewHighLevelServices().addAll(selectServices.getNewHighLevelServices());
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    return new ModelAndView("contract-refresh-page", "operationType", "selectServicesDone");
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
    SelectServices selectServices = new SelectServices();
    selectServices.setSortFlag("serviceDescription");
    selectServices.setPageNumber(1);

    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");

    HttpSession session = request.getSession();
    EditServiceRate editServiceRate = (EditServiceRate)session.getAttribute("com.intertek.web.controller.contract.EditContractServiceFormController.FORM.command");

    if(editServiceRate != null)
    {
      selectServices.setEditServiceRate(editServiceRate);

      Pagination allHighLevelServicePagination = new Pagination(1, 10, selectServices.getPageNumber(), 10);
      selectServices.setAllHighLevelServicePagination(allHighLevelServicePagination);

      List selectedHighLevelServiceExtList = ContractServiceUtil.createHighLevelServiceExtList(
        editServiceRate.getSelectedHighLevelServiceList(),
        editServiceRate.getOldHighLevelServices()
      );

      selectServices.getSelectedHighLevelServiceExtList().addAll(selectedHighLevelServiceExtList);
      selectServices.getOldHighLevelServices().addAll(editServiceRate.getOldHighLevelServices());
      selectServices.getNewHighLevelServices().addAll(editServiceRate.getNewHighLevelServices());

      Collections.sort(selectServices.getSelectedHighLevelServiceExtList(), new HighLevelServiceExtComparator());

      try
      {
        List allHighLevelServiceList = serviceRateService.searchHighLevelServices(
          null,
          false,
          selectServices.getAllHighLevelServicePagination(),
          selectServices.getSortFlag()
        );
        List allHighLevelServiceExtList = ContractServiceUtil.createHighLevelServiceExtList(
          allHighLevelServiceList,
          selectServices.getOldHighLevelServices()
        );
        selectServices.getAllHighLevelServiceExtList().addAll(allHighLevelServiceExtList);
      }
      catch(Exception e)
      {
      }
    }

    return selectServices;
  }
}
