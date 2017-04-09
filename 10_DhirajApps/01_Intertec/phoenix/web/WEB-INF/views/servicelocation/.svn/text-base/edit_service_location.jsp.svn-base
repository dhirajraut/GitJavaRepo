<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<head>
<script language="javascript">
function populateBranchFields()
{
  var branch = document.getElementById("barnchType").value;
  var branch_array=branch.split(",");
  var newBranch ='';
  if(branch_array.length > 1)
  {
    for(var i=0;i<(branch_array.length-1);i++)
    {
    if(newBranch=='')
      newBranch = branch_array[i];
    else
      newBranch = newBranch + "," + branch_array[i];
    }
 
   document.getElementById("barnchType").value = newBranch;
  
  }
  document.serviceLocationEditForm.existingBranchFlag.value = "Y";
  document.serviceLocationEditForm.submit();

}
 function setflag(branchtypeflag,rowIndex)
 {   
        document.serviceLocationEditForm.existingBranchFlag.value="Y";
 }
 function populateLabelFields()
{
  var ccode=document.getElementById("sel2").value;
  
  document.serviceLocationEditForm.labelFlag.value =ccode;
  
  document.serviceLocationEditForm.submit();

}
function updateTaxCodeIframeSrc(){  document.getElementById('searchVatRateFr').setAttribute("src","search_vat_rate_popup.htm?inputFieldId=taxCode&div1=vatcode&div2=vatcodebody&searchForm=servloc&taxType=S");
}
</script>
</head>

<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>

<form:form name="serviceLocationEditForm" method="POST" action="edit_service_location.htm">
<%--<form:hidden path="serviceLocation.id" />--%>

