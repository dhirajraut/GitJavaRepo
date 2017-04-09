<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
   <script language="javascript">
	   function submitSearch(pageNumber)
			  {	 
				document.jobCodeBankSearchPopUpForm.pageNumber.value = pageNumber;
				// START : #119240
				document.jobCodeBankSearchPopUpForm.checkPageSort.value = "true";
				// END : #119240 
				document.jobCodeBankSearchPopUpForm.submit();
			  }	
function sortBankByCode(){
document.jobCodeBankSearchPopUpForm.pageNumber.value = "1";
document.jobCodeBankSearchPopUpForm.sortFlag.value = "bankCode";
document.jobCodeBankSearchPopUpForm.submit();
}
function sortBankByDesc(){
document.jobCodeBankSearchPopUpForm.pageNumber.value = "1";
document.jobCodeBankSearchPopUpForm.sortFlag.value = "bankDesc";
document.jobCodeBankSearchPopUpForm.submit();
}

function submitFunction()
{
	document.jobCodeBankSearchPopUpForm.pageNumber.value = "1";
	document.jobCodeBankSearchPopUpForm.sortFlag.value = "";
	document.jobCodeBankSearchPopUpForm.submit();
}   
function submitParentForm()
{
	var parentForm = document.jobCodeBankSearchPopUpForm.searchForm.value;
	if( parentForm == 'conslInvCreateForm' || parentForm == 'conslInvEditForm')
		top.document.forms[0].submit();
} 
</script>
<form:form name="jobCodeBankSearchPopUpForm" method="POST" action="search_bank_popup.htm">	   
<input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="inputFieldId" />
	<form:hidden path="rowNum"/>
	 <form:hidden path="sortFlag"/>
		 <form:hidden path="searchForm"/>
<!-- START : #119240 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240  --> 
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
						<tr>
							<th colspan="2"><f:message key="accountSearch"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="bankCode"/>: </td>
							<td width="80%" class="TDShadeBlue">
							<!-- START : #119240 -->
							<%-- <form:input cssClass="inputBox" path="bankCode.value" /> --%>
							<form:input cssClass="inputBox" path="bankCode.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
							<!-- END : #119240 -->
									<form:errors path="bankCode.value" cssClass="redstar"/></td>
					</tr>
						<tr>
							<td class="TDShade" nowrap><f:message key="bankCodeDescription"/>:</td>
							<td class="TDShadeBlue">
								<!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="description.value" /> --%>
								<form:input cssClass="inputBox" path="description.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
								<!-- END : #119240 -->
								<form:errors path="description.value" cssClass="redstar"/>	
							</td>
						</tr>
					
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="button" type="button" onClick="submitFunction()"  class="button1" value="<f:message key="search"/>"  /></td>
											</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<c:if test="${command.results != null}">
						<div id="searchresultsremitto"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortBankByCode();" ><span class="TDShade"><f:message key="bankCode"/></span></a></th>
									<th><a href="#start" onClick="sortBankByDesc();" ><span class="TDShade"><f:message key="description"/></span></a></th>
								</tr>
							<c:forEach items="${command.results}" var="bank" varStatus="status">
									 <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
						     <%--   <td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${bank.bankCode}');top.hideVatcode('searchremitto${command.rowNum}','remittobody${command.rowNum}');top.showIt();top.popupboxclose();">${bank.bankDesc}</a></td>
										<td>${bank.bankCode}</td>--%>
										 <td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${bank.bankCode}');top.hideVatcode('searchremitto${command.rowNum}','remittobody${command.rowNum}');top.showIt();top.popupboxclose();submitParentForm();">${bank.bankCode}</a></td>
										<td>${bank.bankDesc}</td>
									</tr>
								</c:forEach>
								 <tr>
					           <td></td>
                             <td align="center">
   					    <!-- START : #119240 -->
						 <%--<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
 					<a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
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
</form:form>		  
