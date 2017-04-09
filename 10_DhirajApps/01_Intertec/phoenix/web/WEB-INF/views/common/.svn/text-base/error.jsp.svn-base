<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true" %>

<%@ page import="java.util.*,java.io.*" %>

<%
InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("messages.properties");
Properties props = new Properties();
props.load(is);

Exception ex = (Exception) request.getAttribute("exception");
%>
<form:form name="errroForm">
<table width="100%" height ="50%" border="0" cellpadding="0" cellspacing="0" >
<tr><br/><br/><br/>
<h3><%=props.getProperty("generic.errorpage.header") %></h3>

<font color="red">
  <b><%=ex.getMessage() %></b><br/><br/>
</font>

  <% 
  String  message = ""; 
  if (ex.getMessage() != null && ex.getMessage().toString().equalsIgnoreCase("generic.error")) {
	  message = ex.getCause().toString();
  }
  %>
  <%= ex.getMessage() %><br /><br/>

  <%
    try
    {
//      out.println("<!-- ");
      java.io.StringWriter sw = new java.io.StringWriter();
      java.io.PrintWriter pw = new java.io.PrintWriter(sw);
      ex.printStackTrace(pw);
      out.print(sw);
      sw.close();
      pw.close();
//      out.println(" -->");
    }
    catch(Throwable t)
    {
    }
  %>

</tr>
</table>
</form:form>
