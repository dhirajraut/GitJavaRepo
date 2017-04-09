package com.intertek.phoenix.esb.dataCollector;

import java.util.List;

import com.intertek.entity.Test;
import com.intertek.entity.TestPrice;
import com.intertek.entity.TestPriceId;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.phoenix.externalEntity.TestMethodologyX;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.util.Constants;

/**
 * 
 * @author Eric.Nguyen
 */
public class TestMethodologyDataCollector extends ConvertibleDataCollector<TestPrice, TestMethodologyX> {
    private JobOrderService jobOrderService;

    @Override
    public TestMethodologyX convert(TestPrice obj) {
        Test test = obj.getTest();
        List<ServiceOffering> serviceOfferings = null;
        try {
            serviceOfferings = jobOrderService.getServiceOffering(test.getTestId());
        }
        catch (JobSrvcException e) {
            e.printStackTrace();
        }
        
        TestMethodologyX tm = new TestMethodologyX(obj, serviceOfferings);
        return tm;
    }

    @Override
    public Class<TestPrice> getEntityType() {
        return TestPrice.class;
    }

    public JobOrderService getJobOrderService() {
        return jobOrderService;
    }

    public void setJobOrderService(JobOrderService jobOrderService) {
        this.jobOrderService = jobOrderService;
    }

    @Override
    public int updateEntityFlag(TestPrice obj, boolean sent) throws DaoException {
        String flag = sent ? Constants.SENT : Constants.FAIL;
        String query = "update TestPrice set updateFlag=?, newFlag=? where " +
        		"testPriceId.contractId=? " +
        		"and testPriceId.testType=? " +
        		"and testPriceId.testId=? " +
        		"and testPriceId.location=? " +
        		"and testPriceId.beginDate=? " +
        		"and testPriceId.minQty=? " +
        		"and testPriceId.maxQty=? ";
        TestPriceId tpId=obj.getTestPriceId();
        Object[] params = new Object[] { flag, flag, 
                                         tpId.getContractId(), 
                                         tpId.getTestType(), 
                                         tpId.getTestId(), 
                                         tpId.getLocation(), 
                                         tpId.getBeginDate(), 
                                         tpId.getMinQty(), 
                                         tpId.getMaxQty()};
        return getEsbService().executeQuery(obj.getClass(), query, params);
    }

}
