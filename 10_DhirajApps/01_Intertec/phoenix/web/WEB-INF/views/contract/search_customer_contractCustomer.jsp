<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script language="javascript">
function submitSearch(pageNumber){  
document.customerSearchContactCustForm.pageNumber.value = pageNumber;
document.customerSearchContactCustForm.cxcel.value="false";
document.customerSearchContactCustForm.submitFlag.value="true";
document.customerSearchContactCustForm.submit();
}	
function sortCustomerByCode(){
document.customerSearchContactCustForm.pageNumber.value = "1";
document.customerSearchContactCustForm.sortFlag.value = "id";
document.customerSearchContactCustForm.cxcel.value="false";
document.customerSearchContactCustForm.submit();
}
function sortCustomerByName(){
document.customerSearchContactCustForm.pageNumber.value = "1";
document.customerSearchContactCustForm.sortFlag.value = "name";
document.customerSearchContactCustForm.cxcel.value="false";
document.customerSearchContactCustForm.submit();
}
function sortCustomerByStatus(){
document.customerSearchContactCustForm.pageNumber.value = "1";
document.customerSearchContactCustForm.sortFlag.value = "status";
document.customerSearchContactCustForm.cxcel.value="false";
document.customerSearchContactCustForm.submit();
}
function submitFunction(){
document.customerSearchContactCustForm.pageNumber.value = "1";
document.customerSearchContactCustForm.sortFlag.value = "";
document.customerSearchContactCustForm.submitFlag.value="true";
document.customerSearchContactCustForm.cxcel.value="false";
document.customerSearchContactCustForm.submit();
}

function submitxcel(){
document.customerSearchContactCustForm.cxcel.value="true";
document.customerSearchContactCustForm.sortFlag.value = "";
top.document.customerSearchContactCustForm.submit();
}

function setCustIdNametoParent(custId,custName){
	top.document.contractEditForm.searchCustomerId.value = custId;
	top.document.contractEditForm.searchCustomerName.value = custName;
	top.popupboxclose();
	top.hidePopupDiv('dsearchCustId','dsearchCustIdbody');
	top.loadCustContrData();
}

   </script>

   <icb:list var="operator">
  <icb:item>operator</icb:item>
</icb:list>


<form:form name="customerSearchContactCustForm" method="POST" action="search_customer_contractCust.htm">
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
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="3">
                    <f:message key="customerSearch"/>                    
                  </th>
                </tr>
                <tr>
                  <td width="20%" class="TDShade"><f:message key="custCode"/>: </td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="customerId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="customerId.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="customerName"/>:</td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="customerName.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" maxlength="40" path="customerName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                </tr>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td><input name="button" type="button" onClick="submitFunction()" class="button1" value="Search" /></td>
                      <td style="text-align:right;"></td>
                    </tr>
                  </table></td>
                </tr>
              </table>
              <br>
              <c:if test="${command.results != null}">
                <div id="customersearchresults"> 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
                      <th><a href="#" onClick="sortCustomerByCode();" ><span class="TDShade"><f:message key="custCode"/></span></a></th>
                      <th><a href="#" onClick="sortCustomerByName();" ><span class="TDShade"><f:message key="companyName"/></span></a></th>
                      <th><a href="#" onClick="sortCustomerByStatus();" ><span class="TDShade"><f:message key="status"/></span></a></th>
                    </tr>
                    <c:forEach items="${command.results}" var="contractCust" varStatus="status">
                       <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
                        <td>
                          <%--<authz:authorize ifAnyGranted="CreateCustomer">--%>
                            <a href="javaScript:setCustIdNametoParent('${contractCust.customer.custCode}','${contractCust.customer.name}')">${contractCust.customer.custCode}</a>
                          <%--</authz:authorize>
                          <authz:authorize ifNotGranted="CreateCustomer">
                            ${contractCust.customer.custCode}
                          </authz:authorize>--%>
                        </td>
                        <td>${contractCust.customer.name}</td>
                        <td><f:message key="activeStatus${contractCust.customer.status}"/></td>
                      </tr>
                    </c:forEach>
                    <tr>
                      <td>&nbsp;</td>
                      <td align="center">
                        <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
                          <a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
                        </c:forEach>
                      </td>
                    </tr>
                    <tr></tr>
                  </table>
                </div>
              </c:if>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
          </div>
        </div>
        <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
     // dolphintabs.init("tabnav", 0)
      
      </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>


