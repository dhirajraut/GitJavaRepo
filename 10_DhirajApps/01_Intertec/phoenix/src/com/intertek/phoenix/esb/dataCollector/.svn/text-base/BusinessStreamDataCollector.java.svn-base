package com.intertek.phoenix.esb.dataCollector;

import com.intertek.entity.ProductGroup;
import com.intertek.entity.ProductGroupId;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.externalEntity.BusinessStreamX;
import com.intertek.util.Constants;
/**
 * 
 * @author Eric.Nguyen
 */
public class BusinessStreamDataCollector extends ConvertibleDataCollector<ProductGroup, BusinessStreamX> {

    @Override
    public BusinessStreamX convert(ProductGroup obj) {
        return new BusinessStreamX(obj);
    }

    @Override
    public Class<ProductGroup> getEntityType() {
        return ProductGroup.class;
    }

    @Override
    public int updateEntityFlag(ProductGroup obj, boolean sent) throws DaoException {
        String flag = sent ? Constants.SENT : Constants.FAIL;
        String query = "update ProductGroup set updateFlag=?, newFlag=? " +
        		"where " +
        		"productGroupId.productGroupSet=? and " +
        		"productGroupId.groupId=? and " +
        		"productGroupId.beginDate=?";
        ProductGroupId id=obj.getProductGroupId();
        Object[] params = new Object[] { flag, flag, id.getProductGroupSet(), id.getGroupId(), id.getBeginDate()};
        return getEsbService().executeQuery(obj.getClass(), query, params);
    }
   
}
