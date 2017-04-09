package com.intertek.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.intertek.domain.AddTaxArticles;
import com.intertek.domain.AddTaxLabels;

import com.intertek.domain.AddTaxRates;
import com.intertek.domain.VatRateSearch;
import com.intertek.entity.TaxArticle;
import com.intertek.entity.TaxCode;
import com.intertek.entity.TaxLabel;
import com.intertek.entity.TaxRate;
import com.intertek.pagination.Pagination;
import com.intertek.exception.ServiceException;

public class TaxServiceImpl extends BaseService implements TaxService
{
  public TaxRate addTaxRate(TaxRate taxRate)
  {

  taxRate=getDao().save(taxRate);
    return taxRate;
  }

public boolean deleteTaxRate(TaxRate taxRate)
{
boolean deletedFlag;
boolean existedtaxRate = checkTaxRate(taxRate.getTaxRateId().getTaxCode());
 if(existedtaxRate==true)
  {
     deletedFlag=true;
    throw new ServiceException("taxRate.delete.error", new Object[] {taxRate.getTaxRateId().getTaxCode()}, null);
  // throw new RuntimeException("taxcode  can not be deleted  :"  + taxRate.getTaxCode());
  }
   else
    {
       getDao().remove(taxRate);
     deletedFlag=false;
    }
    return deletedFlag;
  }
public boolean checkTaxRate(String taxCode)
{
   boolean flag=false;
   List taxCodes = getDao().find("from  CountryVAT c where c.vatCode = '"+ taxCode+"' or c.zeroRatedVATCode='"+taxCode +"'",null);
  // List taxCodes1 = getDao().find("from CustAddress c left join fetch c.taxRate t where t.taxCode=?",new Object[] {taxCode});
   List taxCodes1 = getDao().find("from CustAddress c where c.taxCode= '"+ taxCode+ "'", null);
   if(taxCodes.size() > 0)
   { flag=true;
     return flag;}
   else if(taxCodes1.size()>0)
    {flag=true;
     return flag;}
   else
  return flag;
  }

public void searchVatRate(VatRateSearch search,String sortFlag)
{
  if(search == null) return;

  StringBuffer sb = new StringBuffer();
  List params = new ArrayList();
  boolean hasWhere = false;
    String tc = search.getTaxCode().getValue();
  String desc=search.getDescription().getValue();
  String vatFlag=search.getVatCodeId();
  String ttype=search.getTaxType();
  String tt="S";
  //System.out.println("ttype in taxservice impls is"+ttype);
  String strvatCodeSearch ='%'+tc.toUpperCase() + '%';
  String strdescSearch='%'+desc.toUpperCase() + '%';
    //String strvatFlag=vatFlag.toUpperCase();

  if(vatFlag.equals("val")&&!"".equals(vatFlag.trim()))
  {
    if((tc != null) && !"".equals(tc.trim()))
    {
   //sb.append("where upper(t.taxCode) like ? and t.taxType=? ");
      sb.append("where upper(t.taxRateId.taxCode) like ? and t.taxType=? ");

    params.add(strvatCodeSearch);
    params.add(ttype);
    hasWhere = true;
    }

    if((desc != null) && !"".equals(desc.trim()))
    {
        if(hasWhere) sb.append(" and ");
        else
        {
        hasWhere = true;
        sb.append(" where ");
        }
          sb.append(" upper(t.description) like ? and t.taxType=? ");
          params.add(strdescSearch);
          params.add(ttype);
          hasWhere = true;

    }
    else
        {
      if(hasWhere) sb.append(" and ");
        else
        {
        hasWhere = true;
        sb.append(" where ");
        }
          sb.append("t.taxType=?");
          params.add(ttype);
          hasWhere = true;
    }
  }

  if(vatFlag.equals(""))
  {
    //else if((tc!= null) && !"".equals(tc.trim())&&vatFlag.equals(""))
    if((tc!= null) && !"".equals(tc.trim()))
  {
          if(hasWhere) sb.append(" and ");
        else
        {
        hasWhere = true;
        sb.append(" where ");
        }
       // sb.append("upper(t.taxCode) like ? and upper(t.taxType)=? ");
          sb.append("upper(t.taxRateId.taxCode) like ? and upper(t.taxType)=? ");
        params.add(strvatCodeSearch.toUpperCase());
        params.add(tt);
  }

    //else if((desc!= null) && !"".equals(desc.trim())&&vatFlag.equals(""))
     if((desc!= null) && !"".equals(desc.trim()))
      {
      if(hasWhere) sb.append(" and ");
        else
        {
        hasWhere = true;
        sb.append(" where ");
        }
      sb.append("upper(t.description) like ? and upper(t.taxType)=? ");
      params.add(strdescSearch.toUpperCase());
      params.add(tt);
      }
    else
     {
      if(hasWhere) sb.append(" and ");
      else
        {
        hasWhere = true;
        sb.append(" where ");
        }
        sb.append("t.taxType=?");
        params.add(tt);
        hasWhere = true;
     }
    }


  Pagination pagination = search.getPagination();
  List results = null;
  if(sortFlag != null && sortFlag.equals(""))
  {
  if(pagination != null)
  {
    if(pagination.getTotalRecord() > 0)
    {
      List counts = getDao().find("select count(t.taxRateId.taxCode) from TaxRate t "+ sb.toString(),params.toArray());

      if(counts.size() > 0)
      {
        Number count = (Number)counts.get(0);
        pagination.setTotalRecord(count.intValue());
      }
    }
     results = getDao().find("select distinct t from TaxRate t " + sb.toString() +" order by t.taxRateId.taxCode",params.toArray(),pagination);

    pagination.calculatePages();
  }
  else
  {
    results = getDao().find("select distinct t from TaxRate t "+ sb.toString(),params.toArray());
  }
  search.setResults(results);
  search.setPagination(pagination);
  }else{
    // START : #119240 : 26/06/09 : Fix for VAT Code and Effective Date - sort links, not working 
	//String orderByValue=" order by t."+sortFlag;
	  
	  String orderByValue=" order by t.";
	  if(sortFlag !=null && (sortFlag.equals("taxCode") || sortFlag.equals("effDate"))){
		orderByValue  +=  "taxRateId." + sortFlag;
	  }else{
		  orderByValue += sortFlag;
	  }
	  
	// END : #119240 : 26/06/09
	  
    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find("select count(t.taxRateId.taxCode) from TaxRate t "+ sb.toString(),params.toArray());

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      pagination.calculatePages();
    }
    // START : #119240 : 22/06/09 
    /*results = getDao().find("select distinct t from TaxRate t " + sb.toString() +orderByValue,params.toArray(),pagination);*/
    if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
    	results = getDao().find("select distinct t from TaxRate t " + sb.toString() +orderByValue + " " + search.getSortOrderFlag(),params.toArray(),pagination);
    }else{
    	results = getDao().find("select distinct t from TaxRate t " + sb.toString() +orderByValue,params.toArray(),pagination);
    }
    // END : #119240 : 22/06/09
    //search.setTotalResults(results);
    search.setResults(results);
  }
}

