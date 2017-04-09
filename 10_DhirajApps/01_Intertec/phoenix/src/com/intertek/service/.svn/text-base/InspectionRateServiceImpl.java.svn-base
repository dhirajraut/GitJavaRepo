package com.intertek.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import com.intertek.calculator.ContractExpressionExt;
import com.intertek.calculator.ControlExt;
import com.intertek.domain.EditInspectionRate;
import com.intertek.domain.InspectionRateExt;
import com.intertek.domain.InspectionVersionExt;
import com.intertek.domain.RBExt;
import com.intertek.domain.UpdateUomNotes;
import com.intertek.domain.UomNote;
import com.intertek.domain.VesselTypeExt;
import com.intertek.entity.Control;
import com.intertek.entity.ControlId;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.ContractExpressionId;
import com.intertek.entity.InspectionVersion;
import com.intertek.entity.InspectionRate;
import com.intertek.entity.InspectionRateId;
import com.intertek.entity.RB;
import com.intertek.entity.RBId;
import com.intertek.pagination.Pagination;
import com.intertek.util.Constants;
import com.intertek.util.EntityUtil;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;

public class InspectionRateServiceImpl extends BaseService implements InspectionRateService
{
  public List getInspectionVersionListByContractCode(
    String contractCode
  )
  {
    return getDao().find(
      "from InspectionVersion o where o.inspectionVersionId.contractId = ? order by o.inspectionVersionId.beginDate",
      new Object[] { contractCode }
    );
  }

