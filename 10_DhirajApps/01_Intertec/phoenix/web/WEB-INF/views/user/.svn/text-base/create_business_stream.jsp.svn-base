<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src=" js/calendar.js?random=20060118"></script>

<script language="javascript">
function onBuStreamAdd()
{
document.createBusinessStreamForm.addOrDeleteBuStream.value = "add";
document.createBusinessStreamForm.busExcel.value="false";
document.createBusinessStreamForm.submit();
}

function onBuStreamDelete(index)
{
document.createBusinessStreamForm.addOrDeleteBuStream.value = "delete";
document.createBusinessStreamForm.busStreamIndex.value = index;
document.createBusinessStreamForm.busExcel.value="false";
document.createBusinessStreamForm.submit();

}

function subform()
{
document.createBusinessStreamForm.busExcel.value="false";
document.createBusinessStreamForm.submit();
}
function submitSearch(pageNumber)
{	 
document.createBusinessStreamForm.pageNumber.value = pageNumber;
document.createBusinessStreamForm.pageFlag.value="pageFlag";
document.createBusinessStreamForm.busExcel.value="false";
document.createBusinessStreamForm.submit();
}

function submitxcel(){
document.createBusinessStreamForm.busExcel.value="true";

top.document.createBusinessStreamForm.submit();
}
function submitFunction()
{
	document.createBusinessStreamForm.searchFlag.value="searchFlag";
	document.createBusinessStreamForm.busExcel.value="false";
	document.createBusinessStreamForm.submit();
}
  </script>

 
<form:form name="createBusinessStreamForm" method="POST" action="create_business_stream.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
 <form:hidden path="addOrDeleteBuStream"/>
 <form:hidden path="busStreamIndex"/>
 <form:hidden path="busStreamCount"/>
 <form:hidden path="busStreamFlag"/>
 <form:hidden path="rowNum" />
 <form:hidden path="pageFlag"/>
 <form:hidden path="searchFlag"/>
<input type="hidden" name="busExcel" value="false"/>
<input type="hidden" name="pageNumber" value="1" />

<!-- START : #119240 : 02/07/09 -->
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>
 <!-- END : #119240 : 02/07/09 -->

<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
	
<!--------------------------------------------MAIN CONTAINER------------------------------------------------------->
<div id="MainContentContainer">
<!---------------------------------------------TABS CONTENTS------------------------------------------------------->

<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="businessStream"/></span></a></li>
</ul>
</div>
<!-----------------------------------Sub Menus container. Do not remove-------------------------------------------->
          <div id="tab_inner">			
