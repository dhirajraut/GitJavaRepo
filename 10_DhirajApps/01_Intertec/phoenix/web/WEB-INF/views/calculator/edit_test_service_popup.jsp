<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<form:form name="editTestServicePopUpForm" method="POST" action="edit_test_service_popup.htm">    
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%; margin-bottom:15px;">
    <tr>
     <th><f:message key="methodology"/></th>
          <th><f:message key="description"/></th>
          <th><f:message key="quantity"/><span class="redstar">*</span></th>
          <th><f:message key="pB/CONT"/></th>
    </tr>




    <tr>
      <td>${command.jobContractTest.test.testId}</td>
      <td>${command.jobContractTest.test.testDescription} </td>
      <td>
        <form:input path="jobContractTest.quantity" size="10"/>
        <form:errors path="jobContractTest.quantity" cssClass="redstar"/>
      </td>
      <td>${command.jobContractTest.contractRefNo}</td>
    </tr>

    <tr>
      <td colspan="4">
        <input name="Submit" type="submit" class="button1" value="Save" />&nbsp;&nbsp;
      </td>
    </tr>
  </table>
</form:form>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
  <IMG src=" images/fake-alpha-999.gif"> 
</div>

