<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<head>

<script language="javascript">


	function onStateAdd()
	{
		
		document.countryCreateForm.addOrDeleteState.value = "add";
		document.countryCreateForm.tabName.value = "2";
		document.countryCreateForm.submit();
	}
	function onStateDelete(index)
	{
		document.countryCreateForm.addOrDeleteState.value = "delete";
		document.countryCreateForm.tabName.value = "2";
		document.countryCreateForm.stateIndex.value = index;
		document.countryCreateForm.submit();

	}

	function onCountryVatAdd()
	{
		
		document.countryCreateForm.addOrDeleteCountryVats.value = "add";
		document.countryCreateForm.tabName.value = "0";
		document.countryCreateForm.submit();
	}
	function onCountryVatDelete(index)
	{
		
		document.countryCreateForm.addOrDeleteCountryVats.value = "delete";
		document.countryCreateForm.tabName.value = "0";
		document.countryCreateForm.countryVatIndex.value = index;
		document.countryCreateForm.submit();

	}
 function setflag(rowIndex){	
	
 
  document.countryCreateForm.rowNum.value=rowIndex;
 }
 
 function populateField(checkBoxId,fieldId,fieldValue)
 {
 			if(document.getElementById(checkBoxId).checked )
			{
				document.getElementById(fieldId).value = fieldValue;
			}
			else
			{
				document.getElementById(fieldId).value = '';
			}
 }

</script>
</head>

<icb:list var="treatServiceforForeignBuyer">
  <icb:item>treatServiceforForeignBuyer</icb:item>
</icb:list>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>

<form:form name="countryCreateForm" method="POST" action="create_country.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="tabName"/>
<form:hidden path="addOrDeleteState"/>
<form:hidden path="stateIndex"/>
<form:hidden path="stateCount"/>

<form:hidden path="addOrDeleteCountryVats"/>
<form:hidden path="countryVatIndex"/>
<form:hidden path="countryVatsCount"/>
<form:hidden path="rowNum"/>

