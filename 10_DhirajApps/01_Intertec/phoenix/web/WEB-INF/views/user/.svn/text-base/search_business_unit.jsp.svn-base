<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script language="javascript">
function submitSearch(pageNo){
document.buSearchForm.pageNo.value = pageNo;
document.buSearchForm.bxcel.value="false";
document.buSearchForm.submitFlag.value="true";
// START : #119240 : 26/06/09
document.buSearchForm.checkPageSort.value = "true";
// END : #119240 : 26/06/09
document.buSearchForm.submit();
}
function sortBusinessUnitByName(){
document.buSearchForm.pageNo.value = "1";
document.buSearchForm.sortFlag.value = "name";
document.buSearchForm.bxcel.value="false";
document.buSearchForm.submit();
}
function sortBusinessUnitByDescription(){
document.buSearchForm.pageNo.value = "1";
document.buSearchForm.sortFlag.value = "description";
document.buSearchForm.bxcel.value="false";
document.buSearchForm.submit();
}
function sortBusinessUnitByCountry(){
document.buSearchForm.pageNo.value = "1";
document.buSearchForm.sortFlag.value = "countryCode";
document.buSearchForm.bxcel.value="false";
document.buSearchForm.submit();
}
function sortBusinessUnitByState(){
document.buSearchForm.pageNo.value = "1";
document.buSearchForm.sortFlag.value = "stateCode";
document.buSearchForm.bxcel.value="false";
document.buSearchForm.submit();
}
function sortBusinessUnitByCity(){
document.buSearchForm.pageNo.value = "1";
document.buSearchForm.sortFlag.value = "city";
document.buSearchForm.bxcel.value="false";
document.buSearchForm.submit();
}
// 96509 start
function sortBusinessUnitByAddress1(){
document.buSearchForm.pageNo.value = "1";
document.buSearchForm.sortFlag.value = "address1";
document.buSearchForm.bxcel.value="false";
document.buSearchForm.submit();
}
// 96509 end
function submitFunction(){
document.buSearchForm.pageNo.value = "1";
document.buSearchForm.sortFlag.value = "";
document.buSearchForm.bxcel.value="false";
document.buSearchForm.submitFlag.value="true";

document.buSearchForm.submit();
}

function submitxcel(){
document.buSearchForm.bxcel.value="true";
document.buSearchForm.sortFlag.value = "";
top.document.buSearchForm.submit();
}
</script>

<form:form name="buSearchForm" method="POST" action="search_business_unit.htm">
<div style="color:red;">
<form:errors cssClass="error"/>
<form:hidden path="sortFlag"/>
<form:hidden path="submitFlag"/>
<!-- START : #119240 : 19/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 19/06/09 --> 
<input type="hidden" name="bxcel" value="false"/>
</div>
<form:hidden path="pageNo" />
<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
<td valign="top">
<!------------------------------------------------MAIN CONTAINER--------------------------------------------------->
<div id="MainContentContainer">
<!------------------------------------------------TABS CONTENTS---------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="businessUnitSearch"/> </span></a></li>
</ul>
</div>
<!----------------------------------------- Sub Menus container. Do not remove ------------------------------------>
<div id="tab_inner">
<!---------------------------------------------- TAB 1 CONTAINER -------------------------------------------------->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="4">
                    <f:message key="businessUnitSearch"/>
                    <authz:authorize ifAnyGranted="CreateBusinessUnit">
                      <img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> 
                      <a href="create_business_unit.htm"><f:message key="addBusinessUnit"/></a> 
                    </authz:authorize>
                  </th>
                </tr>
             
					<tr class="InnerApplTable">
                  <td width="20%" class="TDShade"><f:message key="country"/><strong> :</strong></td>
                  <td colspan="1" class="TDShadeBlue">
                    <!-- START : #119240 -->
					<%-- <form:select cssClass="selectionBox" path="country.value" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" /> --%>
					<form:select cssClass="selectionBox" path="country.value" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
					<!-- END : #119240 -->
                    <form:errors path="country.value" cssClass="redstar"/>
                  </td>
                  <td width="20%" class="TDShade"><f:message key="state"/>:</td>
                  <td colspan="1" class="TDShadeBlue">
                    <icb:list var="countryCodeList">
                      <icb:item>${command.country.value}</icb:item>
                    </icb:list> 
					<!-- START : #119240 -->
                    <%-- <form:select cssClass="selectionBox" path="state.value" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" /> --%>
					<form:select cssClass="selectionBox" path="state.value" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
					<!-- END : #119240 -->
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
				  <!-- START : #119240 -->
                    <%-- <form:input cssClass="inputBox" size="35" maxlength="30" path="city.value" /> --%>
					<form:input cssClass="inputBox" size="35" maxlength="30" path="city.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
					<!-- END : #119240 -->
                    <form:errors path="city.value" cssClass="redstar"/>
                  </label></td>
                  <%--  START 96509 --%>
                 <td class="TDShade"><f:message key="address1"/>: </td>
					<td class="TDShadeBlue">
					<!-- START : #119240 -->
					<%-- <form:input cssClass="inputBox" size="35" maxlength="55" path="address1.value"/> --%>
					<form:input cssClass="inputBox" size="35" maxlength="55" path="address1.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					<!-- END : #119240 -->
					<form:errors path="address1" cssClass="redstar"/>
 				 </td>
				<%--  END96509 --%>
                </tr>
				   <tr>
                  <td width="20%" class="TDShade"><f:message key="businessUnitId"/> : </td>
                  <td width="40%" class="TDShadeBlue">
				  <!-- START : #119240 -->
                    <%-- <form:input cssClass="inputBox" maxlength="5" path="name.value" /> --%>
					<form:input cssClass="inputBox" maxlength="5" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
					<!-- END : #119240 -->
                    <form:errors path="name" cssClass="redstar"/>
                  </td>
				  <td class="TDShade"><f:message key="businessUnitName"/> : </td>
                  <td class="TDShadeBlue">
				  <!-- START : #119240 -->
                    <%-- <form:input cssClass="inputBox" maxlength="152" path="desc.value" /> --%>
					<form:input cssClass="inputBox" maxlength="152" path="desc.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
					<!-- END : #119240 -->
                    <form:errors path="name" cssClass="redstar"/>
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
                <div id="branchcodesearchresults" > 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
