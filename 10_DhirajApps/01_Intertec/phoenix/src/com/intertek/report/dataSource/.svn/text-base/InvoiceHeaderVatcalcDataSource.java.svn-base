/**
 * 
 */
package com.intertek.report.dataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;

import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.FieldType;
import com.intertek.report.Pair;
import com.intertek.report.ReportUtil;

/**
 * 
 * @author richard.qin
 */
public class InvoiceHeaderVatcalcDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceHeaderVatcalcDataSource.class);
    
    private static String[] fieldNames = {
        "JOB_NUMBER",
        "INVOICE",
        "UID20",
        "VAT_AMT",
        //START: Adding new two fields to store VAT and Tax total        
        "AMT_VAT"
        //END: Adding new two fields to store VAT and Tax total
    };
    
    private static String[] fieldTypes = {
        "VAT_AMT",  "BigDecimal",
        //START: Adding new two fields to store VAT and Tax total        
        "AMT_VAT", "BigDecimal"
        //END: Adding new two fields to store VAT and Tax total
    };
    
    public InvoiceHeaderVatcalcDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql = " SELECT "
            + " jc.JOB_NUMBER,"
            + " jci.INVOICE,"
            + " jc.UID20,"
            + " il.VAT_AMT, "
            //START: Adding new two fields to store VAT and Tax total            
            + " jci.AMT_VAT "
            //START: Adding new two fields to store VAT and Tax total
            + " FROM JOB_CONTRACTS jc, JOB_CONTRACT_INVOICE jci, INVOICE_LINES il "
            + " WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID "
            + " AND jci.JOB_CONTRACT_INVOICE_ID = il.JOB_CONTRACT_INVOICE_ID "
            + " AND jc.JOB_NUMBER = :JOB_NUMBER "
            + " AND jc.UID20 = :UID20 "
            + " AND jci.INVOICE = :INVOICE ";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice")),
        };
        
        Map<String, FieldType> typeMap2 = new HashMap<String, FieldType>();
        typeMap2.put("VAT_AMT", FieldType.BigDecimal);
        
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        String sql = " SELECT "
            + " jc.JOB_NUMBER,"
            + " jci.INVOICE,"
            //START: Adding new two fields to store VAT and Tax total            
            + " jci.AMT_VAT, "
            //START: Adding new two fields to store VAT and Tax total            
            + " jc.UID20,"
            + " il.VAT_AMT "
            + " FROM JOB_CONTRACTS jc, JOB_CONTRACT_INVOICE jci, INVOICE_LINES il "
            + " WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID "
            + " AND jci.JOB_CONTRACT_INVOICE_ID = il.JOB_CONTRACT_INVOICE_ID "
            + " AND jc.JOB_NUMBER = ? "
            + " AND jc.UID20 = ? "
            + " AND jci.INVOICE = ?";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice")),
        };
        
        Map<String, FieldType> typeMap2 = new HashMap<String, FieldType>();
        typeMap2.put("VAT_AMT", FieldType.BigDecimal);
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
    }
    
    @Override
    public Object calculateFieldValue(String fieldName, Map<String, Object> params, ArrayMapDataSource source) {
        return source.getCalculatedField(fieldName);
    }
    
    @Override
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params){
        String[] fields = {"VAT_AMT"};
        double[] results = ReportUtil.summariseFields(fields, amds);
        
        amds.addCalculatedField("VAT_subtotal", new BigDecimal(String.valueOf(results[0])));
    }
}
