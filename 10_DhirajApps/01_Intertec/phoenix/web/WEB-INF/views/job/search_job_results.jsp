<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script>
Tooltip.offX = 4;  
Tooltip.offY = 4;
Tooltip.followMouse = false;  // must be turned off for hover-tip


function doTooltip(e, msg) {
  if ( typeof Tooltip == "undefined" || !Tooltip.ready ) return;
  Tooltip.clearTimer();
  var tip = document.getElementById? document.getElementById(Tooltip.tipID): null;
  if ( tip && tip.onmouseout == null ) {
      tip.onmouseout = Tooltip.tipOutCheck;
      tip.onmouseover = Tooltip.clearTimer;
  }
  Tooltip.show(e, msg);
}

function hideTip() {
  if ( typeof Tooltip == "undefined" || !Tooltip.ready ) return;
  Tooltip.timerId = setTimeout("Tooltip.hide()", 300);
}

Tooltip.tipOutCheck = function(e) {
  e = dw_event.DOMit(e);
  // is element moused into contained by tooltip?
  var toEl = e.relatedTarget? e.relatedTarget: e.toElement;
  if ( this != toEl && !contained(toEl, this) ) Tooltip.hide();
}

// returns true of oNode is contained by oCont (container)
function contained(oNode, oCont) {
  if (!oNode) return; // in case alt-tab away while hovering (prevent error)
  while ( oNode = oNode.parentNode ) if ( oNode == oCont ) return true;
  return false;
}

Tooltip.timerId = 0;
Tooltip.clearTimer = function() {
  if (Tooltip.timerId) { clearTimeout(Tooltip.timerId); Tooltip.timerId = 0; }
}

Tooltip.unHookHover = function () {
    var tip = document.getElementById? document.getElementById(Tooltip.tipID): null;
    if (tip) {
        tip.onmouseover = null; 
        tip.onmouseout = null;
        tip = null;
    }
}

dw_event.add(window, "unload", Tooltip.unHookHover, true);
 function sortByJobSearchHeader(orderBy){
document.jobSearchForm.pageNumber.value = "1";
document.jobSearchForm.sortFlag.value = orderBy;
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.refreshing.value="false";
document.jobSearchForm.columnFlag.value=orderBy;
top.document.jobSearchForm.submit();
}
function submitForm()
{
document.jobSearchForm.reqFormFlag.value="jobSearch";
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.refreshing.value="true";
top.document.jobSearchForm.submit();
}
function submitSearch(pageNumber)
{  
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.reqFormFlag.value="jobSearch";
//var w = document.jobSearchForm.goto.selectedIndex;
//var page=document.jobSearchForm.goto.options[w].text;
document.jobSearchForm.pageNumber.value = pageNumber;
// START : #119240
document.jobSearchForm.checkPageSort.value = "true";
// END : #119240
document.jobSearchForm.submit();
} 
function submitxcel()
{
document.jobSearchForm.jxcel.value="true";
document.jobSearchForm.reqFormFlag.value="jobSearch";
top.document.jobSearchForm.submit();
}

function submitmail()
{
document.jobSearchForm.reqFormFlag.value="jobSearch";
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.refreshing.value="false";
document.jobSearchForm.jmail.value="true";
top.document.jobSearchForm.submit();
}

function prevSearch(pageNumber)
{
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.reqFormFlag.value="jobSearch";
document.jobSearchForm.pageNumber.value = pageNumber.value;
document.jobSearchForm.submit();
}

function lastSearch(totalRecords,numInPages)
{
document.jobSearchForm.jxcel.value="false";
var pageNumber;
var quotient=totalRecords/numInPages;
var remainder=totalRecords%numInPages;

if(remainder==0)
pageNumber=quotient;
else
pageNumber=Math.floor(quotient)+1;
document.jobSearchForm.reqFormFlag.value="jobSearch";
document.jobSearchForm.pageNumber.value =pageNumber;
document.jobSearchForm.submit();
}

function nextSearch(pageNumber)
{
  
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.reqFormFlag.value="jobSearch";
var b=++pageNumber;
document.jobSearchForm.pageNumber.value = b;
document.jobSearchForm.submit();
}

function previousSearch(pageNumber)
{
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.reqFormFlag.value="jobSearch";
var b=--pageNumber;
document.jobSearchForm.pageNumber.value = b;
document.jobSearchForm.submit();
}

