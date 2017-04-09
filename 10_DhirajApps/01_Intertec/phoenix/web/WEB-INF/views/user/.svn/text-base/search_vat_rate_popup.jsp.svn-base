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
				document.vatRateSearchPopUpForm.pageNumber.value = pageNumber;
				// START : #119240 : 25/06/09
				document.vatRateSearchPopUpForm.checkPageSort.value = "true";
				// END : #119240 : 25/06/09
				document.vatRateSearchPopUpForm.submit();
			  }	

function submitform()
		{
			var name=document.getElementById("searchForm").value;
			if(name!='')
			top.document.forms[name].submit();
			else
			return 			
		}
function sortVatRateByTaxCode(){
document.vatRateSearchPopUpForm.pageNumber.value = "1";
document.vatRateSearchPopUpForm.sortFlag.value = "taxCode";
document.vatRateSearchPopUpForm.submit();
}
function sortVatRateBySalesTaxCode(){
document.vatRateSearchPopUpForm.pageNumber.value = "1";
document.vatRateSearchPopUpForm.sortFlag.value = "taxCode";
document.vatRateSearchPopUpForm.submit();
}
function sortByDescription(){
document.vatRateSearchPopUpForm.pageNumber.value = "1";
document.vatRateSearchPopUpForm.sortFlag.value = "description";
document.vatRateSearchPopUpForm.submit();
}
function sortVatRateByEffDate(){
document.vatRateSearchPopUpForm.pageNumber.value = "1";
document.vatRateSearchPopUpForm.sortFlag.value = "effDate";
document.vatRateSearchPopUpForm.submit();
}

function sortVatRateByTaxPct(){
document.vatRateSearchPopUpForm.pageNumber.value = "1";
document.vatRateSearchPopUpForm.sortFlag.value = "taxPct";
document.vatRateSearchPopUpForm.submit();
}
function submitFunction()
{
	document.vatRateSearchPopUpForm.pageNumber.value = "1";
	document.vatRateSearchPopUpForm.sortFlag.value = "";
	document.vatRateSearchPopUpForm.submit();
}
</script>
<form:form name="vatRateSearchPopUpForm" method="POST" action="search_vat_rate_popup.htm">
	<%--<form:hidden path="searchType" />--%>
	<input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
	<form:hidden path="rowNum" />
	<form:hidden path="vatCodeId"/>
	<form:hidden path="taxType"/>
	<form:hidden path="div1"/>
	<form:hidden path="div2"/>
	<form:hidden path="sortFlag"/>
