/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.job;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.intertek.entity.BranchCode;
import com.intertek.entity.CfgContract;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.Control;
import com.intertek.entity.ControlMap;
import com.intertek.entity.Department;
import com.intertek.entity.Expression;
import com.intertek.entity.ExpressionGLCode;
import com.intertek.entity.HighLevelService;
import com.intertek.entity.PriceBook;
import com.intertek.entity.ProductGroup;
import com.intertek.entity.Service;
import com.intertek.entity.ServiceId;
import com.intertek.entity.ServiceType;
import com.intertek.entity.Slate;
import com.intertek.entity.Test;
import com.intertek.entity.TestProduct;
import com.intertek.entity.VesselType;
import com.intertek.phoenix.BaseService;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.entity.BillingEvent;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.ContractServiceLevel;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.Estimation;
import com.intertek.phoenix.entity.IntegrationHistory;
import com.intertek.phoenix.entity.JobContractService;
import com.intertek.phoenix.entity.JobContractServiceControl;
import com.intertek.phoenix.entity.JobContractSlate;
import com.intertek.phoenix.entity.JobContractTest;
import com.intertek.phoenix.entity.JobOrderAttachment;
import com.intertek.phoenix.entity.JobOrderLineItemAttachment;
import com.intertek.phoenix.entity.JobOrderLineItemNote;
import com.intertek.phoenix.entity.JobOrderNote;
import com.intertek.phoenix.entity.JobOrderType;
import com.intertek.phoenix.entity.JobService;
import com.intertek.phoenix.entity.JobServiceControl;
import com.intertek.phoenix.entity.JobServiceLevel;
import com.intertek.phoenix.entity.JobSlate;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.JobTestAttachment;
import com.intertek.phoenix.entity.JobTestNote;
import com.intertek.phoenix.entity.Period;
import com.intertek.phoenix.entity.Quote;
import com.intertek.phoenix.entity.RevenueSegregation;
import com.intertek.phoenix.entity.SampleTracking;
import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.phoenix.entity.value.SlateInfo;
import com.intertek.phoenix.entity.value.TestInfo;
import com.intertek.phoenix.job.ServiceLevel.ServiceLevelType;
import com.intertek.phoenix.pricing.AccountInfo;
import com.intertek.phoenix.pricing.PricingSrvcException;

/**
 * The interface that exposes Job Order related business functionalities for CE.
 * In the future, this interface can be enhanced to support both OC&A and other
 * Intertek business streams.
 * 
 * @author richard.qin
 */
public interface JobOrderService extends BaseService{

    
    ///////////////////////////////////////////////////////////////////////////
    // methods for lookup reference data
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * Get Service definition.
     * <p> 
     * The PS_ITSC_SERVICE table's primary key is disabled, this creates big
     * problem when other entity needs to maintain a referential relationship to
     * Service. This method is a workaround so that instead of navigating from
     * the foreigh entity to a Service instance, this method is called to retrieve
     * the required Service instance.
     * @throws JobSrvcException 
     */
    Service getService(String contractId, String serviceName, String parentServiceId, Date date) throws JobSrvcException;
    
    Service getService(ServiceId serviceId) throws JobSrvcException;
    
    /**
     * Load all TestProduct.
     * <p>Only referenced in Dropdowns, but not sure how this will be used.
     */
    List<TestProduct> getTestProducts() throws JobSrvcException;
    
    /**
     * Retrieve a complete list of revenue segragation Expressions for a Test.
     * <p>
     * Note, the list of RevenueSegregations returned will not be persisted. 
     * To support revenue segregation for Tests, Tests need to be treated like
     * Services. Currently, all Tests share a same "Service Id" in the 
     * PS_ITSC_CONTR_EXPN table, therefore the same set of Expressions will be
     * used by all Tests for the revenue segregation's purposes. 
     * <P>
     * Alternatively, different Tests can  be treated as different "Services". 
     * In this case, either each Test type defines its own set of Expressions,
     * or a mapping table needs to be introduced so that multiple Tests can 
     * share a same set of Expressions.
     * 
     * @param contractJobOrder Needed for contract id
     * @param serviceLevel Needed to get "vessel" or "lot" information 
     * @param jobContractTest May not be needed currently, as all Tests share 
     * the same "Service Id", but future implementation may change.
     * @return
     * @throws JobSrvcException
     */
    public List<RevenueSegregation> getRevenueSegregationForTest(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, 
                                                                 JobContractTest jobContractTest) throws JobSrvcException ;
    /**
     * Get a list of ServiceTypes defined for a jobType
     * <P> What are the valid jobType values for CE, CG and AS?
     * @param jobType Can be FST, INSP, MAR, OUT.
     * @return
     * @throws JobSrvcException
     */
    List<ServiceType> getServiceTypes(String jobType) throws JobSrvcException;
    
