/**
 * 
 */
package com.intertek.phoenix.search.entity;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.StringSearchField;
import com.intertek.entity.Link;
import com.intertek.entity.Permission;
import com.intertek.phoenix.search.QueryStruct;
import com.intertek.phoenix.search.Search;

/**
 * @author richard.qin
 *
 */
public class PermissionSearch extends Search<Permission> {

    private StringSearchField rolePerms;
    private StringSearchField description;
    private StringSearchField control;
    private StringSearchField controlDesc;
    
    private String searchForm;
    private String pageNo;
    private String inputFieldId;
    private String rowNum;
    private String controlFlag;
    private String name;
    private String sortFlag;
    private String pxcel = "false";
    private String submitFlag;
    // START : #119240 : 19/06/09
    private String currentSortFlag;
    private String prevSortFlag;
    private String sortOrderFlag;
    // END : #119240 : 19/06/09
    

    public PermissionSearch() {
        rolePerms = new StringSearchField();
        pageNo = new String();
        control = new StringSearchField();
        description = new StringSearchField();
        controlDesc = new StringSearchField();
    }

    /* (non-Javadoc)
     * @see com.intertek.phoenix.search.Search#constructQuery()
     */
    @Override
    public QueryStruct constructQuery() {
        StringBuffer sb = new StringBuffer();
        String sql = "select distinct p from Permission p ";
        // START : #119240 : 25/06/09 : Fix for pagination not showing correctly in the Permission Search 
        /*String sqlCount = "select count(p.name) from Permission p ";*/
        String sqlCount = "select count(distinct p.name) from Permission p ";
        // END : #119240 : 25/06/09
        List<String> params = new ArrayList<String>();
        
        boolean hasWhere = false;
        
        hasWhere = buildQueryParameter(sb, params, this.getRolePerms(), "p.name", hasWhere);
        hasWhere = buildQueryParameter(sb, params, this.getDescription(), "p.description", hasWhere);
        
        if(this.getControl() != null || this.getControlDesc() != null) { 
            // permission search by controls, aka links
            sql += " left join fetch p.links l "; 
            sqlCount += " left join p.links l"; 
            hasWhere = buildQueryParameter(sb, params, this.getControl(), "l.name", hasWhere);
            hasWhere = buildQueryParameter(sb, params, this.getControlDesc(), "l.description", hasWhere);
        }
        
        // apply the sort flag
        if (this.sortFlag == null || this.sortFlag.equals("")) {
            sb.append(" order by p.name");
        }
        else{
        	// START : #119240 : 19/06/09
        	//sb.append(" order by p.").append(this.sortFlag);
        	if(this.sortOrderFlag != null && !this.sortOrderFlag.equals("")){
        		sb.append(" order by p.").append(this.sortFlag).append(" " + this.sortOrderFlag);
        	}else{
        		sb.append(" order by p.").append(this.sortFlag);
        	}	
        	// END : #119240 : 19/06/09
        }
        
        return new QueryStruct(sql, sqlCount, sb.toString(), params);
    }
    
    @Override
    public String getExportFileName(){
        return "permission_export.csv";
    }

    @Override
    public String[] getResultHeaders() {
        return new String[]{"Permission Name", "Description", "Controls"};
    }

    @Override
    public Object[] getResultAsStrings(int index) {
        Permission perm = this.getResults().get(index);
        List<Object> strings = new ArrayList<Object>();
        
        // see getResultHeaders for details.
        strings.add(perm.getName());
        strings.add(perm.getDescription());
        
        // add permission of this role as a nested array of strings
        List<Object> linkStrings = new ArrayList<Object>();
        for(Link link: perm.getLinks()){
            linkStrings.add(link.getName());
        }
        strings.add(linkStrings.toArray());
        
        return strings.toArray();
    }
    
    /**
     * @return the controlDescription
     */
    public StringSearchField getControlDesc() {
        return controlDesc;
    }

    /**
     * @param controlDescription the controlDescription to set
     */
    public void setControlDesc(StringSearchField controlDesc) {
        this.controlDesc = controlDesc;
    }

    /**
     * @param rolePerms the rolePerms to set
     */
    public void setRolePerms(StringSearchField rolePerms) {
        this.rolePerms = rolePerms;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(StringSearchField description) {
        this.description = description;
    }

    /**
     * @param control the control to set
     */
    public void setControl(StringSearchField control) {
        this.control = control;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public void SetRolePerms(StringSearchField rolePerms) {
        this.rolePerms = rolePerms;
    }

    public StringSearchField getRolePerms() {
        return rolePerms;
    }

    public void SetDescrption(StringSearchField description) {
        this.description = description;
    }

    public StringSearchField getDescription() {
        return description;
    }

    public void SetControl(StringSearchField control) {
        this.control = control;
    }

    public StringSearchField getControl() {
        return control;
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

    public String getControlFlag() {
        return controlFlag;
    }

    public void setControlFlag(String controlFlag) {
        this.controlFlag = controlFlag;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(String sortFlag) {
        this.sortFlag = sortFlag;
    }

    public String getPxcel() {
        return pxcel;
    }

    public void setPxcel(String pxcel) {
        this.pxcel = pxcel;
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
