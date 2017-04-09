package com.intertek.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.calculator.ControlExt;
import com.intertek.domain.AddInspectionService;
import com.intertek.domain.AddJobContractProd;
import com.intertek.entity.JobContractProd;
import com.intertek.entity.ProductGroup;
import com.intertek.util.Constants;
import com.intertek.util.StringUtil;

public class AddJobContractProdValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return AddInspectionService.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    AddInspectionService addInspectionService = (AddInspectionService)obj;
    AddJobContractProd addJobContractProd = addInspectionService.getAddJobContractProd();
    if(addJobContractProd == null) return;
    JobContractProd jobContractProd = addJobContractProd.getJobContractProd();
    if(jobContractProd == null) return;

    if (jobContractProd.getJobProductName() == null || jobContractProd.getJobProductName().trim().length() == 0)
    {
      errors.rejectValue("addJobContractProd.jobContractProd.jobProductName", "not.blank", "");
    }

    if (jobContractProd.getGroup() == null || jobContractProd.getGroup().trim().length() == 0)
    {
      errors.rejectValue("addJobContractProd.jobContractProd.group", "not.blank", "");
    }

    boolean notChargeInd = false;
    Boolean notChargeIndObj = jobContractProd.getNotChargeInd();
    if(notChargeIndObj != null)
    {
      notChargeInd = notChargeIndObj.booleanValue();
    }

    ControlExt[] controlExts = addInspectionService.getControlExts();
    ProductGroup selectedProductGroup = addInspectionService.getSelectedProductGroup();
    Integer uomFlag = selectedProductGroup.getUomFlag();
    int uomCode = -1;
    if(uomFlag != null) uomCode = uomFlag.intValue();

    if(controlExts != null)
    {
      for(int i=0; i<controlExts.length; i++)
      {
        String dataInput = controlExts[i].getDataInput();

        String objectName = controlExts[i].getControl().getControlId().getObjectName();
        if(Constants.noOfUOM.equals(objectName))
        {
          if((uomCode == 1) && StringUtil.emptyStr(dataInput))
          {
            if(!notChargeInd) errors.rejectValue("controlExts[" + i + "].dataInput", "not.blank", "");
            continue;
          }
          else if(StringUtil.emptyStr(dataInput)) continue;

          Double qty = null;
          try { qty = new Double(dataInput); } catch(Exception e) { }

          if(qty == null)
          {
            errors.rejectValue("controlExts[" + i + "].dataInput", "not.a.number", "");
          }
          else if(qty <= 0)
          {
            errors.rejectValue("controlExts[" + i + "].dataInput", "not.a.positive.number", "");
          }
        }
        else if(Constants.noOfUOM1.equals(objectName))
        {
          if((uomCode == 2) && (StringUtil.emptyStr(dataInput)))
          {
            if(!notChargeInd) errors.rejectValue("controlExts[" + i + "].dataInput", "not.blank", "");
            continue;
          }
          else if(StringUtil.emptyStr(dataInput)) continue;

          Double qty = null;
          try { qty = new Double(dataInput); } catch(Exception e) { }

          if(qty == null)
          {
            errors.rejectValue("controlExts[" + i + "].dataInput", "not.a.number", "");
          }
          else if(qty <= 0)
          {
            errors.rejectValue("controlExts[" + i + "].dataInput", "not.a.positive.number", "");
          }
        }
        else if(Constants.noOfUOM2.equals(objectName))
        {
          if((uomCode == 3) && (StringUtil.emptyStr(dataInput)))
          {
            if(!notChargeInd) errors.rejectValue("controlExts[" + i + "].dataInput", "not.blank", "");
            continue;
          }
          else if(StringUtil.emptyStr(dataInput)) continue;

          Double qty = null;
          try { qty = new Double(dataInput); } catch(Exception e) { }

          if(qty == null)
          {
            errors.rejectValue("controlExts[" + i + "].dataInput", "not.a.number", "");
          }
          else if(qty <= 0)
          {
            errors.rejectValue("controlExts[" + i + "].dataInput", "not.a.positive.number", "");
          }
        }
      }
    }
  }
}
