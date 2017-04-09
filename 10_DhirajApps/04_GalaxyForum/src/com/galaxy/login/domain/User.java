package com.galaxy.login.domain;

public class User {

	private int userIndex;
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	
	
	/* Constructors */
	public User(){
		// Inaccessible constructor.
	}
	protected User (final int id, final String userId, final String firstName) {
		// Basic Constructor.
		setUserIndex (id);
		setUserId (userId);
		setFirstName(firstName);
	}
	protected User (final int id, final String userId, final String password,
			final String firstName, final String lastName) {
		// Full Constructor.
		this(id, userId, firstName);
		setLastName(lastName);
		setPassword(password);
	}
	
	
	/* Getters & Setters. */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getUserIndex() {
		return userIndex;
	}
	public void setUserIndex(int userIndex) {
		this.userIndex = userIndex;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String toString() {
		StringBuffer stringCast = new StringBuffer();
		stringCast.append("Id: ");
		stringCast.append(getUserIndex());
		stringCast.append("\nUser Id: ");
		stringCast.append(getUserId());
		stringCast.append("\nPassword: ");
		stringCast.append(getPassword());
		stringCast.append("\nFirstName: ");
		stringCast.append(getFirstName());
		stringCast.append("\nLastName: ");
		stringCast.append(getLastName());
		return stringCast.toString();
	}
}
