<html>
	<head>
		<script language="javascript">
			function login() {
				document.loginForm.submit();
			}
		</script>
	</head>
	<body>
		<form name="loginForm" action="login.do" method="get">
			<br><br><br><br>
			<center><h1>Welcome To<br>Resource Management System</h1></center>
			<br><br>
			<center><h3><a href="javascript:login();">Click here to continue</a></h3></center>
			<br><br><br><br><br><br><br><br><br><br><br><br>
			<center>
				<h5>This page will be replaced by Spring securities in future.</h5>
			</center>
		</form>
		<% System.out.println("In JSP"); %>
	</body>
</html>