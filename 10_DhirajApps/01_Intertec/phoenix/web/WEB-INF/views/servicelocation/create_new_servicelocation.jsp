
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
  uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

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
  document.createNewTowingcompanyForm.existingBranchFlag.value = "Y";
  
  document.createNewTowingcompanyForm.submit();

}
 function setflag(branchtypeflag,rowIndex)
 {   
        document.createNewTowingcompanyForm.existingBranchFlag.value="Y";
 }

 function populateLabelFields()
{
  
  var ccode=document.getElementById("sel2").value;
  
  document.createNewTowingcompanyForm.labelFlag.value =ccode;
  
  
  document.createNewTowingcompanyForm.submit();

}
function submitform()
{
  top.document.forms[0].submit();
}
function updateTaxCodeIframeSrc(){  document.getElementById('searchVatRateFr').setAttribute("src","search_vat_rate_popup.htm?inputFieldId=taxCode&div1=vatcode&div2=vatcodebody&searchForm=servloc");
}

</script>
</head>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<form:form name="createNewTowingcompanyForm" method="POST"
  action="create_new_servicelocation.htm">
  <div style="color:red;"><form:errors cssClass="error" /></div>
  <form:hidden path="inputFieldId" />
   <form:hidden path="labelFlag"/>  
  <form:hidden path="existingBranchFlag"/> 
  
  <table width="100%" border="0" cellpadding="0" cellspacing="0"
    class="MainTable">
    <tr>
      <td valign="top"><!-- MAIN CONTAINER -->
      <div id="MainContentContainer"><!-- TABS CONTENTS --> <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
      <div id="tab1" class="innercontent">
      <table border="0" align="center" cellpadding="1" cellspacing="0"
        class="mainApplTable">
        <tr>
          <th colspan="12" width="100%"><f:message
            key="createNewServiceLocation" /></th>
        </tr>
        <tr>
          <td width="10%" colspan="1" class="TDShade" nowrap><strong><strong><f:message
            key="serviceLocationCode" /> <strong>:</strong></td>


          <c:choose>
          <c:when test="${command.branchCode!=null}">
          <td class="TDShadeBlue">&nbsp;</td>
          </c:when>
          <c:otherwise>
          <td class="TDShadeBlue">${command.serviceLocation.serviceLocationCode}</td>
           </c:otherwise>
          </c:choose>  
           <td width="23%" class="TDShade"><strong><f:message key="branchCode"/>:</strong></td>
          <td width="30%" class="TDShadeBlue" colspan="1">
          <form:input id="barnchType" cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" maxlength="8" path="serviceLocation.branchName" disabled="${command.viewOnly}"/>
          <form:errors path="serviceLocation.branchName" cssClass="redstar"/>
        </td>
        </tr>
        <tr>
        <td class="TDShade" colspan="1"><strong nowrap><f:message
            key="serviceLocation" />:<span class="redstar">*</span></strong></td>
          <td class="TDShadeBlue"><form:input cssClass="inputBox"
            maxlength="254" path="serviceLocation.name" /> <form:errors
            path="serviceLocation.name" cssClass="redstar" /></td>
        
        <td width="10%" class="TDShade"><f:message key="status" /><strong>:</strong></td>
          <td class="TDShadeBlue"><form:select id="sel1"
            cssClass="selectionBox" path="serviceLocation.status"
            items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name"
            itemValue="value" /> <form:errors path="serviceLocation.status"
            cssClass="redstar" /></td>
        </tr>
        <tr class="InnerApplTable">
          <td width="10%" class="TDShade"><f:message key="country" /><strong>
          :</strong></td>
          <td colspan="1" class="TDShadeBlue"><form:select id="sel2" 
            cssClass="selectionBox" path="serviceLocation.countryCode"
            items="${icbfn:dropdown('country', null)}" itemLabel="name"
            itemValue="value" onchange="populateLabelFields()"/> <form:errors
            path="serviceLocation.countryCode" cssClass="redstar" /></td>

          <c:choose>
        <c:when test="${command.address1Label==null}">
        <td class="TDShade" width="10%"></td>
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
      <td class="TDShade" width="10%"></td>
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
     <td class="TDShade"><strong><f:message key="city" />:</strong></td>
          <td class="TDShadeBlue"><label> <form:input
            cssClass="inputBox" maxlength="30" path="serviceLocation.city" />
          <form:errors path="serviceLocation.city" cssClass="redstar" /> </label></td>
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
        
        <form:input cssClass="inputBox" maxlength="2" path="serviceLocation.houseType" />
          <form:errors path="serviceLocation.houseType" cssClass="redstar"/></td>
         
       
       </c:otherwise>
      </c:choose>  
     </td>
      <c:choose>
            <c:when test="${command.stateLabel==null}">
            <td class="TDShade" width="10%"></td>
                  <td class="TDShadeBlue"></td>

            </c:when>
            <c:otherwise>
            <td class="TDShade">${command.stateLabel}</td>
            <td colspan="2" class="TDShadeBlue" >
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
        <td class="TDShadeBlue"><label>
         
        <form:input cssClass="inputBox" maxlength="12" path="serviceLocation.postal" />
          <form:errors path="serviceLocation.postal" cssClass="redstar"/>
        </label>
        </td>
       </c:otherwise>

      </c:choose>

          <td class="TDShade" nowrap><strong><f:message
            key="emailAddress" />:</strong></td>
          <td class="TDShadeBlue"><form:input maxlength="70"
            cssClass="inputBox" path="serviceLocation.email" /> <form:errors
            path="serviceLocation.email" cssClass="redstar" /></td>
      </tr>
      <tr>
          <td class="TDShade"><strong><f:message
            key="shipToCustomerId" />:</strong></td>

          </td>
          <td class="TDShadeBlue">${command.serviceLocation.custCode}</td>
        <td class="TDShade" nowrap><strong><f:message
            key="telephoneNo" />:</strong></td>
          <td class="TDShadeBlue"><form:input maxlength="32"
            cssClass="inputBox" path="serviceLocation.phone" /> <form:errors
            path="serviceLocation.phone" cssClass="redstar" /></td>
            </tr>
            <tr>
            <td class="TDShade" width="10%"><f:message key="taxCode"/></td>
                  <td class="TDShadeBlue"><form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" cssClass="inputBox" maxlength="32" size="30" path="taxCode"/>
                <form:errors path="taxCode" cssClass="redstar"/><a href="#" style="visibility:hidden" onClick="javascript:updateTaxCodeIframeSrc();popup_show('vatcode','vatcode_drag','vatcode_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('vatcode','vatcodebody');popupboxenable();">

                      <img src="images/lookup.gif" style="visibility:hidden" alt="lookup country" width="13" height="13" border="0" /></td>
          <td class="TDShade" width="10%">
          <td class="TDShadeBlue"></td>
                   </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0"
        class="applTableBot">
        <tr>

          <c:choose>
          <c:when test="${command.okButton==null}">
          <td><input type="button" value="OK" name="ok" class="button1" disabled="true"/>&nbsp&nbsp<input
                    id="cancel" type="button" value="Cancel" name="cancel"
                    class="button1" onClick="javascript:top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();" />&nbsp&nbsp<input id="cancel2" type="submit" value="Apply"
                    name="cancel2" class="button1" /></td>
          </c:when>
          
          <c:otherwise>
          <td>
          <input id="ok" type="button" value="OK" name="ok"
                    class="button1" onClick="javascript:top.return_popup_search_result('${command.inputFieldId}','${command.serviceLocation.serviceLocationCode}');top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();submitform();" />&nbsp&nbsp<input
          id="cancel" type="button" value="Cancel" name="cancel"
          class="button1"
          onClick="javascript:top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();" />
        &nbsp&nbsp<input id="cancel2" type="submit" value="Apply"
          name="cancel2" class="button1" />
          </td>
  
         </c:otherwise>
        </c:choose>  
        </tr>
        
      </table>
      
      </td>
    </tr>
  </table>

  </div>
  <!----------------- TAB 1 CONTAINER END ------------------------------ -->
  </div>
  </div>
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
  source="taxCode"
  target="taxCode"
  className="autocomplete"
  parameters="taxCode={taxCode}"
  minimumCharacters="1"
  /> 
  <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
  </div>
  <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
  </td>
  </tr>
  </table>
</form:form>

<!----------------------------------------------------------------------------------- VAT Code Lookup ----------------------------------------------------------------------------------------------------->
<div class="sample_popup" id="vatcode" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="vatcode_drag" style="width:950px;height:auto; "> <img class="menu_form_exit"   id="vatcode_exit" src=" images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="taxCode"/></div>
<div class="menu_form_body" id="vatcodebody"   style="width:950px; height:550px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  scrolling="auto" id="searchVatRateFr" name="searchVatRateFr" allowtransparency="yes" src="blank.html"></iframe>
</div>  
</div>
<!------------------------------------------------------------------------------------ VAT Code Lookup END ----------------------------------------------------------------------------------------------->








