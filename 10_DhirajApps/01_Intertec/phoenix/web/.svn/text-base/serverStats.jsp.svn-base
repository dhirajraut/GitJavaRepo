<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.acegisecurity.AuthenticationException" %>
<%@ page import="java.util.*" %>
<%@ page import="com.intertek.web.servlet.PhoenixSystem" %>
<%@ page import="com.intertek.util.Constants" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.intertek.web.servlet.PhoenixSystem.LoggedInUser" %>
<%@ page import="com.intertek.web.servlet.PhoenixSystem.ActionUrl" %>

<%!
public static boolean isExpired(HashMap actions){
	long now=System.currentTimeMillis();
	long twoHours=2*60*60*1000;
	Iterator itr=actions.keySet().iterator();
	while(itr.hasNext()){
		String key=(String)itr.next();
		Object[] info=(Object[])actions.get(key);
		long timeStamp=(Long)info[1];
		//System.out.println(new Date(timeStamp+twoHours));		
		if(timeStamp+twoHours>=now){
			return false;
		}
	}
	return true;
}

public static HashMap filterUsers(HashMap users){
	HashMap toReturn=new HashMap();
	Iterator itr=users.keySet().iterator();
	while(itr.hasNext()){
		String email=(String)itr.next();
		HashMap userMap=(HashMap)users.get(email);
		userMap=(HashMap)userMap.get("lastActions");
		if(!isExpired(userMap)){
			HashMap myMap=new HashMap();
			myMap.put("lastActions", userMap);
			toReturn.put(email, myMap);
		}
	}
	return toReturn;
}

%>
<%
	java.text.DecimalFormat decFormat=new java.text.DecimalFormat("####0.0");
	SimpleDateFormat dateFormat2=new SimpleDateFormat("HH:mm:ss");

	//HashMap users=filterUsers(PhoenixSystem.getUsers());
	HashMap users=PhoenixSystem.getUsers();
	int totalUsers=users.size();
	Iterator itr=users.keySet().iterator();
	StringBuffer allUserEmails=new StringBuffer("");
%>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<th>&nbsp;</th>
		<th><%=Constants.machineName%> - <%=totalUsers%> User(s)</th>
	</tr>
	<%int index=1;%>
	<%while(itr.hasNext()){%>
		<%
			String email=(String)itr.next();
			LoggedInUser loggedInUser=(LoggedInUser)users.get(email); 
			allUserEmails.append(email+";");
			ArrayList<ActionUrl> actionUrls=loggedInUser.getActionUrls();
			Collections.sort(actionUrls);
		%>
		<tr>
			<td valign="top" width="15" align="right"><%=index++%>.</td>
			<td>
				<%=email%>  (<a href="javascript:showLastActions('<%=email%>')">Last <%=PhoenixSystem.MAX_LAST_ACTION%> actions</a>)
				<div id="<%=email%>" style="display:none">
					<table>
					<%for(int i=0; i<actionUrls.size(); i++){%>
						<%
							ActionUrl aUrl=actionUrls.get(i);
							if(aUrl==null){
								continue;
							}
						%>
						<tr>
							<td valign="top" align="right">&nbsp;&nbsp;&nbsp;&nbsp;<%=i+1%>.&nbsp;</td>
							<td><%=aUrl.getUrl()%></td>
							<td><%=new java.util.Date((Long)aUrl.getStartTime())%></td>
							<td><%=(Long)aUrl.getEndTime()>0?decFormat.format((aUrl.getEndTime()-aUrl.getStartTime())/1000.000):"Running"%></td>
						</tr>
					<%}%>
					</table>
				</div>
			</td>
		</tr>
	<%}%>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			All Emails:
			<textarea rows="3" cols="60"><%=allUserEmails%></textarea>
		</td>
	</tr>
</table>
