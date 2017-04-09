package com.galaxy.spring;

import java.util.List;
import java.util.Map;
public class Employee {
	/* This population will be done by the spring IOC constructor arguments. */
	private int id;
	private String firstName;
	private String lastName;

	/* This population will be done by the spring IOC setter methods. */
	private String designation;
	private List skillSet;
	private String roles[];
	private Map reportingStructure;

	/* This population will be done by the Employee Processor. */
	private float salary;
	
	/* This population will be done by the spring IOC constructor arguments. */
	public Employee(final int id, final String firstName, final String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}

	/* This population will be done by the spring IOC setter methods. */
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public List getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(List skillSet) {
		this.skillSet = skillSet;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String roles[]) {
		this.roles = roles;
	}
	public Map getReportingStructure() {
		return reportingStructure;
	}
	public void setReportingStructure(Map reportingStructure) {
		this.reportingStructure = reportingStructure;
	}
	
	public String toString(){
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("Id : " + id + "\n");
		strBuf.append("First Name : " + firstName + "\n");
		strBuf.append("Last Name : " + lastName + "\n");
		strBuf.append("Designation : " + designation + "\n");
		strBuf.append("SkillSet : " + skillSet + "\n");
		strBuf.append("Roles : " + roles + "\n");
		strBuf.append("Reporting Structure : " + reportingStructure + "\n");
		strBuf.append("Salary : " + salary + "\n");
		return strBuf.toString();
	}

	/* This population will be done by the Employee Processor. */
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
}
