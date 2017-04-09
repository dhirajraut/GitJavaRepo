<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src=" js/calendar.js?random=20060118"></script>

<script language="javascript">
function onTaxArticleAdd()
{
document.createTaxArticleForm.addOrDeleteTaxArticle.value = "add";
document.createTaxArticleForm.txcel.value="false";
document.createTaxArticleForm.submit();
}

function onTaxArticleDelete(index)
{
document.createTaxArticleForm.addOrDeleteTaxArticle.value = "delete";
document.createTaxArticleForm.taxArticleIndex.value = index;
document.createTaxArticleForm.txcel.value="false";
document.createTaxArticleForm.submit();

}

function subform()
{
document.createTaxArticleForm.txcel.value="false";
document.createTaxArticleForm.submit();
}

function setflag(rowIndex)
{	 
document.createTaxArticleForm.taxArticleFlag.value="newval";
document.createTaxArticleForm.rowNum.value=rowIndex;
document.createTaxArticleForm.vxcel.value="false";
}
function submitSearch(pageNumber)
{	 
document.createTaxArticleForm.pageNumber.value = pageNumber;
document.createTaxArticleForm.pageFlag.value="pageFlag";
document.createTaxArticleForm.txcel.value="false";
document.createTaxArticleForm.submit();
}	

function updateTaxArticleIframeSrc(index,taxType){	
document.getElementById('searchVatRateFr'+index).setAttribute("src","search_vat_rate_popup.htm?inputFieldId=taxRates["+index+"].taxRateId.taxCode&vatCodeId=val&searchForm=createTaxArticleForm&rowNum="+index+"&taxType="+taxType+"&div1=vatcode"+index+"&div2=vatcodebody"+index);
}
function submitxcel(){
document.createTaxArticleForm.txcel.value="true";
top.document.createTaxArticleForm.submit();
}
  </script>

<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
 <form:form name="createTaxArticleForm" method="POST" action="create_tax_article.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
 <form:hidden path="addOrDeleteTaxArticle"/>
 <form:hidden path="taxArticleIndex"/>
 <form:hidden path="taxArticleCount"/>
 <form:hidden path="taxArticleFlag"/>
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
<li><a href="#" rel="tab1"><span><f:message key="taxArticles"/></span></a></li>
</ul>
</div>
<!-----------------------------------Sub Menus container. Do not remove-------------------------------------------->
          <div id="tab_inner">			
<!-----------------------------------------------TAB 1 CONTAINER--------------------------------------------------->
            <div id="tab1" class="innercontent" >
			  <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
<th width="30%"><f:message key="taxArticles"/></th>
                    <th width="25%">&nbsp;</th>
                    <th width="40%" style="text-align:right;" ><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0"  onclick="subform()"/ ></a></th>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td colspan="3" class="TDShade" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" >
                      <tr>
                        <th width="25"><f:message key="no"/></th>
                        <th width="30%"><f:message key="taxArticleCode"/></th>
                        <th width="35%"><f:message key="taxArticleDescription"/></th>
                        <th width="25%"><f:message key="effectiveDate"/></th>
						<th width="25%"><f:message key="status"/></th>

                        <th width="25%" style="text-align:center">&nbsp;&nbsp;&nbsp;<img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onTaxArticleAdd()"/></th>
                      </tr>
					<c:forEach items="${command.taxArticles}" var="taxArticles" varStatus="counter">
                      <tr>
<td class="TDShadeBlue">${command.pagination.currentPageNum*20-20+counter.index+1}</td>

<td class="TDShadeBlue"><form:input cssClass="inputBox" size="15" maxlength="10" path="taxArticles[${counter.index}].taxArticleCode" />
<form:errors path="taxArticles[${counter.index}].taxArticleCode" cssClass="redstar"/></td>
				   
<td class="TDShadeBlue"><form:input cssClass="inputBox" size="15" maxlength="30" path="taxArticles[${counter.index}].taxArticleDescription" />
<form:errors path="taxArticles[${counter.index}].taxArticleDescription" cssClass="redstar"/></td>


<td align="center" class="TDShadeBlue">
<form:input id="effectiveDate${counter.index}" cssClass="inputBox" path="taxArticles[${counter.index}].effectiveDate" size="10" maxlength="12" />
						
<a href="#" onClick="displayCalendar(document.forms[0].effectiveDate${counter.index},'MM/dd/yyyy',this)" > 
<img src=" images/calendar.gif" width="15" height="17"  align="absmiddle" border="0" /></a>
<form:errors path="taxArticles[${counter.index}].effectiveDate" cssClass="redstar" />	
<div id="debug"></div></td>

<td class="TDShadeBlue" width="30%"><form:select cssClass="selectionBox" id ="sel1"  path="taxArticles[${counter.index}].status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/></td>
						        
								 
<td width="50" class="TDShadeBlue" style="text-align:right;">		 				 
<div id="div3" style="width:50px; text-align:center; margin-right:0;">
<img src="images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0" onclick="onTaxArticleDelete('${counter.index}')" /></div></td>
</tr>         

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
<div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>
</form:form>

