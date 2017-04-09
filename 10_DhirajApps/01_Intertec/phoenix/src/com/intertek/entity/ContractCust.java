package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * ContractCust generated by hbm2java
 */
public class ContractCust  implements java.io.Serializable {

    // Fields    

     private ContractCustId contractCustId;
     private Customer customer;
     private Contract contract;
     @Length(min=0, max=1) private String status;

     // Constructors

    /** default constructor */
    public ContractCust() {
    }

	/** minimal constructor */
    public ContractCust(ContractCustId contractCustId) {
        this.contractCustId = contractCustId;
    }
    /** full constructor */
    public ContractCust(ContractCustId contractCustId, Customer customer, Contract contract, String status) {
       this.contractCustId = contractCustId;
       this.customer = customer;
       this.contract = contract;
       this.status = status;
    }
   
    // Property accessors
    public ContractCustId getContractCustId() {
        return this.contractCustId;
    }
    
    public void setContractCustId(ContractCustId contractCustId) {
        this.contractCustId = contractCustId;
    }
    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Contract getContract() {
        return this.contract;
    }
    
    public void setContract(Contract contract) {
        this.contract = contract;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ContractCust) ) return false;
		 ContractCust castOther = ( ContractCust ) other; 
         
		 return ( (this.getContractCustId()==castOther.getContractCustId()) || ( this.getContractCustId()!=null && castOther.getContractCustId()!=null && this.getContractCustId().equals(castOther.getContractCustId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContractCustId() == null ? 0 : this.getContractCustId().hashCode() );
         
         
         
         return result;
   }   


}