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
public class InvoiceNontaxFooterDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceNontaxFooterDataSource.class);

    private static final String[] fieldNames = {
        "JOB_NUMBER",
        "INVOICE",
        "UID20",
        "CUSTOMERS_NAME",
        "BU_NAME",
        "BUSINESS_UNITS_DESCRIPTION",
        "BUSINESS_UNITS_COMPANY_DESC",
        "BUSINESS_UNITS_ADDRESS1",
        "BUSINESS_UNITS_CITY",
        "BUSINESS_UNITS_POSTAL",
        "BUSINESS_UNITS_ADDRESS2",
        "BUSINESS_UNITS_ADDRESS3",
        "BUSINESS_UNITS_ADDRESS4",
        "BUSINESS_UNITS_COUNTRY_CODE",
        "BUSINESS_UNITS_STATE_CODE",
        "NAME",
        "BRANCHES_COMPANY_DESC",
    };
    
    private static final String[] fieldTypes = {};
    
    public InvoiceNontaxFooterDataSource(){
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
                + "     jci.CUSTOMERS_NAME,"
                + " jo.BU_NAME,"
                + "     jci.BUSINESS_UNITS_DESCRIPTION,"
                + "     jci.BUSINESS_UNITS_COMPANY_DESC,"
                + "     jci.BUSINESS_UNITS_ADDRESS1,"
                + "     jci.BUSINESS_UNITS_CITY,"
                + "     jci.BUSINESS_UNITS_POSTAL,"
                + "     jci.BUSINESS_UNITS_ADDRESS2,"
                + "     jci.BUSINESS_UNITS_ADDRESS3,"
                + "     jci.BUSINESS_UNITS_ADDRESS4,"
                + "     jci.BUSINESS_UNITS_COUNTRY_CODE,"
                + "     jci.BUSINESS_UNITS_STATE_CODE,"
                + "     jci.BUSINESS_UNITS_COUNTRY_NAME NAME,"
                + " jci.BRANCHES_COMPANY_DESC"
                + " FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, PHOENIX.job_orders jo"
                + " WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
                + " AND jc.JOB_NUMBER =jo.JOB_NUMBER"
                + "     AND jc.JOB_NUMBER = :JOB_NUMBER"
                + "     AND jc.UID20 = :UID20"
                + "     AND jci.INVOICE = :INVOICE";
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
            + "     jci.CUSTOMERS_NAME,"
            + " jo.BU_NAME,"
            + "     jci.BUSINESS_UNITS_DESCRIPTION,"
            + "     jci.BUSINESS_UNITS_COMPANY_DESC,"
            + "     jci.BUSINESS_UNITS_ADDRESS1,"
            + "     jci.BUSINESS_UNITS_CITY,"
            + "     jci.BUSINESS_UNITS_POSTAL,"
            + "     jci.BUSINESS_UNITS_ADDRESS2,"
            + "     jci.BUSINESS_UNITS_ADDRESS3,"
            + "     jci.BUSINESS_UNITS_ADDRESS4,"
            + "     jci.BUSINESS_UNITS_COUNTRY_CODE,"
            + "     jci.BUSINESS_UNITS_STATE_CODE,"
            + "     jci.BUSINESS_UNITS_COUNTRY_NAME NAME,"
            + " jci.BRANCHES_COMPANY_DESC"
            + " FROM PHOENIX.JOB_CONTRACTS jc, PHOENIX.JOB_CONTRACT_INVOICE jci, PHOENIX.job_orders jo"
            + " WHERE jc.JOB_CONTRACT_ID = jci.JOB_CONTRACT_ID"
            + " AND jc.JOB_NUMBER =jo.JOB_NUMBER"
            + "     AND jc.JOB_NUMBER = ?"
            + "     AND jc.UID20 = ?"
            + "     AND jci.INVOICE = ?";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
            new Pair<String, Object>("UID20", params.get("uid20")),
            new Pair<String, Object>("INVOICE", params.get("Invoice"))
        };
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
    }

    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("TEXT_1")){
//      ( (($F{BUSINESS_UNITS_ADDRESS1} == null) || ($F{BUSINESS_UNITS_ADDRESS1}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_ADDRESS1}+", ")
//    + ( (($F{BUSINESS_UNITS_ADDRESS2} == null) || ($F{BUSINESS_UNITS_ADDRESS2}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_ADDRESS2}+", ") 
//    + ( (($F{BUSINESS_UNITS_ADDRESS3} == null) || ($F{BUSINESS_UNITS_ADDRESS3}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_ADDRESS3}+", ")
//    + ( (($F{BUSINESS_UNITS_CITY} == null) || ($F{BUSINESS_UNITS_CITY}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_CITY}+", ") 
//    + ( (($F{BUSINESS_UNITS_STATE_CODE} == null) || ($F{BUSINESS_UNITS_STATE_CODE}.trim()).isEmpty() || $F{BU_NAME}.equalsIgnoreCase( "KOR01" )) ? "" : $F{BUSINESS_UNITS_STATE_CODE}+", ")
//    + ( (($F{BUSINESS_UNITS_POSTAL} == null) || ($F{BUSINESS_UNITS_POSTAL}.trim()).isEmpty()) ? "" : $F{BUSINESS_UNITS_POSTAL})
//    + ( (($F{NAME} == null) || ($F{NAME}.trim()).isEmpty()) ? "" : 
//        ( $F{NAME}.equals( $F{BUSINESS_UNITS_CITY} )? "" : $F{NAME}))+ "."        
            String buaddr1 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS1");
            String buaddr2 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS2");
            String buaddr3 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS3");
            String bucity = (String)source.getFieldValue("BUSINESS_UNITS_CITY");
            String bustate = (String)source.getFieldValue("BUSINESS_UNITS_STATE_CODE");
            String bupostal = (String)source.getFieldValue("BUSINESS_UNITS_POSTAL");
            String buname = (String)source.getFieldValue("BU_NAME");
            String name = (String)source.getFieldValue("NAME");
            
            StringBuilder sb = new StringBuilder();
            if(buaddr1 != null && !buaddr1.trim().isEmpty()){
                sb.append(buaddr1).append(", ");
            }
            if(buaddr2 != null && !buaddr2.trim().isEmpty()){
                sb.append(buaddr2).append(", ");
            }
            if(buaddr3 != null && !buaddr3.trim().isEmpty()){
                sb.append(buaddr3).append(", ");
            }
            if(bucity != null && !bucity.trim().isEmpty()){
                sb.append(bucity).append(", ");
            }
            if(bustate != null && !bustate.trim().isEmpty() && !buname.equalsIgnoreCase("KOR01")){
                sb.append(bustate).append(", ");
            }
            if(bupostal != null && !bupostal.trim().isEmpty()){
                sb.append(bupostal).append(" ");
            }
            if(name != null && !name.trim().isEmpty() && !name.equals(bucity)){
                sb.append(name).append(".");
            }
            return sb.toString();
        }
        return source.getCalculatedField(fieldName);
    }
}
