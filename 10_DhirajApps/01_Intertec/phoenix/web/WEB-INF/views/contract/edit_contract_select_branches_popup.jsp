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
    document.editContractSelectBranchesPopUpForm.operation.value = operation;
    document.editContractSelectBranchesPopUpForm.submit();
  }  

  function onSearch(pageNumber)
  {
    document.editContractSelectBranchesPopUpForm.operation.value = 'search';
    document.editContractSelectBranchesPopUpForm.pageNumber.value = pageNumber;
    document.editContractSelectBranchesPopUpForm.submit();
  }  

  function doSort(sortFlag)
  {
    document.editContractSelectBranchesPopUpForm.operation.value = 'search';
    document.editContractSelectBranchesPopUpForm.sortFlag.value = sortFlag;
    document.editContractSelectBranchesPopUpForm.submit();
  }  

  function selectAll(myPrefix, selectAllElement)
  {
    var elementList = document.forms[0].elements;
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

<form:form name="editContractSelectBranchesPopUpForm" method="POST" action="edit_contract_select_branches_popup.htm"> 
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
              <th colspan="2"><f:message key="branchSearch"/></th>
            </tr>
            <tr>
              <td width="20%" class="TDShade" nowrap><f:message key="businessUnitName"/>: </td>
              <td width="80%" class="TDShadeBlue">
                <form:select cssClass="selectionBox" id="buname" path="branchSearch.businessUnit.value" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value"/>       
              </td>
            </tr>
            <tr>
              <td class="TDShade" nowrap><f:message key="branchCode"/>:</td>
              <td class="TDShadeBlue">
                <form:input cssClass="inputBox" path="branchSearch.name.value" />
              </td>
            </tr>
            <tr>
              <td class="TDShade" nowrap><f:message key="description"/> : </td>
              <td class="TDShadeBlue"><form:input cssClass="inputBox" path="branchSearch.desc.value" /></td>            
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
                      Branch List: 
                      <c:if test="${command.allBranchPagination != null}">                
                        <c:if test="${fn:length(command.allBranchPagination.pages) > 0}">
                          <c:forEach items="${command.allBranchPagination.pages}" var="myPage">  
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
                            <form:checkbox path="checkAll" onclick="javascript:selectAll('allBranchExtList', this)"/>
                          </th>
                          <th><a href="#start" onClick="doSort('businessUnit.name');" ><span class="TDShade" nowrap><f:message key="businessUnitName"/></span></a></th>
                          <th><a href="#start" onClick="sortBranchByBranchCode('name');" ><span class="TDShade" nowrap><f:message key="branchCode"/></span></a></th>
                          <th><a href="#start" onClick="sortBranchByDescription('description');" ><span class="TDShade" nowrap><f:message key="description"/></span></a></th>
                        </tr>
                        <c:forEach items="${command.allBranchExtList}" var="branchExt" varStatus="status">
                          <tr>         
                            <td>
                              <form:checkbox path="allBranchExtList[${status.index}].checked" />                  
                            </td>
                            <td>${branchExt.branch.businessUnit.name}</td>
                            <td>
                              ${branchExt.branch.name}</a>
                            </td>
                            <td>${branchExt.branch.description}</td>
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
                    <th>Selected Branches</th>
                  </tr>
                  <TR>
                    <TD valign="top">
                      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                        <tr>
                          <th width="5">
                            <form:checkbox path="selectedCheckAll" onclick="javascript:selectAll('selectedBranchExtList', this)"/>
                          </th>
                          <th><span class="TDShade" nowrap><f:message key="businessUnitName"/></span></th>
                          <th><span class="TDShade" nowrap><f:message key="branchCode"/></span></th>
                          <th><span class="TDShade" nowrap><f:message key="description"/></span></th>
                        </tr>
                        <c:forEach items="${command.selectedBranchExtList}" var="branchExt" varStatus="status">
                          <tr>         
                            <td>
                              <form:checkbox path="selectedBranchExtList[${status.index}].checked" disabled="${branchExt.viewOnly}"/>                  
                            </td>
                            <td>${branchExt.branch.businessUnit.name}</td>
                            <td>
                              ${branchExt.branch.name}</a>
                            </td>
                            <td>${branchExt.branch.description}</td>
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
