/**
 * 
 */
package com.intertek.report.dataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.log4j.Logger;

import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.Pair;
import com.intertek.report.ReportUtil;

/**
 * 
 * @author richard.qin
 */
public class ConsolInvoiceDataSource extends AbstractJasperDataSource{
    private static Logger log = Logger.getLogger(ConsolSummaryDataSource.class);
    
    private static final String[] fieldNames = {
        "CONSL_INV_BU_NAME",
        "CONSOL_INVOICE",
        "CUST_CODE",
        "INVOICE_DT",
        "LOCATION_NUMBER",
        "CONSL_INV_AMT_POST_TAX",
        "CUST_NAME",
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
        "ROUNDING_DIGITS",
        "NAME",
        "BUSINESS_UNITS_COUNTRY2_CODE",
        "BANK_ACCT_DESC",
        "DFI_ID_NUM",
        "BANK_CODE",
        "BANK_ACCT_CODE",
        "BANK_ACCONT_NUM",
        "IBAN_CHECK_DIGIT",
        "CHECK_DIGIT",
        "DEPOSIT_TYPE",
        "BANK_BRANCH_ID",
        "BANK_ACCT_ADDRESS1",
        "BANK_ACCT_ADDRESS2",
        "BANK_ACCT_ADDRESS3",
        "BANK_ACCT_ADDRESS4",
        "BANK_ACCT_CITY",
        "BANK_ACCT_STATE_CODE",
        "BANK_ACCT_POSTAL",
        "BANK_ACCT_COUNTRY_CODE",
        "BANKS_BANK_DESC",
        "BANKS_ADDRESS1",
        "BANKS_ADDRESS2",
        "BANKS_ADDRESS3",
        "BANKS_CITY",
        "BANKS_STATE_CODE",
        "BANKS_POSTAL",
        "BANKS_COUNTRY_CODE",
        "BANKS_BANK_ID_NBR",
// BU_NAME is not used in the report; instead, CONSL_INV_BU_NAME is used extensively
// This file includes the invoice_header_sub1 report, which reference CONSL_INV_BU_NAME.
// invoice_header_sub1 is also included by several other reports. Unfortunately, those reports
// reference the same field using name BU_NAME. To solved this inconsistency, BU_NAME
// is removed from this report. Whenever BU_NAME is required (only in InvoiceHeaderSub1DataSource)
// the value of CONSL_INV_BU_NAME will be provided.        
//        "BU_NAME", 
        "JOB_NUMBER",
        "INV_AMT",
        "UID20",
        "INVOICE",
        "INVOICE_DATE",
        "CUST_REF_NUM",
        "INVOICE_LABEL5",
        "INVOICE_VALUE5",
        "INVOICE_DESCR",
        "TRANSACT_CURRENCY_CD",
        "TAX_VAT_FLAG",
        "INV_AMT_PRE_TAX",
        "INV_AMT_POST_TAX",
        "BRANCH_NAME",
        "INV_BU_NAME",
        "LOGO_NAME",
        "COUNTRY_CODE",
        "TERMS_URL_DESCR",
        "TERMS_URL",
        "COMPANY LANG DESC",
        "BANK LANG DESC"
    };
    
    // fields not listed here have the default type of java.lang.String
    static private String[] fieldTypes = {
        "INVOICE_DT",               "Timestamp",
        "LOCATION_NUMBER",          "BigDecimal",
        "CONSL_INV_AMT_POST_TAX",   "BigDecimal",
        "ROUNDING_DIGITS",          "BigDecimal",
        "INV_AMT",                  "BigDecimal",
        "INVOICE_DATE",             "Timestamp",
        "INV_AMT_PRE_TAX",          "BigDecimal",
        "INV_AMT_POST_TAX",         "BigDecimal"
    };
    
    public ConsolInvoiceDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String fieldList = ReportUtil.concatFieldNames(fieldNames, "inv_bu_name", "job_number", "invoice");
            
