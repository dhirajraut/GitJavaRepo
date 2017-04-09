package com.intertek.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.dao.Dao;
import com.intertek.domain.PermissionListSearch;
import com.intertek.domain.RoleSearch;
import com.intertek.domain.Search;
import com.intertek.domain.TimeZoneSearch;
import com.intertek.entity.JobCode;
import com.intertek.entity.Link;
import com.intertek.entity.Permission;
import com.intertek.entity.Role;
import com.intertek.entity.TimeZone;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.exception.ServiceException;

public class AdminServiceImpl implements AdminService {
    private Dao dao;
    private static Log log = LogFactory.getLog(AdminServiceImpl.class);

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public Dao getDao() {
        return dao;
    }

    public Role getRoleByName(String name) {
        Role role = null;
        List roles = new ArrayList();

        roles = getDao().find("from Role r left join fetch r.permissions where upper(r.name) = '" + name.toUpperCase() + "'", null);
        if (roles != null && roles.size() > 0) {
            role = (Role) roles.get(0);
        }
        else {
            throw new ServiceException("roles.list.error");
        }
        return role;
    }

    public Role getAddRoleByName(String name) {
        Role role = null;
        List roles = new ArrayList();

        roles = getDao().find("from Role r left join fetch r.permissions where upper(r.name) = '" + name.toUpperCase() + "'", null);
        if (roles.size() > 0)
            return (Role) roles.get(0);
        return null;
    }

    public Role getRoleByNameWithPerms(String roleName) {
        List roles = getDao().find("from Role r left join fetch r.perms where r.name = ?", new Object[] { roleName });

        if (roles.size() > 0)
            return (Role) roles.get(0);

        return null;
    }

    public Permission getPermissionByName(String permName) {
        Permission permission = null;
        List perms = getDao().find("from Permission p where p.name = ? ", new Object[] { permName });
        if (perms.size() > 0)
            return (Permission) perms.get(0);
        return null;
    }

    public Permission getAddPermissionByName(String permName) {
        Permission permission = null;
        List perms = getDao().find("from Permission p where p.name = ? ", new Object[] { permName });
        if (perms != null && perms.size() > 0) {
            permission = (Permission) perms.get(0);
        }
        else {
            throw new ServiceException("permission.not.exist.error", new Object[] { permName }, null);
        }
        return permission;
    }

    public void saveRoleOnly(Role role) {
        if (role == null)
            return;

        Role existedRole = getRoleByName(role.getName());
        if (existedRole == null) {
            getDao().save(role);
        }
        else {
            existedRole.setRoleDesc(role.getRoleDesc());
        }
    }

    public void saveRole(Role role) {
        getDao().save(role);
    }

    public Role addRole(Role role) {

        if (role == null)
            return null;
        Role existingRole = null;
        if (role != null)
            // existingRole = getRoleByName(role.getName());
            existingRole = getAddRoleByName(role.getName());
        if (existingRole != null) {
            Set existingPermissionSet = existingRole.getPermissions();
            if (existingPermissionSet != null && existingPermissionSet.size() > 0) {
                Iterator iter = role.getPermissions().iterator();

                while (iter.hasNext()) {
                    Permission perm = (Permission) iter.next();
                    existingPermissionSet.add(perm);
                }
            }
            role.setPermissions(existingPermissionSet);
        }
        return getDao().save(role);

    }

    public Permission addPermission(Permission perm) {
        if (perm == null)
            return null;

        Permission existedPermission = getPermissionByName(perm.getName());
        if (existedPermission != null) {
            // throw new RuntimeException("Permission exists: " +
            // perm.getName());
            {
                throw new ServiceException("permission.exists.error", new Object[] { perm.getName() }, null);
            }
        }

        return getDao().save(perm);
    }

    public Permission savePermissionOnly(Permission perm) {
        if (perm == null)
            return null;

        Permission existedPerm = getPermissionByName(perm.getName());
        if (existedPerm == null) {
            return getDao().save(perm);
        }

        existedPerm.setDescription(perm.getDescription());
        return existedPerm;
    }

