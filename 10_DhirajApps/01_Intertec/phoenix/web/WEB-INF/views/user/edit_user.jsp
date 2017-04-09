<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- START : #119240 -->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- END : #119240 -->
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script language="javascript">
function employeeIdPostAjax(){
	var text=document.getElementById('employeeFullName').value;
	var values = text.split('|');
	document.getElementById('employeeFullName').value = values[0].trim();
	document.getElementById('user.employeeId').value = values[1].trim();
}


function showUserEmployeeSearch(){
	   document.getElementById('searchEmployeeFrame').setAttribute("src",src="phx_search.htm?searchType=employee&targetFieldId=employeeFullName|user.employeeId&div1=employeeDetails&div2=employeeDetailsbody&searchForm=createJobsCEForm&sortBy=lastName,firstName");
}

function popEmployeeDetails(){
	  popup_show('employeeDetails','employeeDetails_drag','employeeDetails_exit', 'screen-corner', 120,  20);
	  hideIt();	
	  showPopupDiv('employeeDetails','employeeDetailsbody');
	  popupboxenable();
}

  function onRoleAdd()
  {
      document.userEditForm.addOrDeleteRole.value = "add";
    document.userEditForm.tabName.value = "1";
    document.userEditForm.submit();
  }

  function onRoleDelete(index)
  {
    document.userEditForm.addOrDeleteRole.value = "delete";
    document.userEditForm.roleIndex.value = index;
    document.userEditForm.tabName.value = "1";
    document.userEditForm.submit();
    }
   function setflag(rowIndex)
   {   
    document.userEditForm.userRoleFlag.value="newval";
    document.userEditForm.rowNum.value=rowIndex;
   }
   function onBranchChange(){  
   document.userEditForm.timeFlag.value="branchChange"; 
   document.userEditForm.submit();
   }
   function addNew(){
    document.userEditForm.addFlag.value="addNew";
   }

/*function updateBranchIframeSrc()
  {
  var buName=document.getElementById("sel8").value;
   document.userEditForm.timeFlag.value="branchChange"; 
  if(buName!= "" && buName!= null)
  {    document.getElementById('searchBranchCodeFr').setAttribute("src","search_branch_popup.htm?inputFieldId=user.branchName&div1=branchcode&div2=branchcodebody&buName="+buName+"&formName=userEditForm");
  }  
}


function makeBranchblank()
{
  document.getElementById("sel4").value="";
}*/

 // START : #119240 : 19/06/09
 function doOperation(myOperationType)
  {
    document.userEditForm.operationType.value = myOperationType;
    document.userEditForm.submit();
  }
 // END : #119240 : 19/06/09 
 
</script>

<icb:list var="divisions">
<icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
 <%--<icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>--%>
<icb:item>${command.user.businessUnit.organization.name}</icb:item>
  <icb:item>${command.user.branch.businessUnit.name}</icb:item>
</icb:list>

<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<icb:list var="workFunction">
  <icb:item>workFunction</icb:item>
</icb:list>
<icb:list var="employeeType">
  <icb:item>employeeType</icb:item>
</icb:list>
<icb:list var="regularTemp">
  <icb:item>regularTemp</icb:item>
</icb:list>
<icb:list var="fullPartTime">
  <icb:item>fullPartTime</icb:item>
</icb:list>
<icb:list var="employeeStatus">
  <icb:item>employeeStatus</icb:item>
</icb:list>

<icb:list var="jobType">
  <icb:item>jobType</icb:item>
</icb:list>
<icb:list var="dateFormat">
<icb:item>dateFormat</icb:item>
</icb:list>

<icb:list var="timeFormat">
<icb:item>timeFormat</icb:item>
</icb:list>
<icb:list var="selectedLanguage">
  <icb:item>selectedLanguage</icb:item>
</icb:list>

<icb:list var="productType">
  <icb:item>productType</icb:item>
</icb:list>

<form:form name="userEditForm" method="POST" action="edit_user.htm">

<div style="color:red;">
  <form:errors cssClass="error"/>
</div>

 <form:hidden path="addOrDeleteRole"/>
 <form:hidden path="roleIndex"/>
 <form:hidden path="roleCount"/>
 <form:hidden path="tabName" />
 <form:hidden path="userRoleFlag"/>
  <form:hidden path="rowNum"/>
 <form:hidden path="timeFlag"/>
  <form:hidden path="addFlag"/>
