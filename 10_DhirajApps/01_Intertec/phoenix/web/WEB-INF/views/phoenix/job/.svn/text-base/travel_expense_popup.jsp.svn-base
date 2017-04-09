<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript" src="js/ce/common.js"></script>
<script type="text/javascript" src="js/ce/ce_services.js"></script>

<form:form name="travelExpensePopupForm" method="POST" action="phx_travel_expense_popup.htm">
    
  <table width="95%" border="0" align="center" class="InnerApplTable">
    <c:forEach items="${command.revenueSegregationForms}" var="revenue" varStatus="status">

	  <tr>
	    <td style="height:20px;"><strong> <f:message key="description"/></strong></td>
		<td style="text-align:right;">
			<form:input size="12" cssClass="inputBox" path="description" />
		</td>
	  </tr>

	  <tr>
	    <td style="height:20px;"><strong>${revenue.revenueSegregation.description}</strong></td>
		<td style="text-align:right;">
			<form:input size="12" cssClass="inputBox" path="revenueSegregationForms[${status.index}].amount" />
		</td>
	  </tr>
	</c:forEach>  
  
     <tr>
       <td colspan="2">
       		<input name="Submit" type="button" class="button1" value="Submit" onclick="submitTravelExpenseForm();">
       </td>
    </tr>
  </table>

</form:form>
	
	
