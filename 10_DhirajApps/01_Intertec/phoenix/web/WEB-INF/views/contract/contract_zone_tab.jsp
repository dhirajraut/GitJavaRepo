<script>
  function doOperationForZone(myOperationType, myIndex)
  {
    var markerIndex = myIndex + 1;
    document.forms[0].action = "edit_contract_zone.htm?contractCode=${command.contract.contractCode}#zone" + markerIndex;
    document.forms[0].operationType.value=myOperationType;
    document.forms[0].zoneIndex.value=myIndex;
    document.forms[0].marker.value="zone" + markerIndex;
    document.forms[0].submit();  
  }

  function doOperationForZoneDelete(myOperationType, myIndex, mySubIndex)
  {
    var markerIndex = myIndex + 1;
    document.forms[0].action = "edit_contract_zone.htm?contractCode=${command.contract.contractCode}#zone" + markerIndex;
    document.forms[0].operationType.value=myOperationType;
    document.forms[0].zoneIndex.value=myIndex;
    document.forms[0].zoneSubIndex.value=mySubIndex;
    document.forms[0].marker.value="zone" + markerIndex;
    document.forms[0].submit();  
  }
  
  function doRefresh(myIndex)
  {
    var markerIndex = myIndex + 1;
    document.forms[0].action = "edit_contract_zone.htm?contractCode=${command.contract.contractCode}#zone" + markerIndex;
    document.forms[0].operationType.value="refresh";
    document.forms[0].marker.value="zone" + markerIndex;
    document.forms[0].submit();  
  }

  function updateBranchIframeSrc(buId, inputFieldId)
  {
    var buName= document.getElementById(buId).value;
    if(buName!= "" && buName!= null)
    {
      document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId=" + inputFieldId + "&div1=jobbranchcode&div2=jobbranchcodebody&excludeAdminType=true&excludeEmptyType=true&buName="+buName+"&formName=" + getMyFormName());
    }
  }
  
  function makeBranchblank(branchId)
  {
    document.getElementById(branchId).value="";
  }

  function selectAll(myIndex, selectAllElement)
  {
    var elementList = document.forms[0].elements;
    for(i=0;i<elementList.length;i++)
    {
      if(elementList[i].type == "checkbox")
      {
        var checkBoxName = elementList[i].name;
        var prefix = 'zoneExtList[' + myIndex + ']';
        var myPosition = checkBoxName.indexOf(prefix);
        if(myPosition >= 0)
        {
          if(selectAllElement.checked) elementList[i].checked=true;
          else elementList[i].checked=false;
        }
      }
    }
  }

  // select branches
  function popSelectBranches(zoneIndex)
  {
    popup_show('branchlist', 'branchlist_drag', 'branchlist_exit', 'screen-corner', 40, 40); 
    hideIt();
    popupboxenable();

    document.forms[0].action = "edit_contract_zone.htm?contractCode=${command.contract.contractCode}#zone" + (zoneIndex + 1);
    document.forms[0].marker.value="zone" + (zoneIndex + 1);

    document.getElementById("branchlistbox").src="edit_contract_select_branches_popup.htm?zoneIndex=" + zoneIndex;             
    document.getElementById("branchlistbox").height = "470px";
  }  

  // zone description list
  function popSelectZone(zoneIndex)
  {
    popup_show('zonedesclist', 'zonedesclist_drag', 'zonedesclist_exit', 'screen-corner', 40, 40); 
    hideIt();
    popupboxenable();

    document.forms[0].action = "edit_contract_zone.htm?contractCode=${command.contract.contractCode}#zone" + (zoneIndex + 1);
    document.forms[0].marker.value="zone" + (zoneIndex + 1);

    document.getElementById("zonedesclistbox").src="edit_contract_select_zone_popup.htm?zoneIndex=" + zoneIndex;             
    document.getElementById("zonedesclistbox").height = "470px";
  }  
</script>

<input type="hidden" name="zoneIndex" value="" />
<input type="hidden" name="zoneSubIndex" value="" />
<form:hidden path="marker" />