<!-- START : #119240 : 19/06/09 -->
 <input type="hidden" name="operationType" value="" />
 
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>
 <!-- END : #119240 : 19/06/09 -->

<c:set var="disableFlag" value="true" scope="request"/>
<authz:authorize ifAnyGranted="CreateUser">
<c:set var="disableFlag" value="false"/>
</authz:authorize>  
<authz:authorize ifNotGranted="CreateUser">
<c:set var="disableFlag" value="true"/>
</authz:authorize> 

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span>Edit User </span></a></li>
              <authz:authorize ifAnyGranted="CreateUser,ViewRole">
              <li><a href="#" rel="tab2"><span>User Roles </span></a></li>
              </authz:authorize>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=3 width="55%"><img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> <f:message key="userId"/> : ${command.user.loginName}</th> 
                    <th width="20%" ><f:message key="status"/>:                        
                      <form:select id="sel1" cssClass="selectionBox" path="user.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>
                    </th>
                    <th  width="25%" bgcolor="#ffffff" style="text-align:right">
						<c:if test="${disableFlag=='false'}">
							<a href="#"><input type="image" src="images/icoadddoc.gif" alt="Save and addNew" width="14"  height="14" border="0" onClick="addNew();"/></a>&nbsp;
						</c:if>						
					
						<!-- START : #119240 : 19/06/09 -->
						<%-- <a href="search_user.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp;
							<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14"  height="14" border="0" /></a> --%>
					

						      <a href="#" onClick="javascript:doOperation('searchUser');">
					            <img src="images/icofindjob.gif" alt="Back to Search User" width="16" height="14" border="0" align="absmiddle">
					          </a>&nbsp; 
							<!-- START for ITrack note : 27-Jul-2009 -->
							<%-- <c:if test="${command.userSearch != null}"> --%>
							<c:if test="${command.userSearch != null && command.userSearch.results != null 
								&& fn:length(command.userSearch.results) > 1}">
							<!-- END for ITrack note : 27-Jul-2009 -->
					          <a href="#" onClick="javascript:doOperation('prevUser');">
					            <img src="images/prevleftarrow.gif" alt="Go to Previous User" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
					          <a href="#" onClick="javascript:doOperation('nextUser');">
					            <img src="images/nextrtarrow.gif" alt="Go to Next User" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;							
						 </c:if> 

						  	<%-- <c:if test="${not command.viewOnly}"> --%>
					          <a href="#"  onClick="javascript:doOperation('saveUser');">
					            <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
					          </a>
					         <%-- </c:if> --%>
						<!-- END : #119240 : 19/06/09 -->	
					</th>
                  </tr>
                  <tr>
                    <td width="15%" class="TDShade"><strong><f:message key="userName"/><span class="redstar">*</span></strong></td>
                    <td width="35%" class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="128" path="user.loginName"  disabled="${disableFlag}"/>
                      <form:errors path="user.loginName" cssClass="redstar"/></td>

                    <td width="15%" class="TDShade"><strong><f:message key="employeeName"/>:</strong></td>
                    <td colspan="2" width="35%" class="TDShadeBlue">
          <form:input cssClass="inputBoxBlue" size="35" maxlength="11" path="employeeFullName" disabled="${disableFlag}"/>
          <form:errors path="user.employeeId" cssClass="redstar"/>
			  <form:hidden id="user.employeeId" path="user.employeeId" />
              <form:errors path="user.employeeId" cssClass="redstar"/>
			  <c:if test="${disableFlag=='false'}">
			  <a href="#" onClick="javascript:showUserEmployeeSearch();popEmployeeDetails();"><img
							src=" images/lookup.gif" alt="Lookup Employee" width="13"
							height="13" border="0" /></a>
				</c:if>
				</td>
                  </tr>
                  <tr>
                    <td class="TDShade"><strong><f:message key="password"/></strong></td>
                    <td class="TDShadeBlue">
                      <form:password cssClass="inputBox" size="35" maxlength="128" path="password" showPassword="true" disabled="${disableFlag}"/>
                      <form:errors path="password" cssClass="redstar"/>
                    </td>
                    <td class="TDShade"><strong>  <f:message key="confirmedPassword"/></strong></td>
                    <td colspan="2" class="TDShadeBlue">
                      <form:password cssClass="inputBox" size="35" maxlength="128" path="passwordConfirmation" showPassword="true" disabled="${disableFlag}"/>
                      <form:errors path="passwordConfirmation" cssClass="redstar"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShade"><strong><f:message key="firstName"/>:</strong></td>
                    <td class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="128" path="user.firstName" disabled="${disableFlag}"/>
                      <form:errors path="user.firstName" cssClass="redstar"/>
                    </td>
                    <td class="TDShade"><strong><f:message key="middleName"/>:</strong></td>
                    <td colspan="2" class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="128" path="user.middleName" disabled="${disableFlag}"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShade" style="border-bottom:#CCCCCC dashed 1px;">
          <f:message key="lastName"/>: </td>
                    <td class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px;">
                      <form:input cssClass="inputBox" size="35" maxlength="128" path="user.lastName" disabled="${disableFlag}"/>
                      <form:errors path="user.lastName" cssClass="redstar"/>
                    </td>
                    <td class="TDShade" style="border-bottom:#CCCCCC dashed 1px;"><f:message key="email"/>:</td>
                    <td colspan="2" class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px;">
                      <form:input cssClass="inputBox" size="35" maxlength="70" path="user.email" disabled="${disableFlag}"/>
                      <form:errors path="user.email" cssClass="redstar"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="workerFunction"/>: </td>
                    <td class="TDShadeBlue"><form:select id="sel2" cssClass="selectionBox" path="user.workFunction" items="${icbfn:dropdown('staticDropdown',workFunction)}" itemLabel="name" itemValue="value" disabled="${disableFlag}"/></td>
                <td class="TDShade"><f:message key="jobCode"/>:<span class="redstar">*</span></td>
                    <td colspan="2" class="TDShadeBlue"><form:input cssClass="inputBox" size="35" maxlength="8" path="user.jobCodeValue" disabled="${disableFlag}"/>
                  <form:errors path="user.jobCodeValue" cssClass="redstar"/>
					<c:if test="${disableFlag=='false'}">
                    <a href="#" onClick="javascript:popup_show('jobcode', 'jobcode_drag', 'jobcode_exit', 'screen-corner', 120, 20);
          hideIt();showjobcode();popupboxenable();"><img src="images/lookup.gif" alt="lookup Job Code" width="13" height="13" border="0"></a>
          </c:if>
          </td>
                  </tr>
                 
			<tr>
				<td class="TDShade"><f:message key="businessUnitName"/>:<span class="redstar">*</span></td>
				<td class="TDShadeBlue">
				<form:select cssClass="selectionBox" id="sel8" path="user.buName" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value" disabled="${disableFlag}" onchange="makeBranchblank()"/>
				<form:errors path="user.buName" cssClass="error"/>
				</td>
				<td class="TDShade"><f:message key="branchCode"/>:<span class="redstar">*</span></td>
				<td colspan="2" class="TDShadeBlue">

				<form:select id="sel4" cssClass="selectionBox" path="user.branchName" items="${icbfn:dropdown('branch', divisionBu)}" itemLabel="name" itemValue="value" onchange="onBranchChange()" disabled="${disableFlag}"/>
				<form:errors path="user.branchName" cssClass="error"/>

				<%--<form:input id="sel4" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" cssClass="inputBox" size="40" path="user.branchName" disabled="${disableFlag}"/>
				<c:if test="${disableFlag=='false'}">
				<a href="#" onClick="javascript:updateBranchIframeSrc();popup_show('branchcode', 'branchcode_drag', 'branchcode_exit', 'screen-corner', 120, 20);hideIt();showbranchcode();popupboxenable();">
				<img src="images/lookup.gif" alt="lookup country" width="13" height="13" border="0"></a>
				 </c:if>
				<form:errors path="user.branchName" cssClass="error"/>--%>
				</td>
			</tr>

                  <tr>
                    <td class="TDShade" style="border-bottom:#CCCCCC dashed 1px;"><f:message key="supervisorId"/>:</td>
                <td class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px;">
           <form:input id="superVisor" cssClass="inputBox" size="35" maxlength="19" path="user.supervisorName" disabled="${disableFlag}"/>
            <form:errors path="user.supervisorName" cssClass="redstar"/>
            <c:if test="${disableFlag=='false'}">
                   <a href="#" onClick="javascript:popup_show('supervisorid', 'supervisorid_drag', 'supervisorid_exit', 'screen-corner', 120, 20); hideIt();showsupervisorid();popupboxenable();"><img src="images/lookup.gif" alt="lookup Supervisor Id" width="13" height="13" border="0"></a>
                   </c:if>
                   </td>
                   <td class="TDShade" style="border-bottom:#CCCCCC dashed 1px;">&nbsp;</td>
                    <td colspan="2" class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px;">&nbsp;
                    </td>
          </tr>
                  <tr>
                    <td class="TDShade"><f:message key="employeeType"/>:</td>
                    <td class="TDShadeBlue"><form:select id="sel3" cssClass="selectionBox" path="user.employeeType" items="${icbfn:dropdown('staticDropdown',employeeType)}" itemLabel="name" itemValue="value" disabled="${disableFlag}"/></td>
                   
          <td class="TDShade"><f:message key="reg"/></td>
                    <td colspan="2" class="TDShadeBlue"><form:select id="sel4" cssClass="selectionBox" path="user.regularTemp" items="${icbfn:dropdown('staticDropdown',regularTemp)}" itemLabel="name" itemValue="value" disabled="${disableFlag}"/></td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="fullorPart"/>:</td>
                    <td class="TDShadeBlue"><form:select id="sel5" cssClass="selectionBox" path="user.fullPartTime" items="${icbfn:dropdown('staticDropdown',fullPartTime)}" itemLabel="name" itemValue="value" disabled="${disableFlag}"/></td>
                    
          <td class="TDShade"><f:message key="approver"/></td>
                    <td colspan="2" class="TDShadeBlue"><form:checkbox  path="user.isApprover" value="0" disabled="${disableFlag}"/></td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="employeeStatus"/>:</td>
                    <td class="TDShadeBlue"><form:select id="sel6" cssClass="selectionBox" path="user.employeeStatus" items="${icbfn:dropdown('staticDropdown',employeeStatus)}" itemLabel="name" itemValue="value" disabled="${disableFlag}"/></td>
                                    
                    <td class="TDShade"><f:message key="defaultCountry"/>:</td>
                    <td colspan="2" class="TDShadeBlue"><form:select id="sel7" cssClass="selectionBox" path="user.countryCode" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" disabled="${disableFlag}"/>
          <form:errors path="user.countryCode" cssClass="redstar"/>
                      <a href="#" onClick="javascript:popup_show('country', 'country_drag', 'country_exit', 'screen-corner', 120, 20);showcountryframe();hideIt()"></td>
                     </tr>
                  
 
          
 
