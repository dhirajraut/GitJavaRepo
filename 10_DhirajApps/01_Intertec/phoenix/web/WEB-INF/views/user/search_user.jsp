<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

   <script language="javascript">

  function submitSearch(pageNumber) {
    document.userSearchForm.pageNumber.value = pageNumber;
    document.userSearchForm.uxcel.value = "false";
    document.userSearchForm.submitFlag.value = "true";
    // START : #119240 : 23/06/09
	document.userSearchForm.checkPageSort.value = "true";
	// END : #119240 : 23/06/09
    document.userSearchForm.submit();
  }
  function sortUserByLoginName() {
    document.userSearchForm.pageNumber.value = "1";
    document.userSearchForm.sortFlag.value = "loginName";
    document.userSearchForm.uxcel.value = "false";
    document.userSearchForm.submit();
  }
  function sortUserByFirstName() {
    document.userSearchForm.pageNumber.value = "1";
    document.userSearchForm.sortFlag.value = "firstName";
    document.userSearchForm.uxcel.value = "false";
    document.userSearchForm.submit();
  }
  function sortUserByLastName() {
    document.userSearchForm.pageNumber.value = "1";
    document.userSearchForm.sortFlag.value = "lastName";
    document.userSearchForm.uxcel.value = "false";
    document.userSearchForm.submit();
  }
  function sortUserByCountry() {
    document.userSearchForm.pageNumber.value = "1";
    document.userSearchForm.sortFlag.value = "countryCode";
    document.userSearchForm.uxcel.value = "false";
    document.userSearchForm.submit();
  }
  function sortUserByEmpStatus() {
    document.userSearchForm.pageNumber.value = "1";
    document.userSearchForm.sortFlag.value = "employeeStatus";
    document.userSearchForm.uxcel.value = "false";
    document.userSearchForm.submit();
  }
  function submitFunction() {
    document.userSearchForm.pageNumber.value = "1";
    document.userSearchForm.sortFlag.value = "";
    document.userSearchForm.uxcel.value = "false";
    document.userSearchForm.submitFlag.value = "true";
    document.userSearchForm.submit();
  }

  function submitxcel() {
    document.userSearchForm.uxcel.value = "true";
    document.userSearchForm.sortFlag.value = "";
    top.document.userSearchForm.submit();
  }

  function toggleRole(id){
      if(document.getElementById(id+"one").style.display == "none"){
          document.getElementById(id+"one").style.display = "";
          document.getElementById(id+"all").style.display = "none";
          document.getElementById(id).src = "images/bluarrowrightsml.gif";
      }
      else{
          document.getElementById(id+"one").style.display = "none";
          document.getElementById(id+"all").style.display = "";
          document.getElementById(id).src = "images/bluarrowdownsml.gif";
      }
  }
  
</script>
<form:form name="userSearchForm" method="POST" action="search_user.htm">
  <input type="hidden" name="pageNumber" value="1" />
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
    <input type="hidden" name="uxcel" value="false"/>
  </div>
  
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
    <tr>
      <td valign="top">
  <!----------------------------------------------MAIN CONTAINER----------------------------------------------------->
        <div id="MainContentContainer">
  <!--------------------------------------------TABS CONTENTS-------------------------------------------------------->
          <div id="tabcontainer">
            <div id="tabnav">
              <ul>
                <li><a href="#" rel="tab1"><span><f:message key="userSearch"/></span></a></li>
              </ul>
            </div>
            <!-- Sub Menus container. Do not remove -->
            <div id="tab_inner">
  <!------------------------------------------TAB 1 CONTAINER-------------------------------------------------------->
              <div id="tab1" class="innercontent" >
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                  <tr>
                    <th colspan="4"><f:message key="userSearch"/>
                      <authz:authorize ifAnyGranted="CreateUser">
                        <img src="images/separator2.gif" width="2" height="27" align="middle" style="margin-left:5px; margin-right:5px;"> 
                        <a href="create_user.htm"><f:message key="addUser"/></a> 
                      </authz:authorize>
                      <authz:authorize ifAnyGranted="ChangeUserLoginName">
                        <img src="images/separator2.gif" width="2" height="27" align="middle" style="margin-left:5px; margin-right:5px;"> 
                        <a href="change_user.htm"><f:message key="changeUser"/></a> 
                      </authz:authorize>                    
                    </th>
                  </tr>
                  <tr>
                    <td width="20%" class="TDShade"><f:message key="userName"/>:</td>
                    <td width="30%" class="TDShadeBlue" >
                   <!-- START : #119240 -->
					  <%--  <form:input cssClass="inputBox" size="31" maxlength="128" path="name.value"/> --%>
					  <form:input cssClass="inputBox" size="31" maxlength="128" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					<!-- END : #119240 -->
                    </td>
                    <td width="20%" class="TDShade"><f:message key="roleName"/>:</td>
                    <td width="30%" class="TDShadeBlue">
                      <!-- START : #119240 -->
					  <%--  <form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000000;" cssClass="inputBox" maxlength="128" path="roleName.value" />  --%>
					  <form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000000;" cssClass="inputBox" maxlength="128" path="roleName.value" 
					  onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					  <!-- END : #119240 -->
                    </td>
                    </tr>
                  <tr>
                    <td class="TDShade"><f:message key="firstName"/>:</td>
                    <td class="TDShadeBlue" >
					  <!-- START : #119240 -->
                     <%-- <form:input cssClass="inputBox" size="31" maxlength="128" path="firstName.value" /> --%>
					  <form:input cssClass="inputBox" size="31" maxlength="128" path="firstName.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
					<!-- END : #119240 -->
                    </td>
                    <td class="TDShade"><f:message key="Description"/>:</td>
                    <td class="TDShadeBlue">
				  	<!-- START : #119240 -->
						 <%-- <form:input cssClass="inputBox" maxlength="128" path="roleDesc.value" /> --%>
						 <form:input cssClass="inputBox" maxlength="128" path="roleDesc.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
					<!-- END : #119240 -->
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="lastName"/>:</td>
                    <td class="TDShadeBlue" >
					  <!-- START : #119240 -->
                      <%-- <form:input cssClass="inputBox" size="31" maxlength="128" path="lastName.value" /> --%>
					  <form:input cssClass="inputBox" size="31" maxlength="128" path="lastName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					<!-- END : #119240 -->
                    </td>
                    <td class="TDShade"></td>
                  </tr>
                  <tr>
                     <td class="TDShade"><f:message key="country"/>:</td>
                     <td class="TDShadeBlue" >
                    <!-- START : #119240 -->
                       <%-- <form:select cssClass="selectionBox" path="countryCode.value" 
                                    items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" /> --%>
						<form:select cssClass="selectionBox" path="countryCode.value" 
                            items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					 <!-- END : #119240 -->
                     </td>
                     <td class="TDShade"></td>
                   </tr>
                 </table>
			<ajax:autocomplete
			  baseUrl="createuser.htm"
			  source="roleName.value"
			  target="roleName.name"
			  className="autocomplete"
			  parameters="role={roleName.value}"
			  minimumCharacters="1"
			  />
                 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                   <tr>
                     <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                         <td><input name="button" type="button" onClick="submitFunction()" class="button1" value="Search" /></td>
                         <td style="text-align:right;">
                           <a href="#">
                               <img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" 
                                    width="18" height="18" hspace="5" border="0" align="middle" 
                                    onClick="submitxcel();">
                           </a>
                         </td>
                       </tr>
                     </table></td>
                   </tr>
                 </table>
                 <br>
