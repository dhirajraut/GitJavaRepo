package emppackage;

import deptpackage.Department;

public class Employee {
	private int id;
	private String name;
	private double salary;
	private char employeeType;
	
	public char getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(char employeeType) {
		this.employeeType = employeeType;
	}

	private Address address;
	
	private Department department;

	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
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
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Id = " + id);
		buf.append("\nName = " + name);
		buf.append("\nSalary = " + salary);
		return buf.toString();
	}
}
