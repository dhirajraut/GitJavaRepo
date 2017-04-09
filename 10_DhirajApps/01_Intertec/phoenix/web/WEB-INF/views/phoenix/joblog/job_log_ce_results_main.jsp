<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script	type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript"	src="js/ce/common.js"></script>

<script type="text/javascript" src="js/calendar.js?random=20060118"></script>
<script type="text/javaScript" src="js/lookup.js"></script>
<script type="text/javascript" src="js/services.js"></script>

<script	type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript"	src="js/ce/common.js"></script>



<form:form name="jobLogCeResultForm" method="POST"  action="phx_job_log_ce_results_main.htm">

<form:hidden id="requestAction" path="requestAction"/>
<form:hidden id="tabNavigationTo" path="tabNavigationTo"/>
<form:hidden id="tabNavigationFrom" path="tabNavigationFrom"/>

<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="border-left-width:0px">
	<tr>
	<th style="height:35px;" nowrap><a href="#" onclick="javascript:top.shortByField('projectOperationalStatus')"><f:message key="projectOperationalStatus"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('orderStatus')"><f:message key="orderStatus"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('customerName')"><f:message key="customerName"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('modelNumber')"><f:message key="modelNumber"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('taskSampleDescription')"><f:message key="taskSampleDescription"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('taskName')"><f:message key="taskName"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('taskDescription')"><f:message key="taskDescription"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('taskComments')"><f:message key="taskComments"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('taskOperationalStatus')"><f:message key="taskOperationalStatus"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('taskOwningOrg')"><f:message key="taskOwningOrg"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('currentMonthBilled')"><f:message key="currentMonthBilled"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('pastDue')"><f:message key="pastDue"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('currentMonth')"><f:message key="currentMonth"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('currentMonth+1')"><f:message key="currentMonth+1"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('currentMonth+2')"><f:message key="currentMonth+2"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('futureMonth')"><f:message key="futureMonth"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('rowTotalOfRev')"><f:message key="rowTotalOfRev"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('stream')"><f:message key="stream"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('serviceTypeCode')"><f:message key="serviceTypeCode"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('taskReadyDate')"><f:message key="taskReadyDate"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('promisedCompletionDate')"><f:message key="promisedCompletionDate"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('projectManager')"><f:message key="projectManager"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('salesRep')"><f:message key="salesRep"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('taskManager')"><f:message key="taskManager"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('projectOwningOrg')"><f:message key="projectOwningOrg"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('actualReadyDate')"><f:message key="actualReadyDate"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('customerReadyDate')"><f:message key="customerReadyDate"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('serviceLocation')"><f:message key="serviceLocation"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('billStatus')"><f:message key="billStatus"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('serviceOfferingParentName')"><f:message key="serviceOfferingParentName"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('jobDescription')"><f:message key="jobDescription"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('contractID')"><f:message key="contractID"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('customerNumber')"><f:message key="customerNumber"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('quoteNumber')"><f:message key="quoteNumber"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('orderNumber')"><f:message key="orderNumber"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('ecsOrderNumber')"><f:message key="ecsOrderNumber"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('orderAmt')"><f:message key="orderAmt"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('quoteDate')"><f:message key="quoteDate"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('taskStartDate')"><f:message key="taskStartDate"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('taskCompletionDate')"><f:message key="taskCompletionDate"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('orderDate')"><f:message key="orderDate"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('deliverableShipDate')"><f:message key="deliverableShipDate"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('actualStartDate')"><f:message key="actualStartDate"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('poNumber')"><f:message key="poNumber"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('costBudgetHours')"><f:message key="costBudgetHours"/></th>
	<th nowrap><a href="#" onclick="javascript:top.shortByField('creditoverrideby')"><f:message key="creditoverrideby"/></th>
	</tr>
<c:forEach items="${command.result}" var="jobCEResult" varStatus="status">
	
<tr valign='center'>
<td height="25" align='left' valign="middle" nowrap>
<span class="TDShadeBlue">
  <form:select path="result[${status.index}].projectOperationalStatus" cssClass="selectionBox" id="result[${status.index}].projectOperationalStatus" items="${command.operationalStatusList}" itemLabel="name"  itemValue="value"/>
