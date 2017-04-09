<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ page isErrorPage="true" %>

<form:form name="logJobOrder" method="POST" action="order_log.htm">
<input type="hidden" name="refreshing" value="false" />
<div class="form" style="text-align:center;"">
  <table>
    <tr><td colspan="3"><h2><f:message key="logSearch"/></h2></td></tr>
    <tr><td colspan="3"><form:errors cssClass="error"/></td></tr>

    <c:set var="jobMeta" value="${icbfn:objectMeta(icbfn:user().branch.businessUnit.organization.name, command.jobType, command.class)}" />
    <tr>
      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'captureId')}" />
      <c:if test="${fieldMeta != null}">
        <td align="right"><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></td>
        <td align="left">
          <form:input path="${fieldMeta.name}" size="30" />
        </td>
        <td><form:errors path="${fieldMeta.name}" cssClass="error"/></td>
      </c:if>
    </tr>
    <tr>
      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'statusCode')}" />
      <c:if test="${fieldMeta != null}">
        <td align="right"><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></td>
        <td align="left">
          <form:select path="${fieldMeta.name}" items="${icbfn:dropdown('status', null)}" itemLabel="name" itemValue="value"/>
        </td>
        <td><form:errors path="${fieldMeta.name}" cssClass="error"/></td>
      </c:if>
    </tr>
    <tr>
      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'branch.businessUnit.name')}" />
      <c:if test="${fieldMeta != null}">
        <td align="right"><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></td>
        <td align="left">
          <form:select path="${fieldMeta.name}" items="${icbfn:dropdown('businessUnit', divisions)}" itemLabel="name" itemValue="value" />
        </td>
        <td><form:errors path="${fieldMeta.name}" cssClass="error"/></td>
      </c:if>
    </tr>
    <tr>
      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'branch.name')}" />
      <c:if test="${fieldMeta != null}">
        <td align="right"><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></td>
        <td align="left">
          <form:select path="${fieldMeta.name}" items="${icbfn:dropdown('branch', divisionBu)}" itemLabel="name" itemValue="value"/>
        </td>
        <td><form:errors path="${fieldMeta.name}" cssClass="error"/></td>
      </c:if>
    </tr>
    <tr>
      <td colspan="3"><input name="Search" type="submit" class="submitbutton" value="Search" /></td>
    </tr>
  </table>
</div>
</form:form>

<ajax:select
  baseUrl="business_unit.htm"
  source="branch.businessUnit.name"
  target="branch.name"
  parameters="branch.businessUnit.name={branch.businessUnit.name}"
  parser="new ResponseXmlParser()" />
