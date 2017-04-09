<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
	uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><head>
<script type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript" src="js/ce/common.js"></script>
<script type="text/javascript" src="js/ce/ce_jobInstruction.js"></script>
</head>

<!--------------------- JSTl Content --------------------------------------->


<!-- ------------------------ JSTL Content End --------------------------------------->


<form:form name="jobOrderOpInstrCEForm" method="POST"
	action="phx_job_operational_info_ce.htm">
	<div class="redtext"></div>

	<input type="hidden" name="refreshing" value="false" />
	<input type="hidden" name="createProject" value="false" />
	<input type="hidden" name="_page" value="1" />
	<form:hidden path="addOrDeleteTestLines" />
	<form:hidden path="nextPageFlag" />
	<form:hidden path="scrollFlag" />
	<form:hidden path="testIndex" />
	<form:hidden path="projectFlag" />
	<!--form:hidden path="attachedFileNames"/-->
	<form:hidden path="depIndex" />
	<form:hidden path="addOrDeleteDepositLines" />
	<form:hidden path="addOrDeleteSplitLines" />
	<form:hidden path="splitIndex" />
	<form:hidden path="prodIndex" />
	<form:hidden path="actionFlag" />


	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">

		<tr>
			<td valign="top"><!---------------------------------------------------------------- BREADCRUMB TRAIL  --------------------------------------------------------->

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
			<!------------------------------------------------------------------------------- BREADCRUMB TRAIL END ------------------------------------------------------------>
			
			<div id="breadcrumbContainer">
			<table width="100%" border="0" bordercolor="blue" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan=3><div style="width: auto; padding: -left : 50px; padding-top: 5px; color: red;">
				<form:errors cssClass="error" /></div>
				</td></tr></table></div>
			<div id="MainContentContainer"><!---------------------------------------------------------- TABS CONTENTS ---------------------------------------------------------------------------------->
			<div id="tabcontainerji">
			<div id="tabnav">
			<ul>
				<li><a href="#" rel="tab1"><span><f:message
					key="jobInstructions" /></span></a></li>
			</ul>
			<label> <select name="sel5" id="sel5" class="selectionBoxrt"
				onChange="MM_jumpMenu('parent',this,1)">
				<option selected>Go to ... &gt;</option>
				<option value="phx_job_entry_ce.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="entry" /></option>
			</select> </label></div>
			<!-- Sub Menus container. Do not remove -->
			<div id="tab_inner"><!------------------------------------------------------------------------ TAB 1 CONTAINER -------------------------------------------------------------------->
			<div id="tab1" class="innercontent">
			<table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
				<tbody>
					<tr>
						<th><f:message key="jobInstructions" /><img
							src="images/separator2.gif" width="2" height="27"
							align="absmiddle" class="seperator" /><f:message key="jobNumber" />:
						${command.jobOrder.jobNumber }</th>
						<th class="thr"><f:message key="orderStatus" />:&nbsp; <form:select
							id="sel1" cssClass="selectionBox" path="status"
							items="${command.statusList}" itemLabel="name"
							itemValue="value" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<f:message key="operationalStatus" />:&nbsp; <form:select
							id="sel2" cssClass="selectionBox" path="operationalStatus"
							items="${command.operationalStatuses}" itemLabel="name"
							itemValue="value" /> <a href="#zz" onClick="javascript:updateSampleTrckingIframeSrc('${command.jobOrder.jobNumber}');">Sample Tracking</a></th>
						<th class="thr"><a href="#"
							style="cursor: hand; text-decoration: none;"
							onMouseOver="doTooltip(event, '<a href=# onClick=onAddProduct()>Add New Product</a><br><a href=# onclick=onAddDepositLines();>New Deposit Line Item</a>')"
							onMouseOut="hideTip()"><img src="images/icoadddoc.gif"
							alt="Add Line Items" hspace="2" border="0" /></a> <%--			         <a href="images/JobOrderEntryForm.jpg" target="_new"><img src="images/ico_print.gif" alt="Print Job Order" width="18" height="16" hspace="2" border="0" align="absmiddle" title="Print Job Order"></a> --%>
						<a href="#" onClick="onSaveLinesAndNext('${command.projectType}','${command.projectId}');"><img
							src="images/savennextbluarrow.gif" alt="Save and Go to Next Page"
							width="14" height="14" hspace="4" border="0" align="absmiddle"
							title="Save and Go to Next Page"></a> <a href="#"
							onClick="onSaveLines();"><img src="images/icosave.gif"
							alt="Save" title="Save" width="14" height="14" border="0"
							align="absmiddle" /></a></th>
					</tr>
					<tr>
						<td colspan="3" class="NoPadding"><!------------------------------------------------------------------------- VESSELS ----------------------------------------------------------------------------------->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="mainApplTable">
							<tr>
								<td width="98%" colspan="3" class="NoPadding"><!----------------------------------------------------------- PHOENIX PHASE 2 TOP BLOCKCODE STARTS FROM HERE-------------------------------------------------------------->

								<!--Start --> <!--------------------------------------------------------------- Sample Location Container Table V1 --------------------------------------------------------------------->
								<div id="samplelocContainerv1p1" class="samplelocContainer">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0" class="orderTable">
									<tr>
										<td colspan="14">
										<table border="0" cellpadding="0" cellspacing="0" width="100%">


											<!-- Product Start -->


											<!-- <tr>
												<td colspan="13">
												<table border="0" cellpadding="0" cellspacing="0"
													width="100%"> -->

											<c:forEach items="${command.products}" var="product" varStatus="prodCounter">
											  	
												<tr>
													<th width="13" class="TDShadeGrey">
													<div id="bluarrowrightv1p1s1"
														style="visibility: hidden; position: absolute; z-index: 2; margin-left: 4px">
													<a href="#" onClick="javascript:showv1p1sample1Table();">
													<img src="images/bluarrowrightsml.gif" width="7"
														height="13" border="0" vspace="6" /></a></div>
													<div id="bluarrowdownv1p1s1"
														style="visibility: visible; position: relative; z-index: 1; margin-top: 6px">
													<a href="#" onClick="javascript:hidev1p1sample1Table();">
													<img src="images/bluarrowdownsml.gif" width="13" height="7"
														border="0" vspace="6" /></a></div>
													</th>
													<th nowrap>Product Description:<span
														class="redstar">*</span> <a name="addProd01"></a> 
														<form:input
														cssClass="inputBox"
														path="products[${prodCounter.index}].description"
														id="products[${prodCounter.index}].description" size="20"
														maxlength="256" />
														<form:errors path="products[${prodCounter.index}].description" cssClass="redstar"/>
														</th>
													<th nowrap><strong>Product Group:<span
														class="redstar">*</span></strong> <form:select
														id="products[${prodCounter.index}].productGroup"
														path="products[${prodCounter.index}].productGroup"
														cssClass="selectionBox"
														items="${command.productGroupList}" itemLabel="name"
														itemValue="value" disabled="${command.viewOnly}" />
														<form:errors path="products[${prodCounter.index}].productGroup" cssClass="redstar"/>
														</th>													
													<th nowrap><strong>Product:<span
														class="redstar">*</span></strong> <form:select
														id="products[${prodCounter.index}].productName"
														path="products[${prodCounter.index}].productName"
														cssClass="selectionBox"
														items="${icbfn:getProductFromProductGroup(command, product.productGroup)}" itemLabel="name"
														itemValue="value" disabled="${command.viewOnly}" />
														<form:errors path="products[${prodCounter.index}].productName" cssClass="redstar"/>
														</th>	
			<ajax:select
                  baseUrl="phx_ajax.htm"
                  source="products[${prodCounter.index}].productGroup"
                  target="products[${prodCounter.index}].productName"
                  parameters="namedQuery=getProductByProdGroup,entity=com.intertek.phoenix.entity.Product,entityWrapper=com.intertek.phoenix.ajax.ArrayAjaxWrapper,~productGroup={products[${prodCounter.index}].productGroup}"
                  parser="new ResponseXmlParser()"/> 
													<th colspan="10" class="thr" nowrap><div id="div22" style="width: 120px; text-align: right;">
													<!--  
													<a href="#"
														onclick="onAddTestLines('${prodCounter.index}','${command.jobNumber}');popAddTest();">
													-->	
													  <a href="#"
														onclick="onAddTestLines('${command.products[prodCounter.index].id}','${command.jobNumber}');popAddTest();">
														Add
													Test</a>&nbsp; <a href="#"
														onclick="onDeleteProduct('${command.products[prodCounter.index].id}');"><img
														src="images/delete.gif" alt="Delete Product" width="13"
														height="12" hspace="1px" border="0" align="absmiddle" /></a>
													<c:if test="${command.jobType!='CE'}">
													<a href="#"
														onClick="javascript:copyProduct('${prodCounter.index}','');"><img
														src="images/add.gif" alt="Copy Product" width="13"
														height="12" hspace="1px" border="0" align="absmiddle" /></a>
													</c:if>	
													</div></th>
													
													
												</tr>
												

												<tr>
												<td colspan="14" class="TDGray">
