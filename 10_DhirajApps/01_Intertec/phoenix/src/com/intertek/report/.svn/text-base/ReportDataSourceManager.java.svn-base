/**
 * 
 */
package com.intertek.report;

import static com.intertek.report.ReportConstants.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.intertek.config.PhoenixConfiguration;
import com.intertek.report.dataSource.*;

/**
 * ReportDataSourceManager is the single point of contact for obtaining appropriate 
 * JRDataSource for JasperReport based Phoenix reports.
 * Two types of data sources are supported: jdbc and csv. A jdbc datasource supports getting
 * over a JDBC connection or a Hibernate connection, while a csv datasource is backed by a csv
 * file on local hard disk.
 * A csv datasource will only be used for testing and development purposes.
 * 
 * @author richard.qin
 */
public class ReportDataSourceManager {
    private static Logger log = Logger.getLogger(ReportDataSourceManager.class);
    
    private static ReportDataSourceManager instance;
    
    private Map<String, JasperDataSource> dsHandlers = new HashMap<String, JasperDataSource>();
    private Map<String, String> testfiles = new HashMap<String, String>();

    private ReportDataSourceManager(){
        // initialize the datasource handlers to be used for report generation
        dsHandlers.put(REPORT_CONSOL_SUMMARY, new ConsolSummaryDataSource());
        dsHandlers.put(REPORT_INVOICE_BATCH, new InvoiceBatchDataSource());
        dsHandlers.put(REPORT_INVOICE_MASTER_NONTAXFOOTER, new InvoiceMasterNontaxfooterDataSource());
        dsHandlers.put(REPORT_INVOICE_MASTER_TAXFOOTER, new InvoiceMasterTaxfooterDataSource());
        dsHandlers.put(REPORT_INVOICE_PREVIEW, new InvoicePreviewDataSource());
        dsHandlers.put(REPORT_INVOICE_PREVIEW_REBILL, new InvoicePreviewRebillDataSource());

        dsHandlers.put(REPORT_CONSOL_INVOICE_TAX_KOR, null);
        dsHandlers.put(REPORT_JOB_ORDER, null);
        dsHandlers.put(REPORT_WEEKLY_SALES_REPORT, new WeeklySalesReportDataSource());
        dsHandlers.put(REPORT_INVOICE_MASTER, null);
        dsHandlers.put(REPORT_INVOICE_MASTER_KOR, null);
        
        // templates for sub reports
        dsHandlers.put(REPORT_CONSOL_INVOICE, new ConsolInvoiceDataSource());
        dsHandlers.put(REPORT_CONSOL_INVOICE_HEADER, new ConsolInvoiceHeaderDataSource());
        dsHandlers.put(REPORT_INVOICE_ADDRESS, new InvoiceAddressDataSource());
        dsHandlers.put(REPORT_INVOICE_ADDRESS_CE, new CEInvoiceAddressDataSource());
        dsHandlers.put(REPORT_CONSOL, new ConsolDataSource());
        dsHandlers.put(REPORT_INVOICE, new InvoiceDataSource());
        dsHandlers.put(REPORT_INVOICE_HEADER, new InvoiceHeaderDataSource());
        dsHandlers.put(REPORT_INVOICE_HEADER_VALUESUB, new InvoiceHeaderValueSubDataSource());
        dsHandlers.put(REPORT_INVOICE_HEADER_VALUESUB_CE, new CEInvoiceHeaderValueSubDataSource());
        dsHandlers.put(REPORT_INVOICE_HEADER_VATCALC, new InvoiceHeaderVatcalcDataSource());
        dsHandlers.put(REPORT_INVOICE_NONTAX_FOOTER, new InvoiceNontaxFooterDataSource());
        dsHandlers.put(REPORT_INVOICE_NOPREBILL, new InvoiceNoprebillDataSource());
        dsHandlers.put(REPORT_INVOICE_POSTALGIRO, new InvoicePostalgiroDataSource());
        dsHandlers.put(REPORT_INVOICE_TAX, new InvoiceTaxDataSource());
        dsHandlers.put(REPORT_INVOICE_TAX_FOOTER, new InvoiceTaxFooterDataSource());
        dsHandlers.put(REPORT_INVOICE_TAX_FOOTER_TAX_RATES, new InvoiceTaxFooterTaxRateDataSource());
        dsHandlers.put(REPORT_INVOICE_TAX_FOOTER_TAX_RATES_CE, new CEInvoiceTaxFooterTaxRateDataSource());
        dsHandlers.put(REPORT_INVOICE_TAX_NOPREBILL, new InvoiceTaxNoprebillDataSource());
        dsHandlers.put(REPORT_INVOICE_TAX_VATLABEL, new InvoiceTaxVatLabelDataSource());
        dsHandlers.put(REPORT_INVOICE_HEADER_SUB, new InvoiceHeaderSubDataSource());
        dsHandlers.put(REPORT_INVOICE_HEADER_SUB_1, new InvoiceHeaderSub1DataSource());
        dsHandlers.put(REPORT_INVOICE_HEADER_SUB_CE, new CEInvoiceHeaderSubDataSource("BILLTO"));
        dsHandlers.put(REPORT_INVOICE_HEADER_SHIP_SUB_CE, new CEInvoiceHeaderSubDataSource("SHIPTO"));
        dsHandlers.put(REPORT_INVOICE_HEADER_SUB_1_CE, new CEInvoiceHeaderSub1DataSource());
        dsHandlers.put(REPORT_JOB_ORDER_CE, new CEJobOrderDataSource());
        //CE Invoice Data Sources
        dsHandlers.put(REPORT_INVOICE_MASTER_NONTAXFOOTER_CE, new CEInvoiceMasterNontaxfooterDataSource());
        dsHandlers.put(REPORT_INVOICE_MASTER_TAXFOOTER_CE, new CEInvoiceMasterNontaxfooterDataSource());
        dsHandlers.put(REPORT_INVOICE_PREVIEW_CE, new CEInvoicePreviewDataSource());
        dsHandlers.put(REPORT_INVOICE_HEADER_CE, new CEInvoiceHeaderDataSource());
        dsHandlers.put(REPORT_INVOICE_NONTAX_FOOTER_CE, new CEInvoiceNontaxFooterDataSource());
        dsHandlers.put(REPORT_INVOICE_TAX_CE, new CEInvoiceTaxDatasource());
        dsHandlers.put(REPORT_INVOICE_TAX_FOOTER_CE, new CEInvoiceTaxFooterDataSource());
        dsHandlers.put(REPORT_INVOICE_CE, new CEInvoiceDataSource());
        dsHandlers.put(REPORT_DEPOSIT_INVOICE_CE, new DepositInvoiceDataSource());
        // simple data source, for testing and integration with iReport designer
        dsHandlers.put(REPORT_GENERIC_CSV, new SimpleCsvDataSource());
                
        // initialize the known test files
        String path = PhoenixConfiguration.getConfiguration().getTestDataPath(); 
        testfiles.put(REPORT_CONSOL_SUMMARY, path + "consol_summary.csv");
        testfiles.put(REPORT_INVOICE_BATCH, path + "invoice_batch.csv");
        testfiles.put(REPORT_INVOICE_MASTER_NONTAXFOOTER, path + "invoice_master_nontaxfooter.csv");
        testfiles.put(REPORT_INVOICE_MASTER_TAXFOOTER, path + "invoice_master_taxfooter.csv");
        testfiles.put(REPORT_INVOICE_PREVIEW, path + "invoice_preview.csv");
        testfiles.put(REPORT_INVOICE_PREVIEW_REBILL, path + "invoice_preview_rebill.csv");

        // for sub reports
        testfiles.put(REPORT_CONSOL_INVOICE, path + "consol_invoice.csv");
        testfiles.put(REPORT_CONSOL_INVOICE_HEADER, path + "consol_invoice_header.csv");
        testfiles.put(REPORT_INVOICE_HEADER_SUB, path + "invoice_header_sub.csv");
        testfiles.put(REPORT_INVOICE_HEADER_SUB_1, path + "invoice_header_sub_1.csv");
        testfiles.put(REPORT_INVOICE_ADDRESS, path + "invoice_address.csv");
        testfiles.put(REPORT_INVOICE_POSTALGIRO, path + "invoice_postalgiro.csv");
        
        testfiles.put(REPORT_CONSOL, path + "consol.csv");
        testfiles.put(REPORT_INVOICE, path + "invoice.csv");
        testfiles.put(REPORT_INVOICE_HEADER, path + "invoice_header.csv");
        testfiles.put(REPORT_INVOICE_HEADER_VALUESUB, path + "invoice_header_valuesub.csv");
        testfiles.put(REPORT_INVOICE_HEADER_VATCALC, path + "invoice_header_vatcalc.csv");
        testfiles.put(REPORT_INVOICE_NONTAX_FOOTER, path + "invoice_nontax_footer.csv");
        testfiles.put(REPORT_INVOICE_NOPREBILL, path + "invoice_noprebill.csv");
        testfiles.put(REPORT_INVOICE_TAX, path + "invoice_tax.csv");
        testfiles.put(REPORT_INVOICE_TAX_FOOTER, path + "invoice_tax_footer.csv");
        testfiles.put(REPORT_INVOICE_TAX_FOOTER_TAX_RATES, path + "invoice_tax_footer_tax_rates.csv");
        testfiles.put(REPORT_INVOICE_TAX_NOPREBILL, path + "invoice_tax_noprebill.csv");
        testfiles.put(REPORT_INVOICE_TAX_VATLABEL, path + "invoice_tax_vatlabel.csv");
        
        testfiles.put(REPORT_CONSOL_INVOICE_TAX_KOR, null);
        testfiles.put(REPORT_JOB_ORDER, null);
        testfiles.put(REPORT_WEEKLY_SALES_REPORT, null /*path + "weekly_sales_report.csv"*/);
        testfiles.put(REPORT_INVOICE_MASTER, null);
        testfiles.put(REPORT_INVOICE_MASTER_KOR, null);
    }
    