<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="existingBranchFlag"/>
 <form:hidden path="branchTypeFlag" />
  <form:hidden path="labelFlag"/>
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span>
              <authz:authorize ifAnyGranted="CreateServiceLocation">
              <f:message key="editServiceLocation"/>
              </authz:authorize>
              <authz:authorize ifNotGranted="CreateServiceLocation">
              <f:message key="viewServiceLocation"/>
              </authz:authorize>
              </span></a></li>
            </ul>
      </div>
       <!-- Sub Menus container. Do not remove -->
       <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="mainApplTable">
         <tr>
            <th width="20%" colspan="2"><f:message key="serviceLocation"/></th>
            <th width="20%" colspan="1" ><strong><f:message key="status"/>:
             <form:select cssClass="selectionBox" id="sel1" path="serviceLocation.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>
            </strong></th>
            <th width="15%" colspan="4" style="text-align:right"><a href="search_service_location.htm"><img src="images/icofindjob.gif" alt="Search" width="14" height="14" border="0" align="absmiddle"></a>&nbsp 
            <authz:authorize ifAnyGranted="CreateServiceLocation">
            <a href="document.serviceLocationEditForm.submit();"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" align="absmiddle"></a>
            </authz:authorize>
            </th>
         
        </tr>
        <tr>
            <td class="TDShade"><strong>
            <f:message key="serviceLocationCode"/>: </strong></td>
             <td width="10%" class="TDShadeBlue">${command.serviceLocation.serviceLocationCode }</td> 
            
            <td class="TDShade" width="5%" ><strong><f:message key="branchCode"/>:</strong></td>
            <td width="10%" class="TDShadeBlue" colspan="1">
            <form:input id="barnchType" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" cssClass="inputBox" size="30" maxlength="8" path="serviceLocation.branchName" disabled="${command.viewOnly}"/>
             <form:errors path="serviceLocation.branchName" cssClass="redstar"/> 
              <c:if test="${not command.viewOnly}">
                   <a href="#" onClick="javascript:setflag('addServiceBranch','0');popup_show('branchcode', 'branchcode_drag', 'branchcode_exit', 'screen-corner', 120, 20);hideIt();showbranchcode('branchcode','branchcodebody');popupboxenable()">
                          <img src="images/lookup.gif" alt="<f:message key="lookupBranch"/>" width="13" height="13" border="0">
                   </a>
              </c:if>
            </td>
          </tr>
        
        <tr>
          <td width="20%" class="TDShade"><strong><f:message key="serviceLocation"/> :</strong></td>
            <td width="30%" class="TDShadeBlue">
            
            <form:input cssClass="inputBox" size="30" maxlength="254" path="serviceLocation.name"/>
            <form:errors path="serviceLocation.name" cssClass="redstar"/></td> 
            <td class="TDShade" width="5%" ><strong><f:message key="shipToCustomerId"/>:</strong></td>
           <td class="TDShadeBlue" width="40%" colspan="2">${command.serviceLocation.custCode}</td>
        </tr>

        <tr class="InnerApplTable">
        <td width="20%" class="TDShade"><f:message key="country"/><strong> :<span class="redstar">*</span></strong></td>
         <td class="TDShadeBlue" >
        <form:select cssClass="selectionBox" id="sel2" path="serviceLocation.countryCode" onchange="populateLabelFields()" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />
             <form:errors path="serviceLocation.countryCode" cssClass="redstar"/>
         </td>


      <c:choose>
      <c:when test="${command.address1Label==null}">
      <td class="TDShade" width="5%"></td>
      <td class="TDShadeBlue"></td>
      </c:when>
      <c:otherwise>

       <td class="TDShade"><strong>${command.address1Label}</strong></td>
        
       <td class="TDShadeBlue">
        
        <form:input cssClass="inputBox" size="30" maxlength="55" path="serviceLocation.address1" />
          <form:errors path="serviceLocation.address1" cssClass="redstar"/></td>
         
       
       </c:otherwise>
      </c:choose>  
      </tr>
      <tr>
      <c:choose>
      <c:when test="${command.address2Label==null}">
      <td class="TDShade" width="10%"></td>
      <td class="TDShadeBlue"></td>
      </c:when>
      <c:otherwise>

        <td class="TDShade"><strong>${command.address2Label}</strong></td>
        <td class="TDShadeBlue"><label>
         
        <form:input cssClass="inputBox" size="30" maxlength="55" path="serviceLocation.address2" />
          <form:errors path="serviceLocation.address2" cssClass="redstar"/>
        </label>
        </td> 
        
       
       </c:otherwise>
      </c:choose>  



      <c:choose>
      <c:when test="${command.address3Label==null}">
      <td class="TDShade" width="5%"></td>
      <td class="TDShadeBlue"></td>
      </c:when>
      <c:otherwise>

       <td class="TDShade"><strong>${command.address3Label}</strong></td>
        
       <td class="TDShadeBlue">
        
        <form:input cssClass="inputBox" size="30" maxlength="55" path="serviceLocation.address3" />
          <form:errors path="serviceLocation.address3" cssClass="redstar"/></td>
         
       
       </c:otherwise>
      </c:choose>  
      </tr>
      <tr>

      <c:choose>
      <c:when test="${command.address4Label==null}">
      <td class="TDShade" width="10%"></td>
      <td class="TDShadeBlue"></td>
      </c:when>
      <c:otherwise>

        <td class="TDShade"><strong>${command.address4Label}</strong></td>
        <td class="TDShadeBlue"><label>
         
        <form:input cssClass="inputBox" size="30" maxlength="55" path="serviceLocation.address4" />
          <form:errors path="serviceLocation.address4" cssClass="redstar"/>
        </label>
        </td> 
        
       
       </c:otherwise>
      </c:choose>  
       <td class="TDShade" width="5%"><strong><f:message key="city"/>:</strong></td>
       <td class="TDShadeBlue" width="40%" ><label>
       <form:input cssClass="inputBox" size="30" maxlength="30" path="serviceLocation.city" />
        <form:errors path="serviceLocation.city" cssClass="redstar"/>
      </label></td>

        </tr>
       <tr>
        <c:choose>
      <c:when test="${command.countyLabel==null}">
      <td class="TDShade" width="10%"></td>
      <td class="TDShadeBlue"></td>
      </c:when>
      <c:otherwise>

       <td class="TDShade"><strong>${command.countyLabel}</strong></td>
        
       <td class="TDShadeBlue">
        
        <form:input cssClass="inputBox" size="30" maxlength="2" path="serviceLocation.houseType" />
          <form:errors path="serviceLocation.houseType" cssClass="redstar"/></td>
         
       
       </c:otherwise>
      </c:choose>  

      <c:choose>
      <c:when test="${command.stateLabel==null}">
      <td class="TDShade" width="10%"></td>
      <td class="TDShadeBlue"></td>
      </c:when>
      <c:otherwise>
      <td class="TDShade">${command.stateLabel}</td>
      <td colspan="1" class="TDShadeBlue" width="40%">
      <icb:list var="countryCodeList">
        <icb:item>${command.serviceLocation.countryCode}</icb:item>
      </icb:list> 
      <form:select id="sel3" cssClass="selectionBox" path="serviceLocation.stateCode" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value"/>
      <form:errors path="serviceLocation.stateCode" cssClass="redstar"/>
      </td>
      </c:otherwise>
      </c:choose> 
          </tr>
      <tr>
      <c:choose>
      <c:when test="${command.postalLabel==null}">
      <td class="TDShade" width="10%"></td>
      <td class="TDShadeBlue"></td>
      </c:when>
      <c:otherwise>
       <td class="TDShade"><strong>${command.postalLabel}</strong></td>
      <td class="TDShadeBlue" colspan="1">
       
       <form:input cssClass="inputBox" size="30" maxlength="12" path="serviceLocation.postal" />
        <form:errors path="serviceLocation.postal" cssClass="redstar"/>
      
      </td> 
      </c:otherwise>
      </c:choose> 
          <td width="5%" class="TDShade"><strong><f:message key="emailAddress"/>:</strong> </td>
          <td class="TDShadeBlue" width="30%"><form:input cssClass="inputBox" maxlength="70" size="30" path="serviceLocation.email" />
            <form:errors path="serviceLocation.email" cssClass="redstar"/></td> 
     </tr>
           
        <tr>
         <td width="24%" class="TDShade"><strong><f:message key="telephoneNo"/>:</strong></td>
          <td class="TDShadeBlue" width="30%" colspan=""><form:input cssClass="inputBox" maxlength="32" size="30" path="serviceLocation.phone" />
            <form:errors path="serviceLocation.phone" cssClass="redstar"/></td>
          <td class="TDShade" width="5%"><f:message key="taxCode"/></td>
      <td class="TDShadeBlue" width="40%" colspan="3"><form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" cssClass="inputBox" maxlength="32" size="30" path="taxCode" />
        <form:errors path="taxCode" cssClass="redstar"/><a href="#" onClick="javascript:updateTaxCodeIframeSrc();popup_show('vatcode', 'vatcode_drag', 'vatcode_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('vatcode','vatcodebody');popupboxenable();">

