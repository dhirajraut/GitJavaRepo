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
  
  function doSearch(myPageNumber)
  {
    document.CopyPbTestPricePopUpForm.operation.value = "search";
    document.CopyPbTestPricePopUpForm.searchPageNumber.value = myPageNumber;
    document.CopyPbTestPricePopUpForm.submit();
  }  

  function doSubmit(operation)
  {
    document.CopyPbTestPricePopUpForm.operation.value = operation;
    document.CopyPbTestPricePopUpForm.submit();
  }  
</script>

<form:form name="CopyPbTestPricePopUpForm" method="POST" action="copy_pb_test_price_popup.htm">    
  <input type="hidden" name="searchPageNumber" value="1" />
  <input type="hidden" name="operation" value="search" />
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
    <tr>
      <td width="25%"><strong><f:message key="methodology"/>:</strong></td>
      <td width="100">
        <form:input cssClass="inputBox" size="25" path="testId"/>
        <form:errors path="testId" cssClass="redstar"/>
      </td>
    </tr>
    <tr>
      <td><strong><f:message key="description"/> : </strong></td>
      <td>
        <form:input cssClass="inputBox" size="25" path="description"/>
        <form:errors path="description" cssClass="redstar"/>
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

  <c:if test="${command.selectedTests != null}">       
    <div id="testssearchresults"><!--Search Results -->
      <br>&nbsp;&nbsp;<strong><f:message key="searchResults"/></strong>
      <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%; margin-bottom:15px;">
        <tr>
          <td colspan="2">
            <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
              <c:choose>
                <c:when test="${page.selected}">${page.name}</c:when>
                <c:otherwise>
                  <a href="#start" onClick="doSearch('${page.pageNumber}')">${page.name} </a>&nbsp;
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </td>
        </tr>
        <tr>
          <th><f:message key="methodology"/></th>
          <th><f:message key="description"/></th>
        </tr>

        <tr>
          <td colspan="2">
            <a href="#" onClick="selectAllTests(${fn:length(command.selectedTests)})"><f:message key="checkAll"/></a>&nbsp;&nbsp;
            <a href="#" onClick="clearAllTests(${fn:length(command.selectedTests)})"><f:message key="clearAll"/></a>
          </td>
        </tr>
        <c:forEach items="${command.selectedTests}" var="selectedTest" varStatus="counter">
          <tr>
            <td><form:checkbox path="selectedTests[${counter.index}].selected" />${selectedTest.test.testId }</td>
            <td>${selectedTest.test.testDescription } </td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="2">
            <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submitAndReturn');" value="Save and Return" />      
            <input name="Submit3" type="button" class="button1" onclick="javascript:doSubmit('submit');" value="Save" />
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

