<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script language="javascript">      
  function beforesubmit(){
	if(document.contractNoteForm.notesmry.value.length==0){
		confirm("Please fill Summary ");
		document.contractNoteForm.notesmry.focus();
	} else {
		document.contractNoteForm.addOrDeleteContractNote.value = "add";
	    document.contractNoteForm.tabName.value = "8";
	    document.contractNoteForm.submit();
	}
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
 function onReset()
 {
 		document.contractNoteForm.addOrDeleteContractNote.value = "reset";
	    document.contractNoteForm.tabName.value = "8";
	    document.contractNoteForm.submit();
 } 
 function onContractNoteDelete(index){
  	document.contractNoteForm.addOrDeleteContractNote.value = "delete";
    document.contractNoteForm.tabName.value = "8";
    document.contractNoteForm.contractNoteCount.value = index;

    document.contractNoteForm.submit();
 }
 function selectNote(index){
  	document.contractNoteForm.addOrDeleteContractNote.value = "view";
    document.contractNoteForm.tabName.value = "8";
    document.contractNoteForm.contractNoteCount.value = index;
    document.contractNoteForm.submit();
 }
 function onContractSave(tabNumber){
    document.contractNoteForm.tabName.value = tabNumber;
 }
 function goToContractTab(tabName)
 {
    document.contractNoteForm.operationType.value = "switchTab";
    document.contractNoteForm.goToContractTab.value = tabName;    
    document.contractNoteForm.submit();
 }
 function doOperation(myOperationType)
 {
    document.contractNoteForm.operationType.value = myOperationType;
    document.contractNoteForm.submit();
 }
</script>
<form:form name="contractNoteForm" method="POST" action="edit_contract_notes.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>

<form:hidden path="tabName" />
<form:hidden path="addOrDeleteContractNote"/>
<form:hidden path="contractNoteCount"/>
<input type="hidden" name="operationType" value="" />
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
              <li><a href="javascript:goToContractTab('main');" rel="tab8"><span><f:message key="contract"/> </span></a></li>
              <li><a href="javascript:goToContractTab('customer');" rel="tab8"><span><f:message key="contractCustomers"/></span></a></li>
              <li><a href="javascript:goToContractTab('attachment');" rel="tab8"><span><f:message key="contractAttachments"/></span></a></li>
              <li><a href="javascript:goToContractTab('zone');" rel="tab8"><span><f:message key="zones"/></span></a></li>
              <li><a href="javascript:goToContractTab('inspection');" rel="tab8"><span><f:message key="cargoInspection"/></span></a></li>
              <li><a href="javascript:goToContractTab('service');" rel="tab8"><span><f:message key="contractsServices"/></span></a></li>
              <li><a href="javascript:goToContractTab('test');" rel="tab8"><span><f:message key="contractTest"/></span></a></li>
			  <li><a href="javascript:goToContractTab('slate');" rel="tab8"><span><f:message key="contractSlates"/></span></a></li>
              <li><a href="#" rel="tab8"><span><f:message key="contractNotes"/></span></a></li>
            </ul>
          </div>
          <!---------------------------- Sub Menus container. Do not remove ------------------------------------------------->
          <div id="tab_inner">
            <!---------------------------------------------- TAB 3 CONTAINER -------------------------------------------------->
            <div id="tab8" class="innercontent" >
            <%@ include file="contract_notes_tab.jsp" %>
            </div>
            <!---------------------------------------------- TAB 3 CONTAINER END ---------------------------------------------->
          </div>
        </div>
        <script type="text/javascript">
          dolphintabs.init("tabnav", 8)      
        </script>
        <!------------------------------------------------------- TAB CONTENT END ----------------------------------------->
      </div>
      <!-------------------------------------------- MAIN CONTAINER END ------------------------------------------------->
    </td>
  </tr>
</table>
</form:form> 


