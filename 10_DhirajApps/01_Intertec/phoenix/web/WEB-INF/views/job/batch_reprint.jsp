<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<c:if test="${!empty batchReprintResult}">
	<script type="text/javascript">
			confirm("${batchReprintResult}");
	</script>
</c:if>	

<script>
/*Tooltip.offX = 4;  
Tooltip.offY = 4;
Tooltip.followMouse = false;  // must be turned off for hover-tip
*/
function batchReprint(){
	document.batchReprintForm.batchReprintFlag.value="true";
 	document.batchReprintForm.submit();
}

function invoiceReprint(){
	document.batchReprintForm.batchReprintFlag.value="invoiceReprint";
 	document.batchReprintForm.submit();
}

function viewBatchReprint(id){
	document.batchReprintForm.batchReprintFlag.value=id;
 	document.batchReprintForm.submit();
}

function loadLastFive(){
    document.batchReprintForm.batchReprintFlag.value="loadLastFive";
 	document.batchReprintForm.submit();
}

function onSearchTabClick(){
	document.batchReprintForm.batchReprintFlag.value="";
	document.getElementById("navbuttons").style.visibility = "hidden";
}


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
/*
Tooltip.tipOutCheck = function(e) {
  e = dw_event.DOMit(e);
  // is element moused into contained by tooltip?
  var toEl = e.relatedTarget? e.relatedTarget: e.toElement;
  if ( this != toEl && !contained(toEl, this) ) Tooltip.hide();
}
*/
// returns true of oNode is contained by oCont (container)
function contained(oNode, oCont) {
  if (!oNode) return; // in case alt-tab away while hovering (prevent error)
  while ( oNode = oNode.parentNode ) if ( oNode == oCont ) return true;
  return false;
}
/*
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
*/
 function sortByInvoiceSearchHeader(orderBy){
	document.batchReprintForm.pageNumber.value = "1";
	document.batchReprintForm.sortFlag.value = orderBy;
	document.batchReprintForm.jxcel.value="false";
	top.document.batchReprintForm.submit();
 }


function submitSearch(pageNumber, currentPageNumber)
{  
var tempPageNo = currentPageNumber;
document.batchReprintForm.jxcel.value="false";
//var w = document.batchReprintForm.goto.selectedIndex;
//var page=document.batchReprintForm.goto.options[w].text;
document.batchReprintForm.pageNumber.value = pageNumber;
checkBoxInvoice(tempPageNo);
document.batchReprintForm.submit();
} 
function submitxcel()
{
  document.batchReprintForm.jxcel.value="true";
  top.document.batchReprintForm.submit();
}

function prevSearch(pageNumber, currentPageNumber)
{
	var tempPageNo = currentPageNumber;
	document.batchReprintForm.jxcel.value="false";
	document.batchReprintForm.pageNumber.value = pageNumber.value;
	checkBoxInvoice(tempPageNo);
	document.batchReprintForm.submit();
}

function lastSearch(totalRecords,numInPages,pageNumber)
{
	var tempPageNo = pageNumber;
	document.batchReprintForm.jxcel.value="false";
	var pageNumber;
	var quotient=totalRecords/numInPages;
	var remainder=totalRecords%numInPages;
	
	if(remainder==0)
		pageNumber=quotient;
	else
		pageNumber=Math.floor(quotient)+1;
	document.batchReprintForm.pageNumber.value =pageNumber;
	checkBoxInvoice(tempPageNo);
	document.batchReprintForm.submit();
}

function nextSearch(pageNumber)
{
	var tempPageNo = pageNumber;
	document.batchReprintForm.jxcel.value="false";
	var b=++pageNumber;
	document.batchReprintForm.pageNumber.value = b;
	checkBoxInvoice(tempPageNo);
	document.batchReprintForm.submit();
}

