<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<script language="javascript">
 var invoice_array = new Array(); 

function updateParentCustomerIframeSrc(){  
 document.conslInvEditForm.busFlag.value = '0';
 document.conslInvEditForm.custFlag.value  = 'selectCustomer';
document.getElementById('searchParentCustomerFr').setAttribute("src","search_customer_popup.htm?inputFieldId=consolidatedInvoice.custCode&parentCustomerSearchFlag=n&divName1=parentcust&divName2=parentcustbody&searchForm=conslInvEditForm");
}
function updateBankAccountIframe(buname,currency){
 document.conslInvEditForm.busFlag.value = '0';
var bcode=document.getElementById("remitto").value;
document.getElementById('saccountframe').setAttribute("src","search_bank_account_popup.htm?inputFieldId=consolidatedInvoice.bankAcctKey&buName="+buname+"&currency="+currency+"&bankCode="+bcode);
}

function setCountable(invoiceFileId, countable){
    document.conslInvEditForm.invoiceFileId.value=invoiceFileId;
    if(countable == null || countable == false)
    	document.conslInvEditForm.invoiceFileCountable.value = "N";
    if(countable == true)	
    	document.conslInvEditForm.invoiceFileCountable.value = "Y";
	document.conslInvEditForm.submit();
}

function updateBankIframe(buname,currency){ 
 document.conslInvEditForm.busFlag.value = '0';
document.getElementById('sremittoframe').setAttribute("src","search_bank_popup.htm?inputFieldId=consolidatedInvoice.bankCd&buName="+buname+"&currency="+currency);
}
 function updateSearchContactIframeSrc(custCode){
  document.conslInvEditForm.busFlag.value = '1';
  document.conslInvEditForm.attentionFlag.value  = 'selectContact';
 document.getElementById('searchContactFr').setAttribute("src","search_contact_popup.htm?inputFieldId=attentionLocationNumber&searchForm=conslInvEditForm&divName=contact&divbody=contactbody&custCode="+custCode);
}
 function updateAddrSeqIframeSrc(custCode)
 {
    var div1Val="addressseq";
    var div2Val="addressseqbody";
    
     document.conslInvEditForm.busFlag.value = '1';
    document.getElementById('addressseqFr').setAttribute("src","search_address_sequence_popup.htm?inputFieldId=consolidatedInvoice.locationNumber&custCode="+custCode+"&searchForm=conslInvEditForm");
 }
 function updateBusinessUnitIframeSrc(){
  document.conslInvEditForm.busFlag.value = '0';
 document.conslInvEditForm.busFlag.value  = 'selectBU';
  document.getElementById('searchBusinessUnitFr').setAttribute("src","search_businessunit_popup.htm?inputFieldId=consolidatedInvoice.consolidatedInvoiceId.buName&searchForm=conslInvEditForm");
}



function onBillStatusChange()
{
 document.conslInvEditForm.busFlag.value = '0';
document.conslInvEditForm.billStatusFlag.value = 'selectBillStatus';
document.conslInvEditForm.submit();
}
function onCurrencyChange()
{
 document.conslInvEditForm.busFlag.value = '0';
document.conslInvEditForm.currencyFlag.value = 'selectCurrency';
document.conslInvEditForm.submit();
}
function updateDateFlag()
{
	document.conslInvEditForm.dateFlag.value  = 'selectDate';
	document.conslInvEditForm.submit();
}

function attachInvoices(invoiceCount,attachflag, vatCountryFlag, vatProvince)
{
	if(attachflag == 'attach' && vatCountryFlag !=null && vatCountryFlag == 'true' && (vatProvince == null || vatProvince=='')){
    	confirm("Please select vat province before attach");
    }else{
       for(var i=0;i<invoiceCount;i++)
       {
       		var jobContractInvoiceId = document.getElementById("chk"+i).value;
       		if(document.getElementById("chk"+i).checked)
       		{
       			var idExists = '0';
       			for(var j=0;j<invoice_array.length;j++)
				{
				        if(invoice_array[j] == jobContractInvoiceId)
				        {
				        	idExists = '1';
				        }
				}
				if(idExists == '0')
				{
					invoice_array[invoice_array.length + 1] = jobContractInvoiceId;
				}
       			document.getElementById("chk"+i).checked = false;
       		}
       }
        var invoice="";
        for(var i=0;i<invoice_array.length;i++)
          {
            if(invoice_array[i]!= undefined)
            {
              
              if(invoice == "")
                invoice = invoice_array[i];
              else
                invoice = invoice + ";" + invoice_array[i];
            }
            
          }  
          
          document.conslInvEditForm.chosenInvoices.value=invoice;
          if(attachflag == 'attach')
          	document.conslInvEditForm.attachFlag.value="attach";
          else
          	document.conslInvEditForm.detachFlag.value="detach";
          document.conslInvEditForm.submit();
   }
}

