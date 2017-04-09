/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.intertek.entity.BranchCode;
import com.intertek.entity.CfgContract;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.ControlMap;
import com.intertek.entity.CurrencyRate;
import com.intertek.entity.Customer;
import com.intertek.entity.Department;
import com.intertek.entity.Expression;
import com.intertek.entity.ExpressionGLCode;
import com.intertek.entity.LocationDiscount;
import com.intertek.entity.PriceBook;
import com.intertek.entity.PricingModel;
import com.intertek.entity.ServiceRate;
import com.intertek.entity.ServiceRateId;
import com.intertek.entity.SlatePrice;
import com.intertek.entity.TestPrice;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.FilterOp;
import com.intertek.phoenix.dao.QueryInfo;
import com.intertek.phoenix.entity.JobContractService;
import com.intertek.phoenix.entity.JobContractServiceControl;
import com.intertek.phoenix.entity.JobContractServiceExpression;
import com.intertek.phoenix.entity.JobContractSlate;
import com.intertek.phoenix.entity.JobContractTest;
import com.intertek.phoenix.entity.JobOrderType;
import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.phoenix.job.ContractJobOrder;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.phoenix.job.Order;
import com.intertek.phoenix.job.ServiceLevel;
import com.intertek.phoenix.job.ServiceLevel.ServiceLevelType;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.NumberUtil;

/**
 * 
 * @author richard.qin
 */
public class PricingSrvcImpl implements PricingSrvc {

    @Override
    public PricingInfo calculateTestPrice(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, 
                                          JobContractTest jobContractTest) throws PricingSrvcException, JobSrvcException {
        PricingInfo result = new PricingInfo();

        /* preparation */
        Order jobOrder = contractJobOrder.getJobOrder();
        // get the CfgContract, but should cfgContract be available in
        // contractJobOrder directly? TODO
        String contractCode = contractJobOrder.getContract().getContractCode();
        Date nominationDate = jobOrder.getNominationDate();
        CfgContract contract = ServiceManager.getJobOrderService().getCfgContractByContractId(contractCode, nominationDate);
        // the use of Constants.TEST follows the phoenix 1 logic, not to be
        // confused with the new Constants.CE_TEST, which is defined for
        // revenue segregation for Tests. However, it becomes inconsistent.
        // TODO need to review
        Expression expression = ServiceManager.getJobOrderService().getExpressionByExpressionId(Constants.TEST); 
        // get the location discount
        String contractId = contract.getCfgContractId().getContractId();
        LocationDiscount ld = getLocationDiscount(contractId, contractJobOrder.getZone(), nominationDate);

        /* get the right pricer */
        Pricer pricer = PricerManager.getPricer(PricingModelType.TEST_SERVICE);
        InputInfo inputInfo = new InputInfo();
        inputInfo.setDoubleQuantity(jobContractTest.getQuantity());
        if(jobContractTest.getTest() != null){
            // for non-manual test only
            inputInfo.setTestOrSlateId(jobContractTest.getTest().getTestId());
        }
        inputInfo.setDescription(jobContractTest.getLineDescription());

        /* get the TestPrice */
        TestPrice testPrice = this.getTestPrice(contractJobOrder, serviceLevel, jobContractTest);
        // build a temporary ServiceRate from TestPrice
        ServiceRate serviceRate = createServiceRateFromTestPrice(testPrice);
        // and adjust the temporary ServiceRate
        serviceRate = this.adjustServiceRate(contract, contractJobOrder, expression, serviceRate, ld, nominationDate, result);

        /* calculate price */
        double totalPrice = pricer.calculatePrice(serviceRate, inputInfo);
        if (totalPrice == 0) {
            return null;
        }
        else {
            totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
        }
        result.setInputInfo(inputInfo);
        result.setServiceRate(serviceRate);
        result.setPriceBeforeDiscount(totalPrice);

        /* is contract price */
        boolean isContractPrice = false;
        if(testPrice.getTestPriceId() != null){
            isContractPrice = contractId.equals(testPrice.getTestPriceId().getContractId());
        }
        result.setIsContractPrice(isContractPrice);
        /* calculate discount */
        if (ld != null) {
            double discountPct = pricer.calculateDiscount(contract, null, expression, serviceRate, ld);
            if (discountPct > 0) {
                totalPrice *= (1.0 - discountPct * 0.01);
                boolean hideTestDiscount = contract.getHideTestDiscount();
                if (hideTestDiscount == true) {
                    result.setDiscountPct(0.0);
                }
                else {
                    result.setDiscountPct(discountPct);
                }
            }
            totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
        }
        result.setTotalPrice(totalPrice);

        /* get accounting info */
        AccountInfo accountInfo = getAccountInfo(expression, jobOrder.getJobType(), contractJobOrder.getProductType(), 
                                                 jobOrder.getBranchName(), serviceLevel, contractJobOrder.getCustomer(),
                                                 jobContractTest.getServiceOffering());
        result.setAccountInfo(accountInfo);

        /* is the pricing editable ? */
        boolean editable = getIsEditable(result);
        result.setEditable(editable);

        return result;
    }

