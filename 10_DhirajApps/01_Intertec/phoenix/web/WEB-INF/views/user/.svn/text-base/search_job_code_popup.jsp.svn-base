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
		document.jobCodeSearchPopUpForm.pageNumber.value = pageNumber;
		// START : #119240 : 24/06/09
		document.jobCodeSearchPopUpForm.checkPageSort.value = "true";
		// END : #119240 : 24/06/09
		document.jobCodeSearchPopUpForm.submit();
	  }	
 function sortJobCodeByCode(){
document.jobCodeSearchPopUpForm.pageNumber.value = "1";
document.jobCodeSearchPopUpForm.sortFlag.value = "jobCode";
document.jobCodeSearchPopUpForm.submit();
}
function sortJobCodeByDescription(){
document.jobCodeSearchPopUpForm.pageNumber.value = "1";
document.jobCodeSearchPopUpForm.sortFlag.value = "jobCodeDesc";
document.jobCodeSearchPopUpForm.submit();
}
function submitFunction()
{
	document.jobCodeSearchPopUpForm.pageNumber.value = "1";
	document.jobCodeSearchPopUpForm.sortFlag.value = "";
	document.jobCodeSearchPopUpForm.submit();
}

</script>
<form:form name="jobCodeSearchPopUpForm" method="POST" action="search_job_code_popup.htm">	   
<form:hidden path="searchForm" />
<input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="inputFieldId" />
	 <form:hidden path="sortFlag"/>
<!-- START : #119240 : 22/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 22/06/09 --> 
	
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
						<tr>
							<th colspan="2"><f:message key="jobCodeSearch"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="jobCode"/>: </td>
							<td width="80%" class="TDShadeBlue">
							<!-- START : #119240 -->
							<%-- <form:input cssClass="inputBox" path="jobCode.value" /> --%>
							<form:input cssClass="inputBox" path="jobCode.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
							<!-- END : #119240 -->
									<form:errors path="jobCode.value" cssClass="redstar"/></td>
					</tr>
						<tr>
							<td class="TDShade" nowrap><f:message key="jobCodeDescription"/>:</td>
							<td class="TDShadeBlue">
								<!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="jobCodeDesc.value" /> --%>
								<form:input cssClass="inputBox" path="jobCodeDesc.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
								<!-- END : #119240 -->
								<form:errors path="jobCodeDesc.value" cssClass="redstar"/>	
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
						<div id="jobcodesearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortJobCodeByCode();" ><span class="TDShade"><f:message key="jobCode"/></span></a></th>
									<th><a href="#start" onClick="sortJobCodeByDescription();" ><span class="TDShade"><f:message key="jobCodeDescription"/></span></a></th>
								</tr>
							<c:forEach items="${command.results}" var="jobCode" varStatus="status">
									<c:choose>
						<c:when test="${status.index%2==0}">
						<tr style="background-color:#FFFFFF;">
						</c:when>
						<c:otherwise>
						<tr style="background-color:#e7eeff;">                    
						</c:otherwise>
						</c:choose>
										
				            <td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${jobCode.jobCode}');top.hidejobcode();top.popupboxclose();">${jobCode.jobCode}</a></td>
										<td>${jobCode.jobCodeDesc}</td>
									</tr>
								</c:forEach>
								 <tr>
					           <td></td>
                             <td align="center">
                             <!-- START : #119240 : 22/06/09 --> 
		   					    <%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
		 					<a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
			                  </c:forEach> --%>
			                  <%@ include file="../common/pagination.jsp" %>
    					<!-- END : #119240 : 22/06/09 -->
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
