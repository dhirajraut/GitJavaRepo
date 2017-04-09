<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<head>

<script language="javascript">

  
function onEventAdd(){
document.OperationEventForm.addOrDeleteEvent.value = "add";
document.OperationEventForm.submit();
}

function onEventDelete(index){
document.OperationEventForm.addOrDeleteEvent.value = "delete";
document.OperationEventForm.eventIndex.value = index;
document.OperationEventForm.submit();
}

function setflag(rowIndex){
//document.OperationEventForm.eventFlag.value="newval";
document.OperationEventForm.rowNum.value=rowIndex;
}

function subform(){
document.OperationEventForm.submit();
}

function updateEventIframsrc(index){
document.getElementById('searchEventFr'+index).setAttribute("src","search_event_popup.htm?inputFieldId=events["+index+"].eventCode&searchForm=OperationEventForm&rowNum="+index);
}
</script>
</head>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<form:form name="OperationEventForm" method="POST" action="create_operation_event.htm">
<div style="color:red;"><form:errors cssClass="error"/></div>
<form:hidden path="addOrDeleteEvent"/>
 <form:hidden path="eventIndex"/>
 <form:hidden path="eventCount"/>
 <form:hidden path="rowNum" />
 <form:hidden path="eventFlag"/>
 <form:hidden path="searchForm"/>
<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
<td valign="top"> 
<!---------------------------------------------------MAIN CONTAINER------------------------------------------------>
<div id="MainContentContainer">
<!--------------------------------------------------TABS CONTENTS-------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="operation"/></span></a></li>
</ul>
</div>
<!------------------------------------------------Sub Menus container.Do not remove-------------------------------->
 <div id="tab_inner"> 
<!-------------------------------------------------TAB1 CONTAINER-------------------------------------------------->
<div id="tab1" class="innercontent" >

<table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable >
<tbody>
<tr bgcolor=#ffffff>
<th width="20%"><f:message key="operationEvent"/></th>
<th width="25%" >&nbsp;</th>
<th width="15%" bgcolor="#ffffff" style="text-align:right"><a href="search_operations.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a></th>
</tr>

<tr bgcolor=#ffffff >

<td class="TDShade"><f:message key="operation"/>:&nbsp;&nbsp;<span class="TDShadeBlue"> ${command.operation.operationCode}</span></td>

<td class="TDShade" ><f:message key="description"/>:<span class="TDShadeBlue">
<form:input cssClass="inputBox" size="35" maxlength="60" path="operation.description" disabled="true" />
<form:errors path="operation.description" cssClass="redstar"/>
</span></td>

<td bgcolor="#ffffff" class="TDShade"><f:message key="status"/>:<form:select cssClass="selectionBox" id ="sel1"  path="operation.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" disabled="true"/></td>
</tr>

<tr bgcolor=#ffffff>
<td colspan="5" class="TDShade" style="padding:2px;">
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" border="0">

  <tr>
  <th width="15%" ><f:message key="no"/>.</th>
  <th width="20%" style="text-align:center;">&nbsp;&nbsp;<f:message key="event"/></th>
  <th width="30%" style="text-align:center;"><f:message key="eventName"/></th>
  <th width="40%" style="text-align:center;"><f:message key="eventInstruction"/></th>
    <th width="25%" style="text-align:center;"><f:message key="status"/></th>
  <th width="50%"  style="text-align:right" colspan="2">
  <img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onEventAdd()"/>&nbsp;&nbsp;</th>
  </tr>
          
 <icb:list var="activeStatusDropDownItems" items="${icbfn:dropdown('staticDropdown',activeStatus)}" />
<c:forEach items="${command.events}" var="events" varStatus="counter">
<tr>
<td class="TDShadeBlue" width="15%" align="center">${counter.index+1}<strong>.</strong></td>    

<td class="TDShadeBlue"  style="text-align:center;" width="20%">
<form:input cssClass="inputBox" size="10" maxlength="10" path="events[${counter.index}].eventCode"/>
<form:errors path="events[${counter.index}].eventCode" cssClass="redstar"/>
<a href="#" onClick="javascript:setflag(${counter.index});updateEventIframsrc(${counter.index});
popup_show('eventlist${counter.index}', 'eventlist_drag${counter.index}', 'eventlist_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();">
<img src=" images/lookup.gif" alt="lookup event" width="13" height="13" border="0"/></a></td>

<td class="TDShadeBlue"  style="text-align:center;"width="30%" ><form:input cssClass="inputBox" size="30" maxlength="30" path="events[${counter.index}].eventName"/>
<form:errors path="events[${counter.index}].eventName" cssClass="redstar"/></td> 


<td class="TDShadeBlue" style="text-align:center;" width="40%"><form:input cssClass="inputBox" size="30" maxlength="200" path="events[${counter.index}].eventInstruction"/>
<form:errors path="events[${counter.index}].eventInstruction" cssClass="redstar"/></td> 



<td class="TDShadeBlue"  style="text-align:center;" widht="25%"><form:select cssClass="selectionBox" id ="sel1"  path="events[${counter.index}].status" items="${activeStatusDropDownItems}" itemLabel="name" itemValue="value"/></td>

<td class="TDShadeBlue" style="text-align:right;" width="50%"><div id="div3" style="width:50px; text-align:center; margin-right:0;">    &nbsp;
<img src=" images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0"  onclick="onEventDelete('${counter.index}')"/></a></div></td>
</tr>

<!--------------------------------------------Event List Lookup---------------------------------------------------->
<div class="sample_popup" id="eventlist${counter.index}" style="visibility: hidden;display:none;">
<div class="menu_form_header" id="eventlist_drag${counter.index}" style="width:750px;height:auto; "> 
<img class="menu_form_exit"   id="eventlist_exit${counter.index}" src=" images/form_exit.png" />&nbsp;&nbsp;&nbsp;<f:message key="events"/></div><div class="menu_form_body" id="eventlistbody${counter.index}"  style="width:750px; height:520px;overflow-y:hidden;"><iframe align="left" frameborder="0" style="padding:0px;" height="520px;" width="100%" scrolling="auto" id="searchEventFr${counter.index}" name="searchEventFr${counter.index}" allowtransparency="yes" src="blank.html"></iframe>  
</div></div>
<!--------------------------------------------Event List Lookup End------------------------------------------------>
</c:forEach>  
</table>
</td>
</tr>
</tbody>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>&nbsp;</td>
<td style="text-align:right"><a href="search_operations.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="subform()"/></a></td>
</tr>
</table>
</td>
</tr>
</table>
</div>
<!-------------------------------------------------TAB1 CONTAINER END---------------------------------------------->
</div>
</div>
<script type="text/javascript">
dolphintabs.init("tabnav", 0)
</script>
<!---------------------------------------------------TAB CONTENT END----------------------------------------------->
 </div>-
<!---------------------------------------------------MAIN CONTAINER END-------------------------------------------->
</td>
</tr>
</table>
</form:form>





