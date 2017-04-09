package com.intertek.domain;



public class BankAccountSearchResult{
	String BU;
	String bankCode;
	String bankAccountCode;
	String currencyCode;
	String primary;
	String bankAccountDesc;
	String BI;
	String bankDesc;
	String bankIdNbr;

	public BankAccountSearchResult(
			String BU,
			String bankCode,
			String bankAccountCode,
			String currencyCode,
			String primary,
			String bankAccountDesc,
			String BI,
			String bankDesc,
			String bankIdNbr
			){

		this.BU=BU;
		this.bankCode=bankCode;
		this.bankAccountCode=bankAccountCode;
		this.currencyCode=currencyCode;
		this.primary=primary;
		this.bankAccountDesc=bankAccountDesc;
		this.BI=BI;
		this.bankDesc=bankDesc;
		this.bankIdNbr=bankIdNbr;
	}
	
	public String getBU() {
		return BU;
	}
	public void setBU(String bu) {
		BU = bu;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankAccountCode() {
		return bankAccountCode;
	}
	public void setBankAccountCode(String bankAccountCode) {
		this.bankAccountCode = bankAccountCode;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getPrimary() {
		return primary;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	public String getBankAccountDesc() {
		return bankAccountDesc;
	}
	public void setBankAccountDesc(String bankAccountDesc) {
		this.bankAccountDesc = bankAccountDesc;
	}
	public String getBI() {
		return BI;
	}
	public void setBI(String bi) {
		BI = bi;
	}
	public String getBankDesc() {
		return bankDesc;
	}
	public void setBankDesc(String bankDesc) {
		this.bankDesc = bankDesc;
	}
	public String getBankIdNbr() {
		return bankIdNbr;
	}
	public void setBankIdNbr(String bankIdNbr) {
		this.bankIdNbr = bankIdNbr;
	}
}
