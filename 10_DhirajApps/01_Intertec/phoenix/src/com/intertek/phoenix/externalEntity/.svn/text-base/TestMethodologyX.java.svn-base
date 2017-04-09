/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intertek.entity.Test;
import com.intertek.entity.TestPrice;
import com.intertek.entity.TestPriceId;
import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.phoenix.esb.Logable;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class TestMethodologyX implements Logable {
    private String id;
    private String priceId;
    private String description;
    private double price;
    private Date beginDate;
    private Date endDate;
    private List<ServiceOfferingX> serviceOffering;

    // Test methodogy -- filter by list of price book IDs -- cfg_contract_id
    public TestMethodologyX() {

    }

    public TestMethodologyX(TestPrice tp, List<ServiceOffering> serviceOfferings) {
        Test test = tp.getTest();
        TestPriceId tpId = tp.getTestPriceId();
        this.priceId=tpId.getContractId();
        
        this.id = test.getTestId();
        this.beginDate = tpId.getBeginDate();
        this.endDate = tp.getEndDate();

        this.description = test.getTestDescription();
        this.price = tp.getUnitPrice();
        setServiceOfferings(serviceOfferings);
    }

    protected void setServiceOfferings(List<ServiceOffering> sos) {
        if (sos == null) {
            return;
        }
        this.serviceOffering = new ArrayList<ServiceOfferingX>();
        for (ServiceOffering so : sos) {
            this.serviceOffering.add(new ServiceOfferingX(so));
        }
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ServiceOfferingX> getServiceOffering() {
        return serviceOffering;
    }

    public void setServiceOffering(List<ServiceOfferingX> serviceOffering) {
        this.serviceOffering = serviceOffering;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
