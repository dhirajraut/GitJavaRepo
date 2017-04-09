<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script type="text/javascript" src="js/ce/ce_edit_deposit_amount_popup.js"></script>

<form:form name="editDepositAmountPopupForm" method="POST" action="phx_edit_dep_amount_popup.htm">
  <form:hidden path="operation"/>
  
  <div class="redtext"><form:errors cssClass="error" /></div>
  
  <table width="95%" border="0" align="center" class="InnerApplTable">
	 <tr>
	    <td style="height:20px;"><f:message key="depositeRefNo"/> :</td>
	    <td style="text-align:left;"><strong>${command.reference}</strong></td>
	 </tr>
	 <tr>
		<td><f:message key="depositAmount"/> : </td>
		<td style="text-align:left;">
			<form:input size="12" cssClass="inputBox" path="depositAmount" />
			<form:errors path="depositAmount" cssClass="redstar"/>
		</td>
	 </tr>
	  
     <tr>
       <td colspan="2">
       		<input name="Submit" type="button" class="button1" value="Submit" onclick="submitEditDepositAmountForm()">
       </td>
    </tr>
  </table>
  
  <SCRIPT>
	refreshParent();
  </SCRIPT>
  
</form:form>