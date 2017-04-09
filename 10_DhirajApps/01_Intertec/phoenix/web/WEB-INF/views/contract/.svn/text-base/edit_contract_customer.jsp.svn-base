<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script language="javascript">      
  function doOperation(myOperationType)
  {
    document.contractEditForm.loadCustContflag.value = "none";
    document.contractEditForm.operationType.value = myOperationType;
    document.contractEditForm.submit();
  }

  function onAdd()
  {
    document.contractEditForm.loadCustContflag.value = "none";
    document.getElementById("addOrDelete").value="add";
    document.getElementById("tabName").value="2";
    document.contractEditForm.submit();
  }
  
  function onDelete(index)
  {
    document.contractEditForm.loadCustContflag.value = "none";
    document.contractEditForm.addOrDelete.value = "delete";
    document.getElementById("tabName").value="1";
    document.getElementById("index").value=index;
    document.contractEditForm.submit();
  }

  function onAddContact(contractCustIndex)
  {
    document.contractEditForm.loadCustContflag.value = "none";
    document.contractEditForm.addOrDeleteContractContact.value = "addContact";
    document.contractEditForm.contractCustIndex.value = contractCustIndex;
    document.contractEditForm.tabName.value = "1";
    document.contractEditForm.submit();
  }
  
  function onDeleteContact(contractCustIndex,contractCustContactIndex)
  {
    document.contractEditForm.loadCustContflag.value = "none";
    document.contractEditForm.addOrDeleteContractContact.value = "deleteContact";
    document.contractEditForm.contractCustIndex.value = contractCustIndex;
    document.contractEditForm.contractCustContactIndex.value = contractCustContactIndex;
    document.contractEditForm.tabName.value = "1";
    document.contractEditForm.submit();

  }
   
  function setflagvalue(rowIndex)
  {
    document.contractEditForm.loadCustContflag.value = "none";
    document.contractEditForm.contSeqFlag.value="custval";
    document.contractEditForm.rowNum.value=rowIndex;
  } 

  function setcontactflag(rowIndex,rowContactIndex)
  { 
    document.contractEditForm.loadCustContflag.value = "none";
    document.contractEditForm.contactSearchFlag.value="newval";
    document.contractEditForm.rowNum.value=rowIndex;
    document.contractEditForm.rowNumContact.value=rowContactIndex;
  }

  function updateCustomerIframeSrc(index)
  {
    document.getElementById('customeridFr'+index).setAttribute("src","search_customer_id_popup.htm?inputFieldId=addContractCusts["+index+"].contractCust.contractCustId.custCode&rowNum="+index+"&searchForm=contractEditForm&divName1=customerid"+index+"&divName2=customeridbody"+index);
  }

  function  updateContactIframeSrc(indexf,indexs,ccode)
  {
    document.getElementById('searchContactFr'+indexf+indexs).setAttribute("src","search_contact_popup.htm?inputFieldId=addContractCusts["+indexf+"].contractCustContacts["+indexs+"].contractCustContactId.contactId&rowNum="+indexf+"&rowNumContact="+indexs+"&searchForm=contractEditForm&divName=contactid"+indexf+indexs+"&divbody=contactbody"+indexf+indexs+"&custCode="+ccode);
  } 
  function updateCustomerNotesIframeSrc(index,ccode)
  { document.getElementById('viewCustomerNotesFr'+index).setAttribute("src","view_customer_notes_popup.htm?custCode="+ccode+"&divName=viewCustomerNote"+index+"&divBody=viewCustomerNotebody"+index);
  }
  function resetform()
  {
    document.contractEditForm.loadCustContflag.value = "none";
    document.contractEditForm.showAttachFile.value="none";
    //document.contractEditForm.submit();
  }

  function getMyFormName()
  {
    return "contractEditForm";
  }
  
  function goToContractTab(tabName)
  {
    document.contractEditForm.operationType.value = "switchTab";
    document.contractEditForm.goToContractTab.value = tabName;    
    document.contractEditForm.submit();
  }
  function pageNavigate(contractCode,pageNumber)
  {		
		  document.contractEditForm.ctrCode.value = contractCode;
		  document.contractEditForm.pageNum.value = pageNumber;
		  document.contractEditForm.pageNavigateFlag.value = "true";
		  document.contractEditForm.submit();
  }
  function resetContact()
  {
	document.contractEditForm.contactSearchFlag.value="none";
    document.contractEditForm.rowNum.value=0;
    document.contractEditForm.rowNumContact.value=0;
  }
  function resetCustomer()
  {
	document.contractEditForm.contSeqFlag.value="none";
    document.contractEditForm.rowNum.value=0;
  }
  //START: To fix the issue 99933
  function updateCustomerSearchIframeSrc()
  {
  	 document.getElementById('searchCustIdFr').setAttribute("src","search_customer_contractCust.htm?inputFieldId=searchCustomerId&searchForm=contractEditForm&contractCode=${command.contract.contractCode}&divName1=dsearchCustId&divName2=dsearchCustIdbody");  
  }

  function updateContactSearchIframeSrc()
  {
	document.getElementById('searchContactIdFr').setAttribute("src","search_contact_contractCust.htm?inputFieldId=searchContactId&searchForm=contractEditForm&contractCode=${command.contract.contractCode}&divName1=dsearchContactId&divName2=dsearchContactIdbody");  	
  }
  
 /* function loadCustContrData(){
  	
	try {
	  	if("" == document.contractEditForm.searchCustomerId.value 
	  	  || document.contractEditForm.searchCustomerId.value.length == 0 ){
	  	  	document.contractEditForm.searchCustomerName.value = "";
	  	  }
		if("" == document.contractEditForm.searchContactId.value 
	  	  || document.contractEditForm.searchContactId.value.length == 0 ){
	  	  	document.contractEditForm.searchContactName.value = "";
	  	  }	  	  
  	}
  	catch(e){}
  
  	document.contractEditForm.loadCustContflag.value = "contadd";
  	document.contractEditForm.submit();
  }  */
  
  /*function resetContractContactFld(objTxt){
	  
  	document.contractEditForm.searchContactName.value = '';
  	//loadCustContrData();  	
  }
  
  function resetContactCusts(objTxt){
	  
  	document.contractEditForm.searchCustomerName.value = '';
  	//loadCustContrData();  	
  }*/
 function loadContractCustData(){ 	
  	document.contractEditForm.loadCustContflag.value = "contadd";
  	document.contractEditForm.submit();
 }
 function showcontractcustTable(){
	document.getElementById("divcontractcustomers").style.visibility = "visible"; 
	document.getElementById("divcontractcustomers").style.display = "block"; 
	document.getElementById("bluarrowdowncc1").style.visibility = "visible"; 
	document.getElementById("bluarrowrighcc1").style.visibility = "hidden";
}
function hidecontractcustTable(){
	document.getElementById("divcontractcustomers").style.visibility = "hidden";
	document.getElementById("divcontractcustomers").style.display = "none"; 
	document.getElementById("bluarrowrighcc1").style.visibility = "visible";
	document.getElementById("bluarrowdowncc1").style.visibility = "hidden";
}
  //END: To fix the issue 99933
