<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="js/calendar.js?random=20060118"></script>
<script type="text/javascript" src="js/job_search.js"></script>
<script type="text/javascript" src="js/ce/ce_services.js"></script>

<form:form name="commonJobSearchForm" method="POST" action="phx_common_job_search.htm">
<div style="color:red;"><form:errors cssClass="error" /></div>
<input type="hidden" name="refreshing" value="false" />
<input type="hidden" name="pageNumber" value="1" />
<input type="hidden" name="jxcel" value="false"/>
<input type="hidden" name="jmail" value="false"/>
<input type="hidden" name="criteriaAction" value=""/>
<form:hidden id="sortBy" path="sortBy"/>
<form:hidden path="sortFlag" />
<table width="97%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
<td valign="top">
<div id="MainContentContainer">
 <!---------------------------------------------------------------------------------------------- TABS CONTENTS -------------------------------------------------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
  <ul>
  <c:if test="${command.searchFlag == 'search'}">
    <li><a href="#" onClick="navdisable();" rel="tab1"><span><f:message key="jobSearch" /></span></a></li>
  </c:if>
  <c:if test="${command.searchFlag == 'result'}">
    <li><a href="#" onClick="navdisable();" rel="tab2"><span><f:message key="jobSearchResults"/></span></a></li> 
  </c:if>
  </ul>
