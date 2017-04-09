/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.tax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddress;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.Customer;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.State;
import com.intertek.entity.TaxArticle;
import com.intertek.entity.TaxCode;
import com.intertek.entity.TaxCodeTaxRate;
import com.intertek.entity.TaxLabel;
import com.intertek.entity.TaxRate;
import com.intertek.phoenix.common.EnumField;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.FilterOp;
import com.intertek.phoenix.dao.QueryInfo;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.util.DateUtil;

/**
 * 
 * @author richard.qin
 */
public class TaxSrvcImpl implements TaxSrvc {

    Logger log = Logger.getLogger(TaxSrvcImpl.class);
    
    /**
     * This implementation loosely follows TaxUtil.determineVATCodesByCountry().
     * The return type of that method is changed to the more meaningful TaxCodeInfo.
     * 
     * TODO this implementation needs to be reviewed.
     * 
     * @see com.intertek.phoenix.tax.TaxSrvc#getTaxRateInfo(com.intertek.phoenix.entity.CEJobContract)
     */
    @Override
    public TaxCodeInfo getTaxRateInfo(CEJobContract jobContract) {
        TaxCodeInfo taxRateInfo = new TaxCodeInfo();
        
        CountryInfo countryInfo = getCountryInfo(jobContract);
        if(countryInfo == null){
            // no vat handling
            return null;
        }
        String treatment = this.getVatTreatment(jobContract, countryInfo);
        taxRateInfo.setTreatment(treatment);

        Country vatCountry = countryInfo.getVatCountry();
        State vatState = countryInfo.getVatState();
        Set<CountryVAT> countryVats = vatCountry.getCountryVats();
        if (countryVats != null && countryVats.size() > 0) {
            if (vatCountry.getVatByProvince()) {
                boolean stateMatchFlag = false;
                String genericVatCode = null;
                String genericZeroVatCode = null;
                TaxCode stateVatCode = null;
                TaxCode stateZeroVatCode = null;
                TaxCode generalVatCode = null;
                TaxCode generalZeroVatCode = null;
                
                for(CountryVAT countryVAT: vatCountry.getCountryVats()){
                    TaxCode standardVatCode = getTaxCodeByCode(countryVAT.getVatCode());
                    TaxCode zeroRatedVatCode = getTaxCodeByCode(countryVAT.getZeroRatedVATCode());

                    if (standardVatCode != null){
                        taxRateInfo.setStandardVatCode(standardVatCode);
                    }
                    if (zeroRatedVatCode != null ){
                        taxRateInfo.setStandardZeroVatCode(zeroRatedVatCode);
                    }
                    if (countryVAT.getCountryVATId().getStateCode() == null || countryVAT.getCountryVATId().getStateCode().trim().equals("")) {
                        genericVatCode = countryVAT.getVatCode();
                        genericZeroVatCode = countryVAT.getZeroRatedVATCode();
                    }
                    if (vatState != null && !stateMatchFlag 
                      && countryVAT.getCountryVATId().getStateCode().trim().equalsIgnoreCase(vatState.getStateId().getStateCode())) {
                        stateVatCode = getTaxCodeByCode(countryVAT.getVatCode());
                        stateZeroVatCode = getTaxCodeByCode(countryVAT.getZeroRatedVATCode());
                        stateMatchFlag = true;
                    }
                }
                if (stateVatCode == null) {
                    generalVatCode = getTaxCodeByCode(genericVatCode);
                    generalZeroVatCode = getTaxCodeByCode(genericZeroVatCode);

                    if (treatment.trim().equals("domestic")) {
                        if (generalVatCode != null)
                            taxRateInfo.setGeneralVatCode(generalVatCode);
                    }
                    else {
                        if (generalZeroVatCode != null)
                            taxRateInfo.setGeneralZeroVatCode(generalZeroVatCode);
                    }
                }
                else {
                    if (treatment.trim().equals("domestic")) {
                        if (stateVatCode != null){
                            taxRateInfo.setStateVatCode(stateVatCode);
                        }
                    }
                    else {
                        if (stateZeroVatCode != null){
                            taxRateInfo.setStateZeroVatCode(stateZeroVatCode);
                        }
                    }
                }
            }
            else {
                for(CountryVAT countryVAT: countryVats){
                    TaxCode standardVatCode = getTaxCodeByCode(countryVAT.getVatCode());
                    TaxCode zeroRatedVatCode = getTaxCodeByCode(countryVAT.getZeroRatedVATCode());

                    if (standardVatCode != null){
                        taxRateInfo.setStandardVatCode(standardVatCode);
                    }
                    if (zeroRatedVatCode != null){
                        taxRateInfo.setStandardZeroVatCode(zeroRatedVatCode);
                    }
                }
            }
        }
        else { 
            // BU does not have any vat location, NO vat processing
            return null;
        }

        return taxRateInfo;
    }