<tr>
<td class="TDShade"><f:message key="dateFormat" />: </td>
      <td class="TDShadeBlue">
      <form:select cssClass="selectionBox" id="sel11" path="user.dateFormat" items="${icbfn:dropdown('staticDropdown',dateFormat)}" itemLabel="name" itemValue="value"/><form:errors path="user.dateFormat" cssClass="error"/></td>


<td class="TDShade"><f:message key="timeFormat" />: </td>
      <td colspan="2" class="TDShadeBlue">
      <form:select cssClass="selectionBox" id="sel11" path="user.timeFormat" items="${icbfn:dropdown('staticDropdown',timeFormat)}" itemLabel="name" itemValue="value"/><form:errors path="user.timeFormat" cssClass="error"/></td>
</tr>
<tr>
<td class="TDShade" nowrap><f:message key="selectLanguage" />: </td>
<td class="TDShadeBlue">
<form:select cssClass="selectionBox" id="sel11" path="user.language" items="${icbfn:dropdown('staticDropdown',selectedLanguage)}" itemLabel="name" itemValue="value"/><form:errors path="user.language" cssClass="error"/></td>
                    <td class="TDShade"><f:message key="currency"/>:<span class="redstar">*</span></td>
                    <td class="TDShadeBlue">        
        <form:select id="sel10" cssClass="selectionBox" path="user.currencyCd" items="${icbfn:dropdown('currency', null)}" itemLabel="name" itemValue="value" />  <form:errors path="user.currencyCd" cssClass="redstar"/></td>
