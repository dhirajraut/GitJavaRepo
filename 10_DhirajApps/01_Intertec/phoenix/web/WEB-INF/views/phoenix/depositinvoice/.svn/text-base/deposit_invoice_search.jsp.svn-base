<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
	uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz"%>
<script language="javascript" src="js/depositinvoice/search_di.js"></script>
<form:form name="depositInvoiceSearchForm" method="POST"
	action="phx_deposit_invoice_search.htm">
	<input type="hidden" name="pageNumber" value="1" />
	<input type="hidden" name="updateFlag" value="false" />
	<div style="color: red;"><form:errors cssClass="error" /></div>
	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
			<tr>
			<td valign="top"><!---------------------------------------------BREADCRUMB TRAIL--------------------------------------------------->
			<div id="breadcrumbContainer">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				background="images/intopbluetrailbg.jpg">
				<tr>
					<td width="25"><img src="images/inlfttrailcorner.gif"
						width="8" height="22"></td>
					<td>
					<table height="22" border="0" align="left" cellpadding="0"
						cellspacing="0">
						<tr>
			                  <td class="breadcrumbtrailDeactive" style="background:none; padding-left:5px;">&#9658; <f:message key="Jobs" /></td>
			                  <td class="breadcrumbtrailDeactive"> <f:message key="search.depositInv.brcru" /> </td>
			                  <td align="right">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
			<div id="MainContentContainer"><!-- TABS CONTENTS -->
			<div id="tabcontainer">
			<div id="tabnav">
			<ul>
				<li><a href="#" rel="tab1"><span><f:message
					key="depositInvoiceSearch" /></span></a></li>
			</ul>
			</div>
			<!-- Sub Menus container. Do not remove -->
			<div id="tab_inner"><!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
			<div id="tab1" class="innercontent">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="mainApplTable">
				<tr>
					<th colspan="2"><f:message key="searchCriteria" /></th>
				</tr>
				<tr>
					<td width="15%" class="TDShade"><strong><f:message
						key="businessUnit" />:</strong><span class="redstar">*</span></td>
					<td width="35%" class="TDShadeBlue"><span class="id_input">
					<form:select id="sel3" cssClass="selectionBoxbig"
						path="buissinessUnit.value" items="${command.buNames}"
						itemLabel="name" itemValue="value" /> <form:errors
						path="buissinessUnit" cssClass="redstar" /></span></td>
				</tr>
				<tr>
					<td class="TDShade"><f:message key="branchCode" />:</td>
					<td class="TDShadeBlue"><form:input id="branchId"
						cssClass="inputBox" path="branchId.value" size="11" /> <a
						href="#ws" onClick="javascript:showWarehouseSearch();"><img
						src=" images/lookup.gif" alt="Lookup branch" width="13"
						height="13" border="0" /></a></td>

				</tr>
				<tr>
					<td class="TDShade"><f:message key="jobId" />:</td>
					<td class="TDShadeBlue"><form:input id="jobId"
						cssClass="inputBox" path="jobId.value" size="11" /></td>
				</tr>
				<tr>
					<td class="TDShade"><f:message key="depInvoiceNo" />:</td>
					<td class="TDShadeBlue"><form:input id="depInvoiceNo"
						cssClass="inputBox" path="depInvoiceNo.value" size="11" /></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="applTableBot">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td><input name="button" type="button"
								onClick="submitform('search')" class="button1" value="Search" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<br>
			<c:if test="${command.results != null}">
				<div id="depoinvsearchresults"><strong><f:message
					key="searchResults" /> </strong>
				<table width="100%" cellpadding="0" cellspacing="0"
					class="InnerApplTable">
					<tr>
						<th><span class="TDShade"><f:message key="jobNo" /></span></th>

						<th><span class="TDShade"><f:message key="invoiceNo" /></span></th>

						<th><span class="TDShade"><f:message key="paymtRec" /></span></th>
						
						<th><span class="TDShade"><f:message key="depositReference" /></span></th>
						<th><span class="TDShade"><f:message key="depositType" /></span></th>
					</tr>
					<c:forEach items="${command.results}" var="depInvoice"
						varStatus="status">
						<c:choose>
							<c:when test="${status.index%2==0}">
								<tr style="background-color: #FFFFFF;">
							</c:when>
							<c:otherwise>
								<tr style="background-color: #e7eeff;">
							</c:otherwise>
						</c:choose>
						<tr>
							<td><a href="phx_job_entry_ce.htm?jobNumber=${depInvoice.jobNo}">${depInvoice.jobNo}</a></td>
							<td><a href="invoice_view.htm?invoice=${depInvoice.di.invoiceFileName}&invoicetype=DepositInvoice">${depInvoice.invoiceNo}</a></td>
							<td><span class="id_input"> 
							<form:select id="sel4"
								cssClass="selectionBox" path="results[${status.index}].paymentReceived"
								items="${depInvoice.yesNos}" itemLabel="name"
								itemValue="value" /></span></td>
								
								<td><form:input cssClass="inputBox" size="11" id="depositReference" path="results[${status.index}].depositReference" /></td>
								<td>
								<span class="id_input"> 
								<form:select id="sel4"
								cssClass="selectionBox" path="results[${status.index}].depositInvoiceType"
								items="${depInvoice.depositType}" itemLabel="description" itemValue="name" />
								</span>
								</td>
						</tr>
					</c:forEach>
					    <tr>
					      <td>&nbsp;</td>
					      <td align="center">
					        <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
					          <a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
					        </c:forEach>
					      </td>
    					</tr>
					<tr></tr>
				</table>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" 	class="applTableBot">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td><input name="button" type="button"
								onClick="submitform('update')" class="button1" value="Submit" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</c:if></div>
			<!----------------- TAB 1 CONTAINER END ------------------------------ -->
			</div>
			<script type="text/javascript">
            dolphintabs.init("tabnav", 0)
          </script></div>
			<!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
			</div>
			<!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
			</td>
		</tr>
	</table>
</form:form>
<!-----------------------------------------Branch Code Lookup----------------------------------------------------->
<div class="sample_popup" id="jobbranchcode"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="jobbranchcode_drag"
	style="width: 750px;"><a href="#" onclick="resetBranchTypeFlag()">
<img class="menu_form_exit" id="jobbranchcode_exit"
	src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp; <f:message
	key="searchBranchCode" />
</div>
<div class="menu_form_body" id="jobbranchcodebody"
	style="width: 750px; height: 530px; padding-left: 4px; overflow-y: hidden;">
<iframe align="left" id="jobbu" frameborder="0" style="padding: 10px;"
	height="530px;" width="100%" scrolling="auto" allowtransparency="yes"
	src="blank.html"> </iframe>
</div>
</div>
<!-----------------------------------------Branch Code Lookup END------------------------------------------------->
