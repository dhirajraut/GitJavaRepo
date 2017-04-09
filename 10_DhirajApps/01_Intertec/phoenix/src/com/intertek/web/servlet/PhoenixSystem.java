package com.intertek.web.servlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.intertek.entity.User;

public class PhoenixSystem{
	private static HashMap<String, LoggedInUser> users=new HashMap<String, LoggedInUser>();
	public static HashMap<String, LoggedInUser> logOffUsers=new HashMap<String, LoggedInUser>(); //logged out users with running url
	
	public static int MAX_LAST_ACTION=10;
	
	private PhoenixSystem(){
	}
	
	public static HashMap getUsers(){
		return users;
	}
	
	public static ActionUrl addStartAction(User user, String url){
		if(user!=null){
			String email=user.getLoginName();
			if(email==null){
				email="";
			}
			email=email.toLowerCase();
			LoggedInUser loggedInUser=users.get(email);
			if(loggedInUser==null){
				loggedInUser=new LoggedInUser(email);
				users.put(email, loggedInUser);
			}
			return loggedInUser.addUrl(url);
		}
		return null;
	}
	
	public static void userLoggedOut(User user){
		if(user!=null){
			String email=user.getLoginName();
			userLoggedOut(email);
		}
	}
	
	public static void userLoggedOut(String email){
		if(email==null){
			email="";
		}
		email=email.toLowerCase();
		LoggedInUser loggedInUser=users.remove(email);
		if(loggedInUser!=null){
			ArrayList<ActionUrl> list=loggedInUser.getActionUrls();
			for(int i=0; list!=null && i<list.size(); i++){
				ActionUrl aUrl=list.get(i);
				if(aUrl.getEndTime()<0){
					logOffUsers.put(email, loggedInUser);
					break;
				}
			}
		}
	}
	
	public static class ActionUrl implements Comparable{
		protected String url="";
		protected long startTime;
		protected long endTime=-1;
		
		public ActionUrl(String url){
			this.url=url;
			this.startTime=System.currentTimeMillis();
		}
		
		public String getUrl(){
			return url;
		}
		
		public long getEndTime(){
			return endTime;
		}
		
		public long getStartTime(){
			return startTime;
		}
		
		public void setEndTime(long endTime){
			this.endTime=endTime;
		}

		public void resetUrl(String url){
			this.url=url;
			this.startTime=System.currentTimeMillis();
			this.endTime=-1;
		}
		
		@Override
		public int compareTo(Object arg0) {
			ActionUrl other=(ActionUrl)arg0;
			long diff=this.getStartTime()-other.getStartTime();
			if(diff<0){
				return -1;
			}
			else if(diff>0){
				return 1;
			}
			else{
				return 0;
			}
		}
	}
	
	public static class LoggedInUser{
		protected String email;
		protected ArrayList<ActionUrl> actionUrls;
		
		public LoggedInUser(String email){
			this.email=email;
			this.actionUrls=new ArrayList<ActionUrl>();
		}
		
		public ActionUrl addUrl(String url){
			Collections.sort(actionUrls);
			
			if(actionUrls.size()>=MAX_LAST_ACTION){
				for(int i=0; i<actionUrls.size(); i++){
					ActionUrl old=actionUrls.get(i);
					if(old.getEndTime()>0){ //only reuse the actionUrl that is finished
						old.resetUrl(url);
						return old;
					}
				}
			}
			
			ActionUrl aUrl=new ActionUrl(url);
			actionUrls.add(aUrl);
			return aUrl;
		}
		
		public ArrayList<ActionUrl> getActionUrls(){
			return actionUrls;
		}
	}
}
