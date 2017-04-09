<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src=" js/calendar.js?random=20060118"></script>

<script language="javascript">
function onVatCodeAdd()
{

document.createVatRateForm.addOrDeleteVatCode.value = "add";
document.createVatRateForm.vxcel.value="false";
document.createVatRateForm.submit();
}

function onVatCodeDelete(index)
{
document.createVatRateForm.addOrDeleteVatCode.value = "delete";
document.createVatRateForm.vatCodeIndex.value = index;
document.createVatRateForm.vxcel.value="false";
document.createVatRateForm.submit();

}

function subform()
{
document.createVatRateForm.vxcel.value="false";
document.createVatRateForm.submit();
}

function setflag(rowIndex)
{  
document.createVatRateForm.vatRateFlag.value="newval";
document.createVatRateForm.rowNum.value=rowIndex;
document.createVatRateForm.vxcel.value="false";
}
function submitSearch(pageNumber)
{  
document.createVatRateForm.pageNumber.value = pageNumber;
document.createVatRateForm.pageFlag.value="pageFlag";
document.createVatRateForm.vxcel.value="false";
document.createVatRateForm.submit();
} 

function updateVatCodeIframeSrc(index,taxType){ 
document.createVatRateForm.vxcel.value="false";
document.getElementById('searchVatRateFr'+index).setAttribute("src","search_vat_rate_popup.htm?inputFieldId=taxRates["+index+"].taxRateId.taxCode&vatCodeId=val&searchForm=createVatRateForm&rowNum="+index+"&taxType="+taxType+"&div1=vatcode"+index+"&div2=vatcodebody"+index);
}
function submitxcel(){
document.createVatRateForm.vxcel.value="true";
top.document.createVatRateForm.submit();
}
  </script>


 <form:form name="createVatRateForm" method="POST" action="create_vat_rate.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
 <form:hidden path="addOrDeleteVatCode"/>
 <form:hidden path="vatCodeIndex"/>
 <form:hidden path="taxRateCount"/>
 <form:hidden path="vatRateFlag"/>
 <form:hidden path="rowNum" />
 <form:hidden path="pageFlag"/>
 <input type="hidden" name="vxcel" value="false"/>
<input type="hidden" name="pageNumber" value="1" />
<!-- START : #119240 : 06/07/09 -->
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>
<!-- END : #119240 : 06/07/09 -->

<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
<!----------------------------------------------MAIN CONTAINER----------------------------------------------------->
      <div id="MainContentContainer">
<!-----------------------------------------------TABS CONTENTS----------------------------------------------------->
<c:choose>
<c:when test="${command.taxType=='V'}">
<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="vatRates"/></span></a></li>
</ul>
</div>
</c:when>
<c:otherwise>
<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="salesTaxRates"/></span></a></li>
</ul>
</div>
</c:otherwise>
</c:choose>
<!-----------------------------------------Sub Menus container. Do not remove-------------------------------------->
          <div id="tab_inner">      
<!---------------------------------------------TAB 1 CONTAINER ---------------------------------------------------->
            <div id="tab1" class="innercontent" >
        <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
<c:choose>
<c:when test="${command.taxType=='V'}">
<th width="35%"><f:message key="vatRates"/></th>
</c:when>
<c:otherwise>
<th width="30%"><f:message key="salesTaxRates"/></th>
</c:otherwise>
</c:choose>
                    <th style="text-align:right;" >&nbsp;</th>
                    <th  style="text-align:right;" ><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0"  onclick="subform()"/ ></a></th>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td colspan="3" class="TDShade" style="padding:2px;">
          
          <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" >
                      <tr>
                        <th width="25"><f:message key="no"/></th>
<c:choose>
<c:when test="${command.taxType=='V'}">
<th width="30%"><f:message key="vatCode"/></th>
</c:when>
<c:otherwise>
<th width="30%"><f:message key="salesTaxCode"/></th>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${command.taxType=='V'}">
<th width="30%"><f:message key="varRateDesc"/></th>
</c:when>
<c:otherwise>
<th width="30%"><f:message key="salesTaxDesc"/></th>
</c:otherwise>
</c:choose>
                        <th width="35%"><f:message key="effectiveDate"/> </th>
                        <th width="25%"><f:message key="percentage"/></th>
                        <th style="text-align:center" width="50">&nbsp;&nbsp;&nbsp;<img src="images/add.gif" alt="Add row" width="13" height="12"  onclick="javascript:onVatCodeAdd()"/></th>
                      </tr>
            <c:forEach items="${command.taxRates}" var="taxRates" varStatus="counter">
                      <tr>