<!--  Start Test -->												
												<div align="center" id="sampleloctablev1p1" style="visibility:visible; display:block;">
												<table  border="0" cellpadding="0" cellspacing="0" width=100%>
												 <tr>
													<td><f:message key="line" /></td>
													<td width="3%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
													<td><f:message key="test/standard" /></td>
													<td><f:message key="description" /></td>
													<td align="center"><f:message key="quantity" /></td>
													<td align="center"><f:message key="uom" /></td>
													<td align="center"><f:message key="quotedAmount" /></td>
													<td align="center"><f:message key="estimatedRevenue" /></td>
													<td align="center"><f:message key="listPrice" /></td>
													<td align="center"><f:message key="billedAmount" /></td>
													<!--  
													<td align="center" nowrap><f:message key="billedPrice" /></td>
													-->
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>
													<div align="right"></div>
													</td>
												</tr>											
												<c:forEach items="${product.jobTest}" var="jobTest"	varStatus="testCounter">
												<c:if test="${command.products[prodCounter.index].jobTest[testCounter.index].notRelated}">
													
													<tr>
														<td width="5%">${command.products[prodCounter.index].jobTest[testCounter.index].linenumber}</td>
														<td width="3%">
														</td>
														<td width="13%">
														<c:if test="${command.products[prodCounter.index].jobTest[testCounter.index].manualTest==false}">												
														<form:input cssClass="inputBox"
															size="20"
															id="products[${prodCounter.index}].jobTest[${testCounter.index}].test.testId"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].test.testId"
															disabled="true" /> 
															<form:errors
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].test.testId"
															cssClass="redstar" /> 
														</c:if>
														<c:if test="${command.products[prodCounter.index].jobTest[testCounter.index].manualTest==true}">												
														<form:input cssClass="inputBox"
															size="20"
															id="products[${prodCounter.index}].jobTest[${testCounter.index}].methodology"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].methodology"
															disabled="true" /> 
															<form:errors
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].methodology"
															cssClass="redstar" /> 
														</c:if>
															</td>
														<td width="24%"><form:input cssClass="inputBox"
															size="50"
															id="products[${prodCounter.index}].jobTest[${testCounter.index}].lineDescription"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].lineDescription"
															disabled="${command.viewOnly}" /></td>
														<td width="8%" align="center" nowrap><form:input
															cssClass="inputBox" size="4"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].quantity"
															disabled="${command.viewOnly}" />&nbsp;&nbsp;</td>

														 
														<td width="9%" align="center" nowrap>
														 
														<form:select
															id="sel3" cssClass="selectionBox"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].uom"
															items="${command.uomList}" itemLabel="name"
															itemValue="value" disabled="${command.viewOnly}" />
															
															&nbsp;&nbsp;</td>

														<td width="10%" align="center" nowrap>														  
														<form:input
															cssClass="inputBox" size="4"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].quotedAmount"
															disabled="${command.viewOnly}" />															
															</td>
														<td width="10%" align="center" nowrap><form:input
															cssClass="inputBox" size="4"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].forcastedRevenue"
															disabled="${command.viewOnly}" />
														</td>
														<td width="8%" align="center" nowrap>${command.products[prodCounter.index].jobTest[testCounter.index].unitPrice}</td> 
														<td width="5%" align="center" nowrap>
														${command.products[prodCounter.index].jobTest[testCounter.index].billedAmount}
														</td>
														<td width="8%" align="center" nowrap>
														<a href="#o${prodCounter.index}"
															onClick="javascript:updateOthersIframeSrc('${prodCounter.index}','${command.jobNumber}','${command.products[prodCounter.index].jobTest[testCounter.index].id}','-1');"
															onMouseOver="doTooltip(event, '<table style=width:400px;><tr><td width=120px><b><f:message key="startDate"/>:</b></td><td><c:out value="${command.products[prodCounter.index].jobTest[testCounter.index].startDate}" /></td></tr><tr><td width=120px><b><f:message key="endDate"/>:</b></td><td><c:out value="${command.products[prodCounter.index].jobTest[testCounter.index].endDate}" /></td></tr><tr><td width=120px><b><f:message key="tastReadyDate"/>:</b></td><td><c:out value="${command.products[prodCounter.index].jobTest[testCounter.index].taskReadyDate}" /></td></tr><tr><td><b><f:message key="sampleDescription"/>:</b></td>  <td>${command.products[prodCounter.index].jobTest[testCounter.index].sampleDescription}</td></tr><tr><td><b><f:message key="serviceOffering"/>: </b></td><td>${command.products[prodCounter.index].jobTest[testCounter.index].serviceOfferingName}</td></tr><tr><td><b><f:message key="po"/>: </b></td><td>${command.products[prodCounter.index].jobTest[testCounter.index].poNumber}</td></tr><tr><td><b><f:message key="billingStatus"/>: </b></td><td>${command.products[prodCounter.index].jobTest[testCounter.index].billingStatus}</td></tr><tr><td><b><f:message key="operationalStatus"/>: </b></td><td>${command.products[prodCounter.index].jobTest[testCounter.index].operationalStatus}</td></tr></table>')"
															onMouseOut="hideTip()"> <f:message key="other" /></a></td>
														<td align="right" nowrap>

														<c:if test="${not empty jobTest.notes}">
															<img src="images/corner_arrow.gif" alt="contain note(s)" border="0">
														</c:if>
														
													<a href="#"
														onClick="javascript:updateNoteIframeSrc('${command.jobNumber}','${command.products[prodCounter.index].jobTest[testCounter.index].id}');popup_show('addnote${counter.index}', 'addnote_drag${counter.index}', 'addnote_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable(); "><img
														src=" images/icoaddnote.gif" alt="Add a note" width="18"
														height="15" hspace="5" border="0" /></a>
													</td>
													<td align="right" nowrap><%--<authz:authorize ifAnyGranted="FileUpload,FileUploadNoDel">--%>
														
													<a href="#"
														onClick="javascript:updateAttachIframeSrc('${command.jobNumber}','${command.products[prodCounter.index].jobTest[testCounter.index].id}');popup_show('addattach', 'addattach_drag${counter.index}', 'addattach_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();">
													<img src=" images/icoattach.gif" alt="Add an attachment"
														width="13" height="16" hspace="5" border="0" /></a>
													</td>

														<td nowrap>
														<div align="right"> 
														 
														 
														<a href="#s" 
														onMouseOver="doTooltip(event, '<a href=# onclick=onAddSplitTestLines(\'${command.jobNumber}\',${prodCounter.index},${command.products[prodCounter.index].jobTest[testCounter.index].id});>Add Split Test</a><br><a href=# onClick=onAddSplitTestLines(\'${command.jobNumber}\',${prodCounter.index},${command.products[prodCounter.index].jobTest[testCounter.index].id},\'newTest\');popAddTest();>Add New Split Test</a>')"
														
														onMouseOut="hideTip()"> <img
															src=" images/add.gif" alt="Add Split Test Rows"
															width="13" height="12" hspace="2" border="0"
															title="Add SplitLine" /></a>
															<!-- onClick="javascript:onAddSplitTestLines('${command.jobNumber}',${prodCounter.index},${command.products[prodCounter.index].jobTest[testCounter.index].id});popAddTest();"   -->
														
														<a name="testLine" /></a> <a href="#d"> <img
															src=" images/delete.gif" alt="Del Row" width="13"
															height="12" hspace="2" border="0"
															onClick="onDelTestLines(${prodCounter.index},'${command.products[prodCounter.index].jobTest[testCounter.index].id}');" />
														</a></div>
														</td>
													</tr>												