</tr>
<tr>
<td class="TDShade"><f:message key="productType"/></td>
<td class="TDShadeBlue"><form:select id="sel12" cssClass="selectionBox" path="user.productType" items="${icbfn:dropdown('staticDropdown', productType)}" itemLabel="name" itemValue="value"/></td>
<td class="TDShade"><f:message key="jobType" />: </td>
      <td colspan="2" class="TDShadeBlue">
        <form:select cssClass="selectionBox" id="sel11" path="user.jobType" items="${icbfn:dropdown('staticDropdown',jobType)}" itemLabel="name" itemValue="value"/><form:errors path="user.jobType" cssClass="error"/>
      </td>
</tr>
<tr>
<td class="TDShade" style="border-bottom:#CCCCCC dashed 1px;"><f:message key="timeZone"/></td>
                    <td  class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px;">
          <form:input size="10" cssClass="inputBox" path="user.preferredTz" /> 
          <form:errors path="user.preferredTz" cssClass="redstar" />
          <a href="#" onClick="javascript:popup_show('timezone','timezone_drag','timezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('timezone','timezonebody');popupboxenable();">
          <img src=" images/lookup.gif" width="13" alt="lookup Time Zone" height="13" border="0" /></a>
                    </td>
<td class="TDShade"><f:message key="URL" />: </td>
      <td colspan="2" class="TDShadeBlue">
           <form:input cssClass="inputBox" size="35"  maxlength="256" path="user.url"/>
           <form:errors path="user.url" cssClass="redstar"/>
           <a href="#" onClick="javascript:popupSearchLink('user.url');">
           <img src=" images/lookup.gif" alt="lookup links" width="13" height="13" border="0"/></a></td>                    
