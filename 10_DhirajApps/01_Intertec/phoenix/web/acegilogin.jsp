<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.acegisecurity.AuthenticationException" %>

<html>
<head>
<title>Intertek</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/global.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/tabcontent.css" />
<script type="text/javascript" src="js/tabcontent.js"></script>
<script language="javascript" src="js/flipmenu.js"></script>

<script type="text/JavaScript">
<!--
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//-->
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" OnLoad="document.loginForm.j_username.focus();">
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
        <td class="text14boldBlue">Login &raquo;</td>
      </tr>
    </table></td>
  </tr>
</table>

<form name="loginForm" action="<c:url value='j_acegi_security_check'/>" method="POST">

<table width="100%" height="78%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top">&nbsp;</td> 
    <!-- <td valign="top"><font color="red"><f:message key="serverMessage"/></font></td>-->
  </tr>
  <tr>
    <td>
      <c:if test="${not empty param.login_error}">
        <font color="red">
          Your login attempt was not successful, try again.<BR><BR>
          Reason: <%= ((AuthenticationException) session.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
        </font>
      </c:if>
    </td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="45%" align="center" style="padding:20px;"><table width="50%" border="0" cellspacing="0" cellpadding="5" >
            <tr>
              <td class="text11bold">User ID: </td>
              <td><label>
                <input type='text' name='j_username' size="20" <c:if test="${not empty param.login_error}">value='<%= session.getAttribute(AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY) %>'</c:if>></td></tr>
              </label></td>
            </tr>
            <tr>
              <td class="text11bold">Password:</td>
              <td><input type='password' name='j_password'></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td><input name="Submit" type="submit" class="button1" value="Sign In">              </td>
            </tr>
            <!-- Code changes for iTrack # 121136
            <tr>
              <td>&nbsp;</td>
              <td><a href="#" class="subLink">Forgot your password?</a> </td>
            </tr>
            -->
          </table>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p></td>
        <td width="1" bgcolor="#CCCCCC"><img src="images/spacer.gif" width="1" height="1"></td>
        <td width="55%" align="right" valign="top" style="padding-top:40px;"><table width="50%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><strong>Select Language </strong></td>
            </tr>
            <tr>
              <td bgcolor="#333333"><img src="images/spacer.gif" width="1" height="1"></td>
            </tr>
            <tr>
              <td height="50">English | <a href="#">Spanish</a> | <a href="#">Korean</a> </td>
            </tr>
        </table></td>
      </tr>
    </table></td>
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