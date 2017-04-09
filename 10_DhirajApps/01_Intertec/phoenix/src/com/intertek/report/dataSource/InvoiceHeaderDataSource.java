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
import com.intertek.report.ReportDataSourceManager;
import com.intertek.report.ReportUtil;

/**
 * 
 * @author richard.qin
 */
public class InvoiceHeaderDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceHeaderDataSource.class);
    
    private static String[] fieldNames = {
        "UID20",
        "INVOICE",
        "CUST_CODE",
        "CREDIT_IND",
        "INVOICE_TYPE",
        "PYMNT_TERMS_CD",
        "PYMNT_TERMS_DESC",
        "BILLING_CONTACT_NAME",
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
        "REASON_CODE",
        "REASON_DESCR",
        "JOB_FINISH_DATE",
        "JOB_NUMBER",
        "NOMINATION_DT",
        "VESSEL_NAMES",
        "PRODUCT_NAMES",
        "JOB_DESCRIPTION",
        "SERVICE_LOCATION_CODE",
        "ADDRESS1",
        "ADDRESS2",
        "ADDRESS3",
        "ADDRESS4",
        "CITY",
        "COUNTY",
        "COUNTRY",
        "POSTAL",
        "STATE",
        "CUSTOMERS_NAME",
        "STATE_NAME",
        "COUNTRY_NAME",
        "INVOICE_DATE",
        "ACCOUNTING_DATE",
        "BRANCH_NAME",
        "LOGO_NAME",
        "sellers_country",
    };
    
    private static String[] fieldTypes = {
        "JOB_FINISH_DATE",  "Timestamp", 
        "NOMINATION_DT",    "Timestamp", 
        "INVOICE_DATE",     "Timestamp", 
        "ACCOUNTING_DATE",  "Timestamp"
    };
    
    public InvoiceHeaderDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql = "SELECT "
            + " jc.UID20, "
            + " jci.INVOICE, "
            + " jc.CUST_CODE, "
            + " jci.CREDIT_IND, "
            + " jci.INVOICE_TYPE, "
            + " jci.PYMNT_TERMS_CD, "
            + " jci.PYMNT_TERMS_DESC, "
            + " jci.BILLING_CONTACT_NAME, "
            + " jci.INVOICE_LABEL1, "
            + " jci.INVOICE_VALUE1, "
            + " jci.INVOICE_LABEL2, "
            + " jci.INVOICE_VALUE2, "
            + " jci.INVOICE_LABEL3, "
            + " jci.INVOICE_VALUE3, "
            + " jci.INVOICE_LABEL4, "
            + " jci.INVOICE_VALUE4, "
            + " jci.INVOICE_LABEL5, "
            + " jci.INVOICE_VALUE5, "
            + " jci.CUST_REF_NUM, "
            + " jci.REASON_CODE, "
            + " jci.REASON_DESCR, "
            + " jci.JOB_FINISH_DATE, "
            + " jc.JOB_NUMBER, "
            + " jci.NOMINATION_DT, "
            + " jci.VESSEL_NAMES, "
            + " jci.PRODUCT_NAMES, "
            + " jci.JOB_DESCRIPTION, "
            + " jci.SERVICE_LOCATION_CODE, "
            + " jci.CUST_ADDRESS1 ADDRESS1, "
            + " jci.CUST_ADDRESS2 ADDRESS2, "
            + " jci.CUST_ADDRESS3 ADDRESS3, "
            + " jci.CUST_ADDRESS4 ADDRESS4, "
            + " jci.CUST_CITY CITY, "
            + " jci.CUST_COUNTY COUNTY, "
            + " jci.CUST_COUNTRY COUNTRY, "
            + " jci.CUST_POSTAL POSTAL, "
            + " jci.CUST_STATE STATE, "
            + " jci.CUSTOMERS_NAME, "
            + " jci.CUST_STATE_NAME STATE_NAME, "
            + " jci.CUST_COUNTRY_NAME COUNTRY_NAME, "
            + " jci.INVOICE_DATE, "
            + " jci.ACCOUNTING_DATE, "
            + " b.BRANCH_NAME, "
            + " b.LOGO_NAME, "
            + " (select co.country_code from PHOENIX.country co, PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci "
            + " where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + " and jci.BUSINESS_UNITS_COUNTRY_CODE = co.COUNTRY_CODE"
            + " AND jc.JOB_NUMBER = :JOB_NUMBER_1"
            + " AND jc.UID20 = :UID20_1"
            + " AND jci.INVOICE = :INVOICE_1) as \"sellers_country\" "
            + "   FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, PHOENIX.JOB_ORDERS jo, PHOENIX.BRANCHES b"
            + "   WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "   AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "   AND jo.BRANCH_NAME = b.BRANCH_NAME"
            + "   AND jc.JOB_NUMBER = :JOB_NUMBER_2"
            + "   AND jc.UID20 = :UID20_2"
            + "   AND jci.INVOICE = :INVOICE_2";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER_1", params.get("Job_Number")),
            new Pair<String, Object>("UID20_1", params.get("uid20")),
            new Pair<String, Object>("INVOICE_1", params.get("Invoice")),
            new Pair<String, Object>("JOB_NUMBER_2", params.get("Job_Number")),
            new Pair<String, Object>("UID20_2", params.get("uid20")),
            new Pair<String, Object>("INVOICE_2", params.get("Invoice"))
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
            + " jc.UID20, "
            + " jci.INVOICE, "
            + " jc.CUST_CODE, "
            + " jci.CREDIT_IND, "
            + " jci.INVOICE_TYPE, "
            + " jci.PYMNT_TERMS_CD, "
            + " jci.PYMNT_TERMS_DESC, "
            + " jci.BILLING_CONTACT_NAME, "
            + " jci.INVOICE_LABEL1, "
            + " jci.INVOICE_VALUE1, "
            + " jci.INVOICE_LABEL2, "
            + " jci.INVOICE_VALUE2, "
            + " jci.INVOICE_LABEL3, "
            + " jci.INVOICE_VALUE3, "
            + " jci.INVOICE_LABEL4, "
            + " jci.INVOICE_VALUE4, "
            + " jci.INVOICE_LABEL5, "
            + " jci.INVOICE_VALUE5, "
            + " jci.CUST_REF_NUM, "
            + " jci.REASON_CODE, "
            + " jci.REASON_DESCR, "
            + " jci.JOB_FINISH_DATE, "
            + " jc.JOB_NUMBER, "
            + " jci.NOMINATION_DT, "
            + " jci.VESSEL_NAMES, "
            + " jci.PRODUCT_NAMES, "
            + " jci.JOB_DESCRIPTION, "
            + " jci.SERVICE_LOCATION_CODE, "
            + " jci.CUST_ADDRESS1 ADDRESS1, "
            + " jci.CUST_ADDRESS2 ADDRESS2, "
            + " jci.CUST_ADDRESS3 ADDRESS3, "
            + " jci.CUST_ADDRESS4 ADDRESS4, "
            + " jci.CUST_CITY CITY, "
            + " jci.CUST_COUNTY COUNTY, "
            + " jci.CUST_COUNTRY COUNTRY, "
            + " jci.CUST_POSTAL POSTAL, "
            + " jci.CUST_STATE STATE, "
            + " jci.CUSTOMERS_NAME, "
            + " jci.CUST_STATE_NAME STATE_NAME, "
            + " jci.CUST_COUNTRY_NAME COUNTRY_NAME, "
            + " jci.INVOICE_DATE, "
            + " jci.ACCOUNTING_DATE, "
            + " b.BRANCH_NAME, "
            + " b.LOGO_NAME, "
            + " (select co.country_code from PHOENIX.country co, PHOENIX.job_contracts jc, PHOENIX.job_contract_invoice jci "
            + " where jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + " and jci.BUSINESS_UNITS_COUNTRY_CODE = co.COUNTRY_CODE"
            + " AND jc.JOB_NUMBER = ?"
            + " AND jc.UID20 = ?"
            + " AND jci.INVOICE = ?) as \"sellers_country\" "
            + "   FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, PHOENIX.JOB_ORDERS jo, PHOENIX.BRANCHES b"
            + "   WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "   AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "   AND jo.BRANCH_NAME = b.BRANCH_NAME"
            + "   AND jc.JOB_NUMBER = ?"
            + "   AND jc.UID20 = ?"
            + "   AND jci.INVOICE = ?";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice")),
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice"))
        };
        
        JRDataSource ds = ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
        
        // give it a chance to load some additional data
        loadAdditionalData((ArrayMapDataSource)ds, params, con);
        return ds;
    }
    
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, String path) {
        JRDataSource ds = super.getDataSource(reportName, params, path);
        
        // give it a chance to load some additional data
        loadAdditionalData((ArrayMapDataSource)ds, params, null);
        return ds;
    }
    
    /**
     * The following calculation appeared in two different reports, so the logic is moved to
     * a common class
     */
    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("TEXT_23")){
            return DataSourceHelper.calculateInvoiceReportTitle(fieldName, params, source);
        }
        return source.getCalculatedField(fieldName);
    }
    
    public void loadAdditionalData(ArrayMapDataSource amds, Map<String, Object> params, Connection conn){
        ArrayMapDataSource ds = (ArrayMapDataSource)ReportDataSourceManager.getManager()
                .getReportDataSource("invoice_header_vatcalc", params, conn);
        
        // make VAT_subtotal available through this data source
        // this is an example about how to chain multiple data source together for one report.
        amds.addCalculatedField("VAT_subtotal", ds.getCalculatedField("VAT_subtotal"));
    }
}