    /**
     * Note, this method needs clean up, all commented lines should be removed.
     * @param jobContract
     * @return
     */
    public CountryInfo getCountryInfo(CEJobContract jobContract) {
        CountryInfo countryInfo = new CountryInfo();
        
        // some intermediate variables
        Country vatCountry = null;
        State vatState = null;
        Country shipFromCountry = null;
//        State shipFromState = null;
        Country shipToCountry = null;
        State shipToState = null;
//        Country locationCountry = null;
//        State locationState = null;
        Country customerLocationCountry = null;
//        State customerLocationState = null;
        Country servicePerformedCountry = null;
        State servicePerformedState = null;
        Country reportingCountry = null;
        State reportingState = null;
        
        Country defaultReportingCountry = null;

        CEJobOrder jobOrder = jobContract.getJobOrder();
        Customer customer = jobContract.getCustomer();
        BusinessUnit bu = jobOrder.getBu();
        
        // if the bu does not handle vat, bail        
        if (!bu.getVatEnabledInd() || bu.getBusUnitVatLocs().size() == 0){ 
            return null;
        }
        
        CustAddress billingAddress = jobContract.getBillingAddress();
        customerLocationCountry = getCountry(billingAddress);
        
        // RQ: do shipto country/state
        ServiceLocation serviceLocation = jobOrder.getServiceLocation();
        if(serviceLocation != null && serviceLocation.getCountry() != null){
            shipToCountry = serviceLocation.getCountry();
            shipToState = serviceLocation.getState();
        }
        else{
            for(CustAddrSeq addressSeq : customer.getCustAddrSeqs()){
                if (addressSeq.getCustAddrSeqId().getLocationNumber() == customer.getPrimaryShipToAddress()) {
                    for(CustAddress address: addressSeq.getCustAddresses()){
                        shipToCountry = getCountry(address);
                        shipToState = getState(address);
                        break;
                    }
                    break;
                }
            }
        }
        // RQ: do service performed country, the following is always achieved in the old logic
        servicePerformedCountry = shipToCountry;
        servicePerformedState = shipToState;

        // RQ: do the rest of the country/state, it's getting bad now
        for(BusUnitVatLoc busUnitVatLoc: bu.getBusUnitVatLocs()){
            if (busUnitVatLoc.getCountry().getCountryCode().trim().equals(servicePerformedCountry.getCountryCode())) {
                reportingCountry = servicePerformedCountry;
            }
            vatCountry = busUnitVatLoc.getCountry();
            // Check if this country handles VAT
            if (vatCountry.getVatCountry() == true) {
                if (busUnitVatLoc.getDefaultLocInd()) {
                    shipFromCountry = vatCountry;
//                    locationCountry = vatCountry;
                    defaultReportingCountry = vatCountry;                  
                    
                }
                // To be uncommented when the Country state relationship has been sorted out
                // if(considerStateFlag && busUnitVatLoc.getDefaultLocInd())
                // shipFromState = busUnitVatLoc.getState();
                // locationState = busUnitVatLoc.getState();
                break; // RQ there was no break here, why?
            }
            else {
                // Country does not handle VAT, NO vat processing
                return null;
            }
        }
        if (bu.getBusUnitVatLocs().size() == 1) {
            // If size of busUnitVatLoc is 1 then reportingCountry = BU country
            reportingCountry = vatCountry;           
        }
        if (reportingCountry == null){
            reportingCountry = defaultReportingCountry;
        }
        if (reportingState == null){
            reportingState = servicePerformedState;
        }
        
        // RQ: this is really ugly, as vatCountry is reassigned from reportingCountry,
        // which was assigned to vatCountry earlier.
        if (reportingCountry != null && shipFromCountry != null) {
            if (!reportingCountry.getCountryCode().trim().equals(shipFromCountry.getCountryCode())) {
                vatCountry = reportingCountry;
//                vatCountry = countryService.getCountryByCode(vatCountry.getCountryCode());
                vatState = reportingState;
//                if (reportingState != null)
//                    vatState = countryService.getStateByCodeAndCountryCode(reportingState.getStateId().getStateCode(), vatCountry.getCountryCode());
            }

        }
        if (vatState == null){
            vatState = reportingState;
        }
        
        countryInfo.setVatCountry(vatCountry);
        countryInfo.setVatState(vatState);
        countryInfo.setShipToCountry(shipToCountry);
        countryInfo.setCustomerLocationCountry(customerLocationCountry);
        countryInfo.setReportingCountry(defaultReportingCountry);
        // the rest are not used anywhere, to be removed
//        countryInfo.setCustomerLocationState(customerLocationState);
//        countryInfo.setReportingState(reportingState);
//        countryInfo.setShipToState(shipToState);
//        countryInfo.setShipFromCountry(shipFromCountry);
//        countryInfo.setShipFromState(shipFromState);
//        countryInfo.setLocationCountry(locationCountry);
//        countryInfo.setLocationState(locationState);
//        countryInfo.setServicePerformedCountry(servicePerformedCountry);
//        countryInfo.setServicePerformedState(servicePerformedState);
        
        return countryInfo;
    }

