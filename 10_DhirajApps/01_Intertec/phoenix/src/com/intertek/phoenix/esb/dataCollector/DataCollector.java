package com.intertek.phoenix.esb.dataCollector;

import java.util.List;

import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.entity.Collectable;
/**
 * 
 * @author Eric.Nguyen
 */
public interface DataCollector<T1 extends Collectable>{
    public Class<T1> getEntityType();
    public List<T1> getObjects(int numInPage) throws PhoenixException; //return a list of enitity to be sent
    public int updateEntityFlag(T1 obj, boolean sent) throws DaoException ; //update the given entity and its flag 
}
