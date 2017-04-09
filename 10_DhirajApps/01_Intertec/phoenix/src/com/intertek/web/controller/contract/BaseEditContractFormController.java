package com.intertek.web.controller.contract;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.ContractSearch;
import com.intertek.entity.Contract;
import com.intertek.util.ContractUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class BaseEditContractFormController extends BaseSimpleFormController
{
  protected ModelAndView getModelAndView(
    String operationType,
    String goToContractTab,
    String currentTab,
    Contract contract,
    ContractSearch contractSearch,
    BindException errors
  )
  {
    String viewName = null;
    if("switchTab".equals(operationType))
    {
      viewName = ContractUtil.getViewNameByTabName(goToContractTab);
    }
    else if("searchContract".equals(operationType))
    {
      viewName = "search-contract-r";
    }

    if(viewName == null)
    {
      viewName = ContractUtil.getViewNameByTabName(currentTab);
    }

    ModelAndView modelAndView = new ModelAndView(viewName);

    String contractCode = null;
    if("prevContract".equals(operationType)
      || "nextContract".equals(operationType)
    )
    {
      try
      {
        contractCode = ContractUtil.getNextContractCode(
          contract.getContractCode(),
          contractSearch,
          "prevContract".equals(operationType)
        );
      }
      catch(Exception e)
      {
        e.printStackTrace();

        errors.reject("edit.contract.error", new Object[] {e.getMessage()}, null);
        return null;
      }
      if(contractCode == null)
      {
        contractCode = contract.getContractCode();
        if("prevContract".equals(operationType))
        {
          modelAndView.addObject("saved_message", "No Previous Contract!");
        }
        else
        {
          modelAndView.addObject("saved_message", "No Next Contract!");
        }
      }
    }
    else if("saveContract".equals(operationType))
    {
      modelAndView.addObject("saved_message", "Saved successfully");
    }

    if(contractCode == null) contractCode = contract.getContractCode();
    modelAndView.addObject("contractCode", contractCode);

    return modelAndView;
  }

  protected ContractSearch getContractSearch(HttpServletRequest request)
  {
    ContractSearch contractSearch = null;
    HttpSession session = request.getSession();
    if(session != null)
    {
      contractSearch = (ContractSearch)session.getAttribute(
        "com.intertek.web.controller.contract.SearchContractFormController.FORM.command"
      );
    }

    return contractSearch;
  }
}
