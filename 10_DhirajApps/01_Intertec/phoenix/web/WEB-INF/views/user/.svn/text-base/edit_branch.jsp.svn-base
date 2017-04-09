<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script language="javascript">
	function trimAll(sString) 
	{
		while (sString.substring(0,1) == ' ') 
		{ 
			sString = sString.substring(1, sString.length); 
		} 
		while (sString.substring(sString.length-1, sString.length) == ' ') 
		{ 
			sString = sString.substring(0,sString.length-1); 
		} 
		return sString; 
	} 

	function onSave(){
		var ops = trimAll(document.getElementById("opsBranchName").value);
		var lab = trimAll(document.getElementById("labBranchName").value);
		if( ( ops.length>0 && lab.length==0 ) ||  ( ops.length==0 && lab.length>0 ) ){
			confirm("Please enter both Ops and Lab branch code");
		}else{
			document.branchEditForm.submit();
		}
	}
	
	function openBranchPopup(branchType,rowCount)
	{
			var window_width = screen.availWidth/2;
			var window_height = screen.availHeight/2;
			var window_left = (screen.availWidth/2)-(window_width/2);
			var window_top = (screen.availHeight/2)-(window_height/2);
			var winParms = "Status=yes" + ",resizable=yes" + ",height="+window_height+",width="+window_width + ",left="+window_left+",top="+window_top;
			var newwindow = window.open("search_branch_popup.htm?branchtype="+branchType+"&rowCount="+rowCount+"&formName=branchEditForm",'_blank',winParms);
			newwindow.focus();
			
	}


	function onAdd()
	{
		document.branchEditForm.addOrDelete.value = "add";
		document.branchEditForm.submit();
	}
	function onDelete(index)
	{
		document.branchEditForm.addOrDelete.value = "delete";
		document.branchEditForm.index.value = index;

		document.branchEditForm.submit();
	}
	 function setflag(branchtypeflag,rowIndex)
	 {	 
	    document.branchEditForm.rowNum.value=rowIndex;
	    document.branchEditForm.branchTypeFlag.value=branchtypeflag;
	 }
	 
	function resetBranchTypeFlag()
	{
	
		document.branchEditForm.branchTypeFlag.value="none";
	}	 


 function updateMultilingualBranchIframeSrc(branchCode)
 {    
	     document.getElementById('createMultilingualFr').setAttribute("src","create_multilingual_branch_popup.htm?branchCode="+branchCode+"&divName=multilingual&divBody=multilingualbody&searchForm=branchEditForm");
 }

</script>

<icb:list var="divisionBu">
  <icb:item>${command.branch.buName}</icb:item>
</icb:list> 
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<icb:list var="branchtype">
  <icb:item>branchtype</icb:item>
</icb:list>
<form:form name="branchEditForm" method="POST" action="edit_branch.htm">
<form:hidden path="branch.buName"/>

