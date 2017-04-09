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
				document.addressSeqPopUpForm.pageNumber.value = pageNumber;
				document.addressSeqPopUpForm.submit();
			  }

		function submitform()
		{
			var name=document.getElementById("searchForm").value;
			top.document.forms[name].submit();
		}

    function submitSearch(pageNumber)
  {	 
	 
	document.addressSeqPopUpForm.pageNumber.value = pageNumber;
	document.addressSeqPopUpForm.submit();
  }	
function sortAddressSeqByLocationNumber(){
document.addressSeqPopUpForm.pageNumber.value = "1";
document.addressSeqPopUpForm.sortFlag.value = "custAddrSeqId.locationNumber";
document.addressSeqPopUpForm.submit();
}
function sortAddressSeqByAddressDescr(){
document.addressSeqPopUpForm.pageNumber.value = "1";
document.addressSeqPopUpForm.sortFlag.value = "addressDescr";
document.addressSeqPopUpForm.submit();
}

function submitFunction()
{
	document.addressSeqPopUpForm.pageNumber.value = "1";
	document.addressSeqPopUpForm.sortFlag.value = "";
	document.addressSeqPopUpForm.submit();
}   
</script>
<form:form name="addressSeqPopUpForm" method="POST" action="search_address_sequence_popup.htm">
	<input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
	<form:hidden path="rowNum" />
    <form:hidden path="custCode"/>
	<form:hidden path="sortFlag"/>
	
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
						<tr>
							<th colspan="2"><f:message key="addresssequence"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="locationNo"/>: </td>
							<td width="80%" class="TDShadeBlue">
								<form:input cssClass="inputBox" maxlength="3" path="locationNumber.value" />
									<form:errors path="locationNumber.value" cssClass="redstar"/>	
							</td>
						</tr>
						  <tr>
							<td width="20%" class="TDShade" nowrap><f:message key="addressDescription"/>: </td>
							<td width="80%" class="TDShadeBlue">
								<form:input cssClass="inputBox" path="addressDescr.value" />
								<form:errors path="addressDescr.value" cssClass="redstar"/>	
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="button" type="button" onClick="submitFunction()" class="button1" value="<f:message key="search"/>"  /></td>
									
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<c:if test="${command.results != null}">
						<div id="addresssequencesearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th nowrap><a href="#start" onClick="sortAddressSeqByLocationNumber();" ><span class="TDShade"><f:message key="locationNo"/></span></a></th>
									<th nowrap><a href="#start" onClick="sortAddressSeqByAddressDescr();" ><span class="TDShade"><f:message key="addressDescription"/></span></a></th>
									<th><span class="TDShade"><f:message key="addressDetails"/></span></th>
									
								</tr>
								<c:forEach items="${command.results}" var="addressseq" varStatus="status">
									 <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>	<td nowrap>
					<a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}','${addressseq.custAddrSeq.custAddrSeqId.locationNumber}'); top.hideVatcode('addressseq${command.rowNum}','addressseqbody${command.rowNum}');top.popupboxclose();top.document.${command.searchForm }.submit();">${addressseq.custAddrSeq.custAddrSeqId.locationNumber}</a></td>	 
					 <td nowrap>${addressseq.custAddrSeq.addressDescr}</td> 
					 <td>${addressseq.addressDetails}</td> 
									   </tr>
							 	</c:forEach>
									<tr>
					              <td>&nbsp;</td>
									<td align="center">

									<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
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