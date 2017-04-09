/**
 * 
 */
package com.intertek.report.dataSource;

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
public class ConsolSummaryDataSource extends AbstractJasperDataSource {
    private static Logger log = Logger.getLogger(ConsolSummaryDataSource.class);
    
    private static final String[] fieldNames = {
        "CONSL_INV_BU_NAME",
        "CONSOL_INVOICE",
        "CUST_CODE",
        "INVOICE_DT",
        "LOCATION_NUMBER",
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
        "BU_NAME",
        "JOB_NUMBER",
        "INVOICE",
        "INVOICE_DATE",
        "CUST_REF_NUM",
        "INVOICE_LABEL5",
        "INVOICE_VALUE5",
        "INVOICE_DESCR",
        "TRANSACT_CURRENCY_CD",
        "INV_AMT",
        "UID20",
        "TAX_VAT_FLAG",
        "BRANCH_NAME",
        "INV_BU_NAME",
        "LOGO_NAME",
        "COUNTRY_CODE",
        "TERMS_URL_DESCR",
        "TERMS_URL",
        "COMPANY LANG DESC",
        "BANK LANG DESC"
    };

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
    
    public ConsolSummaryDataSource(){
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
        log.debug("Get data via Hibernate for report " + reportName);
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
        log.debug("Get data via JDBC for report " + reportName);
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
    public Object calculateFieldValue(String fieldName, Map<String, Object> params, ArrayMapDataSource source) {
//( (($F{BUSINESS_UNITS_ADDRESS1} == null) || ($F{BUSINESS_UNITS_ADDRESS1}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_ADDRESS1}+", ")
//+ ( (($F{BUSINESS_UNITS_ADDRESS2} == null) || ($F{BUSINESS_UNITS_ADDRESS2}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_ADDRESS2}+", ") 
//+ ( (($F{BUSINESS_UNITS_ADDRESS3} == null) || ($F{BUSINESS_UNITS_ADDRESS3}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_ADDRESS3}+", ")
//+ ( (($F{BUSINESS_UNITS_CITY} == null) || ($F{BUSINESS_UNITS_CITY}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_CITY}+", ") 
//+ ( (($F{BUSINESS_UNITS_STATE_CODE} == null) || ($F{BUSINESS_UNITS_STATE_CODE}.trim()).isEmpty() || $F{CONSL_INV_BU_NAME}.equalsIgnoreCase( "KOR01" )) ? "" : $F{BUSINESS_UNITS_STATE_CODE}+", ")
//+ ( (($F{BUSINESS_UNITS_POSTAL} == null) || ($F{BUSINESS_UNITS_POSTAL}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_POSTAL})
//+ ( (($F{NAME} == null) || ($F{NAME}.trim()).isEmpty()) ? "" : 
//    ( $F{NAME}.equals( $F{BUSINESS_UNITS_CITY} )? "" : $F{NAME}))+ "."
        
        if(fieldName.equals("BUSINESS_ADDRESS")){
            String buAddr1 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS1");
            String buAddr2 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS2");
            String buAddr3 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS3");
            String buCity = (String)source.getFieldValue("BUSINESS_UNITS_CITY");
            String buState = (String)source.getFieldValue("BUSINESS_UNITS_STATE_CODE");
            String conslInvBuName = (String)source.getFieldValue("CONSL_INV_BU_NAME");
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
            if(buState != null && buState.trim().length() > 0 && !"KOR01".equalsIgnoreCase(conslInvBuName)){
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
        
        return source.getCalculatedField(fieldName);
    }
}