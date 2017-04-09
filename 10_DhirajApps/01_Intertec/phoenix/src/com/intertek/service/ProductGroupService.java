package com.intertek.service;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import com.intertek.domain.EditProductGroupSet;
import com.intertek.entity.ProductGroupSet;

public interface ProductGroupService
{
  List getProductGroupSetNameList(String productGroupSetName);
  List getProductGroupSetNameList();
  List getProductGroupSetList();
  ProductGroupSet getProductGroupSetByName(String productGroupSetName);
  ProductGroupSet getDefaultProductGroupSet();

  boolean existProductGroupSetByName(String productGroupSetName);
  List getProductGroupsByProductGroupSetNameWithEqualBeginDate(
      String productGroupSetName,
      Date date
  );
  List getProductGroupsByProductGroupSetName(
      String productGroupSetName,
      Date date
  );

  List getProductGroupSetDateListByContractId(String contractId);
  List getGroupIdAndRbKeyListByProductGroupSetAndDates(
      String productGroupSet,
      Date beginDate,
      Date endDate
  );
  List getGroupIdAndRbKeyListByProductGroupSet(
      String productGroupSet
  );

  void saveEditProductGroupSet(EditProductGroupSet editProductGroupSet);
}


