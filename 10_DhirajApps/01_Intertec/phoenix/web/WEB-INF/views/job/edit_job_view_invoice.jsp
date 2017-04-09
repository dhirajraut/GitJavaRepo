<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<c:if test="${!empty aribaResult}">
	<script type="text/javascript">
			confirm("${aribaResult}");
	</script>
</c:if>	

<script type="text/javascript">
function popCreditReason(index, invoiceId, viewFlag)
{  
   popup_show('creditreason_' + index, 'creditreason_drag_' + index, 'creditreason_exit_' + index, 'screen-corner', 120, 20);
  hideIt();
  popupboxenable();

  document.getElementById("creditreasonbox_" + index).src="credit_reason_popup.htm?invoiceId=" + invoiceId +"&viewFlag=" +viewFlag+"&contractIndex="+index;              
  document.getElementById("creditreasonbox_" + index).height = "250px";
}

function onSave(myIndex)
{
document.jobOrderEditViewInvoiceForm.contractIndex.value=myIndex;
document.jobOrderEditViewInvoiceForm.submit();
}


function sendToAriba(jobContractId)
{
document.jobOrderEditViewInvoiceForm.sendToAribaFlag.value=jobContractId;
document.jobOrderEditViewInvoiceForm.submit();
}

function convertConToReg(jobContractId, jobContractInvoiceId,myIndex){
  if(confirm("Once converted to a regular invoice, this invoice will be processed by the Financials system. It will no longer be available to be consolidated. If this invoice has already been attached to a consolidated header, it will now be detached.")==true){
    document.jobOrderEditViewInvoiceForm.jobContractId.value=jobContractId;
    document.jobOrderEditViewInvoiceForm.conToRegFlag.value=jobContractInvoiceId;
    document.jobOrderEditViewInvoiceForm.contractIndex.value=myIndex;
    document.jobOrderEditViewInvoiceForm.submit();
  }
}

function setCountable(invoiceFileId, countable){
    document.jobOrderEditViewInvoiceForm.invoiceFileId.value=invoiceFileId;
    if(countable == null || countable == false)
    	document.jobOrderEditViewInvoiceForm.invoiceFileCountable.value = "N";
    if(countable == true)	
    	document.jobOrderEditViewInvoiceForm.invoiceFileCountable.value = "Y";
	document.jobOrderEditViewInvoiceForm.submit();
}

function warnUser(navigationUrl,myIndex)
{
  if(document.jobOrderEditViewInvoiceForm.warnUserFlag.value == 'warn' || document.jobOrderEditViewInvoiceForm.warnUserFlag.value == 'warned')
    document.jobOrderEditViewInvoiceForm.warnUserFlag.value = 'navigate';
  else
    document.jobOrderEditViewInvoiceForm.warnUserFlag.value = "warn";
    
  document.jobOrderEditViewInvoiceForm.navigationUrl.value = navigationUrl;
  document.jobOrderEditViewInvoiceForm.contractIndex.value=myIndex;
  document.jobOrderEditViewInvoiceForm.submit();
}

function regenerateInvoice(jobContractInvoiceId,myIndex)
  {
    document.jobOrderEditViewInvoiceForm.regeneratePdfInvoice.value=jobContractInvoiceId;
    document.jobOrderEditViewInvoiceForm.contractIndex.value=myIndex;
    document.jobOrderEditViewInvoiceForm.submit();
  } 

  // START : #119240
function nextList()
{
document.jobOrderEditViewInvoiceForm.showNextListFlag.value="next";
document.jobOrderEditViewInvoiceForm.submit();
}

function prevList()
{
document.jobOrderEditViewInvoiceForm.showPrevListFlag.value="prev";
document.jobOrderEditViewInvoiceForm.submit();
}

function noPrevList()
{
	document.jobOrderEditViewInvoiceForm.noPrevJob.value="true";
	document.jobOrderEditViewInvoiceForm.submit();
}

