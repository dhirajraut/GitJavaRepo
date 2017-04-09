<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%><head>
 <script language="javascript">
function sortCustomerByCode(){
document.searchCustomerPopUpForm.pageNumber.value = "1";
document.searchCustomerPopUpForm.sortFlag.value = "custCode";
document.searchCustomerPopUpForm.submit();
}
function sortCustomerByName(){
document.searchCustomerPopUpForm.pageNumber.value = "1";
document.searchCustomerPopUpForm.sortFlag.value = "name";
document.searchCustomerPopUpForm.submit();
}

function submitFunction()
{
	document.searchCustomerPopUpForm.pageNumber.value = "1";
	document.searchCustomerPopUpForm.sortFlag.value = "";
	document.searchCustomerPopUpForm.submit();
}
function submitForm()
{
	if(document.searchCustomerPopUpForm.searchForm.value == 'conslInvCreateForm')
	{
		top.document.forms[0].submit();
	}
}


 </script>
</head>

 <form:form name="searchCustomerPopUpForm" method="POST" action="search_customer_popup.htm">
    
	<form:hidden path="searchType"/>
	<form:hidden path="searchForm"/>
	<form:hidden path="inputFieldId"/>
	<form:hidden path="divName1"/>
	<form:hidden path="divName2"/>
	<form:hidden path="sortFlag"/>
	 
<input type="hidden" name="pageNumber" value="1" />
	<div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
						
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="customerCode"/>: </td>
							<td width="80%" class="TDShadeBlue">
							
								 <form:input cssClass="inputBox" path="customer.value"/>
								 <form:errors path="customer.value"/> 
							</td>
						</tr>
						<tr>
							<td class="TDShade" nowrap><f:message key="customerName"/>:</td>
							<td class="TDShadeBlue">
							<form:input cssClass="inputBox" maxlength="60" path="customerName.value" />
								 <form:errors path="customerName.value" cssClass="redstar"/> 
							</td>
						</tr>
						
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="button" type="button" onClick="submitFunction()" class="button1" value="<f:message key="search"/>"  /></td>
										<td style="text-align:right"><a href="jobs/ins_jobsinstructions.html"></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					 <c:if test="${command.results != null}">
						<div id="parentcustsearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortCustomerByCode();" ><span class="TDShade"><f:message key="customerCode"/></span></a></th>
									<th><a href="#start" onClick="sortCustomerByName();" ><span class="TDShade"><f:message key="customerName"/></span></a></th>
									
								 </tr>
								 <c:forEach items="${command.results}" var="customer" varStatus="status">
									 <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
										
				            <td>${customer.custCode}</td>
										<td>
										<c:choose>
										<c:when test="${command.searchForm == 'conslInvCreateForm'}">
										<a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${customer.custCode}');top.hidePopupDiv('${command.divName1 }','${command.divName2 }');top.popupboxclose();submitForm();">${customer.name}</a>
										</c:when>
										<c:otherwise>
										<%--<c:set var="custname" value="${fn:replace(customer.name,'\'','\\\'')}" />
										<a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${custname}');top.hidePopupDiv('${command.divName1 }','${command.divName2 }');top.popupboxclose();${customer.custCode};">${customer.name}</a>--%>
                        <a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}','${fn:replace(customer.name,'\'','\\\'')}');
						top.hidePopupDiv('${command.divName1 }','${command.divName2 }');top.popupboxclose();${customer.custCode};">${customer.name}</a>

										</c:otherwise>
										</c:choose>
										</td>
									</tr>


								</c:forEach> 
									<tr>
					<td>&nbsp;&nbsp;</td>
									<td align="center">

									<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
									<SCRIPT LANGUAGE="JavaScript">

									function submitSearch(pageNumber)
												  {	 
													 
													document.searchCustomerPopUpForm.pageNumber.value = pageNumber;
													document.searchCustomerPopUpForm.submit();
												  }	

									</SCRIPT>
										<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>&nbsp;&nbsp;
									  </c:forEach>
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