    /**
     * @return
     */
    private ServiceRate createServiceRateFromTestPrice(TestPrice testPrice) {
        ServiceRate rate = new ServiceRate();
        rate.setUnitPrice(testPrice.getUnitPrice());
        if(testPrice.getContractRef() != null){
            rate.setContractRef(testPrice.getContractRef());
        }
        if(testPrice.getTestPriceId() != null){
            rate.setServiceRateId(new ServiceRateId());
            rate.getServiceRateId().setLocation(testPrice.getTestPriceId().getLocation());
            rate.getServiceRateId().setContractId(testPrice.getTestPriceId().getContractId());
        }
        return rate;
    }

    @Override
    public PricingInfo calculateSlatePrice(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, 
                                           JobContractSlate jobContractSlate) throws PricingSrvcException, JobSrvcException {
        PricingInfo result = new PricingInfo();

        /* preparation */
        Order jobOrder = contractJobOrder.getJobOrder();
        String contractCode = contractJobOrder.getContract().getContractCode();
        Date nominationDate = jobOrder.getNominationDate();
        CfgContract contract = ServiceManager.getJobOrderService().getCfgContractByContractId(contractCode, nominationDate);

        Expression expression = ServiceManager.getJobOrderService().getExpressionByExpressionId(Constants.SLATE);
        // get the location discount
        String contractId = contract.getCfgContractId().getContractId();
        LocationDiscount ld = getLocationDiscount(contractId, contractJobOrder.getZone(), nominationDate);

        /* get the right pricer */
        Pricer pricer = PricerManager.getPricer(PricingModelType.SLATE);
        InputInfo inputInfo = new InputInfo();
        inputInfo.setDoubleQuantity(jobContractSlate.getQuantity());
        inputInfo.setTestOrSlateId(jobContractSlate.getSlate().getSlateId());
        inputInfo.setDescription(jobContractSlate.getLineDescription());

        /* get the SlatePrice */
        SlatePrice slatePrice = this.getSlatePrice(contractJobOrder, serviceLevel, jobContractSlate);
        // build a temporary ServiceRate from SlatePrice
        ServiceRate serviceRate = createServiceRateFromSlatePrice(slatePrice);
        // adjust the SlatePrice
        serviceRate = this.adjustServiceRate(contract, contractJobOrder, expression, serviceRate, ld, nominationDate, result);

        /* calculate price */
        double totalPrice = pricer.calculatePrice(serviceRate, inputInfo);
        if (totalPrice == 0) {
            return null;
        }
        else {
            totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
        }
        result.setInputInfo(inputInfo);
        result.setServiceRate(serviceRate);
        result.setPriceBeforeDiscount(totalPrice);

        /* is contract price */
        boolean isContractPrice = slatePrice.getSlatePriceId().getContractId().equals(contractId);
        result.setIsContractPrice(isContractPrice);
        /* calculate discount */
        if (ld != null) {
            double discountPct = pricer.calculateDiscount(contract, null, expression, serviceRate, ld);
            if (discountPct > 0) {
                totalPrice *= (1.0 - discountPct * 0.01);
                result.setDiscountPct(discountPct);
            }
            totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
        }
        result.setTotalPrice(totalPrice);

        /* get accounting info */
        AccountInfo accountInfo = getAccountInfo(expression, jobOrder.getJobType(), contractJobOrder.getProductType(), 
                                                 jobOrder.getBranchName(), serviceLevel, contractJobOrder.getCustomer(),
                                                 null);
        result.setAccountInfo(accountInfo);

        /* is the pricing editable ? */
        boolean editable = getIsEditable(result);
        result.setEditable(editable);

        return result;
    }

