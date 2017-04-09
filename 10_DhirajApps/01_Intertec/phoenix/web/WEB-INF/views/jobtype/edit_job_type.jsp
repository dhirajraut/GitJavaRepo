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

  
function onOperationAdd(){
document.jobTypeEditForm.addOrDeleteJobType.value = "add";
document.jobTypeEditForm.oxcel.value="false";
document.jobTypeEditForm.submit();
}

function onOperationDelete(index){
document.jobTypeEditForm.addOrDeleteJobType.value = "delete";
document.jobTypeEditForm.jobTypeIndex.value = index;
document.jobTypeEditForm.oxcel.value="false";
document.jobTypeEditForm.submit();
}

function setflag(rowIndex){
//document.jobTypeEditForm.operationFlag.value="newval";
document.jobTypeEditForm.oxcel.value="false";
document.jobTypeEditForm.rowNum.value=rowIndex;
}

function subform(){
document.jobTypeEditForm.oxcel.value="false";
document.jobTypeEditForm.submit();
}

function updateOperationIframsrc(index){
document.jobTypeEditForm.oxcel.value="false";
document.getElementById('searchOperationFr'+index).setAttribute("src","search_operation_popup.htm?inputFieldId=operations["+index+"].operationCode&searchForm=jobTypeEditForm&rowNum="+index);
}

function submitxcel(){
document.jobTypeEditForm.oxcel.value="true";
document.jobTypeEditForm.submit();
}

</script>
</head>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<form:form name="jobTypeEditForm" method="POST" action="edit_job_type.htm">
<div style="color:red;"><form:errors cssClass="error"/></div>
<form:hidden path="addOrDeleteJobType"/>
 <form:hidden path="jobTypeIndex"/>
 <form:hidden path="jobTypeCount"/>
 <form:hidden path="rowNum" />
 <form:hidden path="operationFlag"/>
 <form:hidden path="searchForm"/>
 <input type="hidden" name="oxcel" value="false"/>
<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top"> 
<!----------------------------------------------MAIN CONTAINER----------------------------------------------------->
      <div id="MainContentContainer">
<!--------------------------------------------TABS CONTENTS-------------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="operation"/></span></a></li>
</ul>
</div>
<!-----------------------------------Sub Menus container. Do not remove-------------------------------------------->
 <div id="tab_inner"> 
<!-----------------------------------------------------TAB1 CONTAINER---------------------------------------------->
<div id="tab1" class="innercontent" >

<table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
<tbody>
<tr bgcolor=#ffffff>
<th width="50%"><f:message key="jobTypeOperation"/></th>
<th>&nbsp;</th>
<th width="10%" bgcolor="#ffffff" style="text-align:right"><a href="#start"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a></th>
</tr>
<tr bgcolor=#ffffff>
<td class="TDShade"><f:message key="jobType"/>: &nbsp;&nbsp;<span class="TDShadeBlue"> ${command.jobType.jobTypeCode}</td>
<td class="TDShade" ><f:message key="description"/>:<span class="TDShadeBlue">
<form:input cssClass="inputBox" size="35" maxlength="60" path="jobType.jobTypeDesc" disabled="true"/>
<form:errors path="jobType.jobTypeDesc" cssClass="redstar"/>

</span></td>
<td bgcolor="#ffffff" class="TDShade" style="text-align:right">&nbsp;</td>
</tr>

<tr bgcolor=#ffffff>
<td colspan="3" class="TDShade" style="padding:2px;">
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">

  <tr>
  <th ><f:message key="no"/>.</th>
  <th width="45%"><f:message key="operation"/></th>
  <th width="45%"><f:message key="operationDescription"/></th>
<th ><f:message key="status"/></th>
  <th width="50%"  style="text-align:right" colspan="2">
  <img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onOperationAdd()"/>&nbsp;&nbsp;</th>
  </tr>
          
 <icb:list var="activeStatusDropDownItems" items="${icbfn:dropdown('staticDropdown',activeStatus)}" />
<c:forEach items="${command.operations}" var="operations" varStatus="counter">
<tr>
<td class="TDShadeBlue" >${counter.index+1}<strong>.</strong></td>    

<td class="TDShadeBlue" width="45%">
<form:input cssClass="inputBox" size="10" maxlength="10" path="operations[${counter.index}].operationCode"/>
<form:errors path="operations[${counter.index}].operationCode" cssClass="redstar"/>
<a href="#" onClick="javascript:setflag(${counter.index});updateOperationIframsrc(${counter.index});
popup_show('operationlist${counter.index}', 'operationlist_drag${counter.index}', 'operationlist_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();">
<img src=" images/lookup.gif" alt="lookup Operation" width="13" height="13" border="0"/></a></td>

<td class="TDShadeBlue" width="45%"><form:input cssClass="inputBox" size="30" maxlength="30" path="operations[${counter.index}].description"/>
<form:errors path="operations[${counter.index}].description" cssClass="redstar"/></td> 
<td class="TDShadeBlue" width="30%"><form:select cssClass="selectionBox" id ="sel1"  path="operations[${counter.index}].status" items="${activeStatusDropDownItems}" itemLabel="name" itemValue="value"/></td>

<td class="TDShadeBlue" style="text-align:right;" width="50%"><div id="div3" style="width:50px; text-align:center; margin-right:0;">    &nbsp;
<img src=" images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"  onclick="onOperationDelete('${counter.index}')"/></a></div></td>
</tr>


<ajax:autocomplete
baseUrl="joborder.htm"
source="operations[${counter.index}].operationCode"
target="operations[${counter.index}].description"
className="autocomplete"
parameters="operationName={operations[${counter.index}].operationCode}"
minimumCharacters="1"
/>
<!----------------------------------------- Operation List Lookup ------------------------------------------------->
<div class="sample_popup" id="operationlist${counter.index}" style="visibility: hidden;display:none;">
<div class="menu_form_header" id="operationlist_drag${counter.index}" style="width:750px;height:auto; "> 
<img class="menu_form_exit"   id="operationlist_exit${counter.index}" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;<f:message key="operations"/></div><div class="menu_form_body" id="operationlistbody${counter.index}"  style="width:750px; height:520px;overflow-y:hidden;"><iframe align="left" frameborder="0" style="padding:0px;" height="520px;" width="100%" scrolling="auto" id="searchOperationFr${counter.index}" name="searchOperationFr${counter.index}" allowtransparency="yes" src="blank.html"></iframe>   
</div></div>
<!-------------------------------------------Operation List Lookup END--------------------------------------------->
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
<td style="text-align:right;"><a href="#start"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a>
<a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="subform()"/></a></td>
</tr>
</table>
</td>
</tr>
</table>
</div>
<!---------------------------------------------TAB1 CONTAINER END-------------------------------------------------->
</div>
</div>
<script type="text/javascript">
dolphintabs.init("tabnav", 0)
</script>
<!----------------------------------------------TAB CONTENT END---------------------------------------------------->
 </div>
<!----------------------------------------- MAIN CONTAINER END ---------------------------------------------------->
</td>
</tr>
</table>
</form:form>