    /**
     * Get a list of VesselType for the given vesselTypeSetName and an effectiveDate.
     * <p>A contractId can also be used as the vesselTypeSetName, or the constant
     * "pricebook" can also be used. TODO verify that this is true.
     * <p>A VesselType is used to construct a ServiceLevel.
     * @param vesselTypeSetName Can also be a contract id
     * @param effectiveDate The job nomination data
     * @return
     * @throws JobSrvcException 
     */
    List<VesselType> getVesselTypes(String vesselTypeSetName, Date effectiveDate) throws JobSrvcException;
    
    /**
     * Get a list of ProductGroup for a given productGroupSet and an effectiveDate.
     * <p>A contractId can also be used as the productGroupSetName. TODO verify this.
     * <p>A productGroup is used to construct a ServiceLevel.
     * @param productGroupSetName
     * @param effectiveDate
     * @return
     * @throws JobSrvcException 
     */
    List<ProductGroup> getProductGroups(String productGroupSetName, Date effectiveDate) throws JobSrvcException;
    
    /**
     * Get a list of top level services.
     * <p>this could be the starting point for adding services, however, I think
     * the real starting point is <code>GetServices()</code>, which loads Services 
     * using contractID. TODO verify this.
     * Note, should we allow parameters such as is_pricebook?
     * @return
     * @throws JobSrvcException 
     */
    List<HighLevelService> getHighLevelServices() throws JobSrvcException;
    
    /**
     * Get the current contract for the contract id and the chosen date.
     * @param contractId The contract id, available in JobContract, ContractCustContact,
     * ContractCust, and many other entities.
     * @param date The nomination date
     * @return
     * @throws JobSrvcException 
     */
    CfgContract getCfgContractByContractId(String contractId, Date date) throws JobSrvcException;
    
    /**
     * Get the price book.
     * @param pbSeries
     * @param currencyCD
     * @param nominationDate
     * @return
     * @throws JobSrvcException 
     */
    PriceBook getPriceBook(String pbSeries, String currencyCD, Date nominationDate) throws JobSrvcException;
    
    /**
     * Retrieve a list of services for a given contract at a given service level.
     * The level of services offered are determined by the business divisions.
     * For example, OC&A has service levels as Vessle, Lot, Product Group, etc.
     * CE has Job level Services. Other divisions have different service levels.
     * <p>
     * However, I have not seen anything like this in the old phoenix. Use the 
     * next method instead.
     * 
     * @param contract
     * @param serviceLevel
     * @return
     */
    List<Service> getServices(CfgContract contract, ServiceLevelType serviceLevel) throws JobSrvcException;
    
    /**
     * Retrieve all applicable services for the given contract or pricebook,
     * nomination data and language combination.
     * 
     * @param contract
     * @param nominationDate
     * @param languageCD Language is available in JobContract, which is either
     * copied from User entity or default to "English"
     * @return
     * @throws JobSrvcException 
     */
    List<Service> getServices(CfgContract contract, Date nominationDate, String language) throws JobSrvcException;

    /**
     * Retrieve a list of Controls for the given contract and service. 
     * <p>
     * Every Service has a collection of Controls which drives the pricing logic.
     * 
     * @param serviceName
     * @param contract
     * @param nominationDate
     * @param lang
     * @return
     * @throws JobSrvcException 
     */
    List<Control> getServiceControls(CfgContract contract, String serviceName, 
                                     Date nominationDate, String lang) throws JobSrvcException;
    
    /**
     * Retrieve a list Contract Expressions for the given contract and service.
     * <p>
     * Every Serice defines a set of expressions that are used to generate JOLIs
     * 
     * @param contract
     * @param serviceName
     * @param location
     * @param nominationDate
     * @param lang
     * @return
     * @throws JobSrvcException 
     */
    List<ContractExpression> getServiceExpressions(CfgContract contract, String serviceName, String location,
                                                   Date nominationDate, String lang) throws JobSrvcException;

    /**
     * Get Expression by expression Id
     * @param expressionId
     * @return
     * @throws JobSrvcException 
     */
    Expression getExpressionByExpressionId(String expressionId) throws JobSrvcException;
    
    /**
     * Retrieve Control Maps for the given service and expression.
     * <p> Service Expression and Control have a many to many relationship.
     * @param contract
     * @param serviceName
     * @param expressionId
     * @return
     * @throws JobSrvcException 
     */
    List<ControlMap> getControlMapsByExpression(CfgContract contract, String serviceName, String expressionId) throws JobSrvcException;
    List<ControlMap> getControlMapsByControl(CfgContract contract, String serviceName, String expressionId) throws JobSrvcException;

