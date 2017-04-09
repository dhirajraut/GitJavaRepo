package com.intertek.util;

import java.io.*;
import java.util.*;

import com.intertek.locator.*;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.pagination.*;
import com.intertek.domain.*;
import com.intertek.entity.*;
import com.intertek.meta.*;
import com.intertek.meta.dropdown.*;
import com.intertek.calculator.*;
import com.intertek.sort.*;

public class InspectionRateUtil
{
  public static void saveEditInspectionRate(
    EditInspectionRate editInspectionRate
  )
  {
    if(editInspectionRate == null) return;

    packEditInspectionRate(editInspectionRate);

    InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");

    inspectionRateService.saveEditInspectionRate(editInspectionRate);
  }

  public static void packEditInspectionRate(
    EditInspectionRate editInspectionRate
  )
  {
    if(editInspectionRate == null) return;

    Contract contract = editInspectionRate.getContract();
    if(contract == null) return;

    List inspectionVersionExtList = editInspectionRate.getInspectionVersionExtList();

    for(int i=0; i<inspectionVersionExtList.size(); i++)
    {
      InspectionVersionExt inspectionVersionExt = (InspectionVersionExt)inspectionVersionExtList.get(i);

      Boolean pbDiscountInd = inspectionVersionExt.getInspectionVersion().getPbDiscountInd();
      if(pbDiscountInd == null) inspectionVersionExt.getInspectionVersion().setPbDiscountInd(false);

      String cfgDiscountPctc = inspectionVersionExt.getInspectionVersion().getCfgDiscountPctc();
      if(cfgDiscountPctc == null) inspectionVersionExt.getInspectionVersion().setCfgDiscountPctc(Constants.U);

      Boolean contractRateInd = inspectionVersionExt.getInspectionVersion().getContractRateInd();
      if(contractRateInd == null) inspectionVersionExt.getInspectionVersion().setContractRateInd(false);
      Boolean vesselMaxInd = inspectionVersionExt.getInspectionVersion().getVesselMaxInd();
      if(vesselMaxInd == null) inspectionVersionExt.getInspectionVersion().setVesselMaxInd(false);
      Boolean additonalVesselInd = inspectionVersionExt.getInspectionVersion().getAdditonalVesselInd();
      if(additonalVesselInd == null) inspectionVersionExt.getInspectionVersion().setAdditonalVesselInd(false);
      Boolean towMaxInd = inspectionVersionExt.getInspectionVersion().getTowMaxInd();
      if(towMaxInd == null) inspectionVersionExt.getInspectionVersion().setTowMaxInd(false);
      Boolean numGradesInd = inspectionVersionExt.getInspectionVersion().getNumGradesInd();
      if(numGradesInd == null) inspectionVersionExt.getInspectionVersion().setNumGradesInd(false);

      List vesselTypeExtList = inspectionVersionExt.getVesselTypeExtList();
      if(vesselTypeExtList != null)
      {
        for(int j=0; j<vesselTypeExtList.size(); j++)
        {
          VesselTypeExt vesselTypeExt = (VesselTypeExt)vesselTypeExtList.get(j);

          List inspectionRateExtList = vesselTypeExt.getContractInspectionRateExtList();
          if(inspectionRateExtList != null)
          {
            for(int k=0; k<inspectionRateExtList.size(); k++)
            {
              InspectionRateExt inspectionRateExt = (InspectionRateExt)inspectionRateExtList.get(k);
              InspectionRate inspectionRate = inspectionRateExt.getInspectionRate();

              inspectionRate.getInspectionRateId().setContractId(contract.getContractCode());
              inspectionRate.getInspectionRateId().setBeginDate(inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate());
              inspectionRate.setEndDate(inspectionVersionExt.getInspectionVersion().getEndDate());
            }
          }

          InspectionRateExt towMaxInspectionRateExt = vesselTypeExt.getTowMaxInspectionRateExt();
          if(towMaxInspectionRateExt != null)
          {
            towMaxInspectionRateExt.getInspectionRate().getInspectionRateId().setContractId(contract.getContractCode());
            towMaxInspectionRateExt.getInspectionRate().getInspectionRateId().setBeginDate(inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate());
            towMaxInspectionRateExt.getInspectionRate().setEndDate(inspectionVersionExt.getInspectionVersion().getEndDate());
          }
        }
      }

      packContractExpressionExtList(
        contract,
        inspectionVersionExt,
        editInspectionRate.getInspectionCeList()
      );

      packControlExtList(
        contract,
        inspectionVersionExt,
        editInspectionRate.getInspectionControlList()
      );
    }
  }

  public static void packContractExpressionExtList(
    Contract contract,
    InspectionVersionExt inspectionVersionExt,
    List inspectionCeList
  )
  {
    if((inspectionVersionExt == null) || (inspectionCeList == null)) return;

    List ceExtList = inspectionVersionExt.getCeExtList();
    if(ceExtList == null) return;

    Boolean contractRateInd = inspectionVersionExt.getInspectionVersion().getContractRateInd();
    Boolean vesselMaxInd = inspectionVersionExt.getInspectionVersion().getVesselMaxInd();
    Boolean additonalVesselInd = inspectionVersionExt.getInspectionVersion().getAdditonalVesselInd();
    Boolean towMaxInd = inspectionVersionExt.getInspectionVersion().getTowMaxInd();
    Boolean numGradesInd = inspectionVersionExt.getInspectionVersion().getNumGradesInd();

    List deletedInspectionCeList = new ArrayList();
    List addedInspectionCeList = new ArrayList();

    for(int i=0; i<inspectionCeList.size(); i++)
    {
      InspectionContractExpression ice = (InspectionContractExpression)inspectionCeList.get(i);

      if(contractRateInd)
      {
        if(ice.getContractRateInd()
          || (vesselMaxInd && ice.getVesselMaxInd())
          || (additonalVesselInd && ice.getAdditonalVesselInd())
          || (towMaxInd && ice.getTowMaxInd())
          || (numGradesInd && ice.getNumGradesInd())
        )
        {
          addedInspectionCeList.add(ice);
        }
        else
        {
          deletedInspectionCeList.add(ice);
        }
      }
      else
      {
        deletedInspectionCeList.add(ice);
      }
    }

    inspectionVersionExt.getDeletedCeExtList().clear();
    for(int i=0; i<deletedInspectionCeList.size(); i++)
    {
      InspectionContractExpression ice = (InspectionContractExpression)deletedInspectionCeList.get(i);

      for(int j=0; j<ceExtList.size(); j++)
      {
        ContractExpressionExt ceExt = (ContractExpressionExt)ceExtList.get(j);

        if(hasSameKey(ice, ceExt.getContractExpression()))
        {
          inspectionVersionExt.getDeletedCeExtList().add(ceExt);
        }
      }
    }
    ceExtList.removeAll(inspectionVersionExt.getDeletedCeExtList());

    List addedCeExtList = new ArrayList();
    for(int i=0; i<addedInspectionCeList.size(); i++)
    {
      InspectionContractExpression ice = (InspectionContractExpression)addedInspectionCeList.get(i);

      boolean existed = false;

      for(int j=0; j<ceExtList.size(); j++)
      {
        ContractExpressionExt ceExt = (ContractExpressionExt)ceExtList.get(j);

        if(hasSameKey(ice, ceExt.getContractExpression()))
        {
          existed = true;
          break;
        }
      }

      if(!existed)
      {
        ContractExpression ce = createContractExpressionFromInspectionContractInspection(ice);
        ContractExpressionExt ceExt = new ContractExpressionExt();
        ceExt.setContractExpression(ce);

        ce.getContractExpressionId().setContractId(contract.getContractCode());
        ce.getContractExpressionId().setLocation("*");
        ce.getContractExpressionId().setBeginDate(inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate());
        ce.setEndDate(inspectionVersionExt.getInspectionVersion().getEndDate());

        addedCeExtList.add(ceExt);
      }
    }
    ceExtList.addAll(addedCeExtList);
  }