<!-- START : #119240 : 15/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 15/06/09 --> 
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
						<tr>
							 <c:choose>
							<c:when test="${command.taxType=='V'}">
							<th colspan="2"><f:message key="vatRateSearch"/></th>
							</c:when>
							<c:otherwise>
							<th colspan="2"><f:message key="salesTaxSearch"/></th>
							</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<c:choose>
							<c:when test="${command.taxType=='V'}">
							<td width="20%" class="TDShade" nowrap><f:message key="vatCode"/>: </td>
							</c:when>
							<c:otherwise>
							<td width="20%" class="TDShade" nowrap><f:message key="salesTaxCode"/>:</td>
							</c:otherwise>
							</c:choose>
							<td width="80%" class="TDShadeBlue">
							  <!-- START : #119240 -->
							<%-- <form:input cssClass="inputBox" path="taxCode.value" /> --%>
							<form:input cssClass="inputBox" path="taxCode.value" 
							onkeypress="if(event.keyCode==13) {submitFunction();}"/>
							  <!-- END : #119240 -->
									<form:errors path="taxCode.value" cssClass="redstar"/>	
							</td>
                         </tr>
						 <tr>
							<c:choose>
							<c:when test="${command.taxType=='V'}">
							<td width="20%" class="TDShade" nowrap><f:message key="varRateDesc"/>: </td>
							
							</c:when>
							<c:otherwise>
							<td width="20%" class="TDShade" nowrap><f:message key="salesTaxDesc"/>:</td>
							
							</c:otherwise>
							</c:choose>
							<td width="80%" class="TDShadeBlue">
							 <!-- START : #119240 -->
							<%-- <form:input cssClass="inputBox" path="description.value" /> --%>
							 <form:input cssClass="inputBox" path="description.value" onkeypress="if(event.keyCode==13) {submitFunction();}" /> 
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
										<td><input name="button" type="button" class="button1" onClick="submitFunction()" value="<f:message key="search"/>"  /></td>
										<td style="text-align:right"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
			
					<c:if test="${command.results != null}">
						<div id="vatratesearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
								<c:choose>
								<c:when test="${command.searchForm=='servloc'}">
								<th><span class="TDShade"><f:message key="taxCode"/></span></th>
								<th><span class="TDShade"><f:message key="taxDesc"/></span></th>
								<th colspan="2">&nbsp;</th>
								</c:when>
								<c:otherwise>
							<c:choose>
							<c:when test="${command.taxType=='V'}">
								<th><a href="#start" onClick="sortVatRateByTaxCode();" ><span class="TDShade"><f:message key="vatCode"/></span></a></th>
							</c:when>
							<c:otherwise>
							<th><a href="#start" onClick="sortVatRateBySalesTaxCode();" ><span class="TDShade"><f:message key="salesTaxCode"/></span></a></th>
							</c:otherwise>
							</c:choose>			
                          <c:choose>
							<c:when test="${command.taxType=='V'}">
								<th><a href="#start" onClick="sortByDescription();" ><span class="TDShade"><f:message key="varRateDesc"/></span></a></th>
							</c:when>
							<c:otherwise>
							<th><a href="#start" onClick="sortByDescription();" ><span class="TDShade"><f:message key="salesTaxDesc"/></span></a></th>
							</c:otherwise>
							</c:choose>	
									<th><a href="#start" onClick="sortVatRateByEffDate();" ><span class="TDShade"><f:message key="effectiveDate"/></span></a></th>
									<th><a href="#start" onClick="sortVatRateByTaxPct();" ><span class="TDShade"><f:message key="percentage"/></span></a></th>
							</c:otherwise>
							</c:choose>
								</tr>
								<c:forEach items="${command.results}" var="taxRates" varStatus="status">
								<c:choose>
								<c:when test="${status.index%2==0}">
								<tr style="background-color:#FFFFFF;">
								</c:when>
								<c:otherwise>
								<tr style="background-color:#e7eeff;">                    
								</c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${command.searchForm=='servloc'}">
													<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${taxRates.taxCodeHeader}');top.hidePopupDiv('${command.div1}','${command.div2}');
										top.popupboxclose();submitform();">${taxRates.taxCodeHeader}</a></td>
										<td>${taxRates.taxDescr}</td>
				                    <td>&nbsp;</td>
										<td>&nbsp;</td>
								</c:when>
								<c:otherwise>
													<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${taxRates.taxRateId.taxCode}');top.hidePopupDiv('${command.div1}','${command.div2}');
										top.popupboxclose();submitform();">${taxRates.taxRateId.taxCode}</a></td>
										<td>${taxRates.description}</td>
				                    <td>${taxRates.taxRateId.effDate}</td>
										<td>${taxRates.taxPct}</td>
								</c:otherwise>
								</c:choose>
				
									</tr>
							 	</c:forEach>
								   <tr>
						             <td>&nbsp;&nbsp;&nbsp;</td>
					           <td align="center">
					            <!-- START : #119240 : 22/06/09 --> 
								   <%--  <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
									<a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name}</a>&nbsp;&nbsp;
									</c:forEach> --%>
									 <%@ include file="../common/pagination.jsp" %>
		    				<!-- END : #119240 : 22/06/09 -->
					  </tr>
					</td><tr></tr>				

							</table>
						</div>
					</c:if>
				</div>
			</td>
		</tr>
	</table>
</form:form>		  
