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
    document.contractEditForm.operationType.value = myOperationType;
    document.contractEditForm.submit();
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

<c:set var="userDateFormat" value="${icbfn:userDateFormat()}" />

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
  <icb:item>${command.contract.workingPB}</icb:item>
</icb:list>

<form:form name="contractEditForm" method="POST" action="edit_contract_test.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>

<input type="hidden" name="operationType" value="" />
<input type="hidden" name="goToContractTab" value="" />

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
      <!------------------------------------------------ MAIN CONTAINER ------------------------------------------------->
      <div id="MainContentContainer">
      <!------------------------------------------------ TABS CONTENTS -------------------------------------------------->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="javascript:goToContractTab('main');" rel="tab7"><span><f:message key="contract"/> </span></a></li>
              <li><a href="javascript:goToContractTab('customer');" rel="tab7"><span><f:message key="contractCustomers"/></span></a></li>
              <li><a href="javascript:goToContractTab('attachment');" rel="tab7"><span><f:message key="contractAttachments"/></span></a></li>
              <li><a href="javascript:goToContractTab('zone');" rel="tab7"><span><f:message key="zones"/></span></a></li>
              <li><a href="javascript:goToContractTab('inspection');" rel="tab7"><span><f:message key="cargoInspection"/></span></a></li>
              <li><a href="javascript:goToContractTab('service');" rel="tab7"><span><f:message key="contractsServices"/></span></a></li>
              <li><a href="#" rel="tab7"><span><f:message key="contractTest"/></span></a></li>
              <li><a href="javascript:goToContractTab('slate');" rel="tab7"><span><f:message key="contractSlates"/></span></a></li>
              <li><a href="javascript:goToContractTab('crtnotes');" rel="tab7"><span><f:message key="contractNotes"/></span></a></li>
            </ul>
          </div>
          <!---------------------------- Sub Menus container. Do not remove ------------------------------------------------->
          <div id="tab_inner">
            <!------------------------------------------------ TAB 7 CONTAINER ------------------------------------------------>
            <div id="tab7" class="innercontent" >
            <%@ include file="contract_test_tab.jsp" %>
            </div>
            <!----------------------------------------------- TAB 7 CONTAINER END --------------------------------------------->
          </div>
        </div>
        <script type="text/javascript">
          dolphintabs.init("tabnav", 6)      
        </script>
        <!------------------------------------------------------- TAB CONTENT END ----------------------------------------->
      </div>
      <!-------------------------------------------- MAIN CONTAINER END ------------------------------------------------->
    </td>
  </tr>
</table>
</form:form> 

<script type="text/javascript">

  <c:if test="${command.activeId != null}">
    <c:choose>
      <c:when test="${command.activeType == 'PB'}">
        <c:forEach items="${command.testPriceExtList}" var="testPriceExt" varStatus="testPriceExtStatus">   
          <c:if test="${testPriceExt.testId == command.activeId}">
            showPriceBookTestPriceWithIndex('${testPriceExtStatus.index}');
          </c:if>
        </c:forEach>
      </c:when>
      <c:when test="${command.activeType == 'Contract'}">
        <c:forEach items="${command.contractTestPriceExtList}" var="contractTestPriceExt" varStatus="contractTestPriceExtStatus">   
          <c:if test="${contractTestPriceExt.testId == command.activeId}">
            showContractTestPriceWithIndex('${contractTestPriceExtStatus.index}');
          </c:if>
        </c:forEach>
      </c:when>
    </c:choose>
  </c:if>
  
</script>

<!-- ----------------------------------- Copy PB Test Price Start ------------------------------------- -->
<div class="sample_popup" id="copypbtestprice" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="copypbtestprice_drag" style="width:800px; "> 
  <img class="menu_form_exit"   id="copypbtestprice_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="CopyPbTestPrice" /></div>
  <div class="menu_form_body" id="copypbtestpricebody"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="copypbtestpricebox" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
<!-- --------------------------------- Copy PB Test Price END ----------------------------------------- -->

<!-- ----------------------------------- Modify Test Prices Start ------------------------------------- -->
<div class="sample_popup" id="modifytestprices" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="modifytestprices_drag" style="width:800px; "> 
  <img class="menu_form_exit"   id="modifytestprices_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="ModifyTestPrices" /></div>
  <div class="menu_form_body" id="modifytestpricesbody"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="modifytestpricesbox" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
<!-- --------------------------------- Modify Test Prices END ----------------------------------------- -->

<!-- ----------------------------------- Create Contract Test Start ------------------------------------- -->
<div class="sample_popup" id="createcontracttest" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="createcontracttest_drag" style="width:800px; "> 
  <img class="menu_form_exit"   id="createcontracttest_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="CreateContractTest" /></div>
  <div class="menu_form_body" id="createcontracttestbody"   style="width:800px; height:auto">
    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="createcontracttestbox" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>
  
  </div>
</div>
<!-- --------------------------------- Create Contract Test END ----------------------------------------- -->
