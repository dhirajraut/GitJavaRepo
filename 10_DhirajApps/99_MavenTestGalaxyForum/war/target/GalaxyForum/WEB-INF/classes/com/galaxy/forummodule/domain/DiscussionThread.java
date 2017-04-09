package com.galaxy.forummodule.domain;

import java.util.Date;
import java.util.List;

public class DiscussionThread {

	private int id;
	private Message topic;
	private List <Message> responseList;


	/* Constructors */
	private DiscussionThread() {
		// Inaccessible Constructor.
	}
	public DiscussionThread(int id, Message topic) {
		// Basic Constructor.
		setId(id);
		setTopic(topic);
	}


	/* Getters & Setters */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Message getTopic() {
		return topic;
	}
	public void setTopic(Message topic) {
		this.topic = topic;
	}
	public List<Message> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<Message> responseList) {
		this.responseList = responseList;
	}
	public void addResponse(Message response) {
		this.responseList.add(response);
	}
	public String toString() {
		StringBuffer stringCast = new StringBuffer();
		stringCast.append("Id: ");
		stringCast.append(getId());
		stringCast.append("\nTopic: ");
		stringCast.append(getTopic());
		if (null != getResponseList()) {
			stringCast.append("\nResponse: ");
			for (int ctr = 0; ctr < getResponseList().size(); ctr ++) {
				stringCast.append("\tR ");
				stringCast.append(ctr);
				stringCast.append(": ");
				stringCast.append(getResponseList().get(ctr));
			}
		}
		return stringCast.toString();
	}

	/* Business Methods. */
	private Message getLastResponse() {
		return this.responseList.get(responseList.size() - 1);
	}
	private int getLastResponseUserId() {
		return getLastResponse().getUserid();
	}
	private Date getLastResponseDate() {
		return getLastResponse().getDate();
	}
}
