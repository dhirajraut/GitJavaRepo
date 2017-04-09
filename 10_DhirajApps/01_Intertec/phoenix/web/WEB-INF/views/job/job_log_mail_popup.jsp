<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script language="javascript">
  function reDisplay(refreshing,emailIndex)
  {
    document.jobLogmailPopUpForm.refreshing.value=refreshing;
    document.jobLogmailPopUpForm.emailIndex.value=emailIndex;
    document.jobLogmailPopUpForm.submit();
  }  

function sendEmail(emailindex)
{
    document.jobLogmailPopUpForm.refreshing.value='sendEmail';
    document.jobLogmailPopUpForm.emailIndex.value=emailindex;
    document.jobLogmailPopUpForm.submit();
}
  
function showJobDetails(divCount)
{
	for(i=0;i<divCount;i++)
	{
		document.getElementById("details"+i).style.visibility = "visible"; 
    	document.getElementById("details"+i).style.display = "block";
    	
    }
    document.getElementById("bluarrowdownv").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv").style.visibility = "hidden";
}

function hideJobDetails(divCount)
{
	for(i=0;i<divCount;i++)
	{
		document.getElementById("details"+i).style.visibility = "hidden"; 
    	document.getElementById("details"+i).style.display = "none";
    	
    }
    document.getElementById("bluarrowdownv").style.visibility = "hidden"; 
    document.getElementById("bluarrowrightv").style.visibility = "visible";
}

   </script>
<form:form name="jobLogmailPopUpForm" method="POST" action="job_log_mail_popup.htm">	   
     <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
        <form:errors cssClass="error"/>
      </div>      
<input type="hidden" name="refreshing" value="true" />
<form:hidden path="emailIndex" />
 
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">  
  
  <tr>
    <th colspan="2" style="text-align:left" ><f:message key="jobOrderSummary" /></th>
    <th colspan="3">
    <c:forEach items="${command.jobEmails}" var="jobEmail" varStatus="emailStatus">
    <a href="#" onClick="javascript:reDisplay('showEmail',${emailStatus.index });">&nbsp;${emailStatus.index + 1}&nbsp; </a>
    </c:forEach>
    </th>
    
 
 			<tr><td colspan="5" class="TDShadeBlue">&nbsp;</td></tr>
           <tr><td colspan="2" class="TDShadeBlue">
           <f:message key="to" />:</td><td>&nbsp;&nbsp;
           <form:input cssClass="inputBox" path="jobEmails[${command.emailIndex}].to" size="60"/>
           </td>
           <td colspan="2"  class="TDShadeBlue">
           <f:message key="separateEmail" />&nbsp;&nbsp;
           </td>
           </tr>
           <tr><td colspan="5"  class="TDShadeBlue">&nbsp;</td></tr>
          <tr><td colspan="2" class="TDShadeBlue">
          <f:message key="cc" />:</td><td>&nbsp;&nbsp;
          <form:input cssClass="inputBox" path="jobEmails[${command.emailIndex}].cc" size="60"/>
          </td>
          <td colspan="2" class="TDShadeBlue">
           <f:message key="companyId" />:&nbsp;&nbsp;${command.jobEmails[command.emailIndex].custCode }
           </td>
          </tr>
          <tr><td colspan="5" class="TDShadeBlue">&nbsp;</td></tr>
          <tr><td colspan="2" class="TDShadeBlue">
          <f:message key="subject" />:</td><td>&nbsp;&nbsp;
          <form:input cssClass="inputBox" path="jobEmails[${command.emailIndex}].subject" size="60"/>
          </td>
          <td colspan="2" class="TDShadeBlue">
           <f:message key="schedulerId" />:&nbsp;&nbsp;${command.jobEmails[command.emailIndex].contactId }
           </td>
          </tr>
          <tr><td colspan="5" class="TDShadeBlue">&nbsp;</td></tr>
          <tr><td colspan="2" class="TDShadeBlue">
          <f:message key="coordinator" />:</td><td>&nbsp;&nbsp;
          <form:input cssClass="inputBox" path="jobEmails[${command.emailIndex}].coordinator" size="60"/>
          </td>
          <td colspan="2" class="TDShadeBlue">&nbsp;</td>
          </tr>
          <tr><td colspan="5" class="TDShadeBlue">&nbsp;</td></tr>
          <tr><td colspan="2" class="TDShadeBlue">
          <f:message key="introduction" />:</td><td>&nbsp;&nbsp;
          <form:textarea cols="60" cssClass="inputBox" rows="4" path="jobEmails[${command.emailIndex}].introduction"/>
          </td>
          <td colspan="2" class="TDShadeBlue">
          <a href="#" class="button1" style="text-decoration: none;color:black;" onclick="javascript:reDisplay('true',${command.emailIndex });">Redisplay</a>&nbsp;
          </a> 
           
           </td>
          </tr>
          <tr><td colspan="5" class="TDShadeBlue">&nbsp;</td></tr>
          
                     

