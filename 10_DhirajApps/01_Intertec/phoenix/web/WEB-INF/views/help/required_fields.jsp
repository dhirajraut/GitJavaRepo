<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<form:form name="requiredFieldsForm" method="POST" action="required_fields.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<c:if test="${command.rfs != null}">
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
	<tr>
		<th width="200" class="TDShade"><f:message key="fieldName"/></th>
		<th width="30" class="TDShade"><f:message key="INSP"/></th>
		<th width="30" class="TDShade"><f:message key="MAR"/></th>
		<th width="30" class="TDShade"><f:message key="FST"/></th>
		<th width="30" class="TDShade"><f:message key="OUT"/></th>
		<th class="TDShade">&nbsp;</th>
	</tr>
	<c:forEach items="${command.rfs}" var="row" varStatus="rowStatus">
		<c:choose>
			<c:when test="${rowStatus.index%2==0}">
				<tr style="background-color:#FFFFFF;">
			</c:when>
			<c:otherwise>
				<tr style="background-color:#e7eeff;">
			</c:otherwise>
		</c:choose>
		<td class="TDShade">${row.requiredFieldId.className}.${row.requiredFieldId.fieldName}</td>
		<td class="TDShade" align="center"><B>${row.insp?'*':''}</B></td>
		<td class="TDShade" align="center"><B>${row.mar?'*':''}</B></td>
		<td class="TDShade" align="center"><B>${row.fst?'*':''}</B></td>
		<td class="TDShade" align="center"><B>${row.out?'*':''}</B></td>
		<td class="TDShade" align="center">&nbsp;</td>
	</tr>    
	</c:forEach>
</table>
</c:if>
</form:form>

