<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script>
  function selectProductGroup()
  {
    document.addInspectionServicePopUpForm.operationType.value = "selectProductGroup";
    document.addInspectionServicePopUpForm.submit();
  }
</script>

<form:form name="addInspectionServicePopUpForm" method="POST" action="add_inspection_service_popup.htm">  
  <input type="hidden" name="operationType" value="" />
  
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <th colspan="2"><f:message key="enterLotInfoFor"/> ${command.addJobContractProd.jobContractProd.jobContractVessel.vesselName}</th>
    </tr>
    <tr>
      <td width="25%" class="TDShadeBlue"><f:message key="name"/>: </td>
      <td class="TDShade">
        <form:input cssClass="inputBox" size="20" path="addJobContractProd.jobContractProd.jobProductName" />
        <form:errors path="addJobContractProd.jobContractProd.jobProductName" cssClass="redstar"/>
      </td>
    </tr>

    <tr>
      <td width="25%" class="TDShadeBlue"><f:message key="selecttheProductGroup"/>: </td>
      <td width="75%">
        <form:select id="sel2" onchange="javascript:selectProductGroup();" cssClass="selectionBox" path="addJobContractProd.jobContractProd.group" items="${command.productGroups}" itemLabel="rbValue" itemValue="productGroupId.groupId" />
      </td>
    </tr>
    <tr>
      <td width="15%" class="TDShadeBlue"><f:message key="applicableUOM"/>: </td>
      <td width="30%">
        <select name="select2" disabled="disabled" class="selectionBox">
          <option>N/A</option>
        </select>
      </td>
    </tr>

    <tr>
      <td colspan="2" style="padding:2px;">
        <table width="99%" border="0" cellpadding="0" cellspacing="0" class="secAppltable">
          <c:forEach items="${command.controlExts}" var="controlExt" varStatus="controlExtStatus">
            <tr>
              <td width="40%" valign="top">
                ${controlExt.control.visibility}
                <c:choose>
                  <c:when test="${controlExt.control.controlId.objectName == 'noOfUOM' and command.selectedProductGroup.uomFlag == 1}">
                    <span style="color:red;">*</span>
                  </c:when>
                  <c:when test="${controlExt.control.controlId.objectName == 'noOfUOM1' and command.selectedProductGroup.uomFlag == 2}">
                    <span style="color:red;">*</span>
                  </c:when>
                  <c:when test="${controlExt.control.controlId.objectName == 'noOfUOM2' and command.selectedProductGroup.uomFlag == 3}">
                    <span style="color:red;">*</span>
                  </c:when>
                </c:choose>
              </td>
              <td width="30%">
                <c:set var="additionalVesselPriceFlag" value="${(empty command.selectedProductGroup.additionalVesselPriceFlag) || (command.selectedProductGroup.additionalVesselPriceFlag == 0)}" />
                <c:choose>
                  <c:when test="${controlExt.control.controlType == 'group'}">
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <c:choose>
                          <c:when test="${controlExt.control.controlId.objectName == 'BOOLEAN_VAL_1'}">
                            <td>
                              <form:radiobuttons path="controlExts[${controlExtStatus.index}].dataInput" items="${controlExt.displayItems}" disabled="${additionalVesselPriceFlag}" />
                            </td>
                          </c:when>
                          <c:otherwise>
                            <td>
                              <form:radiobuttons path="controlExts[${controlExtStatus.index}].dataInput" items="${controlExt.displayItems}" />
                            </td>
                          </c:otherwise>
                        </c:choose>
                      </tr>
                    </table>
                  </c:when>
                  <c:otherwise>            
                    <form:input cssClass="inputBox" size="15"  maxlength="512" path="controlExts[${controlExtStatus.index}].dataInput" />
                    <form:errors path="controlExts[${controlExtStatus.index}].dataInput" cssClass="redstar"/>
                  </c:otherwise>
                </c:choose>
              </td>
              <td>${controlExt.control.rbKey}</td>
            </tr>
          </c:forEach>
          <tr>
            <td width="40%" valign="top"><f:message key="notchargethislot"/>:</td>
            <td width="30%">
              <form:checkbox path="addJobContractProd.jobContractProd.notChargeInd" />
            </td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="3">
              <input name="Submit" type="submit" class="button1" value="Ok" />
            </td>
          </tr>
        </table>    
      </td>
    </tr>
  </table>
</form:form>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
  <IMG src=" images/fake-alpha-999.gif"> 
</div>

