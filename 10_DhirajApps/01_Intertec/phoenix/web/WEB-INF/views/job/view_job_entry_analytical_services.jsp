<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script type="text/javascript">
// adjust horizontal and vertical offsets here
// (distance from mouseover event which activates tooltip)
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


function setform()
{
  var code=document.getElementById("ccode");
  if(code.value==null || code.value=="")
  {
    confirm("Please Enter search string(s) delimited by a semicolon (;) ");
    return false;
  } 
  if(code.value!=null && code.value!="")
  {
  if (isAdd(code.value) == false) {
     confirm("Please Enter a Valid Delimiters");
    document.editJobsAnalyticalServicesForm.ccode.focus();
  document.editJobsAnalyticalServicesForm.ccode.value="";
  return false;
    }
  if (isProper(code.value) == false) {
  confirm("Please Enter a Valid Contract Id(s)");
  document.editJobsAnalyticalServicesForm.ccode.focus();
  document.editJobsAnalyticalServicesForm.ccode.value="";
  return false;
    }
  if (isValid(code.value) == false) {
  confirm("Please Enter a Valid Contract Id(s)");
  document.editJobsAnalyticalServicesForm.ccode.focus();
  document.editJobsAnalyticalServicesForm.ccode.value="";
  return false;
    }
document.editJobsAnalyticalServicesForm.uniqueFlag.value="uniqueFlag";
    document.editJobsAnalyticalServicesForm.submit();
    } 
}


function setDeleteflag(index)
{
if (confirm("Are you sure you want to delete the row?")==true)
{
document.editJobsAnalyticalServicesForm.jobFlag.value="none";
document.editJobsAnalyticalServicesForm.jobIndex.value = index;
document.editJobsAnalyticalServicesForm.deleteFlag.value ="delval";
document.editJobsAnalyticalServicesForm.submit();
}
}

function setsubflag()
{
document.editJobsAnalyticalServicesForm.jobValFlag.value="newjobval";
document.editJobsAnalyticalServicesForm.tabName.value = "1";
}

function setcontactflag(rowIndex)
{
document.editJobsAnalyticalServicesForm.jobFlag.value="none";
document.editJobsAnalyticalServicesForm.contactIndex.value=rowIndex;
document.editJobsAnalyticalServicesForm.contactFlag.value="contFlag";
document.editJobsAnalyticalServicesForm.tabName.value="1";
}

function setAttachFilesFlag(rowIndex)
{
document.editJobsAnalyticalServicesForm.attachFilesFlag.value=rowIndex;
document.editJobsAnalyticalServicesForm.tabName.value="1";
}

function isAdd(string) 
{
if (!string) return false;
var iChars = "*|,\":<>[]{}`\'()&$#%./!@~?><";
for (var i = 0; i < string.length; i++) {
if (iChars.indexOf(string.charAt(i)) != -1)
return false;
}
return true;
}

function isProper(string)
{
if (!string) return false;
var iChars = ";";
var i=string.length-1;
if (iChars.lastIndexOf(string.charAt(i)) != -1)
{    return false;}
return true;
} 

function isValid(string)
{
if (!string) return false;
var iChars = ";";
var i=string.length-1;
if (iChars.lastIndexOf(string.charAt(0)) != -1)
{    return false;}
return true;
} 

function goToNextPage()
{
  
  document.editJobsAnalyticalServicesForm.nextPageFlag.value = "1";
  top.document.forms[0].submit();
}

function setjobflag()
{
document.editJobsAnalyticalServicesForm.jobFlag.value="none";
 document.editJobsAnalyticalServicesForm.popFlag.value="none";
 document.editJobsAnalyticalServicesForm.contactFlag.value="none";
  document.editJobsAnalyticalServicesForm.uniqueFlag.value="none";
  setpage();
}
function checkSearchField()
{

  if(document.getElementById("jobSearch").value=="")
  {
  confirm("Please enter a search string");
  return false;
  }
  else
   submitform();
}
function resetJobValFlag()
{
 document.editJobsAnalyticalServicesForm.jobFlag.value="none";
}

 function updateJobDescription(urllink)
  {
   var w = document.editJobsAnalyticalServicesForm.sel5.selectedIndex;
   var ops_text = document.editJobsAnalyticalServicesForm.sel5.options[w].text;
 
  var etaDate=document.getElementById("etadate").value;
  var finishDate=document.getElementById("finishdate").value;
  var product=document.getElementById("product").value;
  var vessel=document.getElementById("vessel").value;

   var date=null;
   if(finishDate != "" && finishDate != null)
   {
     var dayfield=finishDate.split("/")[0]
     var monthfield=finishDate.split("/")[1]
     var yearfield=finishDate.split("/")[2]
       if(monthfield == 01 || monthfield == 1)
       date="January"+" "+dayfield+","+yearfield;
     if(monthfield == 02 || monthfield == 2)
       date="February"+" "+dayfield+","+yearfield;
     if(monthfield == 03 || monthfield == 3)
       date="March"+" "+dayfield+","+yearfield;
     if(monthfield == 04 || monthfield == 4)
       date="April"+" "+dayfield+","+yearfield;
     if(monthfield == 05 || monthfield == 5)
       date="May"+" "+dayfield+","+yearfield;
     if(monthfield == 06 || monthfield == 6)
       date="June"+" "+dayfield+","+yearfield;
     if(monthfield == 07 || monthfield == 7)
       date="July"+" "+dayfield+","+yearfield;
     if(monthfield == 08 || monthfield == 8)
       date="August"+" "+dayfield+","+yearfield;
     if(monthfield == 09 || monthfield == 9)
       date="September"+" "+dayfield+","+yearfield;
     if(monthfield == 10 )
       date="October"+" "+dayfield+","+yearfield;
     if(monthfield == 11 )
       date="November"+" "+dayfield+","+yearfield;
     if(monthfield == 12 )
       date="December"+" "+dayfield+","+yearfield;

   }
 else
   {
     var dayfield=etaDate.split("/")[0]
     var monthfield=etaDate.split("/")[1]
     var yearfield=etaDate.split("/")[2]
       if(monthfield == 01 || monthfield == 1)
       date="January"+" "+dayfield+","+yearfield;
     if(monthfield == 02 || monthfield == 2)
       date="February"+" "+dayfield+","+yearfield;
     if(monthfield == 03 || monthfield == 3)
       date="March"+" "+dayfield+","+yearfield;
     if(monthfield == 04 || monthfield == 4)
       date="April"+" "+dayfield+","+yearfield;
     if(monthfield == 05 || monthfield == 5)
       date="May"+" "+dayfield+","+yearfield;
     if(monthfield == 06 || monthfield == 6)
       date="June"+" "+dayfield+","+yearfield;
     if(monthfield == 07 || monthfield == 7)
       date="July"+" "+dayfield+","+yearfield;
     if(monthfield == 08 || monthfield == 8)
       date="August"+" "+dayfield+","+yearfield;
     if(monthfield == 09 || monthfield == 9)
       date="September"+" "+dayfield+","+yearfield;
     if(monthfield == 10 )
       date="October"+" "+dayfield+","+yearfield;
     if(monthfield == 11 )
       date="November"+" "+dayfield+","+yearfield;
     if(monthfield == 12 )
       date="December"+" "+dayfield+","+yearfield;
   }
  if(date == null)
  date="";

    var desc=ops_text+" "+"of"+" "+product+","+vessel+" "+"on"+" "+date+".";
  if(urllink=="")
    {
      document.getElementById("jobDesc").value=desc;
    }
    else
    {
     if(document.getElementById("jobDesc").value=="")
    document.getElementById("jobDesc").value=desc;
    }
  return document.getElementById("jobDesc").value;
  }
  function nextList()
  {
    //document.editJobsAnalyticalServicesForm.nextListFlag.value="next";
    document.editJobsAnalyticalServicesForm.showNextListFlag.value="next";
	// START : #119240
	document.editJobsAnalyticalServicesForm.jobFlag.value="none";
	document.editJobsAnalyticalServicesForm.deleteFlag.value="none";
	document.editJobsAnalyticalServicesForm.contactFlag.value="none";
	document.editJobsAnalyticalServicesForm.uniqueFlag.value="none";
	document.editJobsAnalyticalServicesForm.bankFlag.value="none";
	// END : #119240
    document.editJobsAnalyticalServicesForm.submit();
  }

  function prevList()
  {
    //document.editJobsAnalyticalServicesForm.prevListFlag.value="prev";
    document.editJobsAnalyticalServicesForm.showPrevListFlag.value="prev";
	// START : #119240
	document.editJobsAnalyticalServicesForm.jobFlag.value="none";
	document.editJobsAnalyticalServicesForm.deleteFlag.value="none";
	document.editJobsAnalyticalServicesForm.contactFlag.value="none";
	document.editJobsAnalyticalServicesForm.uniqueFlag.value="none";
	document.editJobsAnalyticalServicesForm.bankFlag.value="none";
	// END : #119240
    document.editJobsAnalyticalServicesForm.submit();
  }