    /**
     * Retrieve all available Tests for a given contract.
     * <p> this method can be used to populate a popup page for selecting Tests.
     * <p> This method is ported from Phoenix 1, which is somehow inconsistant
     * with other methods that the return objects are not entities. In phoenix 1,
     * this function is called from a search page, all the parameters are required
     * by the underlying stored procedure.
     * 
     * @param contract
     * @param productGroup
     * @param contractSearchCD
     * @param value
     * @param searchType
     * @param location
     * @param nominationDateStr
     * @param languageCD
     * @return
     * @throws JobSrvcException 
     */
    List<TestInfo> getTestInfos(CfgContract contract, String productGroup, String contractSearchCD,
                                String value, String searchType, String location,
                                Date nominationDate, String languageCD) throws JobSrvcException;
        
    /**
     * Retrieve all available Tests for a given contract.
     * <p> Similar to getTestInfos() except that the returned objects are Test entites.
     * @param contract
     * @param productGroup
     * @param contractSearchCD
     * @param value
     * @param searchType
     * @param location
     * @param nominationDate
     * @param languageCD
     * @return
     * @throws JobSrvcException
     */
    List<Test> getTests(CfgContract contract, String productGroup, String contractSearchCD,
                        String value, String searchType, String location,
                        Date nominationDate, String languageCD) throws JobSrvcException;
        
    /**
     * Retrieve all available Slates for a given contract.
     * <p> this method can be used to populate a popup page for selecting Slates.
     * <p> This method is ported from Phoenix 1, which is somehow inconsistant
     * with other methods that the return objects are not entities. In phoenix 1,
     * this function is called from a search page, all the parameters are required
     * by the underlying stored procedure.
     * @param contract
     * @param value
     * @param searchType
     * @param location
     * @param nominationDateStr
     * @param languageCD
     * @return
     * @throws JobSrvcException 
     */
    List<SlateInfo> getSlateInfos(CfgContract contract, String value, String searchType,
                                  String location, Date nominationDate, String languageCD) throws JobSrvcException;

    /**
     * Retrieve all available Slates for a given contract.
     * <p> Similar to getSlateInfos() except that the returned objects are Slate entites.
     * 
     * @param contract
     * @param value
     * @param searchType
     * @param location
     * @param nominationDate
     * @param languageCD
     * @return
     * @throws JobSrvcException
     */
    List<Slate> getSlates(CfgContract contract, String value, String searchType,
                          String location, Date nominationDate, String languageCD) throws JobSrvcException;

    ///////////////////////////////////////////////////////////////////////////
    // the next group of methods are primarily defined to support GL code lookup.
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * @param expressionId
     * @param jobType
     * @return
     */
    ExpressionGLCode getExpressionGLCode(String expressionId, JobOrderType jobType) throws JobSrvcException;

//    /** This method is available in PricingSrvc, may need to move it back
//     * 
//     * @param expression
//     * @param jobType
//     * @param jobCode
//     * @param branchCode
//     * @param serviceLevel
//     * @param userGroupId
//     * @return
//     * @throws JobSrvcException
//     */
//    public AccountInfo getAccountInfo(Expression expression, JobOrderType jobType, String jobCode, 
//                                      String branchCode, ServiceLevel serviceLevel)
//                                      throws JobSrvcException;
    
   /**
     * @param code
     * @return
     */
    Department getDepartmentByGlCode(String code) throws JobSrvcException;

    /**
     * @param branchCode
     * @return
     * @throws JobSrvcException 
     */
    BranchCode getBranchCodeByBranchCode(String branchCode) throws JobSrvcException;

    /**
     * @param code
     * @param jobCode
     * @param masterGroupId
     * @return
     */
    String getProductCode(ExpressionGLCode code, String jobCode, String masterGroupId) 
                          throws JobSrvcException;
    
    ///////////////////////////////////////////////////////////////////////////
    // the remaining methods are designed to support constructing the common
    // service structures.
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * Create a new ServiceLevel in the Service Structure under <code>
     * parentServiceLevel</code>. The new ServiceLevel object needs to be 
     * populated with user input.
     * <p> The type of the service is determined by the <code>type</type>
     * argument.
     * @param contractId Used as a key to look up VesselType for a vessel service
     * level, ProductGroup for a lot service level, so on so forth. 
     * @param parentServiceLevel The existing ServiceLevel where the new 
     * ServiceLevel will be added.
     * @param type The type of ServiceLevel, which determines which type of 
     * ServiceLevel will be returned.
     * @return The newly created ServiceLevel object, which will be added to
     * the parentServiceLevel, if successful.
     * @throws JobSrvcException
     */
    JobServiceLevel createJobServiceLevel(JobServiceLevel parent, ServiceLevelType type,
                                          String serviceLevelName) throws JobSrvcException;
    
