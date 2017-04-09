package com.galaxy.forummodule.domain;

import java.util.Date;

public class User {

	private int id;
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private Date joinDate;
	private int noOfPosts;
	
	
	/* Constructors */
	private User(){
		// Inaccessible constructor.
	}
	public User (final int id, final String userId, final String firstName) {
		// Basic Constructor.
		setId (id);
		setUserId (userId);
		setFirstName(firstName);
	}
	public User (final int id, final String userId, final String password,
			final String firstName, final String lastName, final Date joinDate) {
		// Full Constructor.
		this(id, userId, firstName);
		setPassword(password);
		setLastName(lastName);
		setJoinDate(joinDate);
		setNoOfPosts(0);
	}
	
	
	/* Getters & Setters. */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getNoOfPosts() {
		return noOfPosts;
	}
	public void setNoOfPosts(int noOfPosts) {
		this.noOfPosts = noOfPosts;
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
		stringCast.append(getId());
		stringCast.append("\nUser Id: ");
		stringCast.append(getUserId());
		stringCast.append("\nPassword: ");
		stringCast.append(getPassword());
		stringCast.append("\nFirstName: ");
		stringCast.append(getFirstName());
		stringCast.append("\nLastName: ");
		stringCast.append(getLastName());
		stringCast.append("\nJoinDate: ");
		stringCast.append(getJoinDate());
		stringCast.append("\nNo Of Posts: ");
		stringCast.append(getNoOfPosts());
		return stringCast.toString();
	}
}
