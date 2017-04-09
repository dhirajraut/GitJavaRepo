<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<head>
<script type="text/javascript" src="js/globalFunctions.js"></script>
<script language="javascript" src="js/ce/ce_invoice_preview.js"></script>

<form:form name="revenueSegregationPopupForm" method="POST" action="phx_revenue_segregation_popup.htm">

<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    <tr>
      <th style="height:20px;"><f:message key="expense" /></th>
	  <th><f:message key="amount" /></th>
      <th><f:message key="account" /></th>
      <th><f:message key="deptId" /></th>
    </tr>

<c:forEach items="${command.revenueSegregationForms}" var="revenue" varStatus="status">

  <tr>
    <td style="height:20px;">${revenue.description}</td>
	<td style="text-align:right;">${revenue.amount}</td>
    <td>${revenue.account}</td>
    <td>${revenue.deptId}</td>
  </tr>
  
</c:forEach>  
  
  <tr>
    <td colspan="4" style="height:26px;"><input name="Button" type="button" class="button1" value="Submit" onClick="parent.hiderevenuesegregation();parent.popupboxclose();" /></td>
  </tr>
</table>

</form:form>