function updateFromJobIdIframeSrc()
{
  var buName= document.getElementById("sel20").value;
  var branchName=document.getElementById("brnch").value;
  var fromJobId= document.getElementById("fromjobid").value;
  document.getElementById('fromJobIds').setAttribute("src","search_jobid_popup.htm?inputFieldId=fromJobId.value&div1=searchfromjobid&div2=searchfromjobidbody&buName="+buName+"&branchName="+branchName+"&jobId="+fromJobId);
}

function updateBranchIframeSrc()
  {
  var buName= document.getElementById("sel20").value;
  if(buName!= "" && buName!= null)
  {
    document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId=branch.value&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=jobsForm");
  
  }
  
}

function updateCreatedByIframeSrc(){
    document.getElementById('searchCreatedFr').setAttribute("src","search_user_popup.htm?inputFieldId=createdBy.value&div1=createdby&div2=createdbybody");
}


function updateModifiedByIframeSrc(){
  document.getElementById('searchModifiedFr').setAttribute("src","search_user_popup.htm?inputFieldId=modifiedBy.value&div1=modifiedby&div2=modifiedbybody");
}


function makeBranchblank()
{
  document.getElementById("brnch").value="";
}
function updateToJobIdIframeSrc()
{
  var buName= document.getElementById("sel20").value;
  var branchName=document.getElementById("brnch").value;
  var toJobId= document.getElementById("tojobid").value;
  document.getElementById('toJobIds').setAttribute("src","search_jobid_popup.htm?inputFieldId=toJobId.value&div1=searchtojobid&div2=searchtojobidbody&buName="+buName+"&branchName="+branchName+"&jobId="+toJobId);
  
}


function checkFromTO()
{
document.jobSearchForm.pageNumber.value = "1";  
var status= document.getElementById("sel3").value;
var fromJobId= document.getElementById("fromjobid").value;
var toJobId= document.getElementById("tojobid").value;
var fromDate= document.getElementById("fdate").value;
var toDate= document.getElementById("tdate").value;
  var branchCode=document.getElementById("brnch").value;
  document.jobSearchForm.reqFormFlag.value="jobSearch";
  document.jobSearchForm.jxcel.value="false";
  document.jobSearchForm.refreshing.value="false";
  document.jobSearchForm.jmail.value="false";
  branchCode = new String(branchCode)
  branchCode = branchCode.toUpperCase()
  fromJobId = new String(fromJobId)
    fromJobId = fromJobId.toUpperCase()
  toJobId = new String(toJobId)
  toJobId = toJobId.toUpperCase()
  if(status==2 || status==3)
  {
    if((fromJobId=="" && toJobId=="") && (fromDate=="" && toDate==""))
    {
    confirm("select either From/To Date fields or From/To Job Id fields");
    }
    if(branchCode!="" && fromJobId!="" && toJobId!="")
    document.jobSearchForm.submit();
  }else{

    if(fromJobId!="" && toJobId!="")
      {
        if(branchCode=="")
        {
        confirm("Please Select Job Associated Branch Code");
        }
      var hasJobid="";
      if(branchCode!="" && (fromJobId!="" || toJobId!=""))
      {
        if(fromJobId!=""){
          var fromJobIdBranch= fromJobId.split("-")[0]
            if(fromJobIdBranch!=branchCode)
            {
            confirm("Please Select Branch"+branchCode+" Associted FromJobId");
              hasJobid="false";
            }
        }
        if(toJobId!=""){
          var toJobIdBranch= toJobId.split("-")[0]
            if(toJobIdBranch!=branchCode)
            {
              confirm("Please Select Branch"+branchCode+" Associted ToJobId");
              hasJobid="false";
            }
        }

        if((fromJobIdBranch==branchCode||fromJobIdBranch=="")||(toJobIdBranch==branchCode||toJobIdBranch==""))
        {
           if(hasJobid=="")
           document.jobSearchForm.submit();
        }
      }
    }else
    {
      document.jobSearchForm.submit();
    }
  }
}

function restSearchCriteria(){
	document.jobSearchForm.method="GET";
	document.jobSearchForm.criteriaAction.value="refreshCriteria";
	document.jobSearchForm.submit();
}

