<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- START : #119240 -->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- END : #119240 -->
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %> 
<script language="javascript">
	function onAdd()
	{
		document.buEditForm.addOrDelete.value = "add";
		document.buEditForm.submit();
	}
	function onDelete(index)
	{
		document.buEditForm.addOrDelete.value = "delete";
		document.buEditForm.index.value = index;

		document.buEditForm.submit();
	}
	function showVatTable()
	{
		
		if(document.getElementById("vatEnabledInd").checked )
		{
			document.getElementById("vattable").style.visibility = "visible";
			document.buEditForm.addOrDelete.value = "add";
			document.buEditForm.submit();		
		}
		else
		document.getElementById("vattable").style.visibility = "hidden";	

	}	

 function updateMultilingualBunitIframeSrc(buName)
 {    
   document.getElementById('createMultilingualFr').setAttribute("src","create_multilingual_bunit_popup.htm?buName="+buName+"&divName=multilingual&divBody=multilingualbody&searchForm=buEditForm");
 }

  // START : #119240 : 29/06/09
 function doOperation(myOperationType)
  {
    document.buEditForm.operationType.value = myOperationType;
    document.buEditForm.submit();
  }
 // END : #119240 : 29/06/09 

</script>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>

<icb:list var="yesNo">
  <icb:item>yesNo</icb:item>
</icb:list>

<form:form name="buEditForm" method="POST" action="edit_business_unit.htm" >
<form:hidden path="businessUnit.name" />
<form:hidden path="addOrDelete" />
<form:hidden path="index" />
<form:hidden path="busUnitVatLocCount" />
 <!-- START : #119240 : 29/06/09 -->
 <input type="hidden" name="operationType" value="" />
 
