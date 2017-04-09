<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<head>
<script language="javascript">
function selectNote(index){
	document.viewCustomerNotesForm.addOrDeleteCustNote.value = "view";
    document.viewCustomerNotesForm.custNoteCount.value = index;
    document.viewCustomerNotesForm.submit();
}
// For iTrack#135193-Start  
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
if(document.viewCustomerNotesForm.noteSummary.value.length==0){
	confirm("Please fill Summary ");
	document.viewCustomerNotesForm.noteSummary.focus();
}else{
	document.viewCustomerNotesForm.addOrDeleteCustNote.value = "add";
	document.viewCustomerNotesForm.submit();
}
}
 function onReset()
 {
 		document.viewCustomerNotesForm.addOrDeleteCustNote.value = "reset";
	    document.viewCustomerNotesForm.submit();
 }
 function onCustNoteDelete(index){
  	document.viewCustomerNotesForm.addOrDeleteCustNote.value = "delete";
    document.viewCustomerNotesForm.custNoteCount.value = index;
    document.viewCustomerNotesForm.submit();
 }
 //For iTrack#135193-End 
</script>
</head>

 <form:form name="viewCustomerNotesForm" method="POST" action="view_customer_notes_popup.htm">
 <form:hidden path="custNoteCount"/> 
 <form:hidden path="addOrDeleteCustNote"/>
 <div id="tab6" class="innercontent" >
    <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
    <tr>
	<td colspan="4" style="padding:2px;">
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
	<%--For iTrack#135193 --%>
	 <c:choose>
	 <c:when test="${command.notesViewOnly != null && command.notesViewOnly =='false'}">
		<tr>
		<td class="TDShadeBlue" colspan="4" align="center">
		<input type="button" value="Save Note" class="button1" onclick="beforesubmit();"/>
		<input type="button"  value="Reset"  class="button1"  onclick="onReset();"/>
		</td>
		</tr>
	  </c:when>
	  <c:otherwise>
	  </c:otherwise>
	  </c:choose>
	<%--For iTrack#135193 --%>
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
	<td><a href="#start" onclick="selectNote('${counter.index}');" >${customerNotesList.noteSummary}</a></td> 
	<td>${customerNotesList.addedByUserName}</td>
	<td>${customerNotesList.dateTimeAdded}</td>
	<%--For iTrack#135193 --%>
	 <c:choose>
	 <c:when test="${command.notesViewOnly != null && command.notesViewOnly =='false'}">
		<td><a href="#"><img src="images/icodel.gif" alt="Delete Note" width="12" height="14" border="0" onclick="if(confirmDelete('${command.notesViewOnly}')){ onCustNoteDelete('${counter.index}'); }" ></a></td>
	  </c:when>
	  <c:otherwise>
	  <td><img src="images/icodel.gif" alt="Delete Note" width="12" height="14" border="0"></td> 
	  </c:otherwise>
	  </c:choose>
	<%--For iTrack#135193 --%>
	</tr>
	</c:forEach>
	</table>
</td>
</tr>
<tr>	
<td class="TDShadeBlue">			
<input type="button" value="Close" name="Close" class="button1" onClick="javascript:top.hidePopupDiv('${command.divName}','${command.divBody}');top.popupboxclose();" />
</td>
<td  class="TDShadeBlue">&nbsp;</td>					
<td class="TDShadeBlue">&nbsp;</td>
<td colspan="2" class="TDShadeBlue">&nbsp;</td>
</tr>		
</table>
</form:form>