    public Permission savePermission(Permission perm) {
        if (perm == null)
            return null;
        return getDao().save(perm);
    }

    public void addPermToRole(String permName, String roleName) {
        Permission perm = getPermissionByName(permName);
        if (perm == null) {
            // throw new RuntimeException("Permission not exists: " + permName);
            {
                throw new ServiceException("permission.not.exist.error", new Object[] { permName }, null);
            }
        }

        Role role = getRoleByName(roleName);
        if (role == null) {
            throw new RuntimeException("Role not exists: " + roleName);
        }

        role.getPermissions().add(perm);
    }

    public void addRoleToUser(String roleName, String userName) {
        Role role = getRoleByName(roleName);
        if (role == null) {
            throw new RuntimeException("Role not exists: " + roleName);
        }

        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
        User user = userService.getUserByName(userName);
        if (user == null) {
            throw new RuntimeException("User not exists: " + userName);
        }

        user.getRoles().add(role);
    }

    public void addPermToLink(String permName, String linkName) {
        Permission perm = getPermissionByName(permName);
        if (perm == null) {
            // throw new RuntimeException("Permission not exists: " + permName);
            {
                throw new ServiceException("permission.not.exist.error", new Object[] { permName }, null);
            }
        }

        MenuService menuService = (MenuService) ServiceLocator.getInstance().getBean("menuService");
        Link link = menuService.getLinkByName(linkName);
        if (link == null) {
            throw new RuntimeException("Link not exists: " + linkName);
        }

        link.getPermissions().add(perm);
    }

    public boolean isAuthorized(String action, User user) {
        Set roles = user.getRoles();
        Iterator it = roles.iterator();
        while (it.hasNext()) {
            Role role = (Role) it.next();
            Iterator it0 = role.getPermissions().iterator();
            while (it0.hasNext()) {
                Permission perm = (Permission) it0.next();
                if (perm.getName().equals(action))
                    return true;
            }
        }

        return false;
    }

    public boolean isAuthorized(List actions, User user) {
        if ((actions == null) || (actions.size() == 0))
            return false;

        Set roles = user.getRoles();
        Iterator it = roles.iterator();
        while (it.hasNext()) {
            Role role = (Role) it.next();
            Iterator it0 = role.getPermissions().iterator();
            while (it0.hasNext()) {
                Permission perm = (Permission) it0.next();
                if (actions.contains(perm.getName()))
                    return true;
            }
        }

        return false;
    }

    public JobCode addJobCode(JobCode jobCode) {
        if (jobCode == null)
            return null;

        JobCode existedJobCode = getJobCodeByJobCode(jobCode.getJobCode());
        if (existedJobCode != null) {
            throw new RuntimeException("JobCode  with the same job code exists: " + jobCode.getJobCode());
        }

        return getDao().save(jobCode);
    }

    public JobCode getJobCodeByJobCode(String jobCode) {
        List jobCodes = getDao().find("from JobCode jobCode where jobCode.jobCode = ?", new Object[] { jobCode });

        if (jobCodes.size() > 0)
            return (JobCode) jobCodes.get(0);

        return null;
    }

    public Link saveLinkOnly(Link link) {
        if (link == null)
            return null;

        Link existedLink = getLinkByName(link.getName());
        if (existedLink == null) {
            return getDao().save(link);
        }

        existedLink.setUrl(link.getUrl());
        existedLink.setSeqNumber(link.getSeqNumber());
        existedLink.setDescription(link.getDescription());

        return existedLink;
    }

    public Link saveLink(Link link) {
        /* if(link == null) return null; */

        return getDao().save(link);
    }

    public Link getLinkByName(String name) {
        Link link = null;
        List links = getDao().find("from Link l left join fetch l.permissions p where l.name=?", new Object[] { name });
        /*
         * if(links.size() > 0) return (Link)links.get(0); return null;
         */

        if (links != null && links.size() > 0) {
            link = (Link) links.get(0);
        }
        else {
            throw new ServiceException("control.not.exist.error", new Object[] { name }, null);
        }
        return link;
    }

