package com.intertek.domain;

import com.intertek.util.Constants;

public class ConsolidatedInvoiceSearch extends Search
{
  private StringSearchField customerId;
  private StringSearchField customerName;
  private StringSearchField buName;
  private StringSearchField invoiceNumber;
  private String cxcel="false";

 
  private String sortFlag;
  
  public ConsolidatedInvoiceSearch()
  {
    customerId = new StringSearchField(Constants.BEGINS_WITH);
    customerName = new StringSearchField(Constants.BEGINS_WITH);
    buName = new StringSearchField(Constants.EQUALS);
    invoiceNumber = new StringSearchField(Constants.BEGINS_WITH);
  }

public String getSortFlag() {
	return sortFlag;
}
public void setSortFlag(String sortFlag) {
	this.sortFlag = sortFlag;
}

public StringSearchField getBuName() {
	return buName;
}

public void setBuName(StringSearchField buName) {
	this.buName = buName;
}

public StringSearchField getCustomerId() {
	return customerId;
}

public void setCustomerId(StringSearchField customerId) {
	this.customerId = customerId;
}

public StringSearchField getCustomerName() {
	return customerName;
}

public void setCustomerName(StringSearchField customerName) {
	this.customerName = customerName;
}

public StringSearchField getInvoiceNumber() {
	return invoiceNumber;
}

public void setInvoiceNumber(StringSearchField invoiceNumber) {
	this.invoiceNumber = invoiceNumber;
}

public String getCxcel(){
return cxcel;
}

public void setCxcel(String cxcel){
this.cxcel=cxcel;
}


}
