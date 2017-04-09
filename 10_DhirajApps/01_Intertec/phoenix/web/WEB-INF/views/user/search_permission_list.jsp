<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="javascript">
  function submitSearch(pageNo) {
    document.permissionSearchForm.pageNo.value = pageNo;
    document.permissionSearchForm.pxcel.value = "false";
    document.permissionSearchForm.submitFlag.value = "true";
	 // START : #119240 : 23/06/09
	document.permissionSearchForm.checkPageSort.value = "true";
	// END : #119240 : 23/06/09
    document.permissionSearchForm.submit();
  }
  function sortPermissionByName() {
    document.permissionSearchForm.pageNo.value = "1";
    document.permissionSearchForm.sortFlag.value = "name";
    document.permissionSearchForm.pxcel.value = "false";
    document.permissionSearchForm.submit();
  }
  function sortPermissionByDescription() {
    document.permissionSearchForm.pageNo.value = "1";
    document.permissionSearchForm.sortFlag.value = "description";
    document.permissionSearchForm.pxcel.value = "false";
    document.permissionSearchForm.submit();
  }
  function submitFunction() {
    document.permissionSearchForm.pageNo.value = "1";
    document.permissionSearchForm.sortFlag.value = "";
    document.permissionSearchForm.pxcel.value = "false";
    document.permissionSearchForm.submitFlag.value = "true";
    document.permissionSearchForm.submit();
  }

  function submitxcel() {
    document.permissionSearchForm.pxcel.value = "true";
    document.permissionSearchForm.sortFlag.value = "";
    top.document.permissionSearchForm.submit();
  }
  
  function toggleLink(id){
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

<form:form name="permissionSearchForm" method="POST" action="search_permission_list.htm">
  <div style="color: red;"><form:errors cssClass="error" /></div>
  <form:hidden path="pageNo" />
  <form:hidden path="sortFlag" />
  <form:hidden path="submitFlag" />
  <!-- START : #119240 : 19/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 19/06/09 --> 
  <input type="hidden" name="pxcel" value="false" />
  <table border="0" cellpadding="0" cellspacing="0" class="MainTable">
    <tr>
      <td valign="top"><!-- MAIN CONTAINER -->
      <div id="MainContentContainer"><!-- TABS CONTENTS -->
      <div id="tabcontainer">
      <div id="tabnav">
      <ul>
        <li>
          <a href="#" rel="tab1">
            <span><f:message key="permissionListSearch" /></span>
          </a>
        </li>
      </ul>
      </div>
      <!-- Sub Menus container. Do not remove -->
      <div id="tab_inner"><!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
      <div id="tab1" class="innercontent">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
        <tr>
          <th colspan="4"><f:message key="permissionListSearch" />
            <img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left: 5px; margin-right: 5px;">
            <a href="create_permission_list.htm">
              <f:message key="addPermission" />
            </a>
          </th>
        </tr>
        <tr>
          <td width="20%" class="TDShade"><f:message key="permission" />:</td>
          <td width="30%" class="TDShadeBlue">
		  <!-- START : #119240 -->
          <%--  <form:input cssClass="inputBox" maxlength="256" path="rolePerms.value" /> --%>
			<form:input cssClass="inputBox" maxlength="256" path="rolePerms.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
		  <!-- END : #119240 -->
          </td>
          <td width="20%" class="TDShade"><f:message key="control" />:</td>
          <td width="30%" class="TDShadeBlue">
            <!-- START : #119240 -->
			<%--<form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000000;" cssClass="inputBox" maxlength="256" path="control.value" /> --%>
			<form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000000;" cssClass="inputBox" maxlength="256" path="control.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
			<!-- END : #119240 -->
          </td>
        </tr>
        <tr>
          <td class="TDShade"><f:message key="description" />:</td>
          <td class="TDShadeBlue" >
		  <!-- START : #119240 -->
           <%-- <form:input cssClass="inputBox" path="description.value" /> --%>
		    <form:input cssClass="inputBox" path="description.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
		  <!-- END : #119240 -->
          </td>
          <td class="TDShade"><f:message key="controlDescription" />:</td>
          <td class="TDShadeBlue">
			<!-- START : #119240 -->
				<%-- <form:input cssClass="inputBox" path="controlDesc.value" /> --%>
				<form:input cssClass="inputBox" path="controlDesc.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
			<!-- END : #119240 -->
          </td>
        </tr>
      </table>
      <ajax:autocomplete
        baseUrl="createuser.htm"
        source="control.value"
        target="control.name"
        className="autocomplete"
        parameters="linkName={control.value}"
        minimumCharacters="1"
        />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
        <tr>
          <td>
            <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td>
                  <input name="button" type="button" onClick="submitFunction()" class="button1" value="Search" />
                </td>
                <td style="text-align: right;">
                  <a href="#">
                    <img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel"
                       width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel()">
                  </a>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <br>
      <c:if test="${command.results != null}">
        <div id="permissionsearchresults">
          <strong><f:message key="searchResults" /></strong>
          <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
            <tr>
              <th><a href="#" onClick="sortPermissionByName()"><span class="TDShade"><f:message key="permissionName" /></span></a></th>
              <th><a href="#" onClick="sortPermissionByDescription()"><span class="TDShade"><f:message key="description" /></span></a></th>
              <th><span class="TDShade">&nbsp;</span></th>
              <th><span class="TDShade"><f:message key="control"/></span></th>
            </tr>
<c:forEach items="${command.results}" var="permission" varStatus="status">
  <c:choose>
    <c:when test="${status.index%2==0}">
            <tr style="background-color: #FFFFFF;" valign="top">
    </c:when>
    <c:otherwise>
            <tr style="background-color: #e7eeff;" valign="top">
    </c:otherwise>
</c:choose>
              <td><A href="edit_permission_list.htm?name=${permission.name}">${permission.name}</A></td>
              <td>${permission.description}</td>
              <td>
          <c:if test="${fn:length(permission.links)>1}">
                 <img id="showLink${status.index}" src="images/bluarrowrightsml.gif" 
                      onclick="javascript:toggleLink('showLink${status.index}')">
          </c:if>
              </td>
              <td>
                  <div id="showLink${status.index}one" style="height:14px;overflow:hidden">${fn:join(icbfn:permissionLinkNames(permission.links), "<BR>")}</div>
          <c:if test="${fn:length(permission.links)>1}">
                  <div id="showLink${status.index}all" style="display:none">${fn:join(icbfn:permissionLinkNames(permission.links), "<BR>")}</div>
          </c:if>
              </td>
            </tr>
          </c:forEach>
          <tr>
            <td>&nbsp;&nbsp;</td>
            <td align="center">
                <!-- START : #119240 : 19/06/09 --> 
			 <%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
                <a href="#" onClick="submitSearch('${page.pageNumber}')">${page.name}</a>
                &nbsp;&nbsp;
              </c:forEach> --%>
			  <%@ include file="../common/pagination.jsp" %>
    		<!-- END : #119240 : 19/06/09 -->
            </td>
          </tr>
          <tr></tr>
        </table>
        </div>
      </c:if></div>
      <!----------------- TAB 1 CONTAINER END ------------------------------ -->
      </div>
      </div>
<script type="text/javascript">
  //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
  dolphintabs.init("tabnav", 0)
</script> <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
      </td>
    </tr>
  </table>
</form:form>
