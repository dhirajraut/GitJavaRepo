<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
	uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><head>
<script language="javascript">
	function submitform()
	{
	top.document.forms[0].sampleLocCount.value = document.sampleLocPopUpForm.samploc.value;
	top.document.forms[0].submit();			
	} 
	function cancelForm()
	{
	document.sampleLocPopUpForm.samploc.value="";
	top.document.forms[0].scrollFlag.value ="none";
	top.document.forms[0].addOrDeleteSampleLoc.value = "none";
	return true;
	}
</script>
</head>

<form:form name="sampleLocPopUpForm" method="POST"
	action="sample_location_popup.htm">

	<form:hidden path="searchForm" />
	<form:hidden path="div1" />
	<form:hidden path="div2" />
	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
		<tr>
			<td valign="top">
			<div id="tab1" class="innercontent">
			<table width="98%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="InnerApplTable" style="margin-bottom: 10px;">
				<tr>
					<td><strong><f:message key="enterSampleLocation" /> :
					</strong></td>
					<td><form:input id="samploc" cssClass="inputBox" size="25"
						path="sampleLocCount" /> <form:errors path="sampleLocCount"
						cssClass="redstar" /> &nbsp;&nbsp;<br><br>
						</td>
						</tr>
				<tr>
					<td colspan="2"><input name="Submit" type="submit"
						class="button1" value="submit"
						onclick="javascript:submitform();top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();" />
					&nbsp;&nbsp; <input name="Button" type="button" class="button1" value="Cancel" onClick="javascript:cancelForm();top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();" /></td>
				</tr>
			</table>
	</table>
	</div>
	</div>
	</td>
	</tr>
	</table>
</form:form>


