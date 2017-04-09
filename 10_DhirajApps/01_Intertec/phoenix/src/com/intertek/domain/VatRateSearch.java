package com.intertek.domain;


public class VatRateSearch extends Search
{

  private StringSearchField taxCode;
  private StringSearchField description;
  private StringSearchField effDate;
  private StringSearchField taxPct;	
// private StringSearchField taxType ;
  private String searchForm;
  private String pageNo;
  private String inputFieldId;
  private String inputFieldId1;
  private String[]  searchFieldId1 ;
  private String inputFieldId2;

  private String rowNum;
  private String vatCodeId;
  private String div1;
  private String div2;
  private String taxType;
  private String sortFlag;
//START : #119240 : 22/06/09
  private String currentSortFlag;
  private String prevSortFlag;
  private String sortOrderFlag;
  // END : #119240 : 22/06/09

 public VatRateSearch()
  {
    taxCode = new StringSearchField();
	description = new StringSearchField();
    effDate = new StringSearchField();
    taxPct = new StringSearchField();
//	taxType=new StringSearchField();
	  pageNo = new String();
  }   					

 
  	    public void setTaxCode(StringSearchField taxCode)
  {
    this.taxCode = taxCode;
  }

  public StringSearchField getTaxCode()
  {
    return taxCode;
  }
public void setDescription(StringSearchField description)
  {
    this.description = description;
  }

  public StringSearchField getDescription()
  {
    return description;
  }

  public void setEffDate(StringSearchField effDate)
  {
    this.effDate = effDate;
  }

  public StringSearchField getEffDate()
  {
    return effDate;
  }

  public void setTaxPct(StringSearchField taxPct)
  {
    this.taxPct = taxPct;
  }

  public StringSearchField getTaxPct()
  {
    return taxPct;
  }

  
/*public void setTaxType(StringSearchField taxType)
  {
    this.taxType = taxType;
  }

  public StringSearchField getTaxType()
  {
    return taxType;
  }  */

public void setTaxType(String taxType)
  {
    this.taxType = taxType;
  }

  public String getTaxType()
  {
    return taxType;
  }  

  public void setSearchForm(String searchForm)
  {
    this.searchForm = searchForm;
  }

  public String getSearchForm()
  {
    return searchForm;
  }
  public void setPageNo(String pageNo)
  {
    this.pageNo = pageNo;
  }

  public String getPageNo()
  {
    return pageNo;
  }  

  public String getInputFieldId()
  {
		return inputFieldId;
	}

	public void setInputFieldId(String inputFieldId)
	{
		this.inputFieldId = inputFieldId;
	}

  public String getRowNum()
  {
		return rowNum;
	}

	public void setRowNum(String rowNum)
	{
		this.rowNum = rowNum;
	}

	 public String getVatCodeId()
  {
		return vatCodeId;
	}

	public void setVatCodeId(String vatCodeId)
	{
		this.vatCodeId = vatCodeId;
	}
	
	 public String getInputFieldId1()
  {
		return inputFieldId1;
	}

	public void setInputFieldId1(String  InputFieldId1)
	{
		this.inputFieldId1 = inputFieldId1;
	}

	  public String[] getSearchFieldId1()
  {
		return searchFieldId1;
	}

	public void setSearchFieldId1(String[]  searchFieldId1)
	{
		this.searchFieldId1 = searchFieldId1;
	}


	  public String getInputFieldId2()
  {
		return inputFieldId2;
	}

	public void setInputFieldId2(String inputFieldId2)
	{
		this.inputFieldId2 = inputFieldId2;
	}	  
  public String getDiv1()
  {
		return div1;
	}

	public void setDiv1(String div1)
	{
		this.div1 = div1;
	}	  
 public String getDiv2()
  {
		return div2;
	}

	public void setDiv2(String div2)
	{
		this.div2 = div2;
	}	  

	public String getSortFlag() {
		return sortFlag;
	}


	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
	}

	//START : #119240 : 22/06/09
	public String getCurrentSortFlag() {
		return currentSortFlag;
	}


	public void setCurrentSortFlag(String currentSortFlag) {
		this.currentSortFlag = currentSortFlag;
	}


	public String getPrevSortFlag() {
		return prevSortFlag;
	}


	public void setPrevSortFlag(String prevSortFlag) {
		this.prevSortFlag = prevSortFlag;
	}


	public String getSortOrderFlag() {
		return sortOrderFlag;
	}


	public void setSortOrderFlag(String sortOrderFlag) {
		this.sortOrderFlag = sortOrderFlag;
	}
	
	// END : #119240 : 22/06/09
}
