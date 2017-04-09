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
document.customerSearchForm.pageNumber.value = pageNumber;
document.customerSearchForm.cxcel.value="false";
document.customerSearchForm.submitFlag.value="true";
document.customerSearchForm.submit();
}	
function sortCustomerByCode(){
document.customerSearchForm.pageNumber.value = "1";
document.customerSearchForm.sortFlag.value = "id";
document.customerSearchForm.cxcel.value="false";
document.customerSearchForm.submit();
}
function sortCustomerByName(){
document.customerSearchForm.pageNumber.value = "1";
document.customerSearchForm.sortFlag.value = "name";
document.customerSearchForm.cxcel.value="false";
document.customerSearchForm.submit();
}


function sortCustomerByAlternateName(){
document.customerSearchForm.pageNumber.value = "1";
document.customerSearchForm.sortFlag.value = "alternateName";
document.customerSearchForm.cxcel.value="false";
document.customerSearchForm.submit();
}
// 96509 start
function sortCustomerByCountry(){
document.customerSearchForm.pageNumber.value = "1";
document.customerSearchForm.sortFlag.value = "country";
document.customerSearchForm.cxcel.value="false";
document.customerSearchForm.submit();
}

function sortCustomerByState(){
document.customerSearchForm.pageNumber.value = "1";
document.customerSearchForm.sortFlag.value = "state";
document.customerSearchForm.cxcel.value="false";
document.customerSearchForm.submit();
}

function sortCustomerByCity(){
document.customerSearchForm.pageNumber.value = "1";
document.customerSearchForm.sortFlag.value = "city";
document.customerSearchForm.cxcel.value="false";
document.customerSearchForm.submit();
}

function sortCustomerByAddress1(){
document.customerSearchForm.pageNumber.value = "1";
document.customerSearchForm.sortFlag.value = "address1";
document.customerSearchForm.cxcel.value="false";
document.customerSearchForm.submit();
}
// 96509 end

function sortCustomerByStatus(){
document.customerSearchForm.pageNumber.value = "1";
document.customerSearchForm.sortFlag.value = "status";
document.customerSearchForm.cxcel.value="false";
document.customerSearchForm.submit();
}
function submitFunction(){
document.customerSearchForm.pageNumber.value = "1";
document.customerSearchForm.sortFlag.value = "";
document.customerSearchForm.submitFlag.value="true";
document.customerSearchForm.cxcel.value="false";
document.customerSearchForm.submit();
}

function submitxcel(){
document.customerSearchForm.cxcel.value="true";
document.customerSearchForm.sortFlag.value = "";
top.document.customerSearchForm.submit();
}

   </script>

   <icb:list var="operator">
  <icb:item>operator</icb:item>
</icb:list>


<form:form name="customerSearchForm" method="POST" action="search_customer.htm">
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
              <li><a href="#" rel="tab1"><span><f:message key="customerSearch"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
