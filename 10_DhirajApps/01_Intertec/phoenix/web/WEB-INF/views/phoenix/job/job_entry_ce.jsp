<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz"%>
<script type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript" src="js/ce/common.js"></script>
<script type="text/javascript" src="js/ce/ce_jobentry.js"></script>


<body >
<form:form name="createJobsCEForm" method="POST"
	action="phx_job_entry_ce.htm">
	<form:hidden path="actionFlag" />
	<form:hidden path="inputFieldIdValue" />
	<form:hidden path="tabName" />
	<form:hidden path="jobNumber"/>
	<form:hidden path="contractIndex"/>
	
	

	<!--------------------------------------------- MAIN CONTAINER---------------------------------------------------->
	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
		<tr>
			<td valign="top"><!---------------------------------------------BREADCRUMB TRAIL--------------------------------------------------->
			<div id="breadcrumbContainer">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				background="images/intopbluetrailbg.jpg">
				   <tr>
					<td width="25"><img src="images/inlfttrailcorner.gif"
						width="8" height="22"></td>
					<td><jsp:include page="ce_job_bread_crumb.jsp">
						<jsp:param name="jobNumber" value="${command.jobOrder.jobNumber}" />
						<jsp:param name="pageNumber" value="${command.jobOrder.pageNumber}" />
					</jsp:include></td>
				</tr>
			</table>
			</div>
			<!----------------------------------------------BREADCRUMB TRAIL END---------------------------------------------->

			<div id="MainContentContainer"><!-- TABS CONTENTS -->
			<div class="redtext"><form:errors cssClass="error" /></div>
			<div id="tabcontainer">
			<div id="tabnav">
			<ul>
				<li><a href="#" rel="tab1"><span><f:message key="entryForm" /></span></a></li>
				<li><a href="#" rel="tab2"><span><f:message key="addCustomers" /></span></a></li>

			</ul>
			</div>
			<!-------------------------------------Sub Menus container. Do not remove----------------------------------------->
			<div id="tab_inner"><!-- ----------- TAB 1 CONTAINER ------------------ -->

			<!---------------------------------------------TAB 1 CONTAINER---------------------------------------------------->

			<div id="tab1" class="innercontent">
			<table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
				<tbody>
					<tr bgcolor=#ffffff>
						<th width="50%" colspan=2 nowrap><f:message
							key="headerDetails" /> <img src=" images/separator2.gif"
							width="2" height="27" align="absmiddle" class="seperator" /> <f:message
							key="jobId" />: ${command.jobNumber}<img
							src=" images/separator2.gif" width="2" height="27"
							align="absmiddle" class="seperator" /> <f:message key="jobType" />:&nbsp;${command.jobOrder.jobType}
						</th>
						<th colspan=3 width="50%" class="thr">
						<table width="100%" align="right" cellpadding="0" cellspacing="0">
							<tr>
								<th class="thh"><f:message key="status" />: <form:select
									id="sel1" cssClass="selectionBox" path="status"
									items="${command.statusList}" itemLabel="name"
									itemValue="value" /> <form:errors path="status"
									cssClass="redstar" />&nbsp;&nbsp;</th>
								<th class="thh">
								<f:message key="projectType" />:&nbsp; <form:select id="projectTypes"
									cssClass="selectionBox" path="projectType"
									items="${command.projectTypes}" itemLabel="name"
									itemValue="value" disabled="${command.projectTypeViewOnly}" />
								</th>
								<th class="thhr"><a href="#"
									onClick="goToNextPage('${sessionScope['dateFormat']}','${not command.customerExist}','${command.jobType}')"> <img
									src="images/savennextbluarrow.gif"
									alt="Save and Go to Next Page" width="14" height="14"
									hspace="4" border="0" align="absmiddle"
									title="Save and Go to Next Page"></a> <a href="#"
									onclick="javascript:updateJobDescription('${sessionScope['dateFormat']}');submitEntryform('addJob');">
								<img src="images/icosave.gif" alt="Save" title="Save" width="14"
									height="14" border="0" align="absmiddle" /></a></th>
							</tr>
						</table>
						</th>
					</tr>
					<tr>
						<td width="15%" class="TDShade"><strong><f:message
							key="businessUnit" />:</strong><span class="redstar">*</span></td>
						<td width="35%" class="TDShadeBlue"><span class="id_input"><form:select
							id="sel3" cssClass="selectionBoxbig" path="buName"
							items="${command.buNames}" itemLabel="name" itemValue="value" disabled="${command.jobNumber != null}"
							onchange="makeBranchblank()" /> <form:errors path="buName"
							cssClass="redstar" /></span></td>
						<td width="15%" class="TDShade"><strong><f:message
							key="operatingUnit" />:<span class="redstar">*</span></strong></td>
						<td width="35%" colspan="2" class="TDShadeBlue"><form:input
							id="brnch" cssClass="inputBoxBlue" maxlength="128" size="52" disabled="${command.jobNumber != null}"
							path="warehouseName" /> <form:errors
							path="warehouseName" cssClass="redstar" />
                            <a href="#ws" onClick="javascript:showWarehouseSearch();"><img
							src=" images/lookup.gif" alt="Lookup Operating Unit" width="13"
							height="13" border="0" /></a>							
                         </td>
					</tr>



					<tr>
						<td class="TDShade"><strong><f:message key="operation" />:</strong><span
							class="redstar">*</span></td>
						<td class="TDShadeBlue"><form:select id="sel5"
							cssClass="selectionBoxbig" path="operation"
							items="${command.operations}" itemLabel="name"
							itemValue="value" /> <form:errors path="operation"
							cssClass="redstar" /></td>
						<td class="TDShade"><strong><f:message
							key="promiseCompletionDate" />:</strong></td>
						<td colspan="2" class="TDShadeBlue"><form:input id="etadate"
							cssClass="inputBox" path="promiseCompletionDate" /> <form:errors
							path="promiseCompletionDate" cssClass="redstar" /><a
							href="#"
							onClick="displayCalendar(document.forms[0].etadate,'${sessionScope['dateFormat']}',this)">
						<img src=" images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a></td>
					</tr>

					<tr>
						<td class="TDShade"><f:message key="salesRep" />:<c:if test="${command.jobType=='CE' || command.jobType=='CG'}"><span
							class="redStar">*</span></c:if></td>
						<td class="TDShadeBlue"><form:input cssClass="inputBoxBlue" id="salesRep"
							maxlength="128" size="52" path="salesPersonName" /><form:errors
							path="salesPersonName" cssClass="redstar" /><a href="#a" onClick="javascript:showSalesRepSearch();popUserDetails();"><img
							src=" images/lookup.gif" alt="Lookup Sales Rep" width="13"
							height="13" border="0" /></a>
						</td>
						<td class="TDShade"><f:message key="secondarySalesRep" />:</td>
						<td colspan="2" class="TDShadeBlue"><form:input
							cssClass="inputBoxBlue" maxlength="100" size="52"
							path="secondarySalesPersonName" /> <form:errors
							path="secondarySalesPersonName" cssClass="redstar" /><a href="#b" onClick="javascript:showSecondarySalesRepSearch();popUserDetails();"><img
							src=" images/lookup.gif" alt="Lookup Secondary Sales Rep" width="13"
							height="13" border="0" /></a>
						</td>
					</tr>


					<tr>
						<td class="TDShade"><strong><f:message
							key="customerReadyDate" />:</strong></td>
						<td class="TDShadeBlue"><form:input id="nomdate"
							cssClass="inputBox" path="customerReadyDate" /> <form:errors
							path="customerReadyDate" cssClass="redstar" /> <a
							href="#"
							onClick="displayCalendar(document.forms[0].nomdate,'${sessionScope['dateFormat']}',this)">
						<img src=" images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a></td>
						<td class="TDShade"><strong><f:message
							key="actualReadyDate" />:</strong></td>
						<td colspan="2" class="TDShadeBlue"><form:input
							id="finishdate" cssClass="inputBox"
							path="actualReadyDate" /> <form:errors
							path="actualReadyDate" cssClass="redstar" /> <a href="#"
							onClick="displayCalendar(document.forms[0].finishdate,'${sessionScope['dateFormat']}',this)">
						<img src=" images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a></td>
					</tr>

					<tr>
						<td class="TDShade"><f:message key="turnaroundTime" />:</td>
						<td class="TDShadeBlue"><form:input cssClass="inputBox"
							size="52" maxlength="100" path="jobOrder.turnaroundDays" /> <form:errors
							path="jobOrder.turnaroundDays" cssClass="redstar" /></td>
						<td class="TDShade"><f:message key="projectManager" />:</td>
						<td colspan="2" class="TDShadeBlue"><form:input
							cssClass="inputBoxBlue" maxlength="100" size="52"
							path="projectManagerFullName" /> <form:errors
							path="projectManagerFullName" cssClass="redstar" /><a href="#p" onClick="javascript:showProjectManagerSearch();popEmployeeDetails();"><img
							src=" images/lookup.gif" alt="Lookup Project Manager" width="13"
							height="13" border="0" /></a>
							<form:hidden id="projectManagerName" path="projectManagerName" />
						</td>
					</tr>
					<tr>
						<td class="TDShadedottedline"><strong><f:message
							key="followUpServices" /></strong></td>
						<td class="TDShadeBluedottedline"><form:checkbox
							path="followUp" value="jobOrder.followUp" /> <form:errors
							path="followUp" cssClass="redstar" /></td>
						<td class="TDShadedottedline"><strong><f:message
							key="serviceLocation" />:<span class="redstar">*</span></strong></td>
						<td colspan="2" class="TDShadeBluedottedline"><form:input
							id="serviceLoc" size="52" cssClass="inputBoxBlue" maxlength="254"
							path="serviceLocationName" /> <form:errors
							path="serviceLocationCode" cssClass="redstar" /> 
							<a href="#" onClick="javascript:showServiceLocationSearch();popServiceLocation();"><img
							src=" images/lookup.gif" alt="Lookup Service Location" width="13"
							height="13" border="0" /></a> 
						    <form:hidden id="serviceLocCode" path="serviceLocationCode" /></td>
					</tr>
					<tr>
						<td class="TDShadedottedline"><f:message
							key="quoteIssuedDate" />:</td>
						<td class="TDShadeBlue"><form:input id="quoteDate"
							cssClass="inputBox" path="quoteDate" readonly="${command.jobOrder.origin=='ECS'}"/> <form:errors
							path="quoteDate" cssClass="redstar" />
							<a href="#"
							onClick="javascript:if(${command.jobOrder.origin=='ECS'}){confirm('Cannot change the issue date since it is from ECS'); return;}else{displayCalendar(document.forms[0].quoteDate,'${sessionScope['dateFormat']}',this);}">
						<img src=" images/calendar.gif" width="15" height="17"
							align="absmiddle" border="0" /></a>&nbsp;&nbsp;
						<f:message key="quoteID" />: <form:input id="quoteId" 
							cssClass="inputBox" size="6" path="quoteNumber" readonly="${command.jobOrder.origin=='ECS'}"/><form:errors
							path="quoteNumber" cssClass="redstar"/></td>
						<td class="TDShadedottedline"><f:message key="ecsOrderDate" />:</td>
						<td class="TDShadeBluedottedline"><f:formatDate
							value="${command.ecsOrderDate}"
							pattern="${sessionScope['dateFormat']}" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<f:message key="ecsOrderNumber" />:
						${command.ecsOrderNumber}</td>
					</tr>


					<tr>
						<td valign="top" class="TDShade"><strong><a href="#"
							onClick="javascript:updateJobDescription('${sessionScope['dateFormat']}','jobdescexist');"><f:message
							key="jobOrderDescription" />:</a></strong></td>
						<td class="TDShadeBlue"><form:textarea id="jobDesc"
							path="jobOrder.description" rows="5" cols="55" /> <form:errors
							path="jobOrder.description" cssClass="redstar" /></td>
						<td valign="top" class="TDShadeBlue"><strong><f:message
							key="modelNumber" />:<span class="redstar">*</span></strong></td>
						<td colspan="2" class="TDShadeBlue"><form:textarea
							id="modelNumber" path="modelNumber" rows="5" cols="55" />
						<form:errors path="modelNumber" cssClass="redstar" /></td>
					</tr>
				</tbody>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="applTableBot">
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><strong><span class="redstar">*</span></strong> <span
								class="Font11pt"><f:message key="markedfiledsaremdtry" /></span></td>
							<td class="thr"><a href="#"
								onClick="goToNextPage('${sessionScope['dateFormat']}','${not command.customerExist}','${command.jobType}')"> <img
								src=" images/savennextbluarrow.gif"
								alt="Save and Go to Next Page" width="14" height="14" hspace="4"
								border="0" align="absmiddle" title="Save and Go to Next Page"></a>
							<a href="#"
								onclick="updateJobDescription('${sessionScope['dateFormat']}');submitEntryform('addJob');"><img
								src=" images/icosave.gif" alt="Save" title="Save" width="14"
								height="14" border="0" align="absmiddle" /></a></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>

			<!------------------------------------------------------- TAB 1 CONTAINER END --------------------------------------------------------->
			<!----------------------------------------------------------TAB 2 CONTAINER------------------------------------------------------------>
			<div id="tab2" class="innercontent">
			<table class=mainApplTable cellspacing=0 cellpadding=0 width="100%">
				<tbody>
					<tr bgcolor=#ffffff>
						<th><f:message key="customerEntry" /> <img
							src=" images/separator2.gif" width="2" height="27"
							align="absmiddle" class="seperator" /> <f:message key="jobId" />
						: ${command.jobNumber}<img src=" images/separator2.gif" width="2"
							height="27" align="absmiddle" class="seperator" /><f:message key="jobType" />:
							&nbsp;${command.jobOrder.jobType}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						   <f:message key="status" />: <form:select id="sel1"
							cssClass="selectionBox" path="status"
							items="${command.statusList}" itemLabel="name" itemValue="value"
							disabled="true" /> <form:errors path="status" cssClass="redstar" />
						&nbsp;&nbsp;</th>

						<th class="thr"><a href="#"
							onClick="goToNextPage('${sessionScope['dateFormat']}','${not command.customerExist}','${command.jobType}')"><img
							src=" images/savennextbluarrow.gif"
							alt="Save and Go to Next Page" width="14" height="14" hspace="4"
							border="0" align="absmiddle" title="Save and Go to Next Page"></a><a
							href="#"><img src=" images/icosave.gif" alt="Save"
							title="Save" width="14" height="14" border="0" align="absmiddle"
							onClick="javascript:updateJobDescription('${sessionScope['dateFormat']}');submitEntryform('saveCustomer');" /></a>
						</th>
					</tr>
					<tr>
						<td colspan="4" class="TDShade" style="font-weight:normal;"><f:message key="customerLookup"/></td>
					</tr>
					<tr>
						<td colspan="4" class="TDShade">
							<strong><f:message key="addCustomer"/>:</strong>
 							<form:input id="ccode" cssClass="inputBox" path="" size="52" maxlength="100" onkeypress="if(event.keyCode==13) {updateCustomerIframeSrc(${command.customerExist});}"/> 
 							<label>
							<input name="Add" type="button" class="button1" value="Add"
								onClick="javascript:updateCustomerIframeSrc(${command.customerExist});" />
							</label>
						</td>
					</tr>
				</tbody>
			</table>

			<!---------------------------------------------------Contract Details Container-------------------------------------------------------->
