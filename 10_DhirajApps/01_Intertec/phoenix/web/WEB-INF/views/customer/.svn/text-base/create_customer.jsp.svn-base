<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="/phoenix/dwr/util.js"></script>
<script type="text/javascript" src="/phoenix/dwr/engine.js"></script>
<script src='/phoenix/dwr/interface/DWRUtil.js'></script>
<head>
<script language="javascript">

function locType(){ 
  var parentCust= document.getElementById("sel5");
  if(parentCust.value=="04")
  document.getElementById("pcust").style.visibility = "hidden";
  else
  document.getElementById("pcust").style.visibility = "visible";
}

function onAdd(){
    document.customerCreateForm.addOrDeleteCustContact.value = "add";
    document.customerCreateForm.tabName.value = "2";
    document.customerCreateForm.submit();
  }


function onAddCustAddrSeq(){
    document.customerCreateForm.addOrDeleteCustAddrSeq.value = "addAddr";
    document.customerCreateForm.tabName.value = "1";
    document.customerCreateForm.submit();
  }

  function onDeleteCustAddrSeq(index){
    document.customerCreateForm.addOrDeleteCustAddrSeq.value = "deleteAddr";
    document.customerCreateForm.tabName.value = "1";
    document.customerCreateForm.custAddrSeqIndex.value = index;
    document.customerCreateForm.submit();
  }

function onAddCustAddrDtls(seqIndex){
    document.customerCreateForm.addOrDeleteCustAddrDtl.value = "addAddrDtls";
    document.customerCreateForm.custAddrSeqIndex.value = seqIndex;
    document.customerCreateForm.tabName.value = "1";
    document.customerCreateForm.submit();
  }

  function onDeleteCustAddrDtls(seqIndex,dtlIndex){
    document.customerCreateForm.addOrDeleteCustAddrDtl.value = "deleteAddrDtls";
    document.customerCreateForm.custAddrSeqIndex.value = seqIndex;
    document.customerCreateForm.custAddrDtlIndex.value = dtlIndex;
    document.customerCreateForm.tabName.value = "1";
    document.customerCreateForm.submit();
  }

  function onSave(){
    document.customerCreateForm.tabName.value = "0";
    document.customerCreateForm.submit();
  }

  function onContractAdd(){ 
    document.customerCreateForm.addOrDeleteCustContract.value = "add";
    document.customerCreateForm.tabName.value = "2";
    document.customerCreateForm.submit();
  }

  function onContractDelete(index){
    document.customerCreateForm.addOrDeleteCustContract.value = "delete";
    document.customerCreateForm.tabName.value = "2";
    document.customerCreateForm.contractCustIndex.value = index;
    document.customerCreateForm.submit();
  }
  function onContractSave(tabNumber){
    document.customerCreateForm.tabName.value = tabNumber;
  }
  
  function onVatRegAdd(){ 
    document.customerCreateForm.addOrDeleteCustVatReg.value = "add";
    document.customerCreateForm.tabName.value = "3";
    document.customerCreateForm.submit();
  }
  
  function confirmDelete(){
    var yesno = confirm ("Are you sure you want to delete this note?");
    if (yesno == true)
	  return true;
    else
      return false;
	}

function beforesubmit(){
	if(document.customerCreateForm.noteSummary.value.length==0){
		confirm("Please fill Summary ");
		document.customerCreateForm.noteSummary.focus();
	}else{
		document.customerCreateForm.addOrDeleteCustNote.value = "add";
	    document.customerCreateForm.tabName.value = "4";
	    document.customerCreateForm.submit();
	}
}
 function onReset()
 {
 		document.customerCreateForm.addOrDeleteCustNote.value = "reset";
	    document.customerCreateForm.tabName.value = "4";
	    document.customerCreateForm.submit();
 } 
  
  function onVatRegDelete(index){
    document.customerCreateForm.addOrDeleteCustVatReg.value = "delete";
    document.customerCreateForm.tabName.value = "3";
    document.customerCreateForm.custVatRegIndex.value = index;
    document.customerCreateForm.submit();
  }
  function onCustNoteDelete(index){
  	document.customerCreateForm.addOrDeleteCustNote.value = "delete";
    document.customerCreateForm.tabName.value = "4";
    document.customerCreateForm.custNoteCount.value = index;
    document.customerCreateForm.submit();
  
  }
  
  function selectNote(index){
  	document.customerCreateForm.addOrDeleteCustNote.value = "view";
    document.customerCreateForm.tabName.value = "4";
    document.customerCreateForm.custNoteCount.value = index;
    document.customerCreateForm.submit();
  }
  
  function onVatRegSave(tabNumber){
    document.customerCreateForm.tabName.value = tabNumber;
  }

function billto(index){ 
  if(document.getElementById("billTo"+index).checked)
  document.getElementById("billprimary"+index).style.visibility = "visible";  
  else
  document.getElementById("billprimary"+index).style.visibility = "hidden"; 
}

function soldto(index){
  if(document.getElementById("soldTo"+index).checked)
  document.getElementById("soldprimary"+index).style.visibility = "visible";
  else
  document.getElementById("soldprimary"+index).style.visibility = "hidden";
}

function setflag(rowIndex){  
  document.customerCreateForm.contractFlag.value="contractFlag";
  document.customerCreateForm.rowNum.value=rowIndex;
 }  

function vflag() {
  document.customerCreateForm.vatCodeId.value="vatflag";
 }

 function updateAccountOwnerIframeSrc(){  document.getElementById('searchAccountOwnerFr').setAttribute("src","search_user_popup.htm?inputFieldId=customer.accountOwnerName&div1=accountOwner&div2=accountOwnerbody&searchForm=createCustomer");
}

 function updateParentCustomerIframeSrc(){  document.getElementById('searchParentCustomerFr').setAttribute("src","search_customer_popup.htm?inputFieldId=customer.parentCustomer.name&parentCustomerSearchFlag=y&divName1=parentcust&divName2=parentcustbody");
}

 function updateCreditAnalystIframeSrc(){ document.getElementById('searchCreditAnalystFr').setAttribute("src","search_creditanalyst_popup.htm?inputFieldId=customer.creditAnalystName");
}

 function updateCollectorIframeSrc(){ document.getElementById('searchCollectorFr').setAttribute("src","search_collector_popup.htm?inputFieldId=customer.collectorName");
}

 function updateBusinessUnitIframeSrc(){
document.customerCreateForm.busFlag.value="bus";
document.customerCreateForm.tabName.value = "0";   document.getElementById('searchBusinessUnitFr').setAttribute("src","search_businessunit_popup.htm?inputFieldId=customer.interunitBusUnitName&searchForm=customerCreateForm");
}
 
 function updateSearchContractIframeSrc(index){ document.getElementById('searchcontractFr'+index).setAttribute("src","search_contract_popup.htm?inputFieldId=contractCusts["+index+"].contract.contractCode&rowNum="+index+"&searchForm=customerCreateForm");
}
 
 function updateNewContractIframeSrc(index){    document.getElementById('contractFr'+index).setAttribute("src","create_contract_popup.htm?inputFieldId=contractCusts["+index+"].contract.contractCode&rowNum="+index+"&searchForm=customerCreateForm");
}
 
 function updateTaxCodeIframeSrc(index1,index2){  document.getElementById('searchVatRateFr'+index1+index2).setAttribute("src","search_vat_rate_popup.htm?inputFieldId=addCustomerAddresses["+index1+"].custAddresses["+index2+"].taxCode&rowNum="+index1+index2+"&div1=vatcode"+index1+index2+"&div2=vatcodebody"+index1+index2);
}

