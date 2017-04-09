<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><head>
<script language="javascript">

  function onAddVessel()
  {
  
    document.jobOrderViewOpInstrForm.addOrDeleteVessel.value = "add";
    document.jobOrderViewOpInstrForm.submit();
  }
  function onDeleteVessel(index)
  {
    document.jobOrderViewOpInstrForm.addOrDeleteVessel.value = "delete";
    document.jobOrderViewOpInstrForm.vesselIndex.value = index;
    document.jobOrderViewOpInstrForm.submit();

  }
  function onAddProduct(index)
  {
  
    document.jobOrderViewOpInstrForm.addOrDeleteProduct.value = "add";
    document.jobOrderViewOpInstrForm.vesselIndex.value = index;
    document.jobOrderViewOpInstrForm.submit();
  }
  function onDeleteProduct(vesselIndex,productIndex)
  {
    document.jobOrderViewOpInstrForm.addOrDeleteProduct.value = "delete";
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;
    document.jobOrderViewOpInstrForm.submit();

  } 
  function onAddEvent(vesselIndex,productIndex)
  {
  
    document.jobOrderViewOpInstrForm.addOrDeleteEvent.value = "add";
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;
    document.jobOrderViewOpInstrForm.submit();
  }
  function onDeleteEvent(vesselIndex,productIndex,eventIndex)
  {
    document.jobOrderViewOpInstrForm.addOrDeleteEvent.value = "delete";
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;
    document.jobOrderViewOpInstrForm.eventIndex.value = eventIndex;
    document.jobOrderViewOpInstrForm.submit();

  } 
  function onAddSampleLoc(vesselIndex,productIndex)
  {
  
    document.jobOrderViewOpInstrForm.addOrDeleteSampleLoc.value = "add";
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;   
    document.jobOrderViewOpInstrForm.submit();
  }
  function onDeleteSampleLoc(vesselIndex,productIndex,sampleLocIndex)
  {
    document.jobOrderViewOpInstrForm.addOrDeleteSampleLoc.value = "delete";
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderViewOpInstrForm.sampleLocIndex.value = sampleLocIndex;
    document.jobOrderViewOpInstrForm.submit();

  } 
  function onAddQty(vesselIndex,productIndex)
  {
  
    document.jobOrderViewOpInstrForm.addOrDeleteQty.value = "add";
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;   
    document.jobOrderViewOpInstrForm.submit();
  }
  function onDeleteQty(vesselIndex,productIndex,qtyIndex)
  {
    document.jobOrderViewOpInstrForm.addOrDeleteQty.value = "delete";
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderViewOpInstrForm.qtyIndex.value = qtyIndex;
    document.jobOrderViewOpInstrForm.submit();

  } 
  function onDeleteTest(vesselIndex,productIndex,sampleLocIndex,testIndex)
  {
    document.jobOrderViewOpInstrForm.addOrDeleteTest.value = "delete";
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderViewOpInstrForm.sampleLocIndex.value = sampleLocIndex;
    document.jobOrderViewOpInstrForm.testIndex.value = testIndex;   
    document.jobOrderViewOpInstrForm.submit();

  } 
    function onAddTest(vesselIndex,productIndex,sampleLocIndex)
  {
    
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderViewOpInstrForm.sampleLocIndex.value = sampleLocIndex;
  } 
  function onDeleteSlate(vesselIndex,productIndex,sampleLocIndex,slateIndex)
  {
    document.jobOrderViewOpInstrForm.addOrDeleteSlate.value = "delete";
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderViewOpInstrForm.sampleLocIndex.value = sampleLocIndex;
    document.jobOrderViewOpInstrForm.testIndex.value = slateIndex;    
    document.jobOrderViewOpInstrForm.submit();
  }

    function onAddSlate(vesselIndex,productIndex,sampleLocIndex)
  {
    
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderViewOpInstrForm.sampleLocIndex.value = sampleLocIndex;
  }     
function onCopyVessel(vesselIndex) {
  confirm("Vessel Copied.");
  document.jobOrderViewOpInstrForm.copyFlag.value = "copyVessel";
  document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;
  document.jobOrderViewOpInstrForm.submit();
  }   
function onCopyProduct() {
  document.jobOrderViewOpInstrForm.copyFlag.value = "copyProduct";
  }       
  function setChosenContracts(vesselIndex, productIndex, contractCount)
  {
  
  for(i=0;i<contractCount;i++)
  {
  
    var elemName = "appchk" + vesselIndex + productIndex + i ;
    
    if(document.getElementById(elemName).checked )
    {
      
      if(document.jobOrderViewOpInstrForm.chosenContracts.value == "")
      {
        document.jobOrderViewOpInstrForm.chosenContracts.value = document.getElementById(elemName).value;
      }
      else
      {
        var existingVal = document.jobOrderViewOpInstrForm.chosenContracts.value;
        document.jobOrderViewOpInstrForm.chosenContracts.value = existingVal + ";" + document.getElementById(elemName).value;
      }
      
      
    }
    
  }
    
  
  
  }   
  function goToNextPage()
{
  document.jobOrderViewOpInstrForm.nextPageFlag.value = "1";
  document.jobOrderViewOpInstrForm.submit();
  
}
   function updateTestIFrameSrc(vesselCnt,productCnt,SampleCnt,nomDate)
  {
  
      chosenContractList = document.jobOrderViewOpInstrForm.chosenContracts.value;
      
    document.getElementById('searchtestpopup').setAttribute("src","search_test_popup.htm?inputFieldId=addJobVessels["+vesselCnt+"].jobVessel.vesselName&rowNum="+vesselCnt+"&searchForm=jobOrderViewOpInstrForm&chosenContracts="+chosenContractList+"&div1=test"+vesselCnt+productCnt+SampleCnt+"&div2=testbody"+vesselCnt+productCnt+SampleCnt+"&nomDate="+nomDate);
    document.getElementById('searchtestpopup').height = "500px";
  }
  
   function updateSlateIFrameSrc(vesselCnt,productCnt,SampleCnt,nomDate)
  {
  
      chosenContractList = document.jobOrderViewOpInstrForm.chosenContracts.value;
      
    document.getElementById('searchslatepopup').setAttribute("src","search_slate_popup.htm?inputFieldId=addJobVessels["+vesselCnt+"].jobVessel.vesselName&rowNum="+vesselCnt+"&searchForm=jobOrderViewOpInstrForm&chosenContracts="+chosenContractList+"&div1=slate"+vesselCnt+productCnt+SampleCnt+"&div2=slatebody"+vesselCnt+productCnt+SampleCnt+"&nomDate="+nomDate);
    document.getElementById('searchslatepopup').height = "500px";
  }  

function setJobContractCodeVal(elemId,vesselIndex,productIndex,jobContractCode)
{
  
  if(document.getElementById(elemId).checked == true)
  {
    document.jobOrderViewOpInstrForm.chosenContracts.value = jobContractCode;
    document.jobOrderViewOpInstrForm.applicableContractFlag.value = "add";
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
  }
  else
  {
    document.jobOrderViewOpInstrForm.chosenContracts.value = jobContractCode;
    document.jobOrderViewOpInstrForm.applicableContractFlag.value = "delete";
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselIndex;
    document.jobOrderViewOpInstrForm.productIndex.value = productIndex;
  }
  document.jobOrderViewOpInstrForm.submit();
}

