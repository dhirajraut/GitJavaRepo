<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script>
	         function submitSearch(pageNumber)
			  {	 
				document.currencySearchPopUpForm.pageNumber.value = pageNumber;
				document.currencySearchPopUpForm.submit();
			  }	 
</script>
<form:form name="currencySearchPopUpForm" method="POST" action="search_currency_popup.htm">
	 <input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
	 	<%--<form:hidden path="searchType" />--%>
	
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
						<tr>
							<th colspan="2"><f:message key="currencySearch"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="currency"/>: </td>
							<td width="80%" class="TDShadeBlue">
								<form:input cssClass="inputBox" path="currencyCd.value" />
									<form:errors path="currencyCd.value" cssClass="redstar"/>	
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="Submit" type="submit" class="button1" value="<f:message key="search"/>"  /></td>
										<td style="text-align:right"><a href="jobs/ins_jobsinstructions.html"></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<c:if test="${command.results != null}">
						<div id="currencysearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><span class="TDShade"><f:message key="currency"/></span></th>
									<th><span class="TDShade"><f:message key="currencyDescription"/></span></th>
							
								</tr>
								<c:forEach items="${command.results}" var="currency" varStatus="status">
									<c:choose>
						<c:when test="${status.index%2==0}">
						<tr style="background-color:#FFFFFF;">
						</c:when>
						<c:otherwise>
						<tr style="background-color:#e7eeff;">                    
						</c:otherwise>
						</c:choose>
					 <td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${currency.currencyCd}');top.hideVatcode('currency','currencybody');top.popupboxclose();">${currency.currencyCd}</a></td>
										  <td>${currency.currencyDescr}</td>
									</tr>
							 	</c:forEach>
								  <tr>
					           <td></td>
                             <td align="center">
   					     <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
 					<a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>
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