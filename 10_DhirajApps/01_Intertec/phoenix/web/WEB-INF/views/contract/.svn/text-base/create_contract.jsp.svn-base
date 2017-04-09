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

  function onAttachDelete(index)
  {
    document.contractCreateForm.deleteAttach.value = "deleteAttach";
    document.contractCreateForm.tabName.value = "2";
    document.contractCreateForm.contractAttachIndex.value = index;
    document.contractCreateForm.submit();
  }
      
  function onAdd()
  {
    document.getElementById("addOrDelete").value="add";
    document.getElementById("tabName").value="2";
    document.contractCreateForm.submit();
  }
  
  function onDelete(index)
  {
    document.contractCreateForm.addOrDelete.value = "delete";
    document.getElementById("tabName").value="1";
    document.getElementById("index").value=index;
    document.contractCreateForm.submit();
  }

  function onAddContact(contractCustIndex)
  {
    document.contractCreateForm.addOrDeleteContractContact.value = "addContact";
    document.contractCreateForm.contractCustIndex.value = contractCustIndex;
    document.contractCreateForm.tabName.value = "1";
    document.contractCreateForm.submit();
  }
  
  function onDeleteContact(contractCustIndex,contractCustContactIndex)
  {
    document.contractCreateForm.addOrDeleteContractContact.value = "deleteContact";
    document.contractCreateForm.contractCustIndex.value = contractCustIndex;
    document.contractCreateForm.contractCustContactIndex.value = contractCustContactIndex;
    document.contractCreateForm.tabName.value = "1";
    document.contractCreateForm.submit();
  }
  
  function setflagvalue(rowIndex)
  {
    document.contractCreateForm.contSeqFlag.value="custval";
    document.contractCreateForm.rowNum.value=rowIndex;
  } 
  
  function setcontactflag(rowIndex,rowContactIndex)
  {   
    document.contractCreateForm.contactSearchFlag.value="newval";
    document.contractCreateForm.rowNum.value=rowIndex;
    document.contractCreateForm.rowNumContact.value=rowContactIndex;
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

function showAttach(filename,index)
{
document.contractCreateForm.showAttachFile.value="showAttach";
document.contractCreateForm.tabName.value = "2";
document.contractCreateForm.contractAttachIndex.value = index;
document.contractCreateForm.submit();
}
function updateCustomerIframeSrc(index)
{
document.getElementById('customeridFr'+index).setAttribute("src","search_customer_id_popup.htm?inputFieldId=addContractCusts["+index+"].contractCust.contractCustId.custCode&rowNum="+index+"&searchForm=contractCreateForm&divName1=customerid"+index+"&divName2=customeridbody"+index);
}

function  updateContactIframeSrc(indexf,indexs,ccode)
{
document.getElementById('searchContactFr'+indexf+indexs).setAttribute("src","search_contact_popup.htm?inputFieldId=addContractCusts["+indexf+"].contractCustContacts["+indexs+"].contractCustContactId.contactId&rowNum="+indexf+"&rowNumContact="+indexs+"&searchForm=contractCreateForm&divName=contactid"+indexf+indexs+"&divbody=contactbody"+indexf+indexs+"&custCode="+ccode);
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
              <li><a href="#" rel="tab2"><span>Contract Customers</span></a></li>
              <li><a href="#" rel="tab3"><span>Contract Attachments</span></a></li>
              <li><a href="#" rel="tab4"><span>Zones</span></a></li>
              <li><a href="#" rel="tab5"><span>Cargo Inspection</span></a></li>
              <li><a href="#" rel="tab6"><span>Contracts Services</span></a></li>
              <li><a href="#" rel="tab7"><span>Contract Test</span></a></li>
              <li><a href="#" rel="tab8"><span>Contract Slates</span></a></li>
			  <li><a href="#" rel="tab9"><span><f:message key="contractNotes"/></span></a></li>
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
<iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%"       <%--src="search_user_popup.htm?inputFieldId=contract.originatorUserName&div1=originator&div2=originatorbody&searchForm=contractEditForm"--%> scrolling="auto" id="searchOriginatorFr" name="searchOriginatorFr"  allowtransparency="yes"></iframe>
</div>
</div>
<!----------------------------------------------Originator Lookup END --------------------------------------------->
<!----------------------------------------- Signatory Lookup START------------------------------------------------->
<div class="sample_popup" id="signatory" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="signatory_drag" style="width:750px;">
<img class="menu_form_exit" id="signatory_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Signatory
</div>
<div class="menu_form_body" id="signatorybody" style="width:750px; height:530px;overflow-y:hidden;padding-left:4px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%"       <%--src="search_user_popup.htm?inputFieldId=contract.signatoryUserName&div1=signatory&div2=signatorybody&searchForm=contractEditForm"--%> scrolling="auto" id="searchSignatoryFr" name="searchSignatoryFr" allowtransparency="yes"></iframe>
</div>
</div>
<!-------------------------------------------Signatory Lookup END-------------------------------------------------->
<!----------------------------------------------- TAB 1 CONTAINER END --------------------------------------------->
<!---------------------------------------------TAB 2 CONTAINER----------------------------------------------------->
 <div id="tab2" class="innercontent" >
 <%@ include file="contract_customer_tab.jsp" %>
 </div>
<!----------------------------------------TAB 2 CONTAINER END------------------------------------------------------>
<!------------------------------------------------- TAB 3 CONTAINER ----------------------------------------------->
<div id="tab3" class="innercontent">
<%@ include file="contract_attachment_tab.jsp" %>
</div>
<!------------------------------------------------ TAB 3 CONTAINER END -------------------------------------------->
<!------------------------------------------------- TAB 4 CONTAINER ----------------------------------------------->
<div id="tab4" class="innercontent" >
<%@ include file="contract_zone_tab.jsp" %>
</div>
<!------------------------------------------------ TAB 4 CONTAINER END -------------------------------------------->
<!---------------------------------------------------- TAB 5 CONTAINER -------------------------------------------->
<div id="tab5" class="innercontent" >
<%@ include file="contract_inspection_tab.jsp" %>
</div>
<!------------------------------------------------- TAB 5 CONTAINER END ------------------------------------------->
<!-------------------------------------------------- TAB 6 CONTAINER ---------------------------------------------->
<div id="tab6" class="innercontent" >
<%@ include file="contract_service_tab.jsp" %>
</div>
<!------------------------------------------------ TAB 6 CONTAINER END -------------------------------------------->
<!-------------------------------------------------- TAB 7 CONTAINER ---------------------------------------------->
<div id="tab7" class="innercontent" >
<%@ include file="contract_test_tab.jsp" %>
</div>
<!------------------------------------------------ TAB 7 CONTAINER END -------------------------------------------->
<!--------------------------------------------------- TAB 8 CONTAINER --------------------------------------------->
<div id="tab8" class="innercontent" >
<%@ include file="contract_slate_tab.jsp" %>
</div> 
<!------------------------------------------------ TAB 8 CONTAINER END -------------------------------------------->
<!------------------------------------------------- TAB 9 CONTAINER ----------------------------------------------->
<div id="tab9" class="innercontent" >
<%@ include file="contract_notes_tab.jsp" %>
</div>
<!------------------------------------------------ TAB 9 CONTAINER END -------------------------------------------->
          </div>
        </div>
        <script type="text/javascript">
          var pageNoVar = "${command.tabName}"
          //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
          dolphintabs.init("tabnav", pageNoVar)      
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
          <td valign="middle"  colspan="2"><iframe align="left" frameborder="0" style="padding:0px; height:230px;" width="100%" src="inc_countrylookup.html" scrolling="no" id="countryfr" name="countryfr" allowtransparency="yes" ></iframe></td>
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

<!-------------------------------------------- Branch Code Lookup ------------------------------------------------->
<div class="sample_popup" id="branchcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="branchcode_drag" style="width:450px; "> <img class="menu_form_exit"   id="branchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Branch Code</div>
  <div class="menu_form_body" id="branchcodebody"   style="width:450px; height:130px;">
    <form method="post" action="setup/popup.php">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
          <td width="25%"><strong>Business Unit: </strong></td>
          <td><input name="textfield" type="text" class="inputBox" size="30" /></td>
        </tr>
        <tr>
          <td><strong>Branch Code: </strong></td>
          <td><input name="textfield2" type="text" class="inputBox" size="30" /></td>
        </tr>
        <tr>
          <td><strong>Description:</strong></td>
          <td><input name="textfield3" type="text" class="inputBox" size="30" /></td>
        </tr>
        <tr>
          <td colspan="2"><input name="Button" type="button" class="button1" value="Search" onClick="searchbranchcode();"/>
            &nbsp;&nbsp;
            <input name="Button" type="button" class="button1" value="Cancel" onClick="hidebranchcode();popupboxclose();" /></td>
        </tr>
      </table>
       
      <div id="branchcodesearchresults" style="visibility:hidden; display:none;"><!--Search Results -->
        <br>&nbsp;&nbsp;<strong>Search Results</strong>
        <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%">
          <tr>
            <th>Business Unit </th>
            <th>Branch Code </th>
            <th>Description</th>
          </tr>
          <tr>
            <td><a href="#">US120-000001</a></td>
            <td><a href="#">US120-000001</a></td>
            <td>----------------</td>
          </tr>
          <tr>
            <td><a href="#">US120-000002</a></td>
            <td><a href="#">US120-000002</a></td>
            <td>----------------</td>
          </tr>
          <tr>
            <td><a href="#">US120-000003</a></td>
            <td><a href="#">US120-000003</a></td>
            <td>----------------</td>
          </tr>
          <tr>
            <td><a href="#">US120-000004</a></td>
            <td><a href="#">US120-000004</a></td>
            <td>----------------</td>
          </tr>
          <tr>
            <td><a href="#">US120-000005</a></td>
            <td><a href="#">US120-000005</a></td>
            <td>----------------</td>
          </tr>
        </table>
      </div><!--Search Results -->
      
    </form>
  </div>
</div>
<!--------------------------------------------- Branch Code Lookup END -------------------------------------------->

<!------------------------------------- Business Unit Lookup ------------------------------------------------------>
<div class="sample_popup" id="bu" style="visibility: hidden; display: none; z-index:2;">
  <div class="menu_form_header" id="bu_drag" style="width:450px; z-index:2; "> <img class="menu_form_exit"   id="bu_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Business Unit</div>
  <div class="menu_form_body" id="bubody"   style="width:450px; height:130px;">
    <form method="post" action="popup.php">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
          <td width="25%"><strong>Business Unit: </strong></td>
          <td><input name="textfield" type="text" class="inputBox" size="30" /></td>
        </tr>
        
        <tr>
          <td><strong>Company Name:</strong></td>
          <td><input name="textfield3" type="text" class="inputBox" size="30" /></td>
        </tr>
        <tr>
          <td colspan="2"><input name="Button" type="button" class="button1" value="Search" onClick="searchBU();"/>
            &nbsp;&nbsp;
            <input name="Button" type="button" class="button1" value="Cancel" onClick="hideBU();popupboxclose();" /></td>
        </tr>
      </table>
       
      <div id="busearchresults" style="visibility:hidden; display:none;"><!--Search Results -->
        <br>&nbsp;&nbsp;<strong>Search Results</strong>
        <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%">
          <tr>
            <th width="30%">Business Unit </th>
            <th width="70%">Company Name </th>
          </tr>
          <tr>
            <td><a href="#">USA01</a></td>
            <td>Caleb Brett USA, Inc</td>
          </tr>
          <tr>
            <td><a href="#">UK001</a></td>
            <td>ITS Testing Services (UK), Limited</td>
          </tr>
        </table>
      </div><!--Search Results -->
      
    </form>
  </div>
</div>
<!------------------------------------------- Business Unit Lookup END -------------------------------------------->

<!----------------------------------------------- Attach Popup ---------------------------------------------------->
<div class="sample_popup" id="attachpop" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="attachpop_drag" style="width:430px; "> 
    <img class="menu_form_exit"   id="attachpop_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Attach a File
  </div>
  <div class="menu_form_body"   style="width:430px; height:100px;overflow-y:hidden;">
  <%-- <form action="setup/popup.php" method="post" enctype="multipart/form-data">
      <table width="95%" border="0" align="center" class="InnerApplTable">
        <tr>
          <td>--%>
      <iframe align="left" frameborder="0" style="padding:0px;" height="210px;" width="100%" src="file_upload.htm?inputFieldId=uploadedFileName&formName=contractCreateForm" scrolling="auto" id="fileUpload" name="fileUpload" allowtransparency="yes" ></iframe>  
       <%--   </td>
        </tr>
      </table>
    </form>--%>
  </div>
</div>

<!----------------------------------- Service List Lookup Start --------------------------------------------------->

<div class="sample_popup" id="servicelist" style="visibility: hidden; display: none;">
  <form>
    <div class="menu_form_header" id="servicelist_drag" style="width:625px; "> 
      <a href="#"><img class="menu_form_exit"   id="servicelist_exit" src="images/form_exit.png" border="0" /></a> &nbsp;&nbsp;&nbsp;Service List  
    </div>
    <div class="menu_form_body" id="servicelistbody"   style="width:625px; height:225px;">
      <table width="95%" BORDER=0 align="center">
        <tr>
          <td width="47%">    
            <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
              <tr>
                <th>Service List</th>
              </tr>
              <tr>
                <td>
                  <SELECT NAME="list11" class="selectionBox" MULTIPLE SIZE=10 onDblClick="moveSelectedOptions(this.form['list11'],this.form['list21'],false)">
                    <OPTION VALUE="AGRI">AGRI DRY CARGO CHARGES</OPTION>
                    <OPTION VALUE="ATMOSPHERIC">ATMOSPHERIC MONITORING</OPTION>
                    <OPTION VALUE="BUNKER">BUNKER SURVEY</OPTION>
                    <OPTION VALUE="CALIBRATION">CALIBRATION CHARGES</OPTION>
                    <OPTION VALUE="CLOSED">CLOSED SYSTEM GAUGING OR SAMPLING EQUIPMENT</OPTION>
                    <OPTION VALUE="INSPECTION">INSPECTION HOURS/MILES/EXPENSES</OPTION>
                    <OPTION VALUE="LABORATORY">LABORATORY HOURS/MILES/EXPENSES</OPTION>
                  </SELECT>
                </td>
              </tr>
            </table>
          </td>
          <td width="6%" align="center">
            <table BORDER=0>
              <tr>
                <td>
                  <INPUT TYPE="button" NAME="right" VALUE="&gt;&gt;" onClick="moveSelectedOptions(document.forms[0]['list11'],document.forms[0]['list21'],false);return false;"><BR><BR>
                  <INPUT TYPE="button" NAME="left" VALUE="&lt;&lt;" onClick="moveSelectedOptions(document.forms[0]['list21'],document.forms[0]['list11'],false); return false;"><BR><BR>
                </td>
              </tr>
            </table>
          </td>
          <td width="47%">
            <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
              <tr>
                <th>Selected Services</th>
              </tr>
              <tr>
                <td>
                  <SELECT NAME="list21" class="selectionBox" MULTIPLE SIZE=10 onDblClick="moveSelectedOptions(this.form['list21'],this.form['list11'],false)">
                    <OPTION VALUE="INSPECTION">INSPECTION HOURS/MILES/EXPENSES</OPTION>
                    <OPTION VALUE="LABORATORY">LABORATORY HOURS/MILES/EXPENSES</OPTION>
                  </SELECT>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>

      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
          <td>
            <input name="Submit2" type="button" class="button1" value="Ok" onClick="hideservicelist();popupboxclose();showIt();"> &nbsp;&nbsp;
            <input name="Submit3" type="button" class="button1" value="Cancel" onClick="hideservicelist();popupboxclose();showIt();">
          </td>
        </tr>
      </table>
    </div>
  </form>
</div>

<!------------------------------------------- Service List Lookup END --------------------------------------------->


<!---------------------------------- Service Rates Lookup Start --------------------------------------------------->

<div class="sample_popup" id="servicerates" style="visibility: hidden; display: none;">
  <form>
    <div class="menu_form_header" id="servicerates_drag" style="width:900px; "> 
      <a href="#"><img class="menu_form_exit"   id="servicerates_exit" src="images/form_exit.png" border="0" /></a> &nbsp;&nbsp;&nbsp;Service Rates  
    </div>
    <div class="menu_form_body" id="serviceratesbody"   style="width:900px; height:700px;">
      <table border="0" cellpadding="0" cellspacing="0" width="95%" align="center" class="InnerApplTable">
        <tr>
          <td>
            <iframe align="left" frameborder="0" style="padding:0px; height:650px;" width="100%" src="inc_servicerateslookup.html" scrolling="no" id="countryfr" name="countryfr" allowtransparency="yes" ></iframe>
          </td>
        </tr>
        <tr>
          <td>
            <input name="Submit22" type="button" class="button1" value="Ok" onClick="hideservicerate();popupboxclose();showIt();">
            &nbsp;&nbsp;
            <input name="Submit32" type="button" class="button1" value="Cancel" onClick="hideservicerate();popupboxclose();showIt();">
          </td>
        </tr>
      </table>

    </div>
  </form>
</div>

<!-- ----------------------------------- Edit Product Group Set Start ------------------------------------- -->
<div class="sample_popup" id="editproductgroupset" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="editproductgroupset_drag" style="width:800px; "> 
  <img class="menu_form_exit"   id="editproductgroupset_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Edit Product Group Set</div>
  <div class="menu_form_body" id="editproductgroupsetbody"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="editproductgroupsetbox" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
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

