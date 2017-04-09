<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
  <%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
  <script	type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript"	src="js/ce/common.js"></script>

<form:form name="NotePopUpForm" method="POST" action="job_instruction_note_popup.htm">

<form:hidden path="deleteNoteId" />
<form:hidden path="selectedNoteId" />
<form:hidden path="orderId"/>
<input type="hidden" name="reset"/>
 	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>
	  
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<td><f:message key="addedBy"/>:</td>
<td id="addedby"><c:out value="${command.note.addedByUserName}"/></td>
<td><f:message key="dateTimeAdded"/>:</td>
<td id="datetimeadded" class="TDShadeGrey"><f:formatDate value="${command.note.dateTimeAdded}" type="date" dateStyle="short" /></td>
</tr>
<tr>
<td><f:message key="summary"/>:<span class="redstar">*</span></td>
<td colspan="3"><form:input cssClass="inputBox" id="notesummary" size="35"  maxlength="100" tabindex="47" path="note.noteSummary" />
<form:errors path="note.noteSummary" cssClass="redstar"/>
</td>
</tr>

<tr>
<td><f:message key="details"/>:</td>
<td colspan="3"><form:textarea path="note.note"  rows="4" cols="40" id="notedetails" tabindex="49"/>
<form:errors path="note.note"  cssClass="redstar"/>
</td>
</tr>


</table>
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<input type="button" value="Save Note" class="button1" onclick="beforesubmit();"/>
<input type="button"  value="Reset"  class="button1"  onclick="onReset();"/>
<input name="attachfile" type="button" class="button1" id="attachfile" accesskey="F" tabindex="53" title="Add Attachment" value="Attach a File" onClick="window.open('create_job_add_prebill_attachfile_popup.htm?prebbillId=${command.prebill.id}&divName=attach', null, 'scrollbars=yes, width=700, height=200');" >
</td>
</tr>
</table>
</td>
</tr>
</table>
<c:if test="${not empty command.noteList}">
<br>
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr><td title="click for details"><b><f:message key="savedNotes"/>:</b></td></tr>
<c:forEach items="${command.noteList}" var="note" varStatus="notecounter">
	<tr><td><a href="#" onclick="onSelectNote('${note.id}');" >${note.noteSummary}</a></td>
	<td><a href="#"><img src="images/icodel.gif" alt="Delete Note" width="12" height="14" border="0" onclick="if(confirmDelete()){ onDelete('${note.id}'); }"></a></td>
	</tr>
</c:forEach>
</table>
</c:if>
</form:form>