function setLabAnalysisFlag()
{
  document.jobOrderViewOpInstrForm.labAnalysisFlag.value = "Y";
  document.jobOrderViewOpInstrForm.submit();
}
function setOtApprovedFlag()
{
  document.jobOrderViewOpInstrForm.otApprovedFlag.value = "Y";
  document.jobOrderViewOpInstrForm.submit();
}
function populateInstructions(vesselCnt,productCnt,eventCnt,eventCode)
{
    document.jobOrderViewOpInstrForm.vesselIndex.value = vesselCnt;
    document.jobOrderViewOpInstrForm.productIndex.value = productCnt;
    document.jobOrderViewOpInstrForm.eventIndex.value = eventCnt;
    document.jobOrderViewOpInstrForm.instructionFlag.value = eventCode;
    document.jobOrderViewOpInstrForm.submit();
}
</script>
</head>
<icb:list var="divisions">
  <icb:item> 
${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
  <icb:item>${command.jobOrder.branch.businessUnit.name}</icb:item>
</icb:list>
<icb:list var="jobDetail">
  <icb:item>${command.jobOrder.jobNumber}</icb:item>
</icb:list>
<icb:list var="operationCode">
  <icb:item>${command.jobOrder.operation}</icb:item>
</icb:list>
<icb:list var="jobStatus">
  <icb:item>jobStatus</icb:item>
</icb:list>
<icb:list var="UOM">
  <icb:item>UOM</icb:item>
</icb:list>
<icb:list var="option">
  <icb:item>option</icb:item>
</icb:list>
<icb:list var="plusMinus">
  <icb:item>plusMinus</icb:item>
</icb:list>
<icb:list var="sampleLocation">
  <icb:item>sampleLocation</icb:item>
</icb:list>
<icb:list var="sampleTiming">
  <icb:item>sampleTiming</icb:item>
</icb:list>
<icb:list var="sampleType">
  <icb:item>sampleType</icb:item>
</icb:list>
<icb:list var="sampleVolume">
  <icb:item>sampleVolume</icb:item>
</icb:list>
<icb:list var="containerType">
  <icb:item>containerType</icb:item>
</icb:list>
<icb:list var="retainTested">
  <icb:item>retainTested</icb:item>
</icb:list>
<icb:list var="yesNo">
  <icb:item>yesNo</icb:item>
</icb:list>

<form:form name="jobOrderViewOpInstrForm" method="POST" action="view_job_operational_info.htm">
<div style="color:red;">
  <form:errors cssClass="error" />
  <c:if test="${not empty requestScope['error_msg']}">
    <f:message key="${requestScope['error_msg']}" />
  </c:if>
</div>
<div style="color:red;"></div>

      <input type="hidden" name="refreshing" value="false" />
	  <input type="hidden" name="createProject" value="false" />
      <input type="hidden" name="_page" value="1" />
      <form:hidden path="addOrDeleteVessel"/>
      <form:hidden path="vesselIndex"/>
      <form:hidden path="targetVesselIndex"/>
      <form:hidden path="addOrDeleteProduct"/>
      <form:hidden path="productIndex"/>
      <form:hidden path="addOrDeleteEvent"/>
      <form:hidden path="eventIndex"/>
      <form:hidden path="addOrDeleteSampleLoc"/>
      <form:hidden path="sampleLocIndex"/>
      <form:hidden path="addOrDeleteTest"/>      
      <form:hidden path="testIndex"/>
      <form:hidden path="addOrDeleteSlate"/>      
      <form:hidden path="slateIndex"/>
      <form:hidden path="addOrDeleteQty"/>
      <form:hidden path="qtyIndex"/> 
      <form:hidden path="copyFlag"/>        
      <form:hidden path="chosenContracts"/>  
      <form:hidden path="chosenTestIds"/>        
      <form:hidden path="chosenSlateIds"/> 
      <form:hidden path="nextPageFlag"/>
      <form:hidden path="applicableContractFlag"/>
      <form:hidden path="otApprovedFlag"/>
      <form:hidden path="labAnalysisFlag"/>
       <form:hidden path="instructionFlag"/>
  <c:set var="urlSuffix" value="${icbfn:urlSuffixByJobType(command.jobOrder.jobType)}" scope="request" />     

         
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
                    <a href="view_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}">
                      Entry 
                    </a>
                  </td>
                  <td class="breadcrumbtrailActive">
                    Job Instructions 
                  </td>
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                      <c:when test="${command.jobOrder.pageNumber >= 3}">               
                        <a href="view_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}">
                          Select Charges
                        </a>
                      </c:when>
                      <c:otherwise>
                        Select Charges 
                      </c:otherwise>
                    </c:choose>
                  </td>
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                      <c:when test="${command.jobOrder.pageNumber >= 4}">               
                        <a href="edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}">
                          Preview
                        </a>
                      </c:when>
                      <c:otherwise>
                        Preview
                      </c:otherwise>
                    </c:choose>
                  </td>               
 
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                      <c:when test="${command.jobOrder.pageNumber >= 5}">               
                        <a href="edit_job_view_invoice.htm?jobNumber=${command.jobOrder.jobNumber}">
                          Invoice 
                        </a>
                      </c:when>
                      <c:otherwise>
                        Invoice 
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
      </div>      <!-- BREADCRUMB TRAIL END -->
      

      
               

      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span>Operational Instructions</span></a></li>
            </ul>
            <label>
            <select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
              <option selected>Go to ... &gt;</option>
              <option value="view_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="entry"/></option>
            </select>
            </label>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr>
                    <th>Job Instructions <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> Job ID: ${command.jobOrder.jobNumber }</th>
                    <th style="text-align:right">Job Status:
              <form:select id="sel1" cssClass="selectionBox" path="jobOrder.jobStatus" items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name" itemValue="value" />
                          <form:errors path="jobOrder.jobStatus" cssClass="error"/>
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:popup_show('integrationlog', 'integrationlog_drag', 'integrationlog_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();">Integration Log</a>
                    </th>
                    <th style="text-align:right"><a href="#"><img src="images/ico_print.gif" alt="Print Job Order" width="18" height="16" hspace="2" border="0" align="absmiddle" title="Print Job Order"></a><a href="#"><img src="images/icoship.gif" alt="Send To SAM" hspace="2" border="0" align="absmiddle" title="Send To SAM"></a><a href="#"><img src="images/icoflask.gif" alt="Send To LIMS" hspace="2" border="0" align="absmiddle" title="Send To LIMS"></a><a href="#" onclick="goToNextPage()"><img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#"><img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle" onclick="javascript:document.jobOrderViewOpInstrForm.submit();"/></a></th>
                  </tr>
                  <tr>
                    <td colspan="3" style="padding:0">
                    
              <!-- ------------------------ VESSELS -------------------------------------------------- -->
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
                <tr>
                  <th width="30" style="background-image:url(images/arrowblubg.gif);"> <div id="bluarrowright" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4" style="margin-top:2px;"/> </div>
                    <div id="bluarrowdown" style="visibility:visible;position:relative; z-index: 1; margin-top:6px "> <a href="#instructions" onClick="javascript:hideInstructions();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>

                  <th width="87%"><a name="vessels"></a>
                  <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                  Vessels
                  </c:if>
                   <c:if test="${command.jobOrder.jobType == 'FST' || command.jobOrder.jobType=='OUT'}">
                  Locations
                  </c:if>
                  </th>
                  <th width="10%">&nbsp;
                  </th>
                </tr>

     <c:forEach items="${command.addJobVessels}" var="arrJobVessel" varStatus="vesselCounter">                               
                  <tr>
                    <td colspan="3" style="padding:0">
          <!-- -------------------- VESSEL 1 CONTAINER -------------------------- -->
                      <div id="vessel${vesselCounter.index}" class="vessels" style="z-index:1;visibility:visible;">
                        <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable" >
                          <tbody>
                            <tr>
                              <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> 
                              
                              
  
              
              
                              <div id="bluarrowrightv${vesselCounter.index}" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> 
                              <a href="#"> 
                              <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/ style="margin-top:2px;" onClick="javascript:showvesselTable('${vesselCounter.index}');">
                              </a> 
                              </div>
                              
                              <div id="bluarrowdownv${vesselCounter.index}" style="visibility:visible;position:relative; z-index: 1; margin-top:6px "> 
                              <a href="#"> 
                              <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"  onClick="javascript:hidevesselTable('${vesselCounter.index}');"/>
                              </a> 
                              </div> 
                              </th>
                              <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                              <td width="10%" class="TDShade" ><a name="vessel1"></a>Vessel ${vesselCounter.index + 1}:<span class="redstar">*</span> </td>
                              </c:if>
                               <c:if test="${command.jobOrder.jobType == 'FST' || command.jobOrder.jobType=='OUT'}">
                              <td width="10%" class="TDShade" ><a name="vessel1"></a>Location ${vesselCounter.index + 1}:<span class="redstar">*</span> </td>
                              </c:if>
                              
                              
                              <td width="20%" class="TDShadeBlue">
                              <form:input cssClass="inputBox" size="20" path="addJobVessels[${vesselCounter.index}].jobVessel.vesselName" maxlength="256" disabled="true"/>
                              <form:errors path="addJobVessels[${vesselCounter.index}].jobVessel.vesselName" cssClass="redstar"/>
                          <!--  
                              <a href="#" onClick="javascript:popup_show('vesselsearch${vesselCounter.index}', 'vesselsearch_drag${vesselCounter.index}', 'vesselsearch_exit${vesselCounter.index}', 'screen-corner', 120, 20); popupboxenable();hideIt();">
                              <img src="images/lookup.gif" width="13" height="13" border="0" align="absmiddle" />
                              </a> -->
                              </td>
                              
                              <td width="40%" nowrap class="TDShade" >
                              <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                              <strong>Vessel Type:<span class="redstar">*</span></strong>                              
                          <form:select id="sel2" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].jobVessel.type" items="${icbfn:dropdown('vesselType', null)}" itemLabel="name" itemValue="value" disabled="true"/>
                              </c:if>
                              </td>
                              <td colspan="2" width="20%" class="TDShadeBlue">
                              
                              <div style="float:right; font-size:10px; font-weight:bold;"> 
                              
                              <a href="#" onClick="javascript:expandAllv1(${vesselCounter.index},${fn:length(arrJobVessel.addJobProds)},'${command.jobOrder.jobType}');">Expand All</a>
                              
                              <br>
                              <a href="#" onClick="javascript:collapseAllv1(${vesselCounter.index},${fn:length(arrJobVessel.addJobProds)},'${command.jobOrder.jobType}');">Collapse All</a>
                              </div>
                              
                              
                          </td>
                              <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                            </tr>
                          </tbody>
                        </table>
                        <!-- ------------------------VESSEL 1 Data ------------------------- -->


                              
                        <div id="vessel${vesselCounter.index}Container" class="vesselContainer">
            
                          <!-- -------------------VESSEL 1 Product 1 --------------------------- -->
                  
                  
                          <div id="productTablev${vesselCounter.index}" class="productContainer" >
                                    
              <table width="100%" border="0" cellspacing="0" cellpadding="0" > <!-- Product Table Holder -->
                  
                            
               <c:forEach items="${arrJobVessel.addJobProds}" var="arrJobProd" varStatus="productCounter">   
                     
                <tr>
                <td align="right" style="background-image:url(images/vesselcontainerbg.jpg); background-repeat:repeat-x; background-color:#fafeff;"> 
                
                  <div style="width:100%; text-align:left;visibility:visible;" >
                  Applicable Contracts: 
                  <c:forEach items="${arrJobProd.applicableContractCodes}" var="arrAppJobContractCodes" varStatus="appContractCounter"> 
                <input type="checkbox" id="appchk${vesselCounter.index }${productCounter.index }${appContractCounter.index }" name="chk${vesselCounter.index }${productCounter.index }${appContractCounter.index }" value="${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].applicableContractCodes[appContractCounter.index]}" onClick="setJobContractCodeVal('chk${vesselCounter.index }${productCounter.index }${appContractCounter.index }','${vesselCounter.index }','${productCounter.index }','${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].applicableContractCodes[appContractCounter.index]}')" checked disabled>
                 ${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].applicableContractCodes[appContractCounter.index]}&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;
                </c:forEach>
                <c:forEach items="${arrJobProd.jobContractCodes}" var="arrJobContractCodes" varStatus="jobContractCounter"> 
                <input type="checkbox" id="chk${vesselCounter.index }${productCounter.index }${jobContractCounter.index }" name="chk${vesselCounter.index }${productCounter.index }${jobContractCounter.index }" value="" disabled>
                 ${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].jobContractCodes[jobContractCounter.index]}&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;
                </c:forEach>
                
                
                    </div>
                <!-- Product Table Start -->
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="OperationsTable" style="width:100%;">
                              <tr>
                              
                                <th width="16" rowspan="2" valign="top" style="border-right:none;" > 
                                <div id="bluarrowrightv${vesselCounter.index }p${productCounter.index }" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> 
                                <a href="#"> 
                                <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="4" style="margin-top:2px;" onClick="javascript:showvproductTable(${vesselCounter.index },${productCounter.index });"/>
                                </a> 
                                </div>
                                
                                <div id="bluarrowdownv${vesselCounter.index}p${productCounter.index }" style="visibility:visible;position:relative; z-index: 1; margin-top:6px "> 
                                <a href="#"> 
                                <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="4" onClick="javascript:hidevproductTable(${vesselCounter.index },${productCounter.index });"/></a> 
                                </div>
                                </th>
                                
                                <th width="27%" class="TDShadeGrey">Product ${ productCounter.index + 1}:<span class="redstar">*</span> 
                          <form:input cssClass="inputBox" size="10" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.jobProductName" maxlength="256" disabled="true"/>
                <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.jobProductName" cssClass="redstar"/>  
                              
                                </th>
                                
                                <th width="40%" nowrap class="TDShadeGrey" ><strong>Product Group:<span class="redstar">*</span></strong>
                    <form:select id="sel3" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.group" items="${icbfn:dropdown('productGroup', null)}" itemLabel="name" itemValue="value" disabled="true"/>
                                  </th>
                                  
                                <th width="28%" class="TDShadeGrey" >Destination: 
                          <form:input cssClass="inputBox" size="7" maxlength="256"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.destination" disabled="true"/>
                <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.destination" cssClass="redstar"/> 
                                </th>
                                
                                <td align="right" class="TDShadeGreyDark" >
                                <div id="div2" style="width:30px; text-align:right;"> 
                                
                                &nbsp;
                                
                                </div>
                                </td>
                              </tr>
                              
                              <tr>
                                <td colspan="6" style="border-right:#eeeeee 1px solid;">
                
                <!-- ------------------QUANTITY CONTAINER V1 ----------------------- -->
                        <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                                  <div id="quantityContainerv${vesselCounter.index}p${productCounter.index}" class="quantityContainer" style="visibility:visible;">
                                    
                                    <table width="100%"  align="center" cellpadding="0" cellspacing="0" style="border:none;">
                                      <tr>
                                        <th width="13" rowspan="2" valign="top"  style="border-bottom:none;"> 
                                        <div id="bluarrowrightv${vesselCounter.index }p${productCounter.index }q1" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> 
                                        <a href="#"> 
                                        <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6" onClick="javascript:showvpquantityTable(${vesselCounter.index },${productCounter.index });"/>
                                        </a> 
                                        </div>
                                        
                                        <div id="bluarrowdownv${vesselCounter.index }p${productCounter.index }q1" style="visibility:visible;position:relative; z-index: 1; margin-top:6px "> 
                                        <a href="#"> 
                                        <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"  onClick="javascript:hidevpquantityTable(${vesselCounter.index },${productCounter.index });"/>
                                        </a> 
                                        </div>
                                        </th>
                                        <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                                        <td width="3%" style="background-color:#f2f1f1;"><img src="images/spacer.gif" width="1" height="1" /></td>
                                        <td width="20%" style="background-color:#f2f1f1;" >Ordered Quantity</td>
                                        <td width="15%" style="background-color:#f2f1f1;" >&nbsp;&nbsp;&nbsp;&nbsp;UOM</td>
                                        <td width="13%" style="background-color:#f2f1f1;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Option</td>
                                        <td width="7%" style="background-color:#f2f1f1;" ><b>&nbsp;&nbsp;&nbsp;&nbsp;+/-</b></td>
                                        <td width="7%" style="background-color:#f2f1f1;" >&nbsp;</td>
                                        <td width="18%" style="background-color:#f2f1f1;" >&nbsp;&nbsp;Vessel/ Drafts </td>
                                        <td width="12%" style="background-color:#f2f1f1;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tanks</td>
                                        <td width="35" style="border-right:none"><div id="div14" style="width:35px; text-align:center;"> 
                                         
                        </c:if>
                                        </div>
                                        </td>
                                      </tr>
                                      <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                                      <tr>
                                        <td width="3%">${productCounter.index + 1 }.0</td>
                                        <td width="20%" >
                                        <form:input cssClass="inputBox" cssStyle="text-align:right; background-color:#d2e1ff; color:#000099;" size="15" maxlength="15"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.productQty" disabled="true"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.productQty" cssClass="redstar"/>  
                                    </td>
                                        <td width="15%" >&nbsp;&nbsp;
                    <form:select id="sel4" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.uom" items="${icbfn:dropdown('staticDropdown',UOM)}" itemLabel="name" itemValue="value" disabled="true"/>                                                                              
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.uom" cssClass="redstar"/>  
                                        </td>
                                        <td width="13%">&nbsp;&nbsp;
                                        <form:select id="sel5" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.option" items="${icbfn:dropdown('staticDropdown',option)}" itemLabel="name" itemValue="value" disabled="true"/>                                                                                                         
                                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.option" cssClass="redstar"/>  
                                        </td>
                                        <td width="7%">&nbsp;&nbsp;&nbsp;
                                        <form:select id="sel6" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.plusMinus" items="${icbfn:dropdown('staticDropdown',plusMinus)}" itemLabel="name" itemValue="value" disabled="true"/>                                                                                                                                      
                                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.plusMinus" cssClass="redstar"/>  
                                        </td>
                                        <td width="7%">&nbsp;&nbsp;
                                        <form:input cssClass="inputBox" cssStyle="width:20px;" size="3"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.plusMinusPct" disabled="true"/>
                                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.plusMinusPct" cssClass="redstar"/>  
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.plusMinusPct" cssClass="redstar"/>                                          
                                          %
                                        </td>
                                      <td width="18%">&nbsp;&nbsp;&nbsp;
                                        <form:input cssClass="inputBox"  size="10" maxlength="30"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.drafts" disabled="true"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.drafts" cssClass="redstar"/>                                                                                                                     
                                    </td>
                                    <td width="12%">&nbsp;&nbsp;&nbsp;
                                    <form:input cssClass="inputBox" size="10" maxlength="4"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.tanks" disabled="true"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.tanks" cssClass="redstar"/>                                                                        
                                    </td>
                                    
                                    <td class="TDGreyDark" style="border-right:none" >&nbsp;</td>
                                  </tr>
                                  </c:if>
                                </table>
                                
                                    <div id="quantitytablev${vesselCounter.index }p${productCounter.index}" style="display:block; visibility:visible;">
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0"  style="border:none;">
                                      
                                      <c:forEach items="${arrJobProd.jobProdQtys}" var="arrJobProdQty" varStatus="qtyCounter">   
                                      
                                        <tr >
                                          <!--  <td width="18" rowspan="3" >
                                          <img src="images/spacer.gif" width="17" height="1" />
                                          </td>-->
                                          <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          ${productCounter.index + 1 }.${qtyCounter.index + 1 }
                                          </td>
                                        <td width="20%">
                                        <form:input cssClass="inputBox"  size="15" maxlength="15"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].productQty" disabled="true"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].productQty" cssClass="redstar"/> 
                                    </td>
                                        <td width="15%">&nbsp;
                    <form:select id="sel4" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].uom" items="${icbfn:dropdown('staticDropdown',UOM)}" itemLabel="name" itemValue="value" disabled="true"/>                                                                              
                                        </td>
                                        <td width="13%">&nbsp;
                                        <form:select id="sel5" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].option" items="${icbfn:dropdown('staticDropdown',option)}" itemLabel="name" itemValue="value" disabled="true"/>                                                                                                         
                                        </td>
                                        <td width="7%">&nbsp;
                                        <form:select id="sel6" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].plusMinus" items="${icbfn:dropdown('staticDropdown',plusMinus)}" itemLabel="name" itemValue="value" disabled="true"/>                                                                                                                                      
                                        </td>
                                        <td width="7%">
                                        <form:input cssClass="inputBox" cssStyle="width:20px;" size="3" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].plusMinusPct" disabled="true"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].plusMinusPct" cssClass="redstar"/>                                         
                                          %
                                        </td>
                                      <td width="18%">
                                        <form:input cssClass="inputBox"  size="10" maxlength="30"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].drafts" disabled="true"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].drafts" cssClass="redstar"/>                                                                                                                    
                                    </td>
                                    <td width="12%">
                                    <form:input cssClass="inputBox" size="10" maxlength="4"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].tanks" disabled="true"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].tanks" cssClass="redstar"/>                                                                         
                                    </td>
                                          <td style="text-align:right; border-right:none;">
                                          <div id="div19">&nbsp;
                                          </div>
                                          </td>
                                        </tr>
                                        
                                        </c:forEach>
                                      </table>
                                    </div>
                                  </div>
                                  </c:if>
                  <!-- ----------------QUANTITY CONTAINER V1 END ----------------------- -->
                  
                                  <!-- ---------------------INSPECTION CONTAINER V1 ------------------------ -->
                  
                                  <div id="inspectionContainerv${vesselCounter.index }p${productCounter.index }" class="inspectionContainer">
                                    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" style="border:none;">
                                      <tr >
                                        <th width="13" style="background-color:#f2f1f1;" > <div id="bluarrowrightv${vesselCounter.index }p${productCounter.index }in1" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showvpinspectionTable(${vesselCounter.index },${productCounter.index });"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="4"/></a> </div>
                                          <div id="bluarrowdownv${vesselCounter.index }p${productCounter.index }in1" style="visibility:visible;position:relative; z-index: 1; margin-top:5px "> <a href="#" onClick="javascript:hidevpinspectionTable(${vesselCounter.index },${productCounter.index });"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="4"/></a> </div></th>
                                        <td width="30%" style="background-color:#f2f1f1;" > Events</td>
                                        <td width="50%" style="background-color:#f2f1f1;" > Event Instructions</td>
                                        <td width="10%" style="background-color:#f2f1f1; text-align:center;" nowrap>&nbsp;&nbsp;Sort#</td>
                                        <td width="25" style="border-right:none;text-align:center;">
                                        <div id="div19" style="width:25px; text-align:right;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                         
                                        
                                        </div>
                                        </td>
                                      </tr>
                                    </table>
                                    <div id="inspectiontablev${vesselCounter.index }p${productCounter.index }" style="visibility:visible; display:block;width:100%">
                                    
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" style="border:none;">
                                      <c:forEach items="${arrJobProd.jobEvents}" var="arrJobEvents" varStatus="eventCounter">   
                                        <tr >
                                          <!-- <td width="13" rowspan="3" >
                                          <img src="images/spacer.gif" width="18" height="1" />
                                          </td> -->
                                          <td width="30%"><span class="redstar">*</span>                                          
                                          <form:select id="sel7" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobEvents[${eventCounter.index}].event.eventCode" items="${icbfn:dropdown('events', operationCode)}" itemLabel="name" itemValue="value" onchange="populateInstructions(${vesselCounter.index },${productCounter.index},${eventCounter.index },'instr')" disabled="true"/>                                                                                                                                      
                                          </td>
                                          <td width="50%" >
                                          <form:input cssClass="inputBox" size="60" maxlength="200"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobEvents[${eventCounter.index}].eventInstructions" disabled="true"/>
                      <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobEvents[${eventCounter.index}].eventInstructions" cssClass="redstar"/>        
                                      </td>
                                          <td width="10%" style="text-align:center">
                                          <form:input cssClass="inputBox" cssStyle="width:13px; text-align:right" size="2" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobEvents[${eventCounter.index}].sortOrder" disabled="true"/>
                      <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobEvents[${eventCounter.index}].sortOrder" cssClass="redstar"/>                                                  
                                          </td>
                                          <td width="5%" style="text-align:right; border-right:none;">
                                          <div id="div19">
                                          &nbsp;</div>
                                          </td>
                                        </tr>
 
                                        </c:forEach>
 
                                      </table>
                                    </div>
                                  </div>
                                  <!-- -----------------Inspection Table V1 END ---------------------- -->
                  
                                  
                  <!-- ---------------- Sample Location Container Table V1 --------------------- -->
                  
                                  <div id="samplelocContainerv${vesselCounter.index }p${productCounter.index }" class="samplelocContainer" style="visibility:visible;">
                                    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" style="border:none;">
                                      <tr >
                                        <th width="3%" style="background-color:#f2f1f1;"> <div id="bluarrowrightv${vesselCounter.index }p${productCounter.index }s1" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showvpsampleTable(${vesselCounter.index },${productCounter.index },${fn:length(arrJobProd.addJobProdSamples)});"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv${vesselCounter.index }p${productCounter.index }s1" style="visibility:visible;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidevpsampleTable(${vesselCounter.index },${productCounter.index },${fn:length(arrJobProd.addJobProdSamples)});"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <td width="8%" style="background-color:#f2f1f1;" >Sample Loc</td>
                                        <td width="15%" align="center" style="background-color:#f2f1f1;" >&nbsp;&nbsp;&nbsp;Samp#</td>
                                        <td width="15%" align="left" style="background-color:#f2f1f1;" >Tank#</td>
                                        <td width="8%" align="left" style="background-color:#f2f1f1;" >Sample&nbsp;&nbsp;&nbsp;&nbsp; Timing</td>
                                        <td width="8%"  align="center" style="background-color:#f2f1f1;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Samples &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type </td>
                                        <td width="12%" align="center" style="background-color:#f2f1f1;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sample Vol.</td>
                                        <td width="10%" align="left" style="background-color:#f2f1f1;" >&nbsp;&nbsp; &nbsp;&nbsp;Container &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; Type</td>
                                        <td width="10%" align="center" style="background-color:#f2f1f1;" >Retain/ Tested</td>
                                        <td width="9%" align="left" style="background-color:#f2f1f1;" nowrap >&nbsp;Sort#</td>
                                        <td width="1%" style="background-color:#f2f1f1;" >&nbsp;
                                        </td>
                                      </tr>
                    </table>

                    <div id="sampleloctablev${vesselCounter.index }p${productCounter.index }" style="visibility:visible; display:block;width:100%">
                                    
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" style="border:none;">
                                     
                                      <c:forEach items="${arrJobProd.addJobProdSamples}" var="arrAddJobProdSamples" varStatus="sampleCounter">   
                                      <tr >
                      <td width="8%"><span class="redstar">*</span>&nbsp;&nbsp;
                                       <form:select id="sel8" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.jobSampleLocation" items="${icbfn:dropdown('staticDropdown',sampleLocation)}" itemLabel="name" itemValue="value" disabled="true"/>
                                        </td>
                                        <td width="15%" align="left">
                                        <form:input cssClass="inputBox"  size="5" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleNumber" disabled="true"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleNumber" cssClass="redstar"/>                                                                                        
                                        </td>
                                    <td  width="15%">
                                        <form:input cssClass="inputBox" cssStyle="text-align:right;" size="5" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.tankNumber" maxlength="64" disabled="true"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.tankNumber" cssClass="redstar"/>    
                    </td>
                                        <td  width="10%" align="center">
                                        <form:select id="sel9" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleTiming" items="${icbfn:dropdown('staticDropdown',sampleTiming)}" itemLabel="name" itemValue="value" disabled="true"/>                         
                                        </td>
                                       <td  width="8%" align="left">
                                        <form:select id="sel10" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleType" items="${icbfn:dropdown('staticDropdown',sampleType)}" itemLabel="name" itemValue="value" disabled="true"/>                                                  
                                        </td>
                                       <td  width="10%">
                                        <form:select id="sel11" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleVolume" items="${icbfn:dropdown('staticDropdown',sampleVolume)}" itemLabel="name" itemValue="value" disabled="true"/>                                                                        
                                        </td>
                                       <td  width="8%"  align="right" >
                                        <form:select id="sel12" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.containerType" items="${icbfn:dropdown('staticDropdown',containerType)}" itemLabel="name" itemValue="value" disabled="true"/>                                                                                         
                                        </td>
                                        <td align="right"  width="10%">&nbsp;&nbsp;&nbsp;&nbsp;
                                        <form:select id="sel13" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.retainTested" items="${icbfn:dropdown('staticDropdown',retainTested)}" itemLabel="name" itemValue="value" onchange="setLabAnalysisFlag()" disabled="true"/>                                                                                                                 
                                        </td>
                                        <td style="text-align:right"  width="5%">
                                        <form:input cssClass="inputBox" cssStyle="width:13px; text-align:right" size="2" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sortOrder" disabled="true"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sortOrder" cssClass="redstar"/>                                                                             
                                        </td>
                                     <td>
                                        <div id="div22" style="width:29px; text-align:left;"> 
                                        &nbsp;     
                              &nbsp;
                                        </div>
                                        </td>
                                      </tr>
                                       <tr >
                                        <td colspan="10" ><div align="center" id="sampleloctablev${vesselCounter.index}p${productCounter.index}s${sampleCounter.index }" style="visibility:visible; display:block;">
                                            <table  border="0" cellpadding="0" cellspacing="0" style="width:100%; border:none;">
                                              <tr>
                                                <td>&nbsp; </td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;&nbsp;</td>
                                                <td>&nbsp;&nbsp;</td>
                                                <td width="2%"><div align="right"></div></td>
                                              </tr>
                                              
                                              <c:forEach items="${arrAddJobProdSamples.jobTests}" var="arrJobTests" varStatus="jobTestCounter">   
                                              <tr>
                                              <td width="19%">Test ${jobTestCounter.index +1 }:</td>
                                              <td width="25%">
                                              <form:input cssClass="inputBox" size="20" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].test.testId" disabled="true"/>
                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].test.testId" cssClass="redstar"/>                              
                                              </td>
                                              <td width="34%">
                                              <form:input cssClass="inputBox" size="50" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].test.testDescription" disabled="true"/>
                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].test.testDescription" cssClass="redstar"/>                             
                                        </td>
                                        <td width="10%">&nbsp;&nbsp;Qty.&nbsp;&nbsp;
                                        <form:input cssClass="inputBox" size="4" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].quantity" maxlength="8" disabled="true"/>
                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].quantity" cssClass="redstar"/>          
                                        </td>
                                        <td width="10%">OT
                                        <form:select id="sel14" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].ot" items="${icbfn:dropdown('staticDropdown',yesNo)}" itemLabel="name" itemValue="value" onchange="setOtApprovedFlag()" disabled="true"/>                                                                                                                 
                                        &nbsp;&nbsp;                                                </td>
                                        <td width="2%" > <div align="right">
                                                  &nbsp;</div></td>
                                              </tr>
                                              </c:forEach>
                                              <!-- 
                                              <tr>
                                                <td width="19%">Test1:</td>
                                                <td width="25%"><input name="RO_HEADER_LOCATION2142"  class="inputBox" 
                                id="RO_HEADER_LOCATION2142" value="ASTMD1839"  size="20"/>                                                </td>
                                                <td width="35%"><input name="RO_HEADER_LOCATION232"  class="inputBox" 
                                id="RO_HEADER_LOCATION232" value="Amyl Nitrate in Diesel Fuels"  size="50"/></td>
                                                <td width="19%">OT
                                                  <select id="sel3" name="sel3" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>&nbsp;&nbsp;                                                </td>
                                                <td width="2%" > <div align="right">
                                                  <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>Test2:</td>
                                                <td><input name="RO_HEADER_LOCATION2152"  class="inputBox" 
                                id="RO_HEADER_LOCATION2152" value="ASTMD5186"  size="20"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION23"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="Aromatics in Diesel by SFC"  size="50"/>                                                </td>
                                                <td>OT
                                                  <select id="sel3" name="sel3" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>&nbsp;&nbsp;                                                </td>
                                                <td ><div align="right">
                                                  <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>State1:</td>
                                                <td><input name="RO_HEADER_LOCATION2162"  class="inputBox" 
                                id="RO_HEADER_LOCATION2162" value="BPPRODF1"  size="20"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION23"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="F1 - Distillates - CARB Diesel"  size="50"/>                                                </td>
                                                <td>OT
                                                  <select id="sel3" name="sel3" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>&nbsp;&nbsp;                         </td>
                                                <td><div align="right">
                                                  <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0"/></a></div></td>
                                              </tr> -->
                                              <c:forEach items="${arrAddJobProdSamples.jobSlates}" var="arrJobSlates" varStatus="jobSlateCounter">   
                                              <tr>
                                              <td width="19%">Slate ${jobSlateCounter.index +1 }:</td>
                                              <td width="25%">
                                              <form:input cssClass="inputBox" size="20" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].slate.slateId" disabled="true"/>
                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].slate.slateId" cssClass="redstar"/>                              
                                              </td>
                                              <td width="34%">
                                              <form:input cssClass="inputBox" size="50" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].slate.slateDescription" disabled="true"/>
                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].slate.slateDescription" cssClass="redstar"/>                             
                                        </td>
                                        <td width="10%">&nbsp;&nbsp;Qty.&nbsp;&nbsp;
                                        <form:input cssClass="inputBox" size="4" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].quantity" maxlength="8" disabled="true"/>
                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].quantity" cssClass="redstar"/>          
                                        </td>
                                        <td width="10%">OT
                                        <form:select id="sel14" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].ot" items="${icbfn:dropdown('staticDropdown',yesNo)}" itemLabel="name" itemValue="value" onchange="setOtApprovedFlag()" disabled="true"/>                                                                                                                 
                                        &nbsp;&nbsp;                                                </td>
                                        <td width="2%" > <div align="right">
                                                  &nbsp;</div></td>
                                              </tr>
                                              </c:forEach>                                              
                                              <tr>
                                                <td colspan="5"><table  border="0" cellpadding="0" cellspacing="0" style="width:100%">
                                                    <tr>
                                                      <td width="10%" valign="top" style="border-bottom:none">Sample Instructions: </td>
                                                      <td colspan="4" width="85%" style="border-bottom:none; border-right:none;"><label>
                                                       <form:textarea cols="90" rows="4"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleInstructions" disabled="true"/>
                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleInstructions" cssClass="redstar"/>
                                                        
                                                        </label></td>
                                                    </tr>
                                                  </table></td>
                                              </tr>
                                            </table>
                                          </div></td>
                                      </tr>



                               </c:forEach>
                                    </table>
                                  </div>
                                  <!-- ----------------SAMPLE LOCATIONS TABLE V1 END -------------- -->                               </td>
                              </tr>
                            </table>
              
                </td>
                </tr>
