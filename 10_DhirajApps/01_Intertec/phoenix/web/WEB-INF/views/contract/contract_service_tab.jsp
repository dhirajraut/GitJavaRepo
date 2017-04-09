<SCRIPT LANGUAGE="JavaScript">
  <!--
  // select services
  function popSelectServices()
  {
    popup_show('servicelist', 'servicelist_drag', 'servicelist_exit', 'screen-corner', 40, 80); 
    hideIt();
    popupboxenable();

    document.getElementById("servicelistbox").src="edit_contract_select_services_popup.htm";             
    document.getElementById("servicelistbox").height = "470px";
  }  
  //-->
</SCRIPT>
<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tr>
    <th width="40%">
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
    <th width="35%" style="text-align:right">
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
    <td colspan="3" style="padding:2px;" class="TDShadeBlue">
      <table width="100%" cellpadding="0" cellspacing="0" style="width:100%" border="0" class="InnerApplTable">
        <tbody>
          <tr>
            <th>Services</th>
          </tr>
          <tr>
            <td class="TDShadeblue" style="margin-left:0px;padding-left:0px;">
              <table width="100%" cellpadding="0" cellspacing="0" style="width:100%" border="0">
                <tr>
                  <td width="20%" style="margin-left:2px;padding-left:2px;" valign="top">
                    <table width="100%" cellpadding="0" cellspacing="0" style="width:100%; margin-top:16px;" border="0" class="InnerApplTable" align="left">
                      <tbody>
                        <tr>
                          <th>Services To Edit &nbsp;&nbsp;<a href="#" onClick="popSelectServices();">Select Services</a></th>
                        </tr>
                        <c:forEach items="${command.selectedHighLevelServiceList}" var="highLevelService" varStatus="highLevelServiceStatus">
                          <tr>
                            <c:choose>
                              <c:when test="${command.highLevelServiceExt != null and command.highLevelServiceExt.highLevelService.highLevelServiceId == highLevelService.highLevelServiceId}">
                                <c:choose>
                                  <c:when test="${icbfn:contains(command.oldHighLevelServices, highLevelService)}">
                                    <td>${highLevelService.serviceDescription}</td>
                                  </c:when>
                                  <c:otherwise>
                                    <td style="color:red;">${highLevelService.serviceDescription}</td>
                                  </c:otherwise>
                                </c:choose>
                              </c:when>
                              <c:otherwise>
                                <c:choose>
                                  <c:when test="${icbfn:contains(command.oldHighLevelServices, highLevelService)}">
                                    <td><a href="#" onClick="doOperation('displayService', '${highLevelService.highLevelServiceId}');">${highLevelService.serviceDescription}</a></td>
                                  </c:when>
                                  <c:otherwise>
                                    <td><a href="#" onClick="doOperation('displayService', '${highLevelService.highLevelServiceId}');" style="color:red;">${highLevelService.serviceDescription}</a></td>
                                  </c:otherwise>
                                </c:choose>
                              </c:otherwise>
                            </c:choose>
                          </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </td>
                  <td valign="top">
                    <c:if test="${command.highLevelServiceExt != null}">
                      <div id="Inspection" style="width:100%;margin-left:2px; ">
                        <table width="100%" cellpadding="0" cellspacing="0" style="width:100%" border="0">
                          <tr>
                            <td>
                              ${command.highLevelServiceExt.highLevelService.serviceDescription}:
                              &nbsp;&nbsp;&nbsp;&nbsp;<form:checkbox path="highLevelServiceExt.hide" disabled="${command.viewOnly}"/><spring:message code="Hide Level" />                                         
                            </td>
                          </tr>                          
                          <c:forEach items="${command.highLevelServiceExt.serviceExtList}" var="serviceExt" varStatus="serviceExtStatus">
                            <tr>
                              <td>
                                <table width="100%" cellpadding="0" cellspacing="0" style="width:100%;" border="0" class="InnerApplTable" align="left">
                                  <tbody>
                                    <tr>
                                      <th><spring:message code="${serviceExt.service.serviceLevel}" text="${serviceExt.service.serviceLevel}"/> Question</th>
                                      <th>&nbsp;</th>
                                    </tr>
                                    <tr>
                                      <td height="25" class="row1">
                                        Description: &nbsp;${serviceExt.description}
                                      </td>
                                      <td height="25" class="row1" style="width:5%;align:center;">
                                        <nobr><form:checkbox path="highLevelServiceExt.serviceExtList[${serviceExtStatus.index}].hide" disabled="${command.viewOnly}"/><spring:message code="Hide Level" /></nobr>
                                      </td>
                                    </tr>
                                    <c:forEach items="${serviceExt.contractExpressionExtList}" var="ceExt" varStatus="ceExtStatus">
                                      <tr>
                                        <td nowrap style="font-weight:normal" class="row${ceExtStatus.index % 2}">
                                          <a href="#" onClick="doEditRates('${serviceExt.service.serviceId.serviceName}', '${serviceExt.service.serviceLevel}', '${ceExt.contractExpression.contractExpressionId.contractId}', '${ceExt.contractExpression.contractExpressionId.expressionId}');">${ceExt.controlQuestions}</a>
                                        </td>
                                        <td nowrap style="width:5%;font-weight:normal;align:center;" class="row${ceExtStatus.index % 2}">
                                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          <c:if test="${ceExt.itemChangedFlag}">
                                            <input name="chk1" type="checkbox" checked="true" onclick="return false;" style="border:2px;color:green;"/>
                                          </c:if>
                                        </td>
                                      </tr>
                                    </c:forEach>
                                    
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                          </c:forEach>
                        </table>
                      </div>
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

