<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script type="text/javascript" src="/phoenix/dwr/util.js"></script>
<script type="text/javascript" src="/phoenix/dwr/engine.js"></script>
<script src='/phoenix/dwr/interface/DWRUtil.js'></script>

<head>
<script language="javascript">
function locType(){ 
  var parentCust= document.getElementById("sel4");
  if(parentCust.value=="04")
  {
    document.getElementById("pcust").style.visibility = "hidden";
  }
  else
  {
    document.getElementById("pcust").style.visibility = "visible";
  }
}

function onAdd(){
    document.customerEditForm.addCustContact.value = "add";
    document.customerEditForm.tabName.value = "2";
    document.customerEditForm.submit();
  }
function sortCustContactsByContactId(){
  
  document.customerEditForm.sortFlag.value = "Contactid";
  document.customerEditForm.tabName.value = "2";
  document.customerEditForm.submit();
}
function sortCustContactsByFname(){
  
  document.customerEditForm.sortFlag.value = "Fnamesort";
  document.customerEditForm.tabName.value = "2";
  document.customerEditForm.submit();
}

function sortCustContactsByLname(){
  document.customerEditForm.sortFlag.value = "Lnamesort";
  document.customerEditForm.tabName.value = "2";
  document.customerEditForm.submit();
}
function sortCustContactsByStatus(){
  document.customerEditForm.sortFlag.value = "rstatus";
  document.customerEditForm.tabName.value = "2";
  document.customerEditForm.submit();
}

function sortCustContactsByAddressSeq(){
  document.customerEditForm.sortFlag.value = "addressSeq";
  document.customerEditForm.tabName.value = "2";
  document.customerEditForm.submit();
}
function sortCustContactsByAddressDesc(){
  document.customerEditForm.sortFlag.value = "addressDesc";
  document.customerEditForm.tabName.value = "2";
  document.customerEditForm.submit();
}
function sortCustContractsByContractCode(){
  document.customerEditForm.sortFlag.value = "con.contractCode";
  document.customerEditForm.tabName.value = "3";
  document.customerEditForm.submit();
}
function sortCustContractsByContractDesc(){
  document.customerEditForm.sortFlag.value = "con.description";
  document.customerEditForm.tabName.value = "3";
  document.customerEditForm.submit();
}


function onAddCustAddrSeq(){
    document.customerEditForm.addOrDeleteCustAddrSeq.value = "addAddr";
    document.customerEditForm.vesselFlag.value="vessel";
    document.customerEditForm.tabName.value = "1";
    document.customerEditForm.submit();
  }

  function onDeleteCustAddrSeq(index){
    document.customerEditForm.addOrDeleteCustAddrSeq.value = "deleteAddr";
    document.customerEditForm.tabName.value = "1";
    document.customerEditForm.custAddrSeqIndex.value = index;
    document.customerEditForm.vesselFlag.value="none";
    document.customerEditForm.submit();
  }
/*
function onAddCustAddrDtls(seqIndex){
    document.customerEditForm.addOrDeleteCustAddrDtl.value = "addAddrDtls";
    document.customerEditForm.custAddrSeqIndex.value = seqIndex;
    document.customerEditForm.tabName.value = "1";
    document.customerEditForm.submit();
  }
*/
  function onDeleteCustAddrDtls(seqIndex,dtlIndex){
    document.customerEditForm.addOrDeleteCustAddrDtl.value = "deleteAddrDtls";
    document.customerEditForm.custAddrSeqIndex.value = seqIndex;
    document.customerEditForm.custAddrDtlIndex.value = dtlIndex;
    document.customerEditForm.tabName.value = "1";
    document.customerEditForm.submit();
  }

  function onSave() {
    document.customerEditForm.tabName.value = "0";
    document.customerEditForm.submit();
  }

  function onContractAdd(){
    document.customerEditForm.addOrDeleteCustContract.value = "add";
    document.customerEditForm.tabName.value = "3";
    document.customerEditForm.submit();
  }

  function onContractDelete(index){
    document.customerEditForm.addOrDeleteCustContract.value = "delete";
    document.customerEditForm.tabName.value = "1";
    document.customerEditForm.contractCustIndex.value = index;
    document.customerEditForm.submit();
  }
 function onCustNoteSave(tabNumber){
    document.customerEditForm.tabName.value = tabNumber;
	document.customerEditForm.submit();
  }
  function onContractSave(tabNumber){
    document.customerEditForm.tabName.value = tabNumber;
  }

  function onVatRegAdd(){ 
    document.customerEditForm.addOrDeleteCustVatReg.value = "add";
    document.customerEditForm.tabName.value = "4";
    document.customerEditForm.submit();
  }

  function onVatRegDelete(index){
    document.customerEditForm.addOrDeleteCustVatReg.value = "delete";
    document.customerEditForm.tabName.value = "4";
    document.customerEditForm.custVatRegIndex.value = index;
    document.customerEditForm.submit();
  }
  function oncustNoteAdd()
  {
   document.customerEditForm.addOrDeleteCustNote.value = "add";
   document.customerEditForm.tabName.value = "5";
   document.customerEditForm.submit();
  }	
  function onCustNoteDelete(index){
  	document.customerEditForm.addOrDeleteCustNote.value = "delete";
    document.customerEditForm.tabName.value = "5";
    document.customerEditForm.custNoteCount.value = index;
    document.customerEditForm.submit();
  
  }
  function selectNote(index){
  	document.customerEditForm.addOrDeleteCustNote.value = "view";
    document.customerEditForm.tabName.value = "5";
    document.customerEditForm.custNoteCount.value = index;
    document.customerEditForm.submit();
  }
 function confirmDelete(notesViewOnly){
	if(notesViewOnly == 'true')
      return false;
    var yesno = confirm ("Are you sure you want to delete this note?");
    if (yesno == true)
	  return true;
    else
      return false;
	}

 function beforesubmit(){
	if(document.customerEditForm.noteSummary.value.length==0){
		confirm("Please fill Summary ");
		document.customerEditForm.noteSummary.focus();
	}else{
		document.customerEditForm.addOrDeleteCustNote.value = "add";
	    document.customerEditForm.tabName.value = "5";
	    document.customerEditForm.submit();
	}
	}
 function onReset()
 {
 		document.customerEditForm.addOrDeleteCustNote.value = "reset";
	    document.customerEditForm.tabName.value = "5";
	    document.customerEditForm.submit();
 }
  function onVatRegSave(tabNumber){
    document.customerEditForm.tabName.value = tabNumber;
  }


function billto(index){ 
  if(document.getElementById("billTo"+index).checked)
  {
    document.getElementById("billprimary"+index).style.visibility = "visible";  
  }
  else
  document.getElementById("billprimary"+index).style.visibility = "hidden"; 
 }

function soldto(index){
  if(document.getElementById("soldTo"+index).checked)
  document.getElementById("soldprimary"+index).style.visibility = "visible";
  else
  document.getElementById("soldprimary"+index).style.visibility = "hidden";
}

 function setcontactflag(rowIndex){   
  document.customerEditForm.contactFlag.value="newval";
  document.customerEditForm.rowNum.value=rowIndex;
 }

 function setnewcontactflag(rowIndex){   
  document.customerEditForm.contactFlag.value="newcontactval";
  document.customerEditForm.rowNum.value=rowIndex;
 }

function setflag(rowIndex) {   
  document.customerEditForm.addSeqFlag.value="newval";
  document.customerEditForm.rowNum.value=rowIndex;
  }

  function setflagvalue(rowIndex) {  
  document.customerEditForm.contractFlag.value="contractFlag";
  document.customerEditForm.rowNum.value=rowIndex;
  }

