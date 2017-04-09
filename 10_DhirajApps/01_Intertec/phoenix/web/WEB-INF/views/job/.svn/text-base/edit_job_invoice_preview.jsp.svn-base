<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@taglib prefix="ajax" uri="http://ajaxtags.org/tags/ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script>

  var prebill_array = new Array(); 
  
  function previewInvoice()
  {
    document.jobOrderEditInvoicePreviewForm.refreshing.value="previewInvoice";
    document.jobOrderEditInvoicePreviewForm.submit();
  }  

  function generateInvoice(contractIndex)
  {
    document.jobOrderEditInvoicePreviewForm.refreshing.value="generateInvoice";
    //document.jobOrderEditInvoicePreviewForm.contractCd.value=contractCode;
    document.jobOrderEditInvoicePreviewForm.contractIndex.value=contractIndex;
    document.jobOrderEditInvoicePreviewForm.submit();
  }  
  function calculateInvoice()
  {
    document.jobOrderEditInvoicePreviewForm.refreshing.value="calculateInvoice";
    document.jobOrderEditInvoicePreviewForm.submit();
  } 
 function hideGenerateInvoice()
 {
	  var jobStatus=document.getElementById("sel1").value;
	  if(jobStatus == '1')
	  document.getElementById("ginvoice").style.visibility = "hidden";
	  else
	  document.getElementById("ginvoice").style.visibility = "visible";
 }
  function doMySubmit(navigateFlag,refreshing,contractIndex,tabsrc)
  {
	var jobStatus = document.getElementById("sel1").value;
	
    if(jobStatus == '1' && navigateFlag=='true')
    {
		confirm("Please select the Job Status as Open to navigate to Invoice page.");
		return false;
    } 
	
    if(refreshing == 'calc')
    {
            var prebillId="";
        for(var i=0;i<prebill_array.length;i++)
          {
            if(prebill_array[i]!= undefined)
            {
              
              if(prebillId == "")
                prebillId = prebill_array[i];
              else
                prebillId = prebillId + ";" + prebill_array[i];
            }
            
          }  
          
          document.jobOrderEditInvoicePreviewForm.chosenPrebillIds.value=prebillId;
    }
    document.jobOrderEditInvoicePreviewForm.refreshing.value=refreshing;
    document.jobOrderEditInvoicePreviewForm.contractIndex.value=contractIndex;
    if(tabsrc == 'edit_job_invoice_preview_tab1.htm')
      tab1Pricing(contractIndex); 
    if(tabsrc == 'edit_job_invoice_preview_tab2.htm')
      tab2Tax(contractIndex);
    if(tabsrc == 'edit_job_invoice_preview_tab3.htm')
      tab3TestnService(contractIndex);
    if(tabsrc == 'edit_job_invoice_preview_tab4.htm')
      tab4TestnService(contractIndex);
     // document.jobOrderEditInvoicePreviewForm.submit();
  }  
  
  function busStreamValid(contractIndex){
  var fr = frames['myIframe'+contractIndex];
    if(fr && fr.document)
    {
      fr=fr.document.forms[0];
    var listArray =  fr.busstreamsel;
        if(listArray != null){
          for (z=0;z<listArray.length;z++){
              if(listArray[z].options!=null && listArray[z].options.length>1 && listArray[z].selectedIndex==0){
                confirm("Please select Business Stream for the line item and save!");
                return false;
              }
           }
        }
        return true;
    }
  }
  
  function adjustSize(contractLength)
  {
    for(var i=0;i<contractLength;i++)
    {
    var height = document.getElementById("fixedAreaId").clientHeight + 20;
    document.getElementById("invpreview").height = height;
    }
  }

  function tab1Pricing(contractIndex) 
  {
    var fr = frames['myIframe'+contractIndex];
    if(fr && fr.document)
    {
      fr=fr.document.forms[0];
      fr.tabSrc.value = "edit_job_invoice_preview_tab1.htm";
    document.jobOrderEditInvoicePreviewForm.tabSource.value = "edit_job_invoice_preview_tab1.htm";
    document.jobOrderEditInvoicePreviewForm.contractIndex.value = contractIndex;
      fr.submit();
    }
  }
  function tab2Tax(contractIndex) {
     var fr = frames['myIframe'+contractIndex];
    if(fr && fr.document)
    {
    
      fr=fr.document.forms[0];
      fr.tabSrc.value = "edit_job_invoice_preview_tab2.htm";
    document.jobOrderEditInvoicePreviewForm.tabSource.value = "edit_job_invoice_preview_tab2.htm";
    document.jobOrderEditInvoicePreviewForm.contractIndex.value = contractIndex;
      fr.submit();
    }
  }
  function tab3TestnService(contractIndex) {
     var fr = frames['myIframe'+contractIndex];
    if(fr && fr.document)
    {
      fr=fr.document.forms[0];
      fr.tabSrc.value = "edit_job_invoice_preview_tab3.htm";
    document.jobOrderEditInvoicePreviewForm.tabSource.value = "edit_job_invoice_preview_tab3.htm";
    document.jobOrderEditInvoicePreviewForm.contractIndex.value = contractIndex;
      fr.submit();
    }
  }
  function tab4TestnService(contractIndex) {
    var fr = frames['myIframe'+contractIndex];
    if(fr && fr.document)
    {
      fr=fr.document.forms[0];
      fr.tabSrc.value = "edit_job_invoice_preview_tab4.htm";
    document.jobOrderEditInvoicePreviewForm.tabSource.value = "edit_job_invoice_preview_tab4.htm";
    document.jobOrderEditInvoicePreviewForm.contractIndex.value = contractIndex;
      fr.submit();
    }
  }
  
  function updateBranchAllocIframeSrc(prebillId,branchName,buName,contractCode,contractIndex)
  {
    document.getElementById('branchallIframe').setAttribute("src","branch_alloc_popup.htm?prebillId="+prebillId+"&allocType=single&branchName="+branchName+"&buName="+buName+"&contractIndex="+contractIndex+"&contractCode="+contractCode);
  }
  
  function updateBranchAllocIframeSrcForMultiplePrebills(branchName,buName,contractCode,contractIndex)
  {
  
      var prebillId="";
    for(var i=0;i<prebill_array.length;i++)
      {
        if(prebill_array[i]!= undefined)
        {
          
          if(prebillId == "")
            prebillId = prebill_array[i];
          else
            prebillId = prebillId + ";" + prebill_array[i];
        }
        
      }
      //Build the list of prebills again if   
      if(prebillId == "")    
      {
        confirm("Please select invoice line items for Branch Allocation");
        return;
      }
      else
      {
      document.getElementById('branchallIframe').setAttribute("src","branch_alloc_popup.htm?prebillId="+prebillId+"&allocType=single&branchName="+branchName+"&buName="+buName+"&contractIndex="+contractIndex+"&contractCode="+contractCode);
      popup_show('branchall', 'branchall_drag', 'branchall_exit', 'screen-corner', 120, 20); 
      hideIt();
      showPopupDiv('branchall','branchallbody');
      popupboxenable();
      }
      
  }    
  
  function updateChosenPrebillIds(contractIndex,prebillIndex,prebillId) 
  {
    if(document.getElementById("chk"+contractIndex+prebillIndex).checked)
    {
      
      prebill_array[prebill_array.length + 1] = prebillId;
    }
    else
    {
      var prebillIndex;
      for(var i=0;i<prebill_array.length;i++)
      {
        if(prebill_array[i] == prebillId)
        {
          
          prebillIndex = i;
        }
        
      }
      prebill_array.splice(prebillIndex,1);
      
    }
    
                var prebill="";
        for(var i=0;i<prebill_array.length;i++)
          {
            if(prebill_array[i]!= undefined)
            {
              
              if(prebill == "")
                prebill = prebill_array[i];
              else
                prebill = prebill + ";" + prebill_array[i];
            }
            
          }  
          
          document.jobOrderEditInvoicePreviewForm.chosenPrebillIds.value=prebill;
    
  }
  
  function setTabIndex(tabIndex)
  {
    document.jobOrderEditInvoicePreviewForm.tabName.value=tabIndex;
    document.jobOrderEditInvoicePreviewForm.contractIndex.value=tabIndex;    
  }
  
