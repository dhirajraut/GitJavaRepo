package com.intertek.service;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import com.intertek.domain.EditVesselTypeSet;
import com.intertek.entity.VesselTypeSet;

public interface VesselTypeService
{
  List getVesselTypeSetNameList(String vesselTypeSetName);
  List getVesselTypeSetNameList();
  List getVesselTypeSetList();
  VesselTypeSet getVesselTypeSetByName(String vesselTypeSetName);
  VesselTypeSet getDefaultVesselTypeSet();

  boolean existVesselTypeSetByName(String vesselTypeSetName);
  List getVesselTypesByVesselTypeSetNameWithEqualBeginDate(
      String vesselTypeSetName,
      Date date
  );
  List getVesselTypesByVesselTypeSetName(
    String vesselTypeSetName,
    Date date
  );

  List getVesselSetDateListByContractId(String contractId);
  List getVesselTypeAndRbKeyListByVesselSetAndDates(
      String vesselSet,
      Date beginDate,
      Date endDate
  );
  List getVesselTypeAndRbKeyListByVesselSet(
      String vesselSet
  );

  void saveEditVesselTypeSet(EditVesselTypeSet editVesselTypeSet);
}


