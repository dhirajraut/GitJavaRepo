<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%><head>
 <script language="javascript">
	   
		function submitform()
		{
			var name=document.getElementById("searchForm").value;
            document.contactIdSearchPopUpForm.cxcel.value="false";
			top.document.forms[name].submit();
			
		}
function sortContactByName(){
document.contactIdSearchPopUpForm.pageNumber.value = "1";
document.contactIdSearchPopUpForm.sortFlag.value = "name";
document.contactIdSearchPopUpForm.cxcel.value="false";
document.contactIdSearchPopUpForm.submit();
}
function sortContactById(){
document.contactIdSearchPopUpForm.pageNumber.value = "1";
document.contactIdSearchPopUpForm.sortFlag.value = "id";
document.contactIdSearchPopUpForm.cxcel.value="false";
document.contactIdSearchPopUpForm.submit();
}
function submitFunction()
{
	document.contactIdSearchPopUpForm.pageNumber.value = "1";
	document.contactIdSearchPopUpForm.sortFlag.value = "";
	document.contactIdSearchPopUpForm.cxcel.value="false";
	document.contactIdSearchPopUpForm.submit();
}

function submitConslInvForm(contactid)
{
	top.document.forms[0].contactId.value = contactid;
	document.contactIdSearchPopUpForm.cxcel.value="false";
	submitform();
}

function submitSearch(pageNumber)
{	 
document.contactIdSearchPopUpForm.pageNumber.value = pageNumber;
document.contactIdSearchPopUpForm.cxcel.value="false";
// START : #119240
document.contactIdSearchPopUpForm.checkPageSort.value = "true";
// END : #119240 
document.contactIdSearchPopUpForm.submit();
}

function submitxcel(){
document.contactIdSearchPopUpForm.cxcel.value="true";
document.contactIdSearchPopUpForm.sortFlag.value = "";
document.contactIdSearchPopUpForm.submit();
}
   </script>
</head>

 <form:form name="contactIdSearchPopUpForm" method="POST" action="search_contact_popup.htm">
    
	<form:hidden path="searchType"/>
	<form:hidden path="searchForm"/>
	<form:hidden path="inputFieldId"/>
	<form:hidden path="divName"/>
	<form:hidden path="divbody"/>
	<form:hidden path="custCode"/>
	<form:hidden path="sortFlag"/>
	<form:hidden path="contractCode"/>
	<!-- START : #119240 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240  --> 
	<input type="hidden" name="cxcel" value="false"/>
