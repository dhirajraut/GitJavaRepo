/**
 * 
 */
package com.intertek.phoenix.search.entity;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.StringSearchField;
import com.intertek.entity.Role;
import com.intertek.entity.User;
import com.intertek.phoenix.search.QueryStruct;
import com.intertek.phoenix.search.Search;

/**
 * @author richard.qin
 * 
 */
public class UserSearch extends Search<User> {

    private StringSearchField name;
    private StringSearchField firstName;
    private StringSearchField lastName;

    private StringSearchField roleName;
    private StringSearchField roleDesc;

    private String inputFieldId;
    private String div1;
    private String div2;
    private String searchForm;
    private String sortFlag;
    private String uxcel = "false";
    private String submitFlag;
    private StringSearchField countryCode;
    // START : #119240 : 19/06/09
    private String currentSortFlag;
    private String prevSortFlag;
    private String sortOrderFlag;
    // END : #119240 : 19/06/09

    public UserSearch() {
        name = new StringSearchField();
        firstName = new StringSearchField();
        lastName = new StringSearchField();
        countryCode = new StringSearchField();
        roleName = new StringSearchField();
        roleDesc = new StringSearchField();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.search.Search#constructQuery()
     */
    @Override
    public QueryStruct constructQuery() {
        StringBuffer sb = new StringBuffer();
        String sql = "select distinct u from User u left join fetch u.branch left join fetch u.branch.businessUnit ";
        String sqlCount = "select count(distinct u.loginName) from User u ";
        List<String> params = new ArrayList<String>();

        boolean hasWhere = false;

        hasWhere = buildQueryParameter(sb, params, this.getName(), "u.loginName", hasWhere);
        hasWhere = buildQueryParameter(sb, params, this.getFirstName(), "u.firstName", hasWhere);
        hasWhere = buildQueryParameter(sb, params, this.getLastName(), "u.lastName", hasWhere);
        hasWhere = buildQueryParameter(sb, params, this.getCountryCode(), "u.countryCode", hasWhere);
        
        if (this.getRoleName() != null || this.getRoleDesc() != null) {
            sql += " left join fetch u.roles r ";
            sqlCount += " left join u.roles r";
            hasWhere = buildQueryParameter(sb, params, this.getRoleName(), "r.name", hasWhere);
            hasWhere = buildQueryParameter(sb, params, this.getRoleDesc(), "r.roleDesc", hasWhere);
        }

        // apply the sort flag
        if (this.sortFlag == null || this.sortFlag.equals("")) {
            sb.append(" order by u.loginName");
        }
        else {
        	// START : #119240 : 19/06/09
        	//sb.append(" order by u.").append(this.sortFlag);
        	if(this.sortOrderFlag != null && !this.sortOrderFlag.equals("")){
        		sb.append(" order by u.").append(this.sortFlag).append(" " + this.sortOrderFlag);
        	}else{
        		sb.append(" order by u.").append(this.sortFlag);
        	}	
        	// END : #119240 : 19/06/09
        }

        return new QueryStruct(sql, sqlCount, sb.toString(), params);
    }

    @Override
    public String getExportFileName(){
        return "user_export.csv";
    }

    @Override
    public String[] getResultHeaders() {
        return new String[]{"User Name", "First Name", "Last Name", "Country", "Employee Status", "Roles"};
    }

    @Override
    public Object[] getResultAsStrings(int index) {
        User user = this.getResults().get(index);
        List<Object> strings = new ArrayList<Object>();
        
        // see getResultHeaders for details.
        strings.add(user.getLoginName());
        strings.add(user.getFirstName());
        strings.add(user.getLastName());
        strings.add(user.getCountryCode());
        strings.add(user.getEmployeeStatus());
        
        // add roles as a nested array of strings
        List<Object> roleStrings = new ArrayList<Object>();
        for(Role role: user.getRoles()){
            roleStrings.add(role.getName());
        }
        strings.add(roleStrings.toArray());
        
        return strings.toArray();
    }
    
    /**
     * @return the roleName
     */
    public StringSearchField getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     *            the roleName to set
     */
    public void setRoleName(StringSearchField roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the roleDesc
     */
    public StringSearchField getRoleDesc() {
        return roleDesc;
    }

    /**
     * @param roleDesc
     *            the roleDesc to set
     */
    public void setRoleDesc(StringSearchField roleDesc) {
        this.roleDesc = roleDesc;
    }

    public void setName(StringSearchField name) {
        this.name = name;
    }

    public StringSearchField getName() {
        return name;
    }

    public void setFirstName(StringSearchField firstName) {
        this.firstName = firstName;
    }

    public StringSearchField getFirstName() {
        return firstName;
    }

    public void setLastName(StringSearchField lastName) {
        this.lastName = lastName;
    }

    public StringSearchField getLastName() {
        return lastName;
    }

    public StringSearchField getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(StringSearchField countryCode) {
        this.countryCode = countryCode;
    }

    public String getInputFieldId() {
        return inputFieldId;
    }

    public void setInputFieldId(String inputFieldId) {
        this.inputFieldId = inputFieldId;
    }

    public String getDiv1() {
        return div1;
    }

    public void setDiv1(String div1) {
        this.div1 = div1;
    }

    public String getDiv2() {
        return div2;
    }

    public void setDiv2(String div2) {
        this.div2 = div2;
    }

    public String getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(String searchForm) {
        this.searchForm = searchForm;
    }

    public String getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(String sortFlag) {
        this.sortFlag = sortFlag;
    }

    public String getUxcel() {
        return uxcel;
    }

    public void setUxcel(String uxcel) {
        this.uxcel = uxcel;
    }

    public String getSubmitFlag() {
        return submitFlag;
    }

    public void setSubmitFlag(String submitFlag) {
        this.submitFlag = submitFlag;
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
