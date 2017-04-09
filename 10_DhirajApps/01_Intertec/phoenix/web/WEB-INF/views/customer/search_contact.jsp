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
document.contactSearchForm.pageNumber.value = pageNumber;
document.contactSearchForm.cxcel.value="false";
document.contactSearchForm.submitFlag.value="true";
document.contactSearchForm.submit();
}	
function sortContactById(){
document.contactSearchForm.pageNumber.value = "1";
document.contactSearchForm.sortFlag.value = "id";
document.contactSearchForm.cxcel.value="false";
document.contactSearchForm.submit();
}
function sortContactByName(){
document.contactSearchForm.pageNumber.value = "1";
document.contactSearchForm.sortFlag.value = "name";
document.contactSearchForm.cxcel.value="false";
document.contactSearchForm.submit();
}
function sortContactByCustomerId(){
document.contactSearchForm.pageNumber.value = "1";
document.contactSearchForm.sortFlag.value = "customer.custCode";
document.contactSearchForm.cxcel.value="false";
document.contactSearchForm.submit();
}
function sortContactByCustomerName(){
document.contactSearchForm.pageNumber.value = "1";
document.contactSearchForm.sortFlag.value = "customer.name";
document.contactSearchForm.cxcel.value="false";
document.contactSearchForm.submit();
}
function submitFunction(){
document.contactSearchForm.pageNumber.value = "1";
document.contactSearchForm.sortFlag.value = "";
document.contactSearchForm.cxcel.value="false";
document.contactSearchForm.submitFlag.value="true";
document.contactSearchForm.submit();
}

function submitxcel(){
document.contactSearchForm.cxcel.value="true";
document.contactSearchForm.sortFlag.value = "";
top.document.contactSearchForm.submit();
}

   </script>
<icb:list var="operator">
  <icb:item>operator</icb:item>
</icb:list>

<form:form name="contactSearchForm" method="POST" action="search_contact.htm">
<input type="hidden" name="pageNumber" value="1" />
<form:hidden path="sortFlag"/>
<form:hidden path="submitFlag"/>
  <input type="hidden" name="cxcel" value="false"/>
	
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
<!----------------------------------------------------MAIN CONTAINER----------------------------------------------->
      <div id="MainContentContainer">
 <!-------------------------------------------------TABS CONTENTS-------------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="contactSearch"/> </span></a></li>
            </ul>
          </div>
<!-----------------------------------------Sub Menus container. Do not remove-------------------------------------->
          <div id="tab_inner">
<!-----------------------------------------------TAB 1 CONTAINER--------------------------------------------------->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="3">
                   <f:message key="contactSearch"/>
                    <authz:authorize ifAnyGranted="CreateContact">
                      <img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> 
                      <a href="create_contact.htm"><f:message key="addContact"/></a> 
                    </authz:authorize>
                  </th>
                </tr>
                <tr>
                  <td width="20%" class="TDShade">
                    Contact ID
                    : </td>
                  <td width="10%" class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="contactId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td width="70%" class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="contactId.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="firstName"/>:</td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="firstName.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" />
                  </td>
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" maxlength="128" path="firstName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="lastName"/>:</td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="lastName.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" maxlength="128" path="lastName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="customerID"/>: </td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="customerId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
                  </td>
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" path="customerId.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="customerName"/>: </td>
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
                      <td style="text-align:right;"><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
                    </tr>
                  </table></td>
                </tr>
              </table>
              <br>
              <c:if test="${command.results != null}">
                <div id="contactsearchresults"> 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
                      <th><a href="#" onClick="sortContactById();" ><span class="TDShade"><f:message key="contactID"/></span></a></th>
                      <th><a href="#" onClick="sortContactByName();" ><span class="TDShade"><f:message key="contactName"/></span></a></th>
                      <th><a href="#" onClick="sortContactByCustomerId();" ><span class="TDShade"><f:message key="customerID"/></span><a></th>
                      <th><a href="#" onClick="sortContactByCustomerName();" ><f:message key="customerName"/></a></th>
                    </tr>
                    <c:forEach items="${command.results}" var="contactCust" varStatus="status">
                       <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
                        <td>
                          <%--<authz:authorize ifAnyGranted="CreateContact">--%>
                            <a href="edit_contact.htm?contact.id=${contactCust.contact.id}">${contactCust.contact.id}</a>
                          <%--</authz:authorize>
                          <authz:authorize ifNotGranted="CreateContact">
                            ${contactCust.contact.id}
                          </authz:authorize>--%>
                        </td>
                        <td>${contactCust.contact.firstName} ${contactCust.contact.lastName} </td>   
                        <td>${contactCust.customer.custCode}</td>
                        <td>${contactCust.customer.name}</td>
                    
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
                    <tr></tr>
                  </table>
                </div>
              </c:if>
              </div>
<!--------------------------------------------TAB 1 CONTAINER END------------------------------------------------->
          </div>
        </div>
        <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", 0)
      
      </script>
<!---------------------------------------------------TAB CONTENT END----------------------------------------------->
      </div>
<!--------------------------------------- MAIN CONTAINER END ------------------------------------------------------>
    </td>
  </tr>
</table>
</form:form>