  public static void setOtherCheckBoxesViewOnly(InspectionVersionExt inspectionVersionExt)
  {
    if(inspectionVersionExt == null) return;

    Boolean contractRateInd = inspectionVersionExt.getInspectionVersion().getContractRateInd();
    if(contractRateInd == null)
    {
      inspectionVersionExt.setOtherCheckBoxesViewOnly(true);
      return;
    }
    else if(!contractRateInd.booleanValue())
    {
      inspectionVersionExt.setOtherCheckBoxesViewOnly(true);
      return;
    }

    inspectionVersionExt.setOtherCheckBoxesViewOnly(false);
  }

  public static void packControlExtList(
    Contract contract,
    InspectionVersionExt inspectionVersionExt,
    List inspectionControlList
  )
  {
    if((inspectionVersionExt == null) || (inspectionControlList == null)) return;

    List controlList = inspectionVersionExt.getControlExtList();
    if(controlList == null) return;

    Boolean contractRateInd = inspectionVersionExt.getInspectionVersion().getContractRateInd();
    Boolean vesselMaxInd = inspectionVersionExt.getInspectionVersion().getVesselMaxInd();
    Boolean additonalVesselInd = inspectionVersionExt.getInspectionVersion().getAdditonalVesselInd();
    Boolean towMaxInd = inspectionVersionExt.getInspectionVersion().getTowMaxInd();
    Boolean numGradesInd = inspectionVersionExt.getInspectionVersion().getNumGradesInd();

    List deletedInspectionControlList = new ArrayList();
    List addedInspectionControlList = new ArrayList();

    for(int i=0; i<inspectionControlList.size(); i++)
    {
      InspectionControl ic = (InspectionControl)inspectionControlList.get(i);

      if(contractRateInd)
      {
        if(ic.getContractRateInd()
          || (vesselMaxInd && ic.getVesselMaxInd())
          || (additonalVesselInd && ic.getAdditonalVesselInd())
          || (towMaxInd && ic.getTowMaxInd())
          || (numGradesInd && ic.getNumGradesInd())
        )
        {
          addedInspectionControlList.add(ic);
        }
        else
        {
          deletedInspectionControlList.add(ic);
        }
      }
      else
      {
        deletedInspectionControlList.add(ic);
      }
    }

    inspectionVersionExt.getDeletedControlExtList().clear();
    for(int i=0; i<deletedInspectionControlList.size(); i++)
    {
      InspectionControl ic = (InspectionControl)deletedInspectionControlList.get(i);

      for(int j=0; j<controlList.size(); j++)
      {
        ControlExt control = (ControlExt)controlList.get(j);

        if(hasSameKey(ic, control.getControl()))
        {
          inspectionVersionExt.getDeletedControlExtList().add(control);
        }
      }
    }
    controlList.removeAll(inspectionVersionExt.getDeletedControlExtList());

    List addedControlExtList = new ArrayList();
    for(int i=0; i<addedInspectionControlList.size(); i++)
    {
      InspectionControl ic = (InspectionControl)addedInspectionControlList.get(i);

      boolean existed = false;

      for(int j=0; j<controlList.size(); j++)
      {
        ControlExt control = (ControlExt)controlList.get(j);

        if(hasSameKey(ic, control.getControl()))
        {
          existed = true;
          break;
        }
      }

      if(!existed)
      {
        Control control = createControlFromInspectionControl(ic);
        ControlExt controlExt = new ControlExt();
        controlExt.setControl(control);

        control.getControlId().setContractId(contract.getContractCode());
        control.getControlId().setBeginDate(inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate());
        control.setEndDate(inspectionVersionExt.getInspectionVersion().getEndDate());

        addedControlExtList.add(controlExt);
      }
    }
    controlList.addAll(addedControlExtList);
  }

  public static ContractExpression createContractExpressionFromInspectionContractInspection(
    InspectionContractExpression ice
  )
  {
    if(ice == null) return null;

    ContractExpression ce = new ContractExpression();
    ContractExpressionId ceId = new ContractExpressionId();

    ce.setContractExpressionId(ceId);

    ceId.setServiceName(ice.getInspectionContractExpressionId().getServiceName());
    ceId.setExpressionId(ice.getInspectionContractExpressionId().getExpressionId());
    ceId.setBeginDate(ice.getInspectionContractExpressionId().getBeginDate());

    ce.setComponentType(ice.getInspectionContractExpressionId().getComponentType());
    ce.setCreateLi(ice.getInspectionContractExpressionId().getCreateLi());

    ce.setContractComponent(ice.getContractComponent());
    ce.setPbComponentType(ice.getPbComponentType());
    ce.setCfgDiscountPercent(ice.getCfgDiscountPercent());
    ce.setParentExpressionId(ice.getParentExpressionId());
    ce.setSiblingExpressionId(ice.getSiblingExpressionId());
    ce.setQuestionId(ice.getQuestionId());
    ce.setServiceRateExpressionId(ice.getServiceRateExpressionId());
    ce.setServiceLevel(ice.getServiceLevel());
    ce.setExpressionRank(ice.getExpressionRank());
    ce.setUseGroupId(ice.getUseGroupId());
    ce.setUseVesselId(ice.getUseVesselId());
    ce.setVisibility(ice.getVisibility());
    ce.setEndDate(ice.getEndDate());

    return ce;
  }