<div align="right">
  <table cellspacing="0" cellpadding="0" border="0">
      <tr>
         <td>
             <select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
                <option selected>Go to ... &gt;</option>
                <option value="phx_common_job_search.htm"><f:message key="jobSearch"/></option>
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
    <!-- ********************************** Begining of Serach Criteras **************************************** -->
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr>
      <th><f:message key="searchCriteria"/>
        <!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --></th>
		<th colspan="4">
			<form:select cssClass="selectionBox" path="jobSearchCriteriaName" onchange="loadJobSearchCriteria()">
				<form:option value="-1" label="Select A Criteria" />
			</form:select>
			&nbsp;&nbsp;&nbsp;<a href="javascript:doSaveCriteria()">Save</a>
			&nbsp;&nbsp;&nbsp;<a href="javascript:doSetAsDefaultCriteria()">Set As Default</a>
			&nbsp;&nbsp;&nbsp;<a href="javascript:doSaveAsCriteria()">Save As...</a>
			&nbsp;&nbsp;&nbsp;<form:input id="jobSearchCriteriaName" cssClass="inputBox" path="jobSearchCriteriaName" />
		</th>
      </tr>
      <tr>
      <td width="15%" class="TDShade"><label for="businessUnitName"><f:message key="businessUnitName" />: </label><span class="redstar">*</span> </td>
      <td width="30%" class="TDShadeBlue">
      <form:select cssClass="selectionBoxBig" id="sel3" path="buName.value" items="${command.buNames}" 
      		itemLabel="name" itemValue="value" onchange="makeBranchblank()" 
      		/>
      		<form:errors path="buName.value" cssClass="error"/>
      </td>
      <td width="15%" class="TDShade"><strong><f:message
          key="operatingUnit" />:</strong></td>
      <td width="35%" colspan="2" class="TDShadeBlue">
        <form:input id="brnch" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;"
          cssClass="inputBox" path="operatingUnit.value" />
        <form:errors path="operatingUnit.value"
          cssClass="redstar" />
		<a href="#ws" onClick="javascript:operatingUnitSearch();"><img src=" images/lookup.gif" alt="Operating Unit" width="13"
		   height="13" border="0" /></a>
		</td>
      </tr>
      <tr>
      <td width="15%" class="TDShade"><f:message key="status"/>:</td>
      <td width="30%" class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel20" path="statusCri.value" items="${command.jobStatus}" 
        itemLabel="name" itemValue="value" />
        <form:errors path="statusCri.value" cssClass="error"/>
         
      </td>
      <td class="TDShade"><f:message key="jobType" />: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel4" path="jobTypeCri.value" items="${command.jobTypes}" 
        itemLabel="name" itemValue="value" />
        <form:errors path="jobTypeCri.value" cssClass="error"/>
      </td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="fromJobId"/>: </td>
      <td class="TDShadeBlue"><form:input id="fromjobid"cssClass="inputBox" path="frmJobId.value" 
      />
	      <form:errors path="frmJobId.value" cssClass="error"/>
	      &nbsp;<a href="#" onClick="javascript:showJobSearch('fromjobid');popJob();"><img
				src="images/lookup.gif" alt="Lookup Job" width="13" height="13" border="0" /></a>
      </td>
      <td class="TDShade"><f:message key="toJobId"/>: </td>
      <td class="TDShadeBlue" colspan="2">
	      <form:input id="tojobid" cssClass="inputBox"  path="toJobId.value"/>
	       <form:errors path="toJobId.value" cssClass="error"/>&nbsp;
	       <a href="#" onClick="javascript:showJobSearch('toJobId');popJob();">
	       <img src="images/lookup.gif" alt="Lookup Job" width="13" height="13" border="0" /></a>
       </td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="fromJobFinishDate"/>: </td>
      <td class="TDShadeBlue">
	      <form:input id="fdate" cssClass="inputBox" path="frmJobFinishDt.value" />
	      <form:errors path="frmJobFinishDt.value" cssClass="error"/>
	      <a href="#" onClick="displayCalendar(document.forms[0].fdate,'${command.dateFormat}',this)">
	      <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
	  </td>
      <td class="TDShade"><f:message key="toJobFinishDate"/>: </td>
      <td class="TDShadeBlue" colspan="2">
	      <form:input id="tdate" cssClass="inputBox" path="toJobFinishDt.value" />
	      <form:errors path="toJobFinishDt.value" cssClass="error"/>
	      <a href="#" onClick="displayCalendar(document.forms[0].tdate,'${command.dateFormat}',this)">
	      <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
      </td>
      </tr>
	  <tr>
      <td class="TDShade"><f:message key="jobOrderDtFr"/>: </td>
      <td class="TDShadeBlue">
	      <form:input id="jfdate" cssClass="inputBox" path="jobOrderDtFrm.value" />
	      <form:errors path="jobOrderDtFrm.value" cssClass="error"/>
	      <a href="#" onClick="displayCalendar(document.forms[0].jfdate,'${command.dateFormat}',this)">
	      <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
	  </td>
      <td class="TDShade"><f:message key="jobOrderDtTo"/>: </td>
      <td class="TDShadeBlue" colspan="2">
	      <form:input id="jtdate" cssClass="inputBox" path="jobOrderDtTo.value" />
	      <form:errors path="jobOrderDtTo.value" cssClass="error"/>
	      <a href="#" onClick="displayCalendar(document.forms[0].jtdate,'${command.dateFormat}',this)">
	      <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
      </td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="product"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel6" path="jobProduct.op" items="${command.operaters}" itemLabel="description" itemValue="value" />
        <form:input cssClass="inputBox" path="jobProduct.value" />
        <form:errors path="jobProduct.value" cssClass="error"/>
      </td>
      <td class="TDShade"><f:message key="custRefNum"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" path="custRefNumCri.value" />
      </td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="invoice"/>: </td>
      <td class="TDShadeBlue"><form:input cssClass="inputBox" path="jobInvoice.value" /></td>
	  <td class="TDShade"><f:message key="invoiceStatus"/>: </td>
      <td class="TDShadeBlue">  
      <form:select cssClass="selectionBox" id="sel3" path="invStatus.value" items="${command.invoiceStatus}" itemLabel="name" itemValue="value" />
      <form:errors path="invStatus.value" cssClass="error"/></td>
	  </tr>
	  <tr>
      <td class="TDShade"><f:message key="contractDescription"/>: </td>
      <td class="TDShadeBlue">
      <form:input cssClass="inputBox"cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="jobContractDsc.value" size="35" /></td>
	  <td class="TDShade"><f:message key="contractId"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel11" path="contractId.op" items="${command.operaters}" itemLabel="description" itemValue="value" />
        <form:input cssClass="inputBox" path="contractId.value" />
        <form:errors path="contractId.value" cssClass="error"/>
      </td>
      </tr>
	  <tr>
      <td class="TDShade"><f:message key="createdBy"/>: </td>
      <td class="TDShadeBlue">
      <form:input cssClass="inputBox" id="createdBy" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="jobCrtedBy.value" />
	  <a href="#a" onClick="javascript:userSearch('createdBy');popUserDetails();">
	  <img src=" images/lookup.gif" alt="Lookup User" width="13" height="13" border="0" /></a>
	   </td>
	  <td class="TDShade"><f:message key="modifiedBy"/>: </td>
      <td class="TDShadeBlue"><form:input id="modifiedBy" cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="modifiedByCri.value" />
	  <a href="#a" onClick="javascript:userSearch('modifiedBy');popUserDetails();">
	  <img src=" images/lookup.gif" alt="Lookup User" width="13" height="13" border="0" /></a>
	  </td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="svcLocation"/>: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel8" path="jobSvcLoctn.op" items="${command.operaters}" itemLabel="description" itemValue="value" />
        <form:input cssClass="inputBox" path="jobSvcLoctn.value" />
        <form:errors path="jobSvcLoctn.value" cssClass="error"/></td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="companyId"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel9" path="jobCompanyId.op" items="${command.operaters}" itemLabel="description" itemValue="value" />
        <form:input cssClass="inputBox" path="jobCompanyId.value" />
        <form:errors path="jobCompanyId.value" cssClass="error"/>
      </td>
      <td class="TDShade"><f:message key="company"/>: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel10" path="company.op" items="${command.operaters}" itemLabel="description" itemValue="value" />
        <form:input cssClass="inputBox" path="company.value" />
        <form:errors path="company.value" cssClass="error"/>
      </td>
      </tr>
      <tr>
  	  <td class="TDShade"><f:message key="contactID"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel12" path="jobContactId.op" items="${command.operaters}" itemLabel="description" itemValue="value" />
        <form:input cssClass="inputBox" path="jobContactId.value" />
        <form:errors path="jobContactId.value" cssClass="error"/>
      </td>
	  <td class="TDShade"><f:message key="billingContact"/>: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel15" path="billContact.op" items="${command.operaters}" itemLabel="description" itemValue="value" />
        <form:input cssClass="inputBox" path="billContact.value" />
        <form:errors path="billContact.value" cssClass="error"/>
      </td>
      </tr>
      <tr>
      <td class="TDShade"><f:message key="schedulerId"/>: </td>
      <td class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel13" path="jobCstmrCoordinator.op" items="${command.operaters}" itemLabel="description" itemValue="value" />
        <form:input cssClass="inputBox" path="jobCstmrCoordinator.value" />
        <form:errors path="jobCstmrCoordinator.value" cssClass="error"/>
      </td>
      <td class="TDShade"><f:message key="scheduler"/>: </td>
      <td class="TDShadeBlue" colspan="2">
        <form:select cssClass="selectionBox" id="sel14" path="schedulerCri.op" items="${command.operaters}" itemLabel="description" itemValue="value" />
        <form:input cssClass="inputBox" path="schedulerCri.value"  />
        <form:errors path="schedulerCri.value" cssClass="error"/>
      </td>
      </tr>
    </table>
    
    <!-- ********************************** End of Serach Criteras **************************************** -->
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
      <tr>
      <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
        <td>
			<input name="Search" type="submit" class="button1" value="Search/Refresh" />
		</td>

        <td style="text-align:right">
        <!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --></td>
        </tr>
      </table></td>
      </tr>
    </table>
    <br>
    </div>