<a href="#inst"></a>
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
							<td width="20%" class="TDShade" nowrap><f:message key="contactID"/>:</td>
							<td width="80%" class="TDShadeBlue">
							 <!-- START : #119240 -->		
								 <%-- <form:input cssClass="inputBox" path="contactIds.value"/> --%>
								 <form:input cssClass="inputBox" path="contactIds.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
								<!-- END : #119240 -->
								 <form:errors path="contactIds.value"/> 
							</td>
						</tr>

						  <tr>
                 <td width="20%" class="TDShade" nowrap><f:message key="firstName"/>:</td>
							<td width="80%" class="TDShadeBlue">
                   <!-- START : #119240 -->                 
                   <%-- <form:input cssClass="inputBox" maxlength="128" path="firstName.value" /> --%>
				   <form:input cssClass="inputBox" maxlength="128" path="firstName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					 <!-- END : #119240 -->
                  </td>
                </tr>
				<tr>
                  <td width="20%" class="TDShade" nowrap><f:message key="lastName"/>:</td>
							<td width="80%" class="TDShadeBlue">
					<!-- START : #119240 -->    	
                    <%-- <form:input cssClass="inputBox" maxlength="128" path="lastName.value" /> --%>
					<form:input cssClass="inputBox" maxlength="128" path="lastName.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
					<!-- END : #119240 -->
                  </td>				 
                </tr>

            
				 <c:choose>
					<c:when test="${command.searchForm == 'customerQuickCreateForm'}">
						<tr>
						<td width="20%" class="TDShade" nowrap><f:message key="showAllContacts"/>:</td>
						<td width="80%" class="TDShadeBlue"><form:checkbox path="showall.value" value="1"/></td>
						</tr>
					</c:when>
				</c:choose>
           

					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="button" type="button" class="button1" onClick="submitFunction()" value="<f:message key="search"/>"  /></td>
										<td style="text-align:right;"><a href="#start"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				<br />
			      <c:if test="${command.results != null}">
						<div id="contactsearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortContactById();" ><span class="TDShade"><f:message key="contactID"/></span></a></th>
									<th><a href="#start" onClick="sortContactByName();" ><span class="TDShade"><f:message key="contactName"/></span></a></th>
									<c:choose>
									<c:when test="${command.searchForm == 'conslInvCreateForm' || command.searchForm == 'conslInvEditForm'}">
									<th><span class="TDShade"><f:message key="addresssequence"/></span></th>
									</c:when>
									<c:otherwise>
									<th>&nbsp;&nbsp;</th>
									</c:otherwise>
									</c:choose>
								 </tr>
								 <c:choose>
								 <c:when test="${command.searchForm == 'conslInvCreateForm' || 
								  command.searchForm == 'conslInvEditForm' || command.searchForm == 'createJobsInspForm' || command.searchForm == 'editJobsInspForm' || 
								  command.searchForm == 'createJobsMarineForm' || command.searchForm == 'editJobsMarineForm' || command.searchForm == 'createJobsOutSourcingForm' || command.searchForm == 'editJobsOutSourcingForm' || command.searchForm == 'createJobsAnalyticalServicesForm' || command.searchForm == 'editJobsAnalyticalServicesForm'}">
									   <c:choose>
									   <c:when test="${command.searchForm == 'conslInvCreateForm' || command.searchForm == 'conslInvEditForm'}"> 
											 <c:forEach items="${command.results}" var="contactCust" varStatus="status">
											 <c:choose>
											 <c:when test="${status.index%2==0}">
											 <tr style="background-color:#FFFFFF;">
											  </c:when>
											  <c:otherwise>
											  <tr style="background-color:#e7eeff;">                    
											  </c:otherwise>
											  </c:choose>
											<td>
											<a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}','${contactCust.contactCustId.locationNumber}');						top.hidePopupDiv('${command.divName}','${command.divbody}');top.showIt();top.popupboxclose();submitConslInvForm(${contactCust.contact.id});">${contactCust.contact.id}</a></td>
												<td>${contactCust.contact.firstName} ${contactCust.contact.lastName}</td>
												<td>${contactCust.contactCustId.locationNumber}</td>
												</tr>
											</c:forEach> 
									</c:when>
									<c:otherwise>
											<c:forEach items="${command.results}" var="contractcustcontact" varStatus="status">
											<c:choose>
											<c:when test="${status.index%2==0}">
											<tr style="background-color:#FFFFFF;">
											</c:when>
											<c:otherwise>
											<tr style="background-color:#e7eeff;">                    
											</c:otherwise>
											</c:choose>
											<td>
											<a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}','${contractcustcontact.contractCustContactId.contactId}');		top.hidePopupDiv('${command.divName}','${command.divbody}');top.showIt();top.popupboxclose();submitform();">${contractcustcontact.contractCustContactId.contactId}</a></td>
											 <td colspan="2">${contractcustcontact.contact.firstName} ${contractcustcontact.contact.lastName}</td> 
											</tr>
											</c:forEach> 
									</c:otherwise> 
									</c:choose>
								 </c:when>
								 <c:otherwise>
								 <c:forEach items="${command.results}" var="contact" varStatus="status">
								<c:choose>
								<c:when test="${status.index%2==0}">
								<tr style="background-color:#FFFFFF;">
								</c:when>
								<c:otherwise>
								<tr style="background-color:#e7eeff;">                    
								</c:otherwise>
								</c:choose>
								<td>
								<a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}','${contact.id}');						top.hidePopupDiv('${command.divName}','${command.divbody}');top.showIt();top.popupboxclose();submitform();">${contact.id}</a></td>
								<td colspan="2">${contact.firstName} ${contact.lastName}</td>
								</tr>
								</c:forEach> 
								</c:otherwise> 
								</c:choose>
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


