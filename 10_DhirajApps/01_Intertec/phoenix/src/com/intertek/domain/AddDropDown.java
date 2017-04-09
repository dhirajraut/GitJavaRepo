	package com.intertek.domain;

	import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.DropDowns;

	public class AddDropDown
	{
	    @CascadeValidation
		private DropDowns dropDowns;
		private String addOrDelete;
		
		private DropDowns[] dropDown;
		private int dropDownCount ;
		private int dropDownIndex;
		private String operationFlag;
		private String rowNum;
		private String searchForm;
		private String dropdownType;
		private String cacheAdminFlag;
		private String tabName="0";
		private Object[] cacheNames;
		
		/**
		 * @return the cacheAdminFlag
		 */
		public String getCacheAdminFlag() {
			return cacheAdminFlag;
		}
		/**
		 * @param cacheAdminFlag the cacheAdminFlag to set
		 */
		public void setCacheAdminFlag(String cacheAdminFlag) {
			this.cacheAdminFlag = cacheAdminFlag;
		}
		public String getDropdownType() {
			return dropdownType;
		}
		public void setDropdownType(String dropdownType) {
			this.dropdownType = dropdownType;
		}

		
		public String getOperationFlag() {
			return operationFlag;
		}
		public void setOperationFlag(String operationFlag) {
			this.operationFlag = operationFlag;
		}
		public String getRowNum() {
			return rowNum;
		}
		public void setRowNum(String rowNum) {
			this.rowNum = rowNum;
		}
		public String getSearchForm() {
			return searchForm;
		}
		public void setSearchForm(String searchForm) {
			this.searchForm = searchForm;
		}
		public DropDowns[] getDropDown() {
			return dropDown;
		}
		public void setDropDown(DropDowns[] dropDown) {
			this.dropDown = dropDown;
		}
		public DropDowns getDropDowns() {
			return dropDowns;
		}
		public void setDropDowns(DropDowns dropDowns) {
			this.dropDowns = dropDowns;
		}
		public int getDropDownCount() {
			return dropDownCount;
		}
		public void setDropDownCount(int dropDownCount) {
			this.dropDownCount = dropDownCount;
		}
		public int getDropDownIndex() {
			return dropDownIndex;
		}
		public void setDropDownIndex(int dropDownIndex) {
			this.dropDownIndex = dropDownIndex;
		}
		public String getAddOrDelete() {
			return addOrDelete;
		}
		public void setAddOrDelete(String addOrDelete) {
			this.addOrDelete = addOrDelete;
		}
		/**
		 * @return the tabName
		 */
		public String getTabName() {
			return tabName;
		}
		/**
		 * @param tabName the tabName to set
		 */
		public void setTabName(String tabName) {
			this.tabName = tabName;
		}
		/**
		 * @return the cacheNames
		 */
		public Object[] getCacheNames() {
			return cacheNames;
		}
		/**
		 * @param cacheNames the cacheNames to set
		 */
		public void setCacheNames(Object[] cacheNames) {
			this.cacheNames = cacheNames;
		}
	}