function setflag(rowIndex)
 {   

  document.jobSearchForm.rowNum.value=rowIndex;
  }

 function updateSession(hrefValue,jobNumber,reqForm){
 document.jobSearchForm.sessionFlag.value="true";
 document.jobSearchForm.hrefValue.value=hrefValue;
 document.jobSearchForm.hrefJobNumber.value=jobNumber;
 document.jobSearchForm.hrefForm.value=reqForm;
 document.jobSearchForm.submit();
 }

 function doSaveAsCriteria(){
	 var form=document.jobSearchForm;
	 if(form.jobSearchCriteriaName.value==""){
		 confirm("Please enter a Criteria Name");
		 return;
	 }
	 
	 form.criteriaAction.value='saveAs';
	 form.action="search_job_criteria.htm";
	 form.submit();
 }

 function doSaveCriteria(){
	 var form=document.jobSearchForm;
	 if(form.jobSearchCriteriaId.selectedIndex<=0){
		 confirm("Please select a Criteria or enter new Criteria Name then hit Save As...");
		 return;
	 }
	 else{
	 	form.criteriaAction.value='save';
	 }
	 form.action="search_job_criteria.htm";
	 form.submit();
 }
 
 function loadJobSearchCriteria(){	 
	 var form=document.jobSearchForm;
	 if(form.jobSearchCriteriaId.selectedIndex>0){	 
		 form.criteriaAction.value='load';
		 form.action="search_job_criteria.htm";
		 form.submit();
	 }
	 
 }

 function doSetAsDefaultCriteria(){
	 var form=document.jobSearchForm;
	 if(form.jobSearchCriteriaId.selectedIndex<=0){
		 confirm("You must select a Criteria first.");
		 return;	
	 }
	 form.criteriaAction.value='setAsDefault';
	 form.action="search_job_criteria.htm";
	 form.submit();	 
 }

 
</script>
<style type="text/css">
   
div.select { text-align:center; margin-bottom:1.6em }

/* This is where you can customize the appearance of the tooltip */
div#tipDiv {
  position:absolute; visibility:hidden;
  left:0; top:0; z-index:1000;
  width:auto; height:auto; padding:3px; font-size:11px;
  font-family:Arial, Helvetica, sans-serif;
  border-color: #b0c8f2;
  border-style: double;
  filter:progid:DXImageTransform.Microsoft.gradient(gradientType=1, startColorstr=#ffffff, endColorstr=#dde7fa);
  background: url(../images/tooltipbg.jpg) repeat-y;
  }
</style>

<icb:list var="divisions">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
 <icb:item>${command.businessUnit.value}</icb:item> 
</icb:list>

<icb:list var="jobsearchStatus">
  <icb:item>jobsearchStatus</icb:item>
</icb:list>
<icb:list var="jobType">
  <icb:item>jobType</icb:item>
</icb:list>
<icb:list var="operator">
  <icb:item>operator</icb:item>
</icb:list>

<icb:list var="contractStatus">
  <icb:item>contractStatus</icb:item>
</icb:list>
<form:form name="jobSearchForm" method="POST" action="search_job_results.htm">
 <form:hidden path="rowNum"/>
 <form:hidden path="reqFormFlag"/>
 <form:hidden path="sortFlag"/>
  <form:hidden path="sessionFlag"/>
 <form:hidden path="hrefValue"/>
 <form:hidden path="hrefJobNumber"/>
 <form:hidden path="hrefForm"/>
<div style="color:red;"><form:errors cssClass="error" /></div>
<input type="hidden" name="refreshing" value="false" />
<input type="hidden" name="pageNumber" value="1" />
<input type="hidden" name="jxcel" value="false"/>
<input type="hidden" name="jmail" value="false"/>
<input type="hidden" name="criteriaAction" value=""/>
<!-- START : #119240  -->
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 -->
<form:hidden path="columnFlag"/>

<table width="97%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
<td valign="top">
<div id="MainContentContainer">
 <!---------------------------------------------------------------------------------------------- TABS CONTENTS -------------------------------------------------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
  <ul>
    <li><a href="#" onClick="navdisable();" rel="tab1"><span><f:message key="jobSearch" /></span></a></li>
    <li><a href="#" onClick="navdisable();" rel="tab2"><span><f:message key="jobSearchResults"/></span></a></li> 
    
  </ul>
<div align="right">
  <table cellspacing="0" cellpadding="0" border="0">
      <tr>
         <td>
             <select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
                <option selected>Go to ... &gt;</option>
                <option value="search_job_results.htm"><f:message key="jobSearch"/></option>
             </select>
           </td>
        </tr>
     </table>
    </div>