function previousSearch(pageNumber)
{
	var tempPageNo = pageNumber;
	document.batchReprintForm.jxcel.value="false";
	var b=--pageNumber;
	document.batchReprintForm.pageNumber.value = b;
	checkBoxInvoice(tempPageNo);
	document.batchReprintForm.submit();
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

function updateBatchBranchIframeSrc()
  {
  var buName= document.getElementById("selBatchBu").value;
  if(buName!= "" && buName!= null)
  {
    document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId=batchReprintBranch.value&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=jobsForm");
  }
}

function makeBranchblank()
{
  document.getElementById("brnch").value="";
}

function makeBatchBranchblank()
{
  document.getElementById("batchBranch").value="";
  HideloadLastFiveDiv();
}

function HideloadLastFiveDiv(){
	loadLastFiveDiv.style.visibility='hidden';
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
	document.batchReprintForm.batchReprintFlag.value="";
	document.batchReprintForm.pageNumber.value = "1";  
	var status= document.getElementById("sel3").value;
	var fromJobId= document.getElementById("fromjobid").value;
	var toJobId= document.getElementById("tojobid").value;
	var fromDate= document.getElementById("fdate").value;
	var toDate= document.getElementById("tdate").value;
	var branchCode=document.getElementById("brnch").value;
	document.batchReprintForm.jxcel.value="false";
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
	    document.batchReprintForm.submit();
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
	           document.batchReprintForm.submit();
	        }
	      }
	    }else
	    {
	      document.batchReprintForm.submit();
	    }
	  }
}

function checkall()
{
	if(document.getElementById('selectall').checked)
	{
	
		for(i=0;i<document.forms[0].elements.length;i++)
		{
			if(document.forms[0].elements[i].type == 'checkbox')
			{
				document.forms[0].elements[i].checked = true;
			}
		}
	} else
	{
		for(i=0;i<document.forms[0].elements.length;i++)
		{
			if(document.forms[0].elements[i].type == 'checkbox')
			{
				document.forms[0].elements[i].checked = false;
			}
		}
	}
}

function validateCheck()
{
	var records=0;
	var totalRecords=0;
	for(i=0;i<document.forms[0].elements.length;i++)
	{
		if(document.forms[0].elements[i].type == 'checkbox')
		{
			if(document.forms[0].elements[i].id != 'selectall')
				totalRecords++;
				
			if(document.forms[0].elements[i].checked){
				if(document.forms[0].elements[i].id != 'selectall')
					records++;
			}
		}
	}
	if(records == totalRecords)
	{
		document.getElementById('selectall').checked = true;
	}
	else if(records < totalRecords)
	{
		document.getElementById('selectall').checked = false;
	}
}
function getXMLHTTPRequest()
{
	var request = false;
	try
  	{
		 request = new XMLHttpRequest(); /* e.g. Firefox*/
	 	if (request.overrideMimeType)
	 	 {
			request.overrideMimeType('text/plain');
		 }
    }
	catch(err1)
  	{
  	try
    {
	  request = new ActiveXObject("Microsoft.XMLHTTP");
  			/* some versions IE */
    }
  	catch(err2)
    {
    try
      {
    	request = new ActiveXObject("Msxml2.XMLHTTP");
	      
  			/* some versions IE */
      }
      catch(err3)
        {
        	request = false;
        }
    }
  }
	return request;
}
var request1;
function ajaxCallForBatchReprint(args,pageNumber){
	var urls = '/phoenix/batch_reprint_multiaction.htm?action=maintainDirtyBack&args='+args+'&pageNumber='+pageNumber;
	request1 = getXMLHTTPRequest();	
	request1.onreadystatechange = CallBack_DependancyForFormula;
	request1.open("POST", urls, true);		
	request1.send(null);
}

function CallBack_DependancyForFormula()
{
	if(request1.readyState == 4)
	{
		//confirm(request1.status);
		if(request1.status == 200)
		{
			var msg =request1.responseText;
			
			if(msg!='flag')
			{
				confirm("SOME THING IS WRONG IN YOUR CODE PLEASE CHECK........");
			}
			else
			{
				//confirm('OK');
			}
		}	
		else
		{
			confirm('<cms:translate key="msg_A013_badresponse"/>');
		}
	}
	
}

function checkBoxInvoice(pageNumber)
{
var arr = new Array();
var k=0;
		for(i=0;i<document.forms[0].elements.length;i++)
		{
			if(document.forms[0].elements[i].type == 'checkbox')
			{
				
				if(document.forms[0].elements[i].checked)
				{
					arr[k] = document.forms[0].elements[i].value;
					k++;
				}
			}
		}
		ajaxCallForBatchReprint(arr,pageNumber);
}

