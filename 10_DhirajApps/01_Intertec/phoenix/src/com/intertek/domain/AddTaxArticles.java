package com.intertek.domain;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.TaxArticle;

public class AddTaxArticles  extends Search
{

private String addOrDeleteTaxArticle;
private TaxArticle[] taxArticles;
private int taxArticleCount ;
private int taxArticleIndex;
private String taxArticleFlag;
private String rowNum;
private String taxArticleId;
List deletedList=new ArrayList();
private String pageNo;
private String pageFlag;
private String taxType;
private String txcel="false";


public String getAddOrDeleteTaxArticle(){
return addOrDeleteTaxArticle;
}

public void setAddOrDeleteTaxArticle(String addOrDeleteTaxArticle){
this.addOrDeleteTaxArticle = addOrDeleteTaxArticle;
}

public TaxArticle[]  getTaxArticles(){
return taxArticles;
}

public void  setTaxArticles(TaxArticle[]  taxArticles){
this.taxArticles = taxArticles;
}

public int getTaxArticleCount(){
return taxArticleCount;
}

public void setTaxArticleCount(int taxArticleCount){
this.taxArticleCount = taxArticleCount;
}

public int getTaxArticleIndex(){
return taxArticleIndex;
}

public void setTaxArticleIndex(int taxArticleIndex){
this.taxArticleIndex = taxArticleIndex;
}

public String getTaxArticleFlag(){
return taxArticleFlag;
}

public void setTaxArticleFlag(String taxArticleFlag){
this.taxArticleFlag = taxArticleFlag;
}
public String getRowNum(){
return rowNum;
}

public void setRowNum(String rowNum){
this.rowNum = rowNum;
}
public String getTaxArticleId()
{
return taxArticleId;
}

public void setTaxArticleId(String taxArticleId)
{
this.taxArticleId = taxArticleId;
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
}