<!-- --------------------------- Copy Product Pop ------------------------------------------------- -->


<div class="sample_popup" id="copyprod${vesselCounter.index}${productCounter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="copyprod_drag${vesselCounter.index}${productCounter.index}" style="width:475px; "> 
  <img class="menu_form_exit"   id="copyprod_exit${vesselCounter.index}${productCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Copy Product </div>
  <div class="menu_form_body" id="copyprodbody${vesselCounter.index}${productCounter.index}"   style="width:475px; height:140px;z-index:10">
      <iframe align="left" frameborder="0" style="padding:0px; height:80px;" width="100%" src="copy_product_popup.htm?searchForm=jobOrderViewOpInstrForm&vesselIndex=${vesselCounter.index }&productIndex=${productCounter.index }&vesselCount=${fn:length(command.addJobVessels)}" scrolling="no" id="frame1" name="frmcopyprod01" allowtransparency="yes" >
      </iframe>
</div>
</div>


<!-- --------------------------------- Copy Product Pop END ----------------------------------------- -->

                
                </c:forEach>
                
              </table>

                          </div>
                          <!-- -------------------VESSEL 1 PRODUCT 1 END --------------------- -->
                          
              
                
                        </div><!-- ----VESSEL 1 Data END ---------- -->
            
                      </div><!-- ---------- VESSEL 1 Container END ---------- -->
            
                    </td>
                  </tr>
