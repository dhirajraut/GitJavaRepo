<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<script type="text/javascript">
  // adjust horizontal and vertical offsets here
  // (distance from mouseover event which activates tooltip)
  Tooltip.offX = 4;  
  Tooltip.offY = 4;
  Tooltip.followMouse = false;  // must be turned off for hover-tip


  function doTooltip(e, msg) {
    if ( typeof Tooltip == "undefined") return;
    if ( !Tooltip.ready ) return;
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
  
  function doNextAction(nextAction, myIndex, contractName, contractInProgress)
  {
	var go ;
	if ('true'==contractInProgress){
		go = confirm("The contract " + contractName + " is stil In Progres. Continue?");
	}
	if(go || ('false'==contractInProgress)){
		document.jobOrderEditSelectChargesForm.refreshing.value=nextAction;
		document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex;
		document.jobOrderEditSelectChargesForm.scrollFlag.value="none"
		document.jobOrderEditSelectChargesForm.submit();
	}
  }
  
  function doDeleteJobContractService(
    myIndex,
    mySelectedJobContractCode, 
    mySelectedChargeIndex
  )
  {
    var doDelete = confirm("Are you sure to delete this?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=false;
      document.jobOrderEditSelectChargesForm.operationType.value="deleteCharge";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=mySelectedJobContractCode;
      document.jobOrderEditSelectChargesForm.selectedChargeIndex.value=mySelectedChargeIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeType.value="JobContractService";
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex;
      document.jobOrderEditSelectChargesForm.scrollFlag.value="dJServ"+myIndex+mySelectedJobContractCode;
      document.jobOrderEditSelectChargesForm.submit();
    }
  }

  function doDeleteJobContractVesselService(
    myIndex,
    mySelectedJobContractCode, 
    mySelectedJobContractVesselIndex, 
    mySelectedChargeIndex
  )
  {
    var doDelete = confirm("Are you sure to delete this?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=false;
      document.jobOrderEditSelectChargesForm.operationType.value="deleteCharge";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=mySelectedJobContractCode;
      document.jobOrderEditSelectChargesForm.selectedJobContractVesselIndex.value=mySelectedJobContractVesselIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeIndex.value=mySelectedChargeIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeType.value="JobContractVesselService";
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex;	  document.jobOrderEditSelectChargesForm.scrollFlag.value="delVessService"+myIndex+mySelectedJobContractCode+mySelectedJobContractVesselIndex;
      document.jobOrderEditSelectChargesForm.submit();
    }
  }

  function doDeleteJobContractProductService(
    myIndex,
    mySelectedJobContractCode, 
    mySelectedJobContractVesselIndex, 
    mySelectedJobContractProdIndex, 
    mySelectedChargeIndex
  )
  {
    var doDelete = confirm("Are you sure to delete this?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=false;
      document.jobOrderEditSelectChargesForm.operationType.value="deleteCharge";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=mySelectedJobContractCode;
      document.jobOrderEditSelectChargesForm.selectedJobContractVesselIndex.value=mySelectedJobContractVesselIndex;
      document.jobOrderEditSelectChargesForm.selectedJobContractProdIndex.value=mySelectedJobContractProdIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeIndex.value=mySelectedChargeIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeType.value="JobContractProductService";
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex; document.jobOrderEditSelectChargesForm.scrollFlag.value="delProdServ"+myIndex+mySelectedJobContractCode+mySelectedJobContractVesselIndex+mySelectedJobContractProdIndex;
      document.jobOrderEditSelectChargesForm.submit();
    }
  }

  function doDeleteJobContractProductInspectionResult(
    myIndex,
    mySelectedJobContractCode, 
    mySelectedJobContractVesselIndex, 
    mySelectedJobContractProdIndex, 
    mySelectedChargeIndex
  )
  {
    var doDelete = confirm("Are you sure to delete this?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=false;
      document.jobOrderEditSelectChargesForm.operationType.value="deleteCharge";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=mySelectedJobContractCode;
      document.jobOrderEditSelectChargesForm.selectedJobContractVesselIndex.value=mySelectedJobContractVesselIndex;
      document.jobOrderEditSelectChargesForm.selectedJobContractProdIndex.value=mySelectedJobContractProdIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeIndex.value=mySelectedChargeIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeType.value="JobContractProductInspection";
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex; document.jobOrderEditSelectChargesForm.scrollFlag.value="delProdInspServ"+myIndex+mySelectedJobContractCode+mySelectedJobContractVesselIndex+mySelectedJobContractProdIndex; 
      document.jobOrderEditSelectChargesForm.submit();
    }
  }

  function doDeleteJobContractTest(
    myIndex,
    mySelectedJobContractCode, 
    mySelectedJobContractVesselIndex, 
    mySelectedJobContractProdIndex, 
    mySelectedChargeIndex
  )
  {
    var doDelete = confirm("Are you sure to delete this?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=false;
      document.jobOrderEditSelectChargesForm.operationType.value="deleteCharge";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=mySelectedJobContractCode;
      document.jobOrderEditSelectChargesForm.selectedJobContractVesselIndex.value=mySelectedJobContractVesselIndex;
      document.jobOrderEditSelectChargesForm.selectedJobContractProdIndex.value=mySelectedJobContractProdIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeIndex.value=mySelectedChargeIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeType.value="JobContractTest";
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex; document.jobOrderEditSelectChargesForm.scrollFlag.value="delTest"+myIndex+mySelectedJobContractCode+mySelectedJobContractVesselIndex+mySelectedJobContractProdIndex;	
      document.jobOrderEditSelectChargesForm.submit();
    }
  }

  function doDeleteJobContractSlate(
    myIndex,
    mySelectedJobContractCode, 
    mySelectedJobContractVesselIndex, 
    mySelectedJobContractProdIndex, 
    mySelectedChargeIndex
  )
  {
    var doDelete = confirm("Are you sure to delete this?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=false;
      document.jobOrderEditSelectChargesForm.operationType.value="deleteCharge";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=mySelectedJobContractCode;
      document.jobOrderEditSelectChargesForm.selectedJobContractVesselIndex.value=mySelectedJobContractVesselIndex;
      document.jobOrderEditSelectChargesForm.selectedJobContractProdIndex.value=mySelectedJobContractProdIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeIndex.value=mySelectedChargeIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeType.value="JobContractSlate";
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex;	  document.jobOrderEditSelectChargesForm.scrollFlag.value="delSlate"+myIndex+mySelectedJobContractCode+mySelectedJobContractVesselIndex+mySelectedJobContractProdIndex;   
      document.jobOrderEditSelectChargesForm.submit();
    }
  }

  function doDeleteJobContractManualTest(
    myIndex,
    mySelectedJobContractCode, 
    mySelectedJobContractVesselIndex, 
    mySelectedJobContractProdIndex, 
    mySelectedChargeIndex
  )
  {
    var doDelete = confirm("Are you sure to delete this?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=false;
      document.jobOrderEditSelectChargesForm.operationType.value="deleteCharge";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=mySelectedJobContractCode;
      document.jobOrderEditSelectChargesForm.selectedJobContractVesselIndex.value=mySelectedJobContractVesselIndex;
      document.jobOrderEditSelectChargesForm.selectedJobContractProdIndex.value=mySelectedJobContractProdIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeIndex.value=mySelectedChargeIndex;
      document.jobOrderEditSelectChargesForm.selectedChargeType.value="JobContractManualTest";
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex;	  document.jobOrderEditSelectChargesForm.scrollFlag.value="delManualTest"+myIndex+mySelectedJobContractCode+mySelectedJobContractVesselIndex+mySelectedJobContractProdIndex;
      document.jobOrderEditSelectChargesForm.submit();
    }
  }

  function doRefreshJobContractFromJob(
    myIndex,
    mySelectedJobContractCode
  )
  {
    var doDelete = confirm("Are you sure to refresh from job?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=false;
      document.jobOrderEditSelectChargesForm.operationType.value="refreshFromJob";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=mySelectedJobContractCode;
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex;
	  document.jobOrderEditSelectChargesForm.scrollFlag.value="none";
      document.jobOrderEditSelectChargesForm.submit();
    }
  }
    
  function onDeleteProduct(
    myIndex,
    myJobContractCode, 
    myJobContractVesselIndex, 
    myJobContractProdIndex
  )
  {
    var doDelete = confirm("Are you sure to delete this product?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=false;
      document.jobOrderEditSelectChargesForm.operationType.value="deleteProduct";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=myJobContractCode;
      document.jobOrderEditSelectChargesForm.selectedJobContractVesselIndex.value=myJobContractVesselIndex;
      document.jobOrderEditSelectChargesForm.selectedJobContractProdIndex.value=myJobContractProdIndex;
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex; document.jobOrderEditSelectChargesForm.scrollFlag.value="delProduct"+myIndex+myJobContractVesselIndex;	  
      document.jobOrderEditSelectChargesForm.submit();
    }
  }
  
  function onDeleteVessel(
    myIndex,
    myJobContractCode, 
    myJobContractVesselIndex
  )
  {
    var doDelete = confirm("Are you sure to delete this vessel?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=false;
      document.jobOrderEditSelectChargesForm.operationType.value="deleteVessel";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=myJobContractCode;
      document.jobOrderEditSelectChargesForm.selectedJobContractVesselIndex.value=myJobContractVesselIndex;
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex;

	  if(myJobContractVesselIndex!=0)
	  {
	   document.jobOrderEditSelectChargesForm.scrollFlag.value="delVess"+myIndex+myJobContractCode+(myJobContractVesselIndex-1);
	  }
	else
	  {
		document.jobOrderEditSelectChargesForm.scrollFlag.value="addVess"+myIndex+myJobContractCode;	
	  }	

      document.jobOrderEditSelectChargesForm.submit();
    }
  }

  function onSplit(
    myIndex,
    myJobContractCode
  )
  {
    document.jobOrderEditSelectChargesForm.refreshing.value=true;
    document.jobOrderEditSelectChargesForm.operationType.value="split";
    document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=myJobContractCode;
    document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex;
    document.jobOrderEditSelectChargesForm.scrollFlag.value="onspl"+myIndex+myJobContractCode;
    document.jobOrderEditSelectChargesForm.submit();
  }

  function onSort(
    myIndex,
    myJobContractCode
  )
  {
    document.jobOrderEditSelectChargesForm.refreshing.value=true;
    document.jobOrderEditSelectChargesForm.operationType.value="sort";
    document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=myJobContractCode;
    document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex;
    document.jobOrderEditSelectChargesForm.scrollFlag.value="onsor"+myIndex+myJobContractCode;
    document.jobOrderEditSelectChargesForm.submit();
  }
  
  function selectAll(myIndex, selectAllElement)
  {
    var elementList = document.jobOrderEditSelectChargesForm.elements;
    for(i=0;i<elementList.length;i++)
    {
      if(elementList[i].type == "checkbox")
      {
        var checkBoxName = elementList[i].name;
        var prefix = 'addJobContracts[' + myIndex + ']';
        var myPosition = checkBoxName.indexOf(prefix);
        if(myPosition >= 0)
        {
          if(selectAllElement.checked) elementList[i].checked=true;
          else elementList[i].checked=false;
        }
      }
    }
  }
  
  function warnUser(navigationUrl)
  {
    if(document.jobOrderEditSelectChargesForm.warnUserFlag.value == 'warn' || document.jobOrderEditSelectChargesForm.warnUserFlag.value == 'warned')
      document.jobOrderEditSelectChargesForm.warnUserFlag.value = 'navigate';
    else
      document.jobOrderEditSelectChargesForm.warnUserFlag.value = "warn";

    document.jobOrderEditSelectChargesForm.navigationUrl.value = navigationUrl;
    document.jobOrderEditSelectChargesForm.submit();
  }  

  function onDelete(
    myIndex,
    myJobContractCode
  )
  {
    var doDelete = confirm("Are you sure to delete the selected charges?");
    if(doDelete)
    {
      document.jobOrderEditSelectChargesForm.refreshing.value=true;
      document.jobOrderEditSelectChargesForm.operationType.value="delete";
      document.jobOrderEditSelectChargesForm.selectedJobContractCode.value=myJobContractCode;
      document.jobOrderEditSelectChargesForm.contractIndex.value=myIndex;
      document.jobOrderEditSelectChargesForm.scrollFlag.value="ondel"+myIndex+myJobContractCode;
      document.jobOrderEditSelectChargesForm.submit();
    }
  }

  function setScrollFlag(index,count)
  {	
	 if(count!=null && count!='')
	  {
	  var a=++count;
	  document.jobOrderEditSelectChargesForm.scrollFlag.value=index+a;
	  }
	  else
	  {
		   document.jobOrderEditSelectChargesForm.scrollFlag.value=index;
	  }
  }

// START : #119240
function nextList()
{
document.jobOrderEditSelectChargesForm.showNextListFlag.value="next";
document.jobOrderEditSelectChargesForm.submit();
}

function prevList()
{
document.jobOrderEditSelectChargesForm.showPrevListFlag.value="prev";
document.jobOrderEditSelectChargesForm.submit();
}

function noPrevList()
{
	document.jobOrderEditSelectChargesForm.noPrevJob.value="true";
	document.jobOrderEditSelectChargesForm.submit();
}

function noNextList()
{
	document.jobOrderEditSelectChargesForm.noNextJob.value="true";
	document.jobOrderEditSelectChargesForm.submit();
}

// END : #119240

</script>

<icb:list var="divisions">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
  <icb:item>${command.jobOrder.branch.businessUnit.name}</icb:item>
</icb:list>

<table width="97%" height="80%" border="0" cellpadding="0" cellspacing="0">
<form:form name="jobOrderEditSelectChargesForm" method="POST" action="edit_job_select_charges.htm">
  <div style="color:orange;font-size: 110%;font-weight: bold;">
   <form:errors cssClass="error" />
     <c:if test="${not empty requestScope['warning_msg']}">
      Warning: ${requestScope['warning_msg']}
    </c:if>
  </div>
  <input type="hidden" name="refreshing" value="true" />
  <input type="hidden" name="selectedJobContractCode" value="" />
  <input type="hidden" name="selectedJobContractVesselIndex" value="" />
  <input type="hidden" name="selectedJobContractProdIndex" value="" />
  <input type="hidden" name="selectedServiceIndex" value="" />
  <input type="hidden" name="selectedChargeIndex" value="" />
  <input type="hidden" name="selectedChargeType" value="" />
  <input type="hidden" name="operationType" value="" />
  <input type="hidden" name="messageKey" value="" />
   <!-- START : #119240 -->
	 <!--  <input type="hidden" name="nextListFlag" value=""/> -->
 <!--  <input type="hidden" name="prevListFlag" value=""/> -->
  <input type="hidden" name="showNextListFlag" value=""/> 
	 <input type="hidden" name="showPrevListFlag" value=""/> 	
<form:hidden path="nextListFlag" />
<form:hidden path="prevListFlag" />
  <input type="hidden" name="noPrevJob" value=""/>
  <input type="hidden" name="noNextJob" value=""/> 	
  <!-- END : #119240 -->
  
  <form:hidden path="contractIndex" />
  <form:hidden path="navigationUrl" />
  <form:hidden path="warnUserFlag" />
  <form:hidden path="scrollFlag"/>

 <c:if test="${requestScope['noJobMessage'] != null}">  
  <div style="color:green;">
    ${requestScope.noJobMessage}
  </div>
</c:if>

  <c:set var="urlSuffix" value="${icbfn:urlSuffixByJobType(command.jobOrder.jobType)}" scope="request" /> 
  <c:set var="currentLocale" value="${icbfn:currentLocale(pageContext.request)}" />
    <c:set var="urlPrefix" value="${icbfn:urlPrefixByJobStatus(command.jobOrder.jobStatus)}" scope="request" />

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
                     <!--<a href="#" onClick="warnUser('${urlPrefix}_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                      <spring:message code="entry" />
                    </a>
                  </td>
                  <td class="breadcrumbtrailDeactive">
                    <c:choose>
                      <c:when test="${command.jobOrder.pageNumber >= 2}">               
                        <a href="${urlPrefix}_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                        <!-- <a href="#" onClick="warnUser('${urlPrefix}_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                          <spring:message code="jobInstructions" />
                        </a>
                      </c:when>
                      <c:otherwise>
                        <spring:message code="jobInstructions" />
                      </c:otherwise>
                    </c:choose>
                  </td>
                  <td class="breadcrumbtrailActive"> 
                    <spring:message code="selectCharges" />
                  </td>
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                      <c:when test="${command.jobOrder.pageNumber >= 4}">               
                         <a href="edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                        <!--<a href="#" onClick="warnUser('edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                          <spring:message code="preview" />
                        </a>
                      </c:when>
                      <c:otherwise>
                        <spring:message code="preview" />
                      </c:otherwise>
                    </c:choose>
                  </td>               
 
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                      <c:when test="${command.jobOrder.pageNumber >= 5}">               
                        <a href="edit_job_view_invoice.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                        <!-- <a href="#" onClick="warnUser('edit_job_view_invoice.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                          <spring:message code="invoice" />
                        </a>
                      </c:when>
                      <c:otherwise>
                        <spring:message code="invoice" />
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
      
      <c:if test="${fn:length(command.addJobContracts) > 0}">
        <!-- MAIN CONTAINER -->
        <div id="MainContentContainer">
          <!-- TABS CONTENTS -->
          <div id="tabcontainer">
            <div id="tabnav">
              <ul>
                <c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
                  <li>
                    <a href="#" rel="tab${addJobContractCounter.index}">
                      <span>${addJobContractCounter.index + 1}. ${addJobContract.jobContract.contractCode}</span>
                    </a>
                  </li>
                </c:forEach>
              </ul>

              <select name="sel1" id="sel1" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
                <option selected><spring:message code="Go To" /> ... &gt;</option>
                <option value="edit_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"><spring:message code="entry" /></option>
                <option value="edit_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}"><spring:message code="jobInstructions" /></option>
              </select>

            </div>
            <!-- Sub Menus container. Do not remove -->
            <div id="tab_inner">      
              <!-- ------------------------- TAB 2 CONTAINER ----------------------------------------- -->
              <c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
              <c:set var="jobLevelServicePopupStr" value="${icbfn:servicesPopupStr(addJobContractCounter.index, -1, -1, addJobContract.jobContract.contractCode, addJobContract.addJobContractServices.jobServiceType,currentLocale)}" />
              <div id="tab${addJobContractCounter.index}" class="innercontent" >
                <table class="mainApplTable" cellspacing="0" cellpadding="0" style="width:100%">
                  <tbody>
                    <tr>
                      <th width="55%">
                        <spring:message code="selectCharges" />
                        <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                        <spring:message code="jobId" />: ${command.jobOrder.jobNumber}
                        <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                        <spring:message code="billingContact" />: ${addJobContract.jobContract.billingContactName}
                        <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                        <spring:message code="jobStatus" />: <spring:message code="${command.jobOrder.jobStatus}" />
                        <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                        <form:checkbox path="addJobContracts[${addJobContractCounter.index}].checkAll" onclick="javascript:selectAll('${addJobContractCounter.index}', this)" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/><spring:message code="Select All" /> &nbsp;&nbsp;&nbsp;&nbsp;
                        <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                      </th>
                      <th nowrap="yes" width="13%">&nbsp;
                      </th>
					  <a name="onspl${addJobContractCounter.index}${addJobContract.jobContract.contractCode}"></a>
					   <a name="ondel${addJobContractCounter.index}${addJobContract.jobContract.contractCode}"></a>
                      <th style="text-align:right" width="16%">
                        <form:input path="addJobContracts[${addJobContractCounter.index}].splitPct" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                        <form:errors path="addJobContracts[${addJobContractCounter.index}].splitPct" cssClass="redstar" />
                        &nbsp;<a href="#" onclick="onSplit('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}')" title="Split all checked"><spring:message code="Split" /></a>
                        &nbsp;&nbsp;&nbsp;<a href="#" onclick="onDelete('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}')" title="Delete all checked"><spring:message code="Delete" /></a>
                      </th>
                      <th style="text-align:right">
		    <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                        <c:if test="${jobLevelServicePopupStr != null}">
                          <a href="#" style="cursor:hand; text-decoration:none;" onMouseOver="doTooltip(event, '${jobLevelServicePopupStr}')" onMouseOut="hideTip()">
                            <img src="images/icoadddocgreen.gif" width="12" height="14" border="0" align="absmiddle"/>
                          </a>
                       </c:if>
                        <a href="#" onclick="setScrollFlag('addVess${addJobContractCounter.index}${addJobContract.jobContract.contractCode}','${fn:length(addJobContract.addJobContractVessels)}');javascript:popAddVessel('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}','-1')">
                          <img src="images/add.gif" alt="Add Vessel" hspace="1px" border="0"/>
                        </a> 
              </c:if>
		 <!-- START : #119240 -->
		 &nbsp;
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
                        <a href="#" onclick="javascript:doNextAction('next', '${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContract.contractInProgress}');">
                          <img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page">
                        </a>
                        <a href="#" onclick="javascript:doNextAction('false', '${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContract.contractInProgress}');">
                          <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" align="absmiddle" />
                        </a>
                      </th>
                    </tr>                  
                    <tr>
                      <td colspan="4" style="padding-left:0px; padding-right:0px; padding-top:0px; padding-bottom:0px;">
                        <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                          <tr>
                            <th width="58%" style="border-right:#7c92be dashed 1px;"><spring:message code="description" /></th>
							<a name="eJServ${addJobContractCounter.index}${addJobContract.jobContract.contractCode}"></a>
							<a name="dJServ${addJobContractCounter.index}${addJobContract.jobContract.contractCode}"></a>
                            <a name="onsor${addJobContractCounter.index}${addJobContract.jobContract.contractCode}"></a>
							<th width="5%" style="border-right:#7c92be dashed 1px;">
                              <a href="#" onclick="onSort('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}')" title="Reorder"><spring:message code="sort" /></a>                 
                            </th>
                            <th width="7%" style="border-right:#7c92be dashed 1px;"><spring:message code="Contract Currency" /> - ${addJobContract.cfgContract.currencyCD}</th>
                            <th width="7%" style="border-right:#7c92be dashed 1px;"><spring:message code="Billing Currency" /> - ${addJobContract.jobContract.transactionCurrencyCd}</th>
                            <th width="5%" style="border-right:#7c92be dashed 1px;"><spring:message code="Split" /></th>
                            <th width="5%" style="border-right:#7c92be dashed 1px;"><spring:message code="Discount" /></th>
                            <th width="8%" style="border-right:#7c92be dashed 1px;"><spring:message code="Extended Price" /> - ${addJobContract.jobContract.transactionCurrencyCd}</th>
                            <th width="5%">&nbsp;</th>
                          </tr>
                          <c:forEach items="${addJobContract.addJobContractServices.addedJobContractServices}" var="jobContractServiceExt" varStatus="jobContractServiceExtStatus">     
                            <c:forEach items="${jobContractServiceExt.results}" var="jobContractServiceResult" varStatus="jobContractServiceResultStatus">
                              <c:forEach items="${jobContractServiceResult.prebills}" var="prebill" varStatus="prebillStatus">
                                <tr>
                                  <td class="row${(jobContractServiceExtStatus.index + 1) % 2}" style="border-right:#7c92be dashed 1px;"><form:checkbox path="addJobContracts[${addJobContractCounter.index}].addJobContractServices.addedJobContractServices[${jobContractServiceExtStatus.index}].selects[${jobContractServiceResultStatus.index}]" cssStyle="float:left"  disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>&nbsp;${prebill.lineDescription} </td>
                                  <c:if test="${jobContractServiceResultStatus.index == 0}">
                                    <td class="row${(jobContractServiceExtStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;align:center;" rowspan="${fn:length(jobContractServiceExt.results)}">
                                      <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractServices.addedJobContractServices[${jobContractServiceExtStatus.index}].service.sortOrderNum" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                      <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractServices.addedJobContractServices[${jobContractServiceExtStatus.index}].service.sortOrderNum" cssClass="redstar" />
                                    </td>
                                  </c:if>
                                  <td class="row${(jobContractServiceExtStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice * addJobContract.currencyMultiplier}" />&nbsp;</td>
                                  <td class="row${(jobContractServiceExtStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                  <td class="row${(jobContractServiceExtStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">
                                    <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractServices.addedJobContractServices[${jobContractServiceExtStatus.index}].results[${jobContractServiceResultStatus.index}].prebills[${prebillStatus.index}].splitPct" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                    <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractServices.addedJobContractServices[${jobContractServiceExtStatus.index}].results[${jobContractServiceResultStatus.index}].prebills[${prebillStatus.index}].splitPct" cssClass="redstar" />
                                  </td>
                                  <td class="row${(jobContractServiceExtStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">${prebill.discountPct}&nbsp;</td>
                                  <td class="row${(jobContractServiceExtStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                  <c:if test="${jobContractServiceResultStatus.index == 0}">
                                    <td class="row${(jobContractServiceExtStatus.index + 1) % 2}" rowspan="${fn:length(jobContractServiceExt.results)}">
                                      <div style="text-align:right; margin-right:0;"> 
                    <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                                        <a href="#" onclick="setScrollFlag('eJServ${addJobContractCounter.index}${addJobContract.jobContract.contractCode}','');javascript:popAddEditService('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '-1', '-1', '${jobContractServiceExt.service.serviceType}', '${jobContractServiceExtStatus.index}')">
                                          <img src="images/icoeditsmall.gif" alt="Edit Job Service" border="0"/>
                                        </a> 
                                        <a href="#" onclick="javascript:doDeleteJobContractService('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${jobContractServiceExtStatus.index}')">
                                          <img src="images/delete.gif" alt="Delete Job Service" width="13" height="12" border="0"/>
                                        </a> 
                      </c:if>
                                      </div>
                                    </td>
                                  </c:if>
                                </tr>
                              </c:forEach>
                            </c:forEach>
                          </c:forEach>
                          <c:forEach items="${addJobContract.results}" var="jobContractInspectionResult" varStatus="jobContractInspectionResultStatus">
                            <c:forEach items="${jobContractInspectionResult.prebills}" var="prebill" varStatus="prebillStatus">
                              <tr>
                                <td class="row${(jobContractInspectionStatus.index + 1) % 2}" style="border-right:#7c92be dashed 1px;"><form:checkbox path="addJobContracts[${addJobContractCounter.index}].selects[${jobContractInspectionResultStatus.index}]" cssStyle="float:left"  disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>&nbsp;${prebill.lineDescription} </td>
                                <td class="row${(jobContractInspectionStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;align:center;">&nbsp;</td>
                                <td class="row${(jobContractInspectionStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice * addJobContract.currencyMultiplier}" />&nbsp;</td>
                                <td class="row${(jobContractInspectionStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                <td class="row${(jobContractInspectionStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">
                                  <form:input path="addJobContracts[${addJobContractCounter.index}].results[${jobContractInspectionResultStatus.index}].prebills[${prebillStatus.index}].splitPct" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                  <form:errors path="addJobContracts[${addJobContractCounter.index}].results[${jobContractInspectionResultStatus.index}].prebills[${prebillStatus.index}].splitPct" cssClass="redstar" />
                                </td>
                                <td class="row${(jobContractInspectionStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">${prebill.discountPct}&nbsp;</td>
                                <td class="row${(jobContractInspectionStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                <td class="row${(jobContractInspectionStatus.index + 1) % 2}" >&nbsp;</td>
                              </tr>
                            </c:forEach>
                          </c:forEach>
                        </table>        
                      </td>
                    </tr>
                    <c:forEach items="${addJobContract.addJobContractVessels}" var="addJobContractVessel" varStatus="addJobContractVesselCounter">                               
                    <form:hidden path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].displayStatus" />
                    <tr>
                      <td style="padding:0;" colspan="4">          

                        <!-- --------------------- VESSEL 1 CONTAINER ------------------------------- -->
                        <div id="T${addJobContractCounter.index}vessel${addJobContractVesselCounter.index}" class="vessels" style="z-index:1">
                          <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable" >
                            <tbody>
                              <tr>
                                <td width="2%" class="TDShade" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x">                 
                                  <div id="T${addJobContractCounter.index}bluarrowrightv${addJobContractVesselCounter.index}" style="visibility:visible; position:absolute; z-index: 2;margin-right:5px;"> 
                                    <a href="#sv${addJobContractVesselCounter.index}" onClick="javascript:showTV('${addJobContractCounter.index}', '${addJobContractVesselCounter.index}');"> 
                                      <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" />
                                    </a> 
                                  </div>
                                  <div id="T${addJobContractCounter.index}bluarrowdownv${addJobContractVesselCounter.index}" style="visibility:hidden; position:relative; text-align:left; z-index: 1;margin-top:3px;margin-right:4px; margin-left:0px;"> 
                                    <a href="#hv${addJobContractVesselCounter.index}" onClick="javascript:hideTV('${addJobContractCounter.index}', '${addJobContractVesselCounter.index}');"> 
                                      <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" />
                                    </a> 
                                  </div>                
                                </td>
                              <%--<td class="TDShade" nowrap="yes" width="55%">--%>
							   <td class="TDShade" style="width:400;wrap:auto;text-wrap:hard-wrap;word-wrap:break-word;"> 

                                <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                                 <spring:message code="vessel" /> ${addJobContractVesselCounter.index + 1}: ${addJobContractVessel.jobContractVessel.vesselName}
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                   <td class="TDShade" nowrap="yes">
								<spring:message code="vesselType" />: &nbsp;${icbfn:displayVesselType(addJobContract.vesselTypes, addJobContractVessel.jobContractVessel.type)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
                                    </c:if>
                  <c:if test="${command.jobOrder.jobType == 'FST' || command.jobOrder.jobType=='OUT'}">
                                  Location ${addJobContractVesselCounter.index + 1}: &nbsp;${addJobContractVessel.jobContractVessel.vesselName}
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </c:if>                                  
                                </td>
								<a name="delProduct${addJobContractCounter.index}${addJobContractVesselCounter.index}"></a>
                                <td class="TDShade" nowrap="yes" width="6%" align="center" style="border-right:#7c92be dashed 0px;align:center;">
                                  <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].jobContractVessel.sortOrderNum" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                  <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].jobContractVessel.sortOrderNum" cssClass="redstar" />
                                </td>

                                 <a name="addVess${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index+1}"></a>

                                 <a name="delVessService${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}"></a>


                                <td class="TDShade" nowrap="yes" width="33%">
                                  <div style="float:right; font-size:10px; font-weight:bold;">
                                    <a href="#svessel${addJobContractVesselCounter.index}" onClick="javascript:SCexpandAllv('${addJobContractCounter.index}', '${addJobContractVesselCounter.index}', '${fn:length(addJobContractVessel.addJobContractProds)}');">
                                      <img src="images/icoexpandall.gif" alt="Expand All" hspace="2" border="0" align="absmiddle"/>
                                    </a>&nbsp;
                                    <a href="#cvessel${addJobContractVesselCounter.index}" onClick="javascript:SCcollapseAllv('${addJobContractCounter.index}', '${addJobContractVesselCounter.index}', '${fn:length(addJobContractVessel.addJobContractProds)}');">
                                      <img src="images/icocollapseall.gif" alt="Collapse All" hspace="2" border="0" align="absmiddle"/>
                                    </a>&nbsp;&nbsp;
                    <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                                    <a href="#" onclick="setScrollFlag('addLotPro${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}','${fn:length(addJobContractVessel.addJobContractProds)}');javascript:popEditLot('${addJobContractCounter.index}','${addJobContract.jobContract.contractCode}','${addJobContractVesselCounter.index}','-1', '-1');">
                                      <img src="images/add.gif" alt="Add Lot" hspace="1px" border="0"/>
                                    </a> 
                    </c:if>
                                  </div>
                                </td>

                                <c:set var="vesselLevelServicePopupStr" value="${icbfn:servicesPopupStr(addJobContractCounter.index, addJobContractVesselCounter.index, -1, addJobContract.jobContract.contractCode, addJobContract.addJobContractServices.jobServiceType,currentLocale)}" />
                                <td width="4%" nowrap="yes" class="TDShadeBlue" align="right">
                                  <div style="width:5%; text-align:center; margin-right:0;">
                  <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                                    <a href="#" style="cursor:hand; text-decoration:none;" onMouseOver="doTooltip(event, '<a href=# onclick=setScrollFlag(\'editVess${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}\',\'\');javascript:popAddVessel(\'${addJobContractCounter.index}\',\'${addJobContract.jobContract.contractCode}\',\'${addJobContractVesselCounter.index}\')>Edit Vessel</a><br>${vesselLevelServicePopupStr}')" onMouseOut="hideTip()">
                                      <img src="images/icoadddoc.gif" alt="Add Vessel Services" hspace="1px" border="0"/>
                                    </a>

                                  <a name="editVess${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}"></a>
									
									<a name="delVess${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}"></a>
                                    <a href="#">
                                      <img src="images/delete.gif" alt="Delete Vessel" onClick="javascript:onDeleteVessel('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContractVesselCounter.index}')" width="13" height="12" hspace="1px" border="0"/>
                                    </a> 
                    </c:if>
                                  </div>
                                </td>
                              </tr>
                            </tbody>
                          </table>
                          <!-- ----------VESSEL 1 Data ---------------- -->

                          <div id="T${addJobContractCounter.index}vessel${addJobContractVesselCounter.index}Container" class="vesselContainer" style="visibility:visible; display:block">

                            <!-- --------------------VESSEL 1 Product 1 -------------- -->

                            <div class="productTableSC">

                              <div id="T${addJobContractCounter.index}productTablev${addJobContractVesselCounter.index}" class="productTableSC" style="display:none">
                                <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTableSC" style="margin-bottom:3px;">
                                  <!--dummy tr -->
                                  <tr style="visibility:hidden;height:1px">
                                    <td width="58%" style="border-right:#7c92be dashed 1px; height:1px; padding-left:10px;padding-right:10px; "><img src="images/spacer.gif" width="61" height="1"></td>
                                    <td width="5%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="35" height="1"></td>
                                    <td width="5%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="35" height="1"></td>
                                    <td width="5%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="35" height="1"></td>
                                    <td width="5%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="24" height="1"></td>
                                    <td width="5%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="48" height="1"></td>
                                    <td width="5%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="51" height="1"></td>
                                    <td width="5%"><img src="images/spacer.gif" width="45" height="1"></td>
                                  </tr>
                                 <!--dummy tr end -->
                                  <c:forEach items="${addJobContractVessel.addJobContractVesselServices.addedJobContractVesselServices}" var="jobContractVesselServiceExt" varStatus="jobContractVesselServiceExtStatus">  
                                    <c:forEach items="${jobContractVesselServiceExt.results}" var="jobContractVesselServiceResult" varStatus="jobContractVesselServiceResultStatus">
                                      <c:forEach items="${jobContractVesselServiceResult.prebills}" var="prebill" varStatus="prebillStatus">
                                        <tr>
                                          <td class="row${(jobContractVesselServiceExtStatus.index + 1) % 2}" width="58%" style="border-right:#7c92be dashed 1px; padding-left:35px;"><form:checkbox path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractVesselServices.addedJobContractVesselServices[${jobContractVesselServiceExtStatus.index}].selects[${jobContractVesselServiceResultStatus.index}]" cssStyle="float:left" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}" />&nbsp;${prebill.lineDescription}</td>
                                          <c:if test="${jobContractVesselServiceResultStatus.index == 0}">
                                            <td class="row${(jobContractVesselServiceExtStatus.index + 1) % 2}" width="5%" align="center" style="border-right:#7c92be dashed 1px;align:center;" rowspan="${fn:length(jobContractVesselServiceExt.results)}">
                                              <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractVesselServices.addedJobContractVesselServices[${jobContractVesselServiceExtStatus.index}].service.sortOrderNum" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                              <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractVesselServices.addedJobContractVesselServices[${jobContractVesselServiceExtStatus.index}].service.sortOrderNum" cssClass="redstar" />
                                            </td>
                                          </c:if>
                                          <td class="row${(jobContractVesselServiceExtStatus.index + 1) % 2}" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice * addJobContract.currencyMultiplier}" />&nbsp;</td>
                                          <td class="row${(jobContractVesselServiceExtStatus.index + 1) % 2}" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                          <td class="row${(jobContractVesselServiceExtStatus.index + 1) % 2}" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">
                                            <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractVesselServices.addedJobContractVesselServices[${jobContractVesselServiceExtStatus.index}].results[${jobContractVesselServiceResultStatus.index}].prebills[${prebillStatus.index}].splitPct" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                            <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractVesselServices.addedJobContractVesselServices[${jobContractVesselServiceExtStatus.index}].results[${jobContractVesselServiceResultStatus.index}].prebills[${prebillStatus.index}].splitPct" cssClass="redstar" />
                                          </td>
                                          <td class="row${(jobContractVesselServiceExtStatus.index + 1) % 2}" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">${prebill.discountPct}&nbsp;</td>
                                          <td class="row${(jobContractVesselServiceExtStatus.index + 1) % 2}" width="8%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                          <c:if test="${jobContractVesselServiceResultStatus.index == 0}">
                                            <td class="row${(jobContractVesselServiceExtStatus.index + 1) % 2}" width="5%" rowspan="${fn:length(jobContractVesselServiceExt.results)}">
                                              <div style="text-align:right; margin-right:0;" > 
                        <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                                                <a href="#" onclick="setScrollFlag('vessServ${addJobContractCounter.index} ${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}','');javascript:popAddEditService('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}','${addJobContractVesselCounter.index}','-1','${jobContractVesselServiceExt.service.serviceType}','${jobContractVesselServiceExtStatus.index}')">
                                                  <img src="images/icoeditsmall.gif" alt="Edit Vessel Service" hspace="1px" border="0"/>
                                                </a> 
                                                <a href="#" onclick="javascript:doDeleteJobContractVesselService('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContractVesselCounter.index}', '${jobContractVesselServiceExtStatus.index}')">
                                                  <img src="images/delete.gif" alt="Delete Vessel Service" width="13" height="12" hspace="1px" border="0"/>
                                                </a> 
												 <a name="vessServ${addJobContractCounter.index} ${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}"></a>
                          </c:if>
                                              </div>
                                            </td>
                                          </c:if>
                                        </tr>
                                      </c:forEach>
                                    </c:forEach>
                                  </c:forEach>
                                  <c:forEach items="${addJobContractVessel.results}" var="jobContractVesselInspectionResult" varStatus="jobContractVesselInspectionResultStatus">
                                    <c:forEach items="${jobContractVesselInspectionResult.prebills}" var="prebill" varStatus="prebillStatus">
                                      <tr>
                                        <td class="row${(jobContractVesselInspectionResultStatus.index + 1) % 2}" width="58%" style="border-right:#7c92be dashed 1px; padding-left:35px;"><form:checkbox path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].selects[${jobContractVesselInspectionResultStatus.index}]" cssStyle="float:left" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}" />&nbsp;${prebill.lineDescription}</td>
                                        <td class="row${(jobContractVesselInspectionResultStatus.index + 1) % 2}" width="5%" align="center" style="border-right:#7c92be dashed 1px;align:center;">&nbsp;</td>
                                        <td class="row${(jobContractVesselInspectionResultStatus.index + 1) % 2}" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice * addJobContract.currencyMultiplier}" />&nbsp;</td>
                                        <td class="row${(jobContractVesselInspectionResultStatus.index + 1) % 2}" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                        <td class="row${(jobContractVesselInspectionResultStatus.index + 1) % 2}" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">
                                          <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].results[${jobContractVesselInspectionResultStatus.index}].prebills[${prebillStatus.index}].splitPct" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                          <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].results[${jobContractVesselInspectionResultStatus.index}].prebills[${prebillStatus.index}].splitPct" cssClass="redstar" />
                                        </td>
                                        <td class="row${(jobContractVesselInspectionResultStatus.index + 1) % 2}" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">${prebill.discountPct}&nbsp;</td>
                                        <td class="row${(jobContractVesselInspectionResultStatus.index + 1) % 2}" width="8%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                        <td class="row${(jobContractVesselInspectionResultStatus.index + 1) % 2}" width="5%">&nbsp;</td>
                                      </tr>
                                    </c:forEach>
                                  </c:forEach>
                                </table>
                              </div>

                              <c:forEach items="${addJobContractVessel.addJobContractProds}" var="addJobContractProd" varStatus="addJobContractProdCounter">   
                              <form:hidden path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].displayStatus" />
                              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltableSC" style="margin-right:0px;padding-bottom:0px;">
                                <tr>
                                  <th width="2%" valign="top" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> 
                                    <div id="T${addJobContractCounter.index}bluarrowrightv${addJobContractVesselCounter.index}p${addJobContractProdCounter.index}" style="visibility:visible; position:absolute; z-index: 2;margin-top:5px; margin-left:5px;"> 
                                      <a href="#spro${addJobContractCounter.index}${addJobContractVesselCounter.index} ${addJobContractProdCounter.index}" onClick="javascript:showTvlotTable('${addJobContractCounter.index}', '${addJobContractVesselCounter.index}', '${addJobContractProdCounter.index}');"> 
                                        <img src="images/bluarrowrightsml.gif" height="13" border="0" />
                                      </a> 
                                    </div>
                                    <div id="T${addJobContractCounter.index}bluarrowdownv${addJobContractVesselCounter.index}p${addJobContractProdCounter.index}" style="visibility:hidden;position:relative; text-align:left; z-index: 1;margin-top:7px;margin-left:0px "> 
                                      <a href="#hpro${addJobContractCounter.index}${addJobContractVesselCounter.index} ${addJobContractProdCounter.index}" onClick="javascript:hideTvlotTable('${addJobContractCounter.index}', '${addJobContractVesselCounter.index}', '${addJobContractProdCounter.index}');"> 
                                        <img src="images/bluarrowdownsml.gif" height="7" border="0" />
                                      </a> 
                                    </div>                  
                                  </th>
                                <%-- <td class="TDShadeGrey" width="56%">--%>
								 <td class="TDShadeGrey" style="width:400;wrap:auto;text-wrap:hard-wrap;word-wrap:break-word;">  
                                    <spring:message code="Lot" />: ${addJobContractProd.jobContractProd.jobProductName}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<td class="TDShadeGrey" width="20%" nowrap="yes">
									<spring:message code="productGroup" />:
                                    ${icbfn:displayProductGroup(addJobContractVessel.productGroups, addJobContractProd.jobContractProd.group)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
                                  </td>

								  <a name="addLotPro${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index+1}"></a>

                                  <td align="center" class="TDShadeGrey" nowrap="yes" width="6%" align="center" style="border-right:#7c92be dashed 0px;align:center;">
                                    <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractProd.sortOrderNum" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                    <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractProd.sortOrderNum" cssClass="redstar" />
                                  </td>
                                  <td width="36%" class="TDShadeGrey">&nbsp;</td>
                                  <a name="delProdInspServ${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>

								  <a name="delProdServ${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>
								  
								  <a name="delTest${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>
								  
								  <a name="delManualTest${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>

                                  <a name="delSlate${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>

								  <a name="editLotP${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>
								 
								  <a name="addTestP${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>
                                  <a name="addManTestP${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>

								  <a name="addSlTestP${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>

                                 
                                  <td align="right" class="TDShadeGrey" nowrap="yes" width="4%">
                                    <div style="text-align:right; margin-right:0;">
                    <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                                      <a href="#" style="cursor:hand; text-decoration:none;" onMouseOver="doTooltip(event, '<a href=# onClick=setScrollFlag(\'editLotP${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}\',\'\');popEditLot(\'${addJobContractCounter.index}\',\'${addJobContract.jobContract.contractCode}\',\'${addJobContractVesselCounter.index}\',\'${addJobContractProdCounter.index}\');>Edit Lot</a><br><a href=# onClick=setScrollFlag(\'addTestP${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}\',\'\');popAddTest(\'${addJobContractCounter.index}\',\'${addJobContract.jobContract.contractCode}\',\'${addJobContractVesselCounter.index}\',\'${addJobContractProdCounter.index}\');>Add Test</a><br><a href=# onClick=setScrollFlag(\'addManTestP${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}\',\'\');popAddManualTest(\'${addJobContractCounter.index}\',\'${addJobContract.jobContract.contractCode}\',\'${addJobContractVesselCounter.index}\',\'${addJobContractProdCounter.index}\');>Add Manual Test</a><br><a href=# onClick=setScrollFlag(\'addSlTestP${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}\',\'\');popAddSlate(\'${addJobContractCounter.index}\',\'${addJobContract.jobContract.contractCode}\',\'${addJobContractVesselCounter.index}\',\'${addJobContractProdCounter.index}\');>Add Slate</a><br>${icbfn:servicesPopupStr(addJobContractCounter.index, addJobContractVesselCounter.index, addJobContractProdCounter.index, addJobContract.jobContract.contractCode, addJobContract.addJobContractServices.jobServiceType,currentLocale)}')" onMouseOut="hideTip()">
                                        <img src="images/icoaddall.gif" border="0"/>
                                      </a>
                                      <a href="#">
                                        <img src="images/delete.gif" onClick="javascript:onDeleteProduct('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContractVesselCounter.index}', '${addJobContractProdCounter.index}');" alt="Delete Product" width="13" height="12" hspace="1px" border="0"/>
                                      </a>
                    </c:if>
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td colspan="5" valign="top" >

                                    <!-- ------------ Sample Location Container Table V1 ------------------- -->
                                    <% int seqCount = 0; %>

                                    <div id="T${addJobContractCounter.index}samplelocContainerv${addJobContractVesselCounter.index}p${addJobContractProdCounter.index}" class="productTableSC" style="visibility:hidden; display:none;">
                                      <table width="100%"  cellpadding="0" cellspacing="0" class="greyApplTableSC">
                                        <!--dummy tr -->
                                        <tr style="visibility:visible;height:1px;background-color:#f7f7f7;">
                                          <td width="56%" style="border-bottom:none;border-right:#7c92be dashed 1px; height:1px; padding-left:10px;padding-right:10px; "><img src="images/spacer.gif" width="59" height="1"></td>
                                          <td width="5%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="35" height="1"></td>
                                          <td width="5%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="35" height="1"></td>
                                          <td width="5%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="35" height="1"></td>
                                          <td width="5%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="24" height="1"></td>
                                          <td width="5%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="48" height="1"></td>
                                          <td width="8%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="51" height="1"></td>
                                          <td width="5%" style="border-bottom:none;border-right:none;"><img src="images/spacer.gif" width="45" height="1"></td>
                                        </tr>
                                        <!--dummy tr end -->
                                        <c:forEach items="${addJobContractProd.results}" var="jobContractProductInspectionResult" varStatus="jobContractProductInspectionResultCounter">  
                                          <% seqCount ++; %>
                                          <c:forEach items="${jobContractProductInspectionResult.prebills}" var="prebill" varStatus="prebillStatus">   
                                            <tr>
                                              <td class="row<%=seqCount % 2%>" width="56%" style="border-right:#7c92be dashed 1px;"><form:checkbox path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].selects[${jobContractProductInspectionResultCounter.index}]" cssStyle="float:left" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>&nbsp;${prebill.lineDescription}</td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice * addJobContract.currencyMultiplier}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;
                                                <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].results[${jobContractProductInspectionResultCounter.index}].prebills[${prebillStatus.index}].splitPct" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                                <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].results[${jobContractProductInspectionResultCounter.index}].prebills[${prebillStatus.index}].splitPct" cssClass="redstar" />
                                              </td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;${prebill.discountPct}&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="8%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="5%">
											   <a name="editInsp${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}${jobContractProductInspectionResultCounter.index}"></a>

                                                <div style="text-align:right; margin-right:0;"> 
                          <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                                                  <a href="#" onclick="setScrollFlag('editInsp${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}${jobContractProductInspectionResultCounter.index}','');popEditLot('${addJobContractCounter.index}','${addJobContract.jobContract.contractCode}','${addJobContractVesselCounter.index}','${addJobContractProdCounter.index}','${jobContractProductInspectionResultCounter.index}')">
                                                    <img src="images/icoeditsmall.gif" alt="Edit Inspection" hspace="1px" border="0"/>
                                                  </a> 
                                                  <a href="#" onclick="javascript:doDeleteJobContractProductInspectionResult('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContractVesselCounter.index}', '${addJobContractProdCounter.index}', '${jobContractProductInspectionResultCounter.index}')">
                                                    <img src="images/delete.gif" alt="Delete Product Inspection Service" width="13" height="12" hspace="1px" border="0"/>
                                                  </a> 
                          </c:if>
                                                </div>
                                              </td>
                                            </tr>
                                          </c:forEach>
                                        </c:forEach>

                                        <c:forEach items="${addJobContractProd.addJobContractProductServices.addedJobContractProductServices}" var="jobContractProductServiceExt" varStatus="jobContractProductServiceExtStatus">  
                                          <% seqCount ++; %>
                                          <c:forEach items="${jobContractProductServiceExt.results}" var="jobContractProductServiceResult" varStatus="jobContractProductServiceResultStatus">
                                            <c:forEach items="${jobContractProductServiceResult.prebills}" var="prebill" varStatus="prebillStatus">
                                              <tr>
                                                <td class="row<%=seqCount % 2%>" width="56%" style="border-right:#7c92be dashed 1px;"><form:checkbox path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].addJobContractProductServices.addedJobContractProductServices[${jobContractProductServiceExtStatus.index}].selects[${jobContractProductServiceResultStatus.index}]" cssStyle="float:left" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>&nbsp;${prebill.lineDescription}</td>
                                                <c:if test="${jobContractProductServiceResultStatus.index == 0}">
                                                  <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;align:center;" rowspan="${fn:length(jobContractProductServiceExt.results)}">
                                                    <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].addJobContractProductServices.addedJobContractProductServices[${jobContractProductServiceExtStatus.index}].service.sortOrderNum" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                                    <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].addJobContractProductServices.addedJobContractProductServices[${jobContractProductServiceExtStatus.index}].service.sortOrderNum" cssClass="redstar" />
                                                  </td>
                                                </c:if>
                                                <td class="row<%=seqCount % 2%>" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice * addJobContract.currencyMultiplier}" />&nbsp;</td>
                                                <td class="row<%=seqCount % 2%>" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                                <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;
                                                  <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].addJobContractProductServices.addedJobContractProductServices[${jobContractProductServiceExtStatus.index}].results[${jobContractProductServiceResultStatus.index}].prebills[${prebillStatus.index}].splitPct" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                                  <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].addJobContractProductServices.addedJobContractProductServices[${jobContractProductServiceExtStatus.index}].results[${jobContractProductServiceResultStatus.index}].prebills[${prebillStatus.index}].splitPct" cssClass="redstar" />
                                                </td>
                                                <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;${prebill.discountPct}&nbsp;</td>
                                                <td class="row<%=seqCount % 2%>" width="8%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>

                                                     <a name="eLServ${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>

                                                <c:if test="${jobContractProductServiceResultStatus.index == 0}">
                                                  <td class="row<%=seqCount % 2%>" width="5%" rowspan="${fn:length(jobContractProductServiceExt.results)}">
                                                    <div style="text-align:right; margin-right:0;"> 
                            <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                                                      <a href="#" onclick="setScrollFlag('eLServ${addJobContractCounter.index}${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}','');javascript:popAddEditService('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}','${addJobContractVesselCounter.index}','${addJobContractProdCounter.index}','${jobContractProductServiceExt.service.serviceType}','${jobContractProductServiceExtStatus.index}')">
                                                        <img src="images/icoeditsmall.gif" alt="Edit Lot Service" hspace="1px" border="0"/>
                                                      </a> 
                                                      <a href="#" onclick="javascript:doDeleteJobContractProductService('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContractVesselCounter.index}', '${addJobContractProdCounter.index}', '${jobContractProductServiceExtStatus.index}')">
                                                        <img src="images/delete.gif" alt="Delete Product Service" width="13" height="12" hspace="1px" border="0"/>
                                                      </a> 
                            </c:if>
                                                    </div>
                                                  </td>
                                                </c:if>
                                              </tr>
                                            </c:forEach>
                                          </c:forEach>
                                        </c:forEach>

                                        <c:forEach items="${addJobContractProd.jobContractTestExts}" var="jobContractTestExt" varStatus="jobContractTestExtCounter">   
                                          <% seqCount ++; %>
                                          <c:forEach items="${jobContractTestExt.test.prebills}" var="prebill" varStatus="prebillStatus">   
                                            <tr>
                                              <td class="row<%=seqCount % 2%>" width="56%" style="border-right:#7c92be dashed 1px;"><form:checkbox path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractTestExts[${jobContractTestExtCounter.index}].selected" cssStyle="float:left" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>&nbsp;${prebill.lineDescription}</td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;align:center;">
                                                  <form:input id="sortNum${addJobContractCounter.index}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}${jobContractTestExtCounter.index}" path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractTestExts[${jobContractTestExtCounter.index}].test.sortOrderNum" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                                  <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractTestExts[${jobContractTestExtCounter.index}].test.sortOrderNum" cssClass="redstar" />
                                              </td>

											<c:if test="${command.addJobContracts[addJobContractCounter.index].addJobContractVessels[addJobContractVesselCounter.index].addJobContractProds[addJobContractProdCounter.index].jobContractTestExts[jobContractTestExtCounter.index].test.sortOrderNum !=''}">                            

											<script>                                   testSort('${addJobContractCounter.index}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}${jobContractTestExtCounter.index}','${jobContractTestExtCounter.index}');
											function testSort(index,index1)
											{
											document.getElementById("sortNum"+index).value=index1;
											}
											</script>
											</c:if>

                                              <td class="row<%=seqCount % 2%>" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice * addJobContract.currencyMultiplier}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;
                                                <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractTestExts[${jobContractTestExtCounter.index}].test.prebills[${prebillStatus.index}].splitPct" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                                <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractTestExts[${jobContractTestExtCounter.index}].test.prebills[${prebillStatus.index}].splitPct" cssClass="redstar" />
                                              </td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;${prebill.discountPct}&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="8%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="5%" >
											   <a name="editTestP${addJobContractCounter.index} ${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}"></a>
                                                <div style="text-align:right; margin-right:0;"> 
                          <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                                                  <a href="#" onclick="setScrollFlag('editTestP${addJobContractCounter.index} ${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}','');javascript:popEditTest('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}','${addJobContractVesselCounter.index}','${addJobContractProdCounter.index}','${jobContractTestExtCounter.index}')">
                                                    <img src="images/icoeditsmall.gif" alt="Edit Test" hspace="1px" border="0"/>
                                                  </a> 
                                                  <a href="#" onclick="javascript:doDeleteJobContractTest('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContractVesselCounter.index}', '${addJobContractProdCounter.index}', '${jobContractTestExtCounter.index}')">
                                                    <img src="images/delete.gif" alt="Delete Test" width="13" height="12" hspace="1px" border="0"/>
                                                  </a> 
                          </c:if>
                                                </div>
                                              </td>
                                            </tr>
                                          </c:forEach>
                                        </c:forEach>

                                        <c:forEach items="${addJobContractProd.jobContractManualTestExts}" var="jobContractManualTestExt" varStatus="jobContractManualTestExtCounter">   
                                          <% seqCount ++; %>
                                          <c:forEach items="${jobContractManualTestExt.manualTest.prebills}" var="prebill" varStatus="prebillStatus">   
                                            <tr>
                                              <td class="row<%=seqCount % 2%>" width="56%" class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;"><form:checkbox path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractManualTestExts[${jobContractManualTestExtCounter.index}].selected" cssStyle="float:left" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}" />&nbsp;${prebill.lineDescription}</td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;align:center;">
                                                <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractManualTestExts[${jobContractManualTestExtCounter.index}].manualTest.sortOrderNum" cssClass="inputBox" cssStyle="width:40px;"  disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                                <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractManualTestExts[${jobContractManualTestExtCounter.index}].manualTest.sortOrderNum" cssClass="redstar" />
                                              </td>
                                              <td class="row<%=seqCount % 2%>" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice * addJobContract.currencyMultiplier}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;
                                                <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractManualTestExts[${jobContractManualTestExtCounter.index}].manualTest.prebills[${prebillStatus.index}].splitPct" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}" />
                                                <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractManualTestExts[${jobContractManualTestExtCounter.index}].manualTest.prebills[${prebillStatus.index}].splitPct" cssClass="redstar" />
                                              </td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;${prebill.discountPct}&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="8%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="5%" >
                                                <div style="text-align:right; margin-right:0;"> 

												 <a name="editManTest${addJobContractCounter.index} ${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}${jobContractManualTestExtCounter.index}"></a> 

                          <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                                                 
												  
												  
												  
												  
												  <a href="#" onclick="setScrollFlag('editManTest${addJobContractCounter.index} ${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}${jobContractManualTestExtCounter.index}','');javascript:popAddManualTest('${addJobContractCounter.index}','${addJobContract.jobContract.contractCode}','${addJobContractVesselCounter.index}','${addJobContractProdCounter.index}','${jobContractManualTestExtCounter.index}')">
                                                    <img src="images/icoeditsmall.gif" alt="Edit Manual Test" hspace="1px" border="0"/>
                                                  </a> 
                                                  <a href="#" onclick="javascript:doDeleteJobContractManualTest('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContractVesselCounter.index}', '${addJobContractProdCounter.index}', '${jobContractManualTestExtCounter.index}')">
                                                    <img src="images/delete.gif" alt="Delete Manual Test" width="13" height="12" hspace="1px" border="0"/>
                                                  </a> 
                          </c:if>
                                                </div>
                                              </td>
                                            </tr>
                                          </c:forEach>
                                        </c:forEach>

                                        <c:forEach items="${addJobContractProd.jobContractSlateExts}" var="jobContractSlateExt" varStatus="jobContractSlateExtCounter">   
                                          <% seqCount ++; %>
                                          <c:forEach items="${jobContractSlateExt.slate.prebills}" var="prebill" varStatus="prebillStatus">   
                                            <tr>
                                              <td class="row<%=seqCount % 2%>" width="56%" style="border-right:#7c92be dashed 1px;"><form:checkbox path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractSlateExts[${jobContractSlateExtCounter.index}].selected" cssStyle="float:left" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>&nbsp;${prebill.lineDescription}</td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;align:center;">
                                                  <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractSlateExts[${jobContractSlateExtCounter.index}].slate.sortOrderNum" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                                  <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractSlateExts[${jobContractSlateExtCounter.index}].slate.sortOrderNum" cssClass="redstar" />
                                              </td>
                                              <td class="row<%=seqCount % 2%>" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice * addJobContract.currencyMultiplier}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="7%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;
                                                <form:input path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractSlateExts[${jobContractSlateExtCounter.index}].slate.prebills[${prebillStatus.index}].splitPct" cssClass="inputBox" cssStyle="width:40px;" disabled="${command.addJobContracts[addJobContractCounter.index].contractViewOnly}"/>
                                                <form:errors path="addJobContracts[${addJobContractCounter.index}].addJobContractVessels[${addJobContractVesselCounter.index}].addJobContractProds[${addJobContractProdCounter.index}].jobContractSlateExts[${jobContractSlateExtCounter.index}].slate.prebills[${prebillStatus.index}].splitPct" cssClass="redstar" />
                                              </td>
                                              <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;${prebill.discountPct}&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" width="8%" align="center" style="border-right:#7c92be dashed 1px;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                               
											    <a name="eslate${addJobContractCounter.index} ${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}${jobContractSlateExtCounter.index}"></a> 
											   
                                              <td class="row<%=seqCount % 2%>" width="5%" >
                                                <div style="text-align:right; margin-right:0;"> 
                          <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                                                  <a href="#" onclick="setScrollFlag('eslate${addJobContractCounter.index} ${addJobContract.jobContract.contractCode}${addJobContractVesselCounter.index}${addJobContractProdCounter.index}${jobContractSlateExtCounter.index}','');javascript:popEditSlate('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}','${addJobContractVesselCounter.index}','${addJobContractProdCounter.index}','${jobContractSlateExtCounter.index}')">
                                                    <img src="images/icoeditsmall.gif" alt="Edit Slate" hspace="1px" border="0"/>
                                                  </a> 
                                                  <a href="#" onclick="javascript:doDeleteJobContractSlate('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}','${addJobContractVesselCounter.index}', '${addJobContractProdCounter.index}','${jobContractSlateExtCounter.index}')">
                                                    <img src="images/delete.gif" alt="Delete Slate" width="13" height="12" hspace="1px" border="0"/>
                                                  </a>
                          </c:if>
                                                </div>
                                              </td>
                                            </tr>
                                          </c:forEach>
                                        </c:forEach>
                                      </table>
                                    </div>
                                  <!-- ------------SAMPLE LOCATIONS TABLE V1 END -------------------- -->
                                  </td>
                                </tr>
                              </table>
                              <!--V1 Lot1 end-->
                              </c:forEach>
                            </div>

                            <!-- ---------------VESSEL 1 PRODUCT 1 END ----------------------- -->
                          </div>
                          <!-- ---------------------VESSEL 1 Data END --------------------------- -->
                        </div>
                        <!-- ------------------------- VESSEL 1 Container END ----------------------------- -->
                      </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                </table>
                <table class="mainApplTable" cellspacing="0" cellpadding="0" style="width:100%">
                  <tbody>
                    <tr>
                      <th width="55%">&nbsp;</th>
                      <th nowrap="yes" width="9%">&nbsp;</th>
                      <th style="text-align:right" width="16%">&nbsp;</th>
                      <th style="text-align:right;padding-right:60">
                        Total: <f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${addJobContract.invoiceTotalAmt}" />
                      </th>
                    </tr>                  
                  </tbody>
                </table>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                  <tr>
                    <td>
                    <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                    <!-- START : #119240 -->
					<!-- <input name="Submit2" type="button" class="button1" onclick="javascript:doRefreshJobContractFromJob('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}');" value="Refresh from Job" style="width:15%;"> -->
					<input name="Submit2" type="button" class="button1" onclick="javascript:doRefreshJobContractFromJob('${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}');" value="Refresh from Job" >
					<!-- END : #119240 -->
                    </c:if>
                    </td>
		
                    <td style="text-align:right">
			          <c:if test="${jobLevelServicePopupStr != null}">
            <c:if test="${!command.addJobContracts[addJobContractCounter.index].contractViewOnly}">
                        <a href="#" style="cursor:hand; text-decoration:none;" onMouseOver="doTooltip(event, '${jobLevelServicePopupStr}')" onMouseOut="hideTip()">
                          <img src="images/icoadddocgreen.gif" width="12" height="14" border="0" align="absmiddle"/>
                        </a>
              </c:if>
                      </c:if>
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
                      <a href="#" onclick="javascript:doNextAction('next', '${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContract.contractInProgress}');">
                        <img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page">
                      </a>
                      <a href="#" onclick="javascript:doNextAction('false', '${addJobContractCounter.index}', '${addJobContract.jobContract.contractCode}', '${addJobContract.contractInProgress}');">
                        <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" align="absmiddle" />
                      </a>
                    </td>
                  </tr>
                </table>
              </div>            
              <!----------------- TAB 2 CONTAINER END ------------------------------ -->
             <SCRIPT LANGUAGE="JavaScript">
         	location.href="#${command.scrollFlag}"	
	        </SCRIPT>
			  </c:forEach>
            </div>
          </div>
          <script type="text/javascript">

            //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
            dolphintabs.init("tabnav", '${command.contractIndex}')

          </script>
          <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
          <table width="100%" cellspacing="0">
            <tr>
              <td height="24" style="text-align:right; padding-right:0px;">
                <select name="sel5" id="sel5" class="SelectionBox" onChange="MM_jumpMenu('parent',this,1)">
                  <option selected><spring:message code="Go To" /> ... &gt;</option>
                  <option value="edit_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"><spring:message code="entry" /></option>
                  <option value="edit_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}"><spring:message code="jobInstructions" /></option>
                </select>            
              </td>
            </tr>
          </table>
        </div>
        <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->  
      </c:if>
    </td>
  </tr>
