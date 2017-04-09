<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%><head>

 <script language="javascript">
	   
		function submitform()
		{
			
			top.document.forms[0].submit();
			
			
		}

function sortTempByNum(){
document.templateSearchPopUpForm.pageNumber.value = "1";
document.templateSearchPopUpForm.sortFlag.value = "jobNumber";
document.templateSearchPopUpForm.submit();
}
function sortTempByName(){
document.templateSearchPopUpForm.pageNumber.value = "1";
document.templateSearchPopUpForm.sortFlag.value = "tempName";
document.templateSearchPopUpForm.submit();
}
function sortTempByBuName(){
document.templateSearchPopUpForm.pageNumber.value = "1";
document.templateSearchPopUpForm.sortFlag.value = "buName";
document.templateSearchPopUpForm.submit();
}
function sortTempByBranchNm(){
document.templateSearchPopUpForm.pageNumber.value = "1";
document.templateSearchPopUpForm.sortFlag.value = "branchName";
document.templateSearchPopUpForm.submit();
}
function sortTempByFirstNm(){
document.templateSearchPopUpForm.pageNumber.value = "1";
document.templateSearchPopUpForm.sortFlag.value = "createdBy.firstName";
document.templateSearchPopUpForm.submit();
}

function sortTempByLastNm(){
document.templateSearchPopUpForm.pageNumber.value = "1";
document.templateSearchPopUpForm.sortFlag.value = "createdBy.lastName";
document.templateSearchPopUpForm.submit();
}

function sortTempByDateCreated(){
document.templateSearchPopUpForm.pageNumber.value = "1";
document.templateSearchPopUpForm.sortFlag.value = "createTime";
document.templateSearchPopUpForm.submit();
}


function submitFunction()
{
	document.templateSearchPopUpForm.pageNumber.value = "1";
	document.templateSearchPopUpForm.sortFlag.value = "";
	document.templateSearchPopUpForm.submit();
}

function onDelete(index,number)
	{
		if (confirm("Are you sure you want to delete the template?")==true)
         {
			document.templateSearchPopUpForm.tempNumber.value =number ;
			document.templateSearchPopUpForm.tempdelFlag.value = "true";
			document.templateSearchPopUpForm.submit();
		 }
	}


   </script>
</head>
<icb:list var="divisionBu">
  <icb:item>${icbfn:user().businessUnit.organization.name}</icb:item>
   <icb:item>${command.buName.value}</icb:item>
 <%-- <icb:item>${icbfn:user().branch.businessUnit.name}</icb:item> --%>
</icb:list>
 <form:form name="templateSearchPopUpForm" method="POST" action="search_template_popup.htm">
	<form:hidden path="inputFieldId"/>
	<form:hidden path="sortFlag"/>
	<form:hidden path="tempNumber"/>
	<form:hidden path="tempdelFlag"/>
	<!-- START : #119240 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240  -->  
	 
