package deptpackage;

import java.util.HashSet;

import emppackage.Employee;

public class Department {
	private int id;
	private String name;
	private String location;
	private HashSet<Employee> employees;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public HashSet<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(HashSet<Employee> employees) {
		this.employees = employees;
	}
}
