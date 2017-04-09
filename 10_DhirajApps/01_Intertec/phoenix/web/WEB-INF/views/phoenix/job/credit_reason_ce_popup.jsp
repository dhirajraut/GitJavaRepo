<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<script type="text/javascript" src="js/ce/ce_services.js"></script>

<form:form name="creditReasonCePopUpForm" method="POST" action="phx_credit_reason_ce_popup.htm">    
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
     <form:errors cssClass="error"/>
  </div>
 
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
      <td width="12%" valign="top"><f:message key="creditDescription"/>:</td>
        <td width="18%" align="right">      
           <c:choose>
              <c:when test="${command.readWriteFlag == null || command.readWriteFlag == ''}">
                 <form:textarea id="creditdesc" path="creditDescription" rows="4" cols="40" />
                   </c:when>
                   <c:otherwise>
                  <form:textarea id="creditdesc" path="creditDescription" rows="4" cols="40" disabled="true"/>
                </c:otherwise>
              </c:choose>
           <form:errors path="creditDescription" cssClass="redstar"/>
       </td>
    </tr>
    
   
	<tr>
		<td width="12%"><f:message key="creditReasonUser"/>:<span class="redstar">*</span> </td>
		   <td width="18%" align="right">
		     <c:choose>
		       <c:when test="${command.readWriteFlag == null || command.readWriteFlag == ''}">
		          <form:input id="user" cssClass="inputBox" path="creditReasonUser" size="30" maxlength="70" disabled="true"/>
		            </c:when>
		           <c:otherwise>
		         <form:input cssClass="inputBox" id="user" path="creditReasonUser" size="30" maxlength="70" disabled="true"/>     
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
					<input name="button" type="button" class="button1" value="Add" onClick="javaascript:validateFields('creditreason_${command.index }','creditreason_${command.index }','${command.readWriteFlag }');"/>
					      </c:when>
					      <c:otherwise>
					      <input name="button" type="button" class="button1" value="Ok" onClick="javaascript:validateFields('creditreason_${command.index }','creditreason_${command.index }','${command.readWriteFlag }');"/>
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