<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
	
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="country"/> </span></a></li>
			  <li><a href="#" rel="tab2"><span><f:message key="countryLabels"/></span></a></li>
			  <li><a href="#" rel="tab3"><span><f:message key="states"/> </span></a></li>
			  </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
      
			<!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 border="0" class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="50%" colspan="2"><f:message key="country"/></th>
 <!-- added for the issue 115770 -->
					<th width="20%" colspan="2" style="text-align:left"><span style="text-align:right"><f:message key="status"/>:
                     <form:select id="sel2"
	                    cssClass="selectionBox" path="country.status"
	                    items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name"
	                    itemValue="value" /> 
					 <form:errors path="country.status" cssClass="redstar"/>
                      </span></th>
  <!-- End 115770 --><%--                    
                      
                  <th width="15%" colspan="2" >&nbsp;</th>
                    --%><th width="15%" bgcolor="#ffffff" style="text-align:right"><a href="search_country.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> 
					</th>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td width="15%" class="TDShade"><f:message key="country"/>:<span class="redstar">*</span> </td>
                    <td width="35%" class="TDShadeBlue"><form:input cssClass="inputBox" size="30" maxlength="3" path="country.countryCode" />
					<form:errors path="country.countryCode" cssClass="redstar"/></td>
                    <td width="15%" class="TDShade" ><f:message key="countryName"/>:</td>
                    <td colspan="2" class="TDShadeBlue" width="35%" ><span class="TDShadeBlue">
					<form:input cssClass="inputBox" size="30" maxlength="30" path="country.name" />
					<form:errors path="country.name" cssClass="redstar"/>
                      
                    </span></td>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td class="TDShade"><f:message key="shortDescr"/>.: </td>
                    <td class="TDShadeBlue"><span class="TDShadeBlue">

                    <form:input cssClass="inputBox" size="30" maxlength="10" path="country.shortName" />
					<form:errors path="country.shortName" cssClass="redstar"/>
                    </span></td>
                    <td class="TDShade" ><f:message key="euMember"/>: </td>
                    <td colspan="2" class="TDShadeBlue" ><form:checkbox id="euMemberId" path="country.euMemberId"/>  </td>
                   </tr>
                  <tr bgcolor=#ffffff>
                    <td class="TDShade"><f:message key="charCode"/>: </td>
                    <td class="TDShadeBlue"><span class="TDShadeBlue">

                      <form:input cssClass="inputBox" size="30" maxlength="2" path="country.country2" />
					  <form:errors path="country.country2" cssClass="redstar"/>
                    </span></td>
                    <td class="TDShade" ><f:message key="requiredStateInAddress"/>: </td>
                    <td colspan="2" class="TDShadeBlue" ><form:checkbox path="country.stateRequiredInAddress"/></td>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td class="TDShade"><f:message key="vatCountry"/>: </td>
                    <td class="TDShadeBlue"><form:checkbox path="country.vatCountry"/></td>
                    <td class="TDShade" ><f:message key="vatByProvince"/>: </td>
                    <td colspan="2" class="TDShadeBlue" ><form:checkbox path="country.vatByProvince"/></td>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td class="TDShade"><f:message key="treatServiceforForeignBuyer"/>: </td>
                    <td class="TDShadeBlue">
					<form:select cssClass="selectionBox" id ="sel1" path="country.foreignBuyer" items="${icbfn:dropdown('staticDropdown',treatServiceforForeignBuyer)}" itemLabel="name" itemValue="value"/>
					<form:errors path="country.foreignBuyer" cssClass="redstar"/>
					 </td>
                    <td class="TDShade" colspan="1" width="25%"><f:message key="sameProvinceValidation"/>: </td>
                    <td class="TDShadeBlue" colspan="2" width="25%"><form:checkbox path="country.sameProvinceValidation"/></td>
                  </tr>               
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                       <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry" /></span> </td>
                        <td style="text-align:right"><a href="search_country.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
			
			
			<!-- ------------------------- TAB 2 CONTAINER ----------------------------------------- -->
            <div id="tab2" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="35%"><f:message key="country"/> </th>
                    <th width="25%">&nbsp;</th>
                    <th width="40%" style="text-align:right;" ><a href="search_country.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0"/></a></th>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td colspan="3" class="TDShade" style="padding:2px;"><table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                      <tbody>
                        <tr bgcolor="#ffffff">
                          <th class="TDShade" style="border-bottom:#CCCCCC 1px solid;"><f:message key="defaultLabel"/></th>
                          <th class="TDShade" style="border-bottom:#CCCCCC 1px solid;"><f:message key="useField"/></th>
                          <th class="TDShade" style="border-bottom:#CCCCCC 1px solid;"><f:message key="countryLabel"/> <span class="TDShadeBlue"></span></th>
                        </tr>
                        <tr bgcolor="#ffffff">
                          <td class="TDShadeBlue"><f:message key="address1"/></td>
                          <td class="TDShadeBlue"><form:checkbox id="addr1Avail" path="country.addr1Avail" onclick="populateField('addr1Avail','addr1Lbl','Address 1:')"/></td>
                          <td class="TDShadeBlue" ><form:input id="addr1Lbl" cssClass="inputBox" size="30" maxlength="15" path="country.addr1Lbl" />
					     <form:errors path="country.addr1Lbl" cssClass="redstar"/></td>
                        </tr>
                        <tr bgcolor="#ffffff">
                          <td class="TDShadeBlue"><f:message key="address2"/></td>
                          <td class="TDShadeBlue"><form:checkbox id="addr2Avail" path="country.addr2Avail" onclick="populateField('addr2Avail','addr2Lbl','Address 2:')"/></td>
                          <td class="TDShadeBlue" ><form:input id="addr2Lbl" cssClass="inputBox" size="30" maxlength="15" path="country.addr2Lbl"/>
					     <form:errors path="country.addr2Lbl" cssClass="redstar"/></td>
                        </tr>
                         <tr bgcolor="#ffffff">
                          <td class="TDShadeBlue"><f:message key="address3"/></td>
                          <td class="TDShadeBlue"><form:checkbox id="addr3Avail" path="country.addr3Avail" onclick="populateField('addr3Avail','addr3Lbl','Address 3:')"/></td>
                          <td class="TDShadeBlue" ><form:input id="addr3Lbl" cssClass="inputBox" size="30" maxlength="15" path="country.addr3Lbl"/>
					     <form:errors path="country.addr3Lbl" cssClass="redstar"/>                          </td>
                        </tr>
                        <tr bgcolor="#ffffff">
                          <td class="TDShadeBlue"><f:message key="address4"/></td>
                          <td class="TDShadeBlue"><form:checkbox id="addr4Avail" path="country.addr4Avail" onclick="populateField('addr4Avail','addr4Lbl','Address 4:')"/></td>
                          <td class="TDShadeBlue" ><form:input id="addr4Lbl" cssClass="inputBox" size="30" maxlength="15" path="country.addr4Lbl"/>
					     <form:errors path="country.addr4Lbl" cssClass="redstar"/>                       </td>
                        </tr>
                        <tr bgcolor="#ffffff">
                          <td class="TDShadeBlue"><f:message key="city"/></td>
                          <td class="TDShadeBlue"><form:checkbox id="cityAvail" path="country.cityAvail" onclick="populateField('cityAvail','cityLbl','City:')"/></td>
                          <td class="TDShadeBlue" ><form:input id="cityLbl" cssClass="inputBox" size="30" maxlength="15" path="country.city"/>
					     <form:errors path="country.city" cssClass="redstar"/>                         </td>
                        </tr>
                        <tr bgcolor="#ffffff">
                          <td class="TDShadeBlue"><f:message key="county"/></td>
                          <td class="TDShadeBlue"><form:checkbox id="countyAvail" path="country.countyAvail" onclick="populateField('countyAvail','countyLbl','County:')"/></td>
                          <td class="TDShadeBlue" ><form:input id="countyLbl" cssClass="inputBox" size="30" path="country.county"/>
					     <form:errors path="country.county" cssClass="redstar"/>                          </td>
                        </tr>
                        <tr bgcolor="#ffffff">
                          <td class="TDShadeBlue"><f:message key="state"/></td>
                          <td class="TDShadeBlue"><form:checkbox id="stateAvail" path="country.stateAvail" onclick="populateField('stateAvail','stateLbl','State:')"/></td>
                          <td class="TDShadeBlue" ><form:input id="stateLbl" cssClass="inputBox" size="30" maxlength="15" path="country.state"/>
					      <form:errors path="country.state" cssClass="redstar"/></td>
                        </tr>
                        <tr bgcolor="#ffffff">
                          <td class="TDShadeBlue"><f:message key="postal"/></td>
                          <td class="TDShadeBlue"><form:checkbox id="postalAvail" path="country.postalAvail" onclick="populateField('postalAvail','postalLbl','Postal:')"/></td>
                          <td class="TDShadeBlue" ><form:input id="postalLbl" cssClass="inputBox" size="30" maxlength="15" path="country.postal"/>
					     <form:errors path="country.postal" cssClass="redstar"/>        </td>
                        </tr> 
                      </tbody>
                    </table></td>
                  </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                       <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry" /></span> </td>
                        <td style="text-align:right"><a href="search_country.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 2 CONTAINER END ------------------------------ -->
			
			
			<!-- ------------------------- TAB 3 CONTAINER ----------------------------------------- -->
            <div id="tab3" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="35%"><f:message key="country"/></th>
                    <th width="25%">&nbsp;</th>
                    <th width="40%" style="text-align:right;" ><a href="search_country.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0"/></a></th>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td colspan="3" class="TDShade" style="padding:2px;"><table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                      <tr>
                        <th width="25"><f:message key="no"/>.</th>
                        <th width="25%"><f:message key="stateProvince"/></th>
                        <th width="45%"><f:message key="description"/></th>
                        <th width="25%"><f:message key="numericCode"/></th>
                       <th width="50" class="TDShadeBlue" style="text-align:right;"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onStateAdd()"/></th>
                      </tr>
                     
					  <c:forEach items="${command.states}" var="states" varStatus="counter">
      
					<tr>
					<td class="TDShadeBlue">${counter.index+1}</td>
					 <td class="TDShadeBlue">
					 <form:input cssClass="inputBox" size="15" maxlength="6" path="states[${counter.index}].stateId.stateCode"/>
						<form:errors path="states[${counter.index}].stateId.stateCode" cssClass="redstar"/> 
						</td>
						<td class="TDShadeBlue" align="center"> 
						<form:input cssClass="inputBox" size="50" maxlength="32" path="states[${counter.index}].name"/>
						<form:errors path="states[${counter.index}].name" cssClass="redstar"/>
						</td>
				      <td class="TDShadeBlue"><form:input cssClass="inputBox" size="15" maxlength="2" path="states[${counter.index}].numericCd"/>
				     <form:errors path="states[${counter.index}].numericCd" cssClass="redstar"/>
				     </td>
					  <td width="50" class="TDShadeBlue" style="text-align:right;"><div id="div3" style="width:50px; text-align:center; margin-right:0;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0" onclick="onStateDelete('${counter.index}')" /></div>
					 </td> 
						  
					</c:forEach>  
					  
					  
					 
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 3 CONTAINER END ------------------------------ -->
			
			
          </div>
        </div>
         <script type="text/javascript">
      var pageNoVar = "${command.tabName}"
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", pageNoVar)      
      </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>