function detachInvoices(attachedInvs,invoiceCount)
{
			var invs = attachedInvs;
			confirm(invoice_array.length);
			if(invoice_array.length == 0)
				invoice_array = invs.split(';');
			
	       for(var i=0;i<invoiceCount;i++)
	       {
	       		var jobContractInvoiceId = document.getElementById("chk"+i).value;
	       		
	       		if(document.getElementById("chk"+i).checked)
	       		{
	       					var invoiceIndex;
	       					
	       			        for(var j=0;j<invoice_array.length;j++)
          					{
          						if(invoice_array[j] == jobContractInvoiceId)
						        {
						          
						          invoiceIndex = j;
						        }
          					}
          					invoice_array.splice(invoiceIndex,1);	
          					document.getElementById("chk"+i).checked = false;       			
	       		}
		   }
		   var invoice="";
	        for(var i=0;i<invoice_array.length;i++)
	          {
	            if(invoice_array[i]!= undefined)
	            {
	              
	              if(invoice == "")
	                invoice = invoice_array[i];
	              else
	                invoice = invoice + ";" + invoice_array[i];
	            }
	            
	          }  
	          
	          document.conslInvEditForm.chosenInvoices.value=invoice;
}
function onSave()
{
	document.conslInvEditForm.saveFlag.value='save';
}
function generateInvoice()
{
	document.conslInvEditForm.generateInvFlag.value  = 'generate';
	document.conslInvEditForm.submit();
}

function regenerateInvoice()
  {
    document.conslInvEditForm.regeneratePdfInvoice.value='true';
    document.conslInvEditForm.submit();
  } 
  
function checkAttachDetach(attached,vatProvince)
{
    if(attached=='true' && vatProvince != null){
    	if(vatProvince != null)
	   		document.getElementById("sel5").value=vatProvince;
	    confirm("Detach the invoices prior to changing the Vat Province");
   }else{
        document.conslInvEditForm.busFlag.value  = 'selectBU';
   	    document.conslInvEditForm.submit();
   }
}
function sortByAttachDetachHeader(orderBy){
	document.conslInvEditForm.busFlag.value='0';
	document.conslInvEditForm.custFlag.value='0';
	document.conslInvEditForm.attentionFlag.value='0';
	document.conslInvEditForm.billStatusFlag.value='0';
	document.conslInvEditForm.currencyFlag.value='0';
	document.conslInvEditForm.bankFlag.value='0';
	document.conslInvEditForm.dateFlag.value='0';
	document.conslInvEditForm.saveFlag.value='0';
	document.conslInvEditForm.attachFlag.value='0';
	document.conslInvEditForm.detachFlag.value='0';
	document.conslInvEditForm.generateInvFlag.value='0';
	document.conslInvEditForm.sortFlag.value = orderBy;
	document.conslInvEditForm.submit();
}
</script>
</head>
<icb:list var="invoiceType">
  <icb:item>invoiceType</icb:item>
</icb:list>
<icb:list var="conslInvStatus">
  <icb:item>conslInvStatus</icb:item>
</icb:list>
<form:form name="conslInvEditForm" method="POST" action="edit_consl_inv.htm" >
<c:set var="generated"  value="${!empty command.consolidatedInvoice.billStatus && command.consolidatedInvoice.billStatus == 'INV'}"/>
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="tabName" />
<form:hidden path="busFlag"/>
<form:hidden path="custFlag"/>
<form:hidden path="attentionFlag"/>
<form:hidden path="contactId"/>
<form:hidden path="billStatusFlag"/>
<form:hidden path="currencyFlag"/>
<form:hidden path="bankFlag"/>
<form:hidden path="chosenInvoices"/>
<form:hidden path="preTaxAmount"/>
<form:hidden path="dateFlag"/>
<form:hidden path="saveFlag"/>
<form:hidden path="attachedInvoices"/>
<form:hidden path="attachFlag"/>
<form:hidden path="detachFlag"/>
<form:hidden path="generateInvFlag"/>
<form:hidden path="sortFlag"/>
<input type="hidden" name="invoiceFileId" />
<input type="hidden" name="invoiceFileCountable" />
<input type="hidden" name="regeneratePdfInvoice" id="regeneratePdfInvoice"/>