</script>
<style type="text/css">
  /* 
div.select { text-align:center; margin-bottom:1.6em }

This is where you can customize the appearance of the tooltip 
div#tipDiv {
  position:absolute; visibility:hidden;
  left:0; top:0; z-index:1000;
  width:auto; height:auto; padding:3px; font-size:11px;
  font-family:Arial, Helvetica, sans-serif;
  border-color: #b0c8f2;
  border-style: double;
  filter:progid:DXImageTransform.Microsoft.gradient(gradientType=1, startColorstr=#ffffff, endColorstr=#dde7fa);
  background: url(../images/tooltipbg.jpg) repeat-y;
  }*/
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
<form:form name="batchReprintForm" method="POST" action="batch_reprint.htm">
  <form:hidden path="sortFlag"/>
  <form:hidden path="batchReprintFlag"/>
  <div style="color:red;"><form:errors cssClass="error" /></div>
  <input type="hidden" name="pageNumber" value="1" />
  <input type="hidden" name="jxcel" value="false"/>
 

<table width="97%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
<td valign="top">
<div id="MainContentContainer">
 <!---------------------------------------------------------------------------------------------- TABS CONTENTS -------------------------------------------------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
  <ul>
    <li><a href="#" onClick="onSearchTabClick();" rel="tab1"><span>Invoice Search</span></a></li>
    <li><a href="#" onClick="navdisable();" rel="tab2"><span>Invoice Search Result</span></a></li> 
    
  </ul>
