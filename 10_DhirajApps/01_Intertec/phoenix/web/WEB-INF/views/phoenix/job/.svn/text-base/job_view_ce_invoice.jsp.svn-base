<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script type="text/javascript" src="js/ce/ce_services.js"></script>


<form:form name="jobOrderEditViewCEInvoiceForm" method="POST" action="phx_job_view_ce_invoice.htm">
<form:hidden path="creditFlag"/>
<form:hidden path="index"/>
<form:hidden path="creditReasonNote"/>
<form:hidden path="creditReasonUser"/>
<form:hidden path="creditDescription"/>
<div class="redtext"><form:errors cssClass="error" /></div>
  
<table width="97%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top"><!-- BREADCRUMB TRAIL  -->
  <!------------------------------- BREADCRUMB TRAIL  --------------------------------------------------->
      <div id="breadcrumbContainer" >
        <table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/intopbluetrailbg.jpg">
          <tr>
            <td width="25"><img src="images/inlfttrailcorner.gif" width="8" height="22"></td>
              <td><jsp:include page="ce_job_bread_crumb.jsp">
						<jsp:param name="jobNumber" value="${command.jobOrder.jobNumber}" />
						<jsp:param name="pageNumber"
							value="${command.jobOrder.pageNumber}" />
					</jsp:include></td>
        </tr>
      </table>
    </div>
  <!--------------------------------------------- BREADCRUMB TRAIL END ------------------------------------>
 <div id="MainContentContainer">
  <!----------------------------------------- TABS CONTENTS -------------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav">
           <ul>
	         <li><a href="#" onClick="navdisable();" rel="tab1"><span><f:message key="ceGlobal"/></span></a></li>
           </ul>
         
		 <div align="right">
            <table cellspacing="0" cellpadding="0" border="0">
               <tr>
                 <td>
                   <select name="sel5" id="sel5" class="SelectionBox" onChange="MM_jumpMenu('parent',this,1)">
                       <option selected>Go to ... &gt;</option>
                       <option value="phx_job_entry_ce.htm?jobNumber=${command.jobNumber}"> <f:message key="entry"/></option>
                       <option value="phx_job_operational_info_ce.htm?jobNumber=${command.jobNumber}"><f:message key="jobInstructions"/></option>
                       <option value="phx_ce_job_select_charges.htm?jobNumber=${command.jobNumber}"><f:message key="selectCharges"/></option>
                       <option value="phx_ce_job_invoice_preview.htm?jobNumber=${command.jobNumber}"><f:message key="invoicePreview"/></option>
                   </select>
                  </td>
                </tr>
              </table>
            </div>
         </div>
<!----------------------------------------------------- Sub Menus container. Do not remove ---------------------------------------------->
          <div id="tab_inner1">
 <!---------------------------------------------------------- TAB 1 CONTAINER ------------------------------------------------------------>
         <div id="tab1" class="innercontent1" >