function selectAllPrebills(contractIndex,prebillCount)    
{
  selectAllElemName = "selectAll"+contractIndex;
  if(document.getElementById(selectAllElemName).checked)
  {
    for(i=0;i<prebillCount;i++)
     {
     var elemName = "chk" + contractIndex + i;
     document.getElementById(elemName).checked = true;
     prebillId = document.getElementById(elemName).value;
     updateChosenPrebillIds(contractIndex,i,prebillId);
     }
   }
   else
    clearAllPrebills(contractIndex,prebillCount);
   
}

function clearAllPrebills(contractIndex,prebillCount)   
{
  for(i=0;i<prebillCount;i++)
   {
   var elemName = "chk" + contractIndex + i;
   document.getElementById(elemName).checked = false;
   prebillId = document.getElementById(elemName).value;
   updateChosenPrebillIds(contractIndex,i,prebillId);
   }
}
function updateJobDesc(index)
{
  
  document.getElementById('descr'+index).value = document.jobOrderEditInvoicePreviewForm.defaultJobDesc.value;
}  

function warnUser(navigationUrl)
{
  if(document.jobOrderEditInvoicePreviewForm.warnUserFlag.value == 'warn' || document.jobOrderEditInvoicePreviewForm.warnUserFlag.value == 'warned')
    document.jobOrderEditInvoicePreviewForm.warnUserFlag.value = 'navigate';
  else
    document.jobOrderEditInvoicePreviewForm.warnUserFlag.value = "warn";
    
  document.jobOrderEditInvoicePreviewForm.navigationUrl.value = navigationUrl;
  document.jobOrderEditInvoicePreviewForm.submit();
}

