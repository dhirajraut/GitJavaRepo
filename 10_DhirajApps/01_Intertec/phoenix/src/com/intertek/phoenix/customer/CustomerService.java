/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.customer;

import com.intertek.entity.Contact;
import com.intertek.entity.Customer;
import com.intertek.phoenix.BaseService;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.entity.PurchaseOrder;

/**
 * This interface defines a service interface for Customer. Although the focus is on Customer,
 * the same interface is applicable to Customer of OC&A, CG and AS.
 * 
 * @author richard.qin
 */
public interface CustomerService extends BaseService{
    
    /**
     * Create a new Customer.
     * <p> Some default value may be automatically populated.
     * 
     * @return
     */
    public Customer createNewCustomer() throws PhoenixException;
    
    /**
     * Update a Customer.
     * @param customer
     * @return
     */
    public Customer upateCustomer(Customer customer) throws PhoenixException;
    
    /**
     * Permanently delete a customer from the system. 
     * <p>
     * Only a customer that has not been in force can be removed. A customer not in force is
     * typically a newly created customer that has not be associated with any purchase order,
     * job order, and has not been sent to external system for integration.
     * @param customer
     */
    public void deleteCustomer(Customer customer) throws PhoenixException;
    
    /**
     * Update a customer contact information.
     * @param contact
     * @return
     */
    public Contact updateContact(Contact contact) throws PhoenixException;
    
    /**
     * Remove a customer contact information.
     * <p> As Customers, a Contact can only be remove under some circumstances, such as a newly
     * created contact has never been used.
     * 
     * @param contact
     */
    public void removeContact(Contact contact) throws PhoenixException;
    
    /**
     * Add a new purchase order to a Customer.
     * <p> A Customer can have zero or more purchase orders.
     * 
     * @param customer
     * @param purchaseOrder
     * @return
     */
    public PurchaseOrder createPurchaseOrder(Customer customer) throws PhoenixException;
    
    /** 
     * Update a purchase order.
     * 
     * @param purchaseOrder
     * @return
     */
    public PurchaseOrder updatePurchaseOrder(PurchaseOrder purchaseOrder) throws PhoenixException;
    
    /**
     * Remove a purchase order.
     * <p> The rule of removing purchase orders are similar to removing Customer's.
     * @param purchaseOrder
     */
    public void removePurchaseOrder(PurchaseOrder purchaseOrder) throws PhoenixException;
    
}
