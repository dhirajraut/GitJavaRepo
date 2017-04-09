/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.intertek.config.PhoenixConfiguration;
import com.intertek.entity.JobContract;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.invoice.InvalidInvoiceOperationException;
import com.intertek.phoenix.util.HibernateUtil;
import com.intertek.report.JasperFillReport;
import com.intertek.report.ReportConstants;
import com.intertek.util.DateUtil;

/**
 * 
 * @author richard.qin
 */
public class ReportServiceImpl implements ReportService {
    /**
     * @see com.intertek.phoenix.report.ReportService#generateInvoicePdf(com.intertek.phoenix.entity.CEInvoice)
     */
    @Override
    public PDF generateInvoicePdf(CEInvoice invoice) throws InvalidInvoiceOperationException {
        PDF pdf = null;

        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String reportName = ReportConstants.REPORT_INVOICE_MASTER_NONTAXFOOTER;
            if(invoice.isTaxVatFlag()){
             reportName = ReportConstants.REPORT_INVOICE_MASTER_TAXFOOTER;
            }
            String reportDir = PhoenixConfiguration.getConfiguration().getReportPath();
            String invoiceDir = PhoenixConfiguration.getConfiguration().getInvoiceDir();
            String pdfFileName="";
            String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
            File f=new File(invoiceDir.concat(dateFolder));
            if(!f.exists()){
                f.mkdirs();  
            }
              
            invoice.calculate();
            HibernateUtil.newTransaction();
            if(invoice.getStatus().getDescription().equalsIgnoreCase("Credited"))
            {
                pdfFileName =dateFolder+"/"+ invoice.getInvoiceNumber() + "CR_Invoice"  + ".pdf";
                //HibernateUtil.newTransaction();
            }
            else
            {
                pdfFileName = dateFolder+"/"+ invoice.getInvoiceNumber() + "_Invoice" + ".pdf";    
            }
            
            InputStream is = new FileInputStream(reportDir + reportName + ".jasper");
            paramMap.put("Invoice", invoice.getInvoiceNumber());
            paramMap.put("dirName", reportDir);
            paramMap.put("Job_Number", invoice.getJobContract().getJobOrderNumber());
            paramMap.put("dirName", reportDir);
            paramMap.put("JobType", "CE");
            paramMap.put("NoPrebillFlag", "F");
            JasperPrint jasperPrint = JasperFillReport.fillReport(reportName, is, paramMap, null);
            JasperExportManager.exportReportToPdfFile(jasperPrint, invoiceDir+pdfFileName);
            pdf = new PDF();
            pdf.setFileName(pdfFileName);
        }
        catch (Exception e) {
            throw new InvalidInvoiceOperationException(e.getMessage(),e);
        }
        return pdf;
    }

    /**
     * @see com.intertek.phoenix.report.ReportService#generateInvoicePreviewPdf(com.intertek.phoenix.entity.CEInvoice)
     */
    @Override
    public byte[] generateInvoicePreviewPdf(CEInvoice invoice) throws InvalidInvoiceOperationException{

        byte[] byteArr=null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String reportName = ReportConstants.REPORT_INVOICE_PREVIEW;
            String reportDir = PhoenixConfiguration.getConfiguration().getReportPath();
            String invoiceDir = PhoenixConfiguration.getConfiguration().getInvoiceDir();
            String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
            File f=new File(invoiceDir.concat(dateFolder));
            if(!f.exists()){
                f.mkdir();  
            }
            InputStream is = new FileInputStream(reportDir + reportName + ".jasper");
            paramMap.put("Invoice", invoice.getInvoiceNumber());
            paramMap.put("dirName", reportDir);
            paramMap.put("JobType", "CE");
            JasperPrint jasperPrint = JasperFillReport.fillReport(reportName, is, paramMap, null);
            byteArr=JasperExportManager.exportReportToPdf(jasperPrint);
            }
        catch (Exception e) {
            throw new InvalidInvoiceOperationException(e.getMessage(),e);
        }
        return byteArr;
    }

    /**
     * @throws PhoenixException
     * @see com.intertek.phoenix.report.ReportService#generateJobOrderPdf(com.intertek.phoenix.entity.CEJobOrder)
     */
    @Override
    public byte[] generateJobOrderPdf(CEJobOrder jo) throws PhoenixException {
        byte[] byteArr=null;

        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String reportName = ReportConstants.REPORT_JOB_ORDER_CE;
            String reportDir = PhoenixConfiguration.getConfiguration().getReportPath();
            String invoiceDir = PhoenixConfiguration.getConfiguration().getInvoiceDir();
            String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
            File f=new File(invoiceDir.concat(dateFolder));
            if(!f.exists()){
                f.mkdir();  
            }
            InputStream is = new FileInputStream(reportDir + reportName + ".jasper");
            paramMap.put("job_Number", jo.getJobNumber());
            paramMap.put("dirName", reportDir);
            JasperPrint jasperPrint = JasperFillReport.fillReport(reportName, is, paramMap, null);
            byteArr= JasperExportManager.exportReportToPdf(jasperPrint);
            
        }
        catch (Exception e) {
            throw new PhoenixException(e);
        }
        return byteArr;
    }

    /**
     * @see com.intertek.phoenix.report.ReportService#generateInvoicePdf(com.intertek.phoenix.entity.DepositInvoice)
     */
    @Override
    public PDF generateDepositInvoicePdf(DepositInvoice invoice) throws InvalidInvoiceOperationException{
        PDF pdf = null;

        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String reportName = ReportConstants.REPORT_INVOICE_MASTER_NONTAXFOOTER;
            String reportDir = PhoenixConfiguration.getConfiguration().getReportPath();
            //String depositInvoiceDir = PhoenixConfiguration.getConfiguration().getDepositInvoiceDir();
            String invoiceDir = PhoenixConfiguration.getConfiguration().getInvoiceDir();
            String pdfFileName="";
            String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
            String depositInvoiceDir =invoiceDir+"DepositInvoice/"+dateFolder;
            File f=new File(depositInvoiceDir);
            if(!f.exists()){
                f.mkdirs();
            }
                pdfFileName = dateFolder+"/"+ invoice.getInvoiceNumber() + "Deposit_Invoice" + ".pdf";  
                
            InputStream is = new FileInputStream(reportDir + reportName + ".jasper");
            paramMap.put("Invoice", invoice.getInvoiceNumber());
            paramMap.put("dirName", reportDir);
            paramMap.put("Job_Number", invoice.getJobContract().getJobOrderNumber());
            paramMap.put("dirName", reportDir);
            paramMap.put("JobType", "CE");
            paramMap.put("NoPrebillFlag", "F");
            paramMap.put("InvType", "deposit");
            //paramMap.put("deposit_invoice","DEPOSITINVOICE");
            JasperPrint jasperPrint = JasperFillReport.fillReport(reportName, is, paramMap, null);
            JasperExportManager.exportReportToPdfFile(jasperPrint,invoiceDir+"DepositInvoice/"+pdfFileName);
            pdf = new PDF();
            pdf.setFileName(pdfFileName);
        }
        catch (Exception e) {
            throw new InvalidInvoiceOperationException(e.getMessage(),e);
        }
        return pdf;
    }

    /**
     * @see com.intertek.phoenix.report.ReportService#generateSampleTrackingReport(com.intertek.entity.JobContract)
     */
    @Override
    public PDF generateSampleTrackingReport(JobContract jobContract) {
        // TODO Auto-generated method stub
        return null;
    }

}