    /**
     * @param address
     * @return
     */
    private State getState(CustAddress address) {
        QueryInfo query = new QueryInfo(State.class);
        query.addFilter("stateId.stateCode", address.getState());
        query.addFilter("stateId.countryCode", address.getCountry());
        try {
            return DaoManager.getDao(State.class).searchUnique(query);
        }
        catch (DaoException e) {
            return null;
        }
    }

    /**
     * @param address
     * @return
     */
    private Country getCountry(CustAddress address) {
        QueryInfo query = new QueryInfo(Country.class);
        query.addFilter("countryCode", address.getCountry());
        try {
            return DaoManager.getDao(Country.class).searchUnique(query);
        }
        catch (DaoException e) {
            return null;
        }
    }

    /**
     * @see com.intertek.phoenix.tax.TaxSrvc#getTaxCodeByCode(java.lang.String)
     */
    @Override
    public TaxCode getTaxCodeByCode(String vatCode) {
        QueryInfo query = new QueryInfo(TaxCode.class);
        query.addFilter("taxCodeHeader", vatCode);
        
        try {
            return DaoManager.getDao(TaxCode.class).searchUnique(query);
        }
        catch (DaoException e) {
            return null;
        }
    }

    /**
     * @see com.intertek.phoenix.tax.TaxSrvc#getTaxCodeByServiceLocation(com.intertek.entity.ServiceLocation)
     */
    @Override
    public TaxCode getTaxCodeByServiceLocation(ServiceLocation servLocation) {
        TaxCode taxCode = null;
        if(servLocation!=null){
        CustAddrSeq shipToAddrSeq = servLocation.getShipToCustAddress();
        if (shipToAddrSeq.getCustAddresses().size() > 0){
            for(CustAddress address: shipToAddrSeq.getCustAddresses()){
                String code = address.getTaxCode();
                taxCode = getTaxCodeByCode(code);
                break;
            }
        }
       }
        return taxCode;
    }