<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>
 <!-- END : #119240 : 29/06/09 -->

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
              <li><a href="#" rel="tab1"><span><f:message key="businessUnit"/> </span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable border="0">
                <tbody>
                
                <tr bgcolor=#ffffff>
                  <th colspan=2 width="50%"><f:message key="editBusinessUnit"/></th>    

                  <th width="15%" style="text-align:left"><span style="text-align:right"><f:message key="status"/>:
                    <form:select id="sel1"cssClass="selectionBox" path="businessUnit.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>
                  </span></th>

                 

				 <th width="18%" colspan="1" bgcolor="#ffffff" style="text-align:left">				   
				  <a href="#" onClick="javascript:updateMultilingualBunitIframeSrc('${command.businessUnit.name}');popup_show('multilingual', 'multilingual_drag', 'multilingual_exit', 'screen-corner', 120, 5);hideIt();showPopupDiv('multilingual','multilingualbody');popupboxenable()"><f:message key="additionalLanguageInfo"/></a></th> 



                  
				 <!-- START : #119240 : 29/06/09 -->
				<%-- <th width="10%" bgcolor="#ffffff" style="text-align:right">
					  <a href="search_business_unit.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
					  <authz:authorize ifAnyGranted="CreateBusinessUnit">
					  <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0"></a>
					  </authz:authorize> --%>

					  <th width="17%" bgcolor="#ffffff" style="text-align:right">
						 <a href="#" onClick="javascript:doOperation('searchBusinessUnit');">
				            <img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle">
				          </a>&nbsp;
						  <!-- START for ITrack note : 27-Jul-2009 -->
							 <%-- <c:if test="${command.businessUnitSearch != null}"> --%>
							<c:if test="${command.businessUnitSearch != null && command.businessUnitSearch.results 	!= null && fn:length(command.businessUnitSearch.results) > 1}">
						  <!-- END for ITrack note : 27-Jul-2009 -->
					   
					          <a href="#" onClick="javascript:doOperation('prevBusinessUnit');">
					            <img src="images/prevleftarrow.gif" alt="Go to Previous Business Unit" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
					          <a href="#" onClick="javascript:doOperation('nextBusinessUnit');">
					            <img src="images/nextrtarrow.gif" alt="Go to Next Business Unit" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
						  </c:if> 
					 <authz:authorize ifAnyGranted="CreateBusinessUnit">
					   <a href="#"  onClick="javascript:doOperation('saveBusinessUnit');">
			            <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
			          </a>
					  </authz:authorize>
				 <!-- END : #119240 : 29/06/09 -->
                  </th>
                </tr>

				<tr>
					<td width="15%" class="TDShade"><f:message key="businessUnitId"/>:<span class="redstar">*</span></td>
					<td width="30%" class="TDShadeBlue">
					 <form:input cssClass="inputBox" size="35"  maxlength="5" path="businessUnit.name" disabled="true"/>
						<form:errors path="businessUnit.name" cssClass="redstar"/>
					</td>
					<td width="20%" class="TDShade"><f:message key="defaultBu"/> :<strong></strong></td>
					<td colspan="2" width="30%" class="TDShadeBlue">
					 <form:checkbox  path="businessUnit.defaultBuInd" />

				</tr>

                <tr>
                  <td class="TDShade"><f:message key="businessUnitName"/> :<strong></td>
                  <td class="TDShadeBlue">
				    <form:input cssClass="inputBox" size="35"  maxlength="512"  path="businessUnit.description" />
                    <form:errors path="businessUnit.description" cssClass="redstar"/>

				  
				  </td>

                  <td class="TDShade"><f:message key="shortDescr"/>  :<strong></strong></td>
                  <td colspan="2" class="TDShadeBlue">
				  
				    <form:input cssClass="inputBox" size="35"  maxlength="128" path="businessUnit.shortDescription" />
                    <form:errors path="businessUnit.shortDescription" cssClass="redstar"/>

				  
				  
				  </td>
                </tr> 
				
					<tr>
					<td class="TDShade"><strong><f:message key="phone"/>:</strong></td>
					<td  class="TDShadeBlue">
					 

					 <form:input cssClass="inputBox" size="16" maxlength="24" path="businessUnit.phoneNumber" />
					  &nbsp;&nbsp;&nbsp;<f:message key="ext"/>:
					   <form:input cssClass="inputBox" size="6"  maxlength="6" path="businessUnit.phoneExtension"/>
						<form:errors path="businessUnit.phoneExtension" cssClass="redstar"/>

					</td>

					 <td class="TDShade"><f:message key="fax"/>:</td>
					<td colspan="2" class="TDShadeBlue">
					<form:input cssClass="inputBox" size="35"  maxlength="30" path="businessUnit.fax"/>
					<form:errors path="businessUnit.fax" cssClass="redstar"/>

					</td>
				 </tr>
				 <tr>
				  <td class="TDShade"><strong><f:message key="country"/>:<span class="redstar">*</span></strong></td>
				  <td class="TDShadeBlue">
					
					<form:select id="sel2" cssClass="selectionBox" path="businessUnit.countryCode" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />
				   <form:errors path="businessUnit.countryCode" cssClass="redstar"/>

				  </td>
				 <td class="TDShade"><f:message key="state"/>:</td>
				<td colspan="2" class="TDShadeBlue">
				<icb:list var="countryCodeList">
					<icb:item>${command.businessUnit.countryCode}</icb:item>
				</icb:list>	
				<form:select cssClass="selectionBox" id="sel5" path="businessUnit.stateCode" items="${icbfn:dropdown('state', countryCodeList)}" itemLabel="name" itemValue="value" />
				<form:errors path="businessUnit.stateCode" cssClass="redstar"/>

				</td>
				<ajax:select
				baseUrl="country.htm"
				source="businessUnit.countryCode"
				target="businessUnit.stateCode"
				parameters="country.countryCode={businessUnit.countryCode}"
				parser="new ResponseXmlParser()" />
				 </tr>
				 <tr>
				 <td class="TDShade"><f:message key="address1"/>: </td>
				 <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35" maxlength="55" path="businessUnit.address1"/>
					<form:errors path="businessUnit.address1" cssClass="redstar"/>

				 </td>
				 <td class="TDShade">&nbsp;</td>
				 <td colspan="2" class="TDShadeBlue">&nbsp;</td>
				</tr>

				<tr>
				<td class="TDShade"><f:message key="address2"/>: </td>
				<td class="TDShadeBlue"><form:input cssClass="inputBox" size="35" maxlength="55" path="businessUnit.address2"/>
								<form:errors path="businessUnit.address2" cssClass="redstar"/>

				</td>
				<td class="TDShade">&nbsp;</td>
				<td colspan="2" class="TDShadeBlue">&nbsp;</td>
			   </tr>
			   <tr>
				<td class="TDShade"><f:message key="address3"/>: </td>
				<td class="TDShadeBlue"><form:input cssClass="inputBox" size="35" maxlength="55" path="businessUnit.address3"/>
								<form:errors path="businessUnit.address3" cssClass="redstar"/>
				</td>
				<td class="TDShade">&nbsp;</td>
				<td colspan="2" class="TDShadeBlue">&nbsp;</td>
			  </tr>
			   <tr>
	 
				<td class="TDShade"><f:message key="address4"/>: </td>
				<td class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="55" path="businessUnit.address4"/>
								<form:errors path="businessUnit.address4" cssClass="redstar"/>
				</td>
				<td class="TDShade"><f:message key="retainPeriod"/>:</td>
				<td colspan="2" class="TDShadeBlue"><label><form:input cssClass="inputBox" size="4" maxlength="4" path="businessUnit.retainPeriod"/>
				<form:errors path="businessUnit.retainPeriod" cssClass="redstar"/>
				<f:message key="days"/></label>
				</td>           
				</tr>
				<tr>

				<td class="TDShade"><f:message key="city"/>:</td>
				<td class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="30" path="businessUnit.city"/>
								<form:errors path="businessUnit.city" cssClass="redstar"/>
				</td>
				<td class="TDShade"><f:message key="county"/>:</td>
				<td class="TDShadeBlue" colspan="2">
				<form:input cssClass="inputBox" size="35"  maxlength="30" path="businessUnit.county"/>
				<form:errors path="businessUnit.county" cssClass="redstar"/></td>
	   
			  </tr>
			   <tr>
				
			   
				<td class="TDShade"><f:message key="postal"/>:</td>
				<td colspan="1" class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="12" path="businessUnit.postal"/>
				<form:errors path="businessUnit.postal" cssClass="redstar"/></td>
				<td class="TDShade">&nbsp;</td>
						<td colspan="2" class="TDShadeBlue">&nbsp;</td>
			  </tr>

			 <tr>
			  <td class="TDShade"> <f:message key="baseCurrency"/>: </td>
			  <td class="TDShadeBlue">
			  
			  <form:select cssClass="selectionBox" path="businessUnit.currencyBase" items="${icbfn:dropdown('currency', null)}" itemLabel="name" itemValue="value" />
			 <form:errors path="businessUnit.currencyBase" cssClass="redstar"/>


			  </td>
				<td class="TDShade"><f:message key="handlesVat"/>? </td>
				<td colspan="2" class="TDShadeBlue">
				<form:checkbox  id="vatEnabledInd" path="businessUnit.vatEnabledInd" onclick="showVatTable()"/>
				</td>
			 </tr>
			  <tr>
			  <td class="TDShade"><f:message key="companyDesc"/>:</td>
			  <td colspan="1" class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="150" path="businessUnit.companyDesc"/>
			  <form:errors path="businessUnit.companyDesc" cssClass="redstar"/></td>
			  <td class="TDShade"></td>
			  <td colspan="2" class="TDShadeBlue"></td>
			  </tr>
			  <tr>
			  <td class="TDShade"><f:message key="vatLocations"/>:</td>
			  <td class="TDShadeBlue"></td>
			  <td class="TDShade"></td>
			  <td class="TDShadeBlue" colspan="2"></td>
			</tr>
			<tr>
			<td colspan="5" class="TDShade" style="padding:2px;">							
			<c:choose> 
			  <c:when test="${command.businessUnit.vatEnabledInd}" > 
				<div id="vattable" style="visibility: visible;">
			  </c:when> 
			  <c:otherwise> 
				<div id="vattable" style="visibility: hidden;">
			  </c:otherwise> 
			</c:choose>
					<table id="vatTable" width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
						<tr>
							<tr>
							<th colspan="1"><f:message key="country"/></th>
							<th colspan="1"><f:message key="state"/></th>
							<th colspan="1"><f:message key="vatRegistrationId"/></th>
							<th colspan="1"><f:message key="localVatId"/></th>
							<th colspan="1"><f:message key="outOfScopeApplicable"/></th>
							<th colspan="2"><f:message key="defaultLocation"/></th>
							<th>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img src="images/add.gif" alt="<f:message key="addVatLocations"/>" width="13" height="12" hspace="1px" border="0" onclick="onAdd()"  /></th>
						</tr>
						
						<c:forEach items="${command.busUnitVatLocs}" var="vatLoc" varStatus="counter">
							<tr>
							  <td>                   
								<form:select cssClass="selectionBox" path="busUnitVatLocs[${counter.index}].busUnitVatLocId.countryCode" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />
								</td>
								
								<td>
    								<icb:list var="stateCountry">
										<icb:item>${vatLoc.busUnitVatLocId.countryCode}</icb:item>
									</icb:list>
        							<form:select id="sel8" cssClass="selectionBox" path="busUnitVatLocs[${counter.index}].state" items="${icbfn:dropdown('state', stateCountry)}" itemLabel="name" itemValue="value"/>
    							</td>
								
								<td class="TDShadeBlue">
							<!-- START : #119240 : 29/06/09 : Fix for page displayed incompletely on the right side -->
							<%--	<form:input cssClass="inputBox" size="35"  maxlength="12" path="busUnitVatLocs[${counter.index}].busUnitVatLocId.vatRegistrationId"/> --%>
							
								<form:input cssClass="inputBox" size="25"  maxlength="12" path="busUnitVatLocs[${counter.index}].busUnitVatLocId.vatRegistrationId"/>
							<!-- END : #119240 : 29/06/09 -->
							  <form:errors path="busUnitVatLocs[${counter.index}].busUnitVatLocId.vatRegistrationId" cssClass="redstar"/>
							  </td>
							   <td class="TDShadeBlue">
							<!-- START : #119240 : 29/06/09 : Fix for page displayed incompletely on the right side -->   
							  <%-- <form:input cssClass="inputBox" size="35"  maxlength="15" path="busUnitVatLocs[${counter.index}].localVatId"/> --%>
							
							   <form:input cssClass="inputBox" size="25"  maxlength="15" path="busUnitVatLocs[${counter.index}].localVatId"/>
							 <!-- END : #119240 : 29/06/09 -->
							  <form:errors path="busUnitVatLocs[${counter.index}].localVatId" cssClass="redstar"/>
							  </td>
							  <td class="TDShadeBlue">
								<form:select id="sel${counter.index}" cssClass="selectionBox" path="busUnitVatLocs[${counter.index}].outOfScope" items="${icbfn:dropdown('staticDropdown',yesNo)}" itemLabel="name" itemValue="value" />
				   				<form:errors path="busUnitVatLocs[${counter.index}].outOfScope" cssClass="redstar"/>
								  </td>
								<td class="TDShadeBlue" colspan="2" width="70">                 
									<form:checkbox   path="busUnitVatLocs[${counter.index}].defaultLocInd" />
								</td>
							<td width="10" class="TDShadeBlue" style="text-align:right;"><div id="div3" style="width:50px; text-align:center; margin-right:0;"> <a href="#"><img src="images/delete.gif" alt="<f:message key="deleteVatLocation"/>" width="13" height="12" hspace="1px" border="0" onclick="onDelete('${counter.index}')" /></a> </div>
							</td>
							</tr>
							<ajax:select
							  baseUrl="country.htm"
							  source="busUnitVatLocs[${counter.index}].busUnitVatLocId.countryCode"
							  target="busUnitVatLocs[${counter.index}].state"
							  parameters="country.countryCode={busUnitVatLocs[${counter.index}].busUnitVatLocId.countryCode}"
							  parser="new ResponseXmlParser()" />
						</c:forEach>
					<tr>
						<td class="TDShadeBlue">&nbsp;</td>
						<td class="TDShadeBlue">&nbsp;</td>
						<td class="TDShadeBlue">&nbsp;</td>
						<td class="TDShadeBlue" style="text-align:center">&nbsp;</td>
					</tr>
				</table>

				</div>
				</td>
				</tr>						
			</tbody>
		</table>
                      
            
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                        <tr>
                          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry"/></span> </td>
                                <td style="text-align:right">
							 <!-- START : #119240 : 29/06/09 -->
									<%-- <a href="search_business_unit.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
									<authz:authorize ifAnyGranted="CreateBusinessUnit">
									<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0"></a>
									</authz:authorize> --%>
								<a href="#" onClick="javascript:doOperation('searchBusinessUnit');">
									<img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle">
		                        </a>&nbsp; 
						 <!-- START for ITrack note : 27-Jul-2009 -->
							 <%-- <c:if test="${command.businessUnitSearch != null}"> --%>
							<c:if test="${command.businessUnitSearch != null && command.businessUnitSearch.results 	!= null && fn:length(command.businessUnitSearch.results) > 1}">
						  <!-- END for ITrack note : 27-Jul-2009 -->
								   <a href="#" onClick="javascript:doOperation('prevBusinessUnit');">
									<img src="images/prevleftarrow.gif" alt="Go to Previous Business Unit" width="13" height="12" hspace="1px" border="0"/>
								  </a> &nbsp;
								  <a href="#" onClick="javascript:doOperation('nextBusinessUnit');">
									<img src="images/nextrtarrow.gif" alt="Go to Next Business Unit" width="13" height="12" hspace="1px" border="0"/>
								  </a> &nbsp;
		 					   </c:if> 
							 <authz:authorize ifAnyGranted="CreateBusinessUnit">
							   <a href="#"  onClick="javascript:doOperation('saveBusinessUnit');">
								<img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
								</a>
							 </authz:authorize>
  						 <!-- END : #119240 : 29/06/09 -->
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

<!---------------------------------------------multilingual Lookup--------------------------------------------------------->
<div class="sample_popup" id="multilingual" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="multilingual_drag" style="width:800px; "> 
<a href="#start"><img class="menu_form_exit"  id="multilingual_exit" src="images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="createMultilingualBunit"/></div>
<div class="menu_form_body" id="multilingualbody"  style="width:800px;height:300px;overflow-y:hidden;">    
<iframe align="left" frameborder="0" style="padding:10px;" height="290px;" width="100%" scrolling="auto" id="createMultilingualFr" name="createMultilingualFr" allowtransparency="yes" ></iframe></div></div>
<!-----------------------------------------------multilingual Lookup END--------------------------------------------------->
</form:form>
