<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script language="javascript">


	function onAdd()
	{
		document.buCreateForm.addOrDelete.value = "add";
		document.buCreateForm.submit();
	}
	function onDelete(index)
	{
		document.buCreateForm.addOrDelete.value = "delete";
		document.buCreateForm.index.value = index;

		document.buCreateForm.submit();
	}
	function showVatTable()
	{
		

		if(document.getElementById("vatEnabledInd").checked )
		{
			document.getElementById("vattable").style.visibility = "visible";
			document.buCreateForm.addOrDelete.value = "add";
			document.buCreateForm.submit();		
		}
		else
		document.getElementById("vattable").style.visibility = "hidden";	

	}	
</script>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>

<icb:list var="yesNo">
  <icb:item>yesNo</icb:item>
</icb:list>

<form:form name="buCreateForm" method="POST" action="create_business_unit.htm">
	<form:hidden path="addOrDelete" />
	<form:hidden path="index" />
	<form:hidden path="busUnitVatLocCount" />

	<div style="color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
		<tr>
			<td valign="top">

				<!-- MAIN CONTAINER -->
				<div id="MainContentContainer">
			<table border="0" width="100%" cellpadding="0" cellspacing="0" class="messgaeTable">
			<tr align="right">
			<td style="text-align:right"><b>
			<c:out value='<%= request.getAttribute("message") %>'/></b>
			</td>
			</tr>
			</table>
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

								<table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
									<tbody>

									<tr bgcolor=#ffffff>
										<th colspan=3 width="40%"><f:message key="createBusinessUnit"/></th>
										<th width="25%" style="text-align:right"><span style="text-align:right"><f:message key="status"/>:
											<form:select cssClass="selectionBox" path="businessUnit.status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>
										</span></th>

										<th width="15%" bgcolor="#ffffff" style="text-align:right"><a href="search_business_unit.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp
										<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0"></a>
										</th>
									</tr>

									<tr>
										<td width="15%" class="TDShade"><f:message key="businessUnitId"/>:<span class="redstar">*</span></td>
										<td width="35%" class="TDShadeBlue">
										 <form:input cssClass="inputBox" size="35"  maxlength="5" path="businessUnit.name" />
											<form:errors path="businessUnit.name" cssClass="redstar"/>
										</td>
										<td width="15%" class="TDShade"><f:message key="defaultBu"/> :<strong></strong></td>
										<td colspan="2" width="35%" class="TDShadeBlue">
										 <form:checkbox  path="businessUnit.defaultBuInd" />

									</tr>
									<tr>
										<td class="TDShade"><f:message key="businessUnitName"/> :<strong></td>
										<td class="TDShadeBlue">

										<form:input cssClass="inputBox" size="35"  maxlength="512" path="businessUnit.description" />
											<form:errors path="businessUnit.description" cssClass="redstar"/>


										<a href="#" onClick="javascript:popup_show('country', 'country_drag', 'country_exit', 'screen-corner', 40, 80);showcountryframe();hideIt()"></a></td>

										<td class="TDShade"><f:message key="shortDescr"/> :<strong></strong></td>
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
								  <td class="TDShadeBlue" >
									
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
                                <td class="TDShadeBlue"><label><form:input cssClass="inputBox" size="4" maxlength="4" path="businessUnit.retainPeriod"/>
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
										<td class="TDShade"> <f:message key="baseCurrency"/> : </td>
										<td class="TDShadeBlue">
											<form:select cssClass="selectionBox" path="businessUnit.currencyBase" items="${icbfn:dropdown('currency', null)}" itemLabel="name" itemValue="value" />
											<form:errors path="businessUnit.currencyBase" cssClass="redstar"/>
                                        </td>
										<td class="TDShade"><f:message key="handlesVat"/>? </td>
										<td colspan="2" class="TDShadeBlue">
										  <form:checkbox id="vatEnabledInd" path="businessUnit.vatEnabledInd" onclick="showVatTable()"/>
						               </td>
									</tr>
									<tr>
									<td class="TDShade"><f:message key="companyDesc"/>:</td>
								    <td colspan="1" class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="150" path="businessUnit.companyDesc"/>
								    <form:errors path="businessUnit.companyDesc" cssClass="redstar"/>
									</td> 
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
												<th colspan="1"><f:message key="country"/></th>
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
															<form:select cssClass="selectionBox" path="busUnitVatLocs[${counter.index}].country.countryCode" items="${icbfn:dropdown('country', null)}" itemLabel="name" itemValue="value" />
														</td>
														 <td class="TDShadeBlue">
														 <!-- START : #119240 : 06/07/09 -->
													<%-- <form:input cssClass="inputBox" size="35"  maxlength="12" 	path="busUnitVatLocs[${counter.index}].vatRegistrationId"/>
														  <form:errors path="busUnitVatLocs[${counter.index}].vatRegistrationId" cssClass="redstar"/> --%>

														  <form:input cssClass="inputBox" size="35"  maxlength="12" path="busUnitVatLocs[${counter.index}].busUnitVatLocId.vatRegistrationId"/>
														  <form:errors path="busUnitVatLocs[${counter.index}].busUnitVatLocId.vatRegistrationId" cssClass="redstar"/>

														<!-- END : #119240 : 06/07/09 -->	
														  </td>
														   <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="15" path="busUnitVatLocs[${counter.index}].localVatId"/>
														  <form:errors path="busUnitVatLocs[${counter.index}].localVatId" cssClass="redstar"/>
														  </td>
														<td class="TDShadeBlue">
														<form:select id="sel${counter.index}" cssClass="selectionBox" path="busUnitVatLocs[${counter.index}].outOfScope" items="${icbfn:dropdown('staticDropdown',yesNo)}" itemLabel="name" itemValue="value" />
										   				<form:errors path="busUnitVatLocs[${counter.index}].outOfScope" cssClass="redstar"/>
														  </td>
														<td class="TDShadeBlue" colspan="2" width="70">      
															<form:checkbox   path="busUnitVatLocs[${counter.index}].defaultLocInd" />
														</td>
												    <td width="10" class="TDShadeBlue" style="text-align:right;"><div id="div3" style="width:50px; text-align:center; margin-right:0;"> <a href="#"><img src="images/delete.gif" alt="<f:message key="deleteVatLocation"/>" width="13" height="12" hspace="1px" border="0" onclick="onDelete('${counter.index}')" /></a> </div></td>

														
													</tr>

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
																	<td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="defaultLocation"/><f:message key="markedfiledsaremdtry"/></span> </td>
																	<td style="text-align:right"><a href="search_business_unit.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0"></a></td>
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

