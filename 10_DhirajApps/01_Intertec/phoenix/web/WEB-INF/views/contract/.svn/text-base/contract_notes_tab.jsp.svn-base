<!-- ------------------------- TAB 9 CONTAINER ----------------------------------------- -->

	  <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
     <tr>
     <th width="60%">
      <f:message key="contractid"/>: ${command.contract.contractCode}
      <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/>
      <f:message key="description"/> : ${command.contract.description} 
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
        </a> 
      </c:if>
	   <c:if test="${not command.viewOnly}">
		  <a href="#"  onClick="doOperation('saveContract');">
			<img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
		  </a>
       </c:if>
    </th>
  </tr>
      <tr><td colspan="3" style="padding:2px;">
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
      <tr>
      <th colspan="4" align="left">Notes</th>
      </tr>
		<tr>
		<td class="TDShadeBlue"><f:message key="contractCreatedby"/>:</td>
		<td id="addedby" class="TDShadeBlue">${command.contractAddBy}</td>
		<td class="TDShadeBlue" style="text-align:right;"><f:message key="contractAddedDateTime"/>:</td>
		<td id="datetimeadded" class="TDShadeBlue">${command.contractAddDateTime}</td>
		</tr>
		<tr>
		<td class="TDShadeBlue"><f:message key="contractLastModifiedBy"/>:</td>
		<td id="addedby" class="TDShadeBlue">${command.contractModiBy}</td>
		
		<td class="TDShadeBlue" style="text-align:right;"><f:message key="contractLastModifiedDateTime"/>:</td>
		<td id="datetimeadded" class="TDShadeBlue">${command.contractModiDateTime}</td>
		</tr>
		<tr>
		<td class="TDShadeBlue"><f:message key="summary"/>:<span class="redstar">*</span></td>
		<td  class="TDShadeBlue"><form:input cssClass="inputBox" id="notesmry" size="39"  maxlength="100" tabindex="47" path="noteSummary" />
		<form:errors path="noteSummary" cssClass="redstar"/>
		</td>
		<td class="TDShadeBlue"></td><td class="TDShadeBlue"></td>
		</tr>
		<tr>
		<td class="TDShadeBlue"><f:message key="details"/>:</td>
		<td  class="TDShadeBlue"><form:textarea path="note"  rows="4" cols="40" id="notedetails" tabindex="49"/>
		<form:errors path="note"  cssClass="redstar"/>
		</td>
		<tdclass="TDShadeBlue"></td><td class="TDShadeBlue"></td>
		</tr>
		<tr>
		<td class="TDShadeBlue" colspan="4" align="center">
		<input type="button" value="Save Note" class="button1" onclick="beforesubmit();"/>
		<input type="button"  value="Reset"  class="button1"  onclick="onReset();"/>
		</td>
		</tr>
	</table>
	</td></tr>
	<tr>
	<td colspan="3" style="padding:2px;">
	<table width="100%" align="left" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
	<tr>
	<th width="25%"><f:message key="note"/></th><th width="25%"><f:message key="noteAddedBy"/></th><th width="25%"><f:message key="noteAddedDateTime"/></th><th width="25%"><f:message key="action"/></th>
	</tr>
	<c:forEach items="${command.contractNotesList}" var="contractNotesList" varStatus="counter">
	<tr>
	<td><a href="#" onclick="selectNote('${counter.index}');" >${contractNotesList.noteSummary}</a></td>
	<td>${contractNotesList.addedByUserName}</td>
	<td>${contractNotesList.dateTimeAdded}</td>
	<td><a href="#"><img src="images/icodel.gif" alt="Delete Note" width="12" height="14" border="0" onclick="if(confirmDelete()){ onContractNoteDelete('${counter.index}'); }"></a></td>
	</tr>
	</c:forEach>
	</table>	
	<tr>
    <th colspan="2">&nbsp;</th>
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
        </a>
      </c:if>
	  <c:if test="${not command.viewOnly}">
		  <a href="#"  onClick="doOperation('saveContract');">
			<img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
		  </a>
      </c:if>
    </th>
  </tr>
</td>
</tr>
</table>		
<!-- ------------------------- TAB 9 CONTAINER END ----------------------------------------- -->