    private ServiceRate createServiceRateFromSlatePrice(SlatePrice slatePrice) {
        ServiceRate rate = new ServiceRate();
        rate.setUnitPrice(slatePrice.getUnitPrice());
        rate.setContractRef(slatePrice.getContractRef());
        rate.setServiceRateId(new ServiceRateId());
        rate.getServiceRateId().setLocation(slatePrice.getSlatePriceId().getLocation());
        rate.getServiceRateId().setContractId(slatePrice.getSlatePriceId().getContractId());
        return rate;
    }

    /**
     * @see com.intertek.phoenix.pricing.PricingSrvc#calculateServicePrice(com.intertek.phoenix.job.ContractJobOrder, com.intertek.phoenix.job.ServiceLevel, com.intertek.phoenix.entity.JobContractService, com.intertek.phoenix.entity.JobContractServiceExpression)
     */
    @Override
    public PricingInfo calculateServicePrice(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, JobContractService service,
                                             JobContractServiceExpression jobServiceExpression) throws PricingSrvcException, JobSrvcException {
        PricingInfo result = new PricingInfo();

        /* preparation */
        Order jobOrder = contractJobOrder.getJobOrder();
        String contractCode = contractJobOrder.getContract().getContractCode();
        Date nominationDate = jobOrder.getNominationDate();
        CfgContract contract = ServiceManager.getJobOrderService().getCfgContractByContractId(contractCode, nominationDate);
        // get the service expression
        ContractExpression contractExpression = jobServiceExpression.getContractExpression();
        // get the related service controls
        List<JobContractServiceControl> serviceControls = getRelatedControls(contract, service, contractExpression);
        // get the expression object
        String expressionId = contractExpression.getServiceRateExpressionId();
        if(expressionId.equals("-1")){ 
            // when this happens, some ad hoc logic must be executed to figure
            // out the applicable service rate, TODO
            // for now, throw and exception
            throw new PricingSrvcException("Invalid service rate experssion id: -1");
        }
        Expression expression = ServiceManager.getJobOrderService().getExpressionByExpressionId(expressionId);
        // get the location discount
        String contractId = contract.getCfgContractId().getContractId();
        LocationDiscount ld = getLocationDiscount(contractId, contractJobOrder.getZone(), nominationDate);

        // for the main expression of a roll up service, don't calculate price
        if (!service.isRollup() || !jobServiceExpression.isMainExpression()) {
            /* get the right pricer */
            // find the appropriate pricer first
            Pricer pricer = this.getPricer(contractExpression.getComponentType());

            /* find the inputInfo for the expression */
            // different pricing model has its own idea of where to get quantities
            InputInfo inputInfo = pricer.getInputInfo(service, jobServiceExpression, serviceControls);
            result.setInputInfo(inputInfo);

            /* get the ServiceRate */
            //getPricingParameters() is deprecated, because it is less readable.
            //Object[] params = pricer.getPricingParameters(contract, contractJobOrder, serviceLevel, contractExpression, inputInfo);
            PricingParameter param = pricer.getPricingParameter(contract, contractJobOrder, serviceLevel, 
                                                                contractExpression, inputInfo);
            ServiceRate serviceRate = this.getServicePrice(param);
            // adjust service rate
            serviceRate = this.adjustServiceRate(contract, contractJobOrder, expression, serviceRate, ld, nominationDate, result);
            result.setServiceRate(serviceRate);

            /* calculate price */
            Double totalPrice = pricer.calculatePrice(serviceRate, inputInfo);
            // this can be moved to calculatePrice?
            totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
            result.setPriceBeforeDiscount(totalPrice);

            /* adjust min and max prices */
            // but, why do this?
            double pct = inputInfo.getPercentage();
            if (pct > 1) {
                if (totalPrice.equals(serviceRate.getMinimumPrice() * pct)) {
                    result.setMinPrice(serviceRate.getMinimumPrice());
                }
                if (totalPrice.equals(serviceRate.getMaximumPrice() * pct)) {
                    result.setMaxprice(serviceRate.getMaximumPrice());
                }
            }
            else {
                if (totalPrice.equals(serviceRate.getMinimumPrice())) {
                    result.setMinPrice(serviceRate.getMinimumPrice());
                }
                if (totalPrice.equals(serviceRate.getMaximumPrice())) {
                    result.setMaxprice(serviceRate.getMaximumPrice());
                }
            }

            /* is contract price */
            boolean isContractPrice = serviceRate.getServiceRateId().getContractId().equals(contractId);
            result.setIsContractPrice(isContractPrice);
            /* calculate discount */
            if (ld != null) {
                double discount = pricer.calculateDiscount(contract, contractExpression, expression, serviceRate, ld);
                if (discount > 0) {
                    totalPrice *= (1.0 - discount * 0.01);
                    result.setDiscountPct(discount);
                }

                /* the final total price */
                totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
            }
            result.setTotalPrice(totalPrice);
        }

        /* get accounting info */
        // double userGroupId = contractExpression.getUseGroupId();
        AccountInfo accountInfo = getAccountInfo(expression, jobOrder.getJobType(), contractJobOrder.getProductType(), 
                                                 jobOrder.getBranchName(), serviceLevel, contractJobOrder.getCustomer(),
                                                 null);
        result.setAccountInfo(accountInfo);

        /* is the pricing editable ?*/
        boolean editable = getIsEditable(result);
        result.setEditable(editable);

        return result; 
    }

