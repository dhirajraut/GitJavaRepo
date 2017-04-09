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
public class InvoiceHeaderValueSubDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceHeaderValueSubDataSource.class);
    
    private static final String[] fieldNames = {
        "UID20",
        "INVOICE",
        "JOB_NUMBER",
        "CUST_CODE",
        "INVOICE_LABEL1",
        "INVOICE_VALUE1",
        "INVOICE_LABEL2",
        "INVOICE_VALUE2",
        "INVOICE_LABEL3",
        "INVOICE_VALUE3",
        "INVOICE_LABEL4",
        "INVOICE_VALUE4",
        "INVOICE_LABEL5",
        "INVOICE_VALUE5",
        "CUST_REF_NUM",
    };
    
    private static final String[] fieldTypes = {};

    public InvoiceHeaderValueSubDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql = "SELECT "
            + "     jc.UID20,"
            + "     jci.INVOICE,"
            + " jc.JOB_NUMBER,"
            + "     jc.CUST_CODE,"
            + "     jci.INVOICE_LABEL1,"
            + "     jci.INVOICE_VALUE1,"
            + "     jci.INVOICE_LABEL2,"
            + "     jci.INVOICE_VALUE2,"
            + "     jci.INVOICE_LABEL3,"
            + "     jci.INVOICE_VALUE3,"
            + "     jci.INVOICE_LABEL4,"
            + "     jci.INVOICE_VALUE4,"
            + "     jci.INVOICE_LABEL5,"
            + "     jci.INVOICE_VALUE5,"
            + "     jci.CUST_REF_NUM"
            + "   FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci"
            + "   WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "   AND jc.JOB_NUMBER = :JOB_NUMBER"
            + "   AND jc.UID20 = :UID20"
            + "   AND jci.INVOICE = :INVOICE";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice"))
        };
        
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        String sql = "SELECT "
            + "     jc.UID20,"
            + "     jci.INVOICE,"
            + " jc.JOB_NUMBER,"
            + "     jc.CUST_CODE,"
            + "     jci.INVOICE_LABEL1,"
            + "     jci.INVOICE_VALUE1,"
            + "     jci.INVOICE_LABEL2,"
            + "     jci.INVOICE_VALUE2,"
            + "     jci.INVOICE_LABEL3,"
            + "     jci.INVOICE_VALUE3,"
            + "     jci.INVOICE_LABEL4,"
            + "     jci.INVOICE_VALUE4,"
            + "     jci.INVOICE_LABEL5,"
            + "     jci.INVOICE_VALUE5,"
            + "     jci.CUST_REF_NUM"
            + "   FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci"
            + "   WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "   AND jc.JOB_NUMBER = ?"
            + "   AND jc.UID20 = ?"
            + "   AND jci.INVOICE = ?";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice"))
        };
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
     }
}