function updateViewContractIframeSrc(rowIndex){
    var contractid= document.getElementById("cid"+rowIndex).value;    
    document.customerEditForm.rowNum.value=rowIndex;  
    if(contractid!= "" && contractid!= null)
    {   document.getElementById('contractid'+rowIndex).setAttribute("src","view_contract_popup.htm?inputFieldId=contractCusts["+rowIndex+"].contract.contractCode&rowNum="+rowIndex+"&div1=viewcontract"+rowIndex+"&div2=viewcontractbody"+rowIndex+"&contractId="+contractid); 
    }
}

 function updateAccountOwnerIframeSrc(){
document.getElementById('searchAccountOwnerFr').setAttribute("src","search_user_popup.htm?inputFieldId=customer.accountOwnerName&div1=accountOwner&div2=accountOwnerbody&searchForm=editCustomer");
}

 function updateParentCustomerIframeSrc(){
/*document.getElementById('searchParentCustomerFr').setAttribute("src","search_customer_popup.htm?inputFieldId=customer.parentCustomer.name&parentCustomerSearchFlag=y&divName1=parentcust&divName2=parentcustbody");*/

document.getElementById('searchParentCustomerFr').setAttribute("src","search_customer_popup.htm?inputFieldId=parentCompanyName&parentCustomerSearchFlag=y&divName1=parentcust&divName2=parentcustbody");
}

 function updateCreditAnalystIframeSrc(){
document.getElementById('searchCreditAnalystFr').setAttribute("src","search_creditanalyst_popup.htm?inputFieldId=customer.creditAnalystName");
}

 function updateCollectorIframeSrc(){
document.getElementById('searchCollectorFr').setAttribute("src","search_collector_popup.htm?inputFieldId=customer.collectorName");
}

 function updateBusinessUnitIframeSrc(){
document.getElementById('searchBusinessUnitFr').setAttribute("src","search_businessunit_popup.htm?inputFieldId=customer.interunitBusUnitName");
}

 function updateNewContractIframeSrc(index){
document.getElementById('contractFr').setAttribute("src","create_contract_popup.htm?inputFieldId=contractCusts["+index+"].contract.contractCode&rowNum="+index+"&searchForm=customerCreateForm");
}
 
 function updateTaxCodeIframeSrc(index1,index2){  document.getElementById('searchVatRateFr'+index1+index2).setAttribute("src","search_vat_rate_popup.htm?inputFieldId=customerAddresses.addCustomerAddresses["+index1+"].custAddresses["+index2+"].taxCode&rowNum="+index1+index2+"&div1=vatcode"+index1+index2+"&div2=vatcodebody"+index1+index2);
}

 function updateSearchContactIframeSrc(index){
 document.getElementById('searchContactFr'+index).setAttribute("src","search_contact_popup.htm?inputFieldId=customerContacts.contactCusts["+index+"].contact.id&rowNum="+index+"&searchForm=customerEditForm&divName=contact"+index+"&divbody=contactbody"+index+"");
}

 function updateNewContactIframeSrc(index){
document.getElementById('createContactFr'+index).setAttribute("src",src="create_new_contact.htm?inputFieldId=customerContacts.contactCusts["+index+"].contact.id&rowNum="+index+"&searchForm=customerEditForm");
}

function interunitind(){  
    if(document.getElementById("interunit").checked)
    { 
		document.getElementById("interunitpend").checked=false;
		document.getElementById("interunitpend").disabled=true;
		document.getElementById("busunit").style.visibility="visible";
		//document.getElementById("anchor").style.visibility="visible";

    }
    else{
      document.getElementById("interunitpend").disabled=false;  
	  document.getElementById("busunit").style.visibility="hidden";
      //document.getElementById("anchor").style.visibility="hidden";
      document.getElementById("bunit").value="";
      } 
   }

function interunitpending(){
     if(document.getElementById("interunitpend").checked)
     {
         document.getElementById("busunit").style.visibility="hidden";
         //document.getElementById("anchor").style.visibility="hidden";
         document.getElementById("bunit").value="";
     }     
   }


 function updateViewContactIframeSrc(rowIndex)  { 
  var contactid= document.getElementById("contid"+rowIndex).value;
  
  var div1Val="viewcontact"+rowIndex;
   var div2Val="viewcontactbody"+rowIndex;
   document.customerEditForm.rowNum.value=rowIndex;
  
  if(contactid!= "" && contactid!= null)
  { document.getElementById('contactid'+rowIndex).setAttribute("src","view_contact_popup.htm?inputFieldId=customerContacts.contactCusts[${counter.index}].contact.id&rowNum=${counter.index}&div1="+div1Val+"&div2="+div2Val+"&contactId="+contactid);
  } 
 }
 function updateAddrSeqIframeSrc(rowIndex,custCode)
 {
    var div1Val="addressseq"+rowIndex;
    var div2Val="addressseqbody"+rowIndex;
    
    
    document.getElementById('addressseqFr'+rowIndex).setAttribute("src","search_address_sequence_popup.htm?inputFieldId=customerContacts.contactCusts["+rowIndex+"].contactCustId.locationNumber&custCode="+custCode+"&rowNum="+rowIndex+"&searchForm=customerEditForm");
 }
 
 function setTabName(tabIndex)
 {
  document.customerEditForm.tabName.value = tabIndex;
  document.customerEditForm.navFlag.value = "nav";
  document.customerEditForm.submit();
 }
 
function submitAddrSearch(pageNumber)
{  
document.customerEditForm.pageNumber.value = pageNumber;
document.customerEditForm.tabName.value="1";
document.customerEditForm.submit();
}
function submitContactSearch(pageNumber)
{  
document.customerEditForm.pageNumber.value = pageNumber;
document.customerEditForm.tabName.value="2";
document.customerEditForm.submit();
} 


function updateCustMultilingualIframeSrc(CustCode){
var name = document.getElementById('cname').value;
document.getElementById('createCustMLingualFr').setAttribute("src","create_multilingual_customer_popup.htm?custCode="+CustCode+"&custName="+name+"&divName=customer_additionalang&divbody=customer_additional_lang_body&searchForm=customerEditForm");
}

function updateCustAddressMultilingualIframeSrc(custCode,index1,index2,id){
var rowIndex=index1+"_"+index2;
document.getElementById('createCustAddressMLingualFr'+index1+index2).setAttribute("src","create_multilingual_customer_address_popup.htm?divName=customeraddresslang"+index1+index2+"&divbody=customeraddresslang_body"+index1+index2+"&searchForm=customerEditForm&rowNum="+index1+index2+"&custAddrId="+id+"&ccode="+custCode+"&rowNum="+rowIndex);
	
}

function setCustomerSearchFlag(){
  document.customerEditForm.existingCustomerFlag.value = "Y";
  document.getElementById('searchCompanynameFr').setAttribute("src","search_companyname_popup.htm?inputFieldId=customer.name&searchForm=customerCreateForm");
  
}