// START : #119240
function noPrevList()
{
	document.editJobsAnalyticalServicesForm.noPrevJob.value="true";
	document.editJobsAnalyticalServicesForm.submit();
}

function noNextList()
{
	document.editJobsAnalyticalServicesForm.noNextJob.value="true";
	document.editJobsAnalyticalServicesForm.submit();
}
// END : #119240

   function checkDate()
  {
  
  var jobFinshDate = document.getElementById("finishdate").value;
  
  var dayfield=jobFinshDate.split("/")[0]
  var monthfield=jobFinshDate.split("/")[1]
  var yearfield=jobFinshDate.split("/")[2]
    
  var currentTime = new Date();
  var month = currentTime.getMonth() + 1;
  var day = currentTime.getDate();
  var year = currentTime.getFullYear();
  
   var jFdate = new Date(yearfield, monthfield-1, monthfield); 
   var toDate = new Date(year, month-1, day);
  
   if(jFdate > toDate ){
      confirm("Job Finish Date cannot be later than today!");
    document.getElementById("finishdate").value="";
      return false; 
   }else{
    submitform();
  }
  
  
 }
 function updateBranchIframeSrc()
  {
  var buName= document.getElementById("sel3").value;
  if(buName!= "" && buName!= null)
  {
    document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId=jobOrder.branchName&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=jobsForm");
  
  }
  
}
function makeBranchblank()
{
  document.getElementById("brnch").value="";
}
function setpage()
{
document.editJobsAnalyticalServicesForm.ccode.value="";
}

function submitform()
    {
      
      top.document.forms[0].submit();
      
    }
    
function setTabName(val)
{
document.editJobsAnalyticalServicesForm.tabName.value=val;
}   
function updateDescription(index)
{
  if(document.getElementById("invoiceDescrdetails"+index).value=="")
  {
  var description="General service charge in connection with"+" "+updateJobDescription()+".";
  document.getElementById("invoiceDescrdetails"+index).value=description;
  }
  else  if(document.getElementById("invoiceDescrdetails"+index).value!="")
  {
    document.getElementById("invoiceDescrdetails"+index).value="General service charge in connection with"+" "+updateJobDescription()+".";
    }
}
function enterButton() {
if (event.keyCode == 13) {
setform();
}
}
function updateBankAccountIframe(index,buname,currency){
var bcode=document.getElementById("remitto"+index).value;
document.getElementById('saccountframe'+index).setAttribute("src","search_bank_account_popup.htm?inputFieldId=addJobContracts["+index+"].jobContract.bankAcctKey&&rowNum="+index+"&buName="+buname+"&currency="+currency+"&bankCode="+bcode);
}

function updateBankIframe(index,buname,currency){ 
document.getElementById('sremittoframe'+index).setAttribute("src","search_bank_popup.htm?inputFieldId=addJobContracts["+index+"].jobContract.bankCd&rowNum="+index+"&buName="+buname+"&currency="+currency);
}

function updateBillingContactIframeSrc(index,customercode){
document.getElementById('searchContactFr'+index).setAttribute("src","search_contact_popup.htm?inputFieldId=addJobContracts["+index+"].jobContract.billingContact.id&rowNum="+index+"&searchForm=editJobsAnalyticalServicesForm&divName=searchbillingcontact"+index+"&divbody=contactbody"+index+"&custCode="+customercode);
}

function setbankFlag(index){
document.editJobsAnalyticalServicesForm.bankFlag.value="bankFlag";
document.editJobsAnalyticalServicesForm.tabName.value="1";
document.editJobsAnalyticalServicesForm.bankIndex.value=index;
 document.editJobsAnalyticalServicesForm.submit();
}

function monjobnumberFlag(index,jobnumber){
  
  if(document.getElementById("monFlag"+index).checked)
     {
    document.getElementById("mjobid"+index).className="unprotected";
    document.getElementById("monjobnumber"+index).value =jobnumber ;  
    document.getElementById("monjobnumber"+index).contentEditable="false";  
     }
     else  if(!document.getElementById("monFlag"+index).checked)
    {
           if (confirm("This will clear out the Monthly Job Number")==true)
               {
        document.getElementById("mjobid"+index).className="protected";
        document.getElementById("monjobnumber"+index).contentEditable="true"; 
        document.getElementById("monjobnumber"+index).value ="";
         }
         else
      {
            document.getElementById("monFlag"+index).checked=true;
      }
     }
  }
  function checkForRebill(elemid)
{
  var status = document.getElementById(elemid).value;
  if(status == '7100')
  {
    document.editJobsInspForm.rebillFlag.value="rebill";
    document.editJobsInspForm.submit();
  }
}

function contractAttach(contractCode,index)
  {
   document.getElementById('contractFr'+index).setAttribute("src","view_contract_attach_popup.htm?contractCode="+contractCode);
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
  background: url(images/tooltipbg.jpg) repeat-y;
  }
</style>
<icb:list var="divisions">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
  <icb:item>${command.jobOrder.branch.businessUnit.name}</icb:item>
</icb:list>
<icb:list var="searchfields">
  <icb:item>searchfields</icb:item>
</icb:list>
<icb:list var="jobContractStatus">
  <icb:item>jobContractStatus</icb:item>
</icb:list>
<icb:list var="origin">
  <icb:item>origin</icb:item>
</icb:list>
<icb:list var="selectedLanguage">
  <icb:item>selectedLanguage</icb:item>
</icb:list>
<icb:list var="invoiceType">
  <icb:item>invoiceType</icb:item>
</icb:list>
<icb:list var="paymentType">
  <icb:item>paymentType</icb:item>
</icb:list>
<body onkeyup="enterButton()"> 
<form:form name="editJobsAnalyticalServicesForm" method="POST" action="view_job_entry_analytical_service.htm">

<div style="color:red;"><form:errors cssClass="error"/></div>

     <form:hidden path="towingCompFlag" />
  <form:hidden path="shippingAgentFlag" />
  <form:hidden path="serviceLocationFlag" />
  <form:hidden path="tabName" />
    <form:hidden path="jobFlag"/>
  <form:hidden path="addOrDeleteJob"/>
  <form:hidden path="jobIndex"/>
  <form:hidden path="jobCount"/>
  <form:hidden path="inputFieldIdValue"/>
  <form:hidden path="deleteFlag"/>
  <form:hidden path="rowNum" />
  <form:hidden path="jobValFlag"/>
  <form:hidden path="contactIndex"/>
  <form:hidden path="contactFlag"/>
  <form:hidden path="attachedFileNames"/>
  <form:hidden path="attachFilesFlag"/>
  <form:hidden path="nextPageFlag" />
  <form:hidden path="uniqueFlag"/>
  <form:hidden path="popFlag"/>
  <form:hidden path="bankIndex"/>
  <form:hidden path="bankFlag"/>
  <form:hidden path="bankCode"/>
  <form:hidden path="rebillFlag"/>

  <c:set var="urlSuffix" value="${icbfn:urlSuffixByJobType(command.jobOrder.jobType)}" scope="request" /> 
    <c:set var="urlPrefix" value="${icbfn:urlPrefixByJobStatus(command.jobOrder.jobStatus)}" scope="request" />
    
    <icb:list var="jobStatus">
    <icb:item>jobStatus</icb:item>
    <icb:item>${command.jobOrder.jobStatus}</icb:item>
    <icb:item>${icbfn:jobContractCount(command.jobOrder.jobNumber)}</icb:item>
    <icb:item>${icbfn:invGeneratedFlag(command.jobOrder.jobNumber)}</icb:item>
	<icb:item>${command.jobOrder.jobNumber}</icb:item>
    <icb:item>${command.jobOrder.jobFinishDate}</icb:item>
  </icb:list>
  <!-- START : #119240 -->
 <!--  <input type="hidden" name="nextListFlag" value=""/> -->
 <!--  <input type="hidden" name="prevListFlag" value=""/> -->
  <input type="hidden" name="showNextListFlag" value=""/> 
	 <input type="hidden" name="showPrevListFlag" value=""/> 	
 <form:hidden path="nextListFlag" />
 <form:hidden path="prevListFlag" />
 <input type="hidden" name="noPrevJob" value=""/>
 <input type="hidden" name="noNextJob" value=""/> 	

 <c:if test="${requestScope['noJobMessage'] != null}">  
  <div style="color:green;">
    ${requestScope.noJobMessage}
  </div>
