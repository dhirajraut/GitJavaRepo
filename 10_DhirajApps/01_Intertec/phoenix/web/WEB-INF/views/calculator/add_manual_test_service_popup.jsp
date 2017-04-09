<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<script language="javascript">
  function doSubmit(operation)
  {
    document.addManualTestServicePopUpForm.operation.value = operation;
    document.addManualTestServicePopUpForm.submit();
  }  
</script>

<form:form name="addManualTestServicePopUpForm" method="POST" action="add_manual_test_service_popup.htm">    
  <input type="hidden" name="operation" value="submit" />
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <th colspan="2"><f:message key="manualTests"/></th>
    </tr>
    <tr>
      <td width="12%" valign="top"><f:message key="methodology"/>: <span class="redstar">*</span></td>
      <td width="13%" align="right">
        <form:input path="jobContractManualTestExt.manualTest.testId" />
        <form:errors path="jobContractManualTestExt.manualTest.testId" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td width="12%"><f:message key="description"/>: </td>
      <td width="13%" align="right">
        <form:textarea path="jobContractManualTestExt.manualTest.testDescription"  rows="3" cols="30"/>
        <form:errors path="jobContractManualTestExt.manualTest.testDescription" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td width="12%"><f:message key="quantity"/>: <span class="redstar">*</span></td>
      <td width="13%" align="right">
        <form:input path="jobContractManualTestExt.manualTest.quantity" size="10"/>
        <form:errors path="jobContractManualTestExt.manualTest.quantity" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td width="12%"><f:message key="price"/>: <span class="redstar">*</span></td>
      <td width="13%" align="right">
        <form:input path="jobContractManualTestExt.manualTest.totalPrice" size="10"/>
        <form:errors path="jobContractManualTestExt.manualTest.totalPrice" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td colspan="8" style="padding:2px;">
        <table width="99%" border="0" cellpadding="0" cellspacing="0" class="secAppltable">
          <tr>
            <td>
              <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submitAndReturn');" value="Submit and Return" />      
              <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submit');" value="Submit" />
              <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('return');" value="Return" />      
            </td>
          </tr>
        </table>    
      </td>
    </tr>
  </table>
</form:form>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
  <IMG src=" images/fake-alpha-999.gif"> 
</div>

