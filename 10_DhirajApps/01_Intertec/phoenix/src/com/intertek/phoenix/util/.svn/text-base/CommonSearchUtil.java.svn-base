/**
 * 
 */
package com.intertek.phoenix.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.intertek.entity.Link;
import com.intertek.entity.Permission;
import com.intertek.entity.Role;
import com.intertek.phoenix.search.SearchCriteria;
import com.intertek.phoenix.search.SearchableCriteria;

/**
 * Contains various static util functions for search screens.
 * 
 * @author richard.qin
 */
public class CommonSearchUtil {
    private CommonSearchUtil() {
    }

    public static String[] userRoleNames(Collection<Role> roles) {
        List<String> names = new ArrayList<String>();

        for (Role role : roles) {
            names.add(role.getName());
        }
        return names.toArray(new String[0]);
    }

    public static String[] rolePermissionNames(Collection<Permission> permissions) {
        List<String> names = new ArrayList<String>();

        for (Permission perm : permissions) {
            names.add(perm.getName());
        }
        return names.toArray(new String[0]);
    }

    public static String[] permissionLinkNames(Collection<Link> links) {
        List<String> names = new ArrayList<String>();

        for (Link link : links) {
            names.add(link.getName());
        }
        return names.toArray(new String[0]);
    }

    public static List<SearchCriteria> buildCriteria(Object searchObj, List<SearchableCriteria> scs) throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        List<SearchCriteria> criteria = new ArrayList<SearchCriteria>();
        for (SearchableCriteria sc : scs) {
            String searchValue = sc.getSearchValue();
            if(sc.isSearchValueIsAttribute()){
                searchValue = BeanUtils.getProperty(searchObj, searchValue);
            }
            if (searchValue != null && !searchValue.isEmpty()) {
                criteria.add(new SearchCriteria(sc.getEntityAttribute(), searchValue, sc.getOperator()));
            }
        }
        return criteria;
    }
}