    /**
     * Get the singleton ReportDataSourceManager object
     * @return
     */
    public static ReportDataSourceManager getManager(){
        if(instance == null){
            instance = new ReportDataSourceManager();
        }
        return instance;
    }

    /**
     * Get the datasource for a named report using a list of name value pairs. 
     * @param reportName
     * @param keys
     * @param values
     * @param conn
     * @return
     */
    public JRDataSource getReportDataSource(String reportName, String[] keys, Object[] values, Connection conn) {
        Map<String, Object> params = new HashMap<String, Object>();
        for(int k=0; k<keys.length; k++){
            params.put(keys[k], values[k]);
        }
        return getReportDataSource(reportName, params, conn);
    }
    
    /**
     * Get the datasource for a named report. In test mode, a local csv datasource will 
     * be returned, otherwise, a jdbc datasource will be returned.
     * @param reportName
     * @param params
     * @param conn
     * @return
     */
    public JRDataSource getReportDataSource(String reportName, Map<String, Object> params, Connection conn) {
        if(conn == null){
            return getReportDataSource(reportName, params);
        }
        else {
            //for calling CE reports
            reportName = chkCEReports(reportName, params);
            JRDataSource result = null;
            // find the data source handler for the named report
            JasperDataSource ds = this.dsHandlers.get(reportName);
            if(ds != null){
                try{
                    result = ds.getDataSource(reportName, params, conn);
                }
                catch(Exception ex){
                    log.error("Failed to obtain data source for report: " + reportName);
                    log.error("Caused by: " + ex.getClass()+ " " + ex.getCause() + ", " + ex.getMessage());
                }
            }
            else{
                // this is a non recognized report, log an error
                log.error("The report name is not recognized: " + reportName);
            }
            return result;
        }
    }

