<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<form:form name="towingCompanyEditForm" method="POST" action="edit_towing_company.htm">
<form:hidden path="id" />

<div style="color:red;">
  <form:errors cssClass="error"/>
</div>

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="towingCompany"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table border="0" align="center" cellpadding="1" cellspacing="0" class="mainApplTable">
      <tr>
      <th colspan="2" width="50%">
      <authz:authorize ifAnyGranted="CreateTowingCompany">
      <f:message key="editTowingCompany"/>
      </authz:authorize>
      <authz:authorize ifNotGranted="CreateTowingCompany">
      <f:message key="viewTowingCompany"/>
      </authz:authorize>
      </th>
      <th colspan="2" width="45%">
        <strong><f:message key="status"/>:
          <form:select cssClass="selectionBox" path="status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>
        </strong></th>
      <th width="15%"><a href="search_towing_company.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp
	<authz:authorize ifAnyGranted="CreateTowingCompany">
	<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" align="absmiddle"></a>
	</authz:authorize>
	</th>
     </tr>
     <tr>
      <td width="20%" class="TDShade"><strong><f:message key="towingCompanyID"/>:</td>
      <td  class="TDShadeBlue"> ${command.id}</td>
      
      <td width="20%" class="TDShade"><strong><f:message key="towingCompany"/>:<span class="redstar">*</span></strong></td>
     
        <td width="30%" colspan="2" class="TDShadeBlue">
      <form:input cssClass="inputBox" size="25" maxlength="128" path="name" />
      <form:errors path="name" cssClass="redstar"/>
        </td>

     
    </tr>
    
    
    <tr class="InnerApplTable">
                  <td width="20%" class="TDShade"><f:message key="country"/><strong> :<span class="redstar">*</span></strong></td>
                  <td colspan="1" class="TDShadeBlue">
				  <form:select cssClass="selectionBox" path="countryCode" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />
					       <form:errors path="countryCode" cssClass="redstar"/>
				 </td>
			  
                   <td class="TDShade"><f:message key="state"/>:</td>
                    <td colspan="2" class="TDShadeBlue">
					<icb:list var="countryCodeList">
						<icb:item>${command.countryCode}</icb:item>
					</icb:list>	
					<form:select cssClass="selectionBox" path="stateCode" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" />
				    <form:errors path="stateCode" cssClass="redstar"/>
					</td>
					<ajax:select
					baseUrl="country.htm"
					source="countryCode"
					target="stateCode"
					parameters="country.countryCode={countryCode}"
					parser="new ResponseXmlParser()" />
				</tr> 
  
   <tr>
    <td class="TDShade"><strong><f:message key="city"/>:</strong></td>
    <td class="TDShadeBlue"><label>
      <form:input cssClass="inputBox" size="25" maxlength="64" path="city" />
      <form:errors path="city" cssClass="redstar"/>
    </label></td>
    <td class="TDShade"><strong><f:message key="telephoneNo"/> :</strong></td>
    <td colspan="2" class="TDShadeBlue"> <form:input cssClass="inputBox" maxlength="32" size="25" path="phone" />
        <form:errors path="phone" cssClass="redstar"/></td>
  </tr>
  
  <tr>
    <td class="TDShade"><strong><f:message key="emailAddress"/>:</strong> </td>
    <td class="TDShadeBlue"> <form:input cssClass="inputBox" maxlength="70" size="25" path="email" />
        <form:errors path="email" cssClass="redstar"/></td>
    <td class="TDShadeBlue">&nbsp;</td>
    <td colspan="2" class="TDShadeBlue">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
  <tr>
    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
       <td><strong><span class="redstar">*</span></strong> <span
						class="Font11pt"><f:message key="markedfiledsaremdtry" /></span></td>
        <td style="text-align:right"><a href="search_towing_company.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
        <authz:authorize ifAnyGranted="CreateTowingCompany">
        <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0"></a>
        </authz:authorize>
        </td>
      </tr>
    </table></td>
  </tr>
</table>

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
