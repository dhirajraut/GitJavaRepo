<script>
  function changeCfgContractData(myOperationType, myIndex)
  {
    document.forms[0].operationType.value=myOperationType;
    document.forms[0].cfgContractIndex.value=myIndex;
    document.forms[0].submit();  
  }
  
  function addCfgContract()
  {
    document.forms[0].operationType.value="addCfgContract";
    document.forms[0].submit();  
  }
</script>

<icb:list var="activeCurrencyCDList">
  <icb:item>${icbfn:activeCurrencyCD(command)}</icb:item>
</icb:list>

<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tbody>
    <tr>
      <th class="TDShade" colspan="6">
        <f:message key="contractid"/><strong>:</strong>${command.contract.contractCode} 
        <img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
        <f:message key="status"/><strong>:</strong> <f:message key="${command.contract.status}" />
        <img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
        <f:message key="workingPB"/><strong> :</strong>
        <form:select id="sel1" cssClass="selectionBox" path="contract.workingPB" items="${icbfn:dropdown('workingPriceBookIdDropdown',activeCurrencyCDList)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
        <img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
        <f:message key="workingUOM"/><strong> :</strong>
        <form:select id="sel2" cssClass="selectionBox" path="contract.workingUOM" items="${icbfn:dropdown('staticDropdown',workingUOM)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
        <img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
        <f:message key="masterListDate"/><strong>:</strong>
        <form:input cssClass="inputBox" id="masterListDate" path="contract.masterListDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
        <c:if test="${not command.viewOnly}">
          <a href="#" onClick="displayCalendar(document.forms[0].masterListDate,'${userDateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
        </c:if>
        <form:errors path="contract.masterListDate" cssClass="redstar"/>   
      </th>
      <th style="text-align:right"> 
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
          <a href="#" onClick="javascript:addCfgContract();">
            <img src="images/add.gif" alt="Add Contract Version" width="13" height="12" hspace="1px" border="0"/>
          </a> &nbsp;&nbsp;
          <a href="#"  onClick="resetform();doOperation('saveContract');">
            <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
          </a>
        </c:if>
      </th>
    </tr>

    <tr>
      <td class="TDShade">
        <f:message key="contractCode"/><strong>:<span class="redstar">*</span></strong>
      </td>
      <td class="TDShadeBlue">
        <c:choose>
          <c:when test="${command.isNewFlag}"> 
            <form:input cssClass="inputBox" path="contract.contractCode" size="20" maxlength="15" disabled="${command.viewOnly}"/>
          </c:when>
          <c:otherwise>
            <form:input cssClass="inputBox" path="contract.contractCode" size="20" maxlength="15" disabled="true" />
          </c:otherwise>
        </c:choose>          
        <form:errors path="contract.contractCode" cssClass="redstar"/></td>
      </td>  
      <td class="TDShade"><f:message key="description"/><strong>:<span class="redstar">*</span></strong></td>
      <td class="TDShadeBlue" colspan="4" >
        <form:input cssClass="inputBox" path="contract.description" size="35" maxlength="512" disabled="${command.viewOnly}"/>
        <form:errors path="contract.description" cssClass="redstar"/>  
      </td>
    </tr>

    <tr>
      <td class="TDShade"><f:message key="invoiceType"/><strong>:</strong></td>
      <td class="TDShadeBlue"><form:select id="sel3" cssClass="selectionBox" path="contract.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/></td>
      <td class="TDShade"><f:message key="paymentterms"/><strong>:</strong></td>
      <td class="TDShadeBlue"><form:select id="sel4" cssClass="selectionBox" path="contract.paymentTermsCD" items="${icbfn:dropdown('paymentTerms', null)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/></td>
    </tr>

    <tr>
      <td class="TDShade"><f:message key="versions"/><strong>:</strong></td>
      <td class="TDShadeBlue" colspan="5">&nbsp;</td>
    </tr>

    <c:forEach items="${command.cfgContractExtList}" var="cfgContractExt" varStatus="cfgContractExtStatus">   
      <icb:list var="localCurrencyCDList">
        <icb:item>${cfgContractExt.cfgContract.currencyCD}</icb:item>
      </icb:list>

      <icb:list var="localCurrencyCDAndPbSeriesList">
        <icb:item>${cfgContractExt.cfgContract.currencyCD}</icb:item>
        <icb:item>${cfgContractExt.cfgContract.pbSeries}</icb:item>
      </icb:list>

      <icb:list var="localProductGroupSetList">
        <icb:item>${cfgContractExt.cfgContract.productGroupSet}</icb:item>
      </icb:list>

      <icb:list var="localVesselTypeSetList">
        <icb:item>${cfgContractExt.cfgContract.vesselSet}</icb:item>
      </icb:list>

      <!-- Begin Date Div Start -->
      <tr>
        <td colspan="6" style="padding:0;">

          <!-- ------------ Contract 2 CONTAINER ------------- -->
          <div id="begindate${cfgContractExtStatus.index}" class="contracts">
            <table cellpadding="0" cellspacing="0" class="mainApplTable" >
              <tbody>
                <tr>
                  <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> 
                    <div id="arrowrtbd${cfgContractExtStatus.index}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> 
                      <a href="#" onClick="javascript:showbegindatebyindex('${cfgContractExtStatus.index}');"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> 
                    </div>
                    <div id="arrowdnbd${cfgContractExtStatus.index}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> 
                      <a href="#" onClick="javascript:hidebegindatebyindex('${cfgContractExtStatus.index}');"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> 
                    </div>
                  </th>
                  <td width="19%" class="TDShade" >Begin Date : </td>
                  <td width="36%" class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.cfgContractId.beginDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                    <c:if test="${not command.viewOnly}">
                      <a href="#" onClick="displayCalendar(document.forms[0].elements['cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.cfgContractId.beginDate'],'${userDateFormat}',this)"> 
                        <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                      </a>
                    </c:if>
                    <form:errors path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.cfgContractId.beginDate" cssClass="redstar"/>
                  </td>
                  <td width="22%" class="TDShade" ><strong>End Date :</strong></td>
                  <td colspan="2" width="24%" class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.endDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                    <c:if test="${not command.viewOnly}">
                      <a href="#" onClick="displayCalendar(document.forms[0].elements['cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.endDate'],'${userDateFormat}',this)"> 
                        <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                      </a>
                    </c:if>
                    <form:errors path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.endDate" cssClass="redstar"/>
                  </td>
                  <td width="60" class="TDShadeBlue" align="right">&nbsp;</td>
                </tr>
              </tbody>
            </table>

            <!-- ----------------------------CONTRACT 2  ---------------------------------------- -->
            <div id="begindate${cfgContractExtStatus.index}Container" class="contractContainer">
              <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                <tr>
                  <td width="15%">Currency: </td>
                  <td>
                      <form:select id="sel1" onchange="javascript:changeCfgContractData('changeCurrencyCD', '${cfgContractExtStatus.index}');" cssClass="selectionBox" path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.currencyCD" items="${icbfn:dropdown('currency',null)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<f:message key="overridable"/><strong>:</strong>
                  <form:checkbox path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.overridable" disabled="${command.viewOnly}"/>
                  </td>
                  <td width="15%">UOM:</td>
                  <td>
                    <form:select id="sel1" onchange="javascript:changeCfgContractData('changeUOM', '${cfgContractExtStatus.index}');" cssClass="selectionBox" path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.uom" items="${icbfn:dropdown('staticDropdown',workingUOM)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                  </td>
                </tr>
                <tr>
                  <td>Pricebook Id:</td>
                  <td>
                    <form:select id="sel1" onchange="javascript:changeCfgContractData('changePriceBookId', '${cfgContractExtStatus.index}');" cssClass="selectionBox" path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.priceBookId" items="${icbfn:dropdown('priceBookIdDropDown',localCurrencyCDAndPbSeriesList)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                  </td>
                  <td>Series:</td>
                  <td>
                    <form:select id="sel1" onchange="javascript:changeCfgContractData('changePriceBookSeries', '${cfgContractExtStatus.index}');" cssClass="selectionBox" path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.pbSeries" items="${icbfn:dropdown('priceBookSeriesDropDown',localCurrencyCDList)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    Editable?: &nbsp;<form:checkbox path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.editPB" disabled="${command.viewOnly}"/>
                  </td>
                </tr>
                <tr>
                  <td>Annual Escalator:</td>
                  <td><form:input path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.annualEscalator" cssClass="inputBox" disabled="${command.viewOnly}"/></td>
                  <td>Escalator Date: </td>
                  <td>
                    <form:input cssClass="inputBox" path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.escalatorDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
                    <c:if test="${not command.viewOnly}">
                      <a href="#" onClick="displayCalendar(document.forms[0].elements['cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.escalatorDate'],'${userDateFormat}',this)"> 
                        <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                      </a>
                    </c:if>
                  </td>
                </tr>                            
                <tr>
                  <td>Discount Pct:</td>
                  <td><form:input path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.cfgDiscountPercent" cssClass="inputBox" disabled="${command.viewOnly}"/></td>
                  <td>Insp Discount Pct: </td>
                  <td><form:input path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.inspectionDiscountPercent" cssClass="inputBox" disabled="${command.viewOnly}"/></td>
                </tr>
                <tr>
                  <td>Test Discount Pct:</td>
                  <td><form:input path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.testDiscount" cssClass="inputBox" disabled="${command.viewOnly}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hide Test Discount:&nbsp;<form:checkbox path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.hideTestDiscount" disabled="${command.viewOnly}"/></td>
                  <td>Slate Discount Pct: </td>
                  <td><form:input path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.slateDiscount" cssClass="inputBox" disabled="${command.viewOnly}"/></td>
                </tr>
                <tr>
                  <td>Ops Discount Pct:</td>
                  <td><form:input path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.opsDiscountPercent" cssClass="inputBox" disabled="${command.viewOnly}"/></td>
                  <td>Lab Discount Pct:</td>
                  <td><form:input path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.labDiscountPercent" cssClass="inputBox" disabled="${command.viewOnly}"/></td>
                </tr>
                <tr>
                  <td>Product Group Set:</td>
                  <td>
                    <form:select id="sel1" onchange="javascript:doOperation('refresh');" cssClass="selectionBox" path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.productGroupSet" items="${icbfn:dropdown('productGroupSetDropdown',localProductGroupSetList)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                    <c:if test="${not command.viewOnly}">
                      <c:if test="${not command.isNewFlag}">
                        <a href="#" onclick="javascript:popEditProductGroupSet('${cfgContractExtStatus.index}')">
                          <img src="images/icoeditsmall.gif" alt="Edit Product Group Set" border="0"/>
                        </a> 
                      </c:if>
                    </c:if>
                  </td>
                  <td>Vessel Type Set:</td>
                  <td>
                    <form:select id="sel1" onchange="javascript:doOperation('refresh');" cssClass="selectionBox" path="cfgContractExtList[${cfgContractExtStatus.index}].cfgContract.vesselSet" items="${icbfn:dropdown('vesselTypeSetDropdown',localVesselTypeSetList)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                    <c:if test="${not command.viewOnly}">
                      <c:if test="${not command.isNewFlag}">
                        <a href="#" onclick="javascript:popEditVesselTypeSet('${cfgContractExtStatus.index}')">
                          <img src="images/icoeditsmall.gif" alt="Edit Vessel Type Set" border="0"/>
                        </a> 
                      </c:if>
                    </c:if>
                  </td>
                </tr>
              </table>
            </div><!-- -------------CONTRACT 2 END ----------------------- -->
          </div>
        </td>
      <!-- -------------------------- CONTRACT 2 CONTAINER END ---------------------------- -->           </td>
      </tr>
      <!-- Begin Date Div End -->
    </c:forEach>


    <tr>
      <td class="TDShade" style="border-top:#CCCCCC dashed 1px;">
        <f:message key="expirationDate"/><strong>:</strong>
      </td>
      <td class="TDShadeBlue" style="border-top:#CCCCCC dashed 1px;">
        <form:input cssClass="inputBox" id="expireDate"path="contract.expireDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>&nbsp;
        <c:if test="${not command.viewOnly}">
          <a href="#" onClick="displayCalendar(document.forms[0].expireDate,'${userDateFormat}',this)">
            <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
          </a>
        </c:if>
        <form:errors path="contract.expireDate" cssClass="redstar"/>
      </td>
      <td class="TDShade" style="border-top:#CCCCCC dashed 1px;">
        <f:message key="statusdate"/><strong>:</strong>
      </td>
      <td class="TDShadeBlue" style="border-top:#CCCCCC dashed 1px;">
        <form:input cssClass="inputBox" id="statusDate" path="contract.statusDate" size="10" maxlength="12" disabled="${command.viewOnly}"/>
        &nbsp;
        <c:if test="${not command.viewOnly}">
          <a href="#" onClick="displayCalendar(document.forms[0].statusDate,'${userDateFormat}',this)">
            <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
          </a>
        </c:if>
        <form:errors path="contract.statusDate" cssClass="redstar"/>
      </td>
    </tr>

    <tr>
      <td class="TDShade"><f:message key="originator"/><strong>:</strong></td>
      <td class="TDShadeBlue">
        <form:input cssClass="inputBox"  cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="contract.originatorUserName" size="10" maxlength="128" disabled="${command.viewOnly}"/>
        <form:errors path="contract.originatorUserName" cssClass="redstar"/>
        <c:if test="${not command.viewOnly}">
          <a href="#" onClick="javascript:setoriginatorflag();updateOriginatorIframeSrc();popup_show('originator','originator_drag','originator_exit', 'screen-corner', 120,20);hideIt(); showPopupDiv('originator','originatorbody');popupboxenable();">
            <img src=" images/lookup.gif" width="13" height="13" alt="Originator" border="0"/>
          </a>
        </c:if>
      </td>
      <td class="TDShade"><f:message key="signatory"/><strong>:</strong></td>
      <td class="TDShadeBlue" colspan="3">
        <form:input cssClass="inputBox"  cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="contract.signatoryUserName" size="10" maxlength="128" disabled="${command.viewOnly}"/>
        <form:errors path="contract.signatoryUserName" cssClass="redstar"/> 
        <c:if test="${not command.viewOnly}">
          <a href="#" onClick="javascript:setsignatoryflag();updateSignatoryIframeSrc();popup_show('signatory','signatory_drag','signatory_exit', 'screen-corner', 120,20);hideIt(); showPopupDiv('signatory','signatorybody');popupboxenable();">
            <img src=" images/lookup.gif" width="13" height="13" alt="Signatory" border="0"/>
          </a>
        </c:if>
      </td>
    </tr> 

    <tr>
      <td class="TDShade"><f:message key="originatorEmail"/>:</td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" path="contract.originator.email" size="20" maxlength="70" disabled="true" /></td>
        <form:errors path="contract.originator.email" cssClass="redstar"/>
      </td>
      <td class="TDShade"><f:message key="signatoryEmail"/>: </td>
      <td class="TDShadeBlue" colspan="3">
        <form:input cssClass="inputBox" path="contract.signatory.email" size="20" maxlength="70" disabled="true" />
      </td>
      <form:errors path="contract.signatory.email" cssClass="redstar"/></td>
    </tr>

  </tbody>
</table>


<script type="text/javascript">
  <c:if test="${fn:length(command.cfgContractExtList) > 0}">
    showbegindatebyindex('${command.activeCfgContractIndex}');
  </c:if>
</script>

<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tbody>
    <tr>
      <td colspan="3" class="TDShadeBlue" ><f:message key="ReferenceFields"/></td>
    </tr>
        
    <c:forEach items="${command.referenceFieldList}" var="referenceField" varStatus="referenceFieldStatus">   
      <tr>
        <td class="TDShade"><f:message key="ReferenceField"/> ${referenceFieldStatus.index + 1} :</td>
        <td class="TDShadeBlue" width="5%">
          <form:input cssClass="inputBox" path="referenceFieldList[${referenceFieldStatus.index}].referenceFieldId.referenceFieldId" size="15" maxlength="15" disabled="${command.viewOnly}" /></td>
        </td>
        <td class="TDShadeBlue" align="left" width="70%">
          <form:errors path="referenceFieldList[${referenceFieldStatus.index}].referenceFieldId.referenceFieldId" cssClass="redstar"/>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>
            <c:if test="${not command.viewOnly}">
              <c:forEach items="${icbfn:operationsByStateMachine('ContractStateMachine', command.contract.status)}" var="operation">
                <input name="${operation}" type="button" class="button1" value="${operation}" onclick="doOperation('${operation}')" />
              </c:forEach>
            </c:if>
          </td>
          <td align="right"><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry" /></span> </td>
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
            <c:if test="${not command.viewOnly}">
              <a href="#" onClick="javascript:addCfgContract();">
                <img src="images/add.gif" alt="Add Contract Version" width="13" height="12" hspace="1px" border="0"/>
              </a> &nbsp;&nbsp;
              <a href="#"  onClick="resetform();doOperation('saveContract');">
                <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
              </a>
            </c:if>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<ajax:autocomplete
  baseUrl="contract.htm"
  source="contract.originatorUserName"
  target="contract.originator.email"
  className="autocomplete"
  parameters="originator={contract.originatorUserName}"
  minimumCharacters="1" /> 
<ajax:autocomplete
  baseUrl="contract.htm"
  source="contract.signatoryUserName"
  target="contract.signatory.email"
  className="autocomplete"
  parameters="signatory={contract.signatoryUserName}"
  minimumCharacters="1" /> 
