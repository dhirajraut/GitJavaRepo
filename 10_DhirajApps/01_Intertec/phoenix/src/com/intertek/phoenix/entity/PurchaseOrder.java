package com.intertek.phoenix.entity;

import com.intertek.entity.Branch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Customer;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;
import org.hibernate.annotations.Index;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Patni
 */
@Entity
@Table(name = "PHX_PURCHASE_ORDER")
public class PurchaseOrder {
    @Id
    @SequenceGenerator(name="PurchaseOrder_seq", sequenceName = "PURCHASE_ORDER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PurchaseOrder_seq" )
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Length(min = 0, max = 128)
    @Column(name = "PO_NUMBER", length = 128)
    private String poNumber;

    @Length(min = 0, max = 128)
    @Column(name = "PO_NAME", length = 128)
    private String poName;

    @Column(name = "BEGIN_DATE")
    private Timestamp beginDate;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @NotNull
    @Column(name = "EXP_COMPLETE_DATE")
    private Timestamp expCompleteDate;
    @NotNull
    @Min(value = 1)
    @Column(name = "maxAmount", precision = 38, scale = 4)
    private Double maxAmount;
    @Column(name = "BALANCE_AMOUNT", precision = 38, scale = 4)
    private Double balanceAmount;

    @Length(min = 0, max = 10)
    @Column(name = "CURRENCY", precision = 38, scale = 4)
    private String currency;

    @Length(min = 0, max = 512)
    @Column(name = "DESCRIPTION", length = 512)
    private String description;

    @NotBlank
    @Length(min = 0, max = 5)
    @Column(name = "BU_NAME", updatable = false, insertable = false)
    private String buName;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "BU_NAME")
    @Index(name="PURCHASE_ORDER_INDEX_BU")
    private BusinessUnit businessUnit;

    @NotBlank
    @Length(min = 0, max = 15)
    @Column(name = "CUST_CODE", updatable = false, insertable = false)
    private String custCode;
    //private Customer customer;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "CUST_CODE")
    @Index(name="PURCHASE_ORDER_INDEX_CUSTOMER")
    private Customer customer;

    @NotBlank
    @Length(min = 0, max = 15)
    @Column(name = "BRANCH_CODE", updatable = false, insertable = false)
    private String branchCode;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "BRANCH_CODE")
    @Index(name="PURCHASE_ORDER_INDEX_BRANCH")
    private Branch branch;

    // TODO
    //@OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY)
    @Transient
    private Set<CEJobOrderLineItem> jobOrderLineItems;

    // Constructors

    /**
     * default constructor
     */
    public PurchaseOrder() {
    }

    /**
     * minimal constructor
     */
    public PurchaseOrder(String poNumber, Timestamp expCompleteDate, Double maxAmount, String buName, String custCode, String branchCode) {
        this.poNumber = poNumber;
        this.expCompleteDate = expCompleteDate;
        this.maxAmount = maxAmount;
        this.buName = buName;
        this.custCode = custCode;
        this.branchCode = branchCode;
    }

    /**
     * full constructor
     */

    public PurchaseOrder(String poNumber, String poName, Timestamp beginDate, Timestamp endDate, Timestamp expCompleteDate, Double maxAmount, Double balanceAmount, String currency, String description, String buName, BusinessUnit businessUnit, String custCode, Customer customer, String branchCode, Branch branch, Set<CEJobOrderLineItem> jobOrderLineItems) {
        this.poNumber = poNumber;
        this.poName = poName;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.expCompleteDate = expCompleteDate;
        this.maxAmount = maxAmount;
        this.balanceAmount = balanceAmount;
        this.currency = currency;
        this.description = description;
        this.buName = buName;
        this.businessUnit = businessUnit;
        this.custCode = custCode;
        this.customer = customer;
        this.branchCode = branchCode;
        this.branch = branch;
        this.jobOrderLineItems = jobOrderLineItems;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getPoName() {
        return this.poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public Timestamp getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getExpCompleteDate() {
        return this.expCompleteDate;
    }

    public void setExpCompleteDate(Timestamp expCompleteDate) {
        this.expCompleteDate = expCompleteDate;
    }

    public Double getMaxAmount() {
        return this.maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Double getBalanceAmount() {
        return this.balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuName() {
        return this.buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public BusinessUnit getBusinessUnit() {
        return this.businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getCustCode() {
        return this.custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.custCode = customer.getCustCode();
        }
        this.customer = customer;
    }

    public String getBranchCode() {
        return this.branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Set<CEJobOrderLineItem> getJobOrderLineItems() {
        if(this.jobOrderLineItems == null){
            return this.jobOrderLineItems = new HashSet<CEJobOrderLineItem>();
        }
        return this.jobOrderLineItems;
    }

    public void setJobOrderLineItems(Set<CEJobOrderLineItem> jobOrderLineItems) {
        this.jobOrderLineItems = jobOrderLineItems;
    }


}


