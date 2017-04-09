 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@page import="java.text.*,java.util.*,com.intertek.cache.*" %>
   
<script language="javascript">

function onAdd(){
document.dropDownCreateForm.addOrDelete.value = "add";
document.dropDownCreateForm.dropdownType.value = document.getElementById('sel10').value;
document.dropDownCreateForm.submit();
}

function onDelete(index){
document.dropDownCreateForm.addOrDelete.value = "delete";
document.dropDownCreateForm.dropDownIndex.value = index;
document.dropDownCreateForm.submit();
}

function subform(){
document.dropDownCreateForm.dropdownType.value = document.getElementById('sel10').value;
document.dropDownCreateForm.submit();
}
function submitForm(){
document.dropDownCreateForm.cacheAdminFlag.value = "true";
document.dropDownCreateForm.submit();
}
function loadDropdownValues()
{
	document.dropDownCreateForm.operationFlag.value = 'load';
	document.dropDownCreateForm.dropdownType.value = document.getElementById('sel10').value;
	document.dropDownCreateForm.submit();
}

function CheckAll(chk)
{
for (i = 0; i < chk.length; i++)
chk[i].checked = true ;
}

function UnCheckAll(chk)
{
for (i = 0; i < chk.length; i++)
chk[i].checked = false ;
}


</script>

<icb:list var="dropdown">
  <icb:item>dropdown</icb:item>
</icb:list>

<form:form name="dropDownCreateForm" method="POST" action="configure_dropdown.htm">
 <div style="color:red;"> <form:errors cssClass="error"/></div>
<form:hidden path="addOrDelete"/>
<form:hidden path="operationFlag"/>
<form:hidden path="dropdownType"/>
<form:hidden path="dropDownIndex"/>
<form:hidden path="cacheAdminFlag"/>
 <form:hidden path="tabName" />

<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
<td valign="top">
<!------------------------------------------------------------------MAIN CONTAINER------------------------------------------------------------------------>
<div id="MainContentContainer">
<!------------------------------------------------------------------TABS CONTENTS------------------------------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="dropDownValues"/></span></a></li>
<li><a href="#" rel="tab2"><span><f:message key="dropDownCacheAdmin"/></span></a></li>
</ul>
</div>
<!--------------------------------------------------------------Sub Menus container. Do not remove----------------------------------------------------->
<div id="tab_inner">   
<!------------------------------------------------------------------TAB1 CONTAINER------------------------------------------------------------------------>
<div id="tab1" class="innercontent" >
<table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
<tbody>
<tr bgcolor=#ffffff>
<th width="50%"><f:message key="dropDowns"/></th>
<th width="45%" >&nbsp;</th>
<th width="5%" bgcolor="#ffffff" style="text-align:right"><a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="subform()"/></a></th>
</tr>
<tr bgcolor=#ffffff>
<td class="TDShade"><f:message key="dropDownType"/>:<span class="TDShadeBlue">
<td class="TDShadeBlue">				
	<form:select id="sel10" cssClass="selectionBox" path="dropDowns.dropDownId.dropdownType" items="${icbfn:dropdown('staticDropdown',dropdown)}" itemLabel="name" itemValue="value" onchange="loadDropdownValues()"/>	<form:errors path="dropDowns.dropDownId.dropdownType" cssClass="redstar"/></td>

</span></td>

</tr>

<tr>
<td colspan="3" style="padding:2px;">

<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<th><f:message key="no"/>.</th>
<th width="45%"><f:message key="name"/></th>
<th width="45%"><f:message key="description"/></th>

<th width="50%"  style="text-align:center" colspan="2">&nbsp;&nbsp;&nbsp;
<img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onAdd()"/>&nbsp;&nbsp;</th>
</tr>
<c:forEach items="${command.dropDown}" var="dropDown" varStatus="counter">
<tr>
<td class="TDShadeBlue">${counter.index+1}<strong>.</strong></td>	  

 <td class="TDShadeBlue" width="45%">
 <form:input cssClass="inputBox" size="10" maxlength="50" path="dropDown[${counter.index}].dropDownId.fieldName"/>
<form:errors path="dropDown[${counter.index}].dropDownId.fieldName" cssClass="redstar"/>
</td> 


<td class="TDShadeBlue" width="45%"><form:input cssClass="inputBox" size="35"  maxlength="128" path="dropDown[${counter.index}].fieldValue"/>
<form:errors path="dropDown[${counter.index}].fieldValue" cssClass="redstar"/></td> 

<td class="TDShadeBlue" width="30%" style="text-align:center">	
						