<!--  Split Job Test Start -->

												<c:forEach items="${jobTest.split}" var="split"	varStatus="splitCounter">
													<tr class="thc">
														<td class="TDGray" width="5%">
														&nbsp;&nbsp;${command.products[prodCounter.index].jobTest[testCounter.index].linenumber}.${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].linenumber}
														</td>													
														<td width="3%" class="TDGray">
														&nbsp;&nbsp;
														<form:checkbox path="products[${prodCounter.index}].jobTest[${testCounter.index}].split[${splitCounter.index}].invoiceable" onmouseover="doTooltip(event, 'Invoicing Allowed?')" onmouseout="hideTip()" />
														
														</td>
														<td width="13%" class="TDGray">
														<c:if test="${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].manualTest==false}">
														<form:input cssClass="inputBox"
															size="20"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].split[${splitCounter.index}].test.testId"
															disabled="true" /> <form:errors
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].split[${splitCounter.index}].test.testId"
															cssClass="redstar" />
														</c:if>
														<c:if test="${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].manualTest==true}">
														<form:input cssClass="inputBox"
															size="20"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].split[${splitCounter.index}].methodology"
															disabled="true" /> <form:errors
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].split[${splitCounter.index}].methodology"
															cssClass="redstar" />
														</c:if>
														</td>
														<td width="24%" class="TDGray"><form:input cssClass="inputBox"
															size="50"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].split[${splitCounter.index}].lineDescription"
															disabled="${command.viewOnly}" /></td>
														<td width="8%" align="center" nowrap class="tdc"><form:input
															cssClass="inputBox" size="4"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].split[${splitCounter.index}].quantity"
															disabled="${command.viewOnly}" />&nbsp;&nbsp;</td>

														 
														<td width="9%" align="center" nowrap class="tdc">
														 
														<form:select
															id="sel3" cssClass="selectionBox"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].split[${splitCounter.index}].uom"
															items="${command.uomList}" itemLabel="name"
															itemValue="value" disabled="${command.viewOnly}" />
															
															&nbsp;&nbsp;</td>

														<td width="10%" align="center" nowrap class="TDShadeGrey">														  
														<!--  
														<form:input
															cssClass="inputBox" size="4"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].split[${splitCounter.index}].quotedAmount"
															disabled="${command.viewOnly}" />
														-->															
															</td>
														<td width="10%" align="center" nowrap class="TDShadeGrey"><form:input
															cssClass="inputBox" size="4"
															path="products[${prodCounter.index}].jobTest[${testCounter.index}].split[${splitCounter.index}].forcastedRevenue"
															disabled="${command.viewOnly}" />
															</td>

														<td width="8%" align="center" nowrap class="TDShadeGrey">${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].unitPrice}</td>
														<%-- 
														<td width="5%" align="center" nowrap class="TDShadeGrey">
														${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].quotedAmount}
														</td>
														--%>
														<td width="8%" align="center" nowrap class="TDShadeGrey"><a
															href="#o${prodCounter.index}"
															onClick="javascript:updateOthersIframeSrc('${prodCounter.index}','${command.jobNumber}','${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].id}','-1');"
															<%-- 
															onClick="javascript:updateOthersIframeSrc('${prodCounter.index}','${command.jobNumber}','${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].id}','-1');popup_show('others${prodCounter.index}', 'others_drag${prodCounter.index}', 'others_exit${prodCounter.index}', 'screen-corner', 120, 20);hideIt();showPopupDiv('others${prodCounter.index}','othersbody${prodCounter.index}');popupboxenable();"
															--%>
															onMouseOver="doTooltip(event, '<table style=width:400px;><tr><td width=120px><b><f:message key="startDate"/>:</b></td><td><f:formatDate value="${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].startDate}" /></td></tr><tr><td width=120px><b><f:message key="endDate"/>:</b></td><td><f:formatDate value="${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].endDate}" /></td></tr><tr><td width=120px><b><f:message key="tastReadyDate"/>:</b></td><td><f:formatDate value="${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].taskReadyDate}" /></td></tr><tr><td><b><f:message key="sampleDescription"/>:</b></td>  <td>${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].sampleDescription}</td></tr><tr><td><b><f:message key="serviceOffering"/>: </b></td><td>${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].serviceOfferingName}</td></tr><tr><td><b><f:message key="po"/>: </b></td><td>${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].poNumber}</td></tr><tr><td><b><f:message key="billingStatus"/>: </b></td><td>${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].billingStatus}</td></tr><tr><td><b><f:message key="operationalStatus"/>: </b></td><td>${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].operationalStatus}</td></tr></table>')"
															onMouseOut="hideTip()"> <f:message key="other" /></a></td>
														<td align="right" nowrap class="TDShadeGrey">
														
													<a href="#"														
														onClick="javascript:updateNoteIframeSrc('${command.jobNumber}','${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].id}');popup_show('addnote${counter.index}', 'addnote_drag${counter.index}', 'addnote_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable(); ">							
														<img
														src=" images/icoaddnote.gif" alt="Add a note" width="18"
														height="15" hspace="5" border="0" /></a>
													</td>
														<td align="right" nowrap><%--<authz:authorize ifAnyGranted="FileUpload,FileUploadNoDel">--%>

													<a href="#"
														onClick="javascript:updateAttachIframeSrc('${command.jobNumber}','${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].id}');popup_show('addattach', 'addattach_drag${counter.index}', 'addattach_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();">
													<img src=" images/icoattach.gif" alt="Add an attachment"
														width="13" height="16" hspace="5" border="0" /></a>
														
													</td>

														<td nowrap class="TDShadeGrey">
														<div align="right"> 
														<!--  
														<a href="#s"> <img
															src=" images/add.gif" alt="Add Split Test Rows"
															width="13" height="12" hspace="2" border="0"
															title="Add SplitLine"
															onClick="onAddSplitTestLines(${prodCounter.index})" /> </a>
														-->
														<a name="testLine" /></a> <a href="#d"> <img
															src=" images/delete.gif" alt="Del Row" width="13"
															height="12" hspace="2" border="0"
															onClick="onDelSplitTestLines(${prodCounter.index},${testCounter.index},'${command.products[prodCounter.index].jobTest[testCounter.index].split[splitCounter.index].id}');" />
														</a></div>
														</td>
													</tr>

													</c:forEach>


