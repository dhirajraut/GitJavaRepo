package com.intertek.phoenix.esb.dataCollector;

import com.intertek.entity.Branch;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.externalEntity.WarehouseX;
import com.intertek.util.Constants;

/**
 * 
 * @author Eric.Nguyen
 */
public class WarehouseDataCollector extends ConvertibleDataCollector<Branch, WarehouseX> {
   
    @Override
    public WarehouseX convert(Branch obj) {
        return new WarehouseX(obj);
    }

    @Override
    public Class<Branch> getEntityType() {
        return Branch.class;
    }

    @Override
    public int updateEntityFlag(Branch obj, boolean sent) throws DaoException {
        String flag = sent ? Constants.SENT : Constants.FAIL;
        String query = "update Branch set updateFlag=?, newFlag=? where name=?";
        Object[] params = new Object[] { flag, flag, obj.getName()};
        return getEsbService().executeQuery(obj.getClass(), query, params);
    }

}