</c:if>
 <!-- END : #119240 --> 
 <!-- ------------------------------------------------------------------------------------------- MAIN CONTAINER ------------------------------------------------------------------------------------------->
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
            <td valign="top"><!-- BREADCRUMB TRAIL  -->
      <div id="breadcrumbContainer" >
        <table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/intopbluetrailbg.jpg">
          <tr>
            <td width="25"><img src="images/inlfttrailcorner.gif" width="8" height="22"></td>
            <td>
              <table height="22" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="breadcrumbtrailDeactive" style="background:none; padding-left:5px;">&#9658; 
                    <a href="${urlPrefix}_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}">
                     <f:message key="entry"/>
                    </a>
                  </td>
                  <td class="breadcrumbtrailDeactive">
                    <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 2}">               
                    <a href="${urlPrefix}_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}">
                     <f:message key="jobInstructions"/>
                    </a>
                    </c:when>
                    <c:otherwise>
                   <f:message key="jobInstructions"/>
                    </c:otherwise>
                    </c:choose>
                    </td>
                    <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 3}">               
                    <a href="edit_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}">
                     <f:message key="selectCharges"/>
                    </a>
                    </c:when>
                    <c:otherwise>
                    <f:message key="selectCharges"/> 
                    </c:otherwise>
                    </c:choose>
                    </td>
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 4}">               
                    <a href="edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}">
                      <f:message key="preview"/>
                    </a>
                    </c:when>
                    <c:otherwise>
                    <f:message key="preview"/>
                    </c:otherwise>
                    </c:choose>
                    </td>               
 
              <td class="breadcrumbtrailDeactive"> 
                   <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 5}">               
                    <a href="edit_job_view_invoice.htm?jobNumber=${command.jobOrder.jobNumber}">
                      <f:message key="invoice"/>
                    </a>
                    </c:when>
                    <c:otherwise>
                   <f:message key="invoice"/>
                    </c:otherwise>
                    </c:choose>  
                    </td>           
 
         
                   
                  
                  <td align="right">&nbsp;</td>
                </tr>
              </table>
 </td>
            <td align="right"><img src="images/inrttrailcorner.gif" width="7" height="22"></td>
          </tr>
        </table>
      </div>
      <!-- BREADCRUMB TRAIL END -->

<div id="MainContentContainer"> <!--------------------------------------------------------------------------------------------- TABS CONTENTS ----------------------------------------------------------------------------------------------> <div id="tabcontainer"> <div id="tabnav"> <ul> <li><a href="#" rel="tab1"><span><f:message key="entryForm" /></span></a></li> <li><a href="#" rel="tab2"><span><f:message key="addCustomers" /></span></a></li> </ul> </div> <!------------------------------------------------------------------------------------------ Sub Menus container. Do not remove ------------------------------------------------------------------------> <div id="tab_inner"> <!-- ----------- TAB 1 CONTAINER ------------------ --> <div id="tab1" class="innercontent"> <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable> <tbody>
<tr bgcolor=#ffffff> 
<th width="50%" colspan=2 nowrap><f:message key="jobId"/> : ${command.jobOrder.jobNumber}<img src=" images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px;margin-right:5px;"/><f:message key="jobTypeOCAASMNLaboratory"/></th>
<th colspan=3 width="50%" style="text-align:right">
<table width="104%" align="right" cellpadding="0" cellspacing="0"> <th nowrap style="border-bottom:none; padding-left:0px; padding-right:0px;"> <f:message key="status" />:
<form:select id="sel1" cssClass="selectionBox" path="jobOrder.jobStatus" items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name" itemValue="value" /> 
<form:errors path="jobOrder.jobStatus" cssClass="redstar" /> &nbsp;&nbsp; &nbsp;&nbsp; 
<!--form:select cssClass="selectionBox" id="sel2" path="searchField" items="${icbfn:dropdown('staticDropdown',searchfields)}" itemLabel="name" itemValue="value" />
<form:errors path="searchField" cssClass="redstar" /> &nbsp; 
<f:message key="id" />:&nbsp; <form:input id="jobSearch" size="12" cssClass="inputBox" path="searchString" onkeypress="if(event.keyCode==13) {checkSearchField();}"/> <form:errors path="searchString" cssClass="redstar" /> <input name="Submit2" type="button" class="button1" value="Go" style="height:18px;" onClick="javascript:checkSearchField();" /-->
<f:message key="projectType" />:&nbsp; 
<form:select id="projectTypes" cssClass="selectionBox" path="phxProjectType" items="${command.projectTypes}" itemLabel="name" itemValue="value" disabled="${command.projectTypeViewOnly}" />

</th> <th style="padding-right:6px; border-bottom:none">
<!-- START : #119240 -->
		  <c:choose>
            <c:when test="${command.originateFromSearchJob==null}">
				&nbsp;
            </c:when>
            <c:otherwise>
              <a href="${command.originateFromSearchJob}?jobNum=${command.jobNumber}">
			   <img src="images/icofindjob.gif" alt="Return to Search" width="16" height="14" border="0" align="absmiddle">			  
			  </a>
			</c:otherwise>
           </c:choose>        
		  <c:choose>
			<c:when test="${command.prevListFlag=='true'}">
              <a href="#" onClick="javascript:prevList();">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
             </c:when>
            <c:otherwise>
		      <c:if test="${command.originateFromSearchJob!=null}">
			  <a href="#" onClick="javascript:noPrevList();">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
			  </c:if>
	        </c:otherwise>
        </c:choose>   
	    <c:choose>
          <c:when test="${command.nextListFlag=='true'}">
              <a href="#" onClick="javascript:nextList();">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
          </c:when>
          <c:otherwise>
			<c:if test="${command.originateFromSearchJob!=null}">
			 <a href="#" onClick="javascript:noNextList();">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
			  </c:if>
          </c:otherwise>
        </c:choose>
		<!-- END : #119240 -->	  
<a href="#"  onclick="goToNextPage()"><img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#" onClick="updateJobDescription();setTabName('0');checkDate();"><img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle" /></a></th> </tr> </table>

</th> </tr> <tr>

<tr>

<td class="TDShade"><f:message key="jobFinishedDate"/>: </td> <td colspan="1" class="TDShadeBlue"><form:input id="finishdate" disabled="true" size="52" cssClass="inputBox" path="jobOrder.jobFinishDate"/> <form:errors path="jobOrder.jobFinishDate" cssClass="redstar"/></td>

<td class="TDShade"><f:message key="turnaroundTime"/>:</td> <td class="TDShadeBlue"> <form:input disabled="true" size="52" cssClass="inputBox" maxlength="100" path="jobOrder.turnaroundTime"/> <form:errors path="jobOrder.turnaroundTime" cssClass="redstar"/></td>

</tr>

<tr> <td width="15%" class="TDShade"><strong><f:message key="businessUnit"/>:</strong><span class="redstar">*</span> </td> <td width="35%" class="TDShadeBlue"><span class="id_input"> <form:select id="sel3" disabled="true" cssClass="selectionBoxbig" path="jobOrder.buName" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value"  onchange="makeBranchblank()"/> <form:errors path="jobOrder.buName" cssClass="redstar"/> </span></td>
<td width="15%" class="TDShade"><strong><f:message
          key="branchCode" />:<span class="redstar">*</span></strong></td>
<td width="35%" colspan="2" class="TDShadeBlue">
<form:input id="brnch" disabled="true" size="52" 
  cssClass="inputBox" path="jobOrder.branchName"/>
<form:errors path="jobOrder.branchName"
  cssClass="redstar" />
<%-- <a href="#" onClick="javascript:updateBranchIframeSrc();popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 80, 80);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()"><img
  src=" images/lookup.gif" width="13" height="13" border="0" />
 </a> --%></td>
</tr> <icb:list var="operations">
<icb:item>${command.jobOrder.jobType}</icb:item>
</icb:list>

