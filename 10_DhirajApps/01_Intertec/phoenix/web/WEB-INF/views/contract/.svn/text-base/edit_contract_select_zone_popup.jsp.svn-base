<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
  function doSubmit(operation)
  {
    document.editContractSelectZonePopUpForm.operation.value = operation;
    document.editContractSelectZonePopUpForm.submit();
  }  
</script>

<form:form name="editContractSelectZonePopUpForm" method="POST" action="edit_contract_select_zone_popup.htm"> 
  <input type="hidden" name="operation" value="" />

  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <c:set var="userDateFormat" value="${icbfn:userDateFormat()}" />

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    <tr>
      <td>
        <input name="Submit2" type="button" class="button1" value="Ok" onClick="javascript:doSubmit('submitAndReturn');"> &nbsp;&nbsp;
        <input name="Submit3" type="button" class="button1" value="Cancel" onClick="javascript:doSubmit('return');">
      </td>
    </tr>
  </table>

  <table border="0" cellpadding="0" cellspacing="0" class="MainTable" style="padding:-left:10px;padding-top:0px;">
    <tr>
      <td valign="top">
        <div id="tab1" class="innercontent" >
          <TABLE width="100%" BORDER="0" valign="top">
            <TR>
              <td width="47%" valign="top">    
                <table width="100%" align="center" cellpadding="0" cellspacing="0" style="color:#000000;border:#86A3DB 1px solid;background-color:#fcffff;">
                  <TR>
                    <TD valign="top">
                      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                        <tr>
                          <th width="5">&nbsp;</th>
                          <th><span class="TDShade" nowrap><f:message key="Pricebook"/></span></th>
                          <th><span class="TDShade" nowrap><f:message key="ZoneId"/></span></th>
                          <th><span class="TDShade" nowrap><f:message key="PortCode"/></span></th>
                          <th><span class="TDShade" nowrap><f:message key="BeginDate"/></span></th>
                          <th><span class="TDShade" nowrap><f:message key="EndDate"/></span></th>
                        </tr>
                        <c:forEach items="${command.portLocationList}" var="portLocation" varStatus="status">
                          <tr>         
                            <td>
                              <form:radiobutton path="selectedZoneId" value="${portLocation.portLocationId.location}" />
                            </td>
                            <td>${portLocation.portLocationId.contractId}</td>
                            <td>${portLocation.portLocationId.location}</td>
                            <td>${portLocation.portLocationId.portCode}</td>
                            <td>                              
                              <f:formatDate pattern="${userDateFormat}" value="${portLocation.portLocationId.beginDate}" />                              
                            </td>
                            <td>                              
                              <f:formatDate pattern="${userDateFormat}" value="${portLocation.endDate}" />                              
                            </td>
                          </tr>
                        </c:forEach>
                      </table>
                    </TD>
                  </tr>
                </table>
              </td>
            </TR>
          </TABLE>
        </div>
      </td>
    </tr>
  </table>

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    <tr>
      <td>
        <input name="Submit2" type="button" class="button1" value="Ok" onClick="javascript:doSubmit('submitAndReturn');"> &nbsp;&nbsp;
        <input name="Submit3" type="button" class="button1" value="Cancel" onClick="javascript:doSubmit('return');">
      </td>
    </tr>
  </table>

</form:form>