<!-- 96509 start-->
                  <th colspan="4">
                    <f:message key="customerSearch"/>
                    <authz:authorize ifAnyGranted="CreateCustomer">
                      <img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> 
                      <a href="create_customer.htm"><f:message key="addCustomer"/></a> 
                    </authz:authorize>
                  </th>
                </tr>
                <tr>
                  <td width="20%" class="TDShade"><f:message key="custCode"/>: </td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="customerId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>&nbsp;&nbsp;<form:input cssClass="inputBox" path="customerId.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                  <td class="TDShadeBlue">&nbsp;</td>
		  <td class="TDShadeBlue">&nbsp;</td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="customerName"/>:</td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="customerName.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>&nbsp;&nbsp;<form:input cssClass="inputBox" maxlength="40" path="customerName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                  <td class="TDShadeBlue">&nbsp;</td>
		  <td class="TDShadeBlue">&nbsp;</td>
                </tr>
				 <tr>
                  <td class="TDShade"><f:message key="alternateName"/>:</td>
                  <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="alternateName.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>&nbsp;&nbsp;<form:input cssClass="inputBox" maxlength="60" path="alternateName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  </td>
                  <td class="TDShadeBlue">&nbsp;</td>
		  <td class="TDShadeBlue">&nbsp;</td>
                </tr>
					<tr class="InnerApplTable">
                  <td width="20%" class="TDShade"><f:message key="country"/><strong> :</strong></td>
                  <td colspan="1" class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="country.value" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />
                    <form:errors path="country.value" cssClass="redstar"/>
                  </td>
                  <td width="20%" class="TDShade"><f:message key="state"/>:</td>
                  <td colspan="1" class="TDShadeBlue">
                    <icb:list var="countryCodeList">
                      <icb:item>${command.country.value}</icb:item>
                    </icb:list> 
                    <form:select cssClass="selectionBox" path="state.value" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" />
                    <form:errors path="state.value" cssClass="redstar"/>
                  </td>
                  <ajax:select
                  baseUrl="country.htm"
                  source="country.value"
                  target="state.value"
                  parameters="country.countryCode={country.value}"
                  parser="new ResponseXmlParser()"/>        
                </tr>
				<tr>
                  <td class="TDShade"><strong><f:message key="city"/>:</strong></td>
                  <td class="TDShadeBlue"><label>
                    <form:input cssClass="inputBox" size="35" maxlength="30" path="city.value" />
                    <form:errors path="city.value" cssClass="redstar"/>
                  </label></td>
                  
                 <td class="TDShade"><f:message key="address1"/>: </td>
					<td class="TDShadeBlue"><form:input cssClass="inputBox" size="35" maxlength="55" path="address1.value"/>
					<form:errors path="address1" cssClass="redstar"/>
 				 </td>
				
                </tr>

<%--  END96509 --%>




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
                <div id="customersearchresults"> 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
                      <th><a href="#" onClick="sortCustomerByCode();" ><span class="TDShade"><f:message key="custCode"/></span></a></th>
                      <th><a href="#" onClick="sortCustomerByName();" ><span class="TDShade"><f:message key="companyName"/></span></a></th>
					
					  <th><a href="#" onClick="sortCustomerByAlternateName();" ><span class="TDShade"><f:message key="alternateName"/></span></a></th>

                      <th><a href="#" onClick="sortCustomerByStatus();" ><span class="TDShade"><f:message key="status"/></span></a></th>
              <!-- 96509 -- start -->        
                      <th><a href="#" onClick="sortCustomerByCountry();" ><span class="TDShade" nowrap><f:message key="country"/></span></a></th>
 					 <th><a href="#" onClick="sortCustomerByState();" ><span class="TDShade" nowrap><f:message key="state"/></span></a></th>
					<th><a href="#" onClick="sortCustomerByCity();" ><span class="TDShade" nowrap><f:message key="city"/></span></a></th>
					<th><a href="#" onClick="sortCustomerByAddress1();" ><span class="TDShade" nowrap><f:message key="address1"/></span></a></th>
 
                    </tr>
                    <c:forEach items="${command.results}" var="custAddresses" varStatus="status">
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
                            <a href="edit_customer.htm?reqFrom=searchCustomerForm&customer.custCode=${custAddresses.custCode}">${custAddresses.custCode}</a>
                          <%--</authz:authorize>
                          <authz:authorize ifNotGranted="CreateCustomer">
                            ${customer.custCode}
                          </authz:authorize>--%>
                        </td>                        
						<td>${custAddresses.custAddrSeq.customer.name}</td>
						<td>${custAddresses.custAddrSeq.customer.alternateName}
                        <td><f:message key="activeStatus${custAddresses.custAddrSeq.customer.status}"/></td>
                        		<td>${custAddresses.country}</td>
						  		<td>${custAddresses.state}</td>
						   		<td>${custAddresses.city}</td>
						   		<td>${custAddresses.address1}</td>
  <!-- 96509  end -->                      
                       
                        
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
      dolphintabs.init("tabnav", 0)
      
      </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>


