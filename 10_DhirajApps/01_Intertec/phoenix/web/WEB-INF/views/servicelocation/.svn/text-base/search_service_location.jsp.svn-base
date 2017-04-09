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
    document.serviceLocationSearchForm.pageNumber.value = pageNumber;
    document.serviceLocationSearchForm.reqForm.value = "serviceLocationForm";
	document.serviceLocationSearchForm.sxcel.value="false";
	document.serviceLocationSearchForm.submitFlag.value="true";   
    document.serviceLocationSearchForm.submit();
  }
  function submitFunction()
  {
    document.serviceLocationSearchForm.reqForm.value = "serviceLocationForm";
	document.serviceLocationSearchForm.sxcel.value="false";
    document.serviceLocationSearchForm.submit();
  }
  function sortServiceLocationByName()
  {
    document.serviceLocationSearchForm.pageNumber.value = "1";
    document.serviceLocationSearchForm.sortFlag.value = "name";
	document.serviceLocationSearchForm.sxcel.value="false";
    document.serviceLocationSearchForm.submit();
  }
   function sortServiceLocationByCode()
  {
    document.serviceLocationSearchForm.pageNumber.value = "1";
    document.serviceLocationSearchForm.sortFlag.value = "serviceLocationCode";
	document.serviceLocationSearchForm.sxcel.value="false";
    document.serviceLocationSearchForm.submit();
  }
  function sortServiceLocationByCity()
  {
    document.serviceLocationSearchForm.pageNumber.value = "1";
    document.serviceLocationSearchForm.sortFlag.value = "city";
	document.serviceLocationSearchForm.sxcel.value="false";
    document.serviceLocationSearchForm.submit();
  }
  function sortServiceLocationByAddress()
  {
    document.serviceLocationSearchForm.pageNumber.value = "1";
    document.serviceLocationSearchForm.sortFlag.value = "address1";
    document.serviceLocationSearchForm.submit();
  }
  function sortServiceLocationByState()
  {
    document.serviceLocationSearchForm.pageNumber.value = "1";
    document.serviceLocationSearchForm.sortFlag.value = "state.name";
	document.serviceLocationSearchForm.sxcel.value="false";
    document.serviceLocationSearchForm.submit();
  }
  function sortServiceLocationByCountry()
  {
    document.serviceLocationSearchForm.pageNumber.value = "1";
    document.serviceLocationSearchForm.sortFlag.value = "country.name";
	document.serviceLocationSearchForm.sxcel.value="false";
    document.serviceLocationSearchForm.submit();
  }
  function submitFunction()
  {
    document.serviceLocationSearchForm.pageNumber.value = "1";
    document.serviceLocationSearchForm.sortFlag.value = "";
	document.serviceLocationSearchForm.sxcel.value="false";
	document.serviceLocationSearchForm.submitFlag.value="true";
    document.serviceLocationSearchForm.submit();
  }
function submitxcel(){
document.serviceLocationSearchForm.sxcel.value="true";
document.serviceLocationSearchForm.sortFlag.value = "";
top.document.serviceLocationSearchForm.submit();
}
</script>
<form:form name="serviceLocationSearchForm" method="POST" action="search_service_location.htm">
<input type="hidden" name="pageNumber" value="1" />
<form:hidden path="sortFlag"/>
<form:hidden path="reqForm"/>
<form:hidden path="submitFlag"/>
  <input type="hidden" name="sxcel" value="false"/>
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>

<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="serviceLocation"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="2" width="50%">
                    <f:message key="searchServiceLocation"/>
                    <authz:authorize ifAnyGranted="CreateServiceLocation">
                      <img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> 
                      <a href="create_service_location.htm"><f:message key="addServiceLocation"/></a> 
                    </authz:authorize>
                  </th>
                  <th colspan="3">&nbsp;</th>
                </tr>
                <tr class="InnerApplTable">
                  <td width="20%" class="TDShade"><f:message key="country"/><strong> :</strong></td>
                  <td colspan="1" class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="country.value" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                    <form:errors path="country.value" cssClass="redstar"/>
                  </td>
                  <td width="20%" class="TDShade"><f:message key="state"/>:</td>
                  <td colspan="1" class="TDShadeBlue">
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
                  <td class="TDShadeBlue"><label>
                    <form:input cssClass="inputBox" size="35" maxlength="30" path="city.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                    <form:errors path="city.value" cssClass="redstar"/>
                  </label></td>
                  <td class="TDShade">&nbsp;</td>
                  <td colspan="2" class="TDShadeBlue">&nbsp;</td>
                </tr>
                <tr>
                  <td class="TDShade"><strong><f:message key="serviceLocation"/>:</strong> </td>
                  <td class="TDShadeBlue"><form:input cssClass="inputBox" maxlength="128" size="35" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                    <form:errors path="name.value" cssClass="redstar"/></td>
                
				  <td class="TDShade"><strong><f:message key="serviceLocationID"/>:</strong> </td>
                  <td class="TDShadeBlue" colspan="2"><form:input cssClass="inputBox" maxlength="128" size="35" path="serviceLocationCode.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                    <form:errors path="serviceLocationCode.value" cssClass="redstar"/></td>
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
                <div id="servicelocationsearchresults" > 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>        
					  <th width="10%" nowrap><a href="#" onClick="sortServiceLocationByCode();" ><span class="TDShade"><f:message key="serviceLocationID"/></span></a></th>
                      <th width="20%" nowrap><a href="#" onClick="sortServiceLocationByName();" ><span class="TDShade"><f:message key="serviceLocation"/></span></a></th>
                      <th width="20%" colspan="1" nowrap><a href="#" onClick="sortServiceLocationByCity();" ><span class="TDShade"><f:message key="city"/></span></a></th>
                      <th width="20%" nowrap><a href="#" onClick="sortServiceLocationByAddress();" ><span class="TDShade"><f:message key="address"/></span></a></th>
                      <th width="10%" nowrap><a href="#" onClick="sortServiceLocationByState();" ><span class="TDShade"><f:message key="state"/></span></a></th>
                      <th width="20%" nowrap><a href="#" onClick="sortServiceLocationByCountry();" ><span class="TDShade"><f:message key="country"/></span></a></th>
                    </tr>
                    <c:forEach items="${command.results}" var="serviceLocation" varStatus="status">
                       <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
					    <td width="10%">${serviceLocation.serviceLocationCode}</td>
                        <td width="20%">
                          <%--<authz:authorize ifAnyGranted="CreateServiceLocation">--%>
                            <a href="edit_service_location.htm?serviceLocationCode=${serviceLocation.serviceLocationCode}">${serviceLocation.name}</a>
                         <%-- </authz:authorize>
                          <authz:authorize ifNotGranted="CreateServiceLocation">
                            ${serviceLocation.name}
                          </authz:authorize>--%>
                        </td>
                        <td width="20%">${serviceLocation.city}</td>
                        <td width="20%">${serviceLocation.address1}${serviceLocation.address2}${serviceLocation.address3}${serviceLocation.address4}</td> 
                        <td width="10%">${serviceLocation.stateCode}</td>
                        <td width="20%">${serviceLocation.country.name}</td>
                      </tr>
                    </c:forEach>
                    <tr colspan="1" align="center">
                      <td width="10%"></td>
                      <td width="10%"></td>
					  <td width="10%"></td>
                      <td align="center" nowrap>
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