    /**
     * Create a ContractServiceLevel
     * @param parent
     * @param type
     * @param serviceLevelName
     * @return
     * @throws JobSrvcException
     */
    ContractServiceLevel createContractServiceLevel(ContractServiceLevel parent, ServiceLevelType type,
                                          String serviceLevelName) throws JobSrvcException;
    
    /**
     * Remove a ServiceLevel from its parent ServiceLevel.
     * @param parentServiceLevel
     * @param toRemove
     * @throws JobSrvcException
     */
    void removeJobServiceLevel(JobServiceLevel parent, JobServiceLevel toRemove)
                               throws JobSrvcException;
    
    /**
     * Remove a ContractServiceLevel
     * @param parent
     * @param toRemove
     * @throws JobSrvcException
     */
    void removeContractServiceLevel(ContractServiceLevel parent, ContractServiceLevel toRemove)
                                    throws JobSrvcException;

    /**
     * Create a new JobTest at the given ServiceLevel.
     * <p>
     * The newly created JobTest will be added to <code>serviceLevel</code>
     * and with available Test information populated.
     * <p> To create a manual test, set <code>test</code> to <code>null</code>
     * @param contract
     * @param serviceLevel
     * @param test The reference Test object
     * @return
     * @throws JobSrvcException
     */
    JobTest createJobTest(JobServiceLevel serviceLevel, Test test) throws JobSrvcException;
    
    /**
     * Create a "split" JobTest.
     * <p> 
     * To create a split manual test, set <code>test</code> to <code>null</code>
     * @param serviceLevel
     * @param test
     * @param master
     * @return
     * @throws JobSrvcException
     */
    JobTest createRelatedJobTest(JobServiceLevel serviceLevel, Test test, JobTest master) 
                                 throws JobSrvcException;

    /**
     * Create a new JobContractTest with a JobTest. This method is to be
     * called when a new JobContractTest is created on "Select Charges"
     * page.
     * <p> To create a manual test, set <code>test</code> to <code>null</code>
     * 
     * @param serviceLevel
     * @param test
     * @return
     * @throws JobSrvcException
     */
    JobContractTest createJobContractTest(ContractServiceLevel serviceLevel, Test test) 
                                          throws JobSrvcException;

    /**
     * Create a new JobContractTest based on a JobTest object.
     * @param serviceLevel
     * @param test
     * @return
     * @throws JobSrvcException
     */
    JobContractTest createOrUpdateJobContractTest(ContractServiceLevel serviceLevel, JobTest test) 
                                          throws JobSrvcException;

    /**
     * Remove a JobTest from the given JobOrder.
     * 
     * @param jobOrder
     * @param test
     */
    void removeJobTest(JobTest test) throws JobSrvcException;

    /**
     * Remove a JobContractTest
     * @param test
     * @throws JobSrvcException
     */
    void removeJobContractTest(JobContractTest test) throws JobSrvcException;
    
    /**
     * Create a new JobService at the given ServiceLevel.
     * <p> the newly created JobService will be added to serviceLevel
     * @param contract
     * @param serviceLevel
     * @param service The reference Service object
     * @return
     * @throws JobSrvcException
     */
    JobService createJobService(JobServiceLevel serviceLevel, Service service) throws JobSrvcException;
    
    /**
     * Create a new JobContractService with a JobService object. This method 
     * is to be called when a JobContractService is created on the "select
     * charges" page.
     * 
     * @param serviceLevel
     * @param service
     * @return
     * @throws JobSrvcException
     */
    JobContractService createJobContractService(ContractServiceLevel serviceLevel, 
                                                Service service) throws JobSrvcException;
    
    /**
     * Create a new JobContractService based on a JobService object.
     * @param serviceLevel
     * @param service
     * @return
     * @throws JobSrvcException
     */
    JobContractService createOrUpdateJobContractService(ContractServiceLevel serviceLevel, 
                                                JobService service) throws JobSrvcException;
    
    /**
     * Remove a JobService instance from the serviceLevel.
     * @param serviceLevel
     * @param jobService
     * @throws JobSrvcException 
     */
    void removeJobService(JobService service) throws JobSrvcException;
    
    /**
     * Remove a JobContractService.
     * @param service
     * @throws JobSrvcException
     */
    void removeJobContractService(JobContractService service) throws JobSrvcException;
    
