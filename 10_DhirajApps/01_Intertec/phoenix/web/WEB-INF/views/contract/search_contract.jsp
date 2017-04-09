<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

  <script language="javascript">
     function submitSearch(pageNumber)
        {  
        document.contractSearchForm.pageNumber.value = pageNumber;
document.contractSearchForm.cxcel.value="false";
document.contractSearchForm.submitFlag.value="true";
        document.contractSearchForm.submit();
        } 
        function sortContractByCode(){
document.contractSearchForm.pageNumber.value = "1";
document.contractSearchForm.sortFlag.value = "contractCode";
document.contractSearchForm.cxcel.value="false";
document.contractSearchForm.submit();
}
function sortContractByStatus(){
document.contractSearchForm.pageNumber.value = "1";
document.contractSearchForm.sortFlag.value = "status";
document.contractSearchForm.cxcel.value="false";
document.contractSearchForm.submit();
}
function sortContractByDescription(){
document.contractSearchForm.pageNumber.value = "1";
document.contractSearchForm.sortFlag.value = "description";
document.contractSearchForm.cxcel.value="false";
document.contractSearchForm.submit();
}
function submitFunction(){
document.contractSearchForm.pageNumber.value = "1";
document.contractSearchForm.sortFlag.value = "";
document.contractSearchForm.cxcel.value="false";
document.contractSearchForm.submitFlag.value="true";
document.contractSearchForm.submit();
}

function submitxcel(){
document.contractSearchForm.cxcel.value="true";
document.contractSearchForm.sortFlag.value = "";
top.document.contractSearchForm.submit();
}

   </script>

<icb:list var="operator">
  <icb:item>operator</icb:item>
</icb:list>
<icb:list var="contractStatus">
  <icb:item>contractStatus</icb:item>
</icb:list>

<form:form name="contractSearchForm" method="POST" action="search_contract.htm">
<input type="hidden" name="pageNumber" value="1" />
<form:hidden path="sortFlag"/>
<form:hidden path="submitFlag"/>
  <input type="hidden" name="cxcel" value="false"/>
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="contractSearch"/>  </span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="3">
                    <f:message key="contractSearch"/>
                    <authz:authorize ifAnyGranted="CreateContract">
                      <img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> 
                      <a href="create_contract.htm"><f:message key="addContract"/></a> 
                    </authz:authorize>
                  </th>
                </tr>
                <tr>
                  <td width="20%" class="TDShade"><f:message key="contractID"/>: </td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="contractCode.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="contractCode.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="contractStatus"/>:</td>
                  <td class="TDShadeBlue">=</td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="contractStatus.value" items="${icbfn:dropdown('staticDropdown',contractStatus)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="contractDescription"/>: </td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="contractDescription.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="contractDescription.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                </tr>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td><input name="button" type="button" onClick="submitFunction()" class="button1" value="Search" /></td>
                       <td style="text-align:right;"><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
                    </tr>
                  </table></td>
                </tr>
              </table>
              <br>
              <c:if test="${command.results != null}">
                <div id="contractsearchresults"> 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
                     <th><a href="#start" onClick="sortContractByCode();" ><span class="TDShade"><f:message key="contractID"/> </span></th>
                      <th><a href="#start" onClick="sortContractByStatus();" ><span class="TDShade"><f:message key="contractStatus"/> </span></th>
                      <th><a href="#start" onClick="sortContractByDescription();" ><span class="TDShade"><f:message key="description"/></span></th>
                      <th><span class="TDShade"><f:message key="createdBy"/></span></th>
                      <th><span class="TDShade"><f:message key="createdTime"/></span></th>
                      <th><span class="TDShade"><f:message key="updatedBy"/></span></th>
                      <th><span class="TDShade"><f:message key="updatedTime"/></span></th>
                    </tr>
                    <c:forEach items="${command.results}" var="contract" varStatus="status">
                      <c:choose>
          <c:when test="${status.index%2==0}">
            <tr style="background-color:#FFFFFF;">
            </c:when>
            <c:otherwise>
            <tr style="background-color:#e7eeff;">                    
            </c:otherwise>
            </c:choose>
                        <td>
                          <authz:authorize ifAnyGranted="CreateContract,ContractCBHeader,ContractCBView,ContractCBBasic">
                            <a href="edit_contract.htm?contractCode=${contract.contractCode}">${contract.contractCode}</a>
                          </authz:authorize>
                          <authz:authorize ifNotGranted="CreateContract,ContractCBHeader,ContractCBView,ContractCBBasic">
                            ${contract.contractCode}
                          </authz:authorize>
                        </td>
                        <td><f:message key="status${contract.status}"/></td>
                        <td>${contract.description}</td>
                        <td>${contract.creByUserName}</td>
                        <td>${contract.createdTime}</td>
                        <td>${contract.modByUserName}</td>
                        <td>${contract.updatedTime}</td>
                      </tr>
                    </c:forEach>
                    <tr>
                      <td>&nbsp;</td>
                      <td align="center">
                        <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
                          <a href="#" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
                        </c:forEach>
                      </td>
                    </tr>
                    </tr></tr>
                  </table>
                </div>
              </c:if>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
          </div>
        </div>
        <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", 0)
      
      </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>