function showPeriodWarning(periodflag,contractIndex,tabSrc)
{
 
  if(confirm("Are you sure you want to generate invoice?")==false)
  {
	generatebutton.disabled = false;
	return false;
  }
  if(periodflag=='false')
  {
    if (confirm("Prior month GL is now closed. Your invoice will now be posted to the current month.Do you want to proceed?")==true)
    {
      generatebutton.disabled = true;
      setTabIndex(contractIndex);
      //generateInvoice(contractIndex); 
      doMySubmit('false','generateInvoice',contractIndex,tabSrc);
    }
    else
    {
      return false;
    }
  }
  else
  {
      generatebutton.disabled = true;
    setTabIndex(contractIndex);
    //generateInvoice(contractIndex); 
    doMySubmit('false','generateInvoice',contractIndex,tabSrc);
  }
}
//START : 53791
function togglePostvNegtv(pIdnrfr, pIndx){
    objHdnIdnrfr = document.jobOrderEditInvoicePreviewForm.elements['togglePostvNegtv'][pIndx];

	if(objHdnIdnrfr){
        hdnIdnrfr = objHdnIdnrfr.value;		
		if('NEGTV' == hdnIdnrfr){
				document.getElementById('signUnitPrc'+pIndx).innerHTML = '+';
				objHdnIdnrfr.value = 'POSTV';
								
				document.jobOrderEditInvoicePreviewForm.signIndex.value = pIndx;
				document.jobOrderEditInvoicePreviewForm.signIdntfr.value = 'NEGTV';
				document.jobOrderEditInvoicePreviewForm.submit();								
		}
		else if('POSTV' == hdnIdnrfr){
				document.getElementById('signUnitPrc'+pIndx).innerHTML = '&#8211;';
				objHdnIdnrfr.value = 'NEGTV';
				
				document.jobOrderEditInvoicePreviewForm.signIndex.value = pIndx;
				document.jobOrderEditInvoicePreviewForm.signIdntfr.value = 'POSTV';
				document.jobOrderEditInvoicePreviewForm.submit();				
		}
	}
}
//END : 53791

// START : #119240
function nextList()
{
document.jobOrderEditInvoicePreviewForm.showNextListFlag.value="next";
document.jobOrderEditInvoicePreviewForm.submit();
}

function prevList()
{
document.jobOrderEditInvoicePreviewForm.showPrevListFlag.value="prev";
document.jobOrderEditInvoicePreviewForm.submit();
}

function noPrevList()
{
	document.jobOrderEditInvoicePreviewForm.noPrevJob.value="true";
	document.jobOrderEditInvoicePreviewForm.submit();
}

function noNextList()
{
	document.jobOrderEditInvoicePreviewForm.noNextJob.value="true";
	document.jobOrderEditInvoicePreviewForm.submit();
}

// END : #119240

</script>

<icb:list var="invoiceType">
  <icb:item>invoiceType</icb:item>
</icb:list>



<form:form name="jobOrderEditInvoicePreviewForm" method="POST" action="edit_job_invoice_preview.htm">
 <div style="color:red;"><form:errors cssClass="error" /></div>
<input type="hidden" name="refreshing" value="true" />
<input type="hidden" name="chosenPrebillIds" value="" />
  <input type="hidden" name="contractCd" value="" />
<input type="hidden" name="tabSource" value="edit_job_invoice_preview_tab1.htm" />

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
<form:hidden path="prebillId" />
<form:hidden path="addOrDeletePrebill" />
<form:hidden path="tabName" />
<form:hidden path="defaultJobDesc" />
<form:hidden path="navigationUrl" />
<form:hidden path="warnUserFlag" />
<form:hidden path="contractIndex" />
<form:hidden path="previewFlag" />

<form:hidden path="signIndex" />
<form:hidden path="signIdntfr" />

<c:if test="${requestScope['noJobMessage'] != null}">  
  <div style="color:green;">
    ${requestScope.noJobMessage}
  </div>
</c:if>

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
  

