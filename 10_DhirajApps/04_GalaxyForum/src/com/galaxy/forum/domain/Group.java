package com.galaxy.forum.domain;

import java.util.List;

public class Group {
	public int groupId;
	public String groupName;
	public List <DThread> discussionThreadList;
	
	
	/* Setters and Getters. */
	public List<DThread> getDiscussionThreadList() {
		return discussionThreadList;
	}
	public void setDiscussionThreadList(List<DThread> discussionThreadList) {
		this.discussionThreadList = discussionThreadList;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
