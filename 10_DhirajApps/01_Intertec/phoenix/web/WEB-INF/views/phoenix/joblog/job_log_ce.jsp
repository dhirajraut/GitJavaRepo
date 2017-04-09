<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="icbfn"
	uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript" src="js/ce/common.js"></script>
<script type="text/javascript" src="js/ce/ce_joblog.js"></script>


<form:form name="jobCeSearchForm" method="POST" action="phx_job_log_ce.htm">
	<form:hidden id="requestAction" path="requestAction" />
	<form:hidden id="sortField" path="sortField" />
	<form:hidden id="pageNumber" path="pageNumber" />
	<form:hidden id="tabNavigationTo" path="tabNavigationTo" />
	<form:hidden id="tabNavigationFrom" path="tabNavigationFrom" />

	<!-- MAIN CONTAINER -->
	<!-- --------------------------------- HEADER END ------------------------------------------------ -->
	<table width="97%" border="0" cellpadding="0" cellspacing="0"
		class="MainTable">
		<tr>
			<td valign="top"><!-- BREADCRUMB TRAIL  -->

			<div id="MainContentContainer"><!-- TABS CONTENTS -->
			<div id="tabcontainer">
			<div id="tabnav">
			<ul>
				<li><a href="#" onClick="navenable('searchCriteria');"
					rel="tab1"><span><f:message key="criteria" /></span></a></li>
				<c:if test="${command.requestAction != 'new search'}">
					<li><a href="#" onClick="navenable('main');" rel="tab2"><span><f:message
						key="main" /></span></a></li>
					<li><a href="#" onClick="navenable('billing');" rel="tab4"><span><f:message
						key="billing" /></span></a></li>
					<li><a href="#" onClick="navenable('process');" rel="tab5"><span><f:message
						key="processLog" /></span></a></li>
				</c:if>
			</ul>
			<select name="sel5" id="sel5" class="selectionBoxrt"
				onChange="navenable(this.value)">
				<option selected value='goto'>Go to ... &gt;</option>
				<option value="new search">Job Search</option>
			</select></div>
			<!-- Sub Menus container. Do not remove -->
			<div id="tab_inner1"><!-- Sub Menus container. Do not remove -->

			<!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
			<div id="tab1" class="innercontent">
			<div style="padding: 0px; height: 750px; overflow: auto"><c:if
				test="${command.tabNavigationTo == '' || command.tabNavigationTo=='searchCriteria' }">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="mainApplTable">
					<tr>
						<th colspan="5"><f:message key="searchCriteria" /> <!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --></th>
					</tr>
					<tr>
						<td width="15%" class="TDShade" valign="top"><label
							for="businessUnitName"> <f:message key="orderStatus" /></label> <span
							class="redstar"></span></td>

						<td width="30%" class="TDShadeBlue"><form:select
							cssClass="multiSelectBox" id="tbox" path="searchInfo.orderStatus"
							multiple="true" size="4" items="${command.orderStatusList}"
							itemLabel="name" itemValue="value" /> <form:errors
							path="searchInfo.orderStatus" cssClass="redstar" /></td>

						<td class="TDShade" valign="top"><f:message
							key="taskOperationalStatus" /></td>
						<td class="TDShadeBlue" colspan="2"><form:select
							cssClass="multiSelectBox" id="taskOperationalStatus"
							path="searchInfo.taskOperationalStatus" multiple="true" size="4"
							items="${command.taskOperationalStatusList}" itemLabel="name"
							itemValue="value" /> <form:errors path="searchInfo.taskOperationalStatus"
							cssClass="redstar" /></td>
					</tr>
					<tr>
						<td width="15%" class="TDShade" valign="top"><f:message
							key="projectOperationalStatus" /></td>
						<td width="30%" class="TDShadeBlue"><form:select
							cssClass="multiSelectBox" id="projectOperationalStatus"
							path="searchInfo.projectOperationalStatus" multiple="true" size="4"
							items="${command.operationalStatusList}" itemLabel="name"
							itemValue="value" /> <form:errors path="searchInfo.projectOperationalStatus"
							cssClass="error" /></td>
						<td class="TDShade" valign="top"><f:message key="taskManager" />
						</td>
						<td class="TDShadeBlue" colspan="2" valign="top">
						<TABLE cellspacing="0" cellpadding="0" border="0" id="TaskTable">

							<c:forEach items="${command.searchInfo.taskManagerId}" var="taskId"
								varStatus="counter">
								<c:if test="${taskId !=null}">
									<TR>
										<TD class="nopadding"><form:input
											id="taskManagerId[${counter.index}]" cssClass="inputBoxblue"
											path="searchInfo.taskManagerId[${counter.index}]" size="42"
											maxlength="42" /> <a href="#pm"
											onClick="javascript:lookupFunction('searchInfo.taskManagerId[${counter.index}]','TaskTable');popUserDetails();">
										<img src="images/lookup.gif" alt="lookup Task Manager"
											width="13" height="13" border="0"></a> <c:if
											test="${counter.index ==0}">
											<a href="#" id="taskManagerAdd" class="taskManagerTable"
												onclick=addRow("TaskTable","searchInfo.taskManagerId")> <img
												src="images/add.gif" alt="Add" width="13" height="12"
												hspace="2" border="0" title="Add" /></a>
										</c:if></TD>
										<form:errors path="searchInfo.taskManagerId[${counter.index}]"
											cssClass="redstar" />
									</TR>
								</c:if>
							</c:forEach>
						</TABLE>
						</td>
					</tr>
					<tr>
						<td width="15%" class="TDShade" valign="top"><label
							for="branchName"><f:message key="projectManager" /></label></td>
						<td width="30%" class="TDShadeBlue" valign="top">


						<TABLE cellspacing="0" cellpadding="0" border="0"
							id='ProjectManagerTable'>

							<c:forEach items="${command.searchInfo.projectManagerId}" var="taskId"
								varStatus="counter">
								<c:if test="${taskId !=null}">
									<TR>
										<TD class="nopadding"><form:input
											id="projectManagerId[${counter.index}]"
											cssClass="inputBoxblue"
											path="searchInfo.projectManagerId[${counter.index}]" size="38" /> <a
											href="#pm"
											onClick="javascript:lookupFunction('searchInfo.projectManagerId[${counter.index}]','ProjectManagerTable');popUserDetails();">
										<img src="images/lookup.gif" alt="lookup Project Manager"
											width="13" height="13" border="0"></a> <c:if
											test="${counter.index ==0}">
											<a href="#"
												onclick="addRow('ProjectManagerTable','searchInfo.projectManagerId');">
											<img src="images/add.gif" alt="Add" width="13" height="12"
												hspace="2" border="0" title="Add" /></a>
										</c:if> <form:errors path="searchInfo.projectManagerId[${counter.index}]"
											cssClass="redstar" /></TD>


									</TR>
								</c:if>
							</c:forEach>
						</TABLE>
						</td>
						<td class="TDShade" valign="top">Sales Rep:</td>
						<td class="TDShadeBlue" colspan="2" valign="top">


						<TABLE cellspacing="0" cellpadding="0" border="0"
							id="salesRepTable">
							<c:forEach items="${command.searchInfo.salesRep}" var="taskId"
								varStatus="counter">
								<c:if test="${taskId !=null}">
									<TR>
										<TD class="nopadding"><form:input
											id="salesRep[${counter.index}]" cssClass="inputBoxblue"
											path="searchInfo.salesRep[${counter.index}]" size="42" /> <a href="#pm"
											onClick="javascript:lookupFunction('searchInfo.salesRep[${counter.index}]','salesRepTable');popUserDetails();">
										<img src="images/lookup.gif" alt="lookup Sales Rep" width="13"
											height="13" border="0"></a><a href="#"
											onclick="addRow('salesRepTable','searchInfo.salesRep');"><img
											src="images/add.gif" alt="Add" width="13" height="12"
											hspace="2" border="0" title="Add" /></a> <form:errors
											path="searchInfo.salesRep[${counter.index}]" cssClass="redstar" /></TD>

									</TR>
								</c:if>
							</c:forEach>
						</TABLE>
						</td>
					</tr>
					<tr>
						<td class="TDShade" valign="top"><f:message
							key="taskOwningOrg" /></td>
						<td class="TDShadeBlue" valign="top">

						<TABLE cellspacing="0" cellpadding="0" border="0"
							id="taskOwingTable">
							<c:forEach items="${command.searchInfo.taskOwningOrg}" var="taskId"
								varStatus="counter">
								<c:if test="${taskId !=null}">
									<TR>
										<TD class="nopadding"><form:input
											id="taskOwningOrg[${counter.index}]" cssClass="inputBoxblue"
											path="searchInfo.taskOwningOrg[${counter.index}]" size="38" /> <a
											href="#pm"
											onClick="javascript:lookupFunction('searchInfo.taskOwningOrg[${counter.index}]','taskOwingTable');popJobBranch();">
										<img src="images/lookup.gif" alt="lookup Task Owning Org"
											width="13" height="13" border="0"></a> <c:if
											test="${counter.index ==0}">
											<a href="#"
												onclick="addRow('taskOwingTable','searchInfo.taskOwningOrg');"><img
												src="images/add.gif" alt="Add" width="13" height="12"
												hspace="2" border="0" title="Add" /></a>
										</c:if> <form:errors path="searchInfo.taskOwningOrg[${counter.index}]"
											cssClass="redstar" /></TD>

									</TR>
								</c:if>
							</c:forEach>
						</TABLE>
						</td>
						<td class="TDShade" valign="top"><f:message
							key="projectOwningOrg" /></td>
						<td class="TDShadeBlue" valign="top" colspan="2">
						<TABLE cellspacing="0" cellpadding="0" border="0"
							id="ProjectOwning">
							<c:forEach items="${command.searchInfo.projectOwningOrg}" var="taskId"
								varStatus="counter">
								<c:if test="${taskId !=null}">
									<TR>
										<TD class="nopadding"><form:input
											id="projectOwningOrg[${counter.index}]"
											cssClass="inputBoxblue"
											path="searchInfo.projectOwningOrg[${counter.index}]" size="42" /> <a
											href="#pm"
											onClick="javascript:lookupFunction('searchInfo.projectOwningOrg[${counter.index}]','ProjectOwning');popJobBranch();">
										<img src="images/lookup.gif" alt="lookup Project Owning Org"
											width="13" height="13" border="0"></a> <c:if
											test="${counter.index ==0}">
											<a href="#"
												onclick="addRow('ProjectOwning','searchInfo.projectOwningOrg')"><img
												src="images/add.gif" alt="Add" width="13" height="12"
												hspace="2" border="0" title="Add" /></a>
										</c:if> <form:errors path="searchInfo.projectOwningOrg[${counter.index}]"
											cssClass="redstar" /></TD>

									</TR>
								</c:if>
							</c:forEach>
						</TABLE>
						</td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="taskReadyDate" /></td>
						<td class="TDShadeBlue"><f:message key="from" />:&nbsp; <form:input
							id="taskReadyDateFrom" cssClass="inputBox"
							path="taskReadyDateFrom" size="8" maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('taskReadyDateFrom'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a>&nbsp;&nbsp;&nbsp;<f:message
							key="to" />:&nbsp; <form:errors path="taskReadyDateFrom"
							cssClass="redstar" /> <form:input id="taskReadyDateTo"
							cssClass="inputBox" path="taskReadyDateTo" size="8"
							maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('taskReadyDateTo'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a></td>
						<form:errors path="taskReadyDateTo" cssClass="redstar" />
						<td class="TDShade"><f:message key="promisedComplitionDate" />:
						</td>
						<td class="TDShadeBlue" colspan="2"><f:message key="from" />:&nbsp
						<form:input id="promisedCompletionDateFrom" cssClass="inputBox"
							path="promisedCompletionDateFrom" size="10" maxlength="12" /> <a
							href="#s"
							onClick="displayCalendar(document.getElementById('promisedCompletionDateFrom'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a>&nbsp;&nbsp;&nbsp;<f:message
							key="to" />:&nbsp <form:errors path="promisedCompletionDateFrom"
							cssClass="redstar" /> <form:input id="promisedCompletionDateTo"
							cssClass="inputBox" path="promisedCompletionDateTo" size="10"
							maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('promisedCompletionDateTo'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a></td>
						<form:errors path="promisedCompletionDateFrom" cssClass="redstar" />
					</tr>
					<tr>
						<td class="TDShade"><f:message key="stream" />:</td>
						<td class="TDShadeBlue"><form:input id="stream"
							cssClass="inputBoxblue" path="searchInfo.stream" size="38" /> <a href="#pm"
							onClick="javascript:lookupFunction('searchInfo.stream','stream');popup_show('buStream','buStream_drag','buStream_exit', 'screen-corner',	120,20);hideIt();showPopupDiv('buStream','buStreambody');popupboxenable();">
						<img src="images/lookup.gif" alt="lookup Stream" width="13"
							height="13" border="0"></a> <form:errors path="searchInfo.stream"
							cssClass="redstar" /></td>
						<td class="TDShade"><f:message key="actualReadyDate" />:</td>

						<td class="TDShadeBlue" colspan="2"><f:message key="from" />:&nbsp;
						<form:input id="actualReadyDateFrom" cssClass="inputBox"
							path="actualReadyDateFrom" size="10" maxlength="12" /> <a
							href="#s"
							onClick="displayCalendar(document.getElementById('actualReadyDateFrom'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a>&nbsp;&nbsp;&nbsp;To:&nbsp; <form:errors
							path="actualReadyDateFrom" cssClass="redstar" /> <form:input
							id="actualReadyDateTo" cssClass="inputBox"
							path="actualReadyDateTo" size="10" maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('actualReadyDateTo'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a> <form:errors
							path="actualReadyDateTo" cssClass="redstar" /></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="taskName" />:</td>
						<td class="TDShadeBlue"><form:select cssClass="selectionBox"
							id="taskName.op" path="searchInfo.taskName.op"
							items="${command.stringOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="taskName.value"
							cssClass="inputBox" path="searchInfo.taskName.value" size="11" /></td>
						<td class="TDShade"><f:message key="customerReadyDate" />:</td>
						<td class="TDShadeBlue" colspan="2">From:&nbsp; <form:input
							id="customerReadyDateFrom" cssClass="inputBox"
							path="customerReadyDateFrom" size="10" maxlength="12" /> <a
							href="#s"
							onClick="displayCalendar(document.getElementById('customerReadyDateFrom'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a> <form:errors
							path="customerReadyDateFrom" cssClass="redstar" />
						&nbsp;&nbsp;&nbsp;To:&nbsp; <form:input id="customerReadyDateTo"
							cssClass="inputBox" path="customerReadyDateTo" size="10"
							maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('customerReadyDateTo'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a> <form:errors
							path="customerReadyDateTo" cssClass="redstar" /></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="taskDescription" />:</td>
						<td class="TDShadeBlue"><form:select cssClass="selectionBox"
							id="taskDescription.op" path="searchInfo.taskDescription.op"
							items="${command.stringOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="taskDescription.value"
							cssClass="inputBox" path="searchInfo.taskDescription.value" size="11" /></td>

						<td class="TDShade"><f:message
							key="serviceOfferingParentName" />:</td>
						<td class="TDShadeBlue" colspan="2">


						<TABLE cellspacing="0" cellpadding="0" border="0"
							id='ServiceOfferingTable'>

							<c:forEach items="${command.searchInfo.serviceOfferingParentName}"
								var="taskId" varStatus="counter">
								<c:if test="${taskId !=null}">
									<TR>
										<TD class="nopadding"><form:input
											id="serviceOfferingParentName[${counter.index}]"
											cssClass="inputBoxblue"
											path="searchInfo.serviceOfferingParentName[${counter.index}]" size="42" />
										<a href="#pm"
											onClick="javascript:lookupFunction('searchInfo.serviceOfferingParentName[${counter.index}]','ServiceOfferingTable');popServiceOffering();">
										<img src="images/lookup.gif"
											alt="lookup Service Offering Parent Name" width="13"
											height="13" border="0"></a> <c:if
											test="${counter.index ==0}">
											<a href="#"
												onclick="addRow('ServiceOfferingTable','searchInfo.serviceOfferingParentName');"><img
												src="images/add.gif" alt="Add" width="13" height="12"
												hspace="2" border="0" title="Add" /></a>
										</c:if></TD>
									</TR>
								</c:if>
							</c:forEach>

						</TABLE>
						</td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="taskComments" />:</td>
						<td class="TDShadeBlue"><form:select cssClass="selectionBox"
							id="taskComments.op" path="searchInfo.taskComments.op"
							items="${command.stringOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="taskComments.value"
							cssClass="inputBox" path="searchInfo.taskComments.value" size="11" /></td>


						<td class="TDShade" valign="top">Service Type Code:</td>
						<td class="TDShadeBlue"><form:select
							cssClass="multiSelectBox" id="serviceTypeCode"
							path="searchInfo.serviceTypeCode" multiple="true" size="4"
							items="${command.serviceTypeList}" itemLabel="name"
							itemValue="value" /></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="modelNumber" />:</td>
						<td class="TDShadeBlue"><form:select cssClass="selectionBox"
							id="modelNumber.op" path="searchInfo.modelNumber.op"
							items="${command.stringOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="modelNumber.value"
							cssClass="inputBox" path="searchInfo.modelNumber.value" size="11" /></td>
						<td class="TDShade"><f:message key="taskSampleDescription" />:
						</td>
						<td class="TDShadeBlue"><form:select cssClass="selectionBox"
							id="taskSampleDescription.op" path="searchInfo.taskSampleDescription.op"
							items="${command.stringOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="taskSampleDescription.value"
							cssClass="inputBox" path="searchInfo.taskSampleDescription.value" /></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="jobDescription" />:</td>
						<td class="TDShadeBlue"><form:select cssClass="selectionBox"
							id="jobDescription.op" path="searchInfo.jobDescription.op"
							items="${command.stringOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="jobDescription.value"
							cssClass="inputBox" path="searchInfo.jobDescription.value" size="11" /></td>
						<td class="TDShade"><f:message key="serviceLocation" />:</td>
						<td class="TDShadeBlue"><form:select cssClass="selectionBox"
							id="serviceLocation.op" path="searchInfo.serviceLocation.op"
							items="${command.stringOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="serviceLocation.value"
							cssClass="inputBox" path="searchInfo.serviceLocation.value" /></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="customerName" />:</td>
						<td class="TDShadeBlue"><form:select cssClass="selectionBox"
							id="customerName.op" path="searchInfo.customerName.op"
							items="${command.stringOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="customerName.value"
							cssClass="inputBox" path="searchInfo.customerName.value" size="11" /></td>

						<td class="TDShade"><f:message key="contract" />:</td>
						<td class="TDShadeBlue" colspan="2"><form:select
							cssClass="selectionBox" id="contract.op" path="searchInfo.contract.op"
							items="${command.stringOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="contract.value"
							cssClass="inputBox" path="searchInfo.contract.value" /></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="customerNumber" />:</td>
						<td class="TDShadeBlue"><form:select cssClass="selectionBox"
							id="customerNumber.op" path="searchInfo.customerNumber.op"
							items="${command.stringOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="customerNumber.value"
							cssClass="inputBox" path="searchInfo.customerNumber.value" size="11" /></td>
						<td class="TDShade"><f:message key="orderAmt" />:</td>
						<td class="TDShadeBlue" colspan="2"><form:select
							cssClass="selectionBox" id="orderAmount.op" path="searchInfo.orderAmount.op"
							items="${command.numericOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="orderAmount.value"
							cssClass="inputBox" path="searchInfo.orderAmount.value" /></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="projectNumber" />:</td>
						<td class="TDShadeBlue"><form:input id="projectNumber"
							cssClass="inputBox" path="searchInfo.projectNumber.value" size="38" /></td>
						<td class="TDShade"><f:message key="currentMonthBilled" />:</td>
						<td class="TDShadeBlue" colspan="2"><form:select
							cssClass="selectionBox" id="currentMonthBilled.op"
							path="searchInfo.currentMonthBilled.op" items="${command.numericOperator}"
							itemLabel="name" itemValue="value" /> <form:input
							id="currentMonthBilled.value" cssClass="inputBox"
							path="searchInfo.currentMonthBilled.value" /></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="quoteNumber" />:</td>
						<td class="TDShadeBlue"><form:input id="quoteNumber"
							cssClass="inputBox" path="searchInfo.quoteNumber.value" size="38" /></td>
						<td class="TDShade"></td>
						<td class="TDShadeBlue" colspan="2"></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="orderNumber" />:</td>
						<td class="TDShadeBlue"><form:input id="orderNumber"
							cssClass="inputBox" path="searchInfo.orderNumber.value" size="38" /></td>
						<td class="TDShade"></td>
						<td class="TDShadeBlue" colspan="2"></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="ecsOrderNumber" />:</td>
						<td class="TDShadeBlue"><form:input id="ecsOrderNumber"
							cssClass="inputBox" path="searchInfo.ecsOrderNumber.value" size="38" /></td>
						<td class="TDShade"></td>
						<td class="TDShadeBlue" colspan="2"></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="quoteDate" />:</td>

						<td class="TDShadeBlue"><f:message key="from" />:&nbsp; <form:input
							id="quoteDateFrom" cssClass="inputBox" path="quoteDateFrom"
							size="8" maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('quoteDateFrom'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a>&nbsp;&nbsp;&nbsp;<f:message
							key="to" />:&nbsp; <form:input id="quoteDateTo"
							cssClass="inputBox" path="quoteDateTo" size="8" maxlength="12" />
						<a href="#s"
							onClick="displayCalendar(document.getElementById('quoteDateTo'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a></td>

						<td class="TDShade"></td>
						<td class="TDShadeBlue" colspan="2"></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="taskStartDate" />:</td>
						<td class="TDShadeBlue"><f:message key="from" />:&nbsp; <form:input
							id="taskStartDateFrom" cssClass="inputBox"
							path="taskStartDateFrom" size="8" maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('taskStartDateFrom'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a>&nbsp;&nbsp;&nbsp;<f:message
							key="to" />:&nbsp; <form:input id="taskStartDateTo"
							cssClass="inputBox" path="taskStartDateTo" size="8"
							maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('taskStartDateTo'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a></td>
						<td class="TDShade"></td>

						<td class="TDShadeBlue" colspan="2"></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="taskCompletionDate" />:</td>
						<td class="TDShadeBlue"><f:message key="from" />:&nbsp; <form:input
							id="taskCompletionDateFrom" cssClass="inputBox"
							path="taskCompletionDateFrom" size="8" maxlength="12" /> <a
							href="#s"
							onClick="displayCalendar(document.getElementById('taskCompletionDateFrom'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a>&nbsp;&nbsp;&nbsp;<f:message
							key="to" />:&nbsp; <form:input id="taskCompletionDateTo"
							cssClass="inputBox" path="taskCompletionDateTo" size="8"
							maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('taskCompletionDateTo'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a></td>
						<td class="TDShade"><f:message key="rowTotalOfRev" />:</td>
						<td class="TDShadeBlue" colspan="2"><form:select
							cssClass="selectionBox" id="rowTotalOfRev.op"
							path="searchInfo.rowTotalOfRev.op" items="${command.numericOperator}"
							itemLabel="name" itemValue="value" /> <form:input
							id="rowTotalOfRev.value" cssClass="inputBox"
							path="searchInfo.rowTotalOfRev.value" /></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="orderDate" />:</td>
						<td class="TDShadeBlue" colspan="2"><f:message key="from" />:&nbsp;

						<form:input id="orderDateFrom" cssClass="inputBox"
							path="orderDateFrom" size="8" maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('orderDateFrom'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a>&nbsp;&nbsp;&nbsp;<f:message
							key="to" />:&nbsp; <form:input id="orderDateTo"
							cssClass="inputBox" path="orderDateTo" size="8" maxlength="12" />
						<a href="#s"
							onClick="displayCalendar(document.getElementById('orderDateTo'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a></td>
					</tr>
					<tr>
						<td class="TDShade"><f:message key="poNumber" />:</td>
						<td class="TDShadeBlue"><form:select cssClass="selectionBox"
							id="poNumber.op" path="searchInfo.poNumber.op"
							items="${command.numericOperator}" itemLabel="name"
							itemValue="value" /> <form:input id="poNumber.value"
							cssClass="inputBox" path="searchInfo.poNumber.value" size="11" /> <form:errors
							path="searchInfo.poNumber.value" cssClass="redstar" /></td>
						<td class="TDShade"><f:message key="deliverableShipDate" />:
						</td>
						<td class="TDShadeBlue" colspan="2"><f:message key="from" />:&nbsp;
						<form:input id="deliveryShipDateFrom" cssClass="inputBox"
							path="deliveryShipDateFrom" size="8" maxlength="12" /> <a
							href="#s"
							onClick="displayCalendar(document.getElementById('deliveryShipDateFrom'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a>&nbsp;&nbsp;&nbsp;<f:message
							key="to" />:&nbsp; <form:input id="deliveryShipDateTo"
							cssClass="inputBox" path="deliveryShipDateTo" size="8"
							maxlength="12" /> <a href="#s"
							onClick="displayCalendar(document.getElementById('deliveryShipDateTo'),'${command.dateFormat}',this)">
						<img src="images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a> <form:errors
							path="deliveryShipDateTo" cssClass="redstar" /></td>
					</tr>
				</table>

				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="applTableBot">
					<tr>
						<td>
						<table width="99%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td><input name="Search" type="button" class="button1"
									value="Search/Refresh" onClick="searchjob();" /><!-- <input name="Notify" type="button" class="button1" value="Notify" /> --></td>
								<td class="tdr"><!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<script>
	includeAjaxObj('TaskTable','taskManagerId[0]');
	includeAjaxObj('ProjectManagerTable','projectManagerId[0]');
	includeAjaxObj('salesRepTable','salesRep[0]');
	includeAjaxObj('taskOwingTable','taskOwningOrg[0]');
	includeAjaxObj('ProjectOwning','projectOwningOrg[0]');
	includeAjaxObj('ServiceOfferingTable','serviceOfferingParentName[0]');
</script>
			</c:if></div>
			</div>
			<!----------------- TAB 1 CONTAINER END ------------------------------ -->
			<!-- ---------------------------------------------------------------------------------------------------------------------- -->

			<!-- ----------- TAB 2 CONTAINER ------------------ -->
			<div id="tab2" class="innercontent1"><c:if
				test="${command.tabNavigationTo=='main' }">
				<table width="100%" cellpadding=0 cellspacing=0
					class="mainApplTable">
					<tbody>
						<tr bgcolor=#ffffff>
							<th width="65%"><f:message key="searchResultMain" /></th>
							<th nowrap class="thr"><a href="#"
								onclick="javascript:exportToExcel()"><img
								src="images/ico_excel.gif" alt="Downlaod to Excel" width="18"
								height="18" hspace="5" border="0" align="absmiddle"></a> <a
								href="#" onClick="window.location.href='#';"><IMG
								SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5"
								BORDER="0" align="absmiddle"></a> <a href="#"
								onclick="searchjob();"><IMG SRC="images/icorefresh.gif"
								ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5"
								BORDER="0" align="absmiddle"></a> <!--  <a href="#" onclick="popup_show('sendemail', 'sendemail_drag', 'sendemail_exit', 'screen-corner', 40, 80); hideIt();popupboxenable();"><IMG SRC="images/icoemail.gif" ALT="Send email" hspace="5" BORDER="0" align="absmiddle"></a> -->
							<a href="#"
								onclick="showEmailPopup(); popup_show('sendemail', 'sendemail_drag', 'sendemail_exit', 'screen-corner', 40, 80); hideIt();popupboxenable();"><IMG
								SRC="images/icoemail.gif" ALT="Send email" hspace="5" BORDER="0"
								align="absmiddle"></a> <a href="#"
								onClick="javascript:submitFrameForm('frameMain');"><img
								src="images/icosave.gif" alt="Save" width="14" height="14"
								hspace="5" border="0" align="absmiddle" /></a> </a></th>
						</tr>
						<tr>
							<td colspan="2" class="smallpadding">
							<table width="100%" height="100%" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td valign="top" width="125" class="NoPadding">
									<table width="100%" cellpadding="0" cellspacing="0"
										class="InnerApplTable">
										<tr>
											<th nowrap><a href="#"
												onclick="javascript:shortByField('projectno')"><f:message
												key="projectno" /></a></th>
											<th nowrap><a href="#"
												onclick="javascript:shortByField('taskno')"><f:message
												key="taskno" /></a></th>
											<th nowrap><f:message key="email" /><br>
											<input type=checkbox name="checkAll"
												onclick=selectDeselect();></th>
										</tr>
										<c:forEach items="${command.result}" var="jobCEResult"
											varStatus="status">
											<tr>
												<td align='CENTER' nowrap='nowrap' height="25"
													valign="middle"><a href="#"
													onClick="openJobEntry('${jobCEResult.orderNumber}')"
													title="Transfer to Nomination">${jobCEResult.projectNumber}</a></td>
												<td align='CENTER' nowrap='nowrap' height="25"
													valign="middle">${jobCEResult.taskId}</td>
												<td align='CENTER' nowrap='nowrap' height="25"
													valign="middle"><input type=checkbox name="mailCheck"></td>
											</tr>
										</c:forEach>
										<tr>
											<td clospan="2">&nbsp;</td>
										</tr>
									</table>
									</td>
									<td class="nopadding"><iframe
										src="phx_job_log_ce_results_main.htm?reqBy=main" frameborder="0"
										height="100%"
										style="padding: 0px; scrolling: yes; border-right: #DBE2F2 1px solid;"
										width="100%" id="frameMain" name="frameMain"></iframe></td>
								</tr>
							</table>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="applTableBot">
					<tr>
						<td>
						<table cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td><a href="#" onClick="gotoPage(1)"> <IMG
									SRC="images/navfirst.gif" ALT="First Page" hspace="1"
									BORDER="0" align="absmiddle"></a></td>
								<td><a href="#"
									onClick="gotoPage('${command.pagination.currentPageNum-1}')">
								<IMG SRC="images/navprevious.gif" ALT="Previous Page" hspace="1"
									BORDER="0" align="absmiddle"></a></td>
								<td><select name="goto" size="1" class="selectionBox"
									id="sel3" onchange="gotoPage(this.value)">
									<option value="Go to page"><c:out value="Go to page" />
									<c:forEach items="${command.pagination.pages}" var="page"
										varStatus="status">
										<option value="${page.pageNumber}"><c:out
											value="${page.name}" />
									</c:forEach>
								</select></td>
								<td><a href="#"
									onClick="gotoPage('${command.pagination.currentPageNum+1}')">
								<IMG SRC="images/navnext.gif" ALT="Next Page" hspace="1"
									BORDER="0" align="absmiddle"> </a></td>
								<td><a href="#"
									onClick="gotoPage('${command.pagination.numInPage}')"> <IMG
									SRC="images/navlast.gif" ALT="Last Page" hspace="1" BORDER="0"
									align="absmiddle"></a></td>
							</tr>
						</table>
						</td>
						<td nowrap class="tdr"><a href="#"
							onclick="javascript:exportToExcel()"><img
							src="images/ico_excel.gif" alt="Downlaod to Excel" width="18"
							height="18" hspace="5" border="0" align="absmiddle"></a><a
							href="#" onClick="window.location.href='#';"><IMG
							SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5"
							BORDER="0" align="absmiddle"></a><a href="#"
							onclick="searchjob();"><IMG SRC="images/icorefresh.gif"
							ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0"
							align="absmiddle"></a> <!-- <a href="#"><IMG SRC="images/icoemail.gif" ALT="Send email" hspace="5" BORDER="0" align="absmiddle"></a> -->
						<a href="#"
							onclick="showEmailPopup(); popup_show('sendemail', 'sendemail_drag', 'sendemail_exit', 'screen-corner', 40, 80); hideIt();popupboxenable();"><IMG
							SRC="images/icoemail.gif" ALT="Send email" hspace="5" BORDER="0"
							align="absmiddle"></a> <a href="#"
							onClick="javascript:submitFrameForm('frameMain');"><img
							src="images/icosave.gif" alt="Save" width="14" height="14"
							hspace="5" border="0" align="absmiddle" /></a></td>
					</tr>
				</table>

			</c:if></div>
			<!-- ---------------------- TAB 2 CONTAINER END ---------------------------- -->

			<!-- ---------------------------------------------------------------------------------------------------------------------- -->
			<!-- ------------------------- TAB 3 CONTAINER ------------------------------- -->
			<div id="tab3" class="innercontent1"><c:if
				test="${command.tabNavigationTo == null || command.tabNavigationTo=='xxxx' }">
				<table width="100%" cellpadding=0 cellspacing=0
					class="mainApplTable">
					<tbody>
						<tr bgcolor=#ffffff>
							<th width="65%">Search Results - Dispatch</th>
							<th nowrap class="thr"><a href="#"
								onclick="javascript:exportToExcel()"><img
								src="images/ico_excel.gif" alt="Downlaod to Excel" width="18"
								height="18" hspace="5" border="0" align="absmiddle"></a><a
								href="#" onClick="window.location.href='#';"><IMG
								SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5"
								BORDER="0" align="absmiddle"></a><a href="#"
								onclick="searchjob();"><IMG SRC="images/icorefresh.gif"
								ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5"
								BORDER="0" align="absmiddle"><IMG
								SRC="images/icoemail.gif" ALT="Send email" hspace="5" BORDER="0"
								align="absmiddle"><img src="images/icosave.gif" alt="Save"
								width="14" height="14" hspace="5" border="0" align="absmiddle" /></a></th>
						</tr>
						<tr>
							<td colspan="2" class="smallpadding">
							<table width="100%" height="100%" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td valign="top" width="125" class="NoPadding">
									<table width="100%" cellpadding="0" cellspacing="0"
										class="InnerApplTable">
										<tr>
											<th nowrap><a href="#"
												onclick="javascript:shortByField('projectno')"><f:message
												key="projectno" /></a></th>
											<th nowrap><a href="#"
												onclick="javascript:shortByField('taskno')"><f:message
												key="taskno" /></a></th>
											<th nowrap><f:message key="email" /><br>
											<input type=checkbox name="checkAll"
												onclick=selectDeselect();></th>
										</tr>
										<c:forEach items="${command.results}" var="jobCEResult"
											varStatus="status">
											<tr>
												<td align='CENTER' nowrap='nowrap' height="25"
													valign="middle"><a href="#"
													onClick="openJobEntry('${jobCEResult.orderNumber}')"
													title="Transfer to JobEntry">${jobCEResult.projectNumber}</a></td>
												<td align='CENTER' nowrap='nowrap' height="25"
													valign="middle">${jobCEResult.taskId}</td>
												<td align='CENTER' nowrap='nowrap' height="25"
													valign="middle"><input type=checkbox name="mailCheck"></td>
											</tr>
										</c:forEach>
										<tr>
											<td clospan="2">&nbsp;</td>
										</tr>
									</table>
									</td>
									<td class="NoPadding"><iframe
										src="phx_job_log_ce_results_billing.htm?reqBy=billing"
										frameborder="0" height="100%"
										style="padding: 0px; scrolling: yes; border-right: #DBE2F2 1px solid;"
										width="100%" id="frame1" name="frame1"></iframe></td>
								</tr>
							</table>
							</td>
						</tr>
					</tbody>
				</table>

				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="applTableBot">
					<tr>
						<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
								<table cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td><a href="#" onClick="gotoPage(1)"> <IMG
											SRC="images/navfirst.gif" ALT="First Page" align="absmiddle"
											hspace="2" BORDER="0"></a></td>
										<td><a href="#"
											onClick="gotoPage('${command.pagination.currentPageNum-1}')">
										<IMG SRC="images/navprevious.gif" ALT="Previous Page"
											align="absmiddle" hspace="2" BORDER="0"></a></td>
										<td><select name="goto" size="1" class="selectionBox"
											id="sel3" onchange="gotoPage(this.value)">
											<option value="Go to page"><c:out value="Go to page" />
											<c:forEach items="${command.pagination.pages}" var="page"
												varStatus="status">
												<option value="${page.pageNumber}"><c:out
													value="${page.name}" />
											</c:forEach>
										</select></td>
										<td><a href="#"
											onClick="gotoPage('${command.pagination.currentPageNum+1}')">
										<IMG SRC="images/navnext.gif" ALT="Next Page"
											align="absmiddle" hspace="2" BORDER="0"> </a></td>
										<td><a href="#"
											onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')">
										<IMG SRC="images/navlast.gif" ALT="Last Page"
											align="absmiddle" hspace="2" BORDER="0"></a></td>
									</tr>
								</table>
								<table cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td><a href="#" onClick="gotoPage(1)"> <IMG
											SRC="images/navfirst.gif" ALT="First Page" align="absmiddle"
											hspace="2" BORDER="0"></a></td>
										<td><a href="#"
											onClick="gotoPage('${command.pagination.currentPageNum-1}')">
										<IMG SRC="images/navprevious.gif" ALT="Previous Page"
											align="absmiddle" hspace="2" BORDER="0"></a></td>
										<td><select name="goto" size="1" class="selectionBox"
											id="sel3" onchange="gotoPage(this.value)">
											<option value="Go to page"><c:out value="Go to page" />
											<c:forEach items="${command.pagination.pages}" var="page"
												varStatus="status">
												<option value="${page.pageNumber}"><c:out
													value="${page.name}" />
											</c:forEach>
										</select></td>
										<td><a href="#"
											onClick="gotoPage('${command.pagination.currentPageNum+1}')">
										<IMG SRC="images/navnext.gif" ALT="Next Page"
											align="absmiddle" hspace="2" BORDER="0"> </a></td>
										<td><a href="#"
											onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')">
										<IMG SRC="images/navlast.gif" ALT="Last Page"
											align="absmiddle" hspace="2" BORDER="0"></a></td>
									</tr>
								</table>


								</td>
								<td nowrap class="tdr"><a href="#"
									onclick="javascript:exportToExcel()"><img
									src="images/ico_excel.gif" alt="Downlaod to Excel" width="18"
									height="18" hspace="5" border="0" align="absmiddle"></a> <a
									href="#" onClick="window.location.href='#';"><IMG
									SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5"
									BORDER="0" align="absmiddle"></a> <a href="#"
									onclick="searchjob();"><IMG SRC="images/icorefresh.gif"
									ALT="Search/Refresh" align="absmiddle" hspace="5" BORDER="0"
									align="absmiddle"></a> <!-- <a href="#"><IMG SRC="images/icoemail.gif" ALT="Send email" hspace="5" BORDER="0" align="absmiddle"></a><a href="#" onClick="javascript:submitFrameForm('frameProcess')"><img src="images/icosave.gif" alt="Save" align="absmiddle" hspace="5" border="0" align="absmiddle"/></a> -->
								<a href="#"
									onclick="showEmailPopup(); popup_show('sendemail', 'sendemail_drag', 'sendemail_exit', 'screen-corner', 40, 80); hideIt();popupboxenable();"><IMG
									SRC="images/icoemail.gif" ALT="Send email" hspace="5"
									BORDER="0" align="absmiddle"></a></td>

							</tr>
						</table>

						</c:if>
						</div>
						<!-- ------------------------------ TAB 3 CONTAINER END --------------------------------------- -->

						<!-- ---------------------------------------------------------------------------------------------------------------------- -->
						<!-- ------------------------- TAB 4 CONTAINER ------------------------------- -->
						<div id="tab4" class="innercontent1"><c:if
							test="${command.tabNavigationTo=='billing' }">
							<table width="100%" cellpadding=0 cellspacing=0
								class="mainApplTable">
								<tbody>
									<tr bgcolor=#ffffff>
										<th width="65%">Search Results - Billing</th>
										<th nowrap class="thr"><a href="#"
											onclick="javascript:exportToExcel()"> <img
											src="images/ico_excel.gif" alt="Downlaod to Excel" width="18"
											height="18" hspace="5" border="0" align="absmiddle"></a> <a
											href="#" onClick="window.location.href='#';"><IMG
											SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5"
											BORDER="0" align="absmiddle"></a> <a href="#"
											onclick="searchjob();"><IMG SRC="images/icorefresh.gif"
											ALT="Search/Refresh" align="absmiddle" hspace="5" BORDER="0"
											align="absmiddle"></a> <a href="#"
											onclick="showEmailPopup(); popup_show('sendemail', 'sendemail_drag', 'sendemail_exit', 'screen-corner', 40, 80); hideIt();popupboxenable();"><IMG
											SRC="images/icoemail.gif" ALT="Send email" hspace="5"
											BORDER="0" align="absmiddle"></a> <a href="#"
											onClick="javascript:submitFrameForm('frameBilling');"><img
											src="images/icosave.gif" alt="Save" align="absmiddle"
											hspace="5" border="0" align="absmiddle" /></a></th>
									</tr>
									<tr>
										<td colspan="2" class="smallpadding">
										<table width="100%" height="100%" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td valign="top" width="125" class="NoPadding">
												<table width="100%" cellpadding="0" cellspacing="0"
													class="InnerApplTable">
													<tr>
														<th nowrap><a href="#"
															onclick="javascript:shortByField('projectno')"><f:message
															key="projectno" /></a></th>
														<th nowrap><a href="#"
															onclick="javascript:shortByField('taskno')"><f:message
															key="taskno" /></a></th>
														<th nowrap><f:message key="email" /><br>
														<input type=checkbox name="checkAll"
															onclick=selectDeselect();></th>
													</tr>
													<c:forEach items="${command.result}" var="jobCEResult"
														varStatus="status">
														<tr>
															<td align='CENTER' nowrap='nowrap' height="25"
																valign="middle"><a href="#"
																onClick="openJobEntry('${jobCEResult.orderNumber}')"
																title="Transfer to JobEntry">${jobCEResult.projectNumber}</a></td>
															<td align='CENTER' nowrap='nowrap' height="25"
																valign="middle">${jobCEResult.taskId}</td>
															<td align='CENTER' nowrap='nowrap' height="25"
																valign="middle"><input type=checkbox
																name="mailCheck"></td>
														</tr>
													</c:forEach>
													<tr>
														<td clospan="2">&nbsp;</td>
													</tr>
												</table>
												</td>
												<td class="NoPadding"><iframe
													src="phx_job_log_ce_results_billing.htm?reqBy=billing"
													frameborder="0" height="100%"
													style="padding: 0px; scrolling: yes;" width="100%"
													id="frameBilling" name="frameBilling"> </iframe></td>
											</tr>
										</table>
										</td>
									</tr>
								</tbody>
							</table>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="applTableBot">
								<tr>
									<td>
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td><a href="#" onClick="gotoPage(1)"> <IMG
												SRC="images/navfirst.gif" ALT="First Page" align="absmiddle"
												hspace="2" BORDER="0"></a></td>
											<td><a href="#"
												onClick="gotoPage('${command.pagination.currentPageNum-1}')">
											<IMG SRC="images/navprevious.gif" ALT="Previous Page"
												align="absmiddle" hspace="2" BORDER="0"></a></td>
											<td><select name="goto" size="1" class="selectionBox"
												id="sel3" onchange="gotoPage(this.value)">
												<option value="Go to page"><c:out
													value="Go to page" /> <c:forEach
													items="${command.pagination.pages}" var="page"
													varStatus="status">
													<option value="${page.pageNumber}"><c:out
														value="${page.name}" />
												</c:forEach>
											</select></td>
											<td><a href="#"
												onClick="gotoPage('${command.pagination.currentPageNum+1}')">
											<IMG SRC="images/navnext.gif" ALT="Next Page"
												align="absmiddle" hspace="2" BORDER="0"> </a></td>
											<td><a href="#"
												onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')">
											<IMG SRC="images/navlast.gif" ALT="Last Page"
												align="absmiddle" hspace="2" BORDER="0"></a></td>
										</tr>
									</table>


									</td>
									<td nowrap class="tdr"><a href="#"
										onclick="javascript:exportToExcel()"><img
										src="images/ico_excel.gif" alt="Downlaod to Excel" width="18"
										height="18" hspace="5" border="0" align="absmiddle"></a><a
										href="#" onClick="window.location.href='#';"><IMG
										SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5"
										BORDER="0" align="absmiddle"></a><a href="#"
										onclick="searchjob();"><IMG SRC="images/icorefresh.gif"
										ALT="Search/Refresh" align="absmiddle" hspace="5" BORDER="0"
										align="absmiddle"></a> <a href="#"
										onclick="showEmailPopup(); popup_show('sendemail', 'sendemail_drag', 'sendemail_exit', 'screen-corner', 40, 80); hideIt();popupboxenable();"><IMG
										SRC="images/icoemail.gif" ALT="Send email" hspace="5"
										BORDER="0" align="absmiddle"></a> <a href="#"
										onClick="javascript:submitFrameForm('frameBilling')"><img
										src="images/icosave.gif" alt="Save" align="absmiddle"
										hspace="5" border="0" align="absmiddle" /></a></td>
								</tr>
							</table>

						</c:if></div>
						<!-- ------------------------------ TAB 4 CONTAINER END --------------------------------------- -->
						<!-- ---------------------------------------------------------------------------------------------------------------------- -->
						<!-- ------------------------- TAB 5 CONTAINER ------------------------------- -->
						<div id="tab5" class="innercontent1"><c:if
							test="${ command.tabNavigationTo=='process' }">
							<table width="100%" cellpadding=0 cellspacing=0
								class="mainApplTable">
								<tbody>
									<tr bgcolor=#ffffff>
										<th width="65%">Search Results - Process Log</th>
										<th nowrap class="thr"><a href="#"
											onclick="javascript:exportToExcel()"><img
											src="images/ico_excel.gif" alt="Downlaod to Excel" width="18"
											height="18" hspace="5" border="0" align="absmiddle"></a> <a
											href="#" onClick="window.location.href='#';"> <IMG
											SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5"
											BORDER="0" align="absmiddle"></a> <a href="#"
											onclick="searchjob();"><IMG SRC="images/icorefresh.gif"
											ALT="Search/Refresh" align="absmiddle" hspace="5" BORDER="0"
											align="absmiddle"></a> <a href="#"
											onclick="showEmailPopup(); popup_show('sendemail', 'sendemail_drag', 'sendemail_exit', 'screen-corner', 40, 80); hideIt();popupboxenable();"><IMG
											SRC="images/icoemail.gif" ALT="Send email" hspace="5"
											BORDER="0" align="absmiddle"></a> <a href="#"
											onClick="javascript:submitFrameForm('frameProcess')"> <img
											src="images/icosave.gif" alt="Save" align="absmiddle"
											hspace="5" border="0" align="absmiddle" /></a></th>
									</tr>
									<tr>
										<td colspan="2" class="smallpadding">
										<table width="100%" height="100%" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td valign="top" width="125" class="NoPadding">
												<table width="100%" cellpadding="0" cellspacing="0"
													class="InnerApplTable">
													<tr>
														<th nowrap><a href="#"
															onclick="javascript:shortByField('projectno')"><f:message
															key="projectno" /></a></th>
														<th nowrap><a href="#"
															onclick="javascript:shortByField('taskno')"><f:message
															key="taskno" /></a></th>
														<th nowrap><f:message key="email" /><br>
														<input type=checkbox name="checkAll"
															onclick=selectDeselect();></th>
													</tr>
													<c:forEach items="${command.result}" var="jobCEResult"
														varStatus="status">
														<tr>
															<td align='CENTER' nowrap='nowrap' height="25"
																valign="middle"><a href="#"
																onClick="openJobEntry('${jobCEResult.orderNumber}')"
																title="Transfer to JobEntry">${jobCEResult.projectNumber}</a></td>
															<td align='CENTER' nowrap='nowrap' height="25"
																valign="middle">${jobCEResult.taskId}</td>
															<td align='CENTER' nowrap='nowrap' height="25"
																valign="middle"><input type=checkbox
																name="mailCheck"></td>
														</tr>
													</c:forEach>
													<tr>
														<td clospan="2">&nbsp;</td>
													</tr>
												</table>
												</td>
												<td class="NoPadding"><iframe
													src="phx_job_log_ce_results_processlog.htm?reqBy=processlog"
													frameborder="0" height="100%"
													style="padding: 0px; scrolling: yes;" width="100%"
													id="frameProcess" name="frameProcess"> </iframe></td>
											</tr>
										</table>
										</td>
									</tr>
								</tbody>
							</table>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="applTableBot">
								<tr>
									<td>
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td><a href="#" onClick="gotoPage(1)"> <IMG
												SRC="images/navfirst.gif" ALT="First Page" align="absmiddle"
												hspace="2" BORDER="0"></a></td>
											<td><a href="#"
												onClick="gotoPage('${command.pagination.currentPageNum-1}')">
											<IMG SRC="images/navprevious.gif" ALT="Previous Page"
												align="absmiddle" hspace="2" BORDER="0"></a></td>
											<td><select name="goto" size="1" class="selectionBox"
												id="sel3" onchange="gotoPage(this.value)">
												<option value="Go to page"><c:out
													value="Go to page" /> <c:forEach
													items="${command.pagination.pages}" var="page"
													varStatus="status">
													<option value="${page.pageNumber}"><c:out
														value="${page.name}" />
												</c:forEach>
											</select></td>
											<td><a href="#"
												onClick="gotoPage('${command.pagination.currentPageNum+1}')">
											<IMG SRC="images/navnext.gif" ALT="Next Page"
												align="absmiddle" hspace="2" BORDER="0"> </a></td>
											<td><a href="#"
												onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')">
											<IMG SRC="images/navlast.gif" ALT="Last Page"
												align="absmiddle" hspace="2" BORDER="0"></a></td>
										</tr>
									</table>
									</td>
									<td nowrap class="tdr"><a href="#"
										onclick="javascript:exportToExcel()"><img
										src="images/ico_excel.gif" alt="Downlaod to Excel" width="18"
										height="18" hspace="5" border="0" align="absmiddle"></a><a
										href="#" onClick="window.location.href='#';"><IMG
										SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5"
										BORDER="0" align="absmiddle"></a><a href="#"
										onclick="searchjob();"><IMG SRC="images/icorefresh.gif"
										ALT="Search/Refresh" align="absmiddle" hspace="5" BORDER="0"
										align="absmiddle"></a> <a href="#"
										onclick="showEmailPopup(); popup_show('sendemail', 'sendemail_drag', 'sendemail_exit', 'screen-corner', 40, 80); hideIt();popupboxenable();"><IMG
										SRC="images/icoemail.gif" ALT="Send email" hspace="5"
										BORDER="0" align="absmiddle"></a> <a href="#"
										onClick="javascript:submitFrameForm('frameProcess')"><img
										src="images/icosave.gif" alt="Save" align="absmiddle"
										hspace="5" border="0" align="absmiddle" /></a></td>
								</tr>
							</table></div>
						<!-- ------------------------------ TAB 5 CONTAINER END --------------------------------------- -->
						</c:if>
						</div>
						</div>
						<script type="text/javascript">
				
				//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
				//if(document.getElementById("tabNavigationTo").value !='')
				{
				dolphintabs.init("tabnav", ${command.tabId})
				}
				</script>
						</div>
						<!-- -------------------------------- TAB CONTENT END --------------------------------------- -->
						<table width="100%" cellspacing="0">
							<tr>
								<td width="90%" height="24" align="right">
								<div id="navbuttons"></div>
								</td>
								<td height="24" style="text-align: right; padding-right: 0px;"><select
									name="sel5" id="sel5" class="SelectionBox" style="float: right"
									onChange="navenable(this.value)">
									<option value='goto' selected>Go to ... &gt;</option>
									<option value="new search">Job Search</option>
								</select></td>
							</tr>
						</table>
						</div>

						<!-- BREADCRUMB TRAIL END --> <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
						</td>
					</tr>

				</table>
				</form:form>
				<script>
if(document.getElementById('goto')!=null)
{
document.getElementById('goto').value = document.getElementById('pageNumber').value;
alert(document.getElementById('goto').value);
}
</script>

				<ajax:autocomplete baseUrl="phx_ajax.htm" source="stream"
					target="stream" className="autocomplete"
					parameters="entity=com.intertek.phoenix.entity.BusinessStream,textAttribute=code,valueAttribute=code,~code={stream}"
					minimumCharacters="3" />


				<!---------------------------------------Received By Lookup	START------------------------------------------------->
				<div class="sample_popup" id="receivedby">
				<div class="menu_form_header" id="receivedby_drag"
					style="width: 750px;"><img class="menu_form_exit"
					id="receivedby_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;
				<f:message key="searchUser" /></div>
				<div class="menu_form_body" id="receivedbybody"
					style="width: 750px; height: 530px; padding-left: 4px; overflow-y: hidden;">
				<iframe align="left" frameborder="0" style="padding: 0px;"
					height="530px" width="100%" scrolling="auto" id="recievedFr1"
					name="recievedFr1" allowtransparency="yes" src="blank.html">
				</iframe></div>
				</div>
				<!------------------------------------ Received	By Lookup END----------------------------------------------------->
				<!---------------------------------------Userdetails By Lookup START------------------------------------------------->
				<div class="sample_popup" id="userDetails"
					style="visibility: hidden; display: none;">
				<div class="menu_form_header" id="userDetails_drag"
					style="width: 750px;"><img class="menu_form_exit"
					id="userDetails_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;<f:message
					key="searchUser" /></div>
				<div class="menu_form_body" id="userDetailsbody"
					style="width: 750px; height: 530px; padding-left: 4px; overflow-y: hidden;">
				<iframe id="recievedFr" name="recievedFr" align="left"
					frameborder="0" style="padding: 0px;" height="530px" width="100%"
					scrolling="auto" allowtransparency="yes" src="blank.html">
				</iframe></div>
				</div>
				<!------------------------------------Userdetails By Lookup END----------------------------------------------------->

				<!-- --------------------------- Send Email Lookup ------------------------------------------------- -->
				<div class="sample_popup" id="sendemail"
					style="visibility: hidden; display: none;">
				<div class="menu_form_header" id="sendemail_drag"
					style="width: 750px;"><img class="menu_form_exit"
					id="sendemail_exit" src="images/form_exit.png" />
				&nbsp;&nbsp;&nbsp;Job Order Email Summaries</div>
				<div class="menu_form_body" id="sendemailbody"
					style="width: 750px; height: 530px; padding-left: 4px; overflow-y: hidden;">
				<iframe id="sendemailFrame" align="left" frameborder="0"
					style="padding: 0px;" height="530px" width="100%" scrolling="auto"
					allowtransparency="yes" src="blank.html"> </iframe></div>
				</div></div>
			<!-- --------------------------------- Send Email Lookup END ----------------------------------------- -->


			<!-----------------------------------------Branch Code Lookup----------------------------------------------------->
			<div class="sample_popup" id="jobbranchcode"
				style="visibility: hidden; display: none;">
			<div class="menu_form_header" id="jobbranchcode_drag"
				style="width: 750px;"><a href="#"
				onclick="resetBranchTypeFlag()"> <img class="menu_form_exit"
				id="jobbranchcode_exit" src="images/form_exit.png" />
			&nbsp;&nbsp;&nbsp; <f:message key="searchBranchCode" /></div>
			<div class="menu_form_body" id="jobbranchcodebody"
				style="width: 750px; height: 530px; padding-left: 4px; overflow-y: hidden;">
			<iframe align="left" id="jobbu" frameborder="0"
				style="padding: 10px;" height="530px;" width="100%" scrolling="auto"
				allowtransparency="yes" src="blank.html"> </iframe></div>
			</div>
			<!-----------------------------------------Branch Code Lookup END------------------------------------------------->

			<!---------------------------------- Service Offering Parent Name Lookup --------------------------------------------------->
			<div class="sample_popup" id="serviceoff"
				style="visibility: hidden; display: none;">
			<div class="menu_form_header" id="serviceoff_drag"
				style="width: 750px; height: auto;"><img
				class="menu_form_exit" id="serviceoff_exit"
				src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message
				key="serviceOfferingParentName" /></div>
			<div class="menu_form_body" id="serviceoffbody"
				style="width: 750px; height: 530px; overflow-y: hidden;"><iframe
				align="left" id="serviceoffIdLookup" frameborder="0"
				style="padding: 10px;" height="530px;" width="100%" scrolling="auto"
				allowtransparency="yes" src="blank.html"> </iframe></div>
			</div>
			<!---------------------------------- Service Offering Parent Name Lookup END --------------------------------------------------->

			<!---------------------------------------Business Stream Lookup	START------------------------------------------------->
			<div class="sample_popup" id="buStream">
			<div class="menu_form_header" id="buStream_drag"
				style="width: 750px;"><img class="menu_form_exit"
				id="buStream_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;
			<f:message key="searchUser" /></div>
			<div class="menu_form_body" id="buStreambody"
				style="width: 750px; height: 530px; padding-left: 4px; overflow-y: hidden;">
			<iframe align="left" frameborder="0" style="padding: 0px;"
				height="530px" width="100%" scrolling="auto" id="buStreamFr"
				name="buStreamFr" allowtransparency="yes" src="blank.html">
			</iframe></div>
			</div>
			<!------------------------------------ Business Stream Lookup Lookup END----------------------------------------------------->