    /**
     * Create a new service Control instance and add it to the jobService object.
     * <p>
     * 
     * @param jobService
     * @param control
     * @return
     */
    JobServiceControl createJobServiceControl(JobService jobService, Control control) 
                                                throws JobSrvcException;
    
    /**
     * Create a new service Control without a JobServiceControl object.
     * @param jobContractService
     * @param control
     * @return
     * @throws JobSrvcException
     */
    JobContractServiceControl createJobContractServiceControl(JobContractService jobContractService, 
                                                              Control control) throws JobSrvcException;

    /**
     * Create a new JobContractServiceControl based on a JobServiceControl.
     * @param jobContractService
     * @param control
     * @return
     * @throws JobSrvcException
     */
    JobContractServiceControl createJobContractServiceControl(JobContractService jobContractService, 
                                                              JobServiceControl control) throws JobSrvcException;

    /**
     * Remove an existing service Control instance from the jobService object.
     * @param jobService
     * @param control
     * @throws JobSrvcException
     */
    void removeJobServiceControl(JobService jobService, JobServiceControl control) 
                                 throws JobSrvcException;
    
    /**
     * Remove a JobContractServiceControl.
     * @param jobService
     * @param control
     * @throws JobSrvcException
     */
    void removeJobContractServiceControl(JobContractService jobService, JobContractServiceControl control) 
                                 throws JobSrvcException;
    
    /**
     * Create a new JobTest at the given ServiceLevel, based on a Slate.
     * @param contract
     * @param serviceLevel
     * @param slate The reference Slate object
     * @return
     * @throws JobSrvcException
     */
    JobSlate createJobSlate(JobServiceLevel serviceLevel, Slate slate) throws JobSrvcException;
    
    /**
     * Create a new JobContractSlate without a JobSlate.
     * @param serviceLevel
     * @param slate
     * @return
     * @throws JobSrvcException
     */
    JobContractSlate createJobContractSlate(ContractServiceLevel serviceLevel, Slate slate) 
                                            throws JobSrvcException;
    
    /**
     * Create a new JobContractSlate based on a JobSlate.
     * @param serviceLevel
     * @param slate
     * @return
     * @throws JobSrvcException
     */
    JobContractSlate createOrUpdateJobContractSlate(ContractServiceLevel serviceLevel, JobSlate slate) 
                                            throws JobSrvcException;

    /**
     * Remove a JobSlate from the given serviceLevel.
     * 
     * @param serviceLevel
     * @param jobSlate
     * @throws JobSrvcException 
     */
    void removeJobSlate(JobSlate slate) throws JobSrvcException;
    
    /**
     * Remove a JobContractSlate.
     * @param slate
     * @throws JobSrvcException
     */
    void removeJobContractSlate(JobContractSlate slate) throws JobSrvcException;
    
    /**
     * Update  CE JobTest from the given CEJobOrder.
     * 
     * @param JobOrder
     * @throws InvalidJobOrderOperationException 
     */
    void updateCEJobTest(CEJobOrder jobOrder) throws InvalidJobOrderOperationException;
    
    /**
     * Update  CE Instruction from the given CEJobOrder.
     * 
     * @param JobOrder
     * @throws InvalidJobOrderOperationException 
     */
    void updateJobInstructions(CEJobOrder jobOrder) throws InvalidJobOrderOperationException;
    
//    /**
//     * Update  DepositInvoice from the given CEJobOrder.
//     * 
//     * @param jobContract
//     * @throws InvalidJobOrderOperationException 
//     */
//    void updateDepositInvoice(CEJobContract jobContract) throws InvalidJobOrderOperationException;
    
    /**
     * Remove  DepositInvoice from the given CEJobOrder.
     * @param jobContract
     * @param DepositInvoice
     * @throws InvalidJobOrderOperationException 
     */
    public void removeDepositInvoice(CEJobContract jobContract, DepositInvoice depositInvoice) throws InvalidJobOrderOperationException ;
    
    // TODO add split methods
	/**
	 * Create a new CEJobOrder based on a Quote submitted from ECS.
	 * <p>
	 * Some of job order details will be available from Quote, and Quote line
	 * items will be mapped to job order line items.
	 * 
	 * @param quote
	 * @return A new CEJobOrder
	 * @throws JobOrderCreationException
	 *             whenever a new CE job order cannot created
	 */
	public CEJobOrder createJobOrder(Quote quote) throws JobOrderCreationException, DaoException;

	/**
	 * Create a new CEJobOrder without a ECS quote.
	 * <p>
	 * After this method returns successfully, a new CEJobOrder exists within
	 * the phoenix system.
	 * 
	 * @param jo
	 * @return
	 * @throws JobOrderCreationException
	 */
	public CEJobOrder createJobOrder() throws JobOrderCreationException;