<table width="97%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top"><!-- BREADCRUMB TRAIL  -->
      <div id="breadcrumbContainer" >
        <table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/intopbluetrailbg.jpg">
          <tr>
            <td width="25"><img src="images/inlfttrailcorner.gif" width="8" height="22"></td>
            <td><table height="22" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="breadcrumbtrailDeactive" style="background:none; padding-left:5px;">&#9658; 
                     <a href="${urlPrefix}_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                    <!--<a href="#" onClick="warnUser('${urlPrefix}_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                      <f:message key="entry"/>
                    </a>
                  </td>
                  <td class="breadcrumbtrailDeactive">
                    <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 2}">               
                     <a href="${urlPrefix}_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                    <!--<a href="#" onClick="warnUser('${urlPrefix}_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
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
                    <!--<a href="#" onClick="warnUser('edit_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                      <f:message key="selectCharges"/>
                    </a>
                    </c:when>
                    <c:otherwise>
                    <f:message key="selectCharges"/>
                    </c:otherwise>
                    </c:choose>
                    </td>
                  <td class="breadcrumbtrailActive"> 
                    <f:message key="preview"/>
                  </td>               
 
              <td class="breadcrumbtrailDeactive"> 
                   <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 5}">               
                     <a href="edit_job_view_invoice.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                    <!--<a href="#" onClick="warnUser('edit_job_view_invoice.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
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

              </table></td>
            <td align="right"><img src="images/inrttrailcorner.gif" width="7" height="22"></td>
          </tr>
        </table>
      </div>
      <!-- BREADCRUMB TRAIL END -->
      
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractStatus">
                <li>
                  <a href="#" rel="tab${addJobContractStatus.index + 1}"  onClick="setTabIndex(${addJobContractStatus.index});doMySubmit('false','true','${addJobContractStatus.index}','');document.jobOrderEditInvoicePreviewForm.submit();">
                    <span>${addJobContractStatus.index + 1}. ${addJobContract.jobContract.contractCode}</span>
                  </a>
                </li>
              </c:forEach>
            </ul>
            <div align="right">
              <table cellspacing="0" cellpadding="0" border="0">
                <tr>
                  <td>
                    <select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
                      <option selected>Go to ... &gt;</option>
                      <option value="edit_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="entry"/></option>
                      <option value="edit_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="jobInstructions"/></option>
                      <option value="edit_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="selectCharges"/></option>
                    </select>
                  </td>
                </tr>
              </table>
            </div>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">

            <!-- ---------------------------------------------------------------------------------------------------------------------- -->
            <!-- ------------------------- TAB 1 CONTAINER ------------------------------- -->
            <c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractStatus">
            
            <div id="tab${addJobContractStatus.index + 1}" class="innercontent1" >
      <c:if test="${addJobContractStatus.index==command.contractIndex}">
              <!-- Invoice Preview Table Start -->
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
        
                <tr>
                  <th colspan="2"><f:message key="invoicePreview"/><img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/><f:message key="jobId"/> : ${command.jobOrder.jobNumber }    </th>    
                  <th nowrap>
                  <c:choose>
              <c:when test="${addJobContractStatus.index==0}">
                <f:message key="status" />: <%-- <form:select id="sel1" 
                  cssClass="selectionBox" path="jobOrder.jobStatus"
                  items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name"
                  itemValue="value" />   --%> 

			 <form:select id="sel1" onchange="hideGenerateInvoice();"
                  cssClass="selectionBox" path="jobOrder.jobStatus"
                  items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name"
                  itemValue="value" />           
              </c:when>
              <c:otherwise>
                <f:message key="status" />: <form:select id="sel1"
                  cssClass="selectionBox" path="jobOrder.jobStatus"
                  items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name"
                  itemValue="value" disabled="true"/> 
              </c:otherwise>
              </c:choose>
          <form:errors path="jobOrder.jobStatus"
              cssClass="redstar" /> 
                  </th>
                  <th style="text-align:right">
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
                    <a href="#" onclick="javascript:setTabIndex(${addJobContractStatus.index});doMySubmit('false','calc',${addJobContractStatus.index},'${addJobContract.invoicePreviewTabSrc }');">
                      <img src="images/icocalculate.gif" alt="Calculate Invoice" width="14" height="14" border="0" align="absmiddle"/></a> 
                      <authz:authorize ifAnyGranted="GenerateInvoice">
                    <c:choose>
                    <c:when test="${command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='7200'}">
                      <a href="#" onclick="javascript:setTabIndex(${addJobContractStatus.index});doMySubmit('false','next',${addJobContractStatus.index},'${addJobContract.invoicePreviewTabSrc }');">
                    </c:when>
                    <c:otherwise>                      
                      <a href="#" onclick="javascript:setTabIndex(${addJobContractStatus.index});if(busStreamValid(${addJobContractStatus.index})){ doMySubmit('true','next',${addJobContractStatus.index},'${addJobContract.invoicePreviewTabSrc }');}">
                  </c:otherwise>
                    </c:choose>
                      <img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a>
                    </authz:authorize>
                    <c:choose>
                    <c:when test="${command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='7200'}">
                      <a href="#" onclick="javascript:setTabIndex(${addJobContractStatus.index});doMySubmit('false','false',${addJobContractStatus.index},'${addJobContract.invoicePreviewTabSrc }');">
                    </c:when>
                    <c:otherwise>
                      <a href="#" onclick="javascript:setTabIndex(${addJobContractStatus.index});if(busStreamValid(${addJobContractStatus.index})) { doMySubmit('false','false',${addJobContractStatus.index},'${addJobContract.invoicePreviewTabSrc }');}">
                    </c:otherwise>
                  </c:choose>
                      <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" align="absmiddle"/></a> 
                  </th>  
                </tr>
                <tr>
                  <td width="15%" class="TDShade"><f:message key="billingContact"/>:
                      <!-- <span class="redstar">*</span> -->    
                  </td>
                  <td width="45%" class="TDShadeBlue"><strong>${command.addJobContracts[addJobContractStatus.index].jobContract.billingContactName}</strong></td>
                  <td width="15%" class="TDShade"><f:message key="invoiceType"/>: </td>
                  <td width="25%" class="TDShadeBlue"><!-- <strong>Regular</strong> -->
                  
                  <c:choose>
                  <c:when test="${command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='7200'}">       
                  <form:select id="sel6" cssClass="selectionBox" path="addJobContracts[${addJobContractStatus.index}].jobContract.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value" disabled="true"/>
                  </c:when>
                  <c:otherwise>
                  <form:select id="sel6" cssClass="selectionBox" path="addJobContracts[${addJobContractStatus.index}].jobContract.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value"/>
                  </c:otherwise>
                  </c:choose>                                    
                                    
          
                  </td>
                </tr>
                <tr>
                  <td width="15%" class="TDShade"><f:message key="billingAddress"/>: </td>
                  <td class="TDShadeBlue"><strong>${command.addJobContracts[addJobContractStatus.index].billingAddress}</strong></td>
                  <td class="TDShade">&nbsp;</td>
                  <td class="TDShadeBlue">&nbsp;</td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="remitTo"/>: </td>
                  <td class="TDShadeBlue"><strong>${command.addJobContracts[addJobContractStatus.index].jobContract.bankCd}</strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<f:message key="acct"/>: <strong>${command.addJobContracts[addJobContractStatus.index].jobContract.bankAcctKey}</strong></td>
                  <td class="TDShade"><f:message key="paymentterms"/>: </td>
                  <td class="TDShadeBlue"><strong>${command.addJobContracts[addJobContractStatus.index].jobContract.pymntTermsCd}</strong></td>
                </tr>
                <tr>
                  <td class="TDShade" valign="top">
                  <c:choose>
                  <c:when test="${command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='7200'}">                         
                  <f:message key="invoiceDesc"/>
                  </c:when>
                  <c:otherwise>
                  <a href="#" onclick="javascript:updateJobDesc(${addJobContractStatus.index});"><f:message key="invoiceDesc"/></a>
                  </c:otherwise>
                  </c:choose>
                  : </td>
                  <td class="TDShadeBlue" colspan="3">
                  <c:choose>
                  <c:when test="${command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='7200'}">       
                  <form:textarea id="descr${addJobContractStatus.index}" cols="122" cssClass="inputBox" rows="4" path="addJobContracts[${addJobContractStatus.index}].jobContract.invoiceDescr" disabled="true"/>
                  </c:when>
                  <c:otherwise>
                  <form:textarea id="descr${addJobContractStatus.index}" cols="122" cssClass="inputBox" rows="4" path="addJobContracts[${addJobContractStatus.index}].jobContract.invoiceDescr"/>
                  </c:otherwise>
                  </c:choose>                     
                  
                  </td>
                </tr>
              </table>
              <!-- Invoice Preview Table End -->
              
              <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                  <td style="padding-top:10px; border-bottom:#c2d7e0 solid 2px;">
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td  style="padding-right:3px;">
                          <a href="#" onClick="javascript:setTabIndex(${addJobContractStatus.index });tab1Pricing(${addJobContractStatus.index});">
                            <c:choose>
                              <c:when test="${addJobContract.invoicePreviewTabSrc=='edit_job_invoice_preview_tab1.htm'}">
                                <img src="images/tab1Pricing_act.gif" name="tab${addJobContractStatus.index}1"  width="124" height="19" border="0" id="tab${addJobContractStatus.index}1" />
                              </c:when>
                              <c:otherwise>
                                <img src="images/tab1Pricing_deact.gif" name="tab${addJobContractStatus.index}1"  width="124" height="19" border="0" id="tab${addJobContractStatus.index}1" />
                              </c:otherwise>
                            </c:choose>
                          </a>
                        </td>
                        <td style="padding-right:3px;">
                          <a href="#" onClick="javascript:setTabIndex(${addJobContractStatus.index });tab2Tax(${addJobContractStatus.index});">
                            <c:choose>
                              <c:when test="${addJobContract.invoicePreviewTabSrc=='edit_job_invoice_preview_tab2.htm'}">
                                <img src="images/tab2Tax_act.gif" name="tab${addJobContractStatus.index}2" width="52" height="19" border="0" id="tab${addJobContractStatus.index}2">
                              </c:when>
                              <c:otherwise>
                                <img src="images/tab2Tax_deact.gif" name="tab${addJobContractStatus.index}2" width="52" height="19" border="0" id="tab${addJobContractStatus.index}2">
                              </c:otherwise>
                            </c:choose>
                          </a>
                        </td>
                        <td>
                          <a href="#" onClick="javascript:setTabIndex(${addJobContractStatus.index });tab3TestnService(${addJobContractStatus.index});">
                            <c:choose>
                              <c:when test="${addJobContract.invoicePreviewTabSrc=='edit_job_invoice_preview_tab3.htm'}">
                                <img src="images/tab3TestnService_act.gif" name="tab${addJobContractStatus.index}3" width="151" height="19" border="0" id="tab${addJobContractStatus.index}3">
                              </c:when>
                              <c:otherwise>
                                <img src="images/tab3TestnService_deact.gif" name="tab${addJobContractStatus.index}3" width="151" height="19" border="0" id="tab${addJobContractStatus.index}3">
                              </c:otherwise>
                            </c:choose>
                          </a>
                        </td>
                        <td>
                          <a href="#" onClick="javascript:setTabIndex(${addJobContractStatus.index });tab4TestnService(${addJobContractStatus.index});">
                            <c:choose>
                              <c:when test="${addJobContract.invoicePreviewTabSrc=='edit_job_invoice_preview_tab4.htm'}">
                                <img src="images/tab4Viewall_act.gif" name="tab${addJobContractStatus.index}4" border="0" id="tab${addJobContractStatus.index}4">
                              </c:when>
                              <c:otherwise>
                                <img src="images/tab4Viewall_deact.gif" name="tab${addJobContractStatus.index}4" border="0" id="tab${addJobContractStatus.index}4">
                              </c:otherwise>
                            </c:choose>
                          </a>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
                
                <tr>
                  <td>
                    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td id="fixedAreaId" valign="top" width="125" style=" padding:0px;">
                          <table border="0" width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="border-right-width:0px">
                            <tr>
                              <th width="15" nowrap>&nbsp;</th>
                              <th width="15" nowrap>&nbsp;</th>
                              <th width="15" nowrap>&nbsp;</th>
                              <th style="width:200px;">&nbsp;</th>
                            </tr>
                            <tr>
                              <td colspan="3" height="21" nowrap>
                                <f:message key="invoiceItems"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="#" onClick="updateBranchAllocIframeSrcForMultiplePrebills('${command.jobOrder.branchName}','${command.jobOrder.buName}','${command.addJobContracts[addJobContractStatus.index].jobContract.contractCode }','${addJobContractStatus.index}');">
                                    <img src="images/icobranchallocation.gif" alt="Allocate Selected Lines" width="14" height="15" border="0" title="Allocate Selected Lines">
                                </a>
                              </td>
                              <td colspan="1" height="21" nowrap>
	                              <c:if test="${command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus!='6000' && command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus!='7200'}">       
		                              <input type="checkbox"  id="selectAll${addJobContractStatus.index }" onClick="selectAllPrebills('${addJobContractStatus.index }',${fn:length(command.addJobContracts[addJobContractStatus.index].jobContract.prebills)})" />&nbsp;&nbsp;&nbsp;<f:message key="selectAll"/>
		                              <%--<a href="#" onClick="selectAllPrebills('${addJobContractStatus.index }',${fn:length(command.addJobContracts[addJobContractStatus.index].jobContract.prebills)})">Check all</a>&nbsp;&nbsp;
				                      <a href="#" onClick="clearAllPrebills('${addJobContractStatus.index }',${fn:length(command.addJobContracts[addJobContractStatus.index].jobContract.prebills)})">Clear all</a>
				                       --%>
			                      </c:if>
                              </td>
                            </tr>
                            <tr>
							<th width="2" nowrap>&nbsp;</th> 
                              <th width="25" nowrap><f:message key="lineNo"/></th>
                              <%--  <th width="10" nowrap>&nbsp;</th> --%>
                              <th width="15" nowrap><f:message key="id"/></th>
                              <authz:authorize ifAnyGranted="AribaExxon">
                                <th width="10" nowrap><f:message key="quantity"/></th>
                              </authz:authorize>
                              <th style="width:200px;">&nbsp;&nbsp;&nbsp;&nbsp;<f:message key="description"/></th>
                            </tr>
                                <c:forEach items="${addJobContract.jobContract.prebills}" var="prebill" varStatus="prebillStatus">
                                  <tr valign='center'>
                                    <td height="25" align='left' valign="middle" nowrap>
                                      <input type="checkbox" id="chk${addJobContractStatus.index }${ prebillStatus.index }" name="chk${addJobContractStatus.index }${ prebillStatus.index }" value="${prebill.id }" onclick="updateChosenPrebillIds('${addJobContractStatus.index }','${ prebillStatus.index }','${prebill.id}')">
                                    </td>
                                    <td align='left' valign="middle"  nowrap='nowrap'>
                                    ${prebill.lineNumber} &nbsp;&nbsp;&nbsp;&nbsp;
                                          
                                      <a href="#" onClick="setTabIndex(${addJobContractStatus.index });updateBranchAllocIframeSrc('${prebill.id}','${command.jobOrder.branchName}','${command.jobOrder.buName}','${command.addJobContracts[addJobContractStatus.index].jobContract.contractCode }','${addJobContractStatus.index}');popup_show('branchall',
                    'branchall_drag','branchall_exit', 'screen-corner', 10,40);hideIt();showPopupDiv('branchall','branchallbody');popupboxenable();">
                                        <img src="images/icobranchallocationred.gif" alt="Branch Allocation" width="14" height="15" border="0" title="Branch Allocation">
                                      </a>
                                   
                                    </td>
                                    <td align='left' valign="middle" >
                                      <span>${prebill.id}</span>
                                    </td>
                                    <authz:authorize ifAnyGranted="AribaExxon">
								        <td align='left' valign="middle">
							                <form:input cssClass="inputBox"
							                  path="addJobContracts[${addJobContractStatus.index}].jobContract.prebills[${prebillStatus.index}].quantity"
							                  size="10" disabled="true" />
								        </td>
                                    </authz:authorize>           
                                    <!-- START : 53791-->     
								<td align='left' valign="middle" nowrap="nowrap">
                                    <authz:authorize ifAnyGranted="ChangeNegativePositiveLineItems">                      <c:choose>
                                      <c:when test="${command.jobOrder.jobStatus=='1' ||command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='7200'}">
                                      </c:when>
                                      <c:otherwise>
                                      	<c:choose>
	                                      <c:when test="${prebill.unitPrice >= 0}">
										    <input type="hidden" name="togglePostvNegtv" value="POSTV">
	                                    <a href="javascript:togglePostvNegtv('POSTV','${prebillStatus.index}')" style="text-decoration:none; font-size:12px;"><span id='signUnitPrc${prebillStatus.index}' nowrap="nowrap" align='left' valign="middle" >+</span></a>
	                                      </c:when>
	                                      <c:otherwise>
										    <input type="hidden" name="togglePostvNegtv" value="NEGTV">
											<a href="javascript:togglePostvNegtv('NEGTV','${prebillStatus.index}')" style="text-decoration:none; font-size:12px;"><span id='signUnitPrc${prebillStatus.index}' nowrap="nowrap"align='left' valign="middle" >&#8211;</span></a>
	                                      </c:otherwise>
	                                      </c:choose>
	                                   </c:otherwise>   
                                      </c:choose>
									</authz:authorize>                                      
                                     <!-- END : 53791-->


					                  <c:choose>
					                  <c:when test="${command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='7200'}">       
					                  <form:input cssClass="inputBox" path="addJobContracts[${addJobContractStatus.index}].jobContract.prebills[${prebillStatus.index}].lineDescription" size="60" maxlength="3048" disabled="true" htmlEscape="true"/>
					                  </c:when>
					                  <c:otherwise>
					                  <form:input cssClass="inputBox" path="addJobContracts[${addJobContractStatus.index}].jobContract.prebills[${prebillStatus.index}].lineDescription" size="60" maxlength="3048" htmlEscape="true"/>
					                  </c:otherwise>
					                  </c:choose>                                    
                                    </td>
                                  </tr>
                                </c:forEach>
                            <tr valign='center'>
                              <td height="26" align='left' valign="middle" nowrap>&nbsp;</td>
                              <td align='left' valign="middle"  nowrap='nowrap'>&nbsp;</td>
                              <td align='left' valign="middle" >&nbsp;</td>
                              <td align='left' valign="middle" nowrap >&nbsp;</td>
                            </tr>
                          </table>
                        </td>
                        <td style="padding:0px;">
                          <iframe name="myIframe${addJobContractStatus.index}" src="${addJobContract.invoicePreviewTabSrc}?contractCode=${addJobContract.jobContract.contractCode}&contractIndex=${addJobContractStatus.index}" frameborder="0" scrolling="auto" style="padding:0px; border-right: #DBE2F2 1px solid; border-left: #DBE2F2 1px solid;" width="100%" id="invpreview" name="frame1${addJobContractStatus.index}"></iframe>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>    
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
                        <authz:authorize ifAnyGranted="CreateJob">
                          <%--  c:if test="${command.previewFlag}">
			              <script>
			              document.jobOrderEditInvoicePreviewForm.previewFlag.value = 'false';
			              window.open('invoice_preview.htm?jobNumber=${addJobContract.jobContract.jobNumber}&uid20=${addJobContract.jobContract.uid20}&jobContractId=${addJobContract.jobContract.id}','Preview', 'menubar,resizable,dependent,status,width=600,height=600,left=10,top=10');
			              </script>
			              <c:set var="previewFlag" value="false" scope="request" />
			              </c:if --%>
                          <a href="invoice_preview.htm?id=${addJobContract.jobContract.id}&jobNumber=${addJobContract.jobContract.jobNumber}&uid20=${addJobContract.jobContract.uid20}" target="_blank" class="button1" style="text-decoration: none;color:black;"><f:message key="previewInvoice"/></a>&nbsp;
                          <%--<a href="#" class="button1" style="text-decoration: none;color:black;" onclick="javascript:doMySubmit('previewInvoice',${addJobContractStatus.index},'${addJobContract.invoicePreviewTabSrc }');">Preview Invoice</a>&nbsp;--%>
                        </authz:authorize>
                        <c:choose>
                         <%-- <c:when test="${command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='7200'}"> --%>
						  
						  <c:when test="${command.jobOrder.jobStatus=='1' ||command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[addJobContractStatus.index].jobContract.jobContractStatus=='7200'}">
                          &nbsp;
                          </c:when>
                          <c:otherwise>
                            <authz:authorize ifAnyGranted="GenerateInvoice">
                              <a name="generatebutton" href="#" id="ginvoice" class="button1" style="text-decoration: none;color:black;" onclick="javascript:if(busStreamValid(${addJobContractStatus.index })){showPeriodWarning('${command.openPeriodsFlag }','${addJobContractStatus.index }','${addJobContract.invoicePreviewTabSrc }');}"><f:message key="generateInvoice"/></a>&nbsp;
                            </authz:authorize>
                          </c:otherwise>
                        </c:choose>  
                      </td>

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
                         <a href="#" onclick="javascript:setTabIndex(${addJobContractStatus.index });doMySubmit('false','calc','${addJobContractStatus.index}','${addJobContract.invoicePreviewTabSrc }');">
                          <img src="images/icocalculate.gif" alt="Calculate Invoice" width="14" height="14" border="0" align="absmiddle"/></a> 
                          <authz:authorize ifAnyGranted="GenerateInvoice">
                          <a href="#" onclick="javascript:doMySubmit('true','next',${addJobContractStatus.index},'${addJobContract.invoicePreviewTabSrc }');">
                            <img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a>
                          </authz:authorize>
                          <a href="#" onclick="javascript:setTabIndex(${addJobContractStatus.index });doMySubmit('false','false',${addJobContractStatus.index},'${addJobContract.invoicePreviewTabSrc }');">
                          <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" align="absmiddle"/>
                        </a>
                      </td>
                    </tr>
                  </table></td>
                </tr>
        
              </table>
        </c:if>
            </div>
            
            </c:forEach>
            <!-- ------------------------------ TAB 1 CONTAINER END --------------------------------------- -->   

          <!-- ---------------------------------------------------------------------------------------------------------------------- -->
          </div>
        </div>
        <script type="text/javascript">
	      var pageNoVar = "${command.contractIndex}"
	      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
	      dolphintabs.init("tabnav", pageNoVar)      
        </script>
        <!-- -------------------------------- TAB CONTENT END --------------------------------------- -->
        <table width="100%" cellspacing="0">
          <tr>
            <td width="90%" height="24" align="right">
              <div id="navbuttons"></div>            
            </td>
            <td height="24" style="text-align:right; padding-right:0px;">
              <select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
                <option selected>Go to ... &gt;</option>
                <option value="edit_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="entry"/></option>
                <option value="edit_job_operational_info_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="jobInstructions"/></option>
                <option value="edit_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="selectCharges"/></option>
              </select>            
            </td>
          </tr>
        </table>
      </div>

    <!-- BREADCRUMB TRAIL END -->
    <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->    
    </td>
  </tr>
