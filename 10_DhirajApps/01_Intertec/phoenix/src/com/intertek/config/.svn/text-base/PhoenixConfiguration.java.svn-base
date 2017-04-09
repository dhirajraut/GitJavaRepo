/**
 * 
 */
package com.intertek.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * An object that captures all the phoenix configurations
 * @author richard.qin
 */
public class PhoenixConfiguration {
    
    private static PhoenixConfiguration instance;
    public static final String PHOENIX_CONFIGURATION = "/config.properties";
    public static final String FORMAT_PROPERTIES = "/fp.properties";
    public static final String REPORT_RUNNER_CONFIGURATION = "/jasperFillReport.properties";
    
    // holds other properties defined in other properties files
    private Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
    // holds other sub configurations
    private Map<Class<?>, Object> subconfigs = new HashMap<Class<?>, Object>();

    private String phoenixRoot = "/";

    private String invoiceDir;
    
    private String emailHost;
    private int emailPort;
    private String emailUsername;
    private String emailPassword;
    private String emailFrom;
    private String emailTo;
    
    private int webServiceDelay;
    private int webServicePeriod;

    private String webServiceContactUrl;
    private String webServiceCustomerUrl;
    private String webServiceInvoiceUrl;
    private String webServiceConsolidatedInvoiceUrl;

    private String webServiceLimsContactUrl;
    private String webServiceLimsCustomerUrl;
    private String webServiceLimsJobOrderUrl;

    private String webServiceSamUrl;

    private String filePath;
    private String contractFilePath;
    private String jobContractFilePath;
    // directory for test data, optional
    private String testDataPath;
    // directory for reports
    private String reportPath;
    
    private boolean isTesting;
    private int testDataLimit;
    private boolean useOldJasper;
    
    private long lasttimeRefreshed ;
    private String depositInvoiceDir;
    
    private PhoenixConfiguration(){
        Properties prop = new Properties();
        InputStream in;
        try {
            in = this.getClass().getResourceAsStream(PHOENIX_CONFIGURATION);
            prop.load(in);
            in.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot initialize configuration. " + PHOENIX_CONFIGURATION + " not found");
        }
        catch (IOException e){
            throw new RuntimeException("Cannot initialize configuration. Error occured when reading " + PHOENIX_CONFIGURATION);
        }
        
        this.propertiesMap.put(PHOENIX_CONFIGURATION, prop);
        // this property is mainly used for dev and testing purposes
        this.phoenixRoot = prop.getProperty("phoenix.root", "");
        this.invoiceDir = prop.getProperty("invoice.dir", phoenixRoot + "invoice/");
        this.emailHost = prop.getProperty("email.host");
        this.emailPort = Integer.parseInt(prop.getProperty("email.port", "23"));
        this.emailUsername = prop.getProperty("email.username");
        this.emailPassword = prop.getProperty("email.password");
        this.emailFrom = prop.getProperty("email.from");
        this.emailTo = prop.getProperty("email.to");
        this.webServiceDelay = Integer.parseInt(prop.getProperty("outbound.web.service.delay", "6000"));
        this.webServicePeriod = Integer.parseInt(prop.getProperty("outbound.web.service.period", "60000"));
        this.webServiceContactUrl = prop.getProperty("outbound.web.service.contact.url");
        this.webServiceCustomerUrl = prop.getProperty("outbound.web.service.customer.url");
        this.webServiceInvoiceUrl = prop.getProperty("outbound.web.service.invoice.url");
        this.webServiceConsolidatedInvoiceUrl = prop.getProperty("outbound.web.service.consolidated_invoice.url");
        this.webServiceLimsContactUrl = prop.getProperty("outbound.web.service.lims_contact.url");
        this.webServiceLimsCustomerUrl = prop.getProperty("outbound.web.service.lims_customer.url");
        this.webServiceLimsJobOrderUrl = prop.getProperty("outbound.web.service.lims_job_order.url");
        this.webServiceConsolidatedInvoiceUrl = prop.getProperty("outbound.web.service.sam.url");
        this.filePath = prop.getProperty("filepath");
        this.contractFilePath = prop.getProperty("contractfilepath");
        this.jobContractFilePath = prop.getProperty("jobcontractfilepath");
        this.testDataPath = prop.getProperty("testDataPath", phoenixRoot + "report_runner/local_data/");
        this.reportPath = prop.getProperty("reportPath", phoenixRoot + "dist/jasper/");
        this.depositInvoiceDir= prop.getProperty("depositinvoice.dir", phoenixRoot + "depositinvoice/");
        this.isTesting = Boolean.parseBoolean(prop.getProperty("isTesting", "false"));
        this.testDataLimit = Integer.parseInt(prop.getProperty("testDataLimit", "0"));
        
        this.useOldJasper = Boolean.parseBoolean(prop.getProperty("useOldJasper", "false"));
        
        // load configuration for sub components
        this.subconfigs.put(ReportRunnerConfiguration.class, ReportRunnerConfiguration.getConfiguration());
        
        // load other properties
        try {
            in = this.getClass().getResourceAsStream(FORMAT_PROPERTIES);
            prop.load(in);
            this.propertiesMap.put(FORMAT_PROPERTIES, prop);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Initialize configuration error. " + FORMAT_PROPERTIES + " not found");
        }
        catch (IOException e){
            throw new RuntimeException("Initialize configuration error. Error occured when reading " + FORMAT_PROPERTIES);
        }
    }
    