	/**
	 * Validate a CEJobOrder. A CE job order may need to satisfy certain
	 * conditions before it can be executed.
	 * 
	 * @param jobOrder
	 * @return TODO should return some more meaningful ValidationStatus
	 * @throws JobSrvcException 
	 */
	public void validateJobOrder(CEJobOrder jobOrder) throws JobSrvcException;

	/**
	 * Add attachment to a JobOrder
	 * 
	 * @param jobOrder
	 * @param attachment
	 * @return
	 * @throws InvalidJobOrderOperationException
	 */
	public void addAttachment(CEJobContract jobContract, JobOrderAttachment attachment, String path, MultipartFile file) throws InvalidJobOrderOperationException;

	public void addAttachment(CEJobOrderLineItem lineItem, JobOrderLineItemAttachment attachment) throws InvalidJobOrderOperationException;

    public void addAttachment(JobTest jobTest, JobTestAttachment attachment,String path, MultipartFile file) throws InvalidJobOrderOperationException;

	/**
	 * Add some notes to a job order
	 * 
	 * @param lineItem
	 * @param note
	 * @return
	 * @throws InvalidJobOrderOperationException
	 */
	public void addNotes(CEJobOrderLineItem lineItem, JobOrderLineItemNote... notes) throws InvalidJobOrderOperationException;

    public void addNotes(CEJobContract jobContract, JobOrderNote... notes) throws InvalidJobOrderOperationException;

    public void addNotes(JobTest jobTest, JobTestNote... notes) throws InvalidJobOrderOperationException;

    /**
	 * Update a CE job order, some validation rules may apply.
	 * 
	 * @param jobOrder
	 * @param customerDetail 
	 * @param inputField A comma separated string of something
	 * @return
	 * @throws InvalidJobOrderOperationException
	 */
	public CEJobOrder updateJobOrder(CEJobOrder jobOrder) throws InvalidJobOrderOperationException;

	/**
	 * Create a new CEJobContract for the given CEJobContract, populate the
	 * object with the information provided in ContractCustContact.
	 * @param jobOrder
	 * @param contractCustContact
	 * @return
	 * @throws InvalidJobOrderOperationException
	 */
	//@Deprecated Use addCustomer() instead
//	public CEJobContract addJobContract(CEJobOrder jobOrder, ContractCustContact contractCustContact) throws InvalidJobOrderOperationException;
	
	/**
	 * Get the billable JOLIs for the given job contract
	 * 
	 * @param jobContract
	 * @return
	 * @throws InvalidJobOrderOperationException
	 */
	public List<CEJobOrderLineItem> getBillableJobOrderLineItems(CEJobContract jobContract) throws InvalidJobOrderOperationException;

    public List<CEJobOrderLineItem> getBillableJobOrderLineItems(CEJobContract jobContract, SortInfo sort) throws InvalidJobOrderOperationException;

	/**
	 * Collect integration event history for a CE job order. Depends on the user
	 * privilege, not every user is able to see the same information.
	 * 
	 * @param jobOrder
	 * @return
	 * @throws JobSrvcException 
	 */
	public List<IntegrationHistory> getJobOrderIntegrationHistory(CEJobOrder jobOrder, Period period) throws JobSrvcException;

//	/**
//	 * Add a deposit invoice to the given CE job order. The deposit invoice can
//	 * only be used to pay normal invoices issued for the same job order.
//	 * 
//	 * @param jobOrder
//	 * @param depositInvoice
//	 * @return
//	 */
//    public void addDepositInvoice(CEJobContract jobContract, DepositInvoice depositInvoice) throws InvalidJobOrderOperationException;
//
	/**
	 * Remove a JobOrder from the system.
	 * <p>
	 * If a job order is not in force, the job order will be removed from the
	 * system completely, along with all the job order line items. Otherwise, a
	 * completed job will be retired and archived. A job order with other status
	 * will not be removed, and an exception will be thrown.
	 * 
	 * @param jobOrder
	 */
	public void removeJobOrder(CEJobOrder jobOrder) throws InvalidJobOrderOperationException;

    /**
     * Remove one or more notes from the given JobContract
     * @param jobOrderContract
     * @param notes
     * @throws InvalidJobOrderOperationException
     */
    public void removeNotes(CEJobContract jobContract, JobOrderNote... notes) throws InvalidJobOrderOperationException;

    /**
     * Remove one or more notes from the given JobContract
     * @param jobOrderContract
     * @param notes
     * @throws InvalidJobOrderOperationException
     */
    public void removeNotes(JobTest jobTest, JobTestNote... notes) throws InvalidJobOrderOperationException;

