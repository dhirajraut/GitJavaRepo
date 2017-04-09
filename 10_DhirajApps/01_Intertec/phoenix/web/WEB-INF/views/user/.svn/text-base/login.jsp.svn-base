<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<form:form name="loginForm" method="POST" action="login.htm">
<div class="form" style="text-align:center;"">
  <table>
    <tr><td colspan="3"><h2>Please Login</h2></td></tr>
    <tr><td colspan="3"><form:errors cssClass="error"/></td></tr>
    <tr>
      <td><label for="command.loginName">Login Name: </label></td>
      <td><form:input path="loginName" size="30" /></td>
      <td><form:errors path="loginName" cssClass="error"/></td>
    </tr>
    <tr>
      <td><label for="command.password">Password: </label></td>
      <td><form:password path="password" size="30" /></td>
      <td><form:errors path="password" cssClass="error"/></td>
    </tr>
    <tr>
      <td colspan="3"><input name="Submit" type="submit" class="submitbutton" value="Login" /></td>
    </tr>
    <!-- Code changes for iTrack # 121136
    <tr>
      <td colspan="3"><a href="forgot.htm" style="color:blue;" title="Need help because you've forgotten your password?">Forgot your password?</a></td>
    </tr>
    -->
  </table>
</div>
</form:form>
