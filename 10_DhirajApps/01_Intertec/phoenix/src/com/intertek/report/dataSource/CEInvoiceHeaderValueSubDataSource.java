/**
 * 
 */
package com.intertek.report.dataSource;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.log4j.Logger;

import com.intertek.phoenix.util.ArrayMap;
import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.ReportUtil;

/**
 * 
 * @author richard.qin
 */
public class CEInvoiceHeaderValueSubDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceHeaderValueSubDataSource.class);
    
    private static final String[] fieldNames = {
//        "UID20",
        "INVOICE",
        "JOB_NUMBER",
        "CUST_CODE",
        "INVOICE_LABEL1",
        "INVOICE_VALUE1",
        "INVOICE_LABEL2",
        "INVOICE_VALUE2",
        "INVOICE_LABEL3",
        "INVOICE_VALUE3",
        "INVOICE_LABEL4",
        "INVOICE_VALUE4",
        "INVOICE_LABEL5",
        "INVOICE_VALUE5",
        "CUST_REF_NUM"
    };
    
    private static final String[] fieldTypes = {};

    public CEInvoiceHeaderValueSubDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
    	 String invoice =(params.get("InvType")!=null && params.get("InvType").equals("deposit"))?"DepositInvoice":"CEInvoice";
   	     String jobContractId=(params.get("InvType")!=null && params.get("InvType").equals("deposit"))?"inv.jobContractId":"inv.jobOrderContractId";
         String hql="SELECT "
            + "     inv.invoiceNumber as INVOICE,"
            + "     jc.jobOrderNumber as JOB_NUMBER,"
            + "     jc.customerCode as CUST_CODE,"
            + "     jc.invoiceLabel1 as INVOICE_LABEL1,"
            + "     jc.invoiceValue1 as INVOICE_VALUE1,"
            + "     jc.invoiceLabel2 as INVOICE_LABEL2,"
            + "     jc.invoiceValue2 as INVOICE_VALUE2,"
            + "     jc.invoiceLabel3 as INVOICE_LABEL3,"
            + "     jc.invoiceValue3 as invoiceValue3,"
            + "     jc.invoiceLabel4 as INVOICE_LABEL4,"
            + "     jc.invoiceValue4 as invoiceValue4,"
            + "     jc.invoiceLabel5 as INVOICE_LABEL5,"
            + "     jc.invoiceValue5 as INVOICE_VALUE5,"
            + "     jc.custRefNum as CUST_REF_NUM"
            + "     FROM "+invoice+ " inv,CEJobContract jc"
            + "     WHERE jc.id="+jobContractId
            + "     AND inv.invoiceNumber = ?";
        
            log.debug(hql);            
        ArrayList<Object> paramList = new ArrayList<Object>();
        paramList.add(params.get("Invoice"));

        //List<Object[]> result =searchService.searchByHql(hql,CEInvoice.class,paramList);
        List<Object[]> result =ReportUtil.hibernateJasperDataSourceHelperByHql(hql.toString(),paramList,typeMap,fieldNames);
        if(result != null){
            return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), result, this, params);
        }
        else
        {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        return getDataSource(reportName,params);
    }
}