</div>
<!------------------------------------------Sub Menus container. Do not remove------------------------------------->
<div id="tab_inner1">
<!-- ------------------------------------------------------------------------------------------- TAB 1 CONTAINER ----------------------------------------------------------------------------------------->
    <div id="tab1" class="innercontent1" >
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr>
      <th><f:message key="searchCriteria"/>
        <!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --></th>
		<th colspan="4">
			<form:select cssClass="selectionBox" path="jobSearchCriteriaId" onchange="loadJobSearchCriteria()">
				<form:option value="-1" label="Select A Criteria" />
				<form:options items="${sessionScope.mySearchJobCriteria}" itemLabel="searchName" itemValue="id" />
			</form:select>
			&nbsp;&nbsp;&nbsp;<a href="javascript:doSaveCriteria()">Save</a>
			&nbsp;&nbsp;&nbsp;<a href="javascript:doSetAsDefaultCriteria()">Set As Default</a>
			&nbsp;&nbsp;&nbsp;<a href="javascript:doSaveAsCriteria()">Save As...</a>
			&nbsp;&nbsp;&nbsp;<form:input id="jobSearchCriteriaName" cssClass="inputBox" path="jobSearchCriteriaName" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
		</th>
      </tr>
      <tr>
      <td width="15%" class="TDShade"><label for="businessUnitName"><f:message key="businessUnitName" />: </label><span class="redstar">*</span> </td>
      <td width="30%" class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel20" path="businessUnit.value" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value" onchange="makeBranchblank()" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="businessUnit.value" cssClass="error"/>
       
      </td>
      <td width="15%" class="TDShade"><strong><f:message
          key="branchCode" />:</strong></td>
      <td width="35%" colspan="2" class="TDShadeBlue">
        <form:input id="brnch" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;"
          cssClass="inputBox" path="branch.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:errors path="branch.value"
          cssClass="redstar" />
         <a href="#" onClick="javascript:updateBranchIframeSrc();popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 80, 80);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()"><img
          src=" images/lookup.gif" width="13" alt="Lookup branch Code" height="13" border="0" />
         </a></td>
      </tr>
      <tr>
      <td width="15%" class="TDShade"><f:message key="status"/>:</td>
      <td width="30%" class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel3" path="status.value" items="${icbfn:dropdown('staticDropdown',jobsearchStatus)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="status.value" cssClass="error"/>
         
      </td>
      <td class="TDShade"><f:message key="jobType" />: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel4" path="jobType.value" items="${icbfn:dropdown('staticDropdown',jobType)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="jobType.value" cssClass="error"/>
         
      </td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="fromJobId"/>: </td>
      <td class="TDShadeBlue"><form:input id="fromjobid"cssClass="inputBox" path="fromJobId.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="fromJobId.value" cssClass="error"/>
      &nbsp;<a href="#" onClick="javascript:updateFromJobIdIframeSrc();popup_show('searchfromjobid', 'searchfromjobid_drag', 'searchfromjobid_exit', 'screen-corner', 120, 20);showPopupDiv('searchfromjobid','searchfromjobidbody');hideIt();popupboxenable();"><img src="images/lookup.gif" width="13" alt="Lookup jobId" height="13" border="0" /></a></td>
      <td class="TDShade"><f:message key="toJobId"/>: </td>
      <td class="TDShadeBlue" colspan="2"><form:input id="tojobid" cssClass="inputBox"  path="toJobId.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="toJobId.value" cssClass="error"/>&nbsp;<a href="#" onClick="javascript:updateToJobIdIframeSrc();popup_show('searchtojobid', 'searchtojobid_drag', 'searchtojobid_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('searchtojobid','searchtojobidbody');popupboxenable();"><img src="images/lookup.gif" width="13" height="13" border="0" alt="lookup jobId" /></a></td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="fromJobFinishDate"/>: </td>
      <td class="TDShadeBlue"><form:input id="fdate" cssClass="inputBox" path="fromJobFinshDate.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
      <form:errors path="fromJobFinshDate.value" cssClass="error"/><a href="#" onClick="displayCalendar(document.forms[0].fdate,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
      <td class="TDShade"><f:message key="toJobFinishDate"/>: </td>
      <td class="TDShadeBlue" colspan="2"><form:input id="tdate" cssClass="inputBox" path="toJobFinshDate.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
      <form:errors path="toJobFinshDate.value" cssClass="error"/><a href="#" onClick="displayCalendar(document.forms[0].tdate,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
      </tr>
      
	  <tr>
      <td class="TDShade"><f:message key="etaFrom"/>: </td>
      <td class="TDShadeBlue"><form:input id="etaFrom" cssClass="inputBox" path="etaFrom.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="etaFrom.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].etaFrom,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
      <td class="TDShade"><f:message key="etaTo"/>: </td>
      <td class="TDShadeBlue" colspan="2"><form:input id="etaTo" cssClass="inputBox" path="etaTo.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="etaTo.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].etaTo,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
      </tr>

     <tr>
      <td class="TDShade"><f:message key="nomDtFrom"/>: </td>
      <td class="TDShadeBlue"><form:input id="ndFrom" cssClass="inputBox" path="nomDateFrom.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="nomDateFrom.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].ndFrom,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
      <td class="TDShade"><f:message key="nomDtTo"/>: </td>
      <td class="TDShadeBlue" colspan="2"><form:input id="nomDtTo" cssClass="inputBox" path="nomDateTo.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="nomDateTo.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].nomDtTo,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
      </tr>
    
      <tr>
      <td class="TDShade"><f:message key="vessel"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel5" path="vessel.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="vessel.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="vessel.value" cssClass="error"/>
      </td>
      <td class="TDShade"><f:message key="product"/>: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel6" path="product.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="product.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="product.value" cssClass="error"/>
      </td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="custRefNum"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" path="custRefNum.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/></td>
      <td class="TDShade"><f:message key="icbRef"/>: </td>
      <td class="TDShadeBlue" colspan="2"><form:input cssClass="inputBox" path="icbRefNum.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="icbRefNum.value" cssClass="error"/></td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="invoice"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" path="invoice.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/></td>
      

	  <icb:list var="jobContractStatus">
      <icb:item>jobContractStatus</icb:item>
	  <icb:item>invoiceStatus.value</icb:item>
      </icb:list> 
	  
	  <td class="TDShade"><f:message key="invoiceStatus"/>: </td>
      <td class="TDShadeBlue">  <form:select cssClass="selectionBox" id="sel3" path="invoiceStatus.value" items="${icbfn:dropdown('staticDropdown',jobContractStatus)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="invoiceStatus.value" cssClass="error"/></td>



	  </tr>
	   <tr>
      <td class="TDShade"><f:message key="contractDescription"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox"cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="contractDescription.value" size="35" onkeypress="if(event.keyCode==13) {checkFromTO();}"/></td>
     
	 <td class="TDShade"><f:message key="contractId"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel11" path="contractId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="contractId.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="contractId.value" cssClass="error"/>
      </td>
	  
      </tr>
	  <tr>
      <td class="TDShade"><f:message key="createdBy"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="createdBy.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
	  <a href="#" onClick="javascript:updateCreatedByIframeSrc();popup_show('createdby','createdby_drag','createdby_exit', 'screen-corner', 120,20);hideIt();showPopupDiv('createdby','createdbybody');popupboxenable();">
	   <img src=" images/lookup.gif"  alt="Lookup createdBy" width="13" height="13" border="0"/></a></td>
	   <td class="TDShade"><f:message key="modifiedBy"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="modifiedBy.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
	  <a href="#" onClick="javascript:updateModifiedByIframeSrc();popup_show('modifiedby','modifiedby_drag','modifiedby_exit', 'screen-corner', 120,20);hideIt();showPopupDiv('modifiedby','modifiedbybody');popupboxenable();">
	   <img src=" images/lookup.gif"  alt="Lookup modifiedBy" width="13" height="13" border="0"/></a></td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="port"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel7" path="port.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="port.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="port.value" cssClass="error"/>
      </td>
      <td class="TDShade"><f:message key="svcLocation"/>: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel8" path="svcLocation.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="svcLocation.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="svcLocation.value" cssClass="error"/></td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="companyId"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel9" path="companyId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="companyId.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="companyId.value" cssClass="error"/>
      </td>
      <td class="TDShade"><f:message key="company"/>: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel10" path="company.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="company.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="company.value" cssClass="error"/>
      </td>
      </tr>
      <tr>
    <%--  <td class="TDShade"><f:message key="billingContact"/>: </td>--%>
	<td class="TDShade"><f:message key="contactID"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel12" path="billingContactId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="billingContactId.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="billingContactId.value" cssClass="error"/>
      </td>
	  <td class="TDShade"><f:message key="billingContact"/>: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel15" path="billingContact.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="billingContact.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="billingContact.value" cssClass="error"/>
      </td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="schedulerId"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel13" path="schedulerId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="schedulerId.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="schedulerId.value" cssClass="error"/>
      </td>
      <td class="TDShade"><f:message key="scheduler"/>: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel14" path="scheduler.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
        <form:input cssClass="inputBox" path="scheduler.value" onkeypress="if(event.keyCode==13) {checkFromTO();}" /><form:errors path="scheduler.value" cssClass="error"/>
      </td>
      </tr>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
      <tr>
      <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
        <td>
			<input name="Search" type="button" class="button1" value="Search/Refresh" onclick="checkFromTO()" />
			&nbsp;&nbsp;<input name="reset" type="button" class="button1" value="Reset" onclick="restSearchCriteria()" />
		</td>

        <td style="text-align:right">
        <!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --></td>
        </tr>
      </table></td>
      </tr>
    </table>
    <br>
    </div>