<tr>
<td class="TDShade"><strong><f:message key="operation" />:<span class="redstar">*</span></strong></td>
<td class="TDShadeBlue"><form:select disabled="true" id="sel5"  cssClass="selectionBoxbig" path="jobOrder.operation"  items="${icbfn:dropdown('jobOperations', operations)}" itemLabel="name" itemValue="value" /> <form:errors path="jobOrder.operation" cssClass="redstar" /></td>
 <td class="TDShade"><strong><f:message key="salesRep"/>: </strong></td> <td colspan="2" class="TDShadeBlue"> <form:input cssClass="inputBox" disabled="true" size="52" maxlength="128" path="jobOrder.receivedByUserName"/> <form:errors path="jobOrder.receivedByUserName" cssClass="redstar"/><a href="#" onClick="javascript:popup_show('salesrep','salesrep_drag','salesrep_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('salesrep','salesrepbody');popupboxenable();"> <%--  <img src=" images/lookup.gif" width="13" height="13" border="0" /></a> --%>
 </td> </tr> <tr> <td class="TDShade"><f:message key="quoteIssuedDate"/>:</td> <td class="TDShadeBlue"> <input name="textfield92" type="text" class="inputBox" size="23" disabled="true" />

 &nbsp;&nbsp;<strong><f:message key="quoteID"/>:</strong></td> <td class="TDShade"><f:message key="adminContact"/>:</td> <td colspan="2" class="TDShadeBlue"> <form:input disabled="true" size="52" cssClass="inputBox"  path="jobOrder.adminContactUserName"/> <form:errors path="jobOrder.adminContactUserName" cssClass="redstar"/> <a href="#" onClick="javascript:popup_show('admincontact','admincontact_drag','admincontact_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('admincontact','admincontactbody');popupboxenable();"><%-- <img src=" images/lookup.gif" width="13" height="13" border="0" /> --%> </a></td> </tr> <tr> <td class="TDShade"><strong><f:message key="nominationDate"/>:<span class="redstar">*</span></strong></td> <td class="TDShadeBlue"><form:input disabled="true" size="23" id="nomdate" cssClass="inputBox" path="jobOrder.nominationDate"/> <form:errors path="jobOrder.nominationDate" cssClass="redstar"/> &nbsp;&nbsp;<f:message key="time"/>:&nbsp <form:input disabled="true" size="4" cssClass="inputBox" path="uiNominationTime" /> <form:errors path="uiNominationTime" cssClass="redstar" /> <form:input disabled="true" size="4" cssClass="inputBox"  path="jobOrder.nominationTimeTz"/> <form:errors path="jobOrder.nominationTimeTz" cssClass="redstar"/> <a href="#" onClick="javascript:popup_show('timezone','timezone_drag','timezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('timezone','timezonebody');popupboxenable();"><%-- <img src=" images/lookup.gif" width="13" height="13" border="0" /> --%> </a>

<div id="debug"></div></td> <td class="TDShade"><strong><f:message key="eta"/>:</strong></td> <td colspan="2" class="TDShadeBlue"><form:input id="etadate" disabled="true" size="23" cssClass="inputBox" path="jobOrder.etaDate"/> <form:errors path="jobOrder.etaDate" cssClass="redstar"/>&nbsp;&nbsp;<f:message key="time"/>:&nbsp; <form:input disabled="true" size="4" cssClass="inputBox" path="uiEtaTime" /> <form:errors path="uiEtaTime" cssClass="redstar" /> <form:input disabled="true" size="4" cssClass="inputBox" path="jobOrder.etaTimeTz"/> <form:errors path="jobOrder.etaTimeTz" cssClass="redstar"/> <a href="#" onClick="javascript:popup_show('etatimezone','etatimezone_drag','etatimezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('etatimezone','etatimezonebody');popupboxenable();"> <%-- <img src=" images/lookup.gif" width="13" height="13" border="0" /> --%> </a> </td> </tr>



<tr> <td class="TDShade" style="border-bottom:#CCCCCC dashed 1px;"><strong> <f:message key="sampleType"/>:</strong></td> <td class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px;">&nbsp;</td>
<div style="visibility: hidden; display: none;"> <form:input id="vessel" size="45"
          cssClass="inputBox" maxlength="254"
          path="jobOrder.vesselNames"/></div>
<td class="TDShade" style="border-bottom:#CCCCCC dashed 1px;"><strong> <f:message key="product"/>:</strong></td><td colspan="2" class="TDShadeBlue"
          style="border-bottom:#7c92be dashed 1px;"> ${command.jobOrder.productNames}</td>
          <div style="visibility: hidden; display: none;"> <form:input id="product" size="45"
          cssClass="inputBox" maxlength="254"
          path="jobOrder.productNames"/></div></tr>

 <tr> <td valign="top" class="TDShade" ><strong><f:message key="jobDescription"/>:</strong></td> <td colspan="4" class="TDShadeBlue"><form:textarea disabled="true" path="jobOrder.jobDescription" rows="5" cols="100" id="jobDesc"/> <form:errors path="jobOrder.jobDescription" cssClass="redstar"/> </td>

</tr> </table> <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot"> <tr> <td><table width="100%" border="0" cellspacing="0" cellpadding="0"> <tr> <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry"/></span> </td>
 <%-- <c:choose>
            <c:when test="${command.originateFromSearchJob==null}">
            <td>&nbsp;</td>
            </c:when>
            <c:otherwise>
              <td><a href="${command.originateFromSearchJob}?jobNum=${command.jobNumber}"><f:message key="returnToSearch" /></a></td>
         </c:otherwise>
            </c:choose>
        <c:choose>
          <c:when test="${command.nextListFlag=='true'}">
              <td><a href="#" onClick="javascript:nextList();"><f:message key="nextInList"/></a></td>
         </c:when>
            <c:otherwise>
      <td>&nbsp;</td>
       </c:otherwise>
            </c:choose>
       <c:choose>
          <c:when test="${command.prevListFlag=='true'}">
              <td><a href="#" onClick="javascript:prevList();"><f:message key="previousInList"/></a></td>
             </c:when>
            <c:otherwise>
      <td>&nbsp;</td>
       </c:otherwise>
       </c:choose> --%>
<td style="text-align:right">
	<!-- START : #119240 -->
		  <c:choose>
            <c:when test="${command.originateFromSearchJob==null}">
				&nbsp;
            </c:when>
            <c:otherwise>
              <a href="${command.originateFromSearchJob}?jobNum=${command.jobNumber}">
			   <img src="images/icofindjob.gif" alt="Return to Search" width="16" height="14" border="0" align="absmiddle">			  
			  </a>
			</c:otherwise>
           </c:choose>        
		  <c:choose>
			<c:when test="${command.prevListFlag=='true'}">
              <a href="#" onClick="javascript:prevList();">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
             </c:when>
            <c:otherwise>
		     <c:if test="${command.originateFromSearchJob!=null}">
			  <a href="#" onClick="javascript:noPrevList();">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
			  </c:if>
	        </c:otherwise>
        </c:choose>   
	    <c:choose>
          <c:when test="${command.nextListFlag=='true'}">
              <a href="#" onClick="javascript:nextList();">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
          </c:when>
          <c:otherwise>
			 <c:if test="${command.originateFromSearchJob!=null}">
			 <a href="#" onClick="javascript:noNextList();">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
			  </c:if>
          </c:otherwise>
        </c:choose>
		<!-- END : #119240 -->	  
<a href="#" onClick="goToNextPage()"><img src=" images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#" onClick="updateJobDescription();checkDate();"><img src=" images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle" /></a></td> </tr> </table></td> </tr> </table> </div>





  <ajax:autocomplete
  baseUrl="joborder.htm"
  source="jobOrder.nominationTimeTz"
  target="jobOrder.nominationTimeTz"
  className="autocomplete"
  parameters="nominationTimeZone={jobOrder.nominationTimeTz}"
  minimumCharacters="3"
   /> 
    <ajax:autocomplete
  baseUrl="joborder.htm"
  source="jobOrder.etaTimeTz"
  target="jobOrder.etaTimeTz"
  className="autocomplete"
  parameters="etaTimeZone={jobOrder.etaTimeTz}"
  minimumCharacters="3"
   /> 
 

<ajax:autocomplete
  baseUrl="joborder.htm"
  source="jobOrder.receivedByUserName"
  target="jobOrder.receivedByUserName"
  className="autocomplete"
  parameters="recivedBy={jobOrder.receivedByUserName}"
  minimumCharacters="3"
   /> 

     <ajax:autocomplete
  baseUrl="joborder.htm"
  source="jobOrder.adminContactUserName"
  target="jobOrder.adminContactUserName"
  className="autocomplete"
  parameters="recivedBy={jobOrder.adminContactUserName}"
  minimumCharacters="3"
   /> 
<%--    <ajax:autocomplete
  baseUrl="joborder.htm"
  source="jobOrder.projectNumber"
  target="jobOrder.projectNumber"
  className="autocomplete"
  parameters="projectNumber={jobOrder.projectNumber}"
  minimumCharacters="3" /> --%>
  <ajax:autocomplete
  baseUrl="branch.htm"
  source="jobOrder.branchName"
  target="jobOrder.branchName"
  className="autocomplete"              
  parameters="branchName={jobOrder.branchName},buName={jobOrder.buName}"
  minimumCharacters="3"
 />
  

<!-- ---------------------- TAB 1 CONTAINER END ---------------------------- -->
<!-- -------------------------------------------------------------------------------------- TAB 2 CONTAINER ------------------------------------------------------------------------------------------- -->
<div id="tab2" class="innercontent" >
<table class=mainApplTable cellspacing=0 cellpadding=0 width="100%">
<tbody>
<tr bgcolor=#ffffff>
<th><f:message key="contractEntry"/>
 <img src=" images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px;margin-right:5px;" />
 <f:message key="jobId" />: ${command.jobOrder.jobNumber}<img src=" images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px;margin-right:5px;"/>
 <f:message key="jobTypeOCAASMNLaboratory"/>&nbsp;&nbsp;&nbsp;
