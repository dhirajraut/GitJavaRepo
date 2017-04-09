/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.invoice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.Expression;
import com.intertek.entity.ExpressionId;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.OpenPeriod;
import com.intertek.entity.TaxLabel;
import com.intertek.entity.User;
import com.intertek.phoenix.BaseServiceImpl;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.QueryInfo;
import com.intertek.phoenix.entity.BillingEvent;
import com.intertek.phoenix.entity.BillingEventItem;
import com.intertek.phoenix.entity.BillingStatus;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.ConsolidatedInvoice;
import com.intertek.phoenix.entity.CreditInvoice;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.DepositInvoiceAmount;
import com.intertek.phoenix.entity.DepositType;
import com.intertek.phoenix.entity.InvoiceFile;
import com.intertek.phoenix.entity.InvoiceStatus;
import com.intertek.phoenix.entity.InvoiceType;
import com.intertek.phoenix.entity.Note;
import com.intertek.phoenix.entity.NoteType;
import com.intertek.phoenix.entity.OrderStatus;
import com.intertek.phoenix.entity.PaymentType;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.phoenix.pricing.AccountInfo;
import com.intertek.phoenix.pricing.PricingSrvc;
import com.intertek.phoenix.report.PDF;
import com.intertek.phoenix.report.ReportService;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.tax.CountryInfo;
import com.intertek.phoenix.tax.TaxSrvc;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;

/**
 * This class implements the InvoiceService interface for Phoenix.
 * 
 * @author richard.qin
 */
