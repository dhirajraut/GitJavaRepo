<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script language="javascript" src="js/po/search_po.js"></script>
 
<form:form name="searchOrderCreateForm" method="POST" action="phx_search_purchase_order.htm">

<input type="hidden" name="pageNumber" value="1" />
<form:hidden path="sortFlag"/>
<form:hidden path="submitFlag"/>
<form:hidden path="cxcel"/>

<div class="redtext">
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
              <li><a href="#" rel="tab1"><span><f:message key="poSearch"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tr>
    <th colspan="2"><f:message key="advancedPOSearch"/></th>
  </tr>
  <tr>
    <td width="20%" class="TDShade"><f:message key="poNumber"/>: </td>
    <td width="80%" class="TDShadeBlue">
    <form:input cssClass="inputBox" path="poNumber.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
    </td>
  </tr>
  <tr>
    <td class="TDShade"><f:message key="customerId"/>: </td>
    <td class="TDShadeBlue">
    <form:input cssClass="inputBox" path="customerId.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
    &nbsp;
    
    <a href="#" onClick="javascript:showCustomerSearchIdSearchLookup();">
      <img src="images/lookup.gif" alt="customer Id" width="13" height="13" border="0">
    </a>
    
    <!--
     <a href="#" onClick="javascript:showCustomerSearch();popup_show('customerid', 'customerid_drag', 'customerid_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();"> 
	  <img src="images/lookup.gif" alt="customer Id" width="13" height="13" border="0">
     </a>
     -->
    </td>
  </tr>
  <tr>
    <td class="TDShade"><f:message key="customerName"/>: </td>
    <td class="TDShadeBlue">
    <form:input cssClass="inputBox" maxlength="40" path="customerName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
    &nbsp;
    
    <a href="#" onClick="javascript:showCustomerSearchNameSearchLookup();">
      <img src="images/lookup.gif" alt="customer Id" width="13" height="13" border="0">
    </a>
    
    <!--
    <a href="#" onClick="javascript:showCompanyNameSearch();popup_show('companyname', 'companyname_drag', 'companyname_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();"> <img src="images/lookup.gif" alt="lookup Customer Name" width="13" height="13" border="0"></a>
    -->
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
  <tr>
    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>
        <input name="button" type="button" onClick="submitFunction()" class="button1" value="<f:message key="search"/>" /></td>
        </td>
        <td><a href="../jobs/ins_jobsinstructions.html"></a></td>
        <td align="right">
			<a href="javascript:exportToXcel();"><img src="images/ico_excel.gif" border="0"></a>
		</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>

<!--results div starts-->
<c:if test="${command.results != null}">
	<div id="posearchresults"> 
	<strong>Search Results</strong>
	<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
	<tr>
		<th><a href="#" onClick="sortPOByNumber();" ><span class="TDShade"><f:message key="poNumber"/></span></a></th>
	    <th><a href="#" onClick="sortPOByCustCode();" ><span class="TDShade"><f:message key="customerId"/></span></a></th>
	    <th><a href="#" onClick="sortPOByCustName();" ><span class="TDShade"><f:message key="customerName"/></span></a></th>
	</tr>
	
	<c:forEach items="${command.results}" var="po" varStatus="status">
        <td>
	        <%--<authz:authorize ifAnyGranted="CreatePurchaseOrder">--%>
	        <a href="phx_create_purchase_order.htm?reqFrom=searchPurchaseOrderForm&po.poNumber=${po.poNumber}&edit=true">${po.poNumber}</a>
	        <%--</authz:authorize>
	        <authz:authorize ifNotGranted="CreatePurchaseOrder">
	        ${po.poNumber}
	        </authz:authorize>--%>
        </td>
        <td>${po.custCode}</td>
        <td>${po.customer.name}</td>
        </tr>
    </c:forEach>

    <tr>
      <td>&nbsp;</td>
      <td align="center">
        <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
          <a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
        </c:forEach>
      </td>
    </tr>
	
	</table>
	</div>
</c:if>
<!--results div ends-->
</div>

<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="customerId.value" 
	target="customerId.value" 
	className="autocomplete" 
	parameters="entity=com.intertek.phoenix.entity.CECustomer,textAttribute=custCode,valueAttribute=custCode,~custCode={customerId.value}"
	minimumCharacters="1" />
	
<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="customerName.value" 
	target="customerName.value" 
	className="autocomplete" 
	parameters="entity=com.intertek.phoenix.entity.CECustomer,textAttribute=name,valueAttribute=name,~name={customerName.value}"
	minimumCharacters="1" />	

<!---------------------------------- Customer Id Sequence Popup --------------------------------------------------->
<div class="sample_popup" id="customerid" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="customerid_drag" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="customerid_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCustomers"/>
</div>                                                           
<div class="menu_form_body" id="customeridbody"   style="width:750px; height:530px;overflow-y:hidden;">

<iframe align="left" id="custIdSearchLookup" frameborder="0" style="padding: 10px;"
	height="530px;" width="100%" scrolling="auto" allowtransparency="yes"
	src="blank.html"> </iframe>

<!--
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="customeridFr" name="customeridFr" allowtransparency="yes" ></iframe>
-->
</div>
</div>
<!-- -------------- ------------------------------------------------------------ -->
<!------------------------------------------------Company Name Lookup--------------------------------------------->
<div class="sample_popup" id="companyname" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="companyname_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="companyname_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCompanyName"/></div>
<div class="menu_form_body" id="companynamebody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">

<iframe align="left" id="custNameSearchLookup" frameborder="0" style="padding: 10px;"
	height="530px;" width="100%" scrolling="auto" allowtransparency="yes"
	src="blank.html"> </iframe>
	
<!-- 
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="searchCompanynameFr" name="searchCompanynameFr" allowtransparency="yes"></iframe></div></div>
-->
<!-------------------------------------------Company Name Lookup END---------------------------------------------->

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