<c:if test="${command.results != null}">
                 <div id="usersearchresults"> 
                    <strong><f:message key="searchResults"/></strong>
                    <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                      <tr>
                        <th><a href="#" onClick="sortUserByLoginName();" ><span class="TDShade"><f:message key="userName"/></span></a></th>
                        <th><a href="#" onClick="sortUserByFirstName();" ><span class="TDShade"><f:message key="firstName"/></span></a></th>
                        <th><a href="#" onClick="sortUserByLastName();" ><span class="TDShade"><f:message key="lastName"/></span></a></th>
                        <th><a href="#" onClick="sortUserByCountry();" ><span class="TDShade"><f:message key="country"/></span></a></th>
                        <th><a href="#" onClick="sortUserByEmpStatus();" ><span class="TDShade"><f:message key="employeeStatus"/></span></a></th>
                        <th><span class="TDShade">&nbsp;</span></th>
                        <th><span class="TDShade"><f:message key="roleName"/></span></th>
                      </tr>
    <c:forEach items="${command.results}" var="user" varStatus="status">
        <c:choose>
            <c:when test="${status.index%2==0}">
                      <tr style="background-color:#FFFFFF;" valign="top">
            </c:when>
            <c:otherwise>
                      <tr style="background-color:#e7eeff;" valign="top">                    
            </c:otherwise>
        </c:choose>
                        <td>
                           <%-- <authz:authorize ifAnyGranted="CreateUser">--%>
                              <a href="edit_user.htm?loginName=${user.loginName}">
                                ${user.loginName}
                              </a>
                            <%--</authz:authorize>
                            <authz:authorize ifNotGranted="CreateUser">
                              ${user.loginName}
                            </authz:authorize>--%>
                        </td>
                        <td>${user.firstName} </td>
                        <td>${user.lastName} </td>
                        <td>${user.countryCode}</td>
                        <td><f:message key="empstatus_${user.employeeStatus}"/></td>
                        <td style="width:6">
                    <c:if test="${fn:length(user.roles)>1}">
                           <img id="showRole${status.index}" src="images/bluarrowrightsml.gif" 
                                onclick="javascript:toggleRole('showRole${status.index}')">
                    </c:if>
                        </td>
	                    <td>
	                        <div id="showRole${status.index}one" style="height:14px;overflow:hidden">${fn:join(icbfn:userRoleNames(user.roles), "<BR>")}</div>
                    <c:if test="${fn:length(user.roles)>1}">
	                        <div id="showRole${status.index}all" style="display:none">${fn:join(icbfn:userRoleNames(user.roles), "<BR>")}</div>
                    </c:if>
	                    </td>
                      </tr>
    </c:forEach>
                      <tr>
                        <td>&nbsp;</td>
                        <td align="center">
                        <!-- START : #119240 : 16/06/09 --> 
    <%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
                            <a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;
    </c:forEach> --%>
    						<%@ include file="../common/pagination.jsp" %>
    					<!-- END : #119240 : 16/06/09 -->
                        </td>
                      </tr>
                    </table>
                  </div>
</c:if>
              </div>
  <!----------------------------------------------TAB 1 CONTAINER END------------------------------------------------>
            </div>
          </div>
          <script type="text/javascript">
            //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
            dolphintabs.init("tabnav", 0)
          </script>
  <!-------------------------------------------TAB CONTENT END------------------------------------------------------->
        </div>
  <!------------------------------------------MAIN CONTAINER END----------------------------------------------------->
      </td>
    </tr>
  </table>
</form:form>
