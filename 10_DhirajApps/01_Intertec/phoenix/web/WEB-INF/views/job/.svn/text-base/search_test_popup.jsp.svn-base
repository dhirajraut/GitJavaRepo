<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><head>
 <script language="javascript">
 function setSelectedTestIds(rowCount,searchFlag)
 {
	
	
	for(i=0;i<rowCount;i++)
	 {
		var elemName = "chk" + i;
		if(document.getElementById(elemName).checked)
		 {
			
			if(document.testSearchPopUpForm.chosenTestIds.value == "")
			{
				document.testSearchPopUpForm.chosenTestIds.value = document.getElementById(elemName).value;
			}
			else
			{
				var existingVal = document.testSearchPopUpForm.chosenTestIds.value;
				document.testSearchPopUpForm.chosenTestIds.value = existingVal + ";" + document.getElementById(elemName).value;
			}
			document.getElementById(elemName).checked = false;
		 }
	 }
	 if(searchFlag == "addtest")
	 {
	 	document.testSearchPopUpForm.pageNumber.value = document.testSearchPopUpForm.pageNo.value;
		document.testSearchPopUpForm.submit();
	 }
	 
 }

	  		function submitform()
		{
			
			var chosenVal = document.testSearchPopUpForm.chosenTestIds.value;
			top.document.forms[0].chosenTestIds.value = chosenVal;
			top.document.forms[0].submit();
			
		}

function selectAllTests(rowCount)		
{
	for(i=0;i<rowCount;i++)
	 {
	 var elemName = "chk" + i;
	 document.getElementById(elemName).checked = true;
	 }
	 
}

function clearAllTests(rowCount)		
{
	for(i=0;i<rowCount;i++)
	 {
	 var elemName = "chk" + i;
	 document.getElementById(elemName).checked = false;
	 }
}
function sortTestSearcById(){
document.testSearchPopUpForm.pageNumber.value = "1";
document.testSearchPopUpForm.sortFlag.value = "test.testId";
document.testSearchPopUpForm.submit();
}
function sortTestSearcByDescription(){
document.testSearchPopUpForm.pageNumber.value = "1";
document.testSearchPopUpForm.sortFlag.value = "test.testDescription";
document.testSearchPopUpForm.submit();
}
function sortTestSearcByContractRef(){
document.testSearchPopUpForm.pageNumber.value = "1";
document.testSearchPopUpForm.sortFlag.value = "contractRef";
document.testSearchPopUpForm.submit();
}
function submitFunction()
{
	document.testSearchPopUpForm.pageNumber.value = "1";
	document.testSearchPopUpForm.sortFlag.value = "";
	document.testSearchPopUpForm.submit();
}
 </script>
</head>
<icb:list var="jobContracts">
  <icb:item>${command.chosenContracts}</icb:item>
</icb:list>
 <form:form name="testSearchPopUpForm" method="POST" action="search_test_popup.htm">
    
	<form:hidden path="searchType"/>
	<form:hidden path="searchForm"/>
	<form:hidden path="inputFieldId"/>
	<form:hidden path="rowNum"/>
	<form:hidden path="chosenContracts"/>
	<form:hidden path="chosenTestIds"/>
	<form:hidden path="divName1"/>
	<form:hidden path="divName2"/>
	<form:hidden path="submitFlag"/>
	<form:hidden path="pageNo"/>
	<form:hidden path="sortFlag"/>

	 

<a href="#inst"></a>
	 <input type="hidden" name="pageNumber" value="1" />
	<div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
		<form:errors cssClass="error"/>
	</div>

      
    
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="mainApplTable" style="margin-bottom:10px;">
      
        <tr>
          <td width="25%"><strong><f:message key="selectGroup"/> : </strong></td>
          <td width="100">
          <form:select id="sel1" cssClass="selectionBox" path="productGroup.value" items="${icbfn:dropdown('testProductGroup', null)}" itemLabel="name" itemValue="value" />
		&nbsp;</td>
          <td nowrap>
          <form:radiobutton path="criteria1.value" value="PB"/>
                      <f:message key="statusPBK"/>&nbsp;</td>
          <td nowrap>
          <form:radiobutton path="criteria1.value" value="CONTR"/>
		  <f:message key="contract"/>&nbsp;&nbsp;</td>
		  <td nowrap>
          <form:select id="sel2" cssClass="selectionBox" path="jobContractId" items="${icbfn:dropdown('contract', jobContracts)}" itemLabel="name" itemValue="value" />&nbsp;&nbsp;</td>
          <td nowrap>
          <form:radiobutton path="criteria1.value" value="BOTH"/>