<f:message key="status" />:<form:select id="sel1" cssClass="selectionBox" path="jobOrder.jobStatus" items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name" itemValue="value" disabled="true"/> <form:errors path="jobOrder.jobStatus"
cssClass="redstar"/>&nbsp;&nbsp;       
 </th>

<th style="text-align:right"><a href="#"  onclick="updateJobDescription();goToNextPage()"><img src=" images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#"><img src=" images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle"  onclick="updateJobDescription();top.document.editJobsAnalyticalServicesForm.submit();" /></a></th>
</tr>

<tr>
<td colspan="4" class="TDShade" style="font-weight:normal;"><f:message key="contractIDDescription"/></td>
</tr>

<tr>
<td colspan="4" class="TDShade"><strong><f:message key="addContracts"/>:</strong>
<form:input  disabled="true" id="ccode" cssClass="inputBox" path="contractCode.value" size="50" maxlength="100"/>
<form:errors path="contractCode.value" cssClass="redstar"/> 
<label>
 <input name="Add" type="button" class="button1" value="Add" disabled="true"  onClick="setform();"/>
</label>
</td>
</tr>
<c:choose>
<c:when test="${command.popFlag=='none'}">
<script type="text/javascript">
setpage();
</script>
</c:when>
</c:choose>
</tbody>
</table>

<!------------------------------------------------------------------------------------------- Job Contract Lookup ----------------------------------------------------------------------------------------->
<div class="sample_popup"     id="popup2" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="popup_drag2" style="width:600px;">
<a href="#"  onclick="setjobflag()"><img class="menu_form_exit"   id="popup_exit2" src=" images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="contractSearchResults"/></div>
<div class="menu_form_body"  id="popup2body "  style="overflow:auto; height:400px; width:600px;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr>
<iframe id="pu" align="left" frameborder="0" style="padding:0px;" height="400px;" width="100%" scrolling="auto" id="searchjobContractFr" src="blank.html"
name="searchjobContractFr" allowtransparency="yes" ></iframe>
</tr>
</table>
</div>
</div>
<!-- ------------------------------------------------------------------------------------------- Job Contract Lookup END ------------------------------------------------------------------------------->
<c:choose>
<c:when test="${command.popFlag=='true'}">
<script type="text/javascript">
setflag();
popup_show('popup2', 'popup_drag2', 'popup_exit2', 'screen-corner', 120, 20);
hideIt();
popupboxenable();
function setflag()
{  
document.editJobsAnalyticalServicesForm.jobFlag.value="jobval";
document.editJobsAnalyticalServicesForm.tabName.value = "1";
var code= document.getElementById("ccode").value;
if(code!= "" && code!= null)
{
document.getElementById('pu').setAttribute("src","search_job_contract_insp_popup.htm?inputFieldId=inputFieldIdValue&searchForm=editJobsAnalyticalServicesForm&code="+code+"&fromPage=8");
}
}
</script>
</c:when>
</c:choose>
<!-- -------------------------------------------------------------------------------------------- Contract Details Container ----------------------------------------------------------------------------->
<div id="contractdetails"><br>
<table width="100%" cellpadding="0" cellspacing="0" cols="1" dir="ltr" class="InnerApplTable" id="RO_LINEDISP_WRK$scroll$0">
<tr>
<td colspan="2" style="padding-bottom:6px;padding-left:1px;">
<!-- Contract 1 -->

<c:forEach items="${command.addJobContracts}" var="addJobContracts" varStatus="counter">
<div id="contract${counter.index}">

<table border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-bottom:0px;" >
<tr valign="center">
<th width="16" rowspan="2" align="center" valign="top" scope="col" style="background-image:url( images/arrowblubg.gif);"> 

<div id="bluarrowright${counter.index+1}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> 

<a href="#" onClick="javascript:showOriginTable('origintable${counter.index+1}','bluarrowdown${counter.index+1}','bluarrowright${counter.index+1}');"> 

<img src=" images/bluarrowright.gif" width="8" height="16" border="0" vspace="6"/></a> </div>

<div id="bluarrowdown${counter.index+1}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hideOriginTable('origintable${counter.index+1}','bluarrowright${counter.index+1}','bluarrowdown${counter.index+1}');"> 

<img src=" images/bluarrowdown.gif" width="16" height="8" border="0" vspace="6"/></a></div></th>

<th scope="col" align="left">&nbsp;</th>
<th width="30" scope="col" align="left"><f:message key="nom"/></th>
<th width="150" scope="col"  align="left"><f:message key="customerName"/></th>
<th width="100" scope="col" align="left"><f:message key="contact"/></th>
<th width="100" scope="col" align="left"><f:message key="contractID"/></th>
<th width="150" scope="col" align="left"><f:message key="contractDescription"/></th>
<th align="left" width="5%"><f:message key="status"/></th>
<th width="20" align="left" scope="col">&nbsp;</th>
<th width="20" align="left" scope="col">&nbsp;</th>
<th width="20" align="left" scope="col">&nbsp;</th>
</tr>

<tr valign="center">
<td align="center" width="18">${counter.index+1}</td>

<c:choose>
<c:when test="${counter.index==0}">
<td nowrap="nowrap" align="center"><form:checkbox id="nom" disabled="true" path="addJobContracts[${counter.index}].jobContract.nominationFlag"  value="Y"/></td>
<script type="text/javascript">
setNomCheck('${counter.index}');
function setNomCheck(index)
{
  document.getElementById("nom").checked = true;
}
</script>
</c:when>
<c:otherwise>
<td nowrap="nowrap" align="center"><form:checkbox id="nom1" disabled="true" path="addJobContracts[${counter.index}].jobContract.nominationFlag"  value="Y"/></td>
</c:otherwise>
</c:choose>

<td width="150" align="left">${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.name}</td>


<td align="left"><a href="#" style="cursor:hand;" onMouseOver="doTooltip(event, '<table><tr><td width=120px><b>Scheduler ID:</b></td><td>${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.id} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Company ID:</b>${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.custCode}</td></tr><tr><td valign=top><b>Scheduler Address:</b></td> <td>${command.addJobContracts[counter.index].schedulerAddress}</td></tr><tr><td><b>Telephone:</b></td>  <td>${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.workPhone}</td></tr><tr><td><b>Email Address:</b></td><td>${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.workEmail}</td></tr></table>')" onMouseOut="hideTip()">${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.firstName} ${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.lastName}</a></td>

<td align="left"><a href="#" onclick="contractAttach('${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}','${counter.index}');popup_show('contractattach${counter.index}', 'contractattach_drag${counter.index}', 'contractattach_exit${counter.index}', 'screen-corner', 80, 35);hideIt();popupboxenable();">${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}</a></td>

<td align="left">${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.description}</td>

<td align="left">
<icb:list var="jobContractStatus">
  <icb:item>jobContractStatus</icb:item>
  <icb:item>${command.addJobContracts[counter.index].jobContract.jobContractStatus}</icb:item>
</icb:list>
<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.jobContractStatus=='6000'}">       
<form:select id="jobContractStatus${counter.index}" disabled="true" cssClass="selectionBox" path="addJobContracts[${counter.index}].jobContract.jobContractStatus" items="${icbfn:dropdown('staticDropdown',jobContractStatus)}"   itemLabel="name" itemValue="value" onchange="checkForRebill('jobContractStatus${counter.index}')"/> 
</c:when>
<c:otherwise>
<form:select id="sel3" cssClass="selectionBox" path="addJobContracts[${counter.index}].jobContract.jobContractStatus" items="${icbfn:dropdown('staticDropdown',jobContractStatus)}"  itemLabel="name" itemValue="value" disabled="true"/> 
</c:otherwise>
</c:choose>
<form:errors path="addJobContracts[${counter.index}].jobContract.jobContractStatus" cssClass="redstar" />
</td>
<td align="center"> <a href="#" onClick="javascript:if(${command.addJobContracts[counter.index].jobContract.id}>0){popup_show('addnote${counter.index}', 'addnote_drag${counter.index}', 'addnote_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();} else {confirm('Please save first');}"> <img src="images/icoaddnote.gif" alt="Add a note" width="18" height="15" border="0" /> </a> </td>

<td align="center">
 <a href="#" onClick="javascript:setAttachFilesFlag(${counter.index });popup_show('attach${counter.index}', 'attach_drag${counter.index}', 'attach_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();">
<img src=" images/icoattach.gif" alt="Add an attachment" width="13" height="16" border="0" /> </a> </td>