<!-- --------------------------- Search Vessel Popup ------------------------------------------------- -->
<div class="sample_popup" id="vesselsearch${vesselCounter.index}" style="visibility: hidden; display: none; z-index:2;">
<div class="menu_form_header" id="vesselsearch_drag${vesselCounter.index}" style="width:450px;height:auto;z-index:2; "> 
<img class="menu_form_exit"   id="vesselsearch_exit${vesselCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Vessel</div>                                                            
<div class="menu_form_body" id="vesselsearchbody${vesselCounter.index}" style="width:450px; height:300px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="300px;" width="100%" src="search_vessel_popup.htm?inputFieldId=addJobVessels[${vesselCounter.index}].jobVessel.vesselName&rowNum=${vesselCounter.index}&searchForm=jobOrderViewOpInstrForm" scrolling="auto" id="searchcontractFr" name="searchcontractFr" allowtransparency="yes" ></iframe>
</div>
</div>
<!-- --------------------------- Search Vessel Popup ------------------------------------------------- -->
                </c:forEach>  
                  
                  
                  
                  
               </table>

                 
                </tbody>
              </table>
              <!-- ------------------------ INSTRUCTIONS -------------------------------------------------- -->
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
                <tr>
                  <th width="30" style="background-image:url(images/arrowblubg.gif);"> <div id="bluarrowrightins" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#instructions" onClick="javascript:showInstructions();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4" style="margin-top:2px;"/></a> </div>
                    <div id="bluarrowdownins" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#instructions" onClick="javascript:hideInstructions();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                  <th width="97%"><a name="instructions"></a>Instructions</th>
                </tr>
                <tr>
                  <td colspan="2" style="padding:0px;"><div id="instructionsContainer">
                      <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="50%">Inspection   Instructions:</td>
                          <td width="50%">Sample   Instructions:</td>
                        </tr>
                        <tr>
                          <td><label>
                         <form:textarea cols="60" rows="4" path="jobOrder.inspInstructions" disabled="true"/>
             <form:errors path="jobOrder.inspInstructions" cssClass="redstar"/>                                                                        
                          </label></td>
                          
                          <td>
                          <form:textarea cols="60" rows="4" path="jobOrder.sampInstructions" disabled="true"/>
              <form:errors path="jobOrder.sampInstructions" cssClass="redstar"/>  
                          </td>
                        </tr>
                        <tr>
                          <td colspan="2">
                          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:0px;">
                              <tr>
                                <th colspan="6"> Laboratory Events </th>
                              </tr>
                              <tr>
                                <td>Retain   Period:</td>
                                <td><label>
                                <form:input cssClass="inputBox" size="4" path="jobOrder.retainPeriod" maxlength="4" disabled="true"/>
                <form:errors path="jobOrder.retainPeriod" cssClass="redstar"/>                                               
                                  days</label></td>
                                <td><label>
                                <c:choose> 
                          <c:when test="${command.jobOrder.labAnalysis}">  
                          <form:checkbox id="labAnalysis" path="jobOrder.labAnalysis"  disabled="true" />
                          </c:when>
                          <c:otherwise> 
                          <form:checkbox id="labAnalysis" path="jobOrder.labAnalysis"  disabled="true" />
                          </c:otherwise>
                          </c:choose>
            
                                
                <form:errors path="jobOrder.labAnalysis" cssClass="redstar"/>
                                  Lab Analysis </label></td>
                                <td>
                                
                                <c:choose> 
                          <c:when test="${command.jobOrder.otApproved}">  
                          <form:checkbox id="otApproved" path="jobOrder.otApproved"  disabled="true" />
                          </c:when>
                          <c:otherwise> 
                          <form:checkbox id="otApproved" path="jobOrder.otApproved"  disabled="true" />
                          </c:otherwise>
                          </c:choose>
                          
                                
                <form:errors path="jobOrder.otApproved" cssClass="redstar"/>
                                  OT Approved </td>
                                <td style="border-left:#CCCCCC solid 2px;">OT Approved   By:</td>
                                <td><label>
                                  <form:select id="sel1" cssClass="selectionBox" path="jobOrder.otApprovedby" items="${icbfn:dropdown('scheduler', jobDetail)}" itemLabel="name" itemValue="value" disabled="true"/>
                             <form:errors path="jobOrder.jobStatus" cssClass="error"/>

                                  </label></td>
                              </tr>
                            </table></td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>Lab   Instructions:</td>
                          <td>Shipping   Instructions:</td>
                        </tr>
                        <tr>
                          <td>
                          <form:textarea cols="60" rows="4" path="jobOrder.labInstructions" disabled="true"/>
              <form:errors path="jobOrder.labInstructions" cssClass="redstar"/> 
                          </td>
                          <td>
                          <form:textarea cols="60" rows="4" path="jobOrder.shipInstructions" disabled="true"/>
              <form:errors path="jobOrder.shipInstructions" cssClass="redstar"/>  
                          </td>
                        </tr>
                        <tr>
                          <td>Reporting   Instructions:</td>
                          <td>Billing   Instructions:</td>
                        </tr>
                        <tr>
                          <td>
                          <form:textarea cols="60" rows="4" path="jobOrder.reptInstructions" disabled="true"/>
              <form:errors path="jobOrder.reptInstructions" cssClass="redstar"/>  
                          </td>
                          <td>
                          <form:textarea cols="60" rows="4" path="jobOrder.billInstructions" disabled="true"/>
              <form:errors path="jobOrder.billInstructions" cssClass="redstar"/>
                          </td>
                        </tr>
                        <tr>
                          <td>Other   Instructions:</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>
                          <form:textarea cols="60" rows="4" path="jobOrder.otherInstructions" disabled="true"/>
              <form:errors path="jobOrder.otherInstructions" cssClass="redstar"/>
                          </td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td colspan="2"><!--<table border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:0px;">
            <tr>
              <td width="12%">Revision: 0 </td>
              <td width="38%">Date/Time: 
      <div id="datetime" style="visibility:hidden; position:absolute; font-weight:normal;">
      <script>

      /*Current date script credit*/
      
      var mydate=new Date()
      var year=mydate.getYear()
      if (year < 1000)
      year+=1900
      var day=mydate.getDay()
      var month=mydate.getMonth()+1
      if (month<10)
      month="0"+month
      var daym=mydate.getDate()
      if (daym<10)
      daym="0"+daym
      document.write("<font color='000000' face='Arial'>"+month+"/"+daym+"/"+year+"</font>")
      </script>
        <script language="javascript" src="js/clock.js"></script>&nbsp;EDT 
        </div>
        
                </td>
              <td width="20%">Revision   Notes:</td>
              <td width="30%"><textarea name="textarea8" cols="50" rows="2"></textarea></td>
            </tr>
            <tr>
              <td height="40" colspan="4" style="padding-left:5px;"><input name="Button" type="button" class="button1" value="New Revision"  onClick="showdatetime();"/></td>
            </tr>
        </table>--></td>
                        </tr>
                      </table>
                    </div></td>
                </tr>
              </table>
              <!-- --------------------------------------- INSTRUCTIONS END -------------------------------------- -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
		<tr>
			<td>
				<table>
					<tr>
						<td class="TDShade"><strong><f:message key="projectNumber" />:</strong></td>
						<td class="TDShadeBlue"><form:input path="phxProjectNumber" cssClass="inputBox" disabled="true" /> <form:errors
							path="projectId" cssClass="redstar" /></td>
						<td class="TDShade">&nbsp;</td>
						<c:if test="${command.allowCreateUpdateProject}">
							<td class="TDShadeBlue">
								<input name="Submit2" type="button" class="button1" value="Create Project"
								onClick="createOCAProject(this.form);">
							</td>
						</c:if>
					</tr>
				</table>
			</td>
		</tr>
	</table>

              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td align="right"></td>
                        <td style="text-align:right"><a href="#"><img src="images/ico_print.gif" alt="Print Job Order" width="18" height="16" hspace="2" border="0" align="absmiddle" title="Print Job Order"></a><a href="#"><img src="images/icoship.gif" alt="Send To SAM" hspace="2" border="0" align="absmiddle" title="Send To SAM"></a><a href="#"><img src="images/icoflask.gif" alt="Send To LIMS" hspace="2" border="0" align="absmiddle" title="Send To LIMS"></a><a href="#" onclick="goToNextPage()"><img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#"><img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle" onclick="javascript:document.jobOrderViewOpInstrForm.submit();"/></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
          </div>
        </div>
        <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", 0)
      
      </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
        <table width="100%" cellspacing="0">
          <tr>
            <td height="24" style="text-align:right; padding-right:0px;"><select name="sel5" id="sel5" class="SelectionBox" onChange="MM_jumpMenu('parent',this,1)">
                <option selected>Go to ... &gt;</option>
               <option value="view_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="entry"/></option>
              </select>
            </td>
          </tr>
        </table>
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
 