    @Override
    public AccountInfo getAccountInfo(Expression expression, JobOrderType jobType, String productType, 
                                      String branchCode, ServiceLevel serviceLevel, Customer customer,
                                      ServiceOffering serviceOffering) throws JobSrvcException {
        AccountInfo accountInfo = new AccountInfo();
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();

        String masterGroupId = null;
        if (serviceLevel != null && serviceLevel.getServiceLevelType() == ServiceLevelType.PRODUCT) {
            // TODO how to work out this one??
            // JobContractProd jobContractProd = (JobContractProd) serviceLevel;
            // masterGroupId = jobContractProd.getProductGroupMaster();
        }
        if (masterGroupId == null) {
            masterGroupId = "*";
        }
        // find the GL code
        String expressionId = expression.getExpressionId().getExpressionId();
        ExpressionGLCode eGLCode = jobSrvc.getExpressionGLCode(expressionId, jobType);
        if (eGLCode != null) {
            // 2009.8.18, choice of gl code depends on whether the customer is 
            // an intertek company or outsider
            if(customer != null && customer.getCustCode().equals("839") // 839 is intertek
                    || customer.getParentCustCode().equals("839")){
                accountInfo.setGlCode(eGLCode.getInterCompanyGLCode());
            }
            else{
                accountInfo.setGlCode(eGLCode.getGLCode());
            }
            String dept = getDepartment(serviceOffering, eGLCode.getGLCode());
            if (dept != null) {
                accountInfo.setDepartmentCode(dept);
            }
        }

        // find the service type
        String serviceType = expression.getExpressionId().getBranchType();
        if (Constants.ADM.equals(expression.getExpressionId().getBranchType())) {
            serviceType = expression.getExpenseBranchType();
        }
        accountInfo.setServiceType(serviceType);

        // find the product group
        if(productType != null){
            String productGroup = jobSrvc.getProductCode(eGLCode, productType, masterGroupId);
            accountInfo.setProductGroup(productGroup);
        }
        
        // find the branch code
        String bc = null;
        BranchCode branchCodeObj = jobSrvc.getBranchCodeByBranchCode(branchCode);
        if (branchCodeObj != null) {
            if (Constants.OPS.equals(expression.getExpenseBranchType())) {
                bc = branchCodeObj.getOpsCode();
            }
            else {
                bc = branchCodeObj.getLabCode();
            }
        }
        accountInfo.setBranchCode(bc);

        return accountInfo;
    }

