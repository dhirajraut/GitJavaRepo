<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
  <%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script>

function onDelete(noteId)
  {
    document.getElementById("deleteNoteId").value=noteId;
    document.jobNotePopUpForm.submit();
  }
  
function onSelectNote(noteId)
  {
  document.getElementById("selectedNoteId").value=noteId;
  	document.jobNotePopUpForm.submit();
  }
  
  function onReset()
  {
    document.getElementById("reset").value="reset";
  	document.jobNotePopUpForm.submit();
  
  }

function confirmDelete(){
    var yesno = confirm ("Are you sure you want to delete this note?");
    if (yesno == true)
	  return true;
    else
      return false;
}

function beforesubmit(){
	if(document.jobNotePopUpForm.notesummary.value.length==0){
		confirm("Please fill Summary ");
		setFocus(document.jobNotePopUpForm.notesummary);
	}else{
		document.jobNotePopUpForm.submit();
	}
}

//Set Focus
	function setFocus(id){
		try {
			id.focus();
		}
		catch (e){}
    }
</script>
<icb:list var="noteType">
  <icb:item>noteType</icb:item>
</icb:list>
<icb:list var="visibility">
  <icb:item>visibility</icb:item>
</icb:list>
<form:form name="jobNotePopUpForm" method="POST" action="create_job_add_customer_note_popup.htm">

<form:hidden path="deleteNoteId" />
<form:hidden path="selectedNoteId" />
<form:hidden id="noteid" path="jobContractNote.id" />
<input type="hidden" name="jobContractId" value="${command.jobContract.id}" />
<input type="hidden" name="reset"/>
 	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>
	  
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<td><f:message key="addedBy"/>:</td>
<td id="addedby"><c:out value="${command.jobContractNote.addedByUserName}"/></td>
<td><f:message key="dateTimeAdded"/>:</td>
<td id="datetimeadded" class="TDShadeGrey"><f:formatDate value="${command.jobContractNote.dateTimeAdded}" type="date" dateStyle="short" /></td>
</tr>
<tr>
<td><f:message key="summary"/>:<span class="redstar">*</span></td>
<td colspan="3"><form:input cssClass="inputBox" id="notesummary" size="35"  maxlength="100" tabindex="47" path="jobContractNote.noteSummary" />
<form:errors path="jobContractNote.noteSummary" cssClass="redstar"/>
</td>
</tr>

<tr>
<td><f:message key="details"/>:</td>
<td colspan="3"><form:textarea path="jobContractNote.note"  rows="4" cols="40" id="notedetails" tabindex="49"/>
<form:errors path="jobContractNote.note"  cssClass="redstar"/>
</td>
</tr>

<tr>
<td><f:message key="noteType"/>:</td>
<td><form:select cssClass="selectionBox" id="notetypes" path="jobContractNote.noteType" items="${icbfn:dropdown('staticDropdown',noteType)}" itemLabel="name" itemValue="value"/></td>

<td><f:message key="visibility"/>:<span class="redstar">*</span></td>
<td><form:select  cssClass="selectionBox" id="notevisibility" path="jobContractNote.noteVisibility"  items="${icbfn:dropdown('staticDropdown',visibility)}" itemLabel="name" itemValue="value"/></td>
</tr>

<tr>
<td><f:message key="origin"/>:</td>
<td><f:message key="internal"/></td>
<td></td><td></td>
</tr>

</table>
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<input type="button" value="Save Note" class="button1" onclick="beforesubmit();"/>
<input type="button"  value="Reset"  class="button1"  onclick="onReset();"/>
<input name="attachfile" type="button" class="button1" id="attachfile" accesskey="F" tabindex="53" title="Add Attachment" value="Attach a File" onClick="window.open('create_job_add_customer_attachfile_popup.htm?jobContractId=${command.jobContract.id}&divName=attach', null, 'scrollbars=yes, width=500, height=200');" >
</td>
</tr>
</table>
</td>
</tr>
</table>
<c:if test="${not empty command.jobContractNoteList}">
<br>
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr><td title="click for details"><b><f:message key="savedNotes"/>:</b></td></tr>
<c:forEach items="${command.jobContractNoteList}" var="note" varStatus="notecounter">
	<tr><td><a href="#" onclick="onSelectNote('${note.id}');" >${note.noteSummary}</a></td>
	<td><a href="#"><img src="images/icodel.gif" alt="Delete Note" width="12" height="14" border="0" onclick="if(confirmDelete()){ onDelete('${note.id}'); }"></a></td>
	</tr>
</c:forEach>
</table>
</c:if>
<div class="sample_popup"     id="attach" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="attach_drag${counter.index}" style="width:650px; "> 
<img class="menu_form_exit"   id="attach_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Attach File(s) </div>
<div class="menu_form_body" style="width:650px; height:200px;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr>
<iframe align="left" frameborder="0" style="padding:0px;" height="200px;" width="100%" src="create_job_add_customer_attachfile_popup.htm?jobContractId=${command.jobContract.id}&divName=attach" scrolling="auto" id="jobContractAttachFileFr" name="jobContractAttachFileFr" allowtransparency="yes" ></iframe>
</tr>
</table>
</div>
</div>

</form:form>
