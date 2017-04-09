<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
   <script language="javascript">
function submitSearch(pageNumber){	 
	document.jobContractInspSearchPopUpForm.pageNumber.value = pageNumber;
	document.jobContractInspSearchPopUpForm.cxcel.value="false";
	// START : #119240
	document.jobContractInspSearchPopUpForm.checkPageSort.value = "true";
	// END : #119240 
	document.jobContractInspSearchPopUpForm.submit();
	}	

function submitform(contractApproved,customerActive,contactActive){

	var validationCheck = "false";
	if(contractApproved != null && (contractApproved != 'APP' && contractApproved != 'INPR'))
	{
		confirm("Contract not Approved please contact Regional Billing Center!");
		validationCheck="true";
	}
	if(customerActive != null && customerActive != 'A' )
	{
		confirm("This Customer is InActive please contact Regional Billing Center!");
		validationCheck="true";
	}
	if(contactActive != null && contactActive != 'A' )
	{
		confirm("Contact associated to this Customer is InActive  please contact Regional Billing Center!");
		validationCheck="true";
	}
	if(validationCheck=='true'){
		return false;
	}
	top.showContract1();
	top.popupboxclose();
	var name=document.getElementById("searchForm").value;
	document.jobContractInspSearchPopUpForm.cxcel.value="false";
	top.document.forms[name].submit();
	}

function sortCustomerBydescription(){
	document.jobContractInspSearchPopUpForm.pageNumber.value = "1";
	document.jobContractInspSearchPopUpForm.sortFlag.value = "contractdescription";
	document.jobContractInspSearchPopUpForm.cxcel.value="false";
	document.jobContractInspSearchPopUpForm.submit();
	}
function sortCustomerBycontractCode(){
	document.jobContractInspSearchPopUpForm.pageNumber.value = "1";
	document.jobContractInspSearchPopUpForm.sortFlag.value = "contractCode";
	document.jobContractInspSearchPopUpForm.cxcel.value="false";
	document.jobContractInspSearchPopUpForm.submit();
	}
function sortCustomerBycustomerCode(){
	document.jobContractInspSearchPopUpForm.pageNumber.value = "1";
	document.jobContractInspSearchPopUpForm.sortFlag.value = "customercode";
	document.jobContractInspSearchPopUpForm.cxcel.value="false";
	document.jobContractInspSearchPopUpForm.submit();
	}
function sortCustomerByCurrency(){
	document.jobContractInspSearchPopUpForm.pageNumber.value = "1";
	document.jobContractInspSearchPopUpForm.sortFlag.value = "currency";
	document.jobContractInspSearchPopUpForm.cxcel.value="false";
	document.jobContractInspSearchPopUpForm.submit();
	}
function sortCustomerBycustomerName(){
	document.jobContractInspSearchPopUpForm.pageNumber.value = "1";
	document.jobContractInspSearchPopUpForm.sortFlag.value = "customername";
	document.jobContractInspSearchPopUpForm.cxcel.value="false";
	document.jobContractInspSearchPopUpForm.submit();
	}
	function sortCustomerBycontactName(){
	document.jobContractInspSearchPopUpForm.pageNumber.value = "1";
	document.jobContractInspSearchPopUpForm.sortFlag.value = "contactName";
	document.jobContractInspSearchPopUpForm.cxcel.value="false";
	document.jobContractInspSearchPopUpForm.submit();
	}

	function submitxcel(){
	document.jobContractInspSearchPopUpForm.cxcel.value="true";
	document.jobContractInspSearchPopUpForm.sortFlag.value = "";
	document.jobContractInspSearchPopUpForm.submit();
	}
</script>

