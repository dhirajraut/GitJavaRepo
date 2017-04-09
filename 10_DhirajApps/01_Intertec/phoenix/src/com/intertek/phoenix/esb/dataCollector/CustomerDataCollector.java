package com.intertek.phoenix.esb.dataCollector;

import com.intertek.entity.Customer;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.externalEntity.CustomerX;
import com.intertek.util.Constants;

/**
 * 
 * @author Eric.Nguyen
 */
public class CustomerDataCollector extends ConvertibleDataCollector<Customer, CustomerX> {

    @Override
    public CustomerX convert(Customer obj) {
        return new CustomerX(obj);
    }

    @Override
    public Class<Customer> getEntityType() {
        return Customer.class;
    }

    @Override
    public int updateEntityFlag(Customer obj, boolean sent) throws DaoException {
        String flag = sent ? Constants.SENT : Constants.FAIL;
        String query = "update Customer set updateFlag=?, newFlag=? where custCode=?";
        Object[] params = new Object[] {flag, flag, obj.getCustCode()};
        return getEsbService().executeQuery(obj.getClass(), query, params);
    }
}
