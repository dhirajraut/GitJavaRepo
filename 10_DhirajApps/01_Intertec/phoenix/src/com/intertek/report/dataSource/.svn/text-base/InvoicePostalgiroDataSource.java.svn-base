/**
 * 
 */
package com.intertek.report.dataSource;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.log4j.Logger;

import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.Pair;
import com.intertek.report.ReportUtil;

/**
 * 
 * @author richard.qin
 */
public class InvoicePostalgiroDataSource extends AbstractJasperDataSource {
    private static Logger log = Logger.getLogger(InvoicePostalgiroDataSource.class);
    private static String[] fieldNames = {
        "BUSINESS_UNIT_NAME", 
        "BANK_CODE", 
        "BANK_ACCONT_NUM", 
        "BANK_ID_NBR"
    };
    
    static private String[] fieldTypes = {};
    
    public InvoicePostalgiroDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {         
        String sql = "SELECT ba.BUSINESS_UNIT_NAME, ba.BANK_CODE, ba.BANK_ACCONT_NUM, b.BANK_ID_NBR"
                   + " FROM PHOENIX.BANK_ACCOUNTS ba, PHOENIX.BANKS b"
                   + " WHERE b.BANK_CODE = ba.BANK_CODE"
                   + " AND b.BANK_CODE = :BankCode";

        log.debug("sql: " + sql);

        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("BANK_CODE", (String) params.get("Bank_Code")),
        };
        
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection conn) {
        String sql = "SELECT ba.BUSINESS_UNIT_NAME, ba.BANK_CODE, ba.BANK_ACCONT_NUM, b.BANK_ID_NBR"
                    + " FROM PHOENIX.BANK_ACCOUNTS ba, PHOENIX.BANKS b"
                    + " WHERE b.BANK_CODE = ba.BANK_CODE"
                    + " AND b.BANK_CODE = ?";       
        log.debug("sql: " + sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, String>("BANK_CODE", (String) params.get("Bank_Code"))
        };
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, conn, sql, parameters, fieldNames, typeMap, this, params);
    }
}
