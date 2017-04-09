<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<SCRIPT LANGUAGE="JavaScript">
  <!--
  function selectVesselType(myVesselTypeName)
  {
    document.contractEditForm.operationType.value = "selectVesselType";
    document.contractEditForm.selectedVesselTypeName.value = myVesselTypeName;
    document.contractEditForm.submit();
  }  
  
  function doFillVesselMax()
  {
    document.contractEditForm.operationType.value = "fillVesselMax";
    document.contractEditForm.submit();
  }  
  
  function doChangeDate()
  {
    document.contractEditForm.operationType.value = "changeDate";
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

  function doChangeProductGroup(changeType)
  {
    document.contractEditForm.operationType.value = "changeProductGroup";
    document.contractEditForm.changeType.value = changeType;
    document.contractEditForm.submit();
  }  

  function doSelectVersion(versionIndex)
  {
    document.contractEditForm.operationType.value = "selectVersion";
    document.contractEditForm.activeInspectionVersion.value = versionIndex;
    document.contractEditForm.submit();
  }  

  function doDeleteVersion(versionIndex)
  {
    var result = confirm("All data for the current version will be deleted, are you sure you want to delete this version?");
    if(result==true)
    {
      document.contractEditForm.operationType.value = "deleteVersion";
      document.contractEditForm.activeInspectionVersion.value = versionIndex;
      document.contractEditForm.submit();
    }
  }  
  
  function doAddInspectionRate(inspectionRateIndex)
  {
    document.contractEditForm.operationType.value = "addInspectionRate";
    document.contractEditForm.inspectionRateIndex.value = inspectionRateIndex;
    document.contractEditForm.submit();
  }  

  // select products
  function popSelectProducts(myVesselTypeName)
  {
    popup_show('productlist', 'productlist_drag', 'productlist_exit', 'screen-corner', 40, 40); 
    hideIt();
    popupboxenable();

    document.getElementById("productlistbox").src="edit_contract_select_products_popup.htm?vesselType=" + myVesselTypeName;             
    document.getElementById("productlistbox").height = "470px";
  }  

  // update uom notes
  function popUpdateUomNotess()
  {
    popup_show('updateuomnotes', 'updateuomnotes_drag', 'updateuomnotes_exit', 'screen-corner', 40, 40); 
    hideIt();
    popupboxenable();

    document.getElementById("updateuomnotesbox").src="edit_contract_update_uom_notes_popup.htm";             
    document.getElementById("updateuomnotesbox").height = "470px";
  }  

  function selectAll(selectAllElement)
  {
    var elementList = document.forms[0].elements;
    for(i=0;i<elementList.length;i++)
    {
      if(elementList[i].type == "checkbox")
      {
        var checkBoxName = elementList[i].name;
        var prefix = 'inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}]';
        var myPosition = checkBoxName.indexOf(prefix);
        if(myPosition >= 0)
        {
          if(selectAllElement.checked) elementList[i].checked=true;
          else elementList[i].checked=false;
        }
      }
    }
  }

  function doSetInspectionRateTab(tabIndex)
  {
    document.contractEditForm['inspectionVersionExtList[${command.activeInspectionVersionIndex}].activeVesselTypeExt.inspectionRateTab'].value = tabIndex;
  }  
  //-->
</SCRIPT>

<input type="hidden" name="selectedVesselTypeName" value="" />
<input type="hidden" name="activeInspectionVersion" value="${command.activeInspectionVersionIndex}" />
<input type="hidden" name="changeType" value="" />
<input type="hidden" name="inspectionRateIndex" value="" />
<c:if test="${not command.inspectionVersionExtList[command.activeInspectionVersionIndex].otherCheckBoxesViewOnly}">
  <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt != null}">
    <c:if test="${not command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.displayAll}">
      <form:hidden path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].activeVesselTypeExt.inspectionRateTab" />
    </c:if>
  </c:if>
</c:if>

<icb:list var="uomList" items="${icbfn:dropdown('uomTableDropDown',null)}" />

<icb:list var="contractList">
  <icb:item>${command.contract.contractCode}</icb:item>
  <icb:item>${command.contract.workingPB}</icb:item>
