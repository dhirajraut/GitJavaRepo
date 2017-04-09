package com.galaxy.spring;

public class GreetingImpl implements IGreeting{

	private String personName;
	
	public void setPersonName(final String personName) {
		this.personName = personName;
	}

	public String getPersonName() {
		return this.personName;
	}
	public void greet() {
		System.out.println("Hello " + getPersonName());
	}
}
