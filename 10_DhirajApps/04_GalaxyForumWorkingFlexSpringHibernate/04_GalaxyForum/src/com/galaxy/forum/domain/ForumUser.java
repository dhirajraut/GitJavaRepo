package com.galaxy.forum.domain;

import java.util.Date;

import com.galaxy.login.domain.User;

public class ForumUser extends User{

	private Date joinDate;
	private int noOfPosts;
	private IUserRole role;
	
	
	/* Constructors */
	protected ForumUser(){
		// Inaccessible constructor.
	}
	public ForumUser (final int id, final String userId, final String firstName) {
		// Basic Constructor.
		super(id, userId, firstName);
	}
	public ForumUser (final int id, final String userId, final String password,
			final String firstName, final String lastName, final Date joinDate) {
		// Full Constructor.
		super(id, userId, password, firstName, lastName);
		setJoinDate(joinDate);
		setNoOfPosts(0);
	}
	
	
	/* Getters & Setters. */
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public int getNoOfPosts() {
		return noOfPosts;
	}
	public void setNoOfPosts(int noOfPosts) {
		this.noOfPosts = noOfPosts;
	}
	public IUserRole getRole() {
		return role;
	}
	public void setRole(IUserRole role) {
		this.role = role;
	}
	
	
	public String toString() {
		StringBuffer stringCast = new StringBuffer();
		stringCast.append(super.toString());
		stringCast.append("\nJoinDate: ");
		stringCast.append(getJoinDate());
		stringCast.append("\nNo Of Posts: ");
		stringCast.append(getNoOfPosts());
		return stringCast.toString();
	}


	/* Business Methods. */
	public boolean promote() {
		IUserRole nextRole = getRole().getNextRole(); 
		if (null != nextRole) {
			setRole(nextRole);
			return true;
		}
		return false;
	}
	public boolean demote() {
		IUserRole previousRole = getRole().getPreviousRole(); 
		if (null != previousRole) {
			setRole(previousRole);
			return true;
		}
		return false;
	}
}
