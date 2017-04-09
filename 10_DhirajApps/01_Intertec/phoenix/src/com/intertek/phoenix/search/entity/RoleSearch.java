/**
 * 
 */
package com.intertek.phoenix.search.entity;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.StringSearchField;
import com.intertek.entity.Permission;
import com.intertek.entity.Role;
import com.intertek.phoenix.search.QueryStruct;
import com.intertek.phoenix.search.Search;

/**
 *
 * @author richard.qin
 */
public class RoleSearch extends Search<Role>{
    private StringSearchField roleDesc;
    private StringSearchField name;
    private StringSearchField role;
    
    // the next 3 fields added to support role search by permission
    private StringSearchField permission;
    private StringSearchField permissionDesc;
    
    private String searchType;
    private String searchForm;
    private String pageNo;
    private String inputFieldId;
    private String rowNum;
    private String sortFlag;
    private String rxcel = "false";
    private String submitFlag;
    private String styleVisibility = "visibility:visible";
    // START : #119240 : 19/06/09
    private String currentSortFlag;
    private String prevSortFlag;
    private String sortOrderFlag;
    // END : #119240 : 19/06/09
    
    
    public RoleSearch() {
        roleDesc = new StringSearchField();
        name = new StringSearchField();
        role = new StringSearchField();
        permission = new StringSearchField();
        permissionDesc = new StringSearchField();
        pageNo = new String();
    }
    
    @Override
    public QueryStruct constructQuery(){
        StringBuffer sb = new StringBuffer();
        String sql = "select distinct r from Role r ";
        String sqlCount = "select count(distinct r.name) from Role r ";
        List<String> params = new ArrayList<String>();
        
        boolean hasWhere = false;
        
        hasWhere = buildQueryParameter(sb, params, this.getName(), "r.name", hasWhere);
        hasWhere = buildQueryParameter(sb, params, this.getRoleDesc(), "r.roleDesc", hasWhere);
        
        if(this.getPermission() != null || this.getPermissionDesc() != null){
            sql += " left join fetch r.permissions p "; 
            sqlCount += " left join r.permissions p "; 
            hasWhere = buildQueryParameter(sb, params, this.getPermission(), "p.name", hasWhere);
            hasWhere = buildQueryParameter(sb, params, this.getPermissionDesc(), "p.description", hasWhere);
        }
        
        // apply the sort flag
        if (this.sortFlag == null || this.sortFlag.equals("")) {
            sb.append(" order by r.name");
        }
        else{
        	// START : #119240 : 19/06/09
        	// sb.append(" order by r.").append(this.sortFlag);
        	if(this.sortOrderFlag != null && !this.sortOrderFlag.equals("")){
                sb.append(" order by r.").append(this.sortFlag).append(" " + this.sortOrderFlag);
        	}else{
                sb.append(" order by r.").append(this.sortFlag);
        	}	
        	// END : #119240 : 19/06/09
        }
        
        return new QueryStruct(sql, sqlCount, sb.toString(), params);
    }    

    @Override
    public String getExportFileName(){
        return "role_export.csv";
    }

    @Override
    public String[] getResultHeaders() {
        return new String[]{"Role Name", "Role Description", "Permission List"};
    }

    @Override
    public Object[] getResultAsStrings(int index) {
        Role role = this.getResults().get(index);
        List<Object> strings = new ArrayList<Object>();
        
        // for this search, only role name, role description and list of permissions are needed.
        // see getResultHeaders for details.
        strings.add(role.getName());
        strings.add(role.getRoleDesc());
        
        // add permission of this role as a nested array of strings
        List<Object> permStrings = new ArrayList<Object>();
        for(Permission perm: role.getPermissions()){
            permStrings.add(perm.getName());
        }
        strings.add(permStrings.toArray());
        
        return strings.toArray();
    }
    
    /**
     * @return the permission
     */
    public StringSearchField getPermission() {
        return permission;
    }

    /**
     * @param permission the permission to set
     */
    public void setPermission(StringSearchField permission) {
        this.permission = permission;
    }

    /**
     * @return the permissionDesc
     */
    public StringSearchField getPermissionDesc() {
        return permissionDesc;
    }

    /**
     * @param permissionDesc the permissionDesc to set
     */
    public void setPermissionDesc(StringSearchField permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public StringSearchField getRole() {
        return role;
    }

    public void setRole(StringSearchField role) {
        this.role = role;
    }

    public void setRoleDesc(StringSearchField roleDesc) {
        this.roleDesc = roleDesc;
    }

    public StringSearchField getRoleDesc() {
        return roleDesc;
    }

    public void setName(StringSearchField name) {
        this.name = name;
    }

    public StringSearchField getName() {
        return name;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchForm(String searchForm) {
        this.searchForm = searchForm;
    }

    public String getSearchForm() {
        return searchForm;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageNo() {
        return pageNo;
    }

    public String getInputFieldId() {
        return inputFieldId;
    }

    public void setInputFieldId(String inputFieldId) {
        this.inputFieldId = inputFieldId;
    }

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(String sortFlag) {
        this.sortFlag = sortFlag;
    }

    public String getRxcel() {
        return rxcel;
    }

    public void setRxcel(String rxcel) {
        this.rxcel = rxcel;
    }

    public String getSubmitFlag() {
        return submitFlag;
    }

    public void setSubmitFlag(String submitFlag) {
        this.submitFlag = submitFlag;
    }

    public String getStyleVisibility() {
        return styleVisibility;
    }

    public void setStyleVisibility(String styleVisibility) {
        this.styleVisibility = styleVisibility;
    }

	// START : #119240 : 19/06/09
	public String getCurrentSortFlag() {
		return currentSortFlag;
	}

	public void setCurrentSortFlag(String currentSortFlag) {
		this.currentSortFlag = currentSortFlag;
	}

	public String getPrevSortFlag() {
		return prevSortFlag;
	}

	public void setPrevSortFlag(String prevSortFlag) {
		this.prevSortFlag = prevSortFlag;
	}

	public String getSortOrderFlag() {
		return sortOrderFlag;
	}

	public void setSortOrderFlag(String sortOrderFlag) {
		this.sortOrderFlag = sortOrderFlag;
	}
    // END : #119240 : 19/06/09
    
}
