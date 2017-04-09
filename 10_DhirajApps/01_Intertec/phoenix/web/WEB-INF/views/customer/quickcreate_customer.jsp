<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script language="javascript" src="js/globalFunctions.js"></script>
<script type="text/javascript" src="/phoenix/dwr/util.js"></script>
<script type="text/javascript" src="/phoenix/dwr/engine.js"></script>
<script src='/phoenix/dwr/interface/DWRUtil.js'></script>
<script language="javascript">

function locType(){ 
  var parentCust= document.getElementById("sel5");
  if(parentCust.value=="04")
  document.getElementById("pcust").style.visibility = "hidden";
  else
  document.getElementById("pcust").style.visibility = "visible";
}

function disableCustomerEdit()
{
  document.customerQuickCreateForm.existingCustomerFlag.value = "Y";
  document.customerQuickCreateForm.submit();
  setDisabled('customerDIV', true);
  setDisabled('customerPhyAddr', true);

}
function populateContactFields()
{
  document.customerQuickCreateForm.existingContactFlag.value = "Y";
  document.customerQuickCreateForm.submit();

}

function setDisabled(id, disabled)
{
if ( !document.getElementById
|| !document.getElementsByTagName) return;

var nodesToDisable = {button :'', input :'', optgroup :'',
option :'', select :'', textarea :''};

var node, nodes;
var div = document.getElementById(id);
if (!div) return;

nodes = div.getElementsByTagName('*');
if (!nodes) return;

var i = nodes.length;
while (i--){
node = nodes[i];
if ( node.nodeName
&& node.nodeName.toLowerCase() in nodesToDisable ){
node.disabled = disabled;
}
}
}


 function updateNewContractIframeSrc(){
document.getElementById('contractFr').setAttribute("src","create_contract_popup.htm?inputFieldId=contractCust.contractCustId.contractCode&rowNum=0&searchForm=customerQuickCreateForm");
}

function updateContactIframeSrc(existCustCode){

document.getElementById('searchContactFr').setAttribute("src","search_contact_popup.htm?custCode="+existCustCode+"&inputFieldId=contact.id&searchForm=customerQuickCreateForm&divName=contactid&divbody=contactbody");
}

function updateCustMultilingualIframeSrc(CustCode){
var name = document.getElementById('cname').value;

document.getElementById('createCustMLingualFr').setAttribute("src","create_multilingual_customer_popup.htm?custCode="+CustCode+"&custName="+name+"&divName=customer_additionalang&divbody=customer_additional_lang_body&searchForm=customerQuickCreateForm");
}

function updateCustAddrMultilingualIframeSrc(custAddrId){
document.getElementById('createCustAddrMLingualFr').setAttribute("src","create_multilingual_quick_customeraddr_popup.htm?custAddrId="+custAddrId+"&divName=customeraddr_additionalang&divbody=customeraddr_additional_lang_body&searchForm=customerQuickCreateForm&custAddress=custAddress");
}

function updateContactAddrMultilingualIframeSrc(contactAddrId,CustCode){

document.getElementById('createCustAddrMLingualFr').setAttribute("src","create_multilingual_quick_customeraddr_popup.htm?contactAddrId="+contactAddrId+"&divName=customeraddr_additionalang&divbody=customeraddr_additional_lang_body&searchForm=customerQuickCreateForm&contactAddress=contactAddress");
}

function billto(){
  
  document.getElementById("billprimary").style.visibility = "visible";
}
function shipto(){
  document.getElementById("shipprimary").style.visibility = "visible";
}

function soldto(){
  document.getElementById("soldprimary").style.visibility = "visible";
}
function OptExistingAddress() {
  document.customerQuickCreateForm.selectedAddrIndicator.value = "existingAddr";
    document.getElementById("ExistingAddress").style.visibility = "visible";
  document.getElementById("ExistingAddress").style.display = "block";
  document.getElementById("NewAddress").style.visibility = "hidden";
  document.getElementById("NewAddress").style.display = "none";
  document.getElementById("addLang").style.visibility = "hidden";
  }
  function OptCustPrimaryAddress() {
  document.customerQuickCreateForm.selectedAddrIndicator.value = "primaryAddr";
  document.getElementById("ExistingAddress").style.visibility = "hidden";
  document.getElementById("ExistingAddress").style.display = "none";
  document.getElementById("NewAddress").style.visibility = "hidden";
  document.getElementById("NewAddress").style.display = "none";
  document.getElementById("addLang").style.visibility = "hidden";
  }
  function OptNewAddress() {
  document.customerQuickCreateForm.selectedAddrIndicator.value = "newAddr";
  document.getElementById("NewAddress").style.visibility = "visible";
  document.getElementById("NewAddress").style.display = "block";
  document.getElementById("ExistingAddress").style.visibility = "hidden";
  document.getElementById("ExistingAddress").style.display = "none";
  newAddrcheck();
  }


function OptExistingContract() {
  document.customerQuickCreateForm.selectedContractIndicator.value = "existingContract";
  document.getElementById("ExistingContract").style.visibility = "visible";
  document.getElementById("ExistingContract").style.display = "block";
  document.getElementById("NewContract").style.visibility = "hidden";
  document.getElementById("NewContract").style.display = "none";
  document.getElementById("contractCustContactDiv").style.visibility = "hidden";
  document.getElementById("contractCustContactDiv").style.display = "none";
  }
  function OptNewContract() {
  document.customerQuickCreateForm.selectedContractIndicator.value = "newContract";
  document.getElementById("NewContract").style.visibility = "visible";
  document.getElementById("NewContract").style.display = "block";
  document.getElementById("ExistingContract").style.visibility = "hidden";
  document.getElementById("ExistingContract").style.display = "none";
  document.getElementById("contractCustContactDiv").style.visibility = "visible";
  document.getElementById("contractCustContactDiv").style.display = "block"; 
  }
  
function setCustomerSearchFlag(){

  document.customerQuickCreateForm.existingCustomerFlag.value = "Y";
}

function setContactSearchFlag(){

  document.customerQuickCreateForm.existingContactFlag.value = "Y";
  document.customerQuickCreateForm.existingCustomerFlag.value = "none";
 }

function setLocationSearchFlag(){

  document.customerQuickCreateForm.existingLocationFlag.value = "Y";
}

<!-- Add-On Script Files -->
function updatevatCodeIframeSrc(){    document.getElementById('searchVatRateFr').setAttribute("src","search_vat_rate_popup.htm?inputFieldId=addCustomerAddresses[0].custAddresses[0].taxCode&div1=vatcode&div2=vatcodebody");
 }

function interunitind(){  
    if(document.getElementById("interunit").checked)
    {
    document.getElementById("interunitpend").checked=false;
    document.getElementById("interunitpend").disabled=true;
    document.getElementById("ccode").style.visibility = "visible";  
    document.getElementById("busunit").style.visibility="visible";
     // document.getElementById("anchor").style.visibility="visible";

    document.getElementById("custcode").value="New";
    document.getElementById("busunit").className="unprotected";
    document.getElementById("bunit").disabled=false;
    }
    else{
      document.getElementById("busunit").style.visibility="hidden";
      document.getElementById("ccode").style.visibility = "hidden"; 
      //document.getElementById("anchor").style.visibility="hidden";
      document.getElementById("interunitpend").disabled=false;
    document.getElementById("bunit").value="";
      document.getElementById("custcode").value="New";
      } 
   }

function interunitpending(){
     if(document.getElementById("interunitpend").checked)
     {
       document.getElementById("ccode").style.visibility = "visible";  
       document.getElementById("busunit").style.visibility="hidden";
     //  document.getElementById("anchor").style.visibility="hidden";
       document.getElementById("custcode").value="";
       document.getElementById("bunit").disabled=true;
     }
     else
     {
      document.getElementById("ccode").style.visibility = "hidden"; 
      document.getElementById("busunit").style.visibility="hidden";
      document.getElementById("custcode").value="New";
     }
   }



function busiUnit(){
  var a=document.getElementById("bunit").value;       
  document.getElementById("custCode").value=a;
  }

function updateBusinessUnitIframeSrc(){
document.customerQuickCreateForm.busFlag.value="bus";
document.getElementById('searchBusinessUnitFr').setAttribute("src","search_businessunit_popup.htm?inputFieldId=customer.interunitBusUnitName&searchForm=customerQuickCreateForm");
}


function resetcust()
{
 document.customerQuickCreateForm.existingCustomerFlag.value = "none";
 document.customerQuickCreateForm.existingContactFlag.value = "none";
}

function populateCustomerDetails()
  {
   document.customerQuickCreateForm.existingCustomerFlag.value = "Y";
   document.customerQuickCreateForm.submit();
  }

  function changeCfgContractData(myOperationType)
  {
    document.customerQuickCreateForm.operationType.value=myOperationType;
    document.customerQuickCreateForm.submit();  
  }

function contDivEnable()
{  
  document.customerQuickCreateForm.existingContactTypeFlag.value = "Y";
  document.customerQuickCreateForm.existingCustomerFlag.value = "none";
  document.customerQuickCreateForm.existingContactFlag.value = "none"; 
}
function contDivEnableAjax()
{
document.customerQuickCreateForm.existingContactTypeFlag.value = "Y";
document.customerQuickCreateForm.submit();  
}

function resetContDivEnable()
{
  document.customerQuickCreateForm.existingContactTypeFlag.value = "N"; 
  document.getElementById("contractCustContactDiv").style.visibility = "hidden";
  document.getElementById("contractCustContactDiv").style.display = "none"; 
}
function checkCreditHold()
{
  if(document.getElementById("sel9").value == 'I'){
    document.getElementById("chd").checked=true;
  } else {
	document.getElementById("chd").checked=false;
  }
}
function updateCustomerNotesIframeSrc()
{ document.getElementById('viewCustomerNotesFr').setAttribute("src","view_customer_notes_popup.htm?divName=viewCustomerNote&divBody=viewCustomerNotebody&reqForm=quickCreateForm");
}

function populateCustCode()
{ 
	var bu = document.getElementById("bunit").value;
	if(bu != null && bu != ''){
		document.getElementById("custCode").value = bu;
	}
}

function updatePaymentType()
{ 
	dwr.engine.setActiveReverseAjax(true); 
	initReverseAjax();
	//confirm(document.getElementById("crapp").checked);
	DWRUtil.dwrTest(document.getElementById("crapp").checked,data);
}
function initReverseAjax() { 

	if (dwr.engine._scriptSessionId == null) 
	{
		 setTimeout("initReverseAjax()", 2000); 
	}
 	else 
 	{ 
 		dwr.engine.setActiveReverseAjax(true); 
	 }
} 
	
function data(value){
//confirm('value.....'+value);
dwr.util.removeAllOptions("sel70");
dwr.util.addOptions("sel70", value);
}



</script>

<icb:list var="customerType">
<icb:item>customerType</icb:item>
</icb:list>
<icb:list var="industryType">
  <icb:item>industryType</icb:item>
</icb:list>
<icb:list var="locationType">
  <icb:item>locationType</icb:item>
</icb:list>

<icb:list var="paymentType">
  <icb:item>paymentType</icb:item>
</icb:list>

<icb:list var="invoiceType">
  <icb:item>invoiceType</icb:item>
</icb:list>
<icb:list var="secondaryIndustry">
  <icb:item>secondaryIndustry</icb:item>
</icb:list>
<icb:list var="invDeliveryMethod">
  <icb:item>invDeliveryMethod</icb:item>