function interunitind(){  
    if(document.getElementById("interunit").checked)
    {
    document.getElementById("interunitpend").checked=false;
    document.getElementById("interunitpend").disabled=true;
    document.getElementById("ccode").style.visibility = "visible";  
    document.getElementById("busunit").style.visibility="visible";
      //document.getElementById("anchor").style.visibility="visible";

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
    //  document.getElementById("busunit").className="protected";
    //  document.getElementById("bunit").disabled=true;
      } 
   }

function interunitpending(){
     if(document.getElementById("interunitpend").checked)
     {
		document.getElementById("ccode").style.visibility = "visible";  
		document.getElementById("busunit").style.visibility="hidden";
		//document.getElementById("anchor").style.visibility="hidden";
		document.getElementById("custcode").value="";
		//document.getElementById("bunit").disabled=true;
     }
     else
     {
    document.getElementById("ccode").style.visibility = "hidden"; 
    document.getElementById("busunit").style.visibility="hidden";
    document.getElementById("custcode").value="New";
     }
   }


function setCheckBoxVal(checkboxid)
{
  if(document.getElementById(checkboxid).checked)
    document.getElementById(checkboxid).value = "1";
  else
    document.getElementById(checkboxid).value = "0";
}
   
function busiUnit(){
  if(document.getElementById("bunit").value!=null)
  {
  var a=document.getElementById("bunit").value;       
  document.getElementById("custCode").value=a;
  }
}
function setCustomerSearchFlag(){
  document.customerCreateForm.existingCustomerFlag.value = "Y";
  document.getElementById('searchCompanynameFr').setAttribute("src","search_companyname_popup.htm?inputFieldId=customer.name&searchForm=customerCreateForm");
  
}