function checkCreditHold()
{
  if(document.getElementById("sel5").value == 'I'){
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
<icb:list var="parentCustomers">
  <icb:item>HQRT</icb:item>
</icb:list>
<icb:list var="childCustomers">
<icb:item>null</icb:item>
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
<icb:list var="invDeliveryMethod">
  <icb:item>invDeliveryMethod</icb:item>
</icb:list>
<icb:list var="secondaryIndustry">
  <icb:item>secondaryIndustry</icb:item>
</icb:list>

<form:form name="customerEditForm" method="POST" action="edit_customer.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>

<form:hidden path="customer.custCode"/>
<form:hidden path="addCustContact" />
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
 <form:hidden path="contactFlag"/>
<form:hidden path="contractFlag"/>
<form:hidden path="navFlag"/>
<form:hidden path="custNoteCount"/>
<form:hidden path="addOrDeleteCustNote"/>
<form:hidden path="rowNum"/>
<form:hidden path="addSeqFlag"/>
<form:hidden path="vesselFlag"/>
<form:hidden path="sortFlag"/>
<form:hidden path="pageNumber"/>
<form:hidden path="addOrDeleteCustVatReg"/>
<form:hidden path="custVatRegIndex"/>
<form:hidden path="custVatRegCount"/>

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
<!------------------------------------------- MAIN CONTAINER ----------------------------------------------------->
      <div id="MainContentContainer">
<!-------------------------------------------- TABS CONTENTS ----------------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="customer"/></span></a></li>
              <li><a href="#" rel="tab2"><span><f:message key="customerAddress"/></span></a></li>
              <li><a href="#" rel="tab3"><span><f:message key="customerContacts"/></span></a></li>
              <li><a href="#" rel="tab4"><span><f:message key="customerContracts"/></span></a></li>
              <li><a href="#" rel="tab5"><span><f:message key="customerVatReg"/></span></a></li>
              <li><a href="#" rel="tab6"><span><f:message key="customerNotes"/></span></a></li>
            </ul>
          </div>
<!------------------------------------------ Sub Menus container. Do not remove ---------------------------------->
          <div id="tab_inner">
 <!------------------------------------------------- TAB 1 CONTAINER --------------------------------------------->
            
            <div id="tab1" class="innercontent" >
      
              <table width="100%" cellpadding="0" cellspacing="0" border="0" class="mainApplTable">
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan="1" width="26%" nowrap><f:message key="customerName"/> :<span class="redstar">*</span>
                    <form:input id="cname" cssClass="inputBox" size="25" maxlength="60" path="customer.name" /> 
					<form:errors path="customer.name" cssClass="redstar"/>
                    </th>

                    <th width="19%"><f:message key="custCode"/> : ${command.customer.custCode}</th>

                    <th width="18%">
                      <span style="text-align:right"><f:message key="status"/>:
                        <form:select id="sel5" cssClass="selectionBox" path="customer.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" onchange="checkCreditHold();"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;<f:formatDate value="${command.customer.statusDate}" type="date" dateStyle="short" />
                        
                      </span>
                    </th>


               <th colspan=2 width="25%">
			 <a href="#" onclick="javascript:updateCustMultilingualIframeSrc('${command.customer.custCode}');popup_show('customer_additionalang', 'customer_additional_lang_drag', 'customer_additional_lang_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('customer_additionalang','customer_additional_lang_body');popupboxenable();"><f:message key="additionalLanguageInfo"/></a></th>


                    <th colspan=2 width="15%" bgcolor="#ffffff" style="text-align:right">
					 <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp
					 <authz:authorize ifAnyGranted="edit_customer">
					<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(0)"/></a>
					</authz:authorize>
					</th>
                  </tr>
                  
     		<tr>
     		              <table width="100%" cellpadding="0" cellspacing="0" border="0" class="mainApplTable">
                <tbody>
                  <tr bgcolor=#ffffff>
                  
                  
				<td class="TDShade" ><f:message key="interunit"/> :</td>
				<td colspan="1" class="TDShadeBlue">
			  			<form:checkbox id="interunit"  path="customer.interunitInd" onclick="interunitind()" />
				<td class="TDShade"></td>
				<td colspan=4 class="TDShadeBlue"></td>
			</tr>
			<tr>
			<td class="TDShade" ><f:message key="interunitPending"/></td>
			<td class="TDShadeBlue" >
            			<form:checkbox id="interunitpend"  path="customer.interunitPendingInd" onclick="interunitpending()" />
			</td>			
			<td class="TDShade" ><f:message key="interunitBusUnit"/>: </td>
			<td id="busunit" colspan=4  width="25%" class="TDShadeBlue" style="background-color:#faffff;">
				<form:select id="bunit" cssClass="selectionBoxbig"
			          path="customer.interunitBusUnitName"
			          items="${icbfn:dropdown('businessUnit', null)}"
			          itemLabel="name" itemValue="value"/> <form:errors
			          path="customer.interunitBusUnitName" cssClass="redstar" />
			</td>			
			</tr >			
			  <tr>
               <td width="15%" class="TDShade"><f:message key="alternateName"/> :<strong></strong></td>
                <td class="TDShadeBlue">   
                  <form:input id="sdate" cssClass="inputBox" path="customer.alternateName" size="35" maxlength="60"/>
                </td>
				<td class="TDShade"><f:message key="taxpayerIDNumber"/></td>
				<td colspan="2" class="TDShadeBlue"><form:input id="prcust" cssClass="inputBox" maxlength="60" path="customer.taxpayerIDNumber" size="35"/><form:errors path="customer.taxpayerIDNumber" cssClass="redstar"/></td>
              
				</tr>
				<tr>
				<td class="TDShade"><f:message key="locationType"/></td>
						<td class="TDShadeBlue">
							  <form:select id="sel5" cssClass="selectionBox" path="customer.locationType" items="${icbfn:dropdown('staticDropdown',locationType)}" itemLabel="name" itemValue="value" onchange="locType()"/>
						
					 <form:errors path="customer.locationType" cssClass="redstar"/> </td>
					   <td width="15%" class="TDShade"><f:message key="customerSince"/>  :<strong></strong></td>
				<td colspan="2" width="28%" class="TDShadeBlue">   
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
                    <td colspan="2" class="TDShadeBlue">
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
	<td colspan="2" class="TDShadeBlue">
	<form:input id="dbnum" cssClass="inputBox" maxlength="20" path="customer.dunnAndBradstreetNumber" size="20" disabled="true"/>
	</td>
</tr>
<tr>
		<td class="TDShade" colspan="5" ><f:message key="internalOrigin"/>:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp
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
              <form:input cssClass="inputBox" size="35" path="customer.accountOwnerName" />
               <form:errors path="customer.accountOwnerName" cssClass="redstar"/> 
               <a href="#" onClick="javascript:updateAccountOwnerIframeSrc();popup_show('accountOwner', 'accountOwner_drag', 'accountOwner_exit', 'screen-corner', 120, 20); popupboxenable();hideIt();showPopupDiv('accountOwner','accountOwnerbody');">
               <img src="images/lookup.gif" alt="lookup account Owner"  width="13" height="13" border="0">
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
		  <td class="TDShadeBlue"></td>
		  </tr>
		 <tr>
		  <td class="TDShade" colspan="4"><f:message key="primaryIndustry"/>:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
          &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<form:select cssClass="selectionBox" id="sel8" path="customer.primaryIndustry" items="${icbfn:dropdown('staticDropdown',secondaryIndustry)}" itemLabel="name" itemValue="value"/><form:errors path="customer.primaryIndustry" cssClass="error"/>
          </td>
		  </tr>
		  </tr>
		  <td class="TDShade" colspan="4"><f:message key="secondaryIndustry"/>:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
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
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	<f:message key="custNotes"/>:&nbsp&nbsp&nbsp
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
  <td class="TDShade"/>
			<td class="TDShadeBlue"/>
<td class="TDShade"><f:message key="paymentterms"/></td>
<td colspan="1" class="TDShadeBlue">
<form:select id="sel13" cssClass="selectionBoxint" path="customer.paymentTerms">
 <form:option value="-" label=" "/>
 <form:options items="${icbfn:dropdown('paymentTerms', null)}"  itemLabel="name" itemValue="value"/>
</form:select>
 </td>
</tr>




<script type="text/javascript">
checkunit();
function checkunit()
{
  if(document.getElementById("interunit").checked)
  {
	document.getElementById("busunit").style.visibility="visible";
	document.getElementById("interunitpend").disabled=true;
	//document.getElementById("anchor").style.visibility="visible";
	document.getElementById("busunit").className="unprotected";
	document.getElementById("bunit").disabled=false;
  }

  if(document.getElementById("interunitpend").checked)
  {
     //document.getElementById("anchor").style.visibility="hidden";
     document.getElementById("busunit").style.visibility="hidden";
     document.getElementById("bunit").value="";
  }
 
  if(!document.getElementById("interunit").checked && !document.getElementById("interunitpend").checked)
	{
	  //document.getElementById("anchor").style.visibility="hidden";
     document.getElementById("busunit").style.visibility="hidden";
     document.getElementById("bunit").value="";
	}
}
</script>

                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry"/></span> </td>
                        <td style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
                        <authz:authorize ifAnyGranted="edit_customer">
                        <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(0)"/></a>
                        </authz:authorize>
                        </td>
                      </tr>
                    </table></td>
                </tr>
 
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
  target="customer.interunitBusUnitName"
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

             </table>
       
            </div>
<!--------------------------------------- TAB 1 CONTAINER END ---------------------------------------------------->
      
<!------------------------------------------ TAB 2 CONTAINER ----------------------------------------------------->
<div id="tab2" class="innercontent" >
<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable" >
<tbody>
<tr bgcolor=#ffffff>
<th width="30%"><f:message key="customerName"/> : ${command.customer.name}</th>
<th width="30%"><f:message key="custCode"/> : ${command.customer.custCode}</th>
<th width="32%" colspan="2" ><f:message key="status"/>:
   <form:select id="sel1" cssClass="selectionBox" path="customer.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" disabled="true" />
&nbsp;&nbsp;&nbsp;&nbsp;<f:formatDate value="${command.customer.statusDate}" type="date" dateStyle="short" />
</th>

<th width="13%" style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
<authz:authorize ifAnyGranted="edit_customer">
<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(1)"/></a>
</authz:authorize>
</th>
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

<c:forEach items="${command.customerAddresses.addCustomerAddresses}" var="arrCustAddrs" varStatus="addrCounter">
 <tr width="100%">
 <td width="100%" style="padding:0">
          
<!--------------------------------------------VESSEL 1 CONTAINER ------------------------------------------------->
<div id="customerAddressContainer" class="customerAddress"  width="100%">
<table width="100%" cellpadding="0" cellspacing="0"  class="mainApplTable" border="0">
<tbody width="100%">
<tr width="100%">
<%--<th width="16" rowspan="2" align="center" valign="top" scope="col" style="background-image:url( images/arrowblubg.gif);"> 
<div id="arrowrtc${addrCounter.index+1}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px">
<a href="#" onClick="javascript:showcontract01('customeraddress${addrCounter.index+1}Container','arrowdnc${addrCounter.index+1}','arrowrtc${addrCounter.index+1}');">
<img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>

<div id="arrowdnc${addrCounter.index+1}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> 
<a href="#" onClick="javascript:hidecontract01('customeraddress${addrCounter.index+1}Container','arrowdnc${addrCounter.index+1}','arrowrtc${addrCounter.index+1}');">
<img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>--%>

<c:choose>
<c:when test="${addrCounter.index+1==command.custAddrSeqCount && command.vesselFlag=='vessel'}">
<th width="16" rowspan="2" align="center" valign="middle" scope="col" style="background-image:url( images/arrowblubg.gif);"> 
<div id="arrowdnc${addrCounter.index+1}"  style="visibility:visible; position:absolute; z-index: 2; margin-top:4px">
<a href="#" onClick="javascript:hidecontract01('customeraddress${addrCounter.index+1}Container','arrowdnc${addrCounter.index+1}','arrowrtc${addrCounter.index+1}');">
<img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div>
<div id="arrowrtc${addrCounter.index+1}" style="visibility:hidden;position:relative; z-index: 1; margin-left:4px ">
<a href="#" onClick="javascript:showcontract01('customeraddress${addrCounter.index+1}Container','arrowdnc${addrCounter.index+1}','arrowrtc${addrCounter.index+1}');">
<img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div></th>
 <td  class="TDShade"><f:message key="location"/>  :<span class="redstar">*</span></td>
<td  class="TDShadeBlue">
<form:input cssClass="inputBox" size="15" maxlength="3" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.custAddrSeqId.locationNumber"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.custAddrSeqId.locationNumber" cssClass="redstar"/>  
</td>
</c:when>
<c:otherwise>
<th width="16" rowspan="2" align="center" valign="top" scope="col" style="background-image:url( images/arrowblubg.gif);"> 
<div id="arrowrtc${addrCounter.index+1}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px">
<a href="#" onClick="javascript:showcontract01('customeraddress${addrCounter.index+1}Container','arrowdnc${addrCounter.index+1}','arrowrtc${addrCounter.index+1}');">
<img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>

<div id="arrowdnc${addrCounter.index+1}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> 
<a href="#" onClick="javascript:hidecontract01('customeraddress${addrCounter.index+1}Container','arrowdnc${addrCounter.index+1}','arrowrtc${addrCounter.index+1}');">
<img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
 <td  class="TDShade"><f:message key="location"/>  :<span class="redstar">*</span></td>
<td  class="TDShadeBlue">
<form:input cssClass="inputBox" size="15" maxlength="3" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.custAddrSeqId.locationNumber" disabled="true"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.custAddrSeqId.locationNumber" cssClass="redstar"/>  
</td>
</c:otherwise>
</c:choose>


<td  class="TDShade">
<form:checkbox id="billTo${addrCounter.index}" disabled="true" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.billTo"  value="1"   onclick="billto(${addrCounter.index})"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.billTo" cssClass="redstar"/>

<f:message key="billTo"/>  </td>
<td  class="TDShade">

<div id="billprimary${addrCounter.index}" style="visibility:visible">
<form:checkbox id="billPrimaryFlag${addrCounter.index}" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].primaryBillToAddr"  value="1" />
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].primaryBillToAddr" cssClass="redstar"/>
Primary </div>
</td>
<td  class="TDShade">
<form:checkbox disabled="true" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.corresponence" value="1" />
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.corresponence" cssClass="redstar"/>
<f:message key="correspondenceAddress"/></td>
<td class="TDShade" style="text-align:right" >&nbsp;</td>

