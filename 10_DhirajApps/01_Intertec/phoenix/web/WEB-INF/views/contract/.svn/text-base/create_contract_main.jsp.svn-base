<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script language="javascript">
  function doOperation(myOperationType)
  {
    document.contractCreateForm.operationType.value = myOperationType;
    document.contractCreateForm.submit();
  }

  function setsignatoryflag()
  {  
    document.contractCreateForm.userFlag.value="sigFlag";
    document.contractCreateForm.tabName.value="0";
  }

  function setoriginatorflag()
  {
    document.contractCreateForm.userFlag.value="originFlag";
    document.contractCreateForm.tabName.value="0";
  }

  function updateOriginatorIframeSrc()
  {
    document.getElementById('searchOriginatorFr').setAttribute("src","search_user_popup.htm?inputFieldId=contract.originatorUserName&div1=originator&div2=originatorbody&searchForm=contractCreateForm");
  }

  function updateSignatoryIframeSrc()
  {
    document.getElementById('searchSignatoryFr').setAttribute("src","search_user_popup.htm?inputFieldId=contract.signatoryUserName&div1=signatory&div2=signatorybody&searchForm=contractCreateForm");
  }

  function resetform()
  {
    document.contractCreateForm.showAttachFile.value="none";
    //document.contractCreateForm.submit();
  }

  function getMyFormName()
  {
    return "contractCreateForm";
  }
</script>

<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<icb:list var="workingPb">
  <icb:item>workingPb</icb:item>
</icb:list>
<icb:list var="workingUOM">
  <icb:item>workingUOM</icb:item>
</icb:list>
<icb:list var="invoiceType">
  <icb:item>invoiceType</icb:item>
</icb:list>
<icb:list var="contractAttachType">
  <icb:item>contractAttachType</icb:item>
</icb:list>
<icb:list var="audienceType">
  <icb:item>audienceType</icb:item>
</icb:list>

<icb:list var="localContractCode">
  <icb:item>${command.contract.contractCode}</icb:item>
</icb:list>

<c:set var="userDateFormat" value="${icbfn:userDateFormat()}" />

<form:form name="contractCreateForm" method="POST" action="create_contract.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="uploadedFileName"/>
<form:hidden path="deleteAttach" />
<form:hidden path="contractAttachCount" />
<form:hidden path="contractAttachIndex"/>
<form:hidden path="tabName" />
<form:hidden path="status" id="status" />
 <form:hidden path="addOrDelete" />
<form:hidden path="index" />
<form:hidden path="contractCustCount"/>
<form:hidden path="contactCustCount" />
<form:hidden path="contractCustIndex" />
<form:hidden path="contractCustContactIndex" />
<form:hidden path="addOrDeleteContractContact" />
<form:hidden path="contSeqFlag"/>
<form:hidden path="rowNum"/>
<form:hidden path="rowNumContact"/>
<form:hidden path="contactSearchFlag"/>
<form:hidden path="custCode"/>
<form:hidden path="userFlag"/>
<form:hidden path="showAttachFile"/>
<input type="hidden" name="operationType" value="" />
<input type="hidden" name="cfgContractIndex" value="" />

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <!----------------------------------------- MAIN CONTAINER -------------------------------------------------------->
      <div id="MainContentContainer">
        <!-------------------------------------------TABS CONTENTS--------------------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span> Contract</span></a></li>
            </ul>
          </div>
          <!-----------------------------------------Sub Menus container. Do not remove ------------------------------------->
          <div id="tab_inner">
          <!-----------------------------------------------------TAB 1 CONTAINER -------------------------------------------->
            <div id="tab1" class="innercontent" >
              <%@ include file="contract_tab.jsp" %> 
            </div>
            <!------------------------------------------ Originator Lookup START----------------------------------------------->
            <div class="sample_popup" id="originator" style="visibility: hidden; display: none;">
            <div class="menu_form_header" id="originator_drag" style="width:750px;">
            <img class="menu_form_exit" id="originator_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Originator
            </div>
            <div class="menu_form_body" id="originatorbody" style="width:750px; height:530px;overflow-y:hidden;padding-left:4px;">
            <iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%" scrolling="auto" id="searchOriginatorFr" name="searchOriginatorFr"  allowtransparency="yes" src="blank.html"></iframe>
            </div>
            </div>
            <!----------------------------------------------Originator Lookup END --------------------------------------------->
            <!----------------------------------------- Signatory Lookup START------------------------------------------------->
            <div class="sample_popup" id="signatory" style="visibility: hidden; display: none;">
            <div class="menu_form_header" id="signatory_drag" style="width:750px;">
            <img class="menu_form_exit" id="signatory_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Signatory
            </div>
            <div class="menu_form_body" id="signatorybody" style="width:750px; height:530px;overflow-y:hidden;padding-left:4px;">
            <iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%" scrolling="auto" id="searchSignatoryFr" name="searchSignatoryFr" allowtransparency="yes" src="blank.html"></iframe>
            </div>
            </div>
            <!-------------------------------------------Signatory Lookup END-------------------------------------------------->
            <!----------------------------------------------- TAB 1 CONTAINER END --------------------------------------------->
            <!---------------------------------------------TAB 2 CONTAINER----------------------------------------------------->
            <div id="tab2" class="innercontent" >
            <%@ include file="contract_customer_tab.jsp" %>
            </div>
          </div>
        </div>
        <script type="text/javascript">
          dolphintabs.init("tabnav", 0)      
        </script>
        <!-------------------------------------------------------- TAB CONTENT END ---------------------------------------->
      </div>
      <!----------------------------------------- MAIN CONTAINER END ---------------------------------------------------->
    </td>
  </tr>