</script>

<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<icb:list var="workingPb">
  <icb:item>workingPb</icb:item>
</icb:list>
<icb:list var="workingUOM">
  <icb:item>workingUOM</icb:item>
</icb:list>
<icb:list var="invoiceType">
  <icb:item>invoiceType</icb:item>
</icb:list>
<icb:list var="contractAttachType">
  <icb:item>contractAttachType</icb:item>
</icb:list>
<icb:list var="audienceType">
  <icb:item>audienceType</icb:item>
</icb:list>

<icb:list var="localContractCode">
  <icb:item>${command.contract.contractCode}</icb:item>
</icb:list>
 <icb:list var="operator">
  <icb:item>operator</icb:item>
</icb:list>
<icb:list var="invDeliveryMethod">
  <icb:item>invDeliveryMethod</icb:item>
</icb:list>
<icb:list var="InvoiceLanguage">
<icb:item>InvoiceLanguage</icb:item>
</icb:list>
<form:form name="contractEditForm" method="POST" action="edit_contract_customer.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>
<form:hidden path="uploadedFileName"/>
<form:hidden path="deleteAttach" />
<form:hidden path="contractAttachCount" />
<form:hidden path="contractAttachIndex"/>
<form:hidden path="tabName" />
<form:hidden path="status" id="status" />
<form:hidden path="addOrDelete" />
<form:hidden path="index" />
<form:hidden path="contractCustCount"/>
<form:hidden path="contactCustCount" />
<form:hidden path="contractCustIndex" />
<form:hidden path="contractCustContactIndex" />
<form:hidden path="addOrDeleteContractContact" />
<form:hidden path="contSeqFlag"/>
<form:hidden path="rowNum"/>
<form:hidden path="rowNumContact"/>
<form:hidden path="contactSearchFlag"/>
<form:hidden path="custCode"/>
<form:hidden path="userFlag"/>
<form:hidden path="showAttachFile"/>
<form:hidden path="ctrCode"/>
<form:hidden path="pageNum"/>
<form:hidden path="pageNavigateFlag"/>
<!-- START: To fix the issue 99933 -->
<form:hidden path="loadCustContflag"/>
<!-- END: To fix the issue 99933 -->

