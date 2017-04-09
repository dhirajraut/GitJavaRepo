<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><head>
 <script language="javascript">
 function setSelectedSlateIds(rowCount,searchFlag)
 {
	
	
	for(i=0;i<rowCount;i++)
	 {
		var elemName = "chk" + i;
		if(document.getElementById(elemName).checked)
		 {
			
			if(document.slateSearchPopUpForm.chosenSlateIds.value == "")
			{
				document.slateSearchPopUpForm.chosenSlateIds.value = document.getElementById(elemName).value;
			}
			else
			{
				var existingVal = document.slateSearchPopUpForm.chosenSlateIds.value;
				document.slateSearchPopUpForm.chosenSlateIds.value = existingVal + ";" + document.getElementById(elemName).value;
			}
			document.getElementById(elemName).checked = false;
		 }
	 }
	 if(searchFlag == "addslate")
	 {
	 	document.slateSearchPopUpForm.pageNumber.value = document.slateSearchPopUpForm.pageNo.value;
		document.slateSearchPopUpForm.submit();
	 }
 }

	  		function submitform()
		{
			
			var chosenVal = document.slateSearchPopUpForm.chosenSlateIds.value;
			top.document.forms[0].chosenSlateIds.value = chosenVal;
			top.document.forms[0].submit();
			
		}
	   

   </script>
</head>
<icb:list var="jobContracts">
  <icb:item>${command.chosenContracts}</icb:item>
</icb:list>
 <form:form name="slateSearchPopUpForm" method="POST" action="search_slate_popup.htm">
    
	<form:hidden path="searchType"/>
	<form:hidden path="searchForm"/>
	<form:hidden path="inputFieldId"/>
	<form:hidden path="rowNum"/>
	<form:hidden path="chosenContracts"/>
	<form:hidden path="chosenSlateIds"/>
	<form:hidden path="divName1"/>
	<form:hidden path="divName2"/>
	<form:hidden path="submitFlag"/>
	<form:hidden path="pageNo"/>
		
	 

<a href="#inst"></a>
	 <input type="hidden" name="pageNumber" value="1" />
	<div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
		<form:errors cssClass="error"/>
	</div>


	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
        <tr>
          <td><strong><f:message key="searchFor"/> : </strong></td>
          <td><form:input cssClass="inputBox" size="25" path="searchStr.value"/>
		  <form:errors path="searchStr.value" cssClass="redstar"/>
          &nbsp;&nbsp;<f:message key="in"/>&nbsp;&nbsp;
          </td>
          <td nowrap>
          <form:radiobutton path="criteria.value" value="ID"/>
          <f:message key="slateId"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td nowrap>
          <form:radiobutton path="criteria.value" value="DESC"/>
		<f:message key="description"/></td>
          <td nowrap>
          <form:radiobutton path="criteria.value" value="BOTH"/>
		<f:message key="both"/></td>
		<td nowrap>
          <form:select id="sel2" cssClass="selectionBox" path="jobContractId" items="${icbfn:dropdown('contract', jobContracts)}" itemLabel="name" itemValue="value" />&nbsp;&nbsp;
		</td>
		
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2"><input name="Submit" type="submit" class="button1" value="<f:message key="search"/>"  />
            &nbsp;&nbsp;
            <input name="Button" type="button" class="button1" value="Cancel" onClick="top.hidePopupDiv('slate','slatebody');top.popupboxclose();" /></td>
        </tr>
      </table>
      	 <table>
	    <tr><td>
	    <div style="width:auto;float:left;padding:5 5 5 40px;color:green;">
			<c:if test="${command.chosenSlateIds != null && command.chosenSlateIds != ''}">     
				<f:message key="slateSuccessfullyAdded"/>: ${command.chosenSlateIds }
			</c:if>
			</div>
		</td></tr>
		</table>
				<br />
			      <c:if test="${command.results != null}">
			      
	<div id="slatessearchresults"><!--Search Results -->
	  <br>&nbsp;&nbsp;<strong><f:message key="searchResults"/></strong>
	  <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%; margin-bottom:15px;">
	  
		<tr>
          <td colspan="4">
          <input name="Button" type="button" class="button1" value="Submit and Return" onClick="javascript:setSelectedSlateIds(${fn:length(command.results)},'returntojob');submitform();top.hidePopupDiv(${command.divName1},${command.divName2});top.popupboxclose();"/>
          &nbsp;<input name="Button" type="button" class="button1" value="Submit" onClick="setSelectedSlateIds(${fn:length(command.results)},'addslate')"/>
          &nbsp;<input name="Button" type="button" class="button1" value="Return" onClick="javascript:submitform();top.hidePopupDiv(${command.divName1},${command.divName2});top.popupboxclose();"/>
          </td>
          
          </tr>
        <tr>
          <th><f:message key="methodology"/></th>
          <th><f:message key="description"/></th>
		  
          </tr>
          <c:forEach items="${command.results}" var="slates" varStatus="status">
		 <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
          <td>
          
          <INPUT TYPE="checkbox" NAME="chk${status.index }" id="chk${status.index }" value="${slates.slateId }">${slates.slateId }</td>
          <td>${slates.slateDescription }</td>
		  
          </tr>
        </c:forEach>
        <tr>
          <td colspan="4" height="26"><f:message key="selectanaction"/> :</td>
          </tr>
		<tr>
          <td colspan="4">
          <input name="Button" type="button" class="button1" value="Submit and Return" onClick="javascript:setSelectedSlateIds(${fn:length(command.results)},'returntojob');submitform();top.hidePopupDiv(${command.divName1},${command.divName2});top.popupboxclose();"/>
          &nbsp;<input name="Button" type="button" class="button1" value="Submit" onClick="setSelectedSlateIds(${fn:length(command.results)},'addslate')"/>
          &nbsp;<input name="Button" type="button" class="button1" value="Return" onClick="javascript:submitform();top.hidePopupDiv(${command.divName1},${command.divName2});top.popupboxclose();"/>
          </td>
          </tr>
      </table>
	  </div><!--Search Results -->
	  </c:if>
	 <tr>

	<td align="center">

	<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
	<SCRIPT LANGUAGE="JavaScript">

	function submitSearch(pageNumber)
				  {	 
					 
					document.slateSearchPopUpForm.pageNumber.value = pageNumber;
					document.slateSearchPopUpForm.submit();
				  }	

	</SCRIPT>
		<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>
	</c:forEach>
	</td>
	</tr>
	</table>
</div>

			
		</div>
	</td>
</tr>
</table>

</form:form>


