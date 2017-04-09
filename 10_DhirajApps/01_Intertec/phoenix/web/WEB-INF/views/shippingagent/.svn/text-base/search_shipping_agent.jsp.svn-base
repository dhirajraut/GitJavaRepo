<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script language="javascript">
  function submitSearch(pageNumber)
  {  
    document.shippingAgentSearchForm.pageNumber.value = pageNumber;
	document.shippingAgentSearchForm.sxcel.value="false";
	document.shippingAgentSearchForm.submitFlag.value="true";
    document.shippingAgentSearchForm.submit();
  } 
  function sortShippingAgentById()
  {
    document.shippingAgentSearchForm.pageNumber.value = "1";
    document.shippingAgentSearchForm.sortFlag.value = "id";
	document.shippingAgentSearchForm.sxcel.value="false";
    document.shippingAgentSearchForm.submit();
  }
  function sortShippingAgentByName()
  {
    document.shippingAgentSearchForm.pageNumber.value = "1";
    document.shippingAgentSearchForm.sortFlag.value = "name";
	document.shippingAgentSearchForm.sxcel.value="false";
    document.shippingAgentSearchForm.submit();
  }
  function sortShippingAgentByStatus()
  {
    document.shippingAgentSearchForm.pageNumber.value = "1";
    document.shippingAgentSearchForm.sortFlag.value = "status";
	document.shippingAgentSearchForm.sxcel.value="false";
    document.shippingAgentSearchForm.submit();
  }
  function submitFunction()
  {
    document.shippingAgentSearchForm.pageNumber.value = "1";
    document.shippingAgentSearchForm.sortFlag.value = "";
	document.shippingAgentSearchForm.sxcel.value="false";
	document.shippingAgentSearchForm.submitFlag.value="true";
    document.shippingAgentSearchForm.submit();
  }
function submitxcel(){
document.shippingAgentSearchForm.sxcel.value="true";
document.shippingAgentSearchForm.sortFlag.value = "";
top.document.shippingAgentSearchForm.submit();
}
</script>
<form:form name="shippingAgentSearchForm" method="POST" action="search_shipping_agent.htm">
<input type="hidden" name="pageNumber" value="1" />
<form:hidden path="sortFlag"/>
<form:hidden path="submitFlag"/>
  <input type="hidden" name="sxcel" value="false"/>
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="shippingAgent"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table border="0" align="center" cellpadding="1" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="5">
                    <f:message key="searchShippingAgent"/>
                    <authz:authorize ifAnyGranted="CreateShippingAgent">
                      <img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> 
                      <a href="create_shipping_agent.htm"><f:message key="addShippingAgent"/></a> 
                    </authz:authorize>
                  </th>
                </tr>
                <tr class="InnerApplTable">
                  <td width="20%" class="TDShade"><f:message key="country"/><strong> :</strong></td>
                  <td colspan="1" class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="country.value" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                    <form:errors path="country.value" cssClass="redstar"/>
                 </td>
        
                 <td class="TDShade"><f:message key="state"/>:</td>
                 <td colspan="2" class="TDShadeBlue">
                  <icb:list var="countryCodeList">
                    <icb:item>${command.country.value}</icb:item>
                  </icb:list> 
                  <form:select cssClass="selectionBox" path="state.value" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
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
                  <td class="TDShadeBlue">
                    <form:input cssClass="inputBox" size="35" maxlength="64" path="city.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                    <form:errors path="city.value" cssClass="redstar"/>
                  </td>
                  <td class="TDShade"><strong><f:message key="shippingAgent"/>:</strong></td>
                  <td colspan="2" class="TDShadeBlue">
                    <form:input cssClass="inputBox" size="35" maxlength="128" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                    <form:errors path="name.value" cssClass="redstar"/>
                  </td>
                </tr>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td><input id="search" type="button" value="Search"
                name="search" class="button1" onClick="submitFunction()"/></td>
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
                      <th><a href="#" onClick="sortShippingAgentById();" ><span class="TDShade"><f:message key="shippingAgentID"/></span></a></th>
                      <th><a href="#" onClick="sortShippingAgentByName();" ><span class="TDShade"><f:message key="shippingAgentName"/></span></a></th>
                      <th><a href="#" onClick="sortShippingAgentByStatus();" ><span class="TDShade"><f:message key="status"/></span></a></th>
                    </tr>
                    <c:forEach items="${command.results}" var="shippingAgent" varStatus="status">
                      <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
                        <td>
                          <%--<authz:authorize ifAnyGranted="CreateShippingAgent">--%>
                            <a href="edit_shipping_agent.htm?id=${shippingAgent.id}">${shippingAgent.id}</a>
                          <%--</authz:authorize>
                          <authz:authorize ifNotGranted="CreateShippingAgent">
                            ${shippingAgent.id}
                          </authz:authorize>--%>
                        </td>
                        <td>${shippingAgent.name}</td>                        
                        <td><f:message key="activeStatus${shippingAgent.status}"/></td>
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
