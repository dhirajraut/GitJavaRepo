package deptpackage;

import java.util.HashSet;

public class Department {
	private int id;
	private String name;
	private String location;
	private HashSet employees;

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
	
	public HashSet getEmployees() {
		return employees;
	}
	public void setEmployees(HashSet employees) {
		this.employees = employees;
	}
}
