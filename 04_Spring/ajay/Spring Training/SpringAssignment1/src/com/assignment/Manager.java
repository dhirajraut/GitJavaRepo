package com.assignment;

public class Manager extends Employee{

	public Manager(double perks) {
		super();
		this.perks = perks;
	}

	public double getPerks() {
		return perks;
	}

	public void setPerks(double perks) {
		this.perks = perks;
	}

	private double perks;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(int empId, String empName, double salary, Department dept) {
		super(empId, empName, salary, dept);
		// TODO Auto-generated constructor stub
	}
}
