package com.intertek.report.dataSource;

import static com.intertek.report.ReportConstants.REPORT_INVOICE_TAX_VATLABEL;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import com.intertek.phoenix.util.ArrayMap;
import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.ReportUtil;

public class CEInvoiceTaxFooterDataSource extends AbstractJasperDataSource {

    private static final String[] fieldNames = { "JOB_NUMBER", "INVOICE", "INVOICE_TYPE", "CUSTOMERS_NAME", "BU_NAME", "BUSINESS_UNITS_DESCRIPTION",
                                                 "BUSINESS_UNITS_COMPANY_DESC", "BUSINESS_UNITS_ADDRESS1", "BUSINESS_UNITS_CITY", "BUSINESS_UNITS_POSTAL",
                                                 "BUSINESS_UNITS_PHONE_PREFIX", "BUSINESS_UNITS_PHONE_NUMBER", "BUSINESS_UNITS_PHONE_EXTENSION",
                                                 "BUSINESS_UNITS_ADDRESS2", "BUSINESS_UNITS_ADDRESS3", "BUSINESS_UNITS_ADDRESS4", "BUSINESS_UNITS_COUNTRY_CODE",
                                                 "BUSINESS_UNITS_STATE_CODE", "NAME", "SHIP_TO_COUNTRY", "SHIP_TO_STATE", "LINE_DESCRIPTION", "SPLIT_PCT",
                                                 "DISCOUNT_PCT", "UNIT_PRICE", "TAX_CODE", "TAX_PCT", "TAX_AMT", "VAT_CODE", "VAT_PCT", "VAT_AMT", "NET_PRICE",
                                                 "TAX_ARTICLE_CODE", "TAX_ARTICLE_DESCRIPTION", "ACCOUNT", "PRODUCT_GROUP", "DEPTID", "PRIMARY_BRANCH_CD",
                                                 "JOB_DESCRIPTION", "VAT_LABEL", "SALES_TAX_LABEL", "VAT_REG_LABEL", "VAT_REGISTRATION_ID", "VAT_PROVINCE",
                                                 "buyers_country", "sellers_country", "TRANSACT_CURRENCY_CD", "BANK_ACCT_DESC", "DFI_ID_NUM", "BANK_CODE",
                                                 "BANK_ACCT_CODE", "BANKS_BANK_DESC", "BANKS_ADDRESS1", "BANKS_ADDRESS2", "BANKS_ADDRESS3", "BANKS_BANK_ID_NBR",
                                                 "BRANCH_NAME", "BRANCHES_COMPANY_DESC", "BRANCHES_PHONE_PREFIX", "BRANCHES_PHONE_NUMBER",
                                                 "BRANCHES_PHONE_EXTENSION", "TO_CURRENCY", "RATE_MULT", "RATE_DIV",
                                                 // "OVERRIDE_CURR_RATE",
                                                 "DECIMAL_DIGITS", "BU_VAT_REGISTRATION_ID", "LOCAL_VAT_ID", "INV_AMT_PRE_TAX", "INV_AMT_POST_TAX"};
	private static final String[] fieldTypes = { "SPLIT_PCT", "BigDecimal",
			"DISCOUNT_PCT", "BigDecimal", "UNIT_PRICE", "BigDecimal",
			"TAX_PCT", "BigDecimal", "TAX_AMT", "BigDecimal", "VAT_PCT",
			"BigDecimal", "VAT_AMT", "BigDecimal", "NET_PRICE", "BigDecimal",
			 "RATE_MULT", "BigDecimal",
			 "RATE_DIV", "BigDecimal",
			// "OVERRIDE_CURR_RATE", "BigDecimal",
			 "DECIMAL_DIGITS", "BigDecimal",
			"INV_AMT_PRE_TAX", "BigDecimal", "INV_AMT_POST_TAX", "BigDecimal" };