<ajax:autocomplete baseUrl="phx_ajax.htm" 
				source="operatingUnit.value"
				target="operatingUnit.value" 
				className="autocomplete"
				parameters="entity=com.intertek.entity.Branch,textAttribute=name,valueAttribute=name,~buName={buName.value},~name={operatingUnit.value}"
				minimumCharacters="3" />
				
<ajax:autocomplete baseUrl="phx_ajax.htm"
				source="jobCrtedBy.value" 
				target="jobCrtedBy.value"
				className="autocomplete"
				parameters="entity=com.intertek.entity.User,textAttribute=loginName,valueAttribute=loginName,~loginName={jobCrtedBy.value}"
				minimumCharacters="1" /> 

<ajax:autocomplete baseUrl="phx_ajax.htm"
				source="modifiedByCri.value" target="modifiedByCri.value"
				className="autocomplete"
				parameters="entity=com.intertek.entity.User,textAttribute=loginName,valueAttribute=loginName,~loginName={modifiedByCri.value}"
				minimumCharacters="1" />
				
<ajax:autocomplete baseUrl="phx_ajax.htm"
				source="modifiedByCri.value" 
				target="modifiedByCri.value"
				className="autocomplete"
				parameters="entity=com.intertek.entity.User,textAttribute=loginName,valueAttribute=loginName,~loginName={modifiedByCri.value}"
				minimumCharacters="1" />
				
