package com.intertek.service;

import java.util.List;
import java.util.Set;

import com.intertek.entity.JobOrder;
import com.intertek.entity.RequiredField;

public interface RequiredFieldService
{
  void saveRequiredField(RequiredField rf);

  List getRequiredFieldsByClassName(String className);
  RequiredField getRequiredFieldsByClassNameAndFieldName(String className, String fieldName);

  List checkRequiredFields(Object obj, String className, String type, Set ignoreFieldNames);
  Object checkRequiredField(Object obj, String className, String fieldName, String type);

  List checkRequiredFieldsForJobOrder(String jobNumber);
  JobOrder getJobOrderForRequiredFieldChecking(String jobNumber);

  List<RequiredField> getRequiredFields();
}