<ajax:autocomplete
  baseUrl="branch.htm"
  source="branch.value"
  target="branch.value"
  className="autocomplete"              
  parameters="branchName={branch.value},buName={businessUnit.value}"
  minimumCharacters="3"
 />

<ajax:autocomplete 
          baseUrl="joborder.htm"
          source="createdBy.value"
          target="createdBy.value"
		  className="autocomplete"
          parameters="createdby={createdBy.value}"
          minimumCharacters="1" />

          <!-- --------------------------- Search From Job Id Lookup Start ------------------------------------------------- -->

<ajax:autocomplete 
          baseUrl="joborder.htm"
          source="modifiedBy.value"
          target="modifiedBy.value"
		  className="autocomplete"
          parameters="modifiedby={modifiedBy.value}"
          minimumCharacters="1" />


<ajax:autocomplete 
          baseUrl="joborder.htm"
          source="contractDescription.value"
          target="contractDescription.value"
		  className="autocomplete"
          parameters="description={contractDescription.value}"
          minimumCharacters="3" />

<!-------------------------------------------------------------------------------------------TAB 1 CONTAINER END ---------------------------------------------------------------------------------------->   
<!------------------------------------------------------------------------------------------ TAB 2 CONTAINER --------------------------------------------------------------------------------------------->
<div id="tab2" class="innercontent1">
<table width="100%" cellpadding=0 cellspacing=0 class="mainApplTable">
<tbody>
<tr bgcolor=#ffffff>
<th width="20%" colspan="2" ><f:message key="jobSearchResults"/></th>

