<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
  function doSubmit(operation)
  {
    document.chooseLevelsToApplyPopUpForm.operation.value = operation;
    document.chooseLevelsToApplyPopUpForm.submit();
  } 
  
  function doSelect(selectIndex, operation)
  {
    document.chooseLevelsToApplyPopUpForm.selectIndex.value = selectIndex;
    document.chooseLevelsToApplyPopUpForm.operation.value = operation;
    document.chooseLevelsToApplyPopUpForm.submit();
  }  
</script>

<form:form name="chooseLevelsToApplyPopUpForm" method="POST" action="choose_apply_levels_popup.htm"> 
  <input type="hidden" name="operation" value="" />
  <input type="hidden" name="selectIndex" value="" />

  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table width="96%" border="0" cellpadding="0" cellspacing="0" style="width:99%;">
    <tr>
      <td valign="top">      
        <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
          <tbody>
            <tr bgcolor=#ffffff>
              <th colspan=3 width="65%">Service Levels </th>
              <th width="35%" >&nbsp;</th>
              <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;&nbsp;</th>
            </tr>

            <tr>
              <td colspan="5" class="TDShade" style="padding:2px;">
                <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                  <tr>
                    <th class="TDShadeBlue">&nbsp;</th>
                    <th class="TDShadeBlue">Service Level Id</th>
                    <th class="TDShadeBlue">Level Toggle</th>
                    <th class="TDShadeBlue">Master List</th>
                    <th class="TDShadeBlue">Apply to This Level?</th>
                  </tr>
                  <c:forEach items="${command.serviceLevelList}" var="serviceLevel" varStatus="serviceLevelStatus">
                    <tr>
                      <td class="TDShadeBlue">&nbsp;</td>
                      <td class="TDShadeBlue">
                        ${serviceLevel.serviceLevel}
                      </td>
                      <td class="TDShadeBlue">${serviceLevel.serviceVersionExtList[command.serviceLevel.activeServiceVersionIndex].rateApplied}</td>
                      <td class="TDShadeBlue">
                        <c:choose>
                          <c:when test="${serviceLevel.cfgContractId == 'MASTER'}">Y</c:when>
                          <c:otherwise>N</c:otherwise>
                        </c:choose>
                      </td>
                      <td class="TDShadeBlue">
                        <c:choose>
                          <c:when test="${serviceLevel.serviceVersionExtList[command.serviceLevel.activeServiceVersionIndex].rateApplied == 'T' or serviceLevel == command.serviceLevel}">
                            ${serviceLevel.serviceVersionExtList[command.serviceLevel.activeServiceVersionIndex].applyToThisLevel}
                          </c:when>
                          <c:otherwise>
                            <form:select onchange="doSelect('${serviceLevelStatus.index}', 'doSelect')" cssClass="selectionBox" path="serviceLevelList[${serviceLevelStatus.index}].serviceVersionExtList[${command.serviceLevel.activeServiceVersionIndex}].applyToThisLevel">
                              <form:option value="No" label="No" />
                              <form:option value="Yes" label="Yes" />
                            </form:select>
                          </c:otherwise>
                        </c:choose>
                      </td>
                    </tr>
                  </c:forEach>
                </table>          
              </td>
            </tr>
          </tbody>
        </table>
      </td>
    </tr>
    <tr>
      <td>
        <input name="Submit2" type="button" class="button1" onclick="javascript:doSubmit('submitAndReturn');" value="OK" />      
      </td>
    </tr>
  </table>

</form:form>