</icb:list>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<icb:list var="InvoiceLanguage">
<icb:item>InvoiceLanguage</icb:item>
</icb:list>
<icb:list var="vipPlatinumPrefer">
<icb:item>vipPlatinumPrefer</icb:item>
</icb:list>
<icb:list var="businessDivisions">
  <icb:item>businessDivisions</icb:item>
</icb:list>
<icb:list var="businessStreams">
  <icb:item>businessStreams</icb:item>
</icb:list>
<icb:list var="OriginSource">
  <icb:item>OriginSource</icb:item>
</icb:list>
<icb:list var="parentCustomers">
  <icb:item>HQRT</icb:item>
</icb:list>
<icb:list var="childCustomers">
  <icb:item>null</icb:item>
</icb:list>
<form:form name="customerQuickCreateForm" method="POST" action="quickcreate_customer.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="selectedAddrIndicator" />
<form:hidden path="selectedContractIndicator" />
<form:hidden path="existingCustomerFlag" />
<form:hidden path="existingContactFlag" />
<form:hidden path="existingLocationFlag" />
<form:hidden path="existingContactTypeFlag"/>
<form:hidden path="busFlag"/>
<input type="hidden" name="operationType" value="" />


<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
<!-------------------------------------------MAIN CONTAINER------------------------------------------------------->
      <div id="MainContentContainer">
<!--------------------------------------------TABS CONTENTS------------------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav"><APPLET CODE="" WIDTH="" HEIGHT="">
          </APPLET>
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="quickCreate"/></span></a></li>
        
            </ul>
          </div>
<!-----------------------------------Sub Menus container. Do not remove------------------------------------------->
          <div id="tab_inner">
            <form>          
<!--------------------------------------------TAB 1 CONTAINER----------------------------------------------------->
            <div id="tab1" class="innercontent" >
      <table width="100%"><tr><td>
      
    
      
      <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=3 width="50%"><f:message key="customer"/>  </th>
             <th colspan=3 width="50%">
       <a href="#" onclick="javascript:updateCustMultilingualIframeSrc('${command.customer.custCode}');popup_show('customer_additionalang', 'customer_additional_lang_drag', 'customer_additional_lang_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('customer_additionalang','customer_additional_lang_body');popupboxenable();"><f:message key="additionalLanguageInformation"/></a></th>
            <th width="5%" bgcolor="#ffffff" style="text-align:right"><a href="#">
            <input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="resetcust();" /></a></th>
          </tr>
        </tbody>
              </table>

    
    <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
           <tbody>
<div id="customerDIV" >
          <tr>
  <td class="TDShade" ><f:message key="interunit"/></td>
<td class="TDShadeBlue" >
              <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:checkbox id="interunit"  path="customer.interunitInd" onclick="interunitind()" disabled="true"/>
              </c:when>
              <c:otherwise>
              <form:checkbox id="interunit"  path="customer.interunitInd" onclick="interunitind()"/>
              </c:otherwise>
              </c:choose>
        </td>
 <td class="TDShade">&nbsp;</td>
<td class="TDShadeBlue" >&nbsp;</td>
</tr>
<tr>
<td class="TDShade"><f:message key="interunitPending"/></td>
<td class="TDShadeBlue" >
<c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:checkbox id="interunitpend" path="customer.interunitPendingInd" onclick="interunitpending()" disabled="true"/>
              </c:when>
              <c:otherwise>
            <form:checkbox id="interunitpend"  path="customer.interunitPendingInd" onclick="interunitpending()"/>
              </c:otherwise>
              </c:choose>




</td>
 <%-- <td class="TDShade" ><f:message key="interunitBusUnit"/></td> 
  <td id="busunit" class="TDShadeBlue" style="visibility:hidden;background-color:#faffff;" >  
  <c:choose>
   <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
  <form:input id="bunit" cssClass="inputBox" size="35" path="customer.interunitBusUnitName" disabled="true" /><form:errors path="customer.interunitBusUnitName" cssClass="redstar"/>
  <c:if test="${command.customerExistsFlag == null || command.customerExistsFlag !='Y'}">  
  <a href="#" id="anchor"  onClick="javascript:updateBusinessUnitIframeSrc();popup_show('bu', 'bu_drag', 'bu_exit', 'screen-corner', 120, 20);popupboxenable(); hideIt();hideBUsearch(); "> <img src="images/lookup.gif" alt="lookup Business Unit" width="13" height="13" border="0"></a>
  </c:if>
  </c:when>
   <c:otherwise>
    <form:input id="bunit" cssClass="inputBox" size="35" path="customer.interunitBusUnitName"/>
  <form:errors path="customer.interunitBusUnitName" cssClass="redstar"/>
    <a href="#" id="anchor"  onClick="javascript:updateBusinessUnitIframeSrc();popup_show('bu', 'bu_drag', 'bu_exit', 'screen-corner', 120, 20);popupboxenable(); hideIt();hideBUsearch(); "> <img src="images/lookup.gif" alt="lookup Business Unit" width="13" height="13" border="0"></a>
   </c:otherwise>
   </c:choose>
  </td> --%>
 <td class="TDShade" ><f:message key="interunitBusUnit"/></td> 
  <td id="busunit" class="TDShadeBlue" style="visibility:hidden;background-color:#faffff;" >  
  <c:choose>
   <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
   <form:select id="bunit" cssClass="selectionBoxbig"
          path="customer.interunitBusUnitName"
          items="${icbfn:dropdown('businessUnit', null)}"
          itemLabel="name" itemValue="value" disabled="true"/> <form:errors
          path="customer.interunitBusUnitName" cssClass="redstar" />
  </c:when>
   <c:otherwise>
	<form:select id="bunit" cssClass="selectionBoxbig" onchange="populateCustCode()"
          path="customer.interunitBusUnitName"
          items="${icbfn:dropdown('businessUnit', null)}"
          itemLabel="name" itemValue="value"/> <form:errors
          path="customer.interunitBusUnitName" cssClass="redstar" />
   </c:otherwise>
   </c:choose>
  </td>
 
</tr>
<tr>  
      <td width="20%" class="TDShade"><f:message key="customerName"/><strong><span class="redstar">*:</span></strong></td>

                    <td width="30%" class="TDShadeBlue">     
                      <form:input id="cname" cssClass="inputBox" size="35" maxlength="60" path="customer.name" />       
                      <form:errors path="customer.name" cssClass="redstar"/>

             <a href="#" onClick="javascript:setCustomerSearchFlag();popup_show('companyname', 'companyname_drag', 'companyname_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();"> <img src="images/lookup.gif" alt="lookup Customer Name" width="13" height="13" border="0"></a>
                     
            </td>                         
          <td  class="TDShade"><f:message key="custCode"/><strong><span class="redstar">*:</span></strong></td> 
<%--  <td  id="ccode" style="visibility:hidden" class="TDShadeBlue"> --%>
        <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <td  id="ccode" style="visibility:visible" class="TDShadeBlue"> 
        ${command.customer.custCode }
              </c:when>
              <c:otherwise>
              <td  id="ccode" style="visibility:hidden" class="TDShadeBlue"> 
        <form:input id="custCode" cssClass="inputBox" size="35" maxlength="15" path="customer.custCode" />
              </c:otherwise>
              </c:choose>
              
                        
                      <form:errors path="customer.custCode" cssClass="redstar"/>
          </td>
      </tr>  
      <tr>  
      	<td  class="TDShade"><f:message key="alternateName"/></td>
        <td width="30%" class="TDShadeBlue"><form:input id="alternateName" cssClass="inputBox" size="35" maxlength="60" path="customer.alternateName" /></td>
		<td class="TDShade"><f:message key="taxpayerIDNumber"/></td>
		<td colspan="1" class="TDShadeBlue"><form:input id="prcust" cssClass="inputBox" maxlength="60" path="customer.taxpayerIDNumber" size="35"/><form:errors path="customer.taxpayerIDNumber" cssClass="redstar"/></td>
		</tr>
        <tr>
		<td class="TDShade"><f:message key="locationType"/></td>
        <td class="TDShadeBlue">
      <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:select id="sel5" cssClass="selectionBox" path="customer.locationType" items="${icbfn:dropdown('staticDropdown',locationType)}" itemLabel="name" itemValue="value" onchange="locType()" disabled="true"/>
              </c:when>
              <c:otherwise>
              <form:select id="sel5" cssClass="selectionBox" path="customer.locationType" items="${icbfn:dropdown('staticDropdown',locationType)}" itemLabel="name" itemValue="value" onchange="locType()"/>
              </c:otherwise>
            </c:choose>
        
     <form:errors path="customer.locationType" cssClass="redstar"/> </td>
	<td class="TDShade"><f:message key="customerSince"/></td>
    <td class="TDShadeBlue">
      <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:input cssClass="inputBox" id="sinceDate" path="customer.sinceDate" size="10" maxlength="12" disabled="true"/>
              </c:when>
              <c:otherwise>
              <form:input cssClass="inputBox" id="sinceDate" path="customer.sinceDate" size="10" maxlength="12"/>
              </c:otherwise>
              </c:choose>
    
    <a href="#" onClick="displayCalendar(document.forms[0].sinceDate,'MM/dd/yyyy',this)"> 
    <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>          
    </td>
    </tr>
	<tr>
       <td width="20%" class="TDShade"><f:message key="parentCustomerName"/></td>
        <td width="30%" class="TDShadeBlue"> 
    <c:choose>
              <c:when test="${command.customer.locationType != null && command.customer.locationType =='04'}">
        <div id="pcust" style="visibility:hidden">  
        </c:when>
        <c:otherwise>
        <div id="pcust" style="visibility:visible">  
        </c:otherwise>
        </c:choose>
      <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:input id="prcust" cssClass="inputBox" maxlength="90" path="customer.parentCustomer.name" size="25" disabled="true"/>
				<form:checkbox id="isChild" path="customer.isChildCustomer" disabled="true"/>
              </c:when>
              <c:otherwise>
              <form:input id="prcust" cssClass="inputBox" maxlength="60" path="customer.parentCustomer.name" size="25"/>
              <a href="#" onClick="javascript:popup_show('parentcust', 'parentcust_drag', 'parentcust_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('parentcust','parentcustbody')"> <img src="images/lookup.gif" alt="lookup Customer Name" width="13" height="13" border="0"></a>&nbsp&nbsp&nbsp
			  
			 <form:checkbox id="isChild" path="customer.isChildCustomer" disabled="true"/>
              </c:otherwise>
        </c:choose>
			   <form:errors path="customer.parentCustomer.name" cssClass="redstar"/>
        </div>
        </td> 
		             <td class="TDShade"><f:message key="dateAdded"/>:<strong></strong></td>
                    <td  class="TDShadeBlue">
                    <c:choose>
          <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
                   <form:input id="addDate" cssClass="inputBox" path="customer.addDate" size="10" maxlength="12" disabled="true"/>
          </c:when>
          <c:otherwise>
          <form:input id="addDate" cssClass="inputBox" path="customer.addDate" size="10" maxlength="12"/>
          <a href="#" onClick="displayCalendar(document.forms[0].addDate,'MM/dd/yyyy',this)"> 
          <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
          <div id="debug"></div> 
          </c:otherwise>
          </c:choose>
                   
          </td>
	</tr>
              <tr>
       
		   <td class="TDShade"><f:message key="currency"/></td>
		<td class="TDShadeBlue">
		
		  <c:choose>
				  <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
				  <form:select id="sel1" cssClass="selectionBox" path="customer.currencyCd" items="${icbfn:dropdown('currency', null)}" itemLabel="name" itemValue="value" disabled="true"/>
				  </c:when>
				  <c:otherwise>
				  <form:select id="sel1" cssClass="selectionBox" path="customer.currencyCd" items="${icbfn:dropdown('currency', null)}" itemLabel="name" itemValue="value"/>
				  </c:otherwise>
				</c:choose>
				
		<form:errors path="customer.currencyCd" cssClass="redstar"/>
		</td>
		<td class="TDShade"><f:message key="Dunn&BradsheetNo"/>:</td>
		<td class="TDShadeBlue">
		<form:input id="dbnum" cssClass="inputBox" maxlength="20" path="customer.dunnAndBradstreetNumber" size="20" disabled="true"/>
		</td>
		</tr>
		<tr>
		<td class="TDShade" colspan="4"><f:message key="internalOrigin"/>:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp
		 <f:message key="businessDivisions"/>:&nbsp
		 <form:select cssClass="selectionBox" id="sel7" path="customer.businessDivisions" items="${icbfn:dropdown('staticDropdown',businessDivisions)}" itemLabel="name" itemValue="value"/><form:errors path="customer.businessDivisions" cssClass="error"/>
		&nbsp&nbsp&nbsp
		 <f:message key="businessStreams"/>:&nbsp
		<form:select cssClass="selectionBox" id="sel7" path="customer.businessStreams" items="${icbfn:dropdown('staticDropdown',businessStreams)}" itemLabel="name" itemValue="value"/><form:errors path="customer.businessStreams" cssClass="error"/>
		&nbsp<f:message key="originSource"/>:&nbsp
		<form:select cssClass="selectionBox" id="sel7" path="customer.originSource" items="${icbfn:dropdown('staticDropdown',OriginSource)}" itemLabel="name" itemValue="value"/><form:errors path="customer.originSource" cssClass="error"/>
		</td>
      </tr>

