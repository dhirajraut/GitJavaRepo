<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script	type="text/javascript" src="js/ce/ce_services.js"></script>
<script	type="text/javascript" src="js/ce/ce_jobInstruction.js"></script>



<form:form name="jobInstructionTestAttribForm" method="POST" action="phx_job_instruction_test_attribute_popup.htm">

<form:hidden path="workertimeFlag"/>
<form:hidden path="divName"/>
<form:hidden path="divNameBody"/>
<form:hidden path="rowNum"/>  
<form:hidden path="lineItemNo"/>
<form:hidden path="splitIndex"/>
<form:hidden path="testIndex"/>
<form:hidden path="productIndex"/>
<form:hidden path="estimationId"/>
<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
          <td><strong><f:message key="startDate"/> </strong></td>
          <td>         
          <form:input id="startDate" cssClass="inputBox" path="startDate" size="30" maxlength="12"/>
          <form:errors path="startDate" cssClass="redstar"/>
				<a href="#s"	onClick="displayCalendar(document.getElementById('startDate'),'${command.dateFormat}',this)">
				   <img	src=" images/calendar.gif" width="15" height="17" align="absmiddle"	border="0"/></a></td>
        </tr>
		<tr>
          <td><strong><f:message key="endDate"/>:</strong></td>
          <td>
          <form:input id="endDate" cssClass="inputBox" path="endDate" size="30" maxlength="12"/>
          <form:errors path="endDate" cssClass="redstar"/>
				<a href="#e"	onClick="displayCalendar(document.getElementById('endDate'),'${command.dateFormat}',this)">
				   <img	src=" images/calendar.gif" width="15" height="17" align="absmiddle"	border="0"/></a></td>
        </tr>

		<tr>
          <td><strong><f:message key="tastReadyDate"/>:</strong></td>
          <td>
          <form:input id="taskReadyDate" cssClass="inputBox" path="taskReadyDate" size="30" maxlength="12"/>
          <form:errors path="taskReadyDate" cssClass="redstar"/>
				<a href="#t"	onClick="displayCalendar(document.getElementById('taskReadyDate'),'${command.dateFormat}',this)">
				   <img	src=" images/calendar.gif" width="15" height="17" align="absmiddle"	border="0"/></a></td>
        </tr>
		<tr>
          <td><strong><f:message key="branch"/>:</strong></td>
          <td>
		  <form:input id="warehouseName" cssClass="inputBoxBlue" path="warehouseName" size="30" maxlength="12"/>
 		  <form:errors path="warehouseName" cssClass="redstar"/>
 		  <a href="#w" onClick="javascript:searchPopupBranch('warehouseName','${command.buName}')"><img	src=" images/lookup.gif" alt="Lookup Operating Unit" width="13"	height="13"	border="0" /></a>
		 </tr>		
        <tr>
          <td><strong><f:message key="sampleDescription"/>:</strong></td>
          <td>
          <form:input id="sampleDescription" cssClass="inputBox" path="sampleDescription" size="25" maxlength="150"/>
          <form:errors path="sampleDescription" cssClass="redstar"/>
          </td>
        </tr>

        <tr>
          <td><strong><f:message key="modelNumber"/>:<span class="redstar">*</span></strong></td>
          <td>
          <form:input id="modelNumber" cssClass="inputBox" path="modelNumber" size="25" maxlength="150"/>
          <form:errors path="modelNumber" cssClass="redstar"/>
          </td>
        </tr>
		<tr>
          <td><strong><f:message key="serviceLocation"/>:<span class="redstar">*</span></strong></td>
             <td><form:input id="serviceLocationName" cssClass="inputBoxBlue" path="serviceLocationName" size="30" maxlength="30"/>
               <form:errors path="serviceLocationCode" cssClass="redstar"/>
               <form:hidden id="serviceLocationCode" path="serviceLocationCode" />
                <a href="#s"	 onClick="javascript:searchPopups('servicelocation','serviceLocationName|serviceLocationCode');"><img src="images/lookup.gif" id="lookup"	width="13" height="13" border="0" /></a></td>
        </tr>
        
		<tr>
          <td><strong><f:message key="serviceOffering"/>:<span class="redstar">*</span></strong></td>
            <td>
				<form:select cssClass="selectionBox" id="serviceOfferingId" path="serviceOfferingId" items="${command.serviceOfferingList}" itemLabel="name"  itemValue="value"/>
				 <form:errors	path="serviceOfferingId" cssClass="redstar" />
				&nbsp;     
            </td>
        </tr>
        
        
		<tr>
          <td><strong><f:message key="po"/>:</strong></td>
          <td><form:input id="purchaseOrderNo" cssClass="inputBoxBlue" path="purchaseOrderNo" size="25" maxlength="20"/>
			<form:errors path="purchaseOrderNo" cssClass="redstar"/>	
                <a href="#p" onClick="javascript:javascript:searchPopups('ponumber','purchaseOrderNo');">
				<img src="images/lookup.gif" alt="Lookup P.O. #" width="13"	height="13"	border="0"></a>
		  </td>
        </tr>
		<tr>
          <td><strong><f:message key="customerPoAmount"/>:</strong></td>
          <td><form:input id="fundedAmount" cssClass="inputBox" path="fundedAmount" size="25" maxlength="20"/>
          <form:errors path="fundedAmount" cssClass="redstar"/>
        </tr>
		
		<tr>
          <td><strong><f:message key="billingStatus"/>:</strong></td>
            <td>
				<form:select cssClass="selectionBox" id="billingstatus" path="billingstatus" items="${command.billingStatusList}" itemLabel="name"  itemValue="value"/>
				 <form:errors	path="billingstatus" cssClass="redstar" />&nbsp;           
           </td>
        </tr>

		<tr>
          <td><strong><f:message key="operationalStatus"/>:</strong></td>
            <td>
				<form:select cssClass="selectionBox" id="operationalStatus" path="operationalStatus" items="${command.operationalStatusList}" itemLabel="name"  itemValue="value"/>
				 <form:errors	path="operationalStatus" cssClass="redstar" />
				&nbsp;     
            </td>
        </tr>
        
		<c:forEach items="${command.estimation}" var="estimation" varStatus="counter">        
		<tr>
          <td><strong><f:message key="estimateHours"/>:</strong></td>
          <td>
          <form:select cssClass="selectionBox" id="estimation[${counter.index}].userTypeId" path="estimation[${counter.index}].userTypeId"    items="${command.userTypeList}" itemLabel="name"  itemValue="value"/>
          <form:errors	path="estimation[${counter.index}].userTypeId" cssClass="redstar" /> 
                &nbsp;
                <form:input id="estimation[${counter.index}].estimatedHour" cssClass="inputBox" path="estimation[${counter.index}].estimatedHour" size="10" maxlength="10"/>
                <form:errors	path="estimation[${counter.index}].estimatedHour" cssClass="redstar" />
                                <a href="#a" onClick="javascript:addEstimation()"><img src="images/add.gif" alt="Add" width="13" height="12" hspace="1px" border="0"/></a> 
				<a href="#d" onClick="javascript:deleteEstimation(${command.estimation[counter.index].id})"><img src="images/delete.gif" alt="Delete" width="13" height="12" hspace="1px" border="0"/></a> 
		  </td>
        </tr>
        </c:forEach>
		<tr>
          <td><strong><f:message key="taskManager"/>:</strong></td>
             <td><form:input id="taskManagerFullName" cssClass="inputBoxBlue" path="taskManagerFullName" size="25" maxlength="30"/>
                  <a href="#t" onClick="javascript:searchPopups('taskManagerLookup','taskManagerFullName|taskManagerId');">
					<img src="images/lookup.gif" alt="Lookup Task Manager asdf" width="13"	height="13"	border="0"></a>
				<form:hidden id="taskManagerId" path="taskManagerId" />
		</td>
        </tr>

		<tr>
          <td><strong><f:message key="creditoverrideby"/>:</strong></td>
             <td><form:input id="creditOverrideById" cssClass="inputBoxBlue" path="creditOverrideById" size="25" maxlength="30"/>
                  <a href="#t" onClick="javascript:javascript:searchPopups('taskmanage','creditOverrideById');">
					<img src="images/lookup.gif" alt="Lookup Credit Override By" width="13"	height="13"	border="0"></a></td>
        </tr>

        
		<tr>
          <td colspan="2">
             <input name="Button" type="button" class="button1" value="Submit" onClick="javascript:save()"/>
            &nbsp;&nbsp;
            <input id="cancel" type="button" value="Cancel" name="cancel" class="button1"	   onClick="javascript:top.hidePopupDiv('${command.divName}','${command.divNameBody}');top.popupboxclose();" />
		  </td>
       </tr>
      </table>
      
	  <!--Search Results -->
 
	
	<script>
	if(document.forms[0].workertimeFlag.value=='close')
	{
		top.hidePopupDiv('${command.divName}','${command.divNameBody}');
		//top.saveTestAttrib(document.forms[0].productIndex.value, document.forms[0].testIndex.value)		
		top.document.forms[0].submit();
		top.popupboxclose();
	} 
	</script>
	