<c:forEach items="${command.jobContracts}" var="jobContracts" varStatus="counter">
	<form:hidden id="billingCustCode${counter.index}" path="jobContracts[${counter.index}].billingCustCode"/>
	<form:hidden id="reportReceivingCustCode${counter.index}" path="jobContracts[${counter.index}].reportReceivingCustCode"/>
	<form:hidden id="manfCustCode${counter.index}" path="jobContracts[${counter.index}].manfCustCode"/>	
	<form:hidden id="supplierCustCode${counter.index}" path="jobContracts[${counter.index}].supplierCustCode"/>
	<form:hidden id="billingAddressId${counter.index}" path="jobContracts[${counter.index}].billingAddressId"/>
	<form:hidden id="reportReceivingAddressId${counter.index}" path="jobContracts[${counter.index}].reportReceivingAddressId"/>
	<form:hidden id="manfAddressId${counter.index}" path="jobContracts[${counter.index}].manfAddressId"/>
	<form:hidden id="supplierAddressId${counter.index}" path="jobContracts[${counter.index}].supplierAddressId"/>
	<form:hidden id="parentCust${counter.index}" path="jobContracts[${counter.index}].jobContract.parentCustCode"/>
	<form:hidden id="remitToBankAccountBuName${counter.index}" path="jobContracts[${counter.index}].jobContract.remitToBankAccountBuName"/>
	<form:hidden id="remitToBankCode${counter.index}" path="jobContracts[${counter.index}].jobContract.remitToBankCode"/>

				<div id="contractdetails"><br>

				<div id="origintable">
				<table border="0" cellpadding="0" cellspacing="0" cols="11"
					class="secAppltable"
					style="margin-top: 0px; border: none; text-align: center;">
					<th width="20" scope="col" align="left"><f:message key="customerName" /></th>
					<th width="30" scope="col" align="left"><f:message key="contact" /></th>
					<th width="20" scope="col" align="left"><f:message key="contractID"/></th>
					<th width="30" scope="col" align="left"><f:message key="contractDescription"/></th>
					<th width="20" align="left" scope="col">&nbsp;</th>
					<tr>
						<td width="60" align="left">&nbsp;${jobContracts.customerName}</td>
						<td width="100" align="left">&nbsp;	
						<a href="#" onMouseOver="doTooltip(event, '<table width=280px><tr><td width=140px><b>Primary Contact ID:</b></td><td width=140px>${jobContracts.jobContract.contactId}</td></tr><tr><td><b>Company ID:</b></td><td>${jobContracts.customerName}</td></tr><tr><td valign=top nowrap><b>Primary Contact Address:</b></td> <td nowrap>${jobContracts.contactAddress}</td></tr><tr><td><b>Telephone:</b></td>  <td>${jobContracts.jobContract.contact.workPhone}</td></tr><tr><td><b>Email Address:</b></td><td>${jobContracts.jobContract.contact.workEmail}</td></tr></table>')" onMouseOut="hideTip()">${jobContracts.contactName}</a></td>           
						<td align="left">&nbsp;${jobContracts.contractName}</td>
						<td align="left">&nbsp;${jobContracts.contractDescription}</td>
						<td align="center"><a href="#"
							onClick="javascript:if('${command.jobContracts[counter.index].id}'!=''){updateCustomerAddNoteIframeSrc('${command.jobContracts[counter.index].id}','${command.jobNumber}');popAddNote();}else{confirm('Please save first');}"><img
							src=" images/icoaddnote.gif" alt="Add Note" width="18"
							height="15" border="0" /></a> &nbsp;&nbsp;<authz:authorize
							ifAnyGranted="FileUpload,FileUploadNoDel">
							<a href="#"
								onClick="javascript:if('${command.jobContracts[counter.index].id}'!=''){updateCustomerAttachIframeSrc('${command.jobContracts[counter.index].id}','${command.jobNumber}');popAttach();}else{confirm('Please save first');}"><img
								src=" images/icoattach.gif" alt="Add an attachment" width="13"
								height="16" border="0" /></a>


						</authz:authorize> &nbsp;&nbsp;<a href="#"
							onClick="javascript:setDeleteflag('${counter.index}','${jobContracts.contractName}');"> <img
							src="images/icodel.gif" alt="Delete row" width="12" height="14"
							border="0" /></a></td>


					</tr>

					<tr valign="center">
						<td colspan="8" style="padding: 0px;">
						<table border="0" cellpadding="0" cellspacing="0"
							class="mainApplTable"
							style="width: 100%; margin: 1px; border-top: 1px #CCCCCC solid; border-right: 0px; border-bottom: 0px;">

							<tr>
								<td width="18%" class="TDShadeGrey"><f:message key="origin" /><span
									class="redstar">*</span></td>
								<td width="26%"><form:select id="sel6"
									cssClass="selectionBoxbig"
									path="jobContracts[${counter.index}].sourceOrigin"
									items="${command.origins}" itemLabel="name" itemValue="value" />
								<form:errors path="jobContracts[${counter.index}].sourceOrigin"
									cssClass="redstar" /></td>
									
								<td class="TDShadeGrey"><f:message key="custRefNum" /><span
									class="redstar">*</span></td>
								<td><form:input id="custren${counter.index}" cssClass="inputBox"
									size="38" path="jobContracts[${counter.index}].jobContract.custRefNum"/>
								<form:errors path="jobContracts[${counter.index}].jobContract.custRefNum"
									cssClass="redstar" /> 	
								

							</tr>

							<tr>
								<td class="TDShadeGrey"><f:message key="selectedLanguage" /></td>
								<td><form:select id="sel7" cssClass="selectionBoxbig"
									path="jobContracts[${counter.index}].jobContract.invoiceLanguage"
									items="${command.invoiceLanguages}" itemLabel="name"
									itemValue="value" /> <form:errors
									path="jobContracts[${counter.index}].jobContract.invoiceLanguage" cssClass="redstar" /></td>
								
								<td class="TDShadeGrey">${command.jobContracts[counter.index].jobContract.invoiceLabel1}&nbsp;</td>
								<td>
								   <c:if test="${command.jobContracts[counter.index].jobContract.invoiceLabel1!=null}">
								    <form:input cssClass="inputBox"
										size="38" path="jobContracts[${counter.index}].jobContract.invoiceValue1"/>
										<form:errors path="jobContracts[${counter.index}].jobContract.invoiceValue1" cssClass="redstar"/>
									</c:if>&nbsp;
								</td>								
							</tr>

							<tr>
								<td class="TDShadeGrey"><f:message
									key="contractCurrency" /></td>
								<td>&nbsp;${command.jobContracts[counter.index].jobContract.contractCurrency}</td>
								
								<td class="TDShadeGrey">${command.jobContracts[counter.index].jobContract.invoiceLabel2}&nbsp;</td>
								<td>
								   <c:if test="${command.jobContracts[counter.index].jobContract.invoiceLabel2!=null}">
								    <form:input cssClass="inputBox"
										size="38" path="jobContracts[${counter.index}].jobContract.invoiceValue2"/>
										<form:errors path="jobContracts[${counter.index}].jobContract.invoiceValue2" cssClass="redstar"/>
									</c:if>&nbsp;
								</td>							
								
							</tr>
							<tr>
								<td class="TDShadeGrey"><f:message
									key="zoneDescription" /></td>
								<td><form:select id="sel8" cssClass="selectionBoxbig"
									path="jobContracts[${counter.index}].jobContract.zoneDescription"
									items="${command.jobContracts[counter.index].zoneDescriptionList}" itemLabel="name"
									itemValue="value"/>
								</td>
								
								<td class="TDShadeGrey">${command.jobContracts[counter.index].jobContract.invoiceLabel3}&nbsp;</td>
								<td>
								   <c:if test="${command.jobContracts[counter.index].jobContract.invoiceLabel3!=null}">
								    <form:input cssClass="inputBox"
										size="38" path="jobContracts[${counter.index}].jobContract.invoiceValue3"/>
										<form:errors path="jobContracts[${counter.index}].jobContract.invoiceValue3" cssClass="redstar"/>
									</c:if>&nbsp;
								</td>
								
							</tr>
							<tr>
								<td class="TDShadeGrey"><f:message
										key="zoneId" /></td>
								<td><form:select id="sel8" cssClass="selectionBoxbig"
										path="jobContracts[${counter.index}].jobContract.zoneId"
										items="${command.jobContracts[counter.index].zoneIdList}" itemLabel="name"
										itemValue="value"/>
								</td>
									
								<td class="TDShadeGrey">${command.jobContracts[counter.index].jobContract.invoiceLabel4}&nbsp;</td>
									<td>
								   <c:if test="${command.jobContracts[counter.index].jobContract.invoiceLabel4!=null}">
								    <form:input cssClass="inputBox"
										size="38" path="jobContracts[${counter.index}].jobContract.invoiceValue4"/>
										<form:errors path="jobContracts[${counter.index}].jobContract.invoiceValue4" cssClass="redstar"/>
									</c:if>&nbsp;
								</td>
							</tr>
							
							<tr>
								<td class="TDShadeGrey"><f:message
										key="transactionCurrencyMultiplier" /></td>
								<td><form:select id="sel8" cssClass="selectionBoxbig"
										path="jobContracts[${counter.index}].jobContract.transactionCurrency"
										items="${command.jobContracts[counter.index].transactionCurrencies}" itemLabel="name"
										itemValue="value"/>
								</td>
									
								<td class="TDShadeGrey">${command.jobContracts[counter.index].jobContract.invoiceLabel5}&nbsp;</td>
								<td>
								   <c:if test="${command.jobContracts[counter.index].jobContract.invoiceLabel5!=null}">
								    <form:input cssClass="inputBox"
										size="38" path="jobContracts[${counter.index}].jobContract.invoiceValue5"/>
										<form:errors path="jobContracts[${counter.index}].jobContract.invoiceValue5" cssClass="redstar"/>
									</c:if>&nbsp;
								</td>
							</tr>
							
							
							<tr>
								<td class="TDShadeGrey"><f:message
										key="parentJobId" /></td>
								<td><form:input cssClass="inputBox" id="parentjob${counter.index}"
										size="50" path="jobContracts[${counter.index}].jobContract.parentJobNumber"/>
										<a href="#" 
										onClick="javascript:showParentJobSearch('${counter.index}','${command.buName}');popJob();"><img
									src="images/lookup.gif" alt="Lookup Parent Job" width="13"
									height="13" border="0" /></a>
								</td>
								
								<td class="TDShadeGrey"><f:message
										key="parentContractId"/></td>
								<td><form:input cssClass="inputBox" id="parentcontract${counter.index}"
										size="38" path="jobContracts[${counter.index}].jobContract.parentContractCode"/>
										<a href="#" 
										onClick="javascript:showParentContractSearch('${counter.index}','${command.jobContracts[counter.index].jobContract.customerCode}');popContract();"><img
									src="images/lookup.gif" alt="Lookup Parent Contract" width="13"
									height="13" border="0" /></a>
								</td>
							
							</tr>
							
							
							<tr>
									<td class="TDShadeGreydottedline"><f:message
											key="productType" /></td>
									<td class="TDShadeGreydottedlinedata"><form:select id="sel7" cssClass="selectionBoxbig"
											path="jobContracts[${counter.index}].jobContract.productType"
											items="${command.productTypes}" itemLabel="name"
											itemValue="value" /></td>
									
									<td class="TDShadeGreydottedline"><f:message key="vatRegId" />:</td>
										<td class="TDShadeGreydottedlinedata"><form:select id="vatSel" cssClass="selectionBoxint"
											path="jobContracts[${counter.index}].jobContract.vatRegId" items="${command.jobContracts[counter.index].vatRegIds}"
											itemLabel="name" itemValue="value" /> <form:errors
											path="jobContracts[${counter.index}].jobContract.vatRegId" cssClass="redstar" />
									</td>
							</tr>
							

							<tr>
							    
							
								<td class="TDShadeGrey"><f:message key="billingContact" />:<span
									class="redstar">*</span><div><form:checkbox id="differentCustomer${counter.index}"
									value="1" path="jobContracts[${counter.index}].jobContract.billingCustomerDifferent"
									label="Different Customer?" onclick="differentCustData('${counter.index}');"/>
									<form:checkbox id="billAddressAsSched${counter.index}" path="" value="1" label="same as scheduler" 
										onclick="setBillAddressAsScheduler('${counter.index}',
										'${command.jobContracts[counter.index].jobContract.contactId}',
										'${command.jobContracts[counter.index].contactName}','${command.jobContracts[counter.index].contactAddress}');"/>
									</div></td>
								<td><form:input id="billingContactId${counter.index}" cssClass="inputBox"
									size="14" maxlength="19" path="jobContracts[${counter.index}].billingContactId"/>
								<form:errors path="jobContracts[${counter.index}].billingContactId"
									cssClass="redstar" /> &nbsp;<a href="#bc"
									onClick="javascript:updateContactIframeSrc('${counter.index}','${command.jobContracts[counter.index].jobContract.customerCode}', 'billing');popup_show('searchcontact',	'searchcontact_drag', 'searchcontact_exit', 'screen-corner', 180, 120);hideIt();popupboxenable();showPopupDiv('searchcontact','contactbody');"><img
									src="images/lookup.gif" alt="Lookup Billing Contact" width="13"
									height="13" border="0" /></a>&nbsp; <form:input
									id="billingContactName${counter.index}" cssClass="inputBox" size="26"
									maxlength="50" path="jobContracts[${counter.index}].billingContactName" readonly="true" /> <form:errors
									path="jobContracts[${counter.index}].billingContactName" cssClass="redstar" /></td>
								<td class="TDShadeGrey"><span id="custInfo"><f:message key="customerInfo" />:</span></td>
								<td><form:input id="custInfo${counter.index}" cssClass="inputBox"
									size="38" maxlength="19" path="jobContracts[${counter.index}].billingCustomerInfo" readonly="true"/></td>	
							</tr>

							<tr>
								<td class="TDShadeGrey"><f:message key="billingAddress" />:
								</td>
								<td colspan="3"><form:input id="billingContactAddress${counter.index}"
									cssClass="inputBox" size="127" path="jobContracts[${counter.index}].billingAddress"
									readonly="true" /> <form:errors path="jobContracts[${counter.index}].billingAddress"
									cssClass="redstar" /></td>
							</tr>

							<tr>
								<td class="TDShadeGrey"><f:message key="reportReceivingContact" />
								<c:if test="${command.jobType=='CE' || command.jobType=='CG'}"><span class="redStar">*</span></c:if>
							 <form:checkbox id="differentReceiver${counter.index}"
									value="1" path="jobContracts[${counter.index}].jobContract.reportReceivingCustomerDifferent"
									label="Different Customer?" onclick="rcvngDifferentCustData('${counter.index}');"/>
							<form:checkbox id="recvAddressAsSched${counter.index}" path="" value="1" label="same as scheduler" 
								onclick="setRecvAddressAsScheduler('${counter.index}',
										'${command.jobContracts[counter.index].jobContract.contactId}',
										'${command.jobContracts[counter.index].contactName}',
										'${command.jobContracts[counter.index].contactAddress}');"/>
										</td>
								<td><form:input id="reportReceivingContactId${counter.index}" cssClass="inputBox"
									size="14" maxlength="19"
									path="jobContracts[${counter.index}].jobContract.reportReceivingContactId"/>
								<form:errors path="jobContracts[${counter.index}].jobContract.reportReceivingContactId"
									cssClass="redstar" /> &nbsp;<a href="#rc"
									onClick="javascript:updateContactIframeSrc('${counter.index}','${command.jobContracts[counter.index].jobContract.customerCode}', 'repreceiving');popup_show('searchcontact',	'searchcontact_drag', 'searchcontact_exit', 'screen-corner', 180, 120);hideIt();popupboxenable();showPopupDiv('searchcontact','contactbody');"><img
									src="images/lookup.gif" alt="Lookup Report Receiving Contact" width="13"
									height="13" border="0" /></a>&nbsp; <form:input
									id="reportReceivingContactName${counter.index}" cssClass="inputBox" size="26"
									maxlength="50" path="jobContracts[${counter.index}].reportReceivingContactName" readonly="true" /> <form:errors path="jobContracts[${counter.index}].reportReceivingContactName"
									cssClass="redstar" /></td>
								<td class="TDShadeGrey"><span id="recvngCustInfo"><f:message key="customerInfo" />:</span></td>
								<td><form:input id="recvngCustInfo${counter.index}" cssClass="inputBox"
									size="38" maxlength="19" path="jobContracts[${counter.index}].reportCustomerInfo" readonly="true"/></td>
							</tr>


							<tr>
								<td class="TDShadeGrey"><f:message key="reportReceivingAddress" />:
								</td>
								<td colspan="3"><form:input id="reportReceivingContactAddress${counter.index}"
									cssClass="inputBox" size="127" path="jobContracts[${counter.index}].reportReceivingAddress"
									readonly="true" /> <form:errors path="jobContracts[${counter.index}].reportReceivingAddress"
									cssClass="redstar" /></td>
							</tr>
							
							<tr>
								<td class="TDShadeGrey"><f:message
									key="supplierContact" />:</td>
								<td><form:input id="supplierContactId${counter.index}" cssClass="inputBox"
									size="14" maxlength="19"
									path="jobContracts[${counter.index}].supplierContactId"/>
								<form:errors path="jobContracts[${counter.index}].supplierContactId"
									cssClass="redstar" /> &nbsp;<a href="#sc"
									onClick="javascript:updateContactIframeSrc('${counter.index}','${command.jobContracts[counter.index].jobContract.customerCode}', 'supply');popup_show('searchcontact',	'searchcontact_drag', 'searchcontact_exit', 'screen-corner', 180, 120);hideIt();popupboxenable();showPopupDiv('searchcontact','contactbody');"><img
									src="images/lookup.gif" alt="Lookup Supplier Contact" width="13"
									height="13" border="0" /></a>&nbsp; <form:input
									id="supplierContactName${counter.index}" cssClass="inputBox" size="26"
									maxlength="50" path="jobContracts[${counter.index}].supplierContactName" readonly="true" /> <form:errors path="jobContracts[${counter.index}].supplierContactName"
									cssClass="redstar" /></td>
								<td class="TDShadeGrey">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							
							<tr>
								<td class="TDShadeGrey"><f:message key="supplierAddress" />:
								</td>
								<td colspan="3"><form:input id="supplierContactAddress${counter.index}"
									cssClass="inputBox" size="127" path="jobContracts[${counter.index}].supplierAddress"
									readonly="true" /> <form:errors path="jobContracts[${counter.index}].supplierAddress"
									cssClass="redstar" /></td>
							</tr>
							
							

							<tr>
								<td class="TDShadeGrey"><f:message
									key="manufacturerContact" />:</td>
								<td><form:input id="oemContactId${counter.index}" cssClass="inputBox"
									size="14" maxlength="19"
									path="jobContracts[${counter.index}].manufacturerContactId"/>
								<form:errors path="jobContracts[${counter.index}].manufacturerContactId"
									cssClass="redstar" /> &nbsp;<a href="#mc"
									onClick="javascript:updateContactIframeSrc('${counter.index}','${command.jobContracts[counter.index].jobContract.customerCode}', 'oem');popup_show('searchcontact',	'searchcontact_drag', 'searchcontact_exit', 'screen-corner', 180, 120);hideIt();popupboxenable();showPopupDiv('searchcontact','contactbody');"><img
									src="images/lookup.gif" alt="Lookup Manufacturer Contact" width="13"
									height="13" border="0" /></a>&nbsp; <form:input
									id="oemContactName${counter.index}" cssClass="inputBox" size="26"
									maxlength="50" path="jobContracts[${counter.index}].manufacturerContactName" readonly="true" /> <form:errors path="jobContracts[${counter.index}].manufacturerContactName"
									cssClass="redstar" /></td>
								<td class="TDShadeGrey">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td class="TDShadeGreydottedline"><f:message
									key="manufacturerAddress" />:</td>
								<td colspan="3" class="TDShadeGreydottedlinedata"><form:input
									id="oemContactAddress${counter.index}" cssClass="inputBox" size="127"
									path="jobContracts[${counter.index}].manufacturerAddress" readonly="true" /> <form:errors
									path="jobContracts[${counter.index}].manufacturerAddress" cssClass="redstar" /></td>
							</tr>
					

							<tr>
								<td valign="top" class="TDShadeGrey"><f:message
									key="remitTo" />:<span class="redstar">*</span></td>
								<td><form:input id="remitto${counter.index}" cssClass="inputBoxBlue"
									size="12"  path="jobContracts[${counter.index}].remitToCode" /> <form:errors
									path="jobContracts[${counter.index}].remitToCode" cssClass="redstar" /> &nbsp;<a
									href="#re"
									onClick="javascript:showBankSearch('${counter.index}','${command.buName}','${command.jobContracts[counter.index].jobContract.contractCurrency}');popup_show('searchremitto', 'searchremitto_drag', 'searchremitto_exit', 'screen-corner',180, 120);hideIt();popupboxenable();showPopupDiv('searchremitto','remittobody');"><img
									src=" images/lookup.gif" width="13" alt="Lookup Remit To"
									height="13" border="0" /></a>
									&nbsp; <f:message key="acct" />: <form:input id="bankaccount${counter.index}"
									cssClass="inputBoxBlue" size="20" 
									path="jobContracts[${counter.index}].remitToBankAccountNum" /> <form:errors
									path="jobContracts[${counter.index}].remitToBankAccountNum" cssClass="redstar" />
								&nbsp;<a href="#ba"
									onClick="javascript:showBankAccountSearch('${counter.index}','${command.buName}','${command.jobContracts[counter.index].jobContract.contractCurrency}');popup_show('searchaccount','searchaccount_drag',	'searchaccount_exit', 'screen-corner', 180,	120);hideIt();popupboxenable();showPopupDiv('searchaccount','accountbody');"><img
									src=" images/lookup.gif" width="13" alt="Lookup Account Info"
									height="13" border="0" /></a>
									&nbsp;</td>
									
									<td width="12%" class="TDShadeGrey"><f:message
									key="invoiceType" />:</td>
								<td width="19%"><form:select id="sel12"
									cssClass="selectionBoxint" path="jobContracts[${counter.index}].jobContract.invoiceType"
									items="${command.invoiceTypes}" itemLabel="name"
									itemValue="value" /> <form:errors
									path="jobContracts[${counter.index}].jobContract.invoiceType" cssClass="redstar" /></td>

								
							</tr>
							<tr>
								<td class="TDShadeGrey"><f:message key="paymentType" />:</td>
								<td><form:select id="sel7" cssClass="selectionBoxbig"
									path="jobContracts[${counter.index}].paymentType"
									items="${command.paymentTypes}" itemLabel="name"
									itemValue="value" /> <form:errors
									path="jobContracts[${counter.index}].paymentType" cssClass="redstar" /></td>
									
									<td nowrap class="TDShadeGrey"><f:message
									key="paymentReference" /></td>
								<td><form:input cssClass="inputBox" size="38" maxlength="4"
									path="jobContracts[${counter.index}].jobContract.paymentReferenceNum" /> <form:errors
									path="jobContracts[${counter.index}].jobContract.paymentReferenceNum" cssClass="redstar" />
								</td>

								
							</tr>


							<tr>
								<td class="TDShadeGrey"><f:message key="prePaymentRequired" />:</td>
								<td><form:checkbox path="jobContracts[${counter.index}].jobContract.prepaymentRequired"
									value="prePaymentRequired" disabled="true" /> <form:errors
									path="jobContracts[${counter.index}].jobContract.prepaymentRequired" cssClass="redstar" />
								</td>
								<td class="TDShadeGrey"><f:message key="paymentterms" />:</td>
								<td><form:select id="sel13" cssClass="selectionBoxint"
									path="jobContracts[${counter.index}].jobContract.paymentTerms"
									items="${command.paymentTermsList}" itemLabel="name"
									itemValue="value" /></td>
							</tr>
							<tr>
								<td nowrap class="TDShadeGrey"><f:message
									key="establishedAccount" /></td>
								<td><form:input cssClass="inputBox" size="50" path="jobContracts[${counter.index}].jobContract.poNumber" /> <form:errors
									path="jobContracts[${counter.index}].jobContract.poNumber" /></td>
								<td class="TDShadeGrey">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td class="TDShadeGrey"><a href="#" onClick="updateDescription('${counter.index}','${sessionScope['dateFormat']}');"><f:message
									key="invoiceDesc" />:</a></td>
								<td colspan="3"><form:textarea
									path="jobContracts[${counter.index}].jobContract.invoiceDescription" cols="127"
									id="invoiceDescrdetails${counter.index}" tabindex="49" /> <form:errors
									path="jobContracts[${counter.index}].jobContract.invoiceDescription" cssClass="redstar" />
								</td>
							</tr>
					
						</table>
						</td>
					</tr>
				</table>
	<script>
	replceCodes('${counter.index}');
	differentCustData('${counter.index}');
	rcvngDifferentCustData('${counter.index}');
        formatServiceLoc('${command.serviceLocationName}');
	</script>