    /**
     * @see com.intertek.phoenix.tax.TaxSrvc#getTaxPctByTaxCode(java.lang.String, java.util.Date)
     */
    @Override
    public double getTaxPctByTaxCode(String code, Date jobFinishDate) {
        double taxPct = 0;
        if (code != null) {
            TaxCode taxCode = getTaxCodeByCode(code);
            if (taxCode != null) {
                for(TaxCodeTaxRate taxCodeTaxRate: taxCode.getTaxCodeTaxRates()){
                    TaxRate effTaxRate = getTaxRate(taxCodeTaxRate.getTaxCodeTaxRateId().getTaxCode(), 
                                                                       jobFinishDate, taxCode.getTaxType());
                    if (effTaxRate != null) {
                        // RQ: why this is accumulative?
                        taxPct += effTaxRate.getTaxPct();
                    }
                }
            }
        }
        return taxPct;
    }

    /**
     * @see com.intertek.phoenix.tax.TaxSrvc#getTaxRate(java.lang.String, java.util.Date, java.lang.String)
     */
    @Override
    public TaxRate getTaxRate(String taxCode, Date jobFinishDate, String taxType) {
        QueryInfo query = new QueryInfo(TaxRate.class);
        query.addFilter("taxRateId.taxCode", taxCode);
        query.addFilter("taxType", taxType);
        query.addFilter("taxRateId.effDate", jobFinishDate, FilterOp.LESS_OR_EQUAL);
        
        try {
            return DaoManager.getDao(TaxRate.class).searchUnique(query);
        }
        catch (DaoException e) {
            return null;
        }
    }

    private String getVatTreatment(CEJobContract jobContract, CountryInfo countryInfo){
        String treatment = null;
        
        Country shipToCountry = countryInfo.getShipFromCountry();
        Country reportingCountry = countryInfo.getReportingCountry();
        Country customerLocationCountry = countryInfo.getCustomerLocationCountry();
        BusinessUnit bu = jobContract.getJobOrder().getBu();
        
        if (shipToCountry != null) {
            if (reportingCountry.getCountryCode().equals(shipToCountry.getCountryCode())) {
                if (customerLocationCountry != null  
                  && reportingCountry.getCountryCode().equals(customerLocationCountry.getCountryCode())){
                    treatment = "domestic";
                }
                else {
                    if (reportingCountry.getForeignBuyer() != null 
                      && reportingCountry.getForeignBuyer().equalsIgnoreCase("Dom")) {
                        treatment = "domestic";
                    }
                    else {
                        treatment = "export";
                    }
                }
            }
            else {
                // Modified based on the out of scope logic
                if (shipToCountry.getVatCountry() == null || shipToCountry.getVatCountry() == false) {
                    for(BusUnitVatLoc busUnitVatLoc: bu.getBusUnitVatLocs()){
                        if (busUnitVatLoc.getBusUnitVatLocId().getCountryCode().trim().equals(reportingCountry.getCountryCode()) 
                          && busUnitVatLoc.getOutOfScope() != null 
                          && busUnitVatLoc.getOutOfScope().trim().equals("true")){
                            return null;
                        }
                    }
                }
                treatment = "export";
            }
        }
        else{
            treatment = "domestic";
        }
        
        return treatment;
    }

    /**
     * @see com.intertek.phoenix.tax.TaxSrvc#getVatTreatment(com.intertek.phoenix.entity.CEJobContract)
     */
    @Override
    public String getVatTreatment(CEJobContract jobContract) {
        CountryInfo countryInfo = this.getCountryInfo(jobContract);
        return this.getVatTreatment(jobContract, countryInfo);
    }

    /**
     * @see com.intertek.phoenix.tax.TaxSrvc#getTaxArticle(java.lang.String)
     */
    @Override
    public TaxArticle getTaxArticle(String taxArticleCode) throws TaxSrvcException {
        QueryInfo query = new QueryInfo(TaxArticle.class);
        query.addFilter("taxArticleCode", taxArticleCode);
        try {
            return DaoManager.getDao(TaxArticle.class).searchUnique(query);
        }
        catch (DaoException e) {
            throw new TaxSrvcException("Failed to look up TaxCode", e);
        }
    }
    

    /**
     * @see com.intertek.phoenix.tax.TaxSrvc#getTaxCodes(java.lang.String)
     */
    @Override
    public List<TaxCode> getTaxCodes(String taxType) throws TaxSrvcException {
        List<TaxCode> result = null;
        QueryInfo query = new QueryInfo(TaxCode.class);
        query.addFilter("taxType", taxType);
        try {
            result = DaoManager.getDao(TaxCode.class).search(query);
        }
        catch (DaoException e) {
            throw new TaxSrvcException("Failed to look up TaxCode", e);
        }
        return result;
    }

