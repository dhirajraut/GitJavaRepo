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

public class CEInvoiceHeaderSubDataSource extends AbstractJasperDataSource {
    private static String DEFAULT_PREFIX = "default";

    private String shipto;
    public CEInvoiceHeaderSubDataSource(String val) {
        shipto=val;
    }

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {

        ArrayList<StringShell> returnedFields = new ArrayList<StringShell>();
        // get the built-in Jasper report data source
        ArrayMapDataSource ds = (ArrayMapDataSource)params.get("REPORT_DATA_SOURCE");

        HashMap <String, String> keyVals = new HashMap<String, String>();
        String prefix;
        
      String Shipvalue=ds.getFieldValue("HAS_SHIPTO")==null?"":ds.getFieldValue("HAS_SHIPTO").toString();
        
        if(Shipvalue.equals("true") && shipto.equalsIgnoreCase("SHIPTO")){
        
        keyVals.put("customers_name", (String)ds.getFieldValue("CUSTOMERS_NAME"));
        keyVals.put("billing_contact_name", (String)ds.getFieldValue("SHIP_BILLING_CONTACT_NAME"));
        keyVals.put("address1", (String)ds.getFieldValue("SHIP_ADDRESS1"));
        keyVals.put("address2", (String)ds.getFieldValue("SHIP_ADDRESS2"));
        keyVals.put("address3", (String)ds.getFieldValue("SHIP_ADDRESS3"));
        keyVals.put("address4", (String)ds.getFieldValue("SHIP_ADDRESS4"));
        keyVals.put("postal", (String)ds.getFieldValue("SHIP_COUNTY"));
        keyVals.put("city", (String)ds.getFieldValue("SHIP_CITY"));
        keyVals.put("county", (String)ds.getFieldValue("SHIP_POSTAL"));
        keyVals.put("state", (String)ds.getFieldValue("SHIP_STATE"));
        keyVals.put("state_name", (String)ds.getFieldValue("SHIP_STATE_NAME"));
        keyVals.put("country", (String)ds.getFieldValue("SHIP_COUNTRY"));
        String shipCountryName = (String)ds.getFieldValue("SHIP_COUNTRY_NAME");
        if(shipCountryName != null && !shipCountryName.equals(ds.getFieldValue("SHIP_CITY"))){
            keyVals.put("country_name", shipCountryName);
        }
        else{
            keyVals.put("country_name", null);
        }

        //get locale.
         prefix = (String)ds.getFieldValue("SHIP_COUNTRY");
        
        
        }
        else{
        
        // prepare format parameters
        keyVals.put("customers_name", (String)ds.getFieldValue("CUSTOMERS_NAME"));
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
        if(countryName != null && !countryName.equals(ds.getFieldValue("CITY"))){
            keyVals.put("country_name", countryName);
        }
        else{
            keyVals.put("country_name", null);
        }

        //get locale.
        prefix = (String)ds.getFieldValue("COUNTRY");
        
        }

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
