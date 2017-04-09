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

<icb:list var="localContractCode">
  <icb:item>${command.contract.contractCode}</icb:item>
</icb:list>

<form:form name="contractEditForm" method="POST" action="edit_contract_inspection.htm">
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
              <li><a href="javascript:goToContractTab('main');" rel="tab5"><span><f:message key="contract"/> </span></a></li>
              <li><a href="javascript:goToContractTab('customer');" rel="tab5"><span><f:message key="contractCustomers"/></span></a></li>
              <li><a href="javascript:goToContractTab('attachment');" rel="tab5"><span><f:message key="contractAttachments"/></span></a></li>
              <li><a href="javascript:goToContractTab('zone');" rel="tab5"><span><f:message key="zones"/></span></a></li>
              <li><a href="#" rel="tab5"><span><f:message key="cargoInspection"/></span></a></li>
              <li><a href="javascript:goToContractTab('service');" rel="tab5"><span><f:message key="contractsServices"/></span></a></li>
              <li><a href="javascript:goToContractTab('test');" rel="tab5"><span><f:message key="contractTest"/></span></a></li>
              <li><a href="javascript:goToContractTab('slate');" rel="tab5"><span><f:message key="contractSlates"/></span></a></li>
              <li><a href="javascript:goToContractTab('crtnotes');" rel="tab5"><span><f:message key="contractNotes"/></span></a></li>
            </ul>
          </div>
          <!---------------------------- Sub Menus container. Do not remove ------------------------------------------------->
          <div id="tab_inner">
            <!-------------------------------------------------- TAB 5 CONTAINER ---------------------------------------------->
            <div id="tab5" class="innercontent" >
            <%@ include file="contract_inspection_tab.jsp" %>
            </div>
            <!-------------------------------------------- TAB 5 CONTAINER END ------------------------------------------------>
          </div>
        </div>
        <script type="text/javascript">
          dolphintabs.init("tabnav", 4)      
        </script>
        <!------------------------------------------------------- TAB CONTENT END ----------------------------------------->
      </div>
      <!-------------------------------------------- MAIN CONTAINER END ------------------------------------------------->
    </td>
  </tr>
</table>
</form:form> 

<!-- --------------------------- Product List Lookup Start ------------------------------------------------- -->
<div class="sample_popup" id="productlist" style="visibility: hidden; display: none;">
  <form>
    <div class="menu_form_header" id="productlist_drag" style="width:900px; "> 
      <a href="#">
        <img class="menu_form_exit"   id="productlist_exit" src="images/form_exit.png" border="0" />
      </a> 
      &nbsp;&nbsp;&nbsp;Product List  
    </div>
    <div class="menu_form_body" id="productlistbody"   style="width:900px; height:520px;">
      <TABLE border="0" cellpadding="0" cellspacing="0" width="95%" align="center" class="InnerApplTable">
        <TR>
          <td>
            <iframe id="productlistbox" align="left" frameborder="0" style="padding:0px; height:500px;" width="100%" src="blank.html" scrolling="auto" allowtransparency="yes" ></iframe>
          </td>
        </TR>
      </TABLE>
    </div>
  </form>
</div>
<!-- --------------------------------- Product List Lookup END ----------------------------------------- -->

<!-- --------------------------- Update Uom Notes Lookup Start ------------------------------------------------- -->
<div class="sample_popup" id="updateuomnotes" style="visibility: hidden; display: none;">
  <form>
    <div class="menu_form_header" id="updateuomnotes_drag" style="width:900px; "> 
      <a href="#">
        <img class="menu_form_exit"   id="updateuomnotes_exit" src="images/form_exit.png" border="0" />
      </a> 
      &nbsp;&nbsp;&nbsp;Unit of Measure Notes  
    </div>
    <div class="menu_form_body" id="updateuomnotesbody"   style="width:900px; height:520px;">
      <TABLE border="0" cellpadding="0" cellspacing="0" width="95%" align="center" class="InnerApplTable">
        <TR>
          <td>
            <iframe id="updateuomnotesbox" align="left" frameborder="0" style="padding:0px; height:500px;" width="100%" src="blank.html" scrolling="auto" allowtransparency="yes" ></iframe>
          </td>
        </TR>
      </TABLE>
    </div>
  </form>
</div>
<!-- --------------------------------- Update Uom Notes Lookup END ----------------------------------------- -->
