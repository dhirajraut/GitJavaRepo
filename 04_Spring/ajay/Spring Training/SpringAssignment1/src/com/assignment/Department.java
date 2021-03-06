package com.assignment;

import java.util.HashSet;
import java.util.List;

public class Department {

	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(int deptId, String deptName, String location, List emps) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.location = location;
		this.emps = emps;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List getEmps() {
		return emps;
	}
	public void setEmps(List emps) {
		this.emps = emps;
	}
	private int deptId;
	private String deptName;
	private String location;
	private List emps;
}
