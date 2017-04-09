<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- START : #119240 -->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- END : #119240 -->
<%@taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<head>

<script language="javascript">



  function onPermissionsAdd()
  {
    
    document.roleCreateForm.addOrDeleteRole.value = "add";
    document.roleCreateForm.tabName.value = "0";
    document.roleCreateForm.submit();
  }
  function onPermissionsDelete(index)
  {
    document.roleCreateForm.addOrDeleteRole.value = "delete";
    document.roleCreateForm.tabName.value = "0";
    document.roleCreateForm.roleIndex.value = index;
    document.roleCreateForm.submit();

  }

    function setflag(rowIndex)
  {
   
    document.roleCreateForm.roleFlag.value="newval";
    document.roleCreateForm.rowNum.value=rowIndex;
    }
    
 // START : #119240 : 23/06/09
 function doOperation(myOperationType)
  {
    document.roleCreateForm.operationType.value = myOperationType;
    document.roleCreateForm.submit();
  }
 // END : #119240 : 23/06/09
 
</script>
</head>

<form:form name="roleCreateForm" method="POST" action="edit_role.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="tabName"/>
<form:hidden path="addOrDeleteRole"/>
<form:hidden path="roleIndex"/>
<form:hidden path="roleCount"/> 

 <form:hidden path="roleFlag"/>
 <form:hidden path="rowNum"/>
 <form:hidden path="viewOnly"/>
 <form:hidden path="styleVisibility"/>
 <!-- START : #119240 : 23/06/09 -->
 <input type="hidden" name="operationType" value="" />
 
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>
 <!-- END : #119240 : 23/06/09 -->
 
<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="role"/></span></a></li>
        
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            
      
      <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="50%"><f:message key="roles"/></th>
                    <th width="21%" >&nbsp;</th>
                     <th width="29%" bgcolor="#ffffff" style="text-align:right">
    	                 <!-- START : #119240 : 23/06/09 -->
		                     <%-- <a href="search_role.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp
							 <a href="#" style="${command.styleVisibility}" ><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --%>
					 		  <a href="#" onClick="javascript:doOperation('searchRole');">
					            <img src="images/icofindjob.gif" alt="Back to Search Role" width="16" height="14" border="0" align="absmiddle">
					          </a>&nbsp; 
						   <!-- START for ITrack note : 27-Jul-2009 -->
						<%-- <c:if test="${command.roleSearch != null}"> --%>
						<c:if test="${command.roleSearch != null && command.roleSearch.results != null 
							&& fn:length(command.roleSearch.results) > 1}">
							 <!-- END for ITrack note : 27-Jul-2009 -->
					          <a href="#" onClick="javascript:doOperation('prevRole');">
					            <img src="images/prevleftarrow.gif" alt="Go to Previous Role" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
					          <a href="#" onClick="javascript:doOperation('nextRole');">
					            <img src="images/nextrtarrow.gif" alt="Go to Next Role" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;	
						</c:if>
							  <a href="#"  onClick="javascript:doOperation('saveRole');" style="${command.styleVisibility}">
					            <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
					          </a>
						 <!-- END : #119240 : 23/06/09 -->
					 </th>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td class="TDShade"><f:message key="role"/>:<span class="TDShadeBlue">${command.role.name} </td>
                   <td class="TDShade" ><f:message key="description"/>: <span class="TDShadeBlue">
                     <form:input cssClass="inputBox" size="15" maxlength="128" path="role.roleDesc" disabled="${command.viewOnly}"/>
                       <form:errors path="role.roleDesc" cssClass="redstar"/>
                    </span></td>
                    <td bgcolor="#ffffff" class="TDShade" style="text-align:right">&nbsp;</td>
                  </tr>

          <tr bgcolor=#ffffff>
                    <td colspan="3" class="TDShade" style="padding:2px;"><table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                      <tr>
                         <th width="25"><f:message key="no"/>.</th>
            <th width="45%"><f:message key="permissionList"/> </th>
            <th width="45%"><f:message key="permissionListDescription"/></th>
         
                       <th width="50" class="TDShadeBlue" style="text-align:right;"><img style="${command.styleVisibility}" src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onPermissionsAdd()"/></th>
                      </tr>
          

           <c:forEach items="${command.permissions}" var="permissions" varStatus="counter">
      
          <tr>
          <td class="TDShadeBlue">${counter.index+1}</td> 
           <td class="TDShadeBlue">
              <form:input cssClass="inputBox" size="15" maxlength="256"  path="permissions[${counter.index}].name" disabled="${command.viewOnly}"/>
            <form:errors path="permissions[${counter.index}].name" cssClass="redstar"/> 
            <a href="#" style="${command.styleVisibility}" onClick="javascript:setflag(${counter.index});popup_show('roles${counter.index}', 'roles_drag${counter.index}', 'roles_exit${counter.index}', 'screen-corner', 120, 20);popupboxenable();">
                <img src=" images/lookup.gif" alt="lookup permissions" width="13" height="13" border="0"/></a>
            </td>
            <td class="TDShadeBlue" align="center"> 
            <form:input cssClass="inputBox" size="15" path="permissions[${counter.index}].description" disabled="${command.viewOnly}"/>
            <form:errors path="permissions[${counter.index}].description" cssClass="redstar"/>
            </td>
               <td width="50" class="TDShadeBlue" style="text-align:right;"><div id="div3" style="width:50px; text-align:center; margin-right:0;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img style="${command.styleVisibility}" src="images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0" onclick="onPermissionsDelete('${counter.index}')" /></div>
           </td> 
          <ajax:autocomplete
            baseUrl="createuser.htm"
            source="permissions[${counter.index}].name"
            target="permissions[${counter.index}].description"
            className="autocomplete"
            parameters="permName={permissions[${counter.index}].name}"
            minimumCharacters="1"
             />


            <!-- --------------------------- PermissionList Lookup ------------------------------------------------- -->
        <div class="sample_popup" id="roles${counter.index}" style="visibility: hidden; display: none;">
      <div class="menu_form_header" id="roles_drag${counter.index}" style="width:750px;height:auto; "> 
      <img class="menu_form_exit"   id="roles_exit${counter.index}" src=" images/form_exit.png" />
       &nbsp;&nbsp;&nbsp;<f:message key="permissionLists"/></div>
      <div class="menu_form_body" id="rolesbody${counter.index}"   style="width:750px; height:500px;overflow-y:hidden;">
      <iframe align="left" frameborder="0" style="padding:0px;" height="500px;" width="100%"     src="search_permission_list_popup.htm?inputFieldId=permissions[${counter.index}].name&rowNum=${counter.index}" 
      scrolling="auto" id="searchRolePermissionListFr" name="searchRolePermissionListFr" allowtransparency="yes" ></iframe>   
      </div>
      </div>
    <!-- --------------------------------- PermissionList Lookup END ----------------------------------------- -->

          
          
          </c:forEach>  
        

          </tr> 
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->

      
      <%--
      <ajax:updateField
      source="role.name"
      target="role.roleDesc,permissions[${counter.index}].name,permissions[${counter.index}].description"
      baseUrl="create_user.htm"
      action="rn"
      parameters="rolename={role.name}"
      parser="new ResponseXmlParser()"/> --%>
          </div>
        </div>
         <script type="text/javascript">
        var pageNoVar = "${command.tabName}"
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", pageNoVar)      
      </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>