<!-- Split End -->

													<!------------------------------ Others Lookup ---------------------------------------------------->
													<div class="sample_popup" id="others${prodCounter.index}">
													<div class="menu_form_header"
														id="others_drag${prodCounter.index}" style="width: 680px;">
													<img class="menu_form_exit"
														id="others_exit${prodCounter.index}"
														src=" images/form_exit.png" />
													&nbsp;&nbsp;&nbsp;Additional Information</div>
														<div class="menu_form_body"
															id="othersbody${prodCounter.index}"
															style="width: 680px; height: auto; overflow-y: hidden;">
														<iframe id="othersFr${prodCounter.index}"
															name="othersFr${prodCounter.index}" width="100%"
															height="400px" scrolling="auto" frameborder="0"
															allowtransparency="yes"
															style="border: 0px; background: none" src="about:blank"></iframe>
														</div>
													</div>
													</div>
													<!-------------------------------------------------------------------- Others Lookup END --------------------------------------------------------------->
												</c:if>
												</c:forEach>
											</table>
											</div>
											</td>
											</tr>
											</c:forEach>
											</div>
											</table>
											</td>
											</tr>
									<!--  enabled the comment  -->		
									 	</table> 
								</div>
								</td>
							</tr>
							<!-- update Split Line -->

							
						</table>
						<div id="depositsection">
						<table border="0" cellpadding="0" cellspacing="0"
							class="InnerApplTableSC" style="width: 100%; border: none">
							<tr>
							<!--  
								<th width="25%" class="thc"><f:message key="depostiAmount" /></th>
							-->
								<th width="5%" class="thc"><f:message key="line" /></th>
								<th width="25%" class="thc"><f:message key="depositType" /></th>
								<th width="25%" class="thc"><f:message
									key="depositReference" /></th>
								<th width="20%" class="thc"><f:message key="depositPaid" /></th>
								<th width="20%" class="thc"><f:message key="status" /></th>
								<th width="10%" class="thc"><div align="right">&nbsp;</div></th>
							</tr>
							<c:forEach items="${command.depositOrderLineItems}"
								var="depositOrderLineItems" varStatus="dcounter">
								<tr>
								<!--  
									<td class="TDShadeBlue"
										style="border-right: #7c92be dashed 1px; text-align: center;"><form:input
										cssClass="inputBox" size="4"
										path="depositOrderLineItems[${dcounter.index}].availableAmount"
										disabled="${command.viewOnly}"  onchange="copytoDepositAmount('${dcounter.index}');"/></td>
										<form:hidden path="depositOrderLineItems[${dcounter.index}].depositAmount" />
								-->
									<td width="5%" class="tdc">${command.depositOrderLineItems[dcounter.index].sortNumber}</td>
									<td class="tdc">${command.depositOrderLineItems[dcounter.index].depositType}&nbsp;&nbsp;</td>
									<td class="tdc"><form:input cssClass="inputBox" size="25"
										path="depositOrderLineItems[${dcounter.index}].depositReference"
										disabled="${'INVOICED' == command.depositOrderLineItems[dcounter.index].status}" />
										<form:errors path="depositOrderLineItems[${dcounter.index}].depositReference" cssClass="redstar"/>										
										</td>
									<td class="tdc">${command.depositOrderLineItems[dcounter.index].depositPaid}</td>
									<td class="tdc">${command.depositOrderLineItems[dcounter.index].status}</td>
									<td class="tdc">
									<div align="right"><a href="#dl"><img
										src=" images/delete.gif" alt="Del Row" width="13" height="12"
										hspace="5" border="0"
										onClick="onDelDepositLines(${command.depositOrderLineItems[dcounter.index].id});" /></a></div>
									</td>
								</tr>


							</c:forEach>


						</table>
						</div>

						<!--End --> <!-------------------------------------------------------------- PHOENIX PHASE 2 TOP BLOCK CODE ENDS HERE----------------------------------------------------------------->
						
