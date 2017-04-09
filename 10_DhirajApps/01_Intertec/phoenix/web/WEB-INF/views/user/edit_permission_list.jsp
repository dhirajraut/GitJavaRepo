 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- START : #119240 -->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- END : #119240 -->
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
   
   <script language="javascript">
     function onPermissionAdd()
  {
  
    document.permissionEditForm.addOrDeletePermission.value = "add";
    document.permissionEditForm.submit();
  }

  function onPermissionDelete(index)
  {
    document.permissionEditForm.addOrDeletePermission.value = "delete";
    document.permissionEditForm.permissionIndex.value = index;
    document.permissionEditForm.submit();

  }
  function subform()
    {
    document.permissionEditForm.submit();
    }

    function setflag(rowIndex)
    {
    document.permissionEditForm.controlFlag.value="newval";
    document.permissionEditForm.rowNum.value=rowIndex;
  }

 // START : #119240 : 22/06/09
 function doOperation(myOperationType)
  {
    document.permissionEditForm.operationType.value = myOperationType;
    document.permissionEditForm.submit();
  }
 // END : #119240 : 22/06/09 
   </script>

<form:form name="permissionEditForm" method="POST" action="edit_permission_list.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="addOrDeletePermission"/>
 <form:hidden path="permissionIndex"/>
 <form:hidden path="permissionCount"/>
 <form:hidden path="rowNum" />
 <form:hidden path="controlFlag"/>
 <form:hidden path="searchForm"/>
  <!-- START : #119240 : 22/06/09 -->
 <input type="hidden" name="operationType" value="" />
 
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>
 <!-- END : #119240 : 22/06/09 -->
 
<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="permissionList"/> </span></a></li>
        
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            
      
      <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="50%"><f:message key="permissionLists"/></th>
                    <th width="21%" >&nbsp;</th>
                    <th width="29%" bgcolor="#ffffff" style="text-align:right">
                    <!-- START : #119240 : 22/06/09 -->
                   <%-- <a href="search_permission_list.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="subform()"/></a> --%>
	                     <a href="#" onClick="javascript:doOperation('searchPermission');">
				            <img src="images/icofindjob.gif" alt="Back to Search Permission" width="16" height="14" border="0" align="absmiddle">
				          </a>&nbsp; 
				  	 <!-- START for ITrack note : 27-Jul-2009 -->
					 <%-- <c:if test="${command.permissionSearch != null}"> --%>
						<c:if test="${command.permissionSearch != null && command.permissionSearch.results != null 
							&& fn:length(command.permissionSearch.results) > 1}">		  
					 <!-- END for ITrack note : 27-Jul-2009 -->
				          <a href="#" onClick="javascript:doOperation('prevPermission');">
				            <img src="images/prevleftarrow.gif" alt="Go to Previous Permission" width="13" height="12" hspace="1px" border="0"/>
				          </a> &nbsp;
				          <a href="#" onClick="javascript:doOperation('nextPermission');">
				            <img src="images/nextrtarrow.gif" alt="Go to Next Permission" width="13" height="12" hspace="1px" border="0"/>
				          </a> &nbsp;
					</c:if>
				          <a href="#"  onClick="javascript:doOperation('savePermission');">
					         <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
					      </a>
					 <!-- END : #119240 :22/06/09 -->     
                    </th>
                  </tr>
                  <tr bgcolor=#ffffff>
                   <td class="TDShade"><f:message key="permission"/>:<span class="TDShadeBlue"> 
           ${command.permission.name}</td>
                     </span></td>
                      <td class="TDShade" ><f:message key="description"/>:<span class="TDShadeBlue">
                       <form:input cssClass="inputBox" size="35"  maxlength="256" path="permission.description" />
                      <form:errors path="permission.description" cssClass="redstar"/>
            
                     </span></td>
                     <td bgcolor="#ffffff" class="TDShade" style="text-align:right">&nbsp;</td>
                  </tr>

                  <tr>
                     <td colspan="3" style="padding:2px;">
           
           <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
           <tr>
           <th width="25"><f:message key="no"/>.</th>
           <th width="20%"><f:message key="control"/></th>
					 <th width="20%"><f:message key="URL"/></th>
					 <th width="50%"><f:message key="controlDescription"/></th>
           <th width="50"  style="text-align:right" colspan="2">
           <img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onPermissionAdd()"/>&nbsp;&nbsp;</th>
          </tr>
            <c:forEach items="${command.links}" var="links" varStatus="counter">
        <tr>
            <td class="TDShadeBlue">${counter.index+1}<strong>.</strong></td>   
           <td class="TDShadeBlue" nowrap>
           <form:input cssClass="inputBox" size="30"  maxlength="256" path="links[${counter.index}].name"/>
           <form:errors path="links[${counter.index}].name" cssClass="redstar"/>
           <a href="#" onClick="javascript:setflag(${counter.index});popup_show('permissionlist${counter.index}', 'permissionlist_drag${counter.index}', 'permissionlist_exit${counter.index}', 'screen-corner', 120, 20);popupboxenable();">
           <img src=" images/lookup.gif" alt="lookup links" width="13" height="13" border="0"/></a></td>
                    
           <td class="TDShadeBlue"><form:input cssClass="inputBox" size="40" path="links[${counter.index}].url"/>
           <form:errors path="links[${counter.index}].url" cssClass="redstar"/></td> 
         				 	<td class="TDShadeBlue"><form:input cssClass="inputBox" size="60" path="links[${counter.index}].description"/>
				   <form:errors path="links[${counter.index}].description" cssClass="redstar"/></td> 
           <td class="TDShadeBlue" style="text-align:right;"><div id="div3" style="width:50px; text-align:center; margin-right:0;">   &nbsp;<img src=" images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0"  onclick="onPermissionDelete('${counter.index}')"/></a></div></td>
        </tr>

        <ajax:autocomplete
                  baseUrl="createuser.htm"
                  source="links[${counter.index}].name"
                  target="links[${counter.index}].url"
                  className="autocomplete"
                  parameters="linkName={links[${counter.index}].name}"
                  minimumCharacters="1"
                 />
                 
