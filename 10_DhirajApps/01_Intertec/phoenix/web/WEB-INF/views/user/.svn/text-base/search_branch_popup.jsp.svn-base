<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%><head>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><head>

 <script language="javascript">
function submitParentForm(){ 
document.branchSearchPopUpForm.bxcel.value="false";
 top.document.forms[0].submit();
}
function sortBranchByBuName(){
document.branchSearchPopUpForm.pageNumber.value = "1";
document.branchSearchPopUpForm.sortFlag.value = "businessUnit.name";
document.branchSearchPopUpForm.bxcel.value="false";
document.branchSearchPopUpForm.submit();
}
function sortBranchByBranchCode(){
document.branchSearchPopUpForm.pageNumber.value = "1";
document.branchSearchPopUpForm.sortFlag.value = "name";
document.branchSearchPopUpForm.bxcel.value="false";
document.branchSearchPopUpForm.submit();
}
function sortBranchByDescription(){
document.branchSearchPopUpForm.pageNumber.value = "1";
document.branchSearchPopUpForm.sortFlag.value = "description";
document.branchSearchPopUpForm.bxcel.value="false";
document.branchSearchPopUpForm.submit();
}

function sortBranchByStatus(){
document.branchSearchPopUpForm.pageNumber.value = "1";
document.branchSearchPopUpForm.sortFlag.value = "status";
document.branchSearchPopUpForm.bxcel.value="false";
document.branchSearchPopUpForm.submit();
}
function submitFunction()
{
	document.branchSearchPopUpForm.pageNumber.value = "1";
	document.branchSearchPopUpForm.sortFlag.value = "";
	document.branchSearchPopUpForm.bxcel.value="false";
	document.branchSearchPopUpForm.submitFlag.value="true";
	document.branchSearchPopUpForm.submit();
}
function submitxcel(){
document.branchSearchPopUpForm.bxcel.value="true";
document.branchSearchPopUpForm.sortFlag.value = "";
document.branchSearchPopUpForm.submit();
}

 </script>
</head>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>



<form:form name="branchSearchPopUpForm" method="POST" action="search_branch_popup.htm">
	<form:hidden path="searchType" />
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
	<form:hidden path="divName1" />
	<form:hidden path="divName2" />
	 <form:hidden path="submitFlag"/>
	<form:hidden path="sortFlag"/>
	<form:hidden path="limsFlag"/>
<!-- START : #119240 : 22/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 22/06/09 --> 
	 <input type="hidden" name="bxcel" value="false"/>
	<input type="hidden" name="buname.disabled" value="${param['buname.disabled']}"/>

