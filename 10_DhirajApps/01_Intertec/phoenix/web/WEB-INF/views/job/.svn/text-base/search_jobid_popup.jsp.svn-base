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
document.jobidSearchPopUpForm.pageNumber.value = pageNumber;
// START : #119240
document.jobidSearchPopUpForm.checkPageSort.value = "true";
// END : #119240 
document.jobidSearchPopUpForm.submit();
}	
 function sortJobById(){
document.jobidSearchPopUpForm.pageNumber.value = "1";
document.jobidSearchPopUpForm.sortFlag.value = "jobNumber";
document.jobidSearchPopUpForm.submit();
}
function sortJobByBu(){
document.jobidSearchPopUpForm.pageNumber.value = "1";
document.jobidSearchPopUpForm.sortFlag.value = "buName";
document.jobidSearchPopUpForm.submit();
}
function sortJobByBranch(){
document.jobidSearchPopUpForm.pageNumber.value = "1";
document.jobidSearchPopUpForm.sortFlag.value = "branchName";
document.jobidSearchPopUpForm.submit();
}
function submitFunction()
{
	document.jobidSearchPopUpForm.pageNumber.value = "1";
	document.jobidSearchPopUpForm.sortFlag.value = "";
	document.jobidSearchPopUpForm.submit();
}

</script>

<form:form name="jobidSearchPopUpForm" method="POST" action="search_jobid_popup.htm">
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
							<td width="20%" class="TDShade" nowrap><f:message key="businessUnit"/>: </td>
							<td width="80%" class="TDShadeBlue">
								<!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="businessUnit.value" /> --%>
								<form:input cssClass="inputBox" path="businessUnit.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
								<!-- END : #119240  -->  
							</td>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="branchCode"/>: </td>
							<td width="80%" class="TDShadeBlue">
							<!-- START : #119240 -->
							<%-- <form:input cssClass="inputBox" path="branch.value" /> --%>
							<form:input cssClass="inputBox" path="branch.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
							<!-- END : #119240  -->  
							</td>
						</tr>
						<tr>
							<td class="TDShade"><f:message key="jobId"/> : </td>
							<td class="TDShadeBlue">
							<!-- START : #119240 -->
							<%-- <form:input cssClass="inputBox" path="fromJobId.value" /> --%>
							<form:input cssClass="inputBox" path="fromJobId.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
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
					<br />
					<c:if test="${command.results != null}">
						<div id="jobidsearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortJobById();" ><span class="TDShade"><f:message key="jobId"/></span></a></th>
									<th><a href="#start" onClick="sortJobByBu();" ><span class="TDShade"><f:message key="businessUnit"/></span></a></th>
									<th><a href="#start" onClick="sortJobByBranch();" ><span class="TDShade"><f:message key="branchCode"/></span></a></th>
									
								</tr>
								<c:forEach items="${command.results}" var="job" varStatus="status">
								<c:choose>
								<c:when test="${status.index%2==0}">
								<tr style="background-color:#FFFFFF;">
								</c:when>
								<c:otherwise>
								<tr style="background-color:#e7eeff;">                    
								</c:otherwise>
								</c:choose>
										<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${job.jobNumber}');top.popupboxclose();top.hidePopupDiv('${command.div1}','${command.div2}');">${job.jobNumber}</a></td>
				                        <td>${job.buName} </td>
										<td>${job.branchName}</td>
									</tr>
									</c:forEach>
									<tr>
					                 <td>&nbsp;</td>
									<td align="center">
									<!--  KETAN - 03/04/09 START - Show different color for current page -->
									<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
									  <c:choose>
										<c:when test="${page.selected==true}">
										  		<span style="color:red">${page.name}</span>&nbsp;&nbsp;
										</c:when>
										<c:otherwise>
											<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>&nbsp;&nbsp;
										</c:otherwise>
									  </c:choose>
									</c:forEach>
									<!--  KETAN - 03/04/09 END - Show different color for current page -->
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