function checkCreditHold()
{
  if(document.getElementById("sel1").value == 'I'){
    document.getElementById("chd").checked=true;
  } else {
	document.getElementById("chd").checked=false;
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
function populateCustCode()
{ 
	var bu = document.getElementById("bunit").value;	
	if(bu != null && bu != ''){
		document.getElementById("custCode").value = bu;
	}
}

</script>
</head>

<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<icb:list var="industryType">
  <icb:item>industryType</icb:item>
</icb:list>
<icb:list var="customerType">
  <icb:item>customerType</icb:item>
</icb:list>
<icb:list var="invoiceType">
  <icb:item>invoiceType</icb:item>
</icb:list>
<icb:list var="locationType">
  <icb:item>locationType</icb:item>
</icb:list>

<icb:list var="paymentType">
  <icb:item>paymentType</icb:item>
</icb:list>
<icb:list var="internalOrigin">
  <icb:item>internalOrigin</icb:item>
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
<form:form name="customerCreateForm" method="POST" action="create_customer.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>

<form:hidden path="contactCustCount" />
<form:hidden path="contactCustIndex"/>
<form:hidden path="addOrDeleteCustAddrSeq" />
<form:hidden path="addOrDeleteCustAddrDtl" />
<form:hidden path="custAddrSeqCount" />
<form:hidden path="custAddrSeqIndex"/>
<form:hidden path="custAddrDtlIndex"/>
<form:hidden path="tabName" />
<form:hidden path="addOrDeleteCustContract"/>
<form:hidden path="contractCustCount"/>
<form:hidden path="contractCustIndex"/>
<form:hidden path="addOrDeleteCustVatReg"/>
<form:hidden path="addOrDeleteCustNote"/>
<form:hidden path="custVatRegIndex"/>
<form:hidden path="custVatRegCount"/>
<form:hidden path="contractFlag"/>
<form:hidden path="rowNum"/>
<form:hidden path="vatCodeId"/>
<form:hidden path="divFlag"/>
<form:hidden path="busFlag"/>
<form:hidden path="custNoteCount"/>

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
<!------------------------------------------------- MAIN CONTAINER ----------------------------------------------->
<div id="MainContentContainer">
<!---------------------------------------------------- TABS CONTENTS --------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="customer"/></span></a></li>
              <li><a href="#" rel="tab2"><span><f:message key="customerAddress"/></span></a></li>
              <li><a href="#" rel="tab3"><span><f:message key="customerContracts"/></span></a></li>
              <li><a href="#" rel="tab4"><span><f:message key="customerVatReg"/></span></a></li>
              <li><a href="#" rel="tab5"><span><f:message key="customerNotes"/></span></a></li>
            </ul>
          </div>
<!--------------------------------- Sub Menus container. Do not remove ------------------------------------------->
          <div id="tab_inner">
<!-------------------------------------------- TAB 1 CONTAINER --------------------------------------------------->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>

  <tr bgcolor=#ffffff>
  <th colspan="2" width="15%"><f:message key="customerName"/></th>
  <th width="30%">
  <span style="text-align:right"><f:message key="status"/>:
  <form:select cssClass="selectionBox" id ="sel1"  path="customer.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" onchange="checkCreditHold();"/>          
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<script type="text/javascript">
  var currentTime = new Date();
  var month = currentTime.getMonth() + 1;
  var day = currentTime.getDate();
  var year = currentTime.getFullYear();
  document.write(month + "/" + day + "/" + year);
  </script>
  </span>
  </th>
  <th width="10%" bgcolor="#ffffff" style="text-align:right">
   <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp
  <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(0);" /></a></th>
  </tr>

<%--<tr>
<td width="15%" class="TDShadeBlue" ><f:message key="interunit"/> </td>
<td > <form:checkbox id="interunit"  path="customer.interunitInd" value="1" onclick="interunitind()"/>
<form:errors path="customer.interunitInd" cssClass="redstar"/>&nbsp;<f:message key="interunitPending"/>
<form:checkbox id="interunitpend"  path="customer.interunitPendingInd" value="0"  onclick="interunitpending()"/>
</td>
<td width="15%" class="TDShadeBlue" ><f:message key="interunitBusUnit"/>: </td>
<td id="busunit" width="35%" class="TDShadeBlue"><form:input id="bunit" cssClass="inputBox" size="35" path="customer.interunitBusUnitName" disabled="true" onmouseup="busiUnit()"/>
<form:errors path="customer.interunitBusUnitName" cssClass="redstar"/> 
<a href="#" onClick="javascript:updateBusinessUnitIframeSrc();popup_show('bu', 'bu_drag', 'bu_exit', 'screen-corner', 40, 80); popupboxenable();hideIt();showPopupDiv('bu','bubody'); searchBUht(); hideBUsearch(); "> <img src="images/lookup.gif" alt="lookup country" width="13" height="13" border="0"></a>
</td>
</tr>--%>

 <tr>
  <td class="TDShade" ><f:message key="interunit"/></td>
<td class="TDShadeBlue" >
  <form:checkbox id="interunit"  path="customer.interunitInd" onclick="interunitind()"/>
 </td>
 <td class="TDShade">&nbsp;</td>
<td class="TDShadeBlue" >&nbsp;</td>
</tr>
<tr>
<%--<td class="TDShade" ><f:message key="interunit"/> </td>
<td class="TDShadeBlue"> <form:checkbox id="interunit" path="customer.interunitInd" onclick="interunitind()"/>
<form:errors path="customer.interunitInd" cssClass="redstar"/>&nbsp;&nbsp;--%>
<td class="TDShade" ><f:message key="interunitPending"/></td>
<td  class="TDShadeBlue">
<form:checkbox id="interunitpend"  path="customer.interunitPendingInd" onclick="interunitpending()"/>
</td>

<td  class="TDShade" ><f:message key="interunitBusUnit"/>: </td>
<%-- <td class="TDShadeBlue" id="busunit" width="35%" style="visibility:hidden;background-color:#faffff;">
<form:input id="bunit" cssClass="inputBox" size="35" path="customer.interunitBusUnitName"/>
<form:errors path="customer.interunitBusUnitName" cssClass="redstar"/> 
<a href="#" id="anchor" onClick="javascript:updateBusinessUnitIframeSrc();popup_show('bu', 'bu_drag', 'bu_exit', 'screen-corner', 120, 20); popupboxenable();hideIt();showPopupDiv('bu','bubody'); ">
<img src="images/lookup.gif" alt="lookup Business Unit" width="13" height="13" border="0"></a>
</td> --%>

<td class="TDShadeBlue" id="busunit" width="35%" style="visibility:hidden;background-color:#faffff;">
<form:select id="bunit" cssClass="selectionBoxbig" onchange="populateCustCode();"
          path="customer.interunitBusUnitName"
          items="${icbfn:dropdown('businessUnit', null)}"
          itemLabel="name" itemValue="value"/> <form:errors
          path="customer.interunitBusUnitName" cssClass="redstar" />
<form:errors path="customer.interunitBusUnitName" cssClass="redstar"/> 
</td> 
</tr>

<tr>
<td  class="TDShade"><f:message key="customerName"/> :<span class="redstar">*</span></td>
<td class="TDShadeBlue" width="35%">
<form:input cssClass="inputBox" size="35" maxlength="60" path="customer.name" />
<form:errors path="customer.name" cssClass="redstar"/>
<!--  <a href="#" onClick="javascript:setCustomerSearchFlag();popup_show('companyname', 'companyname_drag', 'companyname_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();"> <img src="images/lookup.gif" alt="lookup Customer Name" width="13" height="13" border="0"></a> -->
</td>
<td class="TDShade"><f:message key="custCode"/>:<span class="redstar">*</span></td>
<td class="TDShadeBlue" id="ccode" style="visibility:hidden" >
<form:input id="custCode" cssClass="inputBox" size="35" maxlength="15" path="customer.custCode" />
<form:errors path="customer.custCode" cssClass="redstar"/>
</td>
</tr>
	<tr>
		<td  class="TDShade"><f:message key="alternateName"/>:</td>
		<td class="TDShadeBlue" width="35%"><form:input cssClass="inputBox" size="35" maxlength="60" 		path="customer.alternateName" /></td>
		<td class="TDShade"><f:message key="taxpayerIDNumber"/></td>
		<td colspan="1" class="TDShadeBlue"><form:input id="prcust" cssClass="inputBox" maxlength="60" path="customer.taxpayerIDNumber" size="35"/><form:errors path="customer.taxpayerIDNumber" cssClass="redstar"/></td>
	</tr>
<script type="text/javascript">
check();
function check()
{
  if(document.getElementById("interunit").checked)
  {
    document.getElementById("interunitpend").checked=false;
    document.getElementById("interunitpend").disabled=true;
	document.getElementById("busunit").style.visibility="visible";
	document.getElementById("ccode").style.visibility = "visible"; 
	//document.getElementById("anchor").style.visibility="visible";
	document.getElementById("busunit").className="unprotected";
	document.getElementById("bunit").disabled=false;
  }

   if(document.getElementById("interunitpend").checked)
  {
	document.getElementById("busunit").style.visibility="hidden";
	document.getElementById("ccode").style.visibility = "visible"; 
	//document.getElementById("anchor").style.visibility="hidden";
  }
}
</script>


<tr>
<td class="TDShade"><f:message key="locationType"/></td>
        <td class="TDShadeBlue">
              <form:select id="sel5" cssClass="selectionBox" path="customer.locationType" items="${icbfn:dropdown('staticDropdown',locationType)}" itemLabel="name" itemValue="value" onchange="locType()"/>
        
     <form:errors path="customer.locationType" cssClass="redstar"/> </td>

 <td  class="TDShade"><f:message key="customerSince"/>:<strong></strong></td>
<td   class="TDShadeBlue">   
<form:input id="sdate" cssClass="inputBox" path="customer.sinceDate" size="10" maxlength="12"/>
<a href="#" onClick="displayCalendar(document.forms[0].sdate,'MM/dd/yyyy',this)"> 
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
              <form:input id="prcust" cssClass="inputBox" maxlength="90" path="customer.parentCustomer.name" size="30"/>
              <a href="#" onClick="javascript:popup_show('parentcust', 'parentcust_drag', 'parentcust_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('parentcust','parentcustbody')"> <img src="images/lookup.gif" alt="lookup Customer Name" width="13" height="13" border="0"></a>  
        <form:errors path="customer.parentCustomer.name" cssClass="redstar"/>
       <form:checkbox id="isChild" path="customer.isChildCustomer" disabled="true"/>
 </div>
		
        </td> 
                    <td class="TDShade"><f:message key="dateAdded"/>:<strong></strong></td>
                    <td  class="TDShadeBlue">
                   <form:input id="addDate" cssClass="inputBox" path="customer.addDate" size="10" maxlength="12"/>

<a href="#" onClick="displayCalendar(document.forms[0].addDate,'MM/dd/yyyy',this)"> 
<img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
<div id="debug"></div>          
</td>
</tr>
<tr>
 <td class="TDShade"><f:message key="currency"/></td>
		<td class="TDShadeBlue">
				  <form:select id="sel1" cssClass="selectionBox" path="customer.currencyCd" items="${icbfn:dropdown('currency', null)}" itemLabel="name" itemValue="value"/>
		<form:errors path="customer.currencyCd" cssClass="redstar"/>
		</td>
		<td class="TDShade"><f:message key="Dunn&BradsheetNo"/>:</td>
	<td class="TDShadeBlue">
	<form:input id="dbnum" cssClass="inputBox" maxlength="20" path="customer.dunnAndBradstreetNumber" size="20" disabled="true"/>
	</td>
</tr>
<tr>
		<td class="TDShade" colspan="4" ><f:message key="internalOrigin"/>:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		
		&nbsp&nbsp&nbsp
		 <f:message key="businessDivisions"/>:&nbsp&nbsp&nbsp
		 <form:select cssClass="selectionBox" id="sel7" path="customer.businessDivisions" items="${icbfn:dropdown('staticDropdown',businessDivisions)}" itemLabel="name" itemValue="value"/><form:errors path="customer.businessDivisions" cssClass="error"/>
		 &nbsp&nbsp&nbsp&nbsp<f:message key="businessStreams"/>:&nbsp
		<form:select cssClass="selectionBox" id="sel7" path="customer.businessStreams" items="${icbfn:dropdown('staticDropdown',businessStreams)}" itemLabel="name" itemValue="value"/><form:errors path="customer.businessStreams" cssClass="error"/>
		&nbsp<f:message key="originSource"/>:&nbsp
		<form:select cssClass="selectionBox" id="sel7" path="customer.originSource" items="${icbfn:dropdown('staticDropdown',OriginSource)}" itemLabel="name" itemValue="value"/><form:errors path="customer.originSource" cssClass="error"/>
		</td>
	</tr>
	    <tr>
		<td class="TDShade"><f:message key="accountOwner"/></td>
		<td class="TDShadeBlue" >         
		<form:input cssClass="inputBox" size="35"  maxlength="128" path="customer.accountOwnerName" />
		<form:errors path="customer.accountOwnerName" cssClass="redstar"/> 
		<a href="#" onClick="javascript:updateAccountOwnerIframeSrc();popup_show('accountOwner', 'accountOwner_drag', 'accountOwner_exit', 'screen-corner', 120, 20); popupboxenable();hideIt();showPopupDiv('accountOwner','accountOwnerbody');">
		<img src="images/lookup.gif" alt="lookup Account Owner"  width="13" height="13" border="0">
         </a> </td>
          <td class="TDShade"><f:message key="invoiceType"/>: </td>
          <td class="TDShadeBlue">
          <form:select id ="sel4"cssClass="selectionBox" path="customer.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value"/>
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
<form:select id ="sel2" cssClass="selectionBox" path="customer.industry" items="${icbfn:dropdown('staticDropdown',industryType)}" itemLabel="name" itemValue="value"/>
<form:errors path="customer.industry" cssClass="redstar"/>
</td>
<td class="TDShade"></td>
<td  class="TDShadeBlue"></td>
</tr>
 <tr>
	  <td class="TDShade" colspan="4"><f:message key="primaryIndustry"/>:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	  &nbsp
		<form:select cssClass="selectionBox" id="sel8" path="customer.primaryIndustry" items="${icbfn:dropdown('staticDropdown',secondaryIndustry)}" itemLabel="name" itemValue="value"/><form:errors path="customer.primaryIndustry" cssClass="error"/>
	  </td>
	  </tr>
	  </tr>
	  <td class="TDShade" colspan="4"><f:message key="secondaryIndustry"/>:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	 
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
	<td class="TDShade" colspan="6"><f:message key="referral"/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	&nbsp
	<f:message key="custNotes"/>:&nbsp&nbsp
	 <form:input cssClass="inputBox" path="customer.notes" size="30" maxlength="256"/>
	 <form:errors path="customer.notes" cssClass="error"/>
	</td>
	</tr>
    <tr>
		  <td class="TDShade"><f:message key="revenueTier"/>:</td>
            <td  class="TDShadeBlue">
			  <form:select id="sel2" cssClass="selectionBox" path="customer.type" items="${icbfn:dropdown('staticDropdown',customerType)}" itemLabel="name" itemValue="value"/>
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
    <form:checkbox id="chd" path="customer.creditHoldInd" value="1"/>
    <form:errors path="customer.creditHoldInd" cssClass="redstar"/>
    </td>
	<td class="TDShade"><f:message key="creditLimit"/>:</td>
    <td class="TDShadeBlue">
    <form:input cssClass="inputBox" path="customer.creditLimit" size="10" maxlength="12"/>
    <form:errors path="customer.creditLimit" cssClass="redstar"/>
    </td> 
	</tr>
	<tr>
  <td class="TDShade"><f:message key="creditAnalyst"/></td>
  <td class="TDShadeBlue" >
              <form:input cssClass="inputBox" maxlength="40" path="customer.creditAnalystName" size="35"/>
             <a href="#" onClick="javascript:updateCreditAnalystIframeSrc();popup_show('creditan', 'creditan_drag', 'creditan_exit', 'screen-corner', 120, 20);popupboxenable();hideIt();showPopupDiv('creditan','creditanbody');">
  <img src="images/lookup.gif" alt="lookup Credit Analyst" width="13" height="13" border="0"></a>
  <form:errors path="customer.creditAnalystName" cssClass="redstar"/>
  
  </td>

  <td class="TDShade"><span class="TDShade" ><f:message key="collector"/></span></td>
  <td colspan="2" class="TDShadeBlue">
  
              <form:input cssClass="inputBox" size="35" maxlength="40" path="customer.collectorName"  />
  <a href="#" onClick="javascript:updateCollectorIframeSrc();popup_show('collector', 'collector_drag', 'collector_exit', 'screen-corner', 120, 20);popupboxenable();hideIt();showPopupDiv('collector','collectorbody');">
  <img src="images/lookup.gif" alt="lookup Collector" width="13" height="13" border="0"></a>
  
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
	<form:checkbox id="crapp" path="customer.creditApproved" onclick="updatePaymentType();"/>
    </td> 
	<td class="TDShade"><f:message key="paymentType"/>:</td>
	<td class="TDShadeBlue"><form:select id="sel70" cssClass="selectionBox" path="customer.paymentType" items="${icbfn:dropdown('staticDropdown',paymentType)}" itemLabel="name" itemValue="value"/><form:errors path="customer.paymentType" cssClass="redstar"/></td>             
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
</tr>
 </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry"/></span> </td>
                        <td style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(0);"/></a></td>
                      </tr>
                    </table>
          </td>
                </tr>
              </table>
            </div>
  <ajax:autocomplete
  baseUrl="customer.htm"
  source="customer.accountOwnerName"
  target="customer.accountOwnerName"
  className="autocomplete"
  parameters="accountOwnerRole={customer.accountOwnerName}"
  minimumCharacters="1"
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
  baseUrl="business_unit.htm"
  source="customer.interunitBusUnitName"
  target="customer.custCode"
  className="autocomplete"
  parameters="customer.interunitBusUnitName={customer.interunitBusUnitName}"
  minimumCharacters="1"
   /> 
  <ajax:select
  baseUrl="customer.htm"
  source="customer.parentCustomerName"
  target="customer.childCustomerName"
  parameters="customer.parentCustomerName={customer.parentCustomerName}"
  parser="new ResponseXmlParser()" />
<!------------------------------------------- TAB 1 CONTAINER END ------------------------------------------------>
<!---------------------------------------------- TAB 2 CONTAINER ------------------------------------------------->

<div id="tab2" class="innercontent" >
<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable" >
<tbody>
<tr bgcolor=#ffffff>
<th width="50%"><f:message key="customerName"/></th>
<th width="30%" colspan="2" ><f:message key="status"/>:
   <form:select cssClass="selectionBox" path="customer.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" disabled="true" />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<script type="text/javascript">
          var currentTime = new Date();
          var month = currentTime.getMonth() + 1;
          var day = currentTime.getDate();
          var year = currentTime.getFullYear();
          document.write(month + "/" + day + "/" + year);
          </script>
                              
                    </th>

<th width="15%" style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(1)"/></a></th>
</tr>

<tr>
<td colspan="4" class="TDShade" style="padding:2px;">
<table width="100%" cellpadding="0" cellspacing="0" border="0"  class="InnerApplTable" style="width:100%" border="0">
<tbody>
<tr>
<th colspan="2" class="TDShade"><f:message key="addressLocations"/></th>
<th colspan="2" class="TDShade" >&nbsp;</th>
<th class="TDShade" colspan="3" style="text-align:right;" >
<div id="div5" > <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onAddCustAddrSeq()"/></a> </div></th>
</tr>

<c:forEach items="${command.addCustomerAddresses}" var="arrCustAddrs" varStatus="addrCounter">
 <tr width="100%">
 <td width="100%" style="padding:0">
          
<!----------------------------------------------- VESSEL 1 CONTAINER --------------------------------------------->
<div id="customerAddressContainer" class="customerAddress"  width="100%">
<table width="100%" cellpadding="0" cellspacing="0"  class="mainApplTable" border="0">
<tbody width="100%">
<tr width="100%">
<c:choose>
<c:when test="${addrCounter.index+1==command.custAddrSeqCount}">
<th width="16" rowspan="2" align="center" valign="middle" scope="col" style="background-image:url( images/arrowblubg.gif);"> 
<div id="arrowdnc${addrCounter.index+1}"  style="visibility:visible; position:absolute; z-index: 2; margin-top:4px">
<a href="#" onClick="javascript:hidecontract01('customeraddress${addrCounter.index+1}Container','arrowdnc${addrCounter.index+1}','arrowrtc${addrCounter.index+1}');">
<img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div>
<div id="arrowrtc${addrCounter.index+1}" style="visibility:hidden;position:relative; z-index: 1; margin-left:4px ">
<a href="#" onClick="javascript:showcontract01('customeraddress${addrCounter.index+1}Container','arrowdnc${addrCounter.index+1}','arrowrtc${addrCounter.index+1}');">
<img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div></th>
</c:when>
<c:otherwise>
<th width="16" rowspan="2" align="center" valign="top" scope="col" style="background-image:url( images/arrowblubg.gif);"> 
<div id="arrowrtc${addrCounter.index+1}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px">
<a href="#" onClick="javascript:showcontract01('customeraddress${addrCounter.index+1}Container','arrowdnc${addrCounter.index+1}','arrowrtc${addrCounter.index+1}');">
<img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>

<div id="arrowdnc${addrCounter.index+1}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> 
<a href="#" onClick="javascript:hidecontract01('customeraddress${addrCounter.index+1}Container','arrowdnc${addrCounter.index+1}','arrowrtc${addrCounter.index+1}');">
<img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
</c:otherwise>
</c:choose>



 <td  class="TDShade"><f:message key="location"/>  :<span class="redstar">*</span></td>

<td  class="TDShadeBlue">
<form:input cssClass="inputBox" size="15" maxlength="3" path="addCustomerAddresses[${addrCounter.index}].custAddrSeq.custAddrSeqId.locationNumber"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddrSeq.custAddrSeqId.locationNumber" cssClass="redstar"/>  
</td>
<td  class="TDShade">
<form:checkbox disabled="true" id="billTo${addrCounter.index}"  path="addCustomerAddresses[${addrCounter.index}].custAddrSeq.billTo"  value="1"   onclick="billto(${addrCounter.index})"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddrSeq.billTo" cssClass="redstar"/>

<f:message key="billTo"/>  </td>
<td  class="TDShade">

<div id="billprimary${addrCounter.index}" style="visibility:visible">
<form:checkbox id="billPrimaryFlag${addrCounter.index}" path="addCustomerAddresses[${addrCounter.index}].primaryBillToAddr"  value="1" onclick="setCheckBoxVal('billPrimaryFlag${addrCounter.index}')"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].primaryBillToAddr" cssClass="redstar"/>
Primary </div>
</td>
<td  class="TDShade">
<form:checkbox disabled="true" path="addCustomerAddresses[${addrCounter.index}].custAddrSeq.corresponence" value="1" />
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddrSeq.corresponence" cssClass="redstar"/>
<f:message key="correspondenceAddress"/></td>
<td class="TDShade" style="text-align:right" >&nbsp;</td>

</tr>

<tr>
<td class="TDShade"><f:message key="description"/> :</td>
<td class="TDShadeBlue">
<form:input cssClass="inputBox" size="35" maxlength="70" path="addCustomerAddresses[${addrCounter.index}].custAddrSeq.addressDescr"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddrSeq.addressDescr" cssClass="redstar"/>  
<a href="#" onClick="javascript:popup_show('country', 'country_drag', 'country_exit', 'screen-corner', 120, 20);showcountryframe();hideIt()"></a>
</td>

<td class="TDShade">
<form:checkbox disabled="true" id="soldTo${addrCounter.index}" path="addCustomerAddresses[${addrCounter.index}].custAddrSeq.soldTo"  value="1"  onclick="soldto(${addrCounter.index})"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddrSeq.soldTo" cssClass="redstar"/>
<f:message key="soldTo"/> </td>

<td class="TDShade">
<div id="soldprimary${addrCounter.index}" style="visibility:visible">
<form:checkbox id="soldPrimaryFlag${addrCounter.index}" path="addCustomerAddresses[${addrCounter.index}].primarySoldToAddr"  value="1" onclick="setCheckBoxVal('soldPrimaryFlag${addrCounter.index}')"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].primarySoldToAddr" cssClass="redstar"/>
Primary</div>
</td>
<td class="TDShade" style="text-align:right" ></td>
<td  class="TDShade" align="left">
<div id="div5" style="width:50px;"> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onDeleteCustAddrSeq('${addrCounter.index}')"/></a></div>
</td>
</tr>
</tbody>
</table>  
<!----------------------------------- VESSEL 1 CONTAINER END------------------------------------------------------>
<c:choose>
<c:when test="${addrCounter.index+1==command.custAddrSeqCount}">
<div id="customeraddress${addrCounter.index+1}Container" class="customerAddresscontainer" align="right" style="padding:0px; visibility:visible;">
</c:when>
<c:otherwise>
<div id="customeraddress${addrCounter.index+1}Container" class="customerAddresscontainer" align="right" style="padding:0px; visibility:hidden;display:none;">
</c:otherwise>
</c:choose>
<div id="customeraddress${addrCounter.index+1}Container">
 <table width="100%" cellpadding="0" cellspacing="0"  class="mainApplTable" style="width:100%; ">
