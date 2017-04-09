<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script>

Tooltip.offX = 4;  
Tooltip.offY = 4;
Tooltip.followMouse = false;  // must be turned off for hover-tip

var jc_array = new Array();

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


function sortByBusinessUnit() {
	document.cancelledJobReportForm.pageNumber.value = "1";
	document.cancelledJobReportForm.sortFlag.value = "buName";
	document.cancelledJobReportForm.jxcel.value = "false";
	document.cancelledJobReportForm.submit();
}

function sortByBranch() {
	document.cancelledJobReportForm.pageNumber.value = "1";
	document.cancelledJobReportForm.sortFlag.value = "branchName";
	document.cancelledJobReportForm.jxcel.value = "false";
	document.cancelledJobReportForm.submit();
}

function sortByJobNumber() {
	document.cancelledJobReportForm.pageNumber.value = "1";
	document.cancelledJobReportForm.sortFlag.value = "jobNumber";
	document.cancelledJobReportForm.jxcel.value = "false";
	document.cancelledJobReportForm.submit();
}

function sortByReasonForCancel() {
	document.cancelledJobReportForm.pageNumber.value = "1";
	document.cancelledJobReportForm.sortFlag.value = "cancelReasonNote";
	document.cancelledJobReportForm.jxcel.value = "false";
	document.cancelledJobReportForm.submit();
}

function submitSearch(pageNumber)
{  
	document.cancelledJobReportForm.jxcel.value="false";
	// document.cancelledJobReportForm.submitFlag.value = "true";
	document.cancelledJobReportForm.pageNumber.value = pageNumber;
	document.cancelledJobReportForm.checkPageSort.value = "true";
	document.cancelledJobReportForm.submit();
} 

function updateBranchIframeSrc()
  {
  var buName= document.getElementById("sel20").value;
  if(buName!= "" && buName!= null)
  {
    document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId=branch.value&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=jobsForm");
  }
}

function makeBranchblank()
{
  document.getElementById("brnch").value="";
}


function checkFromTO()
{
	document.cancelledJobReportForm.pageNumber.value = "1";
    document.cancelledJobReportForm.sortFlag.value = "";
   // document.cancelledJobReportForm.submitFlag.value = "true";
    document.cancelledJobReportForm.jxcel.value = "false";
	document.cancelledJobReportForm.submit();
}

function submitxcel() {
    document.cancelledJobReportForm.jxcel.value = "true";
    document.cancelledJobReportForm.sortFlag.value = "";
    top.document.cancelledJobReportForm.submit();
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

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
 <icb:item>${command.businessUnit.value}</icb:item> 
</icb:list>
<icb:list var="jobsearchStatus">
  <icb:item>jobsearchStatus</icb:item>
</icb:list>

<form:form name="cancelledJobReportForm" method="POST" action="cancelled_job_report.htm">
<div style="color:red;">
<form:errors cssClass="error"/>
</div>
<form:hidden path="sortFlag"/>
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<input type="hidden" name="pageNumber" value="1" />
<input type="hidden" name="jxcel" value="false"/>

<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
<td valign="top">
<!------------------------------------------------MAIN CONTAINER--------------------------------------------------->
<div id="MainContentContainer">
<!------------------------------------------------TABS CONTENTS---------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="cancelledJobs"/> </span></a></li>
</ul>
</div>
<!----------------------------------------- Sub Menus container. Do not remove ------------------------------------>
<div id="tab_inner">
<!---------------------------------------------- TAB 1 CONTAINER -------------------------------------------------->
   <div id="tab1" class="innercontent1" >
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr>
		<th colspan="5"><f:message key="searchCriteria"/></th>
      </tr>
      <tr>
		  <td width="15%" class="TDShade"><label for="businessUnitName">
			<f:message key="businessUnitName" />: </label><span class="redstar">*</span> </td>
		  <td width="30%" class="TDShadeBlue">
			<form:select cssClass="selectionBox" id="sel20" path="businessUnit.value" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value" onchange="makeBranchblank()" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="businessUnit.value" cssClass="error"/>
	      </td>
		  <td width="15%" class="TDShade"><strong>
				<f:message key="branchCode"/>:<span class="redstar">*</span></strong></td>
		  <td width="35%" colspan="2" class="TDShadeBlue">
			<form:input id="brnch" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;"
			  cssClass="inputBox" path="branch.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/>
			<form:errors path="branch.value"
			  cssClass="redstar" />
			<a href="#" onClick="javascript:updateBranchIframeSrc();popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 80, 
			 80);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()"><img
			  src=" images/lookup.gif" width="13" alt="Lookup branch Code" height="13" border="0" />
			</a>
		  </td>
      </tr>
      
      <tr>
		  <td width="15%" class="TDShade"><f:message key="jobNumber"/>:</td>
		  <td width="30%" class="TDShadeBlue">
			<form:input cssClass="inputBox" maxlength="25" path="jobId.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/> 
		  </td>         
		  <td width="15%" class="TDShade"> &nbsp;</td>
		  <td width="30%" class="TDShadeBlue"> &nbsp; </td>
      </tr>
      
	  <tr>
		  <td class="TDShade"><f:message key="nomDtFrom"/>: </td>
		  <td class="TDShadeBlue"><form:input id="ndFrom" cssClass="inputBox" path="nomDateFrom.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="nomDateFrom.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].ndFrom,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
		  <td class="TDShade"><f:message key="nomDtTo"/>: </td>
		  <td class="TDShadeBlue" colspan="2"><form:input id="nomDtTo" cssClass="inputBox" path="nomDateTo.value" onkeypress="if(event.keyCode==13) {checkFromTO();}"/><form:errors path="nomDateTo.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].nomDtTo,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
      </tr>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
      <tr>
      <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
			<td><input name="Search" type="button" class="button1" value="Search/Refresh" onclick="checkFromTO()"/>	
			</td> 
			<td style="text-align:right;"><a href="#"><img id="xcel" src="images/ico_excel.gif" 
				alt="Download to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>        
        </tr>
      </table></td>
      </tr>
    </table>
  
 <br>