</table>
</form:form>

<!-------------------------------------------- Country Lookup ----------------------------------------------------->
<div class="sample_popup" id="country" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="country_drag" style="width:430px; "> <img class="menu_form_exit"   id="country_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Select Country</div>
  <div class="menu_form_body"   style="width:430px; height:280px;">
    <form method="post" action="setup/popup.php">
      <table width="95%" border="0" align="center" class="InnerApplTable">
        <tr>
          <td valign="middle"  colspan="2">
            <iframe align="left" frameborder="0" style="padding:0px; height:230px;" width="100%" scrolling="no" id="countryfr" name="countryfr" allowtransparency="yes" src="blank.html"></iframe>
          </td>
        </tr>
        <tr>
          <td><input id="ok" type="button" value="OK" name="ok" class="button1" onClick="hidecountrylookup();"/>
            &nbsp;&nbsp;
            <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hidecountrylookup();"/>
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
<!------------------------------------------------- Country Lookup END -------------------------------------------->

<!-- ----------------------------------- Edit Product Group Set Start ------------------------------------- -->
<div class="sample_popup" id="editproductgroupset" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="editproductgroupset_drag" style="width:800px; "> 
  <img class="menu_form_exit"   id="editproductgroupset_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Edit Product Group Set</div>
  <div class="menu_form_body" id="editproductgroupsetbody"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="editproductgroupsetbox" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html" ></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
<!-- --------------------------------- Edit Product Group Set END ----------------------------------------- -->

<!-- ----------------------------------- Edit Vessel Type Set Start ------------------------------------- -->
<div class="sample_popup" id="editvesseltypeset" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="editvesseltypeset_drag" style="width:800px; "> 
  <img class="menu_form_exit"   id="editvesseltypeset_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Edit Vessel Type Set</div>
  <div class="menu_form_body" id="editvesseltypesetbody"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="editvesseltypesetbox" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
<!-- --------------------------------- Edit Vessel Type Set END ----------------------------------------- -->

<!-----------------------------------------Branch Code Lookup----------------------------------------------------->
<div class="sample_popup" id="jobbranchcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="jobbranchcode_drag" style="width:750px; "> 
    <a href="#"  onclick="resetBranchTypeFlag()">
      <img class="menu_form_exit" id="jobbranchcode_exit" src="images/form_exit.png"/> &nbsp;&nbsp;&nbsp;
    <f:message key="searchBranchCode"/>
  </div>
  <div class="menu_form_body" id="jobbranchcodebody"   style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
    <iframe align="left" id="jobbu" frameborder="0" style="padding:10px;" height="530px;" width="100%"  scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" src="blank.html">
    </iframe>
  </div>
</div>
<!-----------------------------------------Branch Code Lookup END------------------------------------------------->

