<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script	type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript"	src="js/ce/common.js"></script>



<icb:list var="billingStatus">
  <icb:item>billingStatus</icb:item>
</icb:list>

<icb:list var="operationalStatus">
  <icb:item>operationalStatus</icb:item>
</icb:list>

<icb:list var="serviceType">
  <icb:item>serviceType</icb:item>
</icb:list>


<icb:list var="workerFunction">
  <icb:item>workerFunction</icb:item>
</icb:list>

<icb:list var="workFunction">
   <icb:item>workFunction</icb:item>
</icb:list>

<form:form name="jobInstructionTestAttribForm" method="POST" action="phx_job_instruction_test_attribute_popup.htm">

<form:hidden path="workertimeFlag"/>
<form:hidden path="divName"/>
<form:hidden path="divNameBody"/>
<form:hidden path="rowNum"/>  
<form:hidden path="lineItemNo"/>

      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
          <td><strong><f:message key="startDate"/> </strong></td>
          <td>         
          <form:input id="cejobOrderLineItem.period.from" cssClass="inputBox" path="cejobOrderLineItem.period.from" size="30" maxlength="12"/>
          <form:errors path="cejobOrderLineItem.period.from" cssClass="redstar"/>
				<a href="#s"	onClick="displayCalendar(document.getElementById('cejobOrderLineItem.period.from'),'${command.dateFormat}',this)">
				   <img	src=" images/calendar.gif" width="15" height="17" align="absmiddle"	border="0"/></a></td>
        </tr>
		<tr>
          <td><strong><f:message key="endDate"/>:</strong></td>
          <td>
          <form:input id="cejobOrderLineItem.period.to" cssClass="inputBox" path="cejobOrderLineItem.period.to" size="30" maxlength="12"/>
          <form:errors path="cejobOrderLineItem.period.to" cssClass="redstar"/>
				<a href="#e"	onClick="displayCalendar(document.getElementById('cejobOrderLineItem.period.to'),'${command.dateFormat}',this)">
				   <img	src=" images/calendar.gif" width="15" height="17" align="absmiddle"	border="0"/></a></td>
        </tr>
		<tr>
          <td><strong><f:message key="tastReadyDate"/>:</strong></td>
          <td>
          <form:input id="taskReadyDate" cssClass="inputBox" path="cejobOrderLineItem.taskReadyDate" size="30" maxlength="12"/>
          <form:errors path="cejobOrderLineItem.taskReadyDate" cssClass="redstar"/>
				<a href="#t"	onClick="displayCalendar(document.getElementById('cejobOrderLineItem.taskReadyDate'),'${command.dateFormat}',this)">
				   <img	src=" images/calendar.gif" width="15" height="17" align="absmiddle"	border="0"/></a></td>
        </tr>
		<tr>
          <td><strong><f:message key="wareHouse"/>:</strong></td>
          <td>
		  <form:input id="cejobOrderLineItem.warehouseId" cssClass="inputBox" path="cejobOrderLineItem.warehouseId" size="30" maxlength="12"/>
 		  <form:errors path="cejobOrderLineItem.warehouseId" cssClass="redstar"/>
 		  <a href="#w" onClick="javascript:searchPopups('branchid','cejobOrderLineItem.warehouseId')"><img	src=" images/lookup.gif" alt="Lookup Branch" width="13"	height="13"	border="0" /></a>
		 </tr>		



        <tr>
          <td><strong><f:message key="sampleDescription"/>:</strong></td>
          <td>
          <form:input id="description" cssClass="inputBox" path="cejobOrderLineItem.sampleDescription" size="25" maxlength="150"/>
          <form:errors path="cejobOrderLineItem.sampleDescription" cssClass="redstar"/>
          </td>
        </tr>

        <tr>
          <td><strong><f:message key="serviceType"/>:</strong></td>
          <td><form:select cssClass="selectionBox" id="operationalStatus" path="cejobOrderLineItem.serviceType.type" items="${icbfn:dropdown('staticDropdown',serviceType)}" itemLabel="name"  itemValue="value"/>
          <form:errors path="cejobOrderLineItem.serviceType.type" cssClass="redstar"/>
          </td>
        </tr>

		<tr>
          <td><strong><f:message key="serviceLocation"/>:</strong></td>
             <td><form:input id="serviceLocationID" cssClass="inputBox" path="cejobOrderLineItem.serviceLocationID" size="25" maxlength="30"/>
               <form:errors path="cejobOrderLineItem.serviceLocationID" cssClass="redstar"/>
                <a href="#s"	 onClick="javascript:searchPopups('servicelocation','cejobOrderLineItem.serviceLocationID');"><img src="images/lookup.gif" id="lookup"	width="13" height="13" border="0" /></a></td>
        </tr>
		
		<tr>
          <td><strong><f:message key="po"/>:</strong></td>
          <td><form:input id="purchaseOrderId" cssClass="inputBox" path="cejobOrderLineItem.purchaseOrderId" size="25" maxlength="20"/>
			<form:errors path="cejobOrderLineItem.purchaseOrderId" cssClass="redstar"/>	
                <a href="#p" onClick="javascript:javascript:searchPopups('ponumber','cejobOrderLineItem.purchaseOrderId');">
				<img src="images/lookup.gif" alt="lookup Sales	Rep" width="13"	height="13"	border="0"></a>
		  </td>
        </tr>

		<tr>
          <td><strong><f:message key="fundedAmount"/>:</strong></td>
          <td><form:input id="fundedAmount" cssClass="inputBox" path="cejobOrderLineItem.fundedAmount" size="25" maxlength="20"/>
          <form:errors path="cejobOrderLineItem.fundedAmount" cssClass="redstar"/>
        </tr>
		
		<tr>
          <td><strong><f:message key="billingStatus"/>:</strong></td>
            <td>
				<form:select cssClass="selectionBox" id="cejobOrderLineItem.billingStatusId" path="cejobOrderLineItem.billingStatusId" items="${icbfn:dropdown('billingStatus',null)}" itemLabel="name"  itemValue="value"/>
				 <form:errors	path="cejobOrderLineItem.billingStatusId" cssClass="redstar" />&nbsp;           
           </td>
        </tr>

		<tr>
          <td><strong><f:message key="operationalStatus"/>:</strong></td>
            <td>
				<form:select cssClass="selectionBox" id="cejobOrderLineItem.operationalStatus" path="cejobOrderLineItem.operationalStatus" items="${icbfn:dropdown('staticDropdown',operationalStatus)}" itemLabel="name"  itemValue="value"/>
				<form:errors	path="cejobOrderLineItem.operationalStatus" cssClass="redstar" />&nbsp;     
            </td>
        </tr>
        
		<c:forEach items="${command.timeCostEstimations}" var="workitems" varStatus="counter">        
		<tr>
          <td><strong><f:message key="estimateHours"/>:</strong></td>
          <td>
          <form:select cssClass="selectionBox" id="timeCostEstimations[${counter.index}].employee.jobCode" path="timeCostEstimations[${counter.index}].employee.jobCode"    items="${icbfn:dropdown('staticDropdown',workFunction)}" itemLabel="name"
			itemValue="value"/>
                &nbsp;
                <form:input id="timeCostEstimations[${counter.index}].time" cssClass="inputBox" path="timeCostEstimations[${counter.index}].time" size="10" maxlength="10"/>
                                <a href="#a" onClick="javascript:doSubmit('add',${counter.index})"><img src="images/add.gif" alt="Add" width="13" height="12" hspace="1px" border="0"/></a> 
				<a href="#d" onClick="javascript:doSubmit('delete',${counter.index})"><img src="images/delete.gif" alt="Delete" width="13" height="12" hspace="1px" border="0"/></a> 
		  </td>
        </tr>
        </c:forEach>
		<tr>
          <td><strong><f:message key="taskManager"/>:</strong></td>
             <td><form:input id="cejobOrderLineItem.taskManagerId" cssClass="inputBox" path="cejobOrderLineItem.taskManagerId" size="25" maxlength="30"/>
                  <a href="#t" onClick="javascript:javascript:searchPopups('taskmanage','cejobOrderLineItem.taskManagerId');">
					<img src="images/lookup.gif" alt="lookup Sales	Rep" width="13"	height="13"	border="0"></a></td>
        </tr>

		<tr>
          <td><strong><f:message key="creditoverrideby"/>:</strong></td>
             <td><form:input id="creditOverrideBy" cssClass="inputBox" path="cejobOrderLineItem.creditOverrideBy" size="25" maxlength="30"/>
                  <a href="#t" onClick="javascript:javascript:searchPopups('taskmanage','cejobOrderLineItem.creditOverrideBy');">
					<img src="images/lookup.gif" alt="lookup Sales	Rep" width="13"	height="13"	border="0"></a></td>
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
		top.popupboxclose();
	} 
	</script>
	

</form:form>
