<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><head>

<script	type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript"	src="js/ce/common.js"></script>

<form:form name="purchaseOrderPopupForm" method="POST" action="search_purchase_order_popup.htm">

	<input type="hidden" name="pageNumber" value="1" />
     <form:hidden path="sortFlag"/>
     <form:hidden path="submitFlag"/>	
	  <form:hidden path="searchForm"/>
	  <form:hidden path="inputFieldId"/>
	<div class="redtext"></div>
	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
		<tr>
			<td valign="top">
			<div id="tab1" class="innercontent">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="mainApplTable">
				</tr>
		<tr>
		<td width="20%" class="TDShade"><f:message key="po"/>: </td>
		<td width="80%" class="TDShadeBlue">
		<form:input cssClass="inputBox" path="poNumber.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
		</td>
		</tr>
		<tr>
		<td class="TDShade"><f:message key="customerID"/>:</td>
		<td class="TDShadeBlue">
		<form:input cssClass="inputBox" path="customerId.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 

		</td>
		</tr>
		<tr>
		<td class="TDShade"><f:message key="customerName"/>: </td>
		<td class="TDShadeBlue">
		<form:input cssClass="inputBox" maxlength="40" path="customerName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>

		</td>
		</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="applTableBot">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td><input id="search" type="button" onClick="submitFunction()" value="Search"
								name="button" class="button1" />
								<td></td>
							</td>
							
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<br/>
			<c:if test="${command.results != null}">
	<div id="posearchresults"> 
	<strong>Search Results</strong>
	<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
	<tr>
		<th><a href="#" onClick="sortPOByNumber();" ><span class="TDShade"><f:message key="po"/></span></a></th>
	    <th><a href="#" onClick="sortPOByBalanceAmount();" ><span class="TDShade"><f:message key="balanceAmount"/></span></a></th>	 
	</tr>
	
	<c:forEach items="${command.results}" var="po" varStatus="status">
        
	  <c:choose>
		 <c:when test="${status.index%2==0}">
			<tr class="whitebg">
		 </c:when>
		   <c:otherwise>
			 <tr class="bluebg">                    
		   </c:otherwise>
	 </c:choose>         



	  <c:choose>
	     <c:when test="${command.searchForm=='jobInstructionTestAttribForm'}">		
            <td><a href="#" onclick="javascript:top.return_popup_result('${command.inputFieldId}','${po.poNumber}');">${po.poNumber}</a></td>
	          </c:when>
	             <c:otherwise>
	           <td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${po.poNumber}');">${po.poNumber}</a></td>
	       </c:otherwise>
	      </c:choose>
		    <td>${po.balanceAmount}</td>
       </tr>
    </c:forEach>

    <tr>
      <td>&nbsp;</td>
      <td align="center">
        <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
          <a href="#start" onClick="submitPOSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
        </c:forEach>
      </td>
    </tr>
	
	</table>
	</div>
</c:if>
</form:form>

