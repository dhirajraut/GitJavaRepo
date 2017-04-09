package com.intertek.domain;


public class CurrencySearch extends Search
{

  private StringSearchField currencyCd;
  private StringSearchField currencyDescr;
  private String searchForm;
  private String inputFieldId;
  private String sortFlag;

 public CurrencySearch()
  {
    currencyCd = new StringSearchField();
    currencyDescr = new StringSearchField();
    }   					

 
  	    public void setCurrencyDescr(StringSearchField currencyDescr)
  {
    this.currencyDescr = currencyDescr;
  }

  public StringSearchField getCurrencyDescr()
  {
    return currencyDescr;
  }

  public void setCurrencyCd(StringSearchField currencyCd)
  {
    this.currencyCd = currencyCd;
  }

  public StringSearchField getCurrencyCd()
  {
    return currencyCd;
  }

  
  public String getInputFieldId()
  {
		return inputFieldId;
	}

	public void setInputFieldId(String inputFieldId)
	{
		this.inputFieldId = inputFieldId;
	}

    public void setSearchForm(String searchForm)
  {
    this.searchForm = searchForm;
  }

  public String getSearchForm()
  {
    return searchForm;
  }
public String getSortFlag() {
	return sortFlag;
}


public void setSortFlag(String sortFlag) {
	this.sortFlag = sortFlag;
}
	

}