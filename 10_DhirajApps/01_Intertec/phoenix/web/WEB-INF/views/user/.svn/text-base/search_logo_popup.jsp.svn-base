<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%><head>
 <script language="javascript">

function sortByLogoName(){
document.logoSearchPopUpForm.pageNumber.value = "1";
document.logoSearchPopUpForm.sortFlag.value = "logoName";
document.logoSearchPopUpForm.submit();
}

function sortBySysFileName(){
document.logoSearchPopUpForm.pageNumber.value = "1";
document.logoSearchPopUpForm.sortFlag.value = "systemFileName";
document.logoSearchPopUpForm.submit();
}

function submitFunction(){
	document.logoSearchPopUpForm.pageNumber.value = "1";
	document.logoSearchPopUpForm.sortFlag.value = "";
	document.logoSearchPopUpForm.submit();
}

function submitSearch(pageNumber) {	 												 
	document.logoSearchPopUpForm.pageNumber.value = pageNumber;
	// START : #119240 : 29/06/09
	document.logoSearchPopUpForm.checkPageSort.value = "true";
	// END : #119240 : 29/06/09
	document.logoSearchPopUpForm.submit();
}	
</script>
</head>



<form:form name="logoSearchPopUpForm" method="POST" action="search_logo_popup.htm">
	<form:hidden path="searchType" />
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
	<form:hidden path="divName1" />
	<form:hidden path="divName2" />
	<form:hidden path="sortFlag"/>
<!-- START : #119240 : 22/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 22/06/09 --> 
	

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
							<th colspan="2"><f:message key="logoSearch"/></th>
						</tr>
						
						<tr>
							<td class="TDShade" nowrap><f:message key="logoName"/>:</td>
							<td class="TDShadeBlue">
								 <!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="logoName.value" /> --%>
								<form:input cssClass="inputBox" path="logoName.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
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
										<td style="text-align:right">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<c:if test="${command.results != null}">
						<div id="logosearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortByLogoName();" ><span class="TDShade" nowrap><f:message key="logoName"/></span></a></th>
									<th><a href="#start" onClick="sortBySysFileName();" ><span class="TDShade" nowrap><f:message key="sysFileName"/></span></a></th>
									
								</tr>
								<c:forEach items="${command.results}" var="logo" varStatus="status">
								<c:choose>
								<c:when test="${status.index%2==0}">
								<tr style="background-color:#FFFFFF;">
								</c:when>
								<c:otherwise>
								<tr style="background-color:#e7eeff;">                    
								</c:otherwise>
								</c:choose>
									<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${logo.logoName}');top.hidebranchcode('${command.divName1}','${command.divName2}');top.popupboxclose();">${logo.logoName}</a></td>
									<td>${logo.systemFileName}</td>
								</tr>
								</c:forEach>
				<tr>
				<td>&nbsp;</td>
				<td align="center">
					<!-- START : #119240 : 22/06/09 --> 
						<%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
						<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>&nbsp;&nbsp;
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
