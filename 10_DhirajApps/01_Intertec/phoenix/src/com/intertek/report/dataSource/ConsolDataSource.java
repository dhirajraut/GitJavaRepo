/**
 * 
 */
package com.intertek.report.dataSource;

import java.sql.Connection;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;

import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.Pair;
import com.intertek.report.ReportUtil;

/**
 * 
 * @author richard.qin
 */
public class ConsolDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(ConsolDataSource.class);
    
    public static final String[] fieldNames = {
        "CONSL_INV_BU_NAME",
        "CONSOL_INVOICE",
        "CUST_CODE",
        "INVOICE_DT",
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
        "INVOICE_DATE",
        "UID20",
        "TAX_VAT_FLAG",
        "BRANCH_NAME",
        "INV_BU_NAME",
        "LOGO_NAME",
    };
    
    static private String[] fieldTypes = {
        "INVOICE_DT",   "Timestamp",
        "INVOICE_DATE", "Timestamp",
        "INVOICE_DATE", "Timestamp",
    };
    
    public ConsolDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql = "SELECT * FROM PHOENIX.CONSOL_INV_VW "
                   + " WHERE consl_inv_bu_name = :BuName "
                   + " and  cust_code = :CustCode"
                   + " and  consol_invoice = :ConsolInvoice"
                   + " ORDER BY inv_bu_name, branch_name";
        
        log.debug("sql: " + sql);

        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("BuName", params.get("BuName")),
            new Pair<String, Object>("CustCode", params.get("CustCode")),
            new Pair<String, Object>("ConsolInvoice", params.get("ConsolInvoice"))
        };
        
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection conn) {
//        <queryString><![CDATA[SELECT
//                              *
//                         FROM
//                              PHOENIX.CONSOL_INV_VW
//                         WHERE
//                             consl_inv_bu_name = $P{BuName}
//                         and  cust_code = $P{CustCode}
//                         and  consol_invoice = $P{ConsolInvoice}
//                         order by 
//                         inv_bu_name,
//                         branch_name]]></queryString>
        String sql = "SELECT * FROM PHOENIX.CONSOL_INV_VW "
                   + " WHERE consl_inv_bu_name = ? "
                   + " and  cust_code = ?"
                   + " and  consol_invoice = ?"
                   + " ORDER BY inv_bu_name, branch_name";
        
        log.debug("sql: " + sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("BuName", params.get("BuName")),
            new Pair<String, Object>("CustCode", params.get("CustCode")),
            new Pair<String, Object>("ConsolInvoice", params.get("ConsolInvoice"))
        };
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, conn, sql, parameters, fieldNames, typeMap, this, params);
    }

}
