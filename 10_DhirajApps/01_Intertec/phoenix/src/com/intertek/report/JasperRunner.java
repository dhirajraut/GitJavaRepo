package com.intertek.report;

import static com.intertek.report.ReportConstants.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import au.com.bytecode.opencsv.CSVReader;

import com.intertek.config.PhoenixConfiguration;
import com.intertek.exception.ServiceException;
import com.intertek.phoenix.util.ArrayMap;

public class JasperRunner{
    private static Logger log = Logger.getLogger(JasperRunner.class);

    // the original function is refactored to call the new override with a blank report name
    @SuppressWarnings("unchecked")
    public static boolean generateReport(InputStream is, Map paramMap, Connection connection, String dirName,  String pdfName){
        return generateReport(null, is, paramMap, connection, dirName, pdfName);
    }
    
	@SuppressWarnings("unchecked")
    public static boolean generateReport(String reportName, InputStream is, Map paramMap, Connection connection, String dirName,  String pdfName){
		boolean result = false;
		if(is ==null || dirName == null || pdfName==null){
		    // RQ: It is ok to have a null connection; if so, 
		    // a new connection will be used, or a local datastore will be used
			return result;
		}
		
		PhoenixConfiguration config = PhoenixConfiguration.getConfiguration();
		
		JasperPrint jasperPrint = null;
		try {
		    log.debug("Generating report: " + pdfName);
		    if(reportName != null && !config.isUseOldJasper()){
		        // the new report solution requires reportName
		        jasperPrint=JasperFillReport.fillReport(reportName, is, paramMap, connection);
		    }
		    else if(connection != null){
		        // invoke the old method to be backward compatable
                jasperPrint=JasperFillReport.fillReport(is, paramMap, connection);
		    }
		    // else, there is an error
		    
			if(jasperPrint==null){
	            if(connection != null){
	              try { connection.close(); } catch(Exception e) {}
	            }
	            if(is != null)
						is.close();
	        	throw new ServiceException("REPORT_GENERATION_ERROR", new Object[] {"Fail to Fill Report"}, null);
	        }
	        
	        JasperExportManager.exportReportToPdfFile(
	          jasperPrint,
	          dirName.concat(pdfName)
	        );
	        
	        if(is != null)
	          is.close();
	
	        FileInputStream generatedPDF = new FileInputStream(dirName.concat(pdfName));
	        if(generatedPDF != null){
	          int bytes = generatedPDF.available();
	          log.debug("available bytes" +bytes);
	          generatedPDF.close();
	          result = true;
	        }
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("REPORT_GENERATION_ERROR", new Object[] {"Fail to Fill Report"}, e);
		}
        return result;
	}
	
    // A simple test configuration object
    class ReportTestConfig {
        List<Pair<String, Map<String, Object>>> reports;
        boolean useLocal;
        boolean useJdbc;
        // extra parameters for load testing
        String paramsFile;
        boolean validatePdf;
        String reportPath;
        String oldReportPath;
        String templatePath;
        String oldTemplatePath;
        // automatically generate test parameters from database
        boolean autoParams;
        boolean quickTest;
        boolean batchTest;
        
        String dburl;
        String dbdriver;
        String dbuser;
        String dbpassword;
        
        public ReportTestConfig(String file){
            this.reports = new ArrayList<Pair<String, Map<String, Object>>>();
            loadConfiguration(file);
        }
                
        public ReportTestConfig(String[] args){
            /**
             * if testfile="testfile", run "testfile"
             * else if report="reportname,reportname" parametername="parametervalue" ..., run "reportname" with parameters
             * else and by default, run JasperRunner.config
             * datasourcetype=local can always be used, if not, the jdbc settings in config.properties will be used.
             * reportdir="pathname" can always be used, if not, the default setting in JasperRunner.properties will be used.
             * paramfile="filename" can always be used. it must be in csv format that contains a list of input parameters
             * 
             */
            // TODO
        }
        