    /**
     * @see com.intertek.phoenix.tax.TaxSrvc#getTaxLabel(java.lang.String, java.lang.String)
     */
    @Override
    public TaxLabel getTaxLabel(String ctryCode, String stateCode) throws TaxSrvcException {
        QueryInfo query = new QueryInfo(TaxLabel.class);
        query.addFilter("taxLabelId.countryCode", ctryCode);
        query.addFilter("taxLabelId.state", stateCode);
        try {
            return DaoManager.getDao(TaxLabel.class).searchUnique(query);
        }
        catch (DaoException e) {
            throw new TaxSrvcException("Failed to look up TaxLabel", e);
        }
    }

    /**
     * @see com.intertek.phoenix.tax.TaxSrvc#getTaxRate(java.lang.String, java.util.Date)
     */
    @Override
    public TaxRate getTaxRate(String taxCode, Date date) throws TaxSrvcException {
        Date effDate = DateUtil.convertTStoDate(date);
        QueryInfo query = new QueryInfo(TaxRate.class);
        query.addFilter("taxRateId.taxCode", taxCode);
        query.addFilter("taxRateId.effDate", effDate,FilterOp.LESS_OR_EQUAL);
        
        try {
            return DaoManager.getDao(TaxRate.class).searchUnique(query);
        }
        catch (DaoException e) {
            throw new TaxSrvcException("Failed to look up TaxRate", e);
        }
    }

    /**
     * @see com.intertek.phoenix.tax.TaxSrvc#getTaxRates(java.lang.String, java.util.Date)
     */
    @Override
    public List<TaxRate> getTaxRates(String taxCode, Date date) throws TaxSrvcException {
        QueryInfo query = new QueryInfo(TaxRate.class);
        query.addFilter("taxRateId.taxCode", taxCode);
        query.addFilter("taxRateId.effDate", date);
        try {
            return DaoManager.getDao(TaxRate.class).search(query);
        }
        catch (DaoException e) {
            throw new TaxSrvcException("Failed to look up TaxRate", e);
        }
    }

    // the following two methods are defined to support vat/tax dropdowns
    // these two methods are refactored from TaxCodesDropDown.java
    @Override
    public List<EnumField> getSalesTaxCode(CEJobContract jobContract) throws TaxSrvcException {
        List<EnumField> result = new ArrayList<EnumField>();
        List<TaxCode> taxCodes = getTaxCodes("S");
        for(TaxCode taxCode: taxCodes){
            EnumField ef = new TaxCodeField(taxCode.getTaxCodeHeader(), taxCode.getTaxDescr());
            result.add(ef);
        }
        Collections.sort(result, new Comparator<EnumField>(){
            public int compare(EnumField one, EnumField two){
                return (one.getName().compareTo(two.getName()));
            }
        });
        return result;
    }
    
    @Override
    public List<EnumField> getVatTaxCode(CEJobContract jobContract) throws TaxSrvcException{
        List<EnumField> result = new ArrayList<EnumField>();
        
        TaxCodeInfo taxcodes = this.getTaxRateInfo(jobContract); 
        // inspect every vat code
        for(TaxCode taxCode: taxcodes.toList()){
            Date date = jobContract.getJobOrder().getNominationDate();
            TaxRate taxRate;
            taxRate = this.getTaxRate(taxCode.getTaxCodeHeader(), date);
            EnumField ef = new TaxCodeField(taxCode.getTaxCodeHeader(), taxRate.getDescription());
            result.add(ef);
        }
        Collections.sort(result, new Comparator<EnumField>(){
            public int compare(EnumField one, EnumField two){
                return (one.getName().compareTo(two.getName()));
            }
        });
        return result;
    }
    