</table>
</form:form>

<!-- --------------------------- Billing Contact Lookup Start ------------------------------------------------- -->

<div class="sample_popup" id="searchbillingcontact" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="searchbillingcontact_drag" style="width:475px; "> 
  <a href="#" onClick="showsel()"><img class="menu_form_exit"   id="searchbillingcontact_exit" src="images/form_exit.png" border="0" /></a> &nbsp;&nbsp;&nbsp;<f:message key="searchBillingContacts"/> </div>
  <div class="menu_form_body" id="billingcontactbody"   style="width:475px; height:180px;">
  <form method="post" action="popup.php">  
      
    
    <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
  <iframe align="left" frameborder="0" style="padding:0px; height:55px;" width="100%" src="inc_billingcontact_lookup.html" scrolling="no" id="sbillingcontactframe" name="sbillingcontactframe" allowtransparency="yes" ></iframe>  </td> 
  </tr>
  <tr><td><input id="Search" type="button" value="Search" name="search2" class="button1" onClick="searchbillingcontact();" />    &nbsp;&nbsp;
      <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hidesearchbillingcontact();popupboxclose();showsel()"/>
     
  </td></tr>
</table>

    </form>
  <br>
  <div id="searchresultsbillingcontact" style="visibility:hidden; display:none"><!--search results -->
  &nbsp;&nbsp;&nbsp;&nbsp;<strong><f:message key="searchResults"/></strong>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    
    
    <tr>
      <th width="40%">Contact ID</th>
      <th>Contact Name</th>
    </tr>
  <tr>
    <td><a href="#">1110</a></td>
    <td>Mark Volz</td>
  </tr>
  <tr>
    <td><a href="#">9497</a></td>
    <td>Accounts Payable</td>
  </tr>
