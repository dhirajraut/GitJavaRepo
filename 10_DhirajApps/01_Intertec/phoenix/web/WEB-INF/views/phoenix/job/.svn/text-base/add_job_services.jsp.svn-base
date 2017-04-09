<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script type="text/javascript" src="js/ce/ce_job_services_popup.js"></script>

<form:form name="ceServicePopupForm" method="POST" action="phx_add_job_services.htm">
<form:hidden path="operation" />

  <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
    	<table border="0" cellpadding="5" cellspacing="0" style="margin:15px;">
      	<tr>
        	<td><strong><f:message key="addJobServices"/> : </strong></td>
        	<td>
        		<form:select id="sel1" cssClass="selectionBox" path="serviceCode" items="${command.serviceNames}" itemLabel="name" itemValue="value" onchange="javascript:onSelectService()" />
        	</td>
      	</tr>
    	</table>
    </td>	
  </tr>
</table>
<br>


<c:if test="${command.controlForms != null}">
	
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin:15px;">
    <tr>
     <th colspan="3">${command.service.visibility}</th>
    </tr>
    <c:forEach items="${command.controlForms}" var="controlForm" varStatus="controlStatus">
      <tr>
        <td width="40%" valign="top" nowrap>${controlForm.questionText}</td>
        <td width="30%">
          <c:choose>
            <c:when test="${controlForm.controlType == 'group'}">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td nowrap>
                    <form:radiobuttons path="controlForms[${controlStatus.index}].dataInput" items="${controlForm.groupItems}"/>
                  </td>
                </tr>
              </table>
            </c:when>
            <c:otherwise>            
              <form:input cssClass="inputBox" size="15"  maxlength="512" path="controlForms[${controlStatus.index}].dataInput" />
              <form:errors path="controlForms[${controlStatus.index}].dataInput" cssClass="error"/>
            </c:otherwise>
          </c:choose>
        </td>
        <td>${controlForm.control.rbKey}</td>
      </tr>
    </c:forEach>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    
  </table>
  
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="ApplTable">
    <tr>
      <td><input name="Button" type="button" class="button1" value="Submit" onClick="addService()"/></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <br/>
</c:if>

<SCRIPT>
	refreshParent();
</SCRIPT>
	
</form:form>