    // get the main phoenix configuration
    public static PhoenixConfiguration getConfiguration(){
        long now = System.currentTimeMillis();
        // give each new instance 5 minutes to live, before the configuration 
        // file is reread.
        if(instance == null || now - instance.lasttimeRefreshed > 300000L){
            instance = new PhoenixConfiguration();
            instance.lasttimeRefreshed = System.currentTimeMillis();
        }
        return instance;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getConfiguration(Class<T> type){
        return (T)instance.subconfigs.get(type);
    }
    
    // get named properties
    public static Properties getProperties(String name){
        return instance.propertiesMap.get(name);
    }
    
    /**
     *  Allow overwrite configuration settings at runtime
     * @param config a Map that contains configuration settings to overwrite
     * @return true if configurations are owerwritten successfully; otherwise, false
     */
    public boolean applyConfiguration(Map<String, ?> config){
        // TODO
        return true;
    }
    
    /**
     *  Allow overwrite configuration settings at runtime for a phoenix component
     * @param type the type of the configuration class for the phoenix component
     * @param config a Map that contains configuration settings to overwrite
     * @return true if configurations are owerwritten successfully; otherwise, false
     */
   public <T> boolean applyConfiguration(Class<T> type, Map<String, ?> config){
        // TODO
        return true;
    }
    
    /**
     * @return the invoice_dir
     */
    public String getInvoiceDir() {
        return invoiceDir;
    }

    /**
     * @param invoice_dir the invoice_dir to set
     */
    public void setInvoiceDir(String invoiceDir) {
        this.invoiceDir = invoiceDir;
    }

    /**
     * @return the emailHost
     */
    public String getEmailHost() {
        return emailHost;
    }

    /**
     * @param emailHost the emailHost to set
     */
    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    /**
     * @return the emailPort
     */
    public int getEmailPort() {
        return emailPort;
    }

    /**
     * @param emailPort the emailPort to set
     */
    public void setEmailPort(int emailPort) {
        this.emailPort = emailPort;
    }

    /**
     * @return the emailUsername
     */
    public String getEmailUsername() {
        return emailUsername;
    }

    /**
     * @param emailUsername the emailUsername to set
     */
    public void setEmailUsername(String emailUsername) {
        this.emailUsername = emailUsername;
    }

    /**
     * @return the emailPassword
     */
    public String getEmailPassword() {
        return emailPassword;
    }

    /**
     * @param emailPassword the emailPassword to set
     */
    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    /**
     * @return the emailFrom
     */
    public String getEmailFrom() {
        return emailFrom;
    }

    /**
     * @param emailFrom the emailFrom to set
     */
    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    /**
     * @return the emailTo
     */
    public String getEmailTo() {
        return emailTo;
    }

    /**
     * @param emailTo the emailTo to set
     */
    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    /**
     * @return the webServiceDelay
     */
    public int getWebServiceDelay() {
        return webServiceDelay;
    }

    /**
     * @param webServiceDelay the webServiceDelay to set
     */
    public void setWebServiceDelay(int webServiceDelay) {
        this.webServiceDelay = webServiceDelay;
    }

    /**
     * @return the webServicePeriod
     */
    public int getWebServicePeriod() {
        return webServicePeriod;
    }

    /**
     * @param webServicePeriod the webServicePeriod to set
     */
    public void setWebServicePeriod(int webServicePeriod) {
        this.webServicePeriod = webServicePeriod;
    }

    /**
     * @return the webServiceContactUrl
     */
    public String getWebServiceContactUrl() {
        return webServiceContactUrl;
    }

    /**
     * @param webServiceContactUrl the webServiceContactUrl to set
     */
    public void setWebServiceContactUrl(String webServiceContactUrl) {
        this.webServiceContactUrl = webServiceContactUrl;
    }

    /**
     * @return the webServiceCustomerUrl
     */
    public String getWebServiceCustomerUrl() {
        return webServiceCustomerUrl;
    }

    /**
     * @param webServiceCustomerUrl the webServiceCustomerUrl to set
     */
    public void setWebServiceCustomerUrl(String webServiceCustomerUrl) {
        this.webServiceCustomerUrl = webServiceCustomerUrl;
    }

    /**
     * @return the webServiceInvoiceUrl
     */
    public String getWebServiceInvoiceUrl() {
        return webServiceInvoiceUrl;
    }

    /**
     * @param webServiceInvoiceUrl the webServiceInvoiceUrl to set
     */
    public void setWebServiceInvoiceUrl(String webServiceInvoiceUrl) {
        this.webServiceInvoiceUrl = webServiceInvoiceUrl;
    }

    /**
     * @return the webServiceConsolidatedInvoiceUrl
     */
    public String getWebServiceConsolidatedInvoiceUrl() {
        return webServiceConsolidatedInvoiceUrl;
    }

    /**
     * @param webServiceConsolidatedInvoiceUrl the webServiceConsolidatedInvoiceUrl to set
     */
    public void setWebServiceConsolidatedInvoiceUrl(String webServiceConsolidatedInvoiceUrl) {
        this.webServiceConsolidatedInvoiceUrl = webServiceConsolidatedInvoiceUrl;
    }

    /**
     * @return the webServiceLimsContactUrl
     */
    public String getWebServiceLimsContactUrl() {
        return webServiceLimsContactUrl;
    }

    /**
     * @param webServiceLimsContactUrl the webServiceLimsContactUrl to set
     */
    public void setWebServiceLimsContactUrl(String webServiceLimsContactUrl) {
        this.webServiceLimsContactUrl = webServiceLimsContactUrl;
    }

    /**
     * @return the webServiceLimsCustomerUrl
     */
    public String getWebServiceLimsCustomerUrl() {
        return webServiceLimsCustomerUrl;
    }

    /**
     * @param webServiceLimsCustomerUrl the webServiceLimsCustomerUrl to set
     */
    public void setWebServiceLimsCustomerUrl(String webServiceLimsCustomerUrl) {
        this.webServiceLimsCustomerUrl = webServiceLimsCustomerUrl;
    }

    /**
     * @return the webServiceLimsJobOrderUrl
     */
    public String getWebServiceLimsJobOrderUrl() {
        return webServiceLimsJobOrderUrl;
    }

    /**
     * @param webServiceLimsJobOrderUrl the webServiceLimsJobOrderUrl to set
     */
    public void setWebServiceLimsJobOrderUrl(String webServiceLimsJobOrderUrl) {
        this.webServiceLimsJobOrderUrl = webServiceLimsJobOrderUrl;
    }

    /**
     * @return the webServiceSamUrl
     */
    public String getWebServiceSamUrl() {
        return webServiceSamUrl;
    }

    /**
     * @param webServiceSamUrl the webServiceSamUrl to set
     */
    public void setWebServiceSamUrl(String webServiceSamUrl) {
        this.webServiceSamUrl = webServiceSamUrl;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the contractFilePath
     */
    public String getContractFilePath() {
        return contractFilePath;
    }

    /**
     * @param contractFilePath the contractFilePath to set
     */
    public void setContractFilePath(String contractFilePath) {
        this.contractFilePath = contractFilePath;
    }

    /**
     * @return the jobContractFilePath
     */
    public String getJobContractFilePath() {
        return jobContractFilePath;
    }

    /**
     * @param jobContractFilePath the jobContractFilePath to set
     */
    public void setJobContractFilePath(String jobContractFilePath) {
        this.jobContractFilePath = jobContractFilePath;
    }
    
    public String getTestDataPath(){
        return this.testDataPath;
    }

    /**
     * @param testDataPath the testDataPath to set
     */
    public void setTestDataPath(String testDataPath) {
        this.testDataPath = testDataPath;
    }
    
    public String getReportPath(){
        return this.reportPath;
    }

    /**
     * @param reportPath the reportPath to set
     */
    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }
    
    public boolean isTesting(){
        return this.isTesting;
    }
    
    public void setTesting(boolean b){
        this.isTesting = b;
    }
    
    public int getTestDataLimit(){
        return this.testDataLimit;
    }
    
    public void setTestDataLimit(int v){
        this.testDataLimit = v;
    }
    
    public boolean isUseOldJasper(){
        return this.useOldJasper;
    }
    
    public void setUseOldJasper(boolean value){
        this.useOldJasper = value;
    }

    public String getDepositInvoiceDir() {
        return depositInvoiceDir;
    }

    public void setDepositInvoiceDir(String depositInvoiceDir) {
        this.depositInvoiceDir = depositInvoiceDir;
    }
}