<form:hidden path="addOrDelete" />
<form:hidden path="index" />
<form:hidden path="assocBranchCount" />
<form:hidden path="rowNum" />
<form:hidden path="branchTypeFlag" />
<div style="color:red;">
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
              <li><a href="#" rel="tab1"><span><f:message key="branch"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
				  <th colspan="2" width="40%"><f:message key="editBranch"/></th>
					
                    <th width="10%" colspan="1" style="text-align:left"><span style="text-align:right"><f:message key="status"/>:
                      <form:select cssClass="selectionBox" id="sel1" path="branch.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>
					   <form:errors path="branch.status" cssClass="redstar"/>
                      </span></th>

					 

                   <th width="10%" colspan="1" bgcolor="#ffffff" style="text-align:left">				   
				   <a href="#" onClick="javascript:updateMultilingualBranchIframeSrc('${command.branch.name}');popup_show('multilingual', 'multilingual_drag', 'multilingual_exit', 'screen-corner', 120, 5);hideIt();showPopupDiv('multilingual','multilingualbody');popupboxenable()"><f:message key="additionalLanguageInfo"/></a></th>  
				   
				   
				   


                    <th width="15%" colspan="3" bgcolor="#ffffff" style="text-align:right"><a href="search_branch.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
                    <authz:authorize ifAnyGranted="CreateBranch">
                    <a href="javascript:onSave()"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a>
                    </authz:authorize>
                    </th>

                  </tr>
                  <tr>
                    <td width="15%" class="TDShade"><strong><f:message key="businessUnitName"/>:</strong><span class="redstar">*</span></td>
                    <td width="35%" class="TDShadeBlue"><span class="id_input">
                      <form:select id="sel1" cssClass="selectionBox" path="branch.buName" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value" disabled="true"/>
                      </span>
                    </td>
                    <td width="15%" class="TDShade"><strong><f:message key="branchCode"/>:<span class="redstar"> *</span> </strong></td>
                    <td colspan="3" width="35%" class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35"  maxlength="8" path="branch.name" disabled="true"/>
                      <form:errors path="branch.name" cssClass="redstar"/>
                    </td>
                  </tr>

				   <tr>
					 <td width="15%" class="TDShade"><strong><f:message key="invoiceNumCat" />:</strong><span class="redstar">*</span></td>
                    <td width="35%" class="TDShadeBlue"><span class="id_input">
                      <form:select id="sel1" cssClass="selectionBox" path="branch.numberCategory" items="${icbfn:dropdown('numberCategorys', divisionBu)}" itemLabel="name" itemValue="value" />
                      </span>
                    </td>
                    <td class="TDShade"><f:message key="branchtype"/>: </td>
                    <td class="TDShadeBlue" colspan="3">
					<form:select cssClass="selectionBox" id="sel3" path="branch.type" items="${icbfn:dropdown('staticDropdown',branchtype)}" itemLabel="name" itemValue="value"/>
					<form:errors path="branch.type" cssClass="redstar"/>
					
					
					</td>
                  </tr>
                  <tr>
                   <td class="TDShade"><strong><f:message key="branchName"/>:<span class="redstar">*</span></strong></td>
                    <td class="TDShadeBlue">
					<form:input cssClass="inputBox" size="35"  maxlength="512" path="branch.description" />
                    <form:errors path="branch.description" cssClass="redstar"/>					

					</td>
                  
                    <td class="TDShade"><strong><f:message key="country"/>:<span class="redstar">*</span></strong></td>
                    <td class="TDShadeBlue" colspan="3">

	    					<form:select id="sel2" cssClass="selectionBox" path="branch.countryCode" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />
					       <form:errors path="branch.countryCode" cssClass="redstar"/>
					

					</td>

                  </tr>
                  <tr>
                  
            <td class="TDShade"><strong><f:message key="phone"/>:</strong></td>
                    <td class="TDShadeBlue">
					
                      <form:input cssClass="inputBox" size="16"  maxlength="24" path="branch.phoneNumber" />
                      <form:errors path="branch.phoneNumber" cssClass="redstar"/>
		
                      &nbsp;&nbsp;&nbsp;<f:message key="ext"/>:
					  		                            <form:input cssClass="inputBox" size="6"  maxlength="6" path="branch.phoneExtension" />
                      <form:errors path="branch.phoneExtension" cssClass="redstar"/></td>
                  
 
                    <td class="TDShade"><f:message key="fax"/>:</td>
                    <td class="TDShadeBlue" colspan="3">
	
		      			<form:input cssClass="inputBox" size="20"  maxlength="30"  path="branch.fax" />
                      <form:errors path="branch.fax" cssClass="redstar"/>
				
		
					  
					  </td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="address1"/>: </td>
                    <td class="TDShadeBlue">
				<form:input cssClass="inputBox" size="35"  maxlength="55" path="branch.address1" />
                      <form:errors path="branch.address1" cssClass="redstar"/>
					
	
					  
					  </td>
                    <td class="TDShade">&nbsp;</td>
                    <td colspan="3" class="TDShadeBlue">&nbsp;</td>
                  </tr>
                  
                  <tr>
                    <td class="TDShade"><f:message key="address2"/>: </td>
                    <td class="TDShadeBlue">
				<form:input cssClass="inputBox" size="35"  maxlength="55" path="branch.address2" />
                      <form:errors path="branch.address2" cssClass="redstar"/>
					
	
					  
					  </td>
                    <td class="TDShade">&nbsp;</td>
                    <td colspan="3" class="TDShadeBlue">&nbsp;</td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="address3"/>: </td>
                    <td class="TDShadeBlue">
					
					<form:input cssClass="inputBox" size="35"  maxlength="55" path="branch.address3"/>
                      <form:errors path="branch.address3" cssClass="redstar"/>	
					  
					  </td>
                    <td class="TDShade">&nbsp;</td>
                    <td colspan="3" class="TDShadeBlue">&nbsp;</td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="address4"/>: </td>
                    <td class="TDShadeBlue">
					
			      		<form:input cssClass="inputBox" size="35"  maxlength="55" path="branch.address4" />
                      <form:errors path="branch.address4" cssClass="redstar"/>				
			
					  
					  </td>
                    <td class="TDShade">&nbsp;</td>
                    <td colspan="3" class="TDShadeBlue">&nbsp;</td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="city"/>:</td>
                    <td class="TDShadeBlue">
						<form:input cssClass="inputBox" size="35"  maxlength="30" path="branch.city" />
                      <form:errors path="branch.city" cssClass="redstar"/>
					  
					  </td>
                    <td class="TDShade"><f:message key="state"/>:</td>
                    <td colspan="3" class="TDShadeBlue">

					<icb:list var="countryCodeList">
						<icb:item>${command.branch.countryCode}</icb:item>
					</icb:list>	
					<form:select id="sel3" cssClass="selectionBox" path="branch.stateCode" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" />
				<form:errors path="branch.stateCode" cssClass="redstar"/>
					
					
					</td>
				<ajax:select
					baseUrl="country.htm"
					source="branch.countryCode"
					target="branch.stateCode"
					parameters="country.countryCode={branch.countryCode}"
					parser="new ResponseXmlParser()" />
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="county"/>:</td>
                    <td class="TDShadeBlue">
					
				<form:input cssClass="inputBox" size="35" path="branch.county" />
                      <form:errors path="branch.county" cssClass="redstar"/>
					
	
					  
					  
					  </td>
                    <td class="TDShade"><f:message key="postal"/>:</td>
                    <td colspan="3" class="TDShadeBlue">
					<form:input cssClass="inputBox" size="35"  maxlength="12" path="branch.postal" />
                      <form:errors path="branch.postal" cssClass="redstar"/>					

					  </td>
                  </tr>
                  <tr>
                    <td class="TDShade" style="border-bottom: #CCCCCC dashed 1px;"><f:message key="branchAdminEmail"/>: </td>
                    <td class="TDShadeBlue" style="border-bottom: #CCCCCC dashed 1px;">
					<form:input cssClass="inputBox" size="35"  maxlength="70" path="branch.branchEmail" />
                      <form:errors path="branch.branchEmail" cssClass="redstar"/>					
					  
					  </td>
					   <td class="TDShade" style="border-bottom: #CCCCCC dashed 1px;"><f:message key="timeZone"/>: </td>
                    <td class="TDShadeBlue" colspan="2" style="border-bottom: #CCCCCC dashed 1px;"><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" size="35"  maxlength="70" path="branch.timezone"/><a href="#"
					onClick="javascript:popup_show('branchtimezone','branchtimezone_drag','branchtimezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('branchtimezone','branchtimezonebody');popupboxenable();">
				    <img src=" images/lookup.gif" alt="lookup Time Zone" width="13" height="13" border="0" /></a>
									<form:errors path="branch.timezone" cssClass="redstar"/>
                    <td colspan="2" class="TDShadeBlue" style="border-bottom: #CCCCCC dashed 1px;">&nbsp;</td>
                  </tr>
				   <tr>
                    <td class="TDShade"><f:message key="opsBranchCode"/>: </td>
                    <td class="TDShadeBlue">
					<form:input cssClass="inputBox" size="35"  cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" maxlength="8" path="branch.opsBranchName" id="opsBranchName" />
                      <form:errors path="branch.opsBranchName" cssClass="redstar"/>
                      <a href="#" onClick="javascript:setflag('Ops','0');popup_show('branchcodeops', 'branchcodeops_drag', 'branchcodeops_exit', 'screen-corner', 120, 20);hideIt();showbranchcode('branchcodeops','branchcodeopsbody');popupboxenable()">
					  <img src="images/lookup.gif" alt="<f:message key="lookupBranch"/>" width="13" height="13" border="0">
					 </a></td>
   
                    <td class="TDShade"><f:message key="labBranchCode"/>: </td>
                    <td colspan="3" class="TDShadeBlue">
	    			<form:input cssClass="inputBox" size="35" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" maxlength="8" path="branch.labBranchName" id="labBranchName"/>
                     <form:errors path="branch.labBranchName" cssClass="redstar"/>
                      <a href="#" onClick="javascript:setflag('Lab','0');popup_show('branchcodelab', 'branchcodelab_drag', 'branchcodelab_exit', 'screen-corner', 120, 20);hideIt();showbranchcode('branchcodelab','branchcodelabbody');popupboxenable()">
					  <img src="images/lookup.gif" alt="<f:message key="lookupBranch"/>" width="13" height="13" border="0">
					  </a></td>
 
                  </tr> 
                  <tr>
                    <td class="TDShade"><f:message key="vatBranchCode"/>: </td>
                    <td class="TDShadeBlue">
					<form:input cssClass="inputBox" size="35"  cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" maxlength="8" path="branch.vatBranchName" />
                      <form:errors path="branch.vatBranchName" cssClass="redstar"/>
                      <a href="#" onClick="javascript:setflag('Vat','0');popup_show('branchcodevat', 'branchcodevat_drag', 'branchcodevat_exit', 'screen-corner', 120, 20);hideIt();showbranchcode('branchcodevat','branchcodevatbody');popupboxenable()">
					  <img src="images/lookup.gif" alt="<f:message key="lookupBranch"/>" width="13" height="13" border="0">
					 </a></td>
				  </tr>
                  <tr>
					 <td class="TDShade"><f:message key="logoFileName"/>:</td>
                    <td colspan="1" class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="30" path="branch.logoName"/>
					<a href="#" onClick="javascript:popup_show('logo', 'logo_drag', 'logo_exit', 'screen-corner', 120, 5);hideIt();showbranchcode('logo','logobody');popupboxenable()">
				<img src="images/lookup.gif" alt="lookupBranch" width="13" height="13" border="0">
				</a>
					<form:errors path="branch.logoName" cssClass="redstar"/></td>
					<td class="TDShade"><f:message key="companyDesc"/>:</td>
                   <td colspan="3" class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="150" path="branch.companyDesc"/>
					<form:errors path="branch.companyDesc" cssClass="redstar"/></td>
				  </tr>
                  <tr>
                    <td class="TDShadeBlue" width="20%"><b><f:message key="associatedBranches"/>:</td></b>
                    <td class="TDShadeBlue"  width="20%"></td>
	                <td class="TDShadeBlue"  width="20%"></td>
	                <td class="TDShadeBlue"  width="22%"></td>
                    <td align="left:0px;" class="TDShadeBlue" width="10%"><a href="#"><img src="images/add.gif" alt="addBranches" width="13" height="12" hspace="1px" border="0" onclick="onAdd()"/></a></td>
					<td class="TDShadeBlue"  width="5%"></td>

                  </tr>
                  <tr>
                    <td colspan="6" class="TDShadeBlue" style="padding:0;"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="secAppltable">
                        <tr>
                          <th width="15%"> <f:message key="no"/>.</th>
                          <th width="20%" align="center" style="text-align:center"><f:message key="branchCode"/></th>
                          <th width="20%" align="center" style="text-align:center"><f:message key="branchName"/></th>
                          <th width="30%" align="center" style="text-align:center"><f:message key="branchtype"/> </th>
                          <th width="15%" align="center" style="text-align:center">&nbsp;</th>
                        </tr>
				<c:forEach items="${command.assocBranches}" var="assocBranch" varStatus="counter">

                        <tr>
                          <td align="center">${counter.index + 1} 
						
						  </td>
                          <td align="center">
							<form:input cssClass="inputBox" size="10"  maxlength="8" path="assocBranches[${counter.index}].assocBranchId.assocBranchName"/> 

                            <a href="#" onClick="javascript:setflag('addAssocBranch','${counter.index}');popup_show('branchcodeassoc${counter.index}', 'branchcodeassoc${counter.index}_drag', 'branchcodeassoc${counter.index}_exit', 'screen-corner', 120, 20);hideIt();popupboxenable()">
					              <img src="images/lookup.gif" alt="<f:message key="lookupBranch"/>" width="13" height="13" border="0">
					        </a></td>

                          <td align="center">
						<form:input cssClass="inputBox" size="50"  maxlength="512" path="assocBranches[${counter.index}].assocBranch.description" disabled="true"/> 
						  </td>
                         <td align="center">
						<form:input cssClass="inputBox" size="10"  maxlength="4" path="assocBranches[${counter.index}].assocBranch.type" disabled="true"/> 
						  </td>
							 <td width="50" class="TDShadeBlue" style="text-align:right;"><div id="div3" style="width:50px; text-align:center; margin-right:0;"> &nbsp;<img src="images/delete.gif" alt="<f:message key="deleteBranch"/>" width="13" height="12" hspace="1px" border="0" onclick="onDelete('${counter.index}')"/> </div></td>


						<ajax:autocomplete
						  baseUrl="branch.htm"
						  source="assocBranches[${counter.index}].assocBranchId.assocBranchName"
						  target="assocBranches[${counter.index}].assocBranch.description"
						  className="autocomplete"						  
						  parameters="branchType=assocBranch,fieldname=description,index=${counter.index},buName={branch.buName}"
						  minimumCharacters="1"
						   />
						   </tr>
