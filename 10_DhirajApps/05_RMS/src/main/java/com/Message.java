package com;

import java.util.Date;

public class Message {

	private int id;
	private int userId;
	private Date postDate;
	private String messageText;
	

	/* Constructors. */
	private Message() {
		// Inaccessible Constructor.
	}
	public Message(int id, int userid, Date date, String text) {
		// Basic Constructor.
		setId(id);
		setUserId(userid);
		setPostDate(date);
		setMessageText(text);
	}


	/* Getters & Setters. */
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String text) {
		this.messageText = text;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String toString() {
		StringBuffer stringCast = new StringBuffer();
		stringCast.append("Id: ");
		stringCast.append(getId());
		stringCast.append("\nUserId: ");
		stringCast.append(getUserId());
		stringCast.append("\nDate: ");
		stringCast.append(getPostDate());
		stringCast.append("\nText: ");
		stringCast.append(getMessageText());
		return stringCast.toString();
	}
}
