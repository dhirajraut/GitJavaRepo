<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script>
  function selectVesselType()
  {
    document.addJobContractVesselPopUpForm.operationType.value = "selectVesselType";
    document.addJobContractVesselPopUpForm.submit();
  }
</script>

<form:form name="addJobContractVesselPopUpForm" method="POST" action="add_job_contract_vessel_popup.htm"> 
  <input type="hidden" name="operationType" value="" />

  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <th><f:message key="vesselName"/>: 
        <form:input cssClass="inputBox" size="20" path="jobContractVessel.vesselName" />
        <form:errors path="jobContractVessel.vesselName" cssClass="redstar"/>
      </th>
      <th>
        <f:message key="vesselType"/>: &nbsp;
        <form:select id="sel2" onchange="javascript:selectVesselType();" cssClass="selectionBox" path="jobContractVessel.type" items="${command.vesselTypes}" itemLabel="rbValue" itemValue="vesselTypeId.vesselType" />
      </th>
      <th>&nbsp;</th>
    </tr>
    <c:forEach items="${command.controlExts}" var="controlExt" varStatus="controlExtStatus">
      <tr>
        <td valign="top">${controlExt.control.visibility}</td>
        <td>
          <c:set var="vesselFlag" value="${command.selectedVesselType.vesselFlag == 0}" />
          <c:choose>
            <c:when test="${controlExt.control.controlType == 'group'}">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>
                    <form:radiobuttons path="controlExts[${controlExtStatus.index}].dataInput" items="${controlExt.displayItems}" disabled="${vesselFlag}" />
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
      <td><input name="Submit3" type="button" class="button1" onclick="document.addJobContractVesselPopUpForm.submit();" value="Submit" /></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form:form>