<script type="text/javascript">
check();
function check(){
    if(document.getElementById("interunit").checked)
    {     document.getElementById("interunitpend").checked=false;
            document.getElementById("interunitpend").disabled=true;
      document.getElementById("busunit").style.visibility="visible";
      document.getElementById("ccode").style.visibility = "visible"; 
     // document.getElementById("anchor").style.visibility="visible";
      document.getElementById("busunit").className="unprotected";
      document.getElementById("bunit").disabled=false;
    }

    if(document.getElementById("interunitpend").checked)
      {
      document.getElementById("busunit").style.visibility="hidden";
      document.getElementById("ccode").style.visibility = "visible"; 
     // document.getElementById("anchor").style.visibility="hidden";
      }
  }
 
</script>
                  <tr>
   
                    
      <%--  <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:input cssClass="inputBox" size="35" maxlength="128" path="customer.name" disabled="true"/>
              </c:when>
              <c:otherwise>
              <form:input cssClass="inputBox" size="35" maxlength="128" path="customer.name" />
              </c:otherwise>
              </c:choose> --%>
              
 
                     
            
                               <td class="TDShade"><f:message key="accountOwner"/></td>
               <td class="TDShadeBlue" >  
               
        <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:input cssClass="inputBox" size="35" maxlength="128" path="customer.accountOwnerName" disabled="true"/>
              </c:when>
              <c:otherwise>
              <form:input cssClass="inputBox" size="35" maxlength="128" path="customer.accountOwnerName" />
            <a href="#" onClick="javascript:popup_show('accountOwner', 'accountOwner_drag', 'accountOwner_exit', 'screen-corner', 120, 20); popupboxenable();hideIt();hideUsersearch();">
       <img src="images/lookup.gif" alt="lookup Account Owner"  width="13" height="13" border="0">
      </a> 
              </c:otherwise>
              </c:choose>  
               <form:errors path="customer.accountOwnerName" cssClass="redstar"/> 
               </td>
          <td class="TDShade"><f:message key="invoiceType"/>: </td>
          <td class="TDShadeBlue">
          <c:choose>
          <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
          <form:select id ="sel4"cssClass="selectionBox" path="customer.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value" disabled="true"/>
          </c:when>
          <c:otherwise>
          <form:select id ="sel4"cssClass="selectionBox" path="customer.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value"/>
          </c:otherwise>
          </c:choose>
          <form:errors path="customer.invoiceType" cssClass="redstar"/>     </td>  

                  </tr>
                  
    
	<tr>
		<td colspan="1" class="TDShade"><f:message key="customerType"/>:</td>
		<td colspan="2" class="TDShade">
		<form:radiobutton path="customer.customerType" value="government"/>Government 
		&nbsp;&nbsp;&nbsp;
		<form:radiobutton path="customer.customerType" value="publiclyTraded"/>Publicly Traded
		&nbsp;&nbsp;&nbsp;
		<form:radiobutton path="customer.customerType" value="privateType"/>Private 
		&nbsp;&nbsp;&nbsp;
		<form:radiobutton path="customer.customerType" value="houseHold"/>Household
		</td>
		<td class="TDShade"></td>
	</tr>
<tr>
<td class="TDShade"><f:message key="industry"/> :</td>
<td  class="TDShadeBlue">
<c:choose>
<c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
<form:select id ="sel2" cssClass="selectionBox" path="customer.industry" items="${icbfn:dropdown('staticDropdown',industryType)}" itemLabel="name" itemValue="value" disabled="true"/>
</c:when>
<c:otherwise>
<form:select id ="sel2" cssClass="selectionBox" path="customer.industry" items="${icbfn:dropdown('staticDropdown',industryType)}" itemLabel="name" itemValue="value"/>
</c:otherwise>
</c:choose>
<form:errors path="customer.industry" cssClass="redstar"/>
</td>
<td class="TDShade"></td>
<td  class="TDShadeBlue">
</tr>
		  <tr>
		  <td class="TDShade" colspan="4"><f:message key="primaryIndustry"/>:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
          &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<form:select cssClass="selectionBox" id="sel8" path="customer.primaryIndustry" items="${icbfn:dropdown('staticDropdown',secondaryIndustry)}" itemLabel="name" itemValue="value"/><form:errors path="customer.primaryIndustry" cssClass="error"/>
          </td>
		  </tr>
		  </tr>
		  <td class="TDShade" colspan="4"><f:message key="secondaryIndustry"/>:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
         &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<form:select cssClass="selectionBox" id="sel8" path="customer.secondaryIndustry" items="${icbfn:dropdown('staticDropdown',secondaryIndustry)}" itemLabel="name" itemValue="value"/><form:errors path="customer.secondaryIndustry" cssClass="error"/>
          </td> 
	</tr>
		<tr>
	<td class="TDShade" nowrap><f:message key="referralParentCustomer"/>:</td>
	<td class="TDShadeBlue">
	<form:select cssClass="selectionBox" id="sel12" path="customer.parentCustomerName" items="${icbfn:dropdown('customer',parentCustomers)}" itemLabel="name" itemValue="value"/>
	<form:errors path="customer.parentCustomerName" cssClass="error"/></td>
	<td class="TDShade" nowrap>
	<f:message key="referralChildCustomer"/>:</td>
	<td class="TDShadeBlue"><form:select cssClass="selectionBox" id="sel3" path="customer.childCustomerName" items="${icbfn:dropdown('customer',childCustomers)}" itemLabel="name" itemValue="value"/><form:errors path="customer.childCustomerName" cssClass="error"/>&nbsp&nbsp&nbsp&nbsp&nbsp
	 </td>
    </tr>
	<tr>
	<td class="TDShade" colspan="4"><f:message key="referral"/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<f:message key="custNotes"/>:&nbsp&nbsp
	 <form:input cssClass="inputBox" path="customer.notes" size="35" maxlength="256"/>
	 <form:errors path="customer.notes" cssClass="error"/></td>
	</tr>
    <tr>
            <td class="TDShade"><f:message key="revenueTier"/>:</td>
            <td class="TDShadeBlue">
              <c:choose>
                      <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
                      <form:select id="sel2" cssClass="selectionBox" path="customer.type" items="${icbfn:dropdown('staticDropdown',customerType)}" itemLabel="name" itemValue="value" disabled="true"/>
                      </c:when>
                      <c:otherwise>
                      <form:select id="sel2" cssClass="selectionBox" path="customer.type" items="${icbfn:dropdown('staticDropdown',customerType)}" itemLabel="name" itemValue="value"/>
                      </c:otherwise>
                    </c:choose>
            
            
            <form:errors path="customer.type" cssClass="redstar"/></td>
             <td class="TDShade"><f:message key="vipPlatinumPreferred"/>:</td>
			<td class="TDShadeBlue"><form:select cssClass="selectionBox" id="sel10" path="customer.vipPlatinumPreferred" items="${icbfn:dropdown('staticDropdown',vipPlatinumPrefer)}" itemLabel="name" itemValue="value"/>
			</td> 
    </tr>
	<tr>
	<td class="TDShade"><f:message key="lastServiceDate"/>:</td>
    <td class="TDShadeBlue">${command.customer.lastServiceDate}</td>
	<td class="TDShade"><f:message key="lastServiceLocation"/>:</td>
    <td class="TDShadeBlue">${command.customer.lastServiceLocation}</td>
	</tr>
	<tr>
	<td class="TDShade"><f:message key="creditHold"/>: </td>
    <td  class="TDShadeBlue">
    <c:choose>
    <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
    <%--<form:checkbox path="customer.creditHoldInd" value="1" disabled="true"/>--%>
	<form:checkbox id="chd" path="customer.creditHoldInd" value="1" disabled="true"/>
    </c:when>
    <c:otherwise>
   <%-- <form:checkbox path="customer.creditHoldInd" value="1"/> --%>
    <form:checkbox id="chd" path="customer.creditHoldInd" value="1"/>
    </c:otherwise>
    </c:choose>
    <form:errors path="customer.creditHoldInd" cssClass="redstar"/> &nbsp&nbsp&nbsp
	<a href="#" onClick="javascript:updateCustomerNotesIframeSrc();popup_show('viewCustomerNote', 'viewCustomerNote_drag', 'viewCustomerNote_exit', 'screen-corner', 120, 20); hideIt();showPopupDiv('viewCustomerNote','viewCustomerNotebody');popupboxenable();"> 
                            <img src=" images/icoaddnote.gif" alt="Notes" width="18" height="15" border="0" />
    </a>
    </td>
	<td class="TDShade"><f:message key="creditLimit"/>:</td>
    <td class="TDShadeBlue">
    <c:choose>
    <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
    <form:input cssClass="inputBox" path="customer.creditLimit" size="10" maxlength="12" disabled="true"/>
    </c:when>
    <c:otherwise>
    <form:input cssClass="inputBox" path="customer.creditLimit" size="10" maxlength="12"/>
    </c:otherwise>
    </c:choose>
    <form:errors path="customer.creditLimit" cssClass="redstar"/>
    </td> 
	</tr>
	<tr>
  <td class="TDShade"><f:message key="creditAnalyst"/></td>
  <td class="TDShadeBlue" >
        <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:input cssClass="inputBox" maxlength="40" path="customer.creditAnalystName" size="35" disabled="true"/>
              </c:when>
              <c:otherwise>
              <form:input cssClass="inputBox" maxlength="40" path="customer.creditAnalystName" size="30"/>
              <a href="#" onClick="javascript:popup_show('creditan', 'creditan_drag', 'creditan_exit', 'screen-corner', 120, 20); popupboxenable();hideIt(); hidecreditansearch();">
  <img src="images/lookup.gif" alt="lookup Credit Analyst" width="13" height="13" border="0"></a>
              </c:otherwise>
            </c:choose>
  
  <form:errors path="customer.creditAnalystName" cssClass="redstar"/>
  
  </td>

  <td class="TDShade"><span class="TDShade" ><f:message key="collector"/></span></td>
  <td colspan="2" class="TDShadeBlue">
  
      <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <c:set var="roleFlag" value="false"/>
               <authz:authorize ifAnyGranted="Collectors">
              	<form:input cssClass="inputBox" size="35" maxlength="40" path="customer.collectorName"/>
              	<a href="#" onClick="javascript:popup_show('collector', 'collector_drag', 'collector_exit', 'screen-corner', 120, 20);popupboxenable(); hideIt();">
  				<img src="images/lookup.gif" alt="lookup Collector" width="13" height="13" border="0"></a>
              	<c:set var="roleFlag" value="true"/>
               </authz:authorize>
               <c:if test="${roleFlag=='false'}">
               	<form:input cssClass="inputBox" size="35" maxlength="40" path="customer.collectorName"  disabled="true"/>
               </c:if>
              </c:when>
              <c:otherwise>
              <form:input cssClass="inputBox" size="35" maxlength="40" path="customer.collectorName"  />
  <a href="#" onClick="javascript:popup_show('collector', 'collector_drag', 'collector_exit', 'screen-corner', 120, 20);popupboxenable(); hideIt();">
  <img src="images/lookup.gif" alt="lookup Collector" width="13" height="13" border="0"></a>
              </c:otherwise>
            </c:choose>
            
            
  
  <form:errors path="customer.collectorName" cssClass="redstar"/>
