/**
 * 
 */
package com.intertek.report.dataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;

import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.util.ArrayMap;
import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.Pair;
import com.intertek.report.ReportUtil;

/**
 * 
 * @author richard.qin
 */
public class CEInvoiceTaxFooterTaxRateDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(CEInvoiceTaxFooterTaxRateDataSource.class);
    private static final String[] fieldNames = {
        "JOB_NUMBER",
        "INVOICE",
//        "UID20",
        "INVOICE_TYPE",
        "TAX_CODE",
        "TAX_PCT",
        "TAX_AMT",
        "VAT_CODE",
        "VAT_PCT",
        "VAT_AMT",
        "NET_PRICE",
        "VAT_LABEL",
        "SALES_TAX_LABEL",
        "VAT_REG_LABEL",
        "TRANSACT_CURRENCY_CD",
        "BU_NAME",
        "DECIMAL_DIGITS",
    };
    
    private static final String[] fieldTypes = {
        "TAX_PCT", "BigDecimal",
        "TAX_AMT", "BigDecimal",
        "VAT_PCT", "BigDecimal",
        "VAT_AMT", "BigDecimal",
        "NET_PRICE", "BigDecimal",
        "DECIMAL_DIGITS", "BigDecimal",
    };

    public CEInvoiceTaxFooterTaxRateDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String hql = "SELECT  "
            + "     jc.jobOrderNumber as JOB_NUMBER,"
            + "     iv.invoiceNumber as INVOICE,"
            + "     iv.type as INVOICE_TYPE,"
            + "     il.taxCode as TAX_CODE,"
            + "     il.taxPct as TAX_PCT,"
            + "     il.taxAmt as TAX_AMT,"
            + "     il.vatCode as VAT_CODE,"
            + "     il.vatPct as VAT_PCT,"
            + "     il.vatAmt as VAT_AMT,"
            + "     il.netPrice as NET_PRICE,"
            + "     iv.vatLabel as VAT_LABEL,"
            + "     iv.salesTaxLabel as SALES_TAX_LABEL,"
            + "     iv.vatRegLabel as VAT_REG_LABEL,"
            + "     iv.transactionCurrencyCd as TRANSACT_CURRENCY_CD,"
            + "     jo.buName as BU_NAME,"
            + "     (select distinct cur.decimalDigits from Currency cur,CEJobContract cejc ,CEInvoice inv" 
            + "     where cejc.transactionCurrency=cur.currencyCd and jc.id = inv.jobOrderContractId and inv.invoiceNumber = ?) as DECIMAL_DIGITS "//to be test
            + "     FROM CEJobContract jc, CEInvoice iv, "
            + "     CEInvoiceLineItem il, CEJobOrder jo "
            + "     WHERE il.invoiceId=iv.id and iv.jobOrderContractId = jc.id " +
            		"and jc.jobOrderNumber=jo.jobNumber and iv.invoiceNumber=? "
            + "     ORDER BY il.vatPct ASC";
        log.debug(hql);

        ArrayList<Object> paramList = new ArrayList<Object>();
        paramList.add(params.get("Invoice"));
        paramList.add(params.get("Invoice"));
        List<Object[]> result =ReportUtil.hibernateJasperDataSourceHelperByHql(hql,paramList,typeMap,fieldNames );
        if (result != null) {
            return new ArrayMapDataSource(new ArrayMap<String, Object>(
                    fieldNames), result, this, params);
        } else {
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

    @Override
    public Object calculateFieldValue(String fieldName, Map<String, Object> params, ArrayMapDataSource source) {
        return source.getCalculatedField(fieldName);
    }
    
    @Override
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params){
        String[] fields = {"NET_PRICE", "VAT_AMT", "TAX_AMT"};
        double[] results = ReportUtil.summariseFields(fields, amds);
        
        amds.addCalculatedField("subtotal", new BigDecimal(String.valueOf(results[0])));
        amds.addCalculatedField("VAT_subtotal", new BigDecimal(String.valueOf(results[1])));
        amds.addCalculatedField("sales_tax_subtotal", new BigDecimal(String.valueOf(results[2])));
    }
}
