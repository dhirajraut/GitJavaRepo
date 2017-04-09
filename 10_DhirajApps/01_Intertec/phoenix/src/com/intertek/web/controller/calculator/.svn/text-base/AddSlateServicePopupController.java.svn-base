package com.intertek.web.controller.calculator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.calculator.CalculatorUtil;
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.AddSlateService;
import com.intertek.domain.JobContractSlateExt;
import com.intertek.domain.SelectedSlate;
import com.intertek.entity.CfgContract;
import com.intertek.entity.JobContractSlate;
import com.intertek.entity.RB;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.service.SlateService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.JobUtil;

public class AddSlateServicePopupController extends SimpleFormController
{
  public AddSlateServicePopupController() {
    super();
    setCommandClass(AddSlateService.class);
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
    AddSlateService addSlateService = (AddSlateService)command;
    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      return new ModelAndView("refresh-page", "index", addSlateService.getIndex());
    }

    try
    {
      HttpSession session = request.getSession();
      AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

      AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
        addJobOrder,
        addSlateService.getIndex()
      );
      AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
        addJobContract,
        addSlateService.getSelectedVesselIndex()
      );
      AddJobContractProd addJobContractProd = JobUtil.getAddJobContractProdBySelectedProdIndex(
        addJobContractVessel,
        addSlateService.getSelectedProdIndex()
      );

      String location = null;

      if((addJobContract != null) && (addJobContract.getJobContract() != null))
      {
        location = addJobContract.getJobContract().getZone();
      }

      if("search".equals(operation))
      {
        String pageNumberStr = request.getParameter("pageNumber");

        int pageNumber = 1;
        try
        {
          pageNumber = new Integer(pageNumberStr).intValue();
        }
        catch (Exception e)
        {
        }
        if (pageNumber <= 0) pageNumber = 1;

        SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

        try
        {
          List searchSlates = slateService.getSlates(
            addSlateService.getContractCode(),
            addSlateService.getContract().getPriceBookId(),
            addSlateService.getSearchText(), // value
            addSlateService.getSearchType(), // searchType
            location != null ? location : "*",
            DateUtil.formatDate(addJobOrder.getJobOrder().getJobFinishDate(), "yyyyMMdd"),
            addJobContract.getJobContract().getLanguage()
          );

          SelectedSlate[] newSelectedSlates = JobUtil.getSelectedSlates(searchSlates);
          addSlateService.setSelectedSlates(newSelectedSlates);
        }
        catch (Exception e)
        {
          e.printStackTrace();
          errors.reject("search.slate.error", new Object[] { e.getMessage() }, null);
          return showForm(request, response, errors);
        }

        return showForm(request, response, errors);
      }
      else
      {
        if((addJobContractVessel != null) && (addJobContractProd != null))
        {
          List newJobContractSlates = new ArrayList();

          SelectedSlate[] selectedSlates = addSlateService.getSelectedSlates();
          int size = selectedSlates != null ? selectedSlates.length : 0;
          for(int i=0; i<size; i++)
          {
            Double qty = selectedSlates[i].getQty();
            if(selectedSlates[i].isSelected() && (qty != null) && (qty > 0))
            {
              JobContractSlate jobContractSlate = new JobContractSlate();
              jobContractSlate.setJobContractProd(addJobContractProd.getJobContractProd());

              jobContractSlate.setQuantity(qty);
              jobContractSlate.setSlate(selectedSlates[i].getSlate());

              JobContractSlate tmpJobContractSlate = CalculatorUtil.calculateSlate(
                jobContractSlate,
                addSlateService.getRb(),
                addSlateService.getContract()
              );

              if(tmpJobContractSlate != null)
              {
                JobContractSlateExt slateExt = new JobContractSlateExt();
                slateExt.setSlate(jobContractSlate);
                addJobContractProd.getJobContractSlateExts().add(slateExt);
                addJobContractProd.getJobContractProd().getJobContractSlates().add(jobContractSlate);
              }
              //else addJobContractProd.getJobContractProd().getJobContractSlates().remove(jobContractSlate);
            }
          }
        }
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    if("submit".equals(operation))
    {
      request.setAttribute("operation", operation);
      SelectedSlate[] selectedSlates = addSlateService.getSelectedSlates();
      StringBuffer sb = new StringBuffer();
      int count = 0;
      if(selectedSlates != null)
      {
        for(int i=0; i<selectedSlates.length; i++)
        {
          if(selectedSlates[i] != null)
          {
            if(selectedSlates[i].isSelected())
            {
              if(count > 0) sb.append(",");
              sb.append(selectedSlates[i].getSlate().getSlateId());

              count ++;
            }
            selectedSlates[i].setSelected(false);
            selectedSlates[i].setQty(1.0);
          }
        }
      }

      if(count > 0)
      {
        request.setAttribute("slates_added", sb.toString());
      }
      else
      {
        request.setAttribute("slates_added", null);
      }

      return showForm(request, response, errors);
    }

    return new ModelAndView("refresh-page", "index", addSlateService.getIndex());
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
    String contractCode = request.getParameter("contractCode");
    String index = request.getParameter("index");
    String selectedVesselIndex = request.getParameter("selectedVesselIndex");
    String selectedProdIndex = request.getParameter("selectedProdIndex");

    AddSlateService addSlateService = new AddSlateService();
    addSlateService.setContractCode(contractCode);
    addSlateService.setIndex(index);
    addSlateService.setSelectedVesselIndex(selectedVesselIndex);
    addSlateService.setSelectedProdIndex(selectedProdIndex);

    HttpSession session = request.getSession();
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");
    AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
      addJobOrder,
      index
    );
    try {
    if(addJobContract != null)
    {
      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
      CfgContract contract = addJobContract.getCfgContract();
      addSlateService.setContract(contract);

      RB rb = calculatorService.getRbByRbKey(Constants.Slate);
      addSlateService.setRb(rb);
    }
    } catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return addSlateService;
  }
}
