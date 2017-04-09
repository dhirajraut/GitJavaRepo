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
document.branchSearchForm.pageNo.value = pageNo;
document.branchSearchForm.bxcel.value="false";
document.branchSearchForm.submitFlag.value="true";
// START : #119240 : 29/06/09
document.branchSearchForm.checkPageSort.value = "true";
// END : #119240 : 29/06/09
document.branchSearchForm.submit();
}
function sortBranchByBuName(){
document.branchSearchForm.pageNo.value = "1";
document.branchSearchForm.sortFlag.value = "businessUnit.name";
document.branchSearchForm.bxcel.value="false";
document.branchSearchForm.submit();
}
function sortBranchByDescription(){
document.branchSearchForm.pageNo.value = "1";
document.branchSearchForm.sortFlag.value = "description";
document.branchSearchForm.bxcel.value="false";
document.branchSearchForm.submit();
}
function sortBranchByBranchCode(){
document.branchSearchForm.pageNo.value = "1";
document.branchSearchForm.bxcel.value="false";
document.branchSearchForm.sortFlag.value = "name";
document.branchSearchForm.submit();
}
function sortBranchByStatus(){
document.branchSearchForm.pageNo.value = "1";
document.branchSearchForm.sortFlag.value = "status";
document.branchSearchForm.bxcel.value="false";
document.branchSearchForm.submit();
}
function sortBranchByCountry(){
document.branchSearchForm.pageNo.value = "1";
document.branchSearchForm.sortFlag.value = "countryCode";
document.branchSearchForm.bxcel.value="false";
document.branchSearchForm.submit();
}
function sortBranchByState(){
document.branchSearchForm.pageNo.value = "1";
document.branchSearchForm.sortFlag.value = "stateCode";
document.branchSearchForm.bxcel.value="false";
document.branchSearchForm.submit();
}
function sortBranchByCity(){
document.branchSearchForm.pageNo.value = "1";
document.branchSearchForm.sortFlag.value = "city";
document.branchSearchForm.bxcel.value="false";
document.branchSearchForm.submit();
}
// 96509 start
function sortBranchByAddress1(){
document.branchSearchForm.pageNo.value = "1";
document.branchSearchForm.sortFlag.value = "address1";
document.branchSearchForm.bxcel.value="false";
document.branchSearchForm.submit();
}
// 96509 End
function submitFunction(){
document.branchSearchForm.pageNo.value = "1";
document.branchSearchForm.sortFlag.value = "";
document.branchSearchForm.bxcel.value="false";
document.branchSearchForm.submitFlag.value="true";
document.branchSearchForm.submit();
}