public void searchTaxCodes(VatRateSearch search)
{
     if(search == null) return;

      StringBuffer sb = new StringBuffer();
      List params = new ArrayList();

      boolean hasWhere = false;
      String taxCode = search.getTaxCode().getValue();
      if((taxCode != null) && !"".equals(taxCode.trim()))
      {
        taxCode = '%' + taxCode + '%';
        sb.append(" where upper(tax.taxCodeHeader) like ? ");
        params.add(taxCode.toUpperCase());
        hasWhere = true;
      }


      String taxDesc = search.getDescription().getValue();
      if((taxDesc != null) && !"".equals(taxDesc.trim()))
      {
        taxDesc = '%' + taxDesc + '%';
        if(hasWhere)
              sb.append(" and ");
            else
             {
              hasWhere = true;
              sb.append(" where ");
             }


        sb.append(" upper(tax.taxDescr) like ?");
        params.add(taxDesc.toUpperCase());
        hasWhere = true;
      }

      String taxType = search.getTaxType();
      if((taxType != null) && !"".equals(taxType.trim()))
      {
        if(hasWhere)
              sb.append(" and ");
            else
             {
              hasWhere = true;
              sb.append(" where ");
             }


        sb.append("  tax.taxType = ?");
        params.add(taxType);
        hasWhere = true;
      }


      Pagination pagination = search.getPagination();
      List results = null;

      if(pagination != null)
      {
        if(pagination.getTotalRecord() > 0)
        {
          List counts = getDao().find(
            "select count(tax.taxCodeHeader) from TaxCode tax " + sb.toString(),
            params.toArray()
          );

          if(counts.size() > 0)
          {
            Number count = (Number)counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }

        results = getDao().find(
          "select distinct tax from TaxCode tax  " + sb.toString(),
          params.toArray(),
          pagination
        );

        pagination.calculatePages();
      }
      else
      {
        results = getDao().find(
          "select distinct tax from TaxCode tax  " + sb.toString(),
          params.toArray()
        );
      }

      search.setResults(results);
}


public TaxRate getTaxdateByCode(String name)
{
TaxRate taxrate=null;
List taxRates=getDao().find("from TaxRate t  where t.taxRateId.taxCode = ?", new Object[]{name});
/*if(taxRates.size()>0) return (TaxRate)taxRates.get(0);
return null;*/
if(taxRates!= null && taxRates.size() >0) {
      taxrate = (TaxRate)taxRates.get(0);
    } else{
          throw new ServiceException("taxrate.not.exist.error", new Object[] {name}, null);
    }
  return taxrate;
}

/*public List getAllTaxRates()
{
List taxRates=getDao().find("from TaxRate",null);
System.out.println("size of the taxRates "+taxRates.size());
return taxRates;
}*/
public List getAllTaxRates(AddTaxRates search)
{
Pagination pagination=search.getPagination();
String ttype=search.getTaxType();
  List results = null;

  if(pagination != null)
  {
    if(pagination.getTotalRecord() > 0)
    {
      List counts = getDao().find( "select count(t.taxRateId.taxCode) from TaxRate t where t.taxType=?",new Object[]{ttype});

      if(counts.size() > 0)
      {
        Number count = (Number)counts.get(0);
        pagination.setTotalRecord(count.intValue());
    }
    }
     results = getDao().find( "select distinct t from TaxRate t where t.taxType=? order by t.taxRateId.taxCode",new Object[]{ttype}, pagination);
     pagination.calculatePages();
  }
  else
  {
    results = getDao().find(  "select distinct t from TaxRate t where  t.taxType=? order by t.taxRateId.taxCode", new Object[]{ttype});
  }
search.setResults(results);
search.setPagination(pagination);
return results;
}
public TaxRate getTaxRateByVatCode(String vatCode) {

  List taxRates=getDao().find(
  "from TaxRate t  where t.taxRateId.taxCode = ?", new Object[]{vatCode});
  if(taxRates.size()>0) return (TaxRate)taxRates.get(0);
  return null;

}
public List getTaxRatesByType(String taxType)
{
  List taxRates=getDao().find(
      "from TaxRate t  where t.taxType = ?", new Object[]{taxType});
      if(taxRates.size()>0) return taxRates;
      return null;
}
public List getTaxRatesByCode(String taxCode)
{

  if(taxCode == null) return new ArrayList();
  List taxRates = new ArrayList();
  try {
       /* taxRates = getDao().find(
        "from TaxRate t where upper(t.taxRateId.taxCode) like '" + taxCode.toUpperCase() + "%' and t.taxType = 'S'",
        null
        );*/
     taxRates = getDao().find(
            "from TaxCode t where t.taxCodeHeader like '" + taxCode.toUpperCase() + "%' and t.taxType = 'S'",
            null
            );
  }
  catch(Exception e ){
  System.out.println("Exception i n getTaxRatesByCode :"+ e.toString());
}
return taxRates;
}



public TaxRate getTaxRateByCode(String taxCode)
{
    if(taxCode == null) return null;
  List taxRates = new ArrayList();

  taxRates = getDao().find(
      "from TaxRate t where t.taxRateId.taxCode = '" + taxCode+"'",
      null
    );

   if(taxRates.size() > 0)
    {
     return (TaxRate) taxRates.get(0);
    }

    return null;

}

public List getVatCodesByCode(String vatCode,String taxType)
{
    if(vatCode == null) return new ArrayList();
  List vatCodes = new ArrayList();
try{
    vatCodes = getDao().find("from TaxRate t where upper(t.taxRateId.taxCode) like '" + vatCode.toUpperCase() + "%' and t.taxType='"+taxType+"'",null);
}
catch(Exception e ){
System.out.println("Exception in getVatCodesByCode :"+ e.toString());
}
return vatCodes;
}


public List getAllTaxArticles(AddTaxArticles search)
{
Pagination pagination=search.getPagination();
//String ttype=search.getTaxType();
  List results = null;

  if(pagination != null)
  {
    if(pagination.getTotalRecord() > 0)
    {
      List counts = getDao().find(" select count(t.taxArticleCode) from TaxArticle t ", null);

      if(counts.size() > 0)
      {
        Number count = (Number)counts.get(0);
        pagination.setTotalRecord(count.intValue());
    }
    }
     results = getDao().find(" select distinct t from TaxArticle t order by t.taxArticleDescription ",null , pagination);
     pagination.calculatePages();
  }
  else
  {
    results = getDao().find(" select distinct t from TaxArticle t order by t.taxArticleDescription " , null);
  }
search.setResults(results);
search.setPagination(pagination);
return results;
}


public TaxArticle addTaxArticle(TaxArticle taxArticle)
  {

  taxArticle=getDao().save(taxArticle);
    return taxArticle;
  }


public boolean deleteTaxArticle(TaxArticle taxArticle)
{
boolean deletedFlag;
boolean existedtaxArticle = checkTaxArticle(taxArticle.getTaxArticleCode());
 if(existedtaxArticle==true)
  {
     deletedFlag=true;
  throw new ServiceException("taxArticle.delete.error", new Object[]{taxArticle.getTaxArticleCode()}, null);
  }
   else
    {
       getDao().remove(taxArticle);
     deletedFlag=false;
    }
    return deletedFlag;
  }
public boolean checkTaxArticle(String taxArticleCode)
{
   boolean flag=false;
  return flag;
  }

public    TaxArticle getTaxArticleByCode(String taxArticleCode)
{

    if(taxArticleCode == null) return null;
    List taxArticles = new ArrayList();

    taxArticles = getDao().find(
        "from TaxArticle t where t.taxArticleCode = '" + taxArticleCode+"'",
        null
      );

     if(taxArticles.size() > 0)
      {
       return (TaxArticle) taxArticles.get(0);
      }

      return null;


}

public TaxCode getTaxCodeByCode(String taxCode)
{
    if(taxCode == null) return null;
    List taxCodes = new ArrayList();

    taxCodes = getDao().find(
        "from TaxCode tax where tax.taxCodeHeader = '" + taxCode+"'",
        null
      );

     if(taxCodes.size() > 0)
      {
       return (TaxCode) taxCodes.get(0);
      }

      return null;
}

public List getAllTaxCodes(String taxType)
{
  List taxCodes = getDao().find(
          "from TaxCode tax where tax.taxType = '"+taxType+"' order by tax.taxCodeHeader ",
          null
        );

       if(taxCodes.size() > 0)
        {
         return taxCodes;
        }

        return null;
}

public TaxCode getTaxCodeByCodeWithTaxRates(String taxCode)
{
    if(taxCode == null) return null;
    List taxCodes = new ArrayList();

    taxCodes = getDao().find(
        "from TaxCode tax left join fetch tax.taxCodeTaxRates tr where tax.taxCodeHeader = '" + taxCode+"' ",
        null
      );

     if(taxCodes.size() > 0)
      {
       return (TaxCode) taxCodes.get(0);
      }

      return null;
}

public TaxRate getTaxRateByTaxCodeAndEffDate(String taxCode,Date jobFinishDate,String taxType)
{
  List effTaxRate = null;
  //System.out.println("jobFinishDate,taxcode inside method: "+jobFinishDate+","+taxCode);
  List effDate = getDao().find(" select max(tr.taxRateId.effDate) from TaxRate tr where tr.taxRateId.taxCode = ?  and tr.taxRateId.effDate <= ? and tr.taxType = ? ", new Object[]{taxCode, jobFinishDate,taxType});
  Date effectiveDate=null;
  if(effDate!=null && effDate.size()>0){
    effectiveDate = (Date) effDate.get(0);
  }

  if(effectiveDate == null){
    return null;
  }
  else{
    //Date effectiveDate = (Date) effDate.get(0);
   // System.out.println("effDate :"+effectiveDate);

    effTaxRate = getDao().find("  from TaxRate tr where tr.taxRateId.taxCode = ?  and tr.taxRateId.effDate = ? and tr.taxType = ? ", new Object[]{taxCode, effectiveDate,taxType});
    if(effTaxRate == null)
      return null;

  }
  return (TaxRate ) effTaxRate.get(0);

}

  public TaxCode addTaxCode(TaxCode taxCode)
  {
    if(taxCode == null) return null;

    List taxRates = new ArrayList();


    TaxCode existedTaxCode = getTaxCodeByCodeWithTaxRates(taxCode.getTaxCodeHeader());
    if(existedTaxCode == null)
    {
      return getDao().save(taxCode);
    }

    Iterator it = taxCode.getTaxCodeTaxRates().iterator();
    while(it.hasNext())
    {
      TaxRate taxRate = (TaxRate)it.next();
      TaxRate existedTaxRate = getTaxdateByCode(taxRate.getTaxRateId().getTaxCode());
     /* if(existedTaxRate == null)
      {
        throw new RuntimeException("Could not find tax rate for code: " + taxRate.getTaxCode());
      }*/

      //itrack 68504 many-to-many changed to one-to-manu
      //existedTaxCode.getTaxCodeTaxRates().add(existedTaxRate);
    }

    existedTaxCode.setTaxDescr(taxCode.getTaxDescr());
    existedTaxCode.setTaxType(taxCode.getTaxType());

    return existedTaxCode;
  }

 /* public TaxLabel getTaxLabelByCtryCodeAndStateCode(String ctryCode,String stateCode)
  {
    if(ctryCode == null || ctryCode.trim().equals(""))
      return null;
    if(stateCode == null)
      stateCode = "";

    List taxLabels = null;
    taxLabels = getDao().find("  from TaxLabel tl where tl.taxLabelId.countryCode = ?  and tl.taxLabelId.state = ? ", new Object[]{ctryCode, stateCode});
      if(taxLabels == null)
        return null;
      if(taxLabels.size() > 0)
        return (TaxLabel) taxLabels.get(0);
      else
        return null;

  }*/

  /**
   * Name :getTaxLabelByCtryCodeAndStateCode
   * Date :Dec 8, 2008
   * Purpose : - itrack 78542
   * It looks for all tax labels for the business unit country
   * If there is only one it returns that
   * If there is more than one, if any record found for service location state, it returns that one
   * If not it returns the one with state =' ' or null (default one)
   * @param businessUnitCountryCode
   * @param serviceLocationState
   * @return
   */
  public TaxLabel getTaxLabelByCtryCodeAndStateCode( String businessUnitCountryCode, String serviceLocationState)
  {
    if(businessUnitCountryCode == null)
      return null;
    List<TaxLabel> list = new ArrayList<TaxLabel>();
    list = getDao().find(
        "from TaxLabel t where t.taxLabelId.countryCode = ?  ",
        new Object[] { businessUnitCountryCode}
      );
    if(!list.isEmpty()){
      if(list.size()==1)
        return list.get(0);
      else{
        for(TaxLabel taxLabel : list){
          if(taxLabel.getTaxLabelId().getState().equals(serviceLocationState))
            return taxLabel;
        }
        for(TaxLabel taxLabel : list){
          if(taxLabel.getTaxLabelId().getState()== null || taxLabel.getTaxLabelId().getState().trim().length()==0)
            return taxLabel;
        }
      }
    }
     return null;
  }

  public List getAllTaxLabels(AddTaxLabels search)
  {
  Pagination pagination=search.getPagination();

    List results = null;

    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find(" select count(*) from TaxLabel t ", null);

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
      }
      }
      // results = getDao().find(" select distinct t from TaxLabel t order by t.taxLabelId ",null , pagination);
      results = getDao().find(" select distinct t from TaxLabel t order by t.taxLabelId.countryCode ",null , pagination);
       pagination.calculatePages();
    }
    else
    {
      results = getDao().find(" select distinct t from TaxLabel t order by t.taxLabelId.countryCode " , null);
    }
  search.setResults(results);
  search.setPagination(pagination);
  return results;
  }

  public boolean deleteTaxLabel(TaxLabel taxLabel)
  {
  boolean deletedFlag;
  boolean existedtaxlabel = checkTaxLabel(taxLabel.getVatLabel());
   if(existedtaxlabel==true)
    {
       deletedFlag=true;
    throw new ServiceException("taxLabel.delete.error", new Object[]{taxLabel.getVatLabel()}, null);
    }
     else
      {
         getDao().remove(taxLabel);
       deletedFlag=false;
      }
      return deletedFlag;
    }
  public boolean checkTaxLabel(String vatLabel)
  {
     boolean flag=false;
    return flag;
    }

  public TaxLabel addTaxLabel(TaxLabel taxLabel)
  {

    taxLabel=getDao().save(taxLabel);
    return taxLabel;
  }

