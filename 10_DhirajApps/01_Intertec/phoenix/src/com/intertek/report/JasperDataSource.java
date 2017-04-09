/**
 * 
 */
package com.intertek.report;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * An interface to obtain DataSource for Jasper reports
 * @author richard.qin
 */
public interface JasperDataSource {
    
    public boolean isReferenceTable();
    
    public String[] getKeyFieldNames();
    
    /**
     * Get a data source for the given JasperReport, using hibernate to access the database
     * @param reportName The name of the report that the data source is targeted to
     * @param params additional parameters used prepare the datasource
     * @return
     */
    public JRDataSource getDataSource(String reportName, Map<String, Object> params);   
    /**
     * Get a data source for the given JasperReport, using JDBC to access the database
     * @param reportName The name of the report that the data source is targeted to
     * @param params additional parameters used prepare the datasource
     * @return
     */
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con);   
    /**
     * Get a data source for the given JasperReport
     * @param reportName The name of the report that the data source is targeted to
     * @param params additional parameters used prepare the datasource
     * @return
     */
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, String path);   
    /**
     * This is a callback function that gives a JasperDataSource implementation a chance
     * to calculate a field value at runtime
     * @param fieldName name of the report
     * @param params a record
     * @param source TODO
     * @param params the parameter map
     * @return value of the calculated field
     */
    public Object calculateFieldValue(String fieldName, Map<String, Object> params, ArrayMapDataSource source);

    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params);
}
