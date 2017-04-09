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
			top.document.forms[0].shippingAgentFlag.value="shippingAgent";
			top.document.forms[0].submit();
			
		}

   </script>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<form:form name="createNewTowingcompanyForm" method="POST"
	action="create_new_shippingagent.htm">
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
						key="createNewShippingAgent" /></th>
				</tr>
				<tr>
					<td width="10%" colspan="1" class="TDShade" nowrap><strong><strong><f:message
						key="shippingAgentID" /> <strong>:</strong></td>
					<td class="TDShadeBlue">${command.shippingAgent.id}</td>

					<td width="20%" class="TDShade"><f:message key="status" /><strong>:</strong></td>
					<td colspan="1" class="TDShadeBlue"><form:select id="sel1"
						cssClass="selectionBox" path="shippingAgent.status"
						items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name"
						itemValue="value" /> <form:errors path="shippingAgent.status"
						cssClass="redstar" /></td>
				</tr>
				<tr>
					<td class="TDShade"><strong nowrap><f:message
						key="shippingAgent" />:<span class="redstar">*</span></strong></td>
					<td class="TDShadeBlue"><form:input cssClass="inputBox" maxlength="128"
						path="shippingAgent.name" /> <form:errors
						path="shippingAgent.name" cssClass="redstar" /></td>
					<td class="TDShade">
					<td class="TDShadeBlue">
				</tr>

				<tr class="InnerApplTable">
					<td width="20%" class="TDShade"><f:message key="country" /><strong>
					:</strong></td>
					<td colspan="1" class="TDShadeBlue"><form:select
						cssClass="selectionBox" path="shippingAgent.countryCode"
						items="${icbfn:dropdown('country', null)}" itemLabel="name"
						itemValue="value" /> <form:errors
						path="shippingAgent.countryCode" cssClass="redstar" /></td>

					<td class="TDShade"><f:message key="state" />:</td>
					<td colspan="2" class="TDShadeBlue"><icb:list
						var="countryCodeList">
						<icb:item>${command.shippingAgent.countryCode}</icb:item>
					</icb:list> <form:select cssClass="selectionBox"
						path="shippingAgent.stateCode"
						items="${icbfn:dropdown('state', countryCodeList)}"
						itemLabel="name" itemValue="value" /> <form:errors
						path="shippingAgent.stateCode" cssClass="redstar" /></td>
					<ajax:select baseUrl="country.htm"
						source="shippingAgent.countryCode"
						target="shippingAgent.stateCode"
						parameters="country.countryCode={shippingAgent.countryCode}"
						parser="new ResponseXmlParser()" />

				</tr>
				<tr>
					<td class="TDShade"><strong><f:message key="city" />:</strong></td>
					<td class="TDShadeBlue"><label> <form:input maxlength="64"
						cssClass="inputBox" path="shippingAgent.city" /> <form:errors
						path="shippingAgent.city" cssClass="redstar" /> </label></td>
					<td class="TDShade"><strong><f:message
						key="telephoneNo" />:</strong></td>
					<td colspan="2" class="TDShadeBlue"><form:input  maxlength="32"
						cssClass="inputBox" path="shippingAgent.phone" /> <form:errors
						path="shippingAgent.phone" cssClass="redstar" /></td>
				</tr>

				<tr>
					<td class="TDShade"><strong><f:message
						key="emailAddress" />:</strong></td>
					<td class="TDShadeBlue"><form:input cssClass="inputBox"  maxlength="70"
						path="shippingAgent.email" /> <form:errors
						path="shippingAgent.email" cssClass="redstar" /></td>
					<td class="TDShadeBlue">&nbsp;</td>
					<td colspan="2" class="TDShadeBlue">&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="applTableBot">
				<tr>
					<td><input id="ok" type="button" value="OK" name="ok"
						class="button1"
						onclick="javascript:top.return_popup_search_result('${command.inputFieldId}','${command.shippingAgent.id}');top.hidePopupDiv('shippingagent','shippingagentbody');top.popupboxclose();submitform();" />&nbsp&nbsp<input
						id="cancel" type="button" value="Cancel" name="cancel"
						class="button1"
						onClick="javascript:top.hidePopupDiv('shippingagent','shippingagentbody');top.popupboxclose();" />
					&nbsp&nbsp<input id="cancel2" type="submit" value="Apply"
						name="cancel2" class="button1" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	</div>
	<!----------------- TAB 1 CONTAINER END ------------------------------ -->
	</div>
	</div>

	<!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
	</div>
	<!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
	</td>
	</tr>
	</table>
</form:form>






