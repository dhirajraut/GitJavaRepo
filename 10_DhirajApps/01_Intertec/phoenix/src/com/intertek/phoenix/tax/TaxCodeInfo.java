/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.tax;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.TaxCode;

/**
 * 
 * @author richard.qin
 */
public class TaxCodeInfo {

    private TaxCode stateVatCode = null;
    private TaxCode stateZeroVatCode = null;
    private TaxCode generalVatCode = null;
    private TaxCode generalZeroVatCode = null;
    private TaxCode standardVatCode = null;
    private TaxCode standardZeroVatCode = null;
    private String treatment;

    public TaxCode getStateVatCode() {
        return stateVatCode;
    }

    public void setStateVatCode(TaxCode stateVatCode) {
        this.stateVatCode = stateVatCode;
    }

    public TaxCode getStateZeroVatCode() {
        return stateZeroVatCode;
    }

    public void setStateZeroVatCode(TaxCode stateZeroVatCode) {
        this.stateZeroVatCode = stateZeroVatCode;
    }

    public TaxCode getGeneralVatCode() {
        return generalVatCode;
    }

    public void setGeneralVatCode(TaxCode generalVatCode) {
        this.generalVatCode = generalVatCode;
    }

    public TaxCode getGeneralZeroVatCode() {
        return generalZeroVatCode;
    }

    public void setGeneralZeroVatCode(TaxCode generalZeroVatCode) {
        this.generalZeroVatCode = generalZeroVatCode;
    }

    public TaxCode getStandardVatCode() {
        return standardVatCode;
    }

    public void setStandardVatCode(TaxCode standardVatCode) {
        this.standardVatCode = standardVatCode;
    }

    public TaxCode getStandardZeroVatCode() {
        return standardZeroVatCode;
    }

    public void setStandardZeroVatCode(TaxCode standardZeroVatCode) {
        this.standardZeroVatCode = standardZeroVatCode;
    }
    
    public String getTreatment(){
        return this.treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    
    public List<TaxCode > toList(){
        List<TaxCode > list = new ArrayList<TaxCode>();
        if(stateVatCode != null){
            list.add(stateVatCode);
        }
        if(stateZeroVatCode != null){
            list.add(stateZeroVatCode);
        }
        if(generalVatCode != null){
            list.add(generalVatCode);
        }
        if(generalZeroVatCode != null){
            list.add(generalZeroVatCode);
        }
        if(standardVatCode != null){
            list.add(standardVatCode);
        }
        if(standardZeroVatCode != null){
            list.add(standardZeroVatCode);
        }
        
        return list;
    }
}