<tr>
<th colspan="2" class="TDShade"> <f:message key="addressDetails"/></th>
<th colspan="2" class="TDShade">&nbsp;
</th>
<th class="TDShade" style="text-align:right" ><div id="div15"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onAddCustAddrDtls('${addrCounter.index}')"/></a> </div></th>
</tr>

<c:forEach items="${arrCustAddrs.custAddresses}" var="arrCustAddrDtls" varStatus="addrDtlCounter">
 <tr>
<th colspan="2" class="TDShade"><f:message key="status"/>:
<form:select id="sel2" cssClass="selectionBox" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].effStatus" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" />
</th>
<th colspan="2" class="TDShade">&nbsp;</th>
<th class="TDShade" style="text-align:right"><a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0" onclick="onDeleteCustAddrDtls('${addrCounter.index}','${addrDtlCounter.index}')" /></a></th>
</tr>
<tr>
<td width="15%" class="TDShade"><f:message key="effectiveDate"/> : <span class="redstar">*</span></td>
<td class="TDShadeBlue" width="35%">

<form:input id="effDt${addrCounter.index}${addrDtlCounter.index}" cssClass="inputBox" size="35" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].effDate"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].effDate" cssClass="redstar"/>  


<a href="#" onClick="displayCalendar(document.forms[0].effDt${addrCounter.index}${addrDtlCounter.index},'MM/dd/yyyy',this)"> 
<img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
<div id="div"></div>                          
</td>