</form:form>
</table>

<script type="text/javascript">

  <c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
    <c:forEach items="${addJobContract.addJobContractVessels}" var="addJobContractVessel" varStatus="addJobContractVesselCounter">                               

      <c:choose>
        <c:when test="${addJobContractVessel.displayStatus == 'HIDE'}">
          hideTV('${addJobContractCounter.index}', '${addJobContractVesselCounter.index}');
        </c:when>
        <c:otherwise>
          showTV('${addJobContractCounter.index}', '${addJobContractVesselCounter.index}');
        </c:otherwise>
      </c:choose>

      <c:forEach items="${addJobContractVessel.addJobContractProds}" var="addJobContractProd" varStatus="addJobContractProdCounter">   
      <c:choose>
        <c:when test="${addJobContractProd.displayStatus == 'HIDE'}">
          hideTvlotTable('${addJobContractCounter.index}', '${addJobContractVesselCounter.index}', '${addJobContractProdCounter.index}');
        </c:when>
        <c:otherwise>
          showTvlotTable('${addJobContractCounter.index}', '${addJobContractVesselCounter.index}', '${addJobContractProdCounter.index}');
        </c:otherwise>
      </c:choose>
      </c:forEach>
        
    </c:forEach>
  </c:forEach>

</script>

<!-- ----------------------------------- Add Test Lookup ------------------------------------------------- -->
<c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
<div class="sample_popup" id="addtest_${addJobContractCounter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="addtest_drag_${addJobContractCounter.index}" style="width:800px; "> 
  <img class="menu_form_exit"   id="addtest_exit_${addJobContractCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Add Test </div>
  <div class="menu_form_body" id="addtestbody_${addJobContractCounter.index}"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="addtestbox_${addJobContractCounter.index}" width="100%" height="230px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