</icb:list>
<icb:list var="zoneListDropDownItemsWithAll" items="${icbfn:dropdown('zoneListDropDown', contractList)}" />

<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tr>
    <th width="50%">
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
    <th>&nbsp;</th>
    <th>&nbsp;</th>
    <th width="25%">&nbsp;
      <c:if test="${command.contractSearch != null}">
        <a href="#" onClick="javascript:doOperation('searchContract');">
          <img src="images/icofindjob.gif" alt="Back to Search Contract" width="16" height="14" border="0" align="absmiddle">
        </a>&nbsp;&nbsp;
        <a href="#" onClick="javascript:doOperation('prevContract');">
          <img src="images/prevleftarrow.gif" alt="Go to Previous Contract" width="13" height="12" hspace="1px" border="0"/>
        </a> &nbsp;&nbsp;
        <a href="#" onClick="javascript:doOperation('nextContract');">
          <img src="images/nextrtarrow.gif" alt="Go to Next Contract" width="13" height="12" hspace="1px" border="0"/>
        </a> &nbsp;&nbsp;
      </c:if>
      <c:if test="${not command.viewOnly}">
        <a href="#"  onClick="doOperation('saveContract');">
          <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
        </a>
      </c:if>
    </th>
  </tr>
  <tr>
    <td colspan="5" style="padding:2px;">
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:100%" >
        <tbody>
          <tr>
            <td>
              <a href="#" onclick="doOperation('addVersion');">Add Version</a>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <a href="#" onclick="popUpdateUomNotess();">Update UOM Notes</a>
            </td>
          </tr>
          
          <tr>
            <td style="padding:0">
              <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=3 width="65%">Inspection Versions </th>
                    <th width="35%" >&nbsp; </th>
                    <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                  </tr>

                  <tr>
                    <td colspan="5" class="TDShade" style="padding:1px;">
                      <table width="100%" border="0" cellpadding="0" cellspacing="0"  style="margin:1px; width:100%">
                        <tr>
                          <td width="35%" style="padding:1px;">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                              <tr>
                                <th>Choose Version</th>
                                <th>&nbsp;</th>
                              </tr>
                              <c:forEach items="${command.inspectionVersionExtList}" var="inspectionVersionExt" varStatus="inspectionVersionExtStatus">
                                <tr>
                                  <td>
                                    <c:choose>
                                      <c:when test="${inspectionVersionExtStatus.index == command.activeInspectionVersionIndex}">
                                        <f:formatDate value="${inspectionVersionExt.inspectionVersion.inspectionVersionId.beginDate}" pattern="${icbfn:userDateFormat()}" /> to <f:formatDate value="${inspectionVersionExt.inspectionVersion.endDate}" pattern="${icbfn:userDateFormat()}" />
                                      </c:when>
                                      <c:otherwise>
                                        <a href="#" onClick="doSelectVersion('${inspectionVersionExtStatus.index}')" />                                
                                          <f:formatDate value="${inspectionVersionExt.inspectionVersion.inspectionVersionId.beginDate}" pattern="${icbfn:userDateFormat()}" /> to <f:formatDate value="${inspectionVersionExt.inspectionVersion.endDate}" pattern="${icbfn:userDateFormat()}" />
                                        </a>
                                      </c:otherwise>
                                    </c:choose>
                                    <form:errors path="inspectionVersionExtList[${inspectionVersionExtStatus.index}].inspectionVersion.inspectionVersionId.beginDate" cssClass="redstar"/>                                    
                                  </td>
                                  <td>
                                    <a href="#"  onClick="doDeleteVersion('${inspectionVersionExtStatus.index}');">
                                      <img src="images/delete.gif" alt="Delete Version" width="14" height="14" border="0" />
                                    </a>
                                  </td>
                                </tr>
                              </c:forEach>
                            </table>
                          </td>
                          <td width="65%" style="padding:1px;">
                            <c:if test="${command.activeInspectionVersionIndex >= 0}">
                              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                                <tr>
                                  <th>Selected Version </th>
                                  <th>Begin Date:<span class="redstar">*</span> 
                                    <form:input onchange="doChangeDate();" onkeypress="onKeyPressed();" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.inspectionVersionId.beginDate" cssClass="inputBox" size="10"/>
                                    <a href="#" onClick="displayCalendar(document.contractEditForm['inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.inspectionVersionId.beginDate'],'${icbfn:userDateFormat()}',this)">
                                      <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                                    </a>
                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.inspectionVersionId.beginDate" cssClass="redstar"/>                                    
                                  </th>
                                  <th>End Date:<span class="redstar">*</span> 
                                    <form:input onchange="doChangeDate();" onkeypress="onKeyPressed();" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.endDate" cssClass="inputBox" size="10"/>
                                    <a href="#" onClick="displayCalendar(document.contractEditForm['inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.endDate'],'${icbfn:userDateFormat()}',this)">
                                      <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                                    </a>
                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.endDate" cssClass="redstar"/>                                    
                                  </th>
                                </tr>
                                <c:if test="${command.activeInspectionVersionIndex >= 0}">
                                  <tr>
                                    <td>
                                     <form:checkbox onclick="doOperation('selectContractSpecificRatesCheckbox');" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.contractRateInd" />
                                     Contract Specific Rates
                                    </td>
                                    <td>
                                      <form:checkbox onclick="doOperation('changeTowMaxCheckBox');" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.towMaxInd" disabled="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].otherCheckBoxesViewOnly}"/>
                                      Tow Max
                                    </td>
                                    <td>
                                      <form:checkbox onclick="doOperation('refresh');" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.vesselMaxInd"  disabled="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].otherCheckBoxesViewOnly}"/>
                                      Vessel Max
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>
                                      <form:checkbox onclick="doOperation('refresh');" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.numGradesInd"  disabled="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].otherCheckBoxesViewOnly}"/>
                                      Total # of Grades
                                    </td>
                                    <td>
                                      <form:checkbox onclick="doOperation('refresh');" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].inspectionVersion.additonalVesselInd"  disabled="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].otherCheckBoxesViewOnly}"/>
                                      Additional Vessel
                                    </td>
                                    <td>&nbsp;</td>
                                  </tr>
                                </c:if>
                              </table>
                            </c:if>
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </tbody>
              </table>
            </td>
          </tr>      
          <c:if test="${command.activeInspectionVersionIndex >= 0 and not command.inspectionVersionExtList[command.activeInspectionVersionIndex].otherCheckBoxesViewOnly}">
          <tr>
            <td style="padding:0">
              <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan="3" width="65%">Inspection Rates</th>
                    <th width="35%" >&nbsp; </th>
                    <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                  </tr>
                  
                  <tr>
                    <td colspan="5">
                      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                        <tr>
                          <th colspan="4">Vessel Types </th>
                        </tr>
                        <c:forEach items="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].vesselTypeExtRows}" var="row">
                          <tr>
                            <c:forEach items="${row}" var="vesselTypeExt">
                              <td width="25%">
                                <c:choose>
                                  <c:when test="${vesselTypeExt == command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt}">
                                    <c:choose>
                                      <c:when test="${vesselTypeExt.rbExt.notesRb != null}">${vesselTypeExt.rbExt.notesRb.rbValue}</c:when>
                                      <c:otherwise>${vesselTypeExt.rbExt.rb.rbValue}</c:otherwise>
                                    </c:choose>
                                  </c:when>
                                  <c:otherwise>
                                    <c:set var="selectVesselTypeStr">
                                      <c:choose>
                                        <c:when test="${fn:length(vesselTypeExt.contractInspectionRateExtList) > 0}">
                                          selectVesselType('${vesselTypeExt.vesselType.vesselTypeId.vesselType}');
                                        </c:when>
                                        <c:otherwise>
                                          popSelectProducts('${vesselTypeExt.vesselType.vesselTypeId.vesselType}');
                                        </c:otherwise>
                                      </c:choose>
                                    </c:set>
                                    <a href="#" onclick="${selectVesselTypeStr}">
                                      <c:choose>
                                        <c:when test="${vesselTypeExt.rbExt.notesRb != null}">${vesselTypeExt.rbExt.notesRb.rbValue}</c:when>
                                        <c:otherwise>${vesselTypeExt.rbExt.rb.rbValue}</c:otherwise>
                                      </c:choose>
                                    </a>
                                  </c:otherwise>
                                </c:choose>
                              </td>
                            </c:forEach>
                          </tr>
                        </c:forEach>                                
                      </table>
                    </td>
                  </tr>
                  <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt != null}">
                    <tr>
                      <td colspan="5">                  
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:1px; width:100%">
                          <tr>
                            <th>
                              <c:choose>
                                <c:when test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.rbExt.notesRb != null}">
                                  ${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.rbExt.notesRb.rbValue}
                                </c:when>
                                <c:otherwise>
                                  ${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.rbExt.rb.rbValue}
                                </c:otherwise>
                              </c:choose>
                              &nbsp;&nbsp;
                              <img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
                              &nbsp;&nbsp;
                              <c:choose>
                                <c:when test="${not command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.displayAll}">
                                  <a href="#" onclick="javascript:doOperation('showAll');" style="cursor:hand;"><span>Show All</span></a>
                                </c:when>
                                <c:otherwise>
                                  <a href="#" onclick="javascript:doOperation('showTabs');" style="cursor:hand;"><span>Show Tabs</span></a>
                                </c:otherwise>
                              </c:choose>
                            </th>
                          </tr>
                          
                          <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.vesselMaxInd != null and command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.vesselMaxInd}">
                            <tr>
                              <td>
                                Vessel Max: 
                                <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].vesselMax" cssClass="inputBox" size="5"/>
                                <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].vesselMax" cssClass="redstar"/>            
                                <a href="#"  onClick="doFillVesselMax();">Fill</a>
                              </td>
                            </tr>
                          </c:if>
                          <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.vesselMaxInd != null and command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.towMaxInd and 'InlandBarge' == command.inspectionVersionExtList[command.activeInspectionVersionIndex].vesselTypeExtList[command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex].vesselType.vesselTypeId.vesselType}">
                            <tr>
                              <td>
                                Tow Max: 
                                <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].towMaxInspectionRateExt.inspectionRate.maximumPrice" cssClass="inputBox" size="5"/>
                                <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].towMaxInspectionRateExt.inspectionRate.maximumPrice" cssClass="redstar"/>            
                              </td>
                            </tr>
                          </c:if>
                          <tr>
                            <td style="background-color:#F7F7F7;">
                              <c:choose>
                                <c:when test="${not command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.displayAll}">
                                  <!-- TABS CONTENTS -->
                                  <div id="tabcontainer4"  style="background-color:#F7F7F7;">
                                    <div id="tabnav4" style="background-color:#F7F7F7;">
                                      <ul>
                                        <li><a rel="tab41" style="cursor:hand;" onclick="doSetInspectionRateTab('0');"><span>Basic</span></a></li>
                                        <li><a rel="tab42" style="cursor:hand;" onclick="doSetInspectionRateTab('1');"><span>Additional Rate Info</span></a></li>
                                        <li><a rel="tab43" style="cursor:hand;" onclick="doSetInspectionRateTab('2');"><span>Hidden</span></a></li>
                                      </ul>
                                    </div>
                                    <!-- Sub Menus container. Do not remove -->
                                    <div id="tab_inner4">
                                      <!-- ------------------------- TAB 41 CONTAINER ----------------------------------------- -->
                                      <div id="tab41" class="innercontent" >
                                        <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                          <tbody>
                                            <tr>
                                              <td class="TDShade" style="padding:2px;">
                                                <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                                  <tr>
                                                    <th width="5%" class="TDShadeBlue">
                                                      <form:checkbox path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].checkAll" onclick="javascript:selectAll(this)" />
                                                    </th>
                                                    <th width="20%" class="TDShadeBlue">Product Group<span class="redstar">*</span></th>
                                                    <th width="10%" class="TDShadeBlue">Zone <span class="redstar">*</span></th>
                                                    <th width="15%" class="TDShadeBlue">Base Price </th>
                                                    <th width="15%" class="TDShadeBlue">Unit Price </th>
                                                    <th width="12%" class="TDShadeBlue">Min Price </th>
                                                    <th width="12%" class="TDShadeBlue">Addl Grade</th>
                                                    <th>&nbsp;</th>
                                                    <th width="12%" class="TDShadeBlue">Units Included</th>
                                                    <th width="5%" class="TDShadeBlue">
                                                      <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].rowsToAdd" cssClass="inputBox" size="5"/>
                                                      <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].rowsToAdd" cssClass="redstar"/>                                    
                                                    </th>
                                                  </tr>
                                                  <c:forEach items="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.contractInspectionRateExtList}" var="inspectionRateExt" varStatus="inspectionRateExtStatus">
                                                    <tr>
                                                      <td class="TDShadeBlue">
                                                        <form:checkbox path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].checked" disabled="${command.viewOnly}"/>
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:select id="sel1" onchange="doChangeProductGroup('fromTab1');" cssClass="selectionBox" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.groupId">
                                                          <form:options items="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].productGroupDropDownList}" itemLabel="name" itemValue="value"/>
                                                        </form:select>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.groupId" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:select id="sel2" cssClass="selectionBox" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.location">
                                                          <form:options items="${zoneListDropDownItemsWithAll}" itemLabel="name" itemValue="value"/>
                                                        </form:select>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.location" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.basePrice" cssClass="inputBox" size="6"/>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.basePrice" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.unitPrice" cssClass="inputBox" size="6"/>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.unitPrice" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.minimumPrice" cssClass="inputBox" size="6"/>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.minimumPrice" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData0" cssClass="inputBox" size="6"/>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData0" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:checkbox path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.intData0" value="1"/>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.intData0" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.unitsIncluded" cssClass="inputBox" size="6"/>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.unitsIncluded" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <a href="#"  onClick="doAddInspectionRate('${inspectionRateExtStatus.index}');">
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
                                            <tr>
                                              <td class="TDShade" style="padding:2px;">
                                                <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                                  <tr>
                                                    <th width="18%" class="TDShadeBlue">Product Group<span class="redstar">*</span></th>
                                                    <th width="12%" class="TDShadeBlue">Max Price</th>
                                                    <th width="12%" class="TDShadeBlue">Min Range<span class="redstar">*</span></th>
                                                    <th width="12%" nowrap class="TDShadeBlue">Max Range<span class="redstar">*</span></th>
                                                    <th width="12%" nowrap class="TDShadeBlue">UOM<span class="redstar">*</span></th>
                                                    <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd != null and command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd}">
                                                      <th width="12%" nowrap class="TDShadeBlue">Addl Vessel Price</th>
                                                    </c:if>
                                                    <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd != null and command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.numGradesInd}">
                                                      <th width="12%" nowrap class="TDShadeBlue">Min Grades</th>
                                                      <th width="12%" nowrap class="TDShadeBlue">Addl Float Data</th>
                                                    </c:if>
                                                    <th width="12%" nowrap class="TDShadeBlue">Contract Ref </th>
                                                  </tr>
                                                  <c:forEach items="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.contractInspectionRateExtList}" var="inspectionRateExt" varStatus="inspectionRateExtStatus">
                                                    <tr>
                                                      <td class="TDShadeBlue">
                                                        <form:select id="sel1" onchange="doChangeProductGroup('fromTab2');" cssClass="selectionBox" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].groupIdTab2">
                                                          <form:options items="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].productGroupDropDownList}" itemLabel="name" itemValue="value"/>
                                                        </form:select>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].groupIdTab2" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.maximumPrice" cssClass="inputBox" size="6"/>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.maximumPrice" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.intData2"cssClass="inputBox" size="6" />
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.intData2" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.intData3" cssClass="inputBox" size="6"/>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.intData3" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">
                                                        <form:select id="sel1" cssClass="selectionBox" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.intData4" items="${uomList}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                                                      </td>
                                                      <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd != null and command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd}">
                                                        <td class="TDShadeBlue">
                                                          <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData1" cssClass="inputBox" size="6"/>
                                                          <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData1" cssClass="redstar"/>                                    
                                                        </td>
                                                      </c:if>
                                                      <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd != null and command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.numGradesInd}">
                                                        <td class="TDShadeBlue">
                                                          <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.floatData2" cssClass="inputBox" size="6"/>
                                                          <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.floatData2" cssClass="redstar"/>                                    
                                                        </td>
                                                        <td class="TDShadeBlue">
                                                          <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData3" cssClass="inputBox" size="6"/>
                                                          <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData3" cssClass="redstar"/>                                    
                                                        </td>
                                                      </c:if>
                                                      <td class="TDShadeBlue">
                                                        <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.contractRef" cssClass="inputBox" size="6"/>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.contractRef" cssClass="redstar"/>                                    
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

                                      <!-- ------------------------- TAB 44 CONTAINER ----------------------------------------- -->
                                      <div id="tab43" class="innercontent" >
                                        <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                                          <tbody>
                                            <tr>
                                              <td colspan="5" class="TDShade" style="padding:2px;">
                                                <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                                  <tr>
                                                    <th width="18%" class="TDShadeBlue">Product Group<span class="redstar">*</span></th>
                                                    <th width="12%" class="TDShadeBlue">Rate ID</th>
                                                    <th width="12%" class="TDShadeBlue">Contract Id</th>
                                                    <th width="12%" nowrap class="TDShadeBlue">Vessel Type</th>
                                                    <th width="12%" class="TDShadeBlue">Group ID</th>
                                                    <th width="12%" nowrap class="TDShadeBlue">With Inspection</th>
                                                    <th width="12%" nowrap class="TDShadeBlue">Row #</th>
                                                    <th width="12%" nowrap class="TDShadeBlue">Service Level Id</th>
                                                    <th width="12%" nowrap class="TDShadeBlue">Is Deleted</th>
                                                  </tr>
                                                  <c:forEach items="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.contractInspectionRateExtList}" var="inspectionRateExt" varStatus="inspectionRateExtStatus">
                                                    <tr>
                                                      <td class="TDShadeBlue">
                                                        <form:select id="sel1" onchange="doChangeProductGroup('fromTab3');" cssClass="selectionBox" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].groupIdTab3">
                                                          <form:options items="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].productGroupDropDownList}" itemLabel="name" itemValue="value"/>
                                                        </form:select>
                                                        <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].groupIdTab3" cssClass="redstar"/>                                    
                                                      </td>
                                                      <td class="TDShadeBlue">${inspectionRateExt.inspectionRate.rateId}</td>
                                                      <td class="TDShadeBlue">${inspectionRateExt.inspectionRate.inspectionRateId.contractId}</td>
                                                      <td class="TDShadeBlue">${inspectionRateExt.inspectionRate.inspectionRateId.vesselType}</td>
                                                      <td class="TDShadeBlue">${inspectionRateExt.inspectionRate.inspectionRateId.groupId}</td>
                                                      <td class="TDShadeBlue">${inspectionRateExt.inspectionRate.inspectionRateId.withInspection}</td>
                                                      <td class="TDShadeBlue">${inspectionRateExtStatus.index}</td>
                                                      <td class="TDShadeBlue">${inspectionRateExt.inspectionRate.inspectionRateId.serviceLevel}</td>
                                                      <td class="TDShadeBlue">&nbsp;</td>
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
                                      <tr>
                                        <td class="TDShade" style="padding:2px;">
                                          <div id="inspectionRateShowAllDiv" style="width:1000px;overflow:auto;">
                                            <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                                              <tr>
                                                <th width="5%" class="TDShadeBlue">
                                                  <form:checkbox path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].checkAll" onclick="javascript:selectAll(this)" />
                                                </th>
                                                <th width="5%" class="TDShadeBlue"><nobr>Product Group<span class="redstar">*</span></nobr></nobr></th>
                                                <th width="5%" class="TDShadeBlue"><nobr>Zone <span class="redstar">*</span></nobr></th>
                                                <th width="5%" class="TDShadeBlue"><nobr>Base Price</nobr></th>
                                                <th width="5%" class="TDShadeBlue"><nobr>Unit Price</nobr></th>
                                                <th width="5%" class="TDShadeBlue"><nobr>Min Price</nobr></th>
                                                <th width="5%" class="TDShadeBlue"><nobr>Max Price</nobr></th>
                                                <th width="5%" class="TDShadeBlue"><nobr>Addl Grade</nobr></th>
                                                <th>&nbsp;</th>
                                                <th width="5%" nowrap class="TDShadeBlue"><nobr>Units Included <span class="redstar"></span></nobr></th>
                                                <th width="5%" class="TDShadeBlue"><nobr>Min Range<span class="redstar">*</span></nobr></th>
                                                <th width="5%" nowrap class="TDShadeBlue"><nobr>Max Range<span class="redstar">*</span></nobr></th>
                                                <th width="5%" class="TDShadeBlue"><nobr>UOM<span class="redstar">*</span></nobr></th>
                                                <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd != null and command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd}">
                                                  <th width="5%" nowrap class="TDShadeBlue">Addl Vessel Price</th>
                                                </c:if>
                                                <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd != null and command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.numGradesInd}">
                                                  <th width="5%" nowrap class="TDShadeBlue">Min Grades</th>
                                                  <th width="5%" nowrap class="TDShadeBlue">Addl Float Data</th>
                                                </c:if>
                                                <th width="5%" nowrap class="TDShadeBlue"><nobr>Contract Ref</nobr></th>
                                                <th width="5%" class="TDShadeBlue"><nobr>Rate ID</nobr></th>
                                                <th width="5%" class="TDShadeBlue"><nobr>Contract Id</nobr></th>
                                                <th width="2%" nowrap class="TDShadeBlue"><nobr>With Inspection</nobr></th>
                                                <th width="2%" nowrap class="TDShadeBlue"><nobr>Row #</nobr></th>
                                                <th width="2%" nowrap class="TDShadeBlue"><nobr>Service Level Id</nobr></th>
                                                <th width="2%" nowrap class="TDShadeBlue"><nobr>Is Deleted</nobr></th>
                                                <th width="5%" class="TDShadeBlue">
                                                  <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].rowsToAdd" cssClass="inputBox" size="5"/>
                                                  <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].rowsToAdd" cssClass="redstar"/>                                    
                                                </th>
                                              </tr>
                                              <c:forEach items="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.contractInspectionRateExtList}" var="inspectionRateExt" varStatus="inspectionRateExtStatus">
                                                <tr>
                                                  <td class="TDShadeBlue">
                                                    <form:checkbox path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].checked" disabled="${command.viewOnly}"/>
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:select id="sel1" onchange="doChangeProductGroup('fromTab1');" cssClass="selectionBox" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.groupId">
                                                      <form:options items="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].productGroupDropDownList}" itemLabel="name" itemValue="value"/>
                                                    </form:select>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.groupId" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:select id="sel1" cssClass="selectionBox" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.location">
                                                      <form:options items="${zoneListDropDownItemsWithAll}" itemLabel="name" itemValue="value"/>
                                                    </form:select>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.location" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.basePrice" cssClass="inputBox" size="6"/>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.basePrice" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.unitPrice" cssClass="inputBox" size="6"/>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.unitPrice" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.minimumPrice" cssClass="inputBox" size="6"/>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.minimumPrice" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.maximumPrice" cssClass="inputBox" size="6"/>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.maximumPrice" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData0" cssClass="inputBox" size="6"/>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData0" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:checkbox path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.intData0" value="1"/>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.intData0" cssClass="redstar"/>                                    
                                                  </td>                                                  
                                                  <td class="TDShadeBlue">
                                                    <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.unitsIncluded" cssClass="inputBox" size="6"/>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.unitsIncluded" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.intData2"cssClass="inputBox" size="6" />
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.intData2" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.intData3" cssClass="inputBox" size="6"/>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.intData3" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">
                                                    <form:select id="sel1" cssClass="selectionBox" path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.intData4" items="${uomList}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                                                  </td>
                                                  <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd != null and command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd}">
                                                    <td class="TDShadeBlue">
                                                      <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData1" cssClass="inputBox" size="6"/>
                                                      <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData1" cssClass="redstar"/>                                    
                                                    </td>
                                                  </c:if>
                                                  <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.additonalVesselInd != null and command.inspectionVersionExtList[command.activeInspectionVersionIndex].inspectionVersion.numGradesInd}">
                                                    <td class="TDShadeBlue">
                                                      <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.floatData2" cssClass="inputBox" size="6"/>
                                                      <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.inspectionRateId.floatData2" cssClass="redstar"/>                                    
                                                    </td>
                                                    <td class="TDShadeBlue">
                                                      <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData3" cssClass="inputBox" size="6"/>
                                                      <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.floatData3" cssClass="redstar"/>                                    
                                                    </td>
                                                  </c:if>
                                                  <td class="TDShadeBlue">
                                                    <form:input path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.contractRef" cssClass="inputBox" size="6"/>
                                                    <form:errors path="inspectionVersionExtList[${command.activeInspectionVersionIndex}].vesselTypeExtList[${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExtIndex}].contractInspectionRateExtList[${inspectionRateExtStatus.index}].inspectionRate.contractRef" cssClass="redstar"/>                                    
                                                  </td>
                                                  <td class="TDShadeBlue">${inspectionRateExt.inspectionRate.rateId}</td>
                                                  <td class="TDShadeBlue">${inspectionRateExt.inspectionRate.inspectionRateId.contractId}</td>
                                                  <td class="TDShadeBlue">${inspectionRateExt.inspectionRate.inspectionRateId.withInspection}</td>
                                                  <td class="TDShadeBlue">${inspectionRateExtStatus.index}</td>
                                                  <td class="TDShadeBlue">${inspectionRateExt.inspectionRate.inspectionRateId.serviceLevel}</td>
                                                  <td class="TDShadeBlue">&nbsp;</td>
                                                  <td class="TDShadeBlue">
                                                    <a href="#"  onClick="doAddInspectionRate('${inspectionRateExtStatus.index}');">
                                                      <img src="images/add.gif" alt="Copy Row" width="14" height="14" border="0" />
                                                    </a>
                                                  </td>
                                                </tr>
                                              </c:forEach>
                                              <tr><td colspan="20">&nbsp;</td></tr>
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
                        </table>                  
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <a href="#" onclick="popSelectProducts('${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.vesselType.vesselTypeId.vesselType}');">Select Products</a>                      
                        <a href="#" onclick="doOperation('deleteSelectedProducts');">Delete Selected</a>                      
                      </td>
                    </tr>
                  </c:if>
                </tbody>
              </table>
            </td>
          </tr>
          </c:if>
        </tbody>
      </table>
    </td>
  </tr>
  <tr>
    <th width="50%">&nbsp;</th>
    <th width="25%">&nbsp;</th>
    <th>&nbsp;</th>
    <th>&nbsp;</th>
    <th width="25%">&nbsp;
      <c:if test="${command.contractSearch != null}">
        <a href="#" onClick="javascript:doOperation('searchContract');">
          <img src="images/icofindjob.gif" alt="Back to Search Contract" width="16" height="14" border="0" align="absmiddle">
        </a>&nbsp;&nbsp;
        <a href="#" onClick="javascript:doOperation('prevContract');">
          <img src="images/prevleftarrow.gif" alt="Go to Previous Contract" width="13" height="12" hspace="1px" border="0"/>
        </a> &nbsp;&nbsp;
        <a href="#" onClick="javascript:doOperation('nextContract');">
          <img src="images/nextrtarrow.gif" alt="Go to Next Contract" width="13" height="12" hspace="1px" border="0"/>
        </a> &nbsp;&nbsp;
      </c:if>
      <c:if test="${not command.viewOnly}">
        <a href="#"  onClick="doOperation('saveContract');">
          <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
        </a>
      </c:if>
    </th>
  </tr>
</table>
        
<script type="text/javascript">

  <c:if test="${not command.inspectionVersionExtList[command.activeInspectionVersionIndex].otherCheckBoxesViewOnly}">
    <c:if test="${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt != null}">
      <c:if test="${not command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.displayAll}">
        dolphintabs4.init("tabnav4", '${command.inspectionVersionExtList[command.activeInspectionVersionIndex].activeVesselTypeExt.inspectionRateTab}')
      </c:if>
    </c:if>
  </c:if>

</script>
        
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td style="text-align:right"><!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> -->&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>


<script>
  var inspectionRateShowAllDiv = document.getElementById("inspectionRateShowAllDiv");
  if(inspectionRateShowAllDiv)
  {
    var wWidth;
    if (window.innerWidth) wWidth = window.innerWidth;
    if (document.body.offsetWidth) wWidth = document.body.offsetWidth;
    inspectionRateShowAllDiv.style.width = wWidth * 0.85 + "px";
  }
</script>