<td width="15%" class="TDShade"></td>
<td width="35%" colspan="2" class="TDShadeBlue">

</td>
</tr>

<tr>
<td class="TDShade" style="border-bottom:#CCCCCC dashed 1px"><f:message key="taxCode"/> : </td>
<td class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px">

<form:input cssClass="inputBox" size="35" maxlength="3" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].taxCode"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].taxCode" cssClass="redstar"/>  
<a href="#" onClick="javascript:updateTaxCodeIframeSrc('${addrCounter.index}','${addrDtlCounter.index}');popup_show('vatcode${addrCounter.index}${addrDtlCounter.index}', 'vatcode_drag${addrCounter.index}${addrDtlCounter.index}', 'vatcode_exit${addrCounter.index}${addrDtlCounter.index}', 'screen-corner', 120, 20);hideIt();showPopupDiv('vatcode${addrCounter.index}${addrDtlCounter.index}','vatcodebody${addrCounter.index}${addrDtlCounter.index}');popupboxenable();">

<img src="images/lookup.gif" alt="lookup Tax Code" width="13" height="13" border="0" /></td>
<td class="TDShade" style="border-bottom:#CCCCCC dashed 1px">&nbsp;</td>
<td colspan="2" class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px">&nbsp;</td>
</tr>
<tr>
<td class="TDShade">

