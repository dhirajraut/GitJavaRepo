/**
 * 
 */
package com.intertek.report.dataSource;

import java.math.BigDecimal;
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
public class InvoicePreviewDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoicePreviewDataSource.class);
    private static final String[] fieldNames = {
        "UID20",
        "INVOICE",
        "CUST_CODE",
        "PYMNT_TERMS_CD",
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
        "TRANSACT_CURRENCY_CD",
        "INVOICE_DESCR",
        "CREDIT_IND",
        "JOB_FINISH_DATE",
        "JOB_NUMBER",
        "NOMINATION_DT",
        "VESSEL_NAMES",
        "PRODUCT_NAMES",
        "JOB_DESCRIPTION",
        "SERVICE_LOCATION_CODE",
        "BU_NAME",
        "LINE_DESCRIPTION",
        "SPLIT_PCT",
        "DISCOUNT_PCT",
        "CURRENCY_CD",
        "NET_PRICE",
        "UNIT_PRICE",
        "ACCOUNT",
        "PRODUCT_GROUP",
        "DEPTID",
        "PRIMARY_BRANCH_CD",
        "LEVEL0",
        "VESSEL_SORT_NUM",
        "LEVEL1",
        "LOT_SORT_NUM",
        "LEVEL2",
        "CHARGE_SORT_NUM",
        "BRANCH_NAME",
        "ALLOC_PCT",
        "PREBILL_SPLIT_ID",
        "ADDRESS1",
        "ADDRESS2",
        "ADDRESS3",
        "CITY",
        "COUNTY",
        "COUNTRY",
        "POSTAL",
        "STATE",
        "ADDRESS4",
        "CUSTOMERS_NAME",
        "LINE_NUMBER",
        "NAME",
        "BUSINESS_UNITS_COUNTRY_CODE",
        "ROUNDING_DIGITS",
        "COMMENTS"
    };

    private static final String[] fieldTypes = {
        "JOB_FINISH_DATE", "Timestamp",
        "NOMINATION_DT", "Timestamp",
        "SPLIT_PCT", "BigDecimal",
        "DISCOUNT_PCT", "BigDecimal",
        "NET_PRICE", "BigDecimal",
        "UNIT_PRICE", "BigDecimal",
        "VESSEL_SORT_NUM", "BigDecimal",
        "LOT_SORT_NUM", "BigDecimal",
        "CHARGE_SORT_NUM", "BigDecimal",
        "ALLOC_PCT", "BigDecimal",
        "PREBILL_SPLIT_ID", "BigDecimal",
        "LINE_NUMBER", "BigDecimal",
        "ROUNDING_DIGITS", "BigDecimal",
    };

    public InvoicePreviewDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String sql = "SELECT *"
            + " FROM"
            + "      PHOENIX.INVOICE_PREVIEW_VW"
            + " WHERE"
            + "      JOB_NUMBER = :JOB_NUMBER"
            + "      and UID20 = :UID20"
            + " ORDER BY"
            + "      VESSEL_SORT_NUM ASC,"
            + "      LOT_SORT_NUM ASC,"
            + "      CHARGE_SORT_NUM ASC,"
            + "      PREBILL_SPLIT_ID ASC";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
        };
        
        return ReportUtil.hibernateJasperDataSourceHelper(
                        reportName, sql, parameters, fieldNames, typeMap, this, params);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        String sql = "SELECT *"
            + " FROM"
            + "      PHOENIX.INVOICE_PREVIEW_VW"
            + " WHERE"
            + "      JOB_NUMBER = ?"
            + "      and UID20 = ?"
            + " ORDER BY"
            + "      VESSEL_SORT_NUM ASC,"
            + "      LOT_SORT_NUM ASC,"
            + "      CHARGE_SORT_NUM ASC,"
            + "      PREBILL_SPLIT_ID ASC";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
        };
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
    }

    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("TEXT_10")){
//  ( (($F{ADDRESS1} == null) || ($F{ADDRESS1}.trim()).isEmpty()) ? "" : $F{ADDRESS1}+", ")
//+ ( (($F{ADDRESS2} == null) || ($F{ADDRESS2}.trim()).isEmpty()) ? "" : $F{ADDRESS2}+", ") 
//+ ( (($F{ADDRESS3} == null) || ($F{ADDRESS3}.trim()).isEmpty()) ? "" : $F{ADDRESS3}+", ")
//+ ( (($F{CITY} == null) || ($F{CITY}.trim()).isEmpty()) ? "" : $F{CITY}+", ") 
//+ ( (($F{STATE} == null) || ($F{STATE}.trim()).isEmpty() || $F{BU_NAME}.equalsIgnoreCase( "KOR01" )) ? "" : $F{STATE}+", ")
//+ ( (($F{POSTAL} == null) || ($F{POSTAL}.trim()).isEmpty()) ? "" : $F{POSTAL}+ " ")
//+ ( (($F{NAME} == null) || ($F{NAME}.trim()).isEmpty()) ? "" : 
//    ( $F{NAME}.equals( $F{CITY} )? "" : $F{NAME}))  

            String buAddr1 = (String) source.getFieldValue("ADDRESS1");
            String buAddr2 = (String) source.getFieldValue("ADDRESS2");
            String buAddr3 = (String) source.getFieldValue("ADDRESS3");
            String buCity = (String) source.getFieldValue("CITY");
            String buState = (String) source.getFieldValue("STATE");
            String conslInvBuName = (String) source.getFieldValue("BU_NAME");
            String buPostal = (String) source.getFieldValue("POSTAL");
            String name = (String) source.getFieldValue("NAME");

            StringBuilder sb = new StringBuilder();
            if (buAddr1 != null && buAddr1.trim().length() > 0) {
                sb.append(buAddr1.trim()).append(", ");
            }
            if (buAddr2 != null && buAddr2.trim().length() > 0) {
                sb.append(buAddr2.trim()).append(", ");
            }
            if (buAddr3 != null && buAddr3.trim().length() > 0) {
                sb.append(buAddr3.trim()).append(", ");
            }
            if (buCity != null && buCity.trim().length() > 0) {
                sb.append(buCity.trim()).append(", ");
            }
            if (buState != null && buState.trim().length() > 0 && !"KOR01".equalsIgnoreCase(conslInvBuName)) {
                sb.append(buState.trim()).append(", ");
            }
            if (buPostal != null && buPostal.trim().length() > 0) {
                sb.append(buPostal.trim()).append(" ");
            }
            if (name != null && name.trim().length() > 0 && !name.equals(buCity)) {
                sb.append(name.trim()).append(".");
            }
            return sb.toString();
        }

        return source.getCalculatedField(fieldName);
    }
    
    @Override
    // TODO need to review
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params){
        // subTotal: sum of NET_PRICE
        // tally: sum of ALLOC_PCT
        // groupSumVar: sum of ALLOC_PCT
        String[] fields = {"NET_PRICE", "ALLOC_PCT", "ALLOC_PCT"};
        double[] results = ReportUtil.summariseFields(fields, amds);
        
        amds.addCalculatedField("subtotal", new BigDecimal(String.valueOf(results[0])));
        amds.addCalculatedField("tally2", new BigDecimal(String.valueOf(results[1])));
        amds.addCalculatedField("groupSumVar", new BigDecimal(String.valueOf(results[2])));
        
        // total: sum of NET_PRICE * ALLOC_PCT
        double result = ReportUtil.perentSummariseFields("NET_PRICE", "ALLOC_PCT", amds);
        amds.addCalculatedField("total", new BigDecimal(String.valueOf(result)));
    }
}
