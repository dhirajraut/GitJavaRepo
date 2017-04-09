<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

 <icb:list var="contractAttachType">
  <icb:item>contractAttachType</icb:item>
</icb:list>
<icb:list var="audienceType">
  <icb:item>audienceType</icb:item>
</icb:list>
<form:form name="viewContractAttachForm" method="POST" action="view_contract_attach_popup.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="tabName" />
<form:hidden path="contractAttachIndex"/>

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
<!-------------------------------------------------------------------------------- MAIN CONTAINER -------------------------------------------------------------------------->
      <div id="MainContentContainer">
<!---------------------------------------------------------------------------TABS CONTENTS---------------------------------------------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav">
            
          </div>
<!----------------------------------------------------------------------------Sub Menus container. Do not remove ----------------------------------------------------------->
          <div id="tab_inner">
<!-------------------------------------------------------------------- TAB 3 CONTAINER ------------------------------------------------------------------------------------->
<div id="tab3" class="innercontent" >
<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tr>
    <th width="60%">
     <f:message key="contractID"/>: ${command.contract.contractCode} 
      <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> 
      <f:message key="description"/>: ${command.contract.description} 
    </th>
    <th width="25%">
      <span style="text-align:right">
        Status:Active |
        &nbsp;<script type="text/javascript">
        var currentTime = new Date();
        var month = currentTime.getMonth() + 1;
        var day = currentTime.getDate();
        var year = currentTime.getFullYear();
        document.write(month + "/" + day + "/" + year);
        </script>
      </span>
    </th>
    <th width="15%" style="text-align:right">&nbsp;
    <%--<a href="search_contract.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>  <a href="#" onClick="resetform();"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --%>  
    </th>
  </tr>
  <tr>
    <td colspan="3" style="padding:2px;">
      <table cellpadding="0" cellspacing="0" width="100%" class="InnerApplTable">
        <tr>
          <th><f:message key="attachmentDetails"/></th>
        </tr>
        <tr>
          <td style="padding-bottom:6px;padding-left:1px;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-bottom:0px;" >
              <tr valign="center">
                <th width="5%" scope="col">&nbsp;</th>
                <th width="20%" align="left" scope="col"><f:message key="fileName"/></th>
                <th width="6%" scope="col" align="left"><f:message key="type"/></th>
                <th width="15%" scope="col" align="left"><f:message key="description"/></th>
                <th width="5%"  align="left" scope="col"><f:message key="required"/></th>
                <th width="8%" align="left" scope="col"><f:message key="audience"/></th>
                <th width="10%" align="left" scope="col"><f:message key="addedBy"/></th>
                <th width="10%" align="left" scope="col"><f:message key="date/TimeAdded"/></th>
              </tr>
              <c:forEach items="${command.contractAttaches}" var="contractAttaches" varStatus="counter">
                <tr valign="center">
                  <td align="center">${counter.index + 1}</td>
                  <td align="left" >
                  <%-- <a href="contract_file_view.htm?fileName=${command.contractAttaches[counter.index].systemFileName}&specialChar=${command.specialChars[counter.index]}&specialCodes=${command.specialCodes[counter.index]}" target="_blank">${command.contractAttaches[counter.index].fileName}</a> --%>

				   <a href="contract_file_view.htm?fileName=${command.contractAttaches[counter.index].systemFileName}" target="_blank">${command.contractAttaches[counter.index].fileName}</a>
                   </td>
                  <td align="left">
                    <form:select cssClass="selectionBox" path="contractAttaches[${counter.index}].attachType" items="${icbfn:dropdown('staticDropdown',contractAttachType)}" itemLabel="name" itemValue="value" disabled="true"/>   
                    <form:errors path="contractAttaches[${counter.index}].attachType" cssClass="redstar"/>
                  </td>
                  <td align="left">
                    <form:input cssClass="inputBox" size="35" path="contractAttaches[${counter.index}].attachDescr" disabled="true"/>
                    <form:errors path="contractAttaches[${counter.index}].attachDescr" cssClass="redstar"/> 
                  </td>
                  <td align="left">
                    <input type="checkbox" name="requiredFlag" disabled="true"/>
                    <%--    <form:checkbox path="contractAttaches[${counter.index}].attachDescr" value="0" />
                    <form:errors path="contractAttaches[${counter.index}].attachDescr" cssClass="redstar"/>--%>
                  </td>
                  <td align="center"> 
                    <form:select cssClass="selectionBox" path="contractAttaches[${counter.index}].audience" items="${icbfn:dropdown('staticDropdown',audienceType)}" itemLabel="name" itemValue="value" disabled="true"/> 
                    <form:errors path="contractAttaches[${counter.index}].audience" cssClass="redstar"/>  
                  </td>
                  <td align="left">
                    ${command.contractAttaches[counter.index].addedByUserName}
                  </td>
                  <td align="center">
                    ${command.contractAttaches[counter.index].dateTimeAdded}
                  </td>
                  <%--<td style="text-align:center"><a href="#"><img src="images/icodel.gif" alt="Delete Attachment" width="12" height="14" border="0" onclick="onAttachDelete('${counter.index}')"></a></td>--%>
                </tr>
              </c:forEach>
            </table>
          </td>
        </tr>
      </table>  
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>
            <input name="Submit2" type="button" class="button1" value="Attach a File" onClick="popup_show('attachpop', 'attachpop_drag', 'attachpop_exit', 'screen-corner', 40, 80); hideIt();" disabled="true"/>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>      


      </div>
<!------------------------------------------------------------------------ TAB 3 CONTAINER END ----------------------------------------------------------------------------->
</div>
</div>
<script type="text/javascript">
var pageNoVar = "${command.tabName}"
//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
dolphintabs.init("tabnav", pageNoVar)      
</script>
<!------------------------------------------------------------------------- TAB CONTENT END -------------------------------------------------------------------------------->
</div>
<!------------------------------------------------------------------------------ MAIN CONTAINER END ------------------------------------------------------------------------>
</td>
</tr>
</table>
</form:form>