  public static Control createControlFromInspectionControl(
    InspectionControl ic
  )
  {
    if(ic == null) return null;

    Control control = new Control();
    ControlId controlId = new ControlId();

    control.setControlId(controlId);

    controlId.setServiceName(ic.getInspectionControlId().getServiceName());
    controlId.setObjectName(ic.getInspectionControlId().getObjectName());
    controlId.setQuestionId(ic.getInspectionControlId().getQuestionId());
    controlId.setBeginDate(ic.getInspectionControlId().getBeginDate());

    control.setRbKey(ic.getRbKey());
    control.setAttributes(ic.getAttributes());
    control.setControlType(ic.getControlType());
    control.setParameters(ic.getParameters());
    control.setSortOrderNum(ic.getSortOrderNum());
    control.setHelpUrl(ic.getHelpUrl());
    control.setEndDate(ic.getEndDate());

    return control;
  }

  public static boolean hasSameKey(InspectionContractExpression ice, ContractExpression ce)
  {
    if((ice == null) || (ce == null)) return false;

    if(ce.getContractExpressionId().getServiceName().equals(ice.getInspectionContractExpressionId().getServiceName())
      && ce.getContractExpressionId().getExpressionId().equals(ice.getInspectionContractExpressionId().getExpressionId())
      && ce.getComponentType().equals(ice.getInspectionContractExpressionId().getComponentType())
    )
    {
      return true;
    }

    return false;
  }

  public static boolean hasSameKey(InspectionControl ic, Control control)
  {
    if((ic == null) || (control == null)) return false;

    if(control.getControlId().getServiceName().equals(ic.getInspectionControlId().getServiceName())
      && control.getControlId().getObjectName().equals(ic.getInspectionControlId().getObjectName())
      && control.getControlId().getQuestionId().equals(ic.getInspectionControlId().getQuestionId())
    )
    {
      return true;
    }

    return false;
  }

  public static void processDeleteSelectedProducts(
    EditInspectionRate editInspectionRate
  )
  {
    if(editInspectionRate == null) return;

    Contract contract = editInspectionRate.getContract();
    if(contract == null) return;

    InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
    if(activeInspectionVersionExt == null) return;

    VesselTypeExt activeVesselTypeExt = activeInspectionVersionExt.getActiveVesselTypeExt();
    if(activeInspectionVersionExt == null) return;

    List inspectionRateExtList = activeVesselTypeExt.getContractInspectionRateExtList();
    if(inspectionRateExtList != null)
    {
      List deleted = activeVesselTypeExt.getDeletedInspectionRateExtList();
      if(deleted == null)
      {
        deleted = new ArrayList();
        activeVesselTypeExt.setDeletedInspectionRateExtList(deleted);
      }

      for(int i=0; i<inspectionRateExtList.size(); i++)
      {
        InspectionRateExt inspectionRateExt = (InspectionRateExt)inspectionRateExtList.get(i);

        if(inspectionRateExt.getChecked())
        {
          deleted.add(inspectionRateExt);
        }
      }

      inspectionRateExtList.removeAll(deleted);
    }
  }

  public static void loadEditInspectionRate(EditInspectionRate editInspectionRate)
  {
    if(editInspectionRate == null) return;

    Contract contract = editInspectionRate.getContract();
    if(contract == null) return;

    InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");

    List inspectionCeList = inspectionRateService.getInspectionContractExpressionList();
    editInspectionRate.setInspectionCeList(inspectionCeList);

    List inspectionControlList = inspectionRateService.getInspectionControlList();
    editInspectionRate.setInspectionControlList(inspectionControlList);

    List inspectionVersionList = inspectionRateService.getInspectionVersionListByContractCode(
      contract.getContractCode()
    );

    List inspectionVersionExtList = createInspectionVersionExtList(inspectionVersionList);
    editInspectionRate.setInspectionVersionExtList(inspectionVersionExtList);

    createInspectionVersion(editInspectionRate, false);
    editInspectionRate.setActiveInspectionVersionIndex(editInspectionRate.getInspectionVersionExtList().size() - 1);

    for(int i=0; i<inspectionVersionExtList.size(); i++)
    {
      InspectionVersionExt inspectionVersionExt = (InspectionVersionExt)inspectionVersionExtList.get(i);

      loadInspectionVersionExtData(contract, inspectionVersionExt);

      configTowMaxInspectionRateExt(inspectionVersionExt);
    }

    Date earliestContractBeginDate = ContractServiceUtil.getEarliestBeginDateOfContract(contract.getContractCode());
    editInspectionRate.setEarliestContractBeginDate(earliestContractBeginDate);
  }

  public static void loadInspectionVersionExtData(Contract contract, InspectionVersionExt inspectionVersionExt)
  {
    if(inspectionVersionExt == null) return;
    if(contract == null) return;

    VesselTypeService vesselTypeService = (VesselTypeService)ServiceLocator.getInstance().getBean("vesselTypeService");
    InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");
    RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");

    loadCfgContract(contract, inspectionVersionExt);

    CfgContract cfgContract = inspectionVersionExt.getCfgContract();
    if(cfgContract != null)
    {
      // need to change
      List vesselTypeExtList = null;
      if(cfgContract.getCfgContractId().getContractId().equals(cfgContract.getVesselSet()))
      {
        vesselTypeExtList = vesselTypeService.getVesselTypesByVesselTypeSetNameWithEqualBeginDate(
          cfgContract.getVesselSet(),
          cfgContract.getCfgContractId().getBeginDate()
        );
      }
      else
      {
        vesselTypeExtList = vesselTypeService.getVesselTypesByVesselTypeSetName(
          cfgContract.getVesselSet(),
          new Date()
        );
      }
      inspectionVersionExt.setVesselTypeExtList(vesselTypeExtList);

      VesselTypeSetUtil.loadVesselTypeRBs(cfgContract, vesselTypeExtList, false, null);

      loadInspectionRatesForInspectionVersion(cfgContract, inspectionVersionExt);

      makeVesselTypeExtRowsForInspectionVersionExt(inspectionVersionExt);

      List ceList = inspectionRateService.getContractExpressionList(
        contract.getContractCode(),
        inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate(),
        new String[] {"L-Insp", "Tow-Max", "Vessel-Insp"}
      );
      List ceExtList = ContractServiceUtil.getContractExpressionExtListByContractExpressionList(ceList);
      inspectionVersionExt.setCeExtList(ceExtList);

      List controlList = inspectionRateService.getControlList(
        contract.getContractCode(),
        inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate(),
        new String[] {"L-Insp", "V-Insp"}
      );
      List controlExtList = ContractServiceUtil.getControlExtListByControlList(controlList);
      inspectionVersionExt.setControlExtList(controlExtList);

      List productGroupExtList = ProductGroupSetUtil.getProductGroupExtList(cfgContract);

      List productGroupDropDownList = ProductGroupSetUtil.makeProductGroupDropDownList(productGroupExtList);
      Collections.sort(productGroupDropDownList, new FieldComparator());

      inspectionVersionExt.setProductGroupDropDownList(productGroupDropDownList);

      Map productGroupDropDownMap = new HashMap();
      for(int i=0; i<productGroupDropDownList.size(); i++)
      {
        Field field = (Field)productGroupDropDownList.get(i);
        productGroupDropDownMap.put(field.getValue(), field);
      }

      sortContractInspectionRateListByProductGroup(inspectionVersionExt, productGroupDropDownMap);
    }

    setOtherCheckBoxesViewOnly(inspectionVersionExt);

    RB rb = rbService.getRB(
      Constants.NONE_CAPITAL_LETTERS,
      contract.getContractCode() + ".ContractOperations",
      inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate(),
      inspectionVersionExt.getInspectionVersion().getEndDate()
    );

    RBExt rbExt = RbUtil.createRBExtByRB(rb);
    inspectionVersionExt.setContractOperationsRbExt(rbExt);
  }