<!-- --------------------------- Integration Log Popup ------------------------------------------------- -->
<div class="sample_popup" id="integrationlog" style="visibility: hidden; display: none; z-index:2;">
  <div class="menu_form_header" id="integrationlog_drag" style="width:625px; z-index:2; "> 
  <img class="menu_form_exit"   id="integrationlog_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Integration Log</div>
    <div class="menu_form_body" style="width:625px; height:auto;">
    <form method="post" action="popup.php">
<table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:15px;">
  <tbody>
    
    <tr>
      <th class="TDShade">Integration Type</th>
      <th class="TDShade">Integration User</th>
      <th class="TDShadeBlue">Date Time</th>
      <th class="TDShadeBlue">Integration Status</th>
      <th class="TDShadeBlue" width="20%">Error Message</th>
    </tr>
    <tr>
      <td class="TDShade">LIMS</td>
      <td class="TDShade">TBASELICE</td>
      <td class="TDShadeBlue">09/13/2007 <br>8:18PM EDT</td>
      <td class="TDShadeBlue">SUCCESS</td>
    <td class="TDShadeBlue">&nbsp;</td>
    </tr>
    
    <tr>
      <td colspan="3" class="TDShade">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3" class="TDShade"><input name="ok" type="button" class="button1" id="ok" onClick="hideintegrationlog();popupboxclose();" value="Ok"></td>
      </tr>
  </tbody>