<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
	
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="header"/></span></a></li>
			  <li><a href="#" rel="tab2"><span><f:message key="address"/></span></a></li>
			  <li><a href="#" rel="tab3"><span><f:message key="attachDetach"/></span></a></li>
			  <li><a href="#" rel="tab4"><span><f:message key="viewInvoice"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            
			
			<!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="50%" colspan="2"><f:message key="unit"/> : ${command.consolidatedInvoice.consolidatedInvoiceId.buName} <img src="images/separator1.gif" width="2" height="22" align="absmiddle" style="margin-left:5px; margin-right:5px;"> &nbsp;<f:message key="invoice"/> :${command.consolidatedInvoice.consolidatedInvoiceId.consolInvoiceNo} </th>
                    <th width="40%" colspan="2" align="right" ><f:message key="pretaxAmt"/> :${command.preTaxAmount}&nbsp;${command.consolidatedInvoice.currencyCd }</th>
					<th width="10%" bgcolor="#ffffff" style="text-align:right"><a href="search_consl_inv.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" <c:if test="${generated}">disabled</c:if>  onClick="onSave()" alt="Save" width="14" height="14" border="0"  /></a></th>
                  </tr>
                  <tr>
                    <td colspan="5" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
					<tr>
					<%-- <th colspan="4"><f:message key="billingControl"/></th> --%>
					<c:choose>
					<c:when test="${command.vatCountryFlag != null && command.vatCountryFlag != 'false'}">
					<th colspan="2"><f:message key="billingControl"/></th> 
					<th colspan="2">
					<f:message key="vatProvince"/>:
					<icb:list var="vatProvinceList">
					<icb:item>${command.consolidatedInvoice.consolidatedInvoiceId.buName}</icb:item>
					</icb:list>	
					<form:select  onchange="checkAttachDetach('${attached}','${command.consolidatedInvoice.vatProvince}');" cssClass="selectionBox" id="sel5" path="consolidatedInvoice.vatProvince" items="${icbfn:dropdown('vatProvince', vatProvinceList)}" itemLabel="name" itemValue="value" />
					<form:errors path="consolidatedInvoice.vatProvince" cssClass="redstar"/>
					</th>
					</c:when>
					<c:otherwise>
					<th colspan="4"><f:message key="billingControl"/></th>
					</c:otherwise>
					</c:choose>
					</tr>
					<!-- <tr>
					  <td class="TDShade" width="20%"><f:message key="unit"/> : </td>
					  <td class="TDShadeBlue">
					  <form:input cssClass="inputBox" size="14" path="consolidatedInvoice.consolidatedInvoiceId.buName"/>
                      <form:errors path="consolidatedInvoice.consolidatedInvoiceId.buName" cssClass="redstar"/>
					  
					  &nbsp;
						<a href="#" id="anchor" onClick="javascript:updateBusinessUnitIframeSrc();popup_show('bu', 'bu_drag', 'bu_exit', 'screen-corner', 120, 20); popupboxenable();hideIt();showPopupDiv('bu','bubody'); ">
						<img src="images/lookup.gif" alt="lookup Business Unit" width="13" height="13" border="0"></a>
					  </td>
					  <td class="TDShade" width="20%">&nbsp; </td>
					  <td class="TDShadeBlue">
					&nbsp;
                       </td>
					</tr>	 -->				
					<tr>
					  <td class="TDShade" width="20%"><f:message key="customer"/> : </td>
					  <td class="TDShadeBlue">
					  <form:input cssClass="inputBox" size="14" path="consolidatedInvoice.custCode" disabled="true"/>
                      <form:errors path="consolidatedInvoice.custCode" cssClass="redstar"/>
					  
					  &nbsp;
					  <c:if test="${not generated && not attached}">
					  	<a href="#" onClick="javascript:updateParentCustomerIframeSrc();popup_show('parentcust', 'parentcust_drag', 'parentcust_exit', 'screen-corner', 120, 20);popupboxenable();hideIt();showPopupDiv('parentcust','parentcustbody');">
						<img src="images/lookup.gif" alt="lookup Customer" width="13" height="13" border="0"></a>
					  </c:if>
						${command.customer.name }
					  </td>
					  <td class="TDShade" width="20%"><f:message key="currency"/> : </td>
					  <td class="TDShadeBlue">
					<form:select id="sel6" cssClass="selectionBox" path="consolidatedInvoice.currencyCd" items="${icbfn:dropdown('currency', null)}" itemLabel="name" itemValue="value" onchange="onCurrencyChange()" disabled="${generated || attached}" />
					<form:errors path="consolidatedInvoice.currencyCd" cssClass="redstar"/>
                       </td>
					</tr>
					<tr>
					  <td class="TDShade" width="20%"><f:message key="status"/> : </td>
					  <td class="TDShadeBlue">
					  <form:select cssClass="selectionBox" id ="sel1"  path="consolidatedInvoice.billStatus" items="${icbfn:dropdown('staticDropdown',conslInvStatus)}" itemLabel="name" itemValue="value" onchange="onBillStatusChange()"  disabled="${generated}"/>
					  </td>
					  <td class="TDShade" width="20%"><f:message key="invoiceType"/> : </td>
					  <td class="TDShadeBlue">${command.consolidatedInvoice.invoiceType }</td>
					</tr>
					<tr>
					  <td class="TDShade"><f:message key="type"/> : </td>
					  <td class="TDShadeBlue">
					  <select name="sel3" size="1" class="selectionBox" id="sel3" style="width:84px;" <c:if test="${generated}">disabled</c:if> >
                           <option value="" selected>CON</option>
                       </select></td>
					  <td class="TDShade"><f:message key="dateBillAdded"/> : </td>
					  <td class="TDShadeBlue"><f:formatDate value="${command.consolidatedInvoice.billAddDt}" type="date" dateStyle="short" /></td>
					</tr>

                  <tr>
                    <td colspan="5" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
					<tr>
					  <th colspan="4"><f:message key="billTo"/></th>
					</tr>
					</table>	
					
					
					</td>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td width="15%" class="TDShade"><f:message key="fromDate"/> : </td>
                    <td width="35%" class="TDShadeBlue">
         			<form:input id="fromDt" cssClass="inputBox" cssStyle="width:70px;" path="consolidatedInvoice.fromDt" onchange="updateDateFlag();" disabled="${generated}" />
                      <form:errors path="consolidatedInvoice.fromDt" cssClass="redstar"/>
                    &nbsp;<c:if test="${not generated}"><a  href="#" onClick="displayCalendar(document.forms[0].fromDt,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></c:if></td>
                    <td width="15%" class="TDShade" ><f:message key="toDate"/> : </td>
                    <td colspan="2" class="TDShadeBlue" width="35%" >
                    <form:input id="toDt" cssClass="inputBox" cssStyle="width:70px;" path="consolidatedInvoice.toDt" onchange="updateDateFlag();" disabled="${generated}"/>
                      <form:errors path="consolidatedInvoice.toDt" cssClass="redstar"/>
                    &nbsp;<c:if test="${not generated}"><a href="#" onClick="displayCalendar(document.forms[0].toDt,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></c:if></td>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td class="TDShade"><f:message key="invoiceDate"/> : </td>
                    <td class="TDShadeBlue">
                   <form:input id="invoiceDt" cssClass="inputBox" cssStyle="width:70px;" path="consolidatedInvoice.invoiceDt" disabled="${generated}"/>
                      <form:errors path="consolidatedInvoice.invoiceDt" cssClass="redstar"/>
                    &nbsp;<c:if test="${not generated}"><a href="#" onClick="displayCalendar(document.forms[0].invoiceDt,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></c:if></td>
                    <td class="TDShade" ><f:message key="paymentterms"/> : </td>
                    <td colspan="2" class="TDShadeBlue" >
                    <form:select id="sel4" cssClass="selectionBox" path="consolidatedInvoice.pymntTermsCd" items="${icbfn:dropdown('paymentTerms', null)}" itemLabel="name" itemValue="value" disabled="${generated}"/>
                     </td>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td class="TDShade"><f:message key="remitTo"/> : </td>
                    <td class="TDShadeBlue">
					<form:input id="remitto" cssClass="inputBox" maxlength="5" path="consolidatedInvoice.bankCd" disabled="${generated}"/>
                     <form:errors path="consolidatedInvoice.bankCd" cssClass="redstar"/>
                     <c:if test="${not generated}"><a href="#" onClick="javascript:updateBankIframe('${command.consolidatedInvoice.consolidatedInvoiceId.buName}','${command.consolidatedInvoice.currencyCd}');popup_show('searchremitto', 'searchremitto_drag', 'searchremitto_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchremitto','remittobody');">
                     <img src=" images/lookup.gif" width="13" alt="lookup remitTo" height="13" border="0" />
                     </a></c:if>
                     </td>
                    <td class="TDShade" ><f:message key="bankAccount"/> : </td>
                    <td colspan="2" class="TDShadeBlue" >
 					<form:input cssClass="inputBox" maxlength="5" path="consolidatedInvoice.bankAcctKey" disabled="${generated}"/>
                     <form:errors path="consolidatedInvoice.bankAcctKey" cssClass="redstar"/>
                     <c:if test="${not generated}"><a href="#" onClick="javascript:updateBankAccountIframe('${command.consolidatedInvoice.consolidatedInvoiceId.buName}','${command.consolidatedInvoice.currencyCd}');popup_show('searchaccount', 'searchaccount_drag', 'searchaccount_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchaccount','accountbody');">
                     <img src=" images/lookup.gif" width="13" alt="Lookup Account" height="13" border="0"/></a></c:if>
                     </td>
                  </tr>

					</table>	
					
					
					</td>
                  </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>
                        	<a href="#" class="button1" style="text-decoration: none;color:black;" onclick="javascript:generateInvoice();" <c:if test="${generated}">disabled</c:if>>Generate</a>&nbsp;
                        </td>
                        <td style="text-align:right"><a href="search_consl_inv.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif"  onClick="onSave()" <c:if test="${generated}">disabled</c:if> alt="Save" width="14" height="14" border="0"  /></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
			
			
			<!-- ------------------------- TAB 2 CONTAINER ----------------------------------------- -->
            <div id="tab2" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="50%" colspan="2"><f:message key="unit"/> :${command.consolidatedInvoice.consolidatedInvoiceId.buName}  <img src="images/separator1.gif" width="2" height="22" align="absmiddle" style="margin-left:5px; margin-right:5px;"> <f:message key="invoice"/> : ${command.consolidatedInvoice.consolidatedInvoiceId.consolInvoiceNo}</th>
                    <th width="40%" colspan="2" align="right" ><f:message key="pretaxAmt"/> : ${command.preTaxAmount}&nbsp;${command.consolidatedInvoice.currencyCd }</th>
                    <th width="10%" bgcolor="#ffffff" style="text-align:right"><a href="search_consl_inv.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif"  onClick="onSave()" <c:if test="${generated}">disabled</c:if> alt="Save" width="14" height="14" border="0"  /></a></th>
                  </tr>
                  <tr>
                    <td colspan="5" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
					<tr>
					  <th colspan="4"><f:message key="billTo"/></th>
					</tr>
					<tr>
					  <td class="TDShade" width="20%"><f:message key="attentionTo"/> : </td>
					  <td class="TDShadeBlue">
					   <form:input cssClass="inputBox" path="contact.id" disabled="${generated}" />
                     <form:errors path="contact.id" cssClass="redstar"/>
					  &nbsp;
					  <c:if test="${not generated}"><a href="#" >
          			<img src="images/lookup.gif" alt="lookup contact" width="13" height="13" border="0" onClick="javascript:updateSearchContactIframeSrc(${command.consolidatedInvoice.custCode });popup_show('contact', 'contact_drag', 'contact_exit', 'screen-corner', 120, 20);popupboxenable();hideIt();showPopupDiv('contact','contactbody');"></a></c:if>
					  </td>
					  <td class="TDShade" width="20%"><f:message key="name"/> : </td>
					  <td class="TDShadeBlue">
					  ${command.contact.firstName }&nbsp;${command.contact.lastName }
					  </td>
					</tr>
					<tr>
					  <td class="TDShade"><f:message key="location"/> : </td>
					  <td class="TDShadeBlue">
					   <form:input cssClass="inputBox" path="consolidatedInvoice.locationNumber" disabled="${generated}" />
                     <form:errors path="consolidatedInvoice.locationNumber" cssClass="redstar"/>&nbsp;
					  <c:if test="${not generated}"><a href="#" >  
					  <img src="images/lookup.gif" alt="lookup location Number" width="13" height="13" border="0" onClick="javascript:updateAddrSeqIframeSrc('${command.consolidatedInvoice.custCode }');popup_show('addressseq','addressseq_drag', 'addressseq_exit','screen-corner', 120, 20); hideIt();popupboxenable();"></a></c:if>
					  </td>
					  <td class="TDShade">&nbsp; </td>
					  <td class="TDShadeBlue">&nbsp;</td>
					</tr>
					<tr>
					  <td class="TDShade" height="22"><f:message key="country"/> : </td>
					  <td class="TDShadeBlue">${command.custAddress.country }</td>
					  <td class="TDShade"><f:message key="state"/> : </td>
					  <td class="TDShadeBlue">${command.custAddress.state }</td>
					</tr>
					<tr>
					  <td class="TDShade" height="22"><f:message key="address1"/> : </td>
					  <td class="TDShadeBlue">${command.custAddress.address1 }</td>
					  <td class="TDShade"><f:message key="address2"/> : </td>
					  <td class="TDShadeBlue">${command.custAddress.address2 }</td>
					</tr>
					<tr>
					  <td class="TDShade" height="22"><f:message key="address3"/> : </td>
					  <td class="TDShadeBlue">${command.custAddress.address3 }</td>
					  <td class="TDShade"><f:message key="address4"/> : </td>
					  <td class="TDShadeBlue">${command.custAddress.address4 }</td>
					</tr>
					<tr>
					  <td class="TDShade" height="22"><f:message key="city"/> : </td>
					  <td class="TDShadeBlue">${command.custAddress.city }</td>
					  <td class="TDShade"><f:message key="postal"/> : </td>
					  <td class="TDShadeBlue">${command.custAddress.postal }</td>
					</tr>
					
					</table>	
					
					
					</td>
                  </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"><a href="search_consl_inv.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif"  onClick="onSave()" <c:if test="${generated}">disabled</c:if> alt="Save" width="14" height="14" border="0"  /></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 2 CONTAINER END ------------------------------ -->
			
			
			<!-- ------------------------- TAB 3 CONTAINER ----------------------------------------- -->
            <div id="tab3" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr>
                    <th width="50%" colspan="2"><f:message key="unit"/> : ${command.consolidatedInvoice.consolidatedInvoiceId.buName} <img src="images/separator1.gif" width="2" height="22" align="absmiddle" style="margin-left:5px; margin-right:5px;"> <f:message key="invoice"/> : ${command.consolidatedInvoice.consolidatedInvoiceId.consolInvoiceNo}</th>
                    <th width="40%" colspan="2" align="right" ><f:message key="pretaxAmt"/> : ${command.preTaxAmount}&nbsp;${command.consolidatedInvoice.currencyCd }</th>
                    <th width="10%" bgcolor="#ffffff" style="text-align:right"><a href="search_consl_inv.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif"  onClick="onSave()" <c:if test="${generated}">disabled</c:if> alt="Save" width="14" height="14" border="0"  /></a></th>
                  </tr>
                  <tr>
                    <td colspan="5" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
					<tr>
					  <th colspan="4"><f:message key="invoiceAttachDetach"/></th>
					</tr>
					<tr>
					  <td class="TDShade" colspan="4" style="margin-left:0px;margin-right:0px;padding-left:0px;padding-right:0px; text-align:right;">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:0px;width:100%;">
                              <tr>
                                <th style="text-align:center;"><f:message key="select"/></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByAttachDetachHeader('jc.custCode');" ><f:message key="billTo"/></a></th>
                                <!-- <th style="text-align:center;"><f:message key="consolidateInvoice"/></th> -->
                                <th style="text-align:center;"><a href="#start" onClick="sortByAttachDetachHeader('job.buName');" ><f:message key="unit"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByAttachDetachHeader('jc.jobNumber');" ><f:message key="JobNumber"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByAttachDetachHeader('jci.consolInvoiceNo');" ><f:message key="consolInv"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByAttachDetachHeader('jci.invoice');" ><f:message key="invoice"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByAttachDetachHeader('jci.invoiceDate');" ><f:message key="invoiceDate"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByAttachDetachHeader('jc.invoiceAmt');" ><f:message key="invoiceAmt"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByAttachDetachHeader('jci.transactionCurrencyCd');" ><f:message key="currency"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByAttachDetachHeader('jci.vatProvince');" ><f:message key="vatProvince"/></a></th>
                                <th style="text-align:center;">&nbsp;</th>
                              </tr>
							  <c:if test="${command.invoiceSearch.results != null}">
							  <div id="busearchresults"> 
							  <c:forEach items="${command.invoiceSearch.results}" var="jobContractInvoice" varStatus="status">
							  <tr>
							  <td align="center">
							  <c:choose>
							  <c:when test="${jobContractInvoice.consolInvoiceNo != null}">
							  <c:choose>
							  <c:when test="${jobContractInvoice.consolInvoiceNo == command.consolidatedInvoice.consolidatedInvoiceId.consolInvoiceNo}">
							  <input type="checkbox" id="chk${status.index }" name="chk${status.index }" value="${jobContractInvoice.id }" <c:if test="${jobContractInvoice.billStatus=='INV'}"> disabled</c:if>>
							  </c:when>
							  <c:otherwise>
							  <input type="checkbox" id="chk${status.index }" name="chk${status.index }" value="${jobContractInvoice.id }" disabled>
							  </c:otherwise>
							  </c:choose>							  
							  </c:when>
							  <c:otherwise>
							  <input type="checkbox" id="chk${status.index }" name="chk${status.index }" value="${jobContractInvoice.id }" >
							  </c:otherwise>
							  </c:choose>
							  </td>
							  <td align="center">
							  ${jobContractInvoice.jobContract.custCode }
							  </td>
							  <!-- <td align="center">
							  &nbsp;
							  </td> -->
							  <td align="center">
							  ${jobContractInvoice.jobContract.jobOrder.buName }
							  </td>
							  <td align="center">
							  ${jobContractInvoice.jobContract.jobNumber }
							  </td>
							  <td align="center">
							  ${jobContractInvoice.consolInvoiceNo }
							  </td>
							  <td align="center">
							  ${jobContractInvoice.invoice }
							  </td>
							  <td align="center">
							  <f:formatDate value="${jobContractInvoice.invoiceDate}" type="date" dateStyle="short" />
							  </td>
							  <td align="center">
							  ${jobContractInvoice.jobContract.invoiceAmt }
							  </td>
							  <td align="center">
							  ${jobContractInvoice.transactionCurrencyCd }
							  </td>
							  <td align="center">
							  <c:if test="${not empty jobContractInvoice.vatProvince}">
							  ${jobContractInvoice.vatProvince }
							  </c:if>
							  </td>
							  <td align="center">
							  &nbsp;
							  </td>
							  </tr>
							  </c:forEach>
							  </div>
							  </c:if>
                            </table>
					  </td>
					</tr>
					</table>	
					
					</td>
                  </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><input type="button" class="button1" value="Attach" <c:if test="${generated}">disabled</c:if>  onClick="attachInvoices(${fn:length(command.invoiceSearch.results)},'attach', '${command.vatCountryFlag}', '${command.consolidatedInvoice.vatProvince}' )"  />&nbsp;&nbsp;
                        <input type="button" class="button1" value="Detach" <c:if test="${generated}">disabled</c:if> onClick="attachInvoices(${fn:length(command.invoiceSearch.results)},'detach')"/></td>
                        <td align="right" nowrap ><table cellspacing="0" cellpadding="0" border="0" style="text-align:right">
                          <tr>
                            <td><IMG SRC="images/navfirst.gif" ALT="First Page" WIDTH="14" HEIGHT="14" hspace="2" BORDER="0"></td>
                            <td><IMG SRC="images/navprevious.gif" ALT="Previous Page" WIDTH="14" HEIGHT="14" hspace="2" BORDER="0"></td>
							<td><select name="sel3" size="1" class="selectionBox" id="sel3" style="width:84px;">
                                  <option value="" selected>Go to page</option>
                                </select></td>
                            <td><IMG SRC="images/navnext.gif" ALT="Next Page" WIDTH="14" HEIGHT="14" hspace="2" BORDER="0"></td>
                            <td><IMG SRC="images/navlast.gif" ALT="Last Page" WIDTH="14" HEIGHT="14" hspace="2" BORDER="0"></td>
                          </tr>
                        </table></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 3 CONTAINER END ------------------------------ -->
            
            <!----------------- TAB 4 CONTAINER  ------------------------------ -->
            <div id="tab4" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="50%" colspan="2"><f:message key="invoiceSummary"/><img src=" images/separator2.gif" width="2" height="27"
          			align="absmiddle" style="margin-left:5px;margin-right:5px;" /><f:message key="unit"/> :${command.consolidatedInvoice.consolidatedInvoiceId.buName}  <img src="images/separator1.gif" width="2" height="22" align="absmiddle" style="margin-left:5px; margin-right:5px;"> <f:message key="invoice"/> : ${command.consolidatedInvoice.consolidatedInvoiceId.consolInvoiceNo}</th>
                    <th width="40%" colspan="2" align="right" ><f:message key="pretaxAmt"/> : ${command.preTaxAmount}&nbsp;${command.consolidatedInvoice.currencyCd }</th>
                    <th width="10%" bgcolor="#ffffff" style="text-align:right"><a href="search_consl_inv.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif"  onClick="onSave()" <c:if test="${generated}">disabled</c:if> alt="Save" width="14" height="14" border="0"  /></a></th>
                  </tr>	
                  
                
                <tr>
                  <td colspan="2"  class="TDShade"><f:message key="company"/>:</td>
                  <td colspan="3"  class="TDShadeBlue">${command.customer.name }</td>
                </tr>
                <tr>
                  <td colspan="2" class="TDShade"><f:message key="billingContact"/>: </td>
                  <td colspan="3" class="TDShadeBlue">${command.contact.firstName }&nbsp;${command.contact.lastName }</td>
                </tr>
  					<tr>
                  <td colspan="2" class="TDShade"><f:message key="consolidatedInvoicePDF"/>: </td>
                  <td colspan="3" class="TDShadeBlue">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="5" class="TDShade" style="padding:2px;">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
                      <tr>
                        <th width="2%">&nbsp;</th>
                        <th><f:message key="invoice"/></th>
                        <th>&nbsp;</th>
                        <th><f:message key="status"/></th>
                        <th><f:message key="generatedOn"/> </th>
                        <th width="15%"><f:message key="generatedBy"/></th>
                        <th>&nbsp;</th>
                      </tr>
                      <%--  c:forEach items="${command.invoiceSearch.results}" var="jobContractInvoice" varStatus="status" --%>
                      <%--  c:if test="${jobContractInvoice.consolInvoiceNo == command.consolidatedInvoice.consolidatedInvoiceId.consolInvoiceNo}" --%>
                        <c:if test="${generated}">
                        <tr>
                          <td class="TDShadeBlue">&nbsp;</td>
                          <td class="TDShadeBlue">${command.consolidatedInvoice.consolidatedInvoiceId.consolInvoiceNo}</td>
                          <td align="center" class="TDShadeBlue">
                            <a href="invoice_view.htm?invoicetype=consol&invoice=${command.consolidatedInvoice.invoiceFileName}" target="_blank">
                               <img src="images/icoviewinvoice.gif" alt="View Invoice" width="16" height="18" border="0">
                            </a>
                            <c:forEach items="${command.consolidatedInvoice.consolInvoiceFiles}" var="invoiceFile" >
	                            <a href="invoice_view.htm?invoicetype=consol&invoice=${invoiceFile.invoiceFileName}" target="_blank">
	                                  <img src="images/icoviewinvoice.gif" alt="View ${invoiceFile.invoiceFileType.invoiceFileType} Invoice" width="16" height="18" border="0">
	                            </a>
	                            <input type="checkbox" name="countable" onchange="javascript:setCountable('${invoiceFile.id}' , this.checked);" <c:if test="${invoiceFile.countable}">checked</c:if> alt="countable"/>
                            </c:forEach>
                            
                            <c:if test="${not empty command.consolidatedInvoice.pdfGeneratedFlag && not command.consolidatedInvoice.pdfGeneratedFlag}">
                              <a href="#" class="smallbutton" style="text-decoration: none;color:black;" onclick="javascript:regenerateInvoice('${command.consolidatedInvoice.consolidatedInvoiceId.consolInvoiceNo}','${command.consolidatedInvoice.consolidatedInvoiceId.buName}');"><f:message key="regenerate"/></a>
                            </c:if>
                          </td>
                          <td class="TDShadeBlue"><f:message key="success"/></td>
                          <td class="TDShadeBlue">${command.consolidatedInvoice.generateTime} </td>
                          <td class="TDShadeBlue">${command.consolidatedInvoice.generatedByUserName}</td>
                          <td class="TDShadeBlue">
                          </td>
                          </tr>
                          </c:if>
                          <%-- /c:if --%>                         
                        
                      <%--  /c:forEach --%>
                    </table>
                  </td>
                </tr>
                  
                  
                  </tbody>
                  </table>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"><a href="search_consl_inv.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" <c:if test="${generated}">disabled</c:if>  onClick="onSave()" alt="Save" width="14" height="14" border="0"  /></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 4 CONTAINER END ------------------------------ -->                		
			
          </div>
        </div>
        <script type="text/javascript">
      	var pageNoVar = "${command.tabName}"
      	dolphintabs.init("tabnav", pageNoVar)      
      </script>   
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>

