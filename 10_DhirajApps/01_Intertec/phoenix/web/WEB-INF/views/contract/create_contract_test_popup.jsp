<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
  function doSubmit(operation)
  {
    document.CreateContractTestPopUpForm.operation.value = operation;
    document.CreateContractTestPopUpForm.submit();
  }  
</script>

<form:form name="CreateContractTestPopUpForm" method="POST" action="create_contract_test_popup.htm">    
  <input type="hidden" name="operation" value="" />
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <td width="15%"><strong><f:message key="contractid"/>:</strong></td>
      <td>${command.contractTest.contractTestId.contractId}</td>
    </tr>
    <tr>
      <td><strong><f:message key="TestId"/><span class="redstar">*</span>:</strong></td>
      <td>
        <form:input cssClass="inputBox" path="contractTest.contractTestId.testId" size="25" maxlength="96"/>
        <form:errors path="contractTest.contractTestId.testId" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td><strong><f:message key="description"/><span class="redstar">*</span>:</strong></td>
      <td>
        <form:textarea cssClass="inputBox" path="contractTest.test.testDescription" cols="50" rows="4"/>
        <form:errors path="contractTest.test.testDescription" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submitAndReturn');" value="Submit" />      
      </td>
    </tr>
  </table>
</form:form>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
  <IMG src=" images/fake-alpha-999.gif"> 
</div>

