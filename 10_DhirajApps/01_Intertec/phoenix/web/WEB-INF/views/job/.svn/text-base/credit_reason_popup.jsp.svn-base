<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<script language="javascript">
  function submitform()
  {
  	top.document.forms[0].rebillFlag.value = "rebill";
  	top.document.forms[0].creditReasonNote.value = document.getElementById("note").value;
  	top.document.forms[0].creditReasonUser.value = document.getElementById("user").value;
	top.document.forms[0].submit();
  }
  function validateFields(div1,div2,viewFlag)
  {
  
  	if(viewFlag != '' && viewFlag == 'readonly')
  	{
  			  	top.hidePopupDiv(div1,div2);
	  			top.popupboxclose();
	  			
  	}
  	else
  	{
	  	if(document.getElementById("note").value == '')
	  	{
	  		confirm("Please enter the credit reason note");
	  		return false;
	  	}
	  	  	if(document.getElementById("user").value == '')
	  	{
	  		confirm("Please enter the credit reason approval manager");
	  		return false;
	  	}
	  	
	  	submitform();
	  	top.hidePopupDiv(div1,div2);
	  	popupboxclose();
	  	
  	}
  	
  }


   function formfocus() {
      document.getElementById('note').focus();
   }
   window.onload = formfocus;

  
</script>  
<form:form name="creditReasonPopUpForm" method="POST" action="credit_reason_popup.htm">    
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>
<input type="hidden" name="controllerName" value="${command.controllerName }" />
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <th colspan="2"><f:message key="creditReason"/></th>
    </tr>
    <tr>
      <td width="12%" valign="top"><f:message key="creditReasonNote"/>: <span class="redstar">*</span></td>
      <td width="18%" align="right">
      
           <c:choose>
      <c:when test="${command.readWriteFlag == null || command.readWriteFlag == ''}">
      <form:textarea id="note" path="creditReasonNote" rows="4" cols="40" />
      </c:when>
      <c:otherwise>
      <form:textarea id="note" path="creditReasonNote" rows="4" cols="40" disabled="true"/>
      </c:otherwise>
      </c:choose>
      
 
        <form:errors path="creditReasonNote" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td width="12%"><f:message key="creditReasonUser"/>:<span class="redstar">*</span> </td>
      <td width="18%" align="right">
      <c:choose>
      <c:when test="${command.readWriteFlag == null || command.readWriteFlag == ''}">
      <form:input id="user" cssClass="inputBox" path="creditReasonUser" size="30" maxlength="70"/>
      <!--<form:select id="user" cssClass="selectionBox" path="creditReasonUser" items="${icbfn:dropdown('users', null)}" itemLabel="name" itemValue="value" />-->
      </c:when>
      <c:otherwise>
      <form:input cssClass="inputBox" id="user" path="creditReasonUser" size="30" maxlength="70" disabled="true"/>
      <!--<form:select id="user" cssClass="selectionBox" path="creditReasonUser" items="${icbfn:dropdown('users', null)}" itemLabel="name" itemValue="value" disabled="true"/>-->
      </c:otherwise>
      </c:choose>
        
        <form:errors path="creditReasonUser" cssClass="redstar"/>
      </td>
    </tr>

    <tr>
      <td colspan="8" style="padding:2px;">
        <table width="99%" border="0" cellpadding="0" cellspacing="0" class="secAppltable">
          <tr>
            <td>
                       <c:choose>
					      <c:when test="${command.readWriteFlag == null || command.readWriteFlag == ''}">
					      <input name="button" type="button" class="button1" value="Add" onClick="javaascript:validateFields('creditreason_${command.contractIndex }','creditreason_${command.contractIndex }','${command.readWriteFlag }');"/>
					      </c:when>
					      <c:otherwise>
					      <input name="button" type="button" class="button1" value="Ok" onClick="javaascript:validateFields('creditreason_${command.contractIndex }','creditreason_${command.contractIndex }','${command.readWriteFlag }');"/>
					      </c:otherwise>
					    </c:choose>
              
            </td>
          </tr>
        </table>    
      </td>
    </tr>
<ajax:autocomplete
  baseUrl="createuser.htm"
  source="creditReasonUser"
  target="creditReasonUser"
  className="autocomplete"
  parameters="username={creditReasonUser}"
  minimumCharacters="1" />     
  </table>
</form:form>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
  <IMG src=" images/fake-alpha-999.gif"> 
</div>