<!--  
			</table>
			</tbody>
-->			
<!--  			
	</table>
-->	
	<table>
		<tr>
			<td></td>
		</tr>
	</table>
	<!----------------------------------------------------------------------- INSTRUCTIONS ---------------------------------------------------------------------->
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="InnerApplTable">
		<tr>
			<td width="16">
			<div id="bluarrowrightins" class="bluarrowrightin"><a
				href="#instructions" onClick="javascript:showInstructions();"> <img
				src="images/bluarrowright.gif" width="8" height="16" border="0"
				vspace="4" style="margin-top: 2px;" /></a></div>
			<div id="bluarrowdownins" class="bluarrowdownin"><a
				href="#instructions" onClick="javascript:hideInstructions();"> <img
				src="images/bluarrowdown.gif" width="16" height="8" border="0"
				vspace="4" /></a></div>
			</td>
			<td width="871" class="TDShade"><a name="instructions"></a><f:message
				key="instructions" /></td>
			<td width="64" class="TDShade">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" class="NoPadding">
			<div id="instructionsContainer">
			<table class="orderTable" width="100%" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<th width="50%"><f:message key="operationInstructions" />:</th>
					<th width="50%"><f:message key="sampleInstructions" />:</th>
				</tr>
				<tr>
					<td align="center"><label> <form:textarea cols="60"
						rows="4" path="operationInstructions"
						disabled="${command.viewOnly}" /> <form:errors
						path="operationInstructions" cssClass="redstar" /> </label></td>

					<td align="center"><form:textarea cols="60" rows="4"
						path="sampInstructions" disabled="${command.viewOnly}" /> <form:errors
						path="sampInstructions" cssClass="redstar" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th><f:message key="labInstructions" />:</th>
					<th><f:message key="shipInstructions" />:</th>
				</tr>
				<tr>
					<td align="center"><form:textarea cols="60" rows="4"
						path="labInstructions" disabled="${command.viewOnly}" /> <form:errors
						path="labInstructions" cssClass="redstar" /></td>
					<td align="center"><form:textarea cols="60" rows="4"
						path="shipInstructions" disabled="${command.viewOnly}" /> <form:errors
						path="shipInstructions" cssClass="redstar" /></td>
				</tr>
				<tr>
					<th><f:message key="reportInstructions" />:</th>
					<th><f:message key="billInstructions" />:</th>
				</tr>
				<tr>
					<td align="center"><form:textarea cols="60" rows="4"
						path="reptInstructions" disabled="${command.viewOnly}" /> <form:errors
						path="reptInstructions" cssClass="redstar" /></td>
					<td align="center"><form:textarea cols="60" rows="4"
						path="billInstructions" disabled="${command.viewOnly}" /> <form:errors
						path="billInstructions" cssClass="redstar" /></td>
				</tr>
				<tr>
					<th><f:message key="otherInstructions" />:</th>
					<th>&nbsp;</th>
				</tr>
				<tr>
					<td><form:textarea cols="60" rows="4" path="otherInstructions"
						disabled="${command.viewOnly}" /> <form:errors
						path="otherInstructions" cssClass="redstar" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>
	<!---------------------------------------------------------------------- INSTRUCTIONS END -------------------------------------------------------->

	<!-------------------------------------------------------------------------- PROJECTS -------------------------------------------------------------->
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
		<tr>
			<td>
				<table>
					<tr>
						<td class="TDShade"><strong><f:message key="projectNumber" />:</strong></td>
						<td class="TDShadeBlue"><form:input path="projectId" cssClass="inputBox" disabled="true" /> <form:errors
							path="projectId" cssClass="redstar" /></td>
						<td class="TDShade">&nbsp;</td>
						<c:if test="${command.allowCreateUpdateProject}">
							<td class="TDShadeBlue">
								<input name="Submit2" type="button" class="button1" value="Create Project"
								onClick="createOrUpdateProject(this.form, '${command.jobOrder.jobNumber}');">
							</td>
						</c:if>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<!----------------------------------------------------------------- ProjectsS END -------------------------------------------------------------------------------->
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="applTableBot">
		<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right"></td>
					<td class="thr"><a
						href="phx_ce_pdf.htm?jobNumber=${command.jobOrder.jobNumber}"
						target="_blank"><img src="images/ico_print.gif"
						alt="Print Job Order" width="18" height="16" hspace="2" border="0"
						align="absmiddle" title="Print Job Order"></a> <a href="#"
						onclick="onSaveLinesAndNext()"><img
						src="images/savennextbluarrow.gif" alt="Save and Go to Next Page"
						width="14" height="14" hspace="4" border="0" align="absmiddle"
						title="Save and Go to Next Page"></a><a href="#"
						onclick="onSaveLines()"><img src="images/icosave.gif"
						alt="Save" title="Save" width="14" height="14" border="0"
						align="absmiddle"/></a></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<div><!------------------------------------------------------------- goto ------------------------------------------------------------------------->
	<table width="100%" cellspacing="0">
		<tr>
			<td height="24"><select name="bottomNavigation" id="bottomNavigation"
				class="SelectionBoxrt" onChange="MM_jumpMenu('parent',this,1)">
				<option selected>Go to ... &gt;</option>
				<option value="phx_job_entry_ce.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="entry" /></option>
			</select></td>
		</tr>
	</table>
	<!---------------------------------------------------------------- goto END --------------------------------------------------------------------->
	</div>
	</div>
	<!--------------------------------------------------------------------------- TAB 1 CONTAINER END --------------------------------------------------------->
	</div>
	</div>
	</div>
	<script type="text/javascript">      
    //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
   dolphintabs.init("tabnav", 0)      
  </script>
	<!---------------------------------------------------------------------- TAB CONTENT END ------------------------------------------------------------------------>
	<!---------------------------------------------------------------- MAIN CONTAINER END -------------------------------------------------------------------->
	<!--------------------------------------- Integration Log Popup ------------------------------------------------- -->
	<div class="sample_popup" id="integrationlog">
	<div class="menu_form_header" id="integrationlog_drag"
		style="width: 625px;"><img class="menu_form_exit"
		id="integrationlog_exit" src="images/form_exit.png" />
	&nbsp;&nbsp;&nbsp;<f:message key="integrationLog" /></div>
	<div class="menu_form_body" style="width: 625px; height: auto;">
	<table width="98%" align="center" cellpadding="0" cellspacing="0"
		class="InnerApplTable" style="margin-bottom: 15px;">
		<tbody>
			<tr>
				<td><iframe id="addeditserivcebox" width="100%" height="290px"
					scrolling="auto" frameborder="0" allowtransparency="yes"
					style="border: 0px; background: none"
					src="view_job_integration_log.htm?jobNumber=${command.jobOrder.jobNumber}">
				</iframe></td>
			</tr>
		</tbody>
	</table>
	</div>
	</div>
	<!--------------------------------------- Integration Log Popup End ---------------------------------------------------------------->

	<jsp:include page="../../common/requiredFields.jsp" flush="true" />
	

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
			id="jobNoteFr" name="jobNoteFr" allowtransparency="yes"></iframe>
	</tr>
