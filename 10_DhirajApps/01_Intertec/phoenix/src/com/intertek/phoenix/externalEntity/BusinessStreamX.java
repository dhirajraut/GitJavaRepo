/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.Date;

import com.intertek.entity.ProductGroup;
import com.intertek.entity.ProductGroupId;
import com.intertek.phoenix.esb.Logable;

/**
 * Product Type
 * 
 * @author Eric.Nguyen
 */
public class BusinessStreamX implements Logable {
    private String productGroupSet; //Catalog Store in ECS
    private String productGroupId;
    private String description;
    private Date beginDate;
    private Date endDate;

    //TODO: filter by cfg_contract_id with productGroupSet - per Rafiq  
    public BusinessStreamX() {

    }

    public ProductGroup convert() {
        ProductGroup pg = new ProductGroup();
        pg.setProductGroupId(new ProductGroupId(this.getProductGroupSet(), this.getProductGroupId(), this.getBeginDate()));
        pg.setEndDate(this.getEndDate());
        return pg;
    }

    public BusinessStreamX(ProductGroup obj) {
        //TODO: look at ITS_PRD_GRP_MASTER might be part of the key 
        ProductGroupId id = obj.getProductGroupId();
        this.productGroupId = id.getGroupId();
        this.productGroupSet=id.getProductGroupSet();
        this.beginDate = id.getBeginDate();

        this.description = obj.getRbValue();
        this.endDate = obj.getEndDate();
    }

    @Override
    public String getId() {
        return getProductGroupId();
    }

    public String getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(String productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProductGroupSet() {
        return productGroupSet;
    }

    public void setProductGroupSet(String productGroupSet) {
        this.productGroupSet = productGroupSet;
    }
}