    /**
     * Get the datasource for a named report, without an existing database connection. 
     * In test mode, only local csv datasource will be returned, Otherwise, a hibernate 
     * connection will be created and used.
     * 
     * @param reportName
     * @param params
     * @return
     */
    public JRDataSource getReportDataSource(String reportName, Map<String, Object> params) {
        //for calling CE reports
        reportName = chkCEReports(reportName, params);
        JRDataSource result = null;
        // find the data source handler for the named report
        JasperDataSource ds = this.dsHandlers.get(reportName);
        log.debug("Get data source for: " + reportName + "; Connection: " + params.get("REPORT_CONNECTION"));
        
        // when developing reports, csv files can be used as temporary datasources. In this case,
        // there is no specific JasperDataSource, instead, a generic SimpleDataSource will be used.
        // This is the scenario when iReport uses SimpleDataSourceProvider to access some csv files as 
        // test data sources.
        if(ds == null && isTesting(params)){ // SimpleDataSourceProvide set the testing flag
            ds = this.dsHandlers.get(REPORT_GENERIC_CSV);
        }
        if(ds != null){
            try{
                // depends on if it's in the test mode, return an appropriate data source
                if(!isTesting(params)){
                    result = ds.getDataSource(reportName, params);
                }
                else{
                    String testfile = getTestFile(reportName);
                    result = ds.getDataSource(reportName, params, testfile);
                }
            }
            catch(Exception ex){
                log.error("Failed to obtain data source for report: " + reportName);
                log.error("Caused by: " + ex.getClass().getName() + " " + ex.getMessage());
            }
        }
        else{
            // this is a non recognized report, log an error
            log.error("The report name is not recognized: " + reportName);
        }
        return result;
    }
    
    public String getTestFile(String reportName){
        return testfiles.get(reportName);
    }
    
    // do a quick test to determine whether we are in the test mode
    private boolean isTesting(Map<String, ?> params){
        if(params != null){
            Boolean testing = (Boolean)params.get("Is_Testing");
            if(testing != null){
                return testing.booleanValue();
            }
        }
        PhoenixConfiguration conf = PhoenixConfiguration.getConfiguration();
        return conf.isTesting();
    }

    //appending CE for CEReport invoice data sources
    private String chkCEReports(String reportName, Map<String, Object> params) {
        if (params.get("JobType") != null && params.get("JobType").toString().equalsIgnoreCase("CE") && params.get("InvType") != null
            && params.get("InvType").toString().equalsIgnoreCase("deposit") && reportName.equals("invoice")) {
            reportName += "_deposit_CE";
        }
        else if (params.get("JobType") != null && params.get("JobType").toString().equalsIgnoreCase("CE")) {
            reportName += "_CE";
        }
        return reportName;
    }

    // quick test
    public static void main(String[] args){
        PropertyConfigurator.configure("conf/log4j.properties");
        ReportDataSourceManager m = new ReportDataSourceManager();
        // load report
        JasperReport report = null;
        try {
            String reportPath = PhoenixConfiguration.getConfiguration().getReportPath();
            report = (JasperReport) JRLoader.loadObjectFromLocation(reportPath + "sample_report.jasper");
        }
        catch (JRException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        JRDataSource ds = m.getReportDataSource(report.getName(), params);
        try{
            if(ds.next()){
                log.info("ok");
            }
            else{
                log.warn("No records found.");
            }
        }
        catch(Exception e){
            log.error(e);
        }
    }
}
