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
		document.createMultiligualBranchForm.languageFlag.value="true"
		document.createMultiligualBranchForm.lcode.value=document.getElementById("langcode").value;
		document.createMultiligualBranchForm.submit()
	}

</script>
</head>


<icb:list var="selectedLanguage">
  <icb:item>selectedLanguage</icb:item>
</icb:list>

<form:form name="createMultiligualBranchForm" method="POST" action="create_multilingual_branch_popup.htm">
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
                    <th colspan=2 width="50%"><f:message key="multilingualBranch"/></th>
                    
                    <th colspan=2 width="50%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                  </tr>
                
				  <tr>
                    <td class="TDShade"><f:message key="branchName"/> <strong> <span class="redstar">*</span>:</strong></td>
                    <td class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="8" path="branchLanguage.branchLanguageId.name" disabled="true" />
                      <form:errors path="branchLanguage.branchLanguageId.name" cssClass="redstar"/>
                    </td>
                   
					
					<td class="TDShade"><f:message key="languageCode"/> <strong> <span class="redstar">*</span>:</strong> </td>
                    <td  class="TDShadeBlue">
                       <form:select id="langcode" cssClass="selectionBox" path="branchLanguage.branchLanguageId.languageCD" items="${icbfn:dropdown('staticDropdown',selectedLanguage)}" itemLabel="name" itemValue="value" onchange="languageChange();"/>
                      <form:errors path="branchLanguage.branchLanguageId.languageCD" cssClass="redstar"/>
                    </td>
                  </tr>


                  <tr>
                    <td class="TDShade"><f:message key="branchDescription"/>:</td>
                    <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="512"  path="branchLanguage.description"/></td>


                   
					<td class="TDShade"><f:message key="companyDesc"/>:</td></td>
                    <td  class="TDShadeBlue">
                     <form:input cssClass="inputBox" size="35"  maxlength="150" path="branchLanguage.companyDesc"/>
					<form:errors path="branchLanguage.companyDesc" cssClass="redstar"/></td>
                    </td>
                  </tr>


                  <tr>
                    <td class="TDShade"><f:message key="address1"/>: </td>
                    <td class="TDShadeBlue">					
			      		<form:input cssClass="inputBox" size="35"  maxlength="55" path="branchLanguage.address1" />
                      <form:errors path="branchLanguage.address1" cssClass="redstar"/>					  
					  </td>



                   <td class="TDShade"><f:message key="address2"/>: </td>
                    <td  class="TDShadeBlue">					
			      		<form:input cssClass="inputBox" size="35"  maxlength="55" path="branchLanguage.address2" />
                      <form:errors path="branchLanguage.address2" cssClass="redstar"/>					  
					  </td>
                  </tr>


                  <tr>
                    <td class="TDShade"><f:message key="address3"/>: </td>
                    <td class="TDShadeBlue">					
			      		<form:input cssClass="inputBox" size="35"  maxlength="55" path="branchLanguage.address3" />
                      <form:errors path="branchLanguage.address3" cssClass="redstar"/>					  
					  </td>
                     <td class="TDShade"><f:message key="address4"/>: </td>
                    <td class="TDShadeBlue">					
			      		<form:input cssClass="inputBox" size="35"  maxlength="55" path="branchLanguage.address4" />
                      <form:errors path="branchLanguage.address4" cssClass="redstar"/>					  
					  </td>
                  </tr>





                  <tr>
                    <td class="TDShade"><f:message key="city"/>:</td>
                         <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="30" path="branchLanguage.city" />
                         <form:errors path="branchLanguage.city" cssClass="redstar"/>
                      </td>


                       <td class="TDShade"><f:message key="county"/>:</td>
                           <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35" path="branchLanguage.county" />
                          <form:errors path="branchLanguage.county" cssClass="redstar"/>	
					   </td>
                  </tr>



                
                  <tr>
                   <td class="TDShade"><f:message key="postal"/>:</td>
                    <td  class="TDShadeBlue">
					<form:input cssClass="inputBox" size="35"  maxlength="12" path="branchLanguage.postal" />
                      <form:errors path="branchLanguage.postal" cssClass="redstar"/>					

					  </td> 



                    <td class="TDShade">&nbsp;</td>
                    <td  class="TDShadeBlue">
                     &nbsp;
                    </td>
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

