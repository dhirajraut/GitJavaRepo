<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script language="javascript">

function submitForm(form){
	form.submit();
}

</script>

<form:form name="mainForm" method="POST" action="webservice_control_switches.htm">
	<input type="hidden" name="doUpdate" value="true"/>
<c:if test="${not empty requestScope['message']}">
<div style="color:${messageColor};">
  <f:message key="${message}"/>
</div>
</c:if>

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <div id="MainContentContainer">
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="outboundWebServiceControl"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table border="0" align="center" cellpadding="1" cellspacing="0" class="InnerApplTable">
	              	<tr>
						<th width="30%" class="TDShade"><f:message key="serviceName"/></th>
						<th width="2%" class="TDShade"><f:message key="enabled"/></th>
						<th width="68%" class="TDShade"><f:message key="disabled"/></th>
					</tr>
					<c:forEach items="${services}" var="item" varStatus="itemStatus">
						<c:choose>
						<c:when test="${itemStatus.index%2==0}">
							<tr style="background-color:#FFFFFF;">
						</c:when>
						<c:otherwise>
							<tr style="background-color:#e7eeff;">                    
						</c:otherwise>
						</c:choose>
						<td class="TDShade">${item.controlSwitchId.controlSwitchName}</td>
						<td class="TDShade"><input type="radio" name="${item.controlSwitchId.controlSwitchName}" value="enabled" ${item.flag=='enabled'?'checked':''}/></td>
						<td class="TDShade"><input type="radio" name="${item.controlSwitchId.controlSwitchName}" value="disabled" ${item.flag=='disabled'?'checked':''}/></td>
					</tr>    
					</c:forEach>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
						<input id="submitButton" type="button" value="Update" name="submitButton" class="button1" onClick="submitForm(this.form)"/>
						&nbsp;&nbsp;<input id="reset" type="reset" value="Reset" name="reset" class="button1"/>
					  </td>
                    </tr>
                  </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
          </div>
        </div>
        <script type="text/javascript">
        //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
        dolphintabs.init("tabnav", 0)

        </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>