</c:forEach>
<!-- --------------------------------- Add Test Lookup END ----------------------------------------- -->


<!-- ----------------------------------- Add Manual Test Lookup ------------------------------------------------- -->
<c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
<div class="sample_popup" id="addmanualtest_${addJobContractCounter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="addmanualtest_drag_${addJobContractCounter.index}" style="width:800px; "> 
  <img class="menu_form_exit"   id="addmanualtest_exit_${addJobContractCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="addManualTest"/> </div>
  <div class="menu_form_body" id="addmanualtest_${addJobContractCounter.index}"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="addmanualtestbox_${addJobContractCounter.index}" width="100%" height="200px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
</c:forEach>
<!-- --------------------------------- Add Manual Test Lookup END ----------------------------------------- -->


<!-- ----------------------------------- Add Slate Lookup ------------------------------------------------- -->
<c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
<div class="sample_popup" id="addslate_${addJobContractCounter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="addslate_drag_${addJobContractCounter.index}" style="width:800px; "> 
  <img class="menu_form_exit"   id="addslate_exit_${addJobContractCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="addSlate"/></div>
  <div class="menu_form_body" id="addslatebody_${addJobContractCounter.index}"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="addslatebox_${addJobContractCounter.index}" width="100%" height="200px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
</c:forEach>
<!-- --------------------------------- Add Slate Lookup END ----------------------------------------- -->