</span></td>

<td align='left' valign="middle"  nowrap='nowrap'><span class="TDShadeBlue">
  <form:select path="result[${status.index}].orderStatus"  id="result[${status.index}].orderStatus" cssClass="selectionBox" items="${command.orderStatusList}" itemLabel="name"  itemValue="value"/>
</span></td>
<td align='left' valign="middle" nowrap >${jobCEResult.customerName}</td>
<td align='left' valign="middle" nowrap >${jobCEResult.modelNumber}</td>
<td align='left' nowrap>
   <form:input path="result[${status.index}].taskSampleDescription" id="result[${status.index}].taskSampleDescription"    cssClass="inputBox" size="24"/>
</td>
<td valign="middle" >${jobCEResult.taskName}</td>
<td align='left' valign="middle" nowrap >${jobCEResult.taskDescription}</td>
<td valign="middle" >
   <form:input path="result[${status.index}].taskComments" id="result[${status.index}].taskComments"    cssClass="inputBox" size="24"/>
</td>

<td align='left' valign="middle" nowrap ><span class="TDShadeBlue">
  <form:select path="result[${status.index}].taskOperationalStatus" cssClass="selectionBox" size= "1" id="result[${status.index}].taskOperationalStatus" items="${command.taskOperationalStatusList}" itemLabel="name"  itemValue="value"/>
</span></td>
<td valign="middle" nowrap >

<form:input path="result[${status.index}].taskOwningOrg" id="result[${status.index}].taskOwningOrg"    cssClass="inputBox" size="40"/>
  <a href="#aa" onClick="javascript:searchPopups('taskOwningOrg','result[${status.index}].taskOwningOrg');">
    <img src="images/lookup.gif" alt="lookup Task Owning Org" width="13" height="13" border="0"></a></td>
<td valign="middle" class="tdr" >
${jobCEResult.currentMonthBilled}</td>
<td valign="middle" class="tdr" >${jobCEResult.pastDue}</td>
<td valign="middle" class="tdr" >${jobCEResult.currentMonth}</td>
<td valign="middle" class="tdr" >${jobCEResult.currentMonthPlus1}</td>
<td valign="middle" class="tdr" >${jobCEResult.currentMonthPlus2}</td>
<td valign="middle" class="tdr" >${jobCEResult.futureMonth}</td>
<td valign="middle" class="tdr" >${jobCEResult.rowTotalOfRev}</td>
<td valign="middle" >${jobCEResult.stream}</td>
<td valign="middle">


<form:select path="result[${status.index}].serviceTypeCode" cssClass="selectionBox" size= "1" id="result[${status.index}].serviceTypeCode" items="${icbfn:dropdown('staticDropdown',serviceType)}" itemLabel="name"  itemValue="value"/>
</td>
<td align='left' valign="middle" nowrap >
<form:input path="result[${status.index}].taskReadyDate" id="result[${status.index}].taskReadyDate"    cssClass="inputBox" size="10" maxlength="10"/>
&nbsp;<a href="#s"	onClick="top.displayCalendar(document.getElementById('result[${status.index}].taskReadyDate'),'${command.dateFormat}',this)">
<img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a>
</td>
<td align='left' valign="middle" nowrap >
<form:input path="result[${status.index}].promiseCompletionDate" id="result[${status.index}].promissedComplaintDate"    cssClass="inputBox" size="10" maxlength="10"/>
&nbsp;
<a href="#s"	onClick="top.displayCalendar(document.getElementById('result[${status.index}].promissedComplaintDate'),'${command.dateFormat}',this)">
<img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a>
</td>
<td align='left' valign="middle"  nowrap>
<form:input path="result[${status.index}].projectManager" id="result[${status.index}].projectManager"    cssClass="inputBox" size="25" />
  <a href="#bb" onClick="javascript:searchPopups('projectManager','result[${status.index}].projectManager');">
	  <img src="images/lookup.gif" alt="lookup Project Manager" width="13" height="13" border="0"></a>
</td>

<td align='left' valign="middle"  nowrap>
<form:input path="result[${status.index}].salesRep" id="result[${status.index}].salesRep"    cssClass="inputBox" size="25" />
  <a href="#cc" onClick="javascript:searchPopups('salesRep','result[${status.index}].salesRep');"> 
  <img src="images/lookup.gif" alt="lookup Project Manager" width="13" height="13" border="0"></a>