</table>

<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">  
  
  <tr>
    <th class="TDShade" style="text-align:left" >
    <div id="bluarrowrightv" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> 
    <a href="#"> 
    <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4" style="margin-top:2px;" onClick="javascript:showJobDetails(${fn:length(command.jobEmails[command.emailIndex].addJobOrders)});"></a></div>
    
    <div id="bluarrowdownv" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> 
    <a href="#"> 
    <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"  onClick="javascript:hideJobDetails(${fn:length(command.jobEmails[command.emailIndex].addJobOrders)});"/></a></div></th>                            

    
    
    <th colspan="5" class="TDShade"><f:message key="jobs" /></th>
     
  </tr>
<c:forEach items="${command.jobEmails[command.emailIndex].addJobOrders}" var="addJobOrder" varStatus="addJobOrderStatus">
<tr>
<td colspan="5">
<div id="details${addJobOrderStatus.index }" style="visibility:hidden;display:none;">
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-top:0px; border-top:none">
<tr>
<td>
<f:message key="custRefNum" />:
</td>
<td>
&nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.custRefNum }
</td>
<td>
<f:message key="jobId" />:
</td>
<td>
&nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.jobNumber }
</td>
<td>
<f:message key="eta" />&nbsp;<f:message key="timeZone" />:
</td>
<td>
${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.etaTimeTz}
</td>
</tr>
<tr>
<td>
<f:message key="icbRef" />:
</td>
<td>
&nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.invoiceValue5 }
</td>
<td>
<f:message key="branchName" />:
</td>
<td>
&nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.branch.description }
</td>
<td>
<f:message key="timeZone" />:
</td>
<td>
&nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.nominationTimeTz}
</td>
</tr>
<tr>
<td>
<f:message key="operation" />:
</td>
<td>
&nbsp;${icbfn:operationDesc(command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.operation)}
</td>
<td>
<f:message key="serviceLocation" />:
</td>
<td>
&nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.serviceLocation.name}
</td>
<td>
<f:message key="port" />:
</td>
<td>
&nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.serviceLocation.city}
</td>
</tr>
<tr>
<td>
<f:message key="vessel" />:
</td>
<td>
&nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.vesselNames}
</td>
<td>
<f:message key="product" />:
</td>
<td>
&nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.productNames}
</td>
<td>
&nbsp;
</td>
<td>
&nbsp;
</td>
</tr>
<tr>
<td>
<f:message key="notes" />:
</td>
<td colspan="4">
<form:textarea cols="70" cssClass="inputBox" rows="4" path="jobEmails[${command.emailIndex}].addJobOrders[${addJobOrderStatus.index}].emailNote"/>
</td>
<td>
&nbsp;
</td>
</tr>
</table>
</div>

</td>
</tr>
</c:forEach>

  <tr><td colspan="5">&nbsp;</td></tr>
  
  
  <tr>
  <td colspan="5">${command.jobEmails[command.emailIndex].introductionToDisplay }</td></tr>
  <tr><td colspan="5">&nbsp;</td></tr>
  
  <c:forEach items="${command.jobEmails[command.emailIndex].addJobOrders}" var="addJobOrder" varStatus="addJobOrderStatus">

  <tr>

<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-top:0px; border-top:none">

<tr>
<td>
<table width="100%">
<tr>
<td>
<table border="1" cellspacing="0" cellpadding="4">
 <tr>
  <td nowrap>
  <f:message key="custRefNum" />:
  </td>
  <td nowrap>
  <c:forEach items="${addJobOrder.addJobContracts}" var="addJobContract" varStatus="addJobContractStatus">
  <c:if test="${addJobContractStatus.index==0}">
    &nbsp;${addJobContract.jobContract.custRefNum }
    </c:if>
    </c:forEach>
  </td>  
  </tr>
<tr>
  <td nowrap>
  <f:message key="jobId" />:
  </td>
  <td nowrap>
  &nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.jobNumber }
  </td>  
  </tr>
<tr>
  <td nowrap>
  <f:message key="icbRef" />:
  </td>
  <td nowrap>
  &nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.invoiceValue5 }
  </td>  
  </tr>  
<tr>
  <td nowrap>
  <f:message key="intertekOffice" />:
  </td>
  <td nowrap>
  &nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.branch.description }
  </td>  
  </tr>    
</table>
</td>

