<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script language="javascript" src="js/ce/ce_invoice_preview.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="MM_preloadImages('images/tab1Pricing_deact.gif','images/tab2Tax_act.gif','images/tab2Tax_deact.gif','images/tab3TestnService_act.gif','images/tab3TestnService_deact.gif','images/tab1Pricing_act.gif','images/tab4Viewall_deact.gif','images/tab4Viewall_act.gif');">
  
   <div id="breadcrumbContainer" >
      <table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/intopbluetrailbg.jpg">
         <tr>
            <td width="25"><img src="images/inlfttrailcorner.gif" width="8" height="22"></td>
            <td> 
                <jsp:include page="/WEB-INF/views/phoenix/job/ce_job_bread_crumb.jsp">
			<jsp:param name="jobNumber" value="${command.jobOrder.jobNumber}" />
			<jsp:param name="pageNumber" value="${command.jobOrder.pageNumber}" />
		</jsp:include></td>
          </tr>
        </table>
      </div>
      <!-- BREADCRUMB TRAIL END -->
 
  <div id="MainContentContainer">
  <!-- TABS CONTENTS -->
  <div id="tabcontainer">
    <div id="tabnav">
      <ul>
	    <li><a href="#" onClick="navdisable();" rel="tab1"><span><f:message key="ceGlobal"/></span></a></li>
      </ul>
	 <div align="right">
	  <table cellspacing="0" cellpadding="0" border="0"><tr>
			<td><select name="sel5" id="sel5" class="SelectionBox" class="jumpmenusel" onChange="MM_jumpMenu('parent',this,1)">
              <option selected>Go to ... &gt;</option>
              <option value="phx_job_entry_ce.htm?jobNumber=${command.jobNumber}">Entry</option>
			  <option value="phx_job_operational_info_ce.htm?jobNumber=${command.jobNumber}">Job Instructions</option>
			  <option value="phx_ce_job_select_charges.htm?jobNumber=${command.jobNumber}">Select Charges</option>
              <option>Notes</option>
              <option>History</option>
            </select></td>
	  </tr></table>
	 </div>
    </div>
    <!-- Sub Menus container. Do not remove -->
    <div id="tab_inner1">
      <form:form name="ceInvoicePreviewForm" method="post" action="phx_ce_job_invoice_preview.htm">

	<div class="redtext">
	  <form:errors cssClass="error"/><c:if test="${command.errorMessgage!=null}"><f:message key="noline.error"/></c:if>
	</div>		
	
	<form:hidden path="refreshing" />
	<form:hidden path="tabSource"/>
	<form:hidden path="invLineItemIndex"/>
	<form:hidden path="splitLineItemIndex"/>
	
        <!-- ---------------------------------------------------------------------------------------------------------------------- -->
        <!-- ------------------------- TAB 1 CONTAINER ------------------------------- -->
        <div id="tab1" class="innercontent1" >

<!-- Invoice Preview Table Start -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tr>
    <th colspan="3"><f:message key="invoicePreview"/>&nbsp;&nbsp;
                    	<img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
                    	&nbsp;&nbsp;<f:message key="jobId"/>: ${command.jobNumber}&nbsp;&nbsp;</th>
    <th class="tdr"> 
        <a href="#a" onclick="doMySubmit('calc');">
        <img src="images/icocalculate.gif" alt="Calculate Invoice" width="14" height="14" border="0" align="absmiddle"/></a> 
	    <a href="#">	    
	    	<img src="images/savennextbluarrow.gif"  onClick="saveNext()" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#">
	    	<img src="images/icosave.gif" onClick="saveip()" alt="Save" width="14" height="14" border="0" align="absmiddle"/></a>
	</th>
  </tr>
  <tr>
    <td width="15%" class="TDShade"><f:message key="billingContact"/>:</td>
    <td width="30%" class="TDShadeBlue"><strong>${command.billingContactName}</strong></td>
    <td width="15%" class="TDShade"><f:message key="invoiceType"/>: </td>
    <td width="30%" class="TDShadeBlue">
       <form:select id="sel1" cssClass="selectionBox"  path="invGenerationType"
            items="${command.invGenerationTypes}" itemLabel="name" itemValue="value"/>
	</td>
  </tr>
  <tr>
    <td width="15%" class="TDShade"><f:message key="billingAddress"/>: </td>
    <td class="TDShadeBlue"> 
    <strong>${command.fullAddress}</strong></td>
    <td class="TDShade">&nbsp;</td>
    <td class="TDShadeBlue">&nbsp;</td>
  </tr>
  <tr>
    <td class="TDShade"><f:message key="remitTo"/>: </td>
    <td class="TDShadeBlue"><strong>${command.remitTo}</strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Acct: <strong> ${command.remitToAcct}</strong></td>
    <td class="TDShade"><f:message key="paymentterms"/>: </td>
    <td class="TDShadeBlue"><strong>${command.paymentTerm}</strong></td>
  </tr>
  <tr>
    <td class="TDShade" valign="top"><a href="#" onClick="javascript:updateInvoicePreviewDescription('${command.jobOrder.description}')"><f:message key="description"/></a>: </td>
    <td class="TDShadeBlue" colspan="3">
    <div>
    <form:textarea id="jobDesc" path="description" rows="5" cols="55" /> 
	<form:errors path="description" cssClass="redstar" />
	<form:textarea id="depInvDesc" path="depositInvDescription" rows="5" cols="55" />
	<form:errors path="depositInvDescription" cssClass="redstar" />
	</div>
    </td>
  </tr>