<!-------------------------------------------------------------------------------------------TAB 1 CONTAINER END ---------------------------------------------------------------------------------------->   
<!------------------------------------------------------------------------------------------ TAB 2 CONTAINER --------------------------------------------------------------------------------------------->
<div id="tab2" class="innercontent1">
<table width="100%" cellpadding=0 cellspacing=0 class="mainApplTable">
<tbody>
<tr bgcolor=#ffffff>
<th width="20%" colspan="2" ><f:message key="jobSearchResults"/></th>

<th width="15%" style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"></a></th>
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
<table width="98%" cellpadding="0" cellspacing="0" class="InnerApplTable" align="left" border="0">
<tr><th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.jobIdField}');" >
<f:message key="jobId"/></a>
</th>
</tr> 
<c:forEach items="${command.results}" var="job" varStatus="status"> 
<tr><td align='CENTER' nowrap='nowrap' height="25" valign="middle">
<a href="#" onClick="jobLink('${job.jobNumber}','${job.jobType}');" title="Transfer to Nomination" >${job.jobNumber}</a>
</td>
</tr>
</c:forEach> 
</table> 
</div>

<div style="float:left;width:89%;" style="visibility: visible;overflow-x:scroll;height:auto;overflow-y:hidden;#DBE2F2 1px solid;">

<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" frameborder="0" border="0" style="border-left-width:0px;#DBE2F2 1px solid;">
<tr><!--  TO DO Sorting part-->
  <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.buNameField}');" ><f:message key="businessUnit"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.operatingUnitField}');" ><f:message key="operation"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.jobTypeField}');" ><f:message key="jobType"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.svcLocationNameField}');" ><f:message key="serviceLocation"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('');" ><f:message key="product"/></a></th><!--  'job.productNames'-->
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.companyIdField}');" ><f:message key="companyId"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.companyNameField}');" ><f:message key="companyName"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('jobContract.contact.firstName');" ><f:message key="scheduler"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.custRefNumField}');" ><f:message key="custRefNum"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('');" ><f:message key="icbReference"/></a></th><!--  'job.productNames'-->
  <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.promiseCompletionDateField}');" ><f:message key="jobFinishDate"/></a></th>
   <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.billingStatusField}');" ><f:message key="status"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.invoiceNameField}');" ><f:message key="invoice"/></a></a></th>
	<th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.contractDescField}');" ><f:message key="contractDescription"/></a></th>
	<th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.contractStatusField}');" ><f:message key="contractStatus"/></a></th>
	<th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.createdByField}');" ><f:message key="createdBy"/></a></th>
	<th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.modifiedByField}');" ><f:message key="modifiedBy"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobSearchHeader('${command.searchInfo.modifiedDateField}');" ><f:message key="modifiedDate"/></a></th>
  </tr>
  <c:forEach items="${command.results}" var="job" varStatus="status">
  <tr valign='center'>
    <td align='left' valign="middle" nowrap> <span>${job.buUnit}</span></td>
    <td align='left' valign="middle" nowrap ><span >${job.operation} </span></td>
    <td align='left' valign="middle" nowrap ><span >${job.jobType}</span></td>
    <td align='left' valign="middle" nowrap ><span>${job.serviceLctn}</span></td>
    <td align='left' valign="middle" nowrap>  <span >${job.product}</span></td>
    <td align='left' valign="middle" nowrap> <span >${job.companyId}</span></td>
    <td align='left' valign="middle" nowrap > <span >${job.companyName}</span></td>
    <td align='left' valign="middle" nowrap > <span >${job.scheduler}</span></td>
    <td align='left' valign="middle" nowrap > <span >${job.custRefNum}</span></td>
    <td align='left' valign="middle" nowrap><span>${job.icbRefNo}</span></td> 
    <td height="25" align='left' valign="middle" nowrap><span>${job.jobFinishDt}</span></td>
    <td align='left' nowrap > ${job.status}</td>
    <td align='left' nowrap > ${job.invoice}</td>
    <td align='left' nowrap > ${job.contractDsc}</td>
	<td align='left' nowrap > ${job.contractSts}</td>
	<td align='left' nowrap > ${job.createdBy}</td>
	<td align='left' nowrap > ${job.modifiedBy}</td>
	<td align='left' nowrap > <span>${job.modifiedDt}</span></td>
    </tr>
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
<td><table cellspacing="0" cellpadding="0" border="0">
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
</table></td>

