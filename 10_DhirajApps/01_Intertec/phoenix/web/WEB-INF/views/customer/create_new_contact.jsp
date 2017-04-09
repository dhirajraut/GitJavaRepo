<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<head>
<script language="javascript">
	 

</script>
</head>

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


<form:form name="newContactCreateForm" method="POST" action="create_new_contact.htm">
 <form:hidden path="searchForm" /> 
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>

              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=3 width="70%"><f:message key="contact"/></th>
                    <th width="25%"><f:message key="status"/>:
                      <form:select cssClass="selectionBox" path="contact.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>
                    </th>
                    <th width="5%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="firstName"/> <strong> <span class="redstar">*</span>:</strong></td>
                    <td class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="128" path="contact.firstName" />
                      <form:errors path="contact.firstName" cssClass="redstar"/>
                    </td>
                    <td class="TDShade"><f:message key="lastName"/> <strong> <span class="redstar">*</span>:</strong> </td>
                    <td colspan="2" class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="128" path="contact.lastName" />
                      <form:errors path="contact.lastName" cssClass="redstar"/>
                    </td>
                  </tr>
                    <tr>
                    <td class="TDShade"><f:message key="title"/>: </td>
                    <td class="TDShadeBlue"><form:input maxlength="128" cssClass="inputBox" size="35" path="contact.title"/></td>
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
                    
                    <!-- added for the issue-104407 -->
                    <td class="TDShade"><f:message key="fax"/>: </td>
                    <td colspan="2" class="TDShadeBlue">
                      <form:input cssClass="inputBox" maxlength="30" size="35" path="contact.fax" />
                      <form:errors path="contact.fax" cssClass="redstar"/>
                    </td>
                    <!--  END  104407-->
                    
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
		
                  <tr>
		<td class="TDShade"><input type="submit"  class="button1" value="Save"/>
	   <a href="#"><input name="Submit3" type="button" class="button1" value="Cancel" onclick="javascript:top.hidePopupDiv('newcontact${command.rowNum}','newcontactbody${command.rowNum}');top.popupboxclose();"/></a></td>
      <td class="TDShade">&nbsp;</td>
      <td colspan="3" class="TDShade">&nbsp;</td>
    </tr>
  </tbody>
</table>
 
</form:form>

