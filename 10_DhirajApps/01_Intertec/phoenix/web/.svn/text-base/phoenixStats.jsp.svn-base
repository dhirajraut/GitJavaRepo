<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.acegisecurity.AuthenticationException" %>
<%@ page import="java.util.*" %>
<%@ page import="com.intertek.web.servlet.PhoenixSystem" %>
<%@ page import="com.intertek.web.servlet.PhoenixSystem.LoggedInUser" %>
<%@ page import="com.intertek.web.servlet.PhoenixSystem.ActionUrl" %>

<%
	java.text.DecimalFormat decFormat=new java.text.DecimalFormat("####0.0");
	HashMap users=PhoenixSystem.getUsers();

	String hang=request.getParameter("hang");
	if(hang!=null && hang.equals("true")){
		users=PhoenixSystem.logOffUsers;
	}
	
	int totalUsers=users.size();
	Iterator itr=users.keySet().iterator();
	StringBuffer allUserEmails=new StringBuffer("");
%>
<html>
<head>
<title>Intertek</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/global.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/tabcontent.css" />
<script type="text/javascript" src="js/tabcontent.js"></script>
<script language="javascript" src="js/flipmenu.js"></script>
</head>

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

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td background="images/homtopmainbg.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="739"><img src="images/hominterteklogo.jpg" width="229" height="127"><img src="images/homtopbg01.jpg" width="510" height="127"></td>
        <td align="right" background="images/homtopbg02.jpg" style="background-repeat:no-repeat"><table width="100%" height="120" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td class="headerText">
                <!-- Current date script -->
                <script>


        var mydate=new Date()
        var year=mydate.getYear()
        if (year < 1000)
        year+=1900
        var day=mydate.getDay()
        var month=mydate.getMonth()
        var daym=mydate.getDate()
        if (daym<10)
        daym="0"+daym
        var dayarray=new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
        var montharray=new Array("January","February","March","April","May","June","July","August","September","October","November","December")
        document.write("<font color='ffffff' face='Verdana'>"+dayarray[day]+", "+montharray[month]+" "+daym+", "+year+"</font>")
        
        </script>
                <!-- Current date script End -->
            </td>
          </tr>
          <tr>
            <td height="52" class="headerText">&nbsp;</td>
          </tr>
          <tr>
            <td class="headerText">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td background="images/intopgreymenubg.jpg"><table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="images/intopgreymenubg.jpg" width="40" height="24"></td>
        <td class="text14boldBlue">Phoenix Statistics &raquo; <%=totalUsers%> user(s)</td>
      </tr>
    </table></td>
  </tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<th>&nbsp;</th>
		<th>Email</th>
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
			<textarea rows="3" cols="180"><%=allUserEmails%></textarea>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td bgcolor="#5370AD"><img src="images/spacer.gif" width="1" height="1"></td>
  </tr>
  <tr>
    <td bgcolor="#A0B7E4"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td class="bottomTextLeft">&nbsp;</td>
        <td align="right" class="bottomTextRight">Copyright &copy; 2006 <strong>Intertek</strong>. All rights reserved. Best viewed in 1024 x 768 Res. &nbsp;| &nbsp;Disclaimer</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>