<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

   <script language="javascript">
	function submitForm()
	{
		document.changeUserForm.submit();
	}

   </script>
<form:form name="changeUserForm" method="POST" action="change_user.htm">
<input type="hidden" name="pageNumber" value="1" />
<div style="color:red;">
<form:errors cssClass="error"/>


</div>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
<!----------------------------------------------MAIN CONTAINER----------------------------------------------------->
      <div id="MainContentContainer">
<!--------------------------------------------TABS CONTENTS-------------------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav">
          <authz:authorize ifAnyGranted="ChangeUserLoginName">
            <ul>
              <li><a href="#" rel="tab1"><span>Change User  </span></a></li>
            </ul>
            </authz:authorize>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
<!------------------------------------------TAB 1 CONTAINER-------------------------------------------------------->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
                  <%--<th colspan="2">Change User</th>--%>
				  <th colspan="2"><f:message key="changeUser"/></th>
                </tr>
                <tr>
                  <%--<td width="20%" class="TDShade">Old User Name:</td>--%>
				  <td width="20%" class="TDShade"><f:message key="oldUserName"/>:</td>
                  <td width="80%" class="TDShadeBlue">
                    <form:input cssClass="inputBox" size="31" maxlength="128" path="oldUser"/>
                  </td>
                </tr>
                <tr>
                  <%--<td class="TDShade">New User Name:</td>--%>
				  <td class="TDShade"><f:message key="newUserName"/>:</td>
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" size="31" maxlength="128" path="newUser" />
                   </td>
                </tr>


              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td><a href="#" onClick="submitForm()" class="button1" style="text-decoration: none;color:black;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Submit&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
              <br>

            </div>
<!----------------------------------------------TAB 1 CONTAINER END------------------------------------------------>
          </div>
        </div>
        <script type="text/javascript">
      
          //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
          dolphintabs.init("tabnav", 0)

        </script>
<!-------------------------------------------TAB CONTENT END------------------------------------------------------->
      </div>
<!------------------------------------------MAIN CONTAINER END----------------------------------------------------->
    </td>
  </tr>
</table>
</form:form>
