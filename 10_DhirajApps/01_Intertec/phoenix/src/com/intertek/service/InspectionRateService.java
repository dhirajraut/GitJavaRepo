package com.intertek.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.intertek.domain.EditInspectionRate;
import com.intertek.domain.UpdateUomNotes;
import com.intertek.entity.Contract;
import com.intertek.entity.InspectionRate;
import com.intertek.entity.InspectionRateId;

public interface InspectionRateService
{
  List getInspectionVersionListByContractCode(
      String contractCode
  );

  List getInspectionRateListByContractIdAndVesselTypeNameListAndDates(
      String contractId,
      List vesselTypeNameList,
      Date beginDate
  );

  Map getUomsMapByGroupId(
    String contractId,
    String expressionId,
    String vesselType
  );

  List getInspectionRateList(
      String contractId,
      String expressionId,
      String vesselType,
      String groupId,
      Integer uom,
      List zoneIdList
  );

  List getInspectionContractExpressionList();

  List getInspectionControlList();

  List getContractExpressionList(
      String contractId,
      Date beginDate,
      String[] serviceNames
  );

  List getControlList(
      String contractId,
      Date beginDate,
      String[] serviceNames
  );

  List getUomTableList();

  void saveEditInspectionRate(EditInspectionRate editInspectionRate);

  void saveUpdateUomNotes(UpdateUomNotes updateUomNotes);
}