</td>
  </tr>
  <tr>
  <icb:list var="paymentType">
  <icb:item>paymentType</icb:item>
  <icb:item>${command.customer.creditApproved}</icb:item>
  </icb:list>
    <td class="TDShade" style="border-bottom:#CCCCCC dashed 1px"><f:message key="creditApplicationApproved"/>:</td>
    <td class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px">
    <c:choose>
    <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
    <form:checkbox path="customer.creditApproved" disabled="true"/>
    </c:when>
    <c:otherwise>
    <%-- <form:checkbox path="customer.creditApproved"/> --%>
	<form:checkbox id="crapp" path="customer.creditApproved" onclick="updatePaymentType();"/>
    </c:otherwise>
    </c:choose>
    </td> 
	<td class="TDShade"><f:message key="paymentType"/>:</td>
	<c:choose>
	<c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
	<td class="TDShadeBlue"><form:select id="sel70" cssClass="selectionBox" path="customer.paymentType" items="${icbfn:dropdown('staticDropdown',paymentType)}" itemLabel="name" itemValue="value" disabled="true"/><form:errors path="customer.paymentType" cssClass="redstar"/></td>
	</c:when>
	<c:otherwise>
	<td class="TDShadeBlue"><form:select id="sel70" cssClass="selectionBox" path="customer.paymentType" items="${icbfn:dropdown('staticDropdown',paymentType)}" itemLabel="name" itemValue="value"/><form:errors path="customer.paymentType" cssClass="redstar"/></td>             
	</c:otherwise>
	</c:choose>
  </tr>
<tr>
<td class="TDShade"></td>
<td class="TDShadeBlue"></td>
<td class="TDShade"><f:message key="paymentterms"/></td>
<td colspan="1" class="TDShadeBlue">
<form:select id="sel13" cssClass="selectionBoxint" path="customer.paymentTerms">
 <form:option value="-" label=" "/>
 <form:options items="${icbfn:dropdown('paymentTerms', null)}"  itemLabel="name" itemValue="value"/>
</form:select>
</td>
</tr></tbody>
              </table>
      </div>
      
        </td></tr>
        <tr><td>
          <div id="customerPhyAddr">
              
        <table width="100%" cellpadding=0 cellspacing=0 class=InnerApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan="2" width="30%"><f:message key="customerphyAddress"/></th>
             
              <th width="25%"> <a href="#" onClick="javascript:updateCustAddrMultilingualIframeSrc('${command.addCustomerAddresses[0].custAddresses[0].id}');popup_show('customeraddr_additionalang', 'customeraddr_additional_lang_drag', 'customeraddr_additional_lang_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('customeraddr_additionalang','customeraddr_additional_lang_body');popupboxenable();"><f:message key="additionalLanguageInformation"/></a></th>  
              
          
                    <th width="30%" style="text-align:right">&nbsp;</th>
                  </tr>
               
    
          <tr></tr>
                  <tr class="InnerApplTable">
                    <td width="20%"><f:message key="country"/><strong> :</strong></td>
                    <td width="30%">
                    
              <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
               <form:select id="sel6" cssClass="selectionBox" path="addCustomerAddresses[0].custAddresses[0].country" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" disabled="true"/>
              </c:when>
              <c:otherwise>
              <form:select id="sel6" cssClass="selectionBox" path="addCustomerAddresses[0].custAddresses[0].country" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value"/>
              </c:otherwise>
            </c:choose>
            
                    
</td>
                    <td width="20%">
                    <f:message key="description"/></td>
                    <td width="30%">
                    
              <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:input cssClass="inputBox" size="20" maxlength="70" path="addCustomerAddresses[0].custAddrSeq.addressDescr" disabled="true"/>
              </c:when>
              <c:otherwise>
        <form:input cssClass="inputBox" size="20" maxlength="70" path="addCustomerAddresses[0].custAddrSeq.addressDescr" />
              </c:otherwise>
            </c:choose>
             
                    
                    </td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="effectiveDate"/><strong>:</strong> </td>
                   <td>
                   
              <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
                   <form:input cssClass="inputBox" id="effDate0" size="30"  path="addCustomerAddresses[0].custAddresses[0].effDate" disabled="true"/>
              </c:when>
              <c:otherwise>
                   <form:input cssClass="inputBox" id="effDate0" size="30"  path="addCustomerAddresses[0].custAddresses[0].effDate" />
                        <a href="#" onClick="displayCalendar(document.forms[0].effDate0,'MM/dd/yyyy',this)"> 
            <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
              </c:otherwise>
            </c:choose> 
                   

            <form:errors path="addCustomerAddresses[0].custAddresses[0].effDate" cssClass="redstar"/>


           
           </td>
              <%--      <td ><f:message key="taxCode"/><strong>:</strong></std>
                 <td >          
         <form:input cssClass="inputBox" size="35" maxlength="3" path="addCustomerAddresses[0].custAddresses[0].taxCode"/>
          <form:errors path="addCustomerAddresses[0].custAddresses[0].taxCode" cssClass="redstar"/> 

                              <img src="images/lookup.gif" alt="lookup country" width="13" height="13" border="0" /></td>--%>
                <td ><f:message key="taxCode"/><strong>:</strong></td>
        <td >
        
        
      <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
        <form:input cssClass="inputBox" size="30" maxlength="3" path="addCustomerAddresses[0].custAddresses[0].taxCode" disabled="true"/>
              </c:when>
              <c:otherwise>
          <form:input cssClass="inputBox" size="30" maxlength="3" path="addCustomerAddresses[0].custAddresses[0].taxCode"/>
        <a href="#" onClick="javascript: updatevatCodeIframeSrc();popup_show('vatcode', 'vatcode_drag', 'vatcode_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();">
        <img src="images/lookup.gif" alt="lookup Tax Code" width="13" height="13" border="0" />         
              </c:otherwise>
            </c:choose>
            
                      

        <form:errors path="addCustomerAddresses[0].custAddresses[0].taxCode" cssClass="redstar"/> 
