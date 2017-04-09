
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
	uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="javascript">
	function submitform()
		{
			top.document.forms[0].towingCompFlag.value="towingCompany";
			top.document.forms[0].submit();
			
		}
	  
  </script>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<form:form name="createNewTowingcompanyForm" method="POST"
	action="create_new_towingcompany.htm">
	<div style="color:red;"><form:errors cssClass="error" /></div>
	<form:hidden path="inputFieldId" />

	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
		<tr>
			<td valign="top"><!-- MAIN CONTAINER -->
			<div id="MainContentContainer"><!-- TABS CONTENTS --> <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
			<div id="tab1" class="innercontent">
			<table border="0" align="center" cellpadding="1" cellspacing="0"
				class="mainApplTable">
				<tr>
					<th colspan="8" width="100%"><f:message
						key="createNewTowingCompany" /></th>
				</tr>
				<tr>
					<td width="10%" colspan="1" class="TDShade" nowrap><strong><f:message
						key="towingCompanyId" /> <strong>:</strong></td>
					<td class="TDShadeBlue">${command.towingCompany.id}</td>

					<td width="20%" class="TDShade"><f:message key="status" /><strong>:</strong></td>
					<td colspan="1" class="TDShadeBlue"><form:select id="sel1"
						cssClass="selectionBox" path="towingCompany.status"
						items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name"
						itemValue="value" /> <form:errors path="towingCompany.status"
						cssClass="redstar" /></td>
				</tr>
				<tr>
					<td class="TDShade"><strong nowrap><f:message
						key="towingCompany" />:<span class="redstar">*</span></strong></td>
					<td class="TDShadeBlue"><form:input maxlength="128"
						cssClass="inputBox" path="towingCompany.name" /> <form:errors
						path="towingCompany.name" cssClass="redstar" /></td>
					<td class="TDShade">
					<td class="TDShadeBlue">
				</tr>

				<tr class="InnerApplTable">
					<td width="20%" class="TDShade"><f:message key="country" /><strong>
					:<span class="redstar">*</span></strong></td>
					<td colspan="1" class="TDShadeBlue"><form:select
						cssClass="selectionBox" path="towingCompany.countryCode"
						items="${icbfn:dropdown('country', null)}" itemLabel="name"
						itemValue="value" /> <form:errors
						path="towingCompany.countryCode" cssClass="redstar" /></td>

					<td class="TDShade"><f:message key="state" />:</td>
					<td colspan="2" class="TDShadeBlue"><icb:list
						var="countryCodeList">
						<icb:item>${command.towingCompany.countryCode}</icb:item>
					</icb:list> <form:select cssClass="selectionBox"
						path="towingCompany.stateCode"
						items="${icbfn:dropdown('state', countryCodeList)}"
						itemLabel="name" itemValue="value" /> <form:errors
						path="towingCompany.stateCode" cssClass="redstar" /></td>
					<ajax:select baseUrl="country.htm"
						source="towingCompany.countryCode"
						target="towingCompany.stateCode"
						parameters="country.countryCode={towingCompany.countryCode}"
						parser="new ResponseXmlParser()" />

				</tr>
				<tr>
					<td class="TDShade"><strong><f:message key="city" />:</strong></td>
					<td class="TDShadeBlue"><label> <form:input
						cssClass="inputBox" maxlength="64" path="towingCompany.city" /> <form:errors
						path="towingCompany.city" cssClass="redstar" /> </label></td>
					<td class="TDShade"><strong><f:message
						key="telephoneNo" />:</strong></td>
					<td colspan="2" class="TDShadeBlue"><form:input
						cssClass="inputBox" size="10" maxlength="32" path="towingCompany.phone" />
					<form:errors path="towingCompany.phone" cssClass="redstar" /></td>
				</tr>

				<tr>
					<td class="TDShade"><strong><f:message
						key="emailAddress" />:</strong></td>
					<td class="TDShadeBlue"><form:input maxlength="70"
						cssClass="inputBox" path="towingCompany.email" /> <form:errors
						path="towingCompany.email" cssClass="redstar" /></td>
					<td class="TDShadeBlue">&nbsp;</td>
					<td colspan="2" class="TDShadeBlue">&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="applTableBot">
				<tr>
					<td><input id="ok" type="button" value="OK" name="ok"
						class="button1"	onclick="top.return_popup_search_result('${command.inputFieldId}','${command.towingCompany.id}');top.hidePopupDiv('towingco','towingbody');top.popupboxclose();submitform();" />&nbsp&nbsp<input
						id="cancel" type="button" value="Cancel" name="cancel"
						class="button1"
						onClick="javascript:top.hidePopupDiv('towingco','towingbody');top.popupboxclose();" />
					&nbsp&nbsp<input id="cancel2" type="submit" value="Apply"
						name="cancel2" class="button1" /></td>
						<td>
						</td>
						<td><strong><span class="redstar">*</span></strong> <span
						class="Font11pt"><f:message key="markedfiledsaremdtry" /></span></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	</div>
	<!----------------- TAB 1 CONTAINER END ------------------------------ -->
	</div>
	</div>
	<%-- <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", 0)
      
      </script> --%>
	<!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
	</div>
	<!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
	</td>
	</tr>
	</table>
</form:form>