</c:forEach>
				<!---------------------------------------------------Contract Details Container End---------------------------------------------------->
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="applTableBot">
					<tr>
						<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><strong> <span class="redstar">*</span> </strong> <span
									class="Font11pt"><f:message key="markedfiledsaremdtry" /></span>
								</td>
								<td class="thr"><a href="#"
									onClick="goToNextPage('${sessionScope['dateFormat']}','${not command.customerExist}','${command.jobType}')"> <img
									src="images/savennextbluarrow.gif"
									alt="Save and Go to Next Page" width="14" height="14"
									hspace="4" border="0" align="absmiddle"
									title="Save and Go to Next Page"></a> <a href="#"><img
									src="images/icosave.gif" alt="Save" title="Save" width="14"
									height="14" border="0"
									onClick="javascript:updateJobDescription('${sessionScope['dateFormat']}');submitEntryform('saveCustomer');" /></a>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</div>
				<!-----------------------------------------------------------TAB2 CONTAINER END-------------------------------------------------------->
				</div>
			</div>

			<script type="text/javascript">
				var pageNoVar = "${command.tabName}"
				dolphintabs.init("tabnav", pageNoVar)
			</script> 
			<!-------------------------------------------------------------TAB CONTENT END--------------------------------------------------------->
			</div>
			<!------------------------------------------------------------MAIN CONTAINER END------------------------------------------------------->
			</td>
		</tr>
	</table>
	<!-------------------------------------------------------Contract Details Container End------------------------------------------------>
	<!------------------------------------------------------MAIN OUTSIDE TABLE HOLDER------------------------------------------------------>