<!-- --------------------------- Parent Customer Lookup ------------------------------------------------- -->
<div class="sample_popup" id="parentcust" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="parentcust_drag" style="width:750px;;height:auto; "> <img class="menu_form_exit"   id="parentcust_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchParentCustomer"/></div>
<div class="menu_form_body" id="parentcustbody"    style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  id="searchParentCustomerFr" name="searchParentCustomerFr" allowtransparency="yes" src="blank.html"></iframe>    
</div></div>
<!-- --------------------------- Parent Customer Lookup End------------------------------------------------- -->

<!--------------------------------------------Search Bank Lookup-------------------------------------------------->
<div class="sample_popup"     id="searchremitto" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchremitto_drag" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="searchremitto_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="remitToSearch"/></div><div class="menu_form_body" id="remittobody"   style="width:750px; height:500px;overflow-y:hidden;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr><iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="sremittoframe" name="sremittoframe" allowtransparency="yes" ></iframe>
</tr></table></div></div>
<!--------------------------------------------Search Bank Lookup End---------------------------------------------->
<!----------------------------------------------Search Account Lookup--------------------------------------------->
<div class="sample_popup" id="searchaccount" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="searchaccount_drag" style="width:750px;height:auto;"> 
  <img class="menu_form_exit"   id="searchaccount_exit" src="images/form_exit.png" border="0" />&nbsp;&nbsp;&nbsp;<f:message key="accountSearch"/></div>
  <div class="menu_form_body" id="accountbody"   style="width:750px; height:530px;overflow-y:hidden;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr><iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="saccountframe" name="saccountframe" allowtransparency="yes"></iframe>