<f:message key="both"/></td>
        </tr>
        <tr>
          <td><strong><f:message key="searchFor"/> : </strong></td>
          <td>
          <form:input cssClass="inputBox" size="25" path="testSearch.value"/>
		  <form:errors path="testSearch.value" cssClass="redstar"/>
		  
          
          &nbsp;&nbsp;<f:message key="in"/>&nbsp;&nbsp;</td>
          <td nowrap>
          <form:radiobutton path="criteria2.value" value="ID"/>
          <f:message key="methodology"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td nowrap>
          <form:radiobutton path="criteria2.value" value="DESC"/>
          <f:message key="description"/></td>
			<td>
          &nbsp;&nbsp;</td>
          <td nowrap>
          <form:radiobutton path="criteria2.value" value="BOTH"/>
          <f:message key="both"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="5"><input name="button" type="button" class="button1" onClick="submitFunction()" value="<f:message key="search"/>"  />
            &nbsp;&nbsp;
            <input name="Button" type="button" class="button1" value="Cancel" onClick="javascript:top.hidePopupDiv('test','testbody');top.popupboxclose();" /></td>
        </tr>
	    </table>
	    
	    <c:if test="${command.chosenTestIds != null && command.chosenTestIds != ''}">  
	   		<div style="width:auto;float:left;padding:5 5 5 40px;color:green;">
				<f:message key="testSuccessfullyAdded"/>
				<span style="word-wrap:break-word">: ${command.chosenTestIds }</span>
			</div>
		</c:if>
		
		
		<c:if test="${command.results != null}">       
	  <div id="testssearchresults"><!--Search Results -->
	  <br>&nbsp;&nbsp;<strong><f:message key="searchResults"/>(<span style="color:green;"><f:message key="numberoftestsreturnedislimitedto300"/></span>)</strong>
	  <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%; margin-bottom:15px;">
	          
		<tr>
          <td colspan="4">
          <input name="Button" type="button" class="button1" value="Submit and Return"  onClick="javascript:setSelectedTestIds(${fn:length(command.results)},'returntojob');submitform();top.hidePopupDiv('test','testbody');top.popupboxclose();"/>&nbsp;
          <input name="Button" type="button" class="button1" value="Submit" onClick="setSelectedTestIds(${fn:length(command.results)},'addtest')"/>
          &nbsp;
          <input name="Button" type="button" class="button1" value="Return" onClick="javascript:submitform();top.hidePopupDiv('test','testbody');top.popupboxclose();"/>
          &nbsp;&nbsp;
          </td>
          </tr>
        <tr>
          <th><a href="#start" onClick="sortTestSearcById();" ><f:message key="methodology"/></a></th>
          <th><a href="#start" onClick="sortTestSearcByDescription();" ><f:message key="description"/></a></th>
		  <th><a href="#start" onClick="sortTestSearcByContractRef();" ><f:message key="pB/CONT"/></a></th>
          </tr>
        
        <tr>
          <td colspan="4"><a href="#" onClick="selectAllTests(${fn:length(command.results)})"><f:message key="checkAll"/></a>&nbsp;&nbsp;
          <a href="#" onClick="clearAllTests(${fn:length(command.results)})"><f:message key="clearAll"/></a></td>
          </tr>
          <c:forEach items="${command.results}" var="tests" varStatus="counter">
		 <c:choose>
					<c:when test="${counter.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
          <td><INPUT TYPE="checkbox" NAME="chk${counter.index}" id="chk${counter.index}"  value="${tests.test.testId}">${tests.test.testId }</td>
          <td>${tests.test.testDescription }</td>
		  <td>
		  ${tests.contractRef}
		  		  </td>
        </tr>
          </c:forEach>

        <tr>
          <td colspan="4" height="26"><f:message key="selectanaction"/> :</td>
          </tr>
		<tr>
          <td colspan="4">
                    <input name="Button" type="button" class="button1" value="Submit and Return"  onClick="javascript:setSelectedTestIds(${fn:length(command.results)},'returntojob');submitform();top.hidePopupDiv('test','testbody');top.popupboxclose();"/>&nbsp;
          <input name="Button" type="button" class="button1" value="Submit" onClick="setSelectedTestIds(${fn:length(command.results)},'addtest')"/>&nbsp;
          
          <input name="Button" type="button" class="button1" value="Return" onClick="javascript:submitform();top.hidePopupDiv('test','testbody');top.popupboxclose();"/>
          &nbsp;
          <%--<a href="add_manual_test.htm?div1=${command.divName1 }&div2=${ command.divName2}"><f:message key="addManualTest"/></a>--%></td>
          </tr>
        </c:if>   
	 <tr>
<br>
<br>
	 <td>&nbsp;</td>
	 <td align="center">
	
	<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
	<SCRIPT LANGUAGE="JavaScript">

	function submitSearch(pageNumber)
				  {	 
					 
					document.testSearchPopUpForm.pageNumber.value = pageNumber;
					document.testSearchPopUpForm.submit();
				  }	

	</SCRIPT>
		<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name}&nbsp;&nbsp;&nbsp; </a>
	</c:forEach>
	</td>
	</tr>          
      </table>
      		
</form:form>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src=" images/fake-alpha-999.gif"> </div>

