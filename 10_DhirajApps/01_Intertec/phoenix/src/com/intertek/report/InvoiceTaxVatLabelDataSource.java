/**
 * 
 */
package com.intertek.report;

import java.sql.Connection;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * This data source implementation replace the invoice_tax_vatlabelcalc.jrxml. This data source
 * does not have a companion jasper report. Instead, it is used as a reference data source for
 * other reports.
 * @author richard.qin
 */
public class InvoiceTaxVatLabelDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceTaxVatLabelDataSource.class);
    private static final String[] fieldNames = {
        "COUNTRY_CODE",
        "STATE",
        "VAT_LABEL",
        "SALES_TAX_LABEL",
        "VAT_REG_LABEL"
    };
    
    private static final String[] fieldTypes = {};

    public InvoiceTaxVatLabelDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
        super.isReferenceTable = true;
        super.keyFieldNames = new String[]{"COUNTRY_CODE", "STATE"};
    }
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        String sql = "SELECT "
            + " COUNTRY_CODE,"
            + " STATE,"
            + " VAT_LABEL,"
            + " SALES_TAX_LABEL,"
            + " VAT_REG_LABEL"
            + " FROM TAX_LABELS";
        log.debug(sql);
        
        return ReportUtil.jdbcJasperDataSourceHelper(
                        reportName, con, sql, new Pair<?, ?>[]{}, fieldNames, typeMap, this, params);
    }

}