<f:message key="country"/>:<span class="redstar">*</span></strong></td>

<td class="TDShadeBlue">

<form:select id="sel3" cssClass="selectionBox" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].country" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />

</td>
<td class="TDShade">&nbsp;</td>
<td colspan="2" class="TDShadeBlue">&nbsp;</td>
</tr>

<tr>
<td class="TDShade"><f:message key="address1"/>: </td>
<td class="TDShadeBlue">

<form:input cssClass="inputBox" size="35" maxlength="70" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address1"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address1" cssClass="redstar"/> 

</td>
<td class="TDShade" ><f:message key="address2"/>: </td>
<td colspan="2" class="TDShadeBlue" >

<form:input cssClass="inputBox" size="35" maxlength="55" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address2"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address2" cssClass="redstar"/>             
</td>
</tr>
<tr>
<td class="TDShade"><f:message key="address3"/>: </td>
<td class="TDShadeBlue">

<form:input cssClass="inputBox" size="35" maxlength="55" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address3"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address3" cssClass="redstar"/>             
</td>
<td class="TDShade"><f:message key="address4"/>: </td>
<td colspan="2" class="TDShadeBlue">

<form:input cssClass="inputBox" size="35" maxlength="55" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address4"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address4" cssClass="redstar"/> 

</td>
</tr>
<tr>
<td class="TDShade"><f:message key="city"/>:</td>
<td class="TDShadeBlue">

<form:input cssClass="inputBox" size="20" maxlength="40" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].city"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].city" cssClass="redstar"/>&nbsp;&nbsp;&nbsp;
<f:message key="inCityLimit"/>


<form:checkbox path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].inCityLimit" />
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].inCityLimit" cssClass="redstar"/>

</td>
<td class="TDShade" >&nbsp;</td>
<td colspan="2" class="TDShadeBlue" >&nbsp;</td>
</tr>
<tr>
<td class="TDShade"><f:message key="county"/>:</td>
<td class="TDShadeBlue">

<form:input cssClass="inputBox" size="35" maxlength="30" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].county"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].county" cssClass="redstar"/>

</td>
<td class="TDShade"><f:message key="postal"/>:</td>
<td colspan="2" class="TDShadeBlue">

<form:input cssClass="inputBox" size="35" maxlength="12" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].postal"/>
<form:errors path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].postal" cssClass="redstar"/>             
</td>
</tr>
<tr>
<td class="TDShade" ><f:message key="state"/> : </td>
<td class="TDShadeBlue" >

<icb:list var="countryCodeList">
<icb:item>${arrCustAddrDtls.country}</icb:item>
</icb:list>                             
<form:select id="sel5" cssClass="selectionBox" path="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].state" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" />

</td>
<td class="TDShade" >&nbsp;</td>
<td colspan="2" class="TDShadeBlue" >&nbsp;</td>
  <ajax:select
  baseUrl="country.htm"
  source="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].country"
  target="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].state"
  parameters="country.countryCode={addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].country}"
  parser="new ResponseXmlParser()" />
  <ajax:autocomplete
  baseUrl="customer.htm"
  source="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].taxCode"
  target="addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].taxCode"
  className="autocomplete"
  parameters="taxCode={addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].taxCode}"
  minimumCharacters="1"
  /> 
</tr>
<!----------------------------------------VAT Code Lookup--------------------------------------------------------->
<div class="sample_popup" id="vatcode${addrCounter.index}${addrDtlCounter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="vatcode_drag${addrCounter.index}${addrDtlCounter.index}" style="width:750px;height:auto; "> <img class="menu_form_exit"   id="vatcode_exit${addrCounter.index}${addrDtlCounter.index}" src=" images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="taxCode" /></div>
<div class="menu_form_body" id="vatcodebody${addrCounter.index}${addrDtlCounter.index}"   style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  scrolling="auto" id="searchVatRateFr${addrCounter.index}${addrDtlCounter.index}" name="searchVatRateFr${addrCounter.index}${addrDtlCounter.index}" allowtransparency="yes" src="blank.html"></iframe>
</div>  
</div>
<!-------------------------------------------VAT Code Lookup END-------------------------------------------------->
</c:forEach>

