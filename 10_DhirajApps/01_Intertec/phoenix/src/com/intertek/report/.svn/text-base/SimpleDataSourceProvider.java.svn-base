/**
 * 
 */
package com.intertek.report;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignField;

/**
 * This simple JRDataSourceProvider implementation provides a simple way for integrating the
 * phoenix data sources with a visual reporting tool.
 * @author richard.qin
 */
public class SimpleDataSourceProvider implements JRDataSourceProvider {
    private static final Logger log = Logger.getLogger(SimpleDataSourceProvider.class);

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSourceProvider#create(net.sf.jasperreports.engine.JasperReport)
     */
    @Override
    public JRDataSource create(JasperReport report) throws JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("Is_Testing", true);
        return DataSourceManager.getReportDataSource(report.getName(), params);
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSourceProvider#dispose(net.sf.jasperreports.engine.JRDataSource)
     */
    @Override
    public void dispose(JRDataSource arg0) throws JRException {
        // Nothing
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSourceProvider#getFields(net.sf.jasperreports.engine.JasperReport)
     */
    @Override
    public JRField[] getFields(JasperReport report) throws JRException, UnsupportedOperationException {
        String reportName = report.getName();
        String path = null;
        InputStream is = null;
        CSVReader reader = null;
        String[] headings = null;
        List<JRField> fields = new ArrayList<JRField>();
        try{
            // read the relevant section of SimpleDataSource.properties file
            Properties prop = new Properties();
            is = this.getClass().getResourceAsStream("/SimpleDataSource.properties");
            prop.load(is);
            for(Object item: prop.keySet()){
                String str = (String)item;
                if(str.equals(reportName)){
                    path = prop.getProperty(str);
                    break;
                }
            }
            // read the first row of the csv file
            reader = new CSVReader(new FileReader(path), ',');
            headings = reader.readNext();
        }
        catch(FileNotFoundException ex){
            log.error("Cannot find csv file: " + path);
            log.error(ex);
            return null;
        }
        catch(IOException ex){
            log.error("Unexpected error when reading " + path);
            log.error(ex);
            return null;
        }
        finally{
            if(is != null){
                try{
                    is.close();
                }
                catch(Exception ignore){
                    
                }
            }
            if(reader != null){
                try{
                    reader.close();
                }
                catch(Exception ignore){
                    
                }
            }
        }
        
        // convert field names to JRFields
        for(String fieldName: headings){
            JRDesignField field = new JRDesignField();
            field.setName(fieldName);
            fields.add(field);
        }
        
        return fields.toArray(new JRField[0]);
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSourceProvider#supportsGetFieldsOperation()
     */
    @Override
    public boolean supportsGetFieldsOperation() {
        return true;
    }

}
