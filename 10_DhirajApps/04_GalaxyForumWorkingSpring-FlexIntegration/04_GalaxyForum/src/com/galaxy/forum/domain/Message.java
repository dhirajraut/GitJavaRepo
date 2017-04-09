package com.galaxy.forum.domain;

import java.util.Date;

public class Message {

	private int id;
	private int userid;
	private Date date;
	private String text;
	

	/* Constructors. */
	private Message() {
		// Inaccessible Constructor.
	}
	public Message(int id, int userid, Date date, String text) {
		// Basic Constructor.
		setId(id);
		setUserid(userid);
		setDate(date);
		setText(text);
	}


	/* Getters & Setters. */
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String toString() {
		StringBuffer stringCast = new StringBuffer();
		stringCast.append("Id: ");
		stringCast.append(getId());
		stringCast.append("\nUserId: ");
		stringCast.append(getUserid());
		stringCast.append("\nDate: ");
		stringCast.append(getDate());
		stringCast.append("\nText: ");
		stringCast.append(getText());
		return stringCast.toString();
	}
}