  public static InspectionVersionExt getActiveInspectionVersionExt(EditInspectionRate editInspectionRate)
  {
    if(editInspectionRate == null) return null;

    int activeIndex = editInspectionRate.getActiveInspectionVersionIndex();

    List inspectionVersionExtList = editInspectionRate.getInspectionVersionExtList();
    if(inspectionVersionExtList == null) return null;

    if((activeIndex >=0 ) && (activeIndex < inspectionVersionExtList.size()))
    {
      return (InspectionVersionExt)inspectionVersionExtList.get(activeIndex);
    }

    return null;
  }

  public static void loadCfgContract(Contract contract, InspectionVersionExt inspectionVersionExt)
  {
    if((contract == null) || (inspectionVersionExt == null)) return;

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    CfgContract cfgContract = calculatorService.getContractByContractId(
      contract.getContractCode(),
      inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate()
    );

    inspectionVersionExt.setCfgContract(cfgContract);
  }

  public static List createInspectionVersionExtList(List inspectionVersionList)
  {
    List results = new ArrayList();
    if(inspectionVersionList == null) return results;

    for(int i=0; i<inspectionVersionList.size(); i++)
    {
      InspectionVersion inspectionVersion = (InspectionVersion)inspectionVersionList.get(i);
      InspectionVersionExt inspectionVersionExt = new InspectionVersionExt();

      InspectionVersion newInspectionVersion = new InspectionVersion();
      EntityUtil.copyInspectionVersion(newInspectionVersion, inspectionVersion);

      inspectionVersionExt.setOldInspectionVersion(newInspectionVersion);
      inspectionVersionExt.setInspectionVersion(inspectionVersion);

      results.add(inspectionVersionExt);
    }

    return results;
  }

  public static InspectionVersionExt createInspectionVersion(EditInspectionRate editInspectionRate, boolean createdIfExists)
  {
    if(editInspectionRate == null) return null;

    Contract contract = editInspectionRate.getContract();
    if(contract == null) return null;

    List inspectionVersionExtList = editInspectionRate.getInspectionVersionExtList();
    if(inspectionVersionExtList == null)
    {
      inspectionVersionExtList = new ArrayList();
      editInspectionRate.setInspectionVersionExtList(inspectionVersionExtList);
    }

    boolean needToCreate = true;

    if(!createdIfExists && (inspectionVersionExtList.size() > 0)) needToCreate = false;

    if(needToCreate)
    {
      InspectionVersionExt inspectionVersionExt = new InspectionVersionExt();
      InspectionVersion inspectionVersion = new InspectionVersion();
      inspectionVersionExt.setInspectionVersion(inspectionVersion);
      InspectionVersionId inspectionVersionId = new InspectionVersionId();
      inspectionVersion.setInspectionVersionId(inspectionVersionId);
      inspectionVersionId.setContractId(contract.getContractCode());
      inspectionVersionId.setBeginDate(new Date());
      inspectionVersion.setEndDate(DateUtil.parseDate("12/31/2999", "MM/dd/yyyy"));

      if(inspectionVersionExtList.size() > 0)
      {
        InspectionVersionExt lastInspectionVersionExt = (InspectionVersionExt)inspectionVersionExtList.get(inspectionVersionExtList.size() - 1);
        InspectionVersion lastInspectionVersion = lastInspectionVersionExt.getInspectionVersion();

        Date[] newDatePair = new Date[] {
          inspectionVersion.getInspectionVersionId().getBeginDate(),
          inspectionVersion.getEndDate()
        };

        Date[] oldDatePair = new Date[] {
          lastInspectionVersion.getInspectionVersionId().getBeginDate(),
          lastInspectionVersion.getEndDate()
        };

        DateUtil.calculateBeginDateAndEndDate(newDatePair, oldDatePair);

        inspectionVersion.getInspectionVersionId().setBeginDate(newDatePair[0]);
        inspectionVersion.setEndDate(newDatePair[1]);

        lastInspectionVersion.getInspectionVersionId().setBeginDate(oldDatePair[0]);
        lastInspectionVersion.setEndDate(oldDatePair[1]);
      }

      inspectionVersionExtList.add(inspectionVersionExt);

      return inspectionVersionExt;
    }

    return null;
  }

