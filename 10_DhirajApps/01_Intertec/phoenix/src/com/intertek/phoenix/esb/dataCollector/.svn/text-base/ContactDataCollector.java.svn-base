package com.intertek.phoenix.esb.dataCollector;

import com.intertek.entity.Contact;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.externalEntity.ContactX;
import com.intertek.util.Constants;
/**
 * 
 * @author Eric.Nguyen
 */
public class ContactDataCollector extends ConvertibleDataCollector<Contact, ContactX> {

    @Override
    public ContactX convert(Contact obj) {
        return new ContactX(obj);
    }

    @Override
    public Class<Contact> getEntityType() {
        return Contact.class;
    }

    @Override
    public int updateEntityFlag(Contact obj, boolean sent) throws DaoException {
        String flag = sent ? Constants.SENT : Constants.FAIL;
        String query = "update Contact set updateFlag=?, newFlag=? where id=?";
        Object[] params = new Object[] { flag, flag, obj.getId()};
        return getEsbService().executeQuery(obj.getClass(), query, params);
    }
}
