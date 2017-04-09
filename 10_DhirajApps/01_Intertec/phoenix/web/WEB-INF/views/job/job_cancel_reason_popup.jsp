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
	top.document.forms[0].goForm.value = "jobCancelAndGo";
  	top.document.forms[0].jobCancelReasonNote.value = document.getElementById("note").value;
  	top.document.forms[0].jobCancelReasonUser.value = document.getElementById("user").value;
	top.document.forms[0].submit();
  }
  function validateFields(div1,div2)
  {
	  	if(document.getElementById("note").value == '')
	  	{
	  		confirm("Please enter the job cancel reason note");
	  		return false;
	  	}
	  	if(document.getElementById("user").value == '')
	  	{
	  		confirm("Please enter the job cancel reason user");
	  		return false;
	  	}
	  	submitform();
	  	top.hidePopupDiv(div1,div2);
	  	top.popupboxclose();
  }
   function formfocus() {
      document.getElementById('note').focus();
   }
   window.onload = formfocus;

</script>  
<form:form name="cancelReasonPopUpForm" method="POST" action="job_cancel_reason_popup.htm">    
 
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <th colspan="2"><f:message key="cancelReason"/></th>
    </tr>
    <tr>
      <td width="12%" valign="top"><f:message key="cancelReasonNote"/>: <span class="redstar">*</span></td>
      <td width="18%" align="right">
      
       <form:textarea id="note" path="jobOrder.cancelReasonNote" rows="4" cols="40" />
       <form:errors path="jobOrder.cancelReasonNote" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td width="12%"><f:message key="cancelReasonUser"/>:<span class="redstar">*</span> </td>
      <td width="18%" align="right">
       <form:input id="user" cssClass="inputBox" path="jobOrder.cancelReasonUserName" size="30" maxlength="128"/>
        <form:errors path="jobOrder.cancelReasonUserName" cssClass="redstar"/>
      </td>
    </tr>
	 <tr>
      <td colspan="8" style="padding:2px;">
        <table width="99%" border="0" cellpadding="0" cellspacing="0" class="secAppltable">
          <tr>
            <td> 
				<input name="button" type="button" class="button1" value="Add" onClick="javaascript:validateFields('cancelreason','cancelreason_drag');"/> 
            </td>
          </tr>
        </table>    
      </td>
    </tr>
  <ajax:autocomplete
  baseUrl="createuser.htm"
  source="jobOrder.cancelReasonUserName"
  target="jobOrder.cancelReasonUserName"
  className="autocomplete"
  parameters="username={jobOrder.cancelReasonUserName}"
  minimumCharacters="1" /> 
  </table>
</form:form>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
  <IMG src=" images/fake-alpha-999.gif"> 
</div>