<div align="right">
  <table cellspacing="0" cellpadding="0" border="0">
      <tr>
         <td>
             <select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
                <option selected>Go to ... &gt;</option>
                <option value="batch_reprint.htm"><f:message key="batchReprint"/></option>
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
	    <div id="batch0">
		  <table border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="margin-bottom:0px;" border="1" >
			<tr valign="center">
			  <th  width=16 class="TDShade" style="background-image:url(images/arrowblubg.gif);">      
			   <div id="bluarrowdown0" style="visibility:visible;position:absolute; z-index: 1; margin-top:4px ">
				  <a href="#" onClick="javascript:hideOriginTable('invoicesearch','bluarrowright0','bluarrowdown0',0);"> 
					<img src=" images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a></div>
					 <div id="bluarrowright0" style="visibility:hidden; position:relative; z-index: 1; margin-left:4px"> 
				  <a href="#" onClick="javascript:showOriginTable('invoicesearch','bluarrowdown0','bluarrowright0',0);"> 
			   <img src=" images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div></th>
			 <th colspan="5" class="TDShade">Search</th>
		  </tr>
		</table>
	 </div>
	 <div id="invoicesearch"  style="padding:0px; visibility:visible;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr>
      <th colspan="5"><f:message key="searchCriteria"/></th>
      </tr>
      <tr>
      <td width="15%" class="TDShade"><label for="businessUnitName"><f:message key="businessUnitName" />: </label><span class="redstar">*</span> </td>
      <td width="30%" class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel20" path="businessUnit.value" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value" onchange="makeBranchblank()" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="businessUnit.value" cssClass="error"/>
       
      </td>
      <td width="15%" class="TDShade"><strong><f:message key="branchCode"/>:<span class="redstar">*</span></strong></td>
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
      <td class="TDShade"><f:message key="invoice"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" path="invoice.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/></td>
	  <icb:list var="jobContractStatus">
      <icb:item>jobContractStatus</icb:item>
	  <icb:item>invoiceStatus.value</icb:item>
      </icb:list> 
	  <td class="TDShade"><f:message key="invoiceStatus"/>: </td>
      <td class="TDShadeBlue"><form:select cssClass="selectionBox" id="sel3" path="invoiceStatus.value" items="${icbfn:dropdown('staticDropdown',jobContractStatus)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="invoiceStatus.value" cssClass="error"/></td>
	  </tr>
      
      <tr>
		 <td class="TDShade"><f:message key="fromInvoiceDate"/>:</td>
			<td class="TDShadeBlue"><form:input id="finvoicedate" cssClass="inputBox" path="fromInvoiceDate.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
			<form:errors path="fromInvoiceDate.value" cssClass="error"/><a href="#" onClick="displayCalendar(document.forms[0].finvoicedate,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
			<td class="TDShade"><f:message key="toInvoiceDate"/>:</td>
			<td class="TDShadeBlue" colspan="2"><form:input id="tinvoicedate" cssClass="inputBox" path="toInvoiceDate.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
			<form:errors path="toInvoiceDate.value" cssClass="error"/><a href="#" onClick="displayCalendar(document.forms[0].tinvoicedate,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
		 </td>
     </tr>

   <tr>
		 <td class="TDShade"><f:message key="fromInvoiceGenerateDate"/>:</td>
			<td class="TDShadeBlue"><form:input id="finvoicegendate" cssClass="inputBox" path="fromInvoiceGenerateDate.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
			<form:errors path="fromInvoiceGenerateDate.value" cssClass="error"/><a href="#" onClick="displayCalendar(document.forms[0].finvoicegendate,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
			<td class="TDShade"><f:message key="toInvoiceGenerateDate"/>:</td>
			<td class="TDShadeBlue" colspan="2"><form:input id="tinvoicegendate" cssClass="inputBox" path="toInvoiceGenerateDate.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
			<form:errors path="toInvoiceGenerateDate.value" cssClass="error"/><a href="#" onClick="displayCalendar(document.forms[0].tinvoicegendate,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
		 </td>
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
      <td class="TDShade"><f:message key="nomDtFrom"/>: </td>
      <td class="TDShadeBlue"><form:input id="ndFrom" cssClass="inputBox" path="nomDateFrom.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="nomDateFrom.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].ndFrom,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
      <td class="TDShade"><f:message key="nomDtTo"/>: </td>
      <td class="TDShadeBlue" colspan="2"><form:input id="nomDtTo" cssClass="inputBox" path="nomDateTo.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="nomDateTo.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].nomDtTo,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
      </tr>
    
      
      <tr>
      <td class="TDShade"><f:message key="custRefNum"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" path="custRefNum.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/></td>
      <td class="TDShade"><f:message key="icbRef"/>: </td>
      <td class="TDShadeBlue" colspan="2"><form:input cssClass="inputBox" path="icbRefNum.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="icbRefNum.value" cssClass="error"/></td>
      </tr>
      
	  <tr>
      <td class="TDShade"><f:message key="createdBy"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="createdBy.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
	  <a href="#" onClick="javascript:popup_show('createdby','createdby_drag','createdby_exit', 'screen-corner', 120,20);hideIt();showPopupDiv('createdby','createdbybody');popupboxenable();">
	   <img src=" images/lookup.gif"  alt="Lookup createdBy" width="13" height="13" border="0"/></a></td>
	   <td class="TDShade"><f:message key="modifiedBy"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="modifiedBy.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
	  <a href="#" onClick="javascript:popup_show('modifiedby','modifiedby_drag','modifiedby_exit', 'screen-corner', 120,20);hideIt();showPopupDiv('modifiedby','modifiedbybody');popupboxenable();">
	   <img src=" images/lookup.gif"  alt="Lookup modifiedBy" width="13" height="13" border="0"/></a></td>
      </tr>
      
      <tr>
      <td class="TDShade"><f:message key="companyId"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel9" path="companyId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
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
        <form:input cssClass="inputBox" path="billingContactId.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="billingContactId.value" cssClass="error"/>
      </td>
	  <td class="TDShade"><f:message key="billingContact"/>: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel15" path="billingContact.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
        <form:input cssClass="inputBox" path="billingContact.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="billingContact.value" cssClass="error"/>
      </td>
      </tr>
      
       <tr>
        <td class="TDShade"><f:message key="schedulerId"/>: </td>
        <td class="TDShadeBlue">
        	<form:input cssClass="inputBox" path="schedulerId.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="schedulerId.value" cssClass="error"/>
        </td>
	    <td class="TDShade"><f:message key="contractId"/>: </td>
        <td class="TDShadeBlue">
        	<form:select cssClass="selectionBox" id="sel11" path="contractId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
            <form:input cssClass="inputBox" path="contractId.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="contractId.value" cssClass="error"/>
        </td>
      </tr>
      
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
      <tr>
      <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
        <td>
        	<input name="Search" type="button" class="button1" value="Search/Refresh" onclick="checkFromTO()" />
        	<input type="button" class="button1" value="Reset" onclick="batchReprintForm.reset();" />
        </td>
        <td style="text-align:right">
        <!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --></td>
        </tr>
      </table></td>
      </tr>
    </table>
    </div>
