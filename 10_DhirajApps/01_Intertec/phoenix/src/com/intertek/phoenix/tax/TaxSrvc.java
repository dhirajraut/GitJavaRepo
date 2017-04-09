/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.tax;

import java.util.Date;
import java.util.List;

import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.TaxArticle;
import com.intertek.entity.TaxCode;
import com.intertek.entity.TaxLabel;
import com.intertek.entity.TaxRate;
import com.intertek.phoenix.common.EnumField;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.entity.CEJobContract;

/**
 * 
 * @author richard.qin
 */
public interface TaxSrvc {

    /**
     * Load all the tax code of the given type
     * @param taxType
     * @return
     * @throws TaxSrvcException 
     */
    public List<TaxCode> getTaxCodes(String taxType) throws TaxSrvcException;

    /**
     * Get TaxRateInfo for a given CEJobContract.
     * @param jobContract
     * @return
     */
    public TaxCodeInfo getTaxRateInfo(CEJobContract jobContract) throws TaxSrvcException;

    /**
     * Get the TaxCode for the given vat code
     * @param vatCode
     * @return
     */
    public TaxCode getTaxCodeByCode(String vatCode) throws TaxSrvcException;

    /**
     * Get TaxCode for the given service location
     * @param servLocation
     * @return
     */
    public TaxCode getTaxCodeByServiceLocation(ServiceLocation servLocation) throws TaxSrvcException;

    /**
     * Get tax rate percentage by tax code
     * @param code
     * @param jobFinishDate
     * @return
     */
    public double getTaxPctByTaxCode(String code, Date date) throws TaxSrvcException;

    /**
     * Get TaxRate
     * @param taxCode
     * @param jobFinishDate
     * @param taxType
     * @return
     */
    public TaxRate getTaxRate(String taxCode, Date date, String taxType) throws TaxSrvcException;
    public TaxRate getTaxRate(String taxCode, Date date) throws TaxSrvcException;
    public List<TaxRate> getTaxRates(String taxCode, Date date) throws TaxSrvcException;

    /**
     * Get vat treatment for the given jobContract
     * @param jobContract
     * @return
     */
    public String getVatTreatment(CEJobContract jobContract) throws TaxSrvcException;
    
    /**
     * Get the tax article
     * @param taxArticleCode
     * @return
     */
    public TaxArticle getTaxArticle(String taxArticleCode) throws TaxSrvcException;
    
    
   /**
    * Get all tax articles
    * * @return
    * @throws TaxSrvcException
    */
    public List<EnumField> getTaxArticles() throws TaxSrvcException;
    
    /**
     * Get the tax label
     * @param ctryCode
     * @param stateCode
     * @return
     */
    public TaxLabel getTaxLabel(String ctryCode,String stateCode) throws TaxSrvcException;

    /**
     * Get a list of dropdown fields. This mehtod is created for dropdown gui
     * @param jobContract
     * @return
     * @throws TaxSrvcException
     */
    public List<EnumField> getVatTaxCode(CEJobContract jobContract) throws TaxSrvcException;

    /**
     * Get a list of dropdown fields. This mehtod is created for dropdown gui
     * @param jobContract
     * @return
     * @throws TaxSrvcException
     */
    public List<EnumField> getSalesTaxCode(CEJobContract jobContract) throws TaxSrvcException;
    
    
    /**
     * Get the country info for the given jobcontract
     * @param jobContract
     * @return
     * @throws TaxSrvcException
     */
    public CountryInfo getCountryInfo(CEJobContract jobContract) throws TaxSrvcException;
    
    /**
     * This method return the businessunit vat location registration id by passin gthe business unit,service location country and vat province state.
     * @param businessUnit
     * @param serviceLocationCountry
     * @param stateCode
     * @return
     * @throws DaoException
     */
    public BusUnitVatLoc getBusVatLocRegistrationId( BusinessUnit businessUnit, String serviceLocationCountry, String stateCode) throws DaoException;
     
    /**
     * This method returns default customer vat registration related to the given customer code and country code and the statecode(optional)
     * @param custCode
     * @param countryCode
     * @param state
     * @return
     * @throws DaoException
     */
     public CustVatRegistration getCustomerVatRegistrationByCustCodeandCountryCode(String custCode, String countryCode, String state) throws DaoException;
     
     
     /**
     * This method returns the country vats based on the given countrycode, and vatcode 
     * @param countrycode
     * @param vatCode
     * @return
     * @throws DaoException
     */
    public CountryVAT getVatProvinceByCountryCodeandVatCode(String countrycode, String vatCode) throws DaoException ;
}