<th width="15%" style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm();" /></a></th>
</tr>


<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<td valign="top" width="125" style=" padding:0px;">

<c:if test="${command.results != null}">
<tr>
<td colspan="0" style="padding:0px;">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="left">
<tr>
<td valign="top" style=" padding:0px;">

<div style="width:100%; vertical-align:top;">

<div style="width:11%;float:left;#DBE2F2 1px solid;background:url(images/intablehdrblubg2.gif) repeat-x;">

<table width="98%" cellpadding="0" cellspacing="0" class="InnerApplTable" align="left" border="0"><tr><th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.jobNumber');" ><f:message key="jobId"/></a></th></tr> 
<c:forEach items="${command.results}" var="job" varStatus="status"> 
<tr><td align='CENTER' nowrap='nowrap' height="25" valign="middle"><a href="#" onClick="updateSession('${job.herfJobType}','${job.jobOrder.jobNumber}','${job.reqFrom}')" title="Transfer to Nomination" >${job.jobOrder.jobNumber}</a></td></tr>
</c:forEach> </table> </div>

<div style="float:left;width:89%;" style="visibility: visible;overflow-x:scroll;height:auto;overflow-y:hidden;#DBE2F2 1px solid;">

<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" frameborder="0" border="0" style="border-left-width:0px;#DBE2F2 1px solid;">
<tr>
  <th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.buName');" ><f:message key="businessUnit"/></a><a name="job.buName"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.operation');" ><f:message key="operation"/></a><a name="job.operation"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.jobType');" ><f:message key="jobType"/></a><a name="job.jobType"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('serv.city');" ><f:message key="portLocation"/></a><a name="serv.city"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('serv.name');" ><f:message key="serviceLocation"/></a><a name="serv.name"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.vesselNames');" ><f:message key="vessel"/></a><a name="job.vesselNames"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.productNames');" ><f:message key="product"/></a><a name="job.productNames"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('cust.custCode');" ><f:message key="companyId"/></a><a name="cust.custCode"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('cust.name');" ><f:message key="companyName"/></a><a name="cust.name"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('contact.firstName');" ><f:message key="scheduler"/><a name="contact.firstName"></a></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('jobc.custRefNum');" ><f:message key="custRefNum"/></a><a name="jobc.custRefNum"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('jobc.invoiceValue5');" ><f:message key="icbReference"/></a><a name="jobc.invoiceValue5"></a></th>
  <th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.jobFinishDate');" ><f:message key="jobFinishDate"/></a><a name="job.jobFinishDate"></a></th>
   <th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.jobStatus');" ><f:message key="status"/></a><a name="job.jobStatus"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('jobcinvc.invoice');" ><f:message key="invoice"/></a><a name="jobcinvc.invoice"></a></th>
	<th nowrap><a href="#start" onClick="sortByJobSearchHeader('contract.description');" ><f:message key="contractDescription"/></a><a name="contract.description"></a></th>
	<th nowrap><a href="#start" onClick="sortByJobSearchHeader('contract.status');" ><f:message key="contractStatus"/></a><a name="contract.status"></a></th>
	<th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.createdByUserName');" ><f:message key="createdBy"/></a><a name="job.createdByUserName"></a></th>
	<th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.updatedByUserName');" ><f:message key="modifiedBy"/></a><a name="job.updatedByUserName"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('job.updateTime');" ><f:message key="modifiedDate"/></a><a name="job.updateTime"></a></th>
  </tr>
  <c:forEach items="${command.results}" var="job" varStatus="status">
  <tr valign='center'>

    <td align='left' valign="middle" nowrap> <span>${job.jobOrder.buName}</span></td>
   <td align='left' valign="middle" nowrap ><span >${job.operation} </span></td>
    <td align='left' valign="middle" nowrap ><span >${job.jobType}</span></td>

    <td align='left' valign="middle" nowrap><span >${job.jobOrder.serviceLocation.city}</span></td>

      <td align='left' valign="middle" nowrap ><span>${job.jobOrder.serviceLocation.name}</span></td>
      
    <td align='left' valign="middle" nowrap ><span >${job.jobOrder.vesselNames}</span></td>

      <td align='left' valign="middle" nowrap>  <span >${job.jobOrder.productNames}</span></td>
    <td align='left' valign="middle" nowrap> <span >${job.jobContract.custCode}</span></td>
    
    <td align='left' valign="middle" nowrap > <span >${job.jobContract.customer.name}</span></td>
   <td align='left' valign="middle" nowrap > <span >${job.jobContract.contact.firstName} ${job.jobContract.contact.lastName}</span></td>
    <td align='left' valign="middle" nowrap > <span >${job.jobContract.custRefNum}</span></td>

    <td align='left' valign="middle" nowrap><span></span>${job.jobContract.invoiceValue5}</td> 
    <td height="25" align='left' valign="middle" nowrap><span><f:formatDate value="${job.jobOrder.jobFinishDate}" type="date" pattern="${command.dateFormat}"