</tr>



                </tbody>
              </table>
               <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry" /></span> </td>
                        <td style="text-align:right">
							<c:if test="${disableFlag=='false'}">
								<a href="#"><input type="image" src="images/icoadddoc.gif" alt="Save and addNew" width="14"  height="14" border="0" onClick="addNew();"/></a>&nbsp;
							</c:if>
							<!-- START : #119240 : 19/06/09 -->
							<%-- <a href="search_user.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp;
								<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14"  height="14" border="0" /></a> --%>
								
					          <a href="#" onClick="javascript:doOperation('searchUser');">
					            <img src="images/icofindjob.gif" alt="Back to Search User" width="16" height="14" border="0" align="absmiddle">
					          </a>&nbsp; 
							 <!-- START for ITrack note : 27-Jul-2009 -->
							 <%-- <c:if test="${command.userSearch != null}"> --%>
							  <c:if test="${command.userSearch != null && command.userSearch.results != null 
									&& fn:length(command.userSearch.results) > 1}">
							<!-- END for ITrack note : 27-Jul-2009 -->
					          <a href="#" onClick="javascript:doOperation('prevUser');">
					            <img src="images/prevleftarrow.gif" alt="Go to Previous User" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
					          <a href="#" onClick="javascript:doOperation('nextUser');">
					            <img src="images/nextrtarrow.gif" alt="Go to Next User" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
					  	 </c:if> 
						  	
					          <a href="#"  onClick="javascript:doOperation('saveUser');">
					            <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
					          </a>
					        
							<!-- END : #119240 : 19/06/09 -->
							</td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
    <ajax:autocomplete
  baseUrl="createuser.htm"
  source="user.jobCodeValue"
  target="user.jobCodeValue"
  className="autocomplete"
  parameters="jobCode={user.jobCodeValue}"
  minimumCharacters="1"
   /> 
  <ajax:autocomplete
  baseUrl="createuser.htm"
  source="user.supervisorName"
  target="user.supervisorName"
  className="autocomplete"
  parameters="supervisorId={user.supervisorName}"
  minimumCharacters="1"
   /> 

 <ajax:autocomplete 
  baseUrl="joborder.htm"
  source="user.preferredTz" target="user.preferredTz"
  className="autocomplete" 
  parameters="etaTimeZone={user.preferredTz}"
  minimumCharacters="1" /> 

  <%-- <ajax:autocomplete
  baseUrl="business_unit.htm"
  source="user.branchName"
  target="user.branchName"
  className="autocomplete"              
  parameters="branchName={user.branchName},buName={user.buName}"
  postFunction="onBranchChange"
  minimumCharacters="3"
 />--%>