</table>
<!-- Invoice Preview Table End -->
<table width="100%" cellpadding="0" cellspacing="0">
<tr>
  <td><table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td id="t0" class="tabpadding">
      <a href="#" onClick="javascript:tab0DepositeInvoice();"><img src="images/tab0DepositInvoice_act.gif" name="tab0"  width="124" height="19" border="0" id="tab0" onClick="MM_swapImage('tab0','','images/tab0DepositInvoice_act.gif','tab1','','images/tab1Pricing_deact.gif','tab2','','images/tab2Tax_deact.gif','tab5','','images/tab5TestnService_deact.gif','tab4','','images/tab4Viewall_deact.gif',1)"></a></td>
	  <td id="t1" class="tabpadding"><a href="#" onClick="javascript:tab1Pricing();"><img src="images/tab1Pricing_deact.gif" name="tab1"  width="124" height="19" border="0" id="tab1" onClick="MM_swapImage('tab0','','images/tab0DepositInvoice_deact.gif','tab1','','images/tab1Pricing_act.gif','tab2','','images/tab2Tax_deact.gif','tab5','','images/tab5TestnService_deact.gif','tab4','','images/tab4Viewall_deact.gif',1)"></a></td>
      <td class="tabpadding"><a href="#" onClick="javascript:tab2Tax();"><img src="images/tab2Tax_deact.gif" name="tab2" width="52" height="19" border="0" id="tab2" onClick="MM_swapImage('tab0','','images/tab0DepositInvoice_deact.gif','tab1','','images/tab1Pricing_deact.gif','tab2','','images/tab2Tax_act.gif','tab5','','images/tab5TestnService_deact.gif','tab4','','images/tab4Viewall_deact.gif',1)"></a></td>
	  <td class="tabpadding"><a href="#" onClick="javascript:tab3ApplyDepositInvoice();"><img src="images/tab5TestnService_deact.gif" name="tab5" border="0" id="tab5" onClick="MM_swapImage('tab0','','images/tab0DepositInvoice_deact.gif','tab1','','images/tab1Pricing_deact.gif','tab2','','images/tab2Tax_deact.gif','tab5','','images/tab5ApplyDepositInvoice_act.gif','tab4','','images/tab4Viewall_deact.gif',1)"></a></td>
	  <td><a href="#" onClick="javascript:tab4ViewAll();"><img src="images/tab4Viewall_deact.gif" name="tab4" width="58" height="19" border="0" id="tab4" onClick="MM_swapImage('tab0','','images/tab0DepositInvoice_deact.gif','tab1','','images/tab1Pricing_deact.gif','tab2','','images/tab2Tax_deact.gif','tab5','','images/tab5TestnService_deact.gif','tab4','','images/tab4Viewall_act.gif',1)"></a></td>
    </tr>
  </table></td>
</tr>
<tr><td>
	 <iframe src="${command.tabSource}" frameborder="0" scrolling="auto" height="280" class="tabframe" width="100%" id="invpreview" name="frame1"></iframe>
	<%-- <iframe id="invpreview" name="frame1" src="${command.tabSource}" frameborder="0" scrolling="auto" class="tabframe" width="100%" height="auto"></iframe>--%>
	<%--<iframe id="invpreview" name="frame1" src="${command.tabSource}" frameborder="0" scrolling="auto" style="padding:0px; border-right: #DBE2F2 1px solid; border-left: #DBE2F2 1px solid;overflow-x:hidden;" width="100%" height="280" ></iframe>--%>