<!-- ----------------------------------- Edit Lot Lookup ------------------------------------------------- -->
<c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
<div class="sample_popup" id="editlot_${addJobContractCounter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="editlot_drag_${addJobContractCounter.index}" style="width:800px; "> 
  <img class="menu_form_exit"   id="editlot_exit_${addJobContractCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="addEditLot"/></div>
  <div class="menu_form_body" id="editlotbody_${addJobContractCounter.index}"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="editlotbox_${addJobContractCounter.index}" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
</c:forEach>
<!-- --------------------------------- Edit Lot Lookup END ----------------------------------------- -->

<!-- ----------------------------------- Edit Test ------------------------------------------------- -->
<c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
<div class="sample_popup" id="edittest_${addJobContractCounter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="edittest_drag_${addJobContractCounter.index}" style="width:800px; "> 
  <img class="menu_form_exit"   id="edittest_exit_${addJobContractCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="editTest"/></div>
  <div class="menu_form_body" id="edittestbody_${addJobContractCounter.index}"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="edittestbox_${addJobContractCounter.index}" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
</c:forEach>
<!-- --------------------------------- Edit Test END ----------------------------------------- -->

<!-- ----------------------------------- Edit Slate ------------------------------------------------- -->
<c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
<div class="sample_popup" id="editslate_${addJobContractCounter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="editslate_drag_${addJobContractCounter.index}" style="width:800px; "> 
  <img class="menu_form_exit"   id="editslate_exit_${addJobContractCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="editSlate"/></div>
  <div class="menu_form_body" id="editslatebody_${addJobContractCounter.index}"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="editslatebox_${addJobContractCounter.index}" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
</c:forEach>
<!-- --------------------------------- Edit Test END ----------------------------------------- -->

<!-- ----------------------------------- Add Vessel ------------------------------------------------- -->
<div class="sample_popup" id="addvessel" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="addvessel_drag" style="width:800px; "> 
  <img class="menu_form_exit"   id="addvessel_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="addEditVessel"/></div>
  <div class="menu_form_body" id="addvesselbody"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="addvesselbox" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
<!-- --------------------------------- Add Vessel END ----------------------------------------- -->

<!-- ----------------------------------- Add/Edit Service ------------------------------------------------- -->
<div class="sample_popup" id="addeditserivce" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="addeditserivce_drag" style="width:800px; "> 
  <img class="menu_form_exit"   id="addeditserivce_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="addEditService"/></div>
  <div class="menu_form_body" id="addeditserivcebody"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="addeditserivcebox" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
<!-- --------------------------------- Add/Edit Service END ----------------------------------------- -->
