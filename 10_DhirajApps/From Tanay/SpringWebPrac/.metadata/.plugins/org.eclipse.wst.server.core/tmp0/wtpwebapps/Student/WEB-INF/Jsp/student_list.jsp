<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@  taglib     prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.StudentList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StudentList</title>
<script type = text/javascript>
function getData(id)
{
document.stuListForm.action="<%=request.getContextPath()%>/detailData.htm?action=showDetail&stud_id="+id+"";

document.stuListForm.submit();

}
</script>
</head>
<body>
<form name ="stuListForm" method ="post">
<% 
List resultList=(List)request.getAttribute("studentList");
for(int i=0;i<resultList.size();i++)
{
	StudentList stuList = (StudentList)resultList.get(i);%>
	<a href =javascript:getData(<%=stuList.getId()%>)><%=stuList.getName() %>
	</a><br/>
<% }%>

</form>

</body>
</html>