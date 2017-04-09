package com.intertek.service;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import com.intertek.domain.AddContract;
import com.intertek.domain.ContractSearch;
import com.intertek.domain.EditRBExt;
import com.intertek.domain.ServiceRates;
import com.intertek.domain.RBExt;
import com.intertek.domain.ServiceExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.HighLevelService;
import com.intertek.entity.ProductGroupSet;
import com.intertek.entity.RB;
import com.intertek.entity.VesselTypeSet;
import com.intertek.entity.Service;
import com.intertek.pagination.Pagination;

public interface RbService
{
  List getRbList(String contractId, String rbKey);
  List getRbList(String contractId, Date date, List rbKeyList);
  List getRbListWithEqualBeginDate(String contractId, Date date, List rbKeyList);
  List getRbList(String contractId, List rbKeyList);

  RB getRb(String contractId, String rbKey);
  RB getRb(String rbKey);
  RB getRB(String contractId, String rbKey, Date beginDate);
  RB getRB(String contractId, String rbKey, Date beginDate, Date endDate);

  void saveRbExt(RBExt rbExt);
  void saveNotesRb(RBExt rbExt, Date beginDate, Date endDate);
  void removeRb(RB rb);

  void removeRbsByContractIdAndBeginDateForGroup(
      String contractId,
      Date beginDate
  );

  void removeRbsByContractIdAndBeginDateForVessel(
    String contractId,
    Date beginDate
  );
}


