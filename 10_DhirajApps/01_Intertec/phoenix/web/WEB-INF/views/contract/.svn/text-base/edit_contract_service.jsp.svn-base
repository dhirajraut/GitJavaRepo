<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">      
  function doOperation(myOperationType, myHighLevelServiceId)
  {
    document.contractEditForm.operationType.value = myOperationType;
    document.contractEditForm.highLevelServiceId.value = myHighLevelServiceId;
    document.contractEditForm.submit();
  }

  function doEditRates(serviceName, serviceLevel, ceContractId, ceExpressionId)
  {
    document.contractEditForm.operationType.value = "editRates_init";
    document.contractEditForm.serviceName.value = serviceName;
    document.contractEditForm.serviceLevel.value = serviceLevel;
    document.contractEditForm.ceContractId.value = ceContractId;
    document.contractEditForm.ceExpressionId.value = ceExpressionId;
    
    document.contractEditForm.submit();
  }

  function saveAndSwitchServiceLevel(serviceName, serviceLevel, ceContractId, ceExpressionId)
  {
    var result = confirm("All the changes made in this level will be saved first. Are you sure you want to save and switch now?");
    if(result)
    {
      document.contractEditForm.operationType.value = "editRates_save_and_switch";
      document.contractEditForm.serviceName.value = serviceName;
      document.contractEditForm.serviceLevel.value = serviceLevel;
      document.contractEditForm.ceContractId.value = ceContractId;
      document.contractEditForm.ceExpressionId.value = ceExpressionId;

      document.contractEditForm.submit();
    }
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

<form:form name="contractEditForm" method="POST" action="edit_contract_service.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>

<input type="hidden" name="operationType" value="" />
<input type="hidden" name="cfgContractIndex" value="" />
<input type="hidden" name="goToContractTab" value="" />
<input type="hidden" name="highLevelServiceId" value="" />

<input type="hidden" name="ceContractId" value="" />
<input type="hidden" name="ceExpressionId" value="" />
<input type="hidden" name="serviceName" value="" />
<input type="hidden" name="serviceLevel" value="" />

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
              <li><a href="javascript:goToContractTab('main');" rel="tab6"><span><f:message key="contract"/> </span></a></li>
              <li><a href="javascript:goToContractTab('customer');" rel="tab6"><span><f:message key="contractCustomers"/></span></a></li>
              <li><a href="javascript:goToContractTab('attachment');" rel="tab6"><span><f:message key="contractAttachments"/></span></a></li>
              <li><a href="javascript:goToContractTab('zone');" rel="tab6"><span><f:message key="zones"/></span></a></li>
              <li><a href="javascript:goToContractTab('inspection');" rel="tab6"><span><f:message key="cargoInspection"/></span></a></li>
              <li><a href="#" rel="tab6"><span><f:message key="contractsServices"/></span></a></li>
              <li><a href="javascript:goToContractTab('test');" rel="tab6"><span><f:message key="contractTest"/></span></a></li>
              <li><a href="javascript:goToContractTab('slate');" rel="tab6"><span><f:message key="contractSlates"/></span></a></li>
              <li><a href="javascript:goToContractTab('crtnotes');" rel="tab6"><span><f:message key="contractNotes"/></span></a></li>
            </ul>
          </div>
          <!---------------------------- Sub Menus container. Do not remove ------------------------------------------------->
          <div id="tab_inner">
            <!-------------------------------------------------- TAB 6 CONTAINER ---------------------------------------------->
            <div id="tab6" class="innercontent" >
              <c:choose>
                <c:when test="${command.serviceRates != null}">
                  <%@ include file="contract_service_tab_edit_rates.jsp" %>
                </c:when>
                <c:otherwise>
                  <%@ include file="contract_service_tab.jsp" %>
                </c:otherwise>
              </c:choose>
            </div>
            <!------------------------------------------- TAB 6 CONTAINER END ------------------------------------------------->
          </div>
        </div>
        <script type="text/javascript">
          dolphintabs.init("tabnav", 5)      
        </script>
        <!------------------------------------------------------- TAB CONTENT END ----------------------------------------->
      </div>
      <!-------------------------------------------- MAIN CONTAINER END ------------------------------------------------->
    </td>
  </tr>
</table>
</form:form> 

<!-- --------------------------- Service List Lookup Start ------------------------------------------------- -->
<div class="sample_popup" id="servicelist" style="visibility: hidden; display: none;">
  <form>
    <div class="menu_form_header" id="servicelist_drag" style="width:750px; "> 
      <a href="#">
        <img class="menu_form_exit"   id="servicelist_exit" src="images/form_exit.png" border="0" />
      </a> 
      &nbsp;&nbsp;&nbsp;Service List  
    </div>
    <div class="menu_form_body" id="servicelistbody"   style="width:750px; height:520px;">
      <TABLE border="0" cellpadding="0" cellspacing="0" width="95%" align="center" class="InnerApplTable">
        <TR>
          <td>
            <iframe id="servicelistbox" align="left" frameborder="0" style="padding:0px; height:500px;" width="100%" src="blank.html" scrolling="auto" allowtransparency="yes" ></iframe>
          </td>
        </TR>
      </TABLE>
    </div>
  </form>
</div>
<!-- --------------------------------- Service List Lookup END ----------------------------------------- -->

<!-- --------------------------- Choose Apply Levels Start ------------------------------------------------- -->
<div class="sample_popup" id="chooseapplylevels" style="visibility: hidden; display: none;">
  <form>
    <div class="menu_form_header" id="chooseapplylevels_drag" style="width:700px; "> 
      <a href="#">
        <img class="menu_form_exit"   id="chooseapplylevels_exit" src="images/form_exit.png" border="0" />
      </a> 
      &nbsp;&nbsp;&nbsp;Service Rates  
    </div>
    <div class="menu_form_body" id="chooseapplylevelsbody"   style="width:700px; height:320px;">
      <TABLE border="0" cellpadding="0" cellspacing="0" width="95%" align="center" class="InnerApplTable">
        <TR>
          <td>
            <iframe id="chooseapplylevelsbox" align="left" frameborder="0" style="padding:0px; height:300px;" width="100%" src="blank.html" scrolling="auto" allowtransparency="yes" ></iframe>
          </td>
        </TR>
      </TABLE>
    </div>
  </form>
</div>
<!-- --------------------------------- Choose Apply Levels END ----------------------------------------- -->

<!-- --------------------------- Edit Question Notes Start ------------------------------------------------- -->
<div class="sample_popup" id="editquestionnotes" style="visibility: hidden; display: none;">
  <form>
    <div class="menu_form_header" id="editquestionnotes_drag" style="width:700px; "> 
      <a href="#">
        <img class="menu_form_exit"   id="editquestionnotes_exit" src="images/form_exit.png" border="0" />
      </a> 
      &nbsp;&nbsp;&nbsp;Service Rates  
    </div>
    <div class="menu_form_body" id="editquestionnotesbody"   style="width:700px; height:320px;">
      <TABLE border="0" cellpadding="0" cellspacing="0" width="95%" align="center" class="InnerApplTable">
        <TR>
          <td>
            <iframe id="editquestionnotesbox" align="left" frameborder="0" style="padding:0px; height:300px;" width="100%" src="blank.html" scrolling="auto" allowtransparency="yes" ></iframe>
          </td>
        </TR>
      </TABLE>
    </div>
  </form>
</div>
<!-- --------------------------------- Edit Question Notes END ----------------------------------------- -->

<script>
  <c:if test="${requestScope.runPopChooseLevelsToApply != null}">
    popChooseApplyLevels();
  </c:if>
</script>
