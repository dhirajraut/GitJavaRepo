package com.intertek.service;

import java.util.List;

import com.intertek.entity.DropDowns;
import com.intertek.exception.ServiceException;

public class DropdownServiceImpl extends BaseService implements DropdownService
{
  public List getDropdownTypes()
  {
    return getDao().find(" from DropDowns d ",null);
  }
  public List getDropDownsByDropdownType(String dropdownType)
  {
  //  return getDao().find(" from DropDowns d where d.dropDownId.dropdownType = '"+dropdownType+"' order by d.fieldValue ",null);
  List dropdowns=getDao().find(" from DropDowns d where d.dropDownId.dropdownType = '"+dropdownType+"' order by d.fieldValue ",null);
  if(dropdowns!= null && dropdowns.size()>=0)
    { return dropdowns;}
  else
    {
     throw new ServiceException("dropdown.not.exist.error", new Object[]{dropdownType}, null);
    }
  }
  public void deleteDropdown(DropDowns dropdown)
  {
    getDao().remove(dropdown);
  }
  public void saveDropdown(DropDowns dropdown)
  {
    getDao().save(dropdown);
  }

  public DropDowns getDropdownByDropdownCodeAndType(String code,String type)
  {
    List dropdowns =  getDao().find(" from DropDowns d where d.dropDownId.dropdownType = '"+type+"' and d.dropDownId.fieldName = '"+code+"' ",null);
    if(dropdowns == null || dropdowns.size() <=0)
      return null;
    return ((DropDowns) dropdowns.get(0));
  }
}