<!-- --------------------------- Assoc Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="branchcodeassoc${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="branchcodeassoc${counter.index}_drag" style="width:750px; "> 
<a href="#"  onclick="resetBranchTypeFlag()"><img class="menu_form_exit"   id="branchcodeassoc${counter.index}_exit" src="images/form_exit.png" /> </a>&nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/></div>
<div class="menu_form_body" id="branchcodeassoc${counter.index}body"  style="width:750px;height:555px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:10px;" height="555px;" width="100%" src="search_branch_popup.htm?inputFieldId=assocBranches[${counter.index}].assocBranchId.assocBranchName&div1=branchcodeassoc${counter.index}&div2=branchcodeassoc${counter.index}body&buName=${command.branch.buName}&formName=branchEditForm" scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" ></iframe></div></div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->

</c:forEach>   
                      </table></td>
                  </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry"/></span> </td>
                        <td style="text-align:right"><a href="search_branch.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
                        <authz:authorize ifAnyGranted="CreateBranch">
                        <a href="javascript:onSave()"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a>
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
				<ajax:autocomplete
					baseUrl="branch.htm"
					source="branch.opsBranchName"
					target="branch.opsBranchName"
					className="autocomplete"
					parameters="branchType=opsBranch,buName={branch.buName}"
					minimumCharacters="1"
					 />
				<ajax:autocomplete
					baseUrl="branch.htm"
					source="branch.labBranchName"
					target="branch.labBranchName"
					className="autocomplete"
					parameters="branchType=labBranch,buName={branch.buName}"
					minimumCharacters="1"
					 />     
				 <ajax:autocomplete
					baseUrl="branch.htm"
					source="branch.vatBranchName"
					target="branch.vatBranchName"
					className="autocomplete"
					parameters="branchType=vatBranch,buName={branch.buName}"
					minimumCharacters="1"
					 />    
				 <ajax:autocomplete 
			  		baseUrl="joborder.htm"
					source="branch.timezone" target="branch.timezone"
					className="autocomplete" parameters="etaTimeZone={branch.timezone}"
					minimumCharacters="3" /> 

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
<!-- --------------------------------- Branch Timezone Lookup START ------------------------------------------->
<div class="sample_popup" id="branchtimezone"	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="branchtimezone_drag"	style="width:750px; "><img class="menu_form_exit"
id="branchtimezone_exit" src=" images/form_exit.png" />&nbsp;&nbsp;&nbsp;<f:message key="selectTimeZone" /></div>
<div class="menu_form_body" id="branchtimezonebody"style="width:750px;height:555px;overflow-y:hidden;"><iframe align="left:2px"
frameborder="0" style="padding:2px; height:555px;" width="100%"	src="search_timezone_popup.htm?inputFieldId=branch.timezone&div1=branchtimezone&div2=branchtimezonebody"
id="frame5" name="frame5" allowtransparency="yes"></iframe></div>
</div>
<!-- --------------------------------- Branch Timezone  Lookup END ------------------------------------------->
<!-- --------------------------- OPS Branch Code Lookup -------------------------------------------------->
<div class="sample_popup" id="branchcodeops" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="branchcodeops_drag" style="width:750px;"> 
<a href="#"  onclick="resetBranchTypeFlag()">  <img class="menu_form_exit" id="branchcodeops_exit" src="images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/></div>
<div class="menu_form_body" id="branchcodeopsbody"   style="width:750px;height:555px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:10px;" height="555px;" width="100%" src="search_branch_popup.htm?inputFieldId=branch.opsBranchName&div1=branchcodeops&div2=branchcodeopsbody&buName=${command.branch.buName}&formName=branchEditForm" scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" ></iframe></div></div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->
<!-- --------------------------- LAB Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="branchcodelab" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="branchcodelab_drag" style="width:750px; "> 
<a href="#"  onclick="resetBranchTypeFlag()">  <img class="menu_form_exit"   id="branchcodelab_exit" src="images/form_exit.png" /> </a>&nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/></div>
<div class="menu_form_body" id="branchcodelabbody"  style="width:750px;height:555px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:10px;" height="555px;" width="100%" src="search_branch_popup.htm?inputFieldId=branch.labBranchName&div1=branchcodelab&div2=branchcodelabbody&buName=${command.branch.buName}&formName=branchEditForm" scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" ></iframe></div></div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->

