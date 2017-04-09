/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.Date;

import com.intertek.entity.CurrencyRate;
import com.intertek.entity.CurrencyRateId;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class MarketRateX {
    private String rateIndex;
    private int term;
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private String rateType;
    private Date effectiveDate;
    private double rateMultiplier;
    private double rateDivisor;
    private int synchronizationID;
    private Date lastUpdateTime;

    public CurrencyRate convert() {
        CurrencyRate obj = new CurrencyRate();

        CurrencyRateId id = new CurrencyRateId();
        id.setRateIndex(rateIndex);
        id.setTerm(term);
        id.setFromCurrency(fromCurrencyCode);
        id.setToCurrency(toCurrencyCode);
        id.setType(rateType);
        id.setEffectiveDate(effectiveDate);

        obj.setCurrencyRateId(id);
        obj.setRateMult(rateMultiplier);
        obj.setRateDiv(rateDivisor);
        obj.setSyncId(synchronizationID);
        obj.setLastUpdateDate(lastUpdateTime);
        return obj;
    }

    public String getRateIndex() {
        return rateIndex;
    }

    public void setRateIndex(String rateIndex) {
        this.rateIndex = rateIndex;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public double getRateMultiplier() {
        return rateMultiplier;
    }

    public void setRateMultiplier(double rateMultiplier) {
        this.rateMultiplier = rateMultiplier;
    }

    public double getRateDivisor() {
        return rateDivisor;
    }

    public void setRateDivisor(double rateDivisor) {
        this.rateDivisor = rateDivisor;
    }

    public int getSynchronizationID() {
        return synchronizationID;
    }

    public void setSynchronizationID(int synchronizationID) {
        this.synchronizationID = synchronizationID;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}
