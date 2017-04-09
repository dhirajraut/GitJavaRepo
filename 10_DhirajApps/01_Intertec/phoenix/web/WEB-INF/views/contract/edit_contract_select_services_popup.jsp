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
    document.editContractSelectServicesPopUpForm.operation.value = operation;
    document.editContractSelectServicesPopUpForm.submit();
  }  

  function onSearch(pageNumber)
  {
    document.editContractSelectServicesPopUpForm.operation.value = 'search';
    document.editContractSelectServicesPopUpForm.pageNumber.value = pageNumber;
    document.editContractSelectServicesPopUpForm.submit();
  }  

  function doSort(sortFlag)
  {
    document.editContractSelectServicesPopUpForm.operation.value = 'search';
    document.editContractSelectServicesPopUpForm.sortFlag.value = sortFlag;
    document.editContractSelectServicesPopUpForm.submit();
  }  

  function selectAll(myPrefix, selectAllElement)
  {
    var elementList = document.editContractSelectServicesPopUpForm.elements;
    for(i=0;i<elementList.length;i++)
    {
      if(elementList[i].type == "checkbox")
      {
        var checkBoxName = elementList[i].name;
        var myPosition = checkBoxName.indexOf(myPrefix);
        if(myPosition >= 0)
        {
          if(!elementList[i].disabled)
          {
            if(selectAllElement.checked) elementList[i].checked=true;
            else elementList[i].checked=false;
          }
        }
      }
    }
  }
</script>

<form:form name="editContractSelectServicesPopUpForm" method="POST" action="edit_contract_select_services_popup.htm"> 
  <input type="hidden" name="operation" value="" />
  <form:hidden path="sortFlag" />
  <form:hidden path="pageNumber" />

  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table border="0" cellpadding="0" cellspacing="0" class="MainTable" style="padding:-left:10px;padding-top:0px;">
    <tr>
      <td valign="top">
        <div id="tab1" class="innercontent" >
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:right:5px;">
            <tr>
              <th colspan="3"><f:message key="SelectServices"/></th>
            </tr>
            <tr>
              <td class="TDShade" nowrap><f:message key="Service"/>:</td>
              <td class="TDShadeBlue">
                <form:input cssClass="inputBox" path="serviceName" />
              </td>
              <td class="TDShadeBlue">
                <form:checkbox path="includeMasterListServices" /><f:message key="IncludeMasterListServices"/>
              </td>
            </tr>
          </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
            <tr>
              <td>
                <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td><input name="button" type="button" onClick="doSubmit('search')" class="button1" value="<f:message key="search"/>"  /></td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
          <TABLE width="100%" BORDER="0" valign="top">
            <TR>
              <td width="47%" valign="top">    
                <table width="100%" align="center" cellpadding="0" cellspacing="0" style="color:#000000;border:#86A3DB 1px solid;background-color:#fcffff;">
                  <tr>
                    <th>
                      <f:message key="Service List"/>: 
                      <c:if test="${command.allHighLevelServicePagination != null}">                
                        <c:if test="${fn:length(command.allHighLevelServicePagination.pages) > 0}">
                          <c:forEach items="${command.allHighLevelServicePagination.pages}" var="myPage">  
                            <c:if test="${myPage.selected == false}">
                              <a href="#" onclick="javascript:onSearch('${myPage.pageNumber}');">
                                ${myPage.name}
                              </a>&nbsp;
                            </c:if>
                            <c:if test="${myPage.selected == true}">
                              ${myPage.name}&nbsp;
                            </c:if>
                          </c:forEach>
                        <br><br>
                        </c:if>
                      </c:if>
                    </th>
                  </tr>
                  <TR>
                    <TD valign="top">
                      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                        <tr>
                          <th width="5">
                            <form:checkbox path="checkAll" onclick="javascript:selectAll('allHighLevelServiceExtList', this)"/>
                          </th>
                          <th><a href="#start" onClick="doSort('serviceDescription');" ><span class="TDShade" nowrap><f:message key="Service"/></span></a></th>
                          <th><a href="#start" onClick="sortBranchByBranchCode('priceBookInd');" ><span class="TDShade" nowrap><f:message key="PB"/></span></a></th>
                        </tr>
                        <c:forEach items="${command.allHighLevelServiceExtList}" var="highLevelServiceExt" varStatus="status">
                          <tr>         
                            <td>
                              <form:checkbox path="allHighLevelServiceExtList[${status.index}].checked" disabled="${highLevelServiceExt.viewOnly}"/>                  
                            </td>
                            <td>${highLevelServiceExt.highLevelService.serviceDescription}</td>
                            <td>
                              <c:choose>
                                <c:when test="${highLevelServiceExt.highLevelService.priceBookInd}">Y</c:when>
                                <c:otherwise>N</c:otherwise>
                              </c:choose>
                            </td>
                          </tr>
                        </c:forEach>
                      </table>
                    </TD>
                  </tr>
                </table>
              </td>
              <td width="6%" valign="top">
                <TABLE BORDER=0>
                  <TR>
                    <TD>
                      <INPUT TYPE="button" NAME="right" VALUE="&gt;&gt;" onClick="javascript:doSubmit('select');"><BR><BR>
                      <INPUT TYPE="button" NAME="left" VALUE="&lt;&lt;" onClick="javascript:doSubmit('deselect');"><BR><BR>
                    </TD>
                  </tr>
                </table>
              </td>
              <td width="47%" valign="top">
                <table width="100%" cellpadding="0" cellspacing="0" style="color:#000000;border:#86A3DB 1px solid;background-color:#fcffff;valign:top;">
                  <tr>
                    <th><f:message key="Selected Services"/></th>
                  </tr>
                  <TR>
                    <TD valign="top">
                      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                        <tr>
                          <th width="5">
                            <form:checkbox path="selectedCheckAll" onclick="javascript:selectAll('selectedHighLevelServiceExtList', this)"/>
                          </th>
                          <th><span class="TDShade" nowrap><f:message key="Service"/></span></th>
                        </tr>
                        <c:forEach items="${command.selectedHighLevelServiceExtList}" var="highLevelServiceExt" varStatus="status">
                          <tr>         
                            <td>
                              <form:checkbox path="selectedHighLevelServiceExtList[${status.index}].checked" disabled="${highLevelServiceExt.viewOnly}"/>                  
                            </td>
                            <td>${highLevelServiceExt.highLevelService.serviceDescription}</td>
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

  <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    <tr>
      <td>
        <input name="Submit2" type="button" class="button1" value="Ok" onClick="javascript:doSubmit('submitAndReturn');"> &nbsp;&nbsp;
        <input name="Submit3" type="button" class="button1" value="Cancel" onClick="javascript:doSubmit('return');">
      </td>
    </tr>
  </table>

</form:form>