</table>
  </div><!-- serach results -->
  
  
  </div>
</div>
</div>

<!-- --------------------------------- Billing Contact Lookup END ----------------------------------------- -->

<!-- --------------------------- Remit To Search Lookup Start ------------------------------------------------- -->

<div class="sample_popup" id="searchremitto" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="searchremitto_drag" style="width:475px; "> 
  <a href="#" onClick="showsel()"><img class="menu_form_exit"   id="searchremitto_exit" src="images/form_exit.png" border="0" /></a> &nbsp;&nbsp;&nbsp;<f:message key="remitToSearch"/></div>
  <div class="menu_form_body" id="remittobody"   style="width:475px; height:180px;">
  <form method="post" action="popup.php">  
      
    
    <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
  <iframe align="left" frameborder="0" style="padding:0px; height:55px;" width="100%" src="inc_remitto_lookup.html" scrolling="no" id="sremittoframe" name="sremittoframe" allowtransparency="yes" ></iframe> </td> 
  </tr>
  <tr><td><input id="Search" type="button" value="Search" name="search2" class="button1" onClick="searchremitto();"/>    &nbsp;&nbsp;
      <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hidesearchremitto();popupboxclose();showsel()"/>
     
  </td></tr>
</table>

    </form>
  <br>
  <div id="searchresultsremitto" style="visibility:hidden; display:none"><!--search results -->
  &nbsp;&nbsp;&nbsp;&nbsp;<strong><f:message key="searchResults"/></strong>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    
    
    <tr>
      <th width="40%">Bank Code</th>
      <th>Description</th>
    </tr>
  <tr>
    <td>BOA</td>
    <td><a href="#">Bank of America</a></td>
  </tr>