</tr>

<tr>
<td class="TDShade"><f:message key="description"/> :</td>
<td class="TDShadeBlue">
<form:input cssClass="inputBox" size="70" maxlength="70" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.addressDescr"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.addressDescr" cssClass="redstar"/>  
</td>

<td class="TDShade">
<form:checkbox id="soldTo${addrCounter.index}" disabled="true" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.soldTo"  value="1"  onclick="soldto(${addrCounter.index})"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddrSeq.soldTo" cssClass="redstar"/>
<f:message key="soldTo"/> </td>

<td class="TDShade">
<div id="soldprimary${addrCounter.index}" style="visibility:visible">
<form:checkbox id="soldPrimaryFlag${addrCounter.index}" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].primarySoldToAddr"  value="1" />
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].primarySoldToAddr" cssClass="redstar"/>
Primary</div>
</td>
<td class="TDShade" style="text-align:right" ></td>
<td  class="TDShade" align="center">
<div id="div5" style="width:50px;"> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onDeleteCustAddrSeq('${addrCounter.index}')"/></a></div>
</td>
</tr>
</tbody>
</table>  
<!------------------------------------------VESSEL 1 CONTAINER END------------------------------------------------>
<c:choose>
<c:when test="${addrCounter.index+1==command.custAddrSeqCount && command.vesselFlag=='vessel'}">
<div id="customeraddress${addrCounter.index+1}Container" class="customerAddresscontainer" align="right" style="padding:0px; visibility:visible;">
</c:when>
<c:otherwise>
<div id="customeraddress${addrCounter.index+1}Container" class="customerAddresscontainer" align="right" style="padding:0px; visibility:hidden;display:none;">
</c:otherwise>
</c:choose>
<div id="customeraddress${addrCounter.index+1}Container">

<%--<div id="customeraddress${addrCounter.index+1}Container" class="customerAddresscontainer" align="right" style="padding:0px; visibility:hidden; display:none;">--%>
 <table width="100%" cellpadding="0" cellspacing="0"  class="mainApplTable" style="width:100%; ">
<tr>
<th colspan="2" class="TDShade"> <f:message key="addressDetails"/></th>
<th colspan="2" class="TDShade">&nbsp;</th>
</tr>

<c:forEach items="${arrCustAddrs.custAddresses}" var="arrCustAddrDtls" varStatus="addrDtlCounter">
 <tr>
<th colspan="2" class="TDShade"><f:message key="status"/>:
<form:select id="sel2" cssClass="selectionBox" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].effStatus" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" />
</th>
<th colspan=2 >
			 <a href="#" onclick="javascript:updateCustAddressMultilingualIframeSrc('${command.customer.custCode}','${addrCounter.index}','${addrDtlCounter.index}','${command.customerAddresses.addCustomerAddresses[addrCounter.index].custAddresses[addrDtlCounter.index].id}');popup_show('customeraddresslang${addrCounter.index}${addrDtlCounter.index}', 'customeraddresslang_drag${addrCounter.index}${addrDtlCounter.index}', 'customeraddresslang_lang_exit${addrCounter.index}${addrDtlCounter.index}', 'screen-corner', 120, 20);hideIt();showPopupDiv('customeraddresslang${addrCounter.index}${addrDtlCounter.index}','customeraddresslang_body${addrCounter.index}${addrDtlCounter.index}');popupboxenable();"><f:message key="additionalLanguageInfo"/></a></th>

