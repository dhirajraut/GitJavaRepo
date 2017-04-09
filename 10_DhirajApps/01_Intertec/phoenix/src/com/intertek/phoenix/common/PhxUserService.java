package com.intertek.phoenix.common;

import com.intertek.entity.User;
import com.intertek.phoenix.dao.DaoException;

public interface PhxUserService {
	User getUserByName(String name) throws DaoException;
}