    /**
     * @param serviceOffering
     * @param code
     * @return
     * @throws JobSrvcException 
     */
    private String getDepartment(ServiceOffering serviceOffering, String glcode) throws JobSrvcException {
        String departmentCode = null;
        
        if(serviceOffering != null){
            departmentCode = serviceOffering.getDepartmentCode();
            if(departmentCode == null && serviceOffering.getParentServiceOffering() != null){
                departmentCode = serviceOffering.getParentServiceOffering().getDepartmentCode();
            }
        }
        if(departmentCode == null){
            JobOrderService jobSrvc = ServiceManager.getJobOrderService();
            Department department = jobSrvc.getDepartmentByGlCode(glcode);
            if(department != null){
                departmentCode = department.getDepartmentCode();
            }
        }
        return departmentCode;
    }

    /**
     * Check if the pricing info is editable or not.
     * @return
     */
    private boolean getIsEditable(PricingInfo pricingInfo) {
        double totalPrice = pricingInfo.getTotalPrice();
        double discountPct = pricingInfo.getDiscountPct();
        if (totalPrice == 0) {
            if (discountPct == 0 || discountPct != 100) {
                return true;
            }
        }
        if (totalPrice == 0.0 && discountPct != 100) {
            return true;
        }
        if (pricingInfo.isContractPrice()) {
            String contractRef = pricingInfo.getContractRef();
            if ((contractRef == null) || !contractRef.startsWith("PB")) {
                return false;
            }
        }
        else if (discountPct >= 0) {
            return false;
        }

        return true;
    }

    @Override
    public ServiceRate getServicePrice(PricingParameter param) throws PricingSrvcException {
        return getServicePrice(param.getParameters());
    }
    
