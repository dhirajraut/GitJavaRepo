package com.galaxy.forummodule.domain.mockdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.galaxy.forummodule.domain.DiscussionThread;
import com.galaxy.forummodule.domain.Message;
import com.galaxy.forummodule.domain.User;

public class MockDataHelper {

	private static List<User> userList = new ArrayList<User> ();
	private static List<Message> messageList = new ArrayList<Message> ();
	private static List<DiscussionThread> discussionThreadList = new ArrayList<DiscussionThread> ();
	

	/* Constructors. */
	private MockDataHelper(){
		// Inaccessible constructor.
	}
	static {
		createUser();
		createMessage();
		createDiscussionThread();
	}
	
	private static void createUser() {
		userList.add(new User(1, "dhiraj_raut1", "password1", "Dhiraj1", "Raut1", new Date()));
		userList.add(new User(2, "dhiraj_raut2", "password2", "Dhiraj2", "Raut2", new Date()));
		userList.add(new User(3, "dhiraj_raut3", "password3", "Dhiraj3", "Raut3", new Date()));
		userList.add(new User(4, "dhiraj_raut4", "password4", "Dhiraj4", "Raut4", new Date()));
		userList.add(new User(5, "dhiraj_raut5", "password5", "Dhiraj5", "Raut5", new Date()));
		userList.add(new User(6, "dhiraj_raut6", "password6", "Dhiraj6", "Raut6", new Date()));
	}
	public static User getUser() {
		return userList.get((int)(Math.random() * 100) % userList.size());
	}
	
	public static void createDiscussionThread() {
		discussionThreadList.add(new DiscussionThread(1, getMessage()));
		discussionThreadList.add(new DiscussionThread(2, getMessage()));
		discussionThreadList.add(new DiscussionThread(3, getMessage()));
		discussionThreadList.add(new DiscussionThread(4, getMessage()));
		discussionThreadList.add(new DiscussionThread(5, getMessage()));
		discussionThreadList.add(new DiscussionThread(6, getMessage()));
	}
	public static DiscussionThread getDiscussionThread() {
		return discussionThreadList.get((int)(Math.random() * 100) 
				% discussionThreadList.size());
	}
	
	public static void createMessage() {
		messageList.add(new Message(1, 1, new Date(), "This is text1"));
		messageList.add(new Message(2, 2, new Date(), "This is text2"));
		messageList.add(new Message(3, 3, new Date(), "This is text3"));
		messageList.add(new Message(4, 4, new Date(), "This is text4"));
		messageList.add(new Message(5, 5, new Date(), "This is text5"));
		messageList.add(new Message(6, 6, new Date(), "This is text6"));
	}
	public static Message getMessage() {
		return messageList.get((int)(Math.random() * 100) % messageList.size());
	}
}