<div id="batch1">
  <table border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="margin-bottom:0px;" border="1" >
			<tr valign="center">
			  <th  width=16 class="TDShade" style="background-image:url(images/arrowblubg.gif);">      
			   <div id="bluarrowdown1" style="visibility:visible;position:absolute; z-index: 1; margin-top:4px ">
				  <a href="#" onClick="javascript:hideOriginTable('batchreprint','bluarrowright1','bluarrowdown1',0);"> 
					<img src=" images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a></div>
					 <div id="bluarrowright1" style="visibility:hidden; position:relative; z-index: 1; margin-left:4px"> 
				  <a href="#" onClick="javascript:showOriginTable('batchreprint','bluarrowdown1','bluarrowright1',0);"> 
			   <img src=" images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div></th>
			 <th colspan="5" class="TDShade">Batch Reprint</th>
		  </tr>
		</table>
</div>

<div id="batchreprint" style="padding:0px; visibility:visible;">
 <table  cellpadding="0" cellspacing="0" class="mainApplTable" border="0" >
 
<tr><td width="40%">
<table cellpadding="0" cellspacing="0"  border="0">		
		<div id="loadLastFiveDiv">
		<c:forEach items="${command.lastFiveBatchReprintList}" var="lastFive">
			<tr>
				<td width="15%"><a href="#" onClick="viewBatchReprint(${lastFive.id});"><f:formatDate type="both" value="${lastFive.runDate}"/></a></td>
				<td>${lastFive.runnedByUserName}</td>
			</tr>
		</c:forEach>
		</div>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr>
	<td class="TDShade"><label for="businessUnitName"><f:message key="businessUnitName" />: </label><span class="redstar">*</span> </td>
      <td width="15%" class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="selBatchBu" path="batchReprintBusinessUnit.value" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value" onchange="makeBatchBranchblank();"/><form:errors path="businessUnit.value" cssClass="error"/>
      </td>
	</tr>
	<tr>
		<td width="15%" class="TDShade"><strong><f:message key="branchCode" />:<span class="redstar">*</span></strong></td>
	      <td class="TDShadeBlue">
	        <form:input  onchange="if(this.value.length>0){javascript:loadLastFive();}else{loadLastFiveDiv.style.visibility='hidden';}"  id="batchBranch" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" cssClass="inputBox" path="batchReprintBranch.value"/>
	        <form:errors path="batchReprintBranch.value" cssClass="redstar" />
	         <a href="#" onClick="javascript:loadLastFiveDiv.style.visibility='hidden';updateBatchBranchIframeSrc();popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 80, 80);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable();">
	         	<img src=" images/lookup.gif" width="13" alt="Lookup branch Code" height="13" border="0" />
	         </a>
	      </td>
	</tr>
	<tr height="50">
		<td class="TDShade" colspan=2>
	    	<input name="Button" type="button" class="button1" value="New Batch" style="border-width:1px;" onClick="batchReprint();"/>
	    	<input name="Button" type="button" class="button1" value="Refresh Last Five" style="border-width:1px;" onClick="loadLastFive();"/>
	    </td>
	</tr>
	</table></td><td>&nbsp;</td></tr>
</table>
</div>
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
  baseUrl="branch.htm"
  source="batchReprintBranch.value"
  target="batchReprintBranch.value"
  className="autocomplete"              
  parameters="branchName={batchReprintBranch.value},buName={batchReprintBusinessUnit.value}"
  minimumCharacters="3"
  preFunction="HideloadLastFiveDiv"
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
<th width="20%" colspan="2" >Invoice Search Results</th>

<th width="15%" style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></th>
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

<div style="float:left;width:100%;" style="visibility: visible;overflow-x:scroll;height:auto;overflow-y:hidden;#DBE2F2 1px solid;">