</table>
</div>
</div>
</td>
</tr>
</c:forEach>
</tbody>
</table></td>
</tr>
</tbody>
</table>
<%--</tbody>
</table>  
</td>
</tr>
</table> <!-- MainAppl Table end -->
</div> --%>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry"/></span> </td>
<td style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(1)"/></a></td>
</tr>
</table>
</td>
</tr>
</table>
</div>
<!---------------------------------------TAB 2 CONTAINER END------------------------------------------------------>
<!--------------------------------------------- TAB 4 CONTAINER -------------------------------------------------->


      
            <div id="tab3" class="innercontent" >
      <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr><th width="45%">
     <f:message key="customerName"/>
      </th>
        <th width="40%"><span style="text-align:right"><f:message key="status"/>:
            <form:select cssClass="selectionBox" id ="sel1"  disabled="true" path="customer.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>          
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<script type="text/javascript">
var currentTime = new Date();
var month = currentTime.getMonth() + 1;
var day = currentTime.getDate();
var year = currentTime.getFullYear();
document.write(month + "/" + day + "/" + year);
</script>
</span></th>
        <th width="15%" style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(3);"/></a></th>
      </tr>
      <tr><td colspan="3" style="padding:2px;">
      
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
      <tr>
        <th width="50%">
       <f:message key="contractID"/> </th>
        <th width="45%"><f:message key="description"/></th>
        <th width="50" align="right"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onContractAdd()"/></th>
      </tr>
   <c:forEach items="${command.contractCusts}" var="contractCusts" varStatus="counter">
      
      <tr>
        <td class="TDShadeBlue">
                   <form:input cssClass="inputBox" size="15" path="contractCusts[${counter.index}].contract.contractCode"/>
          <form:errors path="contractCusts[${counter.index}].contract.contractCode" cssClass="redstar"/>  
<a href="#" onClick="javascript:updateSearchContractIframeSrc(${counter.index});setflag(${counter.index});popup_show('contract${counter.index}', 'contract_drag${counter.index}', 'contract_exit${counter.index}', 'screen-corner', 120, 20); hideIt();showPopupDiv('contract${counter.index}','contractbody${counter.index}');popupboxenable();"><img src="images/lookup.gif" alt="lookup Contract" width="13" height="13" border="0"></a> 
<a href="#" onClick="javascript:updateNewContractIframeSrc(${counter.index});setflag(${counter.index});popup_show('newcontract${counter.index}', 'newcontract_drag${counter.index}', 'newcontract_exit${counter.index}', 'screen-corner', 50, 40); hideIt();showPopupDiv('newcontract${counter.index}','newcontractbody${counter.index}');popupboxenable();"><f:message key="createNewContract"/></a> 
        </td>
           <td class="TDShadeBlue" align="center">
       <form:input cssClass="inputBox" size="15" maxlength="512" path="contractCusts[${counter.index}].contract.description" disabled="true"/>
       <form:errors path="contractCusts[${counter.index}].contract.description" cssClass="redstar"/>
           </td>
      <td width="50" class="TDShadeBlue" style="text-align:left;"><div id="div3" style="width:50px; text-align:left; margin-right:0;"> <img src="images/delete.gif" alt="Delete row" width="13" height="12" hspace="1px" border="0" onclick="onContractDelete('${counter.index}')" /></div>
       </td> 


        <ajax:autocomplete
        baseUrl="customer.htm"
        source="contractCusts[${counter.index}].contract.contractCode"
        target="contractCusts[${counter.index}].contract.description"
        className="autocomplete"              
        parameters="contractCodeAndDesc={contractCusts[${counter.index}].contract.contractCode}"
        minimumCharacters="1"
          />
<!--------------------------------------Search Contract Popup----------------------------------------------------->
<div class="sample_popup" id="contract${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contract_drag${counter.index}" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="contract_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchContract"/></div>                                                            
<div class="menu_form_body" id="contractbody${counter.index}"  style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:10px;" height="530px;" width="100%" scrolling="auto" id="searchcontractFr${counter.index}" name="searchcontractFr" allowtransparency="yes" src="blank.html"></iframe>
</div>
</div>
<!-------------------------------------Search Contract Popup------------------------------------------------------>
<!------------------------------------Create New Contract Popup--------------------------------------------------->
<div class="sample_popup" id="newcontract${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="newcontract_drag${counter.index}" style="width:900px;height:auto;"> 
<img class="menu_form_exit"   id="newcontract_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="createNewContract"/></div>                                               
<div class="menu_form_body" id="newcontractbody${counter.index}" style="width:900px; height:300px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:10px;" height="300px;" width="100%"  scrolling="auto" id="contractFr" name="contractFr${counter.index}" allowtransparency="yes" src="blank.html"></iframe>
</div>
</div>
<!---------------------------------------New Contract Lookup End-------------------------------------------------->
</c:forEach>
</tr>
</table>
</td></tr>
</table>
     
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"><a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(3);"/></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
      
      </div>
<!---------------------------------------------- TAB 4 CONTAINER END --------------------------------------------->
<!------------------------------------------------TAB 5 CONTAINER------------------------------------------------->
 
            <div id="tab4" class="innercontent" >
      <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr><th width="55%">
     <f:message key="customerName"/>
      </th>
        <th width="30%"><span style="text-align:right"><f:message key="status"/>:
            <form:select cssClass="selectionBox" id ="sel1"  disabled="true" path="customer.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>          
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<script type="text/javascript">
var currentTime = new Date();
var month = currentTime.getMonth() + 1;
var day = currentTime.getDate();
var year = currentTime.getFullYear();
document.write(month + "/" + day + "/" + year);
</script>
</span></th>
        <th width="15%" style="text-align:right"><a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(3);"/></a></th>
      </tr>
      <tr><td colspan="3" style="padding:2px;">
      
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
      <tr>
        <th width="40%">
       <f:message key="country"/> </th>
        <th width="40%"><f:message key="registration"/></th>
        <th width="15%"><f:message key="homeCountry"/></th>
        
        <th width="50" align="right"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onVatRegAdd()"/></th>
      </tr>
   <c:forEach items="${command.custVatRegistrations}" var="custVatRegistrations" varStatus="counter">
      
      <tr>
        <td class="TDShadeBlue">
         <form:select id="sel3" cssClass="selectionBox" path="custVatRegistrations[${counter.index}].custVatRegistrationId.countryCode" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />
    </td>
           <td class="TDShadeBlue" align="center">
       <form:input cssClass="inputBox" size="15" maxlength="12" path="custVatRegistrations[${counter.index}].custVatRegistrationId.registrationId"/>
       <form:errors path="custVatRegistrations[${counter.index}].custVatRegistrationId.registrationId" cssClass="redstar"/>
           </td>
           <td>
      <form:checkbox path="custVatRegistrations[${counter.index}].homeCountryInd"/>
      <form:errors path="custVatRegistrations[${counter.index}].homeCountryInd" cssClass="redstar"/>           
           </td>
      <td width="50" class="TDShadeBlue" style="text-align:left;"><div id="div3" style="width:50px; text-align:left; margin-right:0;"> <img src="images/delete.gif" alt="Delete row" width="13" height="12" hspace="1px" border="0" onclick="onVatRegDelete('${counter.index}')" /></div>
       </td> 


</c:forEach>
</tr>
</table>
</td></tr>
</table>
     
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"><a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onVatRegSave(3);"/></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
      
      </div>