/**
 * Name :getTaxRateByTaxCodeHdr
 * Date :Dec 3, 2008
 * Purpose : It returns the tax rate for that tax header with the right effective date
 *
 * @param taxCodeHdr
 * @param jobFinishDate
 * @return
 */
public TaxRate getTaxRateByTaxCodeHdr(String taxCodeHdr, Date jobFinishDate){
   if(taxCodeHdr == null)
     return null;
   List taxRates = getDao().find(
            "select r from TaxCode t, TaxCodeTaxRate tr ,TaxRate r where tr.taxCodeTaxRateId.taxCode = r.taxRateId.taxCode and t.taxCodeHeader=tr.taxCodeTaxRateId.taxCodeHeader and  t.taxCodeHeader = ? and r.taxRateId.effDate=" +
            "(" +
            "select max(r2.taxRateId.effDate) from TaxCode t2, TaxCodeTaxRate tr2 ,TaxRate r2 where tr2.taxCodeTaxRateId.taxCode = r2.taxRateId.taxCode and t2.taxCodeHeader=tr2.taxCodeTaxRateId.taxCodeHeader and t2.taxCodeHeader = ? and r2.taxRateId.effDate <= ?  " +
            ")",
            new Object[]{taxCodeHdr, taxCodeHdr, jobFinishDate});

     if(taxRates.size() > 0)
      {
       return (TaxRate) taxRates.get(0);
      }
    return null;
  }

}

