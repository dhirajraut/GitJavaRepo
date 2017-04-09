<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<head>
<script language="javascript">
	function languageChange()
	{
		document.createMultiligualBunitForm.languageFlag.value="true"
		document.createMultiligualBunitForm.lcode.value=document.getElementById("langcode").value;
		document.createMultiligualBunitForm.submit()
	}

</script>
</head>


<icb:list var="selectedLanguage">
  <icb:item>selectedLanguage</icb:item>
</icb:list>

<form:form name="createMultiligualBunitForm" method="POST" action="create_multilingual_bunit_popup.htm">
<form:hidden path="searchForm" /> 
 <form:hidden path="divName"/>
 <form:hidden path="divBody"/>
<input type="hidden" name="lcode" value="1" />
<input type="hidden" name="languageFlag" value="false" />
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>

              <table width="100%" cellpadding=0 cellspacing=0  border="0" class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=2 width="50%"><f:message key="multilingualBunit"/></th>
                    
                    <th colspan=2 width="50%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                  </tr>
                
				  <tr>
                    <td  class="TDShade"><f:message key="businessUnitId"/>:<span class="redstar">*</span></td>
					<td  class="TDShadeBlue">
					 <form:input cssClass="inputBox" size="35"  maxlength="5" path="businessUnitLanguage.businessUnitLanguageId.name" disabled="true"/>
						<form:errors path="businessUnitLanguage.businessUnitLanguageId.name" cssClass="redstar"/>
					</td>
                   
					
					<td class="TDShade"><f:message key="languageCode"/> <strong> <span class="redstar">*</span>:</strong> </td>
                    <td  class="TDShadeBlue">
                       <form:select  id="langcode" cssClass="selectionBox" path="businessUnitLanguage.businessUnitLanguageId.languageCD" items="${icbfn:dropdown('staticDropdown',selectedLanguage)}" itemLabel="name" itemValue="value" onchange="languageChange();"/>
                      <form:errors path="businessUnitLanguage.businessUnitLanguageId.languageCD" cssClass="redstar"/>
                    </td>
                  </tr>


                  <tr>
                    <td class="TDShade"><f:message key="repDirector"/>:</td>
                    <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="64"  path="businessUnitLanguage.repDirector"/>
					<form:errors path="businessUnitLanguage.repDirector" cssClass="redstar"/></td>
                    

					 <td class="TDShade"><f:message key="businessType"/>:</td>
                    <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="128"  path="businessUnitLanguage.businessType"/>
					<form:errors path="businessUnitLanguage.businessType" cssClass="redstar"/></td>

                   </tr>

                  
					 
					 <tr>

                   <td class="TDShade"><f:message key="businessItem"/>:</td></td>
                    <td  class="TDShadeBlue">
                     <form:input cssClass="inputBox" size="35"  maxlength="128" path="businessUnitLanguage.businessItem"/>
					<form:errors path="businessUnitLanguage.businessItem" cssClass="redstar"/></td>
                    </td>


					<td class="TDShade"><f:message key="businessUnitName"/>:</td></td>
                    <td  class="TDShadeBlue">
                     <form:input cssClass="inputBox" size="35"  maxlength="512" path="businessUnitLanguage.description"/>
					<form:errors path="businessUnitLanguage.description" cssClass="redstar"/></td>
                    </td>
                  </tr>


                 
				 <tr>
                    <td class="TDShade"><f:message key="address1"/>: </td>
                    <td class="TDShadeBlue">					
			      		<form:input cssClass="inputBox" size="35"  maxlength="55" path="businessUnitLanguage.address1" />
                      <form:errors path="businessUnitLanguage.address1" cssClass="redstar"/>					  
					  </td>



                   <td class="TDShade"><f:message key="address2"/>: </td>
                    <td  class="TDShadeBlue">					
			      		<form:input cssClass="inputBox" size="35"  maxlength="55" path="businessUnitLanguage.address2" />
                      <form:errors path="businessUnitLanguage.address2" cssClass="redstar"/>					  
					  </td>
                  </tr>


                  <tr>
                    <td class="TDShade"><f:message key="address3"/>: </td>
                    <td class="TDShadeBlue">					
			      		<form:input cssClass="inputBox" size="35"  maxlength="55" path="businessUnitLanguage.address3" />
                      <form:errors path="businessUnitLanguage.address3" cssClass="redstar"/>					  
					  </td>
                     <td class="TDShade"><f:message key="address4"/>: </td>
                    <td class="TDShadeBlue">					
			      		<form:input cssClass="inputBox" size="35"  maxlength="55" path="businessUnitLanguage.address4" />
                      <form:errors path="businessUnitLanguage.address4" cssClass="redstar"/>					  
					  </td>
                  </tr>





                  <tr>
                       <td class="TDShade"><f:message key="city"/>:</td>
                         <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="30" path="businessUnitLanguage.city" />
                         <form:errors path="businessUnitLanguage.city" cssClass="redstar"/>
                      </td>


                       <td class="TDShade"><f:message key="county"/>:</td>
                           <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35" path="businessUnitLanguage.county" />
                          <form:errors path="businessUnitLanguage.county" cssClass="redstar"/>	
					   </td>
                  </tr>



                
				<tr>
				<td class="TDShade"><f:message key="postal"/>:</td>
				<td  class="TDShadeBlue">
				<form:input cssClass="inputBox" size="35"  maxlength="12" path="businessUnitLanguage.postal" />
				<form:errors path="businessUnitLanguage.postal" cssClass="redstar"/>			
				</td> 

				<td class="TDShade"><f:message key="companyDesc"/>:</td>
				<td colspan="1" class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="150" path="businessUnitLanguage.companyDesc"/>
				<form:errors path="businessUnitLanguage.companyDesc" cssClass="redstar"/></td>
				</tr>

				  		
                  <tr>
		<td class="TDShade"><input type="submit"  class="button1" value="Save"/>
	    <a href="#"><input name="Submit3" type="button" class="button1" value="Cancel" onclick="javascript:top.hidePopupDiv('${command.divName}','${command.divBody}');top.popupboxclose();"/></a></td>
      <td class="TDShade">&nbsp;</td>
      <td colspan="3" class="TDShade">&nbsp;</td>
    </tr>
  </tbody>
</table>
 
</form:form>

