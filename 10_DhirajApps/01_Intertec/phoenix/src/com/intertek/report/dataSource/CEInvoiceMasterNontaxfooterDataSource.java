/**
 * 
 */
package com.intertek.report.dataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.log4j.Logger;

import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.util.ArrayMap;
import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.ReportUtil;

/**
 * 
 * @author Patni
 */
public class CEInvoiceMasterNontaxfooterDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(CEInvoiceMasterNontaxfooterDataSource.class);

    private static final String[] fieldNames = { "JOB_NUMBER", "UID20", "TAX_VAT_FLAG", "INVOICE", "BU_NAME", };

    private static final String[] fieldTypes = {};

    public CEInvoiceMasterNontaxfooterDataSource() {
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {

        SearchService searchService = ServiceManager.getSearchService();
        String invocieNo = params.get("Invoice").toString();
        Map<String, String> map = new HashMap<String, String>();
        map.put("invoiceNumber", invocieNo);
        String[] result = null;
        
        if(params.get("InvType")!=null && params.get("InvType").equals("deposit")){ 
        try {
            List<DepositInvoice> invoiceList = (List<DepositInvoice>) searchService.search(DepositInvoice.class, map);
            if (invoiceList != null && invoiceList.size() > 0) {
                DepositInvoice invoice = invoiceList.get(0);
                if(invoice.isTaxVatFlag()){
                    result = new String[] { invoice.getJobContract().getJobOrderNumber(), "", "T", invoice.getInvoiceNumber(),
                                            invoice.getJobContract().getJobOrder().getBuName() };
                }
                else{
                    result = new String[] { invoice.getJobContract().getJobOrderNumber(), "", "F", invoice.getInvoiceNumber(),
                                            invoice.getJobContract().getJobOrder().getBuName() };
                }
                
            }

        }
        
        catch (DaoException e) {
            log.error(e);
        }
        }      
        
        else {//result for invoice report

            try {
                List<CEInvoice> invoiceList = (List<CEInvoice>) searchService.search(CEInvoice.class, map);
                if (invoiceList != null && invoiceList.size() > 0) {
                    CEInvoice invoice = invoiceList.get(0);
                    if (invoice.isTaxVatFlag()) {
                        result = new String[] { invoice.getJobContract().getJobOrderNumber(), "", "T", invoice.getInvoiceNumber(),
                                               invoice.getJobContract().getJobOrder().getBuName() };
                    }
                    else {
                        result = new String[] { invoice.getJobContract().getJobOrderNumber(), "", "F", invoice.getInvoiceNumber(),
                                               invoice.getJobContract().getJobOrder().getBuName() };
                    }

                }

            }

            catch (DaoException e) {
                log.error(e);
            }
        }
        if (result != null) {
            List<Object[]> resultList = new ArrayList<Object[]>();
            resultList.add(result);
            return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), resultList, this, params);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        return getDataSource(reportName, params);
    }
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params) {
        amds.addCalculatedField("DEPOSIT_INVOICE",params.get("deposit_invoice")==null?"":"DEPOSITINVOICE");
        
    }
}