<!-- --------------------------- Country Lookup ------------------------------------------------- -->
<div class="sample_popup" id="country" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="country_drag" style="width:430px; "> <img class="menu_form_exit"   id="country_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="selectCountry"/></div>
  <div class="menu_form_body"   style="width:430px; height:280px;">
    <form method="post" action="popup.php">
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
</div>
<!-- --------------------------------- Country Lookup END ----------------------------------------- -->
<!-- --------------------------- Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="branchcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="branchcode_drag" style="width:450px; "> <img class="menu_form_exit"   id="branchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/></div>
  <div class="menu_form_body" id="branchcodebody"   style="width:450px; height:130px;">
    <form method="post" action="popup.php">
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
            <input name="Button" type="button" class="button1" value="Cancel" onClick="hidebranchcode();" /></td>
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
          <td><a href="#">USA01</a></td>
          <td><a href="#">US100</a></td>
          <td>Baltimore, MD, USA</td>
        </tr>
        <tr>
          <td><a href="#">USA01</a></td>
          <td><a href="#">US120</a></td>
          <td>Boston, MA, USA</td>
        </tr>
        <tr>
          <td><a href="#">USA01</a></td>
          <td><a href="#">US140</a></td>
          <td>Channelview, TX, USA</td>
        </tr>
      
      </table>
	  </div><!--Search Results -->
      
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->

