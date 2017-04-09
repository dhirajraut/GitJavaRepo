/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.tax;

import com.intertek.entity.Country;
import com.intertek.entity.State;

/**
 * 
 * @author richard.qin
 */
public class CountryInfo {

    private Country vatCountry = null;
    private State vatState = null;
    
    private Country shipFromCountry = null;
    private State shipFromState = null;

    private Country shipToCountry = null;
    private State shipToState = null;

    private Country locationCountry = null;
    private State locationState = null;

    private Country customerLocationCountry = null;
    private State customerLocationState = null;

    private Country servicePerformedCountry = null;
    private State servicePerformedState = null;

    private Country reportingCountry = null;
    private State reportingState = null;

    public CountryInfo(){
        
    }

    public Country getVatCountry() {
        return vatCountry;
    }

    public void setVatCountry(Country vatCountry) {
        this.vatCountry = vatCountry;
    }

    public State getVatState() {
        return vatState;
    }

    public void setVatState(State vatState) {
        this.vatState = vatState;
    }

    public Country getShipFromCountry() {
        return shipFromCountry;
    }

    public void setShipFromCountry(Country shipFromCountry) {
        this.shipFromCountry = shipFromCountry;
    }

    public State getShipFromState() {
        return shipFromState;
    }

    public void setShipFromState(State shipFromState) {
        this.shipFromState = shipFromState;
    }

    public Country getShipToCountry() {
        return shipToCountry;
    }

    public void setShipToCountry(Country shipToCountry) {
        this.shipToCountry = shipToCountry;
    }

    public State getShipToState() {
        return shipToState;
    }

    public void setShipToState(State shipToState) {
        this.shipToState = shipToState;
    }

    public Country getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(Country locationCountry) {
        this.locationCountry = locationCountry;
    }

    public State getLocationState() {
        return locationState;
    }

    public void setLocationState(State locationState) {
        this.locationState = locationState;
    }

    public Country getCustomerLocationCountry() {
        return customerLocationCountry;
    }

    public void setCustomerLocationCountry(Country customerLocationCountry) {
        this.customerLocationCountry = customerLocationCountry;
    }

    public State getCustomerLocationState() {
        return customerLocationState;
    }

    public void setCustomerLocationState(State customerLocationState) {
        this.customerLocationState = customerLocationState;
    }

    public Country getServicePerformedCountry() {
        return servicePerformedCountry;
    }

    public void setServicePerformedCountry(Country servicePerformedCountry) {
        this.servicePerformedCountry = servicePerformedCountry;
    }

    public State getServicePerformedState() {
        return servicePerformedState;
    }

    public void setServicePerformedState(State servicePerformedState) {
        this.servicePerformedState = servicePerformedState;
    }

    public Country getReportingCountry() {
        return reportingCountry;
    }

    public void setReportingCountry(Country reportingCountry) {
        this.reportingCountry = reportingCountry;
    }

    public State getReportingState() {
        return reportingState;
    }

    public void setReportingState(State reportingState) {
        this.reportingState = reportingState;
    }

}
