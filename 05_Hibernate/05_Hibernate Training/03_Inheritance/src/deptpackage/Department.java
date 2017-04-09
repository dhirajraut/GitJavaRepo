package deptpackage;

import java.util.List;

import emppackage.Employee;

public class Department {
	private int id;
	private String name;
	private String location;
	private List<Employee> employees; // Association Member.

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
	
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Displaying Department");
		buf.append("Id = " + id);
		buf.append("\nName = " + name);
		buf.append("\nlocation = " + location);
		return buf.toString();
	}
}
