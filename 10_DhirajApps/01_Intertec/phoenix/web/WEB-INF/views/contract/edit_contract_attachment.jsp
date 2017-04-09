<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script language="javascript">      
  function doOperation(myOperationType)
  {
    document.contractEditForm.operationType.value = myOperationType;
    document.contractEditForm.submit();
  }

  function onAttachDelete(index)
  {
    document.contractEditForm.deleteAttach.value = "deleteAttach";
    document.contractEditForm.tabName.value = "2";
    document.contractEditForm.contractAttachIndex.value = index;
    document.contractEditForm.submit();

  }
  
  function showAttach(filename,index)
  {
    document.contractEditForm.showAttachFile.value="showAttach";
    document.contractEditForm.tabName.value = "2";
    document.contractEditForm.contractAttachIndex.value = index;
    document.contractEditForm.submit();
  }

  function resetform()
  {
    document.contractEditForm.showAttachFile.value="none";
    //document.contractEditForm.submit();
  }

  function getMyFormName()
  {
    return "contractEditForm";
  }
  
  function goToContractTab(tabName)
  {
    document.contractEditForm.operationType.value = "switchTab";
    document.contractEditForm.goToContractTab.value = tabName;    
    document.contractEditForm.submit();
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

<form:form name="contractEditForm" method="POST" action="edit_contract_attachment.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>
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
<input type="hidden" name="goToContractTab" value="" />

<c:set var="userDateFormat" value="${icbfn:userDateFormat()}" />

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
      <!------------------------------------------------ MAIN CONTAINER ------------------------------------------------->
      <div id="MainContentContainer">
      <!------------------------------------------------ TABS CONTENTS -------------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="javascript:goToContractTab('main');" rel="tab3"><span><f:message key="contract"/> </span></a></li>
              <li><a href="javascript:goToContractTab('customer');" rel="tab3"><span><f:message key="contractCustomers"/></span></a></li>
              <li><a href="#" rel="tab3"><span><f:message key="contractAttachments"/></span></a></li>
              <li><a href="javascript:goToContractTab('zone');" rel="tab3"><span><f:message key="zones"/></span></a></li>
              <li><a href="javascript:goToContractTab('inspection');" rel="tab3"><span><f:message key="cargoInspection"/></span></a></li>
              <li><a href="javascript:goToContractTab('service');" rel="tab3"><span><f:message key="contractsServices"/></span></a></li>
              <li><a href="javascript:goToContractTab('test');" rel="tab3"><span><f:message key="contractTest"/></span></a></li>
              <li><a href="javascript:goToContractTab('slate');" rel="tab3"><span><f:message key="contractSlates"/></span></a></li>
              <li><a href="javascript:goToContractTab('crtnotes');" rel="tab3"><span><f:message key="contractNotes"/></span></a></li>
            </ul>
          </div>
          <!---------------------------- Sub Menus container. Do not remove ------------------------------------------------->
          <div id="tab_inner">
            <!---------------------------------------------- TAB 3 CONTAINER -------------------------------------------------->
            <div id="tab3" class="innercontent" >
            <%@ include file="contract_attachment_tab.jsp" %>
            </div>
            <!---------------------------------------------- TAB 3 CONTAINER END ---------------------------------------------->
          </div>
        </div>
        <script type="text/javascript">
          dolphintabs.init("tabnav", 2)      
        </script>
        <!------------------------------------------------------- TAB CONTENT END ----------------------------------------->
      </div>
      <!-------------------------------------------- MAIN CONTAINER END ------------------------------------------------->
    </td>
  </tr>
</table>
</form:form> 

<!---------------------------------------------- Attach Popup ----------------------------------------------------->
<div class="sample_popup" id="attachpop" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="attachpop_drag" style="width:430px; "> 
    <img class="menu_form_exit"   id="attachpop_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="attachaFile"/>
  </div>
  <div class="menu_form_body"   style="width:430px; height:100px;overflow-y:hidden;">
            <iframe align="left" frameborder="0" style="padding:0px;" height="210px;" width="100%" src="file_upload.htm?inputFieldId=uploadedFileName&formName=contractEditForm" scrolling="auto" id="fileUpload" name="fileUpload" allowtransparency="yes" ></iframe>  
  </div>
</div>