  public List getInspectionRateListByContractIdAndVesselTypeNameListAndDates(
    String contractId,
    List vesselTypeNameList,
    Date beginDate
  )
  {
    if((contractId == null)
      || (beginDate == null)
      || (vesselTypeNameList == null)
      || (vesselTypeNameList.size() == 0)
    )
    {
      return new ArrayList();
    }

    StringBuilder sb = new StringBuilder();
    sb.append("select distinct o from InspectionRate o where o.inspectionRateId.contractId = ? and o.inspectionRateId.beginDate = ? and o.inspectionRateId.vesselType in (");

    Object[] params = new Object[vesselTypeNameList.size() + 2];
    params[0] = contractId;
    params[1] = beginDate;

    for(int i=0; i<vesselTypeNameList.size(); i++)
    {
      params[i + 2] = vesselTypeNameList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(")");

    return getDao().find(sb.toString(), params);
  }

  public Map getUomsMapByGroupId(
    String contractId,
    String expressionId,
    String vesselType
  )
  {
    List list = getDao().find(
      "select o.inspectionRateId.groupId, o.inspectionRateId.intData4 from InspectionRate o where o.inspectionRateId.contractId = ? and o.inspectionRateId.expressionId = ? and o.inspectionRateId.vesselType = ?",
      new Object[] { contractId, expressionId, vesselType }
    );

    Map results = new HashMap();
    for(int i=0; i<list.size(); i++)
    {
      Object[] objects = (Object[])list.get(i);

      List uoms = (List)results.get(objects[0]);
      if(uoms == null)
      {
        uoms = new ArrayList();
        results.put(objects[0], uoms);
      }

      uoms.add(objects[1]);
    }

    return results;
  }

  public List getInspectionRateList(
    String contractId,
    String expressionId,
    String vesselType,
    String groupId,
    Integer uom,
    List zoneIdList
  )
  {
    if((contractId == null)
      || (expressionId == null)
      || (vesselType == null)
      || (groupId == null)
      || (uom == null)
      || (zoneIdList == null)
      || (zoneIdList.size() == 0)
    )
    {
      return new ArrayList();
    }

    StringBuilder sb = new StringBuilder();
    sb.append("select distinct o from InspectionRate o where o.inspectionRateId.contractId = ? and o.inspectionRateId.expressionId = ? and o.inspectionRateId.vesselType = ? and o.inspectionRateId.groupId = ? and o.inspectionRateId.intData4 = ? and o.inspectionRateId.location in (");

    Object[] params = new Object[zoneIdList.size() + 5];
    params[0] = contractId;
    params[1] = expressionId;
    params[2] = vesselType;
    params[3] = groupId;
    params[4] = uom;

    for(int i=0; i<zoneIdList.size(); i++)
    {
      params[i + 5] = zoneIdList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(")");

    return getDao().find(sb.toString(), params);
  }

  public List getInspectionContractExpressionList()
  {
    return getDao().find("from InspectionContractExpression", null);
  }

  public List getInspectionControlList()
  {
    return getDao().find("from InspectionControl", null);
  }

  public List getContractExpressionList(
    String contractId,
    Date beginDate,
    String[] serviceNames
  )
  {
    if((contractId == null)
      || (beginDate == null)
      || (serviceNames == null)
      || (serviceNames.length == 0)
    )
    {
      return new ArrayList();
    }

    StringBuilder sb = new StringBuilder();
    sb.append("select distinct o from ContractExpression o where o.contractExpressionId.contractId = ? and o.contractExpressionId.beginDate = ? and o.contractExpressionId.serviceName in (");

    Object[] params = new Object[serviceNames.length + 2];
    params[0] = contractId;
    params[1] = beginDate;

    for(int i=0; i<serviceNames.length; i++)
    {
      params[i + 2] = serviceNames[i];
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(")");

    return getDao().find(sb.toString(), params);
  }

  public List getControlList(
    String contractId,
    Date beginDate,
    String[] serviceNames
  )
  {
    if((contractId == null)
      || (beginDate == null)
      || (serviceNames == null)
      || (serviceNames.length == 0)
    )
    {
      return new ArrayList();
    }

    StringBuilder sb = new StringBuilder();
    sb.append("select distinct o from Control o where o.controlId.contractId = ? and o.controlId.beginDate = ? and o.controlId.serviceName in (");

    Object[] params = new Object[serviceNames.length + 2];
    params[0] = contractId;
    params[1] = beginDate;

    for(int i=0; i<serviceNames.length; i++)
    {
      params[i + 2] = serviceNames[i];
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(")");

    return getDao().find(sb.toString(), params);
  }

  public List getUomTableList()
  {
    return getDao().find("from UomTable", null);
  }

  private void deleteInspectionRateListByContractIdAndDates(
    String contractId,
    Date beginDate
  )
  {
    if((contractId == null) || (beginDate == null)) return;

    getDao().bulkUpdate(
      "delete from InspectionRate o where o.inspectionRateId.contractId = ? and o.inspectionRateId.beginDate = ? ",
      new Object[] { contractId, beginDate }
    );
  }

  private void deleteOldEditInspectionRateData(EditInspectionRate editInspectionRate)
  {
    if(editInspectionRate == null) return;

    List inspectionVersionExtList = editInspectionRate.getInspectionVersionExtList();
    if(inspectionVersionExtList != null)
    {
      for(int i=0; i<inspectionVersionExtList.size(); i++)
      {
        InspectionVersionExt inspectionVersionExt = (InspectionVersionExt)inspectionVersionExtList.get(i);

        deleteOldInspectionVersionExtData(inspectionVersionExt);
      }
    }

    List deletedInspectionVersionExtList = editInspectionRate.getDeletedInspectionVersionExtList();
    if(deletedInspectionVersionExtList != null)
    {
      for(int i=0; i<deletedInspectionVersionExtList.size(); i++)
      {
        InspectionVersionExt inspectionVersionExt = (InspectionVersionExt)deletedInspectionVersionExtList.get(i);

        deleteOldInspectionVersionExtData(inspectionVersionExt);
      }
    }
  }

  private void deleteOldInspectionVersionExtData(
    InspectionVersionExt inspectionVersionExt
  )
  {
    if(inspectionVersionExt == null) return;

    InspectionVersion oldInspectionVersion = inspectionVersionExt.getOldInspectionVersion();
    InspectionVersion inspectionVersion = inspectionVersionExt.getInspectionVersion();
    if(oldInspectionVersion != null)
    {
      getDao().remove(oldInspectionVersion);
    }

    List deletedCeExtList = inspectionVersionExt.getDeletedCeExtList();
    if(deletedCeExtList != null)
    {
      for(int j=0; j<deletedCeExtList.size(); j++)
      {
        ContractExpressionExt ceExt = (ContractExpressionExt)deletedCeExtList.get(j);
        ContractExpression oldCe = ceExt.getOldContractExpression();
        if(oldCe != null) getDao().remove(oldCe);
      }
    }

    List ceExtList = inspectionVersionExt.getCeExtList();
    if(ceExtList != null)
    {
      for(int j=0; j<ceExtList.size(); j++)
      {
        ContractExpressionExt ceExt = (ContractExpressionExt)ceExtList.get(j);
        ContractExpression oldCe = ceExt.getOldContractExpression();
        if(oldCe != null) getDao().remove(oldCe);
      }
    }

    List deletedControlExtList = inspectionVersionExt.getDeletedControlExtList();
    if(deletedControlExtList != null)
    {
      for(int j=0; j<deletedControlExtList.size(); j++)
      {
        ControlExt controlExt = (ControlExt)deletedControlExtList.get(j);
        Control oldControl = controlExt.getOldControl();
        if(oldControl != null) getDao().remove(oldControl);
      }
    }

    List controlExtList = inspectionVersionExt.getControlExtList();
    if(controlExtList != null)
    {
      for(int j=0; j<controlExtList.size(); j++)
      {
        ControlExt controlExt = (ControlExt)controlExtList.get(j);
        Control oldControl = controlExt.getOldControl();
        if(oldControl != null) getDao().remove(oldControl);
      }
    }

    deleteInspectionRateListByContractIdAndDates(
      inspectionVersion.getInspectionVersionId().getContractId(),
      oldInspectionVersion != null ? oldInspectionVersion.getInspectionVersionId().getBeginDate(): inspectionVersion.getInspectionVersionId().getBeginDate()
    );

    RBExt rbExt = inspectionVersionExt.getContractOperationsRbExt();
    if(rbExt != null)
    {
      RB oldRb = rbExt.getRb();
      if(oldRb != null)
      {
        getDao().remove(oldRb);
      }
    }
  }

  public void saveEditInspectionRate(EditInspectionRate editInspectionRate)
  {
    if(editInspectionRate == null) return;

    deleteOldEditInspectionRateData(editInspectionRate);
    SequenceNumberService sequenceNumberService = (SequenceNumberService)ServiceLocator.getInstance().getBean("sequenceNumberService");

    List inspectionVersionExtList = editInspectionRate.getInspectionVersionExtList();
    if(inspectionVersionExtList == null) return;

    for(int i=0; i<inspectionVersionExtList.size(); i++)
    {
      InspectionVersionExt inspectionVersionExt = (InspectionVersionExt)inspectionVersionExtList.get(i);

      InspectionVersion inspectionVersion = inspectionVersionExt.getInspectionVersion();
      getDao().save(inspectionVersion);

      List ceExtList = inspectionVersionExt.getCeExtList();
      if(ceExtList != null)
      {
        for(int j=0; j<ceExtList.size(); j++)
        {
          ContractExpressionExt ceExt = (ContractExpressionExt)ceExtList.get(j);
          ContractExpression ce = ceExt.getContractExpression();

          ce.getContractExpressionId().setBeginDate(inspectionVersion.getInspectionVersionId().getBeginDate());
          ce.setEndDate(inspectionVersion.getEndDate());

          getDao().save(ce);
        }
      }

      List controlExtList = inspectionVersionExt.getControlExtList();
      if(controlExtList != null)
      {
        for(int j=0; j<controlExtList.size(); j++)
        {
          ControlExt controlExt = (ControlExt)controlExtList.get(j);
          Control control = controlExt.getControl();

          control.getControlId().setBeginDate(inspectionVersion.getInspectionVersionId().getBeginDate());
          control.setEndDate(inspectionVersion.getEndDate());

          getDao().save(control);
        }
      }

      List vesselTypeExtList = inspectionVersionExt.getVesselTypeExtList();
      if(vesselTypeExtList != null)
      {
        Boolean towMaxInd = inspectionVersionExt.getInspectionVersion().getTowMaxInd();

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

              inspectionRate.getInspectionRateId().setBeginDate(inspectionVersion.getInspectionVersionId().getBeginDate());
              inspectionRate.setEndDate(inspectionVersion.getEndDate());

              Integer rateId = inspectionRate.getRateId();
              if(rateId == null)
              {
                Long newNumber = sequenceNumberService.getSequenceNumber(Constants.Inspection_Rate_ID_Seq);
                rateId = new Integer(newNumber.intValue());
                inspectionRate.setRateId(rateId);
              }

              getDao().save(inspectionRate);
            }
          }

          if((towMaxInd != null) && (towMaxInd.booleanValue() == true))
          {
            InspectionRateExt towMaxInspectionRateExt = vesselTypeExt.getTowMaxInspectionRateExt();
            if(towMaxInspectionRateExt != null)
            {
              InspectionRate inspectionRate = towMaxInspectionRateExt.getInspectionRate();
              inspectionRate.getInspectionRateId().setBeginDate(inspectionVersion.getInspectionVersionId().getBeginDate());
              inspectionRate.setEndDate(inspectionVersion.getEndDate());

              Integer rateId = inspectionRate.getRateId();
              if(rateId == null)
              {
                Long newNumber = sequenceNumberService.getSequenceNumber(Constants.Inspection_Rate_ID_Seq);
                rateId = new Integer(newNumber.intValue());
                inspectionRate.setRateId(rateId);
              }

              getDao().save(inspectionRate);
            }
          }

        }
      }

      if(inspectionVersion.getVesselMaxInd()
        || inspectionVersion.getAdditonalVesselInd()
        || inspectionVersion.getTowMaxInd()
        || inspectionVersion.getNumGradesInd()
      )
      {
        RBExt rbExt = inspectionVersionExt.getContractOperationsRbExt();
        if(rbExt == null)
        {
          rbExt = new RBExt();
          RB notesRb = new RB();
          RBId rbId = new RBId();
          notesRb.setRbId(rbId);
          rbExt.setNotesRb(notesRb);

          rbId.setContractId(Constants.NONE_CAPITAL_LETTERS);
          rbId.setRbKey(inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getContractId() + ".ContractOperations");

          notesRb.setRbValue("com.itscb.pscb.contractoperations.VesselMaxContractOperations");
          notesRb.setRbType("COP");

          inspectionVersionExt.setContractOperationsRbExt(rbExt);
        }

        rbExt.getNotesRb().getRbId().setBeginDate(
          inspectionVersion.getInspectionVersionId().getBeginDate()
        );
        rbExt.getNotesRb().getRbId().setEndDate(
          inspectionVersion.getEndDate()
        );

        getDao().save(rbExt.getNotesRb());
      }
    }
  }

  public void saveUpdateUomNotes(UpdateUomNotes updateUomNotes)
  {
    if(updateUomNotes == null) return;
    List uomNoteList = updateUomNotes.getUomNoteList();
    if(uomNoteList == null) return;

    for(int i=0; i<updateUomNotes.getDeletedUomNoteList().size(); i++)
    {
      UomNote uomNote = (UomNote)updateUomNotes.getDeletedUomNoteList().get(i);

      RBExt rbExt = uomNote.getRbExt();
      RB oldRb = rbExt.getRb();
      if(oldRb != null) getDao().remove(oldRb);
    }

    for(int i=0; i<uomNoteList.size(); i++)
    {
      UomNote uomNote = (UomNote)uomNoteList.get(i);

      RBExt rbExt = uomNote.getRbExt();
      RB oldRb = rbExt.getRb();
      if(oldRb != null) getDao().remove(oldRb);
    }

    for(int i=0; i<uomNoteList.size(); i++)
    {
      UomNote uomNote = (UomNote)uomNoteList.get(i);

      RBExt rbExt = uomNote.getRbExt();
      RB rb = rbExt.getNotesRb();
      if(rb != null) getDao().save(rb);
    }
  }
}
