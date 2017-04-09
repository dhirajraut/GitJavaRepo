/**
 * 
 */
package com.intertek.report.dataSource;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;

import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.Pair;
import com.intertek.report.ReportUtil;

/**
 * The data source for the weekly_sales_report.jrxml
 * @author richard.qin
 */
public class WeeklySalesReportDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(WeeklySalesReportDataSource.class);
    
    private static final String[] fieldNames ={
       "JOB_NUMBER",
       "JOB_FINISH_DATE",
       "BU_NAME",
       "BRANCH_NAME",
       "JOB_CONTRACT_ID",
       "GENERATED_DATE_TIME",
       "INVOICE",
       "INV_AMT_POST_TAX",
       "INVOICE_DATE",
       "PREBILL",
       "DECIMAL_DIGITS",
    };
    
    private static final String[] fieldTypes = {
        "JOB_FINISH_DATE", "Timestamp",
        "JOB_CONTRACT_ID", "BigDecimal",
        "GENERATED_DATE_TIME", "Timestamp",
        "INV_AMT_POST_TAX", "BigDecimal",
        "INVOICE_DATE", "Timestamp",
        "PREBILL", "BigDecimal",
        "DECIMAL_DIGITS", "BigDecimal",
    };

    public WeeklySalesReportDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
/*
     * (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String,
     * java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String,
     * java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        String sql = "begin ? := Weekly_Sales_Report_Pkg.Weekly_Sales_Report_FN2(?,?,?,?,?); end;";
        log.debug(sql);
        
        Pair<?, ?>[] parameters = {
            new Pair<String, Object>("business_unit", params.get("business_unit")),
            new Pair<String, Object>("operating_unit", params.get("operating_unit")),
            new Pair<String, Object>("as_of_dt", params.get("as_of_dt")),
            new Pair<String, Object>("start_dt", params.get("start_dt")),
            new Pair<String, Object>("dirName", params.get("dirName")),
            new Pair<String, Object>("currency", params.get("currency"))
        };
                    
        return ReportUtil.jdbcCallableJasperDataSourceHelper(
                        reportName, con, sql, parameters, fieldNames, typeMap, this, params);
    }
/*
    @Override
    public Object calculateFieldValue(String fieldName, Map<String, Object> param, ArrayMapDataSource source) {
        BigDecimal zero = new BigDecimal(0);
        if(fieldName.equals("InvAmt_Week12")){
            if(source.getFieldValue("INV_AMT_POST_TAX") == null){
                return zero;
            }
            else{
                return new BigDecimal("1231414");
//                Timestamp ts = (Timestamp)source.getFieldValue("JOB_FINISH_DATE");
//                Date week12 = new Date(((Date)param.get("as_of_dt")).getTime()- 604800000);
//                if(ts.after(week12) || ts.compareTo(week12) == 0){
//                    return source.getFieldValue("INV_AMT_POST_TAX");
//                }
//                else{
//                    return zero;
//                }
            }
        }
        else if(fieldName.equals("InvAmt_Week11")){
            return new BigDecimal("12345566");
//            if(source.getFieldValue("INV_AMT_POST_TAX") == null){
//                return zero;
//            }
//            else{
//                Timestamp ts = (Timestamp)source.getFieldValue("JOB_FINISH_DATE");
//                Date week12 = new Date(((Timestamp)param.get("as_of_dt")).getTime()- 604800000);
//                Date week11 = new Date(((Timestamp)param.get("as_of_dt")).getTime()- 604800000*2);
//                if(ts.before(week12) || ts.after(week11) || ts.compareTo(week12) == 0){
//                    return source.getFieldValue("INV_AMT_POST_TAX");
//                }
//                else{
//                    return zero;
//                }
//            }
        }
        return source.getCalculatedField(fieldName);
    }
*/    
}