<!---------------------------------------------------------- Invoice Preview Table Start ------------------------------------------------->

	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
	<tr>
	   <th colspan="2" class="nopadding"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="border:none;">
	      <tr>
	        <th><f:message key="invoiceSummary"/><img src="images/separator2.gif" width="2" height="27"  align="absmiddle" class="seperator"/><f:message key="jobId"/>:${command.jobNumber}</th>
	       <th>&nbsp;</th>
	        <th class="thr">&nbsp;</th>
	     </tr>
	    </table>
	  </th>
    </tr>
	
	<tr>
	  <td width="15%" class="TDShade"><f:message key="company"/>:</td>
	  <td class="TDShadeBlue">${command.customerName}</td>
	</tr>
	<tr>
	   <td class="TDShade"><f:message key="billingContact"/>:</td>
	   <td class="TDShadeBlue">${command.billingContact}</td>
	</tr>
  
  <tr>
    <td colspan="2" class="TDShadePadding"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
      <tr>
        <th width="2%"><f:message key="lineNo"/></th>
           <th width="8%"><f:message key="invoice"/></th>
		   <th>&nbsp;</th>
             <th>&nbsp;</th>
                <th width="10%"><f:message key="status"/></th>
                <th width="10%"><f:message key="type"/></th>
                  <th><f:message key="generatedOn"/></th>
                <th><f:message key="generatedBy"/></th>
		      <th width="18%"><f:message key="appliedDepositeamount"/></th>
		  <th>&nbsp;</th>
		<th width="20%">&nbsp;</th>
      </tr>
	 
	<c:forEach items="${command.ceInvoiceFormList}" var="ceInvoiceForm" varStatus="invoiceStatus">      
  
      <tr>
			<td class="TDShadeBlue">${invoiceStatus.index+1}</td>  
			 
				<td class="TDShadeBlue">${ceInvoiceForm.invoiceNumber}</td>           
							
				
				   <td align="center" class="TDShadeBlue">&nbsp;</td>
				   
				   <c:choose>
				   <c:when test="${ceInvoiceForm.invoiceFile !=null && fn:length(fn:trim(ceInvoiceForm.invoiceFile)) > 0 }">
					   <td align="center" class="TDShadeBlue"><a href="invoice_view.htm?invoice=${ceInvoiceForm.invoiceFile}&invoicetype=${ceInvoiceForm.formInvoiceType}" target="_blank" onMouseOver="doTooltip(event, '<table><tr><td><b>Line Item No.:1</b></td><td></td></tr><tr><td valign=top><b>Amount:$125</b></td> <td>&nbsp;</td></tr><tr><td><b>Total Amount:$230</b></td> <td>&nbsp;</td></tr></table>')" onMouseOut="hideTip()"><img src="images/icoviewinvoice.gif" alt="View Invoice" width="16" height="18" border="0"></a>	   
				           </td> 
						     </c:when>
						 <c:otherwise>
					  <td align="center" class="TDShadeBlue">&nbsp;</td>
					</c:otherwise>
				 </c:choose>			   
				   
				        
			 <td class="TDShadeBlue">
				<c:choose>
				   <c:when test="${ceInvoiceForm.invoiceFile !=null && fn:length(fn:trim(ceInvoiceForm.invoiceFile)) > 0 }">
					  <f:message key="success"/>
						</c:when>
						<c:otherwise>
					  <f:message key="failed"/>
					</c:otherwise>
				 </c:choose>
			</td>
			<td class="TDShadeBlue">${ceInvoiceForm.formInvoiceType}</td>
			<td class="TDShadeBlue">${ceInvoiceForm.generatedOn}</td>
			  <td class="TDShadeBlue">${ceInvoiceForm.generatedBy}</td>
				  <td class="TDShadeBlue">${ceInvoiceForm.depositAccAmount}</td>
				 


			 <td class="TDShadeBluec">
			 	<c:choose>
				   <c:when test="${(ceInvoiceForm.invoiceFile !=null && fn:length(fn:trim(ceInvoiceForm.invoiceFile)) > 0 ) && 'DepositInvoice'!=ceInvoiceForm.formInvoiceType && ceInvoiceForm.relatedInvoiceNumber =='' }">
					<c:choose>
					<c:when test="${ceInvoiceForm.status!='CREDITED'}">
					<a href="#" onClick="popCreditFlag();popCreditReason('${invoiceStatus.index }','${ceInvoiceForm.invoiceNumber}','')">Credit</a>
					</c:when>
					<c:otherwise>
					Credit
					</c:otherwise>
					</c:choose>
					</c:when>        
					<c:otherwise>
					<c:if test="${'DepositInvoice'!=ceInvoiceForm.formInvoiceType}">
				   <a href="#"><img src="images/icoviewcreditreasonnote.gif" alt="View Credit Reason Note" border="0" onClick="popCreditReason('${invoiceStatus.index }','${ceInvoiceForm.creditInvoiceNumber}','readonly')">
				  </a></c:if> 
				</c:otherwise>
				</c:choose>
			</td>

			<td class="TDShadeBlue">&nbsp;</td>
      </tr> 
	</c:forEach>
       </table>
	  </td>
    </tr>
</table>


				
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
  <tr>
    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><input name="Refresh" type="button" class="button1" id="Refresh" value="Refresh"/>
          &nbsp;&nbsp;
          <input name="Return" type="button" class="button1" id="Return" value="Return"/>
         </td>
         <td class="thr">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
 </div>
<!--------------------------------------------------------------------- TAB 1 CONTAINER END -------------------------------------->	
    </div>
  </div>
<script type="text/javascript">
dolphintabs.init("tabnav", 0)
</script>
<!-------------------------------------------------------- TAB CONTENT END ------------------------------------------------------------>
       <table width="100%" cellspacing="0" border="0">
          <tr>
            <td width="90%" height="24" align="right"><div id="navbuttons"></div></td>
              <td height="24">
                     <select name="bottomNavigation" id="bottomNavigation" class="SelectionBox" onChange="MM_jumpMenu('parent',this,1)">
                       <option selected>Go to ... &gt;</option>
                       <option value="phx_job_entry_ce.htm?jobNumber=${command.jobNumber}"> <f:message key="entry"/></option>
                       <option value="phx_job_operational_info_ce.htm?jobNumber=${command.jobNumber}"><f:message key="jobInstructions"/></option>
                       <option value="phx_ce_job_select_charges.htm?jobNumber=${command.jobNumber}"><f:message key="selectCharges"/></option>
                       <option value="phx_ce_job_invoice_preview.htm?jobNumber=${command.jobNumber}"><f:message key="invoicePreview"/></option>
                   </select>
               </td>
            </tr>
         </table>
       </div>
 <!-------------------------------------------------------- BREADCRUMB TRAIL END ---------------------------------------------------->
<!------------------------------------------------------- MAIN CONTAINER END -------------------------------------------------------->
    </td>
  </tr>
</table>
</form:form>
<!-- ----------------------------------- Credit Reason Lookup ------------------------------------------------- -->
<c:forEach items="${command.ceInvoiceFormList}" var="ceInvoiceForm" varStatus="invoiceStatus">
<div class="sample_popup" id="creditreason_${invoiceStatus.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="creditreason_drag_${invoiceStatus.index}" style="width:800px; "> 
  <img class="menu_form_exit"   id="creditreason_exit_${invoiceStatus.index}" src="images/form_exit.png"/>&nbsp;&nbsp;&nbsp;<f:message key="creditReason"/></div>
  <div class="menu_form_body" id="creditreason_${invoiceStatus.index}"   style="width:800px; height:auto">    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="creditreasonbox_${invoiceStatus.index}" width="100%" height="200px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>  
  </div>
 </div>
</c:forEach>
<!-- --------------------------------- Credit Reason Lookup END ----------------------------------------- -->
<jsp:include page="../../common/requiredFields.jsp" flush="true" />