</form:form>
<!----------------------------------------------------------Job Customer Lookup Start---------------------------------------------------->
<div class="sample_popup" id="customerSearch"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="customerSearch_drag"
	style="width: 650px;"><img class="menu_form_exit"
	id="customerSearch_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;
<f:message key="searchCustomer" /></div>
<div class="menu_form_body" id="customerSearchbody"
	style="width: 650px; height: 430px; padding-left: 4px; overflow-y: hidden;">
<iframe id="customerFr" align="left" frameborder="0"
	style="padding: 0px;" height="430px" width="100%" scrolling="auto"
	allowtransparency="yes" src="blank.html"> </iframe></div>
</div>
<!----------------------------------------------------------Job Customer Lookup END---------------------------------------------------->

<!---------------------------------------Employeedetails By Lookup START------------------------------------------------->
<div class="sample_popup" id="employeeDetails"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="employeeDetails_drag"
	style="width: 750px;"><img class="menu_form_exit"
	id="employeeDetails_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;
<f:message key="searchEmployee" /></div>
<div class="menu_form_body" id="employeeDetailsbody"
	style="width: 750px; height: 630px; padding-left: 4px; overflow-y: hidden;">
<iframe id="searchEmployeeFrame" align="left" frameborder="0"
	style="padding: 0px;" height="630px" width="100%" scrolling="auto"
	allowtransparency="yes" src="blank.html"> </iframe></div>