<td align="center"></td>

</tr>
</table>
</div>

<!---------------------------------------------- Job Contract Add Note  Lookup -------------------------------------------------------->
<div class="sample_popup"     id="addnote${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="addnote_drag${counter.index}" style="width:640px; ">
<img class="menu_form_exit"   id="addnote_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="addNotes"/></div>
<div class="menu_form_body" style="width:640px; height:290px;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr>
<iframe align="left" frameborder="0" style="padding:0px;" height="290px;" width="100%" src="create_job_add_customer_note_popup.htm?jobContractId=${command.addJobContracts[counter.index].jobContract.id}&divName=note${counter.index}" scrolling="auto" id="jobContractAttachFileFr" name="jobContractAttachFileFr" allowtransparency="yes" ></iframe>
</tr>
</table>
</div>
</div>
<!-- ---------------------------------------------------------------------------- Job Contract Add Note  Lookup End----------------------------------------------------------------------------------->

<!-- ------------------------------------------------------------------------------ Attach File Lookup  ----------------------------------------------------------------------------------------------------->
<div class="sample_popup"     id="attach${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="attach_drag${counter.index}" style="width:700px; "> 
<img class="menu_form_exit"   id="attach_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="attachFiles"/> </div>
<div class="menu_form_body" style="width:700px; height:300px;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr>
<iframe align="left" frameborder="0" style="padding:0px;" height="300px;" width="100%" src="create_job_add_customer_attachfile_popup.htm?contractCode=${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}&jobContractId=${command.addJobContracts[counter.index].jobContract.id}&jobNumber=${command.addJobContracts[counter.index].jobContract.jobNumber}&divName=attach${counter.index}&inputFieldId=attachedFileNames" scrolling="auto" id="jobContractAttachFileFr" name="jobContractAttachFileFr" allowtransparency="yes" ></iframe>
</tr>
</table>
</div>
</div>
<!-- ------------------------------------------------------------------------------- Attach File Lookup End------------------------------------------------------------------------------------------------>

<icb:list var="contracts">
<icb:item>${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}</icb:item>
<icb:item>${command.jobOrder.jobFinishDate}</icb:item>
<icb:item>${command.jobOrder.etaDate}</icb:item>  
 <icb:item>${command.addJobContracts[counter.index].priceBookId}</icb:item>
</icb:list>
                      
<icb:list var="currencies">
<icb:item>${command.addJobContracts[counter.index].cfgContract.currencyCD}</icb:item>
<icb:item><f:formatDate value="${command.jobOrder.jobFinishDate}" type="date" dateStyle="short" /></icb:item>
</icb:list>

<div id="origintable${counter.index+1}" style="padding:0px; visibility:hidden; display:none;">
<table border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-top:0px; border-top:none" >

<tr valign="center">
<td width="20"><img src=" images/spacer.gif" width="18" height="1" /></td>
<td colspan="8" style="padding:0px;">
<table border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="width:98%; margin:1px; border:none">

<tr>
<c:choose>
<c:when test="${command.jobOrder.jobType=='INSP'}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:when test="${command.jobOrder.jobType=='MAR'}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey"><strong><f:message key="selectProject"/>:</strong></td>
<td><form:input disabled="true" cssClass="inputBox" maxlength="128" size="43" path="addJobContracts[${counter.index}].jobContract.projectNumber"/>
 <form:errors path="addJobContracts[${counter.index}].jobContract.projectNumber" cssClass="redstar"/>
  <a href="#" onClick="javascript:popup_show('searchprojects${counter.index}','searchprojects_drag${counter.index}','searchprojects_exit${counter.index}', 'screen-corner', 120, 20);showPopupDiv('searchprojects${counter.index}','searchprojectsbody${counter.index}');hideIt();popupboxenable();">
  </a></td>
</c:otherwise>
</c:choose>

<td class="TDShadeGrey"><f:message key="monthlyJobId"/>:</td>
<td> <form:input disabled="true" cssClass="inputBox" size="41" maxlength="128" path="addJobContracts[${counter.index}].jobContract.monthlyJobNumber"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.monthlyJobNumber" cssClass="redstar"/>
<form:checkbox  disabled="true" path="addJobContracts[${counter.index}].jobContract.monthlyFlag" value="Y" /></td>
</tr>
<tr>
<td width="25%" class="TDShadeGrey"><f:message key="origin"/></td>
<td width="30%"><form:select disabled="true" id="sel6" cssClass="selectionBoxmid" path="addJobContracts[${counter.index}].jobContract.custSentBy" items="${icbfn:dropdown('staticDropdown',origin)}" itemLabel="name" itemValue="value"/></td>
<td width="20%" class="TDShadeGrey"><f:message key="custRefNum"/></td>
<td><form:input disabled="true" cssClass="inputBox" size="40" maxlength="40" path="addJobContracts[${counter.index}].jobContract.custRefNum"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.custRefNum" cssClass="redstar"/></td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="selectedLanguage"/></td>
<td><form:select disabled="true" id="sel7" cssClass="selectionBoxmid" path="addJobContracts[${counter.index}].jobContract.language" items="${icbfn:dropdown('staticDropdown',selectedLanguage)}" itemLabel="name" itemValue="value"/></td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel1==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel1}</td>
<td><form:input disabled="true" cssClass="inputBox" size="46" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue1"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue1" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="contractCurrency"/></td>
<td> ${command.addJobContracts[counter.index].cfgContract.currencyCD}</td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel2==null}">
<td  class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td  class="TDShadeGrey" >${command.addJobContracts[counter.index].jobContract.invoiceLabel2}</td>
<td><form:input disabled="true" cssClass="inputBox" size="46" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue2"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue2" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td nowrap class="TDShadeGrey"><f:message key="transactionCurrencyMultiplier"/></td>
<td><form:select disabled="true" id="sel8" cssClass="selectionBoxmid" path="addJobContracts[${counter.index}].jobContract.transactionCurrencyCd" items="${icbfn:dropdown('currencyTrans', currencies)}" itemLabel="name" itemValue="value"/></td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel3==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel3}</td>
<td><form:input disabled="true" cssClass="inputBox" size="46" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue3"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue3" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="productType"/></td>
<td><form:select disabled="true" id="sel9" cssClass="selectionBoxmid" path="addJobContracts[${counter.index}].jobContract.productType" items="${icbfn:dropdown('productType', null)}" itemLabel="name" itemValue="value"/></td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel4==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel4}</td>
<td><form:input disabled="true" cssClass="inputBox" size="46" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue4"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue4" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="zoneDescription"/></td>
<td><form:select disabled="true" id="sel10" cssClass="selectionBoxmid" path="addJobContracts[${counter.index}].jobContract.locationDesc" items="${icbfn:dropdown('portCode', contracts)}" itemLabel="name" itemValue="value" /></td> 

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel5==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel5}</td>
<td><form:input disabled="true" cssClass="inputBox" size="46" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue5" />
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue4" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<icb:list var="pcodes">
  <icb:item>${command.addJobContracts[counter.index].jobContract.locationDesc}</icb:item>
  <icb:item>${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}</icb:item>
  <icb:item>${command.addJobContracts[counter.index].jobContract.zone}</icb:item>
  <icb:item>${command.jobOrder.jobFinishDate}</icb:item>
  <icb:item>${command.jobOrder.etaDate}</icb:item>  
  <icb:item>${command.addJobContracts[counter.index].priceBookId}</icb:item>
 </icb:list>

<tr>
<td class="TDShadeGrey" style="border-bottom:#5B5B5B dashed 1px; "><f:message key="zoneId"/>: </td>
<td style="border-bottom:#5B5B5B dashed 1px; ">
<form:select disabled="true" id="sel11" cssClass="selectionBoxmid" path="addJobContracts[${counter.index}].jobContract.zone" items="${icbfn:dropdown('zoneId', pcodes)}" itemLabel="name" itemValue="value" />
</td>
<td class="TDShadeGrey" style="border-bottom:#5B5B5B dashed 1px; "><f:message key="vatRegId"/>:</td>
<%-- <td  style="border-bottom:#5B5B5B dashed 1px; ">${command.addJobContracts[counter.index].vatRateCountry}</td> --%>
<td>
<icb:list var="vatRegParams">
<icb:item>${command.addJobContracts[counter.index].jobContract.custCode}</icb:item>
</icb:list>
<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[counter.index].jobContract.jobContractStatus=='7200'}">				      
<form:select id="sel4" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.vatRegId" items="${icbfn:dropdown('vatRegIds', vatRegParams)}" itemLabel="name" itemValue="value" disabled="true"/>
</c:when>
<c:otherwise>
<form:select id="sel4" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.vatRegId" items="${icbfn:dropdown('vatRegIds', vatRegParams)}" itemLabel="name" itemValue="value"/>
</c:otherwise>
</c:choose>
<form:errors path="addJobContracts[${counter.index}].jobContract.vatRegId" cssClass="error"/>
</td>
</tr>


