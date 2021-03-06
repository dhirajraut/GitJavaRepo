/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.common;

import java.util.List;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.dao.FilterOp;
import com.intertek.phoenix.dao.LogicOp;
import com.intertek.phoenix.entity.BillingStatus;
import com.intertek.phoenix.entity.DepositType;
import com.intertek.phoenix.entity.InvoiceGenerationType;
import com.intertek.phoenix.entity.InvoiceStatus;
import com.intertek.phoenix.entity.InvoiceType;
import com.intertek.phoenix.entity.NoteType;
import com.intertek.phoenix.entity.OperationalStatus;
import com.intertek.phoenix.entity.OrderOrigin;
import com.intertek.phoenix.entity.OrderStatus;
import com.intertek.phoenix.entity.PaymentStatus;
import com.intertek.phoenix.entity.PaymentType;
import com.intertek.phoenix.entity.ProjectType;
import com.intertek.phoenix.entity.SourceOrigin;
import com.intertek.phoenix.entity.TaskOperationalStatus;
import com.intertek.phoenix.entity.UOM;
import com.intertek.phoenix.entity.UserType;
import com.intertek.phoenix.entity.Visibility;
import com.intertek.phoenix.search.SearchKey;

/**
 * @author eric.nguyen
 */
public interface ReferenceDataService{    
    public enum DropdownName {
        UOM, 
        activeStatus, 
        audienceType, 
        branchtype, 
        communication, 
        conslInvStatus, 
        contactFlag, 
        containerType, 
        contractAttachType, 
        contractStatus, 
        customerType, 
        dateFormat, 
        dispatchStatus, 
        dropdown, 
        employeeStatus, 
        employeeType, 
        fullPartTime, 
        industryType, 
        invoiceType, 
        jobContractStatus, 
        jobStatus, 
        jobTimeFormat, 
        jobType, 
        joblogStatus, 
        jobsearchStatus, 
        locationType, 
        noteType, 
        operator, 
        option, 
        origin, 
        paymentType, 
        plusMinus, 
        priority, 
        productType, 
        regularTemp, 
        retainTested, 
        salutation, 
        sampleLocation, 
        sampleTiming, 
        sampleType, 
        sampleVolume, 
        searchfields, 
        selectedLanguage, 
        status, 
        timeFormat, 
        treatServiceforForeignBuyer, 
        visibility, 
        workFunction, 
        workingPb, 
        workingUOM, 
        yesNo, 
        depositType,
        product
    }
    
   // public void initDropdowns();
    
    public List<Field> getProductGroups(String productType);
    
    /*
     * given a dropdown type, return a list of fields
     */
    public List<Field> getDropdown(DropdownName dropdown);
    
    /*
     * return an array of FilterOps
     */
    public FilterOp[] getFilterOps();
    
    /*
     * return a list of Field of FilterOp to be display on jsp as dropdown
     */
    public List<Field> getFilterOpFields();
    
    public LogicOp[] getLogicOps();
    public List<Field> getLogicOpFields();
    
    public BillingStatus[] getBillingStatuses();
    public List<Field> getBillingStatusFields();
    
    public InvoiceStatus[] getInvoiceStatuses();
    public List<Field> getInvoiceStatusFields();
    
    public InvoiceType[] getInvoiceTypes();
    public List<Field> getInvoiceTypeFields();
    
    public NoteType[] getNoteTypes();
    public List<Field> getNoteTypeFields();
    
    public OperationalStatus[] getOperationalStatuses();
    public List<Field> getOperationalStatusFields();
    
    public OrderOrigin[] getOrderOrigins();
    public List<Field> getOrderOriginFields();
    
    public OrderStatus[] getOrderStatuses();
    public List<Field> getOrderStatusFields();
    
    public PaymentStatus[] getPaymentStatuses();
    public List<Field> getPaymentStatusFields();
    
    public PaymentType[] getPaymentTypes();
    public List<Field> getPaymentTypeFields();
    
    public ProjectType[] getProjectTypes();
    public List<Field> getProjectTypeFields(EnumFieldFilter filter);
        
    public SourceOrigin[] getSourceOrigins();
    public List<Field> getSourceOriginFields();
    
    public TaskOperationalStatus[] getTaskOperationalStatuses();
    public List<Field> getTaskOperationalStatusFields();
    
    public UOM[] getUOMs();
    public List<Field> getUOMFields();
    
    public UserType[] getUserTypes();
    public List<Field> getUserTypeFields();
    
    public Visibility[] getVisibilities();
    public List<Field> getVisibilityFields();
    
    public List<Field> getInvoiceGenerationTypeFields();
    public InvoiceGenerationType[] getInvoiceGenerationTypes();
    
    public DepositType[] getDepositTypes();
    public List<DepositType> getDepositTypeFields();
    //help to filter string logical operators from the generic enum FilterOp
    List<FilterOp> getFilterStringOpFields();
    List<SearchKey> getsearchFields();
    
    public List<Field> getProducts(String groupId);
}