<input type="hidden" name="pageNumber" value="1" />
	
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" class="MainTable" style="padding:-left:10px;padding-top:0px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:right:5px;">
						<tr>
							<th colspan="2"><f:message key="branchSearch"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="businessUnitName"/>: </td>
							<td width="80%" class="TDShadeBlue">
							<c:choose>
								<c:when test="${param['buname.disabled']=='true' or empty param['buname.disabled']}">
								    <!-- <form:input id="buname" cssClass="inputBox" path="businessUnit.value" readonly="true"/> -->
									<c:choose>
									<c:when test="${command.searchForm == 'opInstrForm'}">
									 <!-- START : #119240 -->
									<%-- <form:input id="buname" cssClass="inputBox" path="businessUnit.value"/> --%>
									<form:input id="buname" cssClass="inputBox" path="businessUnit.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
									 <!-- END : #119240 -->
									</c:when>
									<c:otherwise>
									<form:input id="buname" cssClass="inputBox" path="businessUnit.value" readonly="true"/>
									</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<form:select cssClass="selectionBox" id="buname" path="businessUnit.value" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value"/>                    
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td class="TDShade" nowrap><f:message key="branchCode"/>:</td>
							<td class="TDShadeBlue">
								 <!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="name.value" />  --%>
								<form:input cssClass="inputBox" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
								 <!-- END : #119240 -->
							</td>
						</tr>
						<tr>
							<td class="TDShade" nowrap><f:message key="description"/> : </td>
							<td class="TDShadeBlue">
							 <!-- START : #119240 -->
							<%-- <form:input cssClass="inputBox" path="desc.value" /> --%>
							<form:input cssClass="inputBox" path="desc.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
							 <!-- END : #119240 -->
							</td>
						
						</tr>
						<tr>
						<td class="TDShade"><f:message key="status" />: </td>
						<td class="TDShadeBlue">
							 <!-- START : #119240 -->
							<%-- <form:select id="sel1"
								cssClass="selectionBox" path="status.value"
								items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name"
								itemValue="value" /> --%>
							<form:select id="sel1"
								cssClass="selectionBox" path="status.value"
								items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name"
								itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}" /> 
							 <!-- END : #119240 -->
						</td>
						
						</tr>

					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="button" type="button" onClick="submitFunction()" class="button1" value="<f:message key="search"/>"  /></td>
										<td style="text-align:right;"><a href="#start"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<c:if test="${command.results != null}">
						<div id="branchcodesearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortBranchByBuName();" ><span class="TDShade" nowrap><f:message key="businessUnitName"/></span></a></th>
									<th><a href="#start" onClick="sortBranchByBranchCode();" ><span class="TDShade" nowrap><f:message key="branchCode"/></span></a></th>
									<th><a href="#start" onClick="sortBranchByDescription();" ><span class="TDShade" nowrap><f:message key="description"/></span></a></th>
									<th><a href="#start" onClick="sortBranchByStatus();" ><span class="TDShade" nowrap><f:message key="status"/></span></a></th>
								</tr>
								<c:forEach items="${command.results}" var="branch" varStatus="status">
								<c:choose>
								<c:when test="${status.index%2==0}">
								<tr style="background-color:#FFFFFF;">
								</c:when>
								<c:otherwise>
								<tr style="background-color:#e7eeff;">                    
								</c:otherwise>
								</c:choose>
									<c:choose>
									<c:when test="${command.searchForm!='jobsForm' && command.searchForm!='opInstrForm' && command.searchForm!='createSamLimsIntForm'}">
									<td>${branch.businessUnit.name}</td>
										<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${branch.name}');top.hidebranchcode('${command.divName1}','${command.divName2}');top.popupboxclose();submitParentForm();">${branch.name}</a></td>

													<td>${branch.description}</td>
													<td><f:message key="activeStatus${branch.status}"/></td>
									</c:when>
									<c:otherwise>
										<td>${branch.businessUnit.name}</td>
										<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${branch.name}');top.hidebranchcode('${command.divName1}','${command.divName2}');top.popupboxclose();">${branch.name}</a></td>

													<td>${branch.description}</td>
													<td><f:message key="activeStatus${branch.status}"/></td>
									</c:otherwise>
									</c:choose>
								</tr>
								</c:forEach>
							<tr>
							<td>&nbsp;</td>
		                    <td align="center">
							<!-- START : #119240 : 22/06/09 --> 
								<%--	<c:forEach items="${command.pagination.pages}" var="page" varStatus="status"> --%>
		
									<SCRIPT LANGUAGE="JavaScript">

									function submitSearch(pageNumber) {	 												 
													document.branchSearchPopUpForm.pageNumber.value = pageNumber;
													document.branchSearchPopUpForm.bxcel.value="false";
													document.branchSearchPopUpForm.submitFlag.value="true";
													// START : #119240 : 29/06/09
													document.branchSearchPopUpForm.checkPageSort.value = "true";
													// END : #119240 : 29/06/09
													document.branchSearchPopUpForm.submit();
												  }	
									</SCRIPT>
								<%--	<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>&nbsp;&nbsp;
			                  </c:forEach> --%>
								<%@ include file="../common/pagination.jsp" %>
    					<!-- END : #119240 : 22/06/09 -->
							</td>
							</tr>								
							</table>
						</div>
					</c:if>
				</div>
			</td>
		</tr>
	</table>
</form:form>

<c:if test="${fn:length(command.results)==1}">
<script LANGUAGE="JavaScript">
	<c:forEach items="${command.results}" var="branch" varStatus="status">
		<c:choose>
		<c:when test="${command.searchForm!='jobsForm' && command.searchForm!='opInstrForm' && command.searchForm!='createSamLimsIntForm'}">
			top.return_popup_search_result('${command.inputFieldId}', '${branch.name}');top.hidebranchcode('${command.divName1}','${command.divName2}');top.popupboxclose();submitParentForm();
		</c:when>
		<c:otherwise>
			top.return_popup_search_result('${command.inputFieldId}', '${branch.name}');top.hidebranchcode('${command.divName1}','${command.divName2}');top.popupboxclose();
		</c:otherwise>
		</c:choose>	
	</c:forEach>
</script>	
</c:if>