<th class="TDShade" style="text-align:right"><a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0" onclick="onDeleteCustAddrDtls('${addrCounter.index}','${addrDtlCounter.index}')" /></a></th>
</tr>
<tr>
<td width="15%" class="TDShade"><f:message key="effectiveDate"/> : <span class="redstar">*</span></td>
<td class="TDShadeBlue" width="35%">

<form:input id="effDt${addrCounter.index}${addrDtlCounter.index}" cssClass="inputBox" size="35" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].effDate"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].effDate" cssClass="redstar"/>  


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

<form:input cssClass="inputBox" size="35" maxlength="3" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].taxCode"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].taxCode" cssClass="redstar"/>  
<a href="#" onClick="javascript:updateTaxCodeIframeSrc('${addrCounter.index}','${addrDtlCounter.index}');popup_show('vatcode${addrCounter.index}${addrDtlCounter.index}', 'vatcode_drag${addrCounter.index}${addrDtlCounter.index}', 'vatcode_exit${addrCounter.index}${addrDtlCounter.index}', 'screen-corner', 120, 20);hideIt();showPopupDiv('vatcode${addrCounter.index}${addrDtlCounter.index}','vatcodebody${addrCounter.index}${addrDtlCounter.index}');popupboxenable();">

<img src="images/lookup.gif" alt="lookup Tax Code" width="13" height="13" border="0" /></td>
<td class="TDShade" style="border-bottom:#CCCCCC dashed 1px">&nbsp;</td>
<td colspan="2" class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px">&nbsp;</td>
</tr>
<tr>
<td class="TDShade">

<f:message key="country"/>:<span class="redstar">*</span></strong></td>

<td class="TDShadeBlue">

<form:select id="sel3" cssClass="selectionBox" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].country" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />

</td>
<td class="TDShade">&nbsp;</td>
<td colspan="2" class="TDShadeBlue">&nbsp;</td>
</tr>

<tr>
<td class="TDShade"><f:message key="address1"/>: </td>
<td class="TDShadeBlue">

<form:input cssClass="inputBox" size="35" maxlength="70" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address1"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address1" cssClass="redstar"/> 

</td>
<td class="TDShade" ><f:message key="address2"/>: </td>
<td colspan="2" class="TDShadeBlue" >

<form:input cssClass="inputBox" size="35" maxlength="55" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address2"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address2" cssClass="redstar"/>             
</td>
</tr>
<tr>
<td class="TDShade"><f:message key="address3"/>: </td>
<td class="TDShadeBlue">

<form:input cssClass="inputBox" size="35" maxlength="55" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address3"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address3" cssClass="redstar"/>             
</td>
<td class="TDShade"><f:message key="address4"/>: </td>
<td colspan="2" class="TDShadeBlue">

<form:input cssClass="inputBox" size="35" maxlength="55" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address4"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].address4" cssClass="redstar"/> 

</td>
</tr>
<tr>
<td class="TDShade"><f:message key="city"/>:</td>
<td class="TDShadeBlue">

<form:input cssClass="inputBox" size="20" maxlength="40" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].city"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].city" cssClass="redstar"/>&nbsp;&nbsp;&nbsp;

<f:message key="inCityLimit"/>

<form:checkbox path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].inCityLimit" />
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].inCityLimit" cssClass="redstar"/>
</td>
<td class="TDShade" >&nbsp;</td>
<td colspan="2" class="TDShadeBlue" >&nbsp;</td>
</tr>
<tr>
<td class="TDShade"><f:message key="county"/>:</td>
<td class="TDShadeBlue">

<form:input cssClass="inputBox" size="35" maxlength="30" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].county"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].county" cssClass="redstar"/>

</td>
<td class="TDShade"><f:message key="postal"/>:</td>
<td colspan="2" class="TDShadeBlue">

<form:input cssClass="inputBox" size="35" maxlength="12" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].postal"/>
<form:errors path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].postal" cssClass="redstar"/>             
</td>
</tr>
<tr>
<td class="TDShade" ><f:message key="state"/> : </td>
<td class="TDShadeBlue" >

<icb:list var="countryCodeList">
<icb:item>${arrCustAddrDtls.country}</icb:item>
</icb:list>                             
<form:select id="sel5" cssClass="selectionBox" path="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].state" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" />

</td>
<td class="TDShade" >&nbsp;</td>
<td colspan="2" class="TDShadeBlue" >&nbsp;</td>
  <ajax:select
  baseUrl="country.htm"
  source="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].country"
  target="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].state"
  parameters="country.countryCode={customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].country}"
  parser="new ResponseXmlParser()" />
  <ajax:autocomplete
  baseUrl="customer.htm"
  source="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].taxCode"
  target="customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].taxCode"
  className="autocomplete"
  parameters="taxCode={customerAddresses.addCustomerAddresses[${addrCounter.index}].custAddresses[${addrDtlCounter.index}].taxCode}"
  minimumCharacters="1"
  /> 
</tr>
<!----------------------------------------------VAT Code Lookup--------------------------------------------------->
<div class="sample_popup" id="vatcode${addrCounter.index}${addrDtlCounter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="vatcode_drag${addrCounter.index}${addrDtlCounter.index}" style="width:750px;height:auto; "> <img class="menu_form_exit"   id="vatcode_exit${addrCounter.index}${addrDtlCounter.index}" src=" images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="taxCode"/></div>
<div class="menu_form_body" id="vatcodebody${addrCounter.index}${addrDtlCounter.index}"    style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  scrolling="auto" id="searchVatRateFr${addrCounter.index}${addrDtlCounter.index}" name="searchVatRateFr${addrCounter.index}${addrDtlCounter.index}" allowtransparency="yes" src="blank.html"></iframe>
</div>  
</div>
<!------------------------------------------VAT Code Lookup END -------------------------------------------------->