<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tr>
    <th width="50%">
      <f:message key="contractid"/><strong>:</strong> ${command.contract.contractCode} 
      <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
      <f:message key="description"/><strong>:</strong> ${command.contract.description}
    </th>
    <th width="20%">
      <span style="text-align:right">
        <f:message key="status"/><strong>:</strong> 
        <f:message key="${command.contract.status}" /> | &nbsp;
        <f:formatDate pattern="${userDateFormat}" value="${command.contract.statusDate}" />
      </span>
    </th>
    <th>&nbsp;</th>
    <th>&nbsp;</th>
    <th width="30%" style="text-align:right">
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
        <form:input cssClass="inputBox" path="numZonesToAdd" size="10" maxlength="10" />
        <form:errors path="numZonesToAdd" cssClass="redstar"/>
        <a href="#" onClick="javascript:doOperation('addZone');">
          <img src="images/add.gif" alt="Add Zone" width="13" height="12" hspace="1px" border="0"/>
        </a> &nbsp;&nbsp;
      </c:if>
      <a href="#${command.marker}" onClick="javascript:contractExpandAll('zone', '${fn:length(command.zoneExtList)}');">
        <img src="images/icoexpandall.gif" alt="Expand All" hspace="2" border="0" align="absmiddle"/>
      </a>&nbsp;
      <a href="#${command.marker}" onClick="javascript:contractCollapseAll('zone', '${fn:length(command.zoneExtList)}');">
        <img src="images/icocollapseall.gif" alt="Collapse All" hspace="2" border="0" align="absmiddle"/>
      </a>&nbsp;&nbsp;
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
            <th><f:message key="Zones"/></th>
          </tr>
          <tr>
            <td style="padding:0">

              <icb:list var="businessUnitDropDownItems" items="${icbfn:dropdown('businessUnit', null)}" />
              
              <c:forEach items="${command.zoneExtList}" var="zoneExt" varStatus="zoneExtStatus">   
                <form:hidden path="zoneExtList[${zoneExtStatus.index}].displayStatus" />
                <!-- -------------------------------------- VESSEL 1 CONTAINER ------------------------------------- -->
                <div id="zone${zoneExtStatus.index}Container" class="customerid">
                  <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                    <tbody>
                      <tr>
                        <th class="TDShade" style="background-image:url(images/arrowblubg.gif);" width="2%"> 
                          <div id="arrowrtz${zoneExtStatus.index}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> 
                            <a href="#zone${zoneExtStatus.index + 1}" onClick="javascript:showzoneWithIndex('${zoneExtStatus.index}');"> 
                              <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/>
                            </a> 
                          </div>
                          <div id="arrowdnz${zoneExtStatus.index}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> 
                            <a href="#zone${zoneExtStatus.index + 1}" onClick="javascript:hidezoneWithIndex('${zoneExtStatus.index}');"> 
                              <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/>
                            </a> 
                          </div>
                        </th>
                        <td width="18%" class="TDShade" >
                          <a name="#zone${zoneExtStatus.index + 1}"><f:message key="ZoneId"/> ${zoneExtStatus.index + 1}:</a>
                          <form:errors path="zoneExtList[${zoneExtStatus.index}]" cssClass="redstar"/>
                        </td>
                        <td width="32%" class="TDShadeBlue">
                          <form:radiobutton onclick="doOperationForZone('swichCustomPriceBook', ${zoneExtStatus.index});" path="zoneExtList[${zoneExtStatus.index}].customFlag" value="Custom" disabled="${(not zoneExt.editable) or command.viewOnly}"/>
                          <f:message key="Custom"/>
                          <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].customZoneId" size="35" maxlength="35" disabled="${(not zoneExt.editable) or command.viewOnly or command.zoneExtList[zoneExtStatus.index].customFlag == 'Pricebook'}" />
                          <form:errors path="zoneExtList[${zoneExtStatus.index}].customZoneId" cssClass="redstar"/>
                        </td>
                        <td class="TDShadeBlue" >
                          <form:radiobutton onclick="doOperationForZone('swichCustomPriceBook', ${zoneExtStatus.index});" path="zoneExtList[${zoneExtStatus.index}].customFlag" value="Pricebook" disabled="${(not zoneExt.editable) or command.viewOnly}"/>
                          <strong>
                            <f:message key="Pricebook"/>
                            <form:select id="sel1" cssClass="selectionBox" path="zoneExtList[${zoneExtStatus.index}].priceBookZoneId" items="${command.priceBookZoneIdList}" disabled="${(not zoneExt.editable) or command.viewOnly or command.zoneExtList[zoneExtStatus.index].customFlag == 'Custom'}"/>
                          </strong>
                          &nbsp;&nbsp;&nbsp;
                          <c:if test="${zoneExt.editable and (not command.viewOnly) and command.zoneExtList[zoneExtStatus.index].customFlag == 'Pricebook'}">
                            <a href="#" onClick="popSelectZone(${zoneExtStatus.index});">Select Zone</a></th>
                          </c:if>
                        </td>
                        <td width="60" class="TDShadeBlue" align="right">
                          <c:if test="${zoneExt.editable and (not command.viewOnly)}">
                            <div id="div2" style="text-align:center; width:50px;"> 
                              <a href="#"  onClick="javascript:doOperationForZone('deleteZone', ${zoneExtStatus.index});">
                                <img src="images/delete.gif" alt="Delete Zone" width="13" height="12" hspace="1px" border="0"/>
                              </a> 
                            </div>
                          </c:if>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                  <!-- ----------------------------CONTRACT 1  ---------------------------------------- -->
                  <div id="zone0${zoneExtStatus.index}Container" class="contractContainer">
                    <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                      <tr>
                        <th colspan="11">
                          <f:message key="ZoneAdjustments"/>: 
                        </th>
                        <th width="50">
                          <c:if test="${not command.viewOnly}">
                            <div id="div2" style="text-align:center; width:50px;"> 
                              <a href="#" onClick="javascript:doOperationForZone('addLocationDiscount', ${zoneExtStatus.index});">
                                <img src="images/add.gif" alt="Add Zone Adjustment" width="13" height="12" hspace="1px" border="0"/>
                              </a>
                            </div>
                          </c:if>
                        </th>
                      </tr>
                      <tr>
                        <td><f:message key="BeginDate"/></td>
                        <td><f:message key="EndDate"/></td>
                        <td><f:message key="AddlDiscount"/></td>
                        <td><f:message key="ZoneDiscount"/></td>
                        <td><f:message key="LabFactor"/></td>
                        <td><f:message key="OPSFactor"/></td>
                        <td><f:message key="LabDiscount"/></td>
                        <td><f:message key="OPSDiscount"/></td>
                        <td><f:message key="InspDiscount"/></td>
                        <td><f:message key="TestDiscount"/></td>
                        <td><f:message key="SlateDiscount"/></td>
                        <td width="50">&nbsp;</td>
                      </tr>
                      <c:forEach items="${zoneExt.locationDiscounts}" var="locationDiscount" varStatus="locationDiscountStatus">   
                        <tr>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].locationDiscountId.beginDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                            <a href="#" onClick="displayCalendar(document.forms[0].elements['zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].locationDiscountId.beginDate'],'${userDateFormat}',this)"> 
                              <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                            </a>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].locationDiscountId.beginDate" cssClass="redstar"/>
                            <div id="debug"></div>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].endDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                            <a href="#" onClick="displayCalendar(document.forms[0].elements['zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].endDate'],'${userDateFormat}',this)"> 
                              <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                            </a>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].endDate" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].cfgDiscountPercent" size="6" maxlength="12" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].cfgDiscountPercent" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].zoneDiscountPercent" size="6" maxlength="12" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].zoneDiscountPercent" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].labFactor" size="6" maxlength="12" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].labFactor" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].opsFactor" size="6" maxlength="12" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].opsFactor" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].labDiscountPercent" size="6" maxlength="12" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].labDiscountPercent" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].opsDiscountPercent" size="6" maxlength="12" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].opsDiscountPercent" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].inspectionDiscountPercent" size="6" maxlength="12" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].inspectionDiscountPercent" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].testDiscountPercent" size="6" maxlength="12" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].testDiscountPercent" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].slateDiscountPercent" size="6" maxlength="12" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].locationDiscounts[${locationDiscountStatus.index}].slateDiscountPercent" cssClass="redstar"/>
                          </td>
                          <td width="50">
                            <c:if test="${not command.viewOnly}">
                              <div id="div2" style="text-align:center; width:50px;"> 
                                <a href="#" onClick="javascript:doOperationForZoneDelete('deleteLocationDiscount', ${zoneExtStatus.index}, '${locationDiscountStatus.index}');">
                                  <img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/>
                                </a>
                              </div>
                            </c:if>
                          </td>
                        </tr>    
                      </c:forEach>
                    </table>
                    <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                      <tr>
                        <th colspan="6"><f:message key="Branches"/>: 
                          <c:if test="${not command.viewOnly}">
                          &nbsp;&nbsp;&nbsp;
                          <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                          &nbsp;&nbsp;&nbsp;
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].beginDate" size="10" maxlength="12" />
                            <a href="#" onClick="displayCalendar(document.forms[0].elements['zoneExtList[${zoneExtStatus.index}].beginDate'],'${userDateFormat}',this)"> 
                              <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                            </a>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].beginDate" cssClass="redstar"/>
                            &nbsp;&nbsp;&nbsp;
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].endDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                            <a href="#" onClick="displayCalendar(document.forms[0].elements['zoneExtList[${zoneExtStatus.index}].endDate'],'${userDateFormat}',this)"> 
                              <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                            </a>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].endDate" cssClass="redstar"/>
                            <a href="#" onClick="javascript:doOperationForZone('applyDatesForAllBranches', ${zoneExtStatus.index});">Apply Dates</a>
                            &nbsp;&nbsp;&nbsp;
                            <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                            &nbsp;&nbsp;&nbsp;
                            <a href="#" onClick="javascript:doOperationForZone('deleteSelectedBranches', ${zoneExtStatus.index});">Delete Selected</a>
                            &nbsp;&nbsp;&nbsp;
                            <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                            &nbsp;&nbsp;&nbsp;
                            <a href="#" onClick="popSelectBranches(${zoneExtStatus.index});">Select Branches</a></th>
                          </c:if>
                        </th>
                        <th width="50">
                          <c:if test="${not command.viewOnly}">
                            <div id="div2" style="text-align:center; width:50px;"> 
                              <a href="#" onClick="javascript:doOperationForZone('addBranchLocation', ${zoneExtStatus.index});">
                                <img src="images/add.gif" alt="Add Branch" width="13" height="12" hspace="1px" border="0"/>
                              </a>
                            </div>
                          </c:if>
                        </th>
                      </tr>
                      <tr>
                        <td width="5">
                          <form:checkbox path="zoneExtList[${zoneExtStatus.index}].checkAll" onclick="javascript:selectAll('${zoneExtStatus.index}', this)" disabled="${command.viewOnly}"/>
                        </td>
                        <td><f:message key="BusinessUnit"/></td>
                        <td><f:message key="BranchCode"/></td>
                        <td><f:message key="Description"/></td>
                        <td><f:message key="BeginDate"/></td>
                        <td><f:message key="EndDate"/></td>
                        <td width="50">&nbsp;</td>
                      </tr>
                      <c:forEach items="${zoneExt.branchLocationExts}" var="branchLocationExt" varStatus="branchLocationExtStatus">   
                        <tr>
                          <td>
                            <form:checkbox path="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].checked" disabled="${command.viewOnly}" />                  
                          </td>
                          <td>
                            <form:select id="sel1" cssClass="selectionBoxbig"
                                      path="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.branchSetId"
                                      items="${businessUnitDropDownItems}"
                                      itemLabel="name" itemValue="value" onchange="makeBranchblank('branchZoneExt${zoneExtStatus.index}BranchLocation${branchLocationExtStatus.index}')" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.branchSetId" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input id="branchZoneExt${zoneExtStatus.index}BranchLocation${branchLocationExtStatus.index}" cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.branchCode" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.branchCode" cssClass="redstar"/>
                            <a href="#" onClick="javascript:updateBranchIframeSrc('buZoneExt${zoneExtStatus.index}BranchLocation${branchLocationExtStatus.index}', 'zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.branchCode');popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 120, 20);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()">
                              <img src=" images/lookup.gif" alt="Lookup Branch" width="13" height="13" border="0" />
                            </a>                           
                          </td>
                          <td>
                            ${branchLocationExt.branchDescription}
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.beginDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                            <a href="#" onClick="displayCalendar(document.forms[0].elements['zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.beginDate'],'${userDateFormat}',this)"> 
                              <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                            </a>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.beginDate" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.endDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                            <a href="#" onClick="displayCalendar(document.forms[0].elements['zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.endDate'],'${userDateFormat}',this)"> 
                              <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                            </a>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.endDate" cssClass="redstar"/>
                          </td>
                          <td width="50">
                            <c:if test="${not command.viewOnly}">
                              <div id="div2" style="text-align:center; width:50px;"> 
                                <a href="#" onClick="javascript:doOperationForZoneDelete('deleteBranchLocation', ${zoneExtStatus.index}, '${branchLocationExtStatus.index}');">
                                  <img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/>
                                </a>
                              </div>
                            </c:if>
                          </td>
                        </tr>
                 
                        <ajax:autocomplete
                          baseUrl="branch.htm"
                          source="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.branchCode"
                          target="zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.branchCode"
                          className="autocomplete"             
                          parameters="branchName={zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.branchCode},buName={zoneExtList[${zoneExtStatus.index}].branchLocationExts[${branchLocationExtStatus.index}].branchLocation.branchLocationId.branchSetId}"
                          minimumCharacters="3" />
                      </c:forEach>
                    </table>
                    <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                      <tr>
                        <th colspan="3"><f:message key="Ports"/>: </th>
                        <th width="50">
                          <c:if test="${not command.viewOnly}">
                            <div id="div2" style="text-align:center; width:50px;"> 
                              <a href="#" onClick="javascript:doOperationForZone('addPortLocation', ${zoneExtStatus.index});">
                                <img src="images/add.gif" alt="Add Port" width="13" height="12" hspace="1px" border="0"/>
                              </a>
                            </div>
                          </c:if>
                        </th>
                      </tr>
                      <tr>
                        <td><f:message key="PortCode"/></td>
                        <td><f:message key="BeginDate"/></td>
                        <td><f:message key="EndDate"/></td>
                        <td width="50">&nbsp;</td>
                      </tr>
                      <c:forEach items="${zoneExt.portLocations}" var="portLocation" varStatus="portLocationStatus">   
                        <tr>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].portLocations[${portLocationStatus.index}].portLocationId.portCode" size="45" maxlength="45" disabled="${command.viewOnly}"/>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].portLocations[${portLocationStatus.index}].portLocationId.portCode" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].portLocations[${portLocationStatus.index}].portLocationId.beginDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                            <a href="#" onClick="displayCalendar(document.forms[0].elements['zoneExtList[${zoneExtStatus.index}].portLocations[${portLocationStatus.index}].portLocationId.beginDate'],'${userDateFormat}',this)"> 
                              <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                            </a>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].portLocations[${portLocationStatus.index}].portLocationId.beginDate" cssClass="redstar"/>
                          </td>
                          <td>
                            <form:input cssClass="inputBox" path="zoneExtList[${zoneExtStatus.index}].portLocations[${portLocationStatus.index}].endDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                            <a href="#" onClick="displayCalendar(document.forms[0].elements['zoneExtList[${zoneExtStatus.index}].portLocations[${portLocationStatus.index}].endDate'],'${userDateFormat}',this)"> 
                              <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                            </a>
                            <form:errors path="zoneExtList[${zoneExtStatus.index}].portLocations[${portLocationStatus.index}].endDate" cssClass="redstar"/>
                          </td>
                          <td width="50">
                            <c:if test="${not command.viewOnly}">
                              <div id="div2" style="text-align:center; width:50px;"> 
                                <a href="#" onClick="javascript:doOperationForZoneDelete('deletePortLocation', ${zoneExtStatus.index}, '${portLocationStatus.index}');">
                                  <img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/>
                                </a>
                              </div>
                            </c:if>
                          </td>
                        </tr>
                      </c:forEach>
                    </table>
                  </div><!-- -------------CONTRACT 1 END ----------------------- -->
                </div>
                <!-- -------------------------------------- Contract 1 Container END --------------------------------------- -->                 
              </c:forEach>
            </td>
          </tr>
        </tbody>
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
          <td style="text-align:right">
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
            <a href="#${command.marker}" onClick="javascript:contractExpandAll('zone', '${fn:length(command.zoneExtList)}');">
              <img src="images/icoexpandall.gif" alt="Expand All" hspace="2" border="0" align="absmiddle"/>
            </a>&nbsp;
            <a href="#${command.marker}" onClick="javascript:contractCollapseAll('zone', '${fn:length(command.zoneExtList)}');">
              <img src="images/icocollapseall.gif" alt="Collapse All" hspace="2" border="0" align="absmiddle"/>
            </a>&nbsp;&nbsp;
            <c:if test="${not command.viewOnly}">
              <a href="#"  onClick="doOperation('saveContract');">
                <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
              </a>
            </c:if>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
