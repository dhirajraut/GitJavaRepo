package com.galaxy.spring;

public class EmployeeProcessor {
	Employee employee;
	public Employee getEmployee(){
		return employee;
	}
	public void setEmployee(final Employee employee){
		this.employee = employee;
	}
	
	public void calculateSalary() {
		employee.setSalary(100000);
	}
	public void printEmployee() {
		System.out.println(getEmployee());
	}
}