</tr>
<tr><td>&nbsp;</td></tr>
<tr>
<td>
<table border="1" cellspacing="0" cellpadding="4">
  <tr>
  <td nowrap>
  <f:message key="operation" />:
  </td>
  <td nowrap>
  &nbsp;${icbfn:operationDesc(command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.operation)}
  </td>
  </tr>
  <tr>
  <td nowrap>
  <f:message key="serviceLocation" />:
  </td>
  <td nowrap>
  &nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.serviceLocation.name}
  </td>
  </tr>  
  <tr>
  <td nowrap>
  <f:message key="port" />:
  </td>
  <td nowrap>
  &nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.serviceLocation.city}
  </td>
  </tr>  
  <tr>
  <td nowrap>
  <f:message key="vessel" />:
  </td>
  <td nowrap>
  &nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.vesselNames}
  </td>
  </tr>    
  <tr>
  <td nowrap>
  <f:message key="product" />:
  </td>
  <td nowrap>
  &nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.productNames}
  </td>
  </tr>      
  </table>
  </td>
</tr>
</table>
</td>

<td>
<table border="1" cellspacing="0" cellpadding="4">
<tr>
  <td nowrap>
  <f:message key="timeZone" />:
  </td>
  <td nowrap>
    &nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.nominationTimeTz} (ETA : ${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.etaTimeTz})
  </td>
  <td nowrap>
  <f:message key="time" />:
  </td>    
  </tr>
 <tr>
  <td nowrap>
  <f:message key="eta" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.etaDate}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].jobOrder.etaTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr>
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog!=null}">
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.arriveDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="arriveDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.arriveDt}" pattern="dd-MMM-yyyy"/>	
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.arriveTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>
 

  
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.dockDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="dockDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.dockDt}" pattern="dd-MMM-yyyy"/>	

  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.dockTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>
 


   <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.hoseOnDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="hoseOnDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.hoseOnDt}" pattern="dd-MMM-yyyy"/>	
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.hoseOnTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>  

  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.estStartDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="estStartDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.estStartDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.estStartTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>    

  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.commenceDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="commenceDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.commenceDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.commenceTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>      

  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.delayDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="delayDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.delayDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.delayTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>        

  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.delayEndDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="delayEndDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.delayEndDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.delayEndTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>     

   <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.estCompleteDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="estCompleteDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.estCompleteDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.estCompleteTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>      


  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.completeDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="completeDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.completeDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.completeTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>   
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.hoseOffDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="hoseOffDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.hoseOffDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.hoseOffTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>     
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.releaseDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="releasedDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.releaseDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.releaseTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>   
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.summaryDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="summarySent" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.summaryDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;
  </td>    
  </tr> 
  </c:if>
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.sampleReceiveDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="sampleReceivedDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.sampleReceiveDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.sampleReceiveTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.prelimReportDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="prelimReportDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.prelimReportDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.prelimReportTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.sampleShipDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="sampleShippedDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.sampleShipDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.sampleShipTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.finalReportDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="finalReportDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.finalReportDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.finalReportTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>    
  <c:if test="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.distributeDt!=null}">
  <tr>
  <td nowrap>
  <f:message key="distributedDate" />:
  </td>
  <td nowrap>
    &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.distributeDt}" pattern="dd-MMM-yyyy"/>
  </td>
  <td nowrap>
  &nbsp;<f:formatDate value="${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].addJobContracts[0].jobContract.jobLog.distributeTime}" pattern="${command.timeFormat}"/>
  </td>    
  </tr> 
  </c:if>              
</c:if>
</table>
</td>
  
  </tr>





  
  <tr>
  <td colspan="5">&nbsp;
  </td>
  </tr>  
  <tr>
  <td colspan="5">&nbsp;
  <f:message key="notes" />:<br>&nbsp;${command.jobEmails[command.emailIndex].addJobOrders[addJobOrderStatus.index].emailNoteDisplay}
  </td>
</tr>

</c:forEach>

  <tr>
  <td colspan="5">&nbsp;
  </td>
  </tr>
  <tr>
  <td colspan="5">&nbsp; <f:message key="sincerely" />,
  </td>
  </tr>
  <tr>
  <td colspan="5">&nbsp; ${command.jobEmails[command.emailIndex].coordinator}
  </td>
  </tr>
  <tr>
  <td colspan="5">&nbsp; 
  </td>
  </tr> 
  <tr>
  <td colspan="5">&nbsp;
  <c:choose>
  <c:when test="${command.jobEmails[command.emailIndex].emailSentFlag}">
	<input name="button1" type="button" class="button1" value="Send Email" disabled>
  </c:when>
  <c:otherwise>
 <a href="#" class="button1" style="text-decoration: none;color:black;" onclick="javascript:sendEmail(${command.emailIndex });"><f:message key="sendEmail" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </a>
  </c:otherwise> 
  </c:choose>
 
  <a href="#" class="button1" style="text-decoration: none;color:black;" onClick="javascript:top.hidePopupDiv('email','emailbody');top.popupboxclose();" ><f:message key="return" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
  </a>
  </td>
  </tr> 
 <tr>
  <td colspan="5">&nbsp; 
  </td>
  </tr> 
 <tr>
  <td colspan="5">&nbsp; 
  </td>
  </tr>        
</table>    
<!-- --------------------------------- Branch Allocation Lookup END ----------------------------------------- -->


</form:form>