<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
  function onSubmit(operation)
  {
    document.editContractSelectProductsPopUpForm.operation.value = operation;
    document.editContractSelectProductsPopUpForm.submit();
  }

  function selectAll(myPrefix, selectAllElement)
  {
    var elementList = document.forms[0].elements;
    for(i=0;i<elementList.length;i++)
    {
      if(elementList[i].type == "checkbox")
      {
        var checkBoxName = elementList[i].name;
        var myPosition = checkBoxName.indexOf(myPrefix);
        if(myPosition >= 0)
        {
          if(!elementList[i].disabled)
          {
            if(selectAllElement.checked) elementList[i].checked=true;
            else elementList[i].checked=false;
          }
        }
      }
    }
  }
</script>

<icb:list var="uomList" items="${icbfn:dropdown('uomTableDropDown',null)}" />

<form:form name="editContractSelectProductsPopUpForm" method="POST" action="edit_contract_select_products_popup.htm"> 
  <input type="hidden" name="operation" value="" />
  <input type="hidden" name="productGroupIndex" value="" />

  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>
  
  <table width="100%" border="0" align="center" class="InnerApplTable">
    <tr>
      <td valign="middle"  colspan="2">
        Select Products to Add to 
        <c:choose>
          <c:when test="${command.vesselTypeExt.rbExt.notesRb != null}">${command.vesselTypeExt.rbExt.notesRb.rbValue}</c:when>
          <c:otherwise>${command.vesselTypeExt.rbExt.rb.rbValue}</c:otherwise>
        </c:choose>
      </td> 
    </tr>
  </table>

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <td colspan="2">
        <input name="Submit3" type="button" class="button1" onclick="javascript:onSubmit('submit');" value="OK" />
        <input name="Submit4" type="button" class="button1" onclick="javascript:onSubmit('return');" value="Cancel" />
      </td>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <th>
        <form:checkbox path="checkAll" onclick="javascript:selectAll('productGroupExtList', this)"/>
      </th>
      <th>Product Group</th>
      <th><spring:message code="UOM" /></th>
    </tr>
    <c:forEach items="${command.productGroupExtList}" var="pgExt" varStatus="pgExtStatus">
      <tr>
        <td width="5%" valign="top">
          <form:checkbox path="productGroupExtList[${pgExtStatus.index}].selected" disabled="${pgExt.viewOnly}"/>
        </td>
        <td>
          <c:choose>
            <c:when test="${pgExt.rbExt.notesRb != null}">
              ${pgExt.rbExt.notesRb.rbValue}
            </c:when>
            <c:otherwise>
              ${pgExt.rbExt.rb.rbValue}
            </c:otherwise>
          </c:choose>
        </td>
        <td>
          <form:select id="sel1" cssClass="selectionBox" path="productGroupExtList[${pgExtStatus.index}].uom" items="${uomList}" itemLabel="name" itemValue="value" disabled="${pgExt.viewOnly}"/>
        </td>
      </tr>
    </c:forEach>
    <tr>
      <td colspan="2">
        <input name="Submit3" type="button" class="button1" onclick="javascript:onSubmit('submit');" value="OK" />
        <input name="Submit4" type="button" class="button1" onclick="javascript:onSubmit('return');" value="Cancel" />
      </td>
      <td colspan="2">&nbsp;</td>
    </tr>
  </table>
</form:form>
