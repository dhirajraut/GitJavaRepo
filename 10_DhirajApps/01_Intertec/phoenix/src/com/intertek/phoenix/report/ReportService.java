/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.report;

import com.intertek.entity.JobContract;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.invoice.InvalidInvoiceOperationException;

/**
 * 
 * @author richard.qin
 */
public interface ReportService {
    
    /**
     * Generate an invoice PDF file.
     * @param invoice
     * @return
     * @throws  
     */
    public PDF generateInvoicePdf(CEInvoice invoice) throws InvalidInvoiceOperationException;
    
    /**
     * Generate an invoice preview pdf file from a draft invoice
     * @param invoice
     * @return
     */
    public byte[] generateInvoicePreviewPdf(CEInvoice invoice) throws InvalidInvoiceOperationException;
    
    /**
     * Collect job order information for printing. Depends on the user privilege, not
     * every user is able to see the same Job Order information.
     * @param jobOrder
     * @return
     * TODO the exact type of the return object is TBD
     */
    public byte[] generateJobOrderPdf(CEJobOrder jo) throws PhoenixException;

    /**
     * Generate a deposit invoice PDF file
     * @param invoice
     * @return
     */
    public PDF generateDepositInvoicePdf(DepositInvoice invoice) throws InvalidInvoiceOperationException;
    
    /**
     * 
     * @return
     */
    public PDF generateSampleTrackingReport(JobContract jobContract);
}
