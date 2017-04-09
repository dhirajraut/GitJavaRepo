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
    document.contractEditForm.operationType.value = "editRates";
    document.contractEditForm.operation.value = operation;
    document.contractEditForm.submit();
  }  
  
  function switchPbOrContract()
  {      
    document.contractEditForm.operationType.value = "editRates";
    document.contractEditForm.operation.value = 'switchPbOrContract';
    document.contractEditForm.submit();
  }  

  function doChangeDate()
  {
    document.contractEditForm.operationType.value = "editRates";
    document.contractEditForm.operation.value = "changeDate";
    document.contractEditForm.submit();
  }  
  
  function onKeyPressed()
  {
    var keyCode = event.keyCode;
    switch(keyCode)
    {
      case 13:
        doChangeDate();
        break;
    }
  }  

  function doSelectVersion(versionIndex)
  {
    document.contractEditForm.operationType.value = "editRates";
    document.contractEditForm.activeServiceVersion.value = versionIndex;
    document.contractEditForm.operation.value = "selectVersion";
    document.contractEditForm.submit();
  }  

  function doDeleteRate(contractRateIndex)
  {
    document.contractEditForm.operationType.value = "editRates";
    document.contractEditForm.selectedContractRateIndex.value = contractRateIndex;
    document.contractEditForm.operation.value = "deleteRate";
    document.contractEditForm.submit();
  }  

  function popChooseApplyLevels()
  {
    popup_show('chooseapplylevels', 'chooseapplylevels_drag', 'chooseapplylevels_exit', 'screen-corner', 40, 80);
    hideIt();
    popupboxenable();

    document.getElementById("chooseapplylevelsbox").src="choose_apply_levels_popup.htm";             
    document.getElementById("chooseapplylevelsbox").height = "300px";
  }  

  function popEditQuestionNotes(rbIndex)
  {
    popup_show('editquestionnotes', 'editquestionnotes_drag', 'editquestionnotes_exit', 'screen-corner', 40, 80);
    hideIt();
    popupboxenable();

    document.getElementById("editquestionnotesbox").src="edit_question_notes_popup.htm?rbIndex=" + rbIndex;             
    document.getElementById("editquestionnotesbox").height = "300px";
  }  

  function selectAll(myIndex, selectAllElement)
  {
    var elementList = document.forms[0].elements;
    for(i=0;i<elementList.length;i++)
    {
      if(elementList[i].type == "checkbox")
      {
        var checkBoxName = elementList[i].name;
        var prefix = 'serviceRates.serviceLevel.serviceVersionExtList[' + myIndex + ']';
        var myPosition = checkBoxName.indexOf(prefix);
        if(myPosition >= 0)
        {
          if((checkBoxName == (prefix + ".hide"))
            || (checkBoxName == (prefix + ".vesselChecked"))
            || (checkBoxName == (prefix + ".productGroupChecked"))
          ) 
          {
            continue;
          }
          
          if(selectAllElement.checked) elementList[i].checked=true;
          else elementList[i].checked=false;
        }
      }
    }
  }

  function doAddServiceRate(serviceRateIndex)
  {
    document.contractEditForm.operationType.value = "editRates";
    document.contractEditForm.operation.value = "addServiceRateWithIndex";
    document.contractEditForm.selectedContractRateIndex.value = serviceRateIndex;
    document.contractEditForm.submit();
  }  
</script>

<input type="hidden" name="operation" value="" />
<input type="hidden" name="activeServiceVersion" value="${command.serviceRates.serviceLevel.activeServiceVersionIndex}" />
<input type="hidden" name="selectedContractRateIndex" value="" />

<icb:list var="zoneListParams">
  <icb:item>${command.contract.contractCode}</icb:item>
  <icb:item>${command.contract.workingPB}</icb:item>
  <icb:item>NotIncludeAll</icb:item>
</icb:list>
<icb:list var="zoneListDropDownItems" items="${icbfn:dropdown('zoneListDropDown', zoneListParams)}" />

<icb:list var="contractList">
  <icb:item>${command.contract.contractCode}</icb:item>
</icb:list>
<icb:list var="contractListWithPB">
  <icb:item>${command.contract.contractCode}</icb:item>
  <icb:item>${command.contract.workingPB}</icb:item>
</icb:list>
<icb:list var="zoneListDropDownItemsWithAll" items="${icbfn:dropdown('zoneListDropDown', contractListWithPB)}" />

