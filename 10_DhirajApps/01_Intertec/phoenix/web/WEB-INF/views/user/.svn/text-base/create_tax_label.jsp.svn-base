<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src=" js/calendar.js?random=20060118"></script>

<script language="javascript">
function onTaxLabelAdd()
{
document.createTaxLabelForm.addOrDeleteTaxLabel.value = "add";
document.createTaxLabelForm.txcel.value="false";
document.createTaxLabelForm.submit();
}

function onTaxLabelDelete(index)
{
document.createTaxLabelForm.addOrDeleteTaxLabel.value = "delete";
document.createTaxLabelForm.taxLabelIndex.value = index;
document.createTaxLabelForm.txcel.value="false";
document.createTaxLabelForm.submit();

}

function subform()
{
document.createTaxLabelForm.txcel.value="false";
document.createTaxLabelForm.submit();
}

function setflag(rowIndex)
{	 
document.createTaxLabelForm.taxLabelFlag.value="newval";
document.createTaxLabelForm.rowNum.value=rowIndex;
document.createTaxLabelForm.vxcel.value="false";
}
function submitSearch(pageNumber)
{	 
document.createTaxLabelForm.pageNumber.value = pageNumber;
document.createTaxLabelForm.pageFlag.value="pageFlag";
document.createTaxLabelForm.txcel.value="false";
document.createTaxLabelForm.submit();
}	


function submitxcel(){
document.createTaxLabelForm.txcel.value="true";
top.document.createTaxLabelForm.submit();
}
  </script>

<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
 <form:form name="createTaxLabelForm" method="POST" action="create_tax_label.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
 <form:hidden path="addOrDeleteTaxLabel"/>
 <form:hidden path="taxLabelIndex"/>
 <form:hidden path="taxLabelCount"/>
 <form:hidden path="taxLabelFlag"/>
 <form:hidden path="rowNum" />
 <form:hidden path="pageFlag"/>
  <input type="hidden" name="txcel" value="false"/>
<input type="hidden" name="pageNumber" value="1" />

<!-- START : #119240 : 01/07/09 -->
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>
<!-- END : #119240 : 01/07/09 -->

<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
	
<!--------------------------------------------MAIN CONTAINER------------------------------------------------------->
<div id="MainContentContainer">
<!---------------------------------------------TABS CONTENTS------------------------------------------------------->

<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="taxLables"/></span></a></li>
</ul>
</div>
<!-----------------------------------Sub Menus container. Do not remove-------------------------------------------->
          <div id="tab_inner">			
<!-----------------------------------------------TAB 1 CONTAINER--------------------------------------------------->
            <div id="tab1" class="innercontent" >
			  <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
<th width="30%"><f:message key="taxLables"/></th>
                    <th width="25%">&nbsp;</th>
                    <th width="40%" style="text-align:right;" ><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0"  onclick="subform()"/ ></a></th>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td colspan="3" class="TDShade" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" >
                      <tr>
                        <th width="10" nowrap><f:message key="no"/></th>
                        <th width="20%" nowrap><f:message key="country"/></th>
                        <th width="20%" nowrap><f:message key="state"/></th>
                        <th width="20%" nowrap><f:message key="vatLabel"/></th>
						<th width="20%" nowrap><f:message key="salesTaxLabel"/></th>
						<th width="20%" nowrap><f:message key="vatRegLabel"/></th>

                        <th width="25%" style="text-align:center">&nbsp;&nbsp;&nbsp;<img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onTaxLabelAdd()"/></th>
                      </tr>
<c:forEach items="${command.taxLabels}" var="taxLabels" varStatus="counter">
  <tr>
<td class="TDShadeBlue">${command.pagination.currentPageNum*20-20+counter.index+1}</td>

<td class="TDShadeBlue"> <form:select id="sel2" cssClass="selectionBox" path="taxLabels[${counter.index}].taxLabelId.countryCode" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" /> <form:errors path="taxLabels[${counter.index}].taxLabelId.countryCode" cssClass="redstar"/> </td>
				   


<td class="TDShadeBlue">
<icb:list var="countryCodeList">
<icb:item>${taxLabels.taxLabelId.countryCode}</icb:item>
</icb:list>	
<form:select cssClass="selectionBox" id="sel5" path="taxLabels[${counter.index}].taxLabelId.state" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" />
<form:errors path="taxLabels[${counter.index}].taxLabelId.state" cssClass="redstar"/> </td>


<td align="center" class="TDShadeBlue">
<form:input id="vlabel${counter.index}" cssClass="inputBox" path="taxLabels[${counter.index}].vatLabel" size="10" maxlength="12" />
<form:errors path="taxLabels[${counter.index}].vatLabel" cssClass="redstar" />	
<div id="debug"></div></td>

<td class="TDShadeBlue" width="30%"><form:input id="stlabel${counter.index}" cssClass="inputBox" path="taxLabels[${counter.index}].salesTaxLabel" size="10" maxlength="12" />
<form:errors path="taxLabels[${counter.index}].salesTaxLabel" cssClass="redstar" />	</td>
						        
								 
<td width="50" class="TDShadeBlue" style="text-align:right;">		 				 
<form:input id="vrlabel${counter.index}" cssClass="inputBox" path="taxLabels[${counter.index}].vatRegLabel" size="10" maxlength="12" />
<form:errors path="taxLabels[${counter.index}].vatRegLabel" cssClass="redstar" /></td>

<td width="50" class="TDShadeBlue" style="text-align:right;">		 				 
<div id="div3" style="width:50px; text-align:center; margin-right:0;">
<img src="images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0" onclick="onTaxLabelDelete('${counter.index}')" /></div></td>

</tr> 

 <ajax:select
  baseUrl="country.htm"
  source="taxLabels[${counter.index}].taxLabelId.countryCode"
  target="taxLabels[${counter.index}].taxLabelId.state"
  parameters="country.countryCode={taxLabels[${counter.index}].taxLabelId.countryCode}"
  parser="new ResponseXmlParser()" />



</c:forEach>

 <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
 <td>&nbsp;</td>
<td align="center">
 <!-- START : #119240 : 19/06/09 --> 
<%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
<a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
</c:forEach> --%>
 <%@ include file="../common/pagination.jsp" %>
<!-- END : #119240 : 19/06/09 -->
</td>
<tr></tr>
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
<td style="text-align:right"><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="subform()"/></a></td>
</tr>
</table></td>
</tr>
</table>
</div>
<!-------------------------------------------TAB1 CONTAINER END---------------------------------------------------->
</div>
</div>
<script type="text/javascript">
//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
dolphintabs.init("tabnav", 0)
</script>
<!-------------------------------------------TAB CONTENT END------------------------------------------------------->
 </div>
<!------------------------------------------MAIN CONTAINER END----------------------------------------------------->
</td>
</tr>
</table>

</form:form>

