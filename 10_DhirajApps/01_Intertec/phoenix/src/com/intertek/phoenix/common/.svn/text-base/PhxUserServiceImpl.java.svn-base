package com.intertek.phoenix.common;

import com.intertek.entity.User;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.QueryInfo;

public class PhxUserServiceImpl implements PhxUserService {

    public User getUserByName(String loginName) throws DaoException {
        QueryInfo query = new QueryInfo(User.class);
        query.addFilter("loginName", loginName);
        return DaoManager.getDao(User.class).searchUnique(query);
    }
}
