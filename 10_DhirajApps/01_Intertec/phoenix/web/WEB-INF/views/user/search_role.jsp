<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="javascript">

    function submitSearch(pageNo) {
        document.roleSearchForm.pageNo.value = pageNo;
        document.roleSearchForm.rxcel.value = "false";
        document.roleSearchForm.submitFlag.value = "true";
		// START : #119240 : 23/06/09
		document.roleSearchForm.checkPageSort.value = "true";
		// END : #119240 : 23/06/09
        document.roleSearchForm.submit();
    }
    function sortRoleByName() {
        document.roleSearchForm.pageNo.value = "1";
        document.roleSearchForm.sortFlag.value = "name";
        document.roleSearchForm.rxcel.value = "false";
        document.roleSearchForm.submit();
    }
    function sortRoleByRoleDesc() {
        document.roleSearchForm.pageNo.value = "1";
        document.roleSearchForm.sortFlag.value = "roleDesc";
        document.roleSearchForm.rxcel.value = "false";
        document.roleSearchForm.submit();
    }
    function submitFunction() {
        document.roleSearchForm.pageNo.value = "1";
        document.roleSearchForm.sortFlag.value = "";
        document.roleSearchForm.submitFlag.value = "true";
        document.roleSearchForm.rxcel.value = "false";
        document.roleSearchForm.submit();
    }

    function submitxcel() {
        document.roleSearchForm.rxcel.value = "true";
        document.roleSearchForm.sortFlag.value = "";
        top.document.roleSearchForm.submit();
    }
    function togglePerm(id){
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

<form:form name="roleSearchForm" method="POST" action="search_role.htm">
  <div style="color:red;">
    <form:errors cssClass="error"/>
  </div>
  <form:hidden path="pageNo" />
  <form:hidden path="sortFlag"/>
  <form:hidden path="submitFlag"/>
  <form:hidden path="styleVisibility"/>
  <!-- START : #119240 : 19/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 19/06/09 --> 
  
  <input type="hidden" name="rxcel" value="false"/>
  <table border="0" cellpadding="0" cellspacing="0" class="MainTable">
   <tr>
    <td valign="top">
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="roleSearch"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
<!------------------------------------------- TAB 1 CONTAINER ----------------------------------------------------->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="4">
                    <f:message key="roleSearch"/>
                    <img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> 
                    <a href="create_role.htm" style="${command.styleVisibility}"><f:message key="addRole"/></a>
                  </th>
                </tr>
                <tr>
                  <td width="20%" class="TDShade"><f:message key="roleName"/>:</td>
                  <td width="30%" class="TDShadeBlue" >
                    <!-- START : #119240 -->
					<%--<form:input cssClass="inputBox" maxlength="128" path="name.value" /> --%>
					<form:input cssClass="inputBox" maxlength="128" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					<!-- END : #119240 -->
                  </td>
                  <td width="20%" class="TDShade"><f:message key="permissionName"/>:</td>
                  <td width="30%" class="TDShadeBlue">
				  <!-- START : #119240 -->
                    <%-- <form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000000;" cssClass="inputBox" maxlength="128" path="permission.value" /> --%>
					<form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000000;" cssClass="inputBox" maxlength="128" path="permission.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
				   <!-- END : #119240 -->		
                       <form:errors path="permission.value" cssClass="redstar"/>
                  </td>
                </tr>
                <tr>
                  <td class="TDShade"><f:message key="description"/>:</td>
                  <td class="TDShadeBlue" >
				  <!-- START : #119240 -->
                    <%-- <form:input cssClass="inputBox" maxlength="25" path="roleDesc.value" /> --%>
					<form:input cssClass="inputBox" maxlength="25" path="roleDesc.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
                  <!-- END : #119240 -->
                  </td>
                  <td class="TDShade"><f:message key="description"/>:</td>
                  <td class="TDShadeBlue">
				  <!-- START : #119240 -->
                    <%-- <form:input cssClass="inputBox" maxlength="25" path="permissionDesc.value" /> --%>
					<form:input cssClass="inputBox" maxlength="25" path="permissionDesc.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
				   <!-- END : #119240 -->	
                       <form:errors path="permissionDesc.value" cssClass="redstar"/>
                  </td>
                 </tr>
              </table>
              <ajax:autocomplete
                baseUrl="createuser.htm"
                source="permission.value"
                target="permission.name"
                className="autocomplete"
                parameters="permName={permission.value}"
                minimumCharacters="1"
                />
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
                <div id="rolesearchresults"> 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
                      <th><a href="#" onClick="sortRoleByName();" ><span class="TDShade"><f:message key="roleName"/></span></a></th>
                      <th><a href="#" onClick="sortRoleByRoleDesc();" ><span class="TDShade"><f:message key="description"/></span></a></th>
              <c:if test="true">
                      <th><span class="TDShade">&nbsp;</span></th>
                      <th><span class="TDShade"><f:message key="permission"/></span></th>
              </c:if>
                    </tr>
              <c:forEach items="${command.results}" var="role" varStatus="status">
                <c:choose>
                  <c:when test="${status.index%2==0}">
                    <tr style="background-color:#FFFFFF;" valign="top">
                  </c:when>
                  <c:otherwise>
                    <tr style="background-color:#e7eeff;" valign="top">                    
                  </c:otherwise>
                </c:choose>
                      <td><A href="edit_role.htm?role.name=${role.name}">${role.name}</A></td>
                      <td>${role.roleDesc}</td>
                <c:if test="true">
                      <td style="width:6">
                    <c:if test="${fn:length(role.permissions)>1}">
                         <img id="showPerm${status.index}" src="images/bluarrowrightsml.gif" 
                              onclick="javascript:togglePerm('showPerm${status.index}')">
                    </c:if>
                      </td>
                      <td>
                        <div id="showPerm${status.index}one" style="height:14px;overflow:hidden">${fn:join(icbfn:rolePermissionNames(role.permissions), "<BR>")}</div>
                    <c:if test="${fn:length(role.permissions)>1}">
                        <div id="showPerm${status.index}all" style="display:none">${fn:join(icbfn:rolePermissionNames(role.permissions), "<BR>")}</div>
                    </c:if>
                      </td>
                </c:if>      
                    </tr>
              </c:forEach>
                    <tr>
                      <td>&nbsp;</td>
                      <td align="center">
					 <!-- START : #119240 : 19/06/09 --> 
                <%--  <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
                        <a href="#" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>
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