<!-----------------------------------------------TAB 1 CONTAINER--------------------------------------------------->
            <div id="tab1" class="innercontent" >
		  
		   <%-- <icb:list var="divisionBu">
			<icb:item>${icbfn:user().businessUnit.organization.name}</icb:item>
			<icb:item>${command.businessUnit.value}</icb:item> 
			</icb:list> --%>

			<icb:list var="branchBu">
			<icb:item>${command.businessUnit.value}</icb:item> 
			</icb:list>

			  <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
					<th width="30%"><f:message key="businessStream"/></th>
                    <th width="25%">&nbsp;</th>
                    <th width="40%" style="text-align:right;" ><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0"  onclick="subform()"/ ></a></th>
                  </tr>
				  <tr>
				  <td colspan="0" class="TDShade"><f:message key="businessUnit"/><strong> :</strong></td>
				  <td colspan="3" class="TDShadeBlue">
				  <!-- START : #119240 -->
					 <%-- <form:select 
					  path="businessUnit.value"
					  items="${icbfn:dropdown('businessUnit', null)}"
					  itemLabel="name" itemValue="value"/> --%>
					  <form:select 
					  path="businessUnit.value"
					  items="${icbfn:dropdown('businessUnit', null)}"
					  itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
					<!-- END : #119240 -->				   		
				 <form:errors path="businessUnit.value" cssClass="redstar"/>
				 </td>
				 <ajax:select
				  baseUrl="business_unit.htm"
				  source="businessUnit.value"
				  target="branch.value"
				  parameters="bus.stream.buname1={businessUnit.value}"
				  parser="new ResponseXmlParser()" />
				 </tr>
				 <tr>
				<td colspan="0" class="TDShade"><f:message key="branch"/><strong> :</strong></td>
				<td colspan="3" class="TDShadeBlue">
				<!-- START : #119240 -->
				<%--	<form:select id="sel4" cssClass="selectionBox" path="branch.value" items="${icbfn:dropdown('bustreamBranch', branchBu)}" itemLabel="name" itemValue="value"/> --%>
					<form:select id="sel4" cssClass="selectionBox" path="branch.value" items="${icbfn:dropdown('bustreamBranch', branchBu)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
				<!-- END : #119240 -->
				<form:errors path="branch.value" cssClass="redstar"/>
				</td>
				
				</tr>
				<tr>
				<td colspan="3" class="TDShade"><input name="button" type="button" onClick="submitFunction()" class="button1" value="Search" /></td></td>
				<tr>
                  <tr bgcolor=#ffffff>
                    <td colspan="3" class="TDShade" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" >
                      <tr>
                        <th width="10" nowrap><f:message key="no"/></th>
                        <th width="20%" nowrap><f:message key="businessUnit"/></th>
                        <th width="20%" nowrap><f:message key="branch"/></th>
                        <th width="20%" nowrap><f:message key="businessStreamCode"/></th>
						<th width="20%" nowrap><f:message key="sortOrder"/></th>
                        <th width="25%" style="text-align:center">&nbsp;&nbsp;&nbsp;<img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onBuStreamAdd()"/></th>
                      </tr>
  <c:forEach items="${command.busStreams}" var="busStreams" varStatus="counter">
  
  <tr>

  <td class="TDShadeBlue">${command.pagination.currentPageNum*20-20+counter.index+1}</td>

 <td class="TDShadeBlue"> 
  
  <form:select cssClass="selectionBoxbig"
  path="busStreams[${counter.index}].branch.buName"
  items="${icbfn:dropdown('businessUnit', null)}"
  itemLabel="name" itemValue="value"/> 
 <form:errors path="busStreams[${counter.index}].branch.buName" cssClass="redstar"/>
</td> 

<td class="TDShadeBlue">
<icb:list var="branchBu">
<icb:item>${busStreams.branch.buName}</icb:item> 
</icb:list> 

<%-- <icb:list var="divisionBu">
<icb:item>${command.businessStream}</icb:item>
<icb:item>${command.businessUnit.value}</icb:item> 
</icb:list> --%>

 <form:select id="sel4" cssClass="selectionBox" path="busStreams[${counter.index}].busStreamId.branchCode" items="${icbfn:dropdown('bustreamBranch', branchBu)}" itemLabel="name" itemValue="value"/>
</td>

<td class="TDShadeBlue" width="30%"><form:input id="stlabel${counter.index}" cssClass="inputBox"  path="busStreams[${counter.index}].busStreamId.busStreamCode" size="32" maxlength="105" />
<form:errors path="busStreams[${counter.index}].busStreamId.busStreamCode" cssClass="redstar" />	
</td>

<td width="50" class="TDShadeBlue" style="text-align:right;">		 				 

<!-- START : #119240 : 01/07/09 -->
<%-- <form:input id="vrlabel${counter.index}" cssClass="inputBox" path="busStreams[${counter.index}].sortOrderNum" size="10"  />  --%>
<form:input id="vrlabel${counter.index}" cssClass="inputBox" path="busStreams[${counter.index}].sortOrderNum" size="7"  />
<!-- END : #119240 : 01/07/09 -->

<form:errors path="busStreams[${counter.index}].sortOrderNum" cssClass="redstar" />
</td>

<td width="50" class="TDShadeBlue" style="text-align:right;">		 				 
<div id="div3" style="width:50px; text-align:center; margin-right:0;">
<img src="images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0" onclick="onBuStreamDelete('${counter.index}')" /></div>
</td>

</tr>
  <ajax:select
  baseUrl="business_unit.htm"
  source="busStreams[${counter.index}].branch.buName"
  target="busStreams[${counter.index}].busStreamId.branchCode"
  parameters="bus.stream.buname2={busStreams[${counter.index}].branch.buName}"
  parser="new ResponseXmlParser()"/>

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

