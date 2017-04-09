<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="js/ce/ce_add_test_popup.js"></script>



<form:form name="addManualTestForm" method="POST" action="phx_add_manual_test.htm">

	<form:hidden path="manualTest"/>
	<form:hidden path="operation"/>
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>



  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <th colspan="2"><f:message key="manualTests"/></th>
    </tr>

    <tr>
      <td width="12%" valign="top"><f:message key="methodology"/>:</td>
      <td width="13%" align="right">
        <form:input path="methodology" id="testid"/>
        <form:errors path="methodology" cssClass="redstar"/>
      </td>
    </tr>

	<tr>
      <td width="12%"><f:message key="description"/>: </td>
      <td width="13%" align="right">
        <form:textarea path="description" id="description" rows="3" cols="30"/>
        <form:errors path="description" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td width="12%"><f:message key="quantity"/>: <span class="redstar">*</span></td>
      <td width="13%" align="right">
        <form:input path="quantity" size="10" id="qty"/>
        <form:errors path="quantity" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td width="12%"><f:message key="price"/>: <span class="redstar">*</span></td>
      <td width="13%" align="right">
        <form:input path="unitPrice" size="10" id="price"/>
        <form:errors path="unitPrice" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td colspan="8" style="padding:2px;">
        <table width="99%" border="0" cellpadding="0" cellspacing="0" class="secAppltable">
          <tr>
            <td>
			<input id="cancel2" type="button" value="Submit and Return" name="submit1" class="button1" onClick="javascript:submitManualTest('manualTestSubmitAndReturn');"/>
			<input id="cancel2" type="button" value="Submit" name="submit2" class="button1" onClick="javascript:submitManualTest('manualTestSubmit');"/>
			<input id="cancel2" type="button" value="Return" name="submit3" class="button1" onClick="javascript:submitManualTest('manualTestReturn');"/>
            </td>
          </tr>
        </table>    
      </td>
    </tr>
  </table>
</form:form>
<SCRIPT>
	refreshParent1();
</SCRIPT>


