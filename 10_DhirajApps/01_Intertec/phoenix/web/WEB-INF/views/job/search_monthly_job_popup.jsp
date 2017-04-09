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
				document.monthlyJobSearchPopUpForm.pageNumber.value = pageNumber;
				// START : #119240
				document.monthlyJobSearchPopUpForm.checkPageSort.value = "true";
				// END : #119240 
				document.monthlyJobSearchPopUpForm.submit();
			  }	

function submitform()
		{
			var name=document.getElementById("searchForm").value;
			if(name!='')
			top.document.forms[name].submit();
			else
			return 			
		}
function sortJobNumber(){
document.monthlyJobSearchPopUpForm.pageNumber.value = "1";
document.monthlyJobSearchPopUpForm.sortFlag.value = "jobNumber";
document.monthlyJobSearchPopUpForm.submit();
}
function sortSchedulerName(){
document.monthlyJobSearchPopUpForm.pageNumber.value = "1";
document.monthlyJobSearchPopUpForm.sortFlag.value = "sname";
document.monthlyJobSearchPopUpForm.submit();
}
function sortByCustomerName(){
document.monthlyJobSearchPopUpForm.pageNumber.value = "1";
document.monthlyJobSearchPopUpForm.sortFlag.value = "customer.name";
document.monthlyJobSearchPopUpForm.submit();
}
function sortproduct(){
document.monthlyJobSearchPopUpForm.pageNumber.value = "1";
document.monthlyJobSearchPopUpForm.sortFlag.value = "jobOrder.productNames";
document.monthlyJobSearchPopUpForm.submit();
}

function sortvessel(){
document.monthlyJobSearchPopUpForm.pageNumber.value = "1";
document.monthlyJobSearchPopUpForm.sortFlag.value = "jobOrder.vesselNames";
document.monthlyJobSearchPopUpForm.submit();
}

function sortOperation(){
document.monthlyJobSearchPopUpForm.pageNumber.value = "1";
document.monthlyJobSearchPopUpForm.sortFlag.value = "jobOrder.operation";
document.monthlyJobSearchPopUpForm.submit();
}

function sortServiceLocation(){
document.monthlyJobSearchPopUpForm.pageNumber.value = "1";
document.monthlyJobSearchPopUpForm.sortFlag.value = "jobOrder.serviceLocationCode";
document.monthlyJobSearchPopUpForm.submit();
}

function submitFunction()
{
	document.monthlyJobSearchPopUpForm.pageNumber.value = "1";
	document.monthlyJobSearchPopUpForm.sortFlag.value = "";
	document.monthlyJobSearchPopUpForm.submit();
}
</script>
<form:form name="monthlyJobSearchPopUpForm" method="POST" action="search_monthly_job_popup.htm">
	<%--<form:hidden path="searchType" />--%>
	<form:hidden path="sortFlag"/>
	<input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
	<form:hidden path="rowNum" />
	<form:hidden path="div1"/>
	<form:hidden path="div2"/>
	<!-- START : #119240 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240  --> 

	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;"><form:errors cssClass="error"/></div>

	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
						<tr>
							 
							<th colspan="2"><f:message key="monthlyJobIdSearch"/></th>
						</tr>
						
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="monthlyJobId"/>: </td>										
							<td width="80%" class="TDShadeBlue">
							<!-- START : #119240 -->
							<%-- <form:input cssClass="inputBox" path="monthlyJobId.value" size="35"/> --%>
							<form:input cssClass="inputBox" path="monthlyJobId.value" size="35" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
							<!-- END : #119240 -->
							<form:errors path="monthlyJobId.value" cssClass="redstar"/>	
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
				<div id="monthlyjobsearchresults"> 
				<strong><f:message key="searchResults"/></strong>
				<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
				<tr>
				<th nowrap><a href="#start" onClick="sortJobNumber();" ><span class="TDShade"><f:message key="JobNumber"/></span></a></th>
				<th nowrap><a href="#start" onClick="sortSchedulerName();" ><span class="TDShade"><f:message key="schedulerName"/></span></a></th>
				<th nowrap><a href="#start" onClick="sortByCustomerName();" ><span class="TDShade"><f:message key="customerName"/></span></a></th>
				<th nowrap><a href="#start" onClick="sortproduct();" ><span class="TDShade"><f:message key="product"/></span></a></th>
				<th nowrap><a href="#start" onClick="sortvessel();" ><span class="TDShade"><f:message key="vessel"/></span></a></th>
				<th nowrap><a href="#start" onClick="sortOperation();" ><span class="TDShade"><f:message key="operation"/></span></a></th>
				<th nowrap><a href="#start" onClick="sortServiceLocation();"><span class="TDShade"><f:message key="serviceLocation"/></span></a></th>
				</tr>


								<c:forEach items="${command.results}" var="jobContracts" varStatus="status">
								<c:choose>
								<c:when test="${status.index%2==0}">
								<tr style="background-color:#FFFFFF;">
								</c:when>
								<c:otherwise>
								<tr style="background-color:#e7eeff;">                    
								</c:otherwise>
								</c:choose>
									<td nowrap><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${jobContracts.jobNumber}');top.hidePopupDiv('${command.div1}','${command.div2}');
										top.popupboxclose();">${jobContracts.jobNumber}</a></td>
										<td nowrap>${jobContracts.contact.firstName},${jobContracts.contact.lastName}</td>
				                        <td nowrap>${jobContracts.customer.name}</td>
										<td>${jobContracts.jobOrder.productNames}</td>
										<td nowrap>${jobContracts.jobOrder.vesselNames}</td>
										<td nowrap>${jobContracts.jobOrder.operation}</td>
										<td nowrap>${jobContracts.jobOrder.serviceLocationCode}</td>
									</tr>
							 	</c:forEach>
								   <tr  >
						             
					     <td style="text-align:center" colspan="7">
					    <!-- START : #119240 -->
						<%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
						<a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name}</a>&nbsp;&nbsp;
						</c:forEach> --%>
						 <%@ include file="../common/pagination.jsp" %>
						 <!-- END : #119240 -->				
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