</div>
<!------------------------------------Employeedetails By Lookup END----------------------------------------------------->


<!---------------------------------------Userdetails By Lookup START------------------------------------------------->
<div class="sample_popup" id="userDetails"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="userDetails_drag"
	style="width: 750px;"><img class="menu_form_exit"
	id="userDetails_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;
<f:message key="searchUser" /></div>
<div class="menu_form_body" id="userDetailsbody"
	style="width: 750px; height: 630px; padding-left: 4px; overflow-y: hidden;">
<iframe id="recievedFr" align="left" frameborder="0"
	style="padding: 0px;" height="630px" width="100%" scrolling="auto"
	allowtransparency="yes" src="blank.html"> </iframe></div>
</div>
<!------------------------------------Userdetails By Lookup END----------------------------------------------------->
<!-----------------------------------------Branch Code Lookup----------------------------------------------------->
<div class="sample_popup" id="jobbranchcode"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="jobbranchcode_drag"
	style="width: 750px;"><a href="#" onclick="resetBranchTypeFlag()">
<img class="menu_form_exit" id="jobbranchcode_exit"
	src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp; <f:message
	key="searchOperatingUnit" /></div>
<div class="menu_form_body" id="jobbranchcodebody"
	style="width: 750px; height: 630px; padding-left: 4px; overflow-y: hidden;">