    @Override
    public List<EnumField> getTaxArticles() throws TaxSrvcException {
        List<EnumField> result = new ArrayList<EnumField>();
        List<TaxArticle> taxArticles = this.getAllTaxArticles();
        for(TaxArticle taxArticle: taxArticles){
            EnumField ef = new TaxCodeField(taxArticle.getTaxArticleCode(), taxArticle.getTaxArticleDescription());
            result.add(ef);
        }
        Collections.sort(result, new Comparator<EnumField>(){
            public int compare(EnumField one, EnumField two){
                return (one.getName().compareTo(two.getName()));
            }
        });
        return result;
    } 
   
    public List<TaxArticle> getAllTaxArticles() throws TaxSrvcException {
        QueryInfo query = new QueryInfo(TaxArticle.class);       
        try {
            return DaoManager.getDao(TaxArticle.class).search(query);
        }
        catch (DaoException e) {
            throw new TaxSrvcException("Failed to look up TaxArticles", e);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    public BusUnitVatLoc getBusVatLocRegistrationId(BusinessUnit businessUnit, String serviceLocationCountry, String stateCode) throws DaoException {
        if (businessUnit == null)
            return null;

        List<BusUnitVatLoc> lists = new ArrayList<BusUnitVatLoc>();
        QueryInfo query = new QueryInfo(BusUnitVatLoc.class);
        if (stateCode != null && stateCode.trim().length() > 0) {
            query.addFilter("busUnitVatLocId.buName", businessUnit.getName());
            query.addFilter("busUnitVatLocId.countryCode", serviceLocationCountry);
            query.addFilter("state", stateCode);
            lists = DaoManager.getDao(BusUnitVatLoc.class).search(query);
        }
        else {
            query.addFilter("busUnitVatLocId.buName", businessUnit.getName());
            query.addFilter("state", stateCode);
            lists = DaoManager.getDao(BusUnitVatLoc.class).search(query);
            if (lists.size() > 1 && serviceLocationCountry != null) {
                query.addFilter("busUnitVatLocId.buName", businessUnit.getName());
                query.addFilter("busUnitVatLocId.countryCode", serviceLocationCountry);
                query.addFilter("state", stateCode);
            }
        }

        if (lists == null || lists.isEmpty() || lists.size() > 1) {
            boolean deflocInd = true;
            query.addFilter("busUnitVatLocId.buName", businessUnit.getName());
            query.addFilter("defaultLocInd", deflocInd);
            lists = DaoManager.getDao(BusUnitVatLoc.class).search(query);
        }
        if (lists != null && lists.size() > 0) {
            return lists.get(0);
        }
        else
            return null;
    }

    // TODO need to reveiew.
    @SuppressWarnings("unchecked")
    public CustVatRegistration getCustomerVatRegistrationByCustCodeandCountryCode(String custCode, String countryCode, String state) throws DaoException {
        List<CustVatRegistration> custVats = new ArrayList<CustVatRegistration>();
        QueryInfo query = new QueryInfo(CustVatRegistration.class);
        query.addFilter("custVatRegistrationId.custCode", custCode);
        query.addFilter("custVatRegistrationId.countryCode", countryCode);
        query.addFilter("state", state);
        custVats = DaoManager.getDao(CustVatRegistration.class).search(query);
        if (custVats.size() > 0) {
            return custVats.get(0);
        }
        else
            return null;

    }
    
 // TODO need to reveiew.
    //TODO need to create a query which will show like countrycode=? and (zeroratevatcode=? or vatcode =?).but unable to create that type of 
    //criteria.
    
    public CountryVAT getVatProvinceByCountryCodeandVatCode(String countrycode, String vatCode) throws DaoException {
        List<CountryVAT> countries = new ArrayList<CountryVAT>();
        QueryInfo query = new QueryInfo(CountryVAT.class);
        query.addFilter("countryVATId.countryCode", countrycode);
        query.addFilter("zeroRatedVATCode",vatCode);
        countries = DaoManager.getDao(CountryVAT.class).search(query);
        if(countries==null || countries.size()==0){
        query.addFilter("countryVATId.countryCode", countrycode);
        query.addFilter("vatCode",vatCode);
        countries = DaoManager.getDao(CountryVAT.class).search(query);
        }
        if (countries.size() > 0) {
            return countries.get(0);
        }
        else
            return null;

    }   
}

