/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.invoice;

import java.util.List;
import java.util.Set;

import com.intertek.entity.BusinessUnit;
import com.intertek.entity.User;
import com.intertek.entity.JobContractInvoice;
import com.intertek.phoenix.BaseService;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.entity.BillingEventItem;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.ConsolidatedInvoice;
import com.intertek.phoenix.entity.CreditInvoice;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.InvoiceFile;

/**
 * This interface defines the Invoice related business functionalities within
 * the Phoenix system.
 * 
 * @author richard.qin
 */
public interface InvoiceService extends BaseService{
    /**
     * Create a draft invoice for the current jobContract for preview.
     * <p>
     * All the information about the billable JOLIs are retrieved from the
     * jobContract. That is, all the JOLIs in the given jobContract that are billable
     * at the current time will be billed, and one invoice line will be
     * generated for one billable JOLI. The billing amount in the invoice line
     * will be set to the amount-to-be-billed of the matching order line item.
     * If the order line item's amount-to-be-billed is zero, then this line item
     * will not be included in this billing event.
     * <p>
     * The customer for the invoice is retrieved from the jobOrder.
     * <p>
     * Check the javadoc for JOLI to the details about the billable line items.
     * 
     * @param jobOrder
     * @return
     */
    public CEInvoice previewInvoice(CEJobContract jobContract) throws InvalidInvoiceOperationException;

    /**
     * Create a draft invoice for the list of JOLIs for preview.
     * <p>
     * This method is mostly the same as
     * <code>DraftInvoice previewInvoice(CEJobOrder jobOrder)</code> except that
     * instead of automatically include all the billable items in job order upto
     * the current time, once a specified list of JOLIs will be included for
     * invoicing. 
     * 
     * @param jolis
     * @return
     * @throws InvalidInvoiceOperationException
     */
    public CEInvoice previewInvoice(CEJobContract jobContract, List<CEJobOrderLineItem> jolis) throws InvalidInvoiceOperationException;

    /**
     * Create a draft deposit invoice for the JobContract.
     * @param jobContract
     * @return
     * @throws InvalidInvoiceOperationException
     */
    public InvoiceFile previewDepositInvoice(DepositInvoice invoice) throws InvalidInvoiceOperationException;
    
    /**
     * Create an invoice for a job order. Certain rule may be applicable before
     * an invoice can be generated.
     * <p>
     * All the information about the billable JOLIs are retrieved from the
     * jobContract. That is, all the JOLIs in the given jobContract that are billable
     * at the current time will be billed, and one invoice line will be
     * generated for one billable JOLI. The billing amount in the invoice line
     * will be set to the amount-to-be-billed of the matching order line item.
     * If the order line item's amount-to-be-billed is zero, then this line item
     * will not be included in this invoice.
     * <p>
     * The customer for the invoice is retrieved from the jobContract.
     * <p>
     * Check the javadoc for JOLI to the details about the billable line items.
     * 
     * @param invoice
     * @return
     */
    public CEInvoice createInvoice(CEJobContract jobContract) throws InvalidInvoiceOperationException;

    /**
     * Create an invoice for a specific list of JOLIs for a given user.
     * Certain rule may be applicable before an invoice can be generated.
     * <p>
     * This method is mostly the same as
     * <code>CEInvoice generateInvoice(CEJobContract jobContract)</code> except that
     * the list of JOLIs are not derived from a jobOrder, but explicitly given.
     * 
     * @param jobOrder
     * @param customer
     * @return
     * @throws InvalidInvoiceOperationException
     */
    public CEInvoice createInvoice(CEJobContract jobContract, List<CEJobOrderLineItem> jolis) throws InvalidInvoiceOperationException;

    /**
     * Generate an Invoice pdf file for the given invoice.
     * <p>
     * @param invoice
     * @return
     */
    public InvoiceFile generateInvoice(CEInvoice invoice) throws InvalidInvoiceOperationException, DaoException;
    
    /**
     * Create a new deposit invoice for the given JobOrder
     * @param jobContract
     * @return
     * @throws InvalidInvoiceOperationException
     */
    public DepositInvoice createDepositInvoice(CEJobContract jobContract) throws InvalidInvoiceOperationException;

    /**
     * Generate a deposit Invoice pdf file for the deposit invoice.
     * <p>
     * @param invoice
     * @return
     */
    public InvoiceFile generateDepositInvoice(DepositInvoice invoice) throws InvalidInvoiceOperationException, DaoException;
    
    /**
     * Apply a deposit invoice to the given invoice.
     * <p>
     * This method models the simplest scenario that a Deposit Invoice can be
     * applied to an invoice. First, the total invoice amount is calculated and
     * check against the balance of the given deposit invoice. If the total
     * balance of the deposit invoice is greater than the total invoice amount,
     * the given deposit invoice will be automatically apppliced to all the
     * invoice lines of the invoice, and the total invoiced amount will be
     * substracted from the balance of the deposit invoice.
     * <p>
     * If the total balance of the deposit invoice is less than the total
     * invoiced amount, there are several possible outcomes, which behavior to
     * apply can be controlled by a configuration item.
     * <ul>
     * The deposit invoices will be applied to the invoice lines on a first come
     * first serve basis, and the balance of the deposit invoice shall be
     * reduced to zero.
     * <ul>
     * The deposit invoices will be applied to the invoice lines on a best-fit
     * basis, and the balance of the deposit invoice shall be reduced to zero.
     * <ul>
     * The method throws an InvlidInvoiceOperationException without
     * automatically applying the deposit invoice.
     * <p>
     * Remember that DepositInvoice and InvoiceLine has a many-to-many
     * relationship, after this method completes successful, the association
     * shall be updated accordingly.
     * 
     * Certain security rules may be applicable before an invoice can be
     * generated.
     * 
     * @param invoice
     * @return
     */
    public CEInvoice applyDepositInvoice(CEInvoice invoice, DepositInvoice... depositInvoice) throws InvalidInvoiceOperationException;

