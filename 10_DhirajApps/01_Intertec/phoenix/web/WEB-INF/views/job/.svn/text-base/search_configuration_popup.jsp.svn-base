<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script>
function submitFunction(){
document.configurationSearchPopupForm.pageNumber.value = "1";
document.configurationSearchPopupForm.sortFlag.value = "";
document.configurationSearchPopupForm.submit();
} 


function sortConfListById(){
document.configurationSearchPopupForm.pageNumber.value = "1";
document.configurationSearchPopupForm.sortFlag.value = "confListId";
document.configurationSearchPopupForm.submit();
}

function sortConfListByName(){
document.configurationSearchPopupForm.pageNumber.value = "1";
document.configurationSearchPopupForm.sortFlag.value = "confListName";
document.configurationSearchPopupForm.submit();
}

function submitSearch(pageNumber)
{	 
document.configurationSearchPopupForm.pageNumber.value = pageNumber;
// START : #119240
document.configurationSearchPopupForm.checkPageSort.value = "true";
// END : #119240 
document.configurationSearchPopupForm.submit();
}	

function submitform()
		{
		top.document.forms[0].submit();		
		}


</script>

<form:form name="configurationSearchPopupForm" method="POST" action="search_configuration_popup.htm">
	
	<input type="hidden" name="pageNumber" value="1"/>
	<form:hidden path="inputFieldId" />
     <form:hidden path="div1"/>
	<form:hidden path="div2"/>
	<form:hidden path="sortFlag"/>
	<!-- START : #119240 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240  -->  	
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
	<form:errors cssClass="error" /></div>

	<table border="0" cellpadding="0" cellspacing="0" align="center"
		class="MainTable" style="padding:-left:10px;padding-top:5px;padding:-right:0px">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="confListId"/>: </td>
							<td width="80%" class="TDShadeBlue">
								<!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="confListId.value" /> --%>
								<form:input cssClass="inputBox" path="confListId.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
								<!-- END : #119240  -->
							</td>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="confListName"/>: </td>
							<td width="80%" class="TDShadeBlue">
							<!-- START : #119240 -->
							<%-- <form:input cssClass="inputBox" path="confListName.value" /> --%>
							<form:input cssClass="inputBox" path="confListName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
							<!-- END : #119240  -->	
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="button" type="button" onClick="submitFunction()" class="button1"  value="<f:message key="search"/>"  /></td>
									
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br/>

					<c:if test="${command.results != null}">
						<div id="conflistsearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
						<tr>
							<th><a href="#start" onClick="sortConfListById();" ><span class="TDShade"><f:message key="confListId"/></span></a></th>
							<th><a href="#start" onClick="sortConfListByName();" ><span class="TDShade"><f:message key="confListName"/></span></a></th>
						</tr>
								<c:forEach items="${command.results}" var="confList" varStatus="status">
								<c:choose>
								<c:when test="${status.index%2==0}">
								<tr style="background-color:#FFFFFF;">
								</c:when>
								<c:otherwise>
								<tr style="background-color:#e7eeff;">                    
								</c:otherwise>
								</c:choose>
									<td>${confList.confListId} </td>
									<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${confList.confListName}');top.popupboxclose();top.hidePopupDiv('${command.div1}','${command.div2}');submitform();">${confList.confListName}</a></td>
									</tr>
									</c:forEach>
									<tr>
					                 <td>&nbsp;</td>
									<td align="center">
								<!-- START : #119240 -->		
								<%--	<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
									<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>&nbsp;&nbsp;
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