<!-- --------------------------- LAB Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="branchcodevat" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="branchcodevat_drag" style="width:750px; "> 
<a href="#"  onclick="resetBranchTypeFlag()">  <img class="menu_form_exit"   id="branchcodevat_exit" src="images/form_exit.png" /> </a>&nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/></div>
<div class="menu_form_body" id="branchcodevatbody"  style="width:750px;height:555px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:10px;" height="555px;" width="100%" src="search_branch_popup.htm?inputFieldId=branch.vatBranchName&div1=branchcodevat&div2=branchcodevatbody&buName=${command.branch.buName}&formName=branchEditForm" scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" ></iframe></div></div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->

<!---------------------------------------------Logo Lookup--------------------------------------------------------->
<div class="sample_popup" id="logo" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="logo_drag" style="width:750px; "> 
<a href="#"  onclick="resetBranchTypeFlag()">   <img class="menu_form_exit"  id="logo_exit" src="images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="searchLogo"/></div>
<div class="menu_form_body" id="logobody"  style="width:750px;height:555px;overflow-y:hidden;">    
<iframe align="left" frameborder="0" style="padding:10px;" height="555px;" width="100%" src="search_logo_popup.htm?inputFieldId=branch.logoName&div1=logo&div2=logobody&formName=branchCreateForm" scrolling="auto" id="searchLogoFr" name="searchLogoFr" allowtransparency="yes" ></iframe></div></div>
<!-----------------------------------------------Logo Lookup END--------------------------------------------------->

<!---------------------------------------------multilingual Lookup--------------------------------------------------------->
<div class="sample_popup" id="multilingual" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="multilingual_drag" style="width:800px; "> 
<a href="#start"><img class="menu_form_exit"  id="multilingual_exit" src="images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="createMultilingualBranch"/></div>
<div class="menu_form_body" id="multilingualbody"  style="width:800px;height:300px;overflow-y:hidden;">    
<iframe align="left" frameborder="0" style="padding:10px;" height="290px;" width="100%" scrolling="auto" id="createMultilingualFr" name="createMultilingualFr" allowtransparency="yes" ></iframe></div></div>
<!-----------------------------------------------multilingual Lookup END--------------------------------------------------->