	public CEInvoiceTaxFooterDataSource() {
		super.fieldNames = fieldNames;
		super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String,
	 *      java.util.Map)
	 */
	@Override
	public JRDataSource getDataSource(String reportName,
			Map<String, Object> params) {
	    String hql = "select"
            + "     jc.jobOrderNumber as JOB_NUMBER,"
            + "     ce.invoiceNumber as INVOICE,"
            + "     ce.type as INVOICE_TYPE,"
            + "     cu.name as CUSTOMERS_NAME,"
            + "     bu.name as BU_NAME,"
            + "     bu.description as BUSINESS_UNITS_DESCRIPTION,"
            + "     bu.companyDesc as BUSINESS_UNITS_COMPANY_DESC,"
            + "     bu.address1 as BUSINESS_UNITS_ADDRESS1,"
            + "     bu.city as BUSINESS_UNITS_CITY,"
            + "     bu.postal as BUSINESS_UNITS_POSTAL,"
            + "     bu.phonePrefix as BUSINESS_UNITS_PHONE_PREFIX,"
            + "     bu.phoneNumber as BUSINESS_UNITS_PHONE_NUMBER,"
            + "     bu.phoneExtension as BUSINESS_UNITS_PHONE_EXTENSION,"
            + "     bu.address2 as BUSINESS_UNITS_ADDRESS2,"
            + "     bu.address3 as BUSINESS_UNITS_ADDRESS3,"
            + "     bu.address4 as BUSINESS_UNITS_ADDRESS4,"
            + "     bu.countryCode as BUSINESS_UNITS_COUNTRY_CODE,"
            + "     bu.stateCode as BUSINESS_UNITS_STATE_CODE,"
            + "     c.name as  NAME,"// bu country
            + "     supAdd.country as SHIP_TO_COUNTRY," 
            + "     supAdd.state as SHIP_TO_STATE," 
            + "     joli.description as LINE_DESCRIPTION,"
            + "     joli.splitPct as SPLIT_PCT," 
            + "     joli.discountPct as DISCOUNT_PCT," 
            + "     il.unitPrice as UNIT_PRICE,"
            + "     il.taxCode as TAX_CODE,"
            + "     il.taxPct as TAX_PCT,"
            + "     il.taxAmt as TAX_AMT,"
            + "     il.vatCode as VAT_CODE,"
            + "     il.vatPct as VAT_PCT,"
            + "     il.vatAmt as VAT_AMT,"
            + "     il.netPrice as NET_PRICE,"
            + "     il.taxArticleCode as TAX_ARTICLE_CODE,"
            + "      (select distinct ta.taxArticleDescription from TaxArticle ta ,CEInvoiceLineItem il,CEInvoice inv "
            + "     where il.taxArticleCode=ta.taxArticleCode and il.invoiceId=inv.id and inv.invoiceNumber=?)"
            + "     as TAX_ARTICLE_DESCRIPTION,"
            + "     joli.account as ACCOUNT,"
            + "     joli.productGroup as PRODUCT_GROUP,"
            + "     joli.deptid as DEPTID,"
            + "     joli.primaryBranchCd as PRIMARY_BRANCH_CD,"
            + "     jo.description as JOB_DESCRIPTION,"
            + "     ce.vatLabel as VAT_LABEL,"
            + "     ce.salesTaxLabel as SALES_TAX_LABEL,"
            + "     ce.vatRegLabel as VAT_REG_LABEL,"
            + "     ce.vatRegId as VAT_REGISTRATION_ID,"
            + "     ce.vatProvince as VAT_PROVINCE,"
            + "        (select co.country2 from Country co, CEJobContract jc ,CEInvoice inv"
            + "        where inv.jobOrderContractId = jc.id"
            + "        AND jc.vatRegCountry = co.countryCode"
            + "        AND inv.invoiceNumber = ?) as buyers_country,"
            + "      (select co.countryCode from Country co, CEJobContract jc ,CEJobOrder jo, CEInvoice ce, BusinessUnit bu"
            + "       where jc.jobOrderNumber=jo.jobNumber and jo.buName=bu.name and bu.countryCode=co.countryCode"
            + "       and jc.id = ce.jobOrderContractId "
            + "       and  ce.invoiceNumber = ?) as sellers_country, "// to b test
            + "     ce.transactionCurrencyCd as TRANSACT_CURRENCY_CD,"
            + "     ba.bankAcctDesc as BANK_ACCT_DESC," 
            + "     ba.dfIdNum as DFI_ID_NUM,"
            + "     b.bankCode as BANK_CODE," 
            + "     ba.bankAccountId as BANK_ACCT_CODE," 
            + "     b.bankDesc as  BANKS_BANK_DESC,"
            + "     b.address1 as BANKS_ADDRESS1,"
            + "     b.address1 as BANKS_ADDRESS2,"
            + "     b.address1 as BANKS_ADDRESS3,"
            + "     b.bankIdNumber as BANKS_BANK_ID_NBR,"
            + "     br.name as BRANCH_NAME,"
            + "     br.companyDesc as BRANCHES_COMPANY_DESC,"
            + "     br.phonePrefix as BRANCHES_PHONE_PREFIX,"
            + "     br.phoneNumber as BRANCHES_PHONE_NUMBER,"
            + "     br.phoneExtension as BRANCHES_PHONE_EXTENSION,"
            + "        bu.currencyBase as TO_CURRENCY,"
            + "        il.rateMult as RATE_MULT, "
            + "        il.rateDiv as RATE_DIV,"
            + "     (select distinct cur.decimalDigits from Currency cur,CEJobContract cejc ,CEInvoice inv"
            + "     where cejc.transactionCurrency=cur.currencyCd and jc.id = inv.jobOrderContractId and inv.invoiceNumber = ?) as DECIMAL_DIGITS, "// to b test
            + "        ce.buVatRegId as BU_VAT_REGISTRATION_ID,"
            + "     (select buVat.localVatId "
            + "         from BusUnitVatLoc buVat, CEJobContract jc, CEInvoice jci,CEJobOrder jo " 
            + "         where jc.id = jci.jobOrderContractId"
            + "         AND jc.jobOrderNumber = jo.jobNumber" 
            + "     AND jci.buVatRegId = buVat.busUnitVatLocId.vatRegistrationId"
            + "     AND jo.buName = buVat.businessUnit.name" 
            + "     AND jci.invoiceNumber = ?) as LOCAL_VAT_ID,"
            + "     ce.invAmtPreTax as INV_AMT_PRE_TAX," 
            + "     ce.invAmtPostTax as INV_AMT_POST_TAX "
            + "     from CEInvoiceLineItem il left join il.invoice ce left join ce.jobContract jc"
            + "     left join jc.customer cu  left join jc.supplierAddress supAdd left join jc.jobOrder jo "
            + "     left join jo.bu bu left join bu.country c left join jc.remitToBankAccount ba"
            + "     left join jc.remitTo b  left join jo.branch br" 
            + "     left join  il.cEJobOrderLineItem joli where ce.invoiceNumber = ?";

	//	SearchService searchService = (SearchService) ServiceLocator.getInstance().getBean("searchService");
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(params.get("Invoice"));
		paramList.add(params.get("Invoice"));
		paramList.add(params.get("Invoice"));
		paramList.add(params.get("Invoice"));
		paramList.add(params.get("Invoice"));
/*		paramList.add(params.get("Job_Number"));
		paramList.add(params.get("Invoice"));
		paramList.add(params.get("Invoice"));*/
		paramList.add(params.get("Invoice"));
		
/*		List<Object[]> result = searchService.searchByHql(hql, CEInvoice.class,
				paramList);*/
		List<Object[]> result =ReportUtil.hibernateJasperDataSourceHelperByHql(hql,paramList,typeMap,fieldNames );
		if (result != null) {
			return new ArrayMapDataSource(new ArrayMap<String, Object>(
					fieldNames), result, this, params);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String,
	 *      java.util.Map, java.sql.Connection)
	 */
	@Override
	public JRDataSource getDataSource(String reportName,
			Map<String, Object> params, Connection con) {
		return getDataSource(reportName,params);
	}

	@Override
	public Object calculateFieldValue(String fieldName,
			Map<String, Object> params, ArrayMapDataSource source) {
		// ( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ?
		// $F{INV_AMT_POST_TAX} :
		// ($F{OVERRIDE_CURR_RATE} == null) ?
		// (new
		// BigDecimal(($V{VAT_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(2,4).add(
		// new
		// BigDecimal($F{INV_AMT_PRE_TAX}.doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(2,4).add(
		// new
		// BigDecimal(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(2,4))))
		// :
		// (new
		// BigDecimal($F{INV_AMT_PRE_TAX}.doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()).add(
		// new
		// BigDecimal(($V{VAT_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()).add(
		// new
		// BigDecimal(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue())))))
		if (fieldName.equals("TEXT_52")) {
			if (source.getFieldValue("TRANSACT_CURRENCY_CD").equals(
					source.getFieldValue("TO_CURRENCY"))) {
				return source.getFieldValue("INV_AMT_POST_TAX");
			} else {
				double vat_subtotal = ((BigDecimal) source
						.getCalculatedField("VAT_subtotal")).setScale(2, 4)
						.doubleValue();
				double sales_tax_subtotal = ((BigDecimal) source
						.getCalculatedField("sales_tax_subtotal")).setScale(2,
						4).doubleValue();
				double inv_amt_pre_tax = ((BigDecimal) source
						.getFieldValue("INV_AMT_PRE_TAX")).doubleValue();
				double result = 0;
				if (source.getFieldValue("OVERRIDE_CURR_RATE") == null) {
					double rate_mult = ((BigDecimal) source
							.getFieldValue("RATE_MULT")).doubleValue();
					double rate_div = ((BigDecimal) source
							.getFieldValue("RATE_DIV")).doubleValue();
					result = (vat_subtotal + inv_amt_pre_tax + sales_tax_subtotal)
							* rate_mult / rate_div;
				} else {
					double override_curr_rate = ((BigDecimal) source
							.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
					result = (inv_amt_pre_tax + vat_subtotal + sales_tax_subtotal)
							/ override_curr_rate;
				}
				return new BigDecimal(String.valueOf(result));
			}
		} else if (fieldName.equals("TEXT_63")) {
			// ( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ?
			// $F{INV_AMT_POST_TAX} :
			// ($F{OVERRIDE_CURR_RATE} == null) ?
			// (new
			// BigDecimal(($V{VAT_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(0,4).add(
			// new
			// BigDecimal($F{INV_AMT_PRE_TAX}.doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(0,4).add(
			// new
			// BigDecimal(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(0,4))))
			// :
			// (new
			// BigDecimal($F{INV_AMT_PRE_TAX}.doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()).setScale(0,4).add(
			// new
			// BigDecimal(($V{VAT_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()).setScale(0,4).add(
			// new
			// BigDecimal(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()).setScale(0,4)))))

			if (source.getFieldValue("TRANSACT_CURRENCY_CD").equals(
					source.getFieldValue("TO_CURRENCY"))) {
				return source.getFieldValue("INV_AMT_POST_TAX");
			} else {
				double vat_subtotal = ((BigDecimal) source
						.getCalculatedField("VAT_subtotal")).setScale(2, 4)
						.doubleValue();
				double inv_amt_pre_tax = ((BigDecimal) source
						.getFieldValue("INV_AMT_PRE_TAX")).doubleValue();
				double sales_tax_subtotal = ((BigDecimal) source
						.getCalculatedField("sales_tax_subtotal")).setScale(2,
						4).doubleValue();
				double result = 0;
				if (source.getFieldValue("OVERRIDE_CURR_RATE") == null) {
					double rate_mult = ((BigDecimal) source
							.getFieldValue("RATE_MULT")).doubleValue();
					double rate_div = ((BigDecimal) source
							.getFieldValue("RATE_DIV")).doubleValue();
					result = (vat_subtotal + inv_amt_pre_tax + sales_tax_subtotal)
							* rate_mult / rate_div;
				} else {
					double override_curr_rate = ((BigDecimal) source
							.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
					result = (inv_amt_pre_tax + vat_subtotal + sales_tax_subtotal)
							/ override_curr_rate;
				}
				return new BigDecimal(String.valueOf(result)).setScale(0, 4);
			}
		} else if (fieldName.equals("TEXT_50")) {
			// ( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new
			// Double($F{INV_AMT_PRE_TAX}.doubleValue()) :
			// ($F{OVERRIDE_CURR_RATE} == null) ?
			// new
			// Double($F{INV_AMT_PRE_TAX}.doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue())
			// :
			// new
			// Double($F{INV_AMT_PRE_TAX}.doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))
			if (source.getFieldValue("TRANSACT_CURRENCY_CD").equals(
					source.getFieldValue("TO_CURRENCY"))) {
				return new Double(((BigDecimal) source
						.getFieldValue("INV_AMT_PRE_TAX")).doubleValue());
			} else {
				double inv_amt_pre_tax = ((BigDecimal) source
						.getFieldValue("INV_AMT_PRE_TAX")).doubleValue();
				double result = 0;
				if (source.getFieldValue("OVERRIDE_CURR_RATE") == null) {
					double rate_mult = ((BigDecimal) source
							.getFieldValue("RATE_MULT")).doubleValue();
					double rate_div = ((BigDecimal) source
							.getFieldValue("RATE_DIV")).doubleValue();
					result = inv_amt_pre_tax * rate_mult / rate_div;
				} else {
					double override_curr_rate = ((BigDecimal) source
							.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
					result = inv_amt_pre_tax / override_curr_rate;
				}
				return new Double(result);
			}
		} else if (fieldName.equals("TEXT_60")) {
			// ( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new
			// Double($F{INV_AMT_PRE_TAX}.doubleValue()) :
			// ($F{OVERRIDE_CURR_RATE} == null) ?
			// new
			// Double($F{INV_AMT_PRE_TAX}.doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue())
			// :
			// new
			// Double($F{INV_AMT_PRE_TAX}.doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))
			if (source.getFieldValue("TRANSACT_CURRENCY_CD").equals(
					source.getFieldValue("TO_CURRENCY"))) {
				return new Double(((BigDecimal) source
						.getFieldValue("INV_AMT_PRE_TAX")).doubleValue());
			} else {
				double inv_amt_pre_tax = ((BigDecimal) source
						.getFieldValue("INV_AMT_PRE_TAX")).doubleValue();
				double result = 0;
				if (source.getFieldValue("OVERRIDE_CURR_RATE") == null) {
					double rate_mult = ((BigDecimal) source
							.getFieldValue("RATE_MULT")).doubleValue();
					double rate_div = ((BigDecimal) source
							.getFieldValue("RATE_DIV")).doubleValue();
					result = inv_amt_pre_tax * rate_mult / rate_div;
				} else {
					double override_curr_rate = ((BigDecimal) source
							.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
					result = inv_amt_pre_tax / override_curr_rate;
				}
				return new Double(result);
			}
		} else if (fieldName.equals("TEXT_49")) {
			// ( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new
			// Double($V{VAT_subtotal}.setScale(2,4).doubleValue()) :
			// ($F{OVERRIDE_CURR_RATE} == null) ?
			// new
			// Double(($V{VAT_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue())
			// :
			// new
			// Double(($V{VAT_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))
			double vat_subtotal = ((BigDecimal) source
					.getCalculatedField("VAT_subtotal")).setScale(2, 4)
					.doubleValue();
			if (source.getFieldValue("TRANSACT_CURRENCY_CD").equals(
					source.getFieldValue("TO_CURRENCY"))) {
				return new Double(vat_subtotal);
			} else {
				double result = 0;
				if (source.getFieldValue("OVERRIDE_CURR_RATE") == null) {
					double rate_mult = ((BigDecimal) source
							.getFieldValue("RATE_MULT")).doubleValue();
					double rate_div = ((BigDecimal) source
							.getFieldValue("RATE_DIV")).doubleValue();
					result = vat_subtotal * rate_mult / rate_div;
				} else {
					double override_curr_rate = ((BigDecimal) source
							.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
					result = vat_subtotal / override_curr_rate;
				}
				return new Double(result);
			}
		} else if (fieldName.equals("TEXT_61")) {
			// ( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new
			// Double($V{VAT_subtotal}.setScale(0,1).doubleValue()) :
			// ($F{OVERRIDE_CURR_RATE} == null) ?
			// new
			// Double(($V{VAT_subtotal}.setScale(0,1)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue())
			// :
			// new
			// Double(($V{VAT_subtotal}.setScale(0,1)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))
			double vat_subtotal = ((BigDecimal) source
					.getCalculatedField("VAT_subtotal")).setScale(0, 1)
					.doubleValue();
			if (source.getFieldValue("TRANSACT_CURRENCY_CD").equals(
					source.getFieldValue("TO_CURRENCY"))) {
				return new Double(vat_subtotal);
			} else {
				double result = 0;
				if (source.getFieldValue("OVERRIDE_CURR_RATE") == null) {
					double rate_mult = ((BigDecimal) source
							.getFieldValue("RATE_MULT")).doubleValue();
					double rate_div = ((BigDecimal) source
							.getFieldValue("RATE_DIV")).doubleValue();
					result = vat_subtotal * rate_mult / rate_div;
				} else {
					double override_curr_rate = ((BigDecimal) source
							.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
					result = vat_subtotal / override_curr_rate;
				}
				return new Double(result);
			}
		} else if (fieldName.equals("TEXT_51")) {
			// ( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new
			// Double($V{sales_tax_subtotal}.setScale(2,4).doubleValue()) :
			// ($F{OVERRIDE_CURR_RATE} == null) ?
			// new
			// Double(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue())
			// :
			// new
			// Double(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))
			double sales_tax_subtotal = ((BigDecimal) source
					.getCalculatedField("sales_tax_subtotal")).setScale(2, 4)
					.doubleValue();
			if (source.getFieldValue("TRANSACT_CURRENCY_CD").equals(
					source.getFieldValue("TO_CURRENCY"))) {
				return new Double(sales_tax_subtotal);
			} else {
				double result = 0;
				if (source.getFieldValue("OVERRIDE_CURR_RATE") == null) {
					double rate_mult = ((BigDecimal) source
							.getFieldValue("RATE_MULT")).doubleValue();
					double rate_div = ((BigDecimal) source
							.getFieldValue("RATE_DIV")).doubleValue();
					result = sales_tax_subtotal * rate_mult / rate_div;
				} else {
					double override_curr_rate = ((BigDecimal) source
							.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
					result = sales_tax_subtotal / override_curr_rate;
				}
				return new Double(result);
			}
		} else if (fieldName.equals("TEXT_62")) {
			// ( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new
			// Double($V{sales_tax_subtotal}.setScale(2,4).doubleValue()) :
			// ($F{OVERRIDE_CURR_RATE} == null) ?
			// new
			// Double(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue())
			// :
			// new
			// Double(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))
			double sales_tax_subtotal = ((BigDecimal) source
					.getCalculatedField("sales_tax_subtotal")).setScale(2, 4)
					.doubleValue();
			if (source.getFieldValue("TRANSACT_CURRENCY_CD").equals(
					source.getFieldValue("TO_CURRENCY"))) {
				return new Double(sales_tax_subtotal);
			} else {
				double result = 0;
				if (source.getFieldValue("OVERRIDE_CURR_RATE") == null) {
					double rate_mult = ((BigDecimal) source
							.getFieldValue("RATE_MULT")).doubleValue();
					double rate_div = ((BigDecimal) source
							.getFieldValue("RATE_DIV")).doubleValue();
					result = sales_tax_subtotal * rate_mult / rate_div;
				} else {
					double override_curr_rate = ((BigDecimal) source
							.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
					result = sales_tax_subtotal / override_curr_rate;
				}
				return new Double(result);
			}
		} else if (fieldName.equals("TEXT_23")) {
			// ( (($F{BUSINESS_UNITS_ADDRESS1} == null) ||
			// ($F{BUSINESS_UNITS_ADDRESS1}.trim()).isEmpty()) ? "" :
			// $F{BUSINESS_UNITS_ADDRESS1}+", ")
			// + ( (($F{BUSINESS_UNITS_ADDRESS2} == null) ||
			// ($F{BUSINESS_UNITS_ADDRESS2}.trim()).isEmpty()) ? "" :
			// $F{BUSINESS_UNITS_ADDRESS2}+", ")
			// + ( (($F{BUSINESS_UNITS_ADDRESS3} == null) ||
			// ($F{BUSINESS_UNITS_ADDRESS3}.trim()).isEmpty()) ? "" :
			// $F{BUSINESS_UNITS_ADDRESS3}+", ")
			// + ( (($F{BUSINESS_UNITS_CITY} == null) ||
			// ($F{BUSINESS_UNITS_CITY}.trim()).isEmpty()) ? "" :
			// $F{BUSINESS_UNITS_CITY}+", ")
			// + ( (($F{BUSINESS_UNITS_STATE_CODE} == null) ||
			// ($F{BUSINESS_UNITS_STATE_CODE}.trim()).isEmpty() ||
			// $F{BU_NAME}.equalsIgnoreCase( "KOR01" )) ? "" :
			// $F{BUSINESS_UNITS_STATE_CODE}+", ")
			// + ( (($F{BUSINESS_UNITS_POSTAL} == null) ||
			// ($F{BUSINESS_UNITS_POSTAL}.trim()).isEmpty()) ? "" :
			// $F{BUSINESS_UNITS_POSTAL})
			// + ( (($F{NAME} == null) || ($F{NAME}.trim()).isEmpty()) ? "" :
			// ( $F{NAME}.equals( $F{BUSINESS_UNITS_CITY} )? "" : $F{NAME}))+
			// "."
			String buAddr1 = (String) source
					.getFieldValue("BUSINESS_UNITS_ADDRESS1");
			String buAddr2 = (String) source
					.getFieldValue("BUSINESS_UNITS_ADDRESS2");
			String buAddr3 = (String) source
					.getFieldValue("BUSINESS_UNITS_ADDRESS3");
			String buCity = (String) source
					.getFieldValue("BUSINESS_UNITS_CITY");
			String buState = (String) source
					.getFieldValue("BUSINESS_UNITS_STATE_CODE");
			String buName = (String) source.getFieldValue("BU_NAME");
			String buPostal = (String) source
					.getFieldValue("BUSINESS_UNITS_POSTAL");
			String name = (String) source.getFieldValue("NAME");

			StringBuilder sb = new StringBuilder();
			if (buAddr1 != null && buAddr1.trim().length() > 0) {
				sb.append(buAddr1.trim()).append(", ");
			}
			if (buAddr2 != null && buAddr2.trim().length() > 0) {
				sb.append(buAddr2.trim()).append(", ");
			}
			if (buAddr3 != null && buAddr3.trim().length() > 0) {
				sb.append(buAddr3.trim()).append(", ");
			}
			if (buCity != null && buCity.trim().length() > 0) {
				sb.append(buCity.trim()).append(", ");
			}
			if (buState != null && buState.trim().length() > 0
					&& !buName.equalsIgnoreCase("KOR01")) {
				sb.append(buState.trim()).append(", ");
			}
			if (buPostal != null && buPostal.trim().length() > 0) {
				sb.append(buPostal.trim()).append(" ");
			}
			if (name != null && name.trim().length() > 0
					&& !name.equals(buCity)) {
				sb.append(name.trim()).append(".");
			}
			return sb.toString();
		} else if (fieldName.equals("VAT_LABEL")
				|| fieldName.equals("SALES_TAX_LABEL")
				|| fieldName.equals("VAT_REG_LABEL")) {
			/*
			 * The TAX_country_code is defined as the BU’s Country Code. The
			 * TAX_state_code is defined as: If the BU’s COUNTRY_CODE is equal
			 * to “CAN” and the Ship To Country Code is equal to the BU’s
			 * COUNTRY_CODE then If true: TAX_state_code will equal Ship To
			 * State Code Else if false: TAX_state_code will be left blank. The
			 * TAX_country_code & the TAX_state_code are used in the
			 * invoice_tax_vatlabelcalc to query the PHOENIX.TAX_LABELS table
			 * where COUNTRY_CODE = TAX_country_code and STATE = TAX_state_code
			 */
			String buCountryCode = (String) source
					.getFieldValue("BUSINESS_UNITS_COUNTRY_CODE");
			String stateCode = null;
			if (buCountryCode.equals("CAN")
					&& buCountryCode.equals(source
							.getFieldValue("SHIP_TO_COUNTRY"))) {
				stateCode = (String) source.getFieldValue("SHIP_TO_STATE");
			}
			String[] keyValues = new String[] { buCountryCode, stateCode, };
			return ReportUtil.getReferenceFieldValue(
					REPORT_INVOICE_TAX_VATLABEL, keyValues, fieldName);
		} else {
			return source.getCalculatedField(fieldName);
		}
	}

	@Override
	public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params) {
		String[] fields = { "NET_PRICE", "VAT_AMT", "TAX_AMT", "TAX_PCT" };
		double[] results = ReportUtil.summariseFields(fields, amds);

		amds.addCalculatedField("subtotal", new BigDecimal(String
				.valueOf(results[0])));
		amds.addCalculatedField("VAT_subtotal", new BigDecimal(String
				.valueOf(results[1])));
		amds.addCalculatedField("sales_tax_subtotal", new BigDecimal(String
				.valueOf(results[2])));
		amds.addCalculatedField("sum_tax_pct", new BigDecimal(String
				.valueOf(results[3])));
	}
}
