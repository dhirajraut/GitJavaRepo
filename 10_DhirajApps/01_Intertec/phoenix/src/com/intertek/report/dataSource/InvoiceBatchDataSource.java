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
public class InvoiceBatchDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceBatchDataSource.class);
    
    private static final String[] fieldNames = {
        "JOB_NUMBER",
        "UID20",
        "TAX_VAT_FLAG",
        "INVOICE",
    };
    
    private static final String[] fieldTypes = {};

    public InvoiceBatchDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql = "SELECT"
            + "      jc.job_number, jc.uid20, jc.tax_vat_flag, jc.invoice"
            + " FROM"
            + "      job_contracts jc, job_contract_invoice jci"
            + " WHERE"
            + "      jci.job_contract_id = jc.job_contract_id"
            + " and  jci.job_contract_invoice_id in ( "
            + params.get("invoice_id_list")
            + " )";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {};
        
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        String sql = "SELECT"
            + "      jc.job_number, jc.uid20, jc.tax_vat_flag, jc.invoice"
            + " FROM"
            + "      job_contracts jc, job_contract_invoice jci"
            + " WHERE"
            + "      jci.job_contract_id = jc.job_contract_id"
            + " and  jci.job_contract_invoice_id in ( "
            + params.get("invoice_id_list")
            + " )";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {};
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
    }

}