</form:form>

<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="warehouseName" 
	target="warehouseName" 
	className="autocomplete" 
	parameters="entity=com.intertek.entity.Branch,textAttribute=name,valueAttribute=name,~buName=${command.buName},~name={warehouseName}"
	postFunction="updateBranchAndInstrServiceLoc"
	minimumCharacters="3" />
	
	
<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="taskManagerId" 
	target="taskManagerId" 
	className="autocomplete" 
	parameters="entity=com.intertek.entity.User,textAttribute=loginName,valueAttribute=loginName,~loginName={taskManagerId}"
	minimumCharacters="3" />
	
<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="taskManagerFullName" 
	target="taskManagerFullName" 
	className="autocomplete" 
	parameters="entity=com.intertek.phoenix.entity.Employee,entityWrapper=com.intertek.phoenix.ajax.EmployeeAjaxWrapper,~firstName={taskManagerFullName}"
	minimumCharacters="3" 
	postFunction="taskManagerPostAjax"/>				

<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="creditOverrideById" 
	target="creditOverrideById" 
	className="autocomplete" 
	parameters="entity=com.intertek.entity.User,textAttribute=loginName,valueAttribute=loginName,~loginName={creditOverrideById}"
	minimumCharacters="3" />	
	
<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="serviceLocationCode" 
	target="serviceLocationCode" 
	className="autocomplete" 
	parameters="entity=com.intertek.entity.ServiceLocation,textAttribute=serviceLocationCode,valueAttribute=serviceLocationCode,~serviceLocationCode={serviceLocationCode}"
	minimumCharacters="3" />
	
<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="purchaseOrderNo" 
	target="purchaseOrderNo" 
	className="autocomplete" 
	parameters="entity=com.intertek.phoenix.entity.PurchaseOrder,textAttribute=poNumber,valueAttribute=poNumber,~poNumber={purchaseOrderNo}"
	minimumCharacters="3" />		

<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="serviceLocationName" 
	target="serviceLocationName" 
	className="autocomplete" 
	parameters="entity=com.intertek.entity.ServiceLocation,entityWrapper=com.intertek.phoenix.ajax.ServiceLocationAjaxWrapper,valueAttribute=serviceLocationCode,~name={serviceLocationName}"
	minimumCharacters="3" 
	parser="new ResponseXmlParser()" 
	postFunction="updateInstrServiceLoc"/>	
	
	