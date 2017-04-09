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
  function doOperation(myOperationType, myIndex)
  {
    document.ModifySlatePricePopUpForm.operation.value = myOperationType;
    document.ModifySlatePricePopUpForm.index.value = myIndex;
    document.ModifySlatePricePopUpForm.submit();
  }

  function doSubmit(operation)
  {
    document.ModifySlatePricePopUpForm.operation.value = operation;
    document.ModifySlatePricePopUpForm.submit();
  }  
</script>

<icb:list var="localContractCode">
  <icb:item>${command.contractCode}</icb:item>
  <icb:item>${command.priceBookId}</icb:item>
</icb:list>
<icb:list var="zoneListDropDownItems" items="${icbfn:dropdown('zoneListDropDown',localContractCode)}" />
<c:set var="userDateFormat" value="${icbfn:userDateFormat()}" />

<form:form name="ModifySlatePricePopUpForm" method="POST" action="modify_slate_price_popup.htm">    
  <input type="hidden" name="index" value="search" />
  <input type="hidden" name="operation" value="search" />
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <td width="15%"><strong><f:message key="methodology"/>:</strong></td>
      <td>${command.slateId}</td>
    </tr>
    <tr>
      <td><strong><f:message key="description"/><span class="redstar">*</span>:</strong></td>
      <td>
        <c:if test="${command.slate != null}">
          <form:textarea cssClass="inputBox" path="slate.slateDescription" cols="50" rows="4"/>
          <form:errors path="slate.slateDescription" cssClass="redstar"/>
        </c:if>
      </td>
    </tr>
  </table>

  <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:100%; float:right;">
    <tr>
      <th colspan="8">
        <f:message key="ModifiedSlatePrices"/>: 
        &nbsp;&nbsp;
        <img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
        &nbsp;&nbsp;
        <form:input path="rowsToAdd" cssClass="inputBox" size="5"/>
        <form:errors path="rowsToAdd" cssClass="redstar"/>                                    
        <a href="#" onClick="javascript:doOperation('addSlatePrice');">
          <img src="images/add.gif" alt="Add Slate Price" width="13" height="12" hspace="1px" border="0"/>
        </a>
        &nbsp;&nbsp;From: <form:input cssClass="inputBox" id="fromDate" path="fromDate" size="10" maxlength="12"/>
        <a href="#" onClick="displayCalendar(document.forms[0].fromDate,'${userDateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
        &nbsp;&nbsp;From: <form:input cssClass="inputBox" id="toDate" path="toDate" size="10" maxlength="12"/>
        <a href="#" onClick="displayCalendar(document.forms[0].toDate,'${userDateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
        &nbsp;<a href="#" onClick="doOperation('refresh');">Apply</a>
      </th>
    </tr>
    <tr>
      <td colspan="8">
        <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submitAndReturn');" value="Save and Return" />      
        <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submit');" value="Save" />
        <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('return');" value="Return" />      
      </td>
    </tr>
    <tr>
      <th style="width:150px;"><nobr><f:message key="Zone"/><span class="redstar">*</span></nobr></th>
      <th style="width:150px;" ><nobr><f:message key="BeginDate"/><span class="redstar">*</span></nobr></th>
      <th style="width:150px;"><nobr><f:message key="EndDate"/><span class="redstar">*</span></nobr></th>
      <th nowrap><nobr><f:message key="MinQty"/><span class="redstar">*</span></nobr></th>
      <th nowrap><nobr><f:message key="MaxQty"/><span class="redstar">*</span></nobr></th>
      <th nowrap><nobr><f:message key="UnitPrice"/><span class="redstar">*</span></nobr></th>
      <th nowrap><nobr><f:message key="ContractRef"/><span class="redstar">*</span></nobr></th>
      <th nowrap>&nbsp;</th>
    </tr>
    <c:forEach items="${command.slatePrices}" var="slatePrice" varStatus="slatePriceStatus">   
      <c:if test="${(command.fromDate == null or slatePrice.slatePriceId.beginDate >= command.fromDate) and (command.toDate == null or slatePrice.slatePriceId.beginDate <= command.toDate)}">
        <tr valign='center'>
          <td align='left'>
            <form:select cssClass="selectionBox" path="slatePrices[${slatePriceStatus.index}].slatePriceId.location" items="${zoneListDropDownItems}" itemLabel="name" itemValue="value"/>
          </td>
          <td align='left' nowrap>
            <form:input cssClass="inputBox" path="slatePrices[${slatePriceStatus.index}].slatePriceId.beginDate" size="10" maxlength="12"/>
            <a href="#" onClick="displayCalendar(document.forms[0].elements['slatePrices[${slatePriceStatus.index}].slatePriceId.beginDate'],'${userDateFormat}',this)"> 
              <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
            </a>
            <form:errors path="slatePrices[${slatePriceStatus.index}].slatePriceId.beginDate" cssClass="redstar"/>
          </td>
          <td align='left' nowrap>
            <form:input cssClass="inputBox" path="slatePrices[${slatePriceStatus.index}].endDate" size="10" maxlength="12"/>
            <a href="#" onClick="displayCalendar(document.forms[0].elements['slatePrices[${slatePriceStatus.index}].endDate'],'${userDateFormat}',this)"> 
              <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
            </a>
            <form:errors path="slatePrices[${slatePriceStatus.index}].endDate" cssClass="redstar"/>
          </td>
          <td align='left' valign="middle" nowrap >
            <form:input cssClass="inputBox" path="slatePrices[${slatePriceStatus.index}].slatePriceId.minQty" size="10" maxlength="12"/>
            <form:errors path="slatePrices[${slatePriceStatus.index}].slatePriceId.minQty" cssClass="redstar"/>
          </td>
          <td align='left' valign="middle" nowrap >
            <form:input cssClass="inputBox" path="slatePrices[${slatePriceStatus.index}].slatePriceId.maxQty" size="10" maxlength="12"/>
            <form:errors path="slatePrices[${slatePriceStatus.index}].slatePriceId.maxQty" cssClass="redstar"/>
          </td>
          <td align='left' valign="middle" >
            <form:input cssClass="inputBox" path="slatePrices[${slatePriceStatus.index}].unitPrice" size="10" maxlength="12"/>
            <form:errors path="slatePrices[${slatePriceStatus.index}].unitPrice" cssClass="redstar"/>
          </td>
          <td align='left' valign="middle" nowrap >
            <form:input cssClass="inputBox" path="slatePrices[${slatePriceStatus.index}].contractRef" size="10" maxlength="12"/>
            <form:errors path="slatePrices[${slatePriceStatus.index}].contractRef" cssClass="redstar"/>
          </td>
          <td align='left' valign="middle"  nowrap='nowrap'>
            <div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> 
              <a href="#" onClick="javascript:doOperation('deleteSlatePrice', '${slatePriceStatus.index}');">
                <img src="images/delete.gif" alt="Delete" width="13" height="12" hspace="1px" border="0"/>
              </a>  
            </div>
          </td>
        </tr>
      </c:if>        
    </c:forEach>
    <tr>
      <td colspan="8">
        <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submitAndReturn');" value="Save and Return" />      
        <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submit');" value="Save" />
        <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('return');" value="Return" />      
      </td>
    </tr>
  </table>
</form:form>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
  <IMG src=" images/fake-alpha-999.gif"> 
</div>