    public List getLinkByPermissionName(String permName) {
        if (permName == null)
            return new ArrayList();
        List links = new ArrayList();
        links = getDao().find("from Link l left join fetch l.permissions p where upper(p.name) =  '" + permName.toUpperCase() + "'", null);
        if (links != null && links.size() > 0) {
            return links;
        }
        else {
            return null;
        }
    }

    public List getPermissionsByName(String permName) {
        if (permName == null)
            return new ArrayList();
        List permNames = new ArrayList();
        try {
            permNames = getDao().find("from Permission p where upper(p.name) like '" + permName.toUpperCase() + "%'", null);
        }
        catch (Exception e) {
            log.info("Exception in getPermissionsByName" + e.toString());
        }
        return permNames;
    }

    public List getLinksByName(String linkName) {
        if (linkName == null)
            return new ArrayList();
        List linkNames = new ArrayList();
        try {
            linkNames = getDao().find("from Link l where upper(l.name) like'" + linkName.toUpperCase() + "%'", null);
        }
        catch (Exception e) {
            log.info("Exception in getLinksByName " + e.toString());
        }
        return linkNames;
    }

    public List getRolenamesByName(String name) {
        if (name == null)
            return new ArrayList();
        List names = new ArrayList();
        try {
            names = getDao().find("from Role r where upper(r.name) like'" + name.toUpperCase() + "%'", null);
        }
        catch (Exception e) {
            log.info("Exception i n getRolenamesByName :" + e.toString());
        }
        return names;
    }

    public List getRoleAndPermissionsByName(String name) {
        if (name == null)
            return new ArrayList();
        List names = new ArrayList();

        try {
            names = getDao().find("from Role r left join fetch r.permissions where upper(r.name) like'" + name.toUpperCase() + "%'", null);
        }
        catch (Exception e) {
            log.info("Exception i n getRolenamesByName :" + e.toString());
        }
        return names;
    }

    // TODO: to be removed
    public void searchRole(RoleSearch search, String sortFlag){
    }

