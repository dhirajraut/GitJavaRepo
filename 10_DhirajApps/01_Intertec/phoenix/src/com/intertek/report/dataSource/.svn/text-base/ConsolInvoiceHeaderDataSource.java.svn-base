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
public class ConsolInvoiceHeaderDataSource extends AbstractJasperDataSource {
    private static Logger log = Logger.getLogger(ConsolInvoiceHeaderDataSource.class);
    
    private static String[] fieldNames = {
        "CONSOL_INVOICE",
        "CUST_CODE",
        "CURRENCY_CD",
        "INVOICE_DT",
        "PYMNT_TERMS_CD",
        "PYMNT_TERMS_DESC",
        "NAME",
        "FIRST_NAME",
        "LAST_NAME",
        "ADDRESS1",
        "ADDRESS2",
        "ADDRESS3",
        "ADDRESS4",
        "CITY",
        "COUNTY",
        "COUNTRY",
        "POSTAL",
        "STATE",
        "STATE_NAME",
        "COUNTRY_NAME",
        "TAX_VAT_FLAG",
        "BRANCH_NAME",
        "LOGO_NAME"
    };
    
    static private String[] fieldTypes = {
        "INVOICE_DT",               "Timestamp",
    };
    
    public ConsolInvoiceHeaderDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String fieldList = ReportUtil.concatFieldNames(fieldNames);
            
        String sql = "select " + fieldList + " from PHOENIX.consol_header_vw"
                    + " where CONSOL_INVOICE = :ConsolInvoice"
                    + " AND CUST_CODE = :CustCode";

        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("ConsolInvoice", (String) params.get("ConsolInvoice")),
            new Pair<String, Object>("CustCode", (String) params.get("CustCode")),
        };
        
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection conn) {
        String fieldList = ReportUtil.concatFieldNames(fieldNames);
        String sql = "select " + fieldList + " from PHOENIX.consol_header_vw"
                    + " where CONSOL_INVOICE = ?"
                    + " AND CUST_CODE = ?";
        log.debug("sql: " + sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, String>("ConsolInvoice", (String) params.get("ConsolInvoice")),
            new Pair<String, String>("CustCode", (String) params.get("CustCode"))
        };
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, conn, sql, parameters, fieldNames, typeMap, this, params);
    }
    
    @Override
    public Object calculateFieldValue(String fieldName, Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("BILLING_CONTACT_NAME")){
            return source.getFieldValue("FIRST_NAME") + " " + source.getFieldValue("LAST_NAME");
        }
        return source.getCalculatedField(fieldName);
    }
}
