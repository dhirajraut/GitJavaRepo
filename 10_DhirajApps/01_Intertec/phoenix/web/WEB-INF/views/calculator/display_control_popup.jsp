<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
  function onSelectService()
  {
    document.displayControlPopUpForm.operation.value = "selectService";
    document.displayControlPopUpForm.submit();
  }
  function onSubmit(operation)
  {
    document.displayControlPopUpForm.operation.value = operation;
    document.displayControlPopUpForm.submit();
  }
</script>

<form:form name="displayControlPopUpForm" method="POST" action="display_control_popup.htm"> 
  <input type="hidden" name="operation" value="" />

  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <c:if test="${command.selectedServiceIndex == null}">
    <table width="100%" border="0" align="center" class="InnerApplTable">
      <tr>
        <td valign="middle"  colspan="2">
          <table border="0" cellpadding="5" cellspacing="0" style="margin:15px;">
            <tr>
              <td><strong>${command.selectedServiceTypeExt.rbValue}</strong></td>
              <td>
                <c:set var="serviceNameLabel"><spring:message code="Select a service" /></c:set>
                <form:select cssClass="selectionBox" path="serviceName" onchange="javascript:onSelectService()">
                  <form:option value="" label="${serviceNameLabel}" />
                  <form:options items="${icbfn:sortServices(command.selectedServiceTypeExt.services)}" itemLabel="visibility" itemValue="serviceId.serviceName" />
                </form:select>
              </td>
            </tr>
          </table>
        </td> 
      </tr>
    </table>
  </c:if>

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <th>${command.selectedService.visibility}</th>
      <th>&nbsp;</th>
      <th>&nbsp;</th>
    </tr>
    <c:forEach items="${command.controlExts}" var="controlExt" varStatus="controlExtStatus">
      <tr>
        <td width="40%" valign="top">${controlExt.control.visibility}</td>
        <td width="30%">
          <c:choose>
            <c:when test="${controlExt.control.controlType == 'group'}">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>
                    <form:radiobuttons path="controlExts[${controlExtStatus.index}].dataInput" items="${controlExt.displayItems}"/>
                  </td>
                </tr>
              </table>
            </c:when>
            <c:otherwise>            
              <form:input cssClass="inputBox" size="15"  maxlength="512" path="controlExts[${controlExtStatus.index}].dataInput" />
              <form:errors path="controlExts[${controlExtStatus.index}].dataInput" cssClass="error"/>
            </c:otherwise>
          </c:choose>
        </td>
        <td>${controlExt.control.rbKey}</td>
      </tr>
    </c:forEach>
    <tr>
      <td>
        <c:set var="labelSubmitandReturn">
          <spring:message code="Submit and Return" />
        </c:set>
        <c:set var="labelSubmit">
          <spring:message code="Submit" />
        </c:set>
        <c:set var="labelReturn">
          <spring:message code="Return" />
        </c:set>
        <input name="Submit3" type="button" class="button1" onclick="javascript:onSubmit('submitAndReturn');" value="${labelSubmitandReturn}" />      
        <c:if test="${command.selectedServiceIndex == null}">
          <input name="Submit3" type="button" class="button1" onclick="javascript:onSubmit('submit');" value="${labelSubmit}" />
          <input name="Submit3" type="button" class="button1" onclick="javascript:onSubmit('return');" value="${labelReturn}" />      
        </c:if>
      </td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form:form>