<td style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"></a></td>

</tr>
</table></td>
</tr>
</table>
</div>
<!-- ------------------------------------------------------------------------------- TAB 2 CONTAINER END ------------------------------------------------------------------------------------------------>

</div>
  </div>
  <script type="text/javascript">
  dolphintabs.init("tabnav", 0)
  </script>
<!-------------------------------------------------------------------------------------------- TAB CONTENT END ------------------------------------------------------------------------------------------->
  <table width="100%" cellspacing="0">
  <tr>
  <td width="90%" height="24" align="right">
  <div id="navbuttons"></div>
  </td>
  <td height="24" style="text-align:right; padding-right:0px;"><select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
  <option selected>Go to ... &gt;</option>
  <option value="phx_common_job_search.htm"><f:message key="jobSearch"/></option>
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


<!-----------------------------------------Branch Code Lookup----------------------------------------------------->
<div class="sample_popup" id="jobbranchcode"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="jobbranchcode_drag"
	style="width: 750px;"><a href="#" onclick="resetBranchTypeFlag()">
<img class="menu_form_exit" id="jobbranchcode_exit"
	src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp; <f:message
	key="searchBranchCode" />
</div>
<div class="menu_form_body" id="jobbranchcodebody"
	style="width: 750px; height: 530px; padding-left: 4px; overflow-y: hidden;">
<iframe align="left" id="jobbu" frameborder="0" style="padding: 10px;"
	height="530px;" width="100%" scrolling="auto" allowtransparency="yes"
	src="blank.html"> </iframe>
</div>
</div>
<!-----------------------------------------Branch Code Lookup END------------------------------------------------->


<!------------------------------------------------------search	Job Id--------------------------------------------------------->
<div class="sample_popup" id="parentJob">
<div class="menu_form_header" id="parent_drag"	style="width: 750px;; height: auto;">
<a href="#"><img class="menu_form_exit" id="parent_exit" src="images/form_exit.png" /></a>&nbsp;&nbsp;&nbsp;<f:message
	key="searchJobId" /></div>
<div class="menu_form_body" id="parentbody" style="width: 750px; height: 630px; overflow-y: hidden; padding-left: 15px;">
<iframe align="left" frameborder="0" style="padding: 0px;"
	height="630px;" width="100%" src="blank.html" id="searchParentFr"
	name="searchParentFr" allowtransparency="yes"></iframe></div>
</div>
<!-------------------------------------------------------search JobId ------------------------------------------------------->

<!---------------------------------------Userdetails By Lookup START------------------------------------------------->
<div class="sample_popup" id="userDetails"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="userDetails_drag"
	style="width: 750px;"><img class="menu_form_exit"
	id="userDetails_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;
<f:message key="searchUser" /></div>
<div class="menu_form_body" id="userDetailsbody"
	style="width: 750px; height: 630px; padding-left: 4px; overflow-y: hidden;">
<iframe id="recievedFr" align="left" frameborder="0"
	style="padding: 0px;" height="630px" width="100%" scrolling="auto"
	allowtransparency="yes" src="blank.html"> </iframe></div>
</div>
<!------------------------------------Userdetails By Lookup END----------------------------------------------------->
