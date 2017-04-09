<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
  function onSubmit(operation)
  {
    document.editContractUpdateUomNotesPopUpForm.operation.value = operation;
    document.editContractUpdateUomNotesPopUpForm.submit();
  }
  
  
  function doDeleteUomNote(myUomNoteIndex)
  {
    document.editContractUpdateUomNotesPopUpForm.operation.value = "deleteUomNote";
    document.editContractUpdateUomNotesPopUpForm.uomNoteIndex.value = myUomNoteIndex;
    document.editContractUpdateUomNotesPopUpForm.submit();
  }

  function doChangeUom(myUomNoteIndex)
  {
    document.editContractUpdateUomNotesPopUpForm.operation.value = "changeUom";
    document.editContractUpdateUomNotesPopUpForm.uomNoteIndex.value = myUomNoteIndex;
    document.editContractUpdateUomNotesPopUpForm.submit();
  }
</script>

<icb:list var="uomList" items="${icbfn:dropdown('uomTableDropDown',null)}" />

<form:form name="editContractUpdateUomNotesPopUpForm" method="POST" action="edit_contract_update_uom_notes_popup.htm"> 
  <input type="hidden" name="operation" value="" />
  <input type="hidden" name="uomNoteIndex" value="" />

  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>
  
  <div>
    Contract Id: ${command.editInspectionRate.contract.contractCode}
  </div>

  <table width="96%" border="0" cellpadding="0" cellspacing="0" style="width:99%;">
    <tr>
      <td valign="top">      
        <table width="100%" cellpadding=0 cellspacing=0 class="InnerApplTable">
          <tbody>
            <tr bgcolor=#ffffff>
              <th width="20%">UOM </th>
              <th width="20%">Begin Date</th>
              <th width="20%">End Date</th>
              <th width="30%">Description</th>
              <th width="5%">
                <a href="#" onclick="onSubmit('addUomNote');">
                  <img src="images//add.gif" alt="Add Uom Note" width="13" height="12" hspace="1px" border="0"/>
                </a>
              </th>
            </tr>
            <c:forEach items="${command.uomNoteList}" var="uomNote" varStatus="uomNoteStatus">
              <tr>
                <td class="TDShadeBlue">
                  <form:select id="sel1" onchange="doChangeUom('${uomNoteStatus.index}');" cssClass="selectionBox" path="uomNoteList[${uomNoteStatus.index}].uom" items="${uomList}" itemLabel="name" itemValue="value" />
                </td>
                <td class="TDShadeBlue">
                  <form:input path="uomNoteList[${uomNoteStatus.index}].rbExt.notesRb.rbId.beginDate" cssClass="inputBox" size="10"/>
                  <a href="#" onClick="displayCalendar(document.editContractUpdateUomNotesPopUpForm['uomNoteList[${uomNoteStatus.index}].rbExt.notesRb.rbId.beginDate'],'${icbfn:userDateFormat()}',this)">
                    <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                  </a>
                  <form:errors path="uomNoteList[${uomNoteStatus.index}].rbExt.notesRb.rbId.beginDate" cssClass="redstar"/>                                    
                </td>
                <td class="TDShadeBlue">
                  <form:input path="uomNoteList[${uomNoteStatus.index}].rbExt.notesRb.rbId.endDate" cssClass="inputBox" size="10"/>
                  <a href="#" onClick="displayCalendar(document.editContractUpdateUomNotesPopUpForm['uomNoteList[${uomNoteStatus.index}].rbExt.notesRb.rbId.endDate'],'${icbfn:userDateFormat()}',this)">
                    <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                  </a>
                  <form:errors path="uomNoteList[${uomNoteStatus.index}].rbExt.notesRb.rbId.endDate" cssClass="redstar"/>                                    
                </td>
                <td class="TDShadeBlue">
                  <form:textarea path="uomNoteList[${uomNoteStatus.index}].rbExt.notesRb.rbValue" cols="80" rows="4" />
                  <form:errors path="uomNoteList[${uomNoteStatus.index}].rbExt.notesRb.rbValue" cssClass="redstar"/>                                    
                </td>
                <td class="TDShadeBlue">
                  <a href="#" onclick="doDeleteUomNote('${uomNoteStatus.index}');">
                    <img src="images//delete.gif" alt="Delete Uom Note" width="13" height="12" hspace="1px" border="0"/>
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </td>
    </tr>
    <tr>
      <td>
        <input name="Submit1" type="button" class="button1" onclick="javascript:onSubmit('submit');" value="OK" />      
        <input name="Submit3" type="button" class="button1" onclick="javascript:onSubmit('return');" value="Cancel" />      
        <input name="Submit2" type="button" class="button1" onclick="javascript:onSubmit('apply');" value="Apply" />      
      </td>
    </tr>
  </table>
</form:form>