<iframe align="left" id="jobbu" frameborder="0" style="padding: 10px;"
	height="630px;" width="100%" scrolling="auto" allowtransparency="yes"
	src="blank.html"> </iframe></div>
</div>
<!-----------------------------------------Branch Code Lookup END------------------------------------------------->
<!---------------------------------------ServiceLocation Lookup	START--------------------------------------------->
<div class="sample_popup" id="servloc">
<div class="menu_form_header" id="servloc_drag" style="width: 990px;">
<img class="menu_form_exit" id="servloc_exit"
	src=" images/form_exit.png" />&nbsp;&nbsp;&nbsp; <f:message
	key="selectServiceLocation" /></div>
<div class="menu_form_body" id="servlocbody"
	style="width: 990px; height: 630px; padding-left: 4px; overflow-y: hidden;">
<iframe id="serv" align="left" src="blank.html" frameborder="0"
	style="padding: 10px; height: 630px; width: 990px" width="100%"
	scrolling="auto" allowtransparency="yes"> </iframe></div>
</div>
<!-------------------------------------	ServiceLocation	Lookup END------------------------------------------------>
<!----------------------------------------------------------Search Bank	Lookup--------------------------------------------------------->
<div class="sample_popup" id="searchremitto">
<div class="menu_form_header" id="searchremitto_drag"
	style="width: 550px; height: auto;"><img class="menu_form_exit"
	id="searchremitto_exit" src="images/form_exit.png" />