<!-----------------------------------------TAB 1 CONTAINER END----------------------------------------------------->
<!-----------------------------------------TAB 2 CONTAINER--------------------------------------------------------->
            <div id="tab2" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="65%"><f:message key="user"/><img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> User ID: ${command.user.loginName}</th>
                     <th  width="25%" bgcolor="#ffffff" style="text-align:right">
						<c:if test="${disableFlag=='false'}">
							<a href="#"><input type="image" src="images/icoadddoc.gif" alt="Save and addNew" width="14"  height="14" border="0" onClick="addNew();"/></a>&nbsp;
						</c:if>
						<!-- START : #119240 : 19/06/09 -->
						<%-- <a href="search_user.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp; --%>

					          <a href="#" onClick="javascript:doOperation('searchUser');">
					            <img src="images/icofindjob.gif" alt="Back to Search User" width="16" height="14" border="0" align="absmiddle">
					          </a>&nbsp; 
						 <!-- START for ITrack note : 27-Jul-2009 -->
							 <%-- <c:if test="${command.userSearch != null}"> --%>
							<c:if test="${command.userSearch != null && command.userSearch.results != null 
							&& fn:length(command.userSearch.results) > 1}">
							<!-- END for ITrack note : 27-Jul-2009 -->
					          <a href="#" onClick="javascript:doOperation('prevUser');">
					            <img src="images/prevleftarrow.gif" alt="Go to Previous User" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
					          <a href="#" onClick="javascript:doOperation('nextUser');">
					            <img src="images/nextrtarrow.gif" alt="Go to Next User" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
						  	</c:if> 

					        <%-- <c:if test="${disableFlag=='false'}">
								<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14"  height="14" border="0" /></a>
							</c:if> --%>
							
							<c:if test="${disableFlag=='false'}">
							 <a href="#"  onClick="javascript:doOperation('saveUser');">
					            <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
					          </a>
							</c:if>
						<!-- END : #119240 : 19/06/09 -->
							
						
					</th>
                  </tr>
                  <tr>
                    <td colspan="3" style="padding:2px;">
          
          <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
          <tr>
            <th width="25"><f:message key="no"/></th>
            <th width="45%"><f:message key="role"/></th>
            <th width="45%"><f:message key="description"/></th>
             <th width="60" style="text-align:right" colspan="2">
				<c:if test="${disableFlag=='false'}">
				<img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="onRoleAdd('0')"/>
				</c:if>&nbsp;&nbsp;&nbsp;
			</th>
          </tr>

    <c:forEach items="${command.roles}" var="roles" varStatus="counter">

           <tr>
            <td class="TDShadeBlue">${counter.index+1}</td>
            <td class="TDShadeBlue"><form:input cssClass="inputBox" size="15" maxlength="128" path="roles[${counter.index}].name"  disabled="${disableFlag}"/>
      <form:errors path="roles[${counter.index}].name" cssClass="redstar"/>
        <a href="#" onClick="javascript:setflag(${counter.index});popup_show('userrole${counter.index}', 'userrole_drag${counter.index}', 'userrole_exit${counter.index}', 'screen-corner', 120, 20);popupboxenable();">
         <img src=" images/lookup.gif" alt="lookup Role" width="13" height="13" border="0"/></a> </td>
           <td class="TDShadeBlue"><form:input cssClass="inputBox" size="15"  maxlength="25" path="roles[${counter.index}].roleDesc" disabled="${disableFlag}"/>
      <form:errors path="roles[${counter.index}].roleDesc" cssClass="redstar"/></td><td>&nbsp;</td>
      <td class="TDShadeBlue"><div id="tablefunction" style="width:50px; text-align:center; margin-right:0;">
		<c:if test="${disableFlag=='false'}">
		<a href="#"><img src="images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0" onclick="onRoleDelete('${counter.index}')"/></a> </div>
		</c:if>
	</td>
          
        
        <ajax:autocomplete
          baseUrl="createuser.htm"
          source="roles[${counter.index}].name"
          target="roles[${counter.index}].roleDesc"
          className="autocomplete"
          parameters="name={roles[${counter.index}].name}"
          minimumCharacters="1"
           /> 
