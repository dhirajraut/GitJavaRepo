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

<form:form name="contractEditForm" method="POST" action="edit_contract_zone.htm">
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
              <li><a href="javascript:goToContractTab('main');" rel="tab4"><span><f:message key="contract"/> </span></a></li>
              <li><a href="javascript:goToContractTab('customer');" rel="tab4"><span><f:message key="contractCustomers"/></span></a></li>
              <li><a href="javascript:goToContractTab('attachment');" rel="tab4"><span><f:message key="contractAttachments"/></span></a></li>
              <li><a href="#" rel="tab4"><span><f:message key="zones"/></span></a></li>
              <li><a href="javascript:goToContractTab('inspection');" rel="tab4"><span><f:message key="cargoInspection"/></span></a></li>
              <li><a href="javascript:goToContractTab('service');" rel="tab4"><span><f:message key="contractsServices"/></span></a></li>
              <li><a href="javascript:goToContractTab('test');" rel="tab4"><span><f:message key="contractTest"/></span></a></li>
              <li><a href="javascript:goToContractTab('slate');" rel="tab4"><span><f:message key="contractSlates"/></span></a></li>
              <li><a href="javascript:goToContractTab('ctrnotes');" rel="tab4"><span><f:message key="contractNotes"/></span></a></li>
            </ul>
          </div>
          <!---------------------------- Sub Menus container. Do not remove ------------------------------------------------->
          <div id="tab_inner">
            <!------------------------------------------------ TAB 4 CONTAINER ------------------------------------------------>
            <div id="tab4" class="innercontent" >
            <%@ include file="contract_zone_tab.jsp" %>
            </div>
            <!---------------------------------------------- TAB 4 CONTAINER END ---------------------------------------------->
          </div>
        </div>
        <script type="text/javascript">
          dolphintabs.init("tabnav", 3)      
        </script>
        <!------------------------------------------------------- TAB CONTENT END ----------------------------------------->
      </div>
      <!-------------------------------------------- MAIN CONTAINER END ------------------------------------------------->
    </td>
  </tr>
</table>
</form:form> 

<script type="text/javascript">

  <c:forEach items="${command.zoneExtList}" var="zoneExt" varStatus="zoneExtStatus">   
    <c:choose>
      <c:when test="${zoneExt.displayStatus == 'HIDE'}">
        hidezoneWithIndex('${zoneExtStatus.index}');
      </c:when>
      <c:otherwise>
        showzoneWithIndex('${zoneExtStatus.index}');
      </c:otherwise>
    </c:choose>        
  </c:forEach>

</script>

<!-----------------------------------------Branch Code Lookup----------------------------------------------------->
<div class="sample_popup" id="jobbranchcode" style="visibility: hidden; display: none;">
     <div class="menu_form_header" id="jobbranchcode_drag" style="width:750px; "> 
          <a href="#">
             <img class="menu_form_exit" id="jobbranchcode_exit" src="images/form_exit.png"/> &nbsp;&nbsp;&nbsp;
                <f:message key="searchBranchCode"/></div>
                 <div class="menu_form_body" id="jobbranchcodebody"   style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
                 <form method="post" action="popup.php">     
               <iframe align="left" id="jobbu" frameborder="0" style="padding:10px;" height="530px;" width="100%"  scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" src="blank.html">
       </iframe>
      </form>
   </div>
</div>
<!-----------------------------------------Branch Code Lookup END------------------------------------------------->

<!-- --------------------------- Branch List Lookup Start ------------------------------------------------- -->
<div class="sample_popup" id="branchlist" style="visibility: hidden; display: none;">
  <form>
    <div class="menu_form_header" id="branchlist_drag" style="width:900px; "> 
      <a href="#">
        <img class="menu_form_exit"   id="branchlist_exit" src="images/form_exit.png" border="0" />
      </a> 
      &nbsp;&nbsp;&nbsp;Branch List  
    </div>
    <div class="menu_form_body" id="branchlistbody"   style="width:900px; height:520px;">
      <TABLE border="0" cellpadding="0" cellspacing="0" width="95%" align="center" class="InnerApplTable">
        <TR>
          <td>
            <iframe id="branchlistbox" align="left" frameborder="0" style="padding:0px; height:500px;" width="100%" src="blank.html" scrolling="auto" allowtransparency="yes" ></iframe>
          </td>
        </TR>
      </TABLE>
    </div>
  </form>
</div>
<!-- --------------------------------- Branch List Lookup END ----------------------------------------- -->

<!-- --------------------------- Zone Description List Lookup Start ------------------------------------------------- -->
<div class="sample_popup" id="zonedesclist" style="visibility: hidden; display: none;">
  <form>
    <div class="menu_form_header" id="zonedesclist_drag" style="width:900px; "> 
      <a href="#">
        <img class="menu_form_exit"   id="zonedesclist_exit" src="images/form_exit.png" border="0" />
      </a> 
      &nbsp;&nbsp;&nbsp;<f:message key="Zone Description List"/>  
    </div>
    <div class="menu_form_body" id="zonedesclistbody"   style="width:900px; height:520px;">
      <TABLE border="0" cellpadding="0" cellspacing="0" width="95%" align="center" class="InnerApplTable">
        <TR>
          <td>
            <iframe id="zonedesclistbox" align="left" frameborder="0" style="padding:0px; height:500px;" width="100%" src="blank.html" scrolling="auto" allowtransparency="yes" ></iframe>
          </td>
        </TR>
      </TABLE>
    </div>
  </form>
</div>
<!-- --------------------------------- Zone Description List Lookup END ----------------------------------------- -->
