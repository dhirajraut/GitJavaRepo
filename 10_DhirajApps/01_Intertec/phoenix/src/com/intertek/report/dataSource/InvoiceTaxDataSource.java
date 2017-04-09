/**
 * 
 */
package com.intertek.report.dataSource;

import static com.intertek.report.ReportConstants.REPORT_INVOICE_TAX_VATLABEL;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;

import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.Pair;
import com.intertek.report.ReportUtil;

/**
 * 
 * @author richard.qin
 */
public class InvoiceTaxDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceTaxDataSource.class);
    private static final String[] fieldNames = {
        "JOB_NUMBER",
        "INVOICE",
        "UID20",
        "CREDIT_IND",
        "CUSTOMERS_NAME",
        "BUSINESS_UNITS_DESCRIPTION",
        "BUSINESS_UNITS_COMPANY_DESC",
        "BUSINESS_UNITS_ADDRESS1",
        "BUSINESS_UNITS_CITY",
        "BUSINESS_UNITS_POSTAL",
        "BUSINESS_UNITS_PHONE_PREFIX",
        "BUSINESS_UNITS_PHONE_NUMBER",
        "BUSINESS_UNITS_PHONE_EXTENSION",
        "BUSINESS_UNITS_ADDRESS2",
        "BUSINESS_UNITS_ADDRESS3",
        "BUSINESS_UNITS_ADDRESS4",
        "BUSINESS_UNITS_COUNTRY_CODE",
        "BUSINESS_UNITS_STATE_CODE",
        "NAME",
        "BUSINESS_UNITS_COUNTRY2_CODE",
        "SHIP_TO_COUNTRY",
        "SHIP_TO_STATE",
        "INVOICE_LINE_ID",
        "LINE_DESCRIPTION",
        "SPLIT_PCT",
        "DISCOUNT_PCT",
        "UNIT_PRICE",
        "TAX_CODE",
        "TAX_PCT",
        "TAX_AMT",
        "VAT_CODE",
        "VAT_PCT",
        "VAT_AMT",
        "NET_PRICE",
        "TAX_ARTICLE_CODE",
        "ACCOUNT",
        "PRODUCT_GROUP",
        "DEPTID",
        "PRIMARY_BRANCH_CD",
        "LEVEL0",
        "VESSEL_SORT_NUM",
        "LEVEL1",
        "LOT_SORT_NUM",
        "LEVEL2",
        "CHARGE_SORT_NUM",
        "COMMENTS",
        "INVOICE_DESCR",
        "JOB_DESCRIPTION",
        "VAT_LABEL",
        "SALES_TAX_LABEL",
        "VAT_REG_LABEL",
        "VAT_REGISTRATION_ID",
        "TRANSACT_CURRENCY_CD",
        "BANK_ACCT_DESC",
        "DFI_ID_NUM",
        "BANK_CODE",
        "BANK_ACCT_CODE",
        "BANKS_BANK_DESC",
        "BANKS_ADDRESS1",
        "BANKS_ADDRESS2",
        "BANKS_ADDRESS3",
        "BANKS_CITY",
        "BANKS_STATE_CODE",
        "BANKS_POSTAL",
        "BANKS_COUNTRY_CODE",
        "BANKS_BANK_ID_NBR",
        "BANK_LANG_DESC",
        "BANK_ACCT_ADDRESS1",
        "BANK_ACCT_ADDRESS2",
        "BANK_ACCT_ADDRESS3",
        "BANK_ACCT_ADDRESS4",
        "BANK_ACCT_CITY",
        "BANK_ACCT_STATE_CODE",
        "BANK_ACCT_COUNTRY_CODE",
        "BANK_ACCT_POSTAL",
        "BANK_ACCONT_NUM",
        "IBAN_CHECK_DIGIT",
        "CHECK_DIGIT",
        "DEPOSIT_TYPE",
        "BANK_BRANCH_ID",
        "BRANCHES_COMPANY_DESC",
        "BRANCHES_PHONE_PREFIX",
        "BRANCHES_PHONE_NUMBER",
        "BRANCHES_PHONE_EXTENSION",
        "TERMS_URL",
        "BU_NAME",
        "COMPANY_LANG_DESC",
        "LOGO_NAME",
        "sellers_country",
        "DECIMAL_DIGITS",
        "INV_AMT_PRE_TAX",
        "INV_AMT_POST_TAX",
        //START: Adding new two fields to store VAT and Tax total
        "AMT_TAX",
        "AMT_VAT"
        //END: Adding new two fields to store VAT and Tax total        
    };
    
    private static final String[] fieldTypes = {
        "INVOICE_LINE_ID",  "BigDecimal", 
        "SPLIT_PCT",        "BigDecimal", 
        "DISCOUNT_PCT",     "BigDecimal", 
        "UNIT_PRICE",       "BigDecimal", 
        "TAX_PCT",          "BigDecimal", 
        "TAX_AMT",          "BigDecimal", 
        "VAT_PCT",          "BigDecimal", 
        "VAT_AMT",          "BigDecimal", 
        "NET_PRICE",        "BigDecimal", 
        "VESSEL_SORT_NUM",  "BigDecimal", 
        "LOT_SORT_NUM",     "BigDecimal", 
        "CHARGE_SORT_NUM",  "BigDecimal", 
        "DECIMAL_DIGITS",   "BigDecimal", 
        "INV_AMT_PRE_TAX",  "BigDecimal", 
        "INV_AMT_POST_TAX", "BigDecimal",
        //START: Adding new two fields to store VAT and Tax total
        "AMT_TAX",			"BigDecimal",
        "AMT_VAT", 			"BigDecimal"
        //END: Adding new two fields to store VAT and Tax total
    };
    
    public InvoiceTaxDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql  = "SELECT  "
            + "     jc.JOB_NUMBER,"
            + "     jci.INVOICE,"
            + "     jc.UID20,"
            + " jci.CREDIT_IND,"
            + "     jci.CUSTOMERS_NAME,"
            + "     jci.BUSINESS_UNITS_DESCRIPTION,"
            + "     jci.BUSINESS_UNITS_COMPANY_DESC,"
            + "     jci.BUSINESS_UNITS_ADDRESS1,"
            + "     jci.BUSINESS_UNITS_CITY,"
            + "     jci.BUSINESS_UNITS_POSTAL,"
            + "     jci.BUSINESS_UNITS_PHONE_PREFIX,"
            + "     jci.BUSINESS_UNITS_PHONE_NUMBER,"
            + "     jci.BUSINESS_UNITS_PHONE_EXTENSION,"
            + "     jci.BUSINESS_UNITS_ADDRESS2,"
            + "     jci.BUSINESS_UNITS_ADDRESS3,"
            + "     jci.BUSINESS_UNITS_ADDRESS4,"
            + "     jci.BUSINESS_UNITS_COUNTRY_CODE,"
            + "     jci.BUSINESS_UNITS_STATE_CODE,"
            + "     jci.BUSINESS_UNITS_COUNTRY_NAME NAME,"
            + " c.COUNTRY2 AS \"BUSINESS_UNITS_COUNTRY2_CODE\","
            + " ca.COUNTRY AS \"SHIP_TO_COUNTRY\","
            + "     ca.STATE AS \"SHIP_TO_STATE\","
            + " il.INVOICE_LINE_ID,"
            + "     il.LINE_DESCRIPTION,    "
            + "     il.SPLIT_PCT,       "
            + "     il.DISCOUNT_PCT, "
            + "     il.UNIT_PRICE,"
            + "     il.TAX_CODE,"
            + "     il.TAX_PCT,"
            + "     il.TAX_AMT,"
            + "     il.VAT_CODE,"
            + "     il.VAT_PCT,"
            + "     il.VAT_AMT,"
            + "     il.NET_PRICE,   "
            + "     il.TAX_ARTICLE_CODE,    "
            + "     il.ACCOUNT,       "
            + "     il.PRODUCT_GROUP, "
            + "     il.DEPTID,"
            + "     il.PRIMARY_BRANCH_CD,"
            + "     il.LEVEL0,        "
            + "     il.VESSEL_SORT_NUM,   "
            + "     il.LEVEL1,    "
            + "     il.LOT_SORT_NUM, "
            + "     il.LEVEL2,      "
            + "     il.CHARGE_SORT_NUM,"
            + " il.COMMENTS,"
            + " jci.INVOICE_DESCR,   "
            + "     jci.JOB_DESCRIPTION,"
            + "     jci.VAT_LABEL,"
            + "     jci.SALES_TAX_LABEL,"
            + "     jci.VAT_REG_LABEL,"
            + "     jci.VAT_REGISTRATION_ID,"
            + "     jci.TRANSACT_CURRENCY_CD, "
            + "     jci.BANK_ACCT_DESC,     "
            + "     jci.DFI_ID_NUM,     "
            + "     jci.BANK_CODE,    "
            + "     jci.BANK_ACCT_CODE,     "
            + "     jci.BANKS_BANK_DESC,"
            + "     jci.BANKS_ADDRESS1,"
            + "     jci.BANKS_ADDRESS2,"
            + "     jci.BANKS_ADDRESS3,"
            + " b.CITY as \"BANKS_CITY\","
            + "     jci.BANKS_STATE_CODE,"
            + "     b.POSTAL as \"BANKS_POSTAL\","
            + "     jci.BANKS_COUNTRY_CODE,"
            + "     jci.BANKS_BANK_ID_NBR,"
            + "     (select bal.BANK_ACCT_DESC from PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, "
            + "        PHOENIX.job_orders jo,PHOENIX.BANK_ACCOUNTS_LANG bal"
            + "        where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "           AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "           AND jo.BU_NAME = bal.BUSINESS_UNIT_NAME "
            + "           AND jci.BANK_CODE = bal.BANK_CODE "
            + "           AND jci.BANK_ACCT_CODE = bal.BANK_ACCT_CODE"
            + "           AND bal.LANGUAGE_CD = 'KOR'"
            + "           AND jc.JOB_NUMBER = :JOB_NUMBER_1"
            + "           AND jc.UID20 = :UID20_1"
            + "           AND jci.INVOICE = :INVOICE_) as \"BANK_LANG_DESC\","
            + "     ba.BANK_ACCT_DESC,"
            + "     ba.ADDRESS1 as \"BANK_ACCT_ADDRESS1\","
            + "     ba.ADDRESS2 as \"BANK_ACCT_ADDRESS2\","
            + "     ba.ADDRESS3 as \"BANK_ACCT_ADDRESS3\","
            + "     ba.ADDRESS4 as \"BANK_ACCT_ADDRESS4\","
            + "     ba.CITY as \"BANK_ACCT_CITY\","
            + "     ba.STATE_CODE as \"BANK_ACCT_STATE_CODE\","
            + "     ba.COUNTRY_CODE as \"BANK_ACCT_COUNTRY_CODE\","
            + "     ba.POSTAL as \"BANK_ACCT_POSTAL\","
            + " ba.BANK_ACCONT_NUM,"
            + "     ba.IBAN_CHECK_DIGIT,"
            + "     ba.CHECK_DIGIT,"
            + "     ba.DEPOSIT_TYPE,"
            + " ba.BANK_BRANCH_ID,"
            + "     jci.BRANCHES_COMPANY_DESC,"
            + "     jci.BRANCHES_PHONE_PREFIX,"
            + "     jci.BRANCHES_PHONE_NUMBER,"
            + "     jci.BRANCHES_PHONE_EXTENSION,"
            + " ctu.terms_url,"
            + " jo.bu_name,"
            + "     (select bul.COMPANY_DESC from PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, "
            + "        PHOENIX.job_orders jo,PHOENIX.BUSINESS_UNITS_LANG bul"
            + "        where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "        AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "        AND jo.BU_NAME = bul.BU_NAME"
            + "        AND bul.LANGUAGE_CD = 'KOR'"
            + "        AND jc.JOB_NUMBER = :JOB_NUMBER_2"
            + "        AND jc.UID20 = :UID20_2"
            + "        AND jci.INVOICE = :INVOICE_2) AS \"COMPANY_LANG_DESC\","
            + " br.LOGO_NAME,"
            + " (select co.country_code from PHOENIX.country co, PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci "
            + " where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID "
            + "     AND jci.BUSINESS_UNITS_COUNTRY_CODE = co.COUNTRY_CODE"
            + "     AND jc.JOB_NUMBER = :JOB_NUMBER_3"
            + "     AND jc.UID20 = :UID20_3"
            + "     AND jci.INVOICE = :INVOICE_3) as \"sellers_country\","
            + " cur.DECIMAL_DIGITS,"
            + " jci.INV_AMT_PRE_TAX,"
            + " jci.INV_AMT_POST_TAX, "
            //START: Adding new two fields to store VAT and Tax total
            + " jci.AMT_TAX, "
            + " jci.AMT_VAT "
            //END: Adding new two fields to store VAT and Tax total            
            + "   FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, PHOENIX.INVOICE_LINES il, "
            + "   PHOENIX.country_terms_urls ctu, PHOENIX.job_orders jo, PHOENIX.BANK_ACCOUNTS ba,"
            + "   PHOENIX.BANKS b, PHOENIX.COUNTRY c, PHOENIX.BRANCHES br, PHOENIX.CUST_ADDRESSES ca,"
            + "   PHOENIX.BUSINESS_UNITS bu, PHOENIX.CURRENCY cur"
            + "   WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "     AND jci.JOB_CONTRACT_INVOICE_ID = il.JOB_CONTRACT_INVOICE_ID"
            + " and jci.BUSINESS_UNITS_COUNTRY_CODE = ctu.country_code (+)"
            + " AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "     AND jci.BANK_CODE = ba.BANK_CODE"
            + "     AND jci.BANK_ACCT_CODE = ba.BANK_ACCT_CODE"
            + "     AND jci.BANK_CODE = b.BANK_CODE"
            + " AND jci.BUSINESS_UNITS_COUNTRY_CODE = c.COUNTRY_CODE"
            + " AND jo.BRANCH_NAME = br.BRANCH_NAME"
            + " AND jo.SHIP_TO_CUST_CODE = ca.CUST_CODE"
            + "     AND jo.SHIP_TO_ADDR_NUM = ca.LOCATION_NUMBER"
            + " AND jo.BU_NAME = bu.BU_NAME"
            + "     AND jci.TRANSACT_CURRENCY_CD = cur.CURRENCY_CD"
            + "     AND jc.JOB_NUMBER = :JOB_NUMBER_4"
            + "     AND jc.UID20 = :UID20_4"
            + "     AND jci.INVOICE = :INVOICE_4"
            + "   ORDER BY"
            + "     VESSEL_SORT_NUM ASC,"
            + "     LOT_SORT_NUM ASC,"
            + "     CHARGE_SORT_NUM ASC";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER_1", params.get("Job_Number")),
            new Pair<String, Object>("UID20_1", params.get("uid20")),
            new Pair<String, Object>("INVOICE_1", params.get("Invoice")),
            new Pair<String, Object>("JOB_NUMBER_2", params.get("Job_Number")),
            new Pair<String, Object>("UID20_2", params.get("uid20")),
            new Pair<String, Object>("INVOICE_2", params.get("Invoice")),
            new Pair<String, Object>("JOB_NUMBER_3", params.get("Job_Number")),
            new Pair<String, Object>("UID20_3", params.get("uid20")),
            new Pair<String, Object>("INVOICE_3", params.get("Invoice")),
            new Pair<String, Object>("JOB_NUMBER_4", params.get("Job_Number")),
            new Pair<String, Object>("UID20_4", params.get("uid20")),
            new Pair<String, Object>("INVOICE_4", params.get("Invoice")),
        };

        // make sure the required reference table is loaded
        String[] refKeys = new String[]{"COUNTRY_CODE", "STATE"};
        ReportUtil.loadReferenceDataSource(REPORT_INVOICE_TAX_VATLABEL, refKeys, null);
        
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        String sql  = "SELECT  "
                    + "     jc.JOB_NUMBER,"
                    + "     jci.INVOICE,"
                    + "     jc.UID20,"
                    + " jci.CREDIT_IND,"
                    + "     jci.CUSTOMERS_NAME,"
                    + "     jci.BUSINESS_UNITS_DESCRIPTION,"
                    + "     jci.BUSINESS_UNITS_COMPANY_DESC,"
                    + "     jci.BUSINESS_UNITS_ADDRESS1,"
                    + "     jci.BUSINESS_UNITS_CITY,"
                    + "     jci.BUSINESS_UNITS_POSTAL,"
                    + "     jci.BUSINESS_UNITS_PHONE_PREFIX,"
                    + "     jci.BUSINESS_UNITS_PHONE_NUMBER,"
                    + "     jci.BUSINESS_UNITS_PHONE_EXTENSION,"
                    + "     jci.BUSINESS_UNITS_ADDRESS2,"
                    + "     jci.BUSINESS_UNITS_ADDRESS3,"
                    + "     jci.BUSINESS_UNITS_ADDRESS4,"
                    + "     jci.BUSINESS_UNITS_COUNTRY_CODE,"
                    + "     jci.BUSINESS_UNITS_STATE_CODE,"
                    + "     jci.BUSINESS_UNITS_COUNTRY_NAME NAME,"
                    + " c.COUNTRY2 AS \"BUSINESS_UNITS_COUNTRY2_CODE\","
                    + " ca.COUNTRY AS \"SHIP_TO_COUNTRY\","
                    + "     ca.STATE AS \"SHIP_TO_STATE\","
                    + " il.INVOICE_LINE_ID,"
                    + "     il.LINE_DESCRIPTION,    "
                    + "     il.SPLIT_PCT,       "
                    + "     il.DISCOUNT_PCT, "
                    + "     il.UNIT_PRICE,"
                    + "     il.TAX_CODE,"
                    + "     il.TAX_PCT,"
                    + "     il.TAX_AMT,"
                    + "     il.VAT_CODE,"
                    + "     il.VAT_PCT,"
                    + "     il.VAT_AMT,"
                    + "     il.NET_PRICE,   "
                    + "     il.TAX_ARTICLE_CODE,    "
                    + "     il.ACCOUNT,       "
                    + "     il.PRODUCT_GROUP, "
                    + "     il.DEPTID,"
                    + "     il.PRIMARY_BRANCH_CD,"
                    + "     il.LEVEL0,        "
                    + "     il.VESSEL_SORT_NUM,   "
                    + "     il.LEVEL1,    "
                    + "     il.LOT_SORT_NUM, "
                    + "     il.LEVEL2,      "
                    + "     il.CHARGE_SORT_NUM,"
                    + " il.COMMENTS,"
                    + " jci.INVOICE_DESCR,   "
                    + "     jci.JOB_DESCRIPTION,"
                    + "     jci.VAT_LABEL,"
                    + "     jci.SALES_TAX_LABEL,"
                    + "     jci.VAT_REG_LABEL,"
                    + "     jci.VAT_REGISTRATION_ID,"
                    + "     jci.TRANSACT_CURRENCY_CD, "
                    + "     jci.BANK_ACCT_DESC,     "
                    + "     jci.DFI_ID_NUM,     "
                    + "     jci.BANK_CODE,    "
                    + "     jci.BANK_ACCT_CODE,     "
                    + "     jci.BANKS_BANK_DESC,"
                    + "     jci.BANKS_ADDRESS1,"
                    + "     jci.BANKS_ADDRESS2,"
                    + "     jci.BANKS_ADDRESS3,"
                    + " b.CITY as \"BANKS_CITY\","
                    + "     jci.BANKS_STATE_CODE,"
                    + "     b.POSTAL as \"BANKS_POSTAL\","
                    + "     jci.BANKS_COUNTRY_CODE,"
                    + "     jci.BANKS_BANK_ID_NBR,"
                    + "     (select bal.BANK_ACCT_DESC from PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, "
                    + "        PHOENIX.job_orders jo,PHOENIX.BANK_ACCOUNTS_LANG bal"
                    + "        where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
                    + "           AND jc.JOB_NUMBER = jo.JOB_NUMBER"
                    + "           AND jo.BU_NAME = bal.BUSINESS_UNIT_NAME "
                    + "           AND jci.BANK_CODE = bal.BANK_CODE "
                    + "           AND jci.BANK_ACCT_CODE = bal.BANK_ACCT_CODE"
                    + "           AND bal.LANGUAGE_CD = 'KOR'"
                    + "           AND jc.JOB_NUMBER = ?"
                    + "           AND jc.UID20 = ?"
                    + "           AND jci.INVOICE = ?) as \"BANK_LANG_DESC\","
                    + "     ba.BANK_ACCT_DESC,"
                    + "     ba.ADDRESS1 as \"BANK_ACCT_ADDRESS1\","
                    + "     ba.ADDRESS2 as \"BANK_ACCT_ADDRESS2\","
                    + "     ba.ADDRESS3 as \"BANK_ACCT_ADDRESS3\","
                    + "     ba.ADDRESS4 as \"BANK_ACCT_ADDRESS4\","
                    + "     ba.CITY as \"BANK_ACCT_CITY\","
                    + "     ba.STATE_CODE as \"BANK_ACCT_STATE_CODE\","
                    + "     ba.COUNTRY_CODE as \"BANK_ACCT_COUNTRY_CODE\","
                    + "     ba.POSTAL as \"BANK_ACCT_POSTAL\","
                    + " ba.BANK_ACCONT_NUM,"
                    + "     ba.IBAN_CHECK_DIGIT,"
                    + "     ba.CHECK_DIGIT,"
                    + "     ba.DEPOSIT_TYPE,"
                    + " ba.BANK_BRANCH_ID,"
                    + "     jci.BRANCHES_COMPANY_DESC,"
                    + "     jci.BRANCHES_PHONE_PREFIX,"
                    + "     jci.BRANCHES_PHONE_NUMBER,"
                    + "     jci.BRANCHES_PHONE_EXTENSION,"
                    + " ctu.terms_url,"
                    + " jo.bu_name,"
                    + "     (select bul.COMPANY_DESC from PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, "
                    + "        PHOENIX.job_orders jo,PHOENIX.BUSINESS_UNITS_LANG bul"
                    + "        where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
                    + "        AND jc.JOB_NUMBER = jo.JOB_NUMBER"
                    + "        AND jo.BU_NAME = bul.BU_NAME"
                    + "        AND bul.LANGUAGE_CD = 'KOR'"
                    + "        AND jc.JOB_NUMBER = ?"
                    + "        AND jc.UID20 = ?"
                    + "        AND jci.INVOICE = ?) AS \"COMPANY_LANG_DESC\","
                    + " br.LOGO_NAME,"
                    + " (select co.country_code from PHOENIX.country co, PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci "
                    + " where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID "
                    + "     AND jci.BUSINESS_UNITS_COUNTRY_CODE = co.COUNTRY_CODE"
                    + "     AND jc.JOB_NUMBER = ?"
                    + "     AND jc.UID20 = ?"
                    + "     AND jci.INVOICE = ?) as \"sellers_country\","
                    + " cur.DECIMAL_DIGITS,"
                    + " jci.INV_AMT_PRE_TAX,"
                    + " jci.INV_AMT_POST_TAX, "
                    //START: Adding new two fields to store VAT and Tax total
                    + " jci.AMT_TAX, "
                    + " jci.AMT_VAT "
                    //END: Adding new two fields to store VAT and Tax total
                    + "   FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, PHOENIX.INVOICE_LINES il, "
                    + "   PHOENIX.country_terms_urls ctu, PHOENIX.job_orders jo, PHOENIX.BANK_ACCOUNTS ba,"
                    + "   PHOENIX.BANKS b, PHOENIX.COUNTRY c, PHOENIX.BRANCHES br, PHOENIX.CUST_ADDRESSES ca,"
                    + "   PHOENIX.BUSINESS_UNITS bu, PHOENIX.CURRENCY cur"
                    + "   WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
                    + "     AND jci.JOB_CONTRACT_INVOICE_ID = il.JOB_CONTRACT_INVOICE_ID"
                    + " and jci.BUSINESS_UNITS_COUNTRY_CODE = ctu.country_code (+)"
                    + " AND jc.JOB_NUMBER = jo.JOB_NUMBER"
                    + "     AND jci.BANK_CODE = ba.BANK_CODE"
                    + "     AND jci.BANK_ACCT_CODE = ba.BANK_ACCT_CODE"
                    + "     AND jci.BANK_CODE = b.BANK_CODE"
                    + " AND jci.BUSINESS_UNITS_COUNTRY_CODE = c.COUNTRY_CODE"
                    + " AND jo.BRANCH_NAME = br.BRANCH_NAME"
                    + " AND jo.SHIP_TO_CUST_CODE = ca.CUST_CODE"
                    + "     AND jo.SHIP_TO_ADDR_NUM = ca.LOCATION_NUMBER"
                    + " AND jo.BU_NAME = bu.BU_NAME"
                    + "     AND jci.TRANSACT_CURRENCY_CD = cur.CURRENCY_CD"
                    + "     AND jc.JOB_NUMBER = ?"
                    + "     AND jc.UID20 = ?"
                    + "     AND jci.INVOICE = ?"
                    + "   ORDER BY"
                    + "     VESSEL_SORT_NUM ASC,"
                    + "     LOT_SORT_NUM ASC,"
                    + "     CHARGE_SORT_NUM ASC";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice")),
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice")),
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice")),
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice")),
        };
        
        // make sure the required reference table is loaded
        String[] refKeys = new String[]{"COUNTRY_CODE", "STATE"};
        ReportUtil.loadReferenceDataSource(REPORT_INVOICE_TAX_VATLABEL, refKeys, con);
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
    }

    /**
     * The following calculation appeared in two different reports, so the logic is moved to
     * a common class
     */
    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("TEXT_22")){
            return DataSourceHelper.calculateInvoiceReportTitle(fieldName, params, source);
        }
        else if(fieldName.equals("TEXT_39")){
            return DataSourceHelper.calculateInvoiceBankDetail(fieldName, params, source);
        }
        else if(fieldName.equals("TEXT_35")){
            return DataSourceHelper.calculateInvoiceBankDetail2(fieldName, params, source);
        }
        else if(fieldName.equals("TEXT_37")){
            return DataSourceHelper.calculateInvoiceBankDetail3(fieldName, params, source);
        }
        else if(fieldName.equals("VAT_LABEL") 
            || fieldName.equals("SALES_TAX_LABEL") 
            || fieldName.equals("VAT_REG_LABEL")){
            /*
             * The TAX_country_code is defined as the BU’s Country Code.
             * The TAX_state_code is defined as: If the BU’s COUNTRY_CODE 
             * is equal to “CAN” and the Ship To Country Code is equal to the BU’s COUNTRY_CODE then
             * If true: TAX_state_code will equal Ship To State Code
             * Else if false: TAX_state_code will be left blank.
             * The TAX_country_code & the TAX_state_code are used 
             * in the invoice_tax_vatlabelcalc to query the PHOENIX.TAX_LABELS table 
             * where COUNTRY_CODE = TAX_country_code and STATE = TAX_state_code
             */
            String buCountryCode = (String)source.getFieldValue("BUSINESS_UNITS_COUNTRY_CODE");
            String stateCode = null;
            if( buCountryCode.equals( "CAN" ) && buCountryCode.equals( source.getFieldValue("SHIP_TO_COUNTRY"))){
                stateCode = (String)source.getFieldValue("SHIP_TO_STATE");
            }
            String[] keyValues = new String[]{
                buCountryCode,
                stateCode,
            };
            return ReportUtil.getReferenceFieldValue(REPORT_INVOICE_TAX_VATLABEL, keyValues, fieldName);
        }
        return source.getCalculatedField(fieldName);
    }
    
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params){
        // subTotal: sum of NET_PRICE
        // VAT_subtotal: sum of VAT_AMT
        // sales_tax_subtotal: sum of TAX_AMT
        // Sum_Tax_Pct: sum of TAX_PCT
        String[] fields = {"NET_PRICE", "VAT_AMT", "TAX_AMT", "TAX_PCT"};
        double[] results = ReportUtil.summariseFields(fields, amds);
        
        amds.addCalculatedField("subtotal", new BigDecimal(String.valueOf(results[0])));
        amds.addCalculatedField("VAT_subtotal", new BigDecimal(String.valueOf(results[1])));
        amds.addCalculatedField("sales_tax_subtotal", new BigDecimal(String.valueOf(results[2])));
        amds.addCalculatedField("sum_tax_pct", new BigDecimal(String.valueOf(results[3])));
    }
}
