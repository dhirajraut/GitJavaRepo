<script>  
  function submitSearchForSlatePrice(myOperationType, pageNumber)
  {
    document.contractEditForm.operationType.value=myOperationType;
    document.contractEditForm.pageNumber.value=pageNumber;
    document.contractEditForm.submit();  
  }
</script>

<input type="hidden" name="pageNumber" value="" />
<input type="hidden" name="activeId" value="" />

<input type="hidden" name="slatePriceIndex" value="" />
<input type="hidden" name="slatePriceSubIndex" value="" />

<form:hidden path="pbSlatePageNumber" />
<form:hidden path="contractSlatePageNumber" />

<c:set var="userDateFormat" value="${icbfn:userDateFormat()}" />

<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tr>
    <th width="60%">
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
    <th width="15%" style="text-align:right">
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
    </th>
  </tr>
       
  <icb:list var="zoneListDropDownItems" items="${icbfn:dropdown('zoneListDropDown',localContractCode)}" />
  <tr>
    <td colspan="5" style="padding:2px;">
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:100%" >
        <tbody>
          <tr>
            <th><f:message key="PriceBookSlateMods"/></th>
            <th align="right">
              <c:choose>
                <c:when test="${command.pbSlatePageNumber > 0}">
                  <c:forEach items="${command.priceBookSlatePricePagination.pages}" var="page" varStatus="status">
                    <c:choose>
                      <c:when test="${page.selected}">${page.name}</c:when>
                      <c:otherwise>
                        <a href="#start" onClick="submitSearchForSlatePrice('searchPriceBookSlatePrice', '${page.pageNumber}')">${page.name} </a>&nbsp;
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>
                  &nbsp;&nbsp;&nbsp;<a href="#" onClick="doOperation('showAllForPB');">Show All</a>
                </c:when>
                <c:otherwise>
                  <a href="#" onClick="doOperation('showPagesForPB');">Show Pages</a>
                </c:otherwise>
              </c:choose>
              
              &nbsp;&nbsp;From: <form:input cssClass="inputBox" id="pbFromDate" path="pbFromDate" size="10" maxlength="12"/>
              <a href="#" onClick="displayCalendar(document.forms[0].pbFromDate,'${userDateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
              &nbsp;&nbsp;From: <form:input cssClass="inputBox" id="pbToDate" path="pbToDate" size="10" maxlength="12"/>
              <a href="#" onClick="displayCalendar(document.forms[0].pbToDate,'${userDateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
              &nbsp;<a href="#" onClick="doOperation('refresh');">Apply</a>
            </th>
            <th>
              <div id="div2" style="text-align:center; width:250px;"> 
                <c:if test="${not command.viewOnly}">
                    <a href="#" onclick="javascript:popCopyPbSlatePrice('${command.contract.workingPB}', '${command.contract.contractCode}', '${command.pbSlatePageNumber}')">
                      <f:message key="ModifyPBSlates" />
                    </a> 
                </c:if>
                &nbsp;&nbsp;
                <a href="#priceBookExpandAll" onClick="javascript:contractExpandAll('PriceBookSlatePrice', '${fn:length(command.slatePriceExtList)}');">
                  <img src="images/icoexpandall.gif" alt="Expand All" hspace="2" border="0" align="absmiddle"/>
                </a>&nbsp;
                <a href="#priceBookCollapseAll" onClick="javascript:contractCollapseAll('PriceBookSlatePrice', '${fn:length(command.slatePriceExtList)}');">
                  <img src="images/icocollapseall.gif" alt="Collapse All" hspace="2" border="0" align="absmiddle"/>
                </a>
              </div>
            </th>
          </tr>
          <tr>
            <td colspan="3" style="padding:0">

              <c:forEach items="${command.slatePriceExtList}" var="slatePriceExt" varStatus="slatePriceExtStatus">   
                <!-- -------------------------------------- VESSEL 1 CONTAINER ------------------------------------- -->
                <div id="slatePrice${slatePriceExtStatus.index}Container" class="customerid">
                  <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                    <tbody>
                      <tr>
                        <th class="TDShade" style="background-image:url(images/arrowblubg.gif);" width="2%"> 
                          <div id="priceBookSlatePricearrowrtz${slatePriceExtStatus.index}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> 
                            <a href="#pbSlatePrice${slatePriceExtStatus.index}" onClick="javascript:showPriceBookSlatePriceWithIndex('${slatePriceExtStatus.index}');"> 
                              <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/>
                            </a> 
                          </div>
                          <div id="priceBookSlatePricearrowdnz${slatePriceExtStatus.index}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> 
                            <a href="#pbSlatePrice${slatePriceExtStatus.index}" onClick="javascript:hidePriceBookSlatePriceWithIndex('${slatePriceExtStatus.index}');"> 
                              <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/>
                            </a> 
                          </div>
                        </th>
                        <td width="18%" class="TDShade" >
                          <f:message key="SlateId"/>: ${slatePriceExt.slateId}
                        </td>
                        <td width="32%" class="TDShadeBlue">
                          <f:message key="Description"/>: ${slatePriceExt.slate != null ? slatePriceExt.slate.slateDescription : ''}
                        </td>
                        <td widht="60%">&nbsp</td>
                        <td width="50">
                          <c:if test="${not command.viewOnly}">
                            <div id="div2" style="text-align:center; width:50px;"> 
                              <a href="#" onclick="javascript:popModifySlatePrice('${command.contract.workingPB}', '${command.contract.contractCode}', '${slatePriceExt.slateId}', 'PB', '${command.pbSlatePageNumber}')">
                                <f:message key="Edit" />
                              </a> 
                            </div>
                          </c:if>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                  <!-- ----------------------------CONTRACT 1  ---------------------------------------- -->
                  <div id="priceBookSlatePrice0${slatePriceExtStatus.index}Container" class="contractContainer">
                    <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                      <tr>
                        <th colspan="7">
                          <f:message key="PriceBookSlatePrices"/>: 
                        </th>
                      </tr>
                      <tr>
                        <th style="width:150px;"><f:message key="Zone"/></th>
                        <th style="width:150px;" ><f:message key="BeginDate"/></th>
                        <th style="width:150px;"><f:message key="EndDate"/></th>
                        <th nowrap><f:message key="MinQty"/></th>
                        <th nowrap><f:message key="MaxQty"/></th>
                        <th nowrap><f:message key="UnitPrice"/></th>
                        <th nowrap><f:message key="ContractRef"/></th>
                      </tr>
                      <c:forEach items="${slatePriceExt.priceBookSlatePrices}" var="slatePrice" varStatus="slatePriceStatus">   
                        <c:if test="${(command.pbFromDate == null or slatePrice.slatePriceId.beginDate >= command.pbFromDate) and (command.pbToDate == null or slatePrice.slatePriceId.beginDate <= command.pbToDate)}">
                          <tr valign='center'>
                            <td align='left'>
                              ${slatePrice.slatePriceId.location}
                            </td>
                            <td align='left' nowrap>
                              <f:formatDate pattern="${userDateFormat}" value="${slatePrice.slatePriceId.beginDate}" />
                            </td>
                            <td align='left' nowrap>
                              <f:formatDate pattern="${userDateFormat}" value="${slatePrice.endDate}" />
                            </td>
                            <td align='left' valign="middle" nowrap >
                              ${slatePrice.slatePriceId.minQty}
                            </td>
                            <td align='left' valign="middle" nowrap >
                              ${slatePrice.slatePriceId.maxQty}
                            </td>
                            <td align='left' valign="middle" >
                              ${slatePrice.unitPrice}
                            </td>
                            <td align='left' valign="middle" nowrap >
                              ${slatePrice.contractRef}
                            </td>
                          </tr>
                        </c:if>
                      </c:forEach>
                    </table>
                    <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                      <tr>
                        <th colspan="7">
                          <f:message key="ModifiedSlatePrices"/>: 
                        </th>
                      </tr>
                      <tr>
                        <th style="width:150px;"><f:message key="Zone"/></th>
                        <th style="width:150px;" ><f:message key="BeginDate"/></th>
                        <th style="width:150px;"><f:message key="EndDate"/></th>
                        <th nowrap><f:message key="MinQty"/></th>
                        <th nowrap><f:message key="MaxQty"/></th>
                        <th nowrap><f:message key="UnitPrice"/></th>
                        <th nowrap><f:message key="ContractRef"/></th>
                      </tr>
                      <c:forEach items="${slatePriceExt.slatePrices}" var="slatePrice" varStatus="slatePriceStatus">   
                        <c:if test="${(command.pbFromDate == null or slatePrice.slatePriceId.beginDate >= command.pbFromDate) and (command.pbToDate == null or slatePrice.slatePriceId.beginDate <= command.pbToDate)}">
                          <tr valign='center'>
                            <td align='left'>
                              ${slatePrice.slatePriceId.location}
                            </td>
                            <td align='left' nowrap>
                              <f:formatDate pattern="${userDateFormat}" value="${slatePrice.slatePriceId.beginDate}" />
                            </td>
                            <td align='left' nowrap>
                              <f:formatDate pattern="${userDateFormat}" value="${slatePrice.endDate}" />
                            </td>
                            <td align='left' valign="middle" nowrap >
                              ${slatePrice.slatePriceId.minQty}
                            </td>
                            <td align='left' valign="middle" nowrap >
                              ${slatePrice.slatePriceId.maxQty}
                            </td>
                            <td align='left' valign="middle" >
                              ${slatePrice.unitPrice}
                            </td>
                            <td align='left' valign="middle" nowrap >
                              ${slatePrice.contractRef}
                            </td>
                          </tr>
                        </c:if>
                      </c:forEach>
                    </table>
                  </div>
                </div>
              </c:forEach>
            </td>
          </tr>
        </tbody>
      </table>        
    </td>
  </tr>

  <tr>
    <td colspan="5" style="padding:2px;">
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:100%" >
        <tbody>
          <tr>
            <th><f:message key="CustomerSpecificSlates"/></th>
            <th align="right">
              <c:choose>
                <c:when test="${command.contractSlatePageNumber > 0}">
                  <c:forEach items="${command.contractSlatePricePagination.pages}" var="page" varStatus="status">
                    <c:choose>
                      <c:when test="${page.selected}">${page.name}</c:when>
                      <c:otherwise>
                        <a href="#start" onClick="submitSearchForSlatePrice('searchContractSlatePrice', '${page.pageNumber}')">${page.name} </a>&nbsp;
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>
                  &nbsp;&nbsp;&nbsp;<a href="#" onClick="doOperation('showAllForContract');">Show All</a>
                </c:when>
                <c:otherwise>
                  <a href="#" onClick="doOperation('showPagesForContract');">Show Pages</a>
                </c:otherwise>
              </c:choose>
              &nbsp;&nbsp;From: <form:input cssClass="inputBox" id="contractFromDate" path="contractFromDate" size="10" maxlength="12"/>
              <a href="#" onClick="displayCalendar(document.forms[0].contractFromDate,'${userDateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
              &nbsp;&nbsp;From: <form:input cssClass="inputBox" id="contractToDate" path="contractToDate" size="10" maxlength="12"/>
              <a href="#" onClick="displayCalendar(document.forms[0].contractToDate,'${userDateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
              &nbsp;<a href="#" onClick="doOperation('refresh');">Apply</a>
            </th>
            <th>
              <div id="div2" style="text-align:center; width:250px;"> 
                <c:if test="${not command.viewOnly}">
                  <a href="#" onclick="javascript:popCreateContractSlate('${command.contract.contractCode}', '${command.contractSlatePageNumber}')">
                    <f:message key="CreateContractSlate" />
                  </a> 
                </c:if>
                &nbsp;&nbsp;
                <a href="#contractExpandAll" onClick="javascript:contractExpandAll('ContractSlatePrice', '${fn:length(command.contractSlatePriceExtList)}');">
                  <img src="images/icoexpandall.gif" alt="Expand All" hspace="2" border="0" align="absmiddle"/>
                </a>&nbsp;
                <a href="#contractCollapseAll" onClick="javascript:contractCollapseAll('ContractSlatePrice', '${fn:length(command.contractSlatePriceExtList)}');">
                  <img src="images/icocollapseall.gif" alt="Collapse All" hspace="2" border="0" align="absmiddle"/>
                </a>
              </div>
            </th>
          </tr>
          <tr>
            <td colspan="3" style="padding:0">

              <c:forEach items="${command.contractSlatePriceExtList}" var="contractSlatePriceExt" varStatus="contractSlatePriceExtStatus">   
                <!-- -------------------------------------- VESSEL 1 CONTAINER ------------------------------------- -->
                <div id="contractSlatePrice${contractSlatePriceExtStatus.index}Container" class="customerid">
                  <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                    <tbody>
                      <tr>
                        <th class="TDShade" style="background-image:url(images/arrowblubg.gif);" width="2%"> 
                          <div id="contractSlatePricearrowrtz${contractSlatePriceExtStatus.index}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> 
                            <a href="#contractSlatePrice${contractSlatePriceExtStatus.index}" onClick="javascript:showContractSlatePriceWithIndex('${contractSlatePriceExtStatus.index}');"> 
                              <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/>
                            </a> 
                          </div>
                          <div id="contractSlatePricearrowdnz${contractSlatePriceExtStatus.index}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> 
                            <a href="#contractSlatePrice${contractSlatePriceExtStatus.index}" onClick="javascript:hideContractSlatePriceWithIndex('${contractSlatePriceExtStatus.index}');"> 
                              <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/>
                            </a> 
                          </div>
                        </th>
                        <td width="18%" class="TDShade" >
                          <f:message key="SlateId"/>: ${contractSlatePriceExt.slateId}
                        </td>
                        <td width="32%" class="TDShadeBlue">
                          <f:message key="Description"/>: ${contractSlatePriceExt.contractSlate.slate != null ? contractSlatePriceExt.contractSlate.slate.slateDescription : ''}
                        </td>
                        <td widht="60%">&nbsp</td>
                        <td width="50">
                          <c:if test="${not command.viewOnly}">
                            <div id="div2" style="text-align:center; width:50px;"> 
                              <a href="#" onclick="javascript:popModifySlatePrice('${command.contract.workingPB}', '${command.contract.contractCode}', '${contractSlatePriceExt.slateId}', 'CUSTOMER', '${command.contractSlatePageNumber}')">
                                <f:message key="Edit" />
                              </a> 
                            </div>
                          </c:if>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                  <!-- ----------------------------CONTRACT 1  ---------------------------------------- -->
                  <div id="contractSlatePrice0${contractSlatePriceExtStatus.index}Container" class="contractContainer">
                    <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                      <tr>
                        <th style="width:150px;"><f:message key="Zone"/></th>
                        <th style="width:150px;" ><f:message key="BeginDate"/></th>
                        <th style="width:150px;"><f:message key="EndDate"/></th>
                        <th nowrap><f:message key="MinQty"/></th>
                        <th nowrap><f:message key="MaxQty"/></th>
                        <th nowrap><f:message key="UnitPrice"/></th>
                        <th nowrap><f:message key="ContractRef"/></th>
                      </tr>
                      <c:forEach items="${contractSlatePriceExt.slatePrices}" var="slatePrice" varStatus="slatePriceStatus">   
                        <c:if test="${(command.contractFromDate == null or slatePrice.slatePriceId.beginDate >= command.contractFromDate) and (command.contractToDate == null or slatePrice.slatePriceId.beginDate <= command.contractToDate)}">
                          <tr valign='center'>
                            <td align='left'>
                              ${slatePrice.slatePriceId.location}
                            </td>
                            <td align='left' nowrap>
                              <f:formatDate pattern="${userDateFormat}" value="${slatePrice.slatePriceId.beginDate}" />
                            </td>
                            <td align='left' nowrap>
                              <f:formatDate pattern="${userDateFormat}" value="${slatePrice.endDate}" />
                            </td>
                            <td align='left' valign="middle" nowrap >
                              ${slatePrice.slatePriceId.minQty}
                            </td>
                            <td align='left' valign="middle" nowrap >
                              ${slatePrice.slatePriceId.maxQty}
                            </td>
                            <td align='left' valign="middle" >
                              ${slatePrice.unitPrice}
                            </td>
                            <td align='left' valign="middle" nowrap >
                              ${slatePrice.contractRef}
                            </td>
                          </tr>
                        </c:if>                          
                      </c:forEach>
                    </table>
                  </div>
                </div>
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
    <td>&nbsp;</td>
    <td width="15%" style="text-align:right">
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
    </td>
  </tr>
</table>    