    /**
     * Remove one or more notes from the given JobOrderLineItem
     * @param jobOrderLineItem
     * @param notes
     * @throws InvalidJobOrderOperationException
     */
    public void removeNotes(CEJobOrderLineItem jobOrderLineItem, JobOrderLineItemNote... notes) throws InvalidJobOrderOperationException;
	
	/**
	 * Remove one or mroe attachments from the given JobOrder
	 * @param jobOrder
	 * @param attachments
	 * @throws InvalidJobOrderOperationException
	 */
    @Deprecated
	public void removeAttachments(CEJobOrder jobOrder, JobOrderAttachment... attachments) throws InvalidJobOrderOperationException;
	
    /**
     * Remove one or mroe attachments from the given JobOrderLineItem
     * @param jobOrderLineItem
     * @param attachments
     * @throws InvalidJobOrderOperationException
     */
    public void removeAttachments(CEJobOrderLineItem jobOrderLineItem, JobOrderLineItemAttachment... attachments) throws InvalidJobOrderOperationException;

    /**
     * Remove one or mroe attachments from the given JobOrderLineItem
     * @param jobOrderLineItem
     * @param attachments
     * @throws InvalidJobOrderOperationException
     */
    public void removeAttachments(JobTest jobTest, JobTestAttachment... attachments) throws InvalidJobOrderOperationException;

    /**
	 * Add a customer to the given JobOrder.
	 * <p>If there is a customer already added to the JobOrder, then the other
	 * customer must be first removed from the JobOrder.
	 * <p>Adding a customer to a JobOrder will automatically add the default
	 * shipping contact, default billing contact and some other default 
	 * information related to the customer.
	 * @param jobOrder
	 * @param customer
     * @throws JobSrvcException 
	 */
	public CEJobContract addCustomer(CEJobOrder jobOrder, ContractCustContact contractCustContact) throws JobSrvcException ;
	
    /**
     * Remove a customer from the given JobOrder.
     * <p>
     * After a customer is added, the user could delete the added customer, in
     * case incorrect selection is made. The rules to allow a deletion are: 
     * 1. The customer did not originate from ECS 
     * 2. All line items successfully deleted in the job instructions page
     * 
     * @param customer
     */
    public void removeContract(CEJobOrder jobOrder, CEJobContract jobContract) throws InvalidJobOrderOperationException;

    /**
     * updates the CEJobOrderLineItem with new RevenueSegregation list. 
     * Creates LineItemUpdate object to store the update information
     * @param lineItem CEJobOrderLineItem
     * @param rsSet RevenueSergregation set
     * @throws DaoException 
     * @throws JobSrvcException 
     */
    // TODO RevSeg should be automatically taken care of by the Pricing logic
    public void updateRevenueSegregations(CEJobOrderLineItem lineItem, Set<RevenueSegregation> rsSet) throws DaoException, JobSrvcException;
    
    /**
     * Update the JOLI that is related to the jobTest. If a related JOLI cannot be
     * found, a new JOLI will be created, otherwise, the existing JOLI will be
     * updated with the latest pricing information.
     * 
     * @param jobOrder
     * @param jobTest
     * @return
     * @throws InvalidJobOrderOperationException
     */
    public CEJobOrderLineItem updateJobOrderLineItem(ContractJobOrder contractJobOrder, ServiceLevel level, 
                                                     JobContractTest jobTest) 
                                                     throws PricingSrvcException, JobSrvcException, InvalidJobOrderOperationException ;
    
    public CEJobOrderLineItem updateJobOrderLineItem(ContractJobOrder contractJobOrder, ServiceLevel level, 
                                                     JobContractSlate jobSlate) 
                                                     throws PricingSrvcException, JobSrvcException;
    
    public Collection<CEJobOrderLineItem> updateJobOrderLineItem(ContractJobOrder contractJobOrder, ServiceLevel level, 
                                                                 JobContractService jobService) 
                                                                 throws PricingSrvcException, JobSrvcException, InvalidJobOrderOperationException;

    /**
     * create a new DepositInvoice 
     * 
     * @param jobContract CEJobContract
     * @return DepositInvoice
     * @throws JobSrvcException 
     */
    // TODO check if this is correct, cross check this with addDepositInvoice()
    public DepositInvoice createDepositInvoice(CEJobContract jobContract) throws JobSrvcException;

    /**
     * set the default instructions object 
     * 
     * @param ceJobOrder CEJobOrRder
     * @return DepositInvoice
     * @throws JobSrvcException 
     */
    // TODO refactor the name
    public void setDefaultInstructions(CEJobOrder ceJobOrder) throws JobSrvcException;
    
