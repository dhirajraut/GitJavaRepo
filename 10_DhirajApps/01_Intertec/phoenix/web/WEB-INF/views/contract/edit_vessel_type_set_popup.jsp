<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
  function onEditDescription(vesselTypeIndex)
  {
    document.editVesselTypeSetPopUpForm.operation.value = "editDescription";
    document.editVesselTypeSetPopUpForm.vesselTypeIndex.value = vesselTypeIndex;
    document.editVesselTypeSetPopUpForm.submit();    
  }

  function onEditDescriptionAddNew()
  {
    document.editVesselTypeSetPopUpForm.operation.value = "editDescriptionAddNew";
    document.editVesselTypeSetPopUpForm.submit();    
  }

  function onEditDescriptionDelete(rbExtIndex)
  {
    document.editVesselTypeSetPopUpForm.operation.value = "editDescriptionDelete";
    document.editVesselTypeSetPopUpForm.rbExtIndex.value = rbExtIndex;
    document.editVesselTypeSetPopUpForm.submit();    
  }
  
  function onSubmit(operation)
  {
    document.editVesselTypeSetPopUpForm.operation.value = operation;
    document.editVesselTypeSetPopUpForm.submit();
  }

  function selectAll(myPrefix, selectAllElement)
  {
    var elementList = document.forms[0].elements;
    for(i=0;i<elementList.length;i++)
    {
      if(elementList[i].type == "checkbox")
      {
        var checkBoxName = elementList[i].name;
        var myPosition = checkBoxName.indexOf(myPrefix);
        if(myPosition >= 0)
        {
          if(!elementList[i].disabled)
          {
            if(selectAllElement.checked) elementList[i].checked=true;
            else elementList[i].checked=false;
          }
        }
      }
    }
  }
</script>

