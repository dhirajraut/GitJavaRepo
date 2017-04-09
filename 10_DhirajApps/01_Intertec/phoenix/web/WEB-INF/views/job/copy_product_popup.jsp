<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="javascript">
	  		function submitform()
		{
			
			var chosenVal = document.getElementById("select2").value;
			top.document.forms[0].vesselIndex.value = document.copyProductPopupForm.vesselIndex.value;
			top.document.forms[0].productIndex.value = document.copyProductPopupForm.productIndex.value;
			top.document.forms[0].targetVesselIndex.value = chosenVal;
			top.document.forms[0].submit();
			
		}

   </script>

<form:form name="copyProductPopupForm" method="POST" action="copy_product_popup.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
 
      <form:hidden path="vesselIndex"/>
      <form:hidden path="targetVesselIndex"/>
      <form:hidden path="productIndex"/>
 
      
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="LookupTable">
  <tr>
    <td>Copy from: </td>
    <td><select name="select" class="selectionBox">
      <option>Current Vessel</option>
    </select>    </td>
  </tr>
  <tr>
    <td>Copy to:</td>
    <td><select name="select2" id="select2" class="selectionBox">
      <option value="${command.vesselIndex}">Current Vessel</option>
      <c:forEach items="${command.vesselNames}" var="arrVesselNames" varStatus="counter">   
      <option value="${counter.index}">${command.vesselNames[counter.index]}</option>
 	  </c:forEach>
        </select></td>
  </tr>
</table>


<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
 <tr><td><input id="ok" type="button" value="OK" name="ok" class="button1" onclick="javascript:submitform();top.hidePopupDiv('shippingagent','shippingagentbody');top.popupboxclose();"/>&nbsp;&nbsp;<input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="javascript:top.hidePopupDiv('shippingagent','shippingagentbody');top.popupboxclose();"/>
&nbsp;&nbsp;</td>
</tr>
</table>
      
 
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->

</form:form>