</table>
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Integration Log Popup END ----------------------------------------- -->
<!-- --------------------------- Search Test Popup ------------------------------------------------- -->
<div class="sample_popup" id="test" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="test_drag" style="width:850px;"> 
<img class="menu_form_exit"   id="test_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Tests</div>                                                           
<div class="menu_form_body" id="testbody" style="width:850px; height:auto;">
<iframe id="searchtestpopup"  width="99%" height="1px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>

</div>
</div>
<!-- --------------------------- Search Test Popup End------------------------------------------------- -->       

<!-- --------------------------- Add Slates Lookup ------------------------------------------------- -->
<div class="sample_popup" id="slate" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="slate_drag" style="width:850px; "> 
  <img class="menu_form_exit"   id="slate_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Slates </div>
  <div class="menu_form_body" id="slatebody"   style="width:850px; height:auto;">
  <iframe id="searchslatepopup"  width="99%" height="1px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>

</div>
</div>
<!-- --------------------------------- Add Slates Lookup END ----------------------------------------- -->

<!-- --------------------------- Add Tests Lookup ------------------------------------------------- -->
<div class="sample_popup" id="tests" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="tests_drag" style="width:750px; "> <img class="menu_form_exit"   id="tests_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Tests </div>
  <div class="menu_form_body" id="testsbody"   style="width:750px; height:auto;">
    <form method="post" action="../setup/popup.php">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
        <tr>
          <td width="25%"><strong>Select Group : </strong></td>
          <td width="100"><select name="sel1" id="select" class="selectionBox">
                  <option selected>PRD GRP - Residual Fuels</option>
                </select>&nbsp;</td>
          <td>
            <INPUT TYPE="radio" NAME="r1">
          Pricebook&nbsp;</td>
          <td><INPUT TYPE="radio" NAME="r1">