        private void loadConfiguration(String file){
            PhoenixConfiguration phxConf = PhoenixConfiguration.getConfiguration();
            
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(file));
                String line = in.readLine();
                String currentReport = null;
                Map<String, Object> currentParams = null;
                while(line != null){
                    if(line.trim().length() == 0){
                        line = in.readLine();
                        continue;
                    }
                    else if(line.startsWith("#")){
                        // this is a comment line
                        line = in.readLine();
                        continue;
                    }
                    else if(line.startsWith("<")){
                        if(currentReport != null){
                            throw new RuntimeException("Invalid Report Configuration");
                        }
                        // this is a new report block
                        int pos = line.indexOf("=");
                        if(pos >= 0){
                            currentReport = line.substring(pos+1);
                            currentParams = new HashMap<String, Object>();
                        }
                        else{
                            throw new RuntimeException("Invalid Report Configuration: " + line);
                        }
                    }
                    else if(line.startsWith(">")){
                        // this is the end of the current report block
                        // currentParams.put("dirName", reportTemplatePath);
                        
                        // a single conf block may be applicable to multiple reports
                        String[] reportNames = currentReport.split(",");
                        for(String reportName: reportNames){
                            String name = reportName.trim();
                            if(name.length() > 0){
                                reports.add(new Pair<String, Map<String, Object>>(name, currentParams));
                            }
                        }
                        
                        currentReport = null;
                        currentParams = null;
                    }
                    else {
                        int index = line.indexOf("=");
                        String key = line.substring(0, index).trim();
                        String value = line.substring(index+1).trim();
                        if(currentReport != null){
                            currentParams.put(key, value);
                        }
                        else{
                            if(key.equals("datasource")){
                                if(value.endsWith("local")){
                                    this.useLocal = true;
                                    // mark the test mode in PhoenixConfiguration
                                    phxConf.setTesting(true);
                                }
                                else if(value.equals("jdbc")){
                                    this.useJdbc = true;
                                }
                            }
                            else if(key.equals("validatePdf")){
                                this.validatePdf = Boolean.parseBoolean(value);
                            }
                            else if(key.equals("reportPath")){
                                this.reportPath = value;
                            }
                            else if(key.equals("oldReportPath")){
                                this.oldReportPath = value;
                            }
                            else if(key.equals("paramsFile")){
                                this.paramsFile = value;
                            }
                            else if(key.equals("templatePath")){
                                this.templatePath = value;
                            }
                            else if(key.equals("oldTemplatePath")){
                                this.oldTemplatePath = value;
                            }
                            else if(key.equals("autoParameters")){
                                this.autoParams = Boolean.parseBoolean(value);
                            }
                            else if(key.equals("quickTest")){
                                this.quickTest = Boolean.parseBoolean(value);
                            }
                            else if(key.equals("batchTest")){
                                this.batchTest = Boolean.parseBoolean(value);
                            }
                            else if(key.equals("db.url")){
                                this.dburl = value;
                            }
                            else if(key.equals("db.driver")){
                                this.dbdriver = value;
                            }
                            else if(key.equals("db.user")){
                                this.dbuser = value;
                            }
                            else if(key.equals("db.password")){
                                this.dbpassword = value;
                            }
                        }
                    }
                    line = in.readLine();
                }
            }
            catch (FileNotFoundException e) {
                log.error("File not found: " + file);
            }
            catch (IOException e) {
                log.error(e);
            }
            finally{
                if(in != null){
                    try {
                        in.close();
                    }
                    catch (IOException ignore) {
                    }
                }
            }
        }
    } 
    
	private void testLocal(String reportName, String templatePath, String reportPath, Map<String, Object> params){
	    FileInputStream is = null;
        try{
            try {
                is = new FileInputStream(templatePath + reportName + ".jasper");
            }
            catch (FileNotFoundException e) {
                log.warn("File not found: " + reportName + ".jasper");
            }

            long ts = System.currentTimeMillis();
            generateReport(reportName, is, params, null, reportPath, reportName + ts + "_local.pdf");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            try {
                is.close();
            }
            catch (IOException ignore) {
            }
        }
	}
    
    private void testJdbc(String reportName, ReportTestConfig conf, String templatePath, String reportPath, Map<String, Object> params){
        testJdbc(reportName, conf, templatePath, reportPath, params, false);
    }
    
    private void testJdbc(String reportName, ReportTestConfig conf, String templatePath, String reportPath, Map<String, Object> params, boolean old){
	    java.sql.Connection conn = null;
	    try{
    	    Class.forName(conf.dbdriver);
            String connStr = conf.dburl;
            conn = DriverManager.getConnection(connStr, conf.dbuser, conf.dbpassword);
    
    	    FileInputStream is = null;
            try {
                is = new FileInputStream(conf.templatePath + reportName + ".jasper");
            }
            catch (FileNotFoundException e) {
                log.error("File not found: " + reportName + ".jasper");
            }
    
            long ts = System.currentTimeMillis();
            if(old){
                // for backward compatability
                generateReport(is, params, conn, conf.oldReportPath, reportName + ts + ".pdf");
            }
            else{
                generateReport(reportName, is, params, conn, conf.reportPath, reportName + ts + "_jdbc.pdf");
            }
	    }
	    catch(Exception ex){
	        ex.printStackTrace();
	    }
	    finally{
	        if(conn != null){
    	        try {
                    conn.close();
                }
                catch (SQLException ignore){
                }
	        }
	    }
	}
	
	// do a quick test to all testable reports
	private boolean quickTest(ReportTestConfig conf){
	    String[] reports = {
	        REPORT_CONSOL_SUMMARY,
//	        REPORT_INVOICE_BATCH,
	        REPORT_INVOICE_MASTER_NONTAXFOOTER,
	        REPORT_INVOICE_MASTER_TAXFOOTER,
	        REPORT_INVOICE_PREVIEW,
	        REPORT_INVOICE_PREVIEW_REBILL,

	        // sub reports
	        REPORT_CONSOL,
	        REPORT_CONSOL_INVOICE,
	        REPORT_CONSOL_INVOICE_HEADER,
	        REPORT_INVOICE,
	        REPORT_INVOICE_HEADER,
	        REPORT_INVOICE_HEADER_VALUESUB,
	        REPORT_INVOICE_NONTAX_FOOTER,
	        REPORT_INVOICE_NOPREBILL,
	        REPORT_INVOICE_POSTALGIRO,
	        REPORT_INVOICE_TAX,
	        REPORT_INVOICE_TAX_FOOTER,
	        REPORT_INVOICE_TAX_FOOTER_TAX_RATES,
	        REPORT_INVOICE_TAX_NOPREBILL,
	    };
	    
        try{
            Map<String, Object> params = getSimpleParams(conf);
            for(String reportName: reports){
                System.out.println("");
                log.info("Creating report " + reportName);
                if(conf.useLocal){
                    testLocal(reportName, conf.templatePath, conf.reportPath, params);
                }
                if(conf.useJdbc){
                    testJdbc(reportName, conf, conf.templatePath, conf.reportPath, params);
                }
            }
        }
        catch(Throwable t){
            return false;
        }
        
        return true;
	}
	
    @SuppressWarnings("unchecked")
    private ArrayMapDataSource loadParamsList(String path){
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(path), ',');
            List<Object[]> csvdata = reader.readAll();
            
            String[] fieldNames = (String[])csvdata.get(0);
            ArrayMapDataSource ds = new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), csvdata, null, null);
            // spip the first record before return
            ds.next();
            return ds;
        } 
        catch (FileNotFoundException e) {
            log.info("Parameters file not found: " + path);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (JRException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(reader != null){
                    reader.close();
                }
            }
            catch (IOException ignore) {
            }
        }
        return null;
    }
    
    private ArrayMapDataSource loadParamsListFromDB(ReportTestConfig conf){      
        // a quick and dirty way to establish some testing input parameters
        String sql = "SELECT "
                   + " JOB_ORDERS.BU_NAME as BuName,"
                   + " JOB_ORDERS.JOB_NUMBER as Job_Number,"
                   + " JOB_CONTRACTS.UID20 as uid20,"
                   + " JOB_CONTRACTS.CUST_CODE as CustCode,"
                   + " JOB_CONTRACT_INVOICE.INVOICE as Invoice,"
                   + " JOB_CONTRACT_INVOICE.JOB_CONTRACT_INVOICE_ID as Invoice_Id,"
                   + " JOB_CONTRACT_INVOICE.INVOICE_DATE,"
                   + " CONSL_INV.CONSOL_INVOICE as ConsolInvoice"
                   + " FROM "
                   + " JOB_ORDERS, JOB_CONTRACTS, JOB_CONTRACT_INVOICE, CONSL_INV"
                   + " WHERE JOB_ORDERS.JOB_NUMBER = JOB_CONTRACTS.JOB_NUMBER"
                   + " AND   JOB_CONTRACTS.JOB_CONTRACT_ID = JOB_CONTRACT_INVOICE.JOB_CONTRACT_ID"
                   + " AND   JOB_CONTRACT_INVOICE.CONSOL_INVOICE = CONSL_INV.CONSOL_INVOICE"
                   + " order by    JOB_CONTRACT_INVOICE.INVOICE_DATE desc";
        String[] fieldNames = {
            "Job_Number",
            "BuName",
            "uid20",
            "CustCode",
            "Invoice",
            "Invoice_Id",
            "ConsolInvoice",
        };
        java.sql.Connection conn = null;
        try{
            Class.forName(conf.dbdriver);
            String connStr = conf.dburl;
            conn = DriverManager.getConnection(connStr, conf.dbuser, conf.dbpassword);
            ArrayMapDataSource ds = (ArrayMapDataSource)ReportUtil.jdbcJasperDataSourceHelper(
                            "", conn, sql, new Pair<?, ?>[]{}, 
                            fieldNames, new HashMap<String, FieldType>(), null, null);
            return ds;
        }
        catch(Exception e){
            log.error(e);
        }
        finally{
            if(conn != null){
                try {
                    conn.close();
                }
                catch (SQLException ignore) {
                }
            }
        }
        return null;
    }
    
    // test a single report, using either local or jdbc datasource
	private boolean testReport(String reportName, ReportTestConfig config, Map<String, Object> params){
        // the parameter "NoPrebillFlag" required by is not loaded automatically
        // add a random value here
        Random r = new Random();
        String flag = r.nextBoolean() ? "T" : "F";
        params.put("NoPrebillFlag", flag);
	    
        if(config.useLocal){
            if(config.templatePath != null){
                params.put("dirName", config.templatePath);
                testLocal(reportName, config.templatePath, config.reportPath, params);
            }
            // old report cannot use local data source
        }
        else if (config.useJdbc){
            if(config.templatePath != null){
                params.put("dirName", config.templatePath);
                testJdbc(reportName, config, config.templatePath, config.reportPath, params);
            }
            if(config.oldTemplatePath != null){
                params.put("dirName", config.oldTemplatePath);
                testJdbc(reportName, config, config.oldTemplatePath, config.oldReportPath, params, true);
            }
        }
	    
	    return true;
	}
	
	// the main test function
	private boolean batchTest(ReportTestConfig config){
        int count = 0;
        ArrayMapDataSource paramsDs = null;
        log.info("Preparing input parameters...");
        if(config.autoParams){
            paramsDs = loadParamsListFromDB(config);
        }
        else if(config.paramsFile != null){
            paramsDs = loadParamsList(config.paramsFile);
        }
       
        try{
            long start = System.currentTimeMillis();
            log.info("Begin to generate reports...");
            // if some params are provided for bulk execution, run each report with each params set
            if(paramsDs != null){
                Map<String, Object> params = new HashMap<String, Object>();
                while(paramsDs.next()){
                    for(String name: paramsDs.getFieldNames()){
                        params.put(name, paramsDs.getFieldValue(name));
                    }
            	    for(Pair<String, Map<String, Object>> pair: config.reports){
            	        String reportName = pair.key();
            	        testReport(reportName, config, params);
            	    }
            	    // count the number of reports generated in 100's
                    count++;
//                    if(count > 100){
                        log.info(count + " set of reports generated");
//                    }
                }
        	}
            // otherwise, run all the reports specified.
        	else{
        	    count++;
        	    for(Pair<String, Map<String, Object>> pair: config.reports){
    	            String reportName = pair.key();
    	            testReport(reportName, config, pair.value());
        	    }
        	}
            
            long end = System.currentTimeMillis();
            log.info(count + " reports generated in " + (end - start) + " seconds.");
        }
        catch (Throwable e){
            log.error("Report generation failed.");
            log.error(e);
            return false;
        }
        return true;
    }
	
	private static Map<String, Object> getSimpleParams(ReportTestConfig conf){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BU_NAME, "UK001");
        params.put(CUSTOMER_CODE, "3941");
        params.put(CONSOL_INVOICE, "CON0002038");
        params.put(JOB_NUMBER, "US230-0037391");
        params.put(UID20, "1221590");
        params.put(INVOICE, "1000000127");
        //params.put(INVOICE_ID, "1383");
        params.put(INVOICE_ID, "860559");
        // needed to invoice_batch
        params.put("invoice_id_list", "1383, 1380, 1376, 1468");
        
        params.put("BANK_CODE", "ANZ");
        params.put("NoPrebillFlag", "F");
        params.put(DIR_NAME, conf.templatePath);
        
        // need for weekly sales report
        params.put("business_unit", "USA01");
        params.put("operating_unit", "US320");
        params.put("as_of_dt", new Timestamp(Date.valueOf("2008-12-12").getTime()));
        params.put("start_dt", new Timestamp(Date.valueOf("2008-10-10").getTime()));
        params.put("dirName", "C:/Projects/phoenix_trunk/dist/jasper/");
        params.put("currency", "USD");
        return params;
	}
	
	public static void main(String[] args){
        PropertyConfigurator.configure("conf/log4j.properties");
        JasperRunner runner = new JasperRunner();
        ReportTestConfig config = null;
        
        if(args.length == 0){
            config = runner.new ReportTestConfig("conf/JasperRunner.config");
        }
        else if(args.length == 1 && args[0].startsWith("configfile=")){
            String filename = args[0].substring("configfile=".length());
            config = runner.new ReportTestConfig(filename);
        }
        else{
            config = runner.new ReportTestConfig(args[0]);
        }
	    
        // put it in the testing mode
        PhoenixConfiguration phxconf = PhoenixConfiguration.getConfiguration();
        phxconf.setTesting(true);
        phxconf.setTestDataLimit(100);
        
        if(config.quickTest){
            log.info("Performing a quick test");
    	    runner.quickTest(config);
        }
        
        if(config.batchTest){
    	    log.info("Performing batch tests");
    	    runner.batchTest(config);
        }
        
        // test single report
//      runner.testLocal(REPORT_CONSOL, config.templatePath, config.reportPath, getSimpleParams(config));
//      runner.testJdbc(REPORT_WEEKLY_SALES_REPORT, config, config.templatePath, config.reportPath, getSimpleParams(config));
      
	}
}
