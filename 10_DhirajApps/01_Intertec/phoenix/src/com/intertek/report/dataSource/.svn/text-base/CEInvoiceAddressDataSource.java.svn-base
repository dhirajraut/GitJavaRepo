package com.intertek.report.dataSource;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.jasper.StringShell;

public class CEInvoiceAddressDataSource extends AbstractJasperDataSource {
    
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        
        List<StringShell> returnedFields = new ArrayList<StringShell>();
        // get the built-in Jasper report data source
        ArrayMapDataSource ds = (ArrayMapDataSource)params.get("REPORT_DATA_SOURCE");
        if(ds != null){
            List<String> list = new ArrayList<String>();
            String type = (String)ds.getFieldValue("DEPOSIT_TYPE");
            if(type!=null&&!type.equalsIgnoreCase("L")){
                list.add((String)ds.getFieldValue("BUSINESS_UNITS_ADDRESS1"));
                list.add((String)ds.getFieldValue("BUSINESS_UNITS_ADDRESS2"));
                list.add((String)ds.getFieldValue("BUSINESS_UNITS_ADDRESS3"));
                list.add((String)ds.getFieldValue("BUSINESS_UNITS_ADDRESS4"));
                list.add((String)ds.getFieldValue("BUSINESS_UNITS_CITY") + ", " 
                                + ds.getFieldValue("BUSINESS_UNITS_STATE_CODE") + " " 
                                + ds.getFieldValue("BUSINESS_UNITS_POSTAL"));
                // in the old jrxml file, the last component is defined through a $V{Country_Name}
                String name = (String)ds.getFieldValue("NAME");
                if(!name.equals(ds.getFieldValue("BUSINESS_UNITS_CITY"))){
                    list.add(name);
                    
                }
            }
            else{
                list.add((String)ds.getFieldValue("BANK_ACCT_ADDRESS1"));
                list.add((String)ds.getFieldValue("BANK_ACCT_ADDRESS2"));
                list.add((String)ds.getFieldValue("BANK_ACCT_ADDRESS3"));
                list.add((String)ds.getFieldValue("BANK_ACCT_ADDRESS4"));
                list.add((String)ds.getFieldValue("BANK_ACCT_CITY") + ", " 
                                + ds.getFieldValue("BANK_ACCT_STATE_CODE") + " " 
                                + ds.getFieldValue("BANK_ACCT_POSTAL"));
                list.add((String)ds.getFieldValue("BANK_ACCT_COUNTRY_CODE"));
            }

            for (String curField : list) {
                if ((curField != null) && (curField.trim().length() > 0)) {
                    returnedFields.add(new StringShell(curField));
                }
            }
            return new JRBeanCollectionDataSource(returnedFields);
        }
        return null;
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
