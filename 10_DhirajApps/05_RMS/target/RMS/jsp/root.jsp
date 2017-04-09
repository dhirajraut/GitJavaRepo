<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>

<html>
	<head>
	</head>
	<body>
		<table width="100%" height="100%" cellpadding="0" cellspacing="0" >
			<tr>
				<td height="20%" bgcolor="#EEBB51">&nbsp;
					<tiles:insert attribute="header"/>
				</td>
			</tr>
			<tr>
				<td height="60%" colspan="3">&nbsp;
					<tiles:insert attribute="body"/>
				</td>
			</tr>
			<tr>
				<td height="20%" bgcolor="9CE35B">&nbsp;
					<tiles:insert attribute="footer"/>
				</td>
			</tr>
		</table>
	</body>
</html>