Contract&nbsp;&nbsp;</td>
          <td><INPUT TYPE="radio" NAME="r1">
Both</td>
        </tr>
        <tr>
          <td><strong>Search For : </strong></td>
          <td><input name="textfield2" type="text" class="inputBox" size="25" value="ASTMD44" />&nbsp;&nbsp;in&nbsp;&nbsp;</td>
          <td><INPUT TYPE="radio" NAME="r2">
          Methodology&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td><INPUT TYPE="radio" NAME="r2">
Description</td>
          <td><INPUT TYPE="radio" NAME="r2">
Both</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="5"><input name="Button" type="button" class="button1" value="Search" onClick="searchtests();"/>
            &nbsp;&nbsp;
            <input name="Button" type="button" class="button1" value="Cancel" onClick="hidetests();popupboxclose();" /></td>
        </tr>
      </table>
       
    <div id="testssearchresults" style="visibility:hidden; display:none;"><!--Search Results -->
    <br>&nbsp;&nbsp;<strong>Search Results</strong>
    <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%; margin-bottom:15px;">
        <tr>
          <th>Methodology</th>
          <th>Description</th>
      <th>Quantity</th>
      <th>PB/CONT</th>
          </tr>
        <tr>
          <td colspan="4"><a href="#">Check all</a>&nbsp;&nbsp;<a href="#">Clear all</a></td>
          </tr>
    <tr>
          <td><INPUT TYPE="checkbox" NAME="chk1">ASTMD445</td>
          <td>Viscosity - Kinematic at 40°C(104°F), 50°C(122°F), 100°C(212°F), 98.9°C(210°F), 37.8°C(100°F)</td>
      <td><input name="Input243" class="inputBox" style="width:50px;" value="1"  maxlength="2"/></td>
      <td>PB07</td>
          </tr>
        <tr>
          <td><INPUT TYPE="checkbox" NAME="chk1">ASTMD445_other</td>
          <td>Viscosity - Kinematic at non-routine Temperatures</td>
      <td><input name="Input243" class="inputBox" style="width:50px;" value="1"  maxlength="2"/></td>
      <td>PB07</td>
          </tr>
        <tr>
          <td><INPUT TYPE="checkbox" NAME="chk1">ASTMD445_sayb</td>
          <td>Vescosity - Saybolt</td>
      <td><input name="Input243" class="inputBox" style="width:50px;" value="1"  maxlength="2"/></td>
      <td>PB07</td>
          </tr>
        <tr>
          <td colspan="4" height="26">Select an action :</td>
          </tr>
    <tr>
          <td colspan="4"><input name="Button" type="button" class="button1" value="Add Test" />&nbsp;&nbsp;<input name="Button" type="button" class="button1" value="Add and Go To Summary" />&nbsp;&nbsp;<a href="#">Add Manual Test</a></td>
          </tr>
      </table>
    </div><!--Search Results -->
      
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Add Tests Lookup END ----------------------------------------- -->


<div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>

      
    
   </td>
  </tr>
</table> 
      </form:form>      