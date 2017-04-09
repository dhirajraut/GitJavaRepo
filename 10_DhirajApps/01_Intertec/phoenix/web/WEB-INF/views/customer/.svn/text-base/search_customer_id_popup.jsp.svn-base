<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script >
 function submitSearch(pageNumber)
			  {	 
				document.customerIdPopUpForm.pageNumber.value = pageNumber;
				document.customerIdPopUpForm.submit();
			  }	
function submitform()
		{
			var name=document.getElementById("searchForm").value;
			top.document.forms[name].submit();
		}

function sortCustomerById(){
document.customerIdPopUpForm.pageNumber.value = "1";
document.customerIdPopUpForm.sortFlag.value = "custCode";
document.customerIdPopUpForm.submit();
}
function sortCustomerByName(){
document.customerIdPopUpForm.pageNumber.value = "1";
document.customerIdPopUpForm.sortFlag.value = "name";
document.customerIdPopUpForm.submit();
}

function submitFunction()
{
	document.customerIdPopUpForm.pageNumber.value = "1";
	document.customerIdPopUpForm.sortFlag.value = "";
	document.customerIdPopUpForm.submit();
}
</script>
<form:form name="customerIdPopUpForm" method="POST" action="search_customer_id_popup.htm">
	<input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="inputFieldId" />
	<form:hidden path="rowNum" />
	<form:hidden path="custCode"/>
	<form:hidden path="searchForm" />
	<form:hidden path="sortFlag"/>
		
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
						<tr>
							<th colspan="2"><f:message key="customerSearch"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="customerid"/>: </td>
							<td width="80%" class="TDShadeBlue">
								<form:input cssClass="inputBox" path="customerId.value" />
									<form:errors path="customerId.value" cssClass="redstar"/>	
							</td>
						</tr>
						  <tr>
							<td width="20%" class="TDShade" nowrap><f:message key="customerName"/>: </td>
							<td width="80%" class="TDShadeBlue">
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
										<td><input name="button" type="button" class="button1" onClick="submitFunction()" value="<f:message key="search"/>"  /></td>
									
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
				<c:if test="${command.results != null}">
						<div id="customersearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortCustomerById();" ><span class="TDShade"><f:message key="customerid"/></span></a></th>
									<th><a href="#start" onClick="sortCustomerByName();" ><span class="TDShade"><f:message key="customerName"/></span></a></th>
									
								</tr>
								<c:forEach items="${command.results}" var="customers" varStatus="status">
									<c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
                                   <td>
	<a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${customers.custCode}');top.hideVatcode('customerid${command.rowNum}',
	'customeridbody${command.rowNum}');top.popupboxclose();top.document.${command.searchForm }.submit();">${customers.custCode}</a>
								     <td>${customers.name}</td>		 
									   </tr>			  							  

							 	</c:forEach>
									<tr>
					<td>&nbsp;&nbsp;</td>
									<td align="center">

									<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
									<SCRIPT LANGUAGE="JavaScript">

									function submitSearch(pageNumber)
												  {	 
													 
													document.customerIdPopUpForm.pageNumber.value = pageNumber;
													document.customerIdPopUpForm.submit();
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
