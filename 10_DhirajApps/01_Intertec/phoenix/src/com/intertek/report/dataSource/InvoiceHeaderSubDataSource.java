package com.intertek.report.dataSource;

import static com.intertek.config.PhoenixConfiguration.FORMAT_PROPERTIES;
import static com.intertek.report.ReportUtil.populatePrefix;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.intertek.config.PhoenixConfiguration;
import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.jasper.StringShell;

public class InvoiceHeaderSubDataSource extends AbstractJasperDataSource {
    private static String DEFAULT_PREFIX = "default";

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {

        ArrayList<StringShell> returnedFields = new ArrayList<StringShell>();
        // get the built-in Jasper report data source
        ArrayMapDataSource ds = (ArrayMapDataSource)params.get("REPORT_DATA_SOURCE");

        HashMap <String, String> keyVals = new HashMap<String, String>();

        // prepare format parameters
        //START: 130869
        /*
        String custName = (String)ds.getFieldValue("CUSTOMERS_NAME");
        if(custName == null || custName.length() == 0){
            custName = (String)ds.getFieldValue("NAME");
        }
        keyVals.put("customers_name", custName);
        keyVals.put("billing_contact_name", (String)ds.getFieldValue("BILLING_CONTACT_NAME"));
        keyVals.put("address1", (String)ds.getFieldValue("ADDRESS1"));
        keyVals.put("address2", (String)ds.getFieldValue("ADDRESS2"));
        keyVals.put("address3", (String)ds.getFieldValue("ADDRESS3"));
        keyVals.put("address4", (String)ds.getFieldValue("ADDRESS4"));
        keyVals.put("postal", (String)ds.getFieldValue("POSTAL"));
        keyVals.put("city", (String)ds.getFieldValue("CITY"));
        keyVals.put("county", (String)ds.getFieldValue("COUNTY"));
        keyVals.put("state", (String)ds.getFieldValue("STATE"));
        keyVals.put("state_name", (String)ds.getFieldValue("STATE_NAME"));
        keyVals.put("country", (String)ds.getFieldValue("COUNTRY"));
        String countryName = (String)ds.getFieldValue("COUNTRY_NAME");
        */
        String custName = DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("CUSTOMERS_NAME"));
        if(custName == null || custName.length() == 0){
            custName = (String)ds.getFieldValue("NAME");
        }
        keyVals.put("customers_name", custName);
        keyVals.put("billing_contact_name", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BILLING_CONTACT_NAME")));
        keyVals.put("address1", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("ADDRESS1")));
        keyVals.put("address2", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("ADDRESS2")));
        keyVals.put("address3", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("ADDRESS3")));
        keyVals.put("address4", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("ADDRESS4")));
        keyVals.put("postal", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("POSTAL")));
        keyVals.put("city", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("CITY")));
        keyVals.put("county", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("COUNTY")));
        keyVals.put("state", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("STATE")));
        keyVals.put("state_name", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("STATE_NAME")));
        keyVals.put("country", DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("COUNTRY")));
        String countryName = DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("COUNTRY_NAME"));
        //END: 130869
        
        if(countryName != null && !countryName.equals(ds.getFieldValue("CITY"))){
            keyVals.put("country_name", countryName);
        }
        else{
            keyVals.put("country_name", null);
        }

        //get locale.
        String prefix = (String)ds.getFieldValue("COUNTRY");

        //read in the properties file each time this method is called.
        Properties formatProps = PhoenixConfiguration.getProperties(FORMAT_PROPERTIES);

        // find entries in properties file matching locale, use default if none
        // found.
        if (formatProps.containsKey(prefix + ".1")) { // found entry for the
                                                      // first line.
            populatePrefix(prefix, formatProps, keyVals, returnedFields);
        }
        else { // use default.
            populatePrefix(DEFAULT_PREFIX, formatProps, keyVals, returnedFields);
        }

        return new JRBeanCollectionDataSource(returnedFields);//return fields.
    }

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        return getDataSource(reportName, params);
    }

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, String path) {
        return getDataSource(reportName, params);
    }
}