</td>

                  </tr>

                  <tr class="InnerApplTable">
                    <td><f:message key="address1"/><strong>:</strong> </td>
                   <td>
                   
            <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
        <form:input cssClass="inputBox" size="30" maxlength="70" path="addCustomerAddresses[0].custAddresses[0].address1" disabled="true"/>
              </c:when>
              <c:otherwise>
        <form:input cssClass="inputBox" size="30" maxlength="55" path="addCustomerAddresses[0].custAddresses[0].address1" />
              </c:otherwise>
            </c:choose>
                    
                   
                   
                   </td>
                    <td ><f:message key="address2"/><strong>:</strong></std>
                 <td >
                 
            <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:input cssClass="inputBox" size="30" maxlength="55" path="addCustomerAddresses[0].custAddresses[0].address2" disabled="true"/>
              </c:when>
              <c:otherwise>
        <form:input cssClass="inputBox" size="30" maxlength="55" path="addCustomerAddresses[0].custAddresses[0].address2" />
              </c:otherwise>
            </c:choose>
            
            
                 
                 </td>
                  </tr>
                <tr class="InnerApplTable">
                   <td><f:message key="address3"/><strong>:</strong></td>
                   <td>
                   
            <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
               <form:input cssClass="inputBox" size="30" maxlength="55" path="addCustomerAddresses[0].custAddresses[0].address3" disabled="true"/>
              </c:when>
              <c:otherwise>
                <form:input cssClass="inputBox" size="30" maxlength="55" path="addCustomerAddresses[0].custAddresses[0].address3" />
              </c:otherwise>
            </c:choose>
            
            
                   
      </td>
                   <td><f:message key="address4"/><strong>:</strong> </td>
                   <td>
          
            <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:input cssClass="inputBox" size="30"  path="addCustomerAddresses[0].custAddresses[0].address4" disabled="true"/>
              </c:when>
              <c:otherwise>
        <form:input cssClass="inputBox" size="30"  path="addCustomerAddresses[0].custAddresses[0].address4" />
              </c:otherwise>
            </c:choose>
                     
                   
                   </td>
                </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="city"/><strong>:</strong></td>
             <td>
             
      <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:input cssClass="inputBox" size="20" maxlength="40" path="addCustomerAddresses[0].custAddresses[0].city" disabled="true"/>
              </c:when>
              <c:otherwise>
        <form:input cssClass="inputBox" size="20" maxlength="30" path="addCustomerAddresses[0].custAddresses[0].city" />
              </c:otherwise>
            </c:choose>
                         
       &nbsp;
       <f:message key="inCityLimit"/>
       
        <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:checkbox path="addCustomerAddresses[0].custAddresses[0].inCityLimit" disabled="true"/>
              </c:when>
              <c:otherwise>
        <form:checkbox path="addCustomerAddresses[0].custAddresses[0].inCityLimit" />
              </c:otherwise>
            </c:choose>
                   
           
          <form:errors path="addCustomerAddresses[0].custAddresses[0].inCityLimit" cssClass="redstar"/>
      </td>
                    <td ><f:message key="county"/><strong>:</strong></td>
                    <td >
                    
      <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
              <form:input cssClass="inputBox" size="20"  path="addCustomerAddresses[0].custAddresses[0].county" disabled="true"/>
              </c:when>
              <c:otherwise>
        <form:input cssClass="inputBox" size="20"  path="addCustomerAddresses[0].custAddresses[0].county" />
              </c:otherwise>
            </c:choose>
            
                                
                    
                    </td>
                  </tr>
                  <tr class="InnerApplTable">
        <td><f:message key="state"/><strong>:</strong></td>
        <td><icb:list var="countryCodeList">
        <icb:item>${command.addCustomerAddresses[0].custAddresses[0].country}</icb:item>
        </icb:list> 
        
      <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
        <form:select id="sel8" cssClass="selectionBox" path="addCustomerAddresses[0].custAddresses[0].state"
        items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name"
         itemValue="value" disabled="true"/>
              </c:when>
              <c:otherwise>
        <form:select id="sel8" cssClass="selectionBox" path="addCustomerAddresses[0].custAddresses[0].state"
        items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name"
         itemValue="value"/>
              </c:otherwise>
            </c:choose>
            
                    
        
         
         </td>
                    
                    <td><f:message key="postal"/><strong>:</strong></td>
                    <td>
                    
      <c:choose>
              <c:when test="${command.customerExistsFlag != null && command.customerExistsFlag =='Y'}">
        <form:input cssClass="inputBox" size="20" maxlength="12" path="addCustomerAddresses[0].custAddresses[0].postal" disabled="true"/>
              </c:when>
              <c:otherwise>
        <form:input cssClass="inputBox" size="20" maxlength="12" path="addCustomerAddresses[0].custAddresses[0].postal" />
              </c:otherwise>
            </c:choose>
                                
                    </td>
                  </tr>
                </tbody>
              </table>
        </div>
        </td></tr>   


        
        <tr><td>
          <div id="contact">
      <input type="hidden" value="${command.contactCust.custAddrSeq.custAddrSeqId.locationNumber}" id="oldlocation" name="oldlocation"/>
          <c:choose>
              <c:when test="${command.contactExistsFlag != null && command.contactExistsFlag =='Y'}">

  
      
        <table width="100%" cellpadding=0 cellspacing=0 class=InnerApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=4><f:message key="contact"/></th>
                    </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="contactID"/><strong> :</strong></td>
                    <td>  <form:input cssClass="inputBox" size="20"  path="contact.id"  disabled="true"/>
                      <%-- <a href="#" onClick="javascript:setContactSearchFlag();popup_show('contactid', 'contact_drag', 'contact_exit', 'screen-corner', 120, 20);popupboxenable();hideIt();">
           <img src="images/lookup.gif" alt="lookup contact" width="13" height="13" border="0"> </a> --%>
      
       <a href="#" onClick="javascript:setContactSearchFlag();updateContactIframeSrc('${command.customer.custCode}');popup_show('contactid', 'contact_drag', 'contact_exit', 'screen-corner', 120, 20);popupboxenable();hideIt();>
           <img src="images/lookup.gif" alt="lookup contact" width="13" height="13" border="0"> </a>
     
       
       
       </td>
                    
          <td><f:message key="contactSeqNum"/></td>
                    <td><form:input cssClass="inputBox" size="20" maxlength="3" path="contactCust.contactSeqNum" disabled="true"/></td>

                  </tr>
                  <tr class="InnerApplTable">
                    <td width="20%"><f:message key="firstName"/><strong> :</strong></td>
                    <td width="30%"><form:input cssClass="inputBox" size="20" maxlength="128" path="contact.firstName" />
           <a href="#" onClick="javascript:popup_show('country', 'country_drag', 'country_exit', 'screen-corner', 40, 80);showcountryframe();hideIt()"></a></td>
                    <td width="20%"><f:message key="lastName"/><strong> :</strong> </td>
                    <td width="30%"><form:input cssClass="inputBox" size="20" maxlength="128" path="contact.lastName" /></td>
                  </tr>
                   <tr class="InnerApplTable">
                    <td><f:message key="title"/></td>
                    <td><form:input maxlength="32" cssClass="inputBox" size="20"  path="contact.title" disabled="true"/></td>
                     <!-- added for the issue-104407 -->
                    <td ><f:message key="fax"/></td>
                    <td ><form:input maxlength="30" cssClass="inputBox" size="20"  path="contact.fax" disabled="true"/></td>
					<!--  END 104407 -->
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="workPhone"/></td>
                    <td><form:input maxlength="64" cssClass="inputBox" size="20"  path="contact.workPhone" disabled="true"/></td>
                    <td><f:message key="worke-mail"/></td>
                    <td><form:input  maxlength="70" cssClass="inputBox" size="35"  path="contact.workEmail" disabled="true"/>
          <form:errors path="contact.workEmail" cssClass="redstar"/></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="personalPhone"/></td>
                    <td><form:input maxlength="64" cssClass="inputBox" size="20"  path="contact.personalPhone" disabled="true"/></td>
                    <td><f:message key="personalE-mail"/></td>
                    <td><form:input cssClass="inputBox" size="35" maxlength="70" path="contact.personalEmail" disabled="true"/>
          <form:errors path="contact.personalEmail" cssClass="redstar"/></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="cellPhone"/></td>
                    <td><form:input  maxlength="64" cssClass="inputBox" size="35"  path="contact.cellPhone" disabled="true"/></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                 <%--<tr class="InnerApplTable">
                    <td><p><f:message key="schedulerContact"/></p></td>
                    <td><form:checkbox path="contact.schedulerContactFlag" value="contact.schedulerContactFlag" disabled="true"/></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><p><f:message key="billingContact"/></p></td>
                    <td><form:checkbox path="contact.billContactFlag" value="contact.billContactFlag" disabled="true"/></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><p><f:message key="reportingContact"/></p></td>
                    <td><form:checkbox path="contact.reportContactFlag" value="contact.reportContactFlag" disabled="true"/></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>--%>
                </tbody>
              </table>
 
              </c:when>
              <c:otherwise>

                          
        <table width="100%" cellpadding=0 cellspacing=0 class=InnerApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=4><f:message key="contact"/></th>
                    </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="contactID"/><strong> :</strong></td>
                    <td>  <form:input cssClass="inputBox" size="20"  path="contact.id"  />
                     <%-- <a href="#" onClick="javascript:setContactSearchFlag();popup_show('contactid', 'contact_drag', 'contact_exit', 'screen-corner', 120, 20);popupboxenable();hideIt();">
                    <img src="images/lookup.gif" alt="lookup contact" width="13" height="13" border="0"> </a> --%>
          
          <a href="#" onClick="javascript:setContactSearchFlag();updateContactIframeSrc('${command.customer.custCode}');popup_show('contactid', 'contact_drag', 'contact_exit', 'screen-corner', 120, 20);popupboxenable();hideIt();">
                    <img src="images/lookup.gif" alt="lookup contact" width="13" height="13" border="0"> </a>

          </td>
                    <td><f:message key="contactSeqNum"/></td>
                    <td><form:input cssClass="inputBox" size="20" maxlength="3" path="contactCust.contactSeqNum" disabled="true"/></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td width="20%"><f:message key="firstName"/><strong> :</strong></td>
                    <td width="30%"><form:input cssClass="inputBox" size="20" maxlength="128" path="contact.firstName" />
           <a href="#" onClick="javascript:popup_show('country', 'country_drag', 'country_exit', 'screen-corner', 40, 80);showcountryframe();hideIt()"></a></td>
                    <td width="20%"><f:message key="lastName"/><strong> :</strong> </td>
                    <td width="30%"><form:input cssClass="inputBox" size="20" maxlength="128" path="contact.lastName" /></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="title"/></td>
                    <td><form:input maxlength="32" cssClass="inputBox" size="20"  path="contact.title" /></td>            
                     <!--  added for the issue-104407-->
                    <td ><f:message key="fax"/></td>
                    <td ><form:input maxlength="30" cssClass="inputBox" size="20"  path="contact.fax" /></td>
                    <!-- END  104407-->
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="workPhone"/></td>
                    <td><form:input maxlength="64" cssClass="inputBox" size="20"  path="contact.workPhone" /></td>
                    <td><f:message key="worke-mail"/></td>
                    <td><form:input  maxlength="70" cssClass="inputBox" size="35"  path="contact.workEmail" />
          <form:errors path="contact.workEmail" cssClass="redstar"/></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="personalPhone"/></td>
                    <td><form:input maxlength="64" cssClass="inputBox" size="20"  path="contact.personalPhone" /></td>
                    <td><f:message key="personalE-mail"/></td>
                    <td><form:input cssClass="inputBox" size="35" maxlength="70" path="contact.personalEmail"/>
          <form:errors path="contact.personalEmail" cssClass="redstar"/></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="cellPhone"/></td>
                    <td><form:input  maxlength="64" cssClass="inputBox" size="35"  path="contact.cellPhone" /></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                 <%--  <tr class="InnerApplTable">
                    <td><p><f:message key="schedulerContact"/></p></td>
                    <td> <form:checkbox path="contact.schedulerContactFlag"/></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><p><f:message key="billingContact"/></p></td>
                    <td><form:checkbox path="contact.billContactFlag"/></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><p><f:message key="reportingContact"/></p></td>
                    <td><form:checkbox path="contact.reportContactFlag"/></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>--%>
                </tbody>
              </table>
               </c:otherwise>
            </c:choose> 
              
        </div>
        </td></tr>  



          <tr><td>
        <div id="contactAddress">

        <table width="100%" cellpadding=0 cellspacing=0 class=InnerApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan="2">Contact Address</th>
            <c:choose>
            <c:when test="${command.contactAddrIndicator == 'NewAddr' }">
            <th width="30%"> <a style="visibility:vissible" href="#" onClick="javascript:updateContactAddrMultilingualIframeSrc('${command.addCustomerAddresses[1].custAddresses[1].id}');popup_show('customeraddr_additionalang', 'customeraddr_additional_lang_drag', 'customeraddr_additional_lang_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('customeraddr_additionalang','customeraddr_additional_lang_body');popupboxenable();"><f:message key="additionalLanguageInformation"/></a></th>   
            </c:when>
            <c:otherwise>
           <th width="30%"> <a id="addLang" style="visibility:hidden" href="#" onClick="javascript:updateContactAddrMultilingualIframeSrc('${command.addCustomerAddresses[1].custAddresses[1].id}');popup_show('customeraddr_additionalang', 'customeraddr_additional_lang_drag', 'customeraddr_additional_lang_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('customeraddr_additionalang','customeraddr_additional_lang_body');popupboxenable();"><f:message key="additionalLanguageInformation"/></a></th> 
            </c:otherwise>
            </c:choose>
                    </tr>
                  <tr class="InnerApplTable">
                    <td width="35%">
          <form:radiobutton id="contactAddrIndicator" path="contactAddrIndicator" value="primaryAddr" onclick="OptCustPrimaryAddress()" />Customer Primary Address</td>
          <td width="35%">
          <form:radiobutton id="contactAddrIndicator" path="contactAddrIndicator"  value="existingAddr" onclick="OptExistingAddress()"/>Existing Address
          </td>
          <td widht="35%">
          <form:radiobutton id="contactAddrIndicator" path="contactAddrIndicator" value="NewAddr" onclick="OptNewAddress();"/>New Address</td>
      <script type="text/javascript">
      function newAddrcheck(){
          document.getElementById("addLang").style.visibility = "visible";
        }
      </script>
                  </tr>
                  <tr class="InnerApplTable">
                    <td colspan="4">
                    <c:choose>
                    <c:when test="${command.contactAddrIndicator == 'existingAddr' }">
          <div id="ExistingAddress" style="width:100%; visibility:visible;display:block; margin-left:2px; ">
          </c:when>
          <c:otherwise>
          <div id="ExistingAddress" style="width:100%; visibility:hidden;display:none; margin-left:2px; ">
          </c:otherwise>
          </c:choose>
        <table width="100%" cellpadding=0 cellspacing=0 class=InnerApplTable style="border-top:0px;border-right:0px;border-bottom:0px;border-left:0px;">
                <tbody>
                  <tr>
                    <td width="20%"><f:message key="location"/><strong> :</strong></td>
                    <td>
                    <c:choose>
                    <c:when test="${command.contactAddrIndicator == 'existingAddr' && command.contactCustExistsFlag == 'Y' }">
                    <form:input cssClass="inputBox" size="20"  maxlength="3" path="contactCust.custAddrSeq.custAddrSeqId.locationNumber" disabled="true"/>
                    </c:when>
                    <c:otherwise>
                    <form:input cssClass="inputBox" size="20"  maxlength="3" path="contactCust.custAddrSeq.custAddrSeqId.locationNumber" />
                    <a href="#" onClick="javascript:setLocationSearchFlag();popup_show('addressseq0', 'addressseq_drag0', 'addressseq_exit0', 'screen-corner', 120, 20);hideIt();popupboxenable();"><img src="images/lookup.gif" alt="lookup Location" width="13" height="13" border="0" /></a>
                    </c:otherwise>
                    </c:choose>
          
       </td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </tbody>
              </table>
          </div>

      <c:choose>
           <c:when test="${command.contactAddrIndicator == 'NewAddr' }">
           <div id="NewAddress" style="width:98%; visibility:visible;display:block; margin-left:5px; ">
          </c:when>
          <c:otherwise>
          <div id="NewAddress" style="width:98%; visibility:hidden;display:none; margin-left:5px; ">
          </c:otherwise>
          </c:choose>
        <%--<div id="NewAddress" style="width:98%; visibility:hidden;display:none; margin-left:5px; ">--%>
        <table width="100%" cellpadding=0 cellspacing=0 class=InnerApplTable style="border-top:0px;border-right:0px;border-bottom:0px;border-left:0px;">
                <tbody>
                  <tr class="InnerApplTable">
                    <td width="20%"><f:message key="country"/><strong> :</strong></td>
                    <td width="30%"><form:select cssClass="selectionBox" path="addCustomerAddresses[1].custAddresses[0].country" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />  <a href="#" onClick="javascript:popup_show('country', 'country_drag', 'country_exit', 'screen-corner', 120, 20);showcountryframe();hideIt();popupboxenable();"> <img src="images/lookup.gif" alt="lookup country" width="13" height="13" border="0" /></a></td>

            <td width="20%"><f:message key="description"/>:</td>
                    <td width="30%"><form:input cssClass="inputBox" size="20" maxlength="70" path="addCustomerAddresses[1].custAddrSeq.addressDescr" /></td>  
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="address1"/>:</td>
                   <td><form:input cssClass="inputBox" size="20" maxlength="55" path="addCustomerAddresses[1].custAddresses[0].address1" /></td>
                    <td ><f:message key="address2"/></td>
                    <td ><form:input cssClass="inputBox" size="20" maxlength="55" path="addCustomerAddresses[1].custAddresses[0].address2" /></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="address3"/>:</td>
                    <td><form:input cssClass="inputBox" size="20" maxlength="55" path="addCustomerAddresses[1].custAddresses[0].address3" /></td>
                    <td><f:message key="address4"/>:</td>
                    <td><form:input cssClass="inputBox" size="20" maxlength="55" path="addCustomerAddresses[1].custAddresses[0].address4" /></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="city"/>:</td>
                    <td><form:input cssClass="inputBox" size="20" maxlength="30"  path="addCustomerAddresses[1].custAddresses[0].city" /></td>
                    <td ><f:message key="county"/>:</td>
                    <td ><form:input cssClass="inputBox" size="20" maxlength="30" path="addCustomerAddresses[1].custAddresses[0].county" /></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="state"/><strong>:</strong></td>
        <td><icb:list var="countryCodeList1">
        <icb:item>${command.addCustomerAddresses[1].custAddresses[0].country}</icb:item>
        </icb:list> 
        <form:select cssClass="selectionBox" path="addCustomerAddresses[1].custAddresses[0].state"
        items="${icbfn:dropdown('state', countryCodeList1)}" itemLabel="name"
         itemValue="value" />
         </td>
                    <td><f:message key="postal"/>:</td>
                    <td><form:input cssClass="inputBox" size="20"  path="addCustomerAddresses[1].custAddresses[0].postal" /></td>
                  </tr>
                </tbody>
              </table>
          </div>

          </td>
                  </tr>
                </tbody>
              </table>
        </div>
        </td></tr> 
        <tr><td>
        <div id="customerContract">

        <table width="100%" cellpadding=0 cellspacing=0 class=InnerApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=4><f:message key="customerContract"/><a href="#"></a></th>
                    </tr>
                  <tr class="InnerApplTable">
                    <td width="50%">
          <form:radiobutton id="contractIndicator" path="contractIndicator" value="existingContract" onclick="OptExistingContract()" />Existing Contract</td>
          <td width="50%">
          <form:radiobutton id="contractIndicator" path="contractIndicator"  value="newContract" onclick="OptNewContract()"/>New Contract
          </td>
          </tr>     
          
                  <tr class="InnerApplTable">
                    <td colspan="4">
                    <c:choose>
                    <c:when test="${command.contractIndicator == 'existingContract' }">
          <div id="ExistingContract" style="width:100%; visibility:visible;display:block; margin-left:2px; ">
          </c:when>
          <c:otherwise>
          <div id="ExistingContract" style="width:100%; visibility:hidden;display:none; margin-left:2px; ">
          </c:otherwise>
          </c:choose>
        <table width="100%" cellpadding=0 cellspacing=0 class=InnerApplTable style="border-top:0px;border-right:0px;border-bottom:0px;border-left:0px;">
                <tbody>
                  <tr>
                    <td width="20%"><f:message key="contractCode"/><strong> :</strong></td>
                    <td>
                      <form:input id="contCode" cssClass="inputBox" size="20"  path="contractCust.contractCustId.contractCode" />
                  <form:errors path="contractCust.contractCustId.contractCode" cssClass="redstar"/>
                  <a href="#" onClick="javascript:contDivEnable();popup_show('contract', 'contract_drag', 'contract_exit', 'screen-corner', 120, 20);popupboxenable();hideIt();">
                  <img src="images/lookup.gif" alt="lookup contract code" width="13" height="13" border="0"></a>&nbsp;&nbsp;
                  </td>                    
                    
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </tbody>
              </table>
          </div>
                    <c:choose>
                    <c:when test="${command.contractIndicator == 'newContract' }">
          <div id="NewContract" style="width:98%; visibility:visible;display:block; margin-left:2px; ">
          </c:when>
          <c:otherwise>
          <div id="NewContract" style="width:98%; visibility:hidden;display:none; margin-left:2px; ">
          </c:otherwise>
          </c:choose>          
        
        <table width="100%" cellpadding=0 cellspacing=0 class=InnerApplTable style="border-top:0px;border-right:0px;border-bottom:0px;border-left:0px;">
                <tbody>
                  <tr class="InnerApplTable">
                    <td width="20%"><f:message key="contractCode"/><strong>:</strong></td>
                    <td width="30%">
                    <form:input id="contractCode" cssClass="inputBox" path="contractCust.contract.contractCode" size="20" maxlength="15"/>
              <form:errors path="contractCust.contract.contractCode" cssClass="redstar"/>
          </td>

            <td width="20%"><f:message key="description"/><strong>:</strong></td>
                    <td width="30%">
                    <form:input cssClass="inputBox" path="contractCust.contract.description" size="35" maxlength="512"/>
            <form:errors path="contractCust.contract.description" cssClass="redstar"/></td>  
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="invoiceType"/><strong>:</strong></td>
                   <td><form:select cssClass="selectionBox" path="contractCust.contract.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value"/></td>
                    <td ><f:message key="paymentterms"/><strong>:</strong></td>
                    <td ><form:select cssClass="selectionBox" path="contractCust.contract.paymentTermsCD" items="${icbfn:dropdown('paymentTerms', null)}" itemLabel="name" itemValue="value"/></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="originator"/><strong>:</strong></td>
                    <td><form:input cssClass="inputBox" path="contractCust.contract.originatorUserName" size="20" maxlength="128"/>
          <a href="#" onClick="javascript:popup_show('originator', 'originator_drag', 'originator_exit', 'screen-corner', 120, 20); popupboxenable();hideIt();">
                 <img src="images/lookup.gif" alt="lookup Originator"  width="13" height="13" border="0">
                </a> 
          <form:errors path="contractCust.contract.originatorUserName" cssClass="redstar"/></td>
                    <td><f:message key="signatory"/><strong>:</strong></td>
                    <td><form:input cssClass="inputBox" path="contractCust.contract.signatoryUserName" size="20" maxlength="128"/>
                    <a href="#" onClick="javascript:popup_show('signatory', 'signatory_drag', 'signatory_exit', 'screen-corner', 120, 20); popupboxenable();hideIt();">
               <img src="images/lookup.gif" alt="lookup Signatory"  width="13" height="13" border="0">
              </a> 
          <form:errors path="contractCust.contract.signatoryUserName" cssClass="redstar"/></td>
                  </tr>
                  <tr class="InnerApplTable">
                    <td><f:message key="SubmitForApproval"/><strong>:</strong></td>
                    <td>
                      <select name="submitForApproval" id="submitForApproval" class="selectionBox" >
                        <option value="Yes" selected="true"><f:message key="Yes"/></option>
                        <option value="No"><f:message key="No"/></option>
                      </select>
                    </td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>

      <icb:list var="localCurrencyCDList">
        <icb:item>${command.cfgContract.currencyCD}</icb:item>
      </icb:list>

      <icb:list var="localCurrencyCDAndPbSeriesList">
        <icb:item>${command.cfgContract.currencyCD}</icb:item>
        <icb:item>${command.cfgContract.pbSeries}</icb:item>
      </icb:list>

      <icb:list var="localProductGroupSetList">
        <icb:item>${command.cfgContract.productGroupSet}</icb:item>
      </icb:list>

      <icb:list var="localVesselTypeSetList">
        <icb:item>${command.cfgContract.vesselSet}</icb:item>
      </icb:list>

      <icb:list var="workingUOM">
        <icb:item>workingUOM</icb:item>
      </icb:list>

      <!-- Begin Date Div Start -->
      <tr>
        <td colspan="6" style="padding:0;">

          <!-- ------------ Contract 2 CONTAINER ------------- -->
          <div id="begindate0" class="contracts">
            <table cellpadding="0" cellspacing="0" class="mainApplTable" >
              <tbody>
                <tr>
                  <td width="19%" class="TDShade" >Begin Date : </td>
                  <td width="36%" class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="cfgContract.cfgContractId.beginDate" size="10" maxlength="12"/>
                    <a href="#" onClick="displayCalendar(document.forms[0].elements['cfgContract.cfgContractId.beginDate'],'m/dd/yyyy',this)"> 
                      <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                    </a>
                    <form:errors path="cfgContract.cfgContractId.beginDate" cssClass="redstar"/>
                  </td>
                  <td width="22%" class="TDShade" ><strong>End Date :</strong></td>
                  <td colspan="2" width="24%" class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="cfgContract.endDate" size="10" maxlength="12"/>
                    <a href="#" onClick="displayCalendar(document.forms[0].elements['cfgContract.endDate'],'m/dd/yyyy',this)"> 
                      <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
                    </a>
                    <form:errors path="cfgContract.endDate" cssClass="redstar"/>
                  </td>
                  <td width="60" class="TDShadeBlue" align="right">&nbsp;</td>
                </tr>
              </tbody>
            </table>

            <!-- ----------------------------CONTRACT 2  ---------------------------------------- -->
              <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                <tr>
                  <td width="15%">Currency: </td>
                  <td>
                    <form:select onchange="javascript:changeCfgContractData('changeCurrencyCD', '0');" cssClass="selectionBox" path="cfgContract.currencyCD" items="${icbfn:dropdown('currency',null)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td width="15%">UOM:</td>
                  <td>
                    <form:select onchange="javascript:changeCfgContractData('changeUOM', '0');" cssClass="selectionBox" path="cfgContract.uom" items="${icbfn:dropdown('staticDropdown',workingUOM)}" itemLabel="name" itemValue="value"/>
                  </td>
                </tr>
                <tr>
                  <td>Pricebook Id:</td>
                  <td>
                    <form:select onchange="javascript:changeCfgContractData('changePriceBookId', '0');" cssClass="selectionBox" path="cfgContract.priceBookId" items="${icbfn:dropdown('priceBookIdDropDown',localCurrencyCDAndPbSeriesList)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td>Series:</td>
                  <td>
                    <form:select onchange="javascript:changeCfgContractData('changePriceBookSeries', '0');" cssClass="selectionBox" path="cfgContract.pbSeries" items="${icbfn:dropdown('priceBookSeriesDropDown',localCurrencyCDList)}" itemLabel="name" itemValue="value"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    Editable?: &nbsp;<form:checkbox path="cfgContract.editPB" />
                  </td>
                </tr>
                <tr>
                  <td>Annual Escalator:</td>
                  <td><form:input path="cfgContract.annualEscalator" cssClass="inputBox" />
          <form:errors path="cfgContract.annualEscalator" cssClass="redstar"/></td>
                  <td>Escalator Date: </td>
                  <td>
                    <form:input cssClass="inputBox" path="cfgContract.escalatorDate" size="10" maxlength="12"/>
                    <a href="#" onClick="displayCalendar(document.forms[0].elements['cfgContract.escalatorDate'],'m/dd/yyyy',this)"> 
                      <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
           
                    </a>
                  </td>
                </tr>                            
                <tr>
                  <td>Discount Pct:</td>
                  <td><form:input path="cfgContract.cfgDiscountPercent" cssClass="inputBox"/>
          <form:errors path="cfgContract.cfgDiscountPercent" cssClass="redstar"/>
         </td>
                  <td>Insp Discount Pct: </td>
                  <td><form:input path="cfgContract.inspectionDiscountPercent" cssClass="inputBox"/>
          <form:errors path="cfgContract.inspectionDiscountPercent" cssClass="redstar"/>
         </td>
                </tr>
                <tr>
                  <td>Test Discount Pct:</td>
                  <td><form:input path="cfgContract.testDiscount" cssClass="inputBox" />
          <form:errors path="cfgContract.testDiscount" cssClass="redstar"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hide Test Discount:&nbsp;</td>
                  <td>Slate Discount Pct: </td>
                  <td><form:input path="cfgContract.slateDiscount" cssClass="inputBox"/>
           <form:errors path="cfgContract.slateDiscount" cssClass="redstar"/>
         </td>
                </tr>
                <tr>
                  <td>Ops Discount Pct:</td>
                  <td><form:input path="cfgContract.opsDiscountPercent" cssClass="inputBox"/>
          <form:errors path="cfgContract.opsDiscountPercent" cssClass="redstar"/>
         </td>
                  <td>Lab Discount Pct:</td>
                  <td><form:input path="cfgContract.labDiscountPercent" cssClass="inputBox"/>
          <form:errors path="cfgContract.labDiscountPercent" cssClass="redstar"/>
         </td>
                </tr>
                <tr>
                  <td>Product Group Set:</td>
                  <td>
                    <form:select cssClass="selectionBox" path="cfgContract.productGroupSet" items="${icbfn:dropdown('productGroupSetDropdown',localProductGroupSetList)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td>Vessel Type Set:</td>
                  <td>
                    <form:select cssClass="selectionBox" path="cfgContract.vesselSet" items="${icbfn:dropdown('vesselTypeSetDropdown',localVesselTypeSetList)}" itemLabel="name" itemValue="value"/>
                  </td>
                </tr>
              </table>
            </div><!-- -------------CONTRACT 2 END ----------------------- -->
        </td>
      </td>
      </tr></table>
	  </td></tr>

                </tbody>
              </table>
          </div>
       <%-- Start contact type flags--%>

               
		<%-- <tr><td>
          <div id="contractCustContactDiv" style="visibility:hidden; display:none;">
				<table width="100%" cellpadding="0" cellspacing="0"  border="0" class="InnerApplTable" >
				   <tbody>
					  <tr bgcolor=#ffffff>
						 <th colspan="3"><f:message key="contractCustContact"/></th>
					  </tr>
					  
					<tr class="InnerApplTable">
                    <td><f:message key="schedulerContact"/><form:checkbox path="contractCustContact.schedulerContactFlag"/></td>
					<td><f:message key="billingContact"/><form:checkbox path="contractCustContact.billContactFlag"/></td>
					<td><f:message key="reportingContact"/><form:checkbox path="contractCustContact.reportContactFlag"/></td>
                  </tr>
                 
          <c:choose>
          <c:when test="${command.contactTypeExistsFlag == 'Y' || command.contractIndicator == 'newContract'}">              
          <script>
           showcontDivEnable();           
           function showcontDivEnable()
            {          
            document.getElementById("contractCustContactDiv").style.visibility = "visible";
            document.getElementById("contractCustContactDiv").style.display = "block";  
            }
           </script>
           </c:when>
            </c:choose>     
					  
		     </tbody>
		   </table>
          </div>
         </td></tr> --%>

		  <tr><td>
          <div id="contractCustContactDiv" style="visibility:hidden; display:none;">
				<table width="100%" cellpadding="0" cellspacing="0"  border="0" class="InnerApplTable" >
				   <tbody>
					  <tr bgcolor=#ffffff>
						 <th colspan="3"><f:message key="contractCustContact"/></th>
					  </tr>
					  
					<tr class="InnerApplTable">
                    <td><f:message key="schedulerContact"/><form:checkbox path="contractCustContact.schedulerContactFlag"/></td>
					<td><f:message key="billingContact"/><form:checkbox path="contractCustContact.billContactFlag"/></td>
					<td><f:message key="reportingContact"/><form:checkbox path="contractCustContact.reportContactFlag"/></td>
                  </tr>
				  <tr bgcolor=#ffffff>
						 <th colspan="3"><f:message key="language"/></th>
					  </tr>
					  <tr>
					<td class="TDShade"><f:message key="invoiceLabelLanguage"/>:&nbsp&nbsp&nbsp<form:select cssClass="selectionBox" id="sel10" path="contractCustContact.invoiceLabelLanguage" items="${icbfn:dropdown('staticDropdown',InvoiceLanguage)}" itemLabel="name" itemValue="value"/>
					<form:errors path="contractCustContact.invoiceLabelLanguage" cssClass="error"/></td>
					<td class="TDShade"><f:message key="invoiceLineItemLanguage"/>:&nbsp&nbsp&nbsp<form:select cssClass="selectionBox" id="sel11" path="contractCustContact.invoiceLineItemLanguage" items="${icbfn:dropdown('staticDropdown',InvoiceLanguage)}" itemLabel="name" itemValue="value"/><form:errors path="contractCustContact.invoiceLineItemLanguage" cssClass="error"/></td>
					</tr>
					<tr>
					<td class="TDShade"><f:message key="invoiceDeliveryMethod"/>:</td>
				    <td class="TDShadeBlue"> 
				    <form:select cssClass="selectionBox" id="sel8" path="contractCustContact.invoiceDeliveryMethod" items="${icbfn:dropdown('staticDropdown',invDeliveryMethod)}" itemLabel="name" itemValue="value"/><form:errors path="contractCustContact.invoiceDeliveryMethod" cssClass="error"/>
				    </td>
                    </tr>
          <c:choose>
          <c:when test="${command.contactTypeExistsFlag == 'Y' || command.contractIndicator == 'newContract'}">              
          <script>
           showcontDivEnable();           
           function showcontDivEnable()
            {          
            document.getElementById("contractCustContactDiv").style.visibility = "visible";
            document.getElementById("contractCustContactDiv").style.display = "block";  
            }
           </script>
           </c:when>
            </c:choose>     
					  
		     </tbody>
		   </table>
          </div>
         </td></tr>
		 <%-- End contact type flags--%>


          </td>
          </tr>
        </table>     
        
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt">marked fields are mandatory</span> </td>
                        <td style="text-align:right"><%--<a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a>--%>
                        <a href="#">
                       <input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="resetcust();"/></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
      </div>
      
