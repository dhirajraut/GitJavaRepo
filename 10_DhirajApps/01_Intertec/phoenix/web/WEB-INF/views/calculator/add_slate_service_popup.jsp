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
  function selectAllSlates(rowCount)   
  {
    for(i=0;i<rowCount;i++)
    {
      var elemId = "selectedSlates[" + i + "].selected1";
      document.getElementById(elemId).checked = true;
    }
  }

  function clearAllSlates(rowCount)    
  {
    for(i=0;i<rowCount;i++)
    {
      var elemId = "selectedSlates[" + i + "].selected1";
      document.getElementById(elemId).checked = false;
    }
  }
  
  function doSearch()
  {
    document.addSlateServicePopUpForm.operation.value = "search";
    document.addSlateServicePopUpForm.submit();
  }  

  function doSubmit(operation)
  {
    document.addSlateServicePopUpForm.operation.value = operation;
    document.addSlateServicePopUpForm.submit();
  }  

</script>

<form:form name="addSlateServicePopUpForm" method="POST" action="add_slate_service_popup.htm">

  <input type="hidden" name="pageNumber" value="1" />
  <input type="hidden" name="operation" value="search" />
  <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
    <form:errors cssClass="error"/>
  </div>

  <table border="0" cellpadding="0" cellspacing="0" class="MainTable">
    <tr>
      <td valign="top">
        <div id="tab1" class="innercontent" >
          <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
            <tr>
              <td><strong><f:message key="searchFor"/>: </strong></td>
              <td><form:input cssClass="inputBox" size="25" path="searchText"/>
                <form:errors path="searchText" cssClass="redstar"/>&nbsp;&nbsp;in&nbsp;&nbsp;
              </td>
              <td>
                <form:radiobutton path="searchType" value="ID"/><f:message key="slateId"/>&nbsp;&nbsp;&nbsp;&nbsp;
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
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2">
                <input name="Button" type="button" class="button1" value="<f:message key="search"/>" onclick="doSearch();" />&nbsp;&nbsp;
                <input name="Button" type="button" class="button1" value="Cancel" onClick="javascript:doSubmit('return');" />
              </td>
            </tr>
          </table>
          <c:if test="${requestScope.slates_added != null}">
            <div style="padding:5 5 5 5px;color:green;">
              <spring:message code="slates_added" />
              ${requestScope.slates_added}
            </div>
          </c:if>
          <br />
          <c:if test="${fn:length(command.selectedSlates) > 0}">
            <div id="slatessearchresults"><!--Search Results -->
              <br>&nbsp;&nbsp;<strong><f:message key="searchResults"/></strong>
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
                </tr>
                <c:forEach items="${command.selectedSlates}" var="selectedSlate" varStatus="selectedSlateCounter">
                  <tr>
                    <td>
                      <form:checkbox path="selectedSlates[${selectedSlateCounter.index}].selected" />${selectedSlate.slate.slateId }
                    </td>
                    <td>${selectedSlate.slate.slateDescription }</td>
                    <td>
                      <form:input path="selectedSlates[${selectedSlateCounter.index}].qty" size="10"/>
                      <form:errors path="selectedSlates[${selectedSlateCounter.index}].qty" cssClass="redstar"/>
                    </td>
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
            </div><!--Search Results -->
          </c:if>
        </div>
      </td>
    </tr>
  </table>

</form:form>