</table>
</div>
</div>
<!-----------------------------------------------  Add Note	 Lookup	End---------------------------------------------------->




<!-------------------------------------------------	 Add attach  Lookup ----------------------------------------------------->
<div class="sample_popup" id="addattach">
<div class="menu_form_header" id="addattach_drag" style="width: 640px;">
<img class="menu_form_exit" id="addattach_exit" src="images/form_exit.png" />
&nbsp;&nbsp;&nbsp;<f:message key="attachaFile" /></div>
<div class="menu_form_body" style="width: 640px; height: 295px;overflow-y:auto">
<table cellspacing="0" cellpadding="0" align="center"
	style="width: 100%;">
	<tr>
		<iframe align="left" frameborder="0" style="padding: 0px;"
			height="280px;" width="100%" src="blank.html" scrolling="auto"
			id="jobAttchFr" name="jobAttchFr" allowtransparency="yes"></iframe>
	</tr>
</table>
</div>
</div>
<!-----------------------------------------------  Add attach	 Lookup	End---------------------------------------------------->

<!-- ----------------------------------- Add Tests Lookup ------------------------------------------------- -->
<div class="sample_popup" id="addTest">
    <div class="menu_form_header" id="addTest_drag" style="width:850px;"> 
      <img class="menu_form_exit"   id="addTest_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="tests"/></div>                                                           
        <div class="menu_form_body" id="testbody" style="width:850px; height:auto;" align="center">
      <iframe id="addTestId"  width="98%" height="1px" scrolling="auto" frameborder="0"
       allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
     </div>
  </div>
<!-- --------------------------------- Add Tests Lookup END ----------------------------------------- -->
<!------------------------------------------------------Sample Tracking	Lookup--------------------------------------------------------->
<div class="sample_popup" id="sampletracking">
<div class="menu_form_header" id="sampletracking_drag"	style="width: 1000px;height: auto">
<a href="#"><img class="menu_form_exit" id="sampletracking_exit" src="images/form_exit.png" /></a>&nbsp;&nbsp;&nbsp;<f:message key="cg.st.title"/></div>
<div class="menu_form_body" id="sampletrackingbody" style="width: 1000px; height: auto; overflow-y: hidden;padding-left: 15px;">
<iframe align="left" frameborder="0" style="padding: 0px;"
	height="400px;" width="98%" id="sampletrackingFr" name="sampletrackingFr" allowtransparency="true"></iframe></div>
</div>
<!-------------------------------------------------------Sample Tracking Lookup------------------------------------------------------->
						</td>
					</tr>
			</table>
			</div>
			</div>
			</div>
			</div>
			</td>
		</tr>
	</table>
</form:form>
<script>
showOthersFrame('${command.jobOrder.jobNumber}');
</script>
<div id="faderPane" class="faderStyle"><IMG
	src="images/fake-alpha-999.gif"></div>