<!----------------------------- Customer Additional Address Language Lookup --------------------------------------------------->
<div class="sample_popup" id="customeraddresslang${addrCounter.index}${addrDtlCounter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="customeraddresslang_drag${addrCounter.index}${addrDtlCounter.index}" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="customeraddresslang_lang_exit${addrCounter.index}${addrDtlCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="createMultiLingualCustomerAddress"/></div>
<div class="menu_form_body" id="customeraddresslang_body${addrCounter.index}${addrDtlCounter.index}"  style="width:750px; height:230px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" id="createCustAddressMLingualFr${addrCounter.index}${addrDtlCounter.index}" frameborder="0" style="padding:0px;" height="230px;" width="100%"  scrolling="auto"  name="createCustAddressMLingualFr${addrCounter.index}${addrDtlCounter.index}" allowtransparency="yes"></iframe> 
</div></div>
<!--------------------------- Customer Additional Address Language Lookup END -------------------------------------------------->



</c:forEach>

</table>
</div>
</div>
</td>
</tr>
</c:forEach>
<tr>

<td align="center" nowrap>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<c:forEach items="${command.customerAddresses.pagination.pages}" var="page" varStatus="status">
<a href="#start" onClick="submitAddrSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
</c:forEach>
</td></tr>
<tr></tr>


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
<td style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
<authz:authorize ifAnyGranted="edit_customer">
<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(1)"/></a>
</authz:authorize>
</td>
</tr>
</table>
</td>
</tr>
</table>

</div>

<!------------------------------------------TAB 2 CONTAINER END--------------------------------------------------->
<!--------------------------------------------TAB 3 CONTAINER ---------------------------------------------------->
  <div id="tab3" class="innercontent" >


      <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr><th colspan=1 width="35%">
      <f:message key="customerName"/>: ${command.customer.name} </th>
     <th width="20%" nowrap><f:message key="custCode"/> : ${command.customer.custCode}</th>
         <th width="30%" nowrap><span style="text-align:right"><f:message key="status"/>:
                                     <form:select id="sel6" cssClass="selectionBox" path="customer.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" disabled="true" />
                        &nbsp;&nbsp;&nbsp;&nbsp;<f:formatDate value="${command.customer.statusDate}" type="date" dateStyle="short" /></span></th>
        <th width="15%" style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
        <authz:authorize ifAnyGranted="edit_customer">
        <a href="#"><input type="image" src="images/icosave.gif" name="submit" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(2)"/></a>
        </authz:authorize>
        </th>
      </tr>

     
	 <tr><td colspan="4" style="padding:2px;">
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" border="0">
      <tr>
	  <th width="4%">&nbsp;</th>
        <th width="25%"><a href="#" onclick="sortCustContactsByContactId();" ><f:message key="contactID"/></a></th>
        <th width="12%" nowrap> <a href="#" onclick="sortCustContactsByFname();" ><f:message key="firstName"/></a></th>
        <th width="12%" nowrap> <a href="#" onclick="sortCustContactsByLname();" ><f:message key="lastName"/></a></th>
        <th width="5%" nowrap><a href="#" onclick="sortCustContactsByAddressSeq();" ><f:message key="addressSeq"/></a></th>
        <th width="15%" nowrap><a href="#" onclick="sortCustContactsByAddressDesc();" ><f:message key="addressDescription"/></a></th>
        <th width="7%" nowrap><a href="#" onclick="sortCustContactsByStatus();" ><f:message key="relationshipStatus"/></a></th>
        <th width="2%" align="right"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onAdd()" /></th>
      </tr>

 <c:forEach items="${command.customerContacts.contactCusts}" var="arrCustContacts" varStatus="counter">
      
  <tr>
  <td width="4%" class="TDShadeBlue" align="center" nowrap>
  <form:input cssClass="inputBox" size="5" path="customerContacts.contactCusts[${counter.index}].contactSeqNum" disabled="true"/></td>
    <td width="25%" class="TDShadeBlue" align="center" nowrap>
  <c:choose>
      <c:when test="${command.customerContacts.contactCusts[counter.index].contact.id == null || command.customerContacts.contactCusts[counter.index].contact.id == ''}">
          <form:input cssClass="inputBox" size="10" path="customerContacts.contactCusts[${counter.index}].contact.id"/>
          <form:errors path="customerContacts.contactCusts[${counter.index}].contact.id" cssClass="redstar"/> 
           <a href="#" >
          <img src="images/lookup.gif" alt="lookup contact" width="13" height="13" border="0" onClick="javascript:updateSearchContactIframeSrc(${counter.index});setcontactflag(${counter.index});popup_show('contact${counter.index}', 'contact_drag${counter.index}', 'contact_exit${counter.index}', 'screen-corner', 120, 20);popupboxenable();hideIt();showPopupDiv('contact${counter.index}','contactbody${counter.index}');"></a>

          <a href="#" onClick="javascript:updateNewContactIframeSrc(${counter.index});setnewcontactflag(${counter.index});popup_show('newcontact${counter.index}', 'newcontact_drag${counter.index}', 'newcontact_exit${counter.index}', 'screen-corner', 120, 20);popupboxenable();hideIt();showPopupDiv('newcontact${counter.index}','newcontactbody${counter.index}');"> <f:message key="createNewContact"/></a>   
      </c:when>
      <c:otherwise>
          <form:input  id="contid${counter.index}" cssClass="inputBox" size="10" path="customerContacts.contactCusts[${counter.index}].contact.id"/>
          <form:errors path="customerContacts.contactCusts[${counter.index}].contact.id" cssClass="redstar"/> 
          <a href="#" onClick="javascript:updateViewContactIframeSrc(${counter.index});setflag(${counter.index});popup_show('viewcontact${counter.index}', 'viewcontact_drag${counter.index}', 'viewcontact_exit${counter.index}', 'screen-corner', 120, 20);popupboxenable();hideIt();showPopupDiv('viewcontact${counter.index}','viewcontactbody${counter.index}');">View Contact</a>   
      </c:otherwise>
  </c:choose>  
      </td>
      <td width="12%" class="TDShadeBlue" align="center">
        <form:input cssClass="inputBox" size="15" path="customerContacts.contactCusts[${counter.index}].contact.firstName" disabled="true"/> 

     </td>
     <td  width="12%" class="TDShadeBlue" align="center">
        <form:input cssClass="inputBox" size="15" path="customerContacts.contactCusts[${counter.index}].contact.lastName" disabled="true"/> 
     </td>
        <td width="5%" class="TDShadeBlue">                
        <form:input cssClass="inputBox" size="5" maxlength="3" path="customerContacts.contactCusts[${counter.index}].contactCustId.locationNumber" /> 
           <a href="#" >  <img src="images/lookup.gif" alt="lookup location Number" width="13" height="13" border="0" onClick="javascript:updateAddrSeqIframeSrc(${counter.index},'${command.customer.custCode }');setflag(${counter.index});popup_show('addressseq${counter.index}','addressseq_drag${counter.index}', 'addressseq_exit${counter.index}','screen-corner', 120, 20); hideIt();popupboxenable();"></a></td>
      <td width="15%" align="center" class="TDShadeBlue">
        <form:input  cssClass="inputBox" size="23" path="customerContacts.contactCusts[${counter.index}].custAddrSeq.addressDescr" disabled="true"/> 
       </td>
      <td width="7%" align="center" class="TDShadeBlue">
        <form:select id="sel1" cssClass="selectionBox" path="customerContacts.contactCusts[${counter.index}].status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>         
       </td>


  

           <ajax:autocomplete
              baseUrl="customer.htm"
              source="customerContacts.contactCusts[${counter.index}].contact.id"
              target="customerContacts.contactCusts[${counter.index}].contact.firstName"
              className="autocomplete"              
              parameters="contactid={customerContacts.contactCusts[${counter.index}].contact.id}"
              minimumCharacters="1"
               />
            <ajax:autocomplete
              baseUrl="customer_address.htm"
              source="customerContacts.contactCusts[${counter.index}].contactCustId.locationNumber"
              target="customerContacts.contactCusts[${counter.index}].custAddrSeq.addressDescr"
              className="autocomplete"
              parameters="customerId={customer.custCode},addressId={customerContacts.contactCusts[${counter.index}].contactCustId.locationNumber}" 
              minimumCharacters="1"
               /> 
<!-----------------------------------Create New Address Sequence Popup ------------------------------------------->
<div class="sample_popup" id="addressseq${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="addressseq_drag${counter.index}" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="addressseq_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchAddressSequence"/></div>                                                            
<div class="menu_form_body" id="addressseqbody${counter.index}"    style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  scrolling="auto" id="addressseqFr${counter.index}" name="addressseqFr${counter.index}" allowtransparency="yes" src="blank.html"></iframe>

</div>
</div>
<!---------------------------------------New Address Sequence Lookup End ----------------------------------------->
<!---------------------------------- View Contact Popup START ---------------------------------------------------->
<div class="sample_popup" id="viewcontact${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="viewcontact_drag${counter.index}" style="width:800px;"> 
<img class="menu_form_exit"   id="viewcontact_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="viewContact"/></div>
<div class="menu_form_body" id="viewcontactbody${counter.index}"  style="width:800px; height:250px;">
<iframe id="contactid${counter.index}" align="center" frameborder="0" style="padding:10px;" height="275px;" width="100%" name="createContactFr" allowtransparency="yes" src="blank.html"></iframe>
</div></div>
<!-- -------------------------------- View Contact Popup END -----------------------------------------------------> 
<!---------------------------------- Search Contact  Popup Start-------------------------------------------------->
<div class="sample_popup" id="contact${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contact_drag${counter.index}" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="contact_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchContact"/></div>
<div class="menu_form_body" id="contactbody${counter.index}"    style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  id="searchContactFr${counter.index}" name="searchContactFr" allowtransparency="yes" src="blank.html"></iframe>      
</div></div>
<!----------------------------- Search Contact  Popup End--------------------------------------------------------->
<!---------------------------------------- Create New Contact Popup ---------------------------------------------->
<div class="sample_popup" id="newcontact${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="newcontact_drag${counter.index}" style="width:800px; "> 
<img class="menu_form_exit"   id="newcontact_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="createNewContact"/></div>
<div class="menu_form_body" id="newcontactbody${counter.index}"  style="width:800px; height:275px;">
<iframe align="center" frameborder="0" style="padding:10px;" height="275px;" width="100%"  id="createContactFr${counter.index}" name="createContactFr" allowtransparency="yes" src="blank.html"></iframe>
</div></div>
<!---------------------------------------Create New Contact Popup End--------------------------------------------->
  </c:forEach>    
  </tr>
<tr>

<td align="center" colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<c:forEach items="${command.customerContacts.pagination.pages}" var="page" varStatus="status">
<a href="#start" onClick="submitContactSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
</c:forEach>
</td></tr>
<tr></tr> 
     </table>
      
      </td></tr>
      </table>
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
                        <authz:authorize ifAnyGranted="edit_customer">
                        <a href="#"><input type="image" src="images/icosave.gif" name="submit" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(2)"/></a>
                        </authz:authorize>
                        </td>
                      </tr>
                    </table></td>
                </tr>
              </table>
    
        
      </div>
<!--------------------------------------------- TAB 3 CONTAINER END ---------------------------------------------->
    

      
<!------------------------------------------------ TAB 4 CONTAINER ----------------------------------------------->
            <div id="tab4" class="innercontent" >
           
      <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr><th colspan=1 width="35%">
     <f:message key="customerName"/>: ${command.customer.name}</th>
    <th width="20%"><f:message key="custCode"/> : ${command.customer.custCode}</th>
        <th width="30%"><span style="text-align:right"><f:message key="status"/>:
     <form:select id="sel1" cssClass="selectionBox" path="customer.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" disabled="true" />
                        &nbsp;&nbsp;&nbsp;&nbsp;<f:formatDate value="${command.customer.statusDate}" type="date" dateStyle="short" /></span></th>
        <th width="15%" style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
        <authz:authorize ifAnyGranted="edit_customer">
        <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(3)"/></a>
        </authz:authorize>
        </th>
      </tr>
      <tr><td colspan="4" style="padding:2px;">
      
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
      <tr>
        <th width="40%">
       <a href="#" onclick="sortCustContractsByContractCode();" ><f:message key="contractID"/></a> </th>
        <th width="30%"><a href="#" onclick="sortCustContractsByContractDesc();" ><f:message key="description"/></a></th>
            <th width="20%"><f:message key="status"/></th>
        
      </tr>
   <c:forEach items="${command.contractCusts}" var="contractCusts" varStatus="counter">
      
      <tr>
        <td class="TDShadeBlue">
                   <form:input cssClass="inputBox" size="15" id="cid${counter.index}" path="contractCusts[${counter.index}].contract.contractCode" disabled="true"/>
          <form:errors path="contractCusts[${counter.index}].contract.contractCode" cssClass="redstar"/>  
    
      <a href="#" onClick="javascript:updateViewContractIframeSrc(${counter.index});setflag(${counter.index});popup_show('viewcontract${counter.index}', 'viewcontract_drag${counter.index}', 'viewcontract_exit${counter.index}', 'screen-corner', 50, 40); hideIt();showPopupDiv('viewcontract${counter.index}','viewcontractbody${counter.index}');popupboxenable();"><f:message key="viewContract"/></a> </td>
        </td>
           <td class="TDShadeBlue" align="center">
       <form:input cssClass="inputBox" size="50" path="contractCusts[${counter.index}].contract.description" disabled="true"/>
       <form:errors path="contractCusts[${counter.index}].contract.description" cssClass="redstar"/>
           </td>
      <td class="TDShadeBlue" align="center">
        <form:select id="sel2" cssClass="selectionBox" path="contractCusts[${counter.index}].status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" disabled="true"/>         
       </td>
        <ajax:autocomplete
        baseUrl="customer.htm"
        source="contractCusts[${counter.index}].contract.contractCode"
        target="contractCusts[${counter.index}].contract.description"
        className="autocomplete"              
        parameters="contractCodeAndDesc={contractCusts[${counter.index}].contract.contractCode}"
        minimumCharacters="1"
      />
<!-- --------------------------- Search Contract Popup ------------------------------------------------- -->
<div class="sample_popup" id="contract${counter.index}" style="visibility: hidden; display: none; ">
<div class="menu_form_header" id="contract_drag${counter.index}" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="contract_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchContract"/></div>                                                            
<div class="menu_form_body" id="contractbody${counter.index}"  style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="375px;" width="100%"  scrolling="auto" id="searchcontractFr" name="searchcontractFr" allowtransparency="yes" src="blank.html"></iframe>
</div>
</div>

<!---------------------------------------- View Contract Popup --------------------------------------------------->
<div class="sample_popup" id="viewcontract${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="viewcontract_drag${counter.index}" style="width:900px;height:auto;"> 
  <img class="menu_form_exit"   id="viewcontract_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="viewContract"/></div>                                                            
  <div class="menu_form_body" id="viewcontractbody${counter.index}" style="width:900px; height:300px;">
  <iframe id="contractid${counter.index}" align="left" frameborder="0" style="padding:10px;" height="300px;" width="100%"  scrolling="auto" id="contractFr" name="contractFr" allowtransparency="yes" src="blank.html"></iframe>
  
</div>
</div>
<!-------------------------------------------  View Lookup End --------------------------------------------------->
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
                        <td style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
                        <authz:authorize ifAnyGranted="edit_customer">
                        <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onContractSave(3)"/></a>
                        </authz:authorize>
                        </td>
                      </tr>
                    </table></td>
                </tr>
              </table>
    
      </div>
<!----------------------------------------------- TAB 4 CONTAINER END -------------------------------------------->
<!------------------------------------------------- TAB 5 CONTAINER ---------------------------------------------->
            <div id="tab5" class="innercontent" >
      <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr><%-- <th width="45%">
     <f:message key="customerName"/>
      </th> --%>
	   <tr><th width="35%">
	  <f:message key="customerName"/>: ${command.customer.name}</th>
       <th width="25%"><f:message key="custCode"/> : ${command.customer.custCode}</th>
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
        <th width="15%" style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
        <authz:authorize ifAnyGranted="edit_customer">
        <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onVatRegSave(4);"/></a>
        </authz:authorize>
        </th>
      </tr>
      <tr><td colspan="4" style="padding:2px;">
      
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
      <tr>
        <th width="25%"><f:message key="country"/> </th>
        <th width="20%"><f:message key="state"/> </th>
        <th width="20%"><f:message key="registration"/></th>
        <th width="15%"><f:message key="homeCountry"/></th>
        <th width="20" align="right"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onVatRegAdd()"/></th>
      </tr>
   <c:forEach items="${command.custVatRegistrations}" var="custVatRegistrations" varStatus="counter">
      
      <tr>
        <td class="TDShadeBlue">
         <form:select id="sel3" cssClass="selectionBox" path="custVatRegistrations[${counter.index}].custVatRegistrationId.countryCode" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />
    </td>
    <td>
    	<icb:list var="stateCountry">
			<icb:item>${custVatRegistrations.custVatRegistrationId.countryCode}</icb:item>
		</icb:list>
        <form:select id="sel8" cssClass="selectionBox" path="custVatRegistrations[${counter.index}].state" items="${icbfn:dropdown('state', stateCountry)}" itemLabel="name" itemValue="value"/>
    </td>
           <td class="TDShadeBlue" align="center">
       <form:input cssClass="inputBox" size="15" maxlength="12" path="custVatRegistrations[${counter.index}].custVatRegistrationId.registrationId"/>
       <form:errors path="custVatRegistrations[${counter.index}].custVatRegistrationId.registrationId" cssClass="redstar"/>
           </td>
           <td class="TDShadeBlue">
      <form:checkbox path="custVatRegistrations[${counter.index}].homeCountryInd"/>
      <form:errors path="custVatRegistrations[${counter.index}].homeCountryInd" cssClass="redstar"/>           
           </td>
      <td width="50" class="TDShadeBlue" style="text-align:left;"><div id="div3" style="width:50px; text-align:left; margin-right:0;"> <img src="images/delete.gif" alt="Delete row" width="13" height="12" hspace="1px" border="0" onclick="onVatRegDelete('${counter.index}')" /></div>
       </td> 
      <ajax:select
  baseUrl="country.htm"
  source="custVatRegistrations[${counter.index}].custVatRegistrationId.countryCode"
  target="custVatRegistrations[${counter.index}].state"
  parameters="country.countryCode={custVatRegistrations[${counter.index}].custVatRegistrationId.countryCode}"
  parser="new ResponseXmlParser()" />
      </div>

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
                        <td style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
                        <authz:authorize ifAnyGranted="edit_customer">
                        <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onVatRegSave(4);"/></a>
                        </authz:authorize>
                        </td>
                      </tr>
                    </table></td>
                </tr>
              </table>
</div>
<!---------------------------------------------- TAB 5 CONTAINER END --------------------------------------------->
  <!------------------------------------------------TAB 6 CONTAINER------------------------------------------------->
 
            <div id="tab6" class="innercontent" >
     		 <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr><%-- <th width="45%">
     <f:message key="customerName"/>
      </th> --%>
	   <tr><th width="35%">
	  <f:message key="customerName"/>: ${command.customer.name}</th>
       <th width="25%"><f:message key="custCode"/> : ${command.customer.custCode}</th>
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
        <th width="15%" style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
        <authz:authorize ifAnyGranted="edit_customer">
      <%--  <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onVatRegSave(4);"/></a>--%>
     <a href="#" onclick="javascript:onCustNoteSave(5)"><image src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a>

        </authz:authorize>
        </th>
      </tr>
      <tr><td colspan="4" style="padding:2px;">
      
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
		<td  class="TDShadeBlue"><form:input cssClass="inputBox" id="notesummary" size="39"  maxlength="100"  tabindex="47" path="noteSummary" disabled="${command.notesViewOnly}"/>
		<form:errors path="noteSummary" cssClass="redstar"/>
		</td>
		<td class="TDShadeBlue"></td><td class="TDShadeBlue"></td>
		</tr>
		<tr>
		<td class="TDShadeBlue"><f:message key="details"/>:</td>
		<td  class="TDShadeBlue"><form:textarea path="note"  rows="4" cols="40" id="notedetails" tabindex="49" disabled="${command.notesViewOnly}"/>
		<form:errors path="note"  cssClass="redstar"/>
		</td>
		<td class="TDShadeBlue"></td><td class="TDShadeBlue"></td>
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
	<td colspan="4" style="padding:2px;">
	<table width="100%" align="left" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
	<tr>
	<th width="25%"><f:message key="note"/></th><th width="25%"><f:message key="noteAddedBy"/></th><th width="25%"><f:message key="noteAddedDateTime"/></th><th width="25%"><f:message key="action"/></th>
	</tr>
	<c:forEach items="${command.customerNotesList}" var="customerNotesList" varStatus="counter">
	<tr>
	<td><a href="#" onclick="selectNote('${counter.index}');" >${customerNotesList.noteSummary}</a></td>
	<td>${customerNotesList.addedByUserName}</td>
	<td>${customerNotesList.dateTimeAdded}</td>
	<td><a href="#"><img src="images/icodel.gif" alt="Delete Note" width="12" height="14" border="0" onclick="if(confirmDelete('${command.notesViewOnly}')){ onCustNoteDelete('${counter.index}'); }" ></a></td>
	</tr>
	</c:forEach>
	</table>
      
      
</td></tr>
</table>
     
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"> <a href="search_customer.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
                        <authz:authorize ifAnyGranted="edit_customer">
                       <%-- <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="javascript:onVatRegSave(4);"/></a>--%>
					    <a href="#" onclick="javascript:onCustNoteSave(5)"><image src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a>
                        </authz:authorize>
                        </td>
                      </tr>
                    </table></td>
                </tr>
              </table>
<!-----------------------------------------------TAB 6 CONTAINER END---------------------------------------------->
</div>
<script type="text/javascript">
var pageNoVar = "${command.tabName}"
//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
dolphintabs.init("tabnav", pageNoVar)      
</script>
<!------------------------------------------------------- TAB CONTENT END ---------------------------------------->
</div>
<!--------------------------------------- MAIN CONTAINER END ----------------------------------------------------->
</td>
</tr>
</table>



<!------------------------------------------ AccountOwner Lookup START-------------------------------------------->
<div class="sample_popup" id="accountOwner" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="accountOwner_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="accountOwner_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchUser"/></div>
<div class="menu_form_body" id="accountOwnerbody"  style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px" width="100%"  scrolling="auto" id="searchAccountOwnerFr" name="searchAccountOwnerFr" allowtransparency="yes" src="blank.html"></iframe>
</div></div>
<!----------------------------------- AccountOwner Lookup END ---------------------------------------------------->
<!------------------------------------- Parent Customer Lookup --------------------------------------------------->
<div class="sample_popup" id="parentcust" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="parentcust_drag" style="width:750px;;height:auto; "> <img class="menu_form_exit"   id="parentcust_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchParentCustomer"/></div>
<div class="menu_form_body" id="parentcustbody"    style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">       
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  id="searchParentCustomerFr" name="searchParentCustomerFr" allowtransparency="yes" src="blank.html"></iframe>
</div></div>
<!------------------------------------- Parent Customer Lookup --------------------------------------------------->
<!---------------------------------------- Credit Analyst Lookup ------------------------------------------------->
<div class="sample_popup" id="creditan" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="creditan_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="creditan_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCreditAnalyst"/></div>
<div class="menu_form_body" id="creditanbody"  style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px" width="100%"  scrolling="auto" id="searchCreditAnalystFr" name="searchCreditAnalystFr" allowtransparency="yes" src="blank.html"></iframe>
</div></div> 
<!----------------------------------------- Credit Analyst Lookup END -------------------------------------------->
<!------------------------------------------ Collector Lookup ---------------------------------------------------->
<div class="sample_popup" id="collector" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="collector_drag" style="width:750px;height:auto; ">
<img class="menu_form_exit"   id="collector_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCollector"/></div>
<div class="menu_form_body" id="collectorbody"  style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  id="searchCollectorFr" name="searchCollectorFr" allowtransparency="yes" src="blank.html"></iframe>
</div>
</div>
<!---------------------------------------------- Collector Lookup END -------------------------------------------->
<!--------------------------------------- Interunit BU Lookup ---------------------------------------------------->
<div class="sample_popup" id="bu" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="bu_drag" style="width:750px;height:auto; ">
<img class="menu_form_exit"   id="bu_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchBusinessUnit"/></div>
<div class="menu_form_body" id="bubody"   style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">  
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  id="searchBusinessUnitFr" name="searchBusinessUnitFr" allowtransparency="yes" src="blank.html"></iframe>        
</div>
</div>   
<!------------------------------------------- Interunit BU Lookup END -------------------------------------------->


<!----------------------------- Customer Additional Language Lookup --------------------------------------------------->
<div class="sample_popup" id="customer_additionalang" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="customer_additional_lang_drag" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="customer_additional_lang_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="createMultiLingualCustomer"/></div>
<div class="menu_form_body" id="customer_additional_lang_body"  style="width:750px; height:230px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" id="createCustMLingualFr" frameborder="0" style="padding:0px;" height="230px;" width="100%"  scrolling="auto" id="searchCollectorFr" name="searchCollectorFr" allowtransparency="yes"></iframe> 
</div></div>
<!--------------------------- Customer Additional Language Lookup END -------------------------------------------------->


<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left :1px ;Top :1px;">
<IMG src="images/fake-alpha-999.gif"> </div>     
  
</div>
</div>
</div>
</form:form>