<ajax:autocomplete
  baseUrl="customer.htm"
  source="customer.name"
  target="customer.name"
  className="autocomplete"
  parameters="customerName={customer.name}"
  minimumCharacters="1"
  postFunction="populateCustomerDetails"
   />
<ajax:autocomplete
  baseUrl="customer.htm"
  source="customer.parentCustomer.name"
  target="customer.parentCustomer.name"
  className="autocomplete"
  parameters="parentCustName={customer.parentCustomer.name}"
  minimumCharacters="1"
   />
<ajax:autocomplete
  baseUrl="customer.htm"
  source="customer.accountOwnerName"
  target="customer.accountOwnerName"
  className="autocomplete"
  parameters="accountOwnerRole={customer.accountOwnerName}"
  minimumCharacters="1"
   /> 
  <ajax:autocomplete
  baseUrl="business_unit.htm"
  source="customer.interunitBusUnitName"
  target="customer.custCode"
  className="autocomplete"
  parameters="name={customer.interunitBusUnitName}"
  minimumCharacters="1"
   />
   
   <ajax:autocomplete
  baseUrl="customer.htm"
  source="customer.creditAnalystName"
  target="customer.creditAnalystName"
  className="autocomplete"
  parameters="creditAnalystName={customer.creditAnalystName}"
  minimumCharacters="1"
   />

    <ajax:autocomplete
  baseUrl="customer.htm"
  source="customer.collectorName"
  target="customer.collectorName"
  className="autocomplete"
  parameters="collectorName={customer.collectorName}"
  minimumCharacters="1"
   />
   <ajax:autocomplete
  baseUrl="customer.htm"
  source="contactCust.custAddrSeq.custAddrSeqId.locationNumber"
  target="contactCust.custAddrSeq.custAddrSeqId.locationNumber"
  className="autocomplete"
  parameters="location={contactCust.custAddrSeq.custAddrSeqId.locationNumber},custCode={customer.custCode}"
  minimumCharacters="1"
   />


     
  <ajax:select
    baseUrl="country.htm"
    source="addCustomerAddresses[0].custAddresses[0].country"
    target="addCustomerAddresses[0].custAddresses[0].state"   
    parameters="country.countryCode={addCustomerAddresses[0].custAddresses[0].country}"
    parser="new ResponseXmlParser()" />  
   
    <ajax:select
    baseUrl="country.htm"
    source="addCustomerAddresses[1].custAddresses[0].country"
    target="addCustomerAddresses[1].custAddresses[0].state"   
    parameters="country.countryCode={addCustomerAddresses[1].custAddresses[0].country}"
    parser="new ResponseXmlParser()" />  

   <ajax:autocomplete
   baseUrl="customer.htm"
   source="contact.id"
   target="contact.id"
   className="autocomplete"
   parameters="contact.Id={contact.id},existCustCode=${command.customer.custCode}"
   minimumCharacters="1"
   />
   <ajax:autocomplete
   baseUrl="customer.htm"
   source="contractCust.contractCustId.contractCode"
   target="contractCust.contractCustId.contractCode"
   className="autocomplete"
   parameters="contractCode={contractCust.contractCustId.contractCode}"
   postFunction="contDivEnableAjax"
   minimumCharacters="1"
   />

