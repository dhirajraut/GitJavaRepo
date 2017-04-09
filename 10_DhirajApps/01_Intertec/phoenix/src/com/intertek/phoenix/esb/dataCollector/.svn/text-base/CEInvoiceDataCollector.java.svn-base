package com.intertek.phoenix.esb.dataCollector;

import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.externalEntity.CEInvoiceX;
import com.intertek.util.Constants;

/**
 * 
 * @author Eric.Nguyen
 */
public class CEInvoiceDataCollector extends ConvertibleDataCollector<CEInvoice, CEInvoiceX> {

    @Override
    public CEInvoiceX convert(CEInvoice obj) {
        return new CEInvoiceX(obj);
    }

    @Override
    public Class<CEInvoice> getEntityType() {
        return CEInvoice.class;
    }

    @Override
    public int updateEntityFlag(CEInvoice obj, boolean sent) throws DaoException {
        String flag = sent ? Constants.SENT : Constants.FAIL;
        String query = "update CEInvoice set updateFlag=?, newFlag=? where id=? ";
        Object[] params = new Object[] { flag, flag, obj.getId()};
        return getEsbService().executeQuery(obj.getClass(), query, params);
    }
}