<!-- 96509 start -->
                      <th width="15%"><a href="#" onClick="sortBusinessUnitByName();" ><span class="TDShade"><f:message key="businessUnitId"/></span></a></th>
                      <th width="20%"><a href="#" onClick="sortBusinessUnitByDescription();" ><span class="TDShade"><f:message key="businessUnitName"/></span></a></th>
					  <th width="15%"><a href="#" onClick="sortBusinessUnitByCountry();" ><span class="TDShade"><f:message key="country"/></span></a></th>
					  <th width="15%"><a href="#" onClick="sortBusinessUnitByState();" ><span class="TDShade"><f:message key="state"/></span></a></th>
					  <th width="15%"><a href="#" onClick="sortBusinessUnitByCity();" ><span class="TDShade"><f:message key="city"/></span></a></th>
					  <th width="20%"><a href="#" onClick="sortBusinessUnitByAddress1();" ><span class="TDShade"><f:message key="address1"/></span></a></th>
<!-- 96509 end-->                   
 </tr>
                    <c:forEach items="${command.results}" var="businessUnit" varStatus="status">
                      <c:choose>
						<c:when test="${status.index%2==0}">
						<tr style="background-color:#FFFFFF;">
						</c:when>
						<c:otherwise>
						<tr style="background-color:#e7eeff;">                    
						</c:otherwise>
						</c:choose>
                        <td>
                          <%--<authz:authorize ifAnyGranted="CreateBusinessUnit">--%>
                            <a href="edit_business_unit.htm?businessUnit.name=${businessUnit.name}">${businessUnit.name}</a>
                          <%--</authz:authorize>
                          <authz:authorize ifNotGranted="CreateBusinessUnit">
                            ${businessUnit.name}
                          </authz:authorize>--%>
                        </td>
						<td>${businessUnit.description}</td>
                        <td>${businessUnit.countryCode}</td>
						<td>${businessUnit.stateCode}</td>
						<td>${businessUnit.city}</td>
						<!-- 96509 -->
						<td>${businessUnit.address1}</td>
                      </tr>
                    </c:forEach>
                    <tr>
                      <td>&nbsp;&nbsp;</td>
                      <td align="center">
					   <!-- START : #119240 : 19/06/09 --> 
                       <%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
                          <a href="#" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
                        </c:forEach> --%>
						 <%@ include file="../common/pagination.jsp" %>
    				<!-- END : #119240 : 19/06/09 -->
                      </td>
                    </tr>
                    <tr></tr>
                  </table>
                </div>
              </c:if>
            </div>
<!------------------------------------------------TAB 1 CONTAINER END --------------------------------------------->
</div>
</div>
<script type="text/javascript">
//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
dolphintabs.init("tabnav", 0)
</script>
<!--------------------------------------------------- TAB CONTENT END --------------------------------------------->
</div>
<!------------------------------------------ MAIN CONTAINER END --------------------------------------------------->
</td>
</tr>
</table>
</form:form>
