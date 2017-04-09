/**
 * 
 */
package com.intertek.report.dataSource;

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
public class InvoiceNoprebillDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceNoprebillDataSource.class);
    
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
        "LINE_DESCRIPTION",
        "SPLIT_PCT",
        "DISCOUNT_PCT",
        "NET_PRICE",
        "UNIT_PRICE",
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
        "INVOICE_DESCR",
        "TRANSACT_CURRENCY_CD",
        "JOB_DESCRIPTION",
        "COMMENTS",
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
    };

    private static final String[] fieldTypes = {
        "SPLIT_PCT",        "BigDecimal",
        "DISCOUNT_PCT",     "BigDecimal",
        "NET_PRICE",        "BigDecimal",
        "UNIT_PRICE",       "BigDecimal",
        "VESSEL_SORT_NUM",  "BigDecimal",
        "LOT_SORT_NUM",     "BigDecimal",
        "CHARGE_SORT_NUM",  "BigDecimal",
        "DECIMAL_DIGITS",   "BigDecimal",
        "INV_AMT_PRE_TAX",  "BigDecimal",
        "INV_AMT_POST_TAX", "BigDecimal",
    };
    
    public InvoiceNoprebillDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql = "SELECT"  
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
            + "     il.LINE_DESCRIPTION,"    
            + "     il.SPLIT_PCT,"       
            + "     il.DISCOUNT_PCT," 
            + "     il.NET_PRICE,"       
            + "     il.UNIT_PRICE,"
            + "     il.ACCOUNT,"       
            + "     il.PRODUCT_GROUP," 
            + "     il.DEPTID,"
            + "     il.PRIMARY_BRANCH_CD,"
            + "     il.LEVEL0,"        
            + "     il.VESSEL_SORT_NUM,"   
            + "     il.LEVEL1,"    
            + "     il.LOT_SORT_NUM," 
            + "     il.LEVEL2,"      
            + "     il.CHARGE_SORT_NUM,"
            + " il.COMMENTS,"
            + " jci.INVOICE_DESCR,"   
            + "     jci.TRANSACT_CURRENCY_CD,"
            + "     jci.JOB_DESCRIPTION," 
            + "     jci.BANK_ACCT_DESC,"     
            + "     jci.DFI_ID_NUM,"     
            + "     jci.BANK_CODE,"    
            + "     jci.BANK_ACCT_CODE,"     
            + "     jci.BANKS_BANK_DESC,"
            + "     jci.BANKS_ADDRESS1,"
            + "     jci.BANKS_ADDRESS2,"
            + "     jci.BANKS_ADDRESS3,"
            + "     b.CITY as BANKS_CITY,"
            + "     jci.BANKS_STATE_CODE,"
            + "     b.POSTAL as BANKS_POSTAL,"
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
            + "           AND jci.INVOICE = :INVOICE_1) as BANK_LANG_DESC,"
            + " ba.BANK_ACCT_DESC,"
            + "     ba.ADDRESS1 as BANK_ACCT_ADDRESS1,"
            + "     ba.ADDRESS2 as BANK_ACCT_ADDRESS2,"
            + "     ba.ADDRESS3 as BANK_ACCT_ADDRESS3,"
            + "     ba.ADDRESS4 as BANK_ACCT_ADDRESS4,"
            + "     ba.CITY as BANK_ACCT_CITY,"
            + "     ba.STATE_CODE as BANK_ACCT_STATE_CODE,"
            + "     ba.COUNTRY_CODE as BANK_ACCT_COUNTRY_CODE,"
            + "     ba.POSTAL as BANK_ACCT_POSTAL,"
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
            + "     (select bul.COMPANY_DESC from PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci," 
            + "        PHOENIX.job_orders jo,PHOENIX.BUSINESS_UNITS_LANG bul"
            + "        where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "        AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "        AND jo.BU_NAME = bul.BU_NAME"
            + "        AND bul.LANGUAGE_CD = 'KOR'"
            + "        AND jc.JOB_NUMBER = :JOB_NUMBER_2"
            + "        AND jc.UID20 = :UID20_2"
            + "        AND jci.INVOICE = :INVOICE_2) AS COMPANY_LANG_DESC,"
            + " br.LOGO_NAME,"
            + " (select co.country_code from PHOENIX.country co, PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID and"
            + "     jci.BUSINESS_UNITS_COUNTRY_CODE = co.COUNTRY_CODE"
            + "     AND jc.JOB_NUMBER = :JOB_NUMBER_3"
            + "     AND jc.UID20 = :UID20_3"
            + "     AND jci.INVOICE = :INVOICE_3) as sellers_country,"
            + " cur.DECIMAL_DIGITS,"
            + " jci.INV_AMT_PRE_TAX,"
            + " jci.INV_AMT_POST_TAX"
            + "   FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, PHOENIX.INVOICE_LINES il," 
            + "   PHOENIX.country_terms_urls ctu, PHOENIX.job_orders jo, PHOENIX.BANK_ACCOUNTS ba,"
            + "   PHOENIX.BANKS b, PHOENIX.COUNTRY c, PHOENIX.BRANCHES br,"
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
            + " AND jo.BU_NAME = bu.BU_NAME"
            + "     AND jci.TRANSACT_CURRENCY_CD = cur.CURRENCY_CD"
            + "     AND jc.JOB_NUMBER = :JOB_NUMBER_4"
            + "     AND jc.UID20 = :UID20_4"
            + "     AND jci.INVOICE = :INVOICE_4"
            + "   ORDER BY"
            + "      LEVEL0 ASC,"
            + "      LEVEL1 ASC,"
            + "      LINE_NUMBER ASC";
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
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection conn) {
        String sql = "SELECT"  
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
            + " c.COUNTRY2 AS BUSINESS_UNITS_COUNTRY2_CODE,"
            + "     il.LINE_DESCRIPTION,"    
            + "     il.SPLIT_PCT,"       
            + "     il.DISCOUNT_PCT," 
            + "     il.NET_PRICE,"       
            + "     il.UNIT_PRICE,"
            + "     il.ACCOUNT,"       
            + "     il.PRODUCT_GROUP," 
            + "     il.DEPTID,"
            + "     il.PRIMARY_BRANCH_CD,"
            + "     il.LEVEL0,"        
            + "     il.VESSEL_SORT_NUM,"   
            + "     il.LEVEL1,"    
            + "     il.LOT_SORT_NUM," 
            + "     il.LEVEL2,"      
            + "     il.CHARGE_SORT_NUM,"
            + " il.COMMENTS,"
            + " jci.INVOICE_DESCR,"   
            + "     jci.TRANSACT_CURRENCY_CD,"
            + "     jci.JOB_DESCRIPTION," 
            + "     jci.BANK_ACCT_DESC,"     
            + "     jci.DFI_ID_NUM,"     
            + "     jci.BANK_CODE,"    
            + "     jci.BANK_ACCT_CODE,"     
            + "     jci.BANKS_BANK_DESC,"
            + "     jci.BANKS_ADDRESS1,"
            + "     jci.BANKS_ADDRESS2,"
            + "     jci.BANKS_ADDRESS3,"
            + "     b.CITY as BANKS_CITY,"
            + "     jci.BANKS_STATE_CODE,"
            + "     b.POSTAL as BANKS_POSTAL,"
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
            + "           AND jci.INVOICE = ?) as BANK_LANG_DESC,"
            + " ba.BANK_ACCT_DESC,"
            + "     ba.ADDRESS1 as BANK_ACCT_ADDRESS1,"
            + "     ba.ADDRESS2 as BANK_ACCT_ADDRESS2,"
            + "     ba.ADDRESS3 as BANK_ACCT_ADDRESS3,"
            + "     ba.ADDRESS4 as BANK_ACCT_ADDRESS4,"
            + "     ba.CITY as BANK_ACCT_CITY,"
            + "     ba.STATE_CODE as BANK_ACCT_STATE_CODE,"
            + "     ba.COUNTRY_CODE as BANK_ACCT_COUNTRY_CODE,"
            + "     ba.POSTAL as BANK_ACCT_POSTAL,"
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
            + "     (select bul.COMPANY_DESC from PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci," 
            + "        PHOENIX.job_orders jo,PHOENIX.BUSINESS_UNITS_LANG bul"
            + "        where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "        AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "        AND jo.BU_NAME = bul.BU_NAME"
            + "        AND bul.LANGUAGE_CD = 'KOR'"
            + "        AND jc.JOB_NUMBER = ?"
            + "        AND jc.UID20 = ?"
            + "        AND jci.INVOICE = ?) AS COMPANY_LANG_DESC,"
            + " br.LOGO_NAME,"
            + " (select co.country_code from PHOENIX.country co, PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID and"
            + "     jci.BUSINESS_UNITS_COUNTRY_CODE = co.COUNTRY_CODE"
            + "     AND jc.JOB_NUMBER = ?"
            + "     AND jc.UID20 = ?"
            + "     AND jci.INVOICE = ?) as sellers_country,"
            + " jc.TRANSACT_CURRENCY_CD,"
            + " cur.DECIMAL_DIGITS,"
            + " jci.INV_AMT_PRE_TAX,"
            + " jci.INV_AMT_POST_TAX"
            + "   FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, PHOENIX.INVOICE_LINES il," 
            + "   PHOENIX.country_terms_urls ctu, PHOENIX.job_orders jo, PHOENIX.BANK_ACCOUNTS ba,"
            + "   PHOENIX.BANKS b, PHOENIX.COUNTRY c, PHOENIX.BRANCHES br,"
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
            + " AND jo.BU_NAME = bu.BU_NAME"
            + "     AND jci.TRANSACT_CURRENCY_CD = cur.CURRENCY_CD"
            + "     AND jc.JOB_NUMBER = ?"
            + "     AND jc.UID20 = ?"
            + "     AND jci.INVOICE = ?"
            + "   ORDER BY"
            + "      LEVEL0 ASC,"
            + "      LEVEL1 ASC,"
            + "      LINE_NUMBER ASC";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("Job_Number", params.get("Job_Number")),
            new Pair<String, Object>("uid20", params.get("uid20")),
            new Pair<String, Object>("Invoice", params.get("Invoice")),
            new Pair<String, Object>("Job_Number", params.get("Job_Number")),
            new Pair<String, Object>("uid20", params.get("uid20")),
            new Pair<String, Object>("Invoice", params.get("Invoice")),
            new Pair<String, Object>("Job_Number", params.get("Job_Number")),
            new Pair<String, Object>("uid20", params.get("uid20")),
            new Pair<String, Object>("Invoice", params.get("Invoice")),
            new Pair<String, Object>("Job_Number", params.get("Job_Number")),
            new Pair<String, Object>("uid20", params.get("uid20")),
            new Pair<String, Object>("Invoice", params.get("Invoice"))
        };
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, conn, sql, parameters, fieldNames, typeMap, this, params);
    }

    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("TEXT_22")){
            return DataSourceHelper.calculateInvoiceReportTitle(fieldName, params, source);
        }
        else if(fieldName.equals("TEXT_28")){
            return DataSourceHelper.calculateInvoiceBankDetail(fieldName, params, source);
        }
        else if(fieldName.equals("TEXT_24")){
            return DataSourceHelper.calculateInvoiceBankDetail2(fieldName, params, source);
        }
        else if(fieldName.equals("TEXT_26")){
            return DataSourceHelper.calculateInvoiceBankDetail3(fieldName, params, source);
        }
        return source.getCalculatedField(fieldName);
    }
    
    @Override
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params){
        // subTotal: sum of NET_PRICE
        // total: sum of NET_PRICE, but two for the same?
        String[] fields = {"NET_PRICE", "NET_PRICE"};
        double[] results = ReportUtil.summariseFields(fields, amds);
        
        amds.addCalculatedField("subtotal", new BigDecimal(String.valueOf(results[0])));
        amds.addCalculatedField("total", new BigDecimal(String.valueOf(results[1])));
    }
}
