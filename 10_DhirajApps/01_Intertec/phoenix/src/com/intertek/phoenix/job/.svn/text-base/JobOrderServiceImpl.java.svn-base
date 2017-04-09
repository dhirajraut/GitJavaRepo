/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.job;

import java.io.File;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.Branch;
import com.intertek.entity.BranchCode;
import com.intertek.entity.BranchLocation;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractCustContactId;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.Control;
import com.intertek.entity.ControlMap;
import com.intertek.entity.CustAddress;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.Customer;
import com.intertek.entity.Department;
import com.intertek.entity.Expression;
import com.intertek.entity.ExpressionGLCode;
import com.intertek.entity.HighLevelService;
import com.intertek.entity.PriceBook;
import com.intertek.entity.ProductCode;
import com.intertek.entity.ProductGroup;
import com.intertek.entity.RB;
import com.intertek.entity.ReferenceField;
import com.intertek.entity.Service;
import com.intertek.entity.ServiceId;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.ServiceType;
import com.intertek.entity.Slate;
import com.intertek.entity.Test;
import com.intertek.entity.TestProduct;
import com.intertek.entity.User;
import com.intertek.entity.VesselType;
import com.intertek.phoenix.BaseServiceImpl;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.PhxUserService;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.FilterInfo;
import com.intertek.phoenix.dao.FilterOp;
import com.intertek.phoenix.dao.GenericDao;
import com.intertek.phoenix.dao.QueryInfo;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.entity.BillingEvent;
import com.intertek.phoenix.entity.BillingEventItem;
import com.intertek.phoenix.entity.BillingLineDistribution;
import com.intertek.phoenix.entity.BillingStatus;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.ContractServiceLevel;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.Employee;
import com.intertek.phoenix.entity.Estimation;
import com.intertek.phoenix.entity.Instruction;
import com.intertek.phoenix.entity.InstructionType;
import com.intertek.phoenix.entity.IntegrationHistory;
import com.intertek.phoenix.entity.JobContractService;
import com.intertek.phoenix.entity.JobContractServiceControl;
import com.intertek.phoenix.entity.JobContractServiceExpression;
import com.intertek.phoenix.entity.JobContractSlate;
import com.intertek.phoenix.entity.JobContractTest;
import com.intertek.phoenix.entity.JobOrderAttachment;
import com.intertek.phoenix.entity.JobOrderLineItemAttachment;
import com.intertek.phoenix.entity.JobOrderLineItemNote;
import com.intertek.phoenix.entity.JobOrderNote;
import com.intertek.phoenix.entity.JobOrderType;
import com.intertek.phoenix.entity.JobService;
import com.intertek.phoenix.entity.JobServiceControl;
import com.intertek.phoenix.entity.JobServiceExpression;
import com.intertek.phoenix.entity.JobServiceLevel;
import com.intertek.phoenix.entity.JobSlate;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.JobTestAttachment;
import com.intertek.phoenix.entity.JobTestNote;
import com.intertek.phoenix.entity.OperationalStatus;
import com.intertek.phoenix.entity.OrderOrigin;
import com.intertek.phoenix.entity.OrderStatus;
import com.intertek.phoenix.entity.Period;
import com.intertek.phoenix.entity.ProjectType;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.entity.Quote;
import com.intertek.phoenix.entity.QuoteLine;
import com.intertek.phoenix.entity.RevenueSegregation;
import com.intertek.phoenix.entity.SampleTracking;
import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.phoenix.entity.ServiceOfferingTest;
import com.intertek.phoenix.entity.TestJobOrderLineItem;
import com.intertek.phoenix.entity.UOM;
import com.intertek.phoenix.entity.UserType;
import com.intertek.phoenix.entity.value.SlateInfo;
import com.intertek.phoenix.entity.value.TestInfo;
import com.intertek.phoenix.invoice.InvalidInvoiceOperationException;
import com.intertek.phoenix.invoice.InvoiceService;
import com.intertek.phoenix.job.ServiceLevel.ServiceLevelType;
import com.intertek.phoenix.pricing.AccountInfo;
import com.intertek.phoenix.pricing.PricingInfo;
import com.intertek.phoenix.pricing.PricingSrvc;
import com.intertek.phoenix.pricing.PricingSrvcException;
import com.intertek.phoenix.search.BankSearchInfo;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.tax.TaxSrvc;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.SecurityUtil;

/**
 * Implementing the JobOrdreService interface
 * 
 * As of 2009-7-24, the Slate related implementation is incomplete, as we have
 * not discussed much about it. This area needs to be revisited and fixed.
 * 
 * @author richard.qin
 * @author eric.nguyen
 * @author lily.sun
 */
public class JobOrderServiceImpl extends BaseServiceImpl implements JobOrderService {
    static private Logger log = Logger.getLogger(JobOrderServiceImpl.class);

    @Override
    public List<RevenueSegregation> getRevenueSegregationForTest(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, 
                                                                 JobContractTest jobContractTest) throws JobSrvcException {
        List<RevenueSegregation> result = new ArrayList<RevenueSegregation>();
        PricingSrvc pricingSrvc = ServiceManager.getPricingSrvc();
        Order jobOrder = contractJobOrder.getJobOrder();
        String contractCode = contractJobOrder.getContract().getContractCode();
        Date nominationDate = jobOrder.getNominationDate();
        CfgContract contract = getCfgContractByContractId(contractCode, nominationDate);

        //TODO CE_TEST constant is used for all tests. Please confirm
        List<ContractExpression> expressions = getServiceExpressions(contract, Constants.CE_TEST, 
                                                                     contractJobOrder.getZone(), 
                                                                     nominationDate, contractJobOrder.getLanguage());
        for (ContractExpression exp : expressions) {
            Expression expression = this.getExpressionByExpressionId(exp.getContractExpressionId().getExpressionId());

            AccountInfo accountInfo = pricingSrvc.getAccountInfo(expression, jobOrder.getJobType(), 
                                                                 contractJobOrder.getProductType(), 
                                                                 jobOrder.getBranchName(), serviceLevel,
                                                                 contractJobOrder.getCustomer(), null);
            RevenueSegregation revSeg = new RevenueSegregation();
            
            // TODO Later we may need to grab the description from RB table
            //revSeg.setDescription(exp.getContractExpressionId().getExpressionId());
            
            //TODO: Stored proc set RB string into visibility fields of an expression. 
            //It is used to set the desc of RevSeg. Please confirm 
            revSeg.setDescription(MessageFormat.format(exp.getVisibility(), "", contractCode));
            revSeg.setPrimary(exp.getPrimary());
            revSeg.setAccount(accountInfo.getGlCode());
            revSeg.setDeptId(accountInfo.getDepartmentCode());
            result.add(revSeg);
        }
        Collections.sort(result, new Comparator<RevenueSegregation>(){
            public int compare(RevenueSegregation one, RevenueSegregation two){
                return one.getDescription().compareTo(two.getDescription());
            }
        });
        return result;
    }

    @Override
    public List<TestProduct> getTestProducts() throws JobSrvcException {
        try {
            return DaoManager.getDao(TestProduct.class).findAll();
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load TestProduct", e);
        }
    }

