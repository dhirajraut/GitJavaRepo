<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
  function doSubmit(operation)
  {
    document.editQuestionNotesPopupForm.operation.value = operation;
    document.editQuestionNotesPopupForm.submit();
  }  
</script>

<form:form name="editQuestionNotesPopupForm" method="POST" action="edit_question_notes_popup.htm"> 
  <input type="hidden" name="operation" value="" />

  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>
  
  <div>
    Price Book Text:
  </div>

  <table width="96%" border="0" cellpadding="0" cellspacing="0" style="width:99%;">
    <tr>
      <td valign="top">      
        <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
          <tbody>
            <tr bgcolor=#ffffff>
              <th colspan=3 width="65%">Custom Text for Question: </th>
              <th width="35%" >&nbsp;</th>
              <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;&nbsp;</th>
            </tr>

            <tr>
              <td colspan="5" class="TDShade" style="padding:2px;">
                <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                  <tr>
                    <td class="TDShadeBlue">Begin Date:</td>
                    <td class="TDShadeBlue">
                      <form:input path="rbExt.rb.rbId.beginDate" cssClass="inputBox" size="10"/>
                      <a href="#" onClick="displayCalendar(document.editQuestionNotesPopupForm['rbExt.rb.rbId.beginDate'],'${icbfn:userDateFormat()}',this)">
                        <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                      </a>
                    </td>
                    <td class="TDShadeBlue">EndDate: </td>
                    <td class="TDShadeBlue">
                      <form:input path="rbExt.rb.rbId.endDate" cssClass="inputBox" size="10"/>
                      <a href="#" onClick="displayCalendar(document.editQuestionNotesPopupForm['rbExt.rb.rbId.endDate'],'${icbfn:userDateFormat()}',this)">
                        <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                      </a>
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShadeBlue">Custom Text:</td>
                    <td class="TDShadeBlue" colspan="3">
                      <form:textarea path="rbExt.rb.rbValue" cols="80" rows="4" />
                    </td>
                  </tr>
                </table>          
              </td>
            </tr>
          </tbody>
        </table>

        <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
          <tbody>
            <tr bgcolor=#ffffff>
              <th colspan=3 width="65%">Contract Specific Note: </th>
              <th width="35%" >&nbsp;</th>
              <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;&nbsp;</th>
            </tr>

            <tr>
              <td colspan="5" class="TDShade" style="padding:2px;">
                <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                  <tr>
                    <td class="TDShadeBlue">Begin Date:</td>
                    <td class="TDShadeBlue">
                      <form:input path="rbExt.notesRb.rbId.beginDate" cssClass="inputBox" size="10"/>
                      <a href="#" onClick="displayCalendar(document.editQuestionNotesPopupForm['rbExt.notesRb.rbId.beginDate'],'${icbfn:userDateFormat()}',this)">
                        <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                      </a>
                    </td>
                    <td class="TDShadeBlue">EndDate: </td>
                    <td class="TDShadeBlue">
                      <form:input path="rbExt.notesRb.rbId.endDate" cssClass="inputBox" size="10"/>
                      <a href="#" onClick="displayCalendar(document.editQuestionNotesPopupForm['rbExt.notesRb.rbId.endDate'],'${icbfn:userDateFormat()}',this)">
                        <img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                      </a>
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShadeBlue">Custom Text:</td>
                    <td class="TDShadeBlue" colspan="3">
                      <form:textarea path="rbExt.notesRb.rbValue" cols="80" rows="4" />
                    </td>
                  </tr>
                </table>          
              </td>
            </tr>
          </tbody>
        </table>
      </td>
    </tr>
    <tr>
      <td>
        <input name="Submit2" type="button" class="button1" onclick="javascript:doSubmit('submitAndReturn');" value="OK" />      
      </td>
    </tr>
  </table>

</form:form>
