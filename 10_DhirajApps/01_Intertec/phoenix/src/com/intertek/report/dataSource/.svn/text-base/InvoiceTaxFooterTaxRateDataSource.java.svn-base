/**
 * 
 */
package com.intertek.report.dataSource;

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
public class InvoiceTaxFooterTaxRateDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceTaxFooterTaxRateDataSource.class);
    // this is the original datasource before transformation
    private ArrayMapDataSource original;
    
    private static final String[] fieldNames = {
        "JOB_NUMBER",
        "INVOICE",
        "UID20",
        "INVOICE_TYPE",
        "TAX_CODE",
        "TAX_PCT",
        "TAX_AMT",
        "VAT_CODE",
        "VAT_PCT",
        "VAT_AMT",
        "NET_PRICE",
        "VAT_LABEL",
        "SALES_TAX_LABEL",
        "VAT_REG_LABEL",
        "TRANSACT_CURRENCY_CD",
        "BU_NAME",
        "DECIMAL_DIGITS",
        //START: Adding new two fields to store VAT and Tax total        
        "AMT_TAX",
        "AMT_VAT"
        //END: Adding new two fields to store VAT and Tax total        
    };
    
    private static final String[] fieldTypes = {
        "TAX_PCT", "BigDecimal",
        "TAX_AMT", "BigDecimal",
        "VAT_PCT", "BigDecimal",
        "VAT_AMT", "BigDecimal",
        "NET_PRICE", "BigDecimal",
        "DECIMAL_DIGITS", "BigDecimal",
        //START: Adding new two fields to store VAT and Tax total
        "AMT_TAX",	"BigDecimal",
        "AMT_VAT",	"BigDecimal"
        //END: Adding new two fields to store VAT and Tax total
    };

    public InvoiceTaxFooterTaxRateDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql = "SELECT  "
            + "     jc.JOB_NUMBER,"
            + "     jci.INVOICE,"
            + "     jc.UID20,"
            + " jci.INVOICE_TYPE,"
            + "     il.TAX_CODE,"
            + "     il.TAX_PCT,"
            + "     il.TAX_AMT,"
            + "     il.VAT_CODE,"
            + "     il.VAT_PCT,"
            + "     il.VAT_AMT,"
            + "     il.NET_PRICE,   "
            + "     jci.VAT_LABEL,"
            + "     jci.SALES_TAX_LABEL,"
            + "     jci.VAT_REG_LABEL, "
            + "     jci.TRANSACT_CURRENCY_CD,"
            //START: Adding new two fields to store VAT and Tax total            
            + "     jci.AMT_TAX,"
            + "     jci.AMT_VAT,"            
            //END: Adding new two fields to store VAT and Tax total            
            + " 	jo.BU_NAME,"
            + "     cur.DECIMAL_DIGITS"
            + "   FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, "
            + "    PHOENIX.INVOICE_LINES il, PHOENIX.JOB_ORDERS jo,"
            + "        PHOENIX.BUSINESS_UNITS bu, PHOENIX.CURRENCY cur"
            + "   WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "     AND jci.JOB_CONTRACT_INVOICE_ID = il.JOB_CONTRACT_INVOICE_ID"
            + " AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "     AND jo.BU_NAME = bu.BU_NAME"
            + " AND jc.JOB_NUMBER = :JOB_NUMBER"
            + "     AND jc.UID20 = :UID20"
            + "     AND jci.INVOICE = :INVOICE"
            + " AND jci.TRANSACT_CURRENCY_CD = cur.CURRENCY_CD"
            + " ORDER BY il.VAT_PCT ASC";
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
        String sql = "SELECT  "
            + "     jc.JOB_NUMBER,"
            + "     jci.INVOICE,"
            + "     jc.UID20,"
            + " jci.INVOICE_TYPE,"
            + "     il.TAX_CODE,"
            + "     il.TAX_PCT,"
            + "     il.TAX_AMT,"
            + "     il.VAT_CODE,"
            + "     il.VAT_PCT,"
            + "     il.VAT_AMT,"
            + "     il.NET_PRICE,   "
            + "     jci.VAT_LABEL,"
            + "     jci.SALES_TAX_LABEL,"
            + "     jci.VAT_REG_LABEL, "
            + "     jci.TRANSACT_CURRENCY_CD,"
            //START: Adding new two fields to store VAT and Tax total            
            + "     jci.AMT_TAX,"
            + "     jci.AMT_VAT,"
            //END: Adding new two fields to store VAT and Tax total            
            + " 	jo.BU_NAME,"
            + "     cur.DECIMAL_DIGITS"
            + "   FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, "
            + "    PHOENIX.INVOICE_LINES il, PHOENIX.JOB_ORDERS jo,"
            + "        PHOENIX.BUSINESS_UNITS bu, PHOENIX.CURRENCY cur"
            + "   WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "     AND jci.JOB_CONTRACT_INVOICE_ID = il.JOB_CONTRACT_INVOICE_ID"
            + " AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "     AND jo.BU_NAME = bu.BU_NAME"
            + " AND jc.JOB_NUMBER = ?"
            + "     AND jc.UID20 = ?"
            + "     AND jci.INVOICE = ?"
            + " AND jci.TRANSACT_CURRENCY_CD = cur.CURRENCY_CD"
            + " ORDER BY il.VAT_PCT ASC";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice"))
        };
        
        original = (ArrayMapDataSource)ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
        
        String[] sourceFields = {"NET_PRICE", "VAT_AMT", "TAX_AMT"};
        //START: Adding new two fields to store VAT and Tax total     
        //String[] destFields = {"VAT_PCT", "subtotal", "VAT_subtotal", "sales_tax_subtotal"};
        String[] destFields = {"VAT_PCT", "subtotal", "AMT_VAT", "AMT_TAX"};
        //END: Adding new two fields to store VAT and Tax total     
        return ReportUtil.groupJasperDataSourceHelper("VAT_PCT", sourceFields, destFields, original, this, params);
    }

    @Override
    public Object calculateFieldValue(String fieldName, Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("DECIMAL_DIGITS")){
            return this.original.getFieldValue(fieldName);
        }
        return source.getCalculatedField(fieldName);
    }
}
