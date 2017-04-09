<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language="javascript">
  function selectAllTests(rowCount)   
  {
    for(i=0;i<rowCount;i++)
    {
      var elemId = "selectedTests[" + i + "].selected1";
      document.getElementById(elemId).checked = true;
    }
  }

  function clearAllTests(rowCount)    
  {
    for(i=0;i<rowCount;i++)
    {
      var elemId = "selectedTests[" + i + "].selected1";
      document.getElementById(elemId).checked = false;
    }
  }
  
  function doSearch()
  {
    document.addTestServicePopUpForm.operation.value = "search";
    document.addTestServicePopUpForm.submit();
  }  

  function doSubmit(operation)
  {
    document.addTestServicePopUpForm.operation.value = operation;
    document.addTestServicePopUpForm.submit();
  }  
</script>

<form:form name="addTestServicePopUpForm" method="POST" action="add_test_service_popup.htm">    
  <input type="hidden" name="pageNumber" value="1" />
  <input type="hidden" name="operation" value="search" />
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <td width="25%"><strong><f:message key="selectGroup"/>:</strong></td>
      <td width="100">
        <form:select id="sel1" cssClass="selectionBox" path="productGroup" items="${icbfn:dropdown('testProductGroup', null)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {doSearch();}" />&nbsp;
      </td>
      <td>
        <form:radiobutton path="contractSearchCD" value="PB"/><f:message key="statusPBK"/>&nbsp;
      </td>
      <td>
        <form:radiobutton path="contractSearchCD" value="CONTR"/><f:message key="CONTRACT"/>&nbsp;&nbsp;
      </td>
      <td>
        <form:radiobutton path="contractSearchCD" value="BOTH"/><f:message key="both"/>
      </td>
    </tr>
    <tr>
      <td><strong><f:message key="searchFor"/> : </strong></td>
      <td>
        <form:input cssClass="inputBox" size="25" path="searchText" onkeypress="if(event.keyCode==13) {doSearch();}"/>
        <form:errors path="searchText" cssClass="redstar"/>
        &nbsp;&nbsp;in&nbsp;&nbsp;
      </td>
      <td>
        <form:radiobutton path="searchType" value="ID"/><f:message key="methodology"/>&nbsp;&nbsp;&nbsp;&nbsp;
      </td>
      <td>
        <form:radiobutton path="searchType" value="DESC"/><f:message key="description"/>
      </td>
      <td>
        <form:radiobutton path="searchType" value="BOTH"/><f:message key="both"/>
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td colspan="4">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="5">
        <input name="Button" type="button" class="button1" value="<f:message key="search"/>" onclick="doSearch();" />&nbsp;&nbsp;
        <input name="Button" type="button" class="button1" value="Cancel" onClick="javascript:doSubmit('return');" />
      </td>
    </tr>
  </table>

  <c:if test="${requestScope.tests_added != null}">
    <div style="padding:5 5 5 5px;color:green;">
      <spring:message code="tests_added" />
      <span style="word-wrap:break-word">${requestScope.tests_added}</span>
    </div>
  </c:if>

  <c:if test="${command.selectedTests != null}">       
    <div id="testssearchresults"><!--Search Results -->
      <br>&nbsp;&nbsp;<strong><f:message key="searchResults"/> (<span style="color:green;"><f:message key="numberoftestsreturnedislimitedto300"/></span>)</strong>
      <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%; margin-bottom:15px;">
        <tr>
          <td colspan="4">
            <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submitAndReturn');" value="Submit and Return" />      
            <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submit');" value="Submit" />
            <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('return');" value="Return" />      
          </td>
        </tr>
        <tr>
          <th><f:message key="methodology"/></th>
          <th><f:message key="description"/></th>
          <th><f:message key="quantity"/></th>
          <th><f:message key="pB/CONT"/></th>
        </tr>

        <tr>
          <td colspan="4">
            <a href="#" onClick="selectAllTests(${fn:length(command.selectedTests)})"><f:message key="checkAll"/></a>&nbsp;&nbsp;
            <a href="#" onClick="clearAllTests(${fn:length(command.selectedTests)})"><f:message key="clearAll"/></a>
          </td>
        </tr>
        <c:forEach items="${command.selectedTests}" var="selectedTest" varStatus="counter">
          <tr>
            <td><form:checkbox path="selectedTests[${counter.index}].selected" />${selectedTest.test.testId }</td>
            <td>${selectedTest.test.testDescription } </td>
            <td>
              <form:input path="selectedTests[${counter.index}].qty" size="10"/>
              <form:errors path="selectedTests[${counter.index}].qty" cssClass="redstar"/>
            </td>
            <td>${selectedTest.contractRef}</td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="4">
            <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submitAndReturn');" value="Submit and Return" />      
            <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submit');" value="Submit" />
            <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('return');" value="Return" />      
          </td>
        </tr>
      </table>
    </div>
  </c:if> 
</form:form>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
  <IMG src=" images/fake-alpha-999.gif"> 
</div>

