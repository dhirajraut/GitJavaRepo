<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<c:if test="${requestScope['noJobMessage'] != null}">  
  <div style="color:green;">
    ${requestScope.noJobMessage}
  </div>
</c:if>

<table width="97%" height="80%" border="0" cellpadding="0" cellspacing="0">
  <c:set var="urlSuffix" value="${icbfn:urlSuffixByJobType(command.jobOrder.jobType)}" scope="request" />                
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
                  <td class="breadcrumbtrailActive"> 
                     <f:message key="selectCharges"/> 
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

      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <c:forEach items="${command.jobOrder.jobContracts}" var="jobContract" varStatus="jobContractCounter">
                <li>
                  <a href="#" rel="tab${jobContractCounter.index}">
                    <span>${jobContract.contractCode}</span>
                  </a>
                </li>
              </c:forEach>
            </ul>
            
            <select name="sel1" id="sel1" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
              <option selected>Go to ... &gt;</option>
              <option value="view_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="entry"/></option>
              <option value="view_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="jobInstructions"/> </option>
            </select>
            
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">      
            <!-- ------------------------- TAB 2 CONTAINER ----------------------------------------- -->
            <c:forEach items="${command.jobOrder.jobContracts}" var="jobContract" varStatus="jobContractCounter">
            <div id="tab${jobContractCounter.index}" class="innercontent" >
              <table class="mainApplTable" cellspacing="0" cellpadding="0" style="width:100%">
                <tbody>
                  <tr>
                    <th width="55%">
                     <f:message key="selectCharges"/>  
                      <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                      Job ID: ${command.jobOrder.jobNumber}
                      <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
                      Job Status: ${icbfn:dropdownLabel('jobStatus', null, command.jobOrder.jobStatus)}
                    </th>
                    <th nowrap="yes" width="9%">&nbsp;
                    </th>
                    <th style="text-align:right" width="16%">
                      &nbsp;
                    </th>
                    <th style="text-align:right">

	  <!-- START : #119240 -->
		  <c:choose>
            <c:when test="${command.addJobOrder.originateFromSearchJob==null}">
				&nbsp;
            </c:when>
            <c:otherwise>
              <a href="${command.addJobOrder.originateFromSearchJob}?jobNum=${command.jobOrder.jobNumber}">
			   <img src="images/icofindjob.gif" alt="Return to Search" width="16" height="14" border="0" align="absmiddle">			  
			  </a>
			</c:otherwise>
           </c:choose>    
		  <c:choose>
			<c:when test="${command.addJobOrder.prevListFlag=='true'}">
              <a href="view_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}&showPrevListFlag=prev">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
             </c:when>
            <c:otherwise>
		      <c:if test="${command.addJobOrder.originateFromSearchJob != null}">
				  <a href="view_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}&noPrevJob=true">
				  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
				  </a>
			  </c:if>
	        </c:otherwise>
         </c:choose>   
	    <c:choose>
		  <c:when test="${command.addJobOrder.nextListFlag=='true'}">
              <a href="view_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}&showNextListFlag=next">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
          </c:when>
          <c:otherwise>
			  <c:if test="${command.addJobOrder.originateFromSearchJob != null}">
				 <a href="view_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}&noNextJob=true">
				  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
				</c:if>
          </c:otherwise>
        </c:choose>
		<!-- END : #119240 -->	
                <%--  <a href="view_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}"> --%>
					 <a href="edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}">
                        <img src="images/savennextbluarrow.gif" alt="Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Go to Next Page">
                      </a>
                    </th>
                  </tr>                  
                  <tr>
                    <td colspan="4" style="padding-left:0px; padding-right:0px; padding-top:0px; padding-bottom:0px;">
                      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                        <tr>
                          <th width="58%" style="border-right:#7c92be dashed 1px;"><f:message key="description"/></th>
                          <th width="5%" style="border-right:#7c92be dashed 1px;"><f:message key="sort"/></th>
                          <th width="10%" style="border-right:#7c92be dashed 1px;"><f:message key="priceUSD"/></th>
                          <th width="5%" style="border-right:#7c92be dashed 1px;"><f:message key="Split"/></th>
                          <th width="5%" style="border-right:#7c92be dashed 1px;"><f:message key="Discount"/></th>
                          <th width="12%" style="border-right:#7c92be dashed 1px;"><f:message key="Extended\ Price"/></th>
                          <th width="5%">&nbsp;</th>
                        </tr>
                        <c:forEach items="${jobContract.jobContractServices}" var="jobContractService" varStatus="jobContractServiceStatus">     
                          <c:forEach items="${jobContractService.results}" var="jobContractServiceResult" varStatus="jobContractServiceResultStatus">
                            <c:forEach items="${jobContractServiceResult.prebills}" var="prebill" varStatus="prebillStatus">
                              <tr>
                                <td class="row${(jobContractServiceStatus.index + 1) % 2}" style="border-right:#7c92be dashed 1px;">
                                  ${prebill.lineDescription} 
                                </td>
                                <c:if test="${jobContractServiceResultStatus.index == 0}">
                                  <td class="row${(jobContractServiceStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;align:center;" rowspan="${fn:length(jobContractService.results)}">
                                    ${jobContractService.sortOrderNum}
                                  </td>
                                </c:if>
                                <td class="row${(jobContractServiceStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                <td class="row${(jobContractServiceStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber value="${prebill.splitPct}" /></td>
                                <td class="row${(jobContractServiceStatus.index + 1) % 2}" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber value="${prebill.discountPct}" />&nbsp;</td>
                                <td class="row${(jobContractServiceStatus.index + 1) % 2}" align="center" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                <c:if test="${jobContractServiceResultStatus.index == 0}">
                                  <td class="row${(jobContractServiceStatus.index + 1) % 2}" rowspan="${fn:length(jobContractService.results)}">&nbsp;</td>
                                </c:if>
                              </tr>
                            </c:forEach>
                          </c:forEach>
                        </c:forEach>
                      </table>        
                    </td>
                  </tr>
                  <c:forEach items="${jobContract.jobContractVessels}" var="jobContractVessel" varStatus="jobContractVesselCounter">                               
                  <tr>
                    <td style="padding:0;" colspan="4">          

                      <!-- --------------------- VESSEL 1 CONTAINER ------------------------------- -->
                      <div id="T${jobContractCounter.index}vessel${jobContractVesselCounter.index}" class="vessels" style="z-index:1">
                        <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable" >
                          <tbody>
                            <tr>
                              <td width="2%" class="TDShade" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x">                 
                                <div id="T${jobContractCounter.index}bluarrowrightv${jobContractVesselCounter.index}" style="visibility:visible; position:absolute; z-index: 2;margin-right:5px;"> 
                                  <a href="#" onClick="javascript:showTV('${jobContractCounter.index}');"> 
                                    <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" />
                                  </a> 
                                </div>
                                <div id="T${jobContractCounter.index}bluarrowdownv${jobContractVesselCounter.index}" style="visibility:hidden; position:relative; text-align:left; z-index: 1;margin-top:3px;margin-right:4px; margin-left:0px;"> 
                                  <a href="#" onClick="javascript:hideTV('${jobContractCounter.index}');"> 
                                    <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" />
                                  </a> 
                                </div>                
                              </td>
                              <td class="TDShade" nowrap="yes" width="55%">
                               <f:message key="vessel"/>  ${jobContractVesselCounter.index + 1}/Location ${jobContractVesselCounter.index + 1}: &nbsp;${addJobContractVessel.jobContractVessel.vesselName}
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                 <f:message key="vesselType"/> : &nbsp;${addJobContractVessel.jobContractVessel.type}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              </td>
                              <td class="TDShade" nowrap="yes" width="5%">${jobContractVessel.sortOrderNum}</td>
                              <td class="TDShade" nowrap="yes" width="32%">
                                <div style="float:right; font-size:10px; font-weight:bold;">
                                  <a href="#vessel1" onClick="javascript:SCexpandAllv('${jobContractCounter.index}', '${jobContractVesselCounter.index}', '${fn:length(jobContractVessel.jobContractProds)}');">
                                    <img src="images/icoexpandall.gif" alt="Expand All" hspace="2" border="0" align="absmiddle"/>
                                  </a>&nbsp;
                                  <a href="#vessel1" onClick="javascript:SCcollapseAllv('${jobContractCounter.index}', '${jobContractVesselCounter.index}', '${fn:length(jobContractVessel.jobContractProds)}');">
                                    <img src="images/icocollapseall.gif" alt="Collapse All" hspace="2" border="0" align="absmiddle"/>
                                  </a>&nbsp;&nbsp; 
                                </div>
                              </td>
                              
                              <td width="4%" nowrap="yes" class="TDShadeBlue" align="right">&nbsp;</td>
                            </tr>
                          </tbody>
                        </table>
                        <!-- ----------VESSEL 1 Data ---------------- -->
            
                        <div id="T${jobContractCounter.index}vessel${jobContractVesselCounter.index}Container" class="vesselContainer" style="visibility:visible; display:block">
            
                          <!-- --------------------VESSEL 1 Product 1 -------------- -->
              
                          <div class="productTableSC">
              
                            <div id="T${jobContractCounter.index}productTablev${jobContractVesselCounter.index}" class="productTableSC" style="display:none">
                              <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTableSC" style="margin-bottom:3px;">
                                <!--dummy tr -->
                                <tr style="visibility:hidden;height:1px">
                                  <td width="58%" style="border-right:#7c92be dashed 1px; height:1px; padding-left:10px;padding-right:10px; "><img src="images/spacer.gif" width="62" height="1"></td>
                                  <td width="5%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="35" height="1"></td>
                                  <td width="10%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="58" height="1"></td>
                                  <td width="5%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="24" height="1"></td>
                                  <td width="5%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="48" height="1"></td>
                                  <td width="12%" style="border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="81" height="1"></td>
                                  <td width="5%"><img src="images/spacer.gif" width="5" height="1"></td>
                                </tr>
                                <!--dummy tr end -->
                                <c:forEach items="${jobContractVessel.jobContractVesselServices}" var="jobContractVesselService" varStatus="jobContractVesselServiceStatus">  
                                  <c:forEach items="${jobContractVesselService.results}" var="jobContractVesselServiceResult" varStatus="jobContractVesselServiceResultStatus">
                                    <c:forEach items="${jobContractVesselServiceResult.prebills}" var="prebill" varStatus="prebillStatus">
                                      <tr>
                                        <td class="row${(jobContractVesselServiceStatus.index + 1) % 2}" width="58%" style="border-right:#7c92be dashed 1px; padding-left:35px;">&nbsp;${prebill.lineDescription}</td>
                                        <c:if test="${jobContractVesselServiceResultStatus.index == 0}">
                                          <td class="row${(jobContractVesselServiceStatus.index + 1) % 2}" width="5%" align="center" style="border-right:#7c92be dashed 1px;border-bottom:none;" rowspan="${fn:length(jobContractVesselService.results)}">
                                            ${jobContractVesselService.sortOrderNum}
                                          </td>
                                        </c:if>
                                        <td class="row${(jobContractVesselServiceStatus.index + 1) % 2}" width="10%" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                        <td class="row${(jobContractVesselServiceStatus.index + 1) % 2}" width="5%" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber value="${prebill.splitPct}" />&nbsp;</td>
                                        <td class="row${(jobContractVesselServiceStatus.index + 1) % 2}" width="5%" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber value="${prebill.discountPct}" />&nbsp;</td>
                                        <td class="row${(jobContractVesselServiceStatus.index + 1) % 2}" width="12%" style="border-right:#7c92be dashed 1px;text-align:right;"><f:formatNumber minFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                        <c:if test="${jobContractVesselServiceResultStatus.index == 0}">
                                          <td class="row${(jobContractVesselServiceStatus.index + 1) % 2}" width="5%">&nbsp;</td>
                                        </c:if>
                                      </tr>
                                    </c:forEach>
                                  </c:forEach>
                                </c:forEach>
                              </table>
                            </div>

                            <c:forEach items="${jobContractVessel.jobContractProds}" var="jobContractProd" varStatus="jobContractProdCounter">   
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltableSC" style="margin-right:0px;padding-bottom:0px;">
                              <tr>
                                <th width="2%" valign="top" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> 
                                  <div id="T${jobContractCounter.index}bluarrowrightv${jobContractVesselCounter.index}p${jobContractProdCounter.index}" style="visibility:visible; position:absolute; z-index: 2;margin-top:5px; margin-left:5px;"> 
                                    <a href="#" onClick="javascript:showTvlotTable('${jobContractCounter.index}', '${jobContractVesselCounter.index}', '${jobContractProdCounter.index}');"> 
                                      <img src="images/bluarrowrightsml.gif" height="13" border="0" />
                                    </a> 
                                  </div>
                                  <div id="T${jobContractCounter.index}bluarrowdownv${jobContractVesselCounter.index}p${jobContractProdCounter.index}" style="visibility:hidden;position:relative; text-align:left; z-index: 1;margin-top:7px;margin-left:0px "> 
                                    <a href="#" onClick="javascript:hideTvlotTable('${jobContractCounter.index}', '${jobContractVesselCounter.index}', '${jobContractProdCounter.index}');"> 
                                      <img src="images/bluarrowdownsml.gif" height="7" border="0" />
                                    </a> 
                                  </div>                  
                                </th>
                                <td class="TDShadeGrey" width="56%">
                                  &nbsp;&nbsp;<f:message key="Lot"/>: ${jobContractProd.jobProductName}
                                  
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<f:message key="productGroup"/> : &nbsp;&nbsp;
                                  ${jobContractProd.group}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                                <td align="center" class="TDShadeGrey" nowrap="yes" width="6%">${jobContractProd.sortOrderNum}</td>
                                <td width="36%" class="TDShadeGrey">&nbsp;</td>
                                
                                <td align="right" class="TDShadeGrey" nowrap="yes" width="4%">&nbsp;</td>
                              </tr>
                              <tr>
                                <td colspan="5" valign="top" >

                                  <!-- ------------ Sample Location Container Table V1 ------------------- -->
                                  <% int seqCount = 0; %>

                                  <div id="T${jobContractCounter.index}samplelocContainerv${jobContractVesselCounter.index}p${jobContractProdCounter.index}" class="samplelocContainer" style="visibility:hidden; display:none;">
                                    <table width="100%"  cellpadding="0" cellspacing="0" class="greyApplTableSC">
                                      <!--dummy tr -->
                                      <tr style="visibility:visible;height:1px;background-color:#f7f7f7;">
                                        <td width="56%" style="border-bottom:none;border-right:#7c92be dashed 1px; height:1px; padding-left:10px;padding-right:10px; "><img src="images/spacer.gif" width="60" height="1"></td>
                                        <td width="5%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="35" height="1"></td>
                                        <td width="10%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="60" height="1"></td>
                                        <td width="5%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="24" height="1"></td>
                                        <td width="6%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="48" height="1"></td>
                                        <td width="12%" style="border-bottom:none;border-right:#7c92be dashed 1px;height:1px;padding-left:10px; padding-right:10px; "><img src="images/spacer.gif" width="81" height="1"></td>
                                        <td width="5%" style="border-bottom:none;border-right:none;"><img src="images/spacer.gif" width="5" height="1"></td>
                                      </tr>
                                      <!--dummy tr end -->
                                      <c:forEach items="${jobContractProd.results}" var="jobContractProductInspectionResult" varStatus="jobContractProductInspectionResultCounter">  
                                        <% seqCount ++; %>
                                        <c:forEach items="${jobContractProductInspectionResult.prebills}" var="prebill" varStatus="prebillStatus">   
                                          <tr>
                                            <td class="row<%=seqCount % 2%>" width="56%" style="border-right:#7c92be dashed 1px; border-bottom:none;">${prebill.lineDescription}</td>
                                            <td class="row<%=seqCount % 2%>" width="5%" align="center" style="border-right:#7c92be dashed 1px;border-bottom:none;">&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" width="10%" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" width="5%" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber value="${prebill.splitPct}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" width="6%" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber value="${prebill.discountPct}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" width="12%" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                            <c:if test="${jobContractProductInspectionResultCounter.index == 0}">
                                              <td class="row<%=seqCount % 2%>" width="5%" align="right" style="border-bottom:none;border-right:none;" rowspan="${jobContractProductInspectionResultCounter.count}">&nbsp;</td>
                                            </c:if>
                                          </tr>
                                        </c:forEach>
                                      </c:forEach>

                                      <c:forEach items="${jobContractProd.jobContractProductServices}" var="jobContractProductService" varStatus="jobContractProductServiceStatus">  
                                        <% seqCount ++; %>
                                        <c:forEach items="${jobContractProductService.results}" var="jobContractProductServiceResult" varStatus="jobContractProductServiceResultStatus">
                                          <c:forEach items="${jobContractProductServiceResult.prebills}" var="prebill" varStatus="prebillStatus">
                                            <tr>
                                              <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px; border-bottom:none;">${prebill.lineDescription}</td>
                                              <c:if test="${jobContractProductServiceResultStatus.index == 0}">
                                                <td class="row<%=seqCount % 2%>" align="center" style="border-right:#7c92be dashed 1px;border-bottom:none;" rowspan="${fn:length(jobContractProductService.results)}">
                                                  ${jobContractProductService.sortOrderNum}
                                                </td>
                                              </c:if>
                                              <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber value="${prebill.splitPct}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber value="${prebill.discountPct}" />&nbsp;</td>
                                              <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                              <c:if test="${jobContractProductServiceResultStatus.index == 0}">
                                                <td class="row<%=seqCount % 2%>" align="right" style="border-bottom:none;border-right:none;" rowspan="${fn:length(jobContractProductService.results)}">&nbsp;</td>
                                              </c:if>
                                            </tr>
                                          </c:forEach>
                                        </c:forEach>
                                      </c:forEach>

                                      <c:forEach items="${jobContractProd.jobContractTests}" var="jobContractTest" varStatus="jobContractTestCounter">   
                                        <% seqCount ++; %>
                                        <c:forEach items="${jobContractTest.prebills}" var="prebill" varStatus="prebillStatus">   
                                          <tr>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px; border-bottom:none;">${prebill.lineDescription}</td>
                                            <td class="row<%=seqCount % 2%>" align="center" style="border-right:#7c92be dashed 1px;border-bottom:none;">
                                              ${jobContractTest.sortOrderNum}
                                            </td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber value="${prebill.splitPct}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber value="${prebill.discountPct}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" align="right" style="border-bottom:none;border-right:none;">&nbsp;</td>
                                          </tr>
                                        </c:forEach>
                                      </c:forEach>

                                      <c:forEach items="${jobContractProd.jobContractManualTests}" var="jobContractManualTest" varStatus="jobContractManualTestCounter">   
                                        <% seqCount ++; %>
                                        <c:forEach items="${jobContractManualTest.prebills}" var="prebill" varStatus="prebillStatus">   
                                          <tr>
                                            <td class="row<%=seqCount % 2%>" class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px; border-bottom:none;">${prebill.lineDescription}</td>
                                            <td class="row<%=seqCount % 2%>" align="center" style="border-right:#7c92be dashed 1px;border-bottom:none;">
                                              ${jobContractManualTest.sortOrderNum}
                                            </td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber value="${prebill.splitPct}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber value="${prebill.discountPct}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" align="right" style="border-bottom:none;border-right:none;">&nbsp;</td>
                                          </tr>
                                        </c:forEach>
                                      </c:forEach>

                                      <c:forEach items="${jobContractProd.jobContractSlates}" var="jobContractSlate" varStatus="jobContractSlateCounter">   
                                        <% seqCount ++; %>
                                        <c:forEach items="${jobContractSlate.prebills}" var="prebill" varStatus="prebillStatus">   
                                          <tr>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px; border-bottom:none;">${prebill.lineDescription}</td>
                                            <td class="row<%=seqCount % 2%>" align="center" style="border-right:#7c92be dashed 1px;border-bottom:none;">
                                                ${jobContractSlate.sortOrderNum}
                                            </td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" value="${prebill.unitPrice}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber value="${prebill.splitPct}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber value="${prebill.discountPct}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" style="border-right:#7c92be dashed 1px;border-bottom:none;text-align:right;">&nbsp;&nbsp;<f:formatNumber minFractionDigits="2" value="${prebill.netPrice}" />&nbsp;</td>
                                            <td class="row<%=seqCount % 2%>" align="right" style="border-bottom:none;border-right:none;">&nbsp;</td>
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
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
      			  <td></td>
                  <td style="text-align:right">

		   <!-- START : #119240 -->
		  <c:choose>
            <c:when test="${command.addJobOrder.originateFromSearchJob==null}">
				&nbsp;
            </c:when>
            <c:otherwise>
              <a href="${command.addJobOrder.originateFromSearchJob}?jobNum=${command.jobOrder.jobNumber}">
			   <img src="images/icofindjob.gif" alt="Return to Search" width="16" height="14" border="0" align="absmiddle">			  
			  </a>
			</c:otherwise>
           </c:choose>    
		  <c:choose>
			<c:when test="${command.addJobOrder.prevListFlag=='true'}">
              <a href="view_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}&showPrevListFlag=prev">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
             </c:when>
            <c:otherwise>
		     <c:if test="${command.addJobOrder.originateFromSearchJob != null}">
				  <a href="view_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}&noPrevJob=true">
				  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
				  </a>
				</c:if>
	        </c:otherwise>
         </c:choose>   
	    <c:choose>
		  <c:when test="${command.addJobOrder.nextListFlag=='true'}">
              <a href="view_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}&showNextListFlag=next">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
          </c:when>
          <c:otherwise>
			  <c:if test="${command.addJobOrder.originateFromSearchJob != null}">
				 <a href="view_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}&noNextJob=true">
				  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
				</c:if>
          </c:otherwise>
        </c:choose>
		<!-- END : #119240 -->	
                    <%-- <a href="view_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}"> --%>
					<a href="edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}">
                      <img src="images/savennextbluarrow.gif" alt="Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Go to Next Page">
                    </a>
                  </td>
                </tr>
              </table>
            </div>            
            <!----------------- TAB 2 CONTAINER END ------------------------------ -->
            </c:forEach>
          </div>
        </div>
        <script type="text/javascript">
      
          //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
          dolphintabs.init("tabnav", '0')

        </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
        <table width="100%" cellspacing="0">
          <tr>
            <td height="24" style="text-align:right; padding-right:0px;"><select name="sel5" id="sel5" class="SelectionBox" onChange="MM_jumpMenu('parent',this,1)">
                <option selected>Go to ... &gt;</option>
                <option value="view_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"> <f:message key="entry"/>  </option>
                <option value="view_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="jobInstructions"/></option>
              </select>            
            </td>
          </tr>
        </table>
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->    </td>
  </tr>
</table>

