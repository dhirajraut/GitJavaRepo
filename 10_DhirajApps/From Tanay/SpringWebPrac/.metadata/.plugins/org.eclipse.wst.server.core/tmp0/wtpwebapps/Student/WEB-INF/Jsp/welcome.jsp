<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@  taglib     prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome User</title>
<script type="text/javascript">
function loginD(frm){
frm.action="<%=request.getContextPath()%>/ListController.htm?action=showList";
frm.submit();
}
</script>
</head>

<body>

Welcome 
<c:set var="a" value="${param.name}"/>
<c:out value="${a}"></c:out>

<form method= post name = "Welcome" >
Do you want to proceed further..??
<input type="submit" value="Yes" onclick = "javascript:loginD(document.Welcome)">

</form>
</body>
</html>