<!-----------------------------------------------TAB 5 CONTAINER END---------------------------------------------->
 <!------------------------------------------------TAB 6 CONTAINER------------------------------------------------->
 
            <div id="tab5" class="innercontent" >
      <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr><th width="55%">
     <f:message key="customerName"/>
      </th>
        <th width="30%"><span style="text-align:right"><f:message key="status"/>:
            <form:select cssClass="selectionBox" id ="sel1"  disabled="true" path="customer.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>          
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<script type="text/javascript">
var currentTime = new Date();
var month = currentTime.getMonth() + 1;
var day = currentTime.getDate();
var year = currentTime.getFullYear();
document.write(month + "/" + day + "/" + year);
</script>
</span></th>
        <th width="15%" style="text-align:right"><a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(5);"/></a></th>
      </tr>
      <tr><td colspan="3" style="padding:2px;">
      
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
      <tr>
      <th colspan="4" align="left">Notes</th>
      </tr>
      	<tr>
		<td class="TDShadeBlue"><f:message key="customerCreatedby"/>:</td>
		<td id="addedby" class="TDShadeBlue">${command.custAddBy}</td>
		
		<td class="TDShadeBlue" style="text-align:right;"><f:message key="customerAddedDateTime"/>:</td>
		<td id="datetimeadded" class="TDShadeBlue">${command.custAddDateTime}</td>
		</tr>
		<tr>
		<td class="TDShadeBlue"><f:message key="customerLastModifiedBy"/>:</td>
		<td id="addedby" class="TDShadeBlue">${command.custModiBy}</td>
		
		<td class="TDShadeBlue" style="text-align:right;"><f:message key="customerLastModifiedDateTime"/>:</td>
		<td id="datetimeadded" class="TDShadeBlue">${command.custModiDateTime}</td>
		</tr>
  		
		<tr>
		<td class="TDShadeBlue"><f:message key="summary"/>:<span class="redstar">*</span></td>
		<td  class="TDShadeBlue"><form:input cssClass="inputBox" id="notesummary" size="39"  maxlength="100" tabindex="47" path="noteSummary" />
		<form:errors path="noteSummary" cssClass="redstar"/>
		</td>
		<td class="TDShadeBlue"></td><td class="TDShadeBlue"></td>
		</tr>
		<tr>
		<td class="TDShadeBlue"><f:message key="details"/>:</td>
		<td  class="TDShadeBlue"><form:textarea path="note"  rows="4" cols="40" id="notedetails" tabindex="49"/>
		<form:errors path="note"  cssClass="redstar"/>
		</td>
		<tdclass="TDShadeBlue"></td><td class="TDShadeBlue"></td>
		</tr>
		<tr>
		<td class="TDShadeBlue" colspan="4" align="center">
		<input type="button" value="Save Note" class="button1" onclick="beforesubmit();"/>
		<input type="button"  value="Reset"  class="button1"  onclick="onReset();"/>
		</td>
		</tr>
	</table>
	</td></tr>
	<tr>
	<td colspan="3" style="padding:2px;">
	<table width="100%" align="left" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
	<tr>
	<th width="25%"><f:message key="note"/></th><th width="25%"><f:message key="noteAddedBy"/></th><th width="25%"><f:message key="noteAddedDateTime"/></th><th width="25%"><f:message key="action"/></th>
	</tr>
	<c:forEach items="${command.customerNotesList}" var="customerNotesList" varStatus="counter">
	<tr>
	<td><a href="#" onclick="selectNote('${counter.index}');" >${customerNotesList.noteSummary}</a></td>
	<td>${customerNotesList.addedByUserName}</td>
	<td>${customerNotesList.dateTimeAdded}</td>
	<td><a href="#"><img src="images/icodel.gif" alt="Delete Note" width="12" height="14" border="0" onclick="if(confirmDelete()){ onCustNoteDelete('${counter.index}'); }"></a></td>
	</tr>
	</c:forEach>
	</table>						
</td>
</tr>
</table>
     
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"><a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(5);"/></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
      
      </div>
<!-----------------------------------------------TAB 6 CONTAINER END---------------------------------------------->
             
          </div>
        </div>
        <script type="text/javascript">
      var pageNoVar = "${command.tabName}"
      dolphintabs.init("tabnav", pageNoVar)      
      </script>      
      </script>
<!------------------------------------------------ TAB CONTENT END ----------------------------------------------->
      </div>
<!--------------------------------------- MAIN CONTAINER END ----------------------------------------------------->
    </td>
  </tr>
</table>
</form:form>

<!------------------------------------------ AccountOwner Lookup START-------------------------------------------->
<div class="sample_popup" id="accountOwner" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="accountOwner_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="accountOwner_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchUser"/></div>
<div class="menu_form_body" id="accountOwnerbody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px" width="100%"  id="searchAccountOwnerFr" name="searchAccountOwnerFr" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!------------------------------------ AccountOwner Lookup END --------------------------------------------------->
<!------------------------------------Parent Customer Lookup-------------------------------------------->
<div class="sample_popup" id="parentcust" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="parentcust_drag" style="width:750px;;height:auto; "> <img class="menu_form_exit"   id="parentcust_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchParentCustomer"/></div><div class="menu_form_body" id="parentcustbody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_customer_popup.htm?inputFieldId=customer.parentCustomer.name&parentCustomerSearchFlag=y&divName1=parentcust&divName2=parentcustbody" scrolling="auto" id="searchParentCustomerFr" name="searchParentCustomerFr" allowtransparency="yes"></iframe></div></div>

<!---------------------------------------------Parent Customer Lookup--------------------------------------------->
<!-------------------------------------- Credit Analyst Lookup --------------------------------------------------->
<div class="sample_popup" id="creditan" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="creditan_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="creditan_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCreditAnalyst"/></div>
<div class="menu_form_body" id="creditanbody"  style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<table width="100%"><tr><td valign="middle">
<iframe align="left" frameborder="0" style="padding:0px;" height="530" width="100%"  scrolling="auto" id="searchCreditAnalystFr" name="searchCreditAnalystFr" allowtransparency="yes" src="blank.html"></iframe>
</td></tr></table></div></div>

<!------------------------------------------ Credit Analyst Lookup END ------------------------------------------->

<!--------------------------------------- Collector Lookup ------------------------------------------------------->
<div class="sample_popup" id="collector" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="collector_drag" style="width:750px;height:auto; ">
<img class="menu_form_exit"   id="collector_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCollector"/></div>
<div class="menu_form_body" id="collectorbody"  style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">   
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  id="searchCollectorFr" name="searchCollectorFr" allowtransparency="yes" src="blank.html"></iframe>       
</div></div>
<!------------------------------------ Collector Lookup END ------------------------------------------------------>
<!-----------------------------------------Interunit BU Lookup---------------------------------------------------->
<div class="sample_popup" id="bu" style="visibility: hidden;display:none;">
<div class="menu_form_header" id="bu_drag" style="width:750px;">
<img class="menu_form_exit" id="bu_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;<f:message key="searchBusinessUnit"/></div>
<div class="menu_form_body" id="bubody" style="width:750px;height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" scrolling="auto" id="searchBusinessUnitFr" name="searchBusinessUnitFr" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!-------------------------------------------Interunit BU Lookup------------------------------------------------->
<!------------------------------------------------Company Name Lookup--------------------------------------------->
<div class="sample_popup" id="companyname" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="companyname_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="companyname_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCompanyName"/></div>
<div class="menu_form_body" id="companynamebody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="searchCompanynameFr" name="searchCompanynameFr" allowtransparency="yes"></iframe></div></div>
<!-------------------------------------------Company Name Lookup END---------------------------------------------->


