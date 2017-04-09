<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script language="javascript">


	function onAdd()
	{
		document.contactEditForm.addOrDelete.value = "add";
		document.contactEditForm.tabName.value = "1";
		document.contactEditForm.submit();
	}
	function onDelete(index)
	{
		document.contactEditForm.addOrDelete.value = "delete";
		document.contactEditForm.tabName.value = "1";
		document.contactEditForm.index.value = index;
		document.contactEditForm.submit();
	}
	function onSave()
	{
		document.contactEditForm.tabName.value = "0";
	//document.contactEditForm.submit();
	}
    function setflag(rowIndex)
	{	 
	document.contactEditForm.addSeqFlag.value="newval";
	document.contactEditForm.rowNum.value=rowIndex;
	}

	function setflagvalue(rowIndex)
	{
	document.contactEditForm.contSeqFlag.value="custval";
	document.contactEditForm.rowNum.value=rowIndex;
	}      
  function beforesubmit(){
    if(document.contactEditForm.noteSummary.value.length==0){
		confirm("Please fill Summary ");
		document.contactEditForm.noteSummary.focus();
	} else {
		document.contactEditForm.addOrDeleteContactNote.value = "add";
	    document.contactEditForm.tabName.value = "2";
	    document.contactEditForm.submit();
	}
 }
 function onReset()
 {
 		document.contactEditForm.addOrDeleteContactNote.value = "reset";
	    document.contactEditForm.tabName.value = "2";
	    document.contactEditForm.submit();
 } 
 function onContactNoteDelete(index){
  	document.contactEditForm.addOrDeleteContactNote.value = "delete";
    document.contactEditForm.tabName.value = "2";
    document.contactEditForm.contactNoteCount.value = index;
    document.contactEditForm.submit();
 }
 function selectNote(index){
  	document.contactEditForm.addOrDeleteContactNote.value = "view";
    document.contactEditForm.tabName.value = "2";
    document.contactEditForm.contactNoteCount.value = index;
    document.contactEditForm.submit();
 }
 function onContactSave(tabNumber){
    document.contactEditForm.tabName.value = tabNumber;
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
</script>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<icb:list var="salutation">
  <icb:item>salutation</icb:item>
</icb:list>
<icb:list var="contactFlag">
  <icb:item>contactFlag</icb:item>
</icb:list>
<icb:list var="communication">
  <icb:item>communication</icb:item>
</icb:list>

<form:form name="contactEditForm" method="POST" action="edit_contact.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="contact.id" />
<form:hidden path="addOrDelete" />
<form:hidden path="index" />
<form:hidden path="contactCustCount" />
<form:hidden path="tabName" />
<form:hidden path="rowNum"/>
<form:hidden path="custCode"/>
<form:hidden path="addSeqFlag"/>
<form:hidden path="contSeqFlag"/>
<form:hidden path="addOrDeleteContactNote"/>
<form:hidden path="contactNoteCount"/>
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
            <li><a href="#" rel="tab1"><span><f:message key="contact"/></span></a></li>
            <li><a href="#" rel="tab2"><span><f:message key="contactCustomer"/></span></a></li>
			<li><a href="#" rel="tab3"><span><f:message key="contactNotes"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=2 width="50%"><f:message key="contact"/> (<f:message key="id"/>: ${command.contact.id}) </th>
                    <th width="25%"><f:message key="status"/>:
                      <form:select cssClass="selectionBox" path="contact.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>
                      <form:errors path="contact.status" cssClass="redstar"/>
                    </th>
                    <th width="30%" bgcolor="#ffffff" style="text-align:right"><a href="search_contact.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
                    <authz:authorize ifAnyGranted="edit_contact">
                    <a href="#"><input type="image" src="images/icosave.gif" name="submit" alt="Save" width="14" height="14" border="0" onclick="javascript:onSave();"/></a>
                    </authz:authorize>
                    </th>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="firstName"/>  <strong> <span class="redstar">*</span>:</strong></td>
                    <td class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="128" path="contact.firstName" />
                      <form:errors path="contact.firstName" cssClass="redstar"/>
                    </td>
                    <td class="TDShade"><f:message key="lastName"/><strong> <span class="redstar">*</span>:</strong> </td>
                    <td colspan="2" class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="128" path="contact.lastName" />
                      <form:errors path="contact.lastName" cssClass="redstar"/>
                    </td>
                  </tr>
                 <tr>
                    <td class="TDShade"><f:message key="title"/>: </td>
                    <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35" maxlength="32" path="contact.title"/></td>
                    <td class="TDShade"><f:message key="salutation"/>:</td>
                    <td colspan="2" class="TDShadeBlue">
                      <form:select cssClass="selectionBox" path="contact.salutationCd" items="${icbfn:dropdown('staticDropdown',salutation)}" itemLabel="name" itemValue="value"/>
                      <form:errors path="contact.salutationCd" cssClass="redstar"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="workPhone"/>: </td>
                    <td class="TDShadeBlue">
                      <form:input cssClass="inputBox" maxlength="64" size="35" path="contact.workPhone" />
                      <form:errors path="contact.workPhone" cssClass="redstar"/>
                    </td>
                    <!-- START --- added for the issue-104407 -->
                    <td class="TDShade"><f:message key="fax"/>: </td>
                    <td colspan="2" class="TDShadeBlue">
                      <form:input cssClass="inputBox" maxlength="30" size="35" path="contact.fax" />
                      <form:errors path="contact.fax" cssClass="redstar"/>
                    </td>                  
                    <!-- END 104407-->
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="personalPhone"/>:</td>
                    <td class="TDShadeBlue">
                      <form:input cssClass="inputBox" maxlength="64" size="35" path="contact.personalPhone" />
                      <form:errors path="contact.personalPhone" cssClass="redstar"/>
                    </td>
                    <td class="TDShade"><f:message key="worke-mail"/>: </td>
                    <td colspan="2" class="TDShadeBlue">
                      <form:input cssClass="inputBox" maxlength="70" size="35" path="contact.workEmail" />
                      <form:errors path="contact.workEmail" cssClass="redstar"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="cellPhone"/>: </td>
                    <td class="TDShadeBlue">
                      <form:input cssClass="inputBox" maxlength="64" size="35" path="contact.cellPhone" />
                      <form:errors path="contact.cellPhone" cssClass="redstar"/>
                    </td>
                   <td class="TDShade"><f:message key="personalE-mail"/>: </td>
                    <td colspan="2" class="TDShadeBlue">
                      <form:input cssClass="inputBox" maxlength="70" size="35" path="contact.personalEmail" />
                      <form:errors path="contact.personalEmail" cssClass="redstar"/>
                    </td>
                  </tr>
                
                  <tr>
                    <td class="TDShade"><f:message key="contactFlag"/>: </td>
                    <td class="TDShadeBlue">
                      <form:select cssClass="selectionBox" path="contact.contactFlag" items="${icbfn:dropdown('staticDropdown',contactFlag)}" itemLabel="name" itemValue="value"/>
                      <form:errors path="contact.contactFlag" cssClass="redstar"/>
                    </td>
                    <td class="TDShade"><f:message key="preferredCommunicator"/>: </td>
                    <td colspan="2" class="TDShadeBlue">
                      <form:select cssClass="selectionBox" path="contact.prefCommunication" items="${icbfn:dropdown('staticDropdown',communication)}" itemLabel="name" itemValue="value"/>
                      <form:errors path="contact.prefCommunication" cssClass="redstar"/>
                    </td>
                  </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry" /></span> </td>
                        <td style="text-align:right"><a href="search_contact.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp
                          <authz:authorize ifAnyGranted="edit_contact">
                          <a href="#"><input type="image" src="images/icosave.gif" name="submit" alt="Save" width="14" height="14" border="0"  onclick="javascript:onSave();"/></a>
                          </authz:authorize>
                        </td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
      
      <!-- ------------------------- TAB 2 CONTAINER ----------------------------------------- -->
            <div id="tab2" class="innercontent" >
      <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr><th width="50%">
      <f:message key="contact"/> (<f:message key="id"/>: ${command.contact.id}) 
      </th>
        <th width="25%"><f:message key="status"/>:
<form:select id="sel1" cssClass="selectionBox" path="contact.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" disabled="true" /></th>
<th width="15%" style="text-align:right"><a href="search_contact.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
<authz:authorize ifAnyGranted="edit_contact">
<a href="#"><input type="image" src="images/icosave.gif" name="submit" alt="Save" width="14" height="14" border="0"  /></a>
</authz:authorize>
</th>
</tr>


<tr><td colspan="3" style="padding:2px;">
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<th>&nbsp;</th>
 <th><f:message key="customerid"/></th>
        <th><f:message key="name"/></th>
        <th><f:message key="addressSeq"/></th>
        <th><f:message key="addressDescription"/></th>
        <th><f:message key="status"/></th>

<th width="50" align="right">&nbsp;&nbsp;<img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onAdd()" /></th>      
</tr>
<c:forEach items="${command.contactCusts}" var="contactCust" varStatus="counter">
<tr>
<td class="TDShadeBlue"><form:input cssClass="inputBox" maxlength="3" size="3" path="contactCusts[${counter.index}].contactSeqNum" disabled="true"/></td>
<td class="TDShadeBlue">                
<form:input cssClass="inputBox" size="12" maxlength="15" path="contactCusts[${counter.index}].customer.custCode"/>
<form:errors path="contactCusts[${counter.index}].customer.custCode" cssClass="redstar"/>
<a href="#" onClick="javascript:setflagvalue(${counter.index});popup_show('customerid${counter.index}','customerid_drag${counter.index}', 'customerid_exit${counter.index}','screen-corner', 120, 20); hideIt();popupboxenable();"> <img src="images/lookup.gif" alt="lookup customer" width="13" height="13" border="0"></a>
</td>

			<td class="TDShadeBlue" align="center">
					<form:input cssClass="inputBox" maxlength="40" size="40" path="contactCusts[${counter.index}].customer.name" disabled="true"/> 
			 </td>
			  <td class="TDShadeBlue">                
					<form:input cssClass="inputBox" size="3"  maxlength="3" path="contactCusts[${counter.index}].custAddrSeq.custAddrSeqId.locationNumber"/>
					<form:errors path="contactCusts[${counter.index}].custAddrSeq.custAddrSeqId.locationNumber" cssClass="redstar"/>
<a href="#" onClick="javascript:setflag(${counter.index});popup_show('addressseq${counter.index}', 'addressseq_drag${counter.index}', 'addressseq_exit${counter.index}', 'screen-corner', 120, 20); hideIt();popupboxenable();">  <img src="images/lookup.gif" alt="lookup address" width="13" height="13" border="0"></a></td>

			<td align="center">
					<form:input cssClass="inputBox" size="40" maxlength="30" disabled="true" path="contactCusts[${counter.index}].custAddrSeq.addressDescr"/> 
			 </td>
			<td align="center">
<form:select id="sel1" cssClass="selectionBox" path="contactCusts[${counter.index}].status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>
			 </td>

			 <td width="50" class="TDShadeBlue" style="text-align:left;"><div id="div3" style="width:50px; text-align:left; margin-right:0;">&nbsp; <img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0" onclick="onDelete('${counter.index}')" /></div></td> 

	

					<ajax:autocomplete
						  baseUrl="customer.htm"
						  source="contactCusts[${counter.index}].customer.custCode"
						  target="contactCusts[${counter.index}].customer.name"
						  className="autocomplete"						  
						  parameters="custCode={contactCusts[${counter.index}].customer.custCode}"
						  minimumCharacters="1"
						   />
						<ajax:autocomplete
						  baseUrl="customer_address.htm"
						  source="contactCusts[${counter.index}].custAddrSeq.custAddrSeqId.locationNumber"
						  target="contactCusts[${counter.index}].custAddrSeq.addressDescr"
						  className="autocomplete"	  
					parameters="customerId={contactCusts[${counter.index}].customer.custCode},addressId={contactCusts[${counter.index}].custAddrSeq.custAddrSeqId.locationNumber}"
						  minimumCharacters="0"
						   />



<!-- --------------------------- Customer Id Sequence Popup ------------------------------------------------- -->
<div class="sample_popup" id="customerid${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="customerid_drag${counter.index}" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="customerid_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCustomers"/></div>																														
<div class="menu_form_body" id="customeridbody${counter.index}"  style="width:750px;height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_customer_id_popup.htm?inputFieldId=contactCusts[${counter.index}].customer.custCode&rowNum=${counter.index}&searchForm=contactEditForm" scrolling="auto" id="customeridFr" name="customeridFr" allowtransparency="yes" ></iframe>
</div>
</div>
<!-- --------------------------- Customer Id Lookup End ------------------------------------------------- -->

<!-- --------------------------- Create New Address Sequence Popup ------------------------------------------------- -->
<div class="sample_popup" id="addressseq${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="addressseq_drag${counter.index}" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="addressseq_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchAddressSequence"/></div>																														
<div class="menu_form_body" id="addressseqbody${counter.index}"   style="width:750px;height:520px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="520px;" width="100%" src="search_address_sequence_popup.htm?inputFieldId=contactCusts[${counter.index}].custAddrSeq.custAddrSeqId.locationNumber
&custCode=${command.contactCusts[counter.index].customer.custCode}&rowNum=${counter.index}&searchForm=contactEditForm" scrolling="auto" id="addressseqFr" name="addressseqFr" allowtransparency="yes" ></iframe>

</div>
</div>											
<!-- ---------------------------  New Address Sequence Lookup End ------------------------------------------------- -->

</c:forEach>

</tr>

</table>

</td>
</tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>&nbsp;</td>
<td style="text-align:right"><a href="search_contact.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
<authz:authorize ifAnyGranted="edit_contact">
<a href="#"><input type="image" src="images/icosave.gif" name="submit" alt="Save" width="14" height="14" border="0"/></a>
</authz:authorize>
</td>
</tr>
</table></td>
</tr>
</table>
</div>
<!-- ------------------------- TAB 2 CONTAINER END ----------------------------------------- -->
<!-- ------------------------- TAB 3 CONTAINER ----------------------------------------- -->
	<div id="tab3" class="innercontent" >
	  <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr><th width="60%">
      <f:message key="contact"/> (<f:message key="id"/>: ${command.contact.id})
      </th>
        <th width="25%"><f:message key="status"/>:
                       <form:select id="sel1" cssClass="selectionBox" path="contact.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" disabled="true" />                    </th>
        <th width="15%" style="text-align:right"><a href="search_contact.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" name="submit" alt="Save" width="14" height="14" border="0" onclick="javascript:onContactSave(2);"/></a></th>
      </tr>
      <tr><td colspan="3" style="padding:2px;">
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
      <tr>
      <th colspan="4" align="left">Notes</th>
      </tr>
		<tr>
		<td class="TDShadeBlue"><f:message key="contactCreatedby"/>:</td>
		<td id="addedby" class="TDShadeBlue">${command.contactAddBy}</td>
		<td class="TDShadeBlue" style="text-align:right;"><f:message key="contactAddedDateTime"/>:</td>
		<td id="datetimeadded" class="TDShadeBlue">${command.contactAddDateTime}</td>
		</tr>
		<tr>
		<td class="TDShadeBlue"><f:message key="contactLastModifiedBy"/>:</td>
		<td id="addedby" class="TDShadeBlue">${command.contactModiBy}</td>
		
		<td class="TDShadeBlue" style="text-align:right;"><f:message key="contactLastModifiedDateTime"/>:</td>
		<td id="datetimeadded" class="TDShadeBlue">${command.contactModiDateTime}</td>
		</tr>
  		
		<tr>
		<td class="TDShadeBlue"><f:message key="summary"/>:<span class="redstar">*</span></td>
		<td  class="TDShadeBlue"><form:input cssClass="inputBox" id="notesmry" size="39"  maxlength="100" tabindex="47" path="noteSummary" />
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
	<c:forEach items="${command.contactNotesList}" var="contactNotesList" varStatus="counter">
	<tr>
	<td><a href="#" onclick="selectNote('${counter.index}');" >${contactNotesList.noteSummary}</a></td>
	<td>${contactNotesList.addedByUserName}</td>
	<td>${contactNotesList.dateTimeAdded}</td>
	<td><a href="#"><img src="images/icodel.gif" alt="Delete Note" width="12" height="14" border="0" onclick="if(confirmDelete()){ onContactNoteDelete('${counter.index}'); }"></a></td>
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
                        <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt">marked fields are mandatory</span> </td>
                        <td style="text-align:right"><a href="search_contact.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp
                          <a href="#"><input type="image" src="images/icosave.gif"  alt="Save" width="14" height="14" border="0" onclick="javascript:onContactSave(2);"/></a>
                        </td>
                      </tr>
                    </table></td>
                </tr>
              </table> 
			</div>
<!-- ------------------------- TAB 3 CONTAINER END ----------------------------------------- -->
</div>
</div>
<script type="text/javascript">
var pageNoVar = "${command.tabName}"
//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
dolphintabs.init("tabnav", pageNoVar)      
</script>
<!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
</div>
<!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
</td>
</tr>
</table>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>
</form:form>