<ajax:autocomplete
baseUrl="customer.htm"
source="addCustomerAddresses[0].custAddresses[0].taxCode"
target="addCustomerAddresses[0].custAddresses[0].taxCode"
className="autocomplete"
parameters="taxCode={addCustomerAddresses[0].custAddresses[0].taxCode}"
minimumCharacters="1"
/> 

<ajax:select
baseUrl="customer.htm"
source="customer.parentCustomerName"
target="customer.childCustomerName"
parameters="customer.parentCustomerName={customer.parentCustomerName}"
parser="new ResponseXmlParser()" />
</form>
<!-------------------------------------------TAB 1 CONTAINER END-------------------------------------------------->
</div>
</div>
<script type="text/javascript">
//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
dolphintabs.init("tabnav", 0)
</script>
<!----------------------------------------------TAB CONTENT END--------------------------------------------------->
      </div>
<!---------------------------------------------MAIN CONTAINER END------------------------------------------------->
    </td>
  </tr>
</table>
</form:form>
<!-- For iTrack#135193 -->
<!---------------------------------- View CustomerNotes Popup START ---------------------------------------------------->
<div class="sample_popup" id="viewCustomerNote" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="viewCustomerNote_drag" style="width:800px;"> 
<img class="menu_form_exit"   id="viewCustomerNote_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="customerNotes"/></div>
<div class="menu_form_body" id="viewCustomerNotebody"  style="width:800px; height:350px;">
<iframe id="viewCustomerNotesFr" align="center" frameborder="0" style="padding:10px;" height="350px;" width="100%" name="viewCustomerNoteFr" allowtransparency="yes"></iframe>
</div>
</div>
<!-- -------------------------------- View CustomerNotes Popup END -----------------------------------------------------> 
<!-- For iTrack#135193-->
<!----------------------------------------------VAT Code Lookup--------------------------------------------------->
<div class="sample_popup" id="vatcode" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="vatcode_drag" style="width:750px;height:auto; "> <img class="menu_form_exit"   id="vatcode_exit" src=" images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Tax Code</div>
<div class="menu_form_body" id="vatcodebody"    style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  scrolling="auto" id="searchVatRateFr" name="searchVatRateFr" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!----------------------------------------------VAT Code Lookup END----------------------------------------------->
<!----------------------------------------------Parent Customer Lookup-------------------------------------------->
<div class="sample_popup" id="parentcust" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="parentcust_drag" style="width:750px;;height:auto; "> <img class="menu_form_exit"   id="parentcust_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchParentCustomer"/></div><div class="menu_form_body" id="parentcustbody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_customer_popup.htm?inputFieldId=customer.parentCustomer.name&parentCustomerSearchFlag=y&divName1=parentcust&divName2=parentcustbody" scrolling="auto" id="searchParentCustomerFr" name="searchParentCustomerFr" allowtransparency="yes"></iframe></div></div>

