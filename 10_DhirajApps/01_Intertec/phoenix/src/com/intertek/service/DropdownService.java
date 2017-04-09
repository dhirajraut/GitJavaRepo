package com.intertek.service;

import java.util.List;

import com.intertek.entity.DropDowns;

public interface DropdownService
{

List getDropdownTypes();
List getDropDownsByDropdownType(String dropdownType);
void deleteDropdown(DropDowns dropdown);
void saveDropdown(DropDowns dropdown);
DropDowns getDropdownByDropdownCodeAndType(String code,String type);
}