function submitxcel(){
document.branchSearchForm.bxcel.value="true";
document.branchSearchForm.sortFlag.value = "";
top.document.branchSearchForm.submit();
}
</script>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<form:form name="branchSearchForm" method="POST" action="search_branch.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="pageNo" />
 <form:hidden path="sortFlag"/>
 <form:hidden path="submitFlag"/>
 <!-- START : #119240 : 19/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 19/06/09 --> 
 <input type="hidden" name="bxcel" value="false"/>
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="branchSearch"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="4">
                    <f:message key="branchSearch"/>
                    <authz:authorize ifAnyGranted="CreateBranch">
                      <img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> 
                      <a href="create_branch.htm"><f:message key="addBranch"/></a>
                    </authz:authorize>
                  </th>
                </tr>
             


				<tr class="InnerApplTable">
                  <td width="20%" class="TDShade"><f:message key="country"/><strong> :</strong></td>
                  <td colspan="1" class="TDShadeBlue">
                    <!-- START : #119240 -->
					<%-- <form:select cssClass="selectionBox" path="country.value" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" /> --%>
					<form:select cssClass="selectionBox" path="country.value" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
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
					<form:select cssClass="selectionBox" path="state.value" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
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
					<form:input cssClass="inputBox" size="35" maxlength="30" path="city.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
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
                  <td width="20%" class="TDShade"><f:message key="businessUnitName"/>:</td>
                  <td width="40%" class="TDShadeBlue">
					<!-- START : #119240 -->
                    <%-- <form:input cssClass="inputBox" maxlength="128" path="businessUnit.value" /> --%>
					<form:input cssClass="inputBox" maxlength="128" path="businessUnit.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					<!-- END : #119240 -->
                  </td>
				    <td class="TDShade"><f:message key="branchCode"/>:</td>
                  <td class="TDShadeBlue">
				  <!-- START : #119240 -->
                   <%-- <form:input cssClass="inputBox" maxlength="8" path="name.value" /> --%>
				   <form:input cssClass="inputBox" maxlength="8" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					<!-- END : #119240 -->
                  </td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="branchName"/>:</td>
                  <td class="TDShadeBlue">
				  <!-- START : #119240 -->
					 <%--  <form:input cssClass="inputBox" maxlength="512" path="desc.value" /> --%>
					  <form:input cssClass="inputBox" maxlength="512" path="desc.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
                  <!-- END : #119240 -->
	    		  </td>
                  <td class="TDShade"><f:message key="status" />: </td>
                  <td class="TDShadeBlue">
				  <!-- START : #119240 -->
				  <%-- <form:select id="sel1"
                      cssClass="selectionBox" path="status.value"
                      items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name"
                      itemValue="value" /> --%>
					<form:select id="sel1"
                      cssClass="selectionBox" path="status.value"
                      items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name"
                      itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
				  <!-- END : #119240 --> 	
                  </td>
                </tr>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td><input name="button" type="button" onClick="submitFunction()" class="button1" value="Search" /></td></td>
                     <td style="text-align:right;"><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
                    </tr>
                  </table></td>
                </tr>
              </table>
              <br>
              <c:if test="${command.results != null}">
                <div id="branchcodesearchresults"> 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
					<th><a href="#" onClick="sortBranchByBuName();" ><span class="TDShade" nowrap><f:message key="businessUnitName"/></span></a></th>
									<th><a href="#" onClick="sortBranchByBranchCode();" ><span class="TDShade" nowrap><f:message key="branchCode"/></span></a></th>
									<th><a href="#" onClick="sortBranchByDescription();" ><span class="TDShade" nowrap><f:message key="branchName"/></span></a></th>
									<th><a href="#" onClick="sortBranchByStatus();" ><span class="TDShade" nowrap><f:message key="status"/></span></a></th>
									<th><a href="#" onClick="sortBranchByCountry();" ><span class="TDShade" nowrap><f:message key="country"/></span></a></th>
									<th><a href="#" onClick="sortBranchByState();" ><span class="TDShade" nowrap><f:message key="state"/></span></a></th>
									<th><a href="#" onClick="sortBranchByCity();" ><span class="TDShade" nowrap><f:message key="city"/></span></a></th>
									<!-- 96509 -->
									<th><a href="#" onClick="sortBranchByAddress1();" ><span class="TDShade" nowrap><f:message key="address1"/></span></a></th>
                    </tr>
                    <c:forEach items="${command.results}" var="branch" varStatus="status">
                      <c:choose>
						<c:when test="${status.index%2==0}">
						<tr style="background-color:#FFFFFF;">
						</c:when>
						<c:otherwise>
						<tr style="background-color:#e7eeff;">                    
						</c:otherwise>
						</c:choose>
                        <td>
                          ${branch.businessUnit.name}            
                        </td>
                        <td>
                          <%--<authz:authorize ifAnyGranted="CreateBranch">--%>
                            <a href="edit_branch.htm?branch.name=${branch.name}">${branch.name}</a>
                          <%--</authz:authorize>
                          <authz:authorize ifNotGranted="CreateBranch">
                            ${branch.name}
                          </authz:authorize>--%>
                        </td>
                        <td>${branch.description}</td>
                        <td><f:message key="activeStatus${branch.status}"/></td>
						 <td>${branch.countryCode}</td>
						  <td>${branch.stateCode}</td>
						   <td>${branch.city}</td>
						   <!-- 96509 -->
						   <td>${branch.address1}</td>
                      </tr>
                    </c:forEach>
                    <tr>
                      <td>&nbsp;</td>
                      <td align="center">
					   <!-- START : #119240 : 19/06/09 --> 
                     <%--   <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
                          <a href="#end" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
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
