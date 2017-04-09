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
public class InvoiceMasterNontaxfooterDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceMasterNontaxfooterDataSource.class);
    
    private static final String[] fieldNames = {
        "JOB_NUMBER",
        "UID20",
        "TAX_VAT_FLAG",
        "INVOICE",
        "BU_NAME",
    };
    
    private static final String[] fieldTypes = {
    };

    public InvoiceMasterNontaxfooterDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql = "SELECT"
                + "         jc.job_number, jc.uid20, jci.tax_vat_flag, jci.invoice, jo.bu_name"
                + "    FROM"
                + "         PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci,"
                + "         PHOENIX.job_orders jo"
                + "    WHERE"
                + "         jci.job_contract_id = jc.job_contract_id"
                + "    and  jc.job_number = jo.job_number"
                + "    and  jci.job_contract_invoice_id = :INVOICE";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("INVOICE_ID", params.get("Invoice_Id"))
        };
    
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        String sql = "SELECT"
                + "         jc.job_number, jc.uid20, jci.tax_vat_flag, jci.invoice, jo.bu_name"
                + "    FROM"
                + "         PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci,"
                + "         PHOENIX.job_orders jo"
                + "    WHERE"
                + "         jci.job_contract_id = jc.job_contract_id"
                + "    and  jc.job_number = jo.job_number"
                + "    and  jci.job_contract_invoice_id = ?";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("INVOICE_ID", params.get("Invoice_Id"))
        };
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
    }

}