<!---------------------------------------------Parent Customer Lookup--------------------------------------------->
<!------------------------------------------------Company Name Lookup--------------------------------------------->
<div class="sample_popup" id="companyname" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="companyname_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="companyname_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCompanyName"/></div>
<div class="menu_form_body" id="companynamebody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_companyname_popup.htm?inputFieldId=customer.name&searchForm=customerQuickCreateForm" scrolling="auto" id="searchCompanynameFr" name="searchCompanynameFr" allowtransparency="yes"></iframe></div></div>
<!-------------------------------------------Company Name Lookup END---------------------------------------------->
<!------------------------------------------AccountOwner Lookup START--------------------------------------------->
<div class="sample_popup" id="accountOwner" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="accountOwner_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="accountOwner_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchUser"/></div>
<div class="menu_form_body" id="accountOwnerbody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px" width="100%" src="search_user_popup.htm?inputFieldId=customer.accountOwnerName&div1=accountOwner&div2=accountOwnerbody&searchForm=quickCreate" scrolling="auto" id="searchAccountOwnerFr" name="searchAccountOwnerFr" allowtransparency="yes" ></iframe>
</div></div>
<!------------------------------------AccountOwner Lookup END----------------------------------------------------->

<!------------------------------------------Originator Lookup START--------------------------------------------->
<div class="sample_popup" id="originator" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="originator_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="originator_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchUser"/></div>
<div class="menu_form_body" id="originatorbody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px" width="100%" src="search_user_popup.htm?inputFieldId=contractCust.contract.originatorUserName&div1=originator&div2=originatorbody&searchForm=userEditForm" scrolling="auto" id="searchOriginatorFr" name="searchOriginatorFr" allowtransparency="yes" ></iframe>
</div></div>
<!------------------------------------Originator Lookup END----------------------------------------------------->

<!------------------------------------------Signatory Lookup START--------------------------------------------->
<div class="sample_popup" id="signatory" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="signatory_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="signatory_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchUser"/></div>
<div class="menu_form_body" id="signatorybody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px" width="100%" src="search_user_popup.htm?inputFieldId=contractCust.contract.signatoryUserName&div1=signatory&div2=signatorybody&searchForm=userEditForm" scrolling="auto" id="searchSignatoryFr" name="searchSignatoryFr" allowtransparency="yes" ></iframe>
</div></div>
<!------------------------------------Signatory Lookup END----------------------------------------------------->


<!---------------------------------------Credit Analyst Lookup---------------------------------------------------->
<div class="sample_popup" id="creditan" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="creditan_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="creditan_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Credit Analyst</div>
<div class="menu_form_body" id="creditanbody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px" width="100%" src="search_creditanalyst_popup.htm?inputFieldId=customer.creditAnalystName" scrolling="auto" id="searchCreditAnalystFr" name="searchCreditAnalystFr" allowtransparency="yes" ></iframe>
</div></div> 
<!----------------------------------------- Credit Analyst Lookup END -------------------------------------------->
<!------------------------------------------- Collector Lookup --------------------------------------------------->
<div class="sample_popup" id="collector" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="collector_drag" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="collector_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Collector</div>
<div class="menu_form_body" id="collectorbody"  style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_collector_popup.htm?inputFieldId=customer.collectorName" scrolling="auto" id="searchCollectorFr" name="searchCollectorFr" allowtransparency="yes"></iframe> 
</div></div>
<!---------------------------------------- Collector Lookup END -------------------------------------------------->
<!--------------------------------------------Interunit BU Lookup------------------------------------------------->
<div class="sample_popup" id="bu" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="bu_drag" style="width:750px;height:auto; ">
<img class="menu_form_exit"   id="bu_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchBusinessUnit"/></div>
<div class="menu_form_body" id="bubody"  style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" scrolling="auto" id="searchBusinessUnitFr" name="searchBusinessUnitFr" allowtransparency="yes" src="blank.html"></iframe>
</div></div>
<!---------------------------------------------Interunit BU Lookup END-------------------------------------------->
<!---------------------------------------------Contact Lookup START----------------------------------------------->
<%-- <div class="sample_popup" id="contactid" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contact_drag" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="contact_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchContact"/></div>
<div class="menu_form_body" id="contactbody"   style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">   
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_contact_popup.htm?inputFieldId=contact.id&searchForm=customerQuickCreateForm&divName=contactid&divbody=contactbody" id="searchContactFr" scrolling="auto" name="searchContactFr" allowtransparency="yes"></iframe>        
</div></div>   --%>

<div class="sample_popup" id="contactid" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contact_drag" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="contact_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchContact"/></div>
<div class="menu_form_body" id="contactbody"   style="width:750px; height:550px;overflow-y:hidden;padding-left:15px;">   
<iframe align="left" frameborder="0" style="padding:0px;" height="540px;" width="100%" id="searchContactFr" scrolling="auto" name="searchContactFr" allowtransparency="yes" src="blank.html"></iframe>        
</div></div> 
<!----------------------------------------------- Contact Lookup END -------------------------------------------->

<!---------------------------------------------Location Lookup START----------------------------------------------->
<div class="sample_popup" id="addressseq0" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="addressseq_drag0" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="addressseq_exit0" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="addresssequenceSearch"/></div>
<div class="menu_form_body" id="addressseqbody0"   style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">   
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_address_sequence_popup.htm?inputFieldId=contactCust.custAddrSeq.custAddrSeqId.locationNumber&custCode=${command.customer.custCode}&searchForm=customerQuickCreateForm&rowNum=0" id="searchLocationFr" scrolling="auto" name="searchLocationFr" allowtransparency="yes"></iframe>        
</div></div>   
<!----------------------------------------------- Location Lookup END -------------------------------------------->

<!------------------------------------- Contract Lookup START ---------------------------------------------------->
<div class="sample_popup" id="contract" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contract_drag" style="width:750px;;height:auto; ">
<a href="#"  onclick="resetContDivEnable()"><img class="menu_form_exit"   id="contract_exit" src="images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="searchContract"/></div>
<div class="menu_form_body" id="contractbody"   style="width:750px; height:530px;overflow-y:hidden;">
<form method="post" action="popup.php">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<td valign="middle">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_contract_popup.htm?inputFieldId=contractCust.contractCustId.contractCode&searchForm=customerQuickCreateForm" scrolling="auto" id="searchContractFr" name="searchContractFr" allowtransparency="yes"></iframe>
</td>
</tr>
</table>    
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px;Top:1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>
</form>
</div>
</div>   
<!---------------------------------------------- Contract Lookup END --------------------------------------------->

<!---------------------------------- Create New Contract Popup --------------------------------------------------->
<div class="sample_popup" id="newcontract0" style="visibility: hidden; display: none; ">
<div class="menu_form_header" id="newcontract_drag0" style="width:900px;height:auto;"> 
<img class="menu_form_exit"   id="newcontract_exit0" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Create New Contract</div>                                                
<div class="menu_form_body" id="newcontractbody0" style="width:900px; height:300px;overflow-y:hidden;"">
<iframe align="left" frameborder="0" style="padding:10px;" height="300px;" width="100%" scrolling="auto" id="contractFr" name="contractFr" allowtransparency="yes" src="blank.html"></iframe>
</div>
</div>
<!-----------------------------------  New Contract Lookup End --------------------------------------------------->

<!----------------------------- Customer Additional Language Lookup --------------------------------------------------->
<div class="sample_popup" id="customer_additionalang" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="customer_additional_lang_drag" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="customer_additional_lang_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="createMultiLingualCustomer"/></div>
<div class="menu_form_body" id="customer_additional_lang_body"  style="width:750px; height:230px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" id="createCustMLingualFr" frameborder="0" style="padding:0px;" height="230px;" width="100%"  scrolling="auto" id="searchCollectorFr" name="searchCollectorFr" allowtransparency="yes" src="blank.html"></iframe> 
</div></div>
<!--------------------------- Customer Additional Language Lookup END -------------------------------------------------->

<!------------------------- CustomerAddr Additional Language Lookup --------------------------------------------------->
<div class="sample_popup" id="customeraddr_additionalang" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="customeraddr_additional_lang_drag" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="customeraddr_additional_lang_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="createMultiLingualCustomerAddress"/></div>
<div class="menu_form_body" id="customeraddr_additional_lang_body"  style="width:750px; height:230px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" id="createCustAddrMLingualFr" frameborder="0" style="padding:0px;" height="230px;" width="100%"  scrolling="auto" id="searchCollectorFr" name="searchCollectorFr" allowtransparency="yes" src="blank.html"></iframe> 
</div></div>
<!------------------------ CustomerAddrAdditional Language Lookup END -------------------------------------------------->

<div id="faderPane" style="visibility:hidden; display:none;z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>