<input type="hidden" name="operationType" value="" />
<input type="hidden" name="cfgContractIndex" value="" />
<input type="hidden" name="goToContractTab" value="" />

<c:set var="userDateFormat" value="${icbfn:userDateFormat()}" />

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
      <!------------------------------------------------ MAIN CONTAINER ------------------------------------------------->
      <div id="MainContentContainer">
      <!------------------------------------------------ TABS CONTENTS -------------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="javascript:goToContractTab('main');" rel="tab2"><span><f:message key="contract"/> </span></a></li>
              <li><a href="#" rel="tab2"><span><f:message key="contractCustomers"/></span></a></li>
              <li><a href="javascript:goToContractTab('attachment');" rel="tab2"><span><f:message key="contractAttachments"/></span></a></li>
              <li><a href="javascript:goToContractTab('zone');" rel="tab2"><span><f:message key="zones"/></span></a></li>
              <li><a href="javascript:goToContractTab('inspection');" rel="tab2"><span><f:message key="cargoInspection"/></span></a></li>
              <li><a href="javascript:goToContractTab('service');" rel="tab2"><span><f:message key="contractsServices"/></span></a></li>
              <li><a href="javascript:goToContractTab('test');" rel="tab2"><span><f:message key="contractTest"/></span></a></li>
              <li><a href="javascript:goToContractTab('slate');" rel="tab2"><span><f:message key="contractSlates"/></span></a></li>
              <li><a href="javascript:goToContractTab('crtnotes');" rel="tab2"><span><f:message key="contractNotes"/></span></a></li>
            </ul>
          </div>
          <!---------------------------- Sub Menus container. Do not remove ------------------------------------------------->
          <div id="tab_inner">
            <!---------------------------------------- TAB 2 CONTAINER -------------------------------------------------------->
            <div id="tab2" class="innercontent" >
            <%@ include file="contract_customer_tab.jsp" %>
            </div>
            <!--------------------------------------------- TAB 2 CONTAINER END ----------------------------------------------->
          </div>
        </div>
        <script type="text/javascript">
          dolphintabs.init("tabnav", 1)      
        </script>
        <!------------------------------------------------------- TAB CONTENT END ----------------------------------------->
      </div>
      <!-------------------------------------------- MAIN CONTAINER END ------------------------------------------------->
    </td>
  </tr>
</table>

<script>
try{	
	if(document.contractEditForm.loadCustContflag && 
		"contadd" == document.contractEditForm.loadCustContflag.value){
		showcontractcustTable();
	}
}
catch(e){}
</script>

</form:form> 
