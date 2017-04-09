<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.acegisecurity.AuthenticationException" %>
<%@ page import="java.util.*" %>
<%@ page import="com.intertek.web.servlet.PhoenixSystem" %>
<%@ page import="com.intertek.web.servlet.PhoenixSystem.LoggedInUser" %>
<%@ page import="com.intertek.web.servlet.PhoenixSystem.ActionUrl" %>
<%@ page import="com.intertek.util.Constants" %>

<%
	java.text.DecimalFormat decFormat=new java.text.DecimalFormat("####0.0");
	HashMap users=(HashMap)request.getAttribute("users");

	String hang=request.getParameter("hang");
	if(hang!=null && hang.equals("true")){
		users=PhoenixSystem.logOffUsers;
	}
	
	int totalUsers=users.size();
	Iterator itr=users.keySet().iterator();
	StringBuffer allUserEmails=new StringBuffer("");
%>

<script language="javaScript">
function showLastActions(email){
	var myDiv=document.getElementById(email);
	if(myDiv.style.display=="block"){
		myDiv.style.display="none";
	}
	else{
		myDiv.style.display="block";
	}
}
</script>

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
			<td nowrap>
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
							<td nowrap><%=aUrl.getUrl()%></td>
							<td nowrap><%=new java.util.Date((Long)aUrl.getStartTime())%></td>
							<td nowrap><%=(Long)aUrl.getEndTime()>0?decFormat.format((aUrl.getEndTime()-aUrl.getStartTime())/1000.000):"Running"%></td>
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
			<textarea rows="3" cols="180"><%=allUserEmails%></textarea>
		</td>
	</tr>
</table>