    /**
     * This method is created for saving a new JobOrder purposefully.
     * @param jobOrder
     * @return
     * @throws InvalidJobOrderOperationException
     */
    CEJobOrder saveJobOrder(CEJobOrder jobOrder) throws JobSrvcException;

    public void updateCustomer(CEJobOrder jobOrder, CEJobContract jobContract) throws JobSrvcException;

    public void updateContractNote(CEJobOrder jobOrder, CEJobContract jobContract,JobOrderNote note) throws JobSrvcException ;

    public void updateJobTestNote(JobTest jobTest, JobTestNote note) throws JobSrvcException ;

    public void updateJobTest(JobTest jobTest) throws DaoException ;

    public Estimation createEstimation(JobTest jobTest) throws JobSrvcException;

    public void removeEstimation(JobTest jobTest, Estimation estimation) throws JobSrvcException;
    /**
     * This method is created for saving a new job order purposefully after updating the joborderattachment.
     * @param jobOrder
     * @param jobContract
     * @param attachment
     * @throws InvalidJobOrderOperationException
     */
    public void updateContractAttachment(CEJobOrder jobOrder, CEJobContract jobContract,JobOrderAttachment attachment) throws JobSrvcException ;

    public CEJobContract getJobContract(String JobNumber, String jobContractId) throws JobSrvcException ;
    
    public List<ServiceOffering> getServiceOffering(String testId)throws JobSrvcException;
    
    /**
     * Create ContractServiceLevel with respect to jobServiceLevel
     * @param joco
     * @param jobSL
     * @return new contractServiceLevel
     * @throws JobSrvcException
     */
    public ContractServiceLevel createContractServiceLevel(CEJobContract joco, JobServiceLevel jobSL) throws JobSrvcException;

    /**
     * When a job order is not from ECS, user can enter quote issue date and quote number.
     * This method is to create or update a quote based on the input data.
     * 
     * @param jobOrder
     * @param quoteDate
     * @param quoteNumber
     */
    public void createOrUpdateQuote(CEJobOrder jobOrder, Timestamp quoteDate, String quoteNumber);



    /**
     * Update the JobService with a new set of controls.
     * <p>After this call, the JobServiceControls in JobService and the controls
     * list will be in synch, however, the input data is not copied over yet. 
     * <p>the caller is responsible for updating the returned JobServiceControl set,
     * by setting control input.
     * TODO
     * <p>alternatively, this method can be enhanced to update input value as well.
     * 
     * @param jobService
     * @param controls
     * @throws JobSrvcException
     */
    public Set<JobServiceControl> updateJobService(CEJobOrder jobOrer, JobService jobService, List<Control> controls) throws JobSrvcException;

    /**
     * Update the JobContractService with a new set of controls.
     * <p>After this call, the JobContractServiceControls in JobContractService
     * and the controls list will be in synch, however, the input data is not 
     * copied over yet. 
     * <p>the caller is responsible for updating the returned 
     * JobContractServiceControl set, by setting control input.
     * TODO
     * <p>alternatively, this method can be enhanced to update input value as well.
     * 
     * @param jobService
     * @param controls
     * @throws JobSrvcException
     */
    public Set<JobContractServiceControl> updateJobContractService(CEJobContract jobContract, JobContractService jobService, List<Control> controls) throws JobSrvcException;
    
    /**
     * Apply a billing event sent from the PeopleSoft to the designated job 
     * contract order.
     * <p>
     * @param billingEvent
     * @return the JobContract that the billingEvent is associated with
     * @throws JobSrvcException 
     */
    public CEJobContract processBillingEvent(BillingEvent billingEvent) throws JobSrvcException;

    /**
     * Seal this JOLI after the related invoice is issued.
     * @param joli
     * @param batchNumber
     * @throws JobSrvcException 
     */
    public CEJobOrderLineItem sealJobOrderLineItem(CEJobOrderLineItem joli, String batchNumber) throws JobSrvcException;
    
    /**
     * After credit an issued invoice, the related JOLI must be readjusted
     * so invoice amount can be credited back.
     * @param joli
     */
    public void creditJobOrderLineItem(CEJobOrderLineItem joli);
    
    
    /**
     * Check if JobContractService can be removed
     * @param jcs
     * @return true/ false
     */
    public boolean canRemoveJobContractService(JobContractService jcs);
    
    
    /**
     * Check if JobContractTest can be removed
     * @param jcs
     * @return true/ false
     */
    public boolean canRemoveJobContractTest(JobContractTest jct);
    
    public SampleTracking createSampleTracking(CEJobContract jobContract);
    
    public boolean removeSampleTracking(CEJobContract jobContract, SampleTracking sampleTracking);
}
