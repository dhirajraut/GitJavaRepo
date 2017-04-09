<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<form:form name="jobOrderCreateForm" method="POST" action="create_job.htm">
<input type="hidden" name="refreshing" value="false" />
<input type="hidden" name="_page" value="3" />

<div class="form" style="text-align:center;"">
  <table>
    <tr><td colspan="3"><h2>Create Job Order - Notes</h2></td></tr>
    <tr><td colspan="3"><form:errors cssClass="error"/></td></tr>
    <icb:list var="divisions">
      <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
    </icb:list>

    <icb:list var="divisionBu">
      <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
      <icb:item>${command.branch.businessUnit.name}</icb:item>
    </icb:list>
    
    <c:set var="jobMeta" value="${icbfn:objectMeta(icbfn:user().branch.businessUnit.organization.name, command.jobType, command.class)}" />
    <tr>
      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'jobStatus')}" />
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
      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'jobType')}" />
      <c:if test="${fieldMeta != null}">
        <td align="right"><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></td>
        <td align="left">
          <form:select path="${fieldMeta.name}" items="${icbfn:dropdown('jobType', null)}" itemLabel="name" itemValue="value"/>
        </td>
        <td><form:errors path="${fieldMeta.name}" cssClass="error"/></td>
      </c:if>
    </tr>
    <tr>
      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'currencyCD')}" />
      <c:if test="${fieldMeta != null}">
        <td align="right"><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></td>
        <td align="left">
          <form:input path="${fieldMeta.name}" size="30" />
        </td>
        <td><form:errors path="${fieldMeta.name}" cssClass="error"/></td>
      </c:if>
    </tr>
    <tr>
      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'type')}" />
      <c:if test="${fieldMeta != null}">
        <td align="right"><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></td>
        <td align="left">
          <form:input path="${fieldMeta.name}" size="30" />
        </td>
        <td><form:errors path="${fieldMeta.name}" cssClass="error"/></td>
      </c:if>
    </tr>
    <tr>
      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'paymentMethod')}" />
      <c:if test="${fieldMeta != null}">
        <td align="right"><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></td>
        <td align="left">
          <form:input path="${fieldMeta.name}" size="30" />
        </td>
        <td><form:errors path="${fieldMeta.name}" cssClass="error"/></td>
      </c:if>
    </tr>
    <tr>
      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'pymntTermsCd')}" />
      <c:if test="${fieldMeta != null}">
        <td align="right"><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></td>
        <td align="left">
          <form:input path="${fieldMeta.name}" size="30" />
        </td>
        <td><form:errors path="${fieldMeta.name}" cssClass="error"/></td>
      </c:if>
    </tr>

    <tr>
      <td colspan="3">
        <input type="submit" class="submitbutton" name="_target2" value="Prev" />
        <input type="submit" class="submitbutton" name="_target4" value="Next" />
        <input type="submit" class="submitbutton" name="_finish" value="Save" />
        <input type="submit" class="submitbutton" name="_cancel" value="Cancel" />
      </td>
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