function noNextList()
{
	document.jobOrderEditViewInvoiceForm.noNextJob.value="true";
	document.jobOrderEditViewInvoiceForm.submit();
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


<form:form name="jobOrderEditViewInvoiceForm" method="POST" action="edit_job_view_invoice.htm">
 <div style="color:red;"><form:errors cssClass="error" /></div>
<form:hidden path="navigationUrl" />
<form:hidden path="warnUserFlag" />
<form:hidden path="regeneratePdfInvoice" />
<form:hidden path="sendToAribaFlag" />
<form:hidden path="contractIndex" />
<input type="hidden" name="invoiceFileId" />
<input type="hidden" name="invoiceFileCountable" />
<input type="hidden" name="conToRegFlag" />
<input type="hidden" name="jobContractId" />
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
      <!-- BREADCRUMB TRAIL  -->
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
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 4}">               
                     <a href="edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                    <!--<a href="#" onClick="warnUser('edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                      <f:message key="preview"/>
                    </a>
                    </c:when>
                    <c:otherwise>
                    <f:message key="preview"/>
                    </c:otherwise>
                    </c:choose>
                    </td>               
 
                  <td class="breadcrumbtrailActive"> 
                    <f:message key="invoice"/>
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
              <c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="jobContractStatus">
                <li>
                  <a href="#" onClick="navdisable();" rel="tab${jobContractStatus.index}">
                    <span>${jobContractStatus.index + 1}. ${addJobContract.jobContract.contractCode}</span>
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
                    <option value="edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="invoicePreview"/></option>
                    </select>
                  </td>
                </tr>
              </table>
            </div>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner1">

            <!-- ---------------------------------------------------------------------------------------------------------------------- -->
            <!-- ------------------------- TAB 1 CONTAINER ------------------------------- -->
            <c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="jobContractStatus">
            <div id="tab${jobContractStatus.index}" class="innercontent1" >

              <!-- Invoice Preview Table Start -->
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
                  <th><f:message key="invoiceSummary"/><img src=" images/separator2.gif" width="2" height="27"
          align="absmiddle" style="margin-left:5px;margin-right:5px;" /><f:message key="jobId"/>: ${command.jobOrder.jobNumber }</th>   
          <th>
          <c:choose>
          <c:when test="${jobContractStatus.index==0}">
            <f:message key="status" />: <form:select id="sel1" cssClass="selectionBox" path="jobOrder.jobStatus" items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name" itemValue="value" />           
          </c:when>
          <c:otherwise>
            <f:message key="status" />: 
            <form:select id="sel1" cssClass="selectionBox" path="jobOrder.jobStatus" items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name" itemValue="value" disabled="true"/> 
          </c:otherwise>
          </c:choose>
          <form:errors path="jobOrder.jobStatus"
              cssClass="redstar" /> 
              </th>
              <th style="text-align:right">  
                  <authz:authorize ifAnyGranted="AribaExxon">
                  	<!-- START: Patni offshore changes to add check for customer 109 -->
	                  <c:if test="${addJobContract.jobContract.custCode=='114' || addJobContract.jobContract.custCode=='124' || addJobContract.jobContract.custCode=='109'}">
					<!-- END: Patni offshore changes to add check for customer 109 -->	                  
	                      <a href="#" onclick="javascript:sendToAriba('${addJobContract.jobContract.id}');">
	                        <img id="aribaImage" src="images/ariba.bmp" alt="Send To Ariba" title="Send To Ariba" width="16" height="16" border="1"/>
	                      </a>
	                  </c:if>
                  </authz:authorize>
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
                  <a href="#">
                      <img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" onClick="onSave('${jobContractStatus.index}')"/>
                  </a> 
              </th>
                </tr>
                <tr>
                  <td width="35%" class="TDShade"><f:message key="contract"/>: </td>
                  <td colspan="2" class="TDShadeBlue">${addJobContract.contractDesc} </td>
                  
                </tr>
                <tr>
                  <td width="35%" class="TDShade"><f:message key="company"/>:</td>
                  <td colspan="2"  class="TDShadeBlue">${addJobContract.companyName} </td>
                </tr>
                <tr>
                  <td width="35%" class="TDShade"><f:message key="billingContact"/>: </td>
                  <td colspan="2" class="TDShadeBlue">${addJobContract.jobContract.billingContactName}</td>
                </tr>
  
                <tr>
                  <td colspan="3" class="TDShade" style="padding:2px;">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
                      <tr>
                        <th width="2%">&nbsp;</th>
                        <th><f:message key="invoice"/></th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <th><f:message key="status"/></th>
                        <th><f:message key="generatedOn"/> </th>
                        <th width="15%"><f:message key="generatedBy"/></th>
                        <th>&nbsp;</th>
                        <th><f:message key="consolidatedInv"/></th>
                      </tr>
                      <c:forEach items="${addJobContract.jobContractInvoiceList}" var="jobContractInvoice" varStatus="jobContractInvoiceStatus">
                        <tr>
                          <td class="TDShadeBlue">${jobContractInvoiceStatus.index + 1}</td>
                          <td class="TDShadeBlue">${jobContractInvoice.invoice}</td>
                          <td align="center" class="TDShadeBlue">
                            <c:if test="${jobContractInvoice.invoiceFileName !=null && fn:length(fn:trim(jobContractInvoice.invoiceFileName)) > 0 }">
                              <a href="invoice_view.htm?invoice=${jobContractInvoice.invoiceFileName}" target="_blank">
                                  <img src="images/icoviewinvoice.gif" alt="View Invoice" width="16" height="18" border="0">
                              </a>
                            </c:if>
                            <c:forEach items="${jobContractInvoice.invoiceFiles}" var="invoiceFile" >
	                            <a href="invoice_view.htm?invoice=${invoiceFile.invoiceFileName}" target="_blank">
	                                  <img src="images/icoviewinvoice.gif" alt="View ${invoiceFile.invoiceFileType.invoiceFileType} Invoice" width="16" height="18" border="0">
	                            </a>
	                            <input type="checkbox" name="countable"    onchange="javascript:setCountable('${invoiceFile.id}' , this.checked);" <c:if test="${invoiceFile.countable}">checked</c:if> alt="countable" title="countable"/>
                            </c:forEach>
                            <c:if test="${((jobContractInvoice.billType eq 'CON') && (jobContractInvoice.billStatus != 'INV') && (jobContractInvoice.sentToFinFlag == null || jobContractInvoice.sentToFinFlag != 'NEW'))}">
                              <a href="#" onClick="convertConToReg('${addJobContract.jobContract.id}', '${jobContractInvoice.id}','${jobContractStatus.index }');">
                                <img src="images/copy.gif" alt="Convert Consolidated to Regular" width="13" height="15" hspace="1px" border="0">
                              </a>
                            </c:if>
                          </td>
                           <td>
							<c:if test="${( empty jobContractInvoice.pdfGeneratedFlag || not jobContractInvoice.pdfGeneratedFlag) && jobContractInvoice.invoiceType!='CON'}">
                              <a href="#" class="smallbutton" style="text-decoration: none;color:black;" onclick="javascript:regenerateInvoice('${jobContractInvoice.id}','${jobContractStatus.index}');"><f:message key="regenerate"/></a>
                            </c:if>
                          </td>
                          <td class="TDShadeBlue">
                          <c:choose>
                          <c:when test="${jobContractInvoice.invoiceType=='CON' || (jobContractInvoice.invoiceType!='CON' && jobContractInvoice.invoiceFileName !=null && fn:length(fn:trim(jobContractInvoice.invoiceFileName)) > 0  )}">
                              <f:message key="success"/>
                          </c:when>
                          <c:otherwise>
                              <f:message key="failed"/>
                          </c:otherwise>
                          </c:choose>
                          </td>
                          <td class="TDShadeBlue">${jobContractInvoice.generatedDateTime} </td>
                          <td class="TDShadeBlue">${jobContractInvoice.creationUserName}</td>
                          <td class="TDShadeBlue">
                          <c:if test="${jobContractInvoice.creditReasonNote != null && jobContractInvoice.creditReasonNote != ''}">           
                            <a href="#">
                              <img src="images/icoviewcreditreasonnote.gif" alt="View Credit Reason Note" border="0" onClick="popCreditReason('${jobContractStatus.index }', '${jobContractInvoice.id}','readonly')">
                            </a>
                            </c:if>
                          </td>
                          <td class="TDShadeBlue">
                          <c:if test="${jobContractInvoice.consolInvoiceNo != null && jobContractInvoice.consolInvoiceNo != '' && jobContractInvoice.billStatus == 'INV'}">           
                          <a href="edit_consl_inv.htm?conslInvNumber=${jobContractInvoice.consolInvoiceNo}&buName=${jobContractInvoice.consolBuName}">
                             ${jobContractInvoice.consolInvoiceNo} 
                            </a>
                          </c:if>
                          </td>
                        </tr>
                      </c:forEach>
                    </table>
                  </td>
                </tr>
              </table>

              <!-- Invoice Preview Table End -->        
        
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td><input name="Refresh" type="button" class="button1" id="Refresh" value="Refresh" />
                      &nbsp;&nbsp;
                      <!-- <input name="Return" type="button" class="button1" id="Return" value="Return" /> -->
                       <a href="edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}" class="button1" style="text-decoration: none;color:black;">&nbsp;&nbsp;&nbsp;&nbsp;Return&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;
                        <!-- <input name="Notify" type="button" class="button1" value="Notify" /> --></td>
 
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
					  <a href="#">
                      <img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" onClick="onSave('${jobContractStatus.index}')"/>
                      </a>
                      </td>
                    </tr>
                  </table></td>
                </tr>
              </table>
            </div>
            </c:forEach>
            <!-- ------------------------------ TAB 1 CONTAINER END --------------------------------------- -->   
          </div>
        </div>

        <script type="text/javascript">
        
          //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
          dolphintabs.init("tabnav", '${command.contractIndex}')
        
        </script>
        <!-- -------------------------------- TAB CONTENT END --------------------------------------- -->
        <table width="100%" cellspacing="0">
          <tr>
            <td width="90%" height="24" align="right">
              <div id="navbuttons"></div>
            </td>
            <td height="24" style="text-align:right; padding-right:0px;">
              <select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
                <option>Go to ... &gt;</option>
                <option value="edit_job_entry_${urlSuffix}.htm?jobNumber=${command.jobNumber}"><f:message key="entry"/></option>
                <option value="edit_job_operational_info_insp.htm?jobNumber=${command.jobNumber}"><f:message key="jobInstructions"/></option>
                <option value="edit_job_select_charges.htm?jobNumber=${command.jobNumber}"><f:message key="selectCharges"/></option>
                <option value="edit_job_invoice_preview.htm?jobNumber=${command.jobNumber}"><f:message key="invoicePreview"/></option>
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
  <A HREF="#" onClick="showsel()"><img class="menu_form_exit"   id="searchbillingcontact_exit" src="images/form_exit.png" border="0" /></A> &nbsp;&nbsp;&nbsp;<f:message key="searchBillingContacts"/> </div>
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
  &nbsp;&nbsp;&nbsp;&nbsp;<strong><f:message key="searchResults"/> </strong>
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
  <img class="menu_form_exit"   id="searchremitto_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="remitToSearch"/></div>
  <div class="menu_form_body" id="remittobody"   style="width:475px; height:180px;">
  <form method="post" action="popup.php">  
      
    
    <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
  <iframe align="left" frameborder="0" style="padding:0px; height:55px;" width="100%" src="inc_remitto_lookup.html" scrolling="no" id="sremittoframe" name="sremittoframe" allowtransparency="yes" ></iframe> </td> 
  </tr>
  <tr><td><input id="Search" type="button" value="Search" name="search2" class="button1" onClick="searchremitto();" />    &nbsp;&nbsp;
      <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hidesearchremitto();popupboxclose();"/>
     
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

