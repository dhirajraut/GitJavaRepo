/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing;

import com.intertek.entity.Customer;
import com.intertek.entity.Expression;
import com.intertek.entity.ServiceRate;
import com.intertek.entity.SlatePrice;
import com.intertek.entity.TestPrice;
import com.intertek.phoenix.entity.JobContractSlate;
import com.intertek.phoenix.entity.JobContractTest;
import com.intertek.phoenix.entity.JobContractService;
import com.intertek.phoenix.entity.JobContractServiceExpression;
import com.intertek.phoenix.entity.JobOrderType;
import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.phoenix.job.ContractJobOrder;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.phoenix.job.ServiceLevel;

/**
 * 
 * @author richard.qin
 */
public interface PricingSrvc {
    
    /**
     * Calculate the price for a expression of a service at the service level of a contract job order.
     * @param contractJobOrder
     * @param serviceLevel
     * @param service
     * @param expression
     * @return
     * @throws PricingSrvcException
     */
    public PricingInfo calculateServicePrice(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, 
                                             JobContractService service, JobContractServiceExpression expression)
                                             throws PricingSrvcException, JobSrvcException;
    
    /**
     * Calculate the price for a test at the service level of a contract job order.
     * @param contractJobOrder
     * @param serviceLevel
     * @param jobContractTest
     * @return
     * @throws PricingSrvcException
     * @throws JobSrvcException
     */
    public PricingInfo calculateTestPrice(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, 
                                          JobContractTest jobContractTest) 
                                          throws PricingSrvcException, JobSrvcException;

    /**
     * Calculate the price for a slate at the service level of a contract job order.
     * @param contractJobOrder
     * @param serviceLevel
     * @param slate
     * @return
     * @throws PricingSrvcException
     * @throws JobSrvcException
     */
    public PricingInfo calculateSlatePrice(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, 
                                           JobContractSlate jobContractSlate) 
                                           throws PricingSrvcException, JobSrvcException;

    /**
     * @param expression
     * @param jobType
     * @param jobCode
     * @param branchCode
     * @param serviceLevel
     * @param userGroupId Don't know this parameter is defined in the old phoenix
     * @return
     * @throws JobSrvcException
     */
    public AccountInfo getAccountInfo(Expression expression, JobOrderType jobType, String productType, 
                                      String branchCode, ServiceLevel serviceLevel, Customer customer,
                                      ServiceOffering serviceOffering) throws JobSrvcException;

    /**
     * @param contractJobOrder
     * @param serviceLevel
     * @param test
     * @return
     * @throws PricingSrvcException
     * @throws JobSrvcException
     */
    TestPrice getTestPrice(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, JobContractTest test) 
                           throws PricingSrvcException, JobSrvcException;

    /**
     * @param contractJobOrder
     * @param serviceLevel
     * @param slate
     * @return
     * @throws PricingSrvcException
     * @throws JobSrvcException
     */
    SlatePrice getSlatePrice(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, JobContractSlate slate) 
                             throws PricingSrvcException, JobSrvcException;

    /**
     * @param param
     * @return
     * @throws PricingSrvcException
     */
    ServiceRate getServicePrice(PricingParameter param) throws PricingSrvcException;

}