&nbsp;&nbsp;&nbsp;<f:message key="remitToSearch" /></div>
<div class="menu_form_body" id="remittobody"
	style="width: 550px; height: 430px; padding-left: 4px; overflow-y: hidden;">
		<iframe align="left" frameborder="0" style="padding: 0px;"
			height="430px;" width="100%" src="blank.html" scrolling="auto"
			id="sremittoframe" name="sremittoframe" allowtransparency="yes"></iframe>
</div>
</div>
<!---------------------------------------------------------Search Bank Lookup End------------------------------------------------------>
<!----------------------------------------------------------Search Account Lookup------------------------------------------------------>
<div class="sample_popup" id="searchaccount">
<div class="menu_form_header" id="searchaccount_drag"
	style="width: 550px; height: auto;"><img class="menu_form_exit"
	id="searchaccount_exit" src="images/form_exit.png" border="0" />&nbsp;&nbsp;&nbsp;<f:message
	key="accountSearch" /></div>
<div class="menu_form_body" id="accountbody"
	style="width: 550px; height: 430px; padding-left: 4px; overflow-y: hidden;">
		<iframe align="left" frameborder="0" style="padding: 0px;"
			height="430px;" width="100%" src="blank.html" scrolling="auto"
			id="saccountframe" name="saccountframe" allowtransparency="yes"></iframe>	
