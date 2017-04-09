/**
 * 
 */
package com.intertek.report;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import com.intertek.config.PhoenixConfiguration;

/**
 * A simple helper to reduce code clutterness
 * @author richard.qin
 */
public abstract class AbstractJasperDataSource implements JasperDataSource {
    protected String[] fieldNames;
    protected Map<String, FieldType> typeMap;
    protected boolean isReferenceTable;
    protected String[] keyFieldNames = null;
    
    protected AbstractJasperDataSource(){
        this.isReferenceTable = false;
    }
    
    /**
     * The default behavior is to load some data from a local csv file.
     * If data is available in another source or form, this method must be overriden.
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, Map, java.lang.String)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, String path) {
        PhoenixConfiguration conf = PhoenixConfiguration.getConfiguration();
        int limit = conf.getTestDataLimit();
        
        return ReportUtil.localJasperDataSourceHelper(
                        reportName, path, fieldNames, typeMap, limit, this, params);
    }

    /**
     * The default action is to do nothing. If an implementation need to implement some
     * calculated fields, then this method must be implemented.
     * @see com.intertek.report.JasperDataSource#notifyNext(java.util.Map, net.sf.jasperreports.engine.JRDataSource)
     */
    @Override
    public Object calculateFieldValue(String fieldName, Map<String, Object> params, ArrayMapDataSource source) {
        return null;
    }
    
    /**
     * Give the datasource hander a chance to calculate some field values
     */
    @Override
    public void dataLoaded(ArrayMapDataSource datasource, Map<String, Object> params){ 
    }
    
    @Override
    public boolean isReferenceTable(){
        return this.isReferenceTable;
    }
    
    @Override 
    public String[] getKeyFieldNames(){
        return this.keyFieldNames;
    }
}
