<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<head>
<script language="javascript">
function searchbranch() {
		document.getElementById("branchcodesearchresults").style.visibility="visible";
		document.getElementById("branchcodesearchresults").style.display = "block";
		document.conslInvSearchForm.cxcel.value="false";		
	}

function updateBusinessUnitIframeSrc(){ 
document.conslInvSearchForm.cxcel.value="false";
document.getElementById('searchBusinessUnitFr').setAttribute("src","search_businessunit_popup.htm?inputFieldId=buName.value&searchForm=conslInvSearchForm");
}

function submitFunction()
{
	document.conslInvSearchForm.pageNumber.value = "1";
	document.conslInvSearchForm.sortFlag.value = "";
	document.conslInvSearchForm.cxcel.value="false";
	document.conslInvSearchForm.submit();
}
function submitSearch(pageNumber){  
document.conslInvSearchForm.pageNumber.value = pageNumber;
document.conslInvSearchForm.cxcel.value="false";
document.conslInvSearchForm.submit();
}

function submitxcel(){
document.conslInvSearchForm.cxcel.value="true";
document.conslInvSearchForm.sortFlag.value = "";
document.conslInvSearchForm.submit();
}
function sortByConsolInvColumnHeader(orderBy){
document.conslInvSearchForm.pageNumber.value = "1";
document.conslInvSearchForm.sortFlag.value = orderBy;
document.conslInvSearchForm.cxcel.value="false";
document.conslInvSearchForm.submit();
}
</script>
</head>
<icb:list var="operator">
  <icb:item>operator</icb:item>
</icb:list>
<form:form name="conslInvSearchForm" method="POST" action="search_consl_inv.htm">
<div style="color:red;"></div>

<input type="hidden" name="pageNumber" value="1" />
<form:hidden path="sortFlag"/>
<input type="hidden" name="cxcel" value="false"/>

<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="consolidatedInvoiceSearch"/> </span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tr>
    <th colspan="2"><f:message key="consolidatedInvoiceSearch"/><img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"> <a href="create_consl_inv.htm"><f:message key="addConsolidatedInvoice"/> </a></th>
  </tr>
  <tr>
    <td width="20%" class="TDShade">
      <f:message key="businessUnit"/>
      : </td>
    <td width="80%" class="TDShadeBlue">
	<form:select cssClass="selectionBox" id="sel2"  path="buName.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
    &nbsp;
    <form:input cssClass="inputBox" path="buName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
    &nbsp;
	 <a href="#" id="anchor" onClick="javascript:updateBusinessUnitIframeSrc();popup_show('bu', 'bu_drag', 'bu_exit', 'screen-corner', 120, 20); popupboxenable();hideIt();showPopupDiv('bu','bubody'); ">
	<img src="images/lookup.gif" alt="lookup Business Unit" width="13" height="13" border="0"></a>
	</td>
  </tr>
  <tr>
    <td class="TDShade"><f:message key="customer"/> : </td>
    <td class="TDShadeBlue">
	<form:select cssClass="selectionBox" id="sel1" path="customerId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
    &nbsp;
    <form:input cssClass="inputBox" path="customerId.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/></td>
  </tr>
  <tr>
    <td class="TDShade"><f:message key="customerName"/> : </td>
    <td class="TDShadeBlue">
	<form:select cssClass="selectionBox" id="sel3" path="customerName.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
    &nbsp;
    <form:input cssClass="inputBox" maxlength="40" path="customerName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
    </td>
  </tr>
  <tr>
    <td class="TDShade"><f:message key="invoice"/> : </td>
    <td class="TDShadeBlue">
	<form:select cssClass="selectionBox" id="sel4" path="invoiceNumber.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
    &nbsp;
    <form:input cssClass="inputBox" path="invoiceNumber.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
    </td>
  </tr>
</table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td><input name="button" type="button" onClick="submitFunction()" class="button1" value="Search" /></td>
                      <td style="text-align:right;"><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
                    </tr>
                  </table></td>
                </tr>
              </table>
              <br>
              <c:if test="${command.results != null}">
                <div id="contactsearchresults"> 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
                      <th><a href="#" onClick="sortByConsolInvColumnHeader('consolidatedInvoiceId.consolInvoiceNo');" ><span class="TDShade"><f:message key="consolidatedInvoiceNumber"/> </span></a></th>
                      <th><a href="#" onClick="sortByConsolInvColumnHeader('consolidatedInvoiceId.buName');" ><span class="TDShade"><f:message key="businessUnitName"/> </span></a></th>
                      <th><a href="#" onClick="sortByConsolInvColumnHeader('custCode');" ><span class="TDShade"><f:message key="custCode"/>  </span></a></th>
                      <th><a href="#" onClick="sortByConsolInvColumnHeader('customer.name');" ><span class="TDShade"><f:message key="customerName"/>  </span></a></th>
                    </tr>
                    <c:forEach items="${command.results}" var="conslinv" varStatus="status">
                      <tr>
                        <td>
                          <authz:authorize ifAnyGranted="CreateJob">
                            <a href="edit_consl_inv.htm?conslInvNumber=${conslinv.consolidatedInvoiceId.consolInvoiceNo}&buName=${conslinv.consolidatedInvoiceId.buName}">${conslinv.consolidatedInvoiceId.consolInvoiceNo}</a>
                          </authz:authorize>
                          <authz:authorize ifNotGranted="CreateJob">
                            ${conslinv.consolidatedInvoiceId.consolInvoiceNo}
                          </authz:authorize>
                        </td>
                        <td>${conslinv.consolidatedInvoiceId.buName} </td>   
                        <td>${conslinv.custCode}</td>
                        <td>${conslinv.customer.name}</td>
                    
                      </tr>
                    </c:forEach>
                    <tr>
                      <td>&nbsp;</td>
                      <td align="center">

                        <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
                          <a href="#" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
                        </c:forEach>
                      </td>
                    </tr>
                    <tr></tr>
                  </table>
                </div>
              </c:if>


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
<!----------------------------------------- BU Lookup----------------------------------------------------->
<div class="sample_popup" id="bu" style="visibility: hidden;display:none;">
<div class="menu_form_header" id="bu_drag" style="width:750px;">
<img class="menu_form_exit" id="bu_exit" src="images/form_exit.png" />&nbsp;&nbsp;&nbsp;<f:message key="searchBusinessUnit"/></div>
<div class="menu_form_body" id="bubody" style="width:750px;height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" scrolling="auto" id="searchBusinessUnitFr" name="searchBusinessUnitFr" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!------------------------------------------- BU Lookup--------------------------------------------------->