</tr></table></div></div>
<!---------------------------------------------Search Account Lookup End------------------------------------------>
<!------------------------- Search Contact  Popup Start--------------------------------------------------------->
<div class="sample_popup" id="contact" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contact_drag" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="contact_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchContact"/></div>
<div class="menu_form_body" id="contactbody"    style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  id="searchContactFr" name="searchContactFr" allowtransparency="yes" src="blank.html"></iframe>      
</div></div>
<!----------------------- Search Contact  Popup End--------------------------------------------------------->

<!------------------------------------------------------------------------ Create New Address Sequence Popup ---------------------------------------------------------------------------------------->
<div class="sample_popup" id="addressseq" style="visibility: hidden; display: none; z-index:2;">
<div class="menu_form_header" id="addressseq_drag" style="width:750px;height:auto;z-index:2; "> 
<img class="menu_form_exit"   id="addressseq_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchAddressSequence"/></div>                                                            
<div class="menu_form_body" id="addressseqbody"    style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  scrolling="auto" id="addressseqFr" name="addressseqFr" allowtransparency="yes" src="blank.html"></iframe>

</div>
</div>
<!---------------------------------------------------------------------------  New Address Sequence Lookup End -------------------------------------------------------------------------------------->

<!-----------------------------------------BU Lookup----------------------------------------------------->
<div class="sample_popup" id="bu" style="visibility: hidden;display:none;">
<div class="menu_form_header" id="bu_drag" style="width:750px;">
<img class="menu_form_exit" id="bu_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;<f:message key="searchBusinessUnit"/></div>
<div class="menu_form_body" id="bubody" style="width:750px;height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" scrolling="auto" id="searchBusinessUnitFr" name="searchBusinessUnitFr" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!-------------------------------------------BU Lookup--------------------------------------------------->
