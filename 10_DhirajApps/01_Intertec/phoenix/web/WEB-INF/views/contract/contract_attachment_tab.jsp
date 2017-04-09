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
      <c:if test="${not command.viewOnly}">
        <a href="#"  onClick="resetform();doOperation('saveContract');">
          <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
        </a>
      </c:if>
    </th>
  </tr>
  <tr>
    <td colspan="3" style="padding:2px;">
      <table cellpadding="0" cellspacing="0" width="100%" class="InnerApplTable">
        <tr>
          <th><f:message key="attachmentDetails"/></th>
        </tr>
        <tr>
          <td style="padding-bottom:6px;padding-left:1px;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-bottom:0px;" >
              <tr valign="center">
                <th width="15" scope="col">&nbsp;</th>
                <th width="25%" align="left" scope="col"><f:message key="fileName"/> </th>
                <th scope="col" align="left"><f:message key="type"/></th>
                <th scope="col" align="left"><f:message key="description"/></th>
                <th width="80" align="left" scope="col"> <f:message key="required"/></th>
                <th align="left" scope="col"> <f:message key="audience"/></th>
                <th align="left" scope="col"> <f:message key="addedBy"/></th>
                <th align="left" scope="col"> <f:message key="date/TimeAdded"/></th>
                <th width="50" align="left" scope="col">&nbsp;</th>
              </tr>

              <icb:list var="contractAttachTypeDropDownItems" items="${icbfn:dropdown('staticDropdown',contractAttachType)}" />
              <icb:list var="audienceTypeDropDownItems" items="${icbfn:dropdown('staticDropdown',audienceType)}" />

              <c:forEach items="${command.contractAttaches}" var="contractAttaches" varStatus="counter">
                <tr valign="center">
                  <td align="center">${counter.index + 1}</td>
                  <td align="left" >
                  <a href="contract_file_view.htm?fileName=${command.contractAttaches[counter.index].systemFileName}" target="_blank">${command.contractAttaches[counter.index].fileName}</a>
                   </td>
                  <td align="left">
                    <form:select cssClass="selectionBox" path="contractAttaches[${counter.index}].attachType" items="${contractAttachTypeDropDownItems}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>   
                    <form:errors path="contractAttaches[${counter.index}].attachType" cssClass="redstar"/>
                  </td>
                  <td align="left">
                    <form:input cssClass="inputBox" size="35" path="contractAttaches[${counter.index}].attachDescr" disabled="${command.viewOnly}"/>
                    <form:errors path="contractAttaches[${counter.index}].attachDescr" cssClass="redstar"/> 
                  </td>
                  <td align="left">
                    <input type="checkbox" name="requiredFlag" disabled="${command.viewOnly}"/>
                    <%--    <form:checkbox path="contractAttaches[${counter.index}].attachDescr" value="0" />
                    <form:errors path="contractAttaches[${counter.index}].attachDescr" cssClass="redstar"/>--%>
                  </td>
                  <td align="center"> 
                    <form:select cssClass="selectionBox" path="contractAttaches[${counter.index}].audience" items="${audienceTypeDropDownItems}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/> 
                    <form:errors path="contractAttaches[${counter.index}].audience" cssClass="redstar"/>  
                  </td>
                  <td align="left">
                    ${command.contractAttaches[counter.index].addedByUserName}
                  </td>
                  <td align="center">
                    ${command.contractAttaches[counter.index].dateTimeAdded}
                  </td>
                  <td style="text-align:center">
                    <c:if test="${not command.viewOnly}">
                      <authz:authorize ifAnyGranted="FileUpload">
                        <a href="#"><img src="images/icodel.gif" alt="Delete Attachment" width="12" height="14" border="0" onclick="onAttachDelete('${counter.index}')"></a>
                      </authz:authorize>
                    </c:if>
                  </td>
                </tr>
              </c:forEach>
            </table>
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
          <td>
            <c:if test="${not command.viewOnly}">
              <authz:authorize ifAnyGranted="FileUpload">
                <input name="Submit2" type="button" class="button1" value="Attach a File" onClick="popup_show('attachpop', 'attachpop_drag', 'attachpop_exit', 'screen-corner', 40, 80); hideIt();" />
              </authz:authorize>
              <authz:authorize ifAnyGranted="FileUploadNoDel">
                <input name="Submit2" type="button" class="button1" value="Attach a File" onClick="popup_show('attachpop', 'attachpop_drag', 'attachpop_exit', 'screen-corner', 40, 80); hideIt();" />
              </authz:authorize>
            </c:if>
          </td>
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
            <c:if test="${not command.viewOnly}">
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