        String sql = "SELECT " + fieldList + " FROM PHOENIX.CONSOL_INV_VW "
                    + " WHERE consl_inv_bu_name = :BuName"
                    + " and cust_code = :CustCode"
                    + " and consol_invoice = :ConsolInvoice"
                    + " order by inv_bu_name, job_number, invoice";
        log.debug("sql: " + sql);

        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("BuName", params.get("BuName")),
            new Pair<String, Object>("CustCode", params.get("CustCode")),
            new Pair<String, Object>("ConsolInvoice", params.get("ConsolInvoice"))
        };
        
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection conn) {
        String sql = "SELECT * FROM PHOENIX.CONSOL_INV_VW "
                    + " WHERE consl_inv_bu_name = ?"
                    + " and cust_code = ?"
                    + " and consol_invoice = ?"
                    + " order by inv_bu_name, job_number, invoice";
        log.debug("sql: " + sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("BuName", params.get("BuName")),
            new Pair<String, Object>("CustCode", params.get("CustCode")),
            new Pair<String, Object>("ConsolInvoice", params.get("ConsolInvoice"))
        };
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, conn, sql, parameters, fieldNames, typeMap, this, params);
    }
    
    @Override
    public Object calculateFieldValue(String fieldName, Map<String, Object> param, ArrayMapDataSource source) {
        // use the value of CONSL_INV_BU_NAME for BU_NAME, this is a workaround to a problem
        // in invoice_header_sub1, due to inconsistent use of field names.
        if(fieldName.equals("BU_NAME")){
            return source.getFieldValue("CONSL_INV_BU_NAME");
        }
//( $F{BUSINESS_UNITS_COUNTRY_CODE}.equals( "BEL" ) ? 
//    $F{BUSINESS_UNITS_COUNTRY2_CODE} + " " + $F{IBAN_CHECK_DIGIT} + " " + $F{BANKS_BANK_ID_NBR} + " " + $F{BANK_ACCONT_NUM} + " " + $F{CHECK_DIGIT}: 
//    ( $F{BUSINESS_UNITS_COUNTRY_CODE}.equals( "NLD" ) ? 
//    $F{BUSINESS_UNITS_COUNTRY2_CODE} + " " + $F{IBAN_CHECK_DIGIT} + " " + $F{DFI_ID_NUM}.substring( 0, 4 ) + " " +$F{BANK_ACCONT_NUM} + " " + $F{CHECK_DIGIT}: 
//    "" ) )   
        if(fieldName.equals("BU_COUNTRY_LINE")){
            String buLine = null;
            String buCountryCode = (String)source.getFieldValue("BUSINESS_UNITS_COUNTRY_CODE");
            if(buCountryCode.equals("BEL")){
                buLine = source.getFieldValue("BUSINESS_UNITS_COUNTRY2_CODE") + " " 
                       + source.getFieldValue("IBAN_CHECK_DIGIT") + " " 
                       + source.getFieldValue("BANKS_BANK_ID_NBR") + " " 
                       + source.getFieldValue("BANK_ACCONT_NUM") + " " 
                       + source.getFieldValue("CHECK_DIGIT");
            }
            else if(buCountryCode.equals("NLD")){
                buLine = source.getFieldValue("BUSINESS_UNITS_COUNTRY2_CODE") + " " 
                       + source.getFieldValue("IBAN_CHECK_DIGIT") + " " 
                       + ((String)source.getFieldValue("DFI_ID_NUM")).substring( 0, 4 ) + " " 
                       + source.getFieldValue("BANK_ACCONT_NUM") + " " 
                       + source.getFieldValue("CHECK_DIGIT");
            }
            return buLine;
        }

//( $F{BUSINESS_UNITS_COUNTRY_CODE}.equals( "BEL" ) ? 
//    $F{BANKS_BANK_ID_NBR} + "-" + $F{BANK_ACCONT_NUM} + "-" + $F{CHECK_DIGIT} : 
//    ( $F{BUSINESS_UNITS_COUNTRY_CODE}.equals( "MOZ" ) ? 
//    "" : $F{BANK_ACCONT_NUM} ) )    
        if(fieldName.equals("BANK_LINE")){
            String bankLine = null;
            String buCountryCode = (String)source.getFieldValue("BUSINESS_UNITS_COUNTRY_CODE");
            if(buCountryCode != null && buCountryCode.equals("BEL")){
                bankLine = source.getFieldValue("BANKS_BANK_ID_NBR") + "-" 
                         + source.getFieldValue("BANK_ACCONT_NUM") + "-" 
                         + source.getFieldValue("CHECK_DIGIT");
            }
            else if(buCountryCode != null && buCountryCode.equals("MOZ")){
                bankLine = "";
            }
            else{
                bankLine = (String)source.getFieldValue("BANK_ACCONT_NUM");
            }
            return bankLine;
        }
        
//($F{BUSINESS_UNITS_COUNTRY_CODE}.equals("AUS") || $F{BUSINESS_UNITS_COUNTRY_CODE}.equals("NZL")? 
//    $F{BANKS_BANK_ID_NBR} + "-" + $F{BANK_BRANCH_ID} : 
//    ($F{BUSINESS_UNITS_COUNTRY_CODE}.equals("MOZ") ? 
//    $F{BANKS_BANK_ID_NBR}+$F{BANK_ACCONT_NUM}: 
//    ( $F{CONSL_INV_BU_NAME}.equals( "KOR01" ) && $F{BANK_ACCT_DESC}.contains( "USD" ) ? 
//    $F{BUSINESS_UNITS_DESCRIPTION}
//    : $F{BANKS_BANK_ID_NBR})))    
        if(fieldName.equals("BANK_LINE_2")){
            String bankLine2 = null;
            String buCountryCode = (String)source.getFieldValue("BUSINESS_UNITS_COUNTRY_CODE");
            if(buCountryCode.equals("AUS") || buCountryCode.equals("NZL")){
                bankLine2 = source.getFieldValue("BANKS_BANK_ID_NBR") + "-" + source.getFieldValue("BANK_BRANCH_ID"); 
            }
            else if(buCountryCode.equals("MOZ")){
                bankLine2 = (String)source.getFieldValue("BANKS_BANK_ID_NBR") + source.getFieldValue("BANK_ACCONT_NUM"); 
            }
            else if(((String)source.getFieldValue("CONSL_INV_BU_NAME")).equals("KOR01") && 
                    ((String)source.getFieldValue("BANK_ACCT_DESC")).contains("USD")){
                bankLine2 = (String)source.getFieldValue("BUSINESS_UNITS_DESCRIPTION"); 
            }
            else{
                bankLine2 = (String)source.getFieldValue("BANKS_BANK_ID_NBR");
            }
            return bankLine2;
        }
    
        return source.getCalculatedField(fieldName);
    }

    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params){
        // subTotal: sum of INV_AMT
        // total: sum of INV_AMT, but two for the same?
        String[] fields = {"INV_AMT", "INV_AMT"};
        double[] results = ReportUtil.summariseFields(fields, amds);
        
        amds.addCalculatedField("subtotal", new BigDecimal(String.valueOf(results[0])));
        amds.addCalculatedField("total", new BigDecimal(String.valueOf(results[1])));
    }
}
