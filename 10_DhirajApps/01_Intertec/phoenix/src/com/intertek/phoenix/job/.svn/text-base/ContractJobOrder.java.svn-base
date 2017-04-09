/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.job;

import com.intertek.entity.Contract;
import com.intertek.entity.Customer;

/**
 * ContracdtJobOrder is a JobOrder associated with a Contract. A typical
 * JobOrder contains one or more Contracts, for each Contract, there is 
 * corresponding ContractJobOrder.
 * 
 * This interface defines methods that are needed by the pricing engine.
 * 
 * @author richard.qin
 */
public interface ContractJobOrder {
    Order getJobOrder();
    Contract getContract();
    String getZone();
    String getLanguage();
    String getTransactionCurrencyCode();
    String getContractCurrencyCode();
    Double getOverrideCurrencyRate();
    String getProductType();
    Customer getCustomer();
}
