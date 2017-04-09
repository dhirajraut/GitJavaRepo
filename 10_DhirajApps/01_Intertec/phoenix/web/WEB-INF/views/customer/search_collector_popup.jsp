<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%><head>
 <script language="javascript">
function submitSearch(pageNumber)
{	 
  
document.collectorSearchPopUpForm.pageNumber.value = pageNumber;
document.collectorSearchPopUpForm.submit();
}	

function sortCollectorByCode(){
document.collectorSearchPopUpForm.pageNumber.value = "1";
document.collectorSearchPopUpForm.sortFlag.value = "collectorCode";
document.collectorSearchPopUpForm.submit();
}
function sortCollectorByName(){
document.collectorSearchPopUpForm.pageNumber.value = "1";
document.collectorSearchPopUpForm.sortFlag.value = "collectorName";
document.collectorSearchPopUpForm.submit();
}

function submitFunction()
{
	document.collectorSearchPopUpForm.pageNumber.value = "1";
	document.collectorSearchPopUpForm.sortFlag.value = "";
	document.collectorSearchPopUpForm.submit();
}
   </script>
</head>    
<form:form name="collectorSearchPopUpForm" method="POST" action="search_collector_popup.htm">
 

<input type="hidden" name="pageNumber" value="1" />
	
    <form:hidden path="searchType"/>
	<form:hidden path="searchForm"/>
	<form:hidden path="inputFieldId"/>
	<form:hidden path="sortFlag"/>
	<div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
						
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="collector"/>: </td>
							<td width="80%" class="TDShadeBlue">
							
								 <form:input cssClass="inputBox" path="collector.value"/>
								 <form:errors path="collector.value"/> 
							</td>
						</tr>
						<tr>
							<td class="TDShade" nowrap><f:message key="name"/>:</td>
							<td class="TDShadeBlue">
							<form:input cssClass="inputBox" path="name.value" />
								 <form:errors path="name.value" cssClass="redstar"/> 
							</td>
						</tr>
						
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="button" type="button" onClick="submitFunction()"  class="button1" value="<f:message key="search"/>"  /></td>
										<td style="text-align:right"><a href="jobs/ins_jobsinstructions.html"></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					 <c:if test="${command.results != null}">
						<div id="collectorsearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortCollectorByCode();" ><span class="TDShade"><f:message key="collector"/></span></a></th>
									<th><a href="#start" onClick="sortCollectorByName();" ><span class="TDShade"><f:message key="name"/></span></a></th>
									
								 </tr>
								 <c:forEach items="${command.results}" var="collector" varStatus="status">
									 <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
										
				            <td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${collector.collectorCode}');top.hidePopupDiv('collector','collectorbody');top.popupboxclose();">${collector.collectorCode}</a></td>
										<td>${collector.collectorName}</td>
									</tr>


								</c:forEach> 
									<tr>
					<td>&nbsp;</td>
									<td align="center">

									<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
									<SCRIPT LANGUAGE="JavaScript">

									function submitSearch(pageNumber)
												  {	 
													 
													document.collectorSearchPopUpForm.pageNumber.value = pageNumber;
													document.collectorSearchPopUpForm.submit();
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