/> 
</span></td>
    <td align='left' nowrap > ${job.jobStatus}</td>
    <td align='left' nowrap > ${job.latestJobContractInvoice.invoice}</td>
    <td align='left' nowrap > ${job.jobContract.contract.description}</td>
	<td align='left' nowrap > ${job.contractStatus}</td>
	<td align='left' nowrap > ${job.jobOrder.createdByUserName}</td>
	<td align='left' nowrap > ${job.jobOrder.updatedByUserName}</td>
	<td align='left' nowrap > <span><f:formatDate value="${job.jobOrder.updateTime}" type="both" /></span></td>
    </tr>


<SCRIPT LANGUAGE="JavaScript">
location.href="#${command.columnFlag}"	
</SCRIPT>


</c:forEach>
</table>
</div>
<br style="clear:both;" />
</div>

</td>
</tr>
</table>
</td>
</tr>
</c:if>

</td>
</tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<!-- START : #119240  -->
<%-- <table cellspacing="0" cellpadding="0" border="0">
    <tr>
      <td><a href="#" onClick="prevSearch(pageNumber)"><IMG SRC="images/navfirst.gif" ALT="First Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      <td><a href="#" onClick="previousSearch('${command.pagination.currentPageNum}')">
       <IMG SRC="images/navprevious.gif" ALT="Previous Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      <td><select name="goto" size="1" class="selectionBox" id="sel3" style="width:84px;" onchange="submitSearch(this.value)" >
      <option value="Go to page"><c:out value="Go to page" />
      <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
      <option value="${page.pageNumber}"><c:out value="${page.name}" />
      </c:forEach>
      </select></td>
      <td><a href="#" onClick="nextSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navnext.gif" ALT="Next Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      <td><a href="#" onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')"><IMG SRC="images/navlast.gif" ALT="Last Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
   </tr>
</table> --%>

<%@ include file="../common/pagination.jsp" %>
<!--  END : #119240 -->
</td>

<td style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"></a><a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm();"/></a></td>

</tr>
</table></td>
</tr>
</table>
</div>
<!-- ------------------------------------------------------------------------------- TAB 2 CONTAINER END ------------------------------------------------------------------------------------------------>

