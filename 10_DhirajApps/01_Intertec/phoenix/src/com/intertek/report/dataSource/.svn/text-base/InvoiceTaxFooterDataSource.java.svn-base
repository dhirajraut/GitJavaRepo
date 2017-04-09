/**
 * 
 */
package com.intertek.report.dataSource;

import static com.intertek.report.ReportConstants.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.log4j.Logger;

import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.Pair;
import com.intertek.report.ReportUtil;
import com.intertek.util.NumberUtil;

/**
 * 
 * @author richard.qin
 */
public class InvoiceTaxFooterDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceTaxFooterDataSource.class);

    private static final String[] fieldNames = {
        "JOB_NUMBER",
        "INVOICE",
        "UID20",
        "CREDIT_IND",
        "INVOICE_TYPE",
        "CUSTOMERS_NAME",
        "BU_NAME",
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
        "SHIP_TO_COUNTRY",
        "SHIP_TO_STATE",
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
        "TAX_ARTICLE_DESCRIPTION",
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
        "JOB_DESCRIPTION",
        "VAT_LABEL",
        "SALES_TAX_LABEL",
        "VAT_REG_LABEL",
        "VAT_REGISTRATION_ID",
        "VAT_PROVINCE",
        "buyers_country",
        "sellers_country",
        "TRANSACT_CURRENCY_CD",
        "BANK_ACCT_DESC",
        "DFI_ID_NUM",
        "BANK_CODE",
        "BANK_ACCT_CODE",
        "BANKS_BANK_DESC",
        "BANKS_ADDRESS1",
        "BANKS_ADDRESS2",
        "BANKS_ADDRESS3",
        "BANKS_BANK_ID_NBR",
        "BRANCH_NAME",
        "BRANCHES_COMPANY_DESC",
        "BRANCHES_PHONE_PREFIX",
        "BRANCHES_PHONE_NUMBER",
        "BRANCHES_PHONE_EXTENSION",
        "TO_CURRENCY",
        "RATE_MULT",
        "RATE_DIV",
        "OVERRIDE_CURR_RATE",
        "DECIMAL_DIGITS",
        "BU_VAT_REGISTRATION_ID",
        "LOCAL_VAT_ID",
        "INV_AMT_PRE_TAX",
        "INV_AMT_POST_TAX",
      //START: Adding new two fields to store VAT and Tax total
        "AMT_TAX",
        "AMT_VAT",
      //END: Adding new two fields to store VAT and Tax total        
    };
    
    private static final String[] fieldTypes = {
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
        "RATE_MULT",        "BigDecimal",
        "RATE_DIV",         "BigDecimal",
        "OVERRIDE_CURR_RATE", "BigDecimal",
        "DECIMAL_DIGITS",   "BigDecimal",
        "INV_AMT_PRE_TAX",  "BigDecimal",
        "INV_AMT_POST_TAX", "BigDecimal",
      //START: Adding new two fields to store VAT and Tax total
        "AMT_TAX",	"BigDecimal",
        "AMT_VAT",	"BigDecimal"
      //END: Adding new two fields to store VAT and Tax total        
    };
    
    public InvoiceTaxFooterDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql = "SELECT  "
            + "         jc.JOB_NUMBER,"
            + "         jci.INVOICE,"
            + "         jc.UID20,"
            + "     jci.CREDIT_IND,"
            + "     jci.INVOICE_TYPE,"
            + "         jci.CUSTOMERS_NAME,"
            + "     jo.BU_NAME,"
            + "         jci.BUSINESS_UNITS_DESCRIPTION,"
            + "         jci.BUSINESS_UNITS_COMPANY_DESC,"
            + "         jci.BUSINESS_UNITS_ADDRESS1,"
            + "         jci.BUSINESS_UNITS_CITY,"
            + "         jci.BUSINESS_UNITS_POSTAL,"
            + "         jci.BUSINESS_UNITS_PHONE_PREFIX,"
            + "         jci.BUSINESS_UNITS_PHONE_NUMBER,"
            + "         jci.BUSINESS_UNITS_PHONE_EXTENSION,"
            + "         jci.BUSINESS_UNITS_ADDRESS2,"
            + "         jci.BUSINESS_UNITS_ADDRESS3,"
            + "         jci.BUSINESS_UNITS_ADDRESS4,"
            + "         jci.BUSINESS_UNITS_COUNTRY_CODE,"
            + "         jci.BUSINESS_UNITS_STATE_CODE,"
            + "         jci.BUSINESS_UNITS_COUNTRY_NAME NAME,"
            + "     ca.COUNTRY AS \"SHIP_TO_COUNTRY\","
            + "         ca.STATE AS \"SHIP_TO_STATE\","
            + "         il.LINE_DESCRIPTION,    "
            + "         il.SPLIT_PCT,       "
            + "         il.DISCOUNT_PCT, "
            + "         il.UNIT_PRICE,"
            + "         il.TAX_CODE,"
            + "         il.TAX_PCT,"
            + "         il.TAX_AMT,"
            + "         il.VAT_CODE,"
            + "         il.VAT_PCT,"
            + "         il.VAT_AMT,"
            + "         il.NET_PRICE,   "
            + "         il.TAX_ARTICLE_CODE,"
            + "     ta.tax_article_description,    "
            + "         il.ACCOUNT,       "
            + "         il.PRODUCT_GROUP, "
            + "         il.DEPTID,"
            + "         il.PRIMARY_BRANCH_CD,"
            + "         il.LEVEL0,        "
            + "         il.VESSEL_SORT_NUM,   "
            + "         il.LEVEL1,    "
            + "         il.LOT_SORT_NUM, "
            + "         il.LEVEL2,      "
            + "         il.CHARGE_SORT_NUM,   "
            + "         jci.JOB_DESCRIPTION,"
            + "         jci.VAT_LABEL,"
            + "         jci.SALES_TAX_LABEL,"
            + "         jci.VAT_REG_LABEL,"
            + "         jci.VAT_REGISTRATION_ID,"
            + "     jci.VAT_PROVINCE,"
            + "     (select co.country2 from PHOENIX.country co, PHOENIX.job_contracts jc "
            + " where jc.VAT_REGISTRATION_COUNTRY = co.COUNTRY_CODE"
            + "          AND jc.JOB_NUMBER = :JOB_NUMBER_1"
            + "         AND jc.UID20 = :UID20_1"
            + "         AND jci.INVOICE = :INVOICE_1) as \"buyers_country\","
            + "         (select co.country2 from PHOENIX.country co, PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci "
            + " where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID and"
            + "         jci.BUSINESS_UNITS_COUNTRY_CODE = co.COUNTRY_CODE"
            + "          AND jc.JOB_NUMBER = :JOB_NUMBER_2"
            + "         AND jc.UID20 = :UID20_2"
            + "         AND jci.INVOICE = :INVOICE_2) as \"sellers_country\", "
            + "         jci.TRANSACT_CURRENCY_CD, "
            + "         jci.BANK_ACCT_DESC,     "
            + "         jci.DFI_ID_NUM,     "
            + "         jci.BANK_CODE,    "
            + "         jci.BANK_ACCT_CODE,     "
            + "         jci.BANKS_BANK_DESC,"
            + "         jci.BANKS_ADDRESS1,"
            + "         jci.BANKS_ADDRESS2,"
            + "         jci.BANKS_ADDRESS3,"
            + "         jci.BANKS_BANK_ID_NBR,"
            + "         jo.BRANCH_NAME,"
            + "         jci.BRANCHES_COMPANY_DESC,"
            + "         jci.BRANCHES_PHONE_PREFIX,"
            + "         jci.BRANCHES_PHONE_NUMBER,"
            + "         jci.BRANCHES_PHONE_EXTENSION,"
            + "         bu.CURRENCY_BASE AS \"TO_CURRENCY\","
            + "         rt.RATE_MULT,"
            + "         rt.RATE_DIV,"
            + "     jc.OVERRIDE_CURR_RATE,"
            + "     cur.DECIMAL_DIGITS,"
            + "     jci.BU_VAT_REGISTRATION_ID,"
            + "     (select buvl.LOCAL_VAT_ID "
            + "         from PHOENIX.BUS_UNIT_VAT_LOC buvl, PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci,PHOENIX.JOB_ORDERS jo "
            + "         where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "         AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "     AND jci.BU_VAT_REGISTRATION_ID = buvl.VAT_REGISTRATION_ID"
            + "     AND jo.BU_NAME = buvl.BU_NAME"
            + "     AND jc.JOB_NUMBER = :JOB_NUMBER_3"
            + "         AND jc.UID20 = :UID20_3"
            + "         AND jci.INVOICE = :INVOICE_3) as \"LOCAL_VAT_ID\","
            + "     jci.INV_AMT_PRE_TAX,"
            + "     jci.INV_AMT_POST_TAX, "
          //START: Adding new two fields to store VAT and Tax total
            + "     jci.AMT_TAX, "
            + "     jci.AMT_VAT "
          //END: Adding new two fields to store VAT and Tax total
            + "       FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, PHOENIX.INVOICE_LINES il, "
            + "         PHOENIX.tax_articles ta, PHOENIX.JOB_ORDERS jo,"
            + "                 PHOENIX.BUSINESS_UNITS bu, PHOENIX.PS_RT_RATE_TBL rt, PHOENIX.CUST_ADDRESSES ca,"
            + "         PHOENIX.CURRENCY cur"
            + "       WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "         AND jci.JOB_CONTRACT_INVOICE_ID = il.JOB_CONTRACT_INVOICE_ID"
            + "     and il.tax_article_code = ta.tax_article_code (+)"
            + "     AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "         AND jo.BU_NAME = bu.BU_NAME"
            + "         AND jci.TRANSACT_CURRENCY_CD = rt.FROM_CUR"
            + "         AND bu.CURRENCY_BASE = rt.TO_CUR"
            + "         AND rt.RT_TYPE = 'CRRNT'"
            + "         AND rt.EFFDT = (SELECT MAX(RT_ED.EFFDT) FROM PHOENIX.PS_RT_RATE_TBL RT_ED"
            + "                         WHERE RT.RT_RATE_INDEX = RT_ED.RT_RATE_INDEX"
            + "                           AND RT.TERM = RT_ED.TERM"
            + "                           AND RT.FROM_CUR = RT_ED.FROM_CUR"
            + "                           AND RT.TO_CUR = RT_ED.TO_CUR"
            + "                           AND RT.RT_TYPE = RT_ED.RT_TYPE"
            + "                           AND RT_ED.EFFDT <=  jci.JOB_FINISH_DATE)"
            + "     AND jo.SHIP_TO_CUST_CODE = ca.CUST_CODE"
            + "         AND jo.SHIP_TO_ADDR_NUM = ca.LOCATION_NUMBER"
            + "     AND bu.CURRENCY_BASE = cur.CURRENCY_CD"
            + "     AND jc.JOB_NUMBER = :JOB_NUMBER_4"
            + "         AND jc.UID20 = :UID20_4"
            + "         AND jci.INVOICE = :INVOICE_4"
            + " ORDER BY"
            + "         VESSEL_SORT_NUM ASC,"
            + "         LOT_SORT_NUM ASC,"
            + "         CHARGE_SORT_NUM ASC";
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
            new Pair<String, Object>("INVOICE_4", params.get("Invoice"))
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
        String sql = "SELECT  "
            + "         jc.JOB_NUMBER,"
            + "         jci.INVOICE,"
            + "         jc.UID20,"
            + "     jci.CREDIT_IND,"
            + "     jci.INVOICE_TYPE,"
            + "         jci.CUSTOMERS_NAME,"
            + "     jo.BU_NAME,"
            + "         jci.BUSINESS_UNITS_DESCRIPTION,"
            + "         jci.BUSINESS_UNITS_COMPANY_DESC,"
            + "         jci.BUSINESS_UNITS_ADDRESS1,"
            + "         jci.BUSINESS_UNITS_CITY,"
            + "         jci.BUSINESS_UNITS_POSTAL,"
            + "         jci.BUSINESS_UNITS_PHONE_PREFIX,"
            + "         jci.BUSINESS_UNITS_PHONE_NUMBER,"
            + "         jci.BUSINESS_UNITS_PHONE_EXTENSION,"
            + "         jci.BUSINESS_UNITS_ADDRESS2,"
            + "         jci.BUSINESS_UNITS_ADDRESS3,"
            + "         jci.BUSINESS_UNITS_ADDRESS4,"
            + "         jci.BUSINESS_UNITS_COUNTRY_CODE,"
            + "         jci.BUSINESS_UNITS_STATE_CODE,"
            + "         jci.BUSINESS_UNITS_COUNTRY_NAME NAME,"
            + "     ca.COUNTRY AS \"SHIP_TO_COUNTRY\","
            + "         ca.STATE AS \"SHIP_TO_STATE\","
            + "         il.LINE_DESCRIPTION,    "
            + "         il.SPLIT_PCT,       "
            + "         il.DISCOUNT_PCT, "
            + "         il.UNIT_PRICE,"
            + "         il.TAX_CODE,"
            + "         il.TAX_PCT,"
            + "         il.TAX_AMT,"
            + "         il.VAT_CODE,"
            + "         il.VAT_PCT,"
            + "         il.VAT_AMT,"
            + "         il.NET_PRICE,   "
            + "         il.TAX_ARTICLE_CODE,"
            + "     ta.tax_article_description,    "
            + "         il.ACCOUNT,       "
            + "         il.PRODUCT_GROUP, "
            + "         il.DEPTID,"
            + "         il.PRIMARY_BRANCH_CD,"
            + "         il.LEVEL0,        "
            + "         il.VESSEL_SORT_NUM,   "
            + "         il.LEVEL1,    "
            + "         il.LOT_SORT_NUM, "
            + "         il.LEVEL2,      "
            + "         il.CHARGE_SORT_NUM,   "
            + "         jci.JOB_DESCRIPTION,"
            + "         jci.VAT_LABEL,"
            + "         jci.SALES_TAX_LABEL,"
            + "         jci.VAT_REG_LABEL,"
            + "         jci.VAT_REGISTRATION_ID,"
            + "     jci.VAT_PROVINCE,"
            + "     (select co.country2 from PHOENIX.country co, PHOENIX.job_contracts jc "
            + " where jc.VAT_REGISTRATION_COUNTRY = co.COUNTRY_CODE"
            + "          AND jc.JOB_NUMBER = ?"
            + "         AND jc.UID20 = ?"
            + "         AND jci.INVOICE = ?) as \"buyers_country\","
            + "         (select co.country2 from PHOENIX.country co, PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci "
            + " where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID and"
            + "         jci.BUSINESS_UNITS_COUNTRY_CODE = co.COUNTRY_CODE"
            + "          AND jc.JOB_NUMBER = ?"
            + "         AND jc.UID20 = ?"
            + "         AND jci.INVOICE = ?) as \"sellers_country\", "
            + "         jci.TRANSACT_CURRENCY_CD, "
            + "         jci.BANK_ACCT_DESC,     "
            + "         jci.DFI_ID_NUM,     "
            + "         jci.BANK_CODE,    "
            + "         jci.BANK_ACCT_CODE,     "
            + "         jci.BANKS_BANK_DESC,"
            + "         jci.BANKS_ADDRESS1,"
            + "         jci.BANKS_ADDRESS2,"
            + "         jci.BANKS_ADDRESS3,"
            + "         jci.BANKS_BANK_ID_NBR,"
            + "         jo.BRANCH_NAME,"
            + "         jci.BRANCHES_COMPANY_DESC,"
            + "         jci.BRANCHES_PHONE_PREFIX,"
            + "         jci.BRANCHES_PHONE_NUMBER,"
            + "         jci.BRANCHES_PHONE_EXTENSION,"
            + "         bu.CURRENCY_BASE AS \"TO_CURRENCY\","
            + "         rt.RATE_MULT,"
            + "         rt.RATE_DIV,"
            + "     jc.OVERRIDE_CURR_RATE,"
            + "     cur.DECIMAL_DIGITS,"
            + "     jci.BU_VAT_REGISTRATION_ID,"
            + "     (select buvl.LOCAL_VAT_ID "
            + "         from PHOENIX.BUS_UNIT_VAT_LOC buvl, PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci,PHOENIX.JOB_ORDERS jo "
            + "         where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "         AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "     AND jci.BU_VAT_REGISTRATION_ID = buvl.VAT_REGISTRATION_ID"
            + "     AND jo.BU_NAME = buvl.BU_NAME"
            + "     AND jc.JOB_NUMBER = ?"
            + "         AND jc.UID20 = ?"
            + "         AND jci.INVOICE = ?) as \"LOCAL_VAT_ID\","
            + "     jci.INV_AMT_PRE_TAX,"
            + "     jci.INV_AMT_POST_TAX,"
            //START: Adding new two fields to store VAT and Tax total
            + "     jci.AMT_TAX, "
            + "     jci.AMT_VAT "
            //END: Adding new two fields to store VAT and Tax total
            + "       FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, PHOENIX.INVOICE_LINES il, "
            + "         PHOENIX.tax_articles ta, PHOENIX.JOB_ORDERS jo,"
            + "                 PHOENIX.BUSINESS_UNITS bu, PHOENIX.PS_RT_RATE_TBL rt, PHOENIX.CUST_ADDRESSES ca,"
            + "         PHOENIX.CURRENCY cur"
            + "       WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "         AND jci.JOB_CONTRACT_INVOICE_ID = il.JOB_CONTRACT_INVOICE_ID"
            + "     and il.tax_article_code = ta.tax_article_code (+)"
            + "     AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "         AND jo.BU_NAME = bu.BU_NAME"
            + "         AND jci.TRANSACT_CURRENCY_CD = rt.FROM_CUR"
            + "         AND bu.CURRENCY_BASE = rt.TO_CUR"
            + "         AND rt.RT_TYPE = 'CRRNT'"
            + "         AND rt.EFFDT = (SELECT MAX(RT_ED.EFFDT) FROM PHOENIX.PS_RT_RATE_TBL RT_ED"
            + "                         WHERE RT.RT_RATE_INDEX = RT_ED.RT_RATE_INDEX"
            + "                           AND RT.TERM = RT_ED.TERM"
            + "                           AND RT.FROM_CUR = RT_ED.FROM_CUR"
            + "                           AND RT.TO_CUR = RT_ED.TO_CUR"
            + "                           AND RT.RT_TYPE = RT_ED.RT_TYPE"
            + "                           AND RT_ED.EFFDT <=  jci.JOB_FINISH_DATE)"
            + "     AND jo.SHIP_TO_CUST_CODE = ca.CUST_CODE"
            + "         AND jo.SHIP_TO_ADDR_NUM = ca.LOCATION_NUMBER"
            + "     AND bu.CURRENCY_BASE = cur.CURRENCY_CD"
            + "     AND jc.JOB_NUMBER = ?"
            + "         AND jc.UID20 = ?"
            + "         AND jci.INVOICE = ?"
            + " ORDER BY"
            + "         VESSEL_SORT_NUM ASC,"
            + "         LOT_SORT_NUM ASC,"
            + "         CHARGE_SORT_NUM ASC";
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
            new Pair<String, Object>("INVOICE", params.get("Invoice"))
        };
        
        // make sure the required reference table is loaded
        String[] refKeys = new String[]{"COUNTRY_CODE", "STATE"};
        ReportUtil.loadReferenceDataSource(REPORT_INVOICE_TAX_VATLABEL, refKeys, con);
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
    }

    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
//( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? 
//    $F{INV_AMT_POST_TAX} :  
//    ($F{OVERRIDE_CURR_RATE} == null) ? 
//    (new BigDecimal(($V{VAT_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(2,4).add(
//    new BigDecimal($F{INV_AMT_PRE_TAX}.doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(2,4).add(
//    new BigDecimal(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(2,4)))) : 
//    (new BigDecimal($F{INV_AMT_PRE_TAX}.doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()).add(
//    new BigDecimal(($V{VAT_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()).add(
//    new BigDecimal(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue())))))
        if(fieldName.equals("TEXT_52")){
            Double gst = (Double)calculateFieldValue("TEXT_49", params, source);
            Double pretax = (Double)calculateFieldValue("TEXT_50", params, source);
            Double salesTax = (Double)calculateFieldValue("TEXT_51", params, source);
            return new BigDecimal(salesTax.doubleValue() + pretax.doubleValue() + gst.doubleValue()).setScale(2,4);
        }
        else if(fieldName.equals("TEXT_63")){
//( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? 
//    $F{INV_AMT_POST_TAX} :  
//    ($F{OVERRIDE_CURR_RATE} == null) ? 
//    (new BigDecimal(($V{VAT_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(0,4).add(
//    new BigDecimal($F{INV_AMT_PRE_TAX}.doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(0,4).add(
//    new BigDecimal(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()).setScale(0,4)))) : 
//    (new BigDecimal($F{INV_AMT_PRE_TAX}.doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()).setScale(0,4).add(
//    new BigDecimal(($V{VAT_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()).setScale(0,4).add(
//    new BigDecimal(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()).setScale(0,4)))))        

            Double gst = (Double)calculateFieldValue("TEXT_61", params, source);
            Double salesTax = (Double)calculateFieldValue("TEXT_62", params, source);
            Double pretax = (Double)calculateFieldValue("TEXT_60", params, source);            
            return new BigDecimal(salesTax.doubleValue() + pretax.doubleValue() + gst.doubleValue()).setScale(2,4);
        }
        else if(fieldName.equals("TEXT_50")){
//( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new Double($F{INV_AMT_PRE_TAX}.doubleValue()) :  
//    ($F{OVERRIDE_CURR_RATE} == null) ? 
//    new Double($F{INV_AMT_PRE_TAX}.doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()) : 
//    new Double($F{INV_AMT_PRE_TAX}.doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue())) 
            if(source.getFieldValue("TRANSACT_CURRENCY_CD").equals(source.getFieldValue("TO_CURRENCY"))){
                return new Double(((BigDecimal)source.getFieldValue("INV_AMT_PRE_TAX")).doubleValue());
            }
            else {
                double inv_amt_pre_tax = ((BigDecimal)source.getFieldValue("INV_AMT_PRE_TAX")).doubleValue();
                double result = 0;
                if(source.getFieldValue("OVERRIDE_CURR_RATE") == null){
                    double rate_mult = ((BigDecimal)source.getFieldValue("RATE_MULT")).doubleValue();
                    double rate_div = ((BigDecimal)source.getFieldValue("RATE_DIV")).doubleValue();
                    result = inv_amt_pre_tax * rate_mult / rate_div;
                }
                else{
                    double override_curr_rate = ((BigDecimal)source.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
                    result = inv_amt_pre_tax / override_curr_rate ;
                }
                return new Double(NumberUtil.roundHalfUp(result, 2));
            }
        }
        else if(fieldName.equals("TEXT_60")){ 
//( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new Double($F{INV_AMT_PRE_TAX}.doubleValue()) :  
//    ($F{OVERRIDE_CURR_RATE} == null) ? 
//    new Double($F{INV_AMT_PRE_TAX}.doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()) : 
//    new Double($F{INV_AMT_PRE_TAX}.doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))            
            if(source.getFieldValue("TRANSACT_CURRENCY_CD").equals(source.getFieldValue("TO_CURRENCY"))){
                return new Double(((BigDecimal)source.getFieldValue("INV_AMT_PRE_TAX")).doubleValue());
            }
            else {
                double inv_amt_pre_tax = ((BigDecimal)source.getFieldValue("INV_AMT_PRE_TAX")).doubleValue();
                double result = 0;
                if(source.getFieldValue("OVERRIDE_CURR_RATE") == null){
                    double rate_mult = ((BigDecimal)source.getFieldValue("RATE_MULT")).doubleValue();
                    double rate_div = ((BigDecimal)source.getFieldValue("RATE_DIV")).doubleValue();
                    result = inv_amt_pre_tax * rate_mult / rate_div;
                }
                else{
                    double override_curr_rate = ((BigDecimal)source.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
                    result = inv_amt_pre_tax / override_curr_rate ;
                }
                return new Double(NumberUtil.roundHalfUp(result, 2));
            }
        }
        else if(fieldName.equals("TEXT_49")){ 
//( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new Double($V{VAT_subtotal}.setScale(2,4).doubleValue()) :  
//    ($F{OVERRIDE_CURR_RATE} == null) ? 
//    new Double(($V{VAT_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()) : 
//    new Double(($V{VAT_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))
        	//START: Populating values from new column total Tax (AMT_VAT)        	
            //double vat_subtotal = ((BigDecimal)source.getCalculatedField("VAT_subtotal")).setScale(2, 4).doubleValue();
        	double vat_subtotal = ((BigDecimal)source.getFieldValue("AMT_VAT")).setScale(2, 4).doubleValue();
        	//END: Populating values from new column total Tax (AMT_VAT)

        	if(source.getFieldValue("TRANSACT_CURRENCY_CD").equals(source.getFieldValue("TO_CURRENCY"))){
                return new Double(vat_subtotal);
            }
            else {
                double result = 0;
                if(source.getFieldValue("OVERRIDE_CURR_RATE") == null){
                    double rate_mult = ((BigDecimal)source.getFieldValue("RATE_MULT")).doubleValue();
                    double rate_div = ((BigDecimal)source.getFieldValue("RATE_DIV")).doubleValue();
                    result = vat_subtotal * rate_mult / rate_div;
                }
                else{
                    double override_curr_rate = ((BigDecimal)source.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
                    result = vat_subtotal / override_curr_rate ;
                }
                return new Double(NumberUtil.roundHalfUp(result, 2));
            }
        }
        else if(fieldName.equals("TEXT_61")){ 
//( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new Double($V{VAT_subtotal}.setScale(0,1).doubleValue()) :  
//    ($F{OVERRIDE_CURR_RATE} == null) ? 
//    new Double(($V{VAT_subtotal}.setScale(0,1)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()) : 
//    new Double(($V{VAT_subtotal}.setScale(0,1)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))
        	//START: Populating values from new column total Tax (AMT_VAT)        	
            //double vat_subtotal = ((BigDecimal)source.getCalculatedField("VAT_subtotal")).setScale(0, 1).doubleValue();
        	double vat_subtotal = ((BigDecimal)source.getFieldValue("AMT_VAT")).setScale(0, 1).doubleValue();
        	//END: Populating values from new column total Tax (AMT_VAT)
            if(source.getFieldValue("TRANSACT_CURRENCY_CD").equals(source.getFieldValue("TO_CURRENCY"))){
                return new Double(vat_subtotal);
            }
            else {
                double result = 0;
                if(source.getFieldValue("OVERRIDE_CURR_RATE") == null){
                    double rate_mult = ((BigDecimal)source.getFieldValue("RATE_MULT")).doubleValue();
                    double rate_div = ((BigDecimal)source.getFieldValue("RATE_DIV")).doubleValue();
                    result = vat_subtotal * rate_mult / rate_div;
                }
                else{
                    double override_curr_rate = ((BigDecimal)source.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
                    result = vat_subtotal / override_curr_rate ;
                }
                return new Double(NumberUtil.roundHalfUp(result, 2));
            }
        }
        else if(fieldName.equals("TEXT_51")){ 
//( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new Double($V{sales_tax_subtotal}.setScale(2,4).doubleValue()) :  
//    ($F{OVERRIDE_CURR_RATE} == null) ? 
//    new Double(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()) : 
//    new Double(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))        	
           	//START: Populating values from new column total Tax (AMT_TAX)
            //double sales_tax_subtotal = ((BigDecimal)source.getCalculatedField("sales_tax_subtotal")).setScale(2, 4).doubleValue();
        	double sales_tax_subtotal = ((BigDecimal)source.getFieldValue("AMT_TAX")).setScale(2, 4).doubleValue();
        	//END: Populating values from new column total Tax (AMT_TAX)        	

            if(source.getFieldValue("TRANSACT_CURRENCY_CD").equals(source.getFieldValue("TO_CURRENCY"))){
                return new Double(sales_tax_subtotal);
            }
            else {
                double result = 0;
                if(source.getFieldValue("OVERRIDE_CURR_RATE") == null){
                    double rate_mult = ((BigDecimal)source.getFieldValue("RATE_MULT")).doubleValue();
                    double rate_div = ((BigDecimal)source.getFieldValue("RATE_DIV")).doubleValue();
                    result = sales_tax_subtotal * rate_mult / rate_div;
                }
                else{
                    double override_curr_rate = ((BigDecimal)source.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
                    result = sales_tax_subtotal / override_curr_rate ;
                }
                return new Double(NumberUtil.roundHalfUp(result, 2));
            }
        }
        else if(fieldName.equals("TEXT_62")){ 
//( $F{TRANSACT_CURRENCY_CD}.equals( $F{TO_CURRENCY} ) ? new Double($V{sales_tax_subtotal}.setScale(2,4).doubleValue()) :  
//    ($F{OVERRIDE_CURR_RATE} == null) ? 
//    new Double(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()*$F{RATE_MULT}.doubleValue()/$F{RATE_DIV}.doubleValue()) : 
//    new Double(($V{sales_tax_subtotal}.setScale(2,4)).doubleValue()/$F{OVERRIDE_CURR_RATE}.doubleValue()))
        	//START: Populating values from new column total Tax (AMT_TAX)        	
            //double sales_tax_subtotal = ((BigDecimal)source.getCalculatedField("sales_tax_subtotal")).setScale(2, 4).doubleValue();
        	double sales_tax_subtotal = ((BigDecimal)source.getFieldValue("AMT_TAX")).setScale(2, 4).doubleValue();
        	//END: Populating values from new column total Tax (AMT_TAX)
            if(source.getFieldValue("TRANSACT_CURRENCY_CD").equals(source.getFieldValue("TO_CURRENCY"))){
                return new Double(sales_tax_subtotal);
            }
            else {
                double result = 0;
                if(source.getFieldValue("OVERRIDE_CURR_RATE") == null){
                    double rate_mult = ((BigDecimal)source.getFieldValue("RATE_MULT")).doubleValue();
                    double rate_div = ((BigDecimal)source.getFieldValue("RATE_DIV")).doubleValue();
                    result = sales_tax_subtotal * rate_mult / rate_div;
                }
                else{
                    double override_curr_rate = ((BigDecimal)source.getFieldValue("OVERRIDE_CURR_RATE")).doubleValue();
                    result = sales_tax_subtotal / override_curr_rate ;
                }
                return new Double(NumberUtil.roundHalfUp(result, 2));
            }
        }
        else if(fieldName.equals("TEXT_23")){
//( (($F{BUSINESS_UNITS_ADDRESS1} == null) || ($F{BUSINESS_UNITS_ADDRESS1}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_ADDRESS1}+", ")
//+ ( (($F{BUSINESS_UNITS_ADDRESS2} == null) || ($F{BUSINESS_UNITS_ADDRESS2}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_ADDRESS2}+", ") 
//+ ( (($F{BUSINESS_UNITS_ADDRESS3} == null) || ($F{BUSINESS_UNITS_ADDRESS3}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_ADDRESS3}+", ")
//+ ( (($F{BUSINESS_UNITS_CITY} == null) || ($F{BUSINESS_UNITS_CITY}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_CITY}+", ") 
//+ ( (($F{BUSINESS_UNITS_STATE_CODE} == null) || ($F{BUSINESS_UNITS_STATE_CODE}.trim()).isEmpty() || $F{BU_NAME}.equalsIgnoreCase( "KOR01" )) ? "" : $F{BUSINESS_UNITS_STATE_CODE}+", ")
//+ ( (($F{BUSINESS_UNITS_POSTAL} == null) || ($F{BUSINESS_UNITS_POSTAL}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_POSTAL})
//+ ( (($F{NAME} == null) || ($F{NAME}.trim()).isEmpty()) ? "" : 
//    ( $F{NAME}.equals( $F{BUSINESS_UNITS_CITY} )? "" : $F{NAME}))+ "."
            String buAddr1 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS1");
            String buAddr2 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS2");
            String buAddr3 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS3");
            String buCity = (String)source.getFieldValue("BUSINESS_UNITS_CITY");
            String buState = (String)source.getFieldValue("BUSINESS_UNITS_STATE_CODE");
            String buName = (String)source.getFieldValue("BU_NAME");
            String buPostal = (String)source.getFieldValue("BUSINESS_UNITS_POSTAL");
            String name = (String)source.getFieldValue("NAME");
            
            StringBuilder sb = new StringBuilder();
            if(buAddr1 != null && buAddr1.trim().length() > 0){
                sb.append(buAddr1.trim()).append(", ");
            }
            if(buAddr2 != null && buAddr2.trim().length() > 0){
                sb.append(buAddr2.trim()).append(", ");
            }
            if(buAddr3 != null && buAddr3.trim().length() > 0){
                sb.append(buAddr3.trim()).append(", ");
            }
            if(buCity != null && buCity.trim().length() > 0){
                sb.append(buCity.trim()).append(", ");
            }
            if(buState != null && buState.trim().length() > 0 && !buName.equalsIgnoreCase("KOR01")){
                sb.append(buState.trim()).append(", ");
            }
            if(buPostal != null && buPostal.trim().length() > 0){
                sb.append(buPostal.trim()).append(" ");
            }
            if(name != null && name.trim().length() > 0 && !name.equals(buCity)){
                sb.append(name.trim()).append(".");
            }
            return sb.toString();
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
        else {
            return source.getCalculatedField(fieldName);
        }
    }

    @Override
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params){
        String[] fields = {"NET_PRICE", "VAT_AMT", "TAX_AMT", "TAX_PCT"};
        double[] results = ReportUtil.summariseFields(fields, amds);
        
        amds.addCalculatedField("subtotal", new BigDecimal(String.valueOf(results[0])));
        amds.addCalculatedField("VAT_subtotal", new BigDecimal(String.valueOf(results[1])));
        amds.addCalculatedField("sales_tax_subtotal", new BigDecimal(String.valueOf(results[2])));
        amds.addCalculatedField("sum_tax_pct", new BigDecimal(String.valueOf(results[3])));
    }
}