<c:if test="${command.results != null}"> 
<div id="cancelledjobresults"> <strong><f:message key="searchResults"/> </strong> 
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable"> 
	<tr> 
	  <th><a href="#" onClick="sortByBusinessUnit();" ><span class="TDShade"><f:message 	
			key="businessUnit"/></span></a></th>
	  <th><a href="#" onClick="sortByBranch();" ><span class="TDShade"><f:message 		
			key="branch"/></span></a></th>
	  <th><a href="#" onClick="sortByJobNumber();" ><span class="TDShade"><f:message 		
			key="jobNumber"/></span></a></th>
	  <th><a href="#" onClick="sortByReasonForCancel();" ><span class="TDShade"><f:message 		
			key="reasonForCancel"/></span></a></th>
	</tr> 
	<c:forEach items="${command.results}" var="job" varStatus="status"> 
		<c:choose> 
			<c:when test="${status.index%2==0}"> <tr style="background-color:#FFFFFF;"> </c:when> 
			<c:otherwise> <tr style="background-color:#e7eeff;"> </c:otherwise> 
		</c:choose> 
		<td align='left' valign="middle" nowrap>${job.buName}</td> 
		<td align='left' valign="middle" nowrap>${job.branchName}</td> 
		<td align='left' valign="middle" nowrap>${job.jobNumber}</td> 
		<!-- <td align='left' valign="middle" >${job.cancelReasonNote}</td>  -->
		<td nowrap='nowrap' height="25" align='left' valign="top" >
			<span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
			onMouseOver="doTooltip(event,'${command.cancelNoteDetails[status.index]}')" onMouseOut="hideTip()">
			${job.cancelReasonNote}</a></span>
		</td>
	 </tr> 
	</c:forEach> 
	<tr> 
	<td>&nbsp;</td> <td align="center"> 
		<%@ include file="../common/pagination.jsp" %>
	</td> 
	</tr> 
	</table> 
</div> 
</c:if>


   </div>        
<!------------------------------------------------TAB 1 CONTAINER END --------------------------------------------->
</div>
</div>
<script type="text/javascript">
//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
dolphintabs.init("tabnav", 0)
</script>
<!--------------------------------------------------- TAB CONTENT END --------------------------------------------->
</div>
<!------------------------------------------ MAIN CONTAINER END --------------------------------------------------->
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

<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left:1px;Top:1px;">
<IMG src="images/fake-alpha-999.gif"></div>

</td>
</tr>
</table>
</form:form>