<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" frameborder="0" border="0" style="border-left-width:0px;#DBE2F2 1px solid;">
<tr>
	<th nowrap width="20"><input type='checkbox' name='selectall' id='selectall' onclick='javascript:checkall();' value="0000"/></th>
    <th nowrap width="50"><a href="#start" onClick="sortByInvoiceSearchHeader('jci.invoice');" >Invoice</a><a name="jci.invoice"></a></th>
    <th nowrap width="150"><a href="#start" onClick="sortByInvoiceSearchHeader('jci.creationUserName');" >Generated By</a><a name="jci.creationUserName"></a></th>
    <th nowrap><a href="#start" onClick="sortByInvoiceSearchHeader('jci.generatedDateTime');" >Generated On</a><a name="jci.invoiceDate"></a></th>
  </tr>
  
  <c:forEach items="${command.results}" var="jci" varStatus="status">
  
  <tr valign='center'>

	 <c:choose>
	 	<c:when test="${null == map or empty map}">
	 		<td width="20"><input type="checkbox" name="invoiceReprint[]" value="${jci.id}" onClick="validateCheck();"></td>
	 	</c:when>
	 	<c:otherwise>	
	 
			 <c:forEach items="${map}" var="chk">
				 <c:if test="${chk.key == jci.id}">
				 	<c:set var="flag" value="true"/>
				 </c:if>
			 </c:forEach>
			 
				<c:choose>
					<c:when test="${flag eq true}">
				    	<td width="20"><input type="checkbox" name="invoiceReprint[]" CHECKED value="${jci.id}" onClick="validateCheck();"></td>
				    </c:when>
				    <c:otherwise>
				    	<td width="20"><input type="checkbox" name="invoiceReprint[]" value="${jci.id}" onClick="validateCheck();"></td>
				    </c:otherwise>
			    </c:choose>
			  
			  <c:set var="flag" value="false"/>
			  
			  
		</c:otherwise>
	</c:choose>
    
    <td nowrap width="50" align='left' valign="middle"><a href="edit_job_view_invoice.htm?jobNumber=${jci.jobContract.jobNumber}"  title="Transfer to Invoice" >${jci.invoice}</a></td>
    <td nowrap width="150" align='left' valign="middle" nowrap ><span >${jci.creationUserName} </span></td>
    <td align='left' valign="middle" nowrap ><span ><f:formatDate type="both" value="${jci.generatedDateTime}"/></span></td>
  </tr>
</c:forEach>
<tr height="60"><td></td><td></td><td><input name="Button" type="button" class="button1" value="Invoice Reprint" style="border-width:1px;" onClick="invoiceReprint();"/></td><td></td></tr>
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
<td><table cellspacing="0" cellpadding="0" border="0">
    <tr>
      <td><a href="#" onClick="prevSearch(pageNumber,'${command.pagination.currentPageNum}')"><IMG SRC="images/navfirst.gif" ALT="First Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      <td><a href="#" onClick="previousSearch('${command.pagination.currentPageNum}')">
       <IMG SRC="images/navprevious.gif" ALT="Previous Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      <td><select name="goto" size="1" class="selectionBox" id="sel3" style="width:84px;" onchange="submitSearch(this.value,'${command.pagination.currentPageNum}')" >
      <option value="Go to page"><c:out value="Go to page" />
      <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
      <option value="${page.pageNumber}"><c:out value="${page.name}" />
      </c:forEach>
      </select></td>
      <td><a href="#" onClick="nextSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navnext.gif" ALT="Next Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      <td><a href="#" onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}','${command.pagination.currentPageNum}')"><IMG SRC="images/navlast.gif" ALT="Last Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
   </tr>
</table></td>

<td style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a>
</td>

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
  <option value="batch_reprint.htm"><f:message key="batchReprint"/></option>
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

</div>
</div>
</div>
</div>
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
<a href="#"  onclick="resetBranchTypeFlag()">    <img class="menu_form_exit"   id="jobbranchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Branch Code
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
src="search_user_popup.htm?inputFieldId=createdBy.value&div1=createdby&div2=createdbybody" scrolling="auto" id="searchCreatedFr" name="searchCreatedFr" allowtransparency="yes"></iframe></div>
</div>
<!------------------------------------ created By Lookup END----------------------------------------------------->

<!---------------------------------------modified By Lookup START------------------------------------------------->
<div class="sample_popup" id="modifiedby"  style="visibility: hidden; display: none;">
<div class="menu_form_header" id="modifiedby_drag" style="width:750px;">
<img class="menu_form_exit" id="modifiedby_exit" src="images/form_exit.png"/> &nbsp;&nbsp;&nbsp;<f:message key="searchUser" /></div>
<div class="menu_form_body" id="modifiedbybody" style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%"
src="search_user_popup.htm?inputFieldId=modifiedBy.value&div1=modifiedby&div2=modifiedbybody"scrolling="auto" id="searchModifiedFr" name="searchModifiedFr" allowtransparency="yes"></iframe></div>
</div>
<!------------------------------------ modified By Lookup END----------------------------------------------------->





<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left:1px;Top:1px;">
<IMG src="images/fake-alpha-999.gif"></div>