<tr>
<td class="TDShadeGrey"><f:message key="billingContact"/>:</td>
<td><form:input disabled="true" cssClass="inputBox"  size="12"  path="addJobContracts[${counter.index}].jobContract.billingContact.id"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.billingContact.id" cssClass="redstar"/>
&nbsp;<a href="#" onClick="javascript:setcontactflag(${counter.index});popup_show('searchbillingcontact${counter.index}', 'searchbillingcontact_drag${counter.index}', 'searchbillingcontact_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();"><%-- <img src=" images/lookup.gif" width="13" height="13" border="0" /> --%> </a>&nbsp; 
<form:input  cssClass="inputBox"  size="18" maxlength="50" path="addJobContracts[${counter.index}].jobContract.billingContactName" disabled="true"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.billingContactName" cssClass="redstar"/></td>
<td class="TDShadeGrey"><f:message key="invoiceType"/>:</td>
<td>
<form:select id="sel12" disabled="true" cssClass="selectionBoxmid" path="addJobContracts[${counter.index}].jobContract.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value"/> </td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="billingAddress"/>: </td>
<td colspan="3"><form:input cssClass="inputBox"   size="127" path="addJobContracts[${counter.index}].billingAddress" disabled="true"/></td>
</tr>

<tr>
<td valign="top" class="TDShadeGrey"><f:message key="remitTo"/>:</td>
<td><form:input disabled="true" id="remitto${counter.index}" cssClass="inputBox" size="12" maxlength="5"  path="addJobContracts[${counter.index}].jobContract.bankCd"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.bankCd" cssClass="redstar"/>
&nbsp;<a href="#" onClick="javascript:updateBankIframe('${counter.index}','${command.jobOrder.buName}','${command.addJobContracts[counter.index].cfgContract.currencyCD}');popup_show('searchremitto${counter.index}', 'searchremitto_drag${counter.index}', 'searchremitto_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchremitto${counter.index}','remittobody${counter.index}');"> <%-- <img src=" images/lookup.gif" width="13" height="13" border="0" /> --%> </a>&nbsp;


<f:message key="acct"/>:
<form:input disabled="true" cssClass="inputBox" size="12" maxlength="4"  path="addJobContracts[${counter.index}].jobContract.bankAcctKey"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.bankAcctKey" cssClass="redstar"/>&nbsp;
<a href="#" onClick="javascript:updateBankAccountIframe('${counter.index}','${command.jobOrder.buName}','${command.addJobContracts[counter.index].cfgContract.currencyCD}');popup_show('searchaccount${counter.index}', 'searchaccount_drag${counter.index}', 'searchaccount_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchaccount${counter.index}','accountbody${counter.index}');"> <%-- <img src=" images/lookup.gif" width="13" height="13" border="0"/> --%> </a></td>

<td nowrap class="TDShadeGrey"><f:message key="depositIssuedByBank"/></td>
<td> <form:input disabled="true" cssClass="inputBox" size="46" maxlength="4"path="addJobContracts[${counter.index}].jobContract.depositNo"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.depositNo" cssClass="redstar"/></td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="paymentType"/>:</td>
<td><form:select disabled="true" id="sel13" cssClass="selectionBoxmid" path="addJobContracts[${counter.index}].jobContract.pymntType" items="${icbfn:dropdown('staticDropdown',paymentType)}" itemLabel="name" itemValue="value"/></td>
<td class="TDShadeGrey"><f:message key="paymentterms"/>:</td>
<td><form:select disabled="true" id="sel14" cssClass="selectionBoxmid" path="addJobContracts[${counter.index}].jobContract.pymntTermsCd" items="${icbfn:dropdown('paymentTerms', null)}" itemLabel="name" itemValue="value"/></td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="creditApplicationApproved"/>:<span class="redstar">*</span></td>
<td>
<form:checkbox id="creapp${counter.index}" path="addJobContracts[${counter.index}].jobContract.contractCustContact.contractCust.customer.creditApproved" value="1" disabled="true"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.contractCustContact.contractCust.customer.creditApproved" cssClass="redstar"/>
</td>
<td class="TDShadeGrey"><a href="http://www.intertek-cb.com/terms/840-07" target="_blank"><f:message key="creditApplication"/>:</td>
<td>&nbsp;
</td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="establishedAccountPoNo"/>:</td>
<td><form:input cssClass="inputBox" size="46" maxlength="12" path="addJobContracts[${counter.index}].jobContract.payByEstablishedAcctInd" disabled="true"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.payByEstablishedAcctInd" cssClass="redstar"/>  
</td>
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td class="TDShadeGrey"><f:message key="invoiceDesc"/>:</td>
<td colspan="3">
<form:textarea path="addJobContracts[${counter.index}].jobContract.invoiceDescr"  cols="127"  id="invoiceDescrdetails${counter.index}" disabled="true" tabindex="49"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceDescr" /></td>

</tr>
</table>
</table>
</div>

<!-- --------------------------------------------------------------------------------------- Billing Contact Lookup  --------------------------------------------------------------------------------------->
<div class="sample_popup" id="searchbillingcontact${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchbillingcontact_drag${counter.index}" style="width:450px;;height:auto; ">
<a href="#"  onclick="setjobflag()"><img class="menu_form_exit"   id="searchbillingcontact_exit${counter.index}" src="images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="searchContact"/></div>
<div class="menu_form_body" id="contactbody${counter.index}"   style="width:450px; height:250px;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="350px;" width="100%" src="search_contact_popup.htm?inputFieldId=addJobContracts[${counter.index}].jobContract.billingContact.id&rowNum=${counter.index}&searchForm=editJobsAnalyticalServicesForm&divName=searchbillingcontact${counter.index}&divbody=contactbody${counter.index}&custCode=${command.addJobContracts[counter.index].jobContract.custCode}" id="searchContactFr" name="searchContactFr" allowtransparency="yes"></iframe>
</div>
</div>
<!-- ---------------------------------------------------------------------------------------- Billing Contact Lookup  -------------------------------------------------------------------------------------->

<!-- ------------------------------------------------------------------------------------ Search Bank Lookup  --------------------------------------------------------------------------------------------->
<div class="sample_popup"     id="searchremitto${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchremitto_drag${counter.index}" style="width:475px;height:auto;"> 
<img class="menu_form_exit"   id="searchremitto_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="remitToSearch"/></div>
<div class="menu_form_body" id="remittobody${counter.index}"   style="width:475px; height:300px;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr>
<iframe align="left" frameborder="0" style="padding:0px;" height="300px;" width="100%" src="search_bank_popup.htm?inputFieldId=addJobContracts[${counter.index}].jobContract.bankCd&rowNum=${counter.index}&buName=${command.jobOrder.buName}&currency=${command.addJobContracts[counter.index].cfgContract.currencyCD}" scrolling="auto" id="sremittoframe" name="sremittoframe" allowtransparency="yes" ></iframe>
</tr>
</table>
</div>
</div>
 <!-- ------------------------------------------------------------------------------------ Search Bank Lookup End---------------------------------------------------------------------------------------->

<!-- ------------------------------------------------------------------------------------- Search Account Lookup  ---------------------------------------------------------------------------------------->
<div class="sample_popup" id="searchaccount${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchaccount_drag${counter.index}" style="width:475px;height:auto;"> 
<img class="menu_form_exit"   id="searchaccount_exit${counter.index}" src="images/form_exit.png" border="0" />&nbsp;&nbsp;&nbsp;<f:message key="accountSearch"/> </div>
<div class="menu_form_body" id="accountbody${counter.index}"   style="width:475px; height:300px;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr>
<iframe align="left" frameborder="0" style="padding:0px;" height="300px;" width="100%" src="search_bank_account_popup.htm?inputFieldId=addJobContracts[${counter.index}].jobContract.bankAcctKey&rowNum=${counter.index}&buName=${command.jobOrder.buName}&currency=${command.addJobContracts[counter.index].cfgContract.currencyCD}" scrolling="auto" id="saccountframe" name="saccountframe" allowtransparency="yes" ></iframe>
</tr>
</table>
</div>
</div>
<!-- ------------------------------------------------------------------------------------- Search Account Lookup End------------------------------------------------------------------------------------>

