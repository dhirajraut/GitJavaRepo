package com.intertek.service;

import java.util.List;

import com.intertek.domain.PermissionListSearch;
import com.intertek.domain.RoleSearch;
import com.intertek.domain.TimeZoneSearch;
import com.intertek.entity.JobCode;
import com.intertek.entity.Link;
import com.intertek.entity.Permission;
import com.intertek.entity.Role;
import com.intertek.entity.TimeZone;
import com.intertek.entity.User;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.search.Search;

public interface AdminService {
    Role getRoleByName(String roleName);

    Role getRoleByNameWithPerms(String roleName);

    List getRoleAndPermissionsByName(String name);

    List getRolenamesByName(String name);

    Role addRole(Role role);

    void saveRoleOnly(Role role);

    void saveRole(Role role);

    void addRoleToUser(String roleName, String userName);

    void searchRole(RoleSearch search, String sortFlag);

    Permission addPermission(Permission permission);

    Permission savePermissionOnly(Permission perm);

    Permission savePermission(Permission permission);

    void addPermToRole(String permName, String roleName);

    void addPermToLink(String permName, String linkName);

    Permission getPermissionByName(String permName);

    TimeZone getTimeZoneByName(String name);

    List getPermissionsByName(String permName);

    void searchPermissionList(PermissionListSearch search, String sortFlag);

    void searchPermissionListControl(PermissionListSearch search, String sortFlag);

    boolean isAuthorized(String action, User user);

    boolean isAuthorized(List actions, User user);

    JobCode getJobCodeByJobCode(String jobCode);

    JobCode addJobCode(JobCode jobCode);

    Link getLinkByName(String name);

    List getLinkByPermissionName(String permName);

    List getLinksByName(String linkName);

    Link saveLinkOnly(Link link);

    Link saveLink(Link link);

    Link getLinkByControl(String name);

    void searchTimeZone(TimeZoneSearch search, String sortFlag);

    List getTimeZonesByName(String name);

    List getAllPaymentTerms();

    List getEntitiesByHql(String hql, Pagination pagination);

    Role getAddRoleByName(String roleName);

    Permission getAddPermissionByName(String permName);
    
    /**
     * A generic search method for admin objects, will replace
     * other search methods, such as searchRole() and searchPermissionList()
     */
    void searchAdminObject(Search search);
}