    // TODO: to be removed
    public void searchPermissionList(PermissionListSearch search, String sortFlag) {
        if (search == null)
            return;

        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();

        boolean hasWhere = false;
        String strpermissionListSearch = "";
        String permissionName = search.getRolePerms().getValue();
        if ((permissionName != null) && !"".equals(permissionName.trim())) {
            strpermissionListSearch = '%' + permissionName.toUpperCase() + '%';
            sb.append(" where upper(p.name) like ?");
            params.add(strpermissionListSearch);
            hasWhere = true;
        }
        String strpermissionDescsearch = "";
        String description = search.getDescription().getValue();

        if ((description != null) && !"".equals(description.trim())) {
            strpermissionDescsearch = '%' + description.toUpperCase() + '%';

            if (hasWhere)
                sb.append(" and ");
            else {
                hasWhere = true;
                sb.append(" where ");
            }
            sb.append("upper(p.description) like ?");
            params.add(strpermissionDescsearch);
        }

        Pagination pagination = search.getPagination();
        List results = null;
        if (sortFlag != null && sortFlag.equals("")) {
            if (pagination != null) {
                if (pagination.getTotalRecord() > 0) {
                    List counts = getDao().find("select count(p.name) from Permission p " + sb.toString(), params.toArray());

                    if (counts.size() > 0) {
                        Number count = (Number) counts.get(0);
                        pagination.setTotalRecord(count.intValue());
                    }
                }
                results = getDao().find("select distinct p from Permission p " + sb.toString() + " order by p.name",

                params.toArray(), pagination);

                pagination.calculatePages();
            }
            else {
                results = getDao().find("select distinct p from Permission p" + sb.toString(), params.toArray());
            }
            search.setResults(results);
            search.setPagination(pagination);
        }
        else {
            String orderByValue = " order by p." + sortFlag;
            if (pagination != null) {
                if (pagination.getTotalRecord() > 0) {
                    List counts = getDao().find("select count(p.name) from Permission p " + sb.toString(), params.toArray());

                    if (counts.size() > 0) {
                        Number count = (Number) counts.get(0);
                        pagination.setTotalRecord(count.intValue());
                    }
                }
                pagination.calculatePages();
            }
            // START : #119240 : 22/06/09
            /*results = getDao().find("select distinct p from Permission p " + sb.toString() + orderByValue,
            params.toArray(), pagination);*/
            if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
            	results = getDao().find("select distinct p from Permission p " + sb.toString() + orderByValue + " " + search.getSortOrderFlag(),
                        params.toArray(), pagination);
            }else{
            	results = getDao().find("select distinct p from Permission p " + sb.toString() + orderByValue,
                        params.toArray(), pagination);	            
            }            
            // END : #119240 : 22/06/09
            
            // search.setTotalResults(results);
            search.setResults(results);
            search.setPagination(pagination);
        }
    }

    public Link getLinkByControl(String name) {
        Link link = null;
        List links = getDao().find(" from Link l where l.name=?", new Object[] { name });
        if (links != null && links.size() > 0) {
            link = (Link) links.get(0);
        }
        else {
            throw new ServiceException("control.not.exist.error", new Object[] { name }, null);
        }
        return link;
    }

    public void searchPermissionListControl(PermissionListSearch search, String sortFlag) {
        if (search == null)
            return;

        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();

        boolean hasWhere = false;
        String strpermissionListSearch = "";
        String pl = search.getControl().getValue();

        if ((pl != null) && !"".equals(pl.trim())) {
            strpermissionListSearch = '%' + pl.toUpperCase() + '%';
            sb.append(" where upper(l.name) like ?");
            params.add(strpermissionListSearch);
            hasWhere = true;
        }
        Pagination pagination = search.getPagination();
        List results = null;
        if (sortFlag != null && sortFlag.equals("")) {
            if (pagination != null) {
                if (pagination.getTotalRecord() > 0) {
                    List counts = getDao().find("select count(l.name) from Link l " + sb.toString(), params.toArray());

                    if (counts.size() > 0) {
                        Number count = (Number) counts.get(0);
                        pagination.setTotalRecord(count.intValue());
                    }
                }
                results = getDao().find("select l from Link l  left join fetch l.permissions p " + sb.toString() + " order by l.name", params.toArray(),
                                        pagination);

                pagination.calculatePages();
            }
            else {
                results = getDao().find(" select l from Link l  left join fetch l.permissions p" + sb.toString(), params.toArray());
            }
            search.setResults(results);
            search.setPagination(pagination);
        }
        else {
            String orderByValue = " order by l." + sortFlag;
            if (pagination != null) {
                if (pagination.getTotalRecord() > 0) {
                    List counts = getDao().find("select count(l.name) from Link l " + sb.toString(), params.toArray());

                    if (counts.size() > 0) {
                        Number count = (Number) counts.get(0);
                        pagination.setTotalRecord(count.intValue());
                    }
                }

                pagination.calculatePages();
            }
            // START : #119240 : 22/06/09
            /*results = getDao().find("select l from Link l  left join fetch l.permissions p " + sb.toString() + orderByValue, params.toArray(), pagination);*/
            if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
            	results = getDao().find("select l from Link l  left join fetch l.permissions p " + sb.toString() + orderByValue + " " + search.getSortOrderFlag() , params.toArray(), pagination);
            }else{
            	results = getDao().find("select l from Link l  left join fetch l.permissions p " + sb.toString() + orderByValue, params.toArray(), pagination);
            }
            // END : #119240 : 22/06/09
            // search.setTotalResults(results);
            search.setResults(results);
            search.setPagination(pagination);
        }
    }

    public void searchTimeZone(TimeZoneSearch search, String sortFlag) {

        if (search == null)
            return;

        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();

        boolean hasWhere = false;

        String strTimeZoneSearch = "";
        String strZoneDescSearch = "";
        strTimeZoneSearch = search.getTimeZone().getValue();

        if ((strTimeZoneSearch != null) && !"".equals(strTimeZoneSearch.trim()))
            strTimeZoneSearch = '%' + strTimeZoneSearch.toUpperCase() + '%';

        if ((strTimeZoneSearch != null) && !"".equals(strTimeZoneSearch.trim())) {
            sb.append(" where upper(tz.timeZone) like ?");
            params.add(strTimeZoneSearch);
            hasWhere = true;
        }

        strZoneDescSearch = search.getTimeZoneDesc().getValue();

        if ((strZoneDescSearch != null) && !"".equals(strZoneDescSearch.trim()))
            strZoneDescSearch = '%' + strZoneDescSearch.toUpperCase() + '%';

        if ((strZoneDescSearch != null) && !"".equals(strZoneDescSearch.trim())) {
            if (hasWhere)
                sb.append(" and ");
            else {
                hasWhere = true;
                sb.append(" where ");
            }

            sb.append(" upper(tz.timeZoneDesc) like ?");
            params.add(strZoneDescSearch);
        }

        Pagination pagination = search.getPagination();
        List results = null;
        if (sortFlag != null && sortFlag.equals("")) {
            if (pagination != null) {
                if (pagination.getTotalRecord() > 0) {
                    List counts = getDao().find("select count(tz.timeZone) from TimeZone tz " + sb.toString(), params.toArray());

                    if (counts.size() > 0) {
                        Number count = (Number) counts.get(0);
                        pagination.setTotalRecord(count.intValue());
                    }
                }
                results = getDao().find("select distinct tz from TimeZone tz " + sb.toString() + " order by tz.timeZone", params.toArray(), pagination);

                pagination.calculatePages();
            }
            else {
                results = getDao().find("select distinct tz from TimeZone tz " + sb.toString(), params.toArray());
            }

            search.setResults(results);
            search.setPagination(pagination);
        }
        else {
            String orderByValue = " order by tz." + sortFlag;
            if (pagination != null) {
                if (pagination.getTotalRecord() > 0) {
                    List counts = getDao().find("select count(tz.timeZone) from TimeZone tz " + sb.toString(), params.toArray());

                    if (counts.size() > 0) {
                        Number count = (Number) counts.get(0);
                        pagination.setTotalRecord(count.intValue());
                    }
                }
                pagination.calculatePages();
            }

            // START : #119240 : 19/06/09
            /*results = getDao().find("select distinct tz from TimeZone tz " + sb.toString() + orderByValue, params.toArray(), pagination);*/
            if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().trim().equals("")){
            	results = getDao().find("select distinct tz from TimeZone tz " + sb.toString() + orderByValue + " " + search.getSortOrderFlag(), params.toArray(), pagination);
            }else{
            	results = getDao().find("select distinct tz from TimeZone tz " + sb.toString() + orderByValue, params.toArray(), pagination);
            }
            // END : #119240 : 19/06/09

            // search.setTotalResults(results);
            search.setResults(results);
            search.setPagination(pagination);
        }

    }

    public List getTimeZonesByName(String name) {

        if (name == null)
            return new ArrayList();

        return getDao().find("from TimeZone tz where upper(tz.timeZone) like '" + name.toUpperCase() + "%'", null);

    }

    public List getAllPaymentTerms() {
        return getDao().find("from  PaymentTerm", null);
    }

    public TimeZone getTimeZoneByName(String name) {
        TimeZone timezone = null;
        String ename = "";
        if (name != null && !name.trim().equals(""))
            ename = name.toUpperCase();
        List timeZones = getDao().find("from TimeZone tz where upper(tz.timeZone) ='" + ename + "'", null);
        if (timeZones.size() > 0)
            return (TimeZone) timeZones.get(0);
        else
            return null;
        /*
         * if(timeZones!= null && timeZones.size() >0) { timezone =
         * (TimeZone)timeZones.get(0); } else{ throw new
         * ServiceException("timezone.not.exists.error", new Object[] {name},
         * null); } return timezone;
         */
    }

    public List getEntitiesByHql(String hql, Pagination pagination) {
        if (pagination != null) {
            return getDao().find(hql, null, pagination);
        }
        else {
            return getDao().find(hql, null);
        }
    }

    @Override
    public void searchAdminObject(com.intertek.phoenix.search.Search search) {
        throw new UnsupportedOperationException("searchAdminObject is not supported by AdminServiceImpl");
    }
}
