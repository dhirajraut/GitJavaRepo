<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.acegisecurity.AuthenticationException" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.intertek.web.servlet.PhoenixSystem" %>

<%!
public static void getHTML(String stringURL, Writer out)throws Exception{
	getHTML(stringURL, out, null);
}

public static void getHTML(String stringURL, Writer out, Hashtable headers)throws Exception{
	URL url=new URL(stringURL);
	URLConnection con=url.openConnection();
	con.setUseCaches(false);

	if(headers!=null){
		Iterator itr=headers.keySet().iterator();
		while(itr.hasNext()){
			String header=(String)itr.next();
			String value=(String)headers.get(header);
			con.addRequestProperty(header, value);
		}			
	}
	con.connect();
	
	InputStream in=con.getInputStream();

	int readNum=512;
	byte[] bytes=new byte[readNum+1];
	int len=0;

	while((len=in.read(bytes))>0){
		out.write(new String(bytes, 0, len));
	}
	in.close();
	out.flush();
}
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
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%" border="1" cellpadding="1" cellspacing="1">
	<tr>
		<td valign="top"><%getHTML("http://cbwusahousrv58/phoenix/serverStats.jsp?rnd="+System.currentTimeMillis(), new PrintWriter(out));%></td>
		<td valign="top"><%getHTML("http://cbwusahousrv59/phoenix/serverStats.jsp?rnd="+System.currentTimeMillis(), new PrintWriter(out));%></td>
		<td valign="top"><%getHTML("http://cbwusahousrv60/phoenix/serverStats.jsp?rnd="+System.currentTimeMillis(), new PrintWriter(out));%></td>
		<td valign="top"><%getHTML("http://cbwusahousrv61/phoenix/serverStats.jsp?rnd="+System.currentTimeMillis(), new PrintWriter(out));%></td>
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