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
document.countrySearchForm.pageNo.value = pageNo;
document.countrySearchForm.cxcel.value="false";
document.countrySearchForm.submitFlag.value="true";
// START : #119240 : 25/06/09
document.countrySearchForm.checkPageSort.value = "true";
// END : #119240 : 25/06/09
document.countrySearchForm.submit();
} 
function sortCountryByCountryCode(){
document.countrySearchForm.pageNo.value = "1";
document.countrySearchForm.sortFlag.value = "countryCode";
document.countrySearchForm.cxcel.value="false";
document.countrySearchForm.submit();
}
function sortCountryByDescription(){
document.countrySearchForm.pageNo.value = "1";
document.countrySearchForm.sortFlag.value = "name";
document.countrySearchForm.cxcel.value="false";
document.countrySearchForm.submit();
}
function submitFunction(){
document.countrySearchForm.pageNo.value = "1";
document.countrySearchForm.sortFlag.value = "";
document.countrySearchForm.cxcel.value="false";
document.countrySearchForm.submitFlag.value="true";
document.countrySearchForm.submit();
}

function submitxcel(){
document.countrySearchForm.cxcel.value="true";
document.countrySearchForm.sortFlag.value = "";
top.document.countrySearchForm.submit();
}
function sortCountryByStatus(){
document.countrySearchForm.pageNo.value = "1";
document.countrySearchForm.sortFlag.value = "status";
document.countrySearchForm.cxcel.value="false";
document.countrySearchForm.submit();
}
</script>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<form:form name="countrySearchForm" method="POST" action="search_country.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="pageNo" />
 <form:hidden path="sortFlag"/>
 <form:hidden path="submitFlag"/>
<!-- START : #119240 : 15/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 15/06/09 --> 
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
              <li><a href="#" rel="tab1"><span><f:message key="countrySearch"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="4">
                    <f:message key="countrySearch"/>
                    <authz:authorize ifAnyGranted="CreateBaseData">
                      <img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> 
                      <a href="create_country.htm"><f:message key="addCountry"/></a>
                    </authz:authorize>
                  </th>
                </tr>
                <tr>
                  <th colspan="4"><f:message key="countrySearch"/></th>
                </tr>
                <tr>
                  <td width="15%" class="TDShade"><f:message key="countryCode"/>:</td>
                  <td width="30%" class="TDShadeBlue">
                 <!-- START : #119240 -->
				<%--  <form:input cssClass="inputBox" maxlength="3" path="countryCodes.value" /> --%>
				 <form:input cssClass="inputBox" maxlength="3" path="countryCodes.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
				 <!-- END : #119240 -->
                  </td>
                  
                  <td width="12%" class="TDShade"><f:message key="status" />: </td>
                  <td class="TDShadeBlue">
				  <!-- START : #119240 -->
				<%--   <form:select id="sel1"
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
                <tr>
                  <td class="TDShade"><f:message key="description"/>:</td>
                  <td class="TDShadeBlue">
				  <!-- START : #119240 -->
                 <%--  <form:input cssClass="inputBox" maxlength="30" path="name.value" />  --%>
					  <form:input cssClass="inputBox" maxlength="30" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
				  <!-- END : #119240 -->
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
                <div id="countrysearchresults"> 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
                      <th><a href="#" onClick="sortCountryByCountryCode();" ><span class="TDShade"><f:message key="countryCode"/></span></a></th>
                    
                      <th><a href="#" onClick="sortCountryByDescription();" ><span class="TDShade"><f:message key="description"/></span></a></th>
                      
                        <!--  start issue 115770 -->
                      <th><a href="#" onClick="sortCountryByStatus();" ><span class="TDShade"><f:message key="status"/></span></a></th>
                      <!-- End 115770 -->

                    </tr>
                    <c:forEach items="${command.results}" var="country" varStatus="status">
                     <c:choose>
									<c:when test="${status.index%2==0}">
									<tr style="background-color:#FFFFFF;">
									</c:when>
									<c:otherwise>
									<tr style="background-color:#e7eeff;">                    
									</c:otherwise>
									</c:choose>                       
                        <td>
                          <authz:authorize ifAnyGranted="CreateBaseData">
                            <a href="edit_country.htm?countryCode=${country.countryCode}">${country.countryCode}</s>
                          </authz:authorize>
                          <authz:authorize ifNotGranted="CreateBaseData">
                            ${country.countryCode}
                          </authz:authorize>
                        </td>
                        <td>${country.name}</td>
                        <td><f:message key="activeStatus${country.status}"/></td>
                      </tr>
                    </c:forEach>
                    <tr>
                      <td>&nbsp;</td>
                      <td align="center">
                        <!-- START : #119240 : 16/06/09 --> 
                      <%--  <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
                          <a href="#" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
                        </c:forEach> --%>
                        <%@ include file="../common/pagination.jsp" %>
    					<!-- END : #119240 : 16/06/09 -->
                        
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
