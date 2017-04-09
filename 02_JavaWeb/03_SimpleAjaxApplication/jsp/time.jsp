<%
//response.expires=-1;
//alert("Hi");
System.out.println("Char = "+request.getParameter("inputChar"));
response.getWriter().write(request.getParameter("inputChar") + "Dhiraj");
%>