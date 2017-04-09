<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script type="text/javascript" src="js/ce/ce_edit_price_popup.js"></script>

<form:form name="editPricePopupForm" method="POST" action="phx_edit_price_popup.htm">
  <form:hidden path="operation"/>
  
  <div class="redtext"><form:errors cssClass="error" /></div>
  
  <table width="95%" border="0" align="center" class="InnerApplTable">
    <c:forEach items="${command.revenueSegregationForms}" var="revenue" varStatus="status">
	  <tr>
	    <td style="height:20px;">
	    <c:choose>
	    <c:when test="${revenue.primary}">
	    	<div class="redtext"> 
	    		<strong>${revenue.revenueSegregation.description}</strong>
	    	</div>	
	    </c:when>
	    <c:otherwise>
	    	${revenue.revenueSegregation.description}
	    </c:otherwise>
	    </c:choose>
	    </td>
		<td style="text-align:right;">
			<form:input size="12" cssClass="inputBox" path="revenueSegregationForms[${status.index}].amount" />
			<form:errors path="revenueSegregationForms[${status.index}].amount" cssClass="redstar"/>
		</td>
	  </tr>
	</c:forEach>  
  
     <tr>
       <td colspan="2">
       		<input name="Submit" type="button" class="button1" value="Submit" onclick="submitEditPriceForm()">
       </td>
    </tr>
  </table>
  
  <SCRIPT>
	refreshParent();
  </SCRIPT>
  
</form:form>