/**
 * 
 */
package com.intertek.report;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

import com.intertek.config.PhoenixConfiguration;

/**
 *
 * @author richard.qin
 */
public class SimpleCsvDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(SimpleCsvDataSource.class);

    /**
     * Load csv a datasource. The first row is the csv file is the header name. Non-string fields are
     * specified in the SimpleDataSource.properties file.
     * @paraam reportName The name of the report, also used as the key to look up entries in the
     * SimpleDataSource.properties file.
     * @params params This parameter is ignored.
     * @params path Path to the csv file.
     * 
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, Map, java.lang.String)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, String path) {
        PhoenixConfiguration conf = PhoenixConfiguration.getConfiguration();
        int limit = conf.getTestDataLimit();
        
        // the main difference from other csv datasource is that fieldnames and field types are not
        // known, they must be figured out by reading the SimpleDataProvider.properties file and
        // the matching csv file itself.
        InputStream is = null;
        CSVReader reader = null;
        List<String> typeNames = new ArrayList<String>(); 
        String[] headings = null;
        try{
            // read the relevant section of SimpleDataSource.properties file
            Properties prop = new Properties();
            is = this.getClass().getResourceAsStream("/SimpleDataSource.properties");
            prop.load(is);
            int prefixLen = reportName.length() + 1;
            for(Object item: prop.keySet()){
                String str = (String)item;
                // format of SimpleDataSource.properties
                // reportName=csv_file_name
                // reportName.fieldName_1=type_string
                // reportName.fieldName_2=type_string
                // type_string can be Timestamp or BigDecimal
                if(str.equals(reportName)){
                    path = prop.getProperty(str);
                }
                else if(str.startsWith(reportName)){
                    String fieldName = str.substring(prefixLen);
                    typeNames.add(fieldName);
                    typeNames.add(prop.getProperty(str));
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
        // convert the field type strings into a field type map
        Map<String, FieldType> types = ReportUtil.mapFieldTypes(typeNames.toArray(new String[0]));
        
        return ReportUtil.localJasperDataSourceHelper(reportName, path, headings, types, 
                                                      limit, this, params);
    }

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        throw new UnsupportedOperationException("SimpleCsvDataSource cannot be used with hibernate connections.");
    }

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        throw new UnsupportedOperationException("SimpleCsvDataSource cannot be used with JDBC connections.");
    }
}