</div>
  </div>
  <script type="text/javascript">
  var tabnavIndex = 0;
  if('main' == '${searchResult}') tabnavIndex = 1;
  dolphintabs.init("tabnav", tabnavIndex)
  </script>
<!-------------------------------------------------------------------------------------------- TAB CONTENT END ------------------------------------------------------------------------------------------->
  <table width="100%" cellspacing="0">
  <tr>
  <td width="90%" height="24" align="right">
  <div id="navbuttons"></div>
  </td>
  <td height="24" style="text-align:right; padding-right:0px;"><select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
  <option selected>Go to ... &gt;</option>
  <option value="search_job_results.htm"><f:message key="jobSearch"/></option>
  </select>
  </td>
  </tr>
  </table>
  </div>
<!------------------------------------------------------------------------------------------- BREADCRUMB TRAIL END ------------------------------------------------------------------------------------->
<!-- ------------------------------------------------------------------------------------------ MAIN CONTAINER END -------------------------------------------------------------------------------------->
</td>
</tr>
</table>
</form:form>


<!-- --------------------------------- Search From Job Id Lookup START ----------------------------------------- -->

<div class="sample_popup" id="searchfromjobid" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="searchfromjobid_drag" style="width:750px; "> 
  <img class="menu_form_exit"   id="searchfromjobid_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchJobId"/></div>
  <div class="menu_form_body" id="searchfromjobidbody" style="width:750px; height:530px;overflow-y:hidden;">
  <iframe id="fromJobIds" align="left" src="blank.html"
  style="padding:0px; height:530px;" width="100%" scrolling="auto" name="frame1" allowtransparency="yes">
  </iframe>
  </div>
</div>

<!-- --------------------------------- Search From Job Id Lookup END ----------------------------------------- -->

<!-- --------------------------- Search To Job Id Lookup Start ------------------------------------------------- -->

<div class="sample_popup" id="searchtojobid" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="searchtojobid_drag" style="width:750px; "> 
  <img class="menu_form_exit"   id="searchtojobid_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchJobId"/></div>
  <div class="menu_form_body" id="searchtojobidbody"  style="width:750px; height:530px;overflow-y:hidden;">
  <iframe id="toJobIds"  align="left" style="padding:0px; height:530px;" width="100%" src="blank.html"
  scrolling="auto" name="frame1" allowtransparency="yes">
  </iframe>
  </div>
  </div>
<!-- --------------------------------- Search To Job Id Lookup END ----------------------------------------- -->
<!-- ---------------------------  Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="jobbranchcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="jobbranchcode_drag" style="width:750px; "> 
<a href="#"  onclick="resetBranchTypeFlag()"> <img class="menu_form_exit"   id="jobbranchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/>
  </div>
  <div class="menu_form_body" id="jobbranchcodebody" style="width:750px; height:530px;overflow-y:hidden;">
    <form method="post" action="popup.php">
     
            <iframe align="left" id="jobbu" frameborder="0" style="padding:10px;" height="530px;" width="100%"  scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" src="blank.html"></iframe>
             
    </form>
  </div>
</div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->


<!---------------------------------------created By Lookup START------------------------------------------------->
<div class="sample_popup" id="createdby"  style="visibility: hidden; display: none;">
<div class="menu_form_header" id="createdby_drag" style="width:750px;">
<img class="menu_form_exit" id="createdby_exit" src="images/form_exit.png"/> &nbsp;&nbsp;&nbsp;<f:message key="searchUser" /></div>
<div class="menu_form_body" id="createdbybody" style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%"
src="blank.html" scrolling="auto" id="searchCreatedFr" name="searchCreatedFr" allowtransparency="yes"></iframe></div>
</div>
<!------------------------------------ created By Lookup END----------------------------------------------------->

<!---------------------------------------modified By Lookup START------------------------------------------------->
<div class="sample_popup" id="modifiedby"  style="visibility: hidden; display: none;">
<div class="menu_form_header" id="modifiedby_drag" style="width:750px;">
<img class="menu_form_exit" id="modifiedby_exit" src="images/form_exit.png"/> &nbsp;&nbsp;&nbsp;<f:message key="searchUser" /></div>
<div class="menu_form_body" id="modifiedbybody" style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%"
src="blank.html" scrolling="auto" id="searchModifiedFr" name="searchModifiedFr" allowtransparency="yes"></iframe></div>
</div>
<!------------------------------------ modified By Lookup END----------------------------------------------------->





<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left:1px;Top:1px;">
<IMG src="images/fake-alpha-999.gif"></div>