</td>

<td align='left' valign="middle"  nowrap>
  <form:input path="result[${status.index}].taskManager" id="result[${status.index}].taskManager"    cssClass="inputBox" size="25" />
  <a href="#dd" onClick="javascript:searchPopups('taskManager','result[${status.index}].taskManager');"> 
  <img src="images/lookup.gif" alt="lookup Project Manager" width="13" height="13" border="0"></a>
</td>

<td align='left' valign="middle"  nowrap>
  <form:input path="result[${status.index}].projectOwningOrg" id="result[${status.index}].projectOwningOrg"    cssClass="inputBox" size="40" />
</td>

<td align='left' valign="middle" nowrap >

<form:input path="result[${status.index}].actualReadyDate" id="result[${status.index}].actualReadyDate"    cssClass="inputBox" size="10" maxlength="10"/>

  &nbsp;
  <a href="#s"	onClick="top.displayCalendar(document.getElementById('result[${status.index}].actualReadyDate'),'${command.dateFormat}',this)">
  <img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a>
</td>

<td align='left' valign="middle" nowrap >
<form:input path="result[${status.index}].customerReadyDate" id="result[${status.index}].customerReadyDate"    cssClass="inputBox" size="10" maxlength="10"/>
  &nbsp;<a href="#s"	onClick="top.displayCalendar(document.getElementById('result[${status.index}].customerReadyDate'),'${command.dateFormat}',this)">
  <img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a>
</td>
<td valign="middle" nowrap >
<span >
<form:input path="result[${status.index}].serviceLocation" id="result[${status.index}].serviceLocation"    cssClass="inputBox" size="32"/>
&nbsp;<a href="#de" onClick="javascript:searchPopups('serviceLocation','result[${status.index}].serviceLocation');">
<img src="images/lookup.gif" width="13" height="13" border="0"/></a></span></td>
<td valign="middle" >
<span ><span class="TDShadeBlue">
${jobCEResult.billStatus}
</span></span></td>
<td valign="middle" >${jobCEResult.serviceOfferingParentName}</td>
<td valign="middle" >
<form:input path="result[${status.index}].jobDescription" id="result[${status.index}].jobDescription"    cssClass="inputBox" size="32"/>
</td>
<td valign="middle" >${jobCEResult.contractno}</td>
<td valign="middle" >${jobCEResult.customerNumber}</td>
<td valign="middle" >${jobCEResult.quoteNumber}</td>
<td valign="middle" >${jobCEResult.orderNumber}</td>
<td valign="middle" >${jobCEResult.ecsOrderNumber}</td>
<td valign="middle" class="tdr" >${jobCEResult.orderAmount}</td>
<td align='left' valign="middle" nowrap >${jobCEResult.quoteDate}<a href="#"></a></td>
<td align='left' valign="middle" nowrap >

<form:input path="result[${status.index}].taskStartDate" id="result[${status.index}].taskStartDate"    cssClass="inputBox" size="10" maxlength="10"/>
  &nbsp;
  <a href="#s"	onClick="top.displayCalendar(document.getElementById('result[${status.index}].taskStartDate'),'${command.dateFormat}',this)">
  <img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
<td align='left' valign="middle" nowrap >

<form:input path="result[${status.index}].taskCompletionDate" id="result[${status.index}].taskCompletionDate"    cssClass="inputBox" size="10" maxlength="10"/>
  &nbsp;
  <a href="#s"	onClick="top.displayCalendar(document.getElementById('result[${status.index}].taskCompletionDate'),'${command.dateFormat}',this)">
  <img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
<td align='left' valign="middle" nowrap >${jobCEResult.orderDate}<a href="#"></a></td>
<td align='left' valign="middle" nowrap >${jobCEResult.deliveryShipDate}<a href="#"></a></td>
<td valign="middle" >${jobCEResult.poNumber}</td>
<td valign="middle" >${jobCEResult.costBudgetHrs}</td>
<td valign="middle" >${jobCEResult.creditOverRide} </td>
</tr>
</c:forEach>

</table>

</form:form>
