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
public class InvoicePreviewRebillDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoicePreviewRebillDataSource.class);
    
    private static final String[] fieldNames = {
        "JOB_NUMBER",
        "INVOICE",
        "UID20",
        "CUSTOMERS_NAME",
        "ADDRESS1",
        "ADDRESS2",
        "ADDRESS3",
        "CITY",
        "STATE",
        "POSTAL",
        "COUNTRY",
        "BILLING_CONTACT_NAME",
        "BUSINESS_UNITS_DESCRIPTION",
        "BUSINESS_UNITS_COMPANY_DESC",
        "PHONE_PREFIX",
        "PHONE_NUMBER",
        "PHONE_EXTENSION",
        "ADDRESS4",
        "BUSINESS_UNITS_COUNTRY_CODE",
        "STATE_CODE",
        "NAME",
        "JOB_FINISH_DATE",
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
        "CREDIT_IND",
        "LINE_DESCRIPTION",
        "SPLIT_PCT",
        "DISCOUNT_PCT",
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
        "PREBILL_SPLIT_ID",
        "ALLOC_AMT",
        "ALLOC_PCT",
        "LINE_NUMBER",
        "COMMENTS",
        "INVOICE_DESCR",
        "TRANSACT_CURRENCY_CD",
        "JOB_DESCRIPTION",
        "BRANCHES_COMPANY_DESC",
        "BRANCHES_PHONE_PREFIX",
        "BRANCHES_PHONE_NUMBER",
        "BRANCHES_PHONE_EXTENSION",
        "CUST_CODE",
        "BRANCH_NAME",
        "BU_NAME",
        "ROUNDING_DIGITS",
    };

    private static final String[] fieldTypes = {
        "JOB_FINISH_DATE",  "Timestamp",
        "SPLIT_PCT",        "BigDecimal",
        "DISCOUNT_PCT",     "BigDecimal",
        "NET_PRICE",        "BigDecimal",
        "UNIT_PRICE",       "BigDecimal",
        "VESSEL_SORT_NUM",  "BigDecimal",
        "LOT_SORT_NUM",     "BigDecimal",
        "CHARGE_SORT_NUM",  "BigDecimal",
        "PREBILL_SPLIT_ID", "BigDecimal",
        "ALLOC_AMT",        "BigDecimal",
        "ALLOC_PCT",        "BigDecimal",
        "LINE_NUMBER",      "BigDecimal",
        "ROUNDING_DIGITS",  "BigDecimal",
    };

    public InvoicePreviewRebillDataSource(){
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
            + "     c.NAME AS CUSTOMERS_NAME,"
            + " ca.ADDRESS1 AS ADDRESS1,"
            + " ca.ADDRESS2 AS ADDRESS2,"
            + " ca.ADDRESS3 AS ADDRESS3,"
            + " ca.CITY AS CITY,"
            + " ca.STATE AS STATE,"
            + " ca.POSTAL AS POSTAL,"
            + " ca.COUNTRY AS COUNTRY,"
            + " jci.BILLING_CONTACT_NAME,"
            + "     jci.BUSINESS_UNITS_DESCRIPTION,"
            + "     jci.BUSINESS_UNITS_COMPANY_DESC,"
            + "     b.ADDRESS1,"
            + "     b.CITY,"
            + "     b.POSTAL,"
            + "     b.PHONE_PREFIX,"
            + "     b.PHONE_NUMBER,"
            + "     b.PHONE_EXTENSION,"
            + "     b.ADDRESS2,"
            + "     b.ADDRESS3,"
            + "     b.ADDRESS4,"
            + "     b.COUNTRY_CODE AS BUSINESS_UNITS_COUNTRY_CODE,"
            + "     b.STATE_CODE,"
            + "     jci.BUSINESS_UNITS_COUNTRY_NAME NAME,"
            + " jci.JOB_FINISH_DATE,"
            + " jci.INVOICE_LABEL1,"
            + " jci.INVOICE_VALUE1,"
            + " jci.INVOICE_LABEL2,"
            + " jci.INVOICE_VALUE2,"
            + " jci.INVOICE_LABEL3,"
            + " jci.INVOICE_VALUE3,"
            + " jci.INVOICE_LABEL4,"
            + " jci.INVOICE_VALUE4,"
            + " jci.INVOICE_LABEL5,"
            + " jci.INVOICE_VALUE5,"
            + " jci.CUST_REF_NUM,"
            + " jci.CREDIT_IND,"
            + "     il.LINE_DESCRIPTION,    "
            + "     il.SPLIT_PCT,       "
            + "     il.DISCOUNT_PCT, "
            + "     il.NET_PRICE,       "
            + "     il.UNIT_PRICE,"
            + "     il.ACCOUNT,       "
            + "     il.PRODUCT_GROUP, "
            + "     il.DEPTID,"
            + "     il.PRIMARY_BRANCH_CD,"
            + "     il.LEVEL0,        "
            + "     il.VESSEL_SORT_NUM,   "
            + "     il.LEVEL1,    "
            + "     il.LOT_SORT_NUM, "
            + "     il.LEVEL2,      "
            + "     il.CHARGE_SORT_NUM,"
            + "     ils.PREBILL_SPLIT_ID,"
            + "     ILS.ALLOC_AMT,"
            + "     ILS.ALLOC_PCT,"
            + "     IL.LINE_NUMBER,"
            + " il.COMMENTS,"
            + " jc.INVOICE_DESCR,"
            + "     jci.TRANSACT_CURRENCY_CD,"
            + "     jci.JOB_DESCRIPTION, "
            + "     jci.BRANCHES_COMPANY_DESC,"
            + "     jci.BRANCHES_PHONE_PREFIX,"
            + "     jci.BRANCHES_PHONE_NUMBER,"
            + "     jci.BRANCHES_PHONE_EXTENSION,"
            + " jc.CUST_CODE,"
            + " ils.BRANCH_NAME,"
            + " jo.BU_NAME,"
            + " cur.DECIMAL_DIGITS AS ROUNDING_DIGITS "
            + "   FROM JOB_CONTRACTS jc, JOB_CONTRACT_INVOICE jci, CUSTOMERS c,"
            + "   CUST_ADDRESSES ca, JOB_ORDERS jo, BUSINESS_UNITS b,"
            + "   INVOICE_LINES il, INVOICE_LINE_SPLITS ils, CURRENCY cur"
            + "   WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "     AND jc.CUST_CODE = c.CUST_CODE"
            + "     AND jc.CUST_CODE = ca.CUST_CODE"
            + "     AND jc.CUST_LOCATION_NUMBER = ca.LOCATION_NUMBER"
            + "     AND ca.EFF_DATE = ( select max(ca2.EFF_DATE) "
            + "           from CUST_ADDRESSES ca2 where ca2.CUST_CODE = ca.CUST_CODE"
            + "           and ca2.LOCATION_NUMBER = ca.LOCATION_NUMBER)"
            + "     AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "     AND jo.BU_NAME = b.BU_NAME"
            + "     AND jci.JOB_CONTRACT_INVOICE_ID = il.JOB_CONTRACT_INVOICE_ID"
            + "     AND il.INVOICE_LINE_ID = ils.INVOICE_LINE_ID"
            + "     AND jc.TRANSACT_CURRENCY_CD = cur.CURRENCY_CD"
            + "     AND jci.JOB_CONTRACT_INVOICE_ID = :INVOICE_ID"
            + " ORDER BY "
            + "  LEVEL0 ASC,"
            + "  LEVEL1 ASC,"
            + "  LINE_NUMBER ASC,"
            + "  PREBILL_SPLIT_ID ASC";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("INVOICE_ID", params.get("jci_id")),
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
            + "     c.NAME AS CUSTOMERS_NAME,"
            + " ca.ADDRESS1 AS ADDRESS1,"
            + " ca.ADDRESS2 AS ADDRESS2,"
            + " ca.ADDRESS3 AS ADDRESS3,"
            + " ca.CITY AS CITY,"
            + " ca.STATE AS STATE,"
            + " ca.POSTAL AS POSTAL,"
            + " ca.COUNTRY AS COUNTRY,"
            + " jci.BILLING_CONTACT_NAME,"
            + "     jci.BUSINESS_UNITS_DESCRIPTION,"
            + "     jci.BUSINESS_UNITS_COMPANY_DESC,"
            + "     b.ADDRESS1,"
            + "     b.CITY,"
            + "     b.POSTAL,"
            + "     b.PHONE_PREFIX,"
            + "     b.PHONE_NUMBER,"
            + "     b.PHONE_EXTENSION,"
            + "     b.ADDRESS2,"
            + "     b.ADDRESS3,"
            + "     b.ADDRESS4,"
            + "     b.COUNTRY_CODE AS BUSINESS_UNITS_COUNTRY_CODE,"
            + "     b.STATE_CODE,"
            + "     jci.BUSINESS_UNITS_COUNTRY_NAME NAME,"
            + " jci.JOB_FINISH_DATE,"
            + " jci.INVOICE_LABEL1,"
            + " jci.INVOICE_VALUE1,"
            + " jci.INVOICE_LABEL2,"
            + " jci.INVOICE_VALUE2,"
            + " jci.INVOICE_LABEL3,"
            + " jci.INVOICE_VALUE3,"
            + " jci.INVOICE_LABEL4,"
            + " jci.INVOICE_VALUE4,"
            + " jci.INVOICE_LABEL5,"
            + " jci.INVOICE_VALUE5,"
            + " jci.CUST_REF_NUM,"
            + " jci.CREDIT_IND,"
            + "     il.LINE_DESCRIPTION,    "
            + "     il.SPLIT_PCT,       "
            + "     il.DISCOUNT_PCT, "
            + "     il.NET_PRICE,       "
            + "     il.UNIT_PRICE,"
            + "     il.ACCOUNT,       "
            + "     il.PRODUCT_GROUP, "
            + "     il.DEPTID,"
            + "     il.PRIMARY_BRANCH_CD,"
            + "     il.LEVEL0,        "
            + "     il.VESSEL_SORT_NUM,   "
            + "     il.LEVEL1,    "
            + "     il.LOT_SORT_NUM, "
            + "     il.LEVEL2,      "
            + "     il.CHARGE_SORT_NUM,"
            + "     ils.PREBILL_SPLIT_ID,"
            + "     ILS.ALLOC_AMT,"
            + "     ILS.ALLOC_PCT,"
            + "     IL.LINE_NUMBER,"
            + " il.COMMENTS,"
            + " jc.INVOICE_DESCR,"
            + "     jci.TRANSACT_CURRENCY_CD,"
            + "     jci.JOB_DESCRIPTION, "
            + "     jci.BRANCHES_COMPANY_DESC,"
            + "     jci.BRANCHES_PHONE_PREFIX,"
            + "     jci.BRANCHES_PHONE_NUMBER,"
            + "     jci.BRANCHES_PHONE_EXTENSION,"
            + " jc.CUST_CODE,"
            + " ils.BRANCH_NAME,"
            + " jo.BU_NAME,"
            + " cur.DECIMAL_DIGITS AS ROUNDING_DIGITS "
            + "   FROM JOB_CONTRACTS jc, JOB_CONTRACT_INVOICE jci, CUSTOMERS c,"
            + "   CUST_ADDRESSES ca, JOB_ORDERS jo, BUSINESS_UNITS b,"
            + "   INVOICE_LINES il, INVOICE_LINE_SPLITS ils, CURRENCY cur"
            + "   WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + "     AND jc.CUST_CODE = c.CUST_CODE"
            + "     AND jc.CUST_CODE = ca.CUST_CODE"
            + "     AND jc.CUST_LOCATION_NUMBER = ca.LOCATION_NUMBER"
            + "     AND ca.EFF_DATE = ( select max(ca2.EFF_DATE) "
            + "           from CUST_ADDRESSES ca2 where ca2.CUST_CODE = ca.CUST_CODE"
            + "           and ca2.LOCATION_NUMBER = ca.LOCATION_NUMBER)"
            + "     AND jc.JOB_NUMBER = jo.JOB_NUMBER"
            + "     AND jo.BU_NAME = b.BU_NAME"
            + "     AND jci.JOB_CONTRACT_INVOICE_ID = il.JOB_CONTRACT_INVOICE_ID"
            + "     AND il.INVOICE_LINE_ID = ils.INVOICE_LINE_ID"
            + "     AND jc.TRANSACT_CURRENCY_CD = cur.CURRENCY_CD"
            + "     AND jci.JOB_CONTRACT_INVOICE_ID = ?"
            + " ORDER BY "
            + "  LEVEL0 ASC,"
            + "  LEVEL1 ASC,"
            + "  LINE_NUMBER ASC,"
            + "  PREBILL_SPLIT_ID ASC";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("INVOICE_ID", params.get("jci_id"))
        };
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
    }
    
    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("TEXT_10")){
//  ( ($F{ADDRESS1}.trim()).isEmpty() ? "" : $F{ADDRESS1}+", ")
//+ ( ($F{ADDRESS2}.trim()).isEmpty() ? "" : $F{ADDRESS2}+", ") 
//+ ( ($F{ADDRESS3}.trim()).isEmpty() ? "" : $F{ADDRESS3}+", ")
//+ ( ($F{CITY}.trim()).isEmpty() ? "" : $F{CITY}+", ") 
//+ ( (($F{STATE} == null) || ($F{STATE}.trim()).isEmpty() || $F{BU_NAME}.equalsIgnoreCase( "KOR01" )) ? "" : $F{STATE}+", ")
//+ ( ($F{POSTAL}.trim()).isEmpty() ? "" : $F{POSTAL}+", ")
//+ ( ($F{COUNTRY}.trim()).isEmpty() ? "" : $F{COUNTRY})
            String buAddr1 = (String) source.getFieldValue("ADDRESS1");
            String buAddr2 = (String) source.getFieldValue("ADDRESS2");
            String buAddr3 = (String) source.getFieldValue("ADDRESS3");
            String buCity = (String) source.getFieldValue("CITY");
            String buState = (String) source.getFieldValue("STATE");
            String buPostal = (String) source.getFieldValue("POSTAL");
            String country = (String) source.getFieldValue("COUNTRY");
            String buName = (String) source.getFieldValue("BU_NAME");

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
            if (buState != null && buState.trim().length() > 0  && buName.equalsIgnoreCase("KOR01")) {
                sb.append(buState.trim()).append(", ");
            }
            if (buPostal != null && buPostal.trim().length() > 0) {
                sb.append(buPostal.trim()).append(", ");
            }
            if (country != null && country.trim().length() > 0 ) {
                sb.append(country.trim());
            }
            return sb.toString();
        }

        return source.getCalculatedField(fieldName);
    }

    @Override
    // TODO need to review
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params){
        // subTotal: sum of NET_PRICE
        // tally2: sum of ALLOC_PCT
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