</td></tr>
</table>		
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
  <tr>
    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="10%">
            <a href="phx_ce_pdf.htm?invNum=${command.draftInvoice.invoiceNumber}" target="_blank" class="button1" style="text-decoration: none;color:black;"><f:message key="previewInvoice"/>&nbsp;</a>
        </td>
        <td>
        <div id="generateinvoice" style="visibility:hidden;display:none">
        	<input name="GenerateInv" type="button" class="button1" value="Generate Invoice" onclick="javascript:generateInvoice();" />
        </div>
        <div id="generatedepositinvoice" style="visibility:visible;display:block">
        	<c:choose>
        	<c:when test="${command.depInvoiceGenerated}">
        		<input name="GenerateDepositInv" type="button" class="button1" value="Generate Deposit Invoice" disabled="disabled" onclick="javascript:generateDepositInvoice();" />
        	</c:when>
        	<c:otherwise>
        		<input name="GenerateDepositInv" type="button" class="button1" value="Generate Deposit Invoice" onclick="javascript:generateDepositInvoice();" />
        	</c:otherwise>
        	</c:choose>
        </div>
        <td class="tdr">
           <a href="#a" onclick="doMySubmit('calc','${command.tabSource}');">
              <img src="images/icocalculate.gif" alt="Calculate Invoice" width="14" height="14" border="0" align="absmiddle"/></a>                          
        
        	<a href="#">
        		<img src="images/savennextbluarrow.gif" onClick="saveNext()" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a>
            <a href="#">
            	<img src="images/icosave.gif" onClick="saveip()"  alt="Save" width="14" height="14" border="0" align="absmiddle" /></a>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
        </div>
        <!-- ------------------------------ TAB 1 CONTAINER END --------------------------------------- -->		
		
		<!-- ---------------------------------------------------------------------------------------------------------------------- -->
      </form:form>
    </div>
  </div>
  <script type="text/javascript">
				
				//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
				dolphintabs.init("tabnav", 0)
				
				</script>
  <!-- -------------------------------- TAB CONTENT END --------------------------------------- -->
        <table width="100%" cellspacing="0">
          <tr>
            <td width="90%" height="24" align="right">
			<div id="navbuttons"></div>
            </td>
			<td height="24"><select name="bottomNavigation" id="bottomNavigation" class="SelectionBox" class="jumpmenusel" onChange="MM_jumpMenu('parent',this,1)">
              <option selected>Go to ... &gt;</option>
              <option value="phx_job_entry_ce.htm?jobNumber=${command.jobNumber}">Entry</option>
			  <option value="phx_job_operational_info_ce.htm?jobNumber=${command.jobNumber}">Job Instructions</option>
			  <option value="phx_ce_job_select_charges.htm?jobNumber=${command.jobNumber}">Select Charges</option>
              <option>Notes</option>
              <option>History</option>
            </select>
            </td>
          </tr>
        </table>
</div>


<!-- --------------------------- Apply Deposit Lookup Start ------------------------------------------------- -->

		<div class="sample_popup" id="applydeposit">
  	 		<div class="menu_form_header" id="applydeposit_drag" style="width:480px; "> 
	  			<img class="menu_form_exit"   id="applydeposit_exit" src="images/form_exit.png" border="0" /> &nbsp;&nbsp;&nbsp;Account Search 
	  		</div>
  			<div class="menu_form_body" id="applydepositbody"   style="width:480px; height:180px;">
				<iframe align="left" frameborder="0" class="nopadding" height="530px;" width="100%" scrolling="auto" id="applyDepositFrId" name="applyDepositFr" allowtransparency="yes" ></iframe>
			</div>
		</div>
<!-- --------------------------------- Apply Deposit Lookup END ----------------------------------------- -->

<jsp:include page="../../common/requiredFields.jsp" flush="true" />
<!-- --------------------------- Revenue Segregation Lookup Start ------------------------------------------------- -->

		<div class="sample_popup" id="revenuesegregation">
	   	    <div class="menu_form_header" id="revenuesegregation_drag" style="width:480px; "> 
				<img class="menu_form_exit"   id="revenuesegregation_exit" src="images/form_exit.png" border="0" /> &nbsp;&nbsp;&nbsp;Revenue Segregation 
			</div>
			<div class="menu_form_body" id="revenuesegregationbody"   style="width:480px; height:180px;">
				<iframe align="left" frameborder="0" class="nopadding" height="530px;" width="100%" src="blank.htm" scrolling="auto" id="revenueSegregationFrId" name="revenueSegregationFr" allowtransparency="yes" ></iframe>
			</div>
		</div>
<!-- --------------------------------- Revenue Segregation Lookup END ----------------------------------------- -->
<SCRIPT LANGUAGE="JavaScript">
<!--
selecttab();
//-->
</SCRIPT>
</body>
</html>