    /**
     * Apply a deposit invoice to a list of invoice line items.
     * <p>
     * This method is similar to
     * <code>applyDepositInvoice(CEInvoice invoice, DepositInvoice deposittInvoice)</code>
     * except that instead of considering all the invoice line items from an
     * invoice, a list of invoice line items are specificly given.
     * 
     * @param ilis
     * @param depoistInvoice
     * @return
     * @throws InvalidInvoiceOperationException
     */
    public void applyDepositInvoice(List<CEInvoiceLineItem> ilis, DepositInvoice... depoistInvoice) throws InvalidInvoiceOperationException;

    /**
     * Apply a deposit invoice to a list of invoice line items.
     * <p>
     * This method is similar to
     * <code>applyDepositInvoice(CEInvoice invoice, DepositInvoice deposittInvoice)</code>
     * except that instead of considering all the invoice line items from an
     * invoice, a list of invoice line items are specificly given. And only a
     * limit of amount in the deposit invoice can be applied.
     * 
     * @param ilis
     * @param depoistInvoice
     * @return
     * @throws InvalidInvoiceOperationException
     */
    public void applyDepositInvoice(List<CEInvoiceLineItem> ilis, DepositInvoice depoistInvoice, double amount) throws InvalidInvoiceOperationException;

    /**
     * Apply a deposit invoice to one invoice line item, up to specified amount.
     * 
     * @param ili
     * @param depoistInvoice
     * @param amount
     *            If amount is 0, use the total available amount of the
     *            depositInvoice; otherwise, use the specified amount.
     * @throws InvalidInvoiceOperationException
     */
    public void applyDepositInvoice(CEInvoiceLineItem ili, DepositInvoice depoistInvoice, double amount) throws InvalidInvoiceOperationException;

    /**
     * Generate a credit invoice for an invoice previouse generated on a job
     * order.
     * <p>
     * The invoice to be credited must have been issues and paid for. Otherwise,
     * an InvalidInvoiceOperationException will be thrown.
     * <p>
     * Certain security rules may be applicable before a credit invoice can be
     * generated.
     * 
     * @param invoice
     * @return
     */
    public CreditInvoice creditInvoice(User user,CEInvoice invoice, String noteString) throws InvalidInvoiceOperationException;

    /**
     * Generate a credit invoice for an invoice initiated from a billing event.
     * <p>
     * The details to located the invoices to credit can be found in the billing
     * event. The invoice to be credited must have been issues and paid for.
     * Otherwise, an InvalidInvoiceOperationException will be thrown.
     * <p>
     * Certain security rules may be applicable before a credit invoice can be
     * generated.
     * 
     * @param invoice
     * @return
     */
    public CreditInvoice creditInvoice(BillingEventItem billingEvent, String noteString) throws InvalidInvoiceOperationException;

    /**
     * Generate a credit invoice for a deposit invoice.
     * <p>
     * The DepositInvoice must not have been applied to any invoice lines.
     * Otherwise, an InvalidInvoiceOperationException will be thrown
     * 
     * @param depositInvoice
     * @return
     */
    public CreditInvoice creditDepositInvoice(DepositInvoice depositInvoice, String noteString) throws InvalidInvoiceOperationException;

    /**
     * Retrieve the credited invoice based on a CreditInvoice.
     * <p>
     * 
     * @param depositInvoice
     * @return
     */
    public CEInvoice getCreditedInvoice(CreditInvoice creditInvoice) throws InvalidInvoiceOperationException, InvoiceNotFoundException;

    /**
     * Retrieve the credit invoice for an Invoice.
     * <p>
     * 
     * @param depositInvoice
     * @return
     */
    public CreditInvoice getCreditInvoice(CEInvoice invoice) throws InvalidInvoiceOperationException;

    /**
     * Retrieve the credited DepositInvoice based on a CreditInvoice.
     * <p>
     * 
     * @param depositInvoice
     * @return
     */
    public DepositInvoice getCreditedDepositInvoice(CreditInvoice creditInvoice) throws InvalidInvoiceOperationException, InvoiceNotFoundException;

    /**
     * Retrieve the credited ConsolidatedInvoice based on a CreditInvoice.
     * <p>
     * 
     * @param depositInvoice
     * @return
     */
    public DepositInvoice getCreditedConsolidatedInvoice(CreditInvoice creditInvoice) throws InvalidInvoiceOperationException, InvoiceNotFoundException;

    /**
     * Create a new ConsolidatedInvoice for a BU
     * @param bu
     * @return
     */
    public ConsolidatedInvoice createConsolidatedInvoice(BusinessUnit bu);
    
    /**
     * Generate the physical ConsolidatedInvoice file
     * @param consolInvoice
     * @return
     */
    public InvoiceFile generateConsolidatedInvoice(ConsolidatedInvoice consolInvoice);
    
    /**
     * Credit a consolidate invoice
     * @param consolInvoice
     * @return
     */
    public CreditInvoice CreditConsolidatedInvoice(ConsolidatedInvoice consolInvoice);
    
    /**
     * Get a set of attached CEInvoice for the given consolidated invoice
     */
    public Set<CEInvoice> getAttachedInvoices(ConsolidatedInvoice consolInvoice);
    
    /**
     * Get a set of attached JobContractInvoice for the given consolidated invoice
     * @param consolInvoice
     * @return
     */
    public Set<JobContractInvoice> getAttachedJobContractInvoice(ConsolidatedInvoice consolInvoice);
}