<input type="hidden" name="pageNumber" value="1" />
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
	<form:errors cssClass="error" /></div>

	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
						
						<tr>
							<td width="20%" class="TDShade"><f:message key="templateName"/>:</td>
							<td colspan="1" class="TDShadeBlue">
							<!-- START : #119240 -->
								 <%-- <form:input cssClass="inputBox" path="templateName.value"/> --%>
								 <form:input cssClass="inputBox" path="templateName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
							<!-- END : #119240 -->
								 <form:errors path="templateName.value"/> 
							</td>
							 <td width="20%" class="TDShade"><strong><f:message
							  key="businessUnit" />:</strong></td>
							  <td colspan="1" class="TDShadeBlue"><span class="id_input">
						<!-- START : #119240 -->
							 <%-- <form:select cssClass="selectionBoxbig"
							  path="buName.value"
							  items="${icbfn:dropdown('businessUnit', null)}"
							  itemLabel="name" itemValue="value"/> --%>
							<form:select cssClass="selectionBoxbig"
							  path="buName.value"
							  items="${icbfn:dropdown('businessUnit', null)}"
							  itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
							<!-- END : #119240 -->  
							  <form:errors
							  path="buName.value" cssClass="redstar" /> </span>
							  </td>
							
						</tr>
						<tr>
							<td width="20%" class="TDShade"><f:message key="firstName"/>:</td>
							<td colspan="1" class="TDShadeBlue"> 
								<!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="firstName.value"/> --%>
								  <form:input cssClass="inputBox" path="firstName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
								<!-- END : #119240 -->
								 <form:errors path="firstName.value"/> 
							</td>
							<td width="20%" class="TDShade"><f:message key="branchName"/>:</td>
							<td colspan="1" class="TDShadeBlue">
							<!-- START : #119240 -->
							<%-- <form:select id="sel4" cssClass="selectionBox" path="branchName.value" items="${icbfn:dropdown('branch', divisionBu)}" itemLabel="name" itemValue="value"/> --%>
							<form:select id="sel4" cssClass="selectionBox" path="branchName.value" items="${icbfn:dropdown('branch', divisionBu)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
							<!-- END : #119240 -->
            				<form:errors path="branchName.value" cssClass="error"/>
							</td>
						</tr>
						<tr>
							<td width="20%" class="TDShade"><f:message key="lastName"/>:</td>
							<td colspan="3" class="TDShadeBlue">
								<!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="lastName.value"/> --%>
								<form:input cssClass="inputBox" path="lastName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
								<!-- END : #119240 -->
								 <form:errors path="lastName.value"/> 
							</td>
						</tr>		
																
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="button" type="button" onClick="submitFunction()" class="button1" value="<f:message key="search"/>"  />
										<input id="cancel" type="button" value="Cancel" name="cancel" class="button1"							onClick="javascript:top.hidePopupDiv('templatename','templatenamebody');top.popupboxclose();" /></td>

									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
			      <c:if test="${command.results != null}">
						<div id="contractsearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortTempByNum();" ><span class="TDShade"><f:message key="tempNumber"/></span> </a></th>
									<th width="20%"><a href="#start" onClick="sortTempByName();" > <span class="TDShade"><f:message key="templateName"/></span> </a></th>
									<th><a href="#start" onClick="sortTempByBuName();" > <span class="TDShade"><f:message key="businessUnitName"/></span> </a></th>
									<th> <a href="#start" onClick="sortTempByBranchNm();" ><span class="TDShade"><f:message key="branchName"/></span> </a></th>
									<th> <a href="#start" onClick="sortTempByFirstNm();" ><span class="TDShade"><f:message key="firstName"/></span> </a></th>
									<th> <a href="#start" onClick="sortTempByLastNm();" ><span class="TDShade"><f:message key="lastName"/></span> </a></th>
									<th> <a href="#start" onClick="sortTempByDateCreated();" ><span class="TDShade"><f:message key="dateCreated"/></span></a></th>
									<th width="5%"nowrap><span class="TDShade"><f:message key="deleteTemplate"/></span></th>
								
								 </tr>
								 <c:forEach items="${command.results}" var="template" varStatus="status">
									 <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
										
				            <td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${template.jobNumber}');top.hidePopupDiv('templatename','templatenamebody');top.popupboxclose();submitform();">${template.jobNumber}</a></td>
									<td>${template.tempName}</td>
									<td>${template.buName}</td>
									<td>${template.branchName}</td>
									<td>${template.createdBy.firstName}</td>
									<td>${template.createdBy.lastName}</td>
									<td><f:formatDate value="${template.createTime}" type="date" dateStyle="long" /></td>
									
									  <td width="5%" class="TDShadeBlue" style="text-align:left;"><div id="div3" style="width:50px; text-align:left; margin-right:0;">&nbsp; <img src="images/icodel.gif" alt="Delete Template" width="13" height="12" hspace="1px" border="0" onclick="onDelete('${status.index}','${template.jobNumber}')" /></div></td> 
									</tr>


								</c:forEach> 
									 <tr>
					<td>&nbsp;&nbsp;</td>
									<td align="center">
								 <!-- START : #119240 -->
									<%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status"> --%>
									<SCRIPT LANGUAGE="JavaScript">

									function submitSearch(pageNumber)
												  {	 
													 
													document.templateSearchPopUpForm.pageNumber.value = pageNumber;
												// START : #119240
													document.templateSearchPopUpForm.checkPageSort.value = "true";
												// END : #119240 
													document.templateSearchPopUpForm.submit();
												  }	

									</SCRIPT>
									<%--	<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>&nbsp;&nbsp;
									  </c:forEach> --%>
										<%@ include file="../common/pagination.jsp" %>
									 <!-- END : #119240 -->
									</td>
									</tr>
									<tr></tr>
							</table>
						</div>
					</c:if> 
				
				</div>
			</td>
		</tr>
	</table>
<ajax:select
    baseUrl="business_unit.htm"
    source="buName.value"
    target="branchName.value"
    parameters="branch.businessUnit.name={buName.value}"
    parser="new ResponseXmlParser()"
    /> 
</form:form>