<!------------------------------------------USER Roles Lookup------------------------------------------------------>
<div class="sample_popup" id="userrole${counter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="userrole_drag${counter.index}" style="width:750px;height:auto; "> <img class="menu_form_exit"   id="userrole_exit${counter.index}" src=" images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="userRoles"/></div>
  <div class="menu_form_body" id="userrolebody${counter.index}"   style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_user_roles_popup.htm?inputFieldId=roles[${counter.index}].name&rowNum=${counter.index}&searchForm=userEditForm" scrolling="auto" id="searchUserRoleFr" name="searchUserRoleFr" allowtransparency="yes" ></iframe>
  </div>
</div>

<!-----------------------------------------USER Roles  Lookup END-------------------------------------------------->
    </c:forEach>
      
      
      </tr>
            </table>
               </td>
                 </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry" /></span> </td>
                        <td style="text-align:right">
							<c:if test="${disableFlag=='false'}">
								<a href="#"><input type="image" src="images/icoadddoc.gif" alt="Save and addNew" width="14"  height="14" border="0" onClick="addNew();"/></a>&nbsp;
							</c:if>
							<!-- START : #119240 : 19/06/09 -->
							<%--	<a href="search_user.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp; --%>
								 
						          <a href="#" onClick="javascript:doOperation('searchUser');">
						            <img src="images/icofindjob.gif" alt="Back to Search User" width="16" height="14" border="0" align="absmiddle">
						          </a>&nbsp; 
								 <!-- START for ITrack note : 27-Jul-2009 -->
								 <%-- <c:if test="${command.userSearch != null}"> --%>
									<c:if test="${command.userSearch != null && command.userSearch.results != null 
									&& fn:length(command.userSearch.results) > 1}">
								<!-- END for ITrack note : 27-Jul-2009 -->
						          <a href="#" onClick="javascript:doOperation('prevUser');">
						            <img src="images/prevleftarrow.gif" alt="Go to Previous User" width="13" height="12" hspace="1px" border="0"/>
						          </a> &nbsp;
						          <a href="#" onClick="javascript:doOperation('nextUser');">
						            <img src="images/nextrtarrow.gif" alt="Go to Next User" width="13" height="12" hspace="1px" border="0"/>
						          </a> &nbsp;
							  </c:if>	
							<%-- <c:if test="${disableFlag=='false'}">
									<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14"  height="14" border="0" /></a>
								</c:if>	--%>  
		
							<c:if test="${disableFlag=='false'}">
							    <a href="#"  onClick="javascript:doOperation('saveUser');">
						            <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
						          </a>
						      </c:if>		      
							<!-- END : #119240 : 19/06/09 -->
							
							
		    
						</td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 2 CONTAINER END ------------------------------ -->
      
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
<!-- --------------------------- Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="branchcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="branchcode_drag" style="width:750px; "> 
    <img class="menu_form_exit"   id="branchcode_exit" src="images/form_exit.png"/>&nbsp;&nbsp;&nbsp;
	  <f:message key="searchBranchCode"/>
        </div>
           <div class="menu_form_body" id="branchcodebody"   style="width:750px; height:550px;overflow-y:hidden;">
             <table width="100%">
              <tr>
              <td valign="middle">
              <iframe align="left" frameborder="0" style="padding:0px;" height="540px;" width="100%" <%--src="search_branch_popup.htm?inputFieldId=user.branchName"--%> scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" ></iframe>
          </td>
       </tr>
     </table>       
  </div>
</div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->

<!-- --------------------------- Job Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="jobcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="jobcode_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="jobcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchJobCode"/></div>
  <div class="menu_form_body" id="jobcodebody"   style="width:750px; height:530px;overflow-y:hidden;">
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
          <td valign="middle"> 
              <iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_job_code_popup.htm?inputFieldId=user.jobCodeValue" scrolling="auto" id="searchJobCodeFr" name="searchJobCodeFr" allowtransparency="yes" ></iframe>
             </td>        
          </tr>
      </table> 
  </div>