    @SuppressWarnings("unchecked")
    protected ServiceRate getServicePrice(Object[] params) throws PricingSrvcException {
        try {
            List<ServiceRate> result = DaoManager.getDao(ServiceRate.class).searchByNamedQuery("getServiceRate_FN", params, null);
            
            if (result.size() > 0) { // shouldn't it always be 1?
                ServiceRate serviceRate = result.get(0);
                return serviceRate;
            }
            return null;
        }
        catch (DaoException e) {
            throw new PricingSrvcException("Failed to load SlatePrice.", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public SlatePrice getSlatePrice(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, JobContractSlate slate) throws PricingSrvcException,
            JobSrvcException {
        Order jobOrder = contractJobOrder.getJobOrder();
        String contractCode = contractJobOrder.getContract().getContractCode();
        CfgContract contract = ServiceManager.getJobOrderService().getCfgContractByContractId(contractCode, jobOrder.getNominationDate());
        Date date = contractJobOrder.getJobOrder().getNominationDate();
        String priceBookId = getPriceBookId(contract, date);
        String dateStr = DateUtil.formatDate(date, "yyyyMMdd");
        Object[] params = new Object[] { contract.getCfgContractId(), priceBookId, 
                                         slate.getSlate().getSlateId(), contractJobOrder.getZone(),
                                         slate.getQuantity(), dateStr, };

        try {
            List<SlatePrice> result = DaoManager.getDao(SlatePrice.class).searchByNamedQuery("getSlatePrice_FN", params, null);
            if (result.size() > 0) {
                return result.get(0);
            }
            return null;
        }
        catch (DaoException e) {
            throw new PricingSrvcException("Failed to load SlatePrice.", e);
        }
    }

    /**
     * @param contract
     * @param date
     * @return
     * @throws JobSrvcException 
     */
    private String getPriceBookId(CfgContract contract, Date date) throws JobSrvcException {
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        String priceBookId = contract.getPriceBookId();
        if (priceBookId.equalsIgnoreCase(Constants.CURRENT)) {
            PriceBook pb = jobSrvc.getPriceBook(contract.getPbSeries(), contract.getCurrencyCD(), date);
            priceBookId = pb.getPriceBookId().getPriceBookId();
        }
        return priceBookId;
    }

    @SuppressWarnings("unchecked")
    @Override
    public TestPrice getTestPrice(ContractJobOrder contractJobOrder, ServiceLevel serviceLevel, 
                                     JobContractTest jobContractTest) throws PricingSrvcException, JobSrvcException {
        // when there is a quote amount, the pricing parameters needs to be
        // adjusted in a way similar to a manual test. // TODO check with rafiq
        if (jobContractTest.getJobTest() != null && jobContractTest.getJobTest().getQuotedAmount() != 0) {
            TestPrice testPrice = new TestPrice();
            double quotedAmount = jobContractTest.getJobTest().getQuotedAmount();
            double quantity = jobContractTest.getJobTest().getQuantity();
            // TODO check with Rafiq
            testPrice.setUnitPrice(quotedAmount / quantity); 
            return testPrice;
        }
        // a manual test does not have TestPrice, to make logic consistent,
        // just create a dummy TestPrice
        else if(jobContractTest.isManualTest()){
            TestPrice testPrice = new TestPrice();
            testPrice.setUnitPrice(jobContractTest.getUnitPrice());
            return testPrice;
        }
        
        Order jobOrder = contractJobOrder.getJobOrder();
        String contractCode = contractJobOrder.getContract().getContractCode();
        CfgContract contract = ServiceManager.getJobOrderService().getCfgContractByContractId(contractCode, jobOrder.getNominationDate());
        Date date = contractJobOrder.getJobOrder().getNominationDate();
        String priceBookId = getPriceBookId(contract, date);
        String dateStr = DateUtil.formatDate(jobOrder.getNominationDate(), "yyyyMMdd");

        Object[] params = new Object[] {
                          contract.getCfgContractId().getContractId(),
                          priceBookId,
                          jobContractTest.getTest().getTestId(),
                          contractJobOrder.getZone(),
                          jobContractTest.getQuantity(), // changed from new Integer(1);
                          dateStr,
        };
          
        try {
            List<TestPrice> result = DaoManager.getDao(TestPrice.class).searchByNamedQuery("getTestPrice_FN", params, null);
            if(result.size() > 0){
                return result.get(0);
            }
            
            return null;
        }
        catch (DaoException e) {
            throw new PricingSrvcException("Failed to load TestPrice.", e);
        }
    }

    private Pricer getPricer(String componentType) throws PricingSrvcException {
        PricingModel example = new PricingModel();
        example.setName(componentType);
        PricingModel pricingModel;
        try {
            pricingModel = DaoManager.getDao(PricingModel.class).searchUnique(example);
            if (pricingModel != null) {
                PricingModelType pmType = PricingModelType.valueOf(pricingModel.getModel());
                return PricerManager.getPricer(pmType);
            }
            else {
                return null;
            }
        }
        catch (DaoException e) {
            throw new PricingSrvcException("Failed to load PricingModel.", e);
        }
    }

    /**
     * Adjust the ServiceRate to apply multiplier and escalator, currency conversion
     * and round the prices to 4 digits.
     * @param contract
     * @param contractJobOrder
     * @param contractExpression
     * @param serviceRate
     * @return
     * @throws JobSrvcException
     * @throws PricingSrvcException
     */
    private ServiceRate adjustServiceRate(CfgContract contract, ContractJobOrder contractJobOrder, Expression expression, ServiceRate serviceRate,
                                          LocationDiscount ld, Date date, PricingInfo result) throws PricingSrvcException {
        // to begin with a clone
        ServiceRate adjusted = new ServiceRate(serviceRate);

        double multiplierEscalator = getMultiplierAndEscalator(contract, contractJobOrder, expression, serviceRate, ld, date);
        double currencyMultiplier = getCurrencyMultiplier(contract, contractJobOrder, result);

        if (adjusted.getBasePrice() != null) {
            if (multiplierEscalator != 1) {
                adjusted.setBasePrice(adjusted.getBasePrice() * multiplierEscalator);
            }
            if (currencyMultiplier != 1) {
                adjusted.setBasePrice(adjusted.getBasePrice() * currencyMultiplier);
            }
            adjusted.setBasePrice(NumberUtil.roundHalfUp(adjusted.getBasePrice(), Constants.DEFAULT_ROUNDING));
        }
        if (adjusted.getUnitPrice() != null) {
            if (multiplierEscalator != 1) {
                adjusted.setUnitPrice(adjusted.getUnitPrice() * multiplierEscalator);
            }
            if (currencyMultiplier != 1) {
                adjusted.setUnitPrice(adjusted.getUnitPrice() * currencyMultiplier);
            }
            adjusted.setUnitPrice(NumberUtil.roundHalfUp(adjusted.getUnitPrice(), Constants.DEFAULT_ROUNDING));
        }
        if (adjusted.getMinimumPrice() != null) {
            if (multiplierEscalator != 1) {
                adjusted.setMinimumPrice(adjusted.getMinimumPrice() * multiplierEscalator);
            }
            if (currencyMultiplier != 1) {
                adjusted.setMinimumPrice(adjusted.getMinimumPrice() * currencyMultiplier);
            }
            adjusted.setMinimumPrice(NumberUtil.roundHalfUp(adjusted.getMinimumPrice(), Constants.DEFAULT_ROUNDING));
        }
        if (adjusted.getMaximumPrice() != null) {
            if (multiplierEscalator != 1) {
                adjusted.setMaximumPrice(adjusted.getMaximumPrice() * multiplierEscalator);
            }
            if (currencyMultiplier != 1) {
                adjusted.setMaximumPrice(adjusted.getMaximumPrice() * currencyMultiplier);
            }
            adjusted.setMaximumPrice(NumberUtil.roundHalfUp(adjusted.getMaximumPrice(), Constants.DEFAULT_ROUNDING));
        }

        return adjusted;
    }

    protected double getMultiplierAndEscalator(CfgContract contract, ContractJobOrder contractJobOrder, 
                                               Expression expression, ServiceRate serviceRate, LocationDiscount ld, 
                                               Date nominationDate) throws PricingSrvcException {
        double result = 1;

        if (expression == null) {
            return result;
        }
        
        if(serviceRate.getServiceRateId() == null){
            // this happens for manual tests or type 3 Job Orders
            return result;
        }

        String branchType = expression.getExpressionId().getBranchType();

        // expenses --> no multiplier and escalator
        // TODO is this correct?
        if (Constants.ADM.equals(branchType)) {
            return result;
        }

        // figure out all the required parameters
        String contractId = contractJobOrder.getContract().getContractCode(); // contract id in the order
        String rateContractId = serviceRate.getServiceRateId().getContractId();
        String rateLocation = serviceRate.getServiceRateId().getLocation();

        if (!contractId.equals(rateContractId) || Constants.STAR.equals(rateLocation)) {
            // not zone specific price
            if (ld != null) {
                if (Constants.LAB.equals(branchType)) {
                    Double labFactor = ld.getLabFactor();
                    if ((labFactor != null) && (labFactor.doubleValue() > 0)) {
                        result = result * labFactor.doubleValue();
                    }
                }
                else if (Constants.OPS.equals(branchType)) {
                    Double opsFactor = ld.getOpsFactor();
                    if ((opsFactor != null) && (opsFactor.doubleValue() > 0)) {
                        result = result * opsFactor.doubleValue();
                    }
                }
            }
        }

        if (contractId.equals(rateContractId)) {
            Double escalator = contract.getAnnualEscalator();
            if ((escalator != null) && (escalator.doubleValue() > 0)) {
                Date escalatorDate = contract.getEscalatorDate();
                if ((nominationDate != null) && (escalatorDate != null)) {
                    int yearsBetween = DateUtil.getYears(escalatorDate, nominationDate);

                    if (yearsBetween > 0) {
                        result = result * Math.pow(1 + escalator.doubleValue() * 0.01, yearsBetween);
                    }
                }
            }
        }

        return result;
    }

    protected double getCurrencyMultiplier(CfgContract contract, ContractJobOrder contractJobOrder, PricingInfo result) throws PricingSrvcException {
        if (contract == null) {
            return 1.0;
        }

        boolean overridable = CommonUtil.booleanValue(contract.getOverridable());
        double overrideCurrRate = CommonUtil.doubleValue(contractJobOrder.getOverrideCurrencyRate());

        if (overridable && overrideCurrRate > 0) {
            return overrideCurrRate;
        }

        double[] currencyRate = getCurrencyRate(contract, contractJobOrder);

        double rateMult = currencyRate[0];
        double rateDiv = currencyRate[1];
        // rateMult and rateDiv are needed in JOLIs
        result.setRateMult(rateMult);
        result.setRateDiv(rateDiv);

        if (rateMult != 0 && rateDiv != 0) {
            return rateMult / rateDiv;
        }
        return 1.0;
    }

    protected double[] getCurrencyRate(CfgContract contract, ContractJobOrder contractJobOrder) throws PricingSrvcException {
        double[] result = new double[] { 1, 1 }; // [RateMult, RateDiv]

        String fromCurrency = contractJobOrder.getContractCurrencyCode();
        String toCurrency = contractJobOrder.getTransactionCurrencyCode();
        Date date = contractJobOrder.getJobOrder().getNominationDate();

        if ((fromCurrency == null) || (toCurrency == null) || (date == null)) {
            return result;
        }

        if (fromCurrency.equals(toCurrency)) {
            return result;
        }

        CurrencyRate rate = getCurrencyRate(fromCurrency, toCurrency, date);

        if (rate == null) {
            return result;
        }

        double rateMult = CommonUtil.doubleValue(rate.getRateMult());
        double rateDiv = CommonUtil.doubleValue(rate.getRateDiv());
        if (rateMult == 0 || rateDiv == 0) {
            return result;
        }

        result[0] = rateMult;
        result[1] = rateDiv;
        return result;
    }

    /**
     * 
     * @param contract
     * @param jobService
     * @param contractExpression
     * @return
     * @throws JobSrvcException
     */
    protected List<JobContractServiceControl> getRelatedControls(CfgContract contract, JobContractService jobService, 
                                                                 ContractExpression contractExpression) throws JobSrvcException {
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        List<ControlMap> controlMaps = jobSrvc.getControlMapsByExpression(contract, jobService.getServiceName(), 
                                                                          contractExpression.getContractExpressionId().getExpressionId());
        Collection<JobContractServiceControl> selectedControls = jobService.getControls();
        List<JobContractServiceControl> related = new ArrayList<JobContractServiceControl>();

        // find the overlap of two collections
        for (JobContractServiceControl jsc : selectedControls) {
            for (ControlMap cm : controlMaps) {
                if (jsc.getObjectName().equals(cm.getControlMapId().getObjectName())) {
                    related.add(jsc);
                }
            }
        }
        return related;
    }

    private CurrencyRate getCurrencyRate(String fromCurrency, String toCurrency, Date date) throws PricingSrvcException {
        try {
            SearchService search = ServiceManager.getSearchService();
            List<CurrencyRate> rates = search.searchCurrencyRate(fromCurrency, toCurrency, date);
            if (rates.size() > 0) {
                return rates.get(0);
            }
            return null;
        }
        catch (PhoenixException e) {
            throw new PricingSrvcException("Failed to find CurrencyMultiplier.", e);
        }
    }

    private LocationDiscount getLocationDiscount(String contractId, String location, Date date) throws PricingSrvcException {
        Dao<LocationDiscount> dao = DaoManager.getDao(LocationDiscount.class);

        QueryInfo query = new QueryInfo(LocationDiscount.class);
        query.addFilter("locationDiscountId.contractId", contractId)
             .addFilter("locationDiscountId.location", location)
             .addFilter("locationDiscountId.beginDate", "endDate", date, FilterOp.BETWEEN);

        LocationDiscount ld;
        try {
            ld = dao.searchUnique(query);
        }
        catch (DaoException ignore) {
            query.setFilter("locationDiscountId.location", "*");
            try {
                ld = dao.searchUnique(query);
            }
            catch (DaoException e) {
                throw new PricingSrvcException("Failed to load LocationDiscount.", e);
            }
        }

        return ld;
    }

}