<td class="TDShadeBlue">${command.pagination.currentPageNum*20-20+counter.index+1}</td>
<td class="TDShadeBlue"><form:input cssClass="inputBox" size="15" maxlength="3" path="taxRates[${counter.index}].taxRateId.taxCode" />
          <form:errors path="taxRates[${counter.index}].taxRateId.taxCode" cssClass="redstar"/> 
<a href="#" onClick="javascript:setflag(${counter.index});updateVatCodeIframeSrc('${counter.index}','${command.taxType}');popup_show('vatcode${counter.index}', 'vatcode_drag${counter.index}', 'vatcode_exit${counter.index}','screen-corner', 120, 20);hideIt();popupboxenable();">
           <img src=" images/lookup.gif" alt="lookup Tax Rates" width="13" height="13" border="0"/></a></td>
           
           <td class="TDShadeBlue"><form:input cssClass="inputBox" size="60" maxlength="128" path="taxRates[${counter.index}].description" disabled="true"/>
          <form:errors path="taxRates[${counter.index}].description" cssClass="redstar"/>
            <td align="center" class="TDShadeBlue">
            <form:input id="effDate${counter.index}" cssClass="inputBox" path="taxRates[${counter.index}].taxRateId.effDate" size="10" maxlength="12" />
            
             <a href="#" onClick="displayCalendar(document.forms[0].effDate${counter.index},'MM/dd/yyyy',this)" > 
             <img src=" images/calendar.gif" width="15" height="17"  align="absmiddle" border="0" /></a>
               <form:errors path="taxRates[${counter.index}].taxRateId.effDate" cssClass="redstar" /> 
            <div id="debug"></div></td>

                        <td class="TDShadeBlue"><form:input cssClass="inputBox" size="15" path="taxRates[${counter.index}].taxPct"/>
                             <form:errors path="taxRates[${counter.index}].taxPct" cssClass="redstar"/></td>
                     <td width="50" class="TDShadeBlue" style="text-align:right;"><div id="div3" style="width:50px; text-align:center; margin-right:0;"> <img src="images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0" onclick="onVatCodeDelete('${counter.index}')" /></div>
                  </td>
            <ajax:autocomplete
                  baseUrl="customer.htm"
                  source="taxRates[${counter.index}].taxRateId.taxCode"
                  target="taxRates[${counter.index}].taxRateId.effDate"
                  className="autocomplete"
                  parameters="vatCode={taxRates[${counter.index}].taxRateId.taxCode},taxType=${command.taxType}"
                  minimumCharacters="1"
                 /> 

<%--
               <ajax:autocomplete
                  baseUrl="customer.htm"
                  source="taxRates[${counter.index}].taxRateId.effDate"
                  target="taxRates[${counter.index}].taxPct"
                  className="autocomplete"
                  parameters="vatCode={taxRates[${counter.index}].taxRateId.taxCode},effDate={taxRates[${counter.index}].taxRateId.effDate}"
                  minimumCharacters="1"
                 /> 
--%>
 
   </tr>         
<!----------------------------------------VAT Code Lookup --------------------------------------------------------->
<div class="sample_popup" id="vatcode${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="vatcode_drag${counter.index}" style="width:750px;height:auto; ">
<c:choose>
<c:when test="${command.taxType=='V'}">
<img class="menu_form_exit"   id="vatcode_exit${counter.index}" src=" images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="vatCode"/>
</c:when>
<c:otherwise>
<img class="menu_form_exit"   id="vatcode_exit${counter.index}" src=" images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="salesTaxCode"/>
</c:otherwise>
</c:choose>
</div>
<div class="menu_form_body" id="vatcodebody${counter.index}"   style="width:750px; height:500px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="500px;" width="100%" scrolling="auto" id="searchVatRateFr${counter.index}" name="searchVatRateFr${counter.index}" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!----------------------------------------VAT Code Lookup END ----------------------------------------------------->
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
<td style="text-align:right"><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="subform()"/></a></td>
</tr>
</table></td>
</tr>
</table>
</div>
<!-----------------------------------------------TAB1 CONTAINER END------------------------------------------------>
      
      
          </div>
        </div>
        <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", 0)
      
      </script>
<!---------------------------------------------TAB CONTENT END----------------------------------------------------->
      </div>
<!---------------------------------------MAIN CONTAINER END ------------------------------------------------------->
    </td>
  </tr>
</table>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>

</form:form>

