package com.intertek.domain;

import java.util.ArrayList;
import java.util.List;


import com.intertek.entity.TaxLabel;

public class AddTaxLabels  extends Search
{

private String addOrDeleteTaxLabel;
private TaxLabel[] taxLabels;
private TaxLabel taxLabel;
private int taxLabelCount ;
private int taxLabelIndex;
private String taxLabelFlag;
private String rowNum;
private String taxLableId;
List deletedList=new ArrayList();
private String pageNo;
private String pageFlag;
private String taxType;
private String txcel="false";



public String getRowNum(){
return rowNum;
}

public void setRowNum(String rowNum){
this.rowNum = rowNum;
}


public List getDeletedList()
{
return deletedList;
}

public void setDeletedList(List deletedList)
{
this.deletedList=deletedList;
}
 public String getPageNo()
  {
    return pageNo;
  }  
 public void setPageNo(String pageNo)
  {
    this.pageNo = pageNo;
  }

  public String getPageFlag(){
return pageFlag;
}

public void setPageFlag(String pageFlag){
this.pageFlag = pageFlag;
}
public String getTaxType(){
return taxType;
}
public void setTaxType(String taxType){
this.taxType = taxType;
}
public String getTxcel(){
return txcel;
}

public void setTxcel(String txcel){
this.txcel=txcel;
}


public String getTaxLableId() {
	return taxLableId;
}

public void setTaxLableId(String taxLableId) {
	this.taxLableId = taxLableId;
}

public String getAddOrDeleteTaxLabel() {
	return addOrDeleteTaxLabel;
}

public void setAddOrDeleteTaxLabel(String addOrDeleteTaxLabel) {
	this.addOrDeleteTaxLabel = addOrDeleteTaxLabel;
}

public TaxLabel getTaxLabel() {
	return taxLabel;
}

public void setTaxLabel(TaxLabel taxLabel) {
	this.taxLabel = taxLabel;
}

public int getTaxLabelCount() {
	return taxLabelCount;
}

public void setTaxLabelCount(int taxLabelCount) {
	this.taxLabelCount = taxLabelCount;
}

public String getTaxLabelFlag() {
	return taxLabelFlag;
}

public void setTaxLabelFlag(String taxLabelFlag) {
	this.taxLabelFlag = taxLabelFlag;
}

public int getTaxLabelIndex() {
	return taxLabelIndex;
}

public void setTaxLabelIndex(int taxLabelIndex) {
	this.taxLabelIndex = taxLabelIndex;
}

public TaxLabel[] getTaxLabels() {
	return taxLabels;
}

public void setTaxLabels(TaxLabel[] taxLabels) {
	this.taxLabels = taxLabels;
}


}
