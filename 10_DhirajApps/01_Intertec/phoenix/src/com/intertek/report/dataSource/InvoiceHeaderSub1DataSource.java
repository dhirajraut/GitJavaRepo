/**
 * 
 */
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

/**
 * 
 * @author richard.qin
 */
public class InvoiceHeaderSub1DataSource extends AbstractJasperDataSource {

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
                        
        List<StringShell> returnedFields = new ArrayList<StringShell>();
        // get the built-in Jasper report data source
        ArrayMapDataSource ds = (ArrayMapDataSource)params.get("REPORT_DATA_SOURCE");
        String buName = (String)ds.getFieldValue("BU_NAME"); // "CONSL_INV_BU_NAME"
        List<String> list = new ArrayList<String>();
        if(!"CAN02".equalsIgnoreCase(buName)){
        	//START: 130869
        	/*
            list.add((String)ds.getFieldValue("BANKS_ADDRESS1"));
            list.add((String)ds.getFieldValue("BANKS_ADDRESS2"));
            list.add((String)ds.getFieldValue("BANKS_ADDRESS3"));
            list.add((String)ds.getFieldValue("BANKS_CITY") + ", " 
                           + ds.getFieldValue("BANKS_STATE_CODE") + " " 
                           + ds.getFieldValue("BANKS_POSTAL"));
            */
        	 list.add(DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANKS_ADDRESS1")));
             list.add(DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANKS_ADDRESS2")));
             list.add(DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANKS_ADDRESS3")));
             list.add(DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANKS_CITY")) + ", " 
                            + DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANKS_STATE_CODE")) + " " 
                            + DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANKS_POSTAL")));
             //END: 130869 
        }
        else{
        	//START: 130869
        	/*
            list.add((String)ds.getFieldValue("BANK_ACCT_ADDRESS1"));
            list.add((String)ds.getFieldValue("BANK_ACCT_ADDRESS2"));
            list.add((String)ds.getFieldValue("BANK_ACCT_ADDRESS3"));
            list.add((String)ds.getFieldValue("BANK_ACCT_ADDRESS4"));
            list.add((String)ds.getFieldValue("BANK_ACCT_CITY") + ", " 
                           + ds.getFieldValue("BANK_ACCT_STATE_CODE") + " " 
                           + ds.getFieldValue("BANK_ACCT_POSTAL"));
            */
        	list.add(DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANK_ACCT_ADDRESS1")));
            list.add(DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANK_ACCT_ADDRESS2")));
            list.add(DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANK_ACCT_ADDRESS3")));
            list.add(DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANK_ACCT_ADDRESS4")));
            list.add(DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANK_ACCT_CITY")) + ", " 
                           + DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANK_ACCT_STATE_CODE")) + " " 
                           + DataSourceHelper.nullCheckReturnEmptyStr(ds.getFieldValue("BANK_ACCT_POSTAL")));
        	//END: 130869
        }
        // TODO refactor
        for (String curField : list) {
            if ((curField != null) && (curField.trim().length() > 0)) { 
                returnedFields.add(new StringShell(curField));
            }
        }
        return new JRBeanCollectionDataSource(returnedFields);
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
