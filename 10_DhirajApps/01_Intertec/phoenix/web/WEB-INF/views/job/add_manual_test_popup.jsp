<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="javascript">
//var temp='';
function IsNumeric(strString)
   //  check for valid numeric strings	
   {
   var strValidChars = "0123456789.-";
   var strChar;
   var blnResult = true;

   if (strString.length == 0) return false;

   //  test strString consists of valid characters listed above
   for (i = 0; i < strString.length && blnResult == true; i++)
      {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
         blnResult = false;
         }
      }
   return blnResult;
   }
	  		function submitform(testId,qty,price,flag)
			{
				var tid=document.getElementById('testid').value;
				var desc=document.getElementById('desc').value;
				var quantity=document.getElementById('qty').value;
				var totalprice=document.getElementById('price').value;
				//document.forms[0].submit();
				
				if(flag == 'submitAndReturn'){
					if('' == tid || '' == quantity || '' == totalprice){
						document.forms[0].submit();
					}
					else if(!IsNumeric(quantity) || !IsNumeric(totalprice)){
						document.forms[0].submit();
					}
					else {
						if(null != top.document.forms[0].chosenManualTestIds.value && '' != top.document.forms[0].chosenManualTestIds.value
						&&null != top.document.forms[0].manualTestQty.value && '' != top.document.forms[0].manualTestQty.value
						&&null != top.document.forms[0].manualTestPrice.value && '' != top.document.forms[0].manualTestPrice.value
						
						)
						{
							top.document.forms[0].chosenManualTestIds.value = top.document.forms[0].chosenManualTestIds.value+";"+tid;
							if(null == desc || "" == desc)
								desc='@';//because we are maintaining a matrix of records, but description is not a mandatory field, so the arrays should be of same size for all the fields. so if no desc entered the the size of the desc array would be lessthan the one which are mandatory like price. 
							top.document.forms[0].testDesc.value = top.document.forms[0].testDesc.value+";"+desc;
							top.document.forms[0].manualTestQty.value=top.document.forms[0].manualTestQty.value+";"+quantity;
							top.document.forms[0].manualTestPrice.value=top.document.forms[0].manualTestPrice.value+";"+totalprice;
							//confirm(top.document.forms[0].manualTestPrice.value);
							document.forms[0].submit();
							top.document.forms[0].submit();
						}
						else{
							top.document.forms[0].chosenManualTestIds.value = tid;
							//confirm(desc);
							top.document.forms[0].testDesc.value = desc;
							//confirm(top.document.forms[0].testDesc.value);
							top.document.forms[0].manualTestQty.value=quantity;
							top.document.forms[0].manualTestPrice.value=totalprice;
							document.forms[0].submit();
							top.document.forms[0].submit();
						}
					}
				}
			
				if(flag == 'submit'){
				
					if(null != top.document.forms[0].chosenManualTestIds.value && '' != top.document.forms[0].chosenManualTestIds.value
					&&null != top.document.forms[0].manualTestQty.value && '' != top.document.forms[0].manualTestQty.value
					&&null != top.document.forms[0].manualTestPrice.value && '' != top.document.forms[0].manualTestPrice.value
					)
					{
						//confirm('1');
						//confirm(top.document.forms[0].manualTestPrice.value);
						document.forms[0].flag.value="submit";
						//confirm(document.forms[0].flag.value);
						top.document.forms[0].chosenManualTestIds.value = top.document.forms[0].chosenManualTestIds.value+";"+tid;
						if(null == desc || "" == desc)
								desc='@';
						top.document.forms[0].testDesc.value = top.document.forms[0].testDesc.value+";"+desc;
						top.document.forms[0].manualTestQty.value=top.document.forms[0].manualTestQty.value+";"+quantity;
						top.document.forms[0].manualTestPrice.value=top.document.forms[0].manualTestPrice.value+";"+totalprice;
						//confirm(top.document.forms[0].manualTestPrice.value);
						document.forms[0].submit();
						//document.getElementById('testid').value = " ";
					}
					else
					{
					//confirm('2');
						document.forms[0].flag.value="submit";
						//confirm(document.forms[0].flag.value);
						document.forms[0].submit();
						top.document.forms[0].chosenManualTestIds.value = tid;
						top.document.forms[0].testDesc.value = desc;
						top.document.forms[0].manualTestQty.value=quantity;
						top.document.forms[0].manualTestPrice.value=totalprice;
						//confirm(top.document.forms[0].manualTestPrice.value);
						//document.getElementById('testid').value = " ";
					}
					<%--document.getElementById('testid').value=" ";
					document.getElementById('desc').value=" ";
					document.getElementById('qty').value=" ";
					document.getElementById('price').value=" ";--%>
				}
			
				if(flag == 'return'){
					document.forms[0].submit();
					top.document.forms[0].submit();
				}
			
		}

   </script>

<form:form name="addManualTestForm" method="POST" action="add_manual_test.htm">

	<form:hidden path="div1"/>
	<form:hidden path="div2"/>
	<input type="hidden" name="flag" value=""/>
	
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>


  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <th colspan="2"><f:message key="manualTests"/></th>
    </tr>
    <tr>
      <td width="12%" valign="top"><f:message key="methodology"/>: <span class="redstar">*</span></td>
      <td width="13%" align="right">
        <form:input path="jobManualTest.testId" id="testid"/>
        <form:errors path="jobManualTest.testId" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td width="12%"><f:message key="description"/>: </td>
      <td width="13%" align="right">
        <form:textarea path="jobManualTest.testDescription" id="desc" rows="3" cols="30"/>
        <form:errors path="jobManualTest.testDescription" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td width="12%"><f:message key="quantity"/>: <span class="redstar">*</span></td>
      <td width="13%" align="right">
        <form:input path="jobManualTest.quantity" size="10" id="qty"/>
        <form:errors path="jobManualTest.quantity" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td width="12%"><f:message key="price"/>: <span class="redstar">*</span></td>
      <td width="13%" align="right">
        <form:input path="jobManualTest.totalPrice" size="10" id="price"/>
        <form:errors path="jobManualTest.totalPrice" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td colspan="8" style="padding:2px;">
        <table width="99%" border="0" cellpadding="0" cellspacing="0" class="secAppltable">
          <tr>
            <td>
              <input name="Submit3" type="button" class="button1" onclick="javascript:submitform('${command.test.testId }','${command.quantity }','${command.totalPrice }','submitAndReturn');" value="Submit and Return"/>      
              <input name="Submit3" type="button" class="button1" onclick="javascript:submitform('${command.test.testId}','${command.quantity }','${command.totalPrice }','submit');" value="Submit" />
              <input name="Submit3" type="button" class="button1" onclick="javascript:submitform('${command.test.testId}','${command.quantity }','${command.totalPrice }','return');" value="Return" />      
            </td>
          </tr>
        </table>    
      </td>
    </tr>
  </table></form:form>






