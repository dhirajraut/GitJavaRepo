/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.customer;

import com.intertek.entity.Contact;
import com.intertek.phoenix.BaseServiceImpl;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.entity.Customer;
import com.intertek.phoenix.entity.PurchaseOrder;

/**
 * 
 * @author richard.qin
 */
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {

    /**
     * @see com.intertek.phoenix.customer.CustomerService#addPurchaseOrder(com.intertek.phoenix.entity.Customer, com.intertek.phoenix.entity.PurchaseOrder)
     */
    @Override
    public PurchaseOrder createPurchaseOrder(Customer customer) throws PhoenixException {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        // set up some default data
        // TODO
        
        DaoManager.getDao(PurchaseOrder.class).saveOrUpdate(purchaseOrder);
        purchaseOrder.setCustomer(customer);
        return null;
    }

    /**
     * @see com.intertek.phoenix.customer.CustomerService#createNewCustomer()
     */
    @Override
    public Customer createNewCustomer() throws PhoenixException {
        Customer customer = new Customer();
        // setup some default data here
        // TODO
        
        DaoManager.getDao(Customer.class).saveOrUpdate(customer);
        return customer;
    }

    /**
     * @see com.intertek.phoenix.customer.CustomerService#removeCustomer(com.intertek.phoenix.entity.Customer)
     */
    @Override
    public void deleteCustomer(Customer customer) throws PhoenixException {
        // only a customer that is completely "new" can be deleted
        DaoManager.getDao(Customer.class).remove(customer);
       
    }

    /**
     * @see com.intertek.phoenix.customer.CustomerService#removeContact(com.intertek.phoenix.entity.CEContact)
     */
    @Override
    public void removeContact(Contact contact) throws PhoenixException {
        // TODO what are the rules?
        DaoManager.getDao(Contact.class).remove(contact);
    }

    /**
     * @see com.intertek.phoenix.customer.CustomerService#removePurchaseOrder(com.intertek.phoenix.entity.PurchaseOrder)
     */
    @Override
    public void removePurchaseOrder(PurchaseOrder purchaseOrder) throws PhoenixException {
        // TODO what are the rules?
        DaoManager.getDao(PurchaseOrder.class).remove(purchaseOrder);
    }

    /**
     * @see com.intertek.phoenix.customer.CustomerService#upateCustomer(com.intertek.phoenix.entity.Customer)
     */
    @Override
    public Customer upateCustomer(Customer customer) throws PhoenixException {
        // TODO what are the rules?
        return DaoManager.getDao(Customer.class).refresh(customer); // calls hibernate merge
    }

    /**
     * @see com.intertek.phoenix.customer.CustomerService#updateContact(com.intertek.phoenix.entity.CEContact)
     */
    @Override
    public Contact updateContact(Contact contact) throws PhoenixException {
        // TODO what are the rules?
        return DaoManager.getDao(Contact.class).refresh(contact); // calls hibernate merge
    }

    /**
     * @see com.intertek.phoenix.customer.CustomerService#updatePurchaseOrder(com.intertek.phoenix.entity.PurchaseOrder)
     */
    @Override
    public PurchaseOrder updatePurchaseOrder(PurchaseOrder purchaseOrder) throws PhoenixException {
        // TODO what are the rules?
        return DaoManager.getDao(PurchaseOrder.class).refresh(purchaseOrder); // calls hibernate merge
    }

}