<!-- --------------------------- Job Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="jobcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="jobcode_drag" style="width:450px; "> <img class="menu_form_exit"   id="jobcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Job Code</div>
  <div class="menu_form_body" id="jobcodebody"   style="width:450px; height:130px;">
    <form method="post" action="popup.php">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
        
        <tr>
          <td width="25%"><strong>Job Code: </strong></td>
          <td><input name="textfield2" type="text" class="inputBox" size="30" /></td>
        </tr>
        <tr>
          <td><strong>Description:</strong></td>
          <td><input name="textfield3" type="text" class="inputBox" size="30" /></td>
        </tr>
        <tr>
          <td colspan="2"><input name="Button" type="button" class="button1" value="Search" onClick="searchjobcode();"/>
            &nbsp;&nbsp;
            <input name="Button" type="button" class="button1" value="Cancel" onClick="hidejobcode();" /></td>
        </tr>
      </table>
       
	  <div id="jobcodesearchresults" style="visibility:hidden; display:none;"><!--Search Results -->
	  <br>&nbsp;&nbsp;<strong>Search Results</strong>
	  <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%">
        <tr>
          <th>Job Code </th>
          <th>Description</th>
        </tr>
        <tr>
          <td><a href="#">ACCT01</a></td>
          <td>Accounting</td>
        </tr>
        <tr>
          <td><a href="#">MAN001</a></td>
          <td>Branch Manager</td>
        </tr>
        <tr>
          <td><a href="#">MAN002</a></td>
          <td>Lab Manager</td>
        </tr>
      </table>
	  </div><!--Search Results -->
      
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Job Code Lookup END ----------------------------------------- -->

<!-- --------------------------- Business Unit Lookup ------------------------------------------------- -->
<div class="sample_popup" id="bu" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="bu_drag" style="width:450px; "> <img class="menu_form_exit"   id="bu_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Business Unit </div>
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
            <input name="Button" type="button" class="button1" value="Cancel" onClick="hideBU();" /></td>
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
          <td><a href="#">Caleb Brett USA, Inc</a></td>
          </tr>
        <tr>
          <td><a href="#">UK001</a></td>
          <td><a href="#">ITS Testing Services (UK), Limited</a></td>
          </tr>
      </table>
	  </div><!--Search Results -->
      
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Company name Lookup END ----------------------------------------- -->
<!-- --------------------------- VAT Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="vatcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="vatcode_drag" style="width:450px; "> <img class="menu_form_exit"   id="vatcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;VAT Code </div>
  <div class="menu_form_body" id="vatcodebody"   style="width:450px; height:130px;">
    <form method="post" action="popup.php">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
          <td width="25%"><strong><a href="#">CAS</a></strong></td>
        </tr>
        <tr>
          <td><strong><a href="#">CAH</a></strong></td>
        </tr>
        <tr>
          <td><strong><a href="#">CAZ</a></strong></td>
        </tr>
        <tr>
          <td><input name="Submit2" type="button" class="button1" onClick="hideVatcode();popupboxclose();" value="Ok"></td>
        </tr>
      </table>
       
	  <div id="branchcodesearchresults" style="visibility:hidden; display:none;"><!--Search Results -->
	  <br>&nbsp;&nbsp;</div>
	  <!--Search Results -->
      
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- VAT Code Lookup END ----------------------------------------- -->

<div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>