<img src=" images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0"  onclick="onDelete('${counter.index}')"/></a></div></td>
</tr>
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
<td style="text-align:right"><a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="subform()"/></a></td>
</tr>
</table>
</td>
</tr>
</table>
</div>
<!-----------------------------------------------------------------TAB1 CONTAINER END-------------------------------------------------------------------->

<!------------------------------------------------------------------TAB2 CONTAINER------------------------------------------------------------------------>
<div id="tab2" class="innercontent" >
<table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
<tbody>

<font color="#000066"><b><f:message key="cacheAdmin"/></b></FONT>
<table cellspacing="3">
<tr>
    <td align="right"><font size="2"><B><f:message key="options"/>:</B></font></td>
    <td><input type="checkbox" name="FlushCacheSpace"/></td>
	<td><font size="2"><f:message key="flushCacheSpace"/></font></td>
	<td></td>
	<td></td>
	<td rowspan="4" width="25"></td>
	<td rowspan="4" valign="bottom"><input type="BUTTON" CLASS="PSPUSHBUTTON" value="<f:message key="executeChanges"/>" onClick="submitForm()"></td>
	<td rowspan="4" valign="bottom">&nbsp;</td>
</tr>

<tr>
    <td align="right"><font size="2"><B><f:message key="flush"/>:</B></font></td>
	<td></td>
    <td><font size="2"><a href="javascript:CheckAll(document.dropDownCreateForm.FlushList)" STYLE="text-decoration:none"><f:message key="selectAll"/></a></font></td>
    <td><font size="2"><a href="javascript:UnCheckAll(document.dropDownCreateForm.FlushList)" STYLE="text-decoration:none"><f:message key="removeAll"/></a></font></td>
</tr>

<tr>
    <td align="right"><font size="2"><B>Toggle:</B></font></td>
	<td></td>
    <td><font size="2"><a href="javascript:CheckAll(document.dropDownCreateForm.ToggleList)" STYLE="text-decoration:none"><f:message key="selectAll"/></a></font></td>
    <td><font size="2"><a href="javascript:UnCheckAll(document.dropDownCreateForm.ToggleList)" STYLE="text-decoration:none"><f:message key="removeAll"/></a></font></td>
</tr>
</table>

<HR SIZE="1" COLOR="#555599">

<TABLE>
<TR>
    <TD width="30%"><U><B><f:message key="cacheName"/></B></U></TD>
    <TD width="10%"><U><B><f:message key="size"/></B></U></TD>
    <TD width="10%"><U><B><f:message key="status"/></B></U></TD>
    <TD width="15%"><U><B><f:message key="toggleStatus"/></B></U></TD>
    <TD width="15%"><U><B><f:message key="flush"/></B></U></TD>
</TR>

<%
    try {
		Object[] cacheNames = ITSCacheManager.getCacheSpaces().toArray();
		Arrays.sort(cacheNames, Collator.getInstance()); 

		for (int i = 0; i < cacheNames.length; i++) {
			String name = (String)cacheNames[i];
			int size = ITSCacheManager.getCacheSize(name);
			String status = "Disabled";
			if (ITSCacheManager.isCacheEnabled(name)) {
				status = "Enabled";
			}
%>
				<TR>
				<TD ALIGN="left"><%= name %></TD>
				<TD ALIGN="left"><%= size %></TD>
				<TD ALIGN="left"><%= status %></TD>
				<TD><INPUT TYPE="checkbox" NAME="ToggleList" VALUE="<%= name %>"></TD>
				<TD><INPUT TYPE="checkbox" NAME="FlushList" VALUE="<%= name %>"></TD>
				</TR>
<%
		}
	}
	catch (Exception ex) {
%>
		Error Encountered By Cache Administrator<BR>
		Error: <%= ex.toString() %><BR>
		If this error persists, contact your system administrator.
<%
		System.out.println("CacheAdmin Error: " + ex);
		ex.printStackTrace();
	}
%>
</TABLE>
</tbody>
</table>
</div>
<!-----------------------------------------------------------------TAB2 CONTAINER END-------------------------------------------------------------------->
</div>
</div>
<script type="text/javascript">
var pageNoVar = "${command.tabName}"
dolphintabs.init("tabnav", pageNoVar)
</script>
<!-----------------------------------------------------------------TAB CONTENT END------------------------------------------------------------------------>
</div>
<!----------------------------------------------------------------- MAIN CONTAINER END ------------------------------------------------------------------>
</td>
</tr>
</table>
</form:form>