public class InvoiceServiceImpl extends BaseServiceImpl implements InvoiceService {

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#applyDepositInvoice(com.intertek.phoenix.entity.CEInvoice,
     *      com.intertek.phoenix.entity.DepositInvoice[])
     */
    @Override
    public CEInvoice applyDepositInvoice(CEInvoice invoice, DepositInvoice... depositInvoices) throws InvalidInvoiceOperationException {
        List<CEInvoiceLineItem> ilis = new ArrayList<CEInvoiceLineItem>();
        ilis.addAll(invoice.getInvoiceLineItems());
        applyDepositInvoice(ilis, depositInvoices);        
        return invoice;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#applyDepositInvoice(java.util.List,
     *      com.intertek.phoenix.entity.DepositInvoice[])
     */
    @Override
    public void applyDepositInvoice(List<CEInvoiceLineItem> ilis, DepositInvoice... depositInvoices) throws InvalidInvoiceOperationException {
        // check that there is enough fund for all the ilis
        double totalFundNeeded = 0;
        for (CEInvoiceLineItem ili : ilis) {
            totalFundNeeded += ili.getNetPrice();
        }
        double totalAvailable = 0;
        for (DepositInvoice depositInvoice : depositInvoices) {
            totalAvailable += depositInvoice.getAvailableAmount();
        }
        if (totalFundNeeded > totalAvailable) {
            throw new InvalidInvoiceOperationException("Not enough fund.");
        }
        // pay each invoice line one by one
        // the deposit invoice has been used so far
        for (int idxInvoice = 0, idxIli = 0; idxIli < ilis.size() && idxInvoice < depositInvoices.length;) {
            CEInvoiceLineItem ili = ilis.get(idxIli);
            // use the current deposit invoice to pay for this invoice line item
            try {
                DepositInvoiceAmount dia = payByDepositInvoice(ili, depositInvoices[idxInvoice], 0);
                DaoManager.getDao(DepositInvoiceAmount.class).saveOrUpdate(dia);
            }
            catch (DaoException ex) {
                throw new InvalidInvoiceOperationException("Failed to save DepositInvoiceAmount when applying DepositInvoice", ex);
            }
            // has we used up the current deposit invoice?
            if (depositInvoices[idxInvoice].getAvailableAmount() == 0) {
                // have enough fund for this invoice line item and ready for the
                // next
                idxInvoice++;
            }
            if (ili.getNetPrice() > 0) {
                // not enough fund for this invoice, but more is available in
                // the next deposit invoice
                idxIli++;
            }
        }
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#applyDepositInvoice(java.util.List,
     *      com.intertek.phoenix.entity.DepositInvoice, double)
     */
    @Override
    public void applyDepositInvoice(List<CEInvoiceLineItem> ilis, DepositInvoice depositInvoice, 
                                    double amount) throws InvalidInvoiceOperationException {
        // check that there is enough fund for all the ilis
        double totalNeeded = 0;
        Dao<DepositInvoiceAmount> dao = DaoManager.getDao(DepositInvoiceAmount.class);
        for (CEInvoiceLineItem ili : ilis) {
            totalNeeded += ili.getNetPrice();
        }
        if (totalNeeded > amount) {
            // Note, this behavior can be changed, let's discuss
            throw new InvalidInvoiceOperationException("Not enough fund.");
        }
        // "pay off" each invoice line items
        try{
            for (CEInvoiceLineItem ili : ilis) {
                DepositInvoiceAmount dia = payByDepositInvoice(ili, depositInvoice, amount);
                amount -= dia.getAmount();
                dao.saveOrUpdate(dia);
            }
        }
        catch(DaoException ex){
            throw new InvalidInvoiceOperationException("Failed to save DepositInvoiceAmount when applying DepositInvoice", ex);
        }
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#applyDepositInvoice(com.intertek.phoenix.entity.CEInvoiceLineItem,
     *      com.intertek.phoenix.entity.DepositInvoice, double)
     */
    @Override
    public void applyDepositInvoice(CEInvoiceLineItem ili, DepositInvoice depositInvoice, double amount) throws InvalidInvoiceOperationException {
        if (amount == 0) {
            amount = depositInvoice.getAvailableAmount();
        }
        
        if (amount > depositInvoice.getAvailableAmount()) {
            throw new InvalidInvoiceOperationException("Not enough fund");
        }
        // pay it off
        try {
            DepositInvoiceAmount dia = payByDepositInvoice(ili, depositInvoice, amount);
            DaoManager.getDao(DepositInvoiceAmount.class).saveOrUpdate(dia);
        }
        catch (DaoException ex) {
            throw new InvalidInvoiceOperationException("Failed to save DepositInvoiceAmount when applying DepositInvoice", ex);
        }
    }
    
    /**
     * Pay this invoice line item by a deposit invoice.
     * <p/>
     * This invoice line item may be partially paid. It is up to the caller to decide
     * if partial payment is allowed. An InvoiceLineItem can be paid by more than
     * one DepositInoices by calling this method multiple times with different
     * DepositInvoices.
     * <p/>
     * After the payment, the depositInvoice is updated accordingly.
     * Note, this method could be implemented in DepositInvoice equally effectively.
     *
     * @param depositInvoice
     * @param amountLimit    The amount limit of depositInvoice to be used. Allowing One
     *                       depositInvoice to pay more than one invoice line itmes.
     * @return
     * @throws DaoException 
     */
    public DepositInvoiceAmount payByDepositInvoice(CEInvoiceLineItem ili, DepositInvoice depositInvoice, double amountLimit) throws DaoException {
        // figure out exactly how much to pay
        double amountToPay = ili.getTotalAmount();
        if (amountLimit == 0) { // 0 means to use all available amount
            amountLimit = depositInvoice.getAvailableAmount();
        }
        if (amountToPay > amountLimit) {
            // Partial pay. It is up to the caller to determine if partial 
            // payment is allowed or not. 
            amountToPay = amountLimit;
        }
        // apply the payment
        DepositInvoiceAmount dia = new DepositInvoiceAmount(depositInvoice.getId(), ili.getId());
        DepositInvoiceAmount managedDia = DaoManager.getDao(DepositInvoiceAmount.class).searchUnique(dia);
        double oldAmount = 0;
        if (managedDia == null ){
            managedDia = dia;
        } 
        else {
            oldAmount = managedDia.getAmount();
        }
        managedDia.setAmount(amountToPay);
        // take the amount paid out of depositInvoice
        depositInvoice.useFund(amountToPay - oldAmount);
        // InvoiceLineItem and DepositInvoice has many to many relationship
        ili.addDepositInvoiceAmount(managedDia);
        depositInvoice.addAppliedDepositInvoiceAmount(managedDia);

        return managedDia;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#creditDepositInvoice(com.intertek.phoenix.entity.DepositInvoice,
     *      String)
     */
    @Override
    public CreditInvoice creditDepositInvoice(DepositInvoice depositInvoice, String noteString) throws InvalidInvoiceOperationException {
        // Note, I could just delegate it go creditInvoice(CEInvoice, String),
        // if CreditInvoice remains to extend from CEInvoice
        if (depositInvoice.canCredit() == false) {
            throw new InvalidInvoiceOperationException("Cannot credit a used depositInvoice.");
        }

        CreditInvoice creditInvoice = null;
        try {
            Note note = null;
            if (noteString != null) {
                note = new Note(noteString, NoteType.COMMENT); // which note
                                                               // type?
            }
            creditInvoice = depositInvoice.creditInvoice(note);
            DaoManager.getDao(Note.class).saveOrUpdate(note);
            DaoManager.getDao(CreditInvoice.class).saveOrUpdate(creditInvoice);
        }
        catch (DaoException ex) {
            throw new InvalidInvoiceOperationException("Unable to generate a deposit invoice.", ex);
        }
        return creditInvoice;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#creditInvoice(com.intertek.phoenix.entity.CEInvoice)
     */
    @Override
    public CreditInvoice creditInvoice(User user, CEInvoice invoice, String noteString) throws InvalidInvoiceOperationException {
        
        
        if (invoice.canCredit() == false) {
            throw new InvalidInvoiceOperationException("Cannot credit a used depositInvoice or an invoice that has not been paid.");
        }
        ReportService reportService = ServiceManager.getReportService();
        CreditInvoice creditInvoice = new CreditInvoice();
        try {
            creditInvoice.setCreditReasonNote(noteString);
            creditInvoice.setCreditReasonUserName(user.getLoginName());
            
            creditInvoice.setInvoiceNumber(invoice.getInvoiceNumber()+"CR");
            creditInvoice.setGeneratedBy(user.getLoginName());
            creditInvoice.setGeneratedOn(new Timestamp(new Date().getTime()));
            creditInvoice.setRelatedInvoiceNumber(invoice.getInvoiceNumber());
            creditInvoice.setType(InvoiceType.CREDIT);
            creditInvoice.setStatus(InvoiceStatus.CREDITED);
            // need to populate additional details here
            
            // roll back joli
            creditJobOrderLineitems(invoice);
            
            // TODO need to implement the report          
            
            PDF pdf = reportService.generateInvoicePdf(invoice);
            InvoiceFile invoiceFile = new InvoiceFile();
            // invoiceFile.setId(CommonUtil.generateId());
            //invoiceFile.setCreditInvoice(creditInvoice);
            
            creditInvoice.setInvoiceFileName(pdf.getFileName());
            invoiceFile.setInvoiceFileName(pdf.getFileName());
            creditInvoice.setInvoiceFile(invoiceFile);
            // persist this object
            Dao<InvoiceFile> dao = DaoManager.getDao(InvoiceFile.class);
            dao.saveOrUpdate(invoiceFile);           
            DaoManager.getDao(CreditInvoice.class).saveOrUpdate(creditInvoice);
            
        }
        catch (DaoException ex) {
            throw new InvalidInvoiceOperationException("Unable to generate a credit invoice.", ex);
        }
        return creditInvoice;
    }

    /**
     * @param invoice
     */
    private void creditJobOrderLineitems(CEInvoice invoice) {
        JobOrderService jobOrderService = ServiceManager.getJobOrderService();
        for(CEInvoiceLineItem ili: invoice.getInvoiceLineItems()){
            CEJobOrderLineItem joli = ili.getCEJobOrderLineItem();
            jobOrderService.creditJobOrderLineItem(joli);
        }
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#creditInvoice(com.intertek.phoenix.entity.BillingEventItem)
     */
    @Override
    public CreditInvoice creditInvoice(BillingEventItem billingEvent, String noteString) throws InvalidInvoiceOperationException {
        // So far, there is no lead from BillingEvent to an Invoice,
        // reexamine if this method is really needed.
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public String generateInvoiceId(CEJobContract jobContract) {
        // TODO shall provice a meaningful implementation
        return CommonUtil.generateIdCode();
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#createInvoice(com.intertek.phoenix.entity.CEJobOrder,
     *      com.intertek.phoenix.entity.Customer)
     */
    @Override
    public CEInvoice createInvoice(CEJobContract jobContract) throws InvalidInvoiceOperationException {
        // check if the jobOrder has at least one billable item at this time.
        List<CEJobOrderLineItem> billableJolis = jobContract.getBillableJobOrderLineItems();
        if (billableJolis.size() == 0) {
            throw new InvalidInvoiceOperationException("This JobContract does not have any billable line items.");
        }
        return createInvoice(jobContract, billableJolis);
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#createInvoice(java.util.List,
     *      com.intertek.phoenix.entity.Customer)
     */
    @Override
    public CEInvoice createInvoice(CEJobContract jobContract, List<CEJobOrderLineItem> billables) throws InvalidInvoiceOperationException {
        // check if the jobOrder for this jobContract is in a state that can be invoiced.
        OrderStatus status = jobContract.getJobOrder().getStatus();
        CEInvoice example = new CEInvoice();

        if (status != OrderStatus.OPEN && status != OrderStatus.REOPENED) {
            throw new InvalidInvoiceOperationException("This JobOrder's status does not allow it be invoiced: " + status.getDescription());
        }

        // check if there is at least one billable item to bill
        if (jobContract.getJobOrderLineItems().size() > 0 && billables.size() == 0) {
//            throw new InvalidInvoiceOperationException("There is no billable Job Order line items.");
            // Instead of throw exceptions, here return the blank Invoice
            return example;
        }

        // is there an invoice that has not been paid in full?
        Dao<CEInvoice> dao = DaoManager.getDao(CEInvoice.class);
        
        // TODO rethink the status control when generating invoices
        
//        example.setOrderNumber(jobOrder.getJobNumber());
//        example.setStatus(InvoiceStatus.INVOICED); // invoiced but not paid
//        try {
//            List<CEInvoice> unpaidInvoices = dao.search(example);
//            if (unpaidInvoices.size() > 0) {
//                // we have some unfinished business, must not continue.
//                throw new InvalidInvoiceOperationException("There are exsting existing invoices that have not been paid.");
//            }
//        }
//        catch (DaoException e) {
//            throw new InvalidInvoiceOperationException("Operation Failed", e);
//        }

        CEInvoice invoice = null;
        // if there is a new invoice that has not been invoiced, delete its
        // invoice lines and recycle the invoice object.
        // This is to cater for the situation when a user wants to make a
        // change to an new invoice that has not been sent to the customer.
        example.setStatus(InvoiceStatus.NEW);
        example.setJobOrderContractId(jobContract.getId());
        try {
            invoice = dao.searchUnique(example);
            if (invoice != null) {
               
                updateInvoiceLineItem(invoice, billables);
            }
            else {
                
                invoice = new CEInvoice();
                invoice.setType(InvoiceType.NORMAL);
                invoice.setInvoiceNumber(generateInvoiceId(jobContract));
                // save the invoice before continue, hibernate will write it to
                // the database at the end of session.
                dao.saveOrUpdate(invoice);
                buildInvoiceLineItems(invoice, billables);
            }
            populateInvoiceDetails(jobContract, invoice);
            return invoice;
        }
        catch (PhoenixException e) {
            throw new InvalidInvoiceOperationException("Operation Failed", e);
        }
    }

    private void updateInvoiceLineItem(CEInvoice invoice, List<CEJobOrderLineItem> billables) throws DaoException {
        Dao<CEInvoiceLineItem> dao = DaoManager.getDao(CEInvoiceLineItem.class);
        // remove invoice line items that have no matching billable joli
        Set<CEInvoiceLineItem> invoiceLineItems = invoice.getInvoiceLineItems();
        outer1: for (Iterator<CEInvoiceLineItem> it = invoiceLineItems.iterator(); it.hasNext();) {
            CEInvoiceLineItem ili = it.next();
            for(CEJobOrderLineItem joli: billables){
                if(ili.getCEJobOrderLineItemId().equals(joli.getId())){
                    continue outer1;
                }
            }
            it.remove();
            dao.remove(ili);
        }
        // add new invoice line items that does not exist in invoice 
        outer2: for(CEJobOrderLineItem joli: billables){
            for(CEInvoiceLineItem ili: invoice.getInvoiceLineItems()){
                if(ili.getCEJobOrderLineItemId().equals(joli.getId())){
                    updateInvoiceLineItem(joli, ili);
                    continue outer2;
                }
            }
            // add a new ili
            CEInvoiceLineItem ili = this.createInvoiceLineItem(joli);
            invoice.addInvoiceLineItem(ili);            
        }
        invoice.calculate();
    }

    private void populateInvoiceDetails(CEJobContract jobContract, CEInvoice invoice) throws PhoenixException {
        User user = SecurityUtil.getUser();
        TaxSrvc taxSrvc = ServiceManager.getTaxSrvc();        

        invoice.setGeneratedBy(user.getLoginName());
        invoice.setGeneratedOn(new Timestamp(System.currentTimeMillis()));
        // each BU has a rolling open period, which is used to calculate
        // invoice date for all the invoices issued from that BU
        // during that period.
        Timestamp invoiceDate = getInvoiceDateForBU(jobContract.getJobOrder().getBuName());
        if (invoiceDate != null) {
            invoice.setAccountingDate(invoiceDate);
            if (InvoiceStatus.CREDITED != invoice.getStatus()) {
                invoice.setInvoiceDate(invoiceDate);
            }
        }
        invoice.setDescription(jobContract.getInvoiceDescription());
        invoice.setPaymentTermsDesc(jobContract.getPaymentTerms());
        invoice.setTransactionCurrencyCd(jobContract.getTransactionCurrency());
        invoice.setVatRegId(jobContract.getVatRegId());
        invoice.setProject(jobContract.getJobOrder().getProject());
        invoice.setBillingAddress(jobContract.getBillingAddress());
        invoice.setBillingContact(jobContract.getBillingContact());
        invoice.setReportReceivingAddress(jobContract.getReportReceivingAddress());
        invoice.setReportReceivingContact(jobContract.getReportReceivingContact());
        invoice.setVatRegCountryCode(jobContract.getVatRegCountry());
        invoice.setRemitTo(jobContract.getRemitTo());
        invoice.setRemitToCode(jobContract.getRemitToCode());
        invoice.setRemitToBankAccountNum(jobContract.getRemitToBankAccountNum());
        BusUnitVatLoc busUnitVatLoc = taxSrvc.getBusVatLocRegistrationId(jobContract.getJobOrder().getBu(), jobContract.getJobOrder().getServiceLocation()
                                                                         .getCountryCode(), invoice.getVatProvince());
        if(busUnitVatLoc!=null){
            invoice.setBuVatRegId(busUnitVatLoc.getBusUnitVatLocId().getVatRegistrationId());    
        }
        if (jobContract.getJobOrder().getServiceLocation() != null
            && jobContract.getJobOrder().getServiceLocation().getCountryCode().equals(jobContract.getJobOrder().getBu().getCountryCode())
            && jobContract.getJobOrder().getServiceLocation().getStateCode() != null) {
            TaxLabel taxLabel = taxSrvc.getTaxLabel(jobContract.getJobOrder().getBu().getCountryCode(), jobContract.getJobOrder().getServiceLocation()
                    .getStateCode());
            if (taxLabel != null) {
                invoice.setSalesTaxLabel(taxLabel.getSalesTaxLabel());
                invoice.setVatLabel(taxLabel.getVatLabel());
                invoice.setVatRegLabel(taxLabel.getVatRegLabel());
            }
        }

        // invoice.setInvoiceDate(invoiceDate); // what's the difference between
        // On and Date?
        invoice.setStatus(InvoiceStatus.NEW);
        invoice.setJobContract(jobContract);
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#previewInvoice(com.intertek.phoenix.entity.CEJobOrder)
     */
    @Override
    public CEInvoice previewInvoice(CEJobContract jobContract) throws InvalidInvoiceOperationException {
        CEInvoice invoice = createInvoice(jobContract);
        if(invoice != null){
            invoice.setType(InvoiceType.DRAFT);
        }
        return invoice;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#previewInvoice(java.util.List,
     *      com.intertek.phoenix.entity.Customer)
     */
    @Override
    public CEInvoice previewInvoice(CEJobContract jobContract, List<CEJobOrderLineItem> billables) throws InvalidInvoiceOperationException {
        CEInvoice invoice = createInvoice(jobContract, billables);
        if(invoice != null){
            invoice.setType(InvoiceType.DRAFT);
        }
        return invoice;
    }

    private CEInvoice buildInvoiceLineItems(CEInvoice invoice, List<CEJobOrderLineItem> billables) throws DaoException {
        // build an invoice, and for each billable item, create a new invoice
        // line. the billing amount is the billable amount of a billable job
        // order line item. after an invoice line is created, the billing
        // amount should be substracted from the billable amount
        // TODO need to review the code.
        for (CEJobOrderLineItem joli : billables) {           
            CEInvoiceLineItem ili = createInvoiceLineItem(joli);
            // make it a part of the invoice
            invoice.addInvoiceLineItem(ili);
        }

        invoice.calculate();
        return invoice;
    }
    
    private CEInvoiceLineItem createInvoiceLineItem(CEJobOrderLineItem joli) throws DaoException {
        CEInvoiceLineItem ili = new CEInvoiceLineItem();
        updateInvoiceLineItem(joli, ili);
        // make the dao be aware of the new entity
        DaoManager.getDao(CEInvoiceLineItem.class).saveOrUpdate(ili);
        return ili;
    }

    private void updateInvoiceLineItem(CEJobOrderLineItem joli, CEInvoiceLineItem ili) {
        // set account code or GL code        
        ili.setUnitPrice(joli.getUnitPrice()); // TODO is list price unit price?
        ili.setQuantity(joli.getQuantity()); // TODO all quantity in the joli?
        // net price will be calculated automatically, or not?
        // 9/1/2009: per richard netPrice comes from the billing amount
        ili.setNetPrice(joli.getBillingAmount());
        ili.setRateDiv(joli.getRateDiv()); //to set the RateDiv from joli's rateDiv
        ili.setRateMult(joli.getRateMult());//to set the RateMult from joli's rateMult
        ili.setCEJobOrderLineItem(joli);
    }

    private Timestamp getInvoiceDateForBU(String buName) throws DaoException {
        Timestamp retVal = null;
        QueryInfo queryInfo = new QueryInfo(OpenPeriod.class);
        queryInfo.setFilter("buName", buName);
        OpenPeriod openPeriod = null;
        
        openPeriod = DaoManager.getDao(OpenPeriod.class).searchUnique(queryInfo);
        if(openPeriod != null && openPeriod.getOpenFromDate()!= null 
                && openPeriod.getOpenToDate()!=null ){
            retVal = new Timestamp (openPeriod.getOpenFromDate().getTime());
        }
        return retVal;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#getCreditedInvoice(com.intertek.phoenix.entity.CreditInvoice)
     */
    @Override
    public CEInvoice getCreditedInvoice(CreditInvoice creditInvoice) throws InvalidInvoiceOperationException, InvoiceNotFoundException {
        if ( InvoiceType.CREDIT != creditInvoice.getType()) {
            throw new InvalidInvoiceOperationException("Incorrect invoice type, a Normal Invoice is expected.");
        }

        String invoiceNumber = creditInvoice.getRelatedInvoiceNumber();
        CEInvoice invoice = new CEInvoice();
        invoice.setInvoiceNumber(invoiceNumber);
        Dao<CEInvoice> dao = DaoManager.getDao(CEInvoice.class);
        try {
            invoice = dao.searchUnique(invoice);
        }
        catch (DaoException e) {
            throw new InvoiceNotFoundException("Cannot find the Invoice related to the CreditInvoice.", e);
        }
        return invoice;
    }

    @Override
    public CreditInvoice getCreditInvoice(CEInvoice invoice) throws InvalidInvoiceOperationException {
        if(invoice == null){
            throw new InvalidInvoiceOperationException("Invoice must not be null.");
        }
        CreditInvoice creditInvoice = new CreditInvoice();
        creditInvoice.setRelatedInvoiceNumber(invoice.getInvoiceNumber());
        Dao<CreditInvoice> dao = DaoManager.getDao(CreditInvoice.class);
        try {
            creditInvoice = dao.searchUnique(creditInvoice);            
        }
        catch (DaoException e) {
            // this should be expected, so just return null
            invoice = null;
        }
        return creditInvoice;
    }
    
    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#getCreditedDepositInvoice(com.intertek.phoenix.entity.CreditInvoice)
     */
    @Override
    public DepositInvoice getCreditedDepositInvoice(CreditInvoice creditInvoice) throws InvalidInvoiceOperationException, InvoiceNotFoundException {
        if ( InvoiceType.DEPOSIT  != creditInvoice.getType()) {
            throw new InvalidInvoiceOperationException("Incorrect invoice type, a Deposit Invoice is expected");
        }

        String invoiceNumber = creditInvoice.getRelatedInvoiceNumber();
        DepositInvoice invoice = new DepositInvoice();
        invoice.setInvoiceNumber(invoiceNumber);
        Dao<DepositInvoice> dao = DaoManager.getDao(DepositInvoice.class);
        try {
            invoice = dao.searchUnique(invoice);
        }
        catch (DaoException e) {
            throw new InvoiceNotFoundException("Cannot find the DepositInvoice related to the CreditInvoice.", e);
        }
        return invoice;
    }

    @Override
    public InvoiceFile previewDepositInvoice(DepositInvoice invoice) throws InvalidInvoiceOperationException {
        ReportService reportService = ServiceManager.getReportService();
        InvoiceFile invoiceFile = null;

        //TODO need to revisit once the invoice jasper is available
        //PDF pdf = reportService.generateInvoicePreviewPdf(invoice);
        PDF pdf=new PDF();
        pdf.setFileName(invoice.getInvoiceNumber()+".pdf");
        invoiceFile = new InvoiceFile();
//        invoiceFile.setInvoice(invoice);
        invoice.setInvoiceFile(invoiceFile);
        invoiceFile.setInvoiceFileName(pdf.getFileName());
        // this invoice file is transient and will not be persisted, which
        // means that a preview can be repeatedly generated for a draft
        // invoice.
        
        return invoiceFile;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#createDepositInvoice(com.intertek.phoenix.entity.CEJobOrder)
     */
    @Override
    public DepositInvoice createDepositInvoice(CEJobContract jobContract) throws InvalidInvoiceOperationException {
        PricingSrvc pricingSrvc = ServiceManager.getPricingSrvc();
        DepositInvoice depositInvoice = new DepositInvoice();
        depositInvoice.setStatus(InvoiceStatus.NEW);
        depositInvoice.setDepositType(DepositType.WIRE);
        depositInvoice.setPaymentType(PaymentType.CASH);
        depositInvoice.setInvoiceNumber(generateInvoiceId(jobContract));
        depositInvoice.setType(InvoiceType.NORMAL);
        // initialize the sortNumber based on how many deposit invoices have already been created.
        // TODO: Externalize this if possible. Using sortNumber to control the order is tricky.  
        // If some deposit invoices have been deleted, then the total is not accurate; 
        // however, if using the max sortNumber + 1, there could be skipping number problem.
        int sortNumber = 1;
        Set<DepositInvoice> depositInvoices = jobContract.getDepositInvoices();
        if (depositInvoices != null && depositInvoices.size() > 0){
            sortNumber = depositInvoices.size() + 1;
        }
        depositInvoice.setSortNumber(sortNumber);
        
        depositInvoice.setRemitTo(jobContract.getRemitTo());
        depositInvoice.setRemitToCode(jobContract.getRemitToCode());
        depositInvoice.setRemitToBankAccountNum(jobContract.getRemitToBankAccountNum());

        try {
            DaoManager.getDao(DepositInvoice.class).saveOrUpdate(depositInvoice);
        }
        catch (DaoException ex) {
            throw new InvalidInvoiceOperationException("Failed to create DepositInvoice", ex);
        }
        
        Expression expression = new Expression();
        ExpressionId expressionId = new ExpressionId();
        expressionId.setExpressionId(Constants.DEPOSIT_INVOICE); // TODO
        expression.setExpressionId(expressionId);
        try{
            AccountInfo accountInfo = pricingSrvc.getAccountInfo(expression, jobContract.getJobOrder().getJobType(),
                                                                 null, jobContract.getJobOrder().getBranchName(),
                                                                 null, jobContract.getCustomer(), null);
            depositInvoice.setAccount(accountInfo.getGlCode());
            depositInvoice.setDeptId(accountInfo.getDepartmentCode());
        }
        catch(JobSrvcException ex){
            throw new InvalidInvoiceOperationException("Failed to load AccountInfo for DepositInvoice", ex);
        }
        
        // this new deposit invoice needs to be "filled out" by the user for
        // details like amount, payment type, etc.

        jobContract.addDepositInvoice(depositInvoice);
        return depositInvoice;
    }

    /**
     * @throws DaoException
     * @see com.intertek.phoenix.invoice.InvoiceService#generateInvoice(com.intertek.phoenix.entity.CEInvoice)
     */
    @Override
    public InvoiceFile generateInvoice(CEInvoice invoice) throws InvalidInvoiceOperationException, DaoException {
        ReportService reportService = ServiceManager.getReportService();
        InvoiceFile invoiceFile = null;
        // check the invoice type, if it is a draft invoice, generate a preview
        if (invoice.getType() == InvoiceType.DRAFT) {
            //TODO need to revisit once the invoice jasper is available
            //PDF pdf = reportService.generateInvoicePreviewPdf(invoice);
            PDF pdf=new PDF();
            pdf.setFileName(invoice.getInvoiceNumber()+".pdf");
            invoiceFile = new InvoiceFile();
//            invoiceFile.setInvoice(invoice);
            invoiceFile.setInvoiceFileName(pdf.getFileName());
            // this invoice file is transient and will not be persisted, which
            // means that a preview can be repeatedly generated for a draft
            // invoice.
        }
        else if (invoice.getType() == InvoiceType.NORMAL) {
            // check the status of this invoice, if the invoice is already
            // generated, throw an exception
            // TODO do we need to support the regenerate flag?
            InvoiceStatus status = invoice.getStatus();
            if (status != InvoiceStatus.NEW) {
                throw new InvalidInvoiceOperationException("Cannot generate an invoice that is already generated.");
            }

            // TODO why the following logic been commented out?
            // remove invoice lines that have not been selected.
           // List<CEInvoiceLineItem> toRemove = invoice.removeUnselectedLineItems();
            // delete the unselected line items from datastore
           // Dao<CEInvoiceLineItem> daoLineItem = DaoManager.getDao(CEInvoiceLineItem.class);
           // daoLineItem.remove(toRemove);
            
            // adjust jobOrderLineItem billedAmount to accrued amount
            // and its billing status
            try {
                sealBilledJobOrderLineItems(invoice);
            }
            catch (JobSrvcException e) {
                throw new InvalidInvoiceOperationException("Error happened in generating invoice.", e);
            }

            PDF pdf = reportService.generateInvoicePdf(invoice);
            invoiceFile = new InvoiceFile();
//            invoiceFile.setInvoice(invoice);
            invoiceFile.setInvoiceFileName(pdf.getFileName());
            // update the invoice status
            updateInvoiceStatusToInvoiced(invoice, invoiceFile);
            // persist this object
            DaoManager.getDao(InvoiceFile.class).saveOrUpdate(invoiceFile);
            invoice.setInvoiceFile(invoiceFile);
            DaoManager.getDao(CEInvoice.class).saveOrUpdate(invoice);
        }
        else {
            throw new InvalidInvoiceOperationException("Cannot generate invoice, invalid invoice status.");
        }
        return invoiceFile;
    }

    /**
     * @param invoice
     * @throws JobSrvcException 
     */
    private void sealBilledJobOrderLineItems(CEInvoice invoice) throws JobSrvcException {
        Map<CEJobOrderLineItem, CEJobOrderLineItem> map = new HashMap<CEJobOrderLineItem, CEJobOrderLineItem>();
        JobOrderService jobService = ServiceManager.getJobOrderService();
        String batchNumber = null;
        for (CEInvoiceLineItem li : invoice.getInvoiceLineItems()){
            CEJobOrderLineItem joli = li.getCEJobOrderLineItem();
            if(batchNumber == null){
                batchNumber = joli.getBatchNumber();
            }
            
            CEJobOrderLineItem newJoli = jobService.sealJobOrderLineItem(joli, invoice.getInvoiceNumber());
            map.put(joli, newJoli);
            
            // if the jolis are related to a BillingEvent, then update the
            // BillingEvent's status
            BillingEvent billingEvent = invoice.getJobContract().getBillingEvent(batchNumber);
            if(billingEvent != null){
                billingEvent.setBillingStatus(BillingStatus.INVOICED);
            }
        } 
        // need to re-establish the master/related relationship
        for(CEJobOrderLineItem joli: map.values()){
            if (joli == null){
                continue;
            }
            CEJobOrderLineItem master = joli.getMaster();
            if(master != null){
                CEJobOrderLineItem newJoli = map.get(joli);
                if(newJoli != null){
                    CEJobOrderLineItem newMaster = map.get(master);
                    if(newMaster != null){
                        newJoli.setMaster(newMaster);
                    }
                    else{
                        // this actually creates an anormali, a master
                        // is fully invoiced, but one of its related is
                        // not, since a related must have a master, the old
                        // master is used. However, the old master also
                        // relates to the old related, therefore, there is
                        // more than one instances of the "same" related
                        // line items point back the same master. 
                        // Hopefully, there is a business restriction to
                        // prevent it from happening. Will revisit. TODO
                        newJoli.setMaster(master);
                    }
                }
            }
        }
    }

    /**
     * @param invoice
     */
    private void updateInvoiceStatusToInvoiced(CEInvoice invoice, InvoiceFile invoiceFile) {
        invoice.setStatus(InvoiceStatus.INVOICED);
        invoice.setGeneratedBy(CommonUtil.getCurrentUser());
        invoice.setGeneratedOn(new Timestamp(new Date().getTime()));
        invoice.setInvoiceFileName(invoiceFile.getInvoiceFileName());
        invoice.setInvoiceFile(invoiceFile);        
        // TODO more to come
    }
    
    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#generateDepositInvoice(com.intertek.phoenix.entity.DepositInvoice)
     */
    @Override
    public InvoiceFile generateDepositInvoice(DepositInvoice invoice) throws InvalidInvoiceOperationException, DaoException {
        InvoiceFile invoiceFile = null;
        // check the invoice type, if it is a draft invoice, generate a preview
        if (invoice.getType() == InvoiceType.NORMAL || invoice.getType() == InvoiceType.DRAFT) {
            // check the status of this invoice, if the invoice is already generated
            // throw an exception
            // TODO do we need to support the regenerate flag?
            InvoiceStatus status = invoice.getStatus();
            if (InvoiceStatus.INVOICED == status) {
                throw new InvalidInvoiceOperationException("Cannot generate an invoice that is already generated.");
            }
            // let the report service do the work
            ReportService reportService = ServiceManager.getReportService();
            PDF pdf = reportService.generateDepositInvoicePdf(invoice);
            invoiceFile = new InvoiceFile();
            invoiceFile.setInvoiceFileName(pdf.getFileName());           
            // invoiceFile.setId(CommonUtil.generateId());
            invoice.setInvoiceFile(invoiceFile);
            // persist this object
            Dao<InvoiceFile> dao = DaoManager.getDao(InvoiceFile.class);
            dao.saveOrUpdate(invoiceFile);
            // update the invoice status
//            updateDepositInvoiceStatusToInvoiced(invoice, invoiceFile);
            invoice.setStatus(InvoiceStatus.INVOICED);
            invoice.setGeneratedBy(CommonUtil.getCurrentUser());
            invoice.setGeneratedOn(new Timestamp(new Date().getTime()));
            invoice.setInvoiceFileName(invoiceFile.getInvoiceFileName());
            invoice.setPdfGeneratedFlag(true);
            invoice.setType(InvoiceType.NORMAL);
        }
        else{
            throw new InvalidInvoiceOperationException("Cannot generate deposit invoice, invalid invoice status.");
        }
        return invoiceFile;
    }

    
    
    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#CreditConsolidatedInvoice(com.intertek.phoenix.entity.ConsolidatedInvoice)
     */
    @Override
    public CreditInvoice CreditConsolidatedInvoice(ConsolidatedInvoice consolInvoice) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#createConsolidatedInvoice(com.intertek.entity.BusinessUnit)
     */
    @Override
    public ConsolidatedInvoice createConsolidatedInvoice(BusinessUnit bu) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#generateConsolidatedInvoice(com.intertek.phoenix.entity.ConsolidatedInvoice)
     */
    @Override
    public InvoiceFile generateConsolidatedInvoice(ConsolidatedInvoice consolInvoice) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#getAttachedInvoices(com.intertek.phoenix.entity.ConsolidatedInvoice)
     */
    @Override
    public Set<CEInvoice> getAttachedInvoices(ConsolidatedInvoice consolInvoice) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#getAttachedJobContractInvoice(com.intertek.phoenix.entity.ConsolidatedInvoice)
     */
    @Override
    public Set<JobContractInvoice> getAttachedJobContractInvoice(ConsolidatedInvoice consolInvoice) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see com.intertek.phoenix.invoice.InvoiceService#getCreditedConsolidatedInvoice(com.intertek.phoenix.entity.CreditInvoice)
     */
    @Override
    public DepositInvoice getCreditedConsolidatedInvoice(CreditInvoice creditInvoice) throws InvalidInvoiceOperationException, InvoiceNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

}