</table>
  </div><!-- serach results -->
  
  
  </div>
</div>
</div>

<!-- --------------------------------- Remit To Search Lookup END ----------------------------------------- -->

<!-- --------------------------- Account Search Lookup Start ------------------------------------------------- -->

<div class="sample_popup" id="searchaccount" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="searchaccount_drag" style="width:475px; "> 
  <a href="#" onClick="showsel()"><img class="menu_form_exit"   id="searchaccount_exit" src="images/form_exit.png" border="0" /></a> &nbsp;&nbsp;&nbsp;<f:message key="accountSearch"/></div>
  <div class="menu_form_body" id="accountbody"   style="width:475px; height:180px;">
  <form method="post" action="popup.php">  
      
    
    <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
  <iframe align="left" frameborder="0" style="padding:0px; height:75px;" width="100%" src="inc_account_lookup.html" scrolling="no" id="saccountframe" name="saccountframe" allowtransparency="yes" ></iframe> </td> 
  </tr>
  <tr><td><input id="Search" type="button" value="Search" name="search2" class="button1" onClick="searchaccount();" />    &nbsp;&nbsp;
      <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hidesearchaccount();popupboxclose();showsel()"/>
     
  </td></tr>
</table>

    </form>
  <br>
  <div id="searchresultsaccount" style="visibility:hidden; display:none"><!--search results -->
  &nbsp;&nbsp;&nbsp;&nbsp;<strong><f:message key="searchResults"/></strong>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    
    
    <tr>
      <th width="25%">Bank Code</th>
      <th>Bank Account</th>
      <th>Description</th>
    </tr>
  <tr>
    <td>BOA</td>
    <td>2001</td>
    <td><a href="#">LkBox BOA USA01</a></td>
  </tr>
</table>
  </div><!-- serach results -->
  
  
  </div>
</div>
</div>

<!-- --------------------------------- Account Search Lookup END ----------------------------------------- -->


<!-- --------------------------- Branch Allocation Lookup Start ------------------------------------------------- -->

<div class="sample_popup" id="branchall" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="branchall_drag" style="width:980px; "> 
<img class="menu_form_exit"   id="branchall_exit" src="images/form_exit.png" border="0" /></a> &nbsp;&nbsp;&nbsp;<f:message key="branchAllocation"/>  </div>
<div class="menu_form_body" id="branchallbody"   style="width:980px; height:275px;overflow-y:hidden">
<iframe id="branchallIframe" align="left" src="blank.html"
style="padding:0px;" width="100%" height="275px;"
scrolling="auto" name="frame1" allowtransparency="yes"></iframe>
</div>
</div>
<script>
adjustSize('1');
</script>

<!-- --------------------------- Branch Allocation Lookup End ------------------------------------------------- -->