</div>
</div>
<!----------------------------------------------------------Search Account Lookup End-------------------------------------------------->
<!------------------------------------------------------search	Contact	Lookup--------------------------------------------------------->
<div class="sample_popup" id="searchcontact">
<div class="menu_form_header" id="searchcontact_drag"
	style="width: 650px;; height: auto;"><a href="#"><img
	class="menu_form_exit" id="searchcontact_exit"
	src="images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message
	key="searchContact" /></div>
<div class="menu_form_body" id="contactbody"
	style="width: 650px; height: 500px; overflow-y: hidden; padding-left: 15px;">
<iframe align="left" frameborder="0" style="padding: 0px;"
	height="500px;" width="100%" src="blank.html" id="searchContactFr"
	name="searchContactFr" allowtransparency="yes"></iframe></div>
</div>
<!-------------------------------------------------------search Contact Lookup------------------------------------------------------->
<!-------------------------------------------------	 Add Note  Lookup ----------------------------------------------------->
<div class="sample_popup" id="addnote">
<div class="menu_form_header" id="addnote_drag" style="width: 640px;">
<img class="menu_form_exit" id="addnote_exit" src="images/form_exit.png" />
&nbsp;&nbsp;&nbsp;<f:message key="addNotes" /></div>
<div class="menu_form_body" style="width: 640px; height: 295px;overflow-y:auto">
<table cellspacing="0" cellpadding="0" align="center"
	style="width: 100%;">
	<tr>
		<iframe align="left" frameborder="0" style="padding: 0px;"
			height="280px;" width="100%" src="blank.html" scrolling="auto"
			id="customerNoteFr" name="customerNoteFr" allowtransparency="yes"></iframe>
	</tr>
</table>
</div>
</div>
<!-----------------------------------------------  Add Note	 Lookup	End---------------------------------------------------->
<!--------------------------------------------------------Attach File Lookup----------------------------------------------------------->
<div class="sample_popup" id="attach">
<div class="menu_form_header" id="attach_drag" style="width: 700px;">
<img class="menu_form_exit" id="attach_exit" src="images/form_exit.png" />
&nbsp;&nbsp;&nbsp;<f:message key="attachFiles" /></div>
<div class="menu_form_body" style="width: 700px; height: 355px;overflow-y:auto">
<table cellspacing="0" cellpadding="0" align="center"
	style="width: 100%;">
	<tr>
		<iframe align="left" frameborder="0" style="padding: 0px;"
			height="340px;" width="100%" src="blank.html" scrolling="auto"
			id="customerAttachFileFr" name="customerAttachFileFr"
			allowtransparency="yes"></iframe>
	</tr>
</table>
</div>
</div>
<!--------------------------------------------------------Attach File Lookup End------------------------------------------------------->

<!------------------------------------------------------search	Parent Job	Lookup--------------------------------------------------------->
<div class="sample_popup" id="parentJob">
<div class="menu_form_header" id="parent_drag"	style="width: 750px;; height: auto;">
<a href="#"><img class="menu_form_exit" id="parent_exit" src="images/form_exit.png" /></a>&nbsp;&nbsp;&nbsp;<f:message
	key="searchParentJob" /></div>
<div class="menu_form_body" id="parentbody" style="width: 750px; height: 630px; overflow-y: hidden; padding-left: 15px;">
<iframe align="left" frameborder="0" style="padding: 0px;"
	height="630px;" width="100%" src="blank.html" id="searchParentFr"
	name="searchParentFr" allowtransparency="yes"></iframe></div>
</div>
<!-------------------------------------------------------search Parent Job Lookup------------------------------------------------------->

<!------------------------------------------------------search	Parent Job	Lookup--------------------------------------------------------->
<div class="sample_popup" id="parentcontract">
<div class="menu_form_header" id="parentcontract_drag"	style="width: 750px;; height: auto;">
<a href="#"><img class="menu_form_exit" id="parentcontract_exit" src="images/form_exit.png" /></a>&nbsp;&nbsp;&nbsp;<f:message
	key="searchParentContract" /></div>
<div class="menu_form_body" id="parentcontractbody" style="width: 750px; height: 630px; overflow-y: hidden; padding-left: 15px;">
<iframe align="left" frameborder="0" style="padding: 0px;"
	height="620px;" width="100%" src="blank.html" id="searchParentContrFr"
	name="searchParentContrFr" allowtransparency="yes"></iframe></div>
</div>
<!-------------------------------------------------------search Parent Job Lookup------------------------------------------------------->

<jsp:include page="../../common/requiredFields.jsp" flush="true" />


<div id="faderPane" class="faderStyle"><IMG
	src="images/fake-alpha-999.gif"></div>

<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="warehouseName" 
	target="warehouseName" 
	className="autocomplete" 
	parameters="entity=com.intertek.entity.Branch,entityWrapper=com.intertek.phoenix.ajax.BranchAjaxWrapper,~buName={buName},~name={warehouseName}"
	postFunction="updateWareHouseName"
	minimumCharacters="3" />
	
<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="salesPersonName" 
	target="salesPersonName" 
	className="autocomplete" 
	parameters="entity=com.intertek.entity.User,textAttribute=loginName,valueAttribute=loginName,~loginName={salesPersonName}"
	minimumCharacters="3" />
	
<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="secondarySalesPersonName" 
	target="secondarySalesPersonName" 
	className="autocomplete" 
	parameters="entity=com.intertek.entity.User,textAttribute=loginName,valueAttribute=loginName,~loginName={secondarySalesPersonName}"
	minimumCharacters="3" />
	
<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="projectManagerFullName" 
	target="projectManagerFullName" 
	className="autocomplete" 
	parameters="entity=com.intertek.phoenix.entity.Employee,entityWrapper=com.intertek.phoenix.ajax.EmployeeAjaxWrapper,~firstName={projectManagerFullName}"
	minimumCharacters="3" 
	postFunction="projectManagerPostAjax"/>				

<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="serviceLocationName" 
	target="serviceLocationName" 
	className="autocomplete" 
	parameters="entity=com.intertek.entity.ServiceLocation,entityWrapper=com.intertek.phoenix.ajax.ServiceLocationAjaxWrapper,valueAttribute=serviceLocationCode,~name={serviceLocationName}"
	minimumCharacters="3" 
	parser="new ResponseXmlParser()" 
	postFunction="updateServiceLoc"/>	