<!-- -------------------------------------------------------------------------------------- Select Project Lookup START --------------------------------------------------------------------------------->
<div class="sample_popup" id="searchprojects${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchprojects_drag${counter.index}" style="width:400px; "> 
<img class="menu_form_exit"   id="searchprojects_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchProjects"/> </div>
<div class="menu_form_body" id="searchprojectsbody${counter.index}"   style="width:400px; height:265px;">
<iframe align="left" frameborder="0" style="padding:0px; height:265px;" width="100%" src="search_project_popup.htm?inputFieldId=addJobContracts[${counter.index}].jobContract.projectNumber&custCode=${command.addJobContracts[counter.index].jobContract.custCode}&rowNum=${counter.index}" scrolling="no" id="sprojframe" name="sprojframe" allowtransparency="yes" ></iframe>  
</div>
</div>
<!-- ----------------------------------------------------------------------------------- Select Project Lookup END --------------------------------------------------------------------------------------->
<!------------------------------------  view Contract Attach Popup--------------------------------------------------->
<div class="sample_popup" id="contractattach${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contractattach_drag${counter.index}" style="width:900px;height:auto;"> 
<img class="menu_form_exit"   id="contractattach_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="contractAttachments"/></div>                                               
<div class="menu_form_body" id="contractattachbody${counter.index}" style="width:900px; height:300px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:10px;" height="280px;" width="100%"  scrolling="auto" id="contractFr${counter.index}" name="contractFr${counter.index}" allowtransparency="yes" src="blank.html"></iframe>
</div>
</div>
<!---------------------------------------View Contract Attach Lookup End-------------------------------------------------->

  
<ajax:autocomplete
              baseUrl="customer.htm"
              source="addJobContracts[${counter.index}].jobContract.billingContact.id"
              target="addJobContracts[${counter.index}].jobContract.billingContactName"
              className="autocomplete"                          parameters="contactId={addJobContracts[${counter.index}].jobContract.billingContact.id},custCode=${command.addJobContracts[counter.index].jobContract.custCode}"
              minimumCharacters="1"
               />

<ajax:autocomplete
              baseUrl="joborder.htm"
              source="addJobContracts[${counter.index}].jobContract.bankCd"
              target="addJobContracts[${counter.index}].jobContract.bankCd"
              className="autocomplete"              
              parameters="bankDesc={addJobContracts[${counter.index}].jobContract.bankCd},buName=${command.jobOrder.buName},currency= ${command.addJobContracts[counter.index].cfgContract.currencyCD}"
              minimumCharacters="1"
               />
<ajax:autocomplete
              baseUrl="joborder.htm"
              source="addJobContracts[${counter.index}].jobContract.bankAcctKey"
              target="addJobContracts[${counter.index}].jobContract.bankAcctKey"
              className="autocomplete"              
              parameters="bankAccountDesc={addJobContracts[${counter.index}].jobContract.bankAcctKey},buName=${command.jobOrder.buName},currency= ${command.addJobContracts[counter.index].cfgContract.currencyCD}"
              minimumCharacters="1"
               />

 <ajax:autocomplete
              baseUrl="joborder.htm"
              source="addJobContracts[${counter.index}].jobContract.projectNumber"
              target="addJobContracts[${counter.index}].jobContract.projectNumber"
              className="autocomplete"              parameters="projectNumber={addJobContracts[${counter.index}].jobContract.projectNumber},custCode=${command.addJobContracts[counter.index].jobContract.custCode}"
              minimumCharacters="1"
               /> 

<ajax:select
              baseUrl="joborder.htm"
              source="addJobContracts[${counter.index}].jobContract.locationDesc"
              target="addJobContracts[${counter.index}].jobContract.zone"              parameters="portCode={addJobContracts[${counter.index}].jobContract.locationDesc},contractCode=${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode},branchCode=${command.jobOrder.branchName}"
              parser="new ResponseXmlParser()" 
              />
            


</c:forEach>    
<!-- Contract 1 END -->
</table>
</div>

<!---------------------------------------------------------------------------------------- Contract Details Container End -------------------------------------------------------------------------------->

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
            <tr>
              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                   <td>&nbsp;</td>
                    <td style="text-align:right"><a href="#"  onclick="updateJobDescription();goToNextPage()"><img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#"><img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0"  onclick="updateJobDescription();top.document.editJobsAnalyticalServicesForm.submit();"/></a></td>
                  </tr>
                </table>
      </td>
         </tr>
     </table>
  </div>

<!-- -------------------------------------------------------------------------------------------- TAB 2 CONTAINER END ---------------------------------------------------------------------------------->
</div>
</div>
<script type="text/javascript">
var pageNoVar = "${command.tabName}"
dolphintabs.init("tabnav", pageNoVar)      
</script> 
<!-- ------------------------------------------------------------------------------------ TAB CONTENT END ---------------------------------------------------------------------------------------------->
</div>
<!-- ---------------------------------------------------------------------------------- MAIN CONTAINER END -------------------------------------------------------------------------------------------->
</td>
</tr>
</table>
<!-------------------------------------------------------------------------------- MAIN OUTSIDE TABLE HOLDER END ---------------------------------------------------------------------------------->
</form:form>


<!-- --------------------------------- NominationTimezone Lookup START ----------------------------------------- -->

  <div class="sample_popup" id="timezone" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="timezone_drag" style="width:300px; "> 
  <img class="menu_form_exit"   id="timezone_exit" src=" images/form_exit.png" />&nbsp;&nbsp;&nbsp; <f:message key="selectTimeZone"/> </div>
  <div class="menu_form_body" id="timezonebody" style="width:300px; height:250px;"">
  
  <iframe align="middle" frameborder="0" style="padding:1px; height:250px;" width="100%" src="search_timezone_popup.htm?inputFieldId=jobOrder.nominationTimeTz&div1=timezone&div2=timezonebody" id="frame4" name="frame4" allowtransparency="yes" ></iframe>
       
  
  </div>
   </div>
<!-- --------------------------------- Nomination Timezone  Lookup END ----------------------------------------- -->

<!-- --------------------------------- Eta Timezone Lookup START ----------------------------------------- -->

  <div class="sample_popup" id="etatimezone" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="etatimezone_drag" style="width:300px; "> 
  <img class="menu_form_exit"   id="etatimezone_exit" src=" images/form_exit.png" />&nbsp;&nbsp;&nbsp; <f:message key="selectTimeZone"/> </div>
  <div class="menu_form_body" id="etatimezonebody" style="width:300px; height:250px;"">
    
  <iframe align="left:2px" frameborder="0" style="padding:2px; height:250px;" width="100%" src="search_timezone_popup.htm?inputFieldId=jobOrder.etaTimeTz&div1=etatimezone&div2=etatimezonebody" id="frame5" name="frame5" allowtransparency="yes" ></iframe>
  
  </div>
   </div>
<!-- --------------------------------- Eta Timezone  Lookup END ----------------------------------------- -->

<!-- --------------------------------- Sales Rep START----------------------------------------- -->
<div class="sample_popup" id="salesrep" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="salesrep_drag" style="width:300px;"> <img class="menu_form_exit"   id="salesrep_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchUser"/></div>
  <div class="menu_form_body" id="salesrepbody" style="width:300px; height:250px;padding-left:4px;">
  <iframe align="left" frameborder="0" style="padding:0px;" height="250px" width="100%" src="search_user_popup.htm?inputFieldId=jobOrder.receivedByUserName&div1=salesrep&div2=salesrepbody" scrolling="auto" id="searchAccountOwnerFr" name="searchAccountOwnerFr" allowtransparency="yes" ></iframe>
 </div>
</div>
<!-- --------------------------- Sales Rep Lookup END ------------------------------------------------- -->

<!-- --------------------------------- Admin Conatct START----------------------------------------- -->
<div class="sample_popup" id="admincontact" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="admincontact_drag" style="width:300px;"> <img class="menu_form_exit"   id="admincontact_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchUser"/></div>
  <div class="menu_form_body" id="admincontactbody" style="width:300px; height:250px;padding-left:4px;">
  <iframe align="left" frameborder="0" style="padding:0px;" height="250px" width="100%" src="search_user_popup.htm?inputFieldId=jobOrder.adminContactUserName&div1=admincontact&div2=admincontactbody" scrolling="auto" id="searchAccountOwnerFr" name="searchAccountOwnerFr" allowtransparency="yes" ></iframe>
 </div>
</div>
<!-- --------------------------- Admin Conatct Lookup END ------------------------------------------------- -->

<!-- ---------------------------  Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="jobbranchcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="jobbranchcode_drag" style="width:440px; "> 
<a href="#"  onclick="resetBranchTypeFlag()">    <img class="menu_form_exit"   id="jobbranchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/>
  </div>
  <div class="menu_form_body" id="jobbranchcodebody"   style="width:440px;height:340px;">
    <form method="post" action="popup.php">
     
            <iframe align="left" id="jobbu" frameborder="0" style="padding:10px;" height="340px;" width="100%"  scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" src="blank.html"></iframe>
             
    </form>
  </div>
</div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->
<div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>