    @Override
    public List<ServiceType> getServiceTypes(String jobType) throws JobSrvcException {
        QueryInfo query = new QueryInfo(ServiceType.class);
        query.addFilter("serviceTypeId.jobType", jobType);
        try {
            return DaoManager.getDao(ServiceType.class).search(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load ServiceTypes for jobType " + jobType, e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Control> getServiceControls(CfgContract contract, String serviceName, Date nominationDate, String lang) throws JobSrvcException {
        String priceBookId = getPriceBookId(contract, nominationDate);
        String dateStr = DateUtil.formatDate(nominationDate, "yyyyMMdd");
        Object[] params = new Object[] { serviceName, contract.getCfgContractId().getContractId(), 
                                         priceBookId, dateStr, lang };

        try {
            return DaoManager.getDao(Control.class).searchByNamedQuery("getControlRBs_FN", params, null);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load Controls.", e);
        }
    }

    /**
     * @param contract
     * @param nominationDate
     * @return
     */
    private String getPriceBookId(CfgContract contract, Date nominationDate) throws JobSrvcException {
        String priceBookId = contract.getPriceBookId();
        if (priceBookId.equalsIgnoreCase(Constants.CURRENT)) {
            PriceBook pb = this.getPriceBook(contract.getPbSeries(), contract.getCurrencyCD(), nominationDate);
            priceBookId = pb.getPriceBookId().getPriceBookId();
        }
        return priceBookId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ContractExpression> getServiceExpressions(CfgContract contract, String serviceName, String location, Date nominationDate, String lang)
            throws JobSrvcException {
        String dateStr = DateUtil.formatDate(nominationDate, "yyyyMMdd");
        String priceBookId = getPriceBookId(contract, nominationDate);
        Object[] params = new Object[] { serviceName, contract.getCfgContractId().getContractId(), 
                                         priceBookId, location, dateStr, lang };

        try {
            return DaoManager.getDao(ContractExpression.class).searchByNamedQuery("getContractExpressions_FN", params, null);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load Expressions.", e);
        }
    }

    @Override
    public List<Service> getServices(CfgContract contract, ServiceLevel.ServiceLevelType serviceLevel) {
        // the service tables seems to be "suggesting" this method
        // TODO should I really support this?
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Service> getServices(CfgContract contract, Date nominationDate, String languageCD) throws JobSrvcException {
        String dateStr = DateUtil.formatDate(nominationDate, "yyyyMMdd");
        String priceBookId = getPriceBookId(contract, nominationDate);
        Object[] params = new Object[] { contract.getCfgContractId().getContractId(), 
                                         priceBookId, dateStr, languageCD };

        try {
            return DaoManager.getDao(Service.class).searchByNamedQuery("getServices_FN", params, null);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load Services.", e);
        }
    }

    /**
     * RQ: Don't like the fact that several methods look almost the same here:
     * getSlateInfos(), getSlates(), getTestInfos(), getTests(), but it seems
     * there is little I can do at this time.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<SlateInfo> getSlateInfos(CfgContract contract, String value, String searchType, String location, Date nominationDate, String languageCD)
            throws JobSrvcException {
        String dateStr = DateUtil.formatDate(nominationDate, "yyyyMMdd");
        String priceBookId = getPriceBookId(contract, nominationDate);
        if (location == null) {
            location = "*";
        }
        if (languageCD == null) {
            languageCD = "ENG";
        }
        Object[] params = new Object[] { contract.getCfgContractId(), priceBookId, value, searchType, location, dateStr, languageCD };
        try {
            List<Object[]> results = DaoManager.getDao(Slate.class).searchByNamedQuery("getSlate_FN", params, null);

            List<SlateInfo> list = new ArrayList<SlateInfo>();
            for (Object[] objects : results) {
                SlateInfo ti = new SlateInfo();
                ti.setSlateId((String) objects[0]);
                ti.setDescription((String) objects[1]);
            }
            return list;
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load Tests.", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Slate> getSlates(CfgContract contract, String value, String searchType, String location, Date nominationDate, String languageCD)
            throws JobSrvcException {
        String dateStr = DateUtil.formatDate(nominationDate, "yyyyMMdd");
        String priceBookId = getPriceBookId(contract, nominationDate);
        if (location == null) {
            location = "*";
        }
        if (languageCD == null) {
            languageCD = "ENG";
        }
        Object[] params = new Object[] { contract.getCfgContractId(), priceBookId, value, searchType, location, dateStr, languageCD };
        try {
            // 1. run the stored proc
            GenericDao<Slate> dao = DaoManager.getGenericDao(Slate.class);
            List<Object[]> results = dao.searchByNamedQuery("getSlate_FN", params, null);

            // 2. load entities
            String[] ids = buildIdList(results);

            QueryInfo query = new QueryInfo(Slate.class);
            query.addFilter("slateId", ids, FilterOp.IN);
            return dao.search(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load Tests.", e);
        }
    }

    /**
     * @param results
     * @return
     */
    private String[] buildIdList(List<Object[]> results) {
        String[] ids = new String[results.size()];
        for (int k = 0; k < results.size(); k++) {
            ids[k] = (String) results.get(k)[0];
        }
        return ids;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TestInfo> getTestInfos(CfgContract contract, String productGroup, String contractSearchCD, String value, String searchType, String location,
                                       Date nominationDate, String languageCD) throws JobSrvcException {
        String dateStr = DateUtil.formatDate(nominationDate, "yyyyMMdd");
        String priceBookId = getPriceBookId(contract, nominationDate);
        if (location == null) {
            location = "*";
        }
        if (languageCD == null) {
            languageCD = "ENG";
        }
        Object[] params = new Object[] { contract.getCfgContractId(), priceBookId, productGroup, contractSearchCD, value, searchType, location, dateStr,
                                        languageCD };
        try {
            List<Object[]> results = DaoManager.getDao(Test.class).searchByNamedQuery("getTest_FN", params, null);

            List<TestInfo> list = new ArrayList<TestInfo>();
            for (Object[] objects : results) {
                TestInfo ti = new TestInfo();
                ti.setTestId((String) objects[0]);
                ti.setDescription((String) objects[1]);
            }
            return list;
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load Tests.", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Test> getTests(CfgContract contract, String productGroup, String contractSearchCD, String value, String searchType, String location,
                               Date nominationDate, String languageCD) throws JobSrvcException {
        String dateStr = DateUtil.formatDate(nominationDate, "yyyyMMdd");
        String priceBookId = getPriceBookId(contract, nominationDate);
        if (location == null) {
            location = "*";
        }
        if (languageCD == null) {
            languageCD = "ENG";
        }
        Object[] params = new Object[] { contract.getCfgContractId(), priceBookId, 
                                         productGroup, contractSearchCD, value,
                                         searchType, location, dateStr, languageCD };
        try {
            // 1. run the stored proc
            GenericDao<Test> dao = DaoManager.getGenericDao(Test.class);
            List<Object[]> results = dao.searchByNamedQuery("getTest_FN", params, null);

            // 2. load entities
            String[] ids = this.buildIdList(results);

            QueryInfo query = new QueryInfo(Test.class);
            query.addFilter("testId", ids, FilterOp.IN);
            return dao.search(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load Tests.", e);
        }
    }

    @Override
    public List<ControlMap> getControlMapsByExpression(CfgContract contract, String serviceName, String expressionId) throws JobSrvcException {
        QueryInfo query = new QueryInfo(ControlMap.class);
        // the implementation is refactor from this sql
        // "from ControlMap cm where
        // (cm.controlMapId.contractId = ? or cm.controlMapId.contractId = ? )
        // and cm.controlMapId.serviceName = ?
        // and cm.controlMapId.expressionId = ?"
        query.addFilter("controlMapId.serviceName", serviceName)
             .addFilter("controlMapId.expressionId", expressionId)
             .addOrQuery()
             .addFilter("controlMapId.contractId", contract.getCfgContractId().getContractId())
             .addFilter("controlMapId.contractId", Constants.MASTER);

        try {
            return DaoManager.getDao(ControlMap.class).search(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load CfgContract.", e);
        }
    }

    @Override
    public List<ControlMap> getControlMapsByControl(CfgContract contract, String serviceName, String componentName) throws JobSrvcException {
        QueryInfo query = new QueryInfo(ControlMap.class);
        query.addFilter("controlMapId.serviceName", serviceName)
             .addFilter("controlMapId.objectName", componentName)
             .addOrQuery()
             .addFilter("controlMapId.contractId", contract.getCfgContractId().getContractId())
             .addFilter("controlMapId.contractId", Constants.MASTER);

        try {
            return DaoManager.getDao(ControlMap.class).search(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load CfgContract.", e);
        }
    }

    @Override
    public CfgContract getCfgContractByContractId(String contractId, Date date) throws JobSrvcException {
        QueryInfo query = new QueryInfo(CfgContract.class);
        query.addFilter("cfgContractId.contractId", contractId)
             .addFilter("cfgContractId.beginDate", "endDate", date, FilterOp.BETWEEN);

        try {
            return DaoManager.getDao(CfgContract.class).searchUnique(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load CfgContract.", e);
        }
    }

    @Override
    public PriceBook getPriceBook(String pbSeries, String currencyCD, Date date) throws JobSrvcException {
        QueryInfo query = new QueryInfo(PriceBook.class);
        query.addFilter("priceBookId.pbSeries", pbSeries)
             .addFilter("priceBookId.currencyCD", currencyCD)
             .addFilter("priceBookId.beginDate", "endDate", date, FilterOp.BETWEEN);
        try {
            return DaoManager.getDao(PriceBook.class).searchUnique(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load PriceBook.", e);
        }
    }

    /**
     * Implementation refactored from phoenix 1. But, TODO How can this work,
     * because Expression has a composite key, but only one value is need for
     * lookup here. What happened to BRANCH_TYPE?
     */
    @Override
    public Expression getExpressionByExpressionId(String expressionId) throws JobSrvcException {
        Dao<Expression> dao = DaoManager.getDao(Expression.class);
        try {
            QueryInfo query = new QueryInfo(Expression.class);
            query.addFilter("expressionId.expressionId", expressionId);

            return dao.searchUnique(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load Expression by expressionId.", e);
        }
    }

    @Override
    public BranchCode getBranchCodeByBranchCode(String branchCode) throws JobSrvcException {
        Dao<BranchCode> dao = DaoManager.getDao(BranchCode.class);
        if (branchCode != null) {
            try {
                BranchCode example = new BranchCode();
                example.setBranchCode(branchCode);
                List<BranchCode> result = dao.search(example);
                if (result.size() > 0) {
                    return result.get(0);
                }
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed to load BranchCode " + branchCode, e);
            }
        }
        return null;
    }

    @Override
    public Department getDepartmentByGlCode(String glCode) throws JobSrvcException {
        Dao<Department> dao = DaoManager.getDao(Department.class);
        try {
            Department example = new Department();
            example.setGLCode(glCode);
            List<Department> result = dao.search(example);
            if (result.size() > 0) {
                return result.get(0);
            }
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load Department by GL code: " + glCode, e);
        }
        return null;
    }

    @Override
    public ExpressionGLCode getExpressionGLCode(String id, JobOrderType jobType) throws JobSrvcException {
        Dao<ExpressionGLCode> dao = DaoManager.getDao(ExpressionGLCode.class);
        try {
            QueryInfo query = new QueryInfo(ExpressionGLCode.class);
            query.addFilter("expressionGLCodeId.expressionId", id)
                 .addFilter("expressionGLCodeId.nominationType", jobType.name());
            List<ExpressionGLCode> result = dao.search(query);
            if (result.size() > 0) {
                return result.get(0);
            }
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load ExpressionGLCode by id and jobType " + id + ":" + jobType, e);
        }
        return null;
    }

    @Override
    public String getProductCode(ExpressionGLCode code, String jobCode, String masterGroup) throws JobSrvcException {
        if (code != null) {
            Integer useProdFlag = code.getUseProdFlag();
            if ((useProdFlag == 0)) {
                Dao<ProductCode> dao = DaoManager.getDao(ProductCode.class);
                QueryInfo query = new QueryInfo(ProductGroup.class);
                query.addFilter("productCodeId.jobCode", jobCode)
                     .addFilter("productCodeId.noProdCode", code.getNoProdCode());
                List<ProductCode> result = null;
                try {
                    result = dao.search(query);
                    if (result.size() > 0) {
                        return result.get(0).getJobTypeNoProductCode();
                    }
                }
                catch (DaoException e) {
                    throw new JobSrvcException("Failed to load ProductCode for jobCode and noProdCode " + jobCode + ":" + code.getNoProdCode(), e);
                }
            }
            else {
                return masterGroup;
            }
        }
        return null;
    }

    /**
     * This method is not used by phoenix. The contract builder may use it.
     */
    @Override
    public List<HighLevelService> getHighLevelServices() throws JobSrvcException {
        Dao<HighLevelService> dao = DaoManager.getDao(HighLevelService.class);
        try {
            return dao.findAll();
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load HighLevelService.", e);
        }
    }

    @Override
    public List<ProductGroup> getProductGroups(String productGroupSetName, Date effectiveDate) throws JobSrvcException {
        Dao<ProductGroup> dao = DaoManager.getDao(ProductGroup.class);
        QueryInfo query = new QueryInfo(ProductGroup.class);
        query.addFilter("productGroupId.productGroupSet", productGroupSetName)
             .addFilter("productGroupId.beginDate", "endDate", effectiveDate, FilterOp.BETWEEN);
        try {
            return dao.search(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load ProductGroup", e);
        }
    }

    @Override
    public List<VesselType> getVesselTypes(String vesselTypeSetName, Date effectiveDate) throws JobSrvcException {
        Dao<VesselType> dao = DaoManager.getDao(VesselType.class);
        QueryInfo query = new QueryInfo(VesselType.class);
        query.addFilter("vesselTypeId.vesselSet", vesselTypeSetName)
             .addFilter("vesselTypeId.beginDate", "endDate", effectiveDate, FilterOp.BETWEEN);
        try {
            return dao.search(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load ProductGroup", e);
        }
    }

    @Override
    public JobService createJobService(JobServiceLevel serviceLevel, Service service) throws JobSrvcException {
        JobService jobService = serviceLevel.createJobService(JobService.class, service);
        // TODO any default value to be set?

        // the following dao call is not needed, dao should
        // automatically do the saving
        // The same principle applies to many other similar methods
        try {
            DaoManager.getDao(JobService.class).saveOrUpdate(jobService);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to save new JobService", e);
        }

        return jobService;
    }

    @Override
    public JobServiceControl createJobServiceControl(JobService jobService, Control control) throws JobSrvcException {
        JobServiceControl jobServiceControl = jobService.createJobServiceControl(control);
        // redundant dao call
        try {
            DaoManager.getDao(JobServiceControl.class).saveOrUpdate(jobServiceControl);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to save new JobServiceControl", e);
        }

        return jobServiceControl;
    }

    @Override
    public Set<JobServiceControl> updateJobService(CEJobOrder jobOrder, JobService jobService, List<Control> controls) throws JobSrvcException {
        // 1. remove the ones that not in the new list
        for (JobServiceControl jsc : jobService.getControls()) {
            Control control = jsc.getControl();
            if (!controls.contains(control)) {
                this.removeJobServiceControl(jobService, jsc);
            }
        }
        // 2. for the ones in the new list, update if exist, otherwise add a new
        // one
        for (Control control : controls) {
            JobServiceControl jsc = jobService.getJobServiceControl(control);
            if (jsc == null) {
                jsc = jobService.createJobServiceControl(control);
                // it seems to me that there is not need to create or use
                // JobServiceExpression
            }
        }
        return jobService.getControls();
    }

    @Override
    // TODO NOW
    public Set<JobContractServiceControl> updateJobContractService(CEJobContract jobContract, JobContractService jobContractService, List<Control> controls)
            throws JobSrvcException {
        // 1. remove the ones that not in the new list
        for (JobContractServiceControl jcsc : jobContractService.getControls()) {
            Control control = jcsc.getControl();
            if (!controls.contains(control)) {
                this.removeJobContractServiceControl(jobContractService, jcsc);
            }
        }
        // 2. for the ones in the new list, update if exist, otherwise add a new
        // one
        for (Control control : controls) {
            JobContractServiceControl jcsc = jobContractService.getJobContractServiceControl(control);
            if (jcsc == null) {
                jcsc = this.createJobContractServiceControl(jobContractService, control);
            }
        }
        return jobContractService.getControls();
    }

    /**
     * @param jobContract
     * @param serviceName
     * @param objectName
     * @return
     * @throws JobSrvcException
     * @throws DaoException
     */
    // TODO to be improved, fix 1+n queries
    private List<ContractExpression> getExpressionByControl(CEJobContract jobContract, String serviceName, String objectName) throws JobSrvcException,
            DaoException {
        List<ContractExpression> expressions = new ArrayList<ContractExpression>();
        String contractId = jobContract.getContractCode();
        Date date = jobContract.getJobOrder().getNominationDate();
        CfgContract contract = this.getCfgContractByContractId(contractId, date);
        String location = jobContract.getZoneId();
        List<ControlMap> controlMaps = this.getControlMapsByControl(contract, serviceName, objectName);
        for (ControlMap cm : controlMaps) {
            ContractExpression exp = this.getContractExpression(contractId, serviceName, cm.getControlMapId().getExpressionId(), location, date);
            expressions.add(exp);
        }
        return expressions;
    }

    /**
     * @param contractId
     * @param serviceName
     * @param expressionId
     * @param location
     * @param date
     * @return
     * @throws DaoException
     */
    private ContractExpression getContractExpression(String contractId, String serviceName, String expressionId, String location, Date date)
            throws DaoException {
        QueryInfo query = new QueryInfo(ContractExpression.class);
        query.addFilter("contractExpressionId.contractId", contractId)
             .addFilter("contractExpressionId.serviceName", serviceName)
             .addFilter("contractExpressionId.expressionId", expressionId)
             .addFilter("contractExpressionId.location", location)
             .addFilter("contractExpressionId.beginDate", date);
        return DaoManager.getDao(ContractExpression.class).searchUnique(query);
    }

    @Override
    public JobSlate createJobSlate(JobServiceLevel serviceLevel, Slate slate) throws JobSrvcException {
        JobSlate jobSlate = serviceLevel.createJobSlate(JobSlate.class, slate);
        // redundant dao call
        try {
            DaoManager.getDao(JobSlate.class).saveOrUpdate(jobSlate);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to save new JobSlate", e);
        }

        return jobSlate;
    }

    @Override
    public JobTest createJobTest(JobServiceLevel serviceLevel, Test test) throws JobSrvcException {
        JobTest jobTest = serviceLevel.createJobTest(JobTest.class, test);
        // set defaults
        CEJobOrder jobOrder = serviceLevel.getJobOrder();
        if(jobOrder!=null && jobOrder.getBranch()!=null){
            jobTest.setBranch(jobOrder.getBranch());
        }
        // TODO need to set line description, using formatted rb text

        try {
            DaoManager.getDao(JobTest.class).saveOrUpdate(jobTest);
        }

        catch (DaoException e) {
            throw new JobSrvcException("Failed to save new JobTest", e);
        }

        return jobTest;
    }

    private boolean canRemoveJobOrder(CEJobOrder jobOrder) {
        if (jobOrder.getOperationalStatus() != OperationalStatus.Unscheduled || jobOrder.getBillingStatus() != BillingStatus.OPEN
            || (jobOrder.getStatus() != OrderStatus.NEW && jobOrder.getStatus() != OrderStatus.OPEN)) {
            
            return false;
        }
        return true;
    }

    private boolean canRemoveJobService(JobService jobService) {
        CEJobOrder jobOrder = jobService.getJobServiceLevel().getJobOrder();
        if (canRemoveJobOrder(jobOrder) == false || jobService.getBillingStatus() != BillingStatus.OPEN
            || jobService.getOperationalStatus() != OperationalStatus.Unscheduled
            // or related jobContractServices already exist,
            || jobService.getJobContractServices().size() > 0) {
            return false;
        }
        return true;
    }

    private boolean canRemoveJobTest(JobTest jobTest) {
        CEJobOrder jobOrder = null;
        if (jobTest.getMaster() != null) {
            jobOrder = jobTest.getMaster().getJobServiceLevel().getJobOrder();
        }
        else {
            jobOrder = jobTest.getJobServiceLevel().getJobOrder();
        }
        if (canRemoveJobOrder(jobOrder) == false || (jobTest.getBillingStatus()!=null && jobTest.getBillingStatus() != BillingStatus.OPEN)
            ||(jobTest.getOperationalStatus() !=null &&  jobTest.getOperationalStatus() != OperationalStatus.Unscheduled) 
            || jobTest.getJobContractTests().size() > 0) {
            return false;
        }
        return true;
    }

    private boolean canRemoveJobSlate(JobSlate jobSlate) {
        CEJobOrder jobOrder = jobSlate.getJobServiceLevel().getJobOrder();
        if (canRemoveJobOrder(jobOrder) == false) {
            // TODO not complete
            return false;
        }
        return true;
    }

    public boolean canRemoveJobContractService(JobContractService jcs) {
        if (jcs.getJobService() != null // must remove JobService in order to
            // remove this one.
            || jcs.getBillingStatus() != BillingStatus.OPEN) {
            return false;
        }
        return true;
    }

    public boolean canRemoveJobContractTest(JobContractTest jct) {
        if (jct.getJobTest() != null // must remove JobService in order to
            // remove this one.
            || jct.getBillingStatus() != BillingStatus.OPEN) {
            return false;
        }
        return true;
    }

    private boolean canRemoveJobContractSlate(JobContractSlate jcs) {
        if (jcs.getJobSlate() != null // must remove JobService in order to
            // remove this one.
            || jcs.getBillingStatus() != BillingStatus.OPEN) {
            return false;
        }
        return true;
    }

    private boolean canRemoveJobServiceLevel(JobServiceLevel toRemove) {
        if (toRemove.getChildServiceLevels().size() > 0 || toRemove.getJobServices().size() > 0 || toRemove.getJobSlates().size() > 0
            || toRemove.getJobTests().size() > 0) {
            return false;
        }
        return true;
    }

    private boolean canRemoveContractServiceLevel(ContractServiceLevel toRemove) {
        if (toRemove.getChildServiceLevels().size() > 0 || toRemove.getJobContractServices().size() > 0 || toRemove.getJobContractSlates().size() > 0
            || toRemove.getJobContractTests().size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * The "split" concept does not apply to JobService at this time, but this
     * may change in the near future, and the code needs to update accordingly.
     * 
     */
    @Override
    public void removeJobService(JobService jobService) throws JobSrvcException {
        if (canRemoveJobService(jobService) == false) {
            throw new JobSrvcException("Can not remove JobService, this JobService has been scheduled or already invoiced");
        }

        // remove all the controls and expressions
        for (JobServiceControl control : jobService.getControls()) {
            this.removeJobServiceControl(jobService, control);
        }
        for (JobServiceExpression exp : jobService.getServiceExpressions()) {
            this.removeJobServiceExpression(jobService, exp);
        }
        if (jobService.getJobServiceLevel() != null) {
            jobService.getJobServiceLevel().removeJobService(jobService);
        }
        // Also need to remove all the jobContractService that this jobService
        // is related to. But this action is contradictory to one of the
        // conditions
        // defined in the canRemoveJobService() method. This is definitely
        // something that needs to be discussed. TODO
        for (JobContractService jcs : jobService.getJobContractServices()) {
            this.removeJobContractService(jcs);
        }

        try {
            DaoManager.getDao(JobService.class).remove(jobService);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to remove JobService", e);
        }
    }

    /**
     * Unlike removeJobService, there is no need to check if a control or
     * expression can be removed, because a control or an expression is always
     * removed along with the job service object itself.
     * 
     */
    @Override
    public void removeJobServiceControl(JobService jobService, JobServiceControl control) throws JobSrvcException {
        jobService.removeJobServiceControl(control);
        // note, JobServiceControl does not contain JobServiceExpression

        try {
            DaoManager.getDao(JobServiceControl.class).remove(control);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to remove JobServiceControl", e);
        }
    }

    private void removeJobServiceExpression(JobService jobService, JobServiceExpression exp) throws JobSrvcException {
        jobService.removeJobServiceExpression(exp);
        try {
            DaoManager.getDao(JobServiceExpression.class).remove(exp);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to remove JobServiceControl", e);
        }
    }

    /**
     * This method may not be complete, for example, it does not support
     * "split",
     */
    @Override
    public void removeJobSlate(JobSlate jobSlate) throws JobSrvcException {
        if (canRemoveJobSlate(jobSlate) == false) {
            throw new JobSrvcException("Can not remove JobSlate, this JobSlate has been scheduled or already invoiced");
        }

        if (jobSlate.getJobServiceLevel() != null) {
            jobSlate.getJobServiceLevel().removeJobSlate(jobSlate);
        }
        try {
            DaoManager.getDao(JobSlate.class).remove(jobSlate);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to remove JobSlate", e);
        }
    }

    @Override
    public void removeJobTest(JobTest jobTest) throws JobSrvcException {
        if (canRemoveJobTest(jobTest) == false) {
            throw new JobSrvcException("Can not remove JobTest, this JobTest has been scheduled or already invoiced");
        }

        if (jobTest.getJobServiceLevel() != null) {
            jobTest.getJobServiceLevel().removeJobTest(jobTest);
        }
        // remove itself from the master if it is a related / split job test
        if (jobTest.getMaster() != null) {
            jobTest.getMaster().removeRelated(jobTest);
        }
        // remove all the related / split job tests
        for (JobTest related : jobTest.getRelated()) {
            this.removeJobTest(related);
        }

        try {
            DaoManager.getDao(JobTest.class).remove(jobTest);
            //DaoManager.getDao(JobServiceLevel.class).saveOrUpdate(serviceLevel
            // );
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to remove JobTest", e);
        }
    }

    /**
     * this method is sensitive to business divisions, as differnet divisions
     * have different service structures. TODO find out more.
     */
    @Override
    public JobServiceLevel createJobServiceLevel(JobServiceLevel parent, ServiceLevelType type, String serviceLevelName) throws JobSrvcException {
        log.debug("Parent Id<<<<<<<" + parent.getId());

        JobServiceLevel newLevel = parent.createChildServiceLevel(type, serviceLevelName);
        // the parent/child relationship should already be established
        // by parent.createChildServiceLevel()

        log.debug("Parent Service Level Id" + newLevel.getParentServiceLevel());
        // redundant dao call
        try {
            DaoManager.getDao(JobServiceLevel.class).saveOrUpdate(newLevel);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to create JobServiceLevel", e);
        }

        return newLevel;
    }

    @Override
    public ContractServiceLevel createContractServiceLevel(ContractServiceLevel parent, ServiceLevelType type, String serviceLevelName) throws JobSrvcException {
        ContractServiceLevel newLevel = parent.createChildServiceLevel(type, serviceLevelName);
        // redundant dao call
        try {
            DaoManager.getDao(ContractServiceLevel.class).saveOrUpdate(newLevel);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to create ContractServiceLevel", e);
        }

        return newLevel;
    }

    @Override
    public JobContractService createJobContractService(ContractServiceLevel serviceLevel, Service service) throws JobSrvcException {
        JobContractService jobService = serviceLevel.createJobContractService(JobContractService.class, service);
        // redundant dao call
        try {
            DaoManager.getDao(JobContractService.class).saveOrUpdate(jobService);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to create new JobContractService", e);
        }
        // the caller must set the rest of the details, including serviceType

        return jobService;
    }

    @Override
    // create JobContractServiceControl will also create related
    // JobContractServiceExpressions
    public JobContractServiceControl createJobContractServiceControl(JobContractService jobContractService, Control control) throws JobSrvcException {
        try {
            JobContractServiceControl jcsc = jobContractService.createJobContractServiceControl(control);

            // also need to create related jobContractServiceExpressions
            CEJobContract jobContract = jobContractService.getContractServiceLevel().getJobContract();
            String serviceName = jcsc.getControl().getControlId().getServiceName();
            String objectName = jcsc.getControl().getControlId().getObjectName();
            List<ContractExpression> expressions = this.getExpressionByControl(jobContract, serviceName, objectName);
            for (ContractExpression expression : expressions) {
                JobContractServiceExpression jcse = jobContractService.createJobContractServiceExpression(expression);
                // this link is need, so that when a jcsc is removed, all
                // its related jcse can be easilty found and removed too.
                jcsc.addJobContractServiceExpression(jcse);
            }

            DaoManager.getDao(JobContractServiceControl.class).saveOrUpdate(jcsc);
            // extra?

            return jcsc;
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to create new JobContractServiceControl", e);
        }
    }

    @Override
    public JobContractSlate createJobContractSlate(ContractServiceLevel serviceLevel, Slate slate) throws JobSrvcException {
        JobContractSlate jobSlate = serviceLevel.createJobContractSlate(JobContractSlate.class, slate);
        // redundant dao call
        try {
            DaoManager.getDao(JobContractSlate.class).saveOrUpdate(jobSlate);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to create new JobContractSlate", e);
        }
        // extra?

        return jobSlate;
    }

    @Override
    public JobContractTest createJobContractTest(ContractServiceLevel serviceLevel, Test test) throws JobSrvcException {
        JobContractTest jobTest = serviceLevel.createJobContractTest(JobContractTest.class, test);
        // redundant dao call
        try {
            DaoManager.getDao(JobContractTest.class).saveOrUpdate(jobTest);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to create new JobContractTest", e);
        }
        // extra?

        return jobTest;
    }

    @Override
    public void removeJobServiceLevel(JobServiceLevel parent, JobServiceLevel toRemove) throws JobSrvcException {
        if (canRemoveJobServiceLevel(toRemove) == false) {
            throw new JobSrvcException("Cannot remove non-empty Service Level.");
        }
        if (toRemove.getParentServiceLevel() == null) {
            throw new JobSrvcException("Cannot remove root Service Level.");
        }
        parent.removeChildServiceLevel(toRemove);
        try {
            DaoManager.getDao(JobServiceLevel.class).remove(toRemove);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to remove the JobServiceLevel object", e);
        }
    }

    @Override
    public void removeContractServiceLevel(ContractServiceLevel parent, ContractServiceLevel toRemove) throws JobSrvcException {
        if (canRemoveContractServiceLevel(toRemove) == false) {
            throw new JobSrvcException("Cannot remove non-empty Service Level.");
        }
        if (toRemove.getParentServiceLevel() == null) {
            throw new JobSrvcException("Cannot remove root Service Level.");
        }
        if (parent.removeChildServiceLevel(toRemove)) {
            try {
                DaoManager.getDao(ContractServiceLevel.class).remove(toRemove);
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed to remove the ContractServiceLevel object", e);
            }
        }
    }

    @Override
    public void removeJobContractService(JobContractService jcs) throws JobSrvcException {
        if (canRemoveJobContractService(jcs) == false) {
            throw new JobSrvcException("Cannot remove a JobContractService that is already invoiced.");
        }
        // need to remove all the controls, expresions added to the service
        for (JobContractServiceControl control : jcs.getControls()) {
            this.removeJobContractServiceControl(jcs, control);
        }
        for (JobContractServiceExpression exp : jcs.getJobContractServiceExpresions()) {
            this.removeJobContractServiceExpression(jcs, exp);
        }
        if (jcs.getContractServiceLevel() != null) {
            jcs.getContractServiceLevel().removeJobContractService(jcs);
        }
        try {
            DaoManager.getDao(JobContractService.class).remove(jcs);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to remove JobContractService", e);
        }
    }

    private void removeJobContractServiceExpression(JobContractService service, JobContractServiceExpression exp) throws JobSrvcException {
        if (service.removeJobContractServiceExpression(exp)) {
            // associated JOLI will be removed too, as the cascading is set to
            // ALL
            try {
                // need to remove the related joli
                DaoManager.getDao(CEJobOrderLineItem.class).remove(exp.getJobOrderLineItem());
                DaoManager.getDao(JobContractServiceExpression.class).remove(exp);
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed to remove JobContractServiceControl", e);
            }
        }
    }

    @Override
    public void removeJobContractServiceControl(JobContractService jobContractService, JobContractServiceControl control) throws JobSrvcException {
        if (jobContractService.removeJobContractServiceControl(control)) {
            // JobContractServiceControl maintains a set of
            // jobContractServiceExpressions,
            // these objects will also be removed too
            try {
                Dao<JobContractServiceExpression> dao = DaoManager.getDao(JobContractServiceExpression.class);
                for (JobContractServiceExpression jcse : control.getJobContractServiceExpressions()) {
                    dao.remove(jcse);
                }
                DaoManager.getDao(JobContractServiceControl.class).remove(control);
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed to remove JobContractServiceControl", e);
            }
        }
    }

    @Override
    public void removeJobContractSlate(JobContractSlate slate) throws JobSrvcException {
        if (canRemoveJobContractSlate(slate) == false) {
            throw new JobSrvcException("Cannot remove a JobContractSlate that is already invoiced.");
        }

        if (slate.getContractServiceLevel() != null) {
            slate.getContractServiceLevel().removeJobContractSlate(slate);
            // associated JOLI will be removed too, as the cascading is set to
            // ALL
            try {
                // need to remove the related joli
                DaoManager.getDao(CEJobOrderLineItem.class).remove(slate.getJobOrderLineItem());
                DaoManager.getDao(JobContractSlate.class).remove(slate);
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed to remove JobContractSlate", e);
            }
        }
    }

    @Override
    public void removeJobContractTest(JobContractTest test) throws JobSrvcException {
        if (canRemoveJobContractTest(test) == false) {
            throw new JobSrvcException("Cannot remove a JobContractTest that is already invoiced.");
        }

        if (test.getContractServiceLevel() != null) {
            test.getContractServiceLevel().removeJobContractTest(test);
            // associated JOLI will be removed too, as the cascading is set to
            // ALL
            try {
                // need to remove the associated joli
                for(TestJobOrderLineItem joli: test.getJobOrderLineItems()){
                    DaoManager.getDao(TestJobOrderLineItem.class).remove(joli);
                }
                DaoManager.getDao(JobContractTest.class).remove(test);
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed to remove JobContractTest", e);
            }
        }
    }

    /**
     * Note, this method is a litter different from the others, because
     * JobService does not maintain a reference to the Service object. It may be
     * possible to eliminate this inconsistency.
     */
    @Override
    public JobContractService createOrUpdateJobContractService(ContractServiceLevel serviceLevel, JobService jobService) throws JobSrvcException {
        JobContractService jobContractService = serviceLevel.findJobService(jobService);
        if (jobContractService == null) {
            jobContractService = serviceLevel.createJobContractService(JobContractService.class, jobService);
            // the caller must set the rest of the details, including
            // serviceType
        }
        // propagate jobServiceControls
        for (JobServiceControl jsc : jobService.getControls()) {
            this.createJobContractServiceControl(jobContractService, jsc);
        }
        // may be redundant dao call
        try {
            DaoManager.getDao(ContractServiceLevel.class).saveOrUpdate(serviceLevel);
            DaoManager.getDao(JobContractService.class).saveOrUpdate(jobContractService);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to save new JobContractService", e);
        }

        return jobContractService;
    }

    @Override
    public JobContractServiceControl createJobContractServiceControl(JobContractService jobContractService, JobServiceControl control) throws JobSrvcException {
        JobContractServiceControl jcsc = this.createJobContractServiceControl(jobContractService, control.getControl());
        // copy over input values captured at the jobService level
        jcsc.setControlType(control.getControlType());
        jcsc.setInputValue0(control.getInputValue0());
        jcsc.setInputValue1(control.getInputValue1());
        jcsc.setInputValue2(control.getInputValue2());
        jcsc.setInputValue3(control.getInputValue3());
        jcsc.setInputValue4(control.getInputValue4());

        // if a JobContractServiceControl(JCSC) is created based on
        // JobContractService,
        // the relationship needs to be established here.
        jcsc.setJobServiceControl(control);

        return jcsc;
    }

    @Override
    public JobContractSlate createOrUpdateJobContractSlate(ContractServiceLevel serviceLevel, JobSlate jobSlate) throws JobSrvcException {
        JobContractSlate jobContractSlate = serviceLevel.findJobContractSlate(jobSlate);
        if (jobContractSlate == null) {
            jobContractSlate = serviceLevel.createJobContractSlate(JobContractSlate.class, jobSlate.getSlate());
            jobContractSlate.setJobSlate(jobSlate);
            // redundant dao call
            try {
                DaoManager.getDao(JobContractSlate.class).saveOrUpdate(jobContractSlate);
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed to save new JobContractSlate", e);
            }
        }
        jobContractSlate.setQuantity(jobSlate.getQuantity());
        // what about the others?

        return jobContractSlate;
    }

    @Override
    public JobContractTest createOrUpdateJobContractTest(ContractServiceLevel serviceLevel, JobTest jobTest) throws JobSrvcException {

        JobContractTest jobContractTest = serviceLevel.findJobContractTest(jobTest);
        if (jobContractTest == null) {
            if(jobTest.isManualTest()){
                jobContractTest = serviceLevel.createJobContractTest(JobContractTest.class, null);
            }
            else{
                jobContractTest = serviceLevel.createJobContractTest(JobContractTest.class, jobTest.getTest());
            }
            jobContractTest.setLineDescription(jobTest.getLineDescription());
            jobContractTest.setServiceOffering(jobTest.getServiceOffering());
            jobTest.addJobContractTest(jobContractTest);
            if (jobTest.getBillingStatus() == BillingStatus.NOT_INVOICEABLE) {
                jobContractTest.setBillingStatus(BillingStatus.NOT_INVOICEABLE);
            }
            else {
                jobContractTest.setBillingStatus(BillingStatus.OPEN);
            }
            if ( serviceLevel.getJobContract() != null ) {
                jobContractTest.setContractRefNo(serviceLevel.getJobContract().getContractCode());
            }
            // redundant dao call
            try {
                DaoManager.getDao(JobContractTest.class).saveOrUpdate(jobContractTest);
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed to save new JobContractTest", e);
            }
        }
        jobContractTest.setQuantity(jobTest.getQuantity());
        jobContractTest.setUnitPrice(jobTest.getUnitPrice());
        // what about the others?

        return jobContractTest;
    }

    @Override
    public Service getService(String contractId, String serviceName, String parentServiceId, Date date) throws JobSrvcException {
        QueryInfo query = new QueryInfo(Service.class);
        query.addFilter("serviceId.contractId", contractId)
             .addFilter("serviceId.serviceName", serviceName)
             .addFilter("serviceId.parentServiceId", parentServiceId)
             .addFilter("serviceId.beginDate", "endDate", date, FilterOp.BETWEEN);
        log.debug(query);
        try {
            return DaoManager.getDao(Service.class).searchUnique(query);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load Service", e);
        }
    }

    @Override
    public Service getService(ServiceId id) throws JobSrvcException {
        return getService(id.getContractId(), id.getServiceName(), id.getParentServiceId(), id.getBeginDate());
    }

    /****************************************************************************
     * RQ: There are a lot of "update" methods here. I would like NOT to have
     * any of those methods defined here. Instead, let controllers (or
     * controller helpers) to do the updating.
     ****************************************************************************/

    @Override
    // this method does not add value
    public void updateJobInstructions(CEJobOrder jobOrder) throws InvalidJobOrderOperationException {
        for (Instruction ins : jobOrder.getInstructions()) {
            try {
                DaoManager.getDao(Instruction.class).saveOrUpdate(ins);
            }
            catch (DaoException e) {
                throw new InvalidJobOrderOperationException("Exception in saving ce job order and related info", e);
            }
        }
    }

    public void updateJobTest(JobTest jobTest) throws DaoException {
        if (jobTest != null) {
            setPurchaseOrder(jobTest);
            setBranch(jobTest);
            updateManagerAndCreditOverBy(jobTest);
            setServiceLocation(jobTest);
            setServiceOffering(jobTest);
            updateEstimations(jobTest);
            if (jobTest.getRelated() != null && jobTest.getRelated().size() > 0) {
                for (JobTest relatedTest : jobTest.getRelated()) {                    
                    updateJobTest(relatedTest);
                }
            }
            // redundant dao call
            try {
                DaoManager.getDao(JobTest.class).saveOrUpdate(jobTest);
            }
            catch (DaoException e) {
                throw new DaoException("Exception in saving ce job order and related info " + e.getMessage());
            }
        }
    }

    @Override
    public void updateCEJobTest(CEJobOrder jobOrder) throws InvalidJobOrderOperationException {
        try {
            for (JobServiceLevel serviceLevel : jobOrder.getRootServiceLevel().getChildServiceLevels()) {
                for (JobTest jobTest : serviceLevel.getJobTests()) {
                    updateJobTest(jobTest);
                    // setPurchaseOrder(jobTest);
                    // setBranch(jobTest);
                    // updateManagerAndCreditOverBy(jobTest);
                    // setServiceLocation(jobTest);
                    // // setServiceOffering(ceTest);
                    // updateEstimations(jobTest);
                }
            }
        }
        catch (DaoException ex) {
            throw new InvalidJobOrderOperationException("Failed to update JobORder and related info", ex);
        }
        // TODO Hibernate will automatically save the updated object, as long as
        // the jobOrder object is not detached
        // try {
        // DaoManager.getDao(JobTest.class).saveOrUpdate(jobTest);
        // }
        // catch (DaoException e) {
        // throw new InvalidJobOrderOperationException(
        // "Exception in saving ce job order and related info", e);
        // }
        // }
        // try {
        // DaoManager.getDao(JobServiceLevel.class).saveOrUpdate(serviceLevel);
        // }
        // catch (DaoException e) {
        // throw new InvalidJobOrderOperationException(
        // "Exception in saving ce job order and related info", e);
        // }
        // }
    }

    private void setPurchaseOrder(JobTest jobTest) throws DaoException {
        if (jobTest.getPoId() != null ) {
            Dao<PurchaseOrder> purchaseOrderDao = DaoManager.getDao(PurchaseOrder.class);
            PurchaseOrder porder  = purchaseOrderDao.find(jobTest.getPoId());
            jobTest.setPurchaseOrder(porder);
        }
    }

    private void setBranch(JobTest jobTest) throws DaoException {
        if (jobTest.getBranchName() != null && jobTest.getBranchName().trim().length() > 0) {
            Dao<Branch> branchDao = DaoManager.getDao(Branch.class);
            jobTest.setBranch(branchDao.find(jobTest.getBranchName()));
        }
    }

    private void updateManagerAndCreditOverBy(JobTest jobTest) throws DaoException {
        String taskManagerId = jobTest.getTaskManagerId();
        if (!CommonUtil.isNullOrEmpty(taskManagerId)) {
            Employee taskManager = DaoManager.getDao(Employee.class).find(taskManagerId);
            jobTest.setTaskManager(taskManager);
        }
        String creditById = jobTest.getCreditOverrideById();
        if (!CommonUtil.isNullOrEmpty(creditById)) {
            Employee creditOverBy = DaoManager.getDao(Employee.class).find(creditById);
            jobTest.setTaskManager(creditOverBy);
        }
    }

    private void setServiceLocation(JobTest jobTest) throws DaoException {
        String serviceLocationcode = jobTest.getServiceLocationCode();
        if (!CommonUtil.isNullOrEmpty(serviceLocationcode)) {
            ServiceLocation serviceLocation = DaoManager.getDao(ServiceLocation.class).find(serviceLocationcode);
            jobTest.setServiceLocation(serviceLocation);
        }
    }

    private void updateEstimations(JobTest jobTest) throws DaoException {
        for (Estimation est : jobTest.getEstimations()) {
            // TODO to revisit
            UserType userType = est.getUserType();
            if (userType != null) {
                for (UserType ut : UserType.values()) {
                    if (ut.getValue().equals(userType)) {
                        est.setUserType(ut);
                    }
                }
            }
            est.setJobTest(jobTest);
        }
    }

    private void setServiceOffering(JobTest jobTest) throws DaoException {
        Long serviceOfferingId = jobTest.getServiceOfferingId();
        if (serviceOfferingId != null) {
            ServiceOffering serviceOffering = (ServiceOffering) DaoManager.getDao(ServiceOffering.class).find(serviceOfferingId);
            jobTest.setServiceOffering(serviceOffering);
        }
    }

//    /**
//     * RQ: This method does not add any value. Only need to make sure that the
//     * jobOrder object is not detached from Hibernate.
//     */
//    public void updateDepositInvoice(CEJobOrder jobOrder) throws InvalidJobOrderOperationException {
//        // for (DepositInvoice di : jobOrder.getDepositInvoices()) {
//        // try {
//        // DaoManager.getDao(DepositInvoice.class).saveOrUpdate(di);
//        // }
//        // catch (DaoException e) {
//        // throw new InvalidJobOrderOperationException(
//        // "Error during Deposit Invoice update " + e.getMessage());
//        // }
//        // }
//        // JobOrderService service = ServiceManager.getJobOrderService();
//        // service.updateJobOrder(jobOrder);
//    }

    @Override
    public void removeDepositInvoice(CEJobContract jobContract, DepositInvoice depositInvoice) throws InvalidJobOrderOperationException {
        jobContract.removeDepositInvoice(depositInvoice);
        try {
            // depositInvoice.setJobOrder(null);
            DaoManager.getDao(DepositInvoice.class).remove(depositInvoice);
        }
        catch (DaoException e) {
            throw new InvalidJobOrderOperationException("Cannot remove Deposit from the JobContract: " + jobContract.getId());
        }
    }

    /**
     * Quote currently are quotes from ECS and maybe from DC App
     * @throws DaoException 
     */
    @Override
    public CEJobOrder createJobOrder(Quote quote) throws JobOrderCreationException, DaoException {
        //TODO: need map PO number
        Dao<Customer> customerDao=DaoManager.getDao(Customer.class);
        Dao<Contact> contactDao=DaoManager.getDao(Contact.class);
        
        CEJobOrder jo = this.createJobOrder(); // TODO: find a default user for ESB
        jo.setProjectType(ProjectType.TYPE_2);
        jo.setQuote(quote);
        jo.setOrigin(quote.getOrigin());
        jo.setModelNumber(quote.getModelNumber());

        Instruction instruction = new Instruction();
        instruction.setInstructionType(InstructionType.OTHER); // TODO: find a type for instruction
        instruction.setInstruction(quote.getProductionEvaluationDescription());
        jo.addInstruction(instruction);
        jo.setBranch(quote.getBranch());

        try {
            jo.setSalesPerson(this.findById(User.class, quote.getPrimarySalePersonId()));
        }
        catch (DaoException e) {
        }
  
        try {
            jo.setSecondarySalesPerson(this.findById(User.class, quote.getSecondarySalePersonId()));
        }
        catch (DaoException e) {
        }

        try {
            jo.setProjectManager(this.findById(Employee.class, quote.getProjectManagerId()));
        }
        catch (DaoException e) {
        }

        jo.setCustomerReadyDate(quote.getCustomerReadyDate());
        jo.setPromiseCompletionDate(quote.getPromiseCompletionDate());

        ContractCustContactId cccId = new ContractCustContactId();
        cccId.setCustCode(quote.getCustomerId());
        cccId.setContactId(Long.parseLong(quote.getContactId()));
        cccId.setContractCode(quote.getContractId());
        ContractCustContact contractCustContact = null;
        
        contractCustContact = DaoManager.getDao(ContractCustContact.class).find(cccId);

        CEJobContract jc=null;
        try {
            jc=addCustomer(jo, contractCustContact);
        }
        catch (JobSrvcException e) {
            throw new JobOrderCreationException(e);
        }
        
        if(quote.getBillToCustomerId()!=null){
            jc.setBillingCustomer(customerDao.find(quote.getBillToCustomerId()));
        }
        
        if(quote.getBillToContactId()!=null){
            jc.setBillingContact(contactDao.find(Long.parseLong(quote.getBillToContactId())));
        }
        
        if(quote.getShipToCustomerId()!=null){
            jc.setReportReceivingCustomer(customerDao.find(quote.getShipToCustomerId()));
        }
        
        if(quote.getShipToContactId()!=null){
            jc.setReportReceivingContact(contactDao.find(Long.parseLong(quote.getShipToContactId())));
        }
        
        jc.setPoNumber(quote.getAgreementPONumber());
        
        jc.setTransactionCurrency(quote.getCurrencyCode());
        
        // create a product group and product service level too add tests to it
        ProductGroup pg = quote.getProductGroup();
        String serviceLevelName = pg.getProductGroupId().getGroupId() + "/" + "Roofing"; // TODO // get product and replace with Roofing
        
        JobServiceLevel productServiceLevel = jo.getRootServiceLevel().createChildServiceLevel(ServiceLevelType.PRODUCT, serviceLevelName);
        Set<QuoteLine> quoteLines = quote.getQuoteLines();
        for (QuoteLine quoteLine : quoteLines) {
            Test test = null;
            try {
                test = this.findById(Test.class, quoteLine.getItemStandardId());
            }
            catch (DaoException e) {
                throw new JobOrderCreationException(e);
            }
            JobTest jobTest = productServiceLevel.createJobTest(JobTest.class, test);

            jobTest.setQuoteLine(quoteLine);
            jobTest.setQuotedAmount(quoteLine.getQuotedValue());
            jobTest.setLinenumber(quoteLine.getLineItemNumber());

            try {
                jobTest.setTaskManager(this.findById(Employee.class, quoteLine.getTaskOwnerId()));
            }
            catch (DaoException e) {
                throw new JobOrderCreationException(e);
            }

            jobTest.setServiceOffering(quoteLine.getServiceOffering());
            jobTest.setStartDate(quoteLine.getStartDate());
            jobTest.setEndDate(quoteLine.getCompletionDate());

            Estimation estimation = new Estimation();
            estimation.setEstimatedHour(quoteLine.getBillableHours());
            estimation.setUserType(quoteLine.getJobTitle());

            jobTest.addEstimation(estimation);
        }
        return jo;
    }

    @Override
    public List<IntegrationHistory> getJobOrderIntegrationHistory(CEJobOrder jobOrder, Period period) throws JobSrvcException {
        // TODO
        // design Integration classes and use JobIntegration.class

        // need to check user privilege before continue

        // load history for jo
        Dao<IntegrationHistory> dao = DaoManager.getDao(IntegrationHistory.class);
        // use query by example
        IntegrationHistory example = new IntegrationHistory();
        example.setJobOrderNumber(jobOrder.getJobNumber());
        try {
            return dao.search(example);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to load JobOrder IntegrationHistory", e);
        }
    }

    /**
     * why attached file only handled in this method, but not other similar
     * methods? TODO
     */
    @Override
    public void addAttachment(CEJobContract jobContract, JobOrderAttachment attachment, String path, MultipartFile file)
            throws InvalidJobOrderOperationException {

        if (attachment != null && attachment.getJobContract() != null) {
            throw new InvalidJobOrderOperationException("addAttachment - cannot add attachment which is already attached to a job contract");
        }
        try {
            jobContract.addAttachment(attachment);
            attachment.setJobContract(jobContract);
            DaoManager.getDao(JobOrderAttachment.class).saveOrUpdate(attachment);
            DaoManager.getDao(CEJobContract.class).saveOrUpdate(jobContract);
            String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
            File f = new File(path.concat(dateFolder));
            if (!f.exists()) {
                f.mkdir();
            }
            attachment.setFilename(dateFolder.concat("/").concat(String.valueOf(attachment.getId()).concat("_").concat(attachment.getFilename())));
            File xferFile = new File(path + attachment.getFilename());
            file.transferTo(xferFile);

        }
        catch (Exception e) {
            throw new InvalidJobOrderOperationException("addAttachement - cannot add attachments", e);
        }
    }

    @Override
    public void addAttachment(JobTest jobTest, JobTestAttachment attachment, String path, MultipartFile file) throws InvalidJobOrderOperationException {
        if (attachment != null && attachment.getJobTest() != null) {
            throw new InvalidJobOrderOperationException("addAttachment - cannot add attachment which is already attached to a job test");
        }
        try {
            jobTest.addAttachment(attachment);
            attachment.setJobTest(jobTest);
            DaoManager.getDao(JobTestAttachment.class).saveOrUpdate(attachment);
            DaoManager.getDao(JobTest.class).saveOrUpdate(jobTest);
            String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
            File f = new File(path.concat(dateFolder));
            if (!f.exists()) {
                f.mkdir();
            }
            attachment.setFilename(dateFolder.concat("/").concat(String.valueOf(attachment.getId()).concat("_").concat(attachment.getFilename())));
            File xferFile = new File(path + attachment.getFilename());
            file.transferTo(xferFile);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new InvalidJobOrderOperationException("addAttachement - cannot add attachments", e);
        }
    }

    @Override
    public void addAttachment(CEJobOrderLineItem lineItem, JobOrderLineItemAttachment attachment) throws InvalidJobOrderOperationException {
        if (attachment != null && attachment.getJobOrderLineItem() != null) {
            throw new InvalidJobOrderOperationException("addAttachment - cannot add attachment which is already attached to a line item");
        }
        lineItem.addAttachment(attachment);
    }

//    @Override
//    public void addDepositInvoice(CEJobContract jobContract, DepositInvoice depositInvoice) throws InvalidJobOrderOperationException {
//        if (jobContract == null) {
//            throw new InvalidJobOrderOperationException("addDepositInvoice - cannot add deposite invoice to null job order");
//        }
//        if (depositInvoice == null) {
//            throw new InvalidJobOrderOperationException("addDepositInvoice - cannot add null deposite invoice to job order");
//        }
//        jobContract.addDepositInvoice(depositInvoice);
//    }
//
    @Override
    public void addNotes(CEJobOrderLineItem lineItem, JobOrderLineItemNote... notes) throws InvalidJobOrderOperationException {
        if (lineItem == null) {
            throw new InvalidJobOrderOperationException("addNotes - cannot add notes to null line item");
        }
        if (notes == null) {
            return;
        }

        for (JobOrderLineItemNote note : lineItem.getNotes()) {
            lineItem.addNote(note);
        }
        // redundant call
        // try {
        // DaoManager.getDao(JobOrderLineItemNote.class).saveOrUpdate(notes);
        // }
        // catch (Exception e) {
        // throw new
        // InvalidJobOrderOperationException("addNotes - cannot add notes ");
        // }

    }

    @Override
    public void addNotes(CEJobContract jobContract, JobOrderNote... notes) throws InvalidJobOrderOperationException {
        if (jobContract == null) {
            throw new InvalidJobOrderOperationException("addNotes - cannot add notes to null job contract");
        }
        if (notes == null) {
            return;
        }

        for (JobOrderNote note : jobContract.getNotes()) {
            jobContract.addNote(note);
        }
        // redundant call
        try {
            DaoManager.getDao(JobOrderNote.class).saveOrUpdate(notes);
        }
        catch (Exception e) {
            throw new InvalidJobOrderOperationException("addNotes - cannot add notes ");
        }
    }

    @Override
    public void addNotes(JobTest jobTest, JobTestNote... notes) throws InvalidJobOrderOperationException {
        if (jobTest == null) {
            throw new InvalidJobOrderOperationException("addNotes - cannot add notes to null job contract");
        }
        if (notes == null) {
            return;
        }

        for (JobTestNote note : jobTest.getNotes()) {
            jobTest.addNote(note);
        }
        try {
            DaoManager.getDao(JobTestNote.class).saveOrUpdate(notes);
        }
        catch (Exception e) {
            throw new InvalidJobOrderOperationException("addNotes - cannot add notes ");
        }
    }

    protected void addRelatedJobTest(JobTest master, JobTest related) throws InvalidJobOrderOperationException {
        // check the lineItem to see if it is splittable
        if (!canSplit(master)) {
            throw new InvalidJobOrderOperationException("This JobTest cannot be split.");
        }
        master.addRelated(related);
    }

    public boolean canSplit(JobTest jobTest) throws InvalidJobOrderOperationException {
        CEJobOrder jo = jobTest.getJobServiceLevel().getJobOrder();
        switch (jo.getProjectType()) {
        case TYPE_1:
            return false;
        case TYPE_2:
            return true;
        case TYPE_3:
            if (jo.getStatus() == OrderStatus.NEW) {
                // only valid if the job order is just created
                return true;
            }
            return false;
        default:
            throw new InvalidJobOrderOperationException("canSplitJobOrderLineItem - unkown project type");
        }
    }

    @Override
    public CEJobOrder saveJobOrder(CEJobOrder jobOrder) throws InvalidJobOrderOperationException {
        try {
            String jobNumber = jobOrder.getJobNumber();
            if (jobNumber == null) {
                Branch branch = this.findById(Branch.class, jobOrder.getBranchName());
                if (branch != null) {
                    jobNumber = getJobNumberByBranch(branch);
                }
                else {
                    // should only happen in test environment
                    jobNumber = CommonUtil.generateIdCode();
                }
                jobOrder.setJobNumber(jobNumber);
            }
            //TODO To Review: These two methods were called to set the changed  business unit name and branch name as they are
            //  changing back to default  branchname and the businessunit name and saving those default values even though we changed to 
            // some other branch name and buname.
            updateBusinessUnit(jobOrder);
            updateBranch(jobOrder);

            jobOrder.getRootServiceLevel().setJobOrderNumber(jobNumber);
            for(CEJobContract jobContract: jobOrder.getJobContracts()){
                jobContract.setJobOrderNumber(jobNumber);
            }
            DaoManager.getDao(CEJobOrder.class).saveOrUpdate(jobOrder);
        }
        catch (Exception e) {
            throw new InvalidJobOrderOperationException("Failed to save JobOrder.", e);
        }

        return jobOrder;
    }

    // This method returns a transient entity. I am not happy that the returned
    // object is not persisted, it has many implications that not many people
    // can foresee or understand before problems happen. --RQ
    @Override
    public CEJobOrder createJobOrder() throws JobOrderCreationException {
        CEJobOrder jo = new CEJobOrder();

        // Use ServiceManger to obtain a reference to the required service,
        // if we decided to use ServiceLocatior or Dependency Injection,
        // then there is only one place we need to modify.
        // Plus, the current ServiceManager implementation does not depend
        // on SpringFramework, so the unit testing is more straightforward.
        PhxUserService phxUserService = ServiceManager.getUserService();

        try {
            User user = SecurityUtil.getUser();

            if (user != null) {
                String loginName = user.getLoginName();
                user = phxUserService.getUserByName(loginName);
                jo.setBuName(user.getBuName());
                jo.setBranchName(user.getBranchName());
                jo.setBranch(user.getBranch());
                jo.setBu(user.getBusinessUnit());
                // Do not generate the next job number until job is saved
                // jo.setJobNumber(getJobNumberByBranch(user.getBranch()));
                jo.setCreatedByUserName(loginName);
                jo.setCreatedBy(user);
                jo.setCreateDate(new Timestamp(System.currentTimeMillis()));
            }
            else {
                // this should only happen in test case
                jo.setJobNumber(CommonUtil.generateIdCode());
            }
            jo.setProjectType(ProjectType.TYPE_1);
            jo.setPageNumber(Integer.valueOf(1));
            jo.setStatus(OrderStatus.NEW);
            jo.setBillingStatus(BillingStatus.OPEN);
            jo.setOperationalStatus(OperationalStatus.Unscheduled);
            // each JobOrder must contain a root level service level, where
            // test/service instances can be added
            JobServiceLevel rootServiceLevel = new JobServiceLevel();
            rootServiceLevel.setServiceLevelName("JobOrder");
            // TODO check this
            rootServiceLevel.setServiceLevelType(ServiceLevelType.JOB);
            rootServiceLevel.setJobOrder(jo);
            jo.setRootServiceLevel(rootServiceLevel);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new JobOrderCreationException(e);
        }
        return jo;
    }

    // returning a NextJobNumber
    protected synchronized String getJobNumberByBranch(Branch branch) throws DaoException {
        double seqNumber = branch.getSeqNumber();

        if (seqNumber == 0)
            seqNumber = 1;
        else
            seqNumber = seqNumber + 1;

        Double jobSeqNum = new Double(seqNumber);
        String seqNumStr = Integer.toString(jobSeqNum.intValue());
        String updatedSeqNumber = seqNumStr;

        for (int i = 0; i < (7 - seqNumStr.length()); i++) {
            updatedSeqNumber = "0" + updatedSeqNumber;
        }

        branch.setSeqNumber(seqNumber);
        DaoManager.getDao(Branch.class).saveOrUpdate(branch);

        return branch.getName() + "-" + updatedSeqNumber;
    }

    @Override
    public void removeAttachments(CEJobOrder jobOrder, JobOrderAttachment... attachments) throws InvalidJobOrderOperationException {
        CEJobContract jobContract = jobOrder.getJobContract();
        if (jobContract == null || attachments == null) {
            return;
        }

        for (JobOrderAttachment ja : attachments) {
            if (!jobContract.removeAttachment(ja)) {
                throw new InvalidJobOrderOperationException("Cannot remove attachment - attachemnt does not exist in given joborder");
            }

            try {
                DaoManager.getDao(JobOrderAttachment.class).remove(ja);
            }
            catch (Exception e) {
                throw new InvalidJobOrderOperationException("Cannot remove attachment", e);
            }
        }
    }

    @Override
    public void removeAttachments(CEJobOrderLineItem jobOrderLineItem, JobOrderLineItemAttachment... attachments) throws InvalidJobOrderOperationException {
        if (jobOrderLineItem == null || attachments == null) {
            return;
        }

        for (JobOrderLineItemAttachment ja : attachments) {
            if (!jobOrderLineItem.removeAttachments(ja)) {
                throw new InvalidJobOrderOperationException("Cannot remove JobOrderLineItemAttachment - attachemnt does not exist in given joborder");
            }
        }
        try {
            DaoManager.getDao(JobOrderLineItemAttachment.class).remove(attachments);
        }
        catch (Exception e) {
            throw new InvalidJobOrderOperationException("removeAttachments - cannot remove attachment ");
        }
    }

    @Override
    public void removeAttachments(JobTest jobTest, JobTestAttachment... attachments) throws InvalidJobOrderOperationException {
        if (jobTest == null || attachments == null) {
            return;
        }

        for (JobTestAttachment ja : attachments) {
            if (!jobTest.removeAttachment(ja)) {
                throw new InvalidJobOrderOperationException("Cannot remove JobOrderLineItemAttachment - attachemnt does not exist in given joborder");
            }
        }
        try {
            DaoManager.getDao(JobTestAttachment.class).remove(attachments);
        }
        catch (Exception e) {
            throw new InvalidJobOrderOperationException("removeAttachments - cannot remove attachment ");
        }
    }

    /**
     * @see com.intertek.phoenix.job.JobOrderService#removeJobOrder(com.intertek.phoenix.entity.CEJobOrder)
     */
    @Override
    public void removeJobOrder(CEJobOrder jobOrder) throws InvalidJobOrderOperationException {
        if (this.canRemoveJobOrder(jobOrder)) {
            // remove everything step by step TODO

        }
        throw new InvalidJobOrderOperationException("Cannot remove JobOrder that is already scheduled.");
    }

    @Override
    public void removeNotes(CEJobContract jobContract, JobOrderNote... notes) throws InvalidJobOrderOperationException {
        if (jobContract == null || notes == null) {
            return;
        }
        for (JobOrderNote jnote : notes) {
            if (!jobContract.removeNote(jnote)) {
                throw new InvalidJobOrderOperationException("Cannot remove JobOrderNote - note does not exist in given jobContract");
            }
        }

        try {
            DaoManager.getDao(JobOrderNote.class).remove(notes);
        }
        catch (Exception e) {
            throw new InvalidJobOrderOperationException("removeNotes - cannot delete JobOrderNote", e);
        }
    }

    @Override
    public void removeNotes(JobTest jobTest, JobTestNote... notes) throws InvalidJobOrderOperationException {
        if (jobTest == null || notes == null) {
            return;
        }
        for (JobTestNote ja : notes) {
            if (!jobTest.removeNote(ja)) {
                throw new InvalidJobOrderOperationException("Cannot remove JobOrderLineItemAttachment - attachemnt does not exist in given joborder");
            }
        }
        try {
            DaoManager.getDao(JobTestNote.class).remove(notes);
        }
        catch (Exception e) {
            throw new InvalidJobOrderOperationException("removeNotes - cannot delete JobOrderNote", e);
        }
    }

    @Override
    public void removeNotes(CEJobOrderLineItem joli, JobOrderLineItemNote... notes) throws InvalidJobOrderOperationException {
        if (joli == null || notes == null) {
            return;
        }
        for (JobOrderLineItemNote note : notes) {
            if (joli.removeNote(note)) {
                throw new InvalidJobOrderOperationException("Could not remove JobOrderLineItemNote " + note.getId());
                // After this exception is throw, the involved entity joli is
                // no longer in synch with its persistent state, this object
                // must not be reused. This observation is applicable to all
                // removal operations. TODO make it known to all
            }
        }

        try {
            DaoManager.getDao(JobOrderLineItemNote.class).remove(notes);
        }
        catch (Exception e) {
            throw new InvalidJobOrderOperationException("removeNotes - cannot delete JobOrderLineItemNote", e);
        }
    }

    @Override
    public CEJobOrder updateJobOrder(CEJobOrder jobOrder) throws InvalidJobOrderOperationException {
        // TODO this must be revisited afte the demo, higher priority
        try {
            updateServiceLocation(jobOrder);
            updateBusinessUnit(jobOrder);
            updateBranch(jobOrder);
            updateManagerAndSalesPerson(jobOrder);
            // First time when save the JobOrder
            if (jobOrder.getJobNumber() == null) {
                jobOrder.setJobNumber(getJobNumberByBranch(jobOrder.getBranch()));
            }
            User loginUser = SecurityUtil.getUser();
            if (loginUser != null) {
                jobOrder.setUpdatedBy(loginUser);
                jobOrder.setUpdatedByUserName(loginUser.getLoginName());
            }
            jobOrder.setUpdateDate(new Timestamp(System.currentTimeMillis()));

            DaoManager.getDao(CEJobOrder.class).saveOrUpdate(jobOrder);
        }
        catch (DaoException e) {
            throw new InvalidJobOrderOperationException("Exception in saving ce job order and related info " + e.getMessage());
        }
        return jobOrder;
    }

    private void updateManagerAndSalesPerson(CEJobOrder jobOrder) throws DaoException {
        String salesPersonName = jobOrder.getSalesPersonName();
        if (!CommonUtil.isNullOrEmpty(salesPersonName)) {
            User person = DaoManager.getDao(User.class).find(salesPersonName);
            jobOrder.setSalesPerson(person);
        }
        String secondarySalesPerson = jobOrder.getSecondarySalesPersonName();
        if (!CommonUtil.isNullOrEmpty(secondarySalesPerson)) {
            User person = DaoManager.getDao(User.class).find(secondarySalesPerson);
            jobOrder.setSecondarySalesPerson(person);
        }
        String projectManager = jobOrder.getProjectManagerName();
        if (!CommonUtil.isNullOrEmpty(projectManager)) {
            Employee person = DaoManager.getDao(Employee.class).find(projectManager);
            jobOrder.setProjectManager(person);
        }
    }

    private void updateCurrencyInfo(CEJobContract jobContract, Date asOfDate) throws DaoException, JobSrvcException {
        CfgContract contract = this.getCfgContractByContractId(jobContract.getContractCode(), asOfDate);
        jobContract.setContractCurrency(contract.getCurrencyCD());
        String pricebookId = getPriceBookId(contract, asOfDate);

        if (!CommonUtil.isNullOrEmpty(jobContract.getZoneDescription())) {
            return;
        }
        QueryInfo query = new QueryInfo(BranchLocation.class);
        query.addFilter("branchLocationId.branchCode", jobContract.getJobOrder().getBranchName())
             .addFilter("branchLocationId.beginDate", asOfDate, FilterOp.LESS_OR_EQUAL)
             .addFilter("endDate", asOfDate, FilterOp.GREATER_OR_EQUAL);
        if (!CommonUtil.isNullOrEmpty(pricebookId)) {
            query.addFilter("branchLocationId.contractId", new String[] { pricebookId, jobContract.getContractCode() }, FilterOp.IN);
        }
        else {
            query.addFilter("branchLocationId.contractId", jobContract.getContractCode());
        }
        BranchLocation branchLocation = DaoManager.getDao(BranchLocation.class).searchUnique(query);
        String zonedes = null;
        if (branchLocation != null) {
            zonedes = branchLocation.getBranchLocationId().getLocation();
        }
        if (!CommonUtil.isNullOrEmpty(zonedes)) {
            jobContract.setZoneDescription(zonedes);
        }
        else {
            jobContract.setZoneDescription("NONE");
        }
    }

    private void getReferenceFieldInfo(CEJobContract jobContract, Date asOfDate) throws DaoException, JobSrvcException {

        Dao<ReferenceField> dao = DaoManager.getDao(ReferenceField.class);
        QueryInfo query = new QueryInfo(ReferenceField.class);
        query.addFilter("referenceFieldId.contractId", jobContract.getContractCode());
        List<ReferenceField> referenceFields = new ArrayList<ReferenceField>();
        referenceFields = dao.search(query);
        if (referenceFields.size() == 0) {
            CfgContract contract = this.getCfgContractByContractId(jobContract.getContractCode(), asOfDate);
            String pricebookId = getPriceBookId(contract, asOfDate);
            // Note, this is setFilter, not add, because the name filter already
            // exists
            query.setFilter("referenceFieldId.contractId", pricebookId);
            referenceFields = dao.search(query);
        }
        for (ReferenceField ref : referenceFields) {
            if (ref.getSortOrderNum() != null) {
                switch (ref.getSortOrderNum()) {
                case 1:
                    jobContract.setInvoiceLabel1(ref.getReferenceFieldId().getReferenceFieldId());
                    break;
                case 2:
                    jobContract.setInvoiceLabel2(ref.getReferenceFieldId().getReferenceFieldId());
                    break;

                case 3:
                    jobContract.setInvoiceLabel3(ref.getReferenceFieldId().getReferenceFieldId());
                    break;

                case 4:
                    jobContract.setInvoiceLabel4(ref.getReferenceFieldId().getReferenceFieldId());
                    break;

                case 5:
                    jobContract.setInvoiceLabel5(ref.getReferenceFieldId().getReferenceFieldId());
                    break;

                }
            }
        }
    }

    private void updateBusinessUnit(CEJobOrder jobOrder) throws InvalidJobOrderOperationException {
        String buName = jobOrder.getBuName();
        if (buName != null && !"".equals(buName)) {
            BusinessUnit bu;
            try {
                bu = DaoManager.getDao(BusinessUnit.class).find(buName);
            }
            catch (DaoException e) {
                throw new InvalidJobOrderOperationException("Coudn't find businessunit for name :" + buName + " " + e.getMessage());
            }
            jobOrder.setBu(bu);
        }
    }

    private void updateBranch(CEJobOrder jobOrder) throws DaoException {
        String branchName = jobOrder.getBranchName();
        if (!CommonUtil.isNullOrEmpty(branchName)) {
            Branch branch = DaoManager.getDao(Branch.class).find(branchName);
            jobOrder.setBranch(branch);
        }
    }

    private void updateServiceLocation(CEJobOrder jobOrder) throws DaoException {
        String serviceLocationcode = jobOrder.getServiceLocationCode();
        if (!CommonUtil.isNullOrEmpty(serviceLocationcode)) {
            ServiceLocation serviceLocation = DaoManager.getDao(ServiceLocation.class).find(serviceLocationcode);
            jobOrder.setServiceLocation(serviceLocation);
        }
    }

    @Override
    public void validateJobOrder(CEJobOrder jobOrder) throws JobSrvcException {
        for(JobServiceLevel level: jobOrder.getRootServiceLevel().getChildServiceLevels()){
            for(JobTest jobTest: level.getJobTests()){
                if(!jobTest.validatePrice()){
                    throw new JobSrvcException("The total of Estimated Revenue must not exceed" +
                    		" the Quoted Amount of the original line item.");
                }
            }
        }
    }

    @Override
    public List<CEJobOrderLineItem> getBillableJobOrderLineItems(CEJobContract jobContract) throws InvalidJobOrderOperationException {
        return jobContract.getBillableJobOrderLineItems();
    }

    @Override
    public CEJobContract addCustomer(CEJobOrder jobOrder, ContractCustContact contractCustContact) throws JobSrvcException {
        try {            
            TaxSrvc taxSrvc = ServiceManager.getTaxSrvc();
            CEJobContract jobContract = new CEJobContract();
            jobContract.setLanguage("ENG");
            // make sure that the root service level is created
            ContractServiceLevel rootServiceLevel = new ContractServiceLevel();
            rootServiceLevel.setServiceLevelName("JobContract");
            rootServiceLevel.setServiceLevelType(ServiceLevelType.JOB); // why?
            jobContract.setRootServiceLevel(rootServiceLevel);

            jobOrder.addJobContract(jobContract);

            // set contract, customer and contact
            Date date = jobOrder.getNominationDate();
            Contract contract = contractCustContact.getContractCust().getContract();
            jobContract.setContract(contract);
            jobContract.setCustomer(contractCustContact.getContractCust().getCustomer());
            jobContract.setContact(contractCustContact.getContact());            
            // set default currency
            CfgContract cfgContract = this.getCfgContractByContractId(contract.getContractCode(), date);
            jobContract.setContractCurrency(cfgContract.getCurrencyCD());
            jobContract.setTransactionCurrency(cfgContract.getCurrencyCD());
            // set default location
            jobContract.setZoneId(this.getLocation(jobContract, date));

            // setting of the default vatregid and vatreg country
            String custCountry = null;
            String custState = null;
            for (ContactCust contactCust : contractCustContact.getContact().getContactCusts()) {
                for (CustAddress custAddress : contactCust.getCustAddrSeq().getCustAddresses()) {
                    custCountry = custAddress.getCountry();
                    custState = custAddress.getState();   
                    jobContract.setContactAddress(custAddress);
                    break;
                }
            }
            CustVatRegistration custVatRegistration = taxSrvc.getCustomerVatRegistrationByCustCodeandCountryCode(jobContract.getCustomer().getCustCode(),custCountry, custState);
            if(custVatRegistration!=null && !CommonUtil.isNullOrEmpty(custVatRegistration.getCustVatRegistrationId().getRegistrationId())){
                jobContract.setVatRegId(custVatRegistration.getCustVatRegistrationId().getRegistrationId());
                jobContract.setVatRegCountry(custVatRegistration.getCountry().getCountryCode());            
            }
            // TODO Confirm, setting the reference fields
            getReferenceFieldInfo(jobContract, date);            
            // TODO set default banking information if there is only one bank

            return jobContract;
        }
        catch (DaoException ex) {
            throw new JobSrvcException("Failed to add customer", ex);
        }
    }
     
    private String getVatRegCountry(String custCode, String regId) throws DaoException{
        QueryInfo query = new QueryInfo(CustVatRegistration.class);
        query.addFilter("custVatRegistrationId.custCode", custCode);
        query.addFilter("custVatRegistrationId.registrationId", regId);    
        // custCode + registrationId is not the primary key, 
        // must not use searchUnique()
        List<CustVatRegistration> list = DaoManager.getDao(CustVatRegistration.class).search(query);
        if(list.size() > 0){
            return list.get(0).getCustVatRegistrationId().getCountryCode();
        }
        return null;
    }

    private String getLocation(CEJobContract jobContract, Date date) throws JobSrvcException, DaoException {
        CfgContract contract = this.getCfgContractByContractId(jobContract.getContractCode(), date);
        String pricebookId = getPriceBookId(contract, date);
        QueryInfo query = new QueryInfo(BranchLocation.class);
        query.addFilter("branchLocationId.branchCode", jobContract.getJobOrder().getBranchName())
             .addFilter("branchLocationId.beginDate", date, FilterOp.LESS_OR_EQUAL)
             .addFilter("endDate", date, FilterOp.GREATER_OR_EQUAL);
        if (!CommonUtil.isNullOrEmpty(pricebookId)) {
            query.addFilter("branchLocationId.contractId", new String[] { pricebookId, jobContract.getContractCode() }, FilterOp.IN);
        }
        else {
            query.addFilter("branchLocationId.contractId", jobContract.getContractCode());
        }
        BranchLocation branchLocation = DaoManager.getDao(BranchLocation.class).searchUnique(query);
        String location = "NONE";
        if (branchLocation != null) {
            location = branchLocation.getBranchLocationId().getLocation();
        }
        return location;
    }

    @Override
    public void updateCustomer(CEJobOrder jobOrder, CEJobContract jobContract) throws JobSrvcException {

        jobContract.setJobOrderNumber(jobOrder.getJobNumber());
        if (!CommonUtil.isNullOrEmpty(jobContract.getLanguage())) {
            jobContract.setLanguage("ENG");
        }
        
        // RQ: * is used by stored proc for certain lookups, itself is
        // not a valid value.
        // if (jobContract.getZoneId() == null ||
        // "NONE".equals(jobContract.getZoneDescription())) {
        // jobContract.setZoneId("*");
        // }
        try {
            // TODO this logic is questionable
            if(!CommonUtil.isNullOrEmpty(jobContract.getVatRegId())){
                jobContract.setVatRegCountry(this.getVatRegCountry(jobContract.getCustomerCode(),
                                                                   jobContract.getVatRegId()));            
            }
            updateContactInfo(jobContract);
            Date asOfDate = new Date();
            if (jobOrder.getPromiseCompletionDate() != null) {
                asOfDate = jobOrder.getPromiseCompletionDate();
            }
            updateCurrencyInfo(jobContract, asOfDate);
            updateBankInfo(jobContract);
            getReferenceFieldInfo(jobContract, asOfDate);
            // TODO confirm
            // DaoManager.getDao(CEJobOrder.class).saveOrUpdate(jobOrder);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to add customer to JobOrder: " + jobOrder.getJobNumber(), e);
        }
    }

    private void updateBankInfo(CEJobContract jobContract) throws DaoException {
        String remitToCode = jobContract.getRemitToCode();
        if (!CommonUtil.isNullOrEmpty(remitToCode)) {
            Bank bank = DaoManager.getDao(Bank.class).find(remitToCode);
            jobContract.setRemitTo(bank);

            String remitToBankAccountNum = jobContract.getRemitToBankAccountNum();
            if (!CommonUtil.isNullOrEmpty(remitToBankAccountNum)) {
                SearchService searchService = ServiceManager.getSearchService();
                BankSearchInfo searchInfo = new BankSearchInfo();
                searchInfo.setBuName(jobContract.getJobOrder().getBuName());
                searchInfo.setCurrency(jobContract.getTransactionCurrency());
                searchInfo.setBankCode(remitToCode);
                searchInfo.setBankDesc(remitToBankAccountNum);
                List<BankAccount> bankAccounts = searchService.getBankAccount(searchInfo, null, null);
                if (bankAccounts.size() > 0) {
                    BankAccount account = (BankAccount) bankAccounts.get(0);
                    jobContract.setRemitToBankAccount(account);
                }
            }
        }
    }

    @Override
    public void removeContract(CEJobOrder jobOrder, CEJobContract jobContract) throws InvalidJobOrderOperationException {
        // must not originate from ECS
        boolean canDelete = true;
        if (jobOrder.getOrigin() != null && OrderOrigin.ECS == jobOrder.getOrigin()) {
            canDelete = false;
        }
        else if(jobOrder.getStatus() != OrderStatus.OPEN && jobOrder.getStatus() != OrderStatus.NEW){
            canDelete = false;
        }
        else if(jobContract.getBillingEvents().size() > 0){
            canDelete = false;
        }
        else if(jobContract.getRootServiceLevel().getChildServiceLevels().size() > 0){
            canDelete = false;
        }
        else if(jobContract.getJobOrderLineItems().size() > 0){
            canDelete = false; // but should never get here
        }
        
        if(canDelete){
            try {
                jobOrder.removeJobContract(jobContract);
                DaoManager.getDao(CEJobContract.class).remove(jobContract);
                // redundant
                DaoManager.getDao(CEJobOrder.class).saveOrUpdate(jobOrder);
            }
            catch (DaoException e) {
                throw new InvalidJobOrderOperationException("Cannot remove customer from the jobOrder: " + jobOrder.getJobNumber(), e);
            }
        }
        else {
            throw new InvalidJobOrderOperationException("Cannot remove customer from the jobOrder: " + jobOrder.getJobNumber());
        }
    }

    @Override
    public List<CEJobOrderLineItem> getBillableJobOrderLineItems(CEJobContract jobContract, SortInfo s) throws InvalidJobOrderOperationException {
        List<CEJobOrderLineItem> list = getBillableJobOrderLineItems(jobContract);
        final SortInfo sort = s;
        // sort it
        Collections.sort(list, new Comparator<CEJobOrderLineItem>() {
            public int compare(CEJobOrderLineItem one, CEJobOrderLineItem two) {
                Object value1 = CommonUtil.getValue(one, sort.getFieldName());
                Object value2 = CommonUtil.getValue(two, sort.getFieldName());
                return CommonUtil.compare(value1, value2);
            }
        });
        return list;
    }

    @Override
    public void updateRevenueSegregations(CEJobOrderLineItem joli, Set<RevenueSegregation> rsSet) 
            throws DaoException, JobSrvcException {
        double billingAmount = 0;
        for (RevenueSegregation rs : rsSet){
            billingAmount += rs.getAmount();
        }
        // update the billed amount with this totalAmount
        // the billing amount must not exceed the remaining billable amount
        if(billingAmount > joli.getUnbilledAmount() && joli.getTotalAmount() != 0){
            throw new JobSrvcException("Total billing amount must not exceed the remaining billable amount.");
        }

        Dao<RevenueSegregation> dao = DaoManager.getDao(RevenueSegregation.class);

        for (RevenueSegregation rs : rsSet) {
            RevenueSegregation revseg = joli.getRevenueSegregation(rs.getDescription());

            if(revseg != null) {
                if(rs.getAmount() > 0) {
                    //if revseg already present, update its amount
                    revseg.setAmount(rs.getAmount());
                }
                else {
                    //if revseg already present, but updated amount is zero then remove the existing rev seg
                    joli.removeRevenueSegregation(revseg);
                    dao.remove(revseg);
                }
            }
            else {
              //if revseg is not present, add one
                if(rs.getAmount() > 0) {
                    joli.addRevenueSegregation(rs);
                    rs.setCEJobOrderLineItem(joli);
                }
            }
        }
        joli.setAccruedAmount(billingAmount + joli.getBilledAmount());
    }

    // RQ: method like this will waste a lot of cycles TODO
    private void updateContactInfo(CEJobContract jobContract) throws DaoException {
        Long contactId = null;
        String custCode = null;
        Long addressId = null;
        Dao<Contact> contactDao = DaoManager.getDao(Contact.class);
        Dao<Customer> customerDao = DaoManager.getDao(Customer.class);
        Dao<CustAddress> custDao = DaoManager.getDao(CustAddress.class);

        contactId = jobContract.getBillingContactId();
        if (contactId != null && contactId != 0) {
            jobContract.setBillingContact(contactDao.find(contactId));
        }
        custCode = jobContract.getBillingCustCode();
        if (!CommonUtil.isNullOrEmpty(custCode)) {
            jobContract.setBillingCustomer(customerDao.find(custCode));
        }
        addressId = jobContract.getBillingAddressId();
        if (addressId != null && addressId != 0) {
            jobContract.setBillingAddress(custDao.find(addressId));
        }
        contactId = jobContract.getReportReceivingContactId();
        if (contactId != null && contactId != 0) {
            jobContract.setReportReceivingContact(contactDao.find(contactId));
        }
        custCode = jobContract.getReportReceivingCustCode();
        if (!CommonUtil.isNullOrEmpty(custCode)) {
            jobContract.setReportReceivingCustomer(customerDao.find(custCode));
        }
        addressId = jobContract.getReportReceivingAddressId();
        if (addressId != null && addressId != 0) {
            jobContract.setReportReceivingAddress(custDao.find(addressId));
        }
        contactId = jobContract.getSupplierContactId();
        if (contactId != null && contactId != 0) {
            jobContract.setSupplierContact(contactDao.find(contactId));
        }
        custCode = jobContract.getSupplierCustCode();
        if (!CommonUtil.isNullOrEmpty(custCode)) {
            jobContract.setSupplierCustomer(customerDao.find(custCode));
        }
        addressId = jobContract.getSupplierAddressId();
        if (addressId != null && addressId != 0) {
            jobContract.setSupplierAddress(custDao.find(addressId));
        }
        contactId = jobContract.getManufacturerContactId();
        if (contactId != null && contactId != 0) {
            jobContract.setManufacturerContact(contactDao.find(contactId));
        }
        custCode = jobContract.getManfCustCode();
        if (!CommonUtil.isNullOrEmpty(custCode)) {
            jobContract.setManufacturerCustomer(customerDao.find(custCode));
        }
        addressId = jobContract.getManfAddressId();
        if (addressId != null && addressId != 0) {
            jobContract.setManufacturerAddress(custDao.find(addressId));
        }
    }

    /**
     * create the new CEJobOrderLine item instance line items by passin the job
     * tests
     * 
     * @param jobTest
     * @return
     */
    private TestJobOrderLineItem createLineItemObject(JobContractTest jobContractTest) throws DaoException {
        TestJobOrderLineItem joli = new TestJobOrderLineItem();

        joli.setBillingStatus(jobContractTest.getBillingStatus());
        joli.setUom(UOM.EACH);
        
        if (jobContractTest != null ) {
            if(!jobContractTest.isManualTest()){
                String rbKey = jobContractTest.getTest().getRbKey();
                QueryInfo queryInfo = new QueryInfo(RB.class);
                queryInfo.addFilter("rbId.rbKey", rbKey);
                // TODO check if contract and start date, end date is needed or not
                // to retrieve RB value
                // .addFilter("rbId.contractId",
                // serviceLevel.getJobContract().getContractCode());
                String rbValue = null;
    
                List<RB> result = DaoManager.getDao(RB.class).search(queryInfo);
                if (result.size() > 0) {
                    RB rb = result.get(0);
                    rbValue = rb.getRbValue();
                }
    
                if (!CommonUtil.isNullOrEmpty(rbValue)) {
                    joli.setDescription(rbValue);
                }
                else {
                    joli.setDescription(jobContractTest.getLineDescription());
                }
            }
            else{//Manual Test
                joli.setDescription(jobContractTest.getLineDescription());
            }
            joli.setQuantity(jobContractTest.getQuantity());
//            joli.setActivityId(jobContractTest.getActivityId());
            joli.setRelatedToTask(true);
            joli.setServiceOffering(jobContractTest.getServiceOffering());

            if (jobContractTest.getJobTest() != null) {
                joli.setLineNumber(jobContractTest.getJobTest().getLinenumber());
            }
            else {
                setJobOrderLineItemNumber(jobContractTest.getContractServiceLevel().getJobContract(), joli);
            }
            jobContractTest.addJobOrderLineItem(joli);
        }
        return joli;
    }

    /**
     * create the new CEJobOrderLine item instance line items by passin the
     * expression
     * 
     * @param expression
     * @return
     */
    public CEJobOrderLineItem createNewLineItemObject(JobContractServiceExpression expression) throws InvalidJobOrderOperationException {
        if (expression != null) {
            CEJobOrderLineItem joli = new CEJobOrderLineItem();
            JobContractService jocoService = expression.getJobContractService();
            // for a rollup service, some jolis are not billable, only the
            // "main" one is
            if (expression.isMainExpression()) {
                joli.setBillingStatus(BillingStatus.OPEN);
            }
            else { // this should never happen, right?
                joli.setBillingStatus(BillingStatus.NOT_INVOICEABLE);
            }
            joli.setUom(UOM.EACH);

            // TODO must calculate a meaningful description text
            // ceJobOrderLineItem.setDescription("");
            joli.setRelatedToTask(true);
            expression.setJobOrderLineItem(joli);
            setJobOrderLineItemNumber(jocoService.getContractServiceLevel().getJobContract(), joli);
            // redundant dao call
            try {
                DaoManager.getDao(JobContractServiceExpression.class).saveOrUpdate(expression);
                DaoManager.getDao(CEJobOrderLineItem.class).saveOrUpdate(joli);
            }
            catch (DaoException e) {
                throw new InvalidJobOrderOperationException("Failed creating new JOLI for JobContractService");
            }
            return joli;
        }
        return null;
    }

    /* up to this need to check */

    /**
     * Name : calculate and set the maximum of CEJobOrderLineItem number Date
     * :May 25, 2009 purpose :convert the results to entity object and save the
     * entity
     * 
     * @param CEJobOrder
     * @param CEJobOrderLineItem
     * @return
     */
    private void setJobOrderLineItemNumber(CEJobContract jobContract, CEJobOrderLineItem ceJobOrderLineItem) {
        long maxNo = 0;
        for (CEJobOrderLineItem lineItem : jobContract.getJobOrderLineItems()) {
            if (lineItem.getLineNumber() > maxNo) {
                maxNo = lineItem.getLineNumber();
            }
        }
        ceJobOrderLineItem.setLineNumber(maxNo + 1);
    }

    /**
     * create a new DepositInvoice
     * 
     * @param ceJobOrder
     *            CEJobOrRder
     * @return DepositInvoice
     */
    @Override
    public DepositInvoice createDepositInvoice(CEJobContract jobContract) throws JobSrvcException {
        InvoiceService invoiceService = ServiceManager.getInvoiceService();
        try {
            return invoiceService.createDepositInvoice(jobContract);
        }
        catch (InvalidInvoiceOperationException ex) {
            throw new JobSrvcException("Failed to create DepositInvoice", ex);
        }
    }

    /**
     * set the default instructions object
     * 
     * @param ceJobOrder
     *            CEJobOrRder
     * @return DepositInvoice
     */
    @Override
    public void setDefaultInstructions(CEJobOrder ceJobOrder) throws JobSrvcException {
        List<Instruction> instList = new ArrayList<Instruction>();
        // ceJobOrder.setOperationalStatus(OperationalStatus.HOLD);
        if (ceJobOrder.getInstructions() == null || ceJobOrder.getInstructions().size() == 0) {
            Instruction tmpIns = new Instruction();
            tmpIns.setInstructionType(InstructionType.SAMPLE);
            instList.add(tmpIns);

            tmpIns = new Instruction();
            tmpIns.setInstructionType(InstructionType.LAB);
            instList.add(tmpIns);

            tmpIns = new Instruction();
            tmpIns.setInstructionType(InstructionType.SHIPPING);
            instList.add(tmpIns);

            tmpIns = new Instruction();
            tmpIns.setInstructionType(InstructionType.REPORTING);
            instList.add(tmpIns);

            tmpIns = new Instruction();
            tmpIns.setInstructionType(InstructionType.BILLING);
            instList.add(tmpIns);

            tmpIns = new Instruction();
            tmpIns.setInstructionType(InstructionType.OTHER);
            instList.add(tmpIns);

            tmpIns = new Instruction();
            tmpIns.setInstructionType(InstructionType.OPERATION);
            instList.add(tmpIns);

            for (Instruction ins : instList) {
                ins.setJobOrder(ceJobOrder);
                ins.setJobNumber(ceJobOrder.getJobOrderName());
                ceJobOrder.addInstruction(ins);
            }
            tmpIns.setJobOrder(ceJobOrder);
        }
    }

    @Override
    public TestJobOrderLineItem updateJobOrderLineItem(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, JobContractTest jobContractTest)
            throws PricingSrvcException, JobSrvcException, InvalidJobOrderOperationException {
        TestJobOrderLineItem joli = jobContractTest.getJobOrderLineItem();
        if (joli == null) {
            try {
                // TODO create JOLI at the contract level
                joli = this.createLineItemObject(jobContractTest);
                jobContractTest.addJobOrderLineItem(joli);
                DaoManager.getDao(JobContractTest.class).saveOrUpdate(jobContractTest);
                DaoManager.getDao(CEJobOrderLineItem.class).saveOrUpdate(joli);
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed creating new JOLI for JobContractTest");
            }
        }

        PricingSrvc pricingSrvc = ServiceManager.getPricingSrvc();
        PricingInfo priceInfo = pricingSrvc.calculateTestPrice(contractJobOrder, serviceLevel, jobContractTest);
        if (priceInfo != null) {
            setPricingInfo(joli, priceInfo);
        }

        return joli;
    }

    @Override
    public CEJobOrderLineItem updateJobOrderLineItem(ContractJobOrder contractJobOrder, ServiceLevel level, JobContractSlate jobSlate)
            throws PricingSrvcException, JobSrvcException {
        CEJobOrderLineItem joli = jobSlate.getJobOrderLineItem();
        if (joli == null) {
            // TODO create JOLI at the contract level
            // joli = this.createNewLineItemObject(jobSlate);
            jobSlate.setJobOrderLineItem(joli);
        }
        PricingSrvc pricingSrvc = ServiceManager.getPricingSrvc();
        PricingInfo priceInfo = pricingSrvc.calculateSlatePrice(contractJobOrder, level, jobSlate);
        if (priceInfo != null) {
            setPricingInfo(joli, priceInfo);
        }
        return joli;
    }

    @Override
    public Collection<CEJobOrderLineItem> updateJobOrderLineItem(ContractJobOrder contractJobOrder, ServiceLevel level, JobContractService jcs)
            throws PricingSrvcException, JobSrvcException, InvalidJobOrderOperationException {
        List<CEJobOrderLineItem> jolis = new ArrayList<CEJobOrderLineItem>();
        PricingSrvc pricingSrvc = ServiceManager.getPricingSrvc();
        Map<JobContractServiceExpression, PricingInfo> pricingInfos = new HashMap<JobContractServiceExpression, PricingInfo>();
        for (JobContractServiceExpression expression : jcs.getJobContractServiceExpresions()) {
            PricingInfo pricingInfo = pricingSrvc.calculateServicePrice(contractJobOrder, level, jcs, expression);
            if (pricingInfo != null) {
                pricingInfos.put(expression, pricingInfo);
            }
        }

        if (jcs.isRollup() == true) {
            JobContractServiceExpression mainExpression = jcs.getMainServiceItem();
            CEJobOrderLineItem joli = mainExpression.getJobOrderLineItem();
            if (joli == null) {
                joli = new CEJobOrderLineItem(); // TODO
                joli.setBillingStatus(BillingStatus.OPEN);
                mainExpression.setJobOrderLineItem(joli);
            }
            this.setPricingInfo(joli, sumPricingInfo(pricingInfos));
            // this will be the only joli for rollup services
            jolis.add(joli);

            // do revenue segregation
            for (JobContractServiceExpression expression : pricingInfos.keySet()) {
                // TODO verify
                String lineDesc = expression.getExpressionId();
                RevenueSegregation revSeg = joli.getRevenueSegregation(lineDesc);
                if (revSeg == null) {
                    revSeg = new RevenueSegregation();
                    revSeg.setDescription(lineDesc);
                    joli.addRevenueSegregation(revSeg);
                }
                // update revseg with the GLCode in pricing info
                setPricingInfo(revSeg, pricingInfos.get(expression));
            }
        }
        else { // normal service does not have rollup feature

            for (JobContractServiceExpression expression : pricingInfos.keySet()) {
                CEJobOrderLineItem joli = expression.getJobOrderLineItem();
                if (joli == null) {
                    joli = createNewLineItemObject(expression);
                    joli.setBillingStatus(BillingStatus.OPEN);
                }
                PricingInfo pricingInfo = pricingInfos.get(expression);
                setPricingInfo(joli, pricingInfo);
                jolis.add(joli);
            }
        }
        return jolis;
    }

    /**
     * @param revSeg
     * @param priceInfo
     */
    private void setPricingInfo(RevenueSegregation revSeg, PricingInfo priceInfo) {
        revSeg.setAccount(priceInfo.getAccountInfo().getGlCode());
        revSeg.setAmount(priceInfo.getTotalPrice());
        revSeg.setDeptId(priceInfo.getAccountInfo().getDepartmentCode());
    }

    /**
     * @param joli
     * @param values
     */
    private PricingInfo sumPricingInfo(Map<JobContractServiceExpression, PricingInfo> pricingInfos) {
        PricingInfo sum = new PricingInfo();
        double netPrice = 0;
        AccountInfo accountInfo = null;
        for (JobContractServiceExpression exp : pricingInfos.keySet()) {
            PricingInfo pi = pricingInfos.get(exp);
            netPrice += pi.getTotalPrice();
            if (exp.isMainExpression()) {
                accountInfo = pi.getAccountInfo();
            }
        }
        sum.setTotalPrice(netPrice);
        sum.setAccountInfo(accountInfo);

        return sum;
    }

    /**
     * @param joli
     * @param priceInfo
     */
    private void setPricingInfo(CEJobOrderLineItem joli, PricingInfo priceInfo) {
        // set pricing information here
        joli.setUnitPrice(priceInfo.getUnitPrice());
        joli.setNetPrice(priceInfo.getTotalPrice());
        joli.setQuantity(priceInfo.getInputInfo().getDoubleQuantity());
        joli.setDiscountPct(priceInfo.getDiscountPct());

        joli.setPrimaryBranchCd(priceInfo.getAccountInfo().getBranchCode());
        joli.setProductGroup(priceInfo.getAccountInfo().getProductGroup());
        joli.setDeptid(priceInfo.getAccountInfo().getDepartmentCode());
        joli.setAccount(priceInfo.getAccountInfo().getGlCode());
        // joli.setBusStreamCode();

        // set tax stuff here

        // currency and base price
        joli.setRateMult(priceInfo.getRateMult());
        joli.setRateDiv(priceInfo.getRateDiv());
        joli.setBaseUnitPrice(priceInfo.getBaseUnitPrice());
        joli.setBaseNetPrice(priceInfo.getBaseNetPrice());

        joli.setEditable(priceInfo.isEditable());
    }

    @Override
    public void updateContractNote(CEJobOrder jobOrder, CEJobContract jobContract, JobOrderNote note) throws JobSrvcException {
        // redundant dao call
        try {
            DaoManager.getDao(JobOrderNote.class).saveOrUpdate(note);
        }
        catch (DaoException e) {
            throw new InvalidJobOrderOperationException("Failed updating Notes to JobContract & JobOrder: " + jobOrder.getJobNumber());
        }
        this.updateCustomer(jobOrder, jobContract);
        this.updateJobOrder(jobOrder);
    }

    @Override
    public void updateJobTestNote(JobTest jobTest, JobTestNote note) throws JobSrvcException {
        // redundant dao call
        try {
            DaoManager.getDao(JobTestNote.class).saveOrUpdate(note);
            this.updateJobTest(jobTest);
        }
        catch (DaoException e) {
            throw new InvalidJobOrderOperationException("Failed updating Notes to JobTest  " + jobTest.getId());
        }
    }

    @Override
    public void updateContractAttachment(CEJobOrder jobOrder, CEJobContract jobContract, JobOrderAttachment attachment) throws JobSrvcException {
        // redundant dao call
        // try {
        // DaoManager.getDao(JobOrderAttachment.class).saveOrUpdate(attachment);
        // }
        // catch (DaoException e) {
        // throw new InvalidJobOrderOperationException(
        // "Failed updating attachment to JobContract & JobOrder: " +
        // jobOrder.getJobNumber());
        // }
        this.updateCustomer(jobOrder, jobContract);
        this.updateJobOrder(jobOrder);
    }

    public CEJobContract getJobContract(String JobNumber, String jobContractId) throws JobSrvcException {
        CEJobContract ceJobContract = null;

        Dao<CEJobOrder> jobOrderDao = DaoManager.getDao(CEJobOrder.class);
        try {
            CEJobOrder jobOrder = jobOrderDao.find(JobNumber);
            ceJobContract = jobOrder.getJobContract();
            // RQ: why do we need this code? Isn't lazy loading supposed to do
            // that??
            // If lazy loading is not working, we must fix it, not keep adding
            // work around
            // if (ceJobContract != null) {
            // if (ceJobContract.getNotes() == null ||
            // ceJobContract.getNotes().size() <= 0) {
            //
            // Dao<JobOrderNote> dao = DaoManager.getDao(JobOrderNote.class);
            // QueryInfo query = new QueryInfo(JobOrderNote.class);
            // query.addFilter("jobContractId", ceJobContract.getId());
            // List<JobOrderNote> noteList = new ArrayList<JobOrderNote>();
            // noteList = dao.search(query);
            // Set<JobOrderNote> set = new HashSet<JobOrderNote>();
            // if (noteList != null && noteList.size() > 0) {
            // for (JobOrderNote note : noteList) {
            // set.add(note);
            // }
            // ceJobContract.setNotes(set);
            // }
            // }
            // }
        }
        catch (DaoException e) {
            throw new InvalidJobOrderOperationException("Failed get  JobContract  " + jobContractId);
        }
        return ceJobContract;
    }

    @Override
    public List<ServiceOffering> getServiceOffering(String testId) throws JobSrvcException {
        try {
            if (CommonUtil.isNullOrEmpty(testId)){
                return getParentServiceOffering();
            }
            Dao<ServiceOfferingTest> dao = DaoManager.getDao(ServiceOfferingTest.class);
            QueryInfo query = new QueryInfo(ServiceOfferingTest.class);
            query.addFilter(new FilterInfo("testId", testId, true))
                 .addLeftJoin("serviceOffering")
                 .addFilter(new FilterInfo("parentServiceOfferingId", FilterOp.IS_NOT_NULL));
            List<ServiceOfferingTest> list = dao.search(query);
            List<ServiceOffering> result = new ArrayList<ServiceOffering>();
            if (list.size() > 0) {
                for (ServiceOfferingTest sot : list) {
                    result.add(sot.getServiceOffering());
                }
            }
            else { // when this test doesn't have associated service offering
                   // we retrive all parent level SO
                result = getParentServiceOffering();
            }
            return result;
        }
        catch (DaoException ex) {
            throw new JobSrvcException("Failed to retrieve ServiceOffering", ex);
        }
    }

    private List<ServiceOffering> getParentServiceOffering() throws DaoException {
        List<ServiceOffering> result;
        Dao<ServiceOffering> soDao = DaoManager.getDao(ServiceOffering.class);
        QueryInfo query = new QueryInfo(ServiceOffering.class);
        query.addFilter(new FilterInfo("parentServiceOfferingId", FilterOp.IS_NULL));
        result = soDao.search(query);
        return result;
    }

    @Override
    public JobTest createRelatedJobTest(JobServiceLevel serviceLevel, Test test, JobTest master) throws JobSrvcException {
        JobTest jobTest = serviceLevel.createJobTest(JobTest.class, test);
        this.addRelatedJobTest(master, jobTest);

        try {
            DaoManager.getDao(JobTest.class).saveOrUpdate(jobTest);
        }

        catch (DaoException e) {
            throw new JobSrvcException("Failed to save new JobTest", e);
        }

        return jobTest;
    }

    @Override
    public ContractServiceLevel createContractServiceLevel(CEJobContract joco, JobServiceLevel jobSL) throws JobSrvcException {
        ContractServiceLevel root = joco.getRootServiceLevel();
        ContractServiceLevel newContractSL = root.createChildServiceLevel(jobSL.getServiceLevelType(), jobSL.getServiceLevelName());
        newContractSL.setJobServiceLevel(jobSL);
        newContractSL.setDescription(jobSL.getDescription());

        try {
            DaoManager.getDao(ContractServiceLevel.class).saveOrUpdate(newContractSL);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to create ContractServiceLevel", e);
        }

        return newContractSL;
    }

    @Override
    public Estimation createEstimation(JobTest jobTest) throws JobSrvcException {
        Estimation estimation = new Estimation();
        jobTest.addEstimation(estimation);
        try {
            DaoManager.getDao(Estimation.class).saveOrUpdate(estimation);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to save new Estimation", e);
        }
        return estimation;
    }

    @Override
    public void removeEstimation(JobTest jobTest, Estimation estimation) throws JobSrvcException {
        try {
            estimation.setJobTest(null);
            estimation.setJobTestId(null);
            DaoManager.getDao(Estimation.class).remove(estimation);
            jobTest.removeEstimation(estimation);
        }
        catch (DaoException e) {
            throw new JobSrvcException("Failed to remove the estimation object", e);
        }
    }

    @Override
    public void createOrUpdateQuote(CEJobOrder jobOrder, Timestamp quoteDate, String quoteNumber) {
        Quote quote = jobOrder.getQuote();
        if (quote == null) {
            quote = new Quote();
        }
        quote.setQuoteDate(quoteDate);
        quote.setQuoteNumber(quoteNumber);
        jobOrder.setQuote(quote);
    }

    @Override
    public CEJobContract processBillingEvent(BillingEvent billingEvent) throws JobSrvcException {
        CEJobContract jobContract = billingEvent.getJobContract();
        if(billingEvent.isApplied()){
            // this BillingEvent is already processed
            return jobContract;
        }
        else{
            try{
                for(BillingEventItem item: billingEvent.getBillingEventItems()){
                    // a new JOLI for each billing event item
                    TestJobOrderLineItem joli = createJobOrderLineItem(item);
                    for(BillingLineDistribution dist: item.getBillingLineDistributions()){
                        RevenueSegregation revSeg = createRevenueSegregation(dist);
                        joli.addRevenueSegregation(revSeg);
                    }                    
                    jobContract.addJobOrderLineItem(joli);
                    DaoManager.getDao(CEJobOrderLineItem.class).saveOrUpdate(joli);
                }
            }
            catch(DaoException ex){
                throw new JobSrvcException("Error in processing BillingEvent", ex);
            }
            billingEvent.setApplied(true);
            return jobContract;
        }
    }
    
    private RevenueSegregation createRevenueSegregation(BillingLineDistribution dist){
        RevenueSegregation revSeg = new RevenueSegregation();
        revSeg.setDescription(""); // TODO where is description?
        revSeg.setAccount(dist.getAccountCode());
        revSeg.setAmount(dist.getDistributionAmt());
        revSeg.setDeptId(dist.getDepartmentId());
        
        return revSeg;
    }

    /**
     * @param line
     * @return
     * @throws DaoException 
     */
    private TestJobOrderLineItem createJobOrderLineItem(BillingEventItem item) throws DaoException {
        TestJobOrderLineItem joli = new TestJobOrderLineItem();
        joli.setBillingStatus(BillingStatus.OPEN);
        joli.setUom(UOM.EACH);
        joli.setRelatedToTask(false);
        joli.setBatchNumber(item.getBillingEvent().getPsInvoiceNumber()); // temp invoice number
        String serviceOfferingId = item.getServiceOffering();

        if (serviceOfferingId != null) {
            ServiceOffering serviceOffering = this.findById(ServiceOffering.class, serviceOfferingId);
            joli.setServiceOffering(serviceOffering);
        }
        joli.setDescription(item.getDescription());
        joli.setQuantity(item.getQuantity());
        joli.setLineNumber(Long.parseLong(item.getPsLineNumber()));
        joli.setBilledAmount(item.getInvoiceAmount());
        joli.setUnitPrice(item.getUnitAmount());
        joli.setCurrencyCd(item.getBillingCurrencyCD());
        // activityID is used to correlate with PeopleSoft
        joli.setActivityId(item.getActivityId());

        return joli;
    }

    /**
     * @throws DaoException
     * @see com.intertek.phoenix.job.JobOrderService#adjustJobOrderLineItem(com.intertek.phoenix.entity.CEInvoiceLineItem)
     */
    @Override
    public CEJobOrderLineItem sealJobOrderLineItem(CEJobOrderLineItem joli, String batchNumber) throws JobSrvcException {
        joli.adjustBilledAmount();
        if(joli.getBillingStatus() != BillingStatus.NOT_INVOICEABLE){
            joli.setBillingStatus(BillingStatus.INVOICED);
        }
        joli.setBatchNumber(batchNumber);
        // if there is outstanding amount, a new JOLI must be
        // added after the current one is "sealed"
        double balance = joli.getUnbilledAmount();
        if (balance > 0) {
            CEJobOrderLineItem newJoli = joli.clone();
            // must override the batch number so it can be billed the next time
            newJoli.setBatchNumber(null);
            if(joli.getBillingStatus() != BillingStatus.NOT_INVOICEABLE){
                newJoli.setBillingStatus(BillingStatus.OPEN);
            }
            else{
                // keep non_invoiceable status
                newJoli.setBillingStatus(BillingStatus.NOT_INVOICEABLE);
            }
            // if non-billable related line items exist, need to create
            // more jolis for them
            for(CEJobOrderLineItem related: joli.getRelated()){               
                if(related.getUnbilledAmount() > 0&& related.getBillingStatus() == BillingStatus.NOT_INVOICEABLE){
                    CEJobOrderLineItem newRelated = sealJobOrderLineItem(related, batchNumber);
                    if(newRelated != null){
                        newRelated.setMaster(newJoli);
                    }
                }
            }
            // for fully invoiced line items (invoiceable or not),
            // there will be no new JOLI generated, this will create a situation
            // that may have not been recognized, simply put, a not-fully-invoiced
            // master will not contain fully invoiced not_invoiceable related
            // items. It is correct in business sense, but may come as a surprise
            // to the end user, because the related item is no longer displayed
            // on the select charges page.
            
            try {
                DaoManager.getDao(CEJobOrderLineItem.class).saveOrUpdate(newJoli);
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed to seal invoiced JobOrderLineItem.", e);
            }
            return newJoli;
        }
        return null;
    }

    @Override
    public void creditJobOrderLineItem(CEJobOrderLineItem joli) {
        joli.setBillingStatus(BillingStatus.CANCELED);

        // TODO to be completed
    }

    /**
     * @see com.intertek.phoenix.job.JobOrderService#createSampleTracking(com.intertek.phoenix.entity.CEJobContract)
     */
    @Override
    public SampleTracking createSampleTracking(CEJobContract jobContract) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see com.intertek.phoenix.job.JobOrderService#removeSampleTracking(com.intertek.phoenix.entity.CEJobContract, com.intertek.phoenix.entity.SampleTracking)
     */
    @Override
    public boolean removeSampleTracking(CEJobContract jobContract, SampleTracking sampleTracking) {
        // TODO Auto-generated method stub
        return false;
    }
}