<!-- ----------------------------------- Credit Reason Lookup ------------------------------------------------- -->
<c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
<div class="sample_popup" id="creditreason_${addJobContractCounter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="creditreason_drag_${addJobContractCounter.index}" style="width:800px; "> 
  <img class="menu_form_exit"   id="creditreason_exit_${addJobContractCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="creditReason"/> </div>
  <div class="menu_form_body" id="creditreason_${addJobContractCounter.index}"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="creditreasonbox_${addJobContractCounter.index}" width="100%" height="200px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
</c:forEach>
<!-- --------------------------------- Credit Reason Lookup END ----------------------------------------- -->

<!-- --------------------------- Account Search Lookup Start ------------------------------------------------- -->

<div class="sample_popup" id="searchaccount" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="searchaccount_drag" style="width:475px; "> 
  <img class="menu_form_exit"   id="searchaccount_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="accountSearch"/></div>
  <div class="menu_form_body" id="accountbody"   style="width:475px; height:180px;">
  <form method="post" action="popup.php">  
      
    
    <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
  <iframe align="left" frameborder="0" style="padding:0px; height:55px;" width="100%" src="inc_account_lookup.html" scrolling="no" id="saccountframe" name="saccountframe" allowtransparency="yes" ></iframe> </td> 
  </tr>
  <tr><td><input id="Search" type="button" value="Search" name="search2" class="button1" onClick="searchaccount();" />    &nbsp;&nbsp;
      <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hidesearchaccount();popupboxclose();"/>
     
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