<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tr>
    <th width="70%">
      <f:message key="contractid"/><strong>:</strong> ${command.contract.contractCode} 
      <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
      <f:message key="description"/><strong>:</strong> ${command.contract.description}
    </th>
    <th width="25%">
      <span style="text-align:right">
        <f:message key="status"/><strong>:</strong> 
        <f:message key="${command.contract.status}" /> | &nbsp;
        <f:formatDate pattern="${userDateFormat}" value="${command.contract.statusDate}" />
      </span>
    </th>
    <th width="5%" style="text-align:right">&nbsp;<!-- <a href="#"><img src="../images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --></th>
  </tr>

  <tr>
    <td colspan="3" style="padding:2px;" class="TDShadeBlue">
      <table width="96%" border="0" cellpadding="0" cellspacing="0" style="width:99%;">
        <tr>
          <td valign="top">


            <!-- TABS CONTENTS -->
            <div id="tabcontainer5">
              <div id="tabnav5">
                <ul>
                  <li><a rel="tab51" style="cursor:hand;"><span>Service Rates  </span></a></li>
                  <li><a rel="tab52" style="cursor:hand;"><span>Pricebook Rates</span></a></li>
                </ul>
              </div>
              <!-- Sub Menus container. Do not remove -->
              <div id="tab_inner5">
                <!-- ------------------------- TAB 51 CONTAINER ----------------------------------------- -->
                <div id="tab51" class="innercontent" >
                  <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                    <tbody>
                      <tr bgcolor=#ffffff>
                        <th colspan=3 width="65%">Service Rates </th>
                        <th width="35%" >&nbsp; </th>
                        <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                      </tr>

                      <tr>
                        <td colspan="5" class="TDShadeBlue" style="padding:2px;">
                          <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                            <tr>
                              <th>${command.serviceRates.serviceLevel.serviceExt.description} - Applicable Levels </th>
                            </tr>
                            <tr>
                              <td width="25%" class="TDShadeBlue">
                                <table>
                                  <tr>
                                    <c:forEach items="${command.serviceRates.serviceLevelList}" var="serviceLevel" varStatus="serviceLevelStatus">
                                      <td class="TDShadeBlue">
                                        <c:choose>
                                          <c:when test="${serviceLevel.serviceLevel == command.serviceRates.serviceLevel.serviceLevel}">
                                            <spring:message code="${serviceLevel.serviceLevel}" text="${serviceLevel.serviceLevel}" />
                                          </c:when>
                                          <c:otherwise>
                                            <a href="#" onClick="saveAndSwitchServiceLevel('${serviceLevel.serviceName}', '${serviceLevel.serviceLevel}', '${serviceLevel.cfgContractId}', '${serviceLevel.expressionId}');">
                                              <spring:message code="${serviceLevel.serviceLevel}" text="${serviceLevel.serviceLevel}" />
                                            </a>
                                          </c:otherwise>
                                        </c:choose>
                                      </td>
                                    </c:forEach>
                                  </tr>
                                </table>
                              </td>  
                            </tr>
                          </table>          
                        </td>
                      </tr>
                      <tr>
                        <td colspan="5" class="TDShade" style="padding:1px;">
                          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                            <tr>
                              <th width="55%">Questions</th>
                              <th width="35%">Question Note </th>
                              <th width="10%" style="text-align:center;">Edit Note </th>
                            </tr>
                            <c:forEach items="${command.serviceRates.serviceLevel.questionRbExtList}" var="rbExt" varStatus="rbExtStatus">
                              <tr>
                                <td>${rbExt.rb.rbValue}</td>
                                <td>
                                  <c:choose>
                                    <c:when test="${rbExt.notesRb != null}">${rbExt.notesRb.rbValue}</c:when>
                                    <c:otherwise>&nbsp;</c:otherwise>
                                  </c:choose>
                                </td>
                                <td style="text-align:center;">
                                  <a href="#" onclick="popEditQuestionNotes('${rbExtStatus.index}');">
                                    <img src="images/icoeditsmall.gif" width="13" height="12">
                                  </a>
                                </td>
                              </tr>
                            </c:forEach>
                          </table>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="5" class="TDShade" style="padding:1px;"><table width="100%" border="0" cellpadding="0" cellspacing="0"  style="margin:1px; width:100%">
                          <tr>
                            <td width="35%" style="padding:1px;">
                              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                                <tr>
                                  <th colspan="4">Choose Version </th>
                                </tr>
                                <c:forEach items="${command.serviceRates.serviceLevel.serviceVersionExtList}" var="serviceVersionExt" varStatus="serviceVersionExtStatus">
                                  <tr>
                                    <td>
                                      <c:choose>
                                        <c:when test="${serviceVersionExtStatus.index == command.serviceRates.serviceLevel.activeServiceVersionIndex}">
                                          <f:formatDate value="${serviceVersionExt.serviceVersion.serviceVersionId.beginDate}" pattern="${icbfn:userDateFormat()}" /> to <f:formatDate value="${serviceVersionExt.serviceVersion.endDate}" pattern="${icbfn:userDateFormat()}" />
                                        </c:when>
                                        <c:otherwise>
                                          <a href="#" onClick="doSelectVersion('${serviceVersionExtStatus.index}')" />                                
                                            <f:formatDate value="${serviceVersionExt.serviceVersion.serviceVersionId.beginDate}" pattern="${icbfn:userDateFormat()}" /> to <f:formatDate value="${serviceVersionExt.serviceVersion.endDate}" pattern="${icbfn:userDateFormat()}" />
                                          </a>
                                        </c:otherwise>
                                      </c:choose>
                                      <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${serviceVersionExtStatus.index}].serviceVersion.serviceVersionId.beginDate" cssClass="redstar"/>                                    
                                    </td>
                                    <td width="20">${serviceVersion.levelToggle}</td>
                                  </tr>
                                </c:forEach>
                              </table>
                            </td>
                            <td width="15%" align="center" style="background-color:#F9F9F9"><a href="#" onclick="doSubmit('addVersion');">Add Version </a></td>
                            <td width="50%" style="padding:1px;"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                              <tr>
                                <th colspan="2">Selected Version </th>
                              </tr>
                              <c:if test="${command.serviceRates.serviceLevel.activeServiceVersionIndex >= 0}">
                                <tr>
                                  <td>Begin Date:<span class="redstar">*</span> 
                                    <form:input onchange="doChangeDate();" onkeypress="onKeyPressed();" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].serviceVersion.serviceVersionId.beginDate" cssClass="inputBox" size="10"/>
                                    <a href="#" onClick="displayCalendar(document.contractEditForm['serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].serviceVersion.serviceVersionId.beginDate'],'${icbfn:userDateFormat()}',this)">
                                      <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                                    </a>
                                    <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].serviceVersion.serviceVersionId.beginDate" cssClass="redstar"/>                                    
                                  </td>
                                  <td>End Date:<span class="redstar">*</span> 
                                    <form:input onchange="doChangeDate();" onkeypress="onKeyPressed();" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].serviceVersion.endDate" cssClass="inputBox" size="10"/>
                                    <a href="#" onClick="displayCalendar(document.contractEditForm['serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].serviceVersion.endDate'],'${icbfn:userDateFormat()}',this)">
                                      <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                                    </a>
                                    <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].serviceVersion.endDate" cssClass="redstar"/>                                    
                                  </td>
                                </tr>
                              </c:if>
                            </table></td>
                          </tr>
                        </table></td>
                      </tr>
                      <tr>
                        <td colspan="5" class="TDShade" style="padding:2px;"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                          <tr>
                            <th colspan="2">Rate Applicability </th>
                          </tr>
                          <tr>
                            <td width="50%">
                              <c:if test="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableApplyAll}">                                
                                <form:radiobutton path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].rateApplied" value="A" label="Apply to All Levels" onclick="javascript:doSubmit('toggleLevel');"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <c:if test="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].rateApplied == 'A'}">
                                  <a href="#" onclick="popChooseApplyLevels();">Choose Levels to Apply</a>
                                </c:if>
                              </c:if>
                            </td>
                            <td width="50%"><form:radiobutton path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].rateApplied" value="T" label="Price this Level Only" onclick="javascript:doSubmit('toggleLevel')"/></td>
                          </tr>
                        </table></td>
                      </tr>
                      <c:if test="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].rateApplied != null and command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].rateApplied != 'U'}">                                
                        <tr>
                          <td colspan="5" class="TDShade" style="padding:2px;"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                            <tr>
                              <th>
                                Zones
                                <c:if test="${(command.serviceRates.serviceLevel.cfgContractId == 'MASTER') or (command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].allZoneCeExtForContract.contractExpression.contractComponent == 'CONTR')}">
                                  &nbsp;&nbsp;
                                  <img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
                                  &nbsp;&nbsp;
                                  <c:choose>
                                    <c:when test="${not command.displayAll}">
                                      <a href="#" onclick="javascript:doSubmit('showAll');" style="cursor:hand;"><span>Show All</span></a>
                                    </c:when>
                                    <c:otherwise>
                                      <a href="#" onclick="javascript:doSubmit('showTabs');" style="cursor:hand;"><span>Show Tabs</span></a>
                                    </c:otherwise>
                                  </c:choose>
                                </c:if>
                              </th>
                            </tr>

                            <tr>
                              <td>
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                                  <tr>
                                    <td width="35%">Zone: All Zones Discount/Contract Price </td>
                                    <td width="50%">
                                      <c:if test="${command.serviceRates.serviceLevel.cfgContractId != 'MASTER'}">
                                        <form:select onchange="switchPbOrContract(this);" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allZoneCeExtForContract.contractExpression.contractComponent">
                                          <form:option value="CONTR" label="Contract" />
                                          <form:option value="PB" label="Pricebook" />
                                        </form:select>                              
                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allZoneCeExtForContract.contractExpression.contractComponent" cssClass="redstar"/>                                    
                                        &nbsp;&nbsp;
                                        Discount Pct:
                                        <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allZoneCeExtForContract.contractExpression.cfgDiscountPercent" cssClass="inputBox" size="6" disabled="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].allZoneCeExtForContract.contractExpression.contractComponent == 'CONTR'}"/>
                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allZoneCeExtForContract.contractExpression.cfgDiscountPercent" cssClass="redstar"/>                                    
                                        &nbsp;&nbsp;
                                        <form:checkbox path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].hide" />Hide
                                      </c:if>
                                      <c:if test="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableVessel}">
                                        &nbsp;&nbsp;
                                        <form:checkbox path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].vesselChecked" />Vessel
                                      </c:if>
                                      <c:if test="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableProductGroup}">
                                        &nbsp;&nbsp;
                                        <form:checkbox path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].productGroupChecked" />Prod Group
                                      </c:if>
                                    </td>
                                    <c:if test="${(command.serviceRates.serviceLevel.cfgContractId == 'MASTER') or (command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].allZoneCeExtForContract.contractExpression.contractComponent == 'CONTR')}">
                                      <td width="15%">
                                        <input name="addNewRate" type="button" class="button1" onclick="javascript:doSubmit('addNewRate');" value="Add New Rate" />                                    
                                      </td>
                                    </c:if>
                                  </tr>
                                </table>
                              </td>
                            </tr>
                            <c:if test="${(command.serviceRates.serviceLevel.cfgContractId == 'MASTER') or (command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].allZoneCeExtForContract.contractExpression.contractComponent == 'CONTR')}">
                              <tr>
                                <td style="background-color:#F7F7F7;">
                                  <c:if test="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableVessel or command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableProductGroup}">
                                    <icb:list var="vesselTypeDropDown" items="${icbfn:dropdown('vesselTypeByContractIdDropdown', contractList)}" />
                                    <icb:list var="productGroupDropdown" items="${icbfn:dropdown('productGroupByContractIdDropdown', contractList)}" />
                                  </c:if>
                                  <c:choose>
                                    <c:when test="${not command.displayAll}">
                                      <!-- TABS CONTENTS -->
                                      <div id="tabcontainer4"  style="background-color:#F7F7F7;">
                                        <div id="tabnav4" style="background-color:#F7F7F7;">
                                          <ul>
                                            <li><a rel="tab41" style="cursor:hand;"><span>Base Rate Data   </span></a></li>
                                            <li><a rel="tab42" style="cursor:hand;"><span>Price Range Data </span></a></li>
                                            <c:if test="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableVessel or command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableProductGroup}">
                                              <li><a rel="tab43" style="cursor:hand;"><span>Vessel/Product Group</span></a></li>
                                            </c:if>
                                            <li><a rel="tab44" style="cursor:hand;"><span>Hidden Fields</span></a></li>
                                          </ul>
                                        </div>
                                        <!-- Sub Menus container. Do not remove -->
                                        <div id="tab_inner4">
                                          <!-- ------------------------- TAB 41 CONTAINER ----------------------------------------- -->
                                          <div id="tab41" class="innercontent" >
                                            <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                              <tbody>
                                                <tr bgcolor=#ffffff>
                                                  <th width="65%">Base Rate Data
                                                    &nbsp;&nbsp;&nbsp;
                                                    <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                                                    &nbsp;&nbsp;&nbsp;
                                                      <form:input cssClass="inputBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allBeginDate" size="10" maxlength="12" />
                                                      <a href="#" onClick="displayCalendar(document.forms[0].elements['serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allBeginDate'],'${userDateFormat}',this)"> 
                                                        <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                                                      </a>
                                                      <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allBeginDate" cssClass="redstar"/>
                                                      &nbsp;&nbsp;&nbsp;
                                                      <form:input cssClass="inputBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allEndDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                                                      <a href="#" onClick="displayCalendar(document.forms[0].elements['serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allEndDate'],'${userDateFormat}',this)"> 
                                                        <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                                                      </a>
                                                      <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allEndDate" cssClass="redstar"/>
                                                      <a href="#" onClick="javascript:doSubmit('applyDatesForAllRates');">Apply Dates</a>
                                                      &nbsp;&nbsp;&nbsp;
                                                      <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                                                      &nbsp;&nbsp;&nbsp;
                                                      <a href="#" onClick="javascript:doSubmit('deleteSelectedRates');">Delete Selected</a>
                                                  </th>
                                                  <th width="35%" >&nbsp; </th>
                                                  <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                                </tr>

                                                <tr>
                                                  <td colspan="3" class="TDShade" style="padding:2px;">
                                                    <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                                      <tr>
                                                        <th width="2%">
                                                          <form:checkbox path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].checkAll" onclick="javascript:selectAll('${command.serviceRates.serviceLevel.activeServiceVersionIndex}', this)" />
                                                        </th>
                                                        <th width="20%" class="TDShadeBlue">Description</th>
                                                        <th width="15%" class="TDShadeBlue">Begin Date <span class="redstar">*</span></th>
                                                        <th width="15%" class="TDShadeBlue">End Date <span class="redstar">*</span></th>
                                                        <th width="10%" class="TDShadeBlue">Zone <span class="redstar">*</span></th>
                                                        <th width="15%" class="TDShadeBlue">Base Price </th>
                                                        <th width="15%" class="TDShadeBlue">Unit Price </th>
                                                        <th width="5%" class="TDShadeBlue">
                                                          <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].rowsToAdd" cssClass="inputBox" size="5"/>
                                                          <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].rowsToAdd" cssClass="redstar"/>                                    
                                                        </th>
                                                      </tr>
                                                      <c:forEach items="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].contractServiceRateExtList}" var="serviceRateExt" varStatus="serviceRateExtStatus">
                                                        <tr>
                                                          <td>
                                                            <form:checkbox path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].checked" />                  
                                                          </td>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.expressionId}</td>
                                                          <td class="TDShadeBlue">
                                                            <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.beginDate" cssClass="inputBox" size="10"/>
                                                            <a href="#" onClick="displayCalendar(document.contractEditForm['serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.beginDate'],'${icbfn:userDateFormat()}',this)">
                                                              <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                                                            </a>
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.beginDate" cssClass="redstar"/>                                    
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.endDate" cssClass="inputBox" size="10"/>
                                                            <a href="#" onClick="displayCalendar(document.contractEditForm['serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.endDate'],'${icbfn:userDateFormat()}',this)">
                                                              <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                                                            </a>
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.endDate" cssClass="redstar"/>                                    
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <form:select cssClass="selectionBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.location">
                                                              <form:options items="${zoneListDropDownItemsWithAll}" itemLabel="name" itemValue="value"/>
                                                            </form:select>
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.location" cssClass="redstar"/>                                    
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.basePrice" cssClass="inputBox" size="6"/>
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.basePrice" cssClass="redstar"/>                                    
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.unitPrice" cssClass="inputBox" size="6"/>
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.unitPrice" cssClass="redstar"/>                                    
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <a href="#"  onClick="doAddServiceRate('${serviceRateExtStatus.index}');">
                                                              <img src="images/add.gif" alt="Copy Row" width="14" height="14" border="0" />
                                                            </a>
                                                          </td>
                                                        </tr>
                                                      </c:forEach>
                                                    </table>          
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>

                                          </div>
                                          <!----------------- TAB 41 CONTAINER END ------------------------------ -->

                                          <!-- ------------------------- TAB 42 CONTAINER ----------------------------------------- -->
                                          <div id="tab42" class="innercontent" >
                                            <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                              <tbody>
                                                <tr bgcolor=#ffffff>
                                                  <th colspan=3 width="65%">Price Range Data </th>
                                                  <th width="35%" >&nbsp;</th>
                                                  <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                                </tr>

                                                <tr>
                                                  <td colspan="5" class="TDShade" style="padding:2px;">
                                                    <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                                      <tr>
                                                        <th width="18%" class="TDShadeBlue">Description</th>
                                                        <th width="12%" class="TDShadeBlue">Min Price </th>
                                                        <th width="12%" class="TDShadeBlue">Max Price </th>
                                                        <th width="12%" nowrap class="TDShadeBlue">Units Included <span class="redstar"></span></th>
                                                        <th width="12%" class="TDShadeBlue">Min Range<span class="redstar">*</span></th>
                                                        <th width="12%" nowrap class="TDShadeBlue">Max Range<span class="redstar">*</span></th>
                                                        <th width="12%" nowrap class="TDShadeBlue">Contract Ref </th>
                                                      </tr>
                                                      <c:forEach items="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].contractServiceRateExtList}" var="serviceRateExt" varStatus="serviceRateExtStatus">
                                                        <tr>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.expressionId}</td>
                                                          <td class="TDShadeBlue">
                                                            <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.minimumPrice" cssClass="inputBox" size="6"/>
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.minimumPrice" cssClass="redstar"/>                                    
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.maximumPrice" cssClass="inputBox" size="6"/>
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.maximumPrice" cssClass="redstar"/>                                    
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.unitsIncluded" cssClass="inputBox" size="6"/>
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.unitsIncluded" cssClass="redstar"/>                                    
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.intData2"cssClass="inputBox" size="6" />
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.intData2" cssClass="redstar"/>                                    
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.intData3" cssClass="inputBox" size="6"/>
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.intData3" cssClass="redstar"/>                                    
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.contractRef" cssClass="inputBox" size="6"/>
                                                            <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.contractRef" cssClass="redstar"/>                                    
                                                          </td>
                                                        </tr>
                                                      </c:forEach>
                                                    </table>
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>

                                          </div>
                                          <!----------------- TAB 42 CONTAINER END ------------------------------ -->

                                          <c:if test="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableVessel or command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableProductGroup}">

                                            <!-- ------------------------- TAB 43 CONTAINER ----------------------------------------- -->
                                            <div id="tab43" class="innercontent" >
                                              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                                <tbody>
                                                  <tr bgcolor=#ffffff>
                                                    <th colspan=3 width="65%">Vessel/Product Group</th>
                                                    <th width="35%" >&nbsp;</th>
                                                    <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                                  </tr>

                                                  <tr>
                                                    <td colspan="5" class="TDShade" style="padding:2px;">
                                                      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                                        <tr>
                                                          <th width="18%" class="TDShadeBlue">Description</th>
                                                          <th width="12%" class="TDShadeBlue">Product Group</th>
                                                          <th width="12%" class="TDShadeBlue">Vessel</th>
                                                        </tr>
                                                        <c:forEach items="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].contractServiceRateExtList}" var="serviceRateExt" varStatus="serviceRateExtStatus">
                                                          <tr>
                                                            <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.expressionId}</td>
                                                            <td class="TDShadeBlue">
                                                              <form:select cssClass="selectionBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.groupId">
                                                                <form:options items="${productGroupDropdown}" itemLabel="name" itemValue="value"/>
                                                              </form:select>
                                                            </td>
                                                            <td class="TDShadeBlue">
                                                              <form:select cssClass="selectionBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.vesselType">
                                                                <form:options items="${vesselTypeDropDown}" itemLabel="name" itemValue="value"/>
                                                              </form:select>
                                                            </td>
                                                          </tr>
                                                        </c:forEach>
                                                      </table>
                                                    </td>
                                                  </tr>
                                                </tbody>
                                              </table>

                                            </div>
                                            <!----------------- TAB 43 CONTAINER END ------------------------------ -->
                                          </c:if>

                                          <!-- ------------------------- TAB 44 CONTAINER ----------------------------------------- -->
                                          <div id="tab44" class="innercontent" >
                                            <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                              <tbody>
                                                <tr bgcolor=#ffffff>
                                                  <th colspan=3 width="65%">Hidden Fields </th>
                                                  <th width="35%" >&nbsp;</th>
                                                  <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                                </tr>

                                                <tr>
                                                  <td colspan="5" class="TDShade" style="padding:2px;">
                                                    <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                                      <tr>
                                                        <th width="18%" class="TDShadeBlue">Description</th>
                                                        <th width="12%" class="TDShadeBlue">Rate ID</th>
                                                        <th width="12%" class="TDShadeBlue">Contract Id</th>
                                                        <th width="12%" nowrap class="TDShadeBlue">Vessel Type</th>
                                                        <th width="12%" class="TDShadeBlue">Group ID</th>
                                                        <th width="12%" nowrap class="TDShadeBlue">With Inspection</th>
                                                        <th width="12%" nowrap class="TDShadeBlue">Row #</th>
                                                        <th width="12%" nowrap class="TDShadeBlue">Service Level Id</th>
                                                        <th width="12%" nowrap class="TDShadeBlue">Is Deleted</th>
                                                      </tr>
                                                      <c:forEach items="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].contractServiceRateExtList}" var="serviceRateExt" varStatus="serviceRateExtStatus">
                                                        <tr>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.expressionId}</td>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.rateId}</td>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.contractId}</td>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.vesselType}</td>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.groupId}</td>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.withInspection}</td>
                                                          <td class="TDShadeBlue">${serviceRateExtStatus.index}</td>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.serviceLevel}</td>
                                                          <td class="TDShadeBlue"></td>
                                                        </tr>
                                                      </c:forEach>
                                                    </table>
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>

                                          </div>
                                          <!----------------- TAB 44 CONTAINER END ------------------------------ -->

                                        </div>
                                      </div>
                                      <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
                                    </c:when>
                                    <c:otherwise>
                                      <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                        <tbody>
                                          <tr bgcolor=#ffffff>
                                            <th width="65%">
                                                <form:input cssClass="inputBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allBeginDate" size="10" maxlength="12" />
                                                <a href="#" onClick="displayCalendar(document.forms[0].elements['serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allBeginDate'],'${userDateFormat}',this)"> 
                                                  <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                                                </a>
                                                <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allBeginDate" cssClass="redstar"/>
                                                &nbsp;&nbsp;&nbsp;
                                                <form:input cssClass="inputBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allEndDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                                                <a href="#" onClick="displayCalendar(document.forms[0].elements['serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allEndDate'],'${userDateFormat}',this)"> 
                                                  <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                                                </a>
                                                <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].allEndDate" cssClass="redstar"/>
                                                <a href="#" onClick="javascript:doSubmit('applyDatesForAllRates');">Apply Dates</a>
                                                &nbsp;&nbsp;&nbsp;
                                                <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                                                &nbsp;&nbsp;&nbsp;
                                                <a href="#" onClick="javascript:doSubmit('deleteSelectedRates');">Delete Selected</a>
                                            </th>
                                            <th width="35%" >&nbsp; </th>
                                            <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                          </tr>

                                          <tr>
                                            <td colspan="3" class="TDShade" style="padding:2px;">
                                              <div id="serviceRateShowAllDiv" style="width:1000px;overflow:auto;">
                                                <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                                  <tr>
                                                    <th width="2%">
                                                      <form:checkbox path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].checkAll" onclick="javascript:selectAll('${command.serviceRates.serviceLevel.activeServiceVersionIndex}', this)" />
                                                    </th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Description</nobr></th>
                                                    <th width="15%" class="TDShadeBlue"><nobr>Begin Date <span class="redstar">*</span></nobr></th>
                                                    <th width="15%" class="TDShadeBlue"><nobr>End Date <span class="redstar">*</span></nobr></th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Zone <span class="redstar">*</span></nobr></th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Base Price</nobr></th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Unit Price</nobr></th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Min Price</nobr></th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Max Price</nobr></th>
                                                    <th width="5%" nowrap class="TDShadeBlue"><nobr>Units Included <span class="redstar"></span></nobr></th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Min Range<span class="redstar">*</span></nobr></th>
                                                    <th width="5%" nowrap class="TDShadeBlue"><nobr>Max Range<span class="redstar">*</span></nobr></th>
                                                    <th width="5%" nowrap class="TDShadeBlue"><nobr>Contract Ref</nobr></th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Product Group</nobr></th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Vessel</nobr></th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Rate ID</nobr></th>
                                                    <th width="5%" class="TDShadeBlue"><nobr>Contract Id</nobr></th>
                                                    <th width="2%" nowrap class="TDShadeBlue"><nobr>With Inspection</nobr></th>
                                                    <th width="2%" nowrap class="TDShadeBlue"><nobr>Row #</nobr></th>
                                                    <th width="2%" nowrap class="TDShadeBlue"><nobr>Service Level Id</nobr></th>
                                                    <th width="2%" nowrap class="TDShadeBlue"><nobr>Is Deleted</nobr></th>
                                                    <th width="2%" class="TDShadeBlue">
                                                      <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].rowsToAdd" cssClass="inputBox" size="5"/>
                                                      <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].rowsToAdd" cssClass="redstar"/>                                    
                                                    </th>
                                                  </tr>
                                                  <c:forEach items="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].contractServiceRateExtList}" var="serviceRateExt" varStatus="serviceRateExtStatus">
                                                    <tr>
                                                      <td>
                                                        <form:checkbox path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].checked" />                  
                                                      </td>
                                                      <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.expressionId}</td>
                                                      <td class="TDShadeBlue">
                                                        <nobr>
                                                          <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.beginDate" cssClass="inputBox" size="10"/>
                                                          <a href="#" onClick="displayCalendar(document.contractEditForm['serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.beginDate'],'${icbfn:userDateFormat()}',this)">
                                                            <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                                                          </a>
                                                          <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.beginDate" cssClass="redstar"/>                                    
                                                        </nobr>
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <nobr>
                                                          <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.endDate" cssClass="inputBox" size="10"/>
                                                          <a href="#" onClick="displayCalendar(document.contractEditForm['serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.endDate'],'${icbfn:userDateFormat()}',this)">
                                                            <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                                                          </a>
                                                          <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.endDate" cssClass="redstar"/>                                    
                                                        </nobr>
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:select cssClass="selectionBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.location">
                                                          <form:options items="${zoneListDropDownItemsWithAll}" itemLabel="name" itemValue="value"/>
                                                        </form:select>
                                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.location" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.basePrice" cssClass="inputBox" size="6"/>
                                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.basePrice" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.unitPrice" cssClass="inputBox" size="6"/>
                                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.unitPrice" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.minimumPrice" cssClass="inputBox" size="6"/>
                                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.minimumPrice" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.maximumPrice" cssClass="inputBox" size="6"/>
                                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.maximumPrice" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.unitsIncluded" cssClass="inputBox" size="6"/>
                                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.unitsIncluded" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.intData2"cssClass="inputBox" size="6" />
                                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.intData2" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.intData3" cssClass="inputBox" size="6"/>
                                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.intData3" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.contractRef" cssClass="inputBox" size="6"/>
                                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.contractRef" cssClass="redstar"/>                                    
                                                      </td>
                                                      <c:choose>
                                                        <c:when test="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableVessel or command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].enableProductGroup}">
                                                          <td class="TDShadeBlue">
                                                            <form:select cssClass="selectionBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.groupId">
                                                              <form:options items="${productGroupDropdown}" itemLabel="name" itemValue="value"/>
                                                            </form:select>
                                                          </td>
                                                          <td class="TDShadeBlue">
                                                            <form:select cssClass="selectionBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].contractServiceRateExtList[${serviceRateExtStatus.index}].serviceRate.serviceRateId.vesselType">
                                                              <form:options items="${vesselTypeDropDown}" itemLabel="name" itemValue="value"/>
                                                            </form:select>
                                                          </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.groupId}</td>
                                                          <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.vesselType}</td>
                                                        </c:otherwise>
                                                      </c:choose>
                                                      <td class="TDShadeBlue">${serviceRateExt.serviceRate.rateId}</td>
                                                      <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.contractId}</td>
                                                      <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.withInspection}</td>
                                                      <td class="TDShadeBlue">${serviceRateExtStatus.index}</td>
                                                      <td class="TDShadeBlue">${serviceRateExt.serviceRate.serviceRateId.serviceLevel}</td>
                                                      <td class="TDShadeBlue">&nbsp;</td>
                                                      <td class="TDShadeBlue">
                                                        <a href="#"  onClick="doAddServiceRate('${serviceRateExtStatus.index}');">
                                                          <img src="images/add.gif" alt="Copy Row" width="14" height="14" border="0" />
                                                        </a>
                                                      </td>
                                                    </tr>
                                                  </c:forEach>
                                                  <tr><td colspan="22">&nbsp;</td></tr>
                                                </table>          
                                              </div>
                                            </td>
                                          </tr>
                                        </tbody>
                                      </table>
                                    </c:otherwise>
                                  </c:choose>
                                </td>
                              </tr>
                            </c:if>
                          </table></td>
                        </tr>
                        <tr>
                          <td colspan="5" class="TDShade" style="padding:2px;">&nbsp;</td>
                        </tr>
                        <c:if test="${command.serviceRates.serviceLevel.cfgContractId != 'MASTER'}">
                          <tr>
                            <td colspan="5" class="TDShade" style="padding:2px;">
                              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                                <c:forEach items="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].ceExtListForContract}" var="ceExt" varStatus="ceExtStatus">                            
                                  <c:if test="${ceExt.contractExpression.contractExpressionId.location != '*'}">
                                    <tr>
                                      <td width="35%">Zone: 
                                        ${ceExt.contractExpression.contractExpressionId.location}
                                      </td>
                                      <td width="20%">
                                        <form:select path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].ceExtListForContract[${ceExtStatus.index}].contractExpression.contractComponent">
                                          <form:option value="PB" label="Pricebook" />
                                        </form:select>                              
                                        <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].ceExtListForContract[${ceExtStatus.index}].contractExpression.contractComponent" cssClass="redstar"/>                                    
                                      </td>
                                      <td width="15%">
                                        <div id="discountpctLabel">Discount Pct:
                                          <form:input path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].ceExtListForContract[${ceExtStatus.index}].contractExpression.cfgDiscountPercent" cssClass="inputBox" size="6" />
                                          <form:errors path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].ceExtListForContract[${ceExtStatus.index}].contractExpression.cfgDiscountPercent" cssClass="redstar"/>                                    
                                        </div>
                                      </td>
                                    </tr>
                                  </c:if>
                                </c:forEach>
                              </table>
                            </td>
                          </tr>
                          <tr>
                            <td colspan="5" class="TDShade" style="padding:2px;">
                              <form:select cssClass="selectionBox" path="serviceRates.serviceLevel.serviceVersionExtList[${command.serviceRates.serviceLevel.activeServiceVersionIndex}].selectedZoneId" items="${zoneListDropDownItems}" itemLabel="name" itemValue="value"/>
                              &nbsp;&nbsp;
                              <a href="#" onclick="doSubmit('addZoneDiscount');">
                                Add Zone Discount
                              </a> &nbsp;| &nbsp;
                              <a href="#" onclick="doSubmit('deleteZoneDiscount');">
                                Remove Zone Discount
                              </a> 
                            </td>
                          </tr>
                        </c:if>
                      </c:if>
                    </tbody>
                  </table>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                    <tr>
                      <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt">marked fields are mandatory</span> </td>
                            <td style="text-align:right"><a href="#"></a></td>
                          </tr>
                        </table></td>
                    </tr>
                  </table>
                </div>
                <!----------------- TAB 51 CONTAINER END ------------------------------ -->

                <!-- ------------------------- TAB 52 CONTAINER ----------------------------------------- -->
                <div id="tab52" class="innercontent" >
                  <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                    <tbody>
                      <tr bgcolor=#ffffff>
                        <th colspan=3 width="65%">Pricebook Rates </th>
                        <th width="35%" >&nbsp;</th>
                        <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;&nbsp;</th>
                      </tr>

                      <tr>
                        <td colspan="5" class="TDShade" style="padding:2px;">
                          <!-- TABS CONTENTS -->
                          <div id="tabcontainer2"  style="background-color:#F7F7F7;">
                            <div id="tabnav2" style="background-color:#F7F7F7;">
                              <ul>
                                <li><a rel="tab21" style="cursor:hand;"><span>Base Rate Data</span></a></li>
                                <li><a rel="tab22" style="cursor:hand;"><span>Price Range Data</span></a></li>
                                <li><a rel="tab23" style="cursor:hand;"><span>Hidden Fields</span></a></li>
                              </ul>
                            </div>
                            <!-- Sub Menus container. Do not remove -->
                            <div id="tab_inner2">
                              <!-- ------------------------- TAB 21 CONTAINER ----------------------------------------- -->
                              <div id="tab21" class="innercontent" >
                                <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                  <tbody>
                                    <tr bgcolor=#ffffff>
                                      <th width="65%">Base Rate Data</th>
                                      <th width="35%" >&nbsp; </th>
                                      <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                    </tr>

                                    <tr>
                                      <td colspan="3" class="TDShade" style="padding:2px;">
                                        <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                          <tr>
                                            <th width="20%" class="TDShadeBlue">Description</th>
                                            <th width="15%" class="TDShadeBlue">Begin Date </th>
                                            <th width="15%" class="TDShadeBlue">End Date </th>
                                            <th width="10%" class="TDShadeBlue">Zone<span class="redstar">*</span></th>
                                            <th width="15%" class="TDShadeBlue">Base Price </th>
                                            <th width="15%" class="TDShadeBlue">Unit Price </th>
                                          </tr>
                                          <c:forEach items="${command.serviceRates.priceBookServiceRateList}" var="serviceRate" varStatus="serviceRateStatus">
                                            <tr>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.expressionId}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.beginDate}</td>
                                              <td class="TDShadeBlue">${serviceRate.endDate}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.location}</td>
                                              <td class="TDShadeBlue">${serviceRate.basePrice}</td>
                                              <td class="TDShadeBlue">${serviceRate.unitPrice}</td>
                                            </tr>
                                          </c:forEach>
                                        </table>          
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>

                              </div>
                              <!----------------- TAB 21 CONTAINER END ------------------------------ -->

                              <!-- ------------------------- TAB 22 CONTAINER ----------------------------------------- -->
                              <div id="tab22" class="innercontent" >
                                <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                  <tbody>
                                    <tr bgcolor=#ffffff>
                                      <th colspan=3 width="65%">Price Range Data </th>
                                      <th width="35%" >&nbsp;</th>
                                      <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                    </tr>

                                    <tr>
                                      <td colspan="5" class="TDShade" style="padding:2px;">
                                        <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                          <tr>
                                            <th width="18%" class="TDShadeBlue">Description</th>
                                            <th width="12%" class="TDShadeBlue">Min Price</th>
                                            <th width="12%" class="TDShadeBlue">Max Price</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Units Included</span></th>
                                            <th width="12%" class="TDShadeBlue">Min Range</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Max Range</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Contract Ref</th>
                                          </tr>
                                          <c:forEach items="${command.serviceRates.priceBookServiceRateList}" var="serviceRate" varStatus="serviceRateStatus">
                                            <tr>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.expressionId}</td>
                                              <td class="TDShadeBlue">${serviceRate.minimumPrice}</td>
                                              <td class="TDShadeBlue">${serviceRate.maximumPrice}</td>
                                              <td class="TDShadeBlue">${serviceRate.unitsIncluded}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.intData2}</td>
                                              <td class="TDShadeBlue">${serviceRate.intData3}</td>
                                              <td class="TDShadeBlue">${serviceRate.contractRef}</td>
                                            </tr>
                                          </c:forEach>
                                        </table>
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>

                              </div>
                              <!----------------- TAB 22 CONTAINER END ------------------------------ -->

                              <!-- ------------------------- TAB 23 CONTAINER ----------------------------------------- -->
                              <div id="tab23" class="innercontent" >
                                <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                  <tbody>
                                    <tr bgcolor=#ffffff>
                                      <th colspan=3 width="65%">Hidden Fields </th>
                                      <th width="35%" >&nbsp;</th>
                                      <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                    </tr>

                                    <tr>
                                      <td colspan="5" class="TDShade" style="padding:2px;">
                                        <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                          <tr>
                                            <th width="18%" class="TDShadeBlue">Description</th>
                                            <th width="12%" class="TDShadeBlue">Rate ID</th>
                                            <th width="12%" class="TDShadeBlue">Contract Id</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Vessel Type</th>
                                            <th width="12%" class="TDShadeBlue">Group ID</th>
                                            <th width="12%" nowrap class="TDShadeBlue">With Inspection</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Row #</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Service Level Id</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Is Deleted</th>
                                          </tr>
                                          <c:forEach items="${command.serviceRates.priceBookServiceRateList}" var="serviceRate" varStatus="serviceRateStatus">
                                            <tr>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.expressionId}</td>
                                              <td class="TDShadeBlue">${serviceRate.rateId}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.contractId}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.vesselType}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.groupId}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.withInspection}</td>
                                              <td class="TDShadeBlue">${serviceRateStatus.index}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.serviceLevel}</td>
                                              <td class="TDShadeBlue"></td>
                                            </tr>
                                          </c:forEach>
                                        </table>
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>

                              </div>
                              <!----------------- TAB 23 CONTAINER END ------------------------------ -->

                            </div>
                          </div>
                          <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
                        </td>
                      </tr>
                    </tbody>
                  </table>
                  <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                    <tbody>
                      <tr bgcolor=#ffffff>
                        <th colspan=3 width="65%">Contract Specific Rates </th>
                        <th width="35%" >&nbsp;</th>
                        <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;&nbsp;</th>
                      </tr>

                      <tr>
                        <td colspan="5" class="TDShade" style="padding:2px;">
                          <!-- TABS CONTENTS -->
                          <div id="tabcontainer3"  style="background-color:#F7F7F7;">
                            <div id="tabnav3" style="background-color:#F7F7F7;">
                              <ul>
                                <li><a rel="tab31" style="cursor:hand;"><span>Base Rate Data</span></a></li>
                                <li><a rel="tab32" style="cursor:hand;"><span>Price Range Data</span></a></li>
                                <li><a rel="tab33" style="cursor:hand;"><span>Hidden Fields</span></a></li>
                              </ul>
                            </div>
                            <!-- Sub Menus container. Do not remove -->
                            <div id="tab_inner3">
                              <!-- ------------------------- TAB 31 CONTAINER ----------------------------------------- -->
                              <div id="tab31" class="innercontent" >
                                <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                  <tbody>
                                    <tr bgcolor=#ffffff>
                                      <th width="65%">Base Rate Data</th>
                                      <th width="35%" >&nbsp; </th>
                                      <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                    </tr>

                                    <tr>
                                      <td colspan="3" class="TDShade" style="padding:2px;">
                                        <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                          <tr>
                                            <th width="20%" class="TDShadeBlue">Description</th>
                                            <th width="15%" class="TDShadeBlue">Begin Date </th>
                                            <th width="15%" class="TDShadeBlue">End Date </th>
                                            <th width="10%" class="TDShadeBlue">Zone<span class="redstar">*</span></th>
                                            <th width="15%" class="TDShadeBlue">Base Price </th>
                                            <th width="15%" class="TDShadeBlue">Unit Price </th>
                                          </tr>
                                          <c:forEach items="${command.serviceRates.contractSpecificServiceRateList}" var="serviceRate" varStatus="serviceRateStatus">
                                            <tr>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.expressionId}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.beginDate}</td>
                                              <td class="TDShadeBlue">${serviceRate.endDate}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.location}</td>
                                              <td class="TDShadeBlue">${serviceRate.basePrice}</td>
                                              <td class="TDShadeBlue">${serviceRate.unitPrice}</td>
                                            </tr>
                                          </c:forEach>
                                        </table>          
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>

                              </div>
                              <!----------------- TAB 31 CONTAINER END ------------------------------ -->

                              <!-- ------------------------- TAB 32 CONTAINER ----------------------------------------- -->
                              <div id="tab32" class="innercontent" >
                                <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                  <tbody>
                                    <tr bgcolor=#ffffff>
                                      <th colspan=3 width="65%">Price Range Data </th>
                                      <th width="35%" >&nbsp;</th>
                                      <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                    </tr>

                                    <tr>
                                      <td colspan="5" class="TDShade" style="padding:2px;">
                                        <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                          <tr>
                                            <th width="18%" class="TDShadeBlue">Description</th>
                                            <th width="12%" class="TDShadeBlue">Min Price</th>
                                            <th width="12%" class="TDShadeBlue">Max Price</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Units Included</span></th>
                                            <th width="12%" class="TDShadeBlue">Min Range</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Max Range</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Contract Ref</th>
                                          </tr>
                                          <c:forEach items="${command.serviceRates.contractSpecificServiceRateList}" var="serviceRate" varStatus="serviceRateStatus">
                                            <tr>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.expressionId}</td>
                                              <td class="TDShadeBlue">${serviceRate.minimumPrice}</td>
                                              <td class="TDShadeBlue">${serviceRate.maximumPrice}</td>
                                              <td class="TDShadeBlue">${serviceRate.unitsIncluded}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.intData2}</td>
                                              <td class="TDShadeBlue">${serviceRate.intData3}</td>
                                              <td class="TDShadeBlue">${serviceRate.contractRef}</td>
                                            </tr>
                                          </c:forEach>
                                        </table>
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>

                              </div>
                              <!----------------- TAB 32 CONTAINER END ------------------------------ -->

                              <!-- ------------------------- TAB 33 CONTAINER ----------------------------------------- -->
                              <div id="tab33" class="innercontent" >
                                <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                  <tbody>
                                    <tr bgcolor=#ffffff>
                                      <th colspan=3 width="65%">Hidden Fields </th>
                                      <th width="35%" >&nbsp;</th>
                                      <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                                    </tr>

                                    <tr>
                                      <td colspan="5" class="TDShade" style="padding:2px;">
                                        <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                          <tr>
                                            <th width="18%" class="TDShadeBlue">Description</th>
                                            <th width="12%" class="TDShadeBlue">Rate ID</th>
                                            <th width="12%" class="TDShadeBlue">Contract Id</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Vessel Type</th>
                                            <th width="12%" class="TDShadeBlue">Group ID</th>
                                            <th width="12%" nowrap class="TDShadeBlue">With Inspection</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Row #</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Service Level Id</th>
                                            <th width="12%" nowrap class="TDShadeBlue">Is Deleted</th>
                                          </tr>
                                          <c:forEach items="${command.serviceRates.contractSpecificServiceRateList}" var="serviceRate" varStatus="serviceRateStatus">
                                            <tr>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.expressionId}</td>
                                              <td class="TDShadeBlue">${serviceRate.rateId}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.contractId}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.vesselType}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.groupId}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.withInspection}</td>
                                              <td class="TDShadeBlue">${serviceRateStatus.index}</td>
                                              <td class="TDShadeBlue">${serviceRate.serviceRateId.serviceLevel}</td>
                                              <td class="TDShadeBlue"></td>
                                            </tr>
                                          </c:forEach>
                                        </table>
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>

                              </div>
                              <!----------------- TAB 33 CONTAINER END ------------------------------ -->

                            </div>
                          </div>
                          <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
                        </td>
                      </tr>
                    </tbody>
                  </table>
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
                              <th class="TDShadeBlue">Service Id</th>
                              <th class="TDShadeBlue">Expression Id</th>
                              <th class="TDShadeBlue">Master List</th>
                              <th class="TDShadeBlue">One or All Levels</th>
                              <th class="TDShadeBlue">Apply to Level</th>
                            </tr>
                            <c:forEach items="${command.serviceRates.serviceLevelList}" var="serviceLevel" varStatus="serviceLevelStatus">
                              <tr>
                                <td class="TDShadeBlue">&nbsp;</td>
                                <td class="TDShadeBlue">${serviceLevel.serviceLevel}</td>
                                <td class="TDShadeBlue">${serviceLevel.serviceName}</td>
                                <td class="TDShadeBlue">${serviceLevel.expressionId}</td>
                                <td class="TDShadeBlue">
                                  <c:choose>
                                    <c:when test="${serviceLevel.cfgContractId == 'MASTER'}">Y</c:when>
                                    <c:otherwise>N</c:otherwise>
                                  </c:choose>
                                </td>
                                <td class="TDShadeBlue">
                                  ${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].rateApplied}
                                </td>
                                <td class="TDShadeBlue">
                                  ${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].applyToThisLevel}
                                </td>
                              </tr>
                            </c:forEach>
                          </table>          
                        </td>
                      </tr>
                    </tbody>
                  </table>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                    <tr>
                      <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt">marked fields are mandatory</span> </td>
                            <td style="text-align:right"><a href="#"></a></td>
                          </tr>
                        </table></td>
                    </tr>
                  </table>
                </div>
                <!----------------- TAB 52 CONTAINER END ------------------------------ -->

              </div>
            </div>   
            <script type="text/javascript">

              dolphintabs5.init("tabnav5", 0)

              dolphintabs2.init("tabnav2", 0)

              dolphintabs3.init("tabnav3", 0)

              <c:if test="${command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].rateApplied != null and command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].rateApplied != 'U'}">                                
                <c:if test="${(command.serviceRates.serviceLevel.cfgContractId == 'MASTER') or (command.serviceRates.serviceLevel.serviceVersionExtList[command.serviceRates.serviceLevel.activeServiceVersionIndex].allZoneCeExtForContract.contractExpression.contractComponent == 'CONTR')}">
                  <c:if test="${not command.displayAll}">
                    dolphintabs4.init("tabnav4", 0)
                  </c:if>
                </c:if>
              </c:if>

            </script>
              <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->

          </td>
        </tr>
        <tr>
          <td>
            <input name="Submit2" type="button" class="button1" onclick="javascript:doSubmit('submitAndReturn');" value="Submit" />      
            &nbsp;&nbsp;
            <input name="Submit2" type="button" class="button1" onclick="javascript:doSubmit('saveOnly');" value="Save" />      
            &nbsp;&nbsp;
            <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('return');" value="Cancel" />      
          </td>
        </tr>
      </table>

    </td>
  </tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td style="text-align:right">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<script>
  var serviceRateShowAllDiv = document.getElementById("serviceRateShowAllDiv");
  if(serviceRateShowAllDiv)
  {
    var wWidth;
    if (window.innerWidth) wWidth = window.innerWidth;
    if (document.body.offsetWidth) wWidth = document.body.offsetWidth;
    serviceRateShowAllDiv.style.width = wWidth * 0.85 + "px";
  }
</script>