</div>
<!-- --------------------------------- Job Code Lookup END ----------------------------------------- -->

 <!-- --------------------------- SupervisorId Lookup ------------------------------------------------- -->
  <div class="sample_popup" id="supervisorid" style="visibility: hidden; display: none;">
   <div class="menu_form_header" id="supervisorid_drag" style="width:750px;height:auto;">
    <img class="menu_form_exit"   id="supervisorid_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;
	  <f:message key="searchSupervisor"/></div>
       <div class="menu_form_body" id="supervisorbody"   style="width:750px; height:530px;overflow-y:hidden;"> 
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
         <tr>
           <td valign="middle"> 
            <iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="search_user_popup.htm?inputFieldId=user.supervisorName&div1=supervisorid&div2=supervisorbody&searchForm=userEditForm" scrolling="auto" id="searchsupervisorIdFr" name="searchsupervisorIdFr" allowtransparency="yes" >
		   </iframe>
         </td>           
        </tr>        
    </table> 
  </div>
</div>
 <!-- --------------------------------- SupervisorId Lookup END ----------------------------------------- -->

<!-- --------------------------------- Timezone Lookup START ----------------------------------------- -->

<div class="sample_popup" id="timezone"
  style="visibility: hidden; display: none;">
<div class="menu_form_header" id="timezone_drag" style="width:750px; ">
<img class="menu_form_exit" id="timezone_exit"
  src=" images/form_exit.png" />&nbsp;&nbsp;&nbsp; <f:message
  key="selectTimeZone" /></div>
<div class="menu_form_body" id="timezonebody"
  style="width:750px; height:530px;overflow-y:hidden;"><iframe align="middle"
  frameborder="0" style="padding:1px; height:530px;" width="100%"
  src="search_timezone_popup.htm?inputFieldId=user.preferredTz&div1=timezone&div2=timezonebody"
  id="frame4" name="frame4" allowtransparency="yes"></iframe></div>
</div>
<!-- --------------------------------- Timezone  Lookup END ----------------------------------------- -->


<div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>

<ajax:select
  baseUrl="business_unit.htm"
  source="user.buName"
  target="user.branchName"
  parameters="branch.businessUnit.name={user.buName}"
  parser="new ResponseXmlParser()"/>

<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="employeeFullName" 
	target="employeeFullName" 
	className="autocomplete" 
	parameters="entity=com.intertek.phoenix.entity.Employee,entityWrapper=com.intertek.phoenix.ajax.EmployeeAjaxWrapper,~firstName={employeeFullName}"
	minimumCharacters="3" 
	postFunction="employeeIdPostAjax"/>				

</form:form>

<!-- --------------------------- PermissionList Lookup ------------------------------------------------- -->
  <div class="sample_popup" id="permissionlist" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="permissionlist_drag" style="width:750px;height:auto; "> 
  <img class="menu_form_exit"   id="permissionlist_exit" src=" images/form_exit.png" />
   &nbsp;&nbsp;&nbsp;<f:message key="Search Link Popup"/></div>
  <div class="menu_form_body" id="permissionlistbody"   style="width:750px; height:500px;overflow-y:hidden;">
  <iframe align="left" frameborder="0" style="padding:0px;" height="500px;" width="100%" src="blank.html"    
  scrolling="auto" id="permissionlistbox" name="permissionlistbox" allowtransparency="yes" ></iframe>   
  </div>
  </div>
<!-- --------------------------------- PermissionList Lookup END ----------------------------------------- -->

<!-- --------------------------------- Employee ----------------------------------------- -->

<div class="sample_popup" id="employeeDetails"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="employeeDetails_drag"
	style="width: 750px;"><img class="menu_form_exit"
	id="employeeDetails_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;
<f:message key="searchEmployee" /></div>
<div class="menu_form_body" id="employeeDetailsbody"
	style="width: 750px; height: 630px; padding-left: 4px; overflow-y: hidden;">
<iframe id="searchEmployeeFrame" align="left" frameborder="0"
	style="padding: 0px;" height="630px" width="100%" scrolling="auto"
	allowtransparency="yes" src="blank.html"> </iframe></div>
</div>
<!-- --------------------------------- End Employee ----------------------------------------- -->