<form:form name="editVesselTypeSetPopUpForm" method="POST" action="edit_vessel_type_set_popup.htm"> 
  <input type="hidden" name="operation" value="" />
  <input type="hidden" name="vesselTypeIndex" value="" />
  <input type="hidden" name="rbExtIndex" value="" />
  
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <c:choose>
    <c:when test="${command.editRBExt == null}">
    
      <table width="100%" border="0" align="center" class="InnerApplTable">
        <tr>
          <td valign="middle"  colspan="2">
            <table border="0" cellpadding="5" cellspacing="0" style="margin:15px;">
              <tr>
                <td>
                  <span style="font:bold;"><spring:message code="BaseVesselTypeSet" />:</span>
                  <form:select onchange="javascript:onSubmit('changeBaseVesselTypeSet');" cssClass="selectionBox" path="baseVesselTypeSetName" items="${icbfn:dropdown('vesselTypeSetNamesDropdown',null)}" itemLabel="name" itemValue="value" />
                </td>
                <td>
                  <strong><spring:message code="BeginDate" />:</strong>
                  ${command.cfgContract.cfgContractId.beginDate}
                </td>
                <td>
                  <strong><spring:message code="EndDate" />:</strong>
                  ${command.cfgContract.endDate}
                </td>
              </tr>
            </table>
          </td> 
        </tr>
      </table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
        <tr>
          <td colspan="3">
            <c:set var="labelSubmit">
              <spring:message code="Submit" />
            </c:set>
            <c:set var="labelReset">
              <spring:message code="Reset" />
            </c:set>
            <input name="Submit3" type="button" class="button1" onclick="javascript:onSubmit('submit');" value="${labelSubmit}" />
            <input name="Submit4" type="button" class="button1" onclick="javascript:onSubmit('reset');" value="${labelReset}" />
          </td>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <th>
            <form:checkbox path="checkAll" onclick="javascript:selectAll('vesselTypeExts', this)"/>
          </th>
          <th><spring:message code="VesselType" /></th>
          <th><spring:message code="DefaultDescription" /></th>
          <th><spring:message code="CustomDescription" /></th>
          <th>&nbsp;</th>
        </tr>
        <c:forEach items="${command.vesselTypeExts}" var="pgExt" varStatus="pgExtStatus">
          <tr>
            <td width="5%" valign="top">
              <form:checkbox path="vesselTypeExts[${pgExtStatus.index}].selected" />
            </td>
            <td width="10%">${pgExt.vesselType.vesselTypeId.vesselType}</td>
            <td>
              <c:if test="${pgExt.rbExt.rb != null}">
                ${pgExt.rbExt.rb.rbValue}
              </c:if>
            </td>
            <td>
              <c:if test="${pgExt.rbExt.notesRb != null}">
                ${pgExt.rbExt.notesRb.rbValue}
              </c:if>
              <c:if test="${pgExt.newlyAdded}">
                <form:errors path="vesselTypeExts[${pgExtStatus.index}].rbExt.notesRb" cssClass="redstar"/>  
              </c:if>
            </td>
            <td>
              <a href="#" onclick="javascript:onEditDescription('${pgExtStatus.index}');">Edit</a>
            </td>
          </tr>
        </c:forEach>
      </table>
      
    </c:when>
    <c:otherwise>

      <div>
        Price Book Text: 
        <c:if test="${command.editRBExt.rbExt.rb != null}">
          ${command.editRBExt.rbExt.rb.rbValue}
        </c:if>
      </div>

      <table width="96%" border="0" cellpadding="0" cellspacing="0" style="width:99%;">
        <tr>
          <td valign="top">      
            <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
              <tbody>
                <tr bgcolor=#ffffff>
                  <th colspan=3 width="65%">Custom Text: </th>
                  <th width="35%" >&nbsp;</th>
                  <th width="5%" bgcolor="#ffffff" style="text-align:right">
                    <c:if test="${fn:length(command.editRBExt.rbExtList) == 0}">
                      <a href="#"  onClick="onEditDescriptionAddNew();">
                        <img src="images/add.gif" alt="Add New" width="14" height="14" border="0" />
                      </a>
                    </c:if>                    
                  </th>
                </tr>

                <c:forEach items="${command.editRBExt.rbExtList}" var="rbExt" varStatus="rbExtStatus">
                  <tr>
                    <td colspan="5" class="TDShade" style="padding:2px;">
                      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                        <tr>
                          <td class="TDShadeBlue">Begin Date:</td>
                          <td class="TDShadeBlue">
                            <form:input path="editRBExt.rbExtList[${rbExtStatus.index}].notesRb.rbId.beginDate" cssClass="inputBox" size="10" disabled="true"/>
                            <form:errors path="editRBExt.rbExtList[${rbExtStatus.index}].notesRb.rbId.beginDate" cssClass="redstar"/>                                    
                          </td>
                          <td class="TDShadeBlue">EndDate: </td>
                          <td class="TDShadeBlue">
                            <form:input path="editRBExt.rbExtList[${rbExtStatus.index}].notesRb.rbId.endDate" cssClass="inputBox" size="10" disabled="true"/>
                            <form:errors path="editRBExt.rbExtList[${rbExtStatus.index}].notesRb.rbId.endDate" cssClass="redstar"/>                                    
                          </td>
                          <td width="5%">
                            <a href="#"  onClick="onEditDescriptionDelete('${rbExtStatus.index}');">
                              <img src="images/delete.gif" alt="Delete Row" width="14" height="14" border="0" />
                            </a>
                          </td>
                        </tr>
                        <tr>
                          <td class="TDShadeBlue">Custom Text:</td>
                          <td class="TDShadeBlue" colspan="4">
                            <form:textarea path="editRBExt.rbExtList[${rbExtStatus.index}].notesRb.rbValue" cols="80" rows="4" />
                            <form:errors path="editRBExt.rbExtList[${rbExtStatus.index}].notesRb.rbValue" cssClass="redstar"/>                                    
                          </td>
                        </tr>
                      </table>          
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td>
            <input name="Submit1" type="button" class="button1" onclick="javascript:onSubmit('editDescriptionSubmit');" value="Save" />      
            <input name="Submit3" type="button" class="button1" onclick="javascript:onSubmit('editDescriptionCancel');" value="Cancel" />      
          </td>
        </tr>
      </table>
    </c:otherwise>
  </c:choose>      
</form:form>