<form:form name="jobContractInspSearchPopUpForm" method="POST" action="search_job_contract_insp_popup.htm">	   
<form:hidden path="searchForm"/>
<input type="hidden" name="pageNumber" value="1" />
<form:hidden path="inputFieldId" />
<form:hidden path="sortFlag"/>
<form:hidden path="buName"/>
<!-- START : #119240 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240  --> 
<input type="hidden" name="cxcel" value="false"/>
	
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
		
				
	 <table  cellspacing="0" cellpadding="0" align="center" style="width:100%;">
            
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
			<td height="24" colspan="3"><f:message key="jobContractSearchforMultipleMatches"/></td>
			<td style="text-align:right;"><a href="#start"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
			</tr>
			</table>
			
		         
		  <tr>
          <td valign="top" align="left" colspan="2"><table  width="100%" cellspacing="0" cols="4" class="LookupTable" >
              <tbody>
			<tr valign="center">
			<th><a href="#start" onClick="sortCustomerBydescription();"><f:message key="contractDescription"/></a></th>
			<th><a href="#start" onClick="sortCustomerBycontractCode();"><f:message key="contractid"/></a></th>
			<th><a href="#start" onClick="sortCustomerBycustomerCode();"><f:message key="customerCode"/></a></th>
			<th><a href="#start" onClick="sortCustomerByCurrency();"><f:message key="currency"/></a></th>
			<th><a href="#start" onClick="sortCustomerBycustomerName();"><f:message key="customer"/></a></th>
			<th><a href="#start" onClick="sortCustomerBycontactName();"><f:message key="scheduler"/></a></th>

			</tr>
	          <br/>
			<c:if test="${command.results != null}">
			<div id="contractsearchresults"> 
		<c:forEach items="${command.results}" var="addJobContracts" varStatus="status">
			<tr valign="center">
     <c:choose>
	 <c:when test="${status.index%2==0}">
		
		<c:choose>
		<c:when test="${addJobContracts.contractCust.customer.interunitInd!=null && addJobContracts.contractCust.customer.interunitInd=='true' && addJobContracts.contractCust.customer.interunitBusUnitName!=null &&command.buName == addJobContracts.contractCust.customer.interunitBusUnitName}">
		<td align="left" style="background-color:#FFFFFF;" >
		${addJobContracts.contractCust.contract.description}</td>
		<td align="left"  style="background-color:#FFFFFF;" >
		${addJobContracts.contractCust.contract.contractCode}</td>
		
		<td align="left"  style="background-color:#FFFFFF;" >
		${addJobContracts.contractCust.customer.custCode}</td>	
		<td align="left"  style="background-color:#FFFFFF;" >
			<c:forEach items="${addJobContracts.contractCust.contract.cfgContracts}" var="cfgContract" varStatus="cfgContractStatus">
			${cfgContract.currencyCD} 
			</c:forEach>
		</td>	
		<td align="left"  style="background-color:#FFFFFF;" >
		${addJobContracts.contractCust.customer.name}</td>	
		<td align="left" style="background-color:#FFFFFF;" >
		${addJobContracts.contact.firstName} ${addJobContracts.contact.lastName}</td>
		</c:when>

	    <c:otherwise>
		<td align="left" style="background-color:#FFFFFF;" >
		<%-- <a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${addJobContracts.contractCust.contract.contractCode},${addJobContracts.contractCust.customer.custCode},${addJobContracts.contact.id}');top.showContract1();top.popupboxclose();submitform();">${addJobContracts.contractCust.contract.description}</a> --%>
		 <a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${addJobContracts.contractCust.contract.contractCode},${addJobContracts.contractCust.customer.custCode},${addJobContracts.contact.id}');submitform('${addJobContracts.contractCust.contract.status}','${addJobContracts.contractCust.customer.status}','${addJobContracts.contact.status}');">${addJobContracts.contractCust.contract.description}</a>
		 </td> 
		<td align="left"  style="background-color:#FFFFFF;" >
		${addJobContracts.contractCust.contract.contractCode}</td>	
		<td align="left"  style="background-color:#FFFFFF;" >
		${addJobContracts.contractCust.customer.custCode}</td>
		<td align="left"  style="background-color:#FFFFFF;" >
			<c:forEach items="${addJobContracts.contractCust.contract.cfgContracts}" var="cfgContract" varStatus="cfgContractStatus">
				${cfgContract.currencyCD} 
			</c:forEach>
		</td>
		<td align="left"  style="background-color:#FFFFFF;" >
		${addJobContracts.contractCust.customer.name}</td>	
		<td align="left" style="background-color:#FFFFFF;" >
		${addJobContracts.contact.firstName} ${addJobContracts.contact.lastName}</td>
	    </c:otherwise>
	    </c:choose>
	
	 </c:when>
	 <c:otherwise>

				   <c:choose>
					<c:when test="${addJobContracts.contractCust.customer.interunitInd!=null && addJobContracts.contractCust.customer.interunitInd=='true' && addJobContracts.contractCust.customer.interunitBusUnitName!=null &&command.buName==addJobContracts.contractCust.customer.interunitBusUnitName}">
					<td align="left" style="background-color:#e7eeff;">
					${addJobContracts.contractCust.contract.description}</td>
					<td align="left"  style="background-color:#e7eeff;">
					${addJobContracts.contractCust.contract.contractCode}</td>	
					<td align="left" style="background-color:#e7eeff;">
					${addJobContracts.contractCust.customer.custCode}</td>
					<td align="left" style="background-color:#e7eeff;">
						<c:forEach items="${addJobContracts.contractCust.contract.cfgContracts}" var="cfgContract" varStatus="cfgContractStatus">
							${cfgContract.currencyCD} 
						</c:forEach>
					</td>
					<td align="left" style="background-color:#e7eeff;">
					${addJobContracts.contractCust.customer.name}</td>	
					<td align="left" style="background-color:#e7eeff;">
					${addJobContracts.contact.firstName} ${addJobContracts.contact.lastName}</td>
					</c:when>

				    <c:otherwise>
					<td align="left" style="background-color:#e7eeff;">
					<%-- <a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${addJobContracts.contractCust.contract.contractCode},${addJobContracts.contractCust.customer.custCode},${addJobContracts.contact.id}');top.showContract1();top.popupboxclose();submitform();">${addJobContracts.contractCust.contract.description}</a> --%>

					<a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${addJobContracts.contractCust.contract.contractCode},${addJobContracts.contractCust.customer.custCode},${addJobContracts.contact.id}');submitform('${addJobContracts.contractCust.contract.status}','${addJobContracts.contractCust.customer.status}','${addJobContracts.contact.status}');">${addJobContracts.contractCust.contract.description}</a>
					</td>
					<td align="left"  style="background-color:#e7eeff;">
					${addJobContracts.contractCust.contract.contractCode}</td>	
					<td align="left" style="background-color:#e7eeff;">
					${addJobContracts.contractCust.customer.custCode}</td>
					<td align="left" style="background-color:#e7eeff;">
						<c:forEach items="${addJobContracts.contractCust.contract.cfgContracts}" var="cfgContract" varStatus="cfgContractStatus">
							${cfgContract.currencyCD} 
						</c:forEach>
					</td>
					<td align="left" style="background-color:#e7eeff;">
					${addJobContracts.contractCust.customer.name}</td>	
					<td align="left" style="background-color:#e7eeff;">
					${addJobContracts.contact.firstName} ${addJobContracts.contact.lastName}</td>
				    </c:otherwise>
			        </c:choose>

		</c:otherwise>
		</c:choose>
				   </tr>
					</c:forEach>
					 	<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
					 <tr>
					           <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                             <td align="center">
			      <!-- START : #119240 -->
					<%--	 <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
 					<a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
	                  </c:forEach> --%>
					  <%@ include file="../common/pagination.jsp" %>
				  <!-- END : #119240 -->
					</td>
					</tr> 
					<tr></tr>
					</table>
					</div>
					</c:if>
					</tbody>
					</table>
						
				</div>
			</td>
		</tr>
	</table>
</form:form>		  