<!-- --------------------------- PermissionList Lookup ------------------------------------------------- -->
  <div class="sample_popup" id="permissionlist${counter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="permissionlist_drag${counter.index}" style="width:750px;height:auto; "> 
  <img class="menu_form_exit"   id="permissionlist_exit${counter.index}" src=" images/form_exit.png" />
   &nbsp;&nbsp;&nbsp;<f:message key="permissionLists"/></div>
  <div class="menu_form_body" id="permissionlistbody${counter.index}"   style="width:750px; height:500px;overflow-y:hidden;">
  <iframe align="left" frameborder="0" style="padding:0px;" height="500px;" width="100%"     src="search_control_popup.htm?inputFieldId=links[${counter.index}].name&rowNum=${counter.index}&searchForm=permissionEditForm" 
  scrolling="auto" id="searchPermissionListFr" name="searchPermissionListFr" allowtransparency="yes" ></iframe>   
  </div>
  </div>
<!-- --------------------------------- PermissionList Lookup END ----------------------------------------- -->

        </c:forEach>
      </table>
            </td>
                  </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right">
                         <!-- START : #119240 : 22/06/09 -->
	  		                 <%--  <a href="search_permission_list.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="subform()"/></a> --%>
		                     <a href="#" onClick="javascript:doOperation('searchPermission');">
					            <img src="images/icofindjob.gif" alt="Back to Search Permission" width="16" height="14" border="0" align="absmiddle">
					          </a>&nbsp; 
						 <!-- START for ITrack note : 27-Jul-2009 -->	  
						<%-- <c:if test="${command.permissionSearch != null}"> --%>
						<c:if test="${command.permissionSearch != null && command.permissionSearch.results != null 
								&& fn:length(command.permissionSearch.results) > 1}">
						 <!-- END for ITrack note : 27-Jul-2009 -->
					          <a href="#" onClick="javascript:doOperation('prevPermission');">
					            <img src="images/prevleftarrow.gif" alt="Go to Previous Permission" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
					          <a href="#" onClick="javascript:doOperation('nextPermission');">
					            <img src="images/nextrtarrow.gif" alt="Go to Next Permission" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
						</c:if>
					          <a href="#"  onClick="javascript:doOperation('savePermission');">
						         <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
						      </a>
						 <!-- END : #119240 :22/06/09 -->
                        </td>
                      </tr>
                    </table>
          </td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
     </div>
        </div>
        <script type="text/javascript">
    dolphintabs.init("tabnav", 0)
    </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>
  






