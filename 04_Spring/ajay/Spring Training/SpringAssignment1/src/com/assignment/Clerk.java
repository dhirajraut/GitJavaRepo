package com.assignment;

public class Clerk extends Employee{

	public Clerk() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clerk(int empId, String empName, double salary, Department dept) {
		super(empId, empName, salary, dept);
		// TODO Auto-generated constructor stub
	}

	public Clerk(double overtime) {
		super();
		this.overtime = overtime;
	}

	public double getOvertime() {
		return overtime;
	}

	public void setOvertime(double overtime) {
		this.overtime = overtime;
	}

	private double overtime;
}