<img src="images/lookup.gif" alt="lookup country" width="13" height="13" border="0" /></td>
          
        </tr>

      
    </table>
     <ajax:autocomplete
      baseUrl="branch.htm"
      source="serviceLocation.branchName"
      target="serviceLocation.branchName"
      className="autocomplete"
      parameters="name={serviceLocation.branchName}"
	  postFunction="populateBranchFields"
      minimumCharacters="1"
       />
     
     <ajax:autocomplete
      baseUrl="customer.htm"
      source="serviceLocation.custCode"
      target="serviceLocation.custCode"
      className="autocomplete"
      parameters="custCode={serviceLocation.custCode}"
      minimumCharacters="1"
       />
       <ajax:autocomplete
    baseUrl="customer.htm"
    source="taxCode"
    target="taxCode"
    className="autocomplete"
    parameters="taxCode={taxCode}"
    minimumCharacters="1"
    /> 
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry"/></span> </td>
                        <td style="text-align:right"><a href="search_service_location.htm"><img src="images/icofindjob.gif" alt="Search" width="14" height="14" border="0" align="absmiddle"></a>&nbsp 
                        <authz:authorize ifAnyGranted="CreateServiceLocation">
                          <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a>
                          </authz:authorize>
                        </td>
                      </tr>
                    </table></td>
                </tr>
              </table>
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
<!-- --------------------------- Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="branchcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="branchcode_drag" style="width:750px; "> 
    <img class="menu_form_exit"   id="branchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/>
  </div>
  <div class="menu_form_body" id="branchcodebody"style="width:750px; height:500px;overflow-y:hidden;padding-left:4px;">
    <form method="post" action="popup.php">
     
            <iframe align="left:5px" frameborder="0" style="padding:5px;" height="500px;" width="100%" src="search_branch_popup.htm?inputFieldId=serviceLocation.branchName&div1=branchcode&div2=branchcodebody&formName=serviceLocationCreateForm" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" ></iframe>
              
    </form>
  </div>
</div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->
<!----------------------------------------------------------------------------------- VAT Code Lookup ----------------------------------------------------------------------------------------------------->
<div class="sample_popup" id="vatcode" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="vatcode_drag" style="width:750px;height:auto; "> <img class="menu_form_exit"   id="vatcode_exit" src=" images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="taxCode"/></div>
<div class="menu_form_body" id="vatcodebody"   style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  scrolling="auto" id="searchVatRateFr" name="searchVatRateFr" allowtransparency="yes" src="blank.html"></iframe>
</div>  
</div>
<!------------------------------------------------------------------------------------ VAT Code Lookup END ----------------------------------------------------------------------------------------------->