  public static void loadInspectionRatesForInspectionVersion(
    CfgContract cfgContract,
    InspectionVersionExt inspectionVersionExt
  )
  {
    if((cfgContract == null) || (inspectionVersionExt == null)) return;
    List vesselTypeExtList = inspectionVersionExt.getVesselTypeExtList();

    if((vesselTypeExtList != null) && (vesselTypeExtList.size() > 0))
    {
      List vesselTypeNameList = new ArrayList();
      for(int i=0; i<vesselTypeExtList.size(); i++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)vesselTypeExtList.get(i);
        vesselTypeNameList.add(vtExt.getVesselType().getVesselTypeId().getVesselType());
      }

      InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");

      List inspectionRateList = inspectionRateService.getInspectionRateListByContractIdAndVesselTypeNameListAndDates(
        cfgContract.getCfgContractId().getContractId(),
        vesselTypeNameList,
        inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate()
      );

      for(int i=0; i<vesselTypeExtList.size(); i++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)vesselTypeExtList.get(i);
        List contractInspectionRateExtList = vtExt.getContractInspectionRateExtList();
        if(contractInspectionRateExtList == null)
        {
          contractInspectionRateExtList = new ArrayList();
          vtExt.setContractInspectionRateExtList(contractInspectionRateExtList);
        }

        for(int j=0; j<inspectionRateList.size(); j++)
        {
          InspectionRate inspectionRate = (InspectionRate)inspectionRateList.get(j);

          InspectionRateExt inspectionRateExt = new InspectionRateExt();

          InspectionRate oldInspectionRate = new InspectionRate();
          EntityUtil.copyInspectionRate(oldInspectionRate, inspectionRate);

          inspectionRateExt.setOldInspectionRate(oldInspectionRate);
          inspectionRateExt.setInspectionRate(inspectionRate);

          if(vtExt.getVesselType().getVesselTypeId().getVesselType().equals(
            inspectionRate.getInspectionRateId().getVesselType())
          )
          {
            if("InspectionTow".equals(inspectionRate.getInspectionRateId().getExpressionId())
              && "InlandBarge".equals(inspectionRate.getInspectionRateId().getVesselType())
            )
            {
              vtExt.setTowMaxInspectionRateExt(inspectionRateExt);
            }
            else
            {
              contractInspectionRateExtList.add(inspectionRateExt);
            }
          }
        }
      }
    }
  }

  public static void makeVesselTypeExtRowsForInspectionVersionExt(
    InspectionVersionExt inspectionVersionExt
  )
  {
    if(inspectionVersionExt == null) return;
    List vesselTypeExtList = inspectionVersionExt.getVesselTypeExtList();

    if((vesselTypeExtList != null) && (vesselTypeExtList.size() > 0))
    {
      int columnNumber = 4;
      int rowNumber = vesselTypeExtList.size() / columnNumber;
      if(vesselTypeExtList.size() % columnNumber > 0) rowNumber ++;

      List[] rows = new ArrayList[rowNumber];
      for(int i=0; i<vesselTypeExtList.size(); i++)
      {
        Object obj = vesselTypeExtList.get(i);
        int rowCount = i % rowNumber;
        List columns = rows[rowCount];
        if(columns == null)
        {
          columns = new ArrayList();
          rows[rowCount] = columns;
        }

        columns.add(obj);
      }

      inspectionVersionExt.setVesselTypeExtRows(rows);
    }
  }

  public static int getVesselTypeExtIndex(
    InspectionVersionExt inspectionVersionExt,
    String vesselTypeName
  )
  {
    if((vesselTypeName == null) || (inspectionVersionExt == null)) return -1;
    List vesselTypeExtList = inspectionVersionExt.getVesselTypeExtList();

    if((vesselTypeExtList != null) && (vesselTypeExtList.size() > 0))
    {
      for(int i=0; i<vesselTypeExtList.size(); i++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)vesselTypeExtList.get(i);
        if(vesselTypeName.equals(vtExt.getVesselType().getVesselTypeId().getVesselType()))
        {
          return i;
        }
      }
    }

    return -1;
  }

  public static VesselTypeExt getVesselTypeExt(
    InspectionVersionExt inspectionVersionExt,
    String vesselTypeName
  )
  {
    if((vesselTypeName == null) || (inspectionVersionExt == null)) return null;
    List vesselTypeExtList = inspectionVersionExt.getVesselTypeExtList();

    if((vesselTypeExtList != null) && (vesselTypeExtList.size() > 0))
    {
      for(int i=0; i<vesselTypeExtList.size(); i++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)vesselTypeExtList.get(i);
        if(vesselTypeName.equals(vtExt.getVesselType().getVesselTypeId().getVesselType()))
        {
          return vtExt;
        }
      }
    }

    return null;
  }

  public static void configProductGroupExtList(
    SelectProducts selectProducts,
    Map uomsMapByGroupId
  )
  {
    if(selectProducts == null) return;

    EditInspectionRate editInspectionRate = selectProducts.getEditInspectionRate();
    if(editInspectionRate == null) return;

    VesselTypeExt vesselTypeExt = selectProducts.getVesselTypeExt();
    if(vesselTypeExt == null) return;

    List productGroupExtList = selectProducts.getProductGroupExtList();
    if(productGroupExtList == null) return;

    List contractInspectionRateExtList = vesselTypeExt.getContractInspectionRateExtList();

    for(int i=0; i<productGroupExtList.size(); i++)
    {
      ProductGroupExt productGroupExt = (ProductGroupExt)productGroupExtList.get(i);
      String groupId = productGroupExt.getProductGroup().getProductGroupId().getGroupId();

      productGroupExt.setSelected(false);

      if(contractInspectionRateExtList != null)
      {
        for(int j=0; j<contractInspectionRateExtList.size(); j++)
        {
          InspectionRateExt inspectionRateExt = (InspectionRateExt)contractInspectionRateExtList.get(j);
          if(groupId.equals(inspectionRateExt.getInspectionRate().getInspectionRateId().getGroupId()))
          {
            productGroupExt.setSelected(true);
            productGroupExt.setViewOnly(true);
          }
        }
      }

      if(uomsMapByGroupId != null)
      {
        List uoms = (List)uomsMapByGroupId.get(groupId);
        Integer workingUom = NumberUtil.getInteger(editInspectionRate.getContract().getWorkingUOM());
        Integer selectedUom = null;
        if((uoms != null) && (uoms.size() > 0))
        {
          for(int j=0; j<uoms.size(); j++)
          {
            Integer uom = (Integer)uoms.get(j);
            if(workingUom.equals(uom))
            {
              selectedUom = workingUom;
              break;
            }
          }

          if(selectedUom == null) selectedUom = (Integer)uoms.get(0);
        }

        if(selectedUom != null) productGroupExt.setUom(selectedUom);
        else productGroupExt.setUom(workingUom);
      }
    }
  }

  public static void copyPbInspectionRatesForSelectedProducts(
    SelectProducts selectProducts
  )
  {
    if(selectProducts == null) return;

    InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");
    ZoneService zoneService = (ZoneService)ServiceLocator.getInstance().getBean("zoneService");

    EditInspectionRate editInspectionRate = selectProducts.getEditInspectionRate();
    if(editInspectionRate == null) return;

    VesselTypeExt vesselTypeExt = selectProducts.getVesselTypeExt();
    if(vesselTypeExt == null) return;

    String vesselType = vesselTypeExt.getVesselType().getVesselTypeId().getVesselType();

    List productGroupExtList = selectProducts.getProductGroupExtList();
    if(productGroupExtList == null) return;

    List contractInspectionRateExtList = vesselTypeExt.getContractInspectionRateExtList();
    if(contractInspectionRateExtList == null)
    {
      contractInspectionRateExtList = new ArrayList();
      vesselTypeExt.setContractInspectionRateExtList(contractInspectionRateExtList);
    }

    List zoneIdList = zoneService.getZoneIdsForContractWithDate(
      editInspectionRate.getContract().getContractCode(),
      editInspectionRate.getContract().getWorkingPB(),
      new Date()
    );
    zoneIdList.add("*");

    for(int i=0; i<productGroupExtList.size(); i++)
    {
      ProductGroupExt productGroupExt = (ProductGroupExt)productGroupExtList.get(i);
      String groupId = productGroupExt.getProductGroup().getProductGroupId().getGroupId();

      boolean viewOnly = productGroupExt.getViewOnly();
      if(viewOnly) continue;

      boolean selected = productGroupExt.getSelected();
      if(selected)
      {
        List inspectionRateList = inspectionRateService.getInspectionRateList(
          editInspectionRate.getContract().getWorkingPB(),
          "Inspection",
          vesselType,
          groupId,
          productGroupExt.getUom(),
          zoneIdList
        );

        if(inspectionRateList.size() > 0)
        {
          for(int k=0; k<inspectionRateList.size(); k++)
          {
            InspectionRate inspectionRate = (InspectionRate)inspectionRateList.get(k);

            InspectionRate newInspectionRate = new InspectionRate();
            EntityUtil.copyInspectionRate(newInspectionRate, inspectionRate);

            newInspectionRate.setContractRef(Constants.CONT);

            InspectionRateExt inspectionRateExt = new InspectionRateExt();
            inspectionRateExt.setInspectionRate(newInspectionRate);
            inspectionRateExt.setNewFlag(true);

            newInspectionRate.setRateId(null);
            newInspectionRate.getInspectionRateId().setContractId(editInspectionRate.getContract().getContractCode());

            contractInspectionRateExtList.add(inspectionRateExt);
          }
        }
        else
        {
          InspectionRate inspectionRate = new InspectionRate();
          InspectionRateId inspectionRateId = new InspectionRateId();
          inspectionRate.setInspectionRateId(inspectionRateId);

          inspectionRateId.setExpressionId("Inspection");
          inspectionRateId.setServiceLevel("Y");
          inspectionRateId.setLocation("*");
          inspectionRateId.setWithInspection("Y");
          inspectionRateId.setGroupId(groupId);
          inspectionRateId.setVesselType(vesselType);
          inspectionRateId.setIntData2(0);
          inspectionRateId.setFloatData2(0.0);
          inspectionRateId.setIntData4(productGroupExt.getUom());
          inspectionRateId.setContractId(editInspectionRate.getContract().getContractCode());
          inspectionRate.setIntData3((long)999999);
          inspectionRate.setFloatData3(999999.0);

          inspectionRate.setContractRef(Constants.CONT);

          InspectionRateExt inspectionRateExt = new InspectionRateExt();
          inspectionRateExt.setInspectionRate(inspectionRate);
          inspectionRateExt.setNewFlag(true);

          contractInspectionRateExtList.add(inspectionRateExt);
        }
      }
    }
  }

  public static InspectionVersionExt addNewVersion(
    EditInspectionRate editInspectionRate
  )
  {
    if(editInspectionRate == null) return null;

    InspectionVersionExt inspectionVersionExt = createInspectionVersion(editInspectionRate, true);

    editInspectionRate.setActiveInspectionVersionIndex(editInspectionRate.getInspectionVersionExtList().size() - 1);

    return inspectionVersionExt;
  }

  public static List getRbKeyListFromUomTableList(List uomTableList)
  {
    List rbKeyList = new ArrayList();

    if(uomTableList == null) return rbKeyList;

    for(int i=0; i<uomTableList.size(); i++)
    {
      UomTable uomTable = (UomTable)uomTableList.get(i);

      rbKeyList.add(uomTable.getRbKey());
    }

    return rbKeyList;
  }

  public static List createUomNoteList(
    List rbExtList,
    List uomTableList
  )
  {
    List uomNoteList = new ArrayList();

    if((uomTableList == null) || (rbExtList == null)) return uomNoteList;

    for(int i=0; i<rbExtList.size(); i++)
    {
      RBExt rbExt = (RBExt)rbExtList.get(i);

      UomTable uomTable = getUomTableByRbKey(rbExt.getNotesRb().getRbId().getRbKey(), uomTableList);
      if(uomTable == null) continue;

      UomNote uomNote = new UomNote();
      uomNote.setUom(uomTable.getIntData4().toString());

      uomNote.setRbExt(rbExt);

      uomNoteList.add(uomNote);
    }

    return uomNoteList;
  }

  public static UomTable getUomTableByRbKey(
    String rbKey,
    List uomTableList
  )
  {
    if((rbKey == null) || (uomTableList == null)) return null;

    for(int i=0; i<uomTableList.size(); i++)
    {
      UomTable uomTable = (UomTable)uomTableList.get(i);

      if(rbKey.equals(uomTable.getRbKey())) return uomTable;
    }

    return null;
  }

  public static UomTable getUomTableByUom(
    String uom,
    List uomTableList
  )
  {
    if((uom == null) || (uomTableList == null)) return null;

    for(int i=0; i<uomTableList.size(); i++)
    {
      UomTable uomTable = (UomTable)uomTableList.get(i);

      Integer intData4 = uomTable.getIntData4();

      if(uom.equals(intData4.toString())) return uomTable;
    }

    return null;
  }

  public static UomNote createNewUomNote(
    List pbRbList,
    List uomTableList
  )
  {
    if((pbRbList == null) || (uomTableList == null)) return null;

    if(pbRbList.size() <= 0) return null;

    RB rb = (RB)pbRbList.get(0);

    UomTable uomTable = getUomTableByRbKey(
      rb.getRbId().getRbKey(),
      uomTableList
    );

    if(uomTable == null) return null;

    UomNote uomNote = new UomNote();

    uomNote.setUom(uomTable.getIntData4().toString());

    RBExt newRbExt = new RBExt();

    RB notesRb = new RB();
    EntityUtil.copyRb(notesRb, rb);
    newRbExt.setNotesRb(notesRb);

    uomNote.setRbExt(newRbExt);

    return uomNote;
  }

  public static void deleteUomNoteByIndex(
    UpdateUomNotes updateUomNotes,
    String indexStr
  )
  {
    if((updateUomNotes == null) || (indexStr == null)) return;
    List uomNoteList = updateUomNotes.getUomNoteList();

    if(uomNoteList == null) return;

    int index = -1;
    try
    {
      index = new Integer(indexStr).intValue();
    }
    catch(Exception e)
    {
    }

    if((index >= 0) && (index < uomNoteList.size()))
    {
      UomNote uomNote = (UomNote)uomNoteList.remove(index);
      updateUomNotes.getDeletedUomNoteList().add(uomNote);
    }
  }

  public static void changeUomNoteByIndex(
    UpdateUomNotes updateUomNotes,
    String indexStr
  )
  {
    if((updateUomNotes == null) || (indexStr == null)) return;
    List uomNoteList = updateUomNotes.getUomNoteList();

    if(uomNoteList == null) return;

    int index = -1;
    try
    {
      index = new Integer(indexStr).intValue();
    }
    catch(Exception e)
    {
    }

    if((index >= 0) && (index < uomNoteList.size()))
    {
      UomNote uomNote = (UomNote)uomNoteList.get(index);

      UomTable uomTable = getUomTableByUom(uomNote.getUom(), updateUomNotes.getUomTableList());

      if(uomTable != null)
      {
        RB rb = RbUtil.getRBByRbKey(uomTable.getRbKey(), updateUomNotes.getPbRbList());
        if(rb != null)
        {
          RB notesRb = new RB();
          EntityUtil.copyRb(notesRb, rb);
          uomNote.getRbExt().setNotesRb(notesRb);
        }
      }
    }
  }

  public static void saveUpdateUomNotes(UpdateUomNotes updateUomNotes)
  {
    if(updateUomNotes == null) return;
    List uomNoteList = updateUomNotes.getUomNoteList();
    if(uomNoteList == null) return;

    Contract contract = updateUomNotes.getEditInspectionRate().getContract();

    for(int i=0; i<uomNoteList.size(); i++)
    {
      UomNote uomNote = (UomNote)uomNoteList.get(i);

      uomNote.getRbExt().getNotesRb().getRbId().setContractId(contract.getContractCode());
    }

    InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");

    inspectionRateService.saveUpdateUomNotes(updateUomNotes);
  }

  public static void adjustBeginEndDatesIfNecessary(EditInspectionRate editInspectionRate)
  {
    if(editInspectionRate == null) return;

    int versionIndex = editInspectionRate.getActiveInspectionVersionIndex();
    List inspectionVersionExtList = editInspectionRate.getInspectionVersionExtList();

    InspectionVersionExt inspectionVersionExt = null;
    if((versionIndex >= 0) && (versionIndex < inspectionVersionExtList.size()))
    {
      inspectionVersionExt = (InspectionVersionExt)inspectionVersionExtList.get(versionIndex);
    }

    if(inspectionVersionExt == null) return;

    InspectionVersionExt inspectionVersionExt1 = inspectionVersionExt;
    for(int i=versionIndex-1; i>=0; i--)
    {
      InspectionVersionExt inspectionVersionExt2 = (InspectionVersionExt)inspectionVersionExtList.get(i);
      adjustInspectionVersionBeginDateAndEndDateIfNecessary(inspectionVersionExt1, inspectionVersionExt2, false);

      inspectionVersionExt1 = inspectionVersionExt2;
    }

    inspectionVersionExt1 = inspectionVersionExt;
    for(int i=versionIndex+1; i<inspectionVersionExtList.size(); i++)
    {
      InspectionVersionExt inspectionVersionExt2 = (InspectionVersionExt)inspectionVersionExtList.get(i);
      adjustInspectionVersionBeginDateAndEndDateIfNecessary(inspectionVersionExt1, inspectionVersionExt2, true);

      inspectionVersionExt1 = inspectionVersionExt2;
    }
  }

  public static void adjustInspectionVersionBeginDateAndEndDateIfNecessary(
    InspectionVersionExt inspectionVersionExt1,
    InspectionVersionExt inspectionVersionExt2,
    boolean ascend
  )
  {
    if((inspectionVersionExt1 == null) || (inspectionVersionExt2 == null)) return;

    Date[] datePair1 = new Date[2];
    datePair1[0] = inspectionVersionExt1.getInspectionVersion().getInspectionVersionId().getBeginDate();
    datePair1[1] = inspectionVersionExt1.getInspectionVersion().getEndDate();

    Date[] datePair2 = new Date[2];
    datePair2[0] = inspectionVersionExt2.getInspectionVersion().getInspectionVersionId().getBeginDate();
    datePair2[1] = inspectionVersionExt2.getInspectionVersion().getEndDate();

    if(ascend) DateUtil.adjustBeginDateAndEndDateAscend(datePair1, datePair2);
    else DateUtil.adjustBeginDateAndEndDateDescend(datePair1, datePair2);

    inspectionVersionExt2.getInspectionVersion().getInspectionVersionId().setBeginDate(datePair2[0]);
    inspectionVersionExt2.getInspectionVersion().setEndDate(datePair2[1]);
  }

  public static void deleteVersion(
    EditInspectionRate editInspectionRate,
    int versionIndex
  )
  {
    if(editInspectionRate == null) return;
    List inspectionVersionExtList = editInspectionRate.getInspectionVersionExtList();
    if(inspectionVersionExtList == null) return;

    if(versionIndex < 0 || versionIndex >= inspectionVersionExtList.size()) return;

    InspectionVersionExt inspectionVersionExt = (InspectionVersionExt)inspectionVersionExtList.remove(versionIndex);
    editInspectionRate.getDeletedInspectionVersionExtList().add(
      inspectionVersionExt
    );

    // adjust active version index
    int activeVersionIndex = editInspectionRate.getActiveInspectionVersionIndex();
    if(activeVersionIndex >= versionIndex)
    {
      activeVersionIndex --;
      if(activeVersionIndex < 0)
      {
        if(inspectionVersionExtList.size() > 0) activeVersionIndex = 0;
      }

      editInspectionRate.setActiveInspectionVersionIndex(activeVersionIndex);
    }
  }

  public static List getNextInspectionRateExtListByCurrentInspectionRate(
    List inspectionRateExtList,
    InspectionRate inspectionRate,
    int startIndex
  )
  {
    List results = new ArrayList();
    if((inspectionRateExtList == null) || (inspectionRate == null)) return results;

    for(int i=startIndex; i<inspectionRateExtList.size(); i++)
    {
      InspectionRateExt inspectionRateExt = (InspectionRateExt)inspectionRateExtList.get(i);
      InspectionRate tmpInspectionRate = inspectionRateExt.getInspectionRate();

      if(tmpInspectionRate == null) continue;

      if(inspectionRate.getInspectionRateId().getLocation().equals(tmpInspectionRate.getInspectionRateId().getLocation())
        && inspectionRate.getInspectionRateId().getExpressionId().equals(tmpInspectionRate.getInspectionRateId().getExpressionId())
        && inspectionRate.getInspectionRateId().getGroupId().equals(tmpInspectionRate.getInspectionRateId().getGroupId())
        && inspectionRate.getInspectionRateId().getVesselType().equals(tmpInspectionRate.getInspectionRateId().getVesselType())
        && inspectionRate.getInspectionRateId().getWithInspection().equals(tmpInspectionRate.getInspectionRateId().getWithInspection())
        && inspectionRate.getInspectionRateId().getFloatData2().equals(tmpInspectionRate.getInspectionRateId().getFloatData2())
        && inspectionRate.getInspectionRateId().getIntData4().equals(tmpInspectionRate.getInspectionRateId().getIntData4())
        && inspectionRate.getInspectionRateId().getServiceLevel().equals(tmpInspectionRate.getInspectionRateId().getServiceLevel())
      )
      {
        results.add(inspectionRateExt);
      }
    }

    return results;
  }

  public static void copyProductGroupByChangeType(
    InspectionVersionExt inspectionVersionExt,
    String changeType
  )
  {
    if((inspectionVersionExt == null) || (changeType == null)) return;

    VesselTypeExt vesselTypeExt = inspectionVersionExt.getActiveVesselTypeExt();
    if(vesselTypeExt == null) return;

    List inspectionRateExtList = vesselTypeExt.getContractInspectionRateExtList();
    if(inspectionRateExtList == null) return;

    for(int i=0; i<inspectionRateExtList.size(); i++)
    {
      InspectionRateExt inspectionRateExt = (InspectionRateExt)inspectionRateExtList.get(i);

      if("fromTab1".equals(changeType))
      {
        inspectionRateExt.setGroupIdTab2(inspectionRateExt.getInspectionRate().getInspectionRateId().getGroupId());
        inspectionRateExt.setGroupIdTab3(inspectionRateExt.getInspectionRate().getInspectionRateId().getGroupId());
      }
      else if("fromTab2".equals(changeType))
      {
        inspectionRateExt.getInspectionRate().getInspectionRateId().setGroupId(inspectionRateExt.getGroupIdTab2());
        inspectionRateExt.setGroupIdTab3(inspectionRateExt.getGroupIdTab2());
      }
      else if("fromTab3".equals(changeType))
      {
        inspectionRateExt.getInspectionRate().getInspectionRateId().setGroupId(inspectionRateExt.getGroupIdTab3());
        inspectionRateExt.setGroupIdTab2(inspectionRateExt.getGroupIdTab3());
      }
    }
  }

  public static void addInspectionRates(
    InspectionVersionExt inspectionVersionExt,
    int inspectionRateIndex
  )
  {
    if((inspectionVersionExt == null) || (inspectionRateIndex < 0)) return;

    VesselTypeExt vesselTypeExt = inspectionVersionExt.getActiveVesselTypeExt();
    if(vesselTypeExt == null) return;

    List inspectionRateExtList = vesselTypeExt.getContractInspectionRateExtList();
    if(inspectionRateExtList == null) return;

    if(inspectionRateIndex >= inspectionRateExtList.size()) return;

    int rowsToAdd = vesselTypeExt.getRowsToAdd();
    if(rowsToAdd <= 0) rowsToAdd = 1;
    vesselTypeExt.setRowsToAdd(rowsToAdd);

    InspectionRateExt inspectionRateExt = (InspectionRateExt)inspectionRateExtList.get(inspectionRateIndex);

    boolean addAtEnd = inspectionRateIndex == inspectionRateExtList.size() - 1;

    for(int i=0; i<rowsToAdd; i++)
    {
      InspectionRateExt newInspectionRateExt = new InspectionRateExt();

      InspectionRate newInspectionRate = new InspectionRate();
      EntityUtil.copyInspectionRate(newInspectionRate, inspectionRateExt.getInspectionRate());

      newInspectionRate.setRateId(null);
      newInspectionRateExt.setInspectionRate(newInspectionRate);

      if(addAtEnd)
      {
        inspectionRateExtList.add(newInspectionRateExt);
      }
      else
      {
        inspectionRateExtList.add(inspectionRateIndex + 1, newInspectionRateExt);
      }
    }
  }

  public static void fillVesselMax(InspectionVersionExt inspectionVersionExt)
  {
    if(inspectionVersionExt == null) return;

    VesselTypeExt vesselTypeExt = inspectionVersionExt.getActiveVesselTypeExt();
    if(vesselTypeExt == null) return;

    double vesselMax = vesselTypeExt.getVesselMax();
    if(vesselMax < 0) vesselMax = 0;
    vesselTypeExt.setVesselMax(vesselMax);

    List inspectionRateExtList = vesselTypeExt.getContractInspectionRateExtList();
    if(inspectionRateExtList == null) return;

    for(int i=0; i<inspectionRateExtList.size(); i++)
    {
      InspectionRateExt inspectionRateExt = (InspectionRateExt)inspectionRateExtList.get(i);

      inspectionRateExt.getInspectionRate().setMaximumPrice(vesselMax);
    }
  }

  public static void configTowMaxInspectionRateExt(InspectionVersionExt inspectionVersionExt)
  {
    if(inspectionVersionExt == null) return;

    CfgContract cfgContract = inspectionVersionExt.getCfgContract();
    if(cfgContract == null) return;

    List vesselTypeExtList = inspectionVersionExt.getVesselTypeExtList();
    if(vesselTypeExtList == null) return;

    Boolean towMaxInd = inspectionVersionExt.getInspectionVersion().getTowMaxInd();
    if((towMaxInd == null) || (towMaxInd.booleanValue() == false)) return;

    for(int i=0; i<vesselTypeExtList.size(); i++)
    {
      VesselTypeExt vtExt = (VesselTypeExt)vesselTypeExtList.get(i);

      if("InlandBarge".equals(vtExt.getVesselType().getVesselTypeId().getVesselType()))
      {
        InspectionRateExt towMaxInspectionRateExt = vtExt.getTowMaxInspectionRateExt();
        if(towMaxInspectionRateExt == null)
        {
          InspectionRateExt inspectionRateExt = new InspectionRateExt();
          InspectionRate inspectionRate = new InspectionRate();
          InspectionRateId inspectionRateId = new InspectionRateId();
          inspectionRate.setInspectionRateId(inspectionRateId);
          inspectionRateExt.setInspectionRate(inspectionRate);

          inspectionRateId.setExpressionId("InspectionTow");
          inspectionRateId.setVesselType("InlandBarge");
          inspectionRateId.setGroupId("*");
          inspectionRateId.setLocation("*");
          inspectionRateId.setWithInspection("Y");
          inspectionRateId.setServiceLevel("Y");
          inspectionRateId.setIntData2(0);
          inspectionRateId.setIntData4(1);
          inspectionRateId.setFloatData2(0.0);

          inspectionRate.setRateId(null);
          inspectionRate.setIntData3(9999999l);
          inspectionRate.setFloatData3(999999.0);
          inspectionRate.setContractRef(Constants.CONT);
          inspectionRate.setCurrencyCD(cfgContract.getCurrencyCD());

          vtExt.setTowMaxInspectionRateExt(inspectionRateExt);
        }
      }
    }
  }

  public static void sortContractInspectionRateListByProductGroup(
    InspectionVersionExt inspectionVersionExt,
    Map productGroupDropDownMap
  )
  {
    if(inspectionVersionExt == null) return;

    List vesselTypeExtList = inspectionVersionExt.getVesselTypeExtList();
    if(vesselTypeExtList == null) return;

    for(int i=0; i<vesselTypeExtList.size(); i++)
    {
      VesselTypeExt vtExt = (VesselTypeExt)vesselTypeExtList.get(i);

      List contractInspectionRateExtList = vtExt.getContractInspectionRateExtList();
      if(contractInspectionRateExtList == null) continue;

      Collections.sort(contractInspectionRateExtList, new InspectionRateExtComparator(productGroupDropDownMap));
    }
  